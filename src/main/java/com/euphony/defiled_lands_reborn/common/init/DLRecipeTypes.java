package com.euphony.defiled_lands_reborn.common.init;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DLRecipeTypes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, DefiledLandsReborn.MOD_ID);

    // public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CorruptionRecipe>> CORRUPTION_RECIPE = RECIPE_SERIALIZERS.register("corruption_recipe", () -> new SimpleCraftingRecipeSerializer<>(CorruptionRecipe::new));
}
