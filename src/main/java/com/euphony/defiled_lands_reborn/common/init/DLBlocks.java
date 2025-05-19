package com.euphony.defiled_lands_reborn.common.init;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.common.block.*;
import com.euphony.defiled_lands_reborn.common.worldgen.features.tree.DLTreeGrowers;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;


public class DLBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DefiledLandsReborn.MOD_ID);

    public static final DeferredBlock<CorruptedBlock> DEFILED_STONE = registerWithItem("defiled_stone", CorruptedBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<CorruptedFallingBlock> DEFILED_SAND = registerWithItem("defiled_sand", CorruptedFallingBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SAND));
    public static final DeferredBlock<CorruptedBlock> DEFILED_SANDSTONE = registerWithItem("defiled_sandstone", CorruptedBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE));
    public static final DeferredBlock<CorruptedBlock> DEFILED_DIRT = registerWithItem("defiled_dirt", CorruptedBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT));
    public static final DeferredBlock<CorruptedGrassBlock> DEFILED_GRASS_BLOCK = registerWithItem("defiled_grass_block", CorruptedGrassBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK));
    public static final DeferredBlock<CorruptedFallingBlock> DEFILED_GRAVEL = registerWithItem("defiled_gravel", CorruptedFallingBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.GRAVEL));

    public static final DeferredBlock<CorruptedLogBlock> TENEBRA_LOG = registerWithItem("tenebra_log", CorruptedLogBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static final DeferredBlock<LeavesBlock> TENEBRA_LEAVES = registerWithItem("tenebra_leaves", LeavesBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<TenebraSaplingBlock> TENEBRA_SAPLING = registerWithItem("tenebra_sapling", (p) -> new TenebraSaplingBlock(DLTreeGrowers.TENEBRA, p),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<CorruptedBlock> TENEBRA_PLANKS = registerWithItem("tenebra_planks", CorruptedBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredBlock<HephaestiteOreBlock> HEPHAESTITE_ORE = registerWithItem("hephaestite_ore", p -> new HephaestiteOreBlock(UniformInt.of(0, 2), p),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_ORE));
    public static final DeferredBlock<HephaestiteBlock> HEPHAESTITE_BLOCK = registerWithItem("hephaestite_block", HephaestiteBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_BLOCK));
    public static final DeferredBlock<CorruptedBlock> UMBRIUM_ORE = registerWithItem("umbrium_ore", CorruptedBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE));
    public static final DeferredBlock<CorruptedBlock> UMBRIUM_BLOCK = registerWithItem("umbrium_block", CorruptedBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK));
    public static final DeferredBlock<Block> SCARLITE_ORE = registerWithItem("scarlite_ore", Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE));
    public static final DeferredBlock<CorruptedBlock> SCARLITE_BLOCK = registerWithItem("scarlite_block", CorruptedBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<VilespineBlock> VILESPINE = registerWithItem("vilespine", VilespineBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEAD_BUSH));
    public static final DeferredBlock<BlastemBlock> BLASTEM = registerWithItem("blastem", BlastemBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB));
    public static final DeferredBlock<ScuronotteBlock> SCURONOTTE = registerWithItem("scuronotte", ScuronotteBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM));

    public static final DeferredBlock<HealingPadBlock> HEALING_PAD = registerWithItem("healing_pad", HealingPadBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB));
    public static final DeferredBlock<ConjuringAltarBlock> CONJURING_ALTAR = registerWithItem("conjuring_altar", ConjuringAltarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB));

    public static final DeferredBlock<Block> DEFILED_STONE_BRICKS = registerWithItem("defiled_stone_bricks", Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS));
    public static final DeferredBlock<Block> DEFILED_CRACKED_STONE_BRICKS = registerWithItem("defiled_cracked_stone_bricks", Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS));
    public static final DeferredBlock<Block> DEFILED_MOSSY_STONE_BRICKS = registerWithItem("defiled_mossy_stone_bricks", Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS));
    public static final DeferredBlock<Block> DEFILED_MOSSY_STONE = registerWithItem("defiled_mossy_stone", Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE));

    public static final DeferredBlock<SlabBlock> DEFILED_STONE_SLAB = registerWithItem("defiled_stone_slab", SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<SlabBlock> DEFILED_SANDSTONE_SLAB = registerWithItem("defiled_sandstone_slab", SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_SLAB));
    public static final DeferredBlock<SlabBlock> DEFILED_STONE_BRICK_SLAB = registerWithItem("defiled_stone_brick_slab", SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB));

    public static final DeferredBlock<StairBlock> DEFILED_STONE_STAIRS = registerWithItem("defiled_stone_stairs", p -> new StairBlock(DEFILED_STONE.get().defaultBlockState(), p),
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<StairBlock> DEFILED_SANDSTONE_STAIRS = registerWithItem("defiled_sandstone_stairs", p -> new StairBlock(DEFILED_SANDSTONE.get().defaultBlockState(), p),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_STAIRS));
    public static final DeferredBlock<StairBlock> DEFILED_STONE_BRICK_STAIRS = registerWithItem("defiled_stone_brick_stairs", p -> new StairBlock(DEFILED_STONE_BRICKS.get().defaultBlockState(), p),
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS));

    public static final DeferredBlock<SlabBlock> TENEBRA_SLAB = registerWithItem("tenebra_slab", SlabBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB));
    public static final DeferredBlock<StairBlock> TENEBRA_STAIRS = registerWithItem("tenebra_stairs", p -> new StairBlock(TENEBRA_PLANKS.get().defaultBlockState(), p),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<DoorBlock> TENEBRA_DOOR = registerWithItem("tenebra_door", p -> new DoorBlock(new BlockSetType("tenebra"), p),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));

    public static final DeferredBlock<Block> RAVAGING_STONE = registerWithItem("ravaging_stone", Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> RAVAGING_BRICKS = registerWithItem("ravaging_bricks", Block::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS));

    public static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> function, BlockBehaviour.Properties properties) {
        return BLOCKS.registerBlock(name, function, properties);
    }

    public static <T extends Block> DeferredBlock<T> registerWithItem(String name, Function<BlockBehaviour.Properties, T> block, BlockBehaviour.Properties properties) {
        DeferredBlock<T> ret = register(name, block, properties);
        DLItems.register(name, ret);
        return ret;
    }
}
