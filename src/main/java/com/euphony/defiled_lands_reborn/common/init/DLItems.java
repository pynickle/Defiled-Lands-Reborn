package com.euphony.defiled_lands_reborn.common.init;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.common.item.*;
import com.euphony.defiled_lands_reborn.common.item.armor.DLArmorMaterials;
import com.euphony.defiled_lands_reborn.common.item.food.DLConsumables;
import com.euphony.defiled_lands_reborn.common.item.food.DLFoods;
import com.euphony.defiled_lands_reborn.common.item.tool.DLToolMaterials;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

public class DLItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DefiledLandsReborn.MOD_ID);

    public static final Collection<DeferredItem<Item>> SPAWN_EGGS = new ArrayList<>();

    public static final DeferredItem<HephaestiteShardItem> HEPHAESTITE_SHARD = register("hephaestite_shard",
            HephaestiteShardItem::new);
    public static final DeferredItem<Item> UMBRIUM_INGOT = register("umbrium_ingot",
            Item::new);
    public static final DeferredItem<Item> UMBRIUM_NUGGET = register("umbrium_nugget",
            Item::new);
    public static final DeferredItem<Item> SCARLITE = register("scarlite",
            Item::new);

    public static final DeferredItem<Item> BLASTEM_SEED = register("blastem_seed",
            Item::new);
    public static final DeferredItem<BlastemFruitItem> BLASTEM_FRUIT = register("blastem_fruit",
            BlastemFruitItem::new);
    public static final DeferredItem<BlazingBlastemFruitItem> BLAZING_BLASTEM_FRUIT = register("blazing_blastem_fruit",
            BlazingBlastemFruitItem::new);
    public static final DeferredItem<DefilementPowderItem> DEFILEMENT_POWDER = register("defilement_powder",
            DefilementPowderItem::new);

    public static final DeferredItem<Item> SCUTTLER_EYE = register("scuttler_eye",
            p -> new Item(p.food(DLFoods.SCUTTLER_EYE, DLConsumables.SCUTTLER_EYE)));
    public static final DeferredItem<Item> BLACK_HEART = register("black_heart",
            p -> new Item(p.food(DLFoods.BLACK_HEART, DLConsumables.BLACK_HEART)));
    public static final DeferredItem<Item> FOUL_SLIME = register("foul_slime",
            Item::new);
    public static final DeferredItem<Item> FOUL_CANDY = register("foul_candy",
            p -> new Item(p.food(DLFoods.FOUL_CANDY, DLConsumables.FOUL_CANDY)));
    public static final DeferredItem<Item> RAW_BOOK_WYRM = register("raw_book_wyrm",
            p -> new Item(p.food(DLFoods.RAW_BOOK_WYRM, DLConsumables.RAW_BOOK_WYRM)));
    public static final DeferredItem<Item> COOKED_BOOK_WYRM = register("cooked_book_wyrm",
            p -> new Item(p.food(DLFoods.COOKED_BOOK_WYRM)));
    public static final DeferredItem<Item> BOOK_WYRM_SCALE = register("book_wyrm_scale",
            Item::new);
    public static final DeferredItem<Item> GOLDEN_BOOK_WYRM_SCALE = register("golden_book_wyrm_scale",
            Item::new);

    public static final DeferredItem<AxeItem> UMBRIUM_AXE = register("umbrium_axe",
            p -> new AxeItem(DLToolMaterials.UMBRIUM, 6.0f, -3.1F, p));
    public static final DeferredItem<HoeItem> UMBRIUM_HOE = register("umbrium_hoe",
            p -> new HoeItem(DLToolMaterials.UMBRIUM, -2.0f, -1.0F, p));
    public static final DeferredItem<Item> UMBRIUM_PICKAXE = register("umbrium_pickaxe",
            p -> new Item(p.pickaxe(DLToolMaterials.UMBRIUM, 1.0f, -2.8F)));
    public static final DeferredItem<ShovelItem> UMBRIUM_SHOVEL = register("umbrium_shovel",
            p -> new ShovelItem(DLToolMaterials.UMBRIUM, 1.5f, -3.0F, p));
    public static final DeferredItem<Item> UMBRIUM_SWORD = register("umbrium_sword",
            p -> new Item(p.sword(DLToolMaterials.UMBRIUM, 3.0f, -2.4F)));

    public static final DeferredItem<ScarliteReaverItem> SCARLITE_REAVER = register("scarlite_reaver",
            ScarliteReaverItem::new);
    public static final DeferredItem<ScarliteRazorItem> SCARLITE_RAZOR = register("scarlite_razor",
            ScarliteRazorItem::new);

    public static final DeferredItem<Item> UMBRIUM_HELMET = register("umbrium_helmet",
            p -> new Item(p.humanoidArmor(DLArmorMaterials.UMBRIUM, ArmorType.HELMET)));
    public static final DeferredItem<Item> UMBRIUM_CHESTPLATE = register("umbrium_chestplate",
            p -> new Item(p.humanoidArmor(DLArmorMaterials.UMBRIUM, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> UMBRIUM_LEGGINGS = register("umbrium_leggings",
            p -> new Item(p.humanoidArmor(DLArmorMaterials.UMBRIUM, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> UMBRIUM_BOOTS = register("umbrium_boots",
            p -> new Item(p.humanoidArmor(DLArmorMaterials.UMBRIUM, ArmorType.BOOTS)));

    public static final DeferredItem<Item> BOOK_WYRM_SCALE_HELMET = register("book_wyrm_scale_helmet",
            p -> new Item(p.humanoidArmor(DLArmorMaterials.BOOK_WYRM_SCALE, ArmorType.HELMET)));
    public static final DeferredItem<Item> BOOK_WYRM_SCALE_CHESTPLATE = register("book_wyrm_scale_chestplate",
            p -> new Item(p.humanoidArmor(DLArmorMaterials.BOOK_WYRM_SCALE, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> BOOK_WYRM_SCALE_LEGGINGS = register("book_wyrm_scale_leggings",
            p -> new Item(p.humanoidArmor(DLArmorMaterials.BOOK_WYRM_SCALE, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> BOOK_WYRM_SCALE_BOOTS = register("book_wyrm_scale_boots",
            p -> new Item(p.humanoidArmor(DLArmorMaterials.BOOK_WYRM_SCALE, ArmorType.BOOTS)));

    public static final DeferredItem<Item> GOLDEN_BOOK_WYRM_SCALE_HELMET = register("golden_book_wyrm_scale_helmet",
            p -> new Item(p.humanoidArmor(DLArmorMaterials.GOLDEN_BOOK_WYRM_SCALE, ArmorType.HELMET)));
    public static final DeferredItem<Item> GOLDEN_BOOK_WYRM_SCALE_CHESTPLATE = register("golden_book_wyrm_scale_chestplate",
            p -> new Item(p.humanoidArmor(DLArmorMaterials.GOLDEN_BOOK_WYRM_SCALE, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> GOLDEN_BOOK_WYRM_SCALE_LEGGINGS = register("golden_book_wyrm_scale_leggings",
            p -> new Item(p.humanoidArmor(DLArmorMaterials.GOLDEN_BOOK_WYRM_SCALE, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> GOLDEN_BOOK_WYRM_SCALE_BOOTS = register("golden_book_wyrm_scale_boots",
            p -> new Item(p.humanoidArmor(DLArmorMaterials.GOLDEN_BOOK_WYRM_SCALE, ArmorType.BOOTS)));

    public static final DeferredItem<UmbraBlasterItem> UMBRA_BLASTER = register("umbra_blaster",
            UmbraBlasterItem::new);
    public static final DeferredItem<ConcussionSmasherItem> CONCUSSION_SMASHER = register("concussion_smasher",
            ConcussionSmasherItem::new);

    public static final DeferredItem<BookWyrmAnalyzerItem> BOOK_WYRM_ANALYZER = register("book_wyrm_analyzer",
            BookWyrmAnalyzerItem::new);

    public static final DeferredItem<Item> CALLING_STONE = register("calling_stone",
            Item::new);
    public static final DeferredItem<Item> RAVAGING_ESSENCE = register("ravaging_essence",
            p -> new Item(p.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final DeferredItem<Item> RAVAGING_INGOT = register("ravaging_ingot",
            p -> new Item(p.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    public static final DeferredItem<AxeItem> RAVAGING_AXE = register("ravaging_axe",
            p -> new AxeItem(DLToolMaterials.RAVAGING, 5.0f, -3.0F, p.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final DeferredItem<Item> RAVAGING_PICKAXE = register("ravaging_pickaxe",
            p -> new Item(p.pickaxe(DLToolMaterials.RAVAGING, 1.0f, -2.8F).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final DeferredItem<ShovelItem> RAVAGING_SHOVEL = register("ravaging_shovel",
            p -> new ShovelItem(DLToolMaterials.RAVAGING, 1.5f, -3.0F, p.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    public static final DeferredItem<RavagerItem> THE_RAVAGER = register("the_ravager",
            p -> new RavagerItem(p.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true).stacksTo(1)));
    public static final DeferredItem<Item> UMBRIUM_PELLET = register("umbrium_pellet",
            Item::new);
    public static final DeferredItem<Item> SPIKED_PELLET = register("spiked_pellet",
            Item::new);
    public static final DeferredItem<Item> RAVAGING_PELLET = register("ravaging_pellet",
            p -> new Item(p.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    public static final DeferredItem<Item> IDOL_SORROW = register("idol_sorrow",
            Item::new);
    public static final DeferredItem<Item> REMORSEFUL_ESSENCE = register("remorseful_essence",
            p -> new Item(p.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final DeferredItem<Item> REMORSEFUL_GEM = register("remorseful_gem",
            p -> new Item(p.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final DeferredItem<TearsItem> TEARS_FLAME = register("tears_flame",
            p -> new TearsItem.Flame(p.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final DeferredItem<TearsItem> TEARS_SHULKER = register("tears_shulker",
            p -> new TearsItem.Shulker(p.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    public static final DeferredItem<Item> SCARLITE_RING = register("scarlite_ring",
            p -> new Item(p.stacksTo(1)));
    public static final DeferredItem<Item> PHYTOPROSTASIA_AMULET = register("phytoprostasia_amulet",
            Item::new);

    static <T extends Item> DeferredItem<T> register(String name, Function<Item.Properties, T> item) {
        return ITEMS.registerItem(name, item);
    }


    static DeferredItem<BlockItem> register(String name, Supplier<? extends Block> block) {
        return ITEMS.registerSimpleBlockItem(name, block, new Item.Properties());
    }
}
