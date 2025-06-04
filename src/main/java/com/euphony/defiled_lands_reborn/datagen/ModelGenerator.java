package com.euphony.defiled_lands_reborn.datagen;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.common.init.DLBlocks;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.BlockModelWrapper;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.RangeSelectItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.numeric.CrossbowPull;
import net.minecraft.client.renderer.item.properties.numeric.UseCycle;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.client.renderer.item.properties.select.Charge;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.level.block.Block;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ModelGenerator extends ModelProvider {
    public ModelGenerator(PackOutput packOutput) {
        super(packOutput, DefiledLandsReborn.MOD_ID);
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        // return Stream.empty();
        return BuiltInRegistries.BLOCK.listElements().filter((holder) -> holder.getKey().location().getPath().contains("blastem"));
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        // return BuiltInRegistries.ITEM.listElements().filter((holder) -> holder.getKey().location().getPath().contains("black_heart"));
        return BuiltInRegistries.ITEM.listElements().filter((holder) -> holder.getKey().location().getNamespace().equals(this.modId));
    }

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

        concussionSmasherModel(itemModels, DLItems.CONCUSSION_SMASHER.get());

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
        blockModel(blockModels, DLBlocks.DEFILED_SANDSTONE.get());
        blockModel(blockModels, DLBlocks.DEFILED_CRACKED_STONE_BRICKS.get());

        blockModel(blockModels, DLBlocks.DEFILED_STONE.get());
        blockModel(blockModels, DLBlocks.DEFILED_MOSSY_STONE.get());
        blockModel(blockModels, DLBlocks.DEFILED_MOSSY_STONE_BRICKS.get());

        DLItems.SPAWN_EGGS.forEach(item -> itemModel(itemModels, item.get()));
    }

    public void concussionSmasherModel(ItemModelGenerators itemModels, Item item) {
        ItemModel.Unbaked itemmodel$unbaked = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked itemmodel$unbaked1 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_pulling_0", ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked itemmodel$unbaked2 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_pulling_1", ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked itemmodel$unbaked3 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_pulling_2", ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked itemmodel$unbaked4 = ItemModelUtils.plainModel(itemModels.createFlatItemModel(item, "_pulling_3", ModelTemplates.FLAT_ITEM));
        itemModels.itemModelOutput.accept(item, ItemModelUtils.rangeSelect(new UseCycle(10.0F), 0.1F, itemmodel$unbaked, ItemModelUtils.override(itemmodel$unbaked1, 0.2F), ItemModelUtils.override(itemmodel$unbaked2, 0.4F), ItemModelUtils.override(itemmodel$unbaked3, 0.6F), ItemModelUtils.override(itemmodel$unbaked4, 0.8F)));

    }

    public void blockModel(BlockModelGenerators blockModels, Block block)
    {
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
