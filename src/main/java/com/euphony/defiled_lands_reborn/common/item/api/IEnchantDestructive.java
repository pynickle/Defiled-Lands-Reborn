package com.euphony.defiled_lands_reborn.common.item.api;

import com.euphony.defiled_lands_reborn.common.init.DLEnchantments;
import com.euphony.defiled_lands_reborn.utils.ItemUtils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;

public interface IEnchantDestructive {
    default float getDestructiveBonus(RegistryAccess registryAccess, ItemStack stack) {
        int level = ItemUtils.getEnchantmentLevel(registryAccess, stack, DLEnchantments.DESTRUCTIVE);
        if (level > 0) return 1.0F + (level + 1) * 0.25F;
        else return 1.0F;
    }
}
