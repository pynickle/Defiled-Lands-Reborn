package com.euphony.defiled_lands_reborn.common.item.armor;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class DLArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> MATERIALS = DeferredRegister.create(BuiltInRegistries.ARMOR_MATERIAL, DefiledLandsReborn.MOD_ID);

    public static final Holder<ArmorMaterial> UMBRIUM = MATERIALS.register("umbrium", () -> register("umbrium", Util.make(new EnumMap<>(ArmorItem.Type.class), (p_323378_) -> {
        p_323378_.put(ArmorItem.Type.BOOTS, 1);
        p_323378_.put(ArmorItem.Type.LEGGINGS, 4);
        p_323378_.put(ArmorItem.Type.CHESTPLATE, 5);
        p_323378_.put(ArmorItem.Type.HELMET, 2);
        p_323378_.put(ArmorItem.Type.BODY, 4);
    }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(DLItems.UMBRIUM_INGOT.get())));

    public static final Holder<ArmorMaterial> BOOK_WYRM_SCALE = MATERIALS.register("book_wyrm_scale", () -> register("book_wyrm_scale", Util.make(new EnumMap<>(ArmorItem.Type.class), (p_323378_) -> {
        p_323378_.put(ArmorItem.Type.BOOTS, 1);
        p_323378_.put(ArmorItem.Type.LEGGINGS, 3);
        p_323378_.put(ArmorItem.Type.CHESTPLATE, 4);
        p_323378_.put(ArmorItem.Type.HELMET, 2);
        p_323378_.put(ArmorItem.Type.BODY, 3);
    }), 9, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(DLItems.BOOK_WYRM_SCALE.get())));

    public static final Holder<ArmorMaterial> GOLDEN_BOOK_WYRM_SCALE = MATERIALS.register("golden_book_wyrm_scale", () -> register("golden_book_wyrm_scale", Util.make(new EnumMap<>(ArmorItem.Type.class), (p_323378_) -> {
        p_323378_.put(ArmorItem.Type.BOOTS, 1);
        p_323378_.put(ArmorItem.Type.LEGGINGS, 3);
        p_323378_.put(ArmorItem.Type.CHESTPLATE, 4);
        p_323378_.put(ArmorItem.Type.HELMET, 2);
        p_323378_.put(ArmorItem.Type.BODY, 3);
    }), 9, SoundEvents.ARMOR_EQUIP_LEATHER, 1.0F, 0.0F, () -> Ingredient.of(DLItems.GOLDEN_BOOK_WYRM_SCALE.get())));

    private static ArmorMaterial register(
            String pName,
            EnumMap<ArmorItem.Type, Integer> pDefense,
            int pEnchantmentValue,
            Holder<SoundEvent> pEquipSound,
            float pToughness,
            float pKnockbackResistance,
            Supplier<Ingredient> pRepairIngredient
    ) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(Utils.prefix(pName)));
        return register(pDefense, pEnchantmentValue, pEquipSound, pToughness, pKnockbackResistance, pRepairIngredient, list);
    }

    private static ArmorMaterial register(
            EnumMap<ArmorItem.Type, Integer> pDefense,
            int pEnchantmentValue,
            Holder<SoundEvent> pEquipSound,
            float pToughness,
            float pKnockbackResistance,
            Supplier<Ingredient> pRepairIngridient,
            List<ArmorMaterial.Layer> pLayers
    ) {
        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
            enummap.put(armoritem$type, pDefense.get(armoritem$type));
        }

        return new ArmorMaterial(enummap, pEnchantmentValue, pEquipSound, pRepairIngridient, pLayers, pToughness, pKnockbackResistance);
    }
}
