package com.euphony.defiled_lands_reborn.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.List;

public class ItemUtils {
    public static void addTooltip(List<Component> components, String key) {
        components.add(Component.translatable(key).withStyle(ChatFormatting.GRAY));
    }

    public static int getEnchantmentLevel(RegistryAccess registryAccess, ItemStack stack, ResourceKey<Enchantment> enchantment) {
        return stack.getEnchantmentLevel(registryAccess.holderOrThrow(enchantment));
    }
}
