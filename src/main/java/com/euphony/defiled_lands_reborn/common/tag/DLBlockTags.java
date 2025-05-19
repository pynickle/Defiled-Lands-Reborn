package com.euphony.defiled_lands_reborn.common.tag;

import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class DLBlockTags {
    public static final TagKey<Block> BASE_DEFILED_SURFACE = create("base_defiled_surface");

    public static final TagKey<Block> STONES = createCTag("stones");
    public static final TagKey<Block> SANDSTONE = createCTag("sandstone/blocks");
    public static final TagKey<Block> GRAVELS = createCTag("gravels");

    public static final TagKey<Block> ORES = createCTag("ores");
    public static final TagKey<Block> STORAGE_BLOCKS = createCTag("storage_blocks");

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, Utils.prefix(name));
    }

    public static TagKey<Block> createVanillaTag(String tagName) {
        return BlockTags.create(ResourceLocation.withDefaultNamespace(tagName));
    }

    public static TagKey<Block> createCTag(String tagName) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", tagName));
    }
}
