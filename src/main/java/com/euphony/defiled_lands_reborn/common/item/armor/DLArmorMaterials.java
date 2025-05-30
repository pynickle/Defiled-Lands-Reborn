package com.euphony.defiled_lands_reborn.common.item.armor;

import com.euphony.defiled_lands_reborn.common.item.tool.DLEquipmentAssets;
import com.euphony.defiled_lands_reborn.common.tag.DLItemTags;
import com.google.common.collect.Maps;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;

public interface DLArmorMaterials {
    ArmorMaterial UMBRIUM = new ArmorMaterial(18, makeDefense(1, 4, 5, 2, 4),
            9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, DLItemTags.REPAIRS_UMBRIUM_ARMOR, DLEquipmentAssets.UMBRIUM);

    ArmorMaterial BOOK_WYRM_SCALE = new ArmorMaterial(18, makeDefense(1, 3, 4, 2, 3),
            9, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, DLItemTags.REPAIRS_BOOK_WYRM_SCALE_ARMOR, DLEquipmentAssets.BOOK_WYRM_SCALE);
    ArmorMaterial GOLDEN_BOOK_WYRM_SCALE = new ArmorMaterial(18, makeDefense(1, 3, 4, 2, 3),
            9, SoundEvents.ARMOR_EQUIP_LEATHER, 1.0F, 0.0F, DLItemTags.REPAIRS_GOLDEN_BOOK_WYRM_SCALE_ARMOR, DLEquipmentAssets.GOLDEN_BOOK_WYRM_SCALE);

    private static Map<ArmorType, Integer> makeDefense(int boots, int leggings, int chestplate, int helmet, int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, leggings, ArmorType.CHESTPLATE, chestplate, ArmorType.HELMET, helmet, ArmorType.BODY, body));
    }
}
