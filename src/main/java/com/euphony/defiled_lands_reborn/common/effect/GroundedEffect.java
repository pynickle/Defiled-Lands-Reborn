package com.euphony.defiled_lands_reborn.common.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class GroundedEffect extends MobEffect {
    public GroundedEffect() {
        super(MobEffectCategory.HARMFUL, 0x5f5e57);
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.onGround()) {
            if (livingEntity.yya > 0) {
                livingEntity.yya /= (float) (amplifier + 2);
            }
            livingEntity.yya -= (float) (0.05D * (amplifier + 1));
        }
        return true;
    }
}
