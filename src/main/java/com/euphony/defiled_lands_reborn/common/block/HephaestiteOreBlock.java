package com.euphony.defiled_lands_reborn.common.block;

import com.euphony.defiled_lands_reborn.utils.CorruptionUtils;
import com.euphony.defiled_lands_reborn.utils.ItemUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class HephaestiteOreBlock extends DropExperienceBlock {
    public HephaestiteOreBlock(IntProvider xpRange, Properties properties) {
        super(xpRange, properties.randomTicks());
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.fireImmune() && entity instanceof LivingEntity livingEntity && EnchantmentHelper.getEnchantmentLevel(
                level.registryAccess().holderOrThrow(Enchantments.FROST_WALKER), livingEntity) <= 0) {
            entity.hurt(level.damageSources().hotFloor(), 2.0F);
            entity.setRemainingFireTicks(60);
        }

        super.stepOn(level, pos, state, entity);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        CorruptionUtils.spread(level, pos, state, random);
    }
}
