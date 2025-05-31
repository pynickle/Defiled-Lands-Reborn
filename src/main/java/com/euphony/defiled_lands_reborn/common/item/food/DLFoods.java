package com.euphony.defiled_lands_reborn.common.item.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class DLFoods {
    public static final FoodProperties SCUTTLER_EYE = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.8F).alwaysEdible().build();

    public static final FoodProperties BLACK_HEART = (new FoodProperties.Builder()).nutrition(4).saturationModifier(0.1F).build();

    public static final FoodProperties FOUL_CANDY = (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.1F).build();

    public static final FoodProperties RAW_BOOK_WYRM = (new FoodProperties.Builder()).nutrition(3).saturationModifier(0.3F).build();
    public static final FoodProperties COOKED_BOOK_WYRM = (new FoodProperties.Builder()).nutrition(8).saturationModifier(0.8F).build();

}
