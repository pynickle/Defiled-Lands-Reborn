package com.euphony.defiled_lands_reborn.common.item;

import com.euphony.defiled_lands_reborn.common.entity.BookWyrm;
import com.euphony.defiled_lands_reborn.common.entity.GoldenBookWyrm;
import com.euphony.defiled_lands_reborn.utils.ItemUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class BookWyrmAnalyzerItem extends Item {
    public BookWyrmAnalyzerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand usedHand) {
        if(player.level().isClientSide) return InteractionResult.CONSUME;

        if (entity instanceof BookWyrm) {
            BookWyrm target = (BookWyrm) entity;

            String base = "ui.defiled_lands_reborn.book_wyrm_analyze.";
            player.sendSystemMessage(Component.translatable(base + "health", target.getHealth()));
            player.sendSystemMessage(Component.translatable(base + "digest_time", target.getDigestingTime()));
            player.sendSystemMessage(Component.translatable(base + "max_level", target.getEnchLevel()));
            player.sendSystemMessage(Component.translatable(base + "digested", target.getDigested()));
            player.sendSystemMessage(Component.translatable(base + "digesting", target.getToDigest()));

            if (target.getToDigest() > 0)
                player.sendSystemMessage(Component.translatable(base + "digesting", target.getToDigest()));
            if (target.isBaby()) {
                int minutes = (int)Math.ceil((-target.getAge()) / 1200.0D);
                player.sendSystemMessage(Component.translatable(base + "maturing", minutes));
            } else if (target.getAge() > 0) {
                int minutes = (int)Math.ceil(target.getAge() / 1200.0D);
                player.sendSystemMessage(Component.translatable(base + "reproduce", minutes));
            } else {
                player.sendSystemMessage(Component.translatable(base + "ready"));
            }
            if (target instanceof GoldenBookWyrm)
                player.sendSystemMessage(Component.translatable(base + "golden"));

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        ItemUtils.addTooltip(tooltipComponents, "item.defiled_lands_reborn.book_wyrm_analyzer.tooltip");
    }
}
