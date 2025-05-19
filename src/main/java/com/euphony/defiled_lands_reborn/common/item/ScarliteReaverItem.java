package com.euphony.defiled_lands_reborn.common.item;

import com.euphony.defiled_lands_reborn.common.item.tool.DLTiers;
import com.euphony.defiled_lands_reborn.utils.ItemUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ScarliteReaverItem extends SwordItem {
    public ScarliteReaverItem(Properties properties) {
        super(DLTiers.SCARLITE, properties.attributes(
                SwordItem.createAttributes(DLTiers.SCARLITE, 3.0f, -2.0F)
        ));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 3, 18));

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        ItemUtils.addTooltip(tooltipComponents, "item.defiled_lands_reborn.scarlite_reaver.tooltip");
    }
}
