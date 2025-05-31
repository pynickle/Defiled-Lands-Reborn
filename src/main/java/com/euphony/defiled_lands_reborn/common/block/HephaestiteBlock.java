package com.euphony.defiled_lands_reborn.common.block;

import com.euphony.defiled_lands_reborn.utils.ItemUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Consumer;

public class HephaestiteBlock extends CorruptedBlock {
    public HephaestiteBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.fireImmune() && entity instanceof LivingEntity livingEntity && EnchantmentHelper.getEnchantmentLevel(
                level.registryAccess().holderOrThrow(Enchantments.FROST_WALKER), livingEntity) <= 0) {
            entity.hurt(level.damageSources().hotFloor(), 6.0F);
            entity.setRemainingFireTicks(180);
        }

        super.stepOn(level, pos, state, entity);
    }
}
