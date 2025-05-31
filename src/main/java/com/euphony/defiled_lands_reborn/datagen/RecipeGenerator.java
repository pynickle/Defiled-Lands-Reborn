package com.euphony.defiled_lands_reborn.datagen;

import com.euphony.defiled_lands_reborn.common.init.DLBlocks;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends RecipeProvider {
    private static HolderLookup.RegistryLookup<Item> registry = null;
    protected RecipeGenerator(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
        registry = registries.lookupOrThrow(Registries.ITEM);
    }

    @Override
    protected void buildRecipes() {
        addDecorationRecipes();
        addDecorationStoneCuttingRecipes();
        addDecorationSmeltingRecipes();
        addToolRecipes();
        addEquipmentRecipes();
        addMiscRecipes();
        addSmeltingRecipes();
    }

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new RecipeGenerator(registries, output);
        }

        @Override
        public String getName() {
            return "Defiled Lands Reborn's Recipes";
        }
    }

    private void addDecorationRecipes() {
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_SANDSTONE)
                .pattern("##")
                .pattern("##")
                .define('#', DLBlocks.DEFILED_SAND.asItem())
                .unlockedBy("has_item", has(DLBlocks.DEFILED_SAND))
                .save(output);
        ShapelessRecipeBuilder.shapeless(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.TENEBRA_PLANKS, 4)
                .requires(DLBlocks.TENEBRA_LOG)
                .unlockedBy("has_item", has(DLBlocks.TENEBRA_LOG))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.TENEBRA_SLAB)
                .pattern("###")
                .define('#', DLBlocks.TENEBRA_PLANKS.asItem())
                .unlockedBy("has_item", has(DLBlocks.TENEBRA_PLANKS))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.TENEBRA_STAIRS)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', DLBlocks.TENEBRA_PLANKS.asItem())
                .unlockedBy("has_item", has(DLBlocks.TENEBRA_PLANKS))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.REDSTONE, DLBlocks.TENEBRA_DOOR)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', DLBlocks.TENEBRA_PLANKS.asItem())
                .unlockedBy("has_item", has(DLBlocks.TENEBRA_PLANKS))
                .save(output);

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_SANDSTONE_SLAB)
                .pattern("###")
                .define('#', DLBlocks.DEFILED_SANDSTONE.asItem())
                .unlockedBy("has_item", has(DLBlocks.DEFILED_SANDSTONE))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_SLAB)
                .pattern("###")
                .define('#', DLBlocks.DEFILED_STONE.asItem())
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_BRICK_SLAB)
                .pattern("###")
                .define('#', DLBlocks.DEFILED_STONE_BRICKS)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE_BRICKS))
                .save(output);

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_STAIRS)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', DLBlocks.DEFILED_STONE.asItem())
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_SANDSTONE_STAIRS)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', DLBlocks.DEFILED_SANDSTONE.asItem())
                .unlockedBy("has_item", has(DLBlocks.DEFILED_SANDSTONE))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_BRICK_STAIRS)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', DLBlocks.DEFILED_STONE_BRICKS)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE_BRICKS))
                .save(output);

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.HEPHAESTITE_BLOCK)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', DLItems.HEPHAESTITE_SHARD)
                .unlockedBy("has_item", has(DLItems.HEPHAESTITE_SHARD))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.UMBRIUM_BLOCK)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.SCARLITE_BLOCK)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', DLItems.SCARLITE)
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .save(output);

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.RAVAGING_STONE)
                .pattern("###")
                .pattern("#X#")
                .pattern("#X#")
                .define('#', DLBlocks.DEFILED_STONE)
                .define('X', DLItems.RAVAGING_ESSENCE)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .unlockedBy("has_item", has(DLItems.RAVAGING_ESSENCE))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.RAVAGING_BRICKS)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .define('#', Blocks.BRICKS)
                .define('X', DLItems.RAVAGING_ESSENCE)
                .unlockedBy("has_item", has(Blocks.BRICKS))
                .unlockedBy("has_item", has(DLItems.RAVAGING_ESSENCE))
                .save(output);

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_BRICKS)
                .pattern("##")
                .pattern("##")
                .define('#', DLBlocks.DEFILED_STONE)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .save(output);

        ShapelessRecipeBuilder.shapeless(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_MOSSY_STONE)
                .requires(DLBlocks.DEFILED_STONE)
                .requires(Blocks.MOSS_BLOCK)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .unlockedBy("has_item", has(Blocks.MOSS_BLOCK))
                .save(output, createKey("defiled_mossy_stone_from_moss"));
        ShapelessRecipeBuilder.shapeless(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_MOSSY_STONE)
                .requires(DLBlocks.DEFILED_STONE)
                .requires(Blocks.VINE)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .unlockedBy("has_item", has(Blocks.VINE))
                .save(output, createKey("defiled_mossy_stone_from_vine"));

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_MOSSY_STONE_BRICKS)
                .pattern("##")
                .pattern("##")
                .define('#', DLBlocks.DEFILED_MOSSY_STONE)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_MOSSY_STONE))
                .save(output);
    }

    private void addDecorationStoneCuttingRecipes() {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_STONE), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_BRICKS, 1)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .save(output, createKey("defiled_stone_bricks_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_MOSSY_STONE), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_MOSSY_STONE_BRICKS, 1)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_MOSSY_STONE))
                .save(output, createKey("defiled_mossy_stone_bricks_from_stonecutting"));

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_STONE), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_SLAB, 2)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .save(output, createKey("defiled_stone_slab_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_STONE), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_STAIRS, 1)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .save(output, createKey("defiled_stone_stairs_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_SANDSTONE_SLAB, 2)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_SANDSTONE))
                .save(output, createKey("defiled_sandstone_slab_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_SANDSTONE_STAIRS, 1)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_SANDSTONE))
                .save(output, createKey("defiled_sandstone_stairs_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_BRICK_SLAB, 2)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE_BRICKS))
                .save(output, createKey("defiled_stone_brick_slab_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_BRICK_STAIRS, 1)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE_BRICKS))
                .save(output, createKey("defiled_stone_brick_stairs_from_stonecutting"));
    }

    private void addDecorationSmeltingRecipes() {
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(DLBlocks.DEFILED_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_CRACKED_STONE_BRICKS, 0.1f, 200)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE_BRICKS))
                .save(output);
    }

    private void addToolRecipes() {
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.TOOLS, DLItems.UMBRIUM_AXE)
                .pattern("## ")
                .pattern("#S ")
                .pattern(" S ")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.TOOLS, DLItems.UMBRIUM_PICKAXE)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.TOOLS, DLItems.UMBRIUM_SHOVEL)
                .pattern("#")
                .pattern("S")
                .pattern("S")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.TOOLS, DLItems.UMBRIUM_SWORD)
                .pattern("#")
                .pattern("#")
                .pattern("S")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.TOOLS, DLItems.UMBRIUM_HOE)
                .pattern("##")
                .pattern(" S")
                .pattern(" S")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(output);

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.TOOLS, DLItems.RAVAGING_AXE)
                .pattern("## ")
                .pattern("#S ")
                .pattern(" S ")
                .define('#', DLItems.RAVAGING_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.RAVAGING_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.TOOLS, DLItems.RAVAGING_PICKAXE)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .define('#', DLItems.RAVAGING_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.RAVAGING_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.TOOLS, DLItems.RAVAGING_SHOVEL)
                .pattern("#")
                .pattern("S")
                .pattern("S")
                .define('#', DLItems.RAVAGING_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.RAVAGING_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(output);

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.TOOLS, DLItems.SCARLITE_RAZOR)
                .pattern("# ")
                .pattern("#S")
                .pattern(" X")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', DLItems.BLACK_HEART)
                .define('X', DLItems.SCARLITE)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .unlockedBy("has_item", has(DLItems.BLACK_HEART))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.TOOLS, DLItems.SCARLITE_REAVER)
                .pattern("X  ")
                .pattern("XSX")
                .pattern(" # ")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', DLItems.BLACK_HEART)
                .define('X', DLItems.SCARLITE)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .unlockedBy("has_item", has(DLItems.BLACK_HEART))
                .save(output);

    }

    private void addEquipmentRecipes() {
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, DLItems.UMBRIUM_HELMET)
                .pattern("###")
                .pattern("# #")
                .define('#', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, DLItems.UMBRIUM_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, DLItems.UMBRIUM_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, DLItems.UMBRIUM_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .define('#', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(output);

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, DLItems.BOOK_WYRM_SCALE_HELMET)
                .pattern("###")
                .pattern("# #")
                .define('#', DLItems.BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.BOOK_WYRM_SCALE))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, DLItems.BOOK_WYRM_SCALE_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', DLItems.BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.BOOK_WYRM_SCALE))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, DLItems.BOOK_WYRM_SCALE_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', DLItems.BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.BOOK_WYRM_SCALE))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, DLItems.BOOK_WYRM_SCALE_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .define('#', DLItems.BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.BOOK_WYRM_SCALE))
                .save(output);

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, DLItems.GOLDEN_BOOK_WYRM_SCALE_HELMET)
                .pattern("###")
                .pattern("# #")
                .define('#', DLItems.GOLDEN_BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.GOLDEN_BOOK_WYRM_SCALE))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, DLItems.GOLDEN_BOOK_WYRM_SCALE_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', DLItems.GOLDEN_BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.GOLDEN_BOOK_WYRM_SCALE))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, DLItems.GOLDEN_BOOK_WYRM_SCALE_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', DLItems.GOLDEN_BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.GOLDEN_BOOK_WYRM_SCALE))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.COMBAT, DLItems.GOLDEN_BOOK_WYRM_SCALE_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .define('#', DLItems.GOLDEN_BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.GOLDEN_BOOK_WYRM_SCALE))
                .save(output);
    }

    private void addMiscRecipes() {
        ShapelessRecipeBuilder.shapeless(registry, RecipeCategory.MISC, DLItems.HEPHAESTITE_SHARD, 9)
                .requires(DLBlocks.HEPHAESTITE_BLOCK)
                .unlockedBy("has_item", has(DLBlocks.HEPHAESTITE_BLOCK))
                .save(output, createKey("hephaestite_shard_from_block"));
        ShapelessRecipeBuilder.shapeless(registry, RecipeCategory.MISC, DLItems.UMBRIUM_INGOT, 9)
                .requires(DLBlocks.UMBRIUM_BLOCK)
                .unlockedBy("has_item", has(DLBlocks.UMBRIUM_BLOCK))
                .save(output, createKey("umrium_ingot_from_block"));
        ShapelessRecipeBuilder.shapeless(registry, RecipeCategory.MISC, DLItems.SCARLITE, 9)
                .requires(DLBlocks.SCARLITE_BLOCK)
                .unlockedBy("has_item", has(DLBlocks.SCARLITE_BLOCK))
                .save(output, createKey("scarlite_from_block"));

        ShapelessRecipeBuilder.shapeless(registry, RecipeCategory.MISC, DLItems.UMBRIUM_NUGGET, 9)
                .requires(DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(output, createKey("umrium_nugget_from_ingot"));
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.UMBRIUM_INGOT, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', DLItems.UMBRIUM_NUGGET)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_NUGGET))
                .save(output, createKey("umrium_ingot_from_nugget"));

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.UMBRA_BLASTER)
                .pattern("  S")
                .pattern("##X")
                .pattern("  #")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', DLItems.SCUTTLER_EYE)
                .define('X', DLBlocks.UMBRIUM_BLOCK.asItem())
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(DLBlocks.UMBRIUM_BLOCK))
                .unlockedBy("has_item", has(DLItems.SCUTTLER_EYE))
                .save(output);

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.SCARLITE_RING)
                .pattern("#X ")
                .pattern("XSX")
                .pattern(" X ")
                .define('#', DLItems.SCARLITE)
                .define('X', DLItems.UMBRIUM_INGOT)
                .define('S', DLItems.BLACK_HEART)
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(DLItems.BLACK_HEART))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.PHYTOPROSTASIA_AMULET)
                .pattern("###")
                .pattern("X X")
                .pattern("SPS")
                .define('#', Items.STRING)
                .define('X', DLItems.UMBRIUM_INGOT)
                .define('S', DLBlocks.VILESPINE.asItem())
                .define('P', DLItems.SCUTTLER_EYE)
                .unlockedBy("has_item", has(Items.STRING))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(DLBlocks.VILESPINE))
                .unlockedBy("has_item", has(DLItems.SCUTTLER_EYE))
                .save(output);

        ShapelessRecipeBuilder.shapeless(registry, RecipeCategory.MISC, DLItems.DEFILEMENT_POWDER)
                .requires(DLBlocks.SCURONOTTE.asItem())
                .unlockedBy("has_item", has(DLBlocks.SCURONOTTE))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.RAVAGING_INGOT)
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .define('#', DLItems.RAVAGING_ESSENCE)
                .define('X', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLItems.RAVAGING_ESSENCE))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.REMORSEFUL_GEM)
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .define('#', DLItems.REMORSEFUL_ESSENCE)
                .define('X', DLItems.SCARLITE)
                .unlockedBy("has_item", has(DLItems.REMORSEFUL_ESSENCE))
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .save(output);

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLBlocks.HEALING_PAD)
                .pattern("#X#")
                .pattern("SPS")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('X', DLItems.SCARLITE)
                .define('S', DLBlocks.DEFILED_STONE)
                .define('P', DLItems.BLACK_HEART)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .unlockedBy("has_item", has(DLItems.BLACK_HEART))
                .save(output);

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.RAVAGING_PELLET)
                .pattern(" S ")
                .pattern("SRS")
                .pattern(" S ")
                .define('S', DLBlocks.DEFILED_STONE)
                .define('R', DLItems.RAVAGING_INGOT)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .unlockedBy("has_item", has(DLItems.RAVAGING_INGOT))
                .save(output);
        ShapelessRecipeBuilder.shapeless(registry, RecipeCategory.MISC, DLItems.RAVAGING_PELLET)
                .requires(DLItems.RAVAGING_ESSENCE)
                .requires(DLItems.UMBRIUM_PELLET, 2)
                .unlockedBy("has_item", has(DLItems.RAVAGING_ESSENCE))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_PELLET))
                .save(output, createKey("ravaging_pellet_upgrade"));
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.SPIKED_PELLET)
                .pattern("V V")
                .pattern(" S ")
                .pattern("V V")
                .define('V', DLBlocks.VILESPINE)
                .define('S', DLItems.UMBRIUM_PELLET)
                .unlockedBy("has_item", has(DLBlocks.VILESPINE))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_PELLET))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.UMBRIUM_PELLET)
                .pattern(" S ")
                .pattern("SUS")
                .pattern(" S ")
                .define('S', DLBlocks.DEFILED_STONE)
                .define('U', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(output);

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.CALLING_STONE)
                .pattern("TST")
                .pattern("SHS")
                .pattern("TST")
                .define('T', DLBlocks.DEFILED_STONE)
                .define('S', DLItems.SCARLITE)
                .define('H', DLItems.BLACK_HEART)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .unlockedBy("has_item", has(DLItems.BLACK_HEART))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.IDOL_SORROW)
                .pattern(" R ")
                .pattern("USU")
                .pattern(" U ")
                .define('R', DLItems.RAVAGING_INGOT)
                .define('U', DLItems.UMBRIUM_INGOT)
                .define('S', DLItems.SCARLITE)
                .unlockedBy("has_item", has(DLItems.RAVAGING_INGOT))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .save(output);

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, Items.ARROW)
                .pattern("V")
                .pattern("S")
                .pattern("F")
                .define('V', DLBlocks.VILESPINE)
                .define('S', Items.STICK)
                .define('F', Items.FEATHER)
                .unlockedBy("has_item", has(DLBlocks.VILESPINE))
                .unlockedBy("has_item", has(Items.STICK))
                .unlockedBy("has_item", has(Items.FEATHER))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, Items.TORCH)
                .pattern("R")
                .pattern("S")
                .define('R', DLItems.HEPHAESTITE_SHARD)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.HEPHAESTITE_SHARD))
                .unlockedBy("has_item", has(Items.STICK))
                .save(output);

        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.THE_RAVAGER)
                .pattern("  E")
                .pattern("RUC")
                .pattern("  L")
                .define('U', DLItems.UMBRIUM_INGOT)
                .define('R', DLItems.RAVAGING_INGOT)
                .define('C', DLItems.SCARLITE)
                .define('E', DLItems.SCUTTLER_EYE)
                .define('L', Items.LEATHER)
                .unlockedBy("has_item", has(DLItems.RAVAGING_INGOT))
                .unlockedBy("has_item", has(Items.LEATHER))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .unlockedBy("has_item", has(DLItems.SCUTTLER_EYE))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.TEARS_FLAME)
                .pattern("MRM")
                .pattern(" U ")
                .pattern(" U ")
                .define('M', DLBlocks.HEPHAESTITE_BLOCK)
                .define('R', DLItems.REMORSEFUL_GEM)
                .define('U', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLBlocks.HEPHAESTITE_BLOCK))
                .unlockedBy("has_item", has(DLItems.REMORSEFUL_GEM))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.TEARS_SHULKER)
                .pattern("MRM")
                .pattern(" U ")
                .pattern(" U ")
                .define('M', Items.SHULKER_SHELL)
                .define('R', DLItems.REMORSEFUL_GEM)
                .define('U', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(Items.SHULKER_SHELL))
                .unlockedBy("has_item", has(DLItems.REMORSEFUL_GEM))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.CONCUSSION_SMASHER)
                .pattern("UEE")
                .pattern("USU")
                .pattern("UUU")
                .define('U', DLItems.UMBRIUM_INGOT)
                .define('S', DLItems.SCARLITE)
                .define('E', DLItems.SCUTTLER_EYE)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .unlockedBy("has_item", has(DLItems.SCUTTLER_EYE))
                .save(output);
        ShapedRecipeBuilder.shaped(registry, RecipeCategory.MISC, DLItems.BOOK_WYRM_ANALYZER)
                .pattern("UUU")
                .pattern("UGU")
                .pattern("UEU")
                .define('U', DLItems.UMBRIUM_INGOT)
                .define('G', Items.GLASS)
                .define('E', DLItems.SCUTTLER_EYE)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(Items.GLASS))
                .unlockedBy("has_item", has(DLItems.SCUTTLER_EYE))
                .save(output);
        ShapelessRecipeBuilder.shapeless(registry, RecipeCategory.MISC, DLItems.BLAZING_BLASTEM_FRUIT, 4)
                .requires(DLItems.HEPHAESTITE_SHARD)
                .requires(DLItems.BLASTEM_FRUIT, 4)
                .unlockedBy("has_item", has(DLItems.HEPHAESTITE_SHARD))
                .unlockedBy("has_item", has(DLItems.BLASTEM_FRUIT))
                .save(output);

        ShapelessRecipeBuilder.shapeless(registry, RecipeCategory.MISC, DLItems.FOUL_CANDY)
                .requires(DLItems.FOUL_SLIME)
                .requires(DLItems.DEFILEMENT_POWDER)
                .requires(Items.SUGAR)
                .unlockedBy("has_item", has(DLItems.FOUL_SLIME))
                .unlockedBy("has_item", has(DLItems.DEFILEMENT_POWDER))
                .unlockedBy("has_item", has(Items.SUGAR))
                .save(output);
    }

    public void addSmeltingRecipes() {
        smeltingAndBlasting(DLBlocks.HEPHAESTITE_ORE.get(), RecipeCategory.MISC,
                DLItems.HEPHAESTITE_SHARD, 0.1f, 200, "hephaestite_shard");
        smeltingAndBlasting(DLBlocks.UMBRIUM_ORE.get(), RecipeCategory.MISC,
                DLItems.UMBRIUM_INGOT, 0.1f, 200, "umbrium_ingot");
        smeltingAndBlasting(DLBlocks.SCARLITE_ORE.get(), RecipeCategory.MISC,
                DLItems.SCARLITE, 0.1f, 200, "scarlite");
        smeltingAndSmoking(DLItems.RAW_BOOK_WYRM, RecipeCategory.FOOD,
                DLItems.COOKED_BOOK_WYRM, 0.1f, 200, "cooked_book_wyrm");
    }

    public void smeltingAndBlasting(ItemLike ingredient, RecipeCategory category, ItemLike result, float experience, int cookingTime, String name) {
        smelting(ingredient, category, result, experience, cookingTime, name);
        blasting(ingredient, category, result, experience, cookingTime / 2, name);
    }

    public void smeltingAndSmoking(ItemLike ingredient, RecipeCategory category, ItemLike result, float experience, int cookingTime, String name) {
        smelting(ingredient, category, result, experience, cookingTime, name);
        smoking(ingredient, category, result, experience, cookingTime / 2, name);
    }

    public void smelting(ItemLike ingredient, RecipeCategory category, ItemLike result, float experience, int cookingTime, String name) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_item", has(ingredient))
                .save(output, createKey(name + "_smelting"));
    }

    public void blasting(ItemLike ingredient, RecipeCategory category, ItemLike result, float experience, int cookingTime, String name) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_item", has(ingredient))
                .save(output, createKey(name + "_blasting"));
    }

    public void smoking(ItemLike ingredient, RecipeCategory category, ItemLike result, float experience, int cookingTime, String name) {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_item", has(ingredient))
                .save(output, createKey(name + "_smoking"));
    }

    protected ResourceLocation createLocation(String name) {
        return Utils.prefix(name);
    }

    protected ResourceKey<Recipe<?>> createKey(String name) {
        return ResourceKey.create(Registries.RECIPE, createLocation(name));
    }
}
