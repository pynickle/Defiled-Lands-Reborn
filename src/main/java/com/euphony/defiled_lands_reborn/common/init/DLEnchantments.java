package com.euphony.defiled_lands_reborn.common.init;

import com.euphony.defiled_lands_reborn.common.tag.DLItemTags;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

public class DLEnchantments {
    public static final ResourceKey<Enchantment> SHARP_SHOOTER = registerKey("sharp_shooter");
    public static final ResourceKey<Enchantment> ECONOMICAL = registerKey("economical");
    public static final ResourceKey<Enchantment> DESTRUCTIVE = registerKey("destructive");
    public static final ResourceKey<Enchantment> SAFE_GUARD = registerKey("safe_guard");
    public static final ResourceKey<Enchantment> BLAZING = registerKey("blazing");

    private static ResourceKey<Enchantment> registerKey(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, Utils.prefix(name));
    }

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> items = context.lookup(Registries.ITEM);
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);

        HolderSet<Item> itemHolderSet = items.getOrThrow(DLItemTags.GUN_ENCHANTABLE);
        HolderSet<Item> destructiveHolderSet = items.getOrThrow(DLItemTags.DESTRUCTIVE_AVAILABLE);
        HolderSet<Item> umbraBlasterSet = items.getOrThrow(DLItemTags.BLASTER_ENCHANTABLE);
        register(context, SHARP_SHOOTER, new Enchantment.Builder(Enchantment.definition(
                itemHolderSet, itemHolderSet,
                10, 3, Enchantment.dynamicCost(5, 9),
                Enchantment.dynamicCost(25, 8),
                2
        )));
        register(context, ECONOMICAL, new Enchantment.Builder(Enchantment.definition(
                itemHolderSet, itemHolderSet,
                5, 4, Enchantment.dynamicCost(15, 9),
                Enchantment.dynamicCost(65, 9),
                4
        )));
        register(context, DESTRUCTIVE, new Enchantment.Builder(Enchantment.definition(
                destructiveHolderSet, destructiveHolderSet,
                10, 5, Enchantment.dynamicCost(10, 10),
                Enchantment.dynamicCost(25, 8),
                4

        )));
        register(context, SAFE_GUARD, new Enchantment.Builder(Enchantment.definition(
                umbraBlasterSet, umbraBlasterSet,
                10, 1, Enchantment.dynamicCost(5, 9),
                Enchantment.dynamicCost(20, 9),
                2
        )));
        register(context, BLAZING, new Enchantment.Builder(Enchantment.definition(
                umbraBlasterSet, umbraBlasterSet,
                1, 1, Enchantment.constantCost(20),
                Enchantment.constantCost(50),
                8
        )));
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }
}
