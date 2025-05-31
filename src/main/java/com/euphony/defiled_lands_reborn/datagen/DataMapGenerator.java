package com.euphony.defiled_lands_reborn.datagen;

import com.euphony.defiled_lands_reborn.common.datamap.DLDataMaps;
import com.euphony.defiled_lands_reborn.common.datamap.block.CorruptionBlock;
import com.euphony.defiled_lands_reborn.common.init.DLBlocks;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class DataMapGenerator extends DataMapProvider {
    Builder<CorruptionBlock, Block> corruption = this.builder(DLDataMaps.CORRUPTION);
    Builder<FurnaceFuel, Item> furnace_fuels = this.builder(NeoForgeDataMaps.FURNACE_FUELS);

    public DataMapGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        addCorruption(Blocks.STONE, DLBlocks.DEFILED_STONE.get());
        addCorruption(Blocks.DIORITE, DLBlocks.DEFILED_STONE.get());
        addCorruption(Blocks.GRANITE, DLBlocks.DEFILED_STONE.get());
        addCorruption(Blocks.ANDESITE, DLBlocks.DEFILED_STONE.get());

        addCorruption(Blocks.GRAVEL, DLBlocks.DEFILED_GRAVEL.get());
        addCorruption(Blocks.DIRT, DLBlocks.DEFILED_DIRT.get());
        addCorruption(Blocks.GRASS_BLOCK, DLBlocks.DEFILED_GRASS_BLOCK.get());
        addCorruption(Blocks.SANDSTONE, DLBlocks.DEFILED_SANDSTONE.get());
        addCorruption(Blocks.SAND, DLBlocks.DEFILED_SAND.get());

        addCorruption(Blocks.STONE_BRICKS, DLBlocks.DEFILED_STONE_BRICKS.get());
        addCorruption(Blocks.CRACKED_STONE_BRICKS, DLBlocks.DEFILED_CRACKED_STONE_BRICKS.get());
        addCorruption(Blocks.MOSSY_COBBLESTONE, DLBlocks.DEFILED_MOSSY_STONE.get());
        addCorruption(Blocks.MOSSY_STONE_BRICKS, DLBlocks.DEFILED_MOSSY_STONE_BRICKS.get());

        addCorruption(Blocks.COAL_BLOCK, DLBlocks.HEPHAESTITE_BLOCK.get());
        addCorruption(Blocks.COAL_ORE, DLBlocks.HEPHAESTITE_ORE.get());
        addCorruption(Blocks.DEEPSLATE_COAL_ORE, DLBlocks.HEPHAESTITE_ORE.get());

        addCorruption(Blocks.IRON_BLOCK, DLBlocks.UMBRIUM_BLOCK.get());
        addCorruption(Blocks.IRON_ORE, DLBlocks.UMBRIUM_ORE.get());
        addCorruption(Blocks.DEEPSLATE_IRON_ORE, DLBlocks.UMBRIUM_ORE.get());

        addCorruption(Blocks.DIAMOND_BLOCK, DLBlocks.SCARLITE_BLOCK.get());
        addCorruption(Blocks.DIAMOND_ORE, DLBlocks.SCARLITE_ORE.get());
        addCorruption(Blocks.DEEPSLATE_DIAMOND_ORE, DLBlocks.SCARLITE_ORE.get());

        furnace_fuels.add(DLItems.HEPHAESTITE_SHARD.get().builtInRegistryHolder(), new FurnaceFuel(200), false);
        furnace_fuels.add(DLBlocks.HEPHAESTITE_BLOCK.asItem().builtInRegistryHolder(), new FurnaceFuel(2000), false);
    }

    public void addCorruption(Block block1, Block block2) {
        corruption.add(block1.builtInRegistryHolder(), new CorruptionBlock(block2), false);
    }
}

