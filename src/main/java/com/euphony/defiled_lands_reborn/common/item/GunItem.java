package com.euphony.defiled_lands_reborn.common.item;

import com.euphony.defiled_lands_reborn.common.init.DLEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;

public abstract class GunItem extends Item {
    public GunItem(Properties properties) {
        super(properties.stacksTo(1).enchantable(1));
    }

    protected float getSharpshooterBonus(RegistryAccess registryAccess, ItemStack gun) {
        Holder<Enchantment> sharpShooter = registryAccess.holderOrThrow(DLEnchantments.SHARP_SHOOTER);
        int sharpShooterLevel = gun.getEnchantmentLevel(sharpShooter);
        return 1.0F + 0.5F * sharpShooterLevel;
    }

    protected void consumeAmmo(ItemStack gun, ItemStack ammo, Player player, RandomSource rand) {
        Level level = player.level();
        Holder<Enchantment> economical = level.registryAccess().holderOrThrow(DLEnchantments.ECONOMICAL);
        int economicalLevel = gun.getEnchantmentLevel(economical);

        if (economicalLevel > 0 && rand.nextInt(economicalLevel + 2) > 1) {
            return;
        }

        ammo.consume(1, player);
    }

    protected abstract boolean isAmmo(ItemStack stack);

    protected ItemStack findAmmo(Player player, ItemStack defaultAmmo) {
        if (isAmmo(player.getItemInHand(InteractionHand.OFF_HAND))) {
            return player.getItemInHand(InteractionHand.OFF_HAND);
        }
        else if (isAmmo(player.getItemInHand(InteractionHand.MAIN_HAND))) {
            return player.getItemInHand(InteractionHand.MAIN_HAND);
        }

        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (isAmmo(stack)) {
                return stack;
            }
        }
        if(player.isCreative()) {
            return defaultAmmo;
        }
        return ItemStack.EMPTY;
    }
}
