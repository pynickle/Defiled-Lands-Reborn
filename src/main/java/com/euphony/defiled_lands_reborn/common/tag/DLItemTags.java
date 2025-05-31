package com.euphony.defiled_lands_reborn.common.tag;

import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class DLItemTags {
    public static final TagKey<Item> FOOD_POISONING = createCTag("foods/food_poisoning");
    public static final TagKey<Item> FOODS = createCTag("foods");

    public static final TagKey<Item> RAVAGER_PELLET = create("ravager_pellet");
    public static final TagKey<Item> GUN_ENCHANTABLE = create("gun_enchantable");
    public static final TagKey<Item> BLASTER_ENCHANTABLE = create("blaster_enchantable");
    public static final TagKey<Item> BLASTER_AMMO = create("blaster_ammo");
    public static final TagKey<Item> DESTRUCTIVE_AVAILABLE = create("destructive_available");

    public static final TagKey<Item> REPAIRS_UMBRIUM_ARMOR = create("repairs_umbrium_armor");
    public static final TagKey<Item> REPAIRS_GOLDEN_BOOK_WYRM_SCALE_ARMOR = create("repairs_golden_book_wyrm_scale_armor");
    public static final TagKey<Item> REPAIRS_BOOK_WYRM_SCALE_ARMOR = create("repairs_book_wyrm_scale_armor");

    public static final TagKey<Item> UMBRIUM_TOOL_MATERIALS = create("umbrium_tool_materials");
    public static final TagKey<Item> SCARLITE_TOOL_MATERIALS = create("scarlite_tool_materials");
    public static final TagKey<Item> SCARLITE_RAZOR_TOOL_MATERIALS = create("scarlite_razor_tool_materials");
    public static final TagKey<Item> RAVAGING_TOOL_MATERIALS = create("ravaging_tool_materials");

    public static final TagKey<Item> RAVAGING_TOOLS = create("ravaging_tools");

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, Utils.prefix(name));
    }

    public static TagKey<Item> createVanillaTag(String tagName) {
        return ItemTags.create(ResourceLocation.withDefaultNamespace(tagName));
    }

    public static TagKey<Item> createCTag(String tagName) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", tagName));
    }
}
