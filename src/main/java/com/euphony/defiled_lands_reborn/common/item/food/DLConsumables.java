package com.euphony.defiled_lands_reborn.common.item.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class DLConsumables {
    public static final Consumable SCUTTLER_EYE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    List.of(
                        new MobEffectInstance(MobEffects.NAUSEA, 200, 0),
                        new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 0),
                        new MobEffectInstance(MobEffects.HUNGER, 600, 0)
                    )
            ))
            .build();

    public static final Consumable BLACK_HEART = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    List.of(
                        new MobEffectInstance(MobEffects.NAUSEA, 200, 0),
                        new MobEffectInstance(MobEffects.SLOWNESS, 200, 3),
                        new MobEffectInstance(MobEffects.MINING_FATIGUE, 200, 3),
                        new MobEffectInstance(MobEffects.WEAKNESS, 200, 1),
                        new MobEffectInstance(MobEffects.WITHER, 200, 1),
                        new MobEffectInstance(MobEffects.HUNGER, 600, 0)
                    )
            ))
            .build();

    public static final Consumable FOUL_CANDY = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    List.of(
                        new MobEffectInstance(MobEffects.NAUSEA, 200, 0)
                    )
            ))
            .consumeSeconds(0.8F)
            .build();

    public static final Consumable RAW_BOOK_WYRM = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    new MobEffectInstance(MobEffects.HUNGER, 600, 0),
                    0.5F
            ))
            .build();
}
