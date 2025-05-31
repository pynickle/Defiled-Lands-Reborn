package com.euphony.defiled_lands_reborn.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;

public class Shambler extends Monster {
    public Shambler(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENDERMAN_AMBIENT;
    }

    @Override
    protected @NotNull SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ENDERMAN_HURT;
    }

    @Override
    protected @NotNull SoundEvent getDeathSound() {
        return SoundEvents.ENDERMAN_DEATH;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 40.0F).add(Attributes.MOVEMENT_SPEED, 0.14F).add(Attributes.ATTACK_DAMAGE, 14.0F).add(Attributes.FOLLOW_RANGE, 64.0F).add(Attributes.STEP_HEIGHT, 1.0F).add(Attributes.KNOCKBACK_RESISTANCE, 1.0F);
    }

    public static boolean checkShamblerSpawnRules(EntityType<? extends Monster> type, ServerLevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return pos.getY() > 50 && level.getDifficulty() != Difficulty.PEACEFUL && (EntitySpawnReason.ignoresLightRequirements(spawnReason) || isDarkEnoughToSpawn(level, pos, random)) && checkMobSpawnRules(type, level, spawnReason, pos, random);
    }

    protected int getDebuffDuration() {
        int multiplier = switch (level().getDifficulty()) {
            case NORMAL -> 15;
            case HARD -> 30;
            default -> 3;
        };
        return multiplier * 20;
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, @NotNull Entity entity) {
        int i = getDebuffDuration();

        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.SLOWNESS, i, 1));
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, i, 1));
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0));
        }
        return super.doHurtTarget(level, entity);
    }

    @Override
    public void tick() {
        Level level = level();
        if (level.isClientSide) {
            for (int i = 0; i < 2; ++i) {
                level.addParticle(ParticleTypes.SMOKE,
                        this.getX() + (random.nextDouble() - 0.5D) * getBbWidth(),
                        this.getY() + random.nextDouble() * getBbHeight(),
                        this.getZ() + (random.nextDouble() - 0.5D) * getBbWidth(),
                        0.0D, 0.0D, 0.0D);
            }
        }

        if (level.isBrightOutside() && level.canSeeSky(getOnPos().above()) && random.nextFloat() < 0.02f) {
            this.die(new DamageSource(registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(DamageTypes.GENERIC)));
            this.setHealth(0.0f);
        }
        super.tick();
    }
}
