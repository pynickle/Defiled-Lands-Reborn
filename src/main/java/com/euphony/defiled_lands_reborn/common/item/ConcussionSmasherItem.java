package com.euphony.defiled_lands_reborn.common.item;

import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.common.item.api.IEnchantDestructive;
import com.euphony.defiled_lands_reborn.utils.ItemUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ConcussionSmasherItem extends Item implements IEnchantDestructive {
    public ConcussionSmasherItem(Properties properties) {
        super(properties.stacksTo(1).durability(178));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        ItemUtils.addTooltip(tooltipComponents, "item.defiled_lands_reborn.concussion_smasher.tooltip");
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (entity instanceof Player player) {
            int useDuration = getUseDuration(stack, entity) - timeLeft;
            float strength = getExplosionStrength(useDuration);

            if (strength >= 0.1) {
                if (!level.isClientSide) {
                    strength *= 2.0F * getDestructiveBonus(level.registryAccess(), stack);
                    level.explode(entity,
                            player.damageSources().playerAttack(player),
                            null,
                            entity.getX(), entity.getEyeY() - 0.1, entity.getZ(),
                            strength, false,
                            Level.ExplosionInteraction.TRIGGER);

                    if(stack.getEquipmentSlot() != null) {
                        stack.hurtAndBreak(1, player, stack.getEquipmentSlot());
                    }
                }
                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    public static float getExplosionStrength(int charge) {
        float ratio = (float) charge / 7.0F;
        return Math.min(ratio, 1.0F);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(DLItems.UMBRIUM_INGOT.get());
    }

    @Override
    public int getEnchantmentValue() {
        return 1;
    }

}
