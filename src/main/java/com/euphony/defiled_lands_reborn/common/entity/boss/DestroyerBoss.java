package com.euphony.defiled_lands_reborn.common.entity.boss;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class DestroyerBoss extends Monster {
    private final ServerBossEvent bossEvent;

    private @Nullable EntityReference<Player> unlimitedLastHurtByPlayer;

    public DestroyerBoss(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.bossEvent = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    }

    private static final EntityDataAccessor<Integer> DATA_ID_INV = SynchedEntityData.defineId(DestroyerBoss.class, EntityDataSerializers.INT);;
    private static final EntityDataAccessor<Boolean> LEAPING = SynchedEntityData.defineId(DestroyerBoss.class, EntityDataSerializers.BOOLEAN);;

    private static final int INVULNERABILITY_TIME = 200;

    @Override
    protected boolean shouldDropLoot() {
        return false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_INV, 0);
        builder.define(LEAPING, false);
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
    }

    @Override
    public void addAdditionalSaveData(ValueOutput compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Invul", this.getInvulTime());
        compound.putBoolean("Leaping", this.isLeaping());
    }

    @Override
    public void readAdditionalSaveData(ValueInput compound) {
        super.readAdditionalSaveData(compound);
        this.setInvulTime(compound.getIntOr("Invul", 0));
        setLeaping(compound.getBooleanOr("Leaping", false));
        if (this.hasCustomName()) {
            this.bossEvent.setName(this.getDisplayName());
        }
    }

    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        this.bossEvent.setName(this.getDisplayName());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new DestroyerDoNothingGoal());
        // this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1.0, 120));
        this.goalSelector.addGoal(2, new DestroyerBigLeapGoal(this, 1.2f));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 200)
                .add(Attributes.FOLLOW_RANGE, 128)
                .add(Attributes.MOVEMENT_SPEED, 0.32D)
                .add(Attributes.ATTACK_DAMAGE, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.STEP_HEIGHT, 1);
    }

    @Override
    public boolean causeFallDamage(double p_397597_, float p_147187_, DamageSource p_147189_) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        if (!level().isClientSide && this.isLeaping() && onGround) {
            level().explode(this, getX(), getY(), getZ(), 3.0F, Level.ExplosionInteraction.MOB);
            setLeaping(false);
            Vec3 movement = this.getDeltaMovement();
            this.setDeltaMovement(new Vec3(movement.x, 0, movement.z));
            this.yya = 0;
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.lastHurtByPlayer != null) {
            this.unlimitedLastHurtByPlayer = this.lastHurtByPlayer;
        }

        if (this.unlimitedLastHurtByPlayer != null) {
            Player p = this.unlimitedLastHurtByPlayer.getEntity(this.level(), Player.class);
            if (p == null || p.isRemoved()) {
                this.unlimitedLastHurtByPlayer = null;
            }
        }

        Level level = level();
        if (getInvulTime() > 0) {
            this.setDeltaMovement(0, 0.01f, 0);
            if (level.isClientSide) {
                for (int i1 = 0; i1 < 3; ++i1) {
                    level.addParticle(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, 0.7F, 0.7F, 0.9F), getX() + random.nextGaussian(), getY() + random.nextFloat() * 3.3f, getZ() + random.nextGaussian(), 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D);
                }
            }
        }

        if(isLeaping() && isInWater()) {
            setLeaping(false);
        }
    }

    @Override
    protected void customServerAiStep(@NotNull ServerLevel level) {
        if (this.getInvulTime() > 0) {
            int j1 = this.getInvulTime() - 1;

            if (j1 <= 0) {
                level().explode(this, getX(), getEyeY(), getZ(), 7.0F, false, Level.ExplosionInteraction.TNT);
                this.playSound(SoundEvents.WITHER_SPAWN, 10.0F, 1.0F);
            }

            this.setInvulTime(j1);
        } else {
            this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
        }
    }

    @Override
    public int getMaxFallDistance() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity entity) {
        if (super.doHurtTarget(level, entity)) {
            entity.addDeltaMovement(new Vec3(0, 0.4000000059604645D, 0));

            return true;
        } else {
            return false;
        }
    }

    @Override
    public float getBlockExplosionResistance(Explosion explosion, BlockGetter level, BlockPos pos, BlockState blockState, FluidState fluidState, float explosionPower) {
        float f = super.getBlockExplosionResistance(explosion, level, pos, blockState, fluidState, explosionPower);

        if (!blockState.isAir() && !blockState.is(BlockTags.WITHER_IMMUNE)) {
            f = Math.min(2F, f);
        }

        return f;
    }

    @Override
    public boolean hurtServer(@NotNull ServerLevel level, DamageSource source, float amount) {
        if (!source.is(DamageTypes.DROWN) && !(source.getEntity() instanceof MournerBoss)) {
            if (this.getInvulTime() > 0 && !source.is(DamageTypes.FELL_OUT_OF_WORLD)) {
                return false;
            }

            return super.hurtServer(level, source, amount);
        } else {
            return false;
        }
    }

    public void makeInvulnerable() {
        this.setInvulTime(200);
    }

    @Override
    protected void tickDeath() {
        ++this.deathTime;

        if (this.deathTime >= 180 && this.deathTime <= 200) {
            float f = (random.nextFloat() - 0.5F) * 8.0F;
            float f1 = (random.nextFloat() - 0.5F) * 4.0F;
            float f2 = (random.nextFloat() - 0.5F) * 8.0F;
            level().addParticle(ParticleTypes.EXPLOSION, getX() + (double) f, getY() + 2.0D + (double) f1, getZ() + (double) f2, 0.0D, 0.0D, 0.0D);
        }

        int i = 50;

        if (!level().isClientSide) {
            if (this.deathTime > 150 && this.deathTime % 5 == 0) {
                int award = EventHooks.getExperienceDrop(this, EntityReference.get(this.unlimitedLastHurtByPlayer, level(), Player.class), Mth.floor((float)i * 0.08F));
                ExperienceOrb.award((ServerLevel)this.level(), this.position(), award);
            }
            if (this.deathTime == 1 && !this.isSilent()) {
                this.level().globalLevelEvent(1028, this.blockPosition(), 0);
                this.playSound(SoundEvents.ENDER_DRAGON_DEATH, 10.0F, 1.0F);
            }
        }

        this.setDeltaMovement(0, 0.01f, 0);
        if (this.deathTime >= 200) {
            if (!level().isClientSide) {
                if (this.unlimitedLastHurtByPlayer != null) {
                    int award = EventHooks.getExperienceDrop(this, EntityReference.get(this.unlimitedLastHurtByPlayer, level(), Player.class), Mth.floor((float) i * 0.2F));
                    ExperienceOrb.award((ServerLevel) this.level(), this.position(), award);
                    Player player = EntityReference.get(this.unlimitedLastHurtByPlayer, level(), Player.class);
                    if(player != null) {
                        this.dropFromLootTable((ServerLevel) level(), damageSources().playerAttack(player), true);
                    } else {
                        this.dropFromLootTable((ServerLevel) level(), damageSources().genericKill(), true);
                    }
                }
                this.remove(RemovalReason.KILLED);
                this.gameEvent(GameEvent.ENTITY_DIE);
            }

            for (int k = 0; k < 20; ++k) {
                double d2 = random.nextGaussian() * 0.02D;
                double d0 = random.nextGaussian() * 0.02D;
                double d1 = random.nextGaussian() * 0.02D;
                level().addParticle(ParticleTypes.EXPLOSION_EMITTER, getX() + (double) (random.nextFloat() * getBbWidth() * 2.0F) - getBbWidth(), getY() + (double) (random.nextFloat() * this.getBbHeight()), this.getZ() + (random.nextFloat() * this.getBbWidth() * 2.0F) - getBbWidth(), d2, d0, d1);
            }
        }
    }

    public boolean isLeaping() {
        return this.entityData.get(LEAPING);
    }

    public void setLeaping(boolean isLeaping) {
        this.entityData.set(LEAPING, isLeaping);
    }

    @Override
    public void setCustomNameVisible(boolean alwaysRenderNameTag) {
        super.setCustomNameVisible(alwaysRenderNameTag);
        this.bossEvent.setName(this.getDisplayName());
    }


    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.WITHER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WITHER_DEATH;
    }

    public int getInvulTime() {
        return this.entityData.get(DATA_ID_INV);
    }

    public void setInvulTime(int invulnerableTicks) {
        this.entityData.set(DATA_ID_INV, invulnerableTicks);
    }

    class DestroyerDoNothingGoal extends Goal {
        public DestroyerDoNothingGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return DestroyerBoss.this.getInvulTime() > 0;
        }
    }

    static class DestroyerBigLeapGoal extends Goal {
        private final DestroyerBoss destroyer;
        private final float leapYya;

        public DestroyerBigLeapGoal(DestroyerBoss destroyer, float leapYya) {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
            this.destroyer = destroyer;
            this.leapYya = leapYya;
        }

        @Override
        public boolean canUse() {
            LivingEntity livingEntity = destroyer.getTarget();
            if(livingEntity == null || !destroyer.onGround() || destroyer.isInWater()) return false;
            else {
                double d0 = destroyer.distanceToSqr(livingEntity);
                if (d0 >= 16.0D && d0 <= 1024.0D && destroyer.random.nextInt(7) == 0) return true;
                else return false;
            }
        }

        @Override
        public boolean canContinueToUse() {
            return false;
        }

        @Override
        public void start() {
            double d0 = destroyer.getTarget().getX() - destroyer.getX();
            double d1 = destroyer.getTarget().getZ() - destroyer.getZ();
            float f = Mth.sqrt((float) (d0 * d0 + d1 * d1));

            float enrage = 2.0F - destroyer.getHealth() / destroyer.getMaxHealth();
            Vec3 movement = destroyer.getDeltaMovement();
            double x = movement.x;
            double z = movement.z;
            if (f >= 1.0E-4F) {
                x = x * 1.4 + d0 / f * 1.5D * 0.800000011920929D;
                z = z * 1.4 + d1 / f * 1.5D * 0.800000011920929D;
            }
            x *= enrage;
            z *= enrage;
            Vec3 newMovement = new Vec3(x, leapYya, z);
            destroyer.setDeltaMovement(newMovement);
            this.destroyer.setLeaping(true);
            destroyer.getLookControl().setLookAt(destroyer.getTarget(), 10.0F, 10.0F);
        }
    }
}
