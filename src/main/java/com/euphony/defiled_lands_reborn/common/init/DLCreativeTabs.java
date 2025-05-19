package com.euphony.defiled_lands_reborn.common.init;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DLCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DefiledLandsReborn.MOD_ID);

    static {
        TABS.register(
                "defiled_lands_reborn",
                () ->
                        CreativeModeTab.builder()
                                .title(Component.translatable("itemGroup.tabDefiledLands"))
                                .icon(() -> new ItemStack(DLBlocks.DEFILED_GRASS_BLOCK.asItem(), 1))
                                .displayItems(
                                        (parameters, output) -> {
                                            output.accept(DLBlocks.DEFILED_GRASS_BLOCK.asItem());
                                            output.accept(DLBlocks.DEFILED_STONE.asItem());
                                            output.accept(DLBlocks.DEFILED_SAND.asItem());
                                            output.accept(DLBlocks.DEFILED_SANDSTONE.asItem());
                                            output.accept(DLBlocks.DEFILED_DIRT.asItem());
                                            output.accept(DLBlocks.DEFILED_GRAVEL.asItem());

                                            output.accept(DLBlocks.TENEBRA_LOG.asItem());
                                            output.accept(DLBlocks.TENEBRA_LEAVES.asItem());
                                            output.accept(DLBlocks.TENEBRA_SAPLING.asItem());
                                            output.accept(DLBlocks.TENEBRA_PLANKS.asItem());

                                            output.accept(DLBlocks.HEPHAESTITE_ORE.asItem());
                                            output.accept(DLBlocks.HEPHAESTITE_BLOCK.asItem());
                                            output.accept(DLBlocks.UMBRIUM_ORE.asItem());
                                            output.accept(DLBlocks.UMBRIUM_BLOCK.asItem());
                                            output.accept(DLBlocks.SCARLITE_ORE.asItem());
                                            output.accept(DLBlocks.SCARLITE_BLOCK.asItem());

                                            output.accept(DLBlocks.DEFILED_STONE_BRICKS.asItem());
                                            output.accept(DLBlocks.DEFILED_CRACKED_STONE_BRICKS.asItem());
                                            output.accept(DLBlocks.DEFILED_MOSSY_STONE_BRICKS.asItem());
                                            output.accept(DLBlocks.DEFILED_MOSSY_STONE.asItem());

                                            output.accept(DLBlocks.DEFILED_STONE_SLAB.asItem());
                                            output.accept(DLBlocks.DEFILED_SANDSTONE_SLAB.asItem());
                                            output.accept(DLBlocks.DEFILED_STONE_BRICK_SLAB.asItem());

                                            output.accept(DLBlocks.DEFILED_STONE_STAIRS.asItem());
                                            output.accept(DLBlocks.DEFILED_SANDSTONE_STAIRS.asItem());
                                            output.accept(DLBlocks.DEFILED_STONE_BRICK_STAIRS.asItem());

                                            output.accept(DLBlocks.TENEBRA_SLAB.asItem());
                                            output.accept(DLBlocks.TENEBRA_STAIRS.asItem());
                                            output.accept(DLBlocks.TENEBRA_DOOR.asItem());

                                            output.accept(DLBlocks.RAVAGING_STONE.asItem());
                                            output.accept(DLBlocks.RAVAGING_BRICKS.asItem());

                                            output.accept(DLBlocks.VILESPINE.asItem());
                                            output.accept(DLBlocks.BLASTEM.asItem());
                                            output.accept(DLBlocks.SCURONOTTE.asItem());

                                            output.accept(DLBlocks.HEALING_PAD.asItem());
                                            output.accept(DLBlocks.CONJURING_ALTAR.asItem());

                                            output.accept(DLItems.HEPHAESTITE_SHARD);
                                            output.accept(DLItems.UMBRIUM_INGOT);
                                            output.accept(DLItems.UMBRIUM_NUGGET);
                                            output.accept(DLItems.SCARLITE);

                                            output.accept(DLItems.UMBRIUM_HELMET);
                                            output.accept(DLItems.UMBRIUM_CHESTPLATE);
                                            output.accept(DLItems.UMBRIUM_LEGGINGS);
                                            output.accept(DLItems.UMBRIUM_BOOTS);

                                            output.accept(DLItems.BOOK_WYRM_SCALE_HELMET);
                                            output.accept(DLItems.BOOK_WYRM_SCALE_CHESTPLATE);
                                            output.accept(DLItems.BOOK_WYRM_SCALE_LEGGINGS);
                                            output.accept(DLItems.BOOK_WYRM_SCALE_BOOTS);

                                            output.accept(DLItems.GOLDEN_BOOK_WYRM_SCALE_HELMET);
                                            output.accept(DLItems.GOLDEN_BOOK_WYRM_SCALE_CHESTPLATE);
                                            output.accept(DLItems.GOLDEN_BOOK_WYRM_SCALE_LEGGINGS);
                                            output.accept(DLItems.GOLDEN_BOOK_WYRM_SCALE_BOOTS);

                                            output.accept(DLItems.BLASTEM_SEED);
                                            output.accept(DLItems.BLASTEM_FRUIT);
                                            output.accept(DLItems.BLAZING_BLASTEM_FRUIT);
                                            output.accept(DLItems.DEFILEMENT_POWDER);

                                            output.accept(DLItems.SCUTTLER_EYE);
                                            output.accept(DLItems.BLACK_HEART);
                                            output.accept(DLItems.FOUL_SLIME);
                                            output.accept(DLItems.FOUL_CANDY);
                                            output.accept(DLItems.RAW_BOOK_WYRM);
                                            output.accept(DLItems.COOKED_BOOK_WYRM);
                                            output.accept(DLItems.BOOK_WYRM_SCALE);
                                            output.accept(DLItems.GOLDEN_BOOK_WYRM_SCALE);

                                            output.accept(DLItems.UMBRIUM_AXE);
                                            output.accept(DLItems.UMBRIUM_HOE);
                                            output.accept(DLItems.UMBRIUM_PICKAXE);
                                            output.accept(DLItems.UMBRIUM_SHOVEL);
                                            output.accept(DLItems.UMBRIUM_SWORD);

                                            output.accept(DLItems.SCARLITE_REAVER);
                                            output.accept(DLItems.SCARLITE_RAZOR);

                                            output.accept(DLItems.UMBRA_BLASTER);
                                            output.accept(DLItems.CONCUSSION_SMASHER);

                                            output.accept(DLItems.BOOK_WYRM_ANALYZER);

                                            output.accept(DLItems.CALLING_STONE);
                                            output.accept(DLItems.RAVAGING_ESSENCE);
                                            output.accept(DLItems.RAVAGING_INGOT);

                                            output.accept(DLItems.RAVAGING_AXE);
                                            output.accept(DLItems.RAVAGING_PICKAXE);
                                            output.accept(DLItems.RAVAGING_SHOVEL);

                                            output.accept(DLItems.THE_RAVAGER);
                                            output.accept(DLItems.UMBRIUM_PELLET);
                                            output.accept(DLItems.SPIKED_PELLET);
                                            output.accept(DLItems.RAVAGING_PELLET);

                                            output.accept(DLItems.IDOL_SORROW);
                                            output.accept(DLItems.REMORSEFUL_ESSENCE);
                                            output.accept(DLItems.REMORSEFUL_GEM);
                                            output.accept(DLItems.TEARS_FLAME);
                                            output.accept(DLItems.TEARS_SHULKER);

                                            output.accept(DLItems.SCARLITE_RING);
                                            output.accept(DLItems.PHYTOPROSTASIA_AMULET);

                                            output.acceptAll(DLItems.SPAWN_EGGS.stream()
                                                    .map(DeferredItem::toStack)
                                                    .toList());
                                        })
                                .build());
    }
}
