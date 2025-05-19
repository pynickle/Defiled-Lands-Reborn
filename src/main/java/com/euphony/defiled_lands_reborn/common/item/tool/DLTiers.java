package com.euphony.defiled_lands_reborn.common.item.tool;//

import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.google.common.base.Suppliers;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.function.Supplier;

public enum DLTiers implements Tier {
    UMBRIUM(BlockTags.INCORRECT_FOR_IRON_TOOL, 302, 6.0F, 2.0F, 14, () -> Ingredient.of(DLItems.UMBRIUM_INGOT)),
    SCARLITE(BlockTags.INCORRECT_FOR_IRON_TOOL, 1561, 6.0F, 2.0F, 14, () -> Ingredient.of(DLItems.SCARLITE)),
    SCARLITE_RAZOR(BlockTags.INCORRECT_FOR_IRON_TOOL, 31, 6.0F, 2.0F, 14, () -> Ingredient.of(DLItems.UMBRIUM_INGOT)),
    RAVAGING(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2107, 8.0F, 3.0F, 10, () -> Ingredient.of(DLItems.RAVAGING_INGOT));

    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    DLTiers(TagKey<Block> incorrectBlockForDrops, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.incorrectBlocksForDrops = incorrectBlockForDrops;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        Objects.requireNonNull(repairIngredient);
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocksForDrops;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }
}
