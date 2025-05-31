package com.euphony.defiled_lands_reborn.common.entity.boss;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.EnumSet;


public class MournerBoss extends Monster {
    private final ServerBossEvent bossEvent;

    private @Nullable EntityReference<Player> unlimitedLastHurtByPlayer;

    public MournerBoss(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
        this.moveControl = new MournerMoveControl(this);
        this.bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    }

    @Override
    protected @NotNull PathNavigation createNavigation(Level level) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        return flyingpathnavigation;
    }

    private static final EntityDataAccessor<Integer> DATA_ID_INV = SynchedEntityData.defineId(MournerBoss.class, EntityDataSerializers.INT);;

    private static final int INVULNERABILITY_TIME = 200;
    public static int attackType = 3;
    public static boolean isCharging = false;
    public static boolean isFiring = false;

    public static final int ATTACK_FIREBALLS = 0, ATTACK_SHULKER = 1, ATTACK_GHAST = 2;

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_INV, 0);
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
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Invul", this.getInvulTime());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setInvulTime(compound.getInt("Invul").get());
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
        this.goalSelector.addGoal(0, new MournerDoNothingGoal());
        // this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1.0, 120));
        this.goalSelector.addGoal(3, new ChargeAttackGoal(this));
        this.goalSelector.addGoal(4, new MournerRangedAttackGoal(this));
        this.goalSelector.addGoal(8, new MoveRandomGoal(this));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, LivingEntity.class, 8.0F));

        this.targetSelector.addGoal(1, new MournerLookForPlayerGoal(this));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 400)
                .add(Attributes.FOLLOW_RANGE, 128)
                .add(Attributes.ATTACK_DAMAGE, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Override
    protected int calculateFallDamage(double fallDistance, float damageMultiplier) {
        return 0;
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
            } else {
                int i = getInvulTime();
                if ((i > 60 && i % 40 == 0) || (i <= 60 && i % 10 == 0)) {
                    int y = (int)getY() + random.nextInt(11);
                    BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(getX() + random.nextInt(15) - 7, y, getZ() + random.nextInt(15) - 7);
                    while (pos.getY() > 0 && y - pos.getY() < 16 && level.getBlockState(pos).isAir()) {
                        pos.setY(pos.getY() - 1);
                    }

                    ((ServerLevel) level).setWeatherParameters(0, ServerLevel.THUNDER_DURATION.sample(level.random), true, true);
                    LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                    lightningBolt.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
                    lightningBolt.setVisualOnly(true);

                    level.addFreshEntity(lightningBolt);
                }
            }
        }

        if (level.isClientSide) {
            if (isFiring()) {
                SimpleParticleType particle = switch (attackType) {
                    case ATTACK_FIREBALLS -> ParticleTypes.FLAME;
                    case ATTACK_SHULKER -> ParticleTypes.END_ROD;
                    case ATTACK_GHAST -> ParticleTypes.LARGE_SMOKE;
                    default -> null;
                };

                for (int i = 0; i < 2; ++i) {
                    if(particle != null) {
                        level.addParticle(particle, getX() + (random.nextDouble() - 0.5) * getBbWidth(), getY() + random.nextDouble() * getBbHeight(), getZ() + (random.nextDouble() - 0.5) * getBbWidth(), 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        }
    }


    @Override
    protected void customServerAiStep(ServerLevel level) {
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

    static class MournerLookForPlayerGoal extends NearestAttackableTargetGoal<Player> {
        private final MournerBoss mourner;

        public MournerLookForPlayerGoal(MournerBoss mourner) {
            super(mourner, Player.class, false, false);
            this.mourner = mourner;
        }

        @Override
        public void tick() {
            if (this.target != null) {
                if (this.target.distanceToSqr(this.mourner) > 400.0F) {
                    this.mourner.teleportTowards(this.target);
                }
            }
        }
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity entity) {
        if (super.doHurtTarget(level, entity)) {
            int force = 6;

            if (entity instanceof LivingEntity) {
                ((LivingEntity)entity).knockback( (float)force * 0.5F, Math.sin(this.getYRot() * 0.017453292F), -Math.cos(this.getYRot() * 0.017453292F));
            } else {
                entity.setDeltaMovement(-Math.sin(this.getYRot() * 0.017453292F) * (float)force * 0.5F, 0.1D, Math.cos(this.getYRot() * 0.017453292F) * (float)force * 0.5F);
            }

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
    public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
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
    protected boolean shouldDropLoot() {
        return false;
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

        int i = 200;

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

    public boolean isCharging() {
        return isCharging;
    }
    public void setIsCharging(boolean value) {
        isCharging = value;
    }

    public boolean isFiring() {
        return isFiring;
    }
    public void setIsFiring(boolean value) {
        isFiring = value;
    }

    public int getCurrentAttack() {
        return attackType;
    }

    public void setCurrentAttack(int value) {
        attackType = value;
    }

    public int getRageFactor() {
        float h = getHealth();
        if (h < 100) return 3;
        else if (h < 200) return 2;
        else return 1;
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

    class MournerDoNothingGoal extends Goal {
        public MournerDoNothingGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return MournerBoss.this.getInvulTime() > 0;
        }
    }

    static class ChargeAttackGoal extends Goal {
        private MournerBoss mourner;

        public ChargeAttackGoal(MournerBoss mourner) {
            this.mourner = mourner;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return mourner.getTarget() != null && mourner.getTarget().isAlive()
                    && mourner.distanceToSqr(mourner.getTarget()) <= 16.0D;
        }

        @Override
        public boolean canContinueToUse() {
            return mourner.isCharging() && mourner.getTarget() != null && mourner.getTarget().isAlive()
                    && mourner.getMoveControl().hasWanted() && mourner.distanceToSqr(mourner.getTarget()) <= 25.0D;
        }

        @Override
        public void start() {
            LivingEntity livingEntity = mourner.getTarget();
            mourner.getNavigation().moveTo(livingEntity, 0.5 * (mourner.getRageFactor() + 1));
            mourner.setIsCharging(true);
            mourner.setIsFiring(false);
            mourner.playSound(SoundEvents.VEX_CHARGE, 1.0F, 1.0F);
        }

        @Override
        public void stop() {
            super.stop();
            mourner.setIsCharging(false);
        }

        @Override
        public void tick() {
            super.tick();
            LivingEntity livingEntity = mourner.getTarget();

            if (mourner.getBoundingBox().inflate(0.5).intersects(livingEntity.getBoundingBox())) {
                mourner.doHurtTarget((ServerLevel) mourner.level(), livingEntity);
                mourner.swing(InteractionHand.MAIN_HAND);
                mourner.setIsCharging(false);
            } else {
                Vec3 vec3 = livingEntity.getEyePosition();
                mourner.getNavigation().moveTo(vec3.x, vec3.y - 1.0, vec3.z, 0.5 * (mourner.getRageFactor() + 1));
            }
        }
    }

    static class MournerRangedAttackGoal extends Goal {
        private final MournerBoss mourner;
        private final Level level;
        private final RandomSource random;
        private int attackStep;
        private int attackTime;

        public MournerRangedAttackGoal(MournerBoss mourner) {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
            this.mourner = mourner;
            this.random = mourner.random;
            this.level = mourner.level();
        }

        @Override
        public boolean canUse() {
            return mourner.getTarget() != null && mourner.getTarget().isAlive() && !mourner.getMoveControl().hasWanted()
                    && !mourner.isCharging() && random.nextInt(9 - mourner.getRageFactor() * 2) == 0;
        }

        @Override
        public boolean canContinueToUse() {
            return attackStep > 0 && mourner.isFiring() && mourner.getTarget() != null && mourner.getTarget().isAlive();
        }

        @Override
        public void start() {
            attackStep = 2 + mourner.getRageFactor() * 3;
            attackTime = 60;
            mourner.setIsFiring(true);
            mourner.setCurrentAttack(random.nextInt(3));
        }

        @Override
        public void stop() {
            mourner.setIsFiring(false);
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = mourner.getTarget();
            --attackTime;

            if (attackTime <= 0) {
                --attackStep;
                attackTime = 3;
                switch (mourner.getCurrentAttack()) {
                    case ATTACK_FIREBALLS:
                        double d0 = mourner.distanceToSqr(livingEntity);
                        double d1 = livingEntity.getX() - mourner.getX();
                        double d2 = livingEntity.getBoundingBox().minY + (livingEntity.getBbHeight() / 2.0) - (mourner.getY() + (mourner.getBbHeight() / 2.0));
                        double d3 = livingEntity.getZ() - mourner.getZ();

                        float f = (float) (Math.sqrt(Math.sqrt(d0)) * 0.5f);

                        SmallFireball smallFireball = new SmallFireball(level, mourner, new Vec3(d1 + random.nextGaussian() * (double)f, d2, d3 + random.nextGaussian() * (double)f));
                        smallFireball.setPos(mourner.getX(), mourner.getY() + mourner.getBbHeight() / 2.0 + 0.5D, mourner.getZ());
                        level.addFreshEntity(smallFireball);
                        break;
                    case ATTACK_SHULKER:
                        Direction.Axis axis = Direction.Axis.values()[random.nextInt(3)];
                        ShulkerBullet shulkerBullet = new ShulkerBullet(level, mourner, livingEntity, axis);
                        shulkerBullet.setPos(shulkerBullet.getX(), shulkerBullet.getY() + mourner.getBbHeight() / 2.0 + 0.5D, shulkerBullet.getZ());
                        level.addFreshEntity(shulkerBullet);
                        mourner.playSound(SoundEvents.SHULKER_SHOOT, 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                        break;
                    case ATTACK_GHAST:
                        d1 = livingEntity.getX() - mourner.getX();
                        d2 = livingEntity.getBoundingBox().minY + (double)(livingEntity.getBbHeight() / 2.0F) - (0.5D + mourner.getY() + mourner.getBbHeight());
                        d3 = livingEntity.getZ() - mourner.getZ();
                        LargeFireball largeFireball = new LargeFireball(level, mourner, new Vec3(d1, d2, d3), mourner.getRageFactor());
                        largeFireball.setPos(mourner.getX(), mourner.getY() + mourner.getBbHeight() + 0.5D, mourner.getZ());
                        level.addFreshEntity(largeFireball);
                        --attackStep;
                        attackTime = 10;
                        break;
                }
            }

            mourner.getLookControl().setLookAt(livingEntity, 10.0F, 10.0F);
        }
    }

    static class MournerMoveControl extends MoveControl {
        public MournerMoveControl(Mob mob) {
            super(mob);
        }

        @Override
        public void tick() {
            if (operation == Operation.MOVE_TO) {
                double d0 = wantedX - this.mob.getX();
                double d1 = wantedY - this.mob.getY();
                double d2 = wantedZ - this.mob.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                d3 = Math.sqrt(d3);

                AABB boundingBox = mob.getBoundingBox();

                double xLength = boundingBox.getXsize();
                double yLength = boundingBox.getYsize();
                double zLength = boundingBox.getZsize();

                double averageEdge = (xLength + yLength + zLength) / 3.0;

                if (d3 < averageEdge) {
                    operation = Operation.WAIT;
                    mob.setDeltaMovement(mob.getDeltaMovement().scale(0.5f));
                } else {
                    mob.setDeltaMovement(
                            mob.getDeltaMovement().x + d0 / d3 * 0.05D * speedModifier,
                            mob.getDeltaMovement().y + d1 / d3 * 0.05D * speedModifier,
                            mob.getDeltaMovement().z + d2 / d3 * 0.05D * speedModifier);

                    float f1 = (float) speedModifier;
                    if (mob.getTarget() == null) {
                        double d4 = Math.sqrt(d0 * d0 + d2 * d2);
                        float f2 = (float)(-(Mth.atan2(d1, d4) * (double)180.0F / (double)(float)Math.PI));
                        this.mob.setXRot(this.rotlerp(this.mob.getXRot(), f2, 10));
                        this.mob.setYya(d1 > (double)0.0F ? f1 : -f1);
                        mob.setYRot(this.rotlerp(this.mob.getYHeadRot(), f2, 10));
                    }
                    else {
                        double d4 = mob.getTarget().getX() - mob.getX();
                        double d5 = mob.getTarget().getZ() - mob.getZ();
                        float f2 = (float)(-(Mth.atan2(d4, d5) * (double)180.0F / (double)(float)Math.PI));
                        this.mob.setXRot(this.rotlerp(this.mob.getXRot(), f2, 10));
                        this.mob.setYya(d1 > (double)0.0F ? f1 : -f1);
                        mob.setYRot(this.rotlerp(this.mob.getYHeadRot(), f2, 10));
                    }
                }
            }
        }
    }

    boolean teleportTowards(Entity target) {
        Vec3 vec3 = new Vec3(this.getX() - target.getX(), this.getY(0.5F) - target.getEyeY(), this.getZ() - target.getZ());
        vec3 = vec3.normalize();
        double d0 = 20.0F;
        double d1 = this.getX() + (this.random.nextDouble() - 0.5F) * 10.0F - vec3.x * d0;
        double d2 = this.getY() + (this.random.nextInt((int) d0) - 8) - vec3.y * d0;
        double d3 = this.getZ() + (this.random.nextDouble() - 0.5F) * 10.0F - vec3.z * d0;
        return this.randomTeleport(d1, d2, d3, true);
    }

    @Override
    public boolean randomTeleport(double x, double y, double z, boolean broadcastTeleport) {
        double d0 = this.getX();
        double d1 = this.getY();
        double d2 = this.getZ();
        double d3 = y;
        boolean flag = false;
        BlockPos blockpos = BlockPos.containing(x, y, z);
        Level level = this.level();
        if (level.hasChunkAt(blockpos)) {
            boolean flag1 = false;

            while(!flag1 && blockpos.getY() > level.getMinY()) {
                BlockPos blockpos1 = blockpos.below();
                BlockState blockstate = level.getBlockState(blockpos1);
                if (blockstate.blocksMotion()) {
                    flag1 = true;
                } else {
                    --d3;
                    blockpos = blockpos1;
                }
            }

            if (flag1) {
                this.teleportTo(x, d3, z);
                if (level.noCollision(this) && !level.containsAnyLiquid(this.getBoundingBox())) {
                    flag = true;
                }
            }
        }

        if (!flag) {
            this.teleportTo(d0, d1, d2);
            return false;
        } else {
            if (broadcastTeleport) {
                level.broadcastEntityEvent(this, (byte)46);
            }
            for (int j = 0; j < 128; ++j) {
                double d7 = (double)j / 127.0D;
                float f = (random.nextFloat() - 0.5F) * 0.2F;
                float f1 = (random.nextFloat() - 0.5F) * 0.2F;
                float f2 = (random.nextFloat() - 0.5F) * 0.2F;
                double d4 = d0 + (getX() - d0) * d7 + (random.nextDouble() - 0.5D) * this.getBbWidth() * 2.0D;
                double d5 = d1 + (getY() - d1) * d7 + random.nextDouble() * this.getBbHeight();
                double d6 = d2 + (getZ() - d2) * d7 + (random.nextDouble() - 0.5D) * this.getBbWidth() * 2.0D;
                level.addParticle(ParticleTypes.PORTAL, d4, d5, d6, f, f1, f2);
            }

            if (this instanceof PathfinderMob) {
                PathfinderMob pathfindermob = this;
                pathfindermob.getNavigation().stop();
            }

            return true;
        }
    }

    static class MoveRandomGoal extends Goal {
        private final MournerBoss mourner;
        private final RandomSource random;

        public MoveRandomGoal(MournerBoss mourner) {
            setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
            this.mourner = mourner;
            this.random = mourner.random;
        }

        @Override
        public boolean canUse() {
            return !mourner.getMoveControl().hasWanted() && mourner.random.nextInt(7) == 0;
        }

        @Override
        public boolean canContinueToUse() {
            return false;
        }

        @Override
        public void tick() {
            BlockPos blockPos;
            if (mourner.getTarget() != null && mourner.getTarget().isAlive()) {
                blockPos = mourner.getTarget().blockPosition();
            } else {
                blockPos = mourner.blockPosition();
            }
            for (int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockPos.offset(random.nextInt(15) - 7, random.nextInt(11) - 5, random.nextInt(15) - 7);

                if (mourner.level().getBlockState(blockpos1).isAir()) {
                    mourner.getNavigation().moveTo((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 0.25 * mourner.getRageFactor());

                    if (mourner.getTarget() == null) {
                        mourner.getLookControl().setLookAt((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                    }

                    break;
                }
            }
        }
    }
}
