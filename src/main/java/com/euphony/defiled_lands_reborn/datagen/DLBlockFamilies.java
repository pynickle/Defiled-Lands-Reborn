package com.euphony.defiled_lands_reborn.datagen;

import com.euphony.defiled_lands_reborn.common.init.DLBlocks;
import com.google.common.collect.Maps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.stream.Stream;

public class DLBlockFamilies {
    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();
    private static final String RECIPE_GROUP_PREFIX_WOODEN = "wooden";
    private static final String RECIPE_UNLOCKED_BY_HAS_PLANKS = "has_planks";

    private static BlockFamily.Builder familyBuilder(Block baseBlock) {
        BlockFamily.Builder blockfamily$builder = new BlockFamily.Builder(baseBlock);
        BlockFamily blockfamily = MAP.put(baseBlock, blockfamily$builder.getFamily());
        if (blockfamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + String.valueOf(BuiltInRegistries.BLOCK.getKey(baseBlock)));
        } else {
            return blockfamily$builder;
        }
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }

    public static final BlockFamily DEFILED_SANDSTONE = familyBuilder(DLBlocks.DEFILED_SANDSTONE.get())
            .slab(DLBlocks.DEFILED_SANDSTONE_SLAB.get())
            .stairs(DLBlocks.DEFILED_SANDSTONE_STAIRS.get())
            .getFamily();

    public static final BlockFamily DEFILED_STONE_BRICKS = familyBuilder(DLBlocks.DEFILED_STONE_BRICKS.get())
            .slab(DLBlocks.DEFILED_STONE_BRICK_SLAB.get())
            .stairs(DLBlocks.DEFILED_STONE_BRICK_STAIRS.get())
            .getFamily();

    public static final BlockFamily DEFILED_STONE = familyBuilder(DLBlocks.DEFILED_STONE.get())
            .slab(DLBlocks.DEFILED_STONE_SLAB.get())
            .stairs(DLBlocks.DEFILED_STONE_STAIRS.get())
            .getFamily();

    public static final BlockFamily TENEBRA_PLANKS = familyBuilder(DLBlocks.TENEBRA_PLANKS.get())
            .slab(DLBlocks.TENEBRA_SLAB.get())
            .stairs(DLBlocks.TENEBRA_STAIRS.get())
            .door(DLBlocks.TENEBRA_DOOR.get())
            .recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
}
