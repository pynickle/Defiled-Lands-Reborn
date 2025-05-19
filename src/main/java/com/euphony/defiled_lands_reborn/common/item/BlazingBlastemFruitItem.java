package com.euphony.defiled_lands_reborn.common.item;

import com.euphony.defiled_lands_reborn.common.entity.projectile.BlazingBlastemFruitProjectile;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlazingBlastemFruitItem extends Item {
    public BlazingBlastemFruitItem(Properties properties) {
        super(properties.stacksTo(16));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        level.playSound(null,
                player.getX(), player.getEyeY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL,
                0.5F, 0.4F / (level.random.nextFloat() * 0.4F + 0.8F)
        );
        player.getCooldowns().addCooldown(this, 20);

        if (!level.isClientSide) {
            BlazingBlastemFruitProjectile projectile = new BlazingBlastemFruitProjectile(level, player);
            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            projectile.setRemainingFireTicks(100);
            level.addFreshEntity(projectile);
            stack.consume(1, player);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
