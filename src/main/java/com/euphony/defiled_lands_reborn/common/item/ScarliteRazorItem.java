package com.euphony.defiled_lands_reborn.common.item;

import com.euphony.defiled_lands_reborn.common.item.tool.DLTiers;
import com.euphony.defiled_lands_reborn.utils.ItemUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;

import java.util.List;

public class ScarliteRazorItem extends SwordItem {
    public ScarliteRazorItem(Properties properties) {
        super(DLTiers.SCARLITE_RAZOR, properties.attributes(
                SwordItem.createAttributes(DLTiers.SCARLITE_RAZOR, 0.0f, -1.0F)
        ));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if(livingEntity instanceof Player player) {
            DamageSource damageSource = player.damageSources().playerAttack(player);
            if (player.hurt(damageSource, 3f)) {
                player.getFoodData().eat(4, 0.4f);
                player.level().playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.PLAYER_BURP,
                        SoundSource.PLAYERS,
                        0.5f,
                        player.level().random.nextFloat() * 0.1f + 0.9f
                );
                player.awardStat(Stats.ITEM_USED.get(this));
                if(stack.getEquipmentSlot() != null) {
                    stack.hurtAndBreak(1, player, stack.getEquipmentSlot());
                }
            }
        }
        return stack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if(player.canEat(false)) {
            return InteractionResultHolder.success(stack);
        } else {
            return InteractionResultHolder.fail(stack);
        }
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 12;
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
        return super.getDefaultAttributeModifiers(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        ItemUtils.addTooltip(tooltipComponents, "item.defiled_lands_reborn.scarlite_razor.tooltip");
    }
}
