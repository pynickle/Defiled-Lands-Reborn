package com.euphony.defiled_lands_reborn.common.item.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class DLFoods {
    public static final FoodProperties SCUTTLER_EYE = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.8F).alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 1.0f).build();

    public static final FoodProperties BLACK_HEART = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200, 0), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 3), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 200, 3), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 200, 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.WITHER, 200, 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 1.0f).build();

    public static final FoodProperties FOUL_CANDY = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.1F).fast()
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200, 0), 1.0f).build();

    public static final FoodProperties RAW_BOOK_WYRM = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.5f).build();
    public static final FoodProperties COOKED_BOOK_WYRM = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.8F).build();

}
