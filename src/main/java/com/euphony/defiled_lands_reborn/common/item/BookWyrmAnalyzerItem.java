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
import net.minecraft.world.item.component.TooltipDisplay;
import org.apache.logging.log4j.core.appender.rolling.action.IfAll;

import java.util.List;
import java.util.function.Consumer;

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
            player.displayClientMessage(Component.translatable(base + "health", target.getHealth(), target.getMaxHealth()), false);
            player.displayClientMessage(Component.translatable(base + "digest_time", target.getDigestingTime()), false);
            player.displayClientMessage(Component.translatable(base + "max_level", target.getEnchLevel()), false);
            player.displayClientMessage(Component.translatable(base + "digested", target.getDigested()), false);
            player.displayClientMessage(Component.translatable(base + "digesting", target.getToDigest()), false);

            if (target.getToDigest() > 0)
                player.displayClientMessage(Component.translatable(base + "digesting", target.getToDigest()), false);
            if (target.isBaby()) {
                int minutes = (int)Math.ceil((-target.getAge()) / 1200.0D);
                player.displayClientMessage(Component.translatable(base + "maturing", minutes), false);
            } else if (target.getAge() > 0) {
                int minutes = (int)Math.ceil(target.getAge() / 1200.0D);
                player.displayClientMessage(Component.translatable(base + "reproduce", minutes), false);
            } else {
                player.displayClientMessage(Component.translatable(base + "ready"), false);
            }
            if (target instanceof GoldenBookWyrm)
                player.displayClientMessage(Component.translatable(base + "golden"), false);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        ItemUtils.addTooltip(tooltipAdder, "item.defiled_lands_reborn.book_wyrm_analyzer.tooltip");
    }
}
