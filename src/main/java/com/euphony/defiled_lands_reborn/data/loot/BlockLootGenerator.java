package com.euphony.defiled_lands_reborn.data.loot;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.common.init.DLBlocks;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BlockLootGenerator extends BlockLootSubProvider {
    public BlockLootGenerator(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        List<Block> blockList = BuiltInRegistries.BLOCK.entrySet().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(DefiledLandsReborn.MOD_ID))
                .map(Map.Entry::getValue)
                .toList();
        return blockList;
        // return DLBlocks.BLOCKS.getEntries().stream().filter(holder -> !holder.get().asItem().equals(Items.AIR)).map(holder -> (Block)holder.get()).toList();
    }

    @Override
    protected void generate() {
        dropSelf(DLBlocks.DEFILED_STONE.get());
        dropSelf(DLBlocks.DEFILED_SAND.get());
        dropSelf(DLBlocks.DEFILED_SANDSTONE.get());
        dropSelf(DLBlocks.DEFILED_DIRT.get());
        dropSelf(DLBlocks.DEFILED_GRASS_BLOCK.get());
        dropSelf(DLBlocks.DEFILED_GRAVEL.get());

        dropSelf(DLBlocks.TENEBRA_LOG.get());
        add(DLBlocks.TENEBRA_LEAVES.get(), createLeavesDrops(DLBlocks.TENEBRA_LEAVES.get(), DLBlocks.TENEBRA_SAPLING.get()));
        dropSelf(DLBlocks.TENEBRA_SAPLING.get());
        dropSelf(DLBlocks.TENEBRA_PLANKS.get());

        add(DLBlocks.HEPHAESTITE_ORE.get(), createOreDrop(DLBlocks.HEPHAESTITE_ORE.get(), DLItems.HEPHAESTITE_SHARD.get()));
        dropSelf(DLBlocks.HEPHAESTITE_BLOCK.get());
        add(DLBlocks.UMBRIUM_ORE.get(), createOreDrop(DLBlocks.UMBRIUM_ORE.get(), DLItems.UMBRIUM_INGOT.get()));
        dropSelf(DLBlocks.UMBRIUM_BLOCK.get());
        add(DLBlocks.SCARLITE_ORE.get(), createOreDrop(DLBlocks.SCARLITE_ORE.get(), DLItems.SCARLITE.get()));
        dropSelf(DLBlocks.SCARLITE_BLOCK.get());

        dropSelf(DLBlocks.VILESPINE.get());

        LootItemCondition.Builder builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(DLBlocks.BLASTEM.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.AGE_15, 15));
        add(DLBlocks.BLASTEM.get(), createCropDrops(DLBlocks.BLASTEM.get(), DLItems.BLASTEM_FRUIT.get(), DLItems.BLASTEM_SEED.get(), builder));
        dropSelf(DLBlocks.SCURONOTTE.get());

        dropSelf(DLBlocks.HEALING_PAD.get());
        dropOther(DLBlocks.CONJURING_ALTAR.get(), DLBlocks.DEFILED_STONE.get());

        dropSelf(DLBlocks.DEFILED_STONE_BRICKS.get());
        dropSelf(DLBlocks.DEFILED_CRACKED_STONE_BRICKS.get());
        dropSelf(DLBlocks.DEFILED_MOSSY_STONE_BRICKS.get());
        dropSelf(DLBlocks.DEFILED_MOSSY_STONE.get());

        add(DLBlocks.DEFILED_STONE_SLAB.get(), createSlabItemTable(DLBlocks.DEFILED_STONE_SLAB.get()));
        add(DLBlocks.DEFILED_SANDSTONE_SLAB.get(), createSlabItemTable(DLBlocks.DEFILED_SANDSTONE_SLAB.get()));
        add(DLBlocks.DEFILED_STONE_BRICK_SLAB.get(), createSlabItemTable(DLBlocks.DEFILED_STONE_BRICK_SLAB.get()));

        dropSelf(DLBlocks.DEFILED_STONE_STAIRS.get());
        dropSelf(DLBlocks.DEFILED_SANDSTONE_STAIRS.get());
        dropSelf(DLBlocks.DEFILED_STONE_BRICK_STAIRS.get());

        add(DLBlocks.TENEBRA_SLAB.get(), createSlabItemTable(DLBlocks.TENEBRA_SLAB.get()));
        dropSelf(DLBlocks.TENEBRA_STAIRS.get());
        dropSelf(DLBlocks.TENEBRA_DOOR.get());

        dropSelf(DLBlocks.RAVAGING_STONE.get());
        dropSelf(DLBlocks.RAVAGING_BRICKS.get());
    }
}
