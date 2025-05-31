package com.euphony.defiled_lands_reborn.common.item.tool;

import net.minecraft.Util;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

public interface DLEquipmentAssets {
    ResourceKey<EquipmentAsset> UMBRIUM = createId("umbrium");
    ResourceKey<EquipmentAsset> BOOK_WYRM_SCALE = createId("book_wyrm_scale");
    ResourceKey<EquipmentAsset> GOLDEN_BOOK_WYRM_SCALE = createId("golden_book_wyrm_scale");

    static ResourceKey<EquipmentAsset> createId(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.withDefaultNamespace(name));
    }
}
