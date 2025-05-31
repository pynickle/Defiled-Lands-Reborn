package com.euphony.defiled_lands_reborn.datagen.tag;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.common.init.DLBlocks;
import com.euphony.defiled_lands_reborn.common.tag.DLBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider {
    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, DefiledLandsReborn.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(DLBlockTags.STONES).add(DLBlocks.DEFILED_STONE.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(DLBlocks.DEFILED_STONE.get(), DLBlocks.DEFILED_SANDSTONE.get(), DLBlocks.HEPHAESTITE_ORE.get(),
                DLBlocks.HEPHAESTITE_BLOCK.get(), DLBlocks.UMBRIUM_ORE.get(), DLBlocks.UMBRIUM_BLOCK.get(), DLBlocks.SCARLITE_ORE.get(),
                DLBlocks.SCARLITE_BLOCK.get(), DLBlocks.DEFILED_STONE_STAIRS.get(), DLBlocks.DEFILED_SANDSTONE_STAIRS.get(),
                DLBlocks.DEFILED_STONE_BRICK_STAIRS.get(), DLBlocks.DEFILED_STONE_SLAB.get(), DLBlocks.DEFILED_SANDSTONE_SLAB.get(),
                DLBlocks.DEFILED_STONE_BRICK_SLAB.get(), DLBlocks.RAVAGING_BRICKS.get(), DLBlocks.RAVAGING_STONE.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(DLBlocks.DEFILED_DIRT.get(), DLBlocks.DEFILED_GRASS_BLOCK.get(),
                DLBlocks.DEFILED_GRAVEL.get());
        tag(BlockTags.MINEABLE_WITH_AXE).add(DLBlocks.SCURONOTTE.get(), DLBlocks.VILESPINE.get(), DLBlocks.BLASTEM.get());
        tag(BlockTags.SWORD_EFFICIENT).add(DLBlocks.SCURONOTTE.get(), DLBlocks.VILESPINE.get(), DLBlocks.BLASTEM.get());

        tag(BlockTags.NEEDS_STONE_TOOL).add(DLBlocks.UMBRIUM_ORE.get(), DLBlocks.UMBRIUM_BLOCK.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(DLBlocks.SCARLITE_ORE.get(), DLBlocks.SCARLITE_BLOCK.get());

        tag(BlockTags.DIRT).add(DLBlocks.DEFILED_DIRT.get(), DLBlocks.DEFILED_GRASS_BLOCK.get());
        tag(BlockTags.SAND).add(DLBlocks.DEFILED_SAND.get());
        tag(DLBlockTags.SANDSTONE).add(DLBlocks.DEFILED_SANDSTONE.get());
        tag(DLBlockTags.GRAVELS).add(DLBlocks.DEFILED_GRAVEL.get());
        tag(BlockTags.LOGS).add(DLBlocks.TENEBRA_LOG.get());
        tag(BlockTags.LEAVES).add(DLBlocks.TENEBRA_LEAVES.get());
        tag(BlockTags.SAPLINGS).add(DLBlocks.TENEBRA_SAPLING.get());
        tag(BlockTags.PLANKS).add(DLBlocks.TENEBRA_PLANKS.get());

        tag(BlockTags.WOODEN_STAIRS).add(DLBlocks.TENEBRA_STAIRS.get());
        tag(BlockTags.STAIRS).add(DLBlocks.DEFILED_STONE_STAIRS.get(), DLBlocks.DEFILED_SANDSTONE_STAIRS.get(), DLBlocks.DEFILED_STONE_BRICK_STAIRS.get());
        tag(BlockTags.WOODEN_SLABS).add(DLBlocks.TENEBRA_SLAB.get());
        tag(BlockTags.SLABS).add(DLBlocks.DEFILED_STONE_SLAB.get(), DLBlocks.DEFILED_SANDSTONE_SLAB.get(), DLBlocks.DEFILED_STONE_BRICK_SLAB.get());

        tag(DLBlockTags.ORES).add(DLBlocks.HEPHAESTITE_ORE.get(), DLBlocks.UMBRIUM_ORE.get(), DLBlocks.SCARLITE_ORE.get());
        tag(DLBlockTags.STORAGE_BLOCKS).add(DLBlocks.HEPHAESTITE_BLOCK.get(), DLBlocks.UMBRIUM_BLOCK.get(), DLBlocks.SCARLITE_BLOCK.get());

        tag(DLBlockTags.BASE_DEFILED_SURFACE).add(DLBlocks.DEFILED_DIRT.get(), DLBlocks.DEFILED_GRASS_BLOCK.get(),
                DLBlocks.DEFILED_SAND.get());
    }

    @SafeVarargs
    public final <T extends Block> void add(TagKey<Block> tag, T... blocks) {
        tag(tag).add(blocks);
    }
}

