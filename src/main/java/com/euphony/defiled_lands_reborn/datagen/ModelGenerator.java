package com.euphony.defiled_lands_reborn.datagen;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.common.block.ConjuringAltarBlock;
import com.euphony.defiled_lands_reborn.common.block.VilespineBlock;
import com.euphony.defiled_lands_reborn.common.init.DLBlocks;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.BlockModelWrapper;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.numeric.UseCycle;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

public class ModelGenerator extends ModelProvider {
    public ModelGenerator(PackOutput packOutput) {
        super(packOutput, DefiledLandsReborn.MOD_ID);
    }

    ModelTemplate ALTAR_BASE = new ModelTemplate(Optional.of(Utils.prefix("block/altar_base")), Optional.empty(), TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE);

    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModel(itemModels, DLItems.BLACK_HEART.get());
        itemModel(itemModels, DLItems.BLASTEM_FRUIT.get());
        itemModel(itemModels, DLItems.BLASTEM_SEED.get());
        itemModel(itemModels, DLItems.BLAZING_BLASTEM_FRUIT.get());

        itemModel(itemModels, DLItems.BOOK_WYRM_ANALYZER.get());

        itemModel(itemModels, DLItems.BOOK_WYRM_SCALE.get());
        itemModel(itemModels, DLItems.BOOK_WYRM_SCALE_BOOTS.get());
        itemModel(itemModels, DLItems.BOOK_WYRM_SCALE_CHESTPLATE.get());
        itemModel(itemModels, DLItems.BOOK_WYRM_SCALE_HELMET.get());
        itemModel(itemModels, DLItems.BOOK_WYRM_SCALE_LEGGINGS.get());

        itemModel(itemModels, DLItems.CALLING_STONE.get());

        generateConcussionSmasher(itemModels, DLItems.CONCUSSION_SMASHER.get());

        itemModel(itemModels, DLItems.RAW_BOOK_WYRM.get());
        itemModel(itemModels, DLItems.COOKED_BOOK_WYRM.get());
        itemModel(itemModels, DLItems.DEFILEMENT_POWDER.get());
        itemModel(itemModels, DLItems.FOUL_CANDY.get());
        itemModel(itemModels, DLItems.FOUL_SLIME.get());

        itemModel(itemModels, DLItems.GOLDEN_BOOK_WYRM_SCALE.get());
        itemModel(itemModels, DLItems.GOLDEN_BOOK_WYRM_SCALE_BOOTS.get());
        itemModel(itemModels, DLItems.GOLDEN_BOOK_WYRM_SCALE_CHESTPLATE.get());
        itemModel(itemModels, DLItems.GOLDEN_BOOK_WYRM_SCALE_HELMET.get());
        itemModel(itemModels, DLItems.GOLDEN_BOOK_WYRM_SCALE_LEGGINGS.get());

        itemModel(itemModels, DLItems.HEPHAESTITE_SHARD.get());
        itemModel(itemModels, DLItems.IDOL_SORROW.get());
        itemModel(itemModels, DLItems.PHYTOPROSTASIA_AMULET.get());
        itemModel(itemModels, DLItems.RAVAGING_ESSENCE.get());

        itemModel(itemModels, DLItems.RAVAGING_INGOT.get());

        toolModel(itemModels, DLItems.RAVAGING_PELLET.get());
        toolModel(itemModels, DLItems.RAVAGING_PICKAXE.get());
        toolModel(itemModels, DLItems.RAVAGING_SHOVEL.get());
        toolModel(itemModels, DLItems.RAVAGING_AXE.get());

        itemModel(itemModels, DLItems.REMORSEFUL_ESSENCE.get());
        itemModel(itemModels, DLItems.REMORSEFUL_GEM.get());

        itemModel(itemModels, DLItems.SCARLITE.get());
        toolModel(itemModels, DLItems.SCARLITE_RAZOR.get());
        toolModel(itemModels, DLItems.SCARLITE_REAVER.get());

        itemModel(itemModels, DLItems.SCARLITE_RING.get());
        itemModel(itemModels, DLItems.SCUTTLER_EYE.get());
        itemModel(itemModels, DLItems.SPIKED_PELLET.get());

        toolModel(itemModels, DLItems.TEARS_FLAME.get());
        toolModel(itemModels, DLItems.TEARS_SHULKER.get());

        toolModel(itemModels, DLItems.THE_RAVAGER.get());
        toolModel(itemModels, DLItems.UMBRA_BLASTER.get());

