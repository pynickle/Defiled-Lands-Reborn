package com.euphony.defiled_lands_reborn.data;

import com.euphony.defiled_lands_reborn.common.init.DLBlocks;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends RecipeProvider {
    public RecipeGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        addDecorationRecipes(recipeOutput);
        addDecorationStoneCuttingRecipes(recipeOutput);
        addDecorationSmeltingRecipes(recipeOutput);
        addToolRecipes(recipeOutput);
        addEquipmentRecipes(recipeOutput);
        addMiscRecipes(recipeOutput);
        addSmeltingRecipes(recipeOutput);
    }

    private void addDecorationRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_SANDSTONE)
                .pattern("##")
                .pattern("##")
                .define('#', DLBlocks.DEFILED_SAND.asItem())
                .unlockedBy("has_item", has(DLBlocks.DEFILED_SAND))
                .save(recipeOutput, createKey("defiled_sandstone"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DLBlocks.TENEBRA_PLANKS, 4)
                .requires(DLBlocks.TENEBRA_LOG)
                .unlockedBy("has_item", has(DLBlocks.TENEBRA_LOG))
                .save(recipeOutput, createKey("tenebra_planks"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.TENEBRA_SLAB)
                .pattern("###")
                .define('#', DLBlocks.TENEBRA_PLANKS.asItem())
                .unlockedBy("has_item", has(DLBlocks.TENEBRA_PLANKS))
                .save(recipeOutput, createKey("tenebra_slab"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.TENEBRA_STAIRS)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', DLBlocks.TENEBRA_PLANKS.asItem())
                .unlockedBy("has_item", has(DLBlocks.TENEBRA_PLANKS))
                .save(recipeOutput, createKey("tenebra_stairs"));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, DLBlocks.TENEBRA_DOOR)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', DLBlocks.TENEBRA_PLANKS.asItem())
                .unlockedBy("has_item", has(DLBlocks.TENEBRA_PLANKS))
                .save(recipeOutput, createKey("tenebra_door"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_SANDSTONE_SLAB)
                .pattern("###")
                .define('#', DLBlocks.DEFILED_SANDSTONE.asItem())
                .unlockedBy("has_item", has(DLBlocks.DEFILED_SANDSTONE))
                .save(recipeOutput, createKey("defiled_sandstone_slab"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_SLAB)
                .pattern("###")
                .define('#', DLBlocks.DEFILED_STONE.asItem())
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .save(recipeOutput, createKey("defiled_stone_slab"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_BRICK_SLAB)
                .pattern("###")
                .define('#', DLBlocks.DEFILED_STONE_BRICKS)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE_BRICKS))
                .save(recipeOutput, createKey("defiled_stone_brick_slab"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_STAIRS)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', DLBlocks.DEFILED_STONE.asItem())
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .save(recipeOutput, createKey("defiled_stone_stairs"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_SANDSTONE_STAIRS)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', DLBlocks.DEFILED_SANDSTONE.asItem())
                .unlockedBy("has_item", has(DLBlocks.DEFILED_SANDSTONE))
                .save(recipeOutput, createKey("defiled_sandstone_stairs"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_BRICK_STAIRS)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', DLBlocks.DEFILED_STONE_BRICKS)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE_BRICKS))
                .save(recipeOutput, createKey("defiled_stone_brick_stairs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.HEPHAESTITE_BLOCK)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', DLItems.HEPHAESTITE_SHARD)
                .unlockedBy("has_item", has(DLItems.HEPHAESTITE_SHARD))
                .save(recipeOutput, createKey("hephaestite_block"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.UMBRIUM_BLOCK)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(recipeOutput, createKey("umbrium_block"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.SCARLITE_BLOCK)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', DLItems.SCARLITE)
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .save(recipeOutput, createKey("scarlite_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.RAVAGING_STONE)
                .pattern("###")
                .pattern("#X#")
                .pattern("#X#")
                .define('#', DLBlocks.DEFILED_STONE)
                .define('X', DLItems.RAVAGING_ESSENCE)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .unlockedBy("has_item", has(DLItems.RAVAGING_ESSENCE))
                .save(recipeOutput, createKey("ravaging_stone"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.RAVAGING_BRICKS)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .define('#', Blocks.BRICKS)
                .define('X', DLItems.RAVAGING_ESSENCE)
                .unlockedBy("has_item", has(Blocks.BRICKS))
                .unlockedBy("has_item", has(DLItems.RAVAGING_ESSENCE))
                .save(recipeOutput, createKey("ravaging_bricks"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_BRICKS)
                .pattern("##")
                .pattern("##")
                .define('#', DLBlocks.DEFILED_STONE)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .save(recipeOutput, createKey("defiled_stone_bricks"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_MOSSY_STONE)
                .requires(DLBlocks.DEFILED_STONE)
                .requires(Blocks.MOSS_BLOCK)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .unlockedBy("has_item", has(Blocks.MOSS_BLOCK))
                .save(recipeOutput, createKey("defiled_mossy_stone_from_moss_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_MOSSY_STONE)
                .requires(DLBlocks.DEFILED_STONE)
                .requires(Blocks.VINE)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .unlockedBy("has_item", has(Blocks.VINE))
                .save(recipeOutput, createKey("defiled_mossy_stone_from_vine"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_MOSSY_STONE_BRICKS)
                .pattern("##")
                .pattern("##")
                .define('#', DLBlocks.DEFILED_MOSSY_STONE)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_MOSSY_STONE))
                .save(recipeOutput, createKey("defiled_mossy_stone_bricks"));
    }

    private void addDecorationStoneCuttingRecipes(RecipeOutput recipeOutput) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_STONE), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_BRICKS, 1)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .save(recipeOutput, createKey("defiled_stone_bricks_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_MOSSY_STONE), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_MOSSY_STONE_BRICKS, 1)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_MOSSY_STONE))
                .save(recipeOutput, createKey("defiled_mossy_stone_bricks_from_stonecutting"));

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_STONE), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_SLAB, 2)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .save(recipeOutput, createKey("defiled_stone_slab_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_STONE), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_STAIRS, 1)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .save(recipeOutput, createKey("defiled_stone_stairs_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_SANDSTONE_SLAB, 2)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_SANDSTONE))
                .save(recipeOutput, createKey("defiled_sandstone_slab_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_SANDSTONE_STAIRS, 1)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_SANDSTONE))
                .save(recipeOutput, createKey("defiled_sandstone_stairs_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_BRICK_SLAB, 2)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE_BRICKS))
                .save(recipeOutput, createKey("defiled_stone_brick_slab_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DLBlocks.DEFILED_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_STONE_BRICK_STAIRS, 1)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE_BRICKS))
                .save(recipeOutput, createKey("defiled_stone_brick_stairs_from_stonecutting"));
    }

    private void addDecorationSmeltingRecipes(RecipeOutput recipeOutput) {
        SimpleCookingRecipeBuilder
                .smelting(Ingredient.of(DLBlocks.DEFILED_STONE_BRICKS), RecipeCategory.BUILDING_BLOCKS, DLBlocks.DEFILED_CRACKED_STONE_BRICKS, 0.1f, 200)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE_BRICKS))
                .save(recipeOutput, createKey("defiled_cracked_stone_bricks_from_smelting"));
    }

    private void addToolRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DLItems.UMBRIUM_AXE)
                .pattern("## ")
                .pattern("#S ")
                .pattern(" S ")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(recipeOutput, createKey("umbrium_axe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DLItems.UMBRIUM_PICKAXE)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(recipeOutput, createKey("umbrium_pickaxe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DLItems.UMBRIUM_SHOVEL)
                .pattern("#")
                .pattern("S")
                .pattern("S")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(recipeOutput, createKey("umbrium_shovel"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DLItems.UMBRIUM_SWORD)
                .pattern("#")
                .pattern("#")
                .pattern("S")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(recipeOutput, createKey("umbrium_sword"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DLItems.UMBRIUM_HOE)
                .pattern("##")
                .pattern(" S")
                .pattern(" S")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(recipeOutput, createKey("umbrium_hoe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DLItems.RAVAGING_AXE)
                .pattern("## ")
                .pattern("#S ")
                .pattern(" S ")
                .define('#', DLItems.RAVAGING_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.RAVAGING_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(recipeOutput, createKey("ravaging_axe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DLItems.RAVAGING_PICKAXE)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .define('#', DLItems.RAVAGING_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.RAVAGING_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(recipeOutput, createKey("ravaging_pickaxe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DLItems.RAVAGING_SHOVEL)
                .pattern("#")
                .pattern("S")
                .pattern("S")
                .define('#', DLItems.RAVAGING_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.RAVAGING_INGOT))
                .unlockedBy("has_item", has(Items.STICK))
                .save(recipeOutput, createKey("ravaging_shovel"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DLItems.SCARLITE_RAZOR)
                .pattern("# ")
                .pattern("#S")
                .pattern(" X")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', DLItems.BLACK_HEART)
                .define('X', DLItems.SCARLITE)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .unlockedBy("has_item", has(DLItems.BLACK_HEART))
                .save(recipeOutput, createKey("scarlite_razor"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, DLItems.SCARLITE_REAVER)
                .pattern("X  ")
                .pattern("XSX")
                .pattern(" # ")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', DLItems.BLACK_HEART)
                .define('X', DLItems.SCARLITE)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .unlockedBy("has_item", has(DLItems.BLACK_HEART))
                .save(recipeOutput, createKey("scarlite_reaver"));

    }

    private void addEquipmentRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DLItems.UMBRIUM_HELMET)
                .pattern("###")
                .pattern("# #")
                .define('#', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(recipeOutput, createKey("umbrium_helmet"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DLItems.UMBRIUM_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(recipeOutput, createKey("umbrium_chestplate"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DLItems.UMBRIUM_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(recipeOutput, createKey("umbrium_leggings"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DLItems.UMBRIUM_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .define('#', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(recipeOutput, createKey("umbrium_boots"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DLItems.BOOK_WYRM_SCALE_HELMET)
                .pattern("###")
                .pattern("# #")
                .define('#', DLItems.BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.BOOK_WYRM_SCALE))
                .save(recipeOutput, createKey("book_wyrm_scale_helmet"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DLItems.BOOK_WYRM_SCALE_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', DLItems.BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.BOOK_WYRM_SCALE))
                .save(recipeOutput, createKey("book_wyrm_scale_chestplate"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DLItems.BOOK_WYRM_SCALE_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', DLItems.BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.BOOK_WYRM_SCALE))
                .save(recipeOutput, createKey("book_wyrm_scale_leggings"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DLItems.BOOK_WYRM_SCALE_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .define('#', DLItems.BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.BOOK_WYRM_SCALE))
                .save(recipeOutput, createKey("book_wyrm_scale_boots"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DLItems.GOLDEN_BOOK_WYRM_SCALE_HELMET)
                .pattern("###")
                .pattern("# #")
                .define('#', DLItems.GOLDEN_BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.GOLDEN_BOOK_WYRM_SCALE))
                .save(recipeOutput, createKey("golden_book_wyrm_scale_helmet"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DLItems.GOLDEN_BOOK_WYRM_SCALE_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', DLItems.GOLDEN_BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.GOLDEN_BOOK_WYRM_SCALE))
                .save(recipeOutput, createKey("golden_book_wyrm_scale_chestplate"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DLItems.GOLDEN_BOOK_WYRM_SCALE_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', DLItems.GOLDEN_BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.GOLDEN_BOOK_WYRM_SCALE))
                .save(recipeOutput, createKey("golden_book_wyrm_scale_leggings"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, DLItems.GOLDEN_BOOK_WYRM_SCALE_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .define('#', DLItems.GOLDEN_BOOK_WYRM_SCALE)
                .unlockedBy("has_item", has(DLItems.GOLDEN_BOOK_WYRM_SCALE))
                .save(recipeOutput, createKey("golden_book_wyrm_scale_boots"));
    }

    private void addMiscRecipes(RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DLItems.HEPHAESTITE_SHARD, 9)
                .requires(DLBlocks.HEPHAESTITE_BLOCK)
                .unlockedBy("has_item", has(DLBlocks.HEPHAESTITE_BLOCK))
                .save(recipeOutput, createKey("hephaestite_shard"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DLItems.UMBRIUM_INGOT, 9)
                .requires(DLBlocks.UMBRIUM_BLOCK)
                .unlockedBy("has_item", has(DLBlocks.UMBRIUM_BLOCK))
                .save(recipeOutput, createKey("umbrium_ingot"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DLItems.SCARLITE, 9)
                .requires(DLBlocks.SCARLITE_BLOCK)
                .unlockedBy("has_item", has(DLBlocks.SCARLITE_BLOCK))
                .save(recipeOutput, createKey("scarlite"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DLItems.UMBRIUM_NUGGET, 9)
                .requires(DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(recipeOutput, createKey("umbrium_nugget"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.UMBRIUM_INGOT, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', DLItems.UMBRIUM_NUGGET)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_NUGGET))
                .save(recipeOutput, createKey("umbrium_ingot_from_nuggets"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.UMBRA_BLASTER)
                .pattern("  S")
                .pattern("##X")
                .pattern("  #")
                .define('#', DLItems.UMBRIUM_INGOT)
                .define('S', DLItems.SCUTTLER_EYE)
                .define('X', DLBlocks.UMBRIUM_BLOCK.asItem())
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(DLBlocks.UMBRIUM_BLOCK))
                .unlockedBy("has_item", has(DLItems.SCUTTLER_EYE))
                .save(recipeOutput, createKey("umbra_blaster"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.SCARLITE_RING)
                .pattern("#X ")
                .pattern("XSX")
                .pattern(" X ")
                .define('#', DLItems.SCARLITE)
                .define('X', DLItems.UMBRIUM_INGOT)
                .define('S', DLItems.BLACK_HEART)
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(DLItems.BLACK_HEART))
                .save(recipeOutput, createKey("scarlite_ring"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.PHYTOPROSTASIA_AMULET)
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
                .save(recipeOutput, createKey("phytoprostasia_amulet"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DLItems.DEFILEMENT_POWDER)
                .requires(DLBlocks.SCURONOTTE.asItem())
                .unlockedBy("has_item", has(DLBlocks.SCURONOTTE))
                .save(recipeOutput, createKey("defilement_powder"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.RAVAGING_INGOT)
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .define('#', DLItems.RAVAGING_ESSENCE)
                .define('X', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLItems.RAVAGING_ESSENCE))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(recipeOutput, createKey("ravaging_ingot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.REMORSEFUL_GEM)
                .pattern(" # ")
                .pattern("#X#")
                .pattern(" # ")
                .define('#', DLItems.REMORSEFUL_ESSENCE)
                .define('X', DLItems.SCARLITE)
                .unlockedBy("has_item", has(DLItems.REMORSEFUL_ESSENCE))
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .save(recipeOutput, createKey("remorseful_gem"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLBlocks.HEALING_PAD)
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
                .save(recipeOutput, createKey("healing_pad"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.RAVAGING_PELLET)
                .pattern(" S ")
                .pattern("SRS")
                .pattern(" S ")
                .define('S', DLBlocks.DEFILED_STONE)
                .define('R', DLItems.RAVAGING_INGOT)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .unlockedBy("has_item", has(DLItems.RAVAGING_INGOT))
                .save(recipeOutput, createKey("ravaging_pellet"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DLItems.RAVAGING_PELLET)
                .requires(DLItems.RAVAGING_ESSENCE)
                .requires(DLItems.UMBRIUM_PELLET, 2)
                .unlockedBy("has_item", has(DLItems.RAVAGING_ESSENCE))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_PELLET))
                .save(recipeOutput, createKey("ravaging_pellet_upgrade"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.SPIKED_PELLET)
                .pattern("V V")
                .pattern(" S ")
                .pattern("V V")
                .define('V', DLBlocks.VILESPINE)
                .define('S', DLItems.UMBRIUM_PELLET)
                .unlockedBy("has_item", has(DLBlocks.VILESPINE))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_PELLET))
                .save(recipeOutput, createKey("spiked_pellet"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.UMBRIUM_PELLET)
                .pattern(" S ")
                .pattern("SUS")
                .pattern(" S ")
                .define('S', DLBlocks.DEFILED_STONE)
                .define('U', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(recipeOutput, createKey("umbrium_pellet"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.CALLING_STONE)
                .pattern("TST")
                .pattern("SHS")
                .pattern("TST")
                .define('T', DLBlocks.DEFILED_STONE)
                .define('S', DLItems.SCARLITE)
                .define('H', DLItems.BLACK_HEART)
                .unlockedBy("has_item", has(DLBlocks.DEFILED_STONE))
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .unlockedBy("has_item", has(DLItems.BLACK_HEART))
                .save(recipeOutput, createKey("calling_stone"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.IDOL_SORROW)
                .pattern(" R ")
                .pattern("USU")
                .pattern(" U ")
                .define('R', DLItems.RAVAGING_INGOT)
                .define('U', DLItems.UMBRIUM_INGOT)
                .define('S', DLItems.SCARLITE)
                .unlockedBy("has_item", has(DLItems.RAVAGING_INGOT))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .save(recipeOutput, createKey("idol_sorrow"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.ARROW)
                .pattern("V")
                .pattern("S")
                .pattern("F")
                .define('V', DLBlocks.VILESPINE)
                .define('S', Items.STICK)
                .define('F', Items.FEATHER)
                .unlockedBy("has_item", has(DLBlocks.VILESPINE))
                .unlockedBy("has_item", has(Items.STICK))
                .unlockedBy("has_item", has(Items.FEATHER))
                .save(recipeOutput, createKey("arrow_vilespine"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.TORCH)
                .pattern("R")
                .pattern("S")
                .define('R', DLItems.HEPHAESTITE_SHARD)
                .define('S', Items.STICK)
                .unlockedBy("has_item", has(DLItems.HEPHAESTITE_SHARD))
                .unlockedBy("has_item", has(Items.STICK))
                .save(recipeOutput, createKey("torch_hephaestite"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.THE_RAVAGER)
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
                .save(recipeOutput, createKey("the_ravager"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.TEARS_FLAME)
                .pattern("MRM")
                .pattern(" U ")
                .pattern(" U ")
                .define('M', DLBlocks.HEPHAESTITE_BLOCK)
                .define('R', DLItems.REMORSEFUL_GEM)
                .define('U', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(DLBlocks.HEPHAESTITE_BLOCK))
                .unlockedBy("has_item", has(DLItems.REMORSEFUL_GEM))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(recipeOutput, createKey("tears_flame"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.TEARS_SHULKER)
                .pattern("MRM")
                .pattern(" U ")
                .pattern(" U ")
                .define('M', Items.SHULKER_SHELL)
                .define('R', DLItems.REMORSEFUL_GEM)
                .define('U', DLItems.UMBRIUM_INGOT)
                .unlockedBy("has_item", has(Items.SHULKER_SHELL))
                .unlockedBy("has_item", has(DLItems.REMORSEFUL_GEM))
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .save(recipeOutput, createKey("tears_shulker"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.CONCUSSION_SMASHER)
                .pattern("UEE")
                .pattern("USU")
                .pattern("UUU")
                .define('U', DLItems.UMBRIUM_INGOT)
                .define('S', DLItems.SCARLITE)
                .define('E', DLItems.SCUTTLER_EYE)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(DLItems.SCARLITE))
                .unlockedBy("has_item", has(DLItems.SCUTTLER_EYE))
                .save(recipeOutput, createKey("concussion_smasher"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DLItems.BOOK_WYRM_ANALYZER)
                .pattern("UUU")
                .pattern("UGU")
                .pattern("UEU")
                .define('U', DLItems.UMBRIUM_INGOT)
                .define('G', Items.GLASS)
                .define('E', DLItems.SCUTTLER_EYE)
                .unlockedBy("has_item", has(DLItems.UMBRIUM_INGOT))
                .unlockedBy("has_item", has(Items.GLASS))
                .unlockedBy("has_item", has(DLItems.SCUTTLER_EYE))
                .save(recipeOutput, createKey("book_wyrm_analyzer"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DLItems.BLAZING_BLASTEM_FRUIT, 4)
                .requires(DLItems.HEPHAESTITE_SHARD)
                .requires(DLItems.BLASTEM_FRUIT, 4)
                .unlockedBy("has_item", has(DLItems.HEPHAESTITE_SHARD))
                .unlockedBy("has_item", has(DLItems.BLASTEM_FRUIT))
                .save(recipeOutput, createKey("blazing_blastem_fruit"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DLItems.FOUL_CANDY)
                .requires(DLItems.FOUL_SLIME)
                .requires(DLItems.DEFILEMENT_POWDER)
                .requires(Items.SUGAR)
                .unlockedBy("has_item", has(DLItems.FOUL_SLIME))
                .unlockedBy("has_item", has(DLItems.DEFILEMENT_POWDER))
                .unlockedBy("has_item", has(Items.SUGAR))
                .save(recipeOutput, createKey("foul_candy"));
    }

    public void addSmeltingRecipes(RecipeOutput recipeOutput) {
        smeltingAndBlasting(DLBlocks.HEPHAESTITE_ORE.get(), RecipeCategory.MISC,
                DLItems.HEPHAESTITE_SHARD, 0.1f, 200, recipeOutput, "hephaestite_shard");
        smeltingAndBlasting(DLBlocks.UMBRIUM_ORE.get(), RecipeCategory.MISC,
                DLItems.UMBRIUM_INGOT, 0.1f, 200, recipeOutput, "umbrium_ingot");
        smeltingAndBlasting(DLBlocks.SCARLITE_ORE.get(), RecipeCategory.MISC,
                DLItems.SCARLITE, 0.1f, 200, recipeOutput, "scarlite");
        smeltingAndSmoking(DLItems.RAW_BOOK_WYRM, RecipeCategory.FOOD,
                DLItems.COOKED_BOOK_WYRM, 0.1f, 200, recipeOutput, "cooked_book_wyrm");
    }

    public void smeltingAndBlasting(ItemLike ingredient, RecipeCategory category, ItemLike result, float experience, int cookingTime, RecipeOutput recipeOutput, String name) {
        smelting(ingredient, category, result, experience, cookingTime, recipeOutput, name);
        blasting(ingredient, category, result, experience, cookingTime / 2, recipeOutput, name);
    }

    public void smeltingAndSmoking(ItemLike ingredient, RecipeCategory category, ItemLike result, float experience, int cookingTime, RecipeOutput recipeOutput, String name) {
        smelting(ingredient, category, result, experience, cookingTime, recipeOutput, name);
        smoking(ingredient, category, result, experience, cookingTime / 2, recipeOutput, name);
    }

    public void smelting(ItemLike ingredient, RecipeCategory category, ItemLike result, float experience, int cookingTime, RecipeOutput recipeOutput, String name) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), category, result, experience, cookingTime).unlockedBy("has_item", has(ingredient)).save(recipeOutput, createKey(name + "_from_smelting"));
    }

    public void blasting(ItemLike ingredient, RecipeCategory category, ItemLike result, float experience, int cookingTime, RecipeOutput recipeOutput, String name) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), category, result, experience, cookingTime).unlockedBy("has_item", has(ingredient)).save(recipeOutput, createKey(name + "_from_blasting"));
    }

    public void smoking(ItemLike ingredient, RecipeCategory category, ItemLike result, float experience, int cookingTime, RecipeOutput recipeOutput, String name) {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), category, result, experience, cookingTime).unlockedBy("has_item", has(ingredient)).save(recipeOutput, createKey(name + "_from_smoking"));
    }

    protected ResourceLocation createKey(String name) {
        return Utils.prefix(name);
    }
}