        toolModel(itemModels, DLItems.UMBRIUM_AXE.get());
        toolModel(itemModels, DLItems.UMBRIUM_SWORD.get());
        toolModel(itemModels, DLItems.UMBRIUM_PICKAXE.get());
        toolModel(itemModels, DLItems.UMBRIUM_SHOVEL.get());
        toolModel(itemModels, DLItems.UMBRIUM_HOE.get());

        itemModel(itemModels, DLItems.UMBRIUM_HELMET.get());
        itemModel(itemModels, DLItems.UMBRIUM_CHESTPLATE.get());
        itemModel(itemModels, DLItems.UMBRIUM_BOOTS.get());
        itemModel(itemModels, DLItems.UMBRIUM_LEGGINGS.get());

        itemModel(itemModels, DLItems.UMBRIUM_INGOT.get());
        itemModel(itemModels, DLItems.UMBRIUM_NUGGET.get());
        itemModel(itemModels, DLItems.UMBRIUM_PELLET.get());

        blockModel(blockModels, DLBlocks.DEFILED_DIRT.get());
        blockModel(blockModels, DLBlocks.DEFILED_GRAVEL.get());
        blockModel(blockModels, DLBlocks.DEFILED_SAND.get());
        blockModel(blockModels, DLBlocks.DEFILED_CRACKED_STONE_BRICKS.get());

        blockModel(blockModels, DLBlocks.DEFILED_MOSSY_STONE.get());
        blockModel(blockModels, DLBlocks.DEFILED_MOSSY_STONE_BRICKS.get());

        blockModel(blockModels, DLBlocks.HEPHAESTITE_ORE.get());
        blockModel(blockModels, DLBlocks.HEPHAESTITE_BLOCK.get());

        blockModel(blockModels, DLBlocks.RAVAGING_STONE.get());
        blockModel(blockModels, DLBlocks.RAVAGING_BRICKS.get());

        blockModel(blockModels, DLBlocks.SCARLITE_ORE.get());
        blockModel(blockModels, DLBlocks.SCARLITE_BLOCK.get());

        blockModel(blockModels, DLBlocks.UMBRIUM_BLOCK.get());
        blockModel(blockModels, DLBlocks.UMBRIUM_ORE.get());

        DLBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateModel).forEach((p_386718_) -> blockModels.family(p_386718_.getBaseBlock()).generateFor(p_386718_));

        blockModels.woodProvider(DLBlocks.TENEBRA_LOG.get()).logWithHorizontal(DLBlocks.TENEBRA_LOG.get());
        createPlantWithDefaultItem(blockModels, DLBlocks.TENEBRA_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        createLeaves(blockModels, DLBlocks.TENEBRA_LEAVES.get(), TexturedModel.LEAVES);

        createHealingPad(blockModels);

        createPlantWithDefaultItem(blockModels, DLBlocks.SCURONOTTE.get(), PlantType.NOT_TINTED);

        createVilespine(blockModels);

        createDefiledGrassBlocks(blockModels);

        createBlastem(blockModels);

        createConjuringAltar(blockModels);

        DLItems.SPAWN_EGGS.forEach(item -> itemModel(itemModels, item.get()));
    }

    public void generateConcussionSmasher(ItemModelGenerators itemModels, Item item) {
        ItemModel.Unbaked itemmodel$unbaked = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked itemmodel$unbaked1 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_pulling_0", ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked itemmodel$unbaked2 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_pulling_1", ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked itemmodel$unbaked3 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_pulling_2", ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked itemmodel$unbaked4 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_pulling_3", ModelTemplates.FLAT_ITEM));
        itemModels.itemModelOutput.accept(item, ItemModelUtils.rangeSelect(new UseCycle(10.0F), 0.1F, itemmodel$unbaked, ItemModelUtils.override(itemmodel$unbaked1, 0.2F), ItemModelUtils.override(itemmodel$unbaked2, 0.4F), ItemModelUtils.override(itemmodel$unbaked3, 0.6F), ItemModelUtils.override(itemmodel$unbaked4, 0.8F)));
    }

    public void createDefiledGrassBlocks(BlockModelGenerators blockModels) {
        ResourceLocation resourcelocation = TextureMapping.getBlockTexture(DLBlocks.DEFILED_DIRT.get());

        TextureMapping texturemapping = (new TextureMapping()).put(TextureSlot.BOTTOM, resourcelocation).copyForced(TextureSlot.BOTTOM, TextureSlot.PARTICLE).put(TextureSlot.TOP, TextureMapping.getBlockTexture(DLBlocks.DEFILED_GRASS_BLOCK.get(), "_top")).put(TextureSlot.SIDE, TextureMapping.getBlockTexture(DLBlocks.DEFILED_GRASS_BLOCK.get(), "_side"));
        plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.create(DLBlocks.DEFILED_GRASS_BLOCK.get(), texturemapping, blockModels.modelOutput));

        texturemapping = texturemapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(Blocks.SNOW));
        MultiVariant multivariant = plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(DLBlocks.DEFILED_GRASS_BLOCK.get(), "_snow", texturemapping, blockModels.modelOutput));

        ResourceLocation resourcelocation1 = ModelLocationUtils.getModelLocation(DLBlocks.DEFILED_GRASS_BLOCK.get());
        blockModels.registerSimpleItemModel(DLBlocks.DEFILED_GRASS_BLOCK.get(), resourcelocation1);
        blockModels.createGrassLikeBlock(DLBlocks.DEFILED_GRASS_BLOCK.get(), createRotatedVariants(plainModel(resourcelocation1)), multivariant);
    }

    public void createBlastem(BlockModelGenerators blockModels) {
        MultiVariant multiVariant = createBlastemVariant(blockModels, "0");
        MultiVariant multiVariant1 = createBlastemVariant(blockModels, "2");
        MultiVariant multiVariant2 = createBlastemVariant(blockModels, "4");
        MultiVariant multiVariant3 = createBlastemVariant(blockModels, "6");
        MultiVariant multiVariant4 = createBlastemVariant(blockModels, "8");
        MultiVariant multiVariant5 = createBlastemVariant(blockModels, "10");
        MultiVariant multiVariant6 = createBlastemVariant(blockModels, "11");
        MultiVariant multiVariant7 = createBlastemVariant(blockModels, "13");
        MultiVariant multiVariant8 = createBlastemVariant(blockModels, "15");

        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(DLBlocks.BLASTEM.get())
                .with(PropertyDispatch.initial(BlockStateProperties.AGE_15)
                        .select(0, multiVariant)
                        .select(1, multiVariant)
                        .select(2, multiVariant1)
                        .select(3, multiVariant1)
                        .select(4, multiVariant2)
                        .select(5, multiVariant2)
                        .select(6, multiVariant3)
                        .select(7, multiVariant3)
                        .select(8, multiVariant4)
                        .select(9, multiVariant4)
                        .select(10, multiVariant5)
                        .select(11, multiVariant6)
                        .select(12, multiVariant6)
                        .select(13, multiVariant7)
                        .select(14, multiVariant7)
                        .select(15, multiVariant8)
                )
        );
        blockModels.registerSimpleFlatItemModel(DLBlocks.BLASTEM.get(), "/10");
    }

    public MultiVariant createBlastemVariant(BlockModelGenerators blockModels, String suffix) {
        return plainVariant(createSuffixedVariant(blockModels, DLBlocks.BLASTEM.get(), "/" + suffix, PlantType.NOT_TINTED.getCross(), TextureMapping::cross));
    }

    public void createVilespine(BlockModelGenerators blockModels) {
        MultiVariant multiVariant = plainVariant(createSuffixedVariant(blockModels, DLBlocks.VILESPINE.get(), "_part", PlantType.NOT_TINTED.getCross(), TextureMapping::cross));
        MultiVariant multiVariant1 = plainVariant(createSuffixedVariant(blockModels, DLBlocks.VILESPINE.get(), "_top", PlantType.NOT_TINTED.getCross(), TextureMapping::cross));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(DLBlocks.VILESPINE.get())
                .with(PropertyDispatch.initial(VilespineBlock.TOPMOST)
                        .select(false, multiVariant)
                        .select(true, multiVariant1)
                )
        );
        blockModels.registerSimpleFlatItemModel(DLBlocks.VILESPINE.asItem());
    }

    public ResourceLocation createSuffixedVariant(BlockModelGenerators blockModels, Block block, String suffix, ModelTemplate modelTemplate, Function<ResourceLocation, TextureMapping> textureMappingGetter) {
        return modelTemplate.extend().renderType("cutout").build().createWithSuffix(block, suffix, (TextureMapping)textureMappingGetter.apply(TextureMapping.getBlockTexture(block, suffix)), blockModels.modelOutput);
    }

    public void createConjuringAltar(BlockModelGenerators blockModels) {
        TextureMapping texturemapping = (new TextureMapping())
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(DLBlocks.DEFILED_STONE.get()))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(DLBlocks.CONJURING_ALTAR.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(DLBlocks.CONJURING_ALTAR.get(), "_side"));

        MultiVariant multiVariant = plainVariant(ALTAR_BASE.create(DLBlocks.CONJURING_ALTAR.get(), texturemapping, blockModels.modelOutput));
        texturemapping = texturemapping
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(DLBlocks.CONJURING_ALTAR.get(), "_top_active"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(DLBlocks.CONJURING_ALTAR.get(), "_side_active"));
        MultiVariant multiVariant1 = plainVariant(ALTAR_BASE.createWithSuffix(DLBlocks.CONJURING_ALTAR.get(), "_active", texturemapping, blockModels.modelOutput));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(DLBlocks.CONJURING_ALTAR.get())
                .with(PropertyDispatch.initial(ConjuringAltarBlock.ACTIVE)
                        .select(false, multiVariant)
                        .select(true, multiVariant1)
                )
        );

        ResourceLocation resourcelocation = ModelLocationUtils.getModelLocation(DLBlocks.CONJURING_ALTAR.get());
        blockModels.registerSimpleItemModel(DLBlocks.CONJURING_ALTAR.get(), resourcelocation);
    }

    public void createHealingPad(BlockModelGenerators blockModels) {
        TextureMapping texturemapping = (new TextureMapping())
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(DLBlocks.DEFILED_STONE.get()))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(DLBlocks.HEALING_PAD.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(DLBlocks.HEALING_PAD.get(), "_side"));
        ResourceLocation resourcelocation = ModelTemplates.SLAB_BOTTOM.create(DLBlocks.HEALING_PAD.get(), texturemapping, blockModels.modelOutput);
        MultiVariant multivariant = BlockModelGenerators.plainVariant(resourcelocation);
        blockModels.blockStateOutput.accept(createSimpleBlock(DLBlocks.HEALING_PAD.get(), multivariant));
        blockModels.registerSimpleItemModel(DLBlocks.HEALING_PAD.get(), resourcelocation);
    }

    public void createPlantWithDefaultItem(BlockModelGenerators blockModels, Block block, PlantType plantType) {
        blockModels.registerSimpleItemModel(block.asItem(), plantType.createItemModel(blockModels, block));
        this.createPlant(blockModels, block, plantType);
    }

    public void createPlant(BlockModelGenerators blockModels, Block block, PlantType plantType) {
        createCrossBlock(blockModels, block, plantType);
    }

    public void createCrossBlock(BlockModelGenerators blockModels, Block block, PlantType plantType) {
        TextureMapping texturemapping = plantType.getTextureMapping(block);
        this.createCrossBlock(blockModels, block, plantType, texturemapping);
    }

    public void createCrossBlock(BlockModelGenerators blockModels, Block block, PlantType plantType, TextureMapping textureMapping) {
        MultiVariant multivariant = plainVariant(plantType.getCross().extend().renderType("cutout").build().create(block, textureMapping, blockModels.modelOutput));
        blockModels.blockStateOutput.accept(createSimpleBlock(block, multivariant));
    }

    public void createLeaves(BlockModelGenerators blockModels, Block block, TexturedModel.Provider provider) {
        ResourceLocation resourcelocation = provider.create(block, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(createSimpleBlock(block, plainVariant(resourcelocation)));
        blockModels.registerSimpleItemModel(block, resourcelocation);
    }

    public void blockModel(BlockModelGenerators blockModels, Block block) {
        blockModels.createTrivialCube(block);
    }

    public void toolModel(ItemModelGenerators itemModels, Item item) {
        this.itemModel(itemModels, item, ModelTemplates.FLAT_HANDHELD_ITEM);
    }

    public void itemModel(ItemModelGenerators itemModels, Item item) {
        this.itemModel(itemModels, item, ModelTemplates.FLAT_ITEM);
    }

    public void itemModel(ItemModelGenerators itemModels, Item item, ModelTemplate template) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
        ResourceLocation textureLoc = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + itemId.getPath());
        TextureMapping textureMapping = new TextureMapping().put(TextureSlot.LAYER0, textureLoc);
        itemModels.itemModelOutput.accept(item, new BlockModelWrapper.Unbaked(template.create(item, textureMapping, itemModels.modelOutput), Collections.emptyList()));
    }

    public void itemModel(ItemModelGenerators itemModels, Item item, String loc) {
        this.itemModel(itemModels, item, ModelTemplates.FLAT_ITEM, loc);
    }

    public void itemModel(ItemModelGenerators itemModels, Item item, ModelTemplate template, String loc) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
        ResourceLocation textureLoc = ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "item/" + loc);
        TextureMapping textureMapping = new TextureMapping().put(TextureSlot.LAYER0, textureLoc);
        itemModels.itemModelOutput.accept(item, new BlockModelWrapper.Unbaked(template.create(item, textureMapping, itemModels.modelOutput), Collections.emptyList()));
    }
}
