package com.euphony.defiled_lands_reborn.common.item;

import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.utils.ItemUtils;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Consumer;

public abstract class TearsItem extends Item {
    protected static final int USE_DURATION = 72000;
    protected int warmup, tickRate;

    public TearsItem(Properties properties, int warmup, int tickRate) {
        super(properties.stacksTo(1).durability(2107)
                .repairable(DLItems.REMORSEFUL_GEM.asItem()).enchantable(1));
        this.warmup = USE_DURATION - warmup;
        this.tickRate = tickRate;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return USE_DURATION;
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        if (remainingUseDuration <= warmup && (warmup - remainingUseDuration) % tickRate == 0)
            fire(stack, livingEntity, warmup - remainingUseDuration);
    }

    protected abstract void fire(ItemStack stack, LivingEntity player, int time);

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand usedHand) {
        player.startUsingItem(usedHand);
        return InteractionResult.SUCCESS;
    }


    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.BOW;
    }

    protected abstract String getItemName();

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        ItemUtils.addTooltip(tooltipAdder, "item.defiled_lands_reborn." + getItemName() + ".tooltip");
    }

    public static class Flame extends TearsItem {
        public Flame(Properties properties) {
            super(properties, 20, 5);
        }

        @Override
        protected void fire(ItemStack stack, LivingEntity player, int time) {
            Level level = player.level();
            if(player instanceof Player) {
                level.playSound(null,
                        player.getX(),
                        player.getEyeY(),
                        player.getZ(),
                        SoundEvents.BLAZE_SHOOT,
                        SoundSource.NEUTRAL,
                        0.5F,
                        0.4F / (level.random.nextFloat() * 0.4F + 0.8F)
                );
                if (!level.isClientSide) {
                    Vec3 lookAngle = player.getLookAngle();
                    SmallFireball projectile = new SmallFireball(
                            level,
                            player,
                            new Vec3(lookAngle.x * 0.2D,
                            lookAngle.y * 0.2D,
                            lookAngle.z * 0.2D)
                    );
                    projectile.moveOrInterpolateTo(
                            new Vec3(player.getX(),
                            player.getEyeY(),
                            player.getZ()),
                            player.getYRot(),
                            player.getXRot()
                    );

                    level.addFreshEntity(projectile);

                    if (time % 25 == 0) {
                        stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
                    }
                }
            }
        }

        @Override
        protected String getItemName() {
            return "tears_flame";
        }
    }

    public static class Shulker extends TearsItem {
        public Shulker(Properties properties) {
            super(properties, 20, 5);
        }

        @Override
        protected void fire(ItemStack stack, LivingEntity player, int time) {
            Level level = player.level();

            if (!level.isClientSide) {
                AABB scanArea = player.getBoundingBox().inflate(16, 8, 16);
                List<Monster> targets = level.getEntitiesOfClass(Monster.class, scanArea);

                if (!targets.isEmpty()) {
                    level.playSound(null,
                            player.getX(),
                            player.getEyeY(),
                            player.getZ(),
                            SoundEvents.SHULKER_SHOOT,
                            SoundSource.NEUTRAL,
                            2.0F,
                            (level.random.nextFloat() - level.random.nextFloat()) * 0.2F + 1.0F
                    );

                    stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
                }

                targets.forEach(target -> {
                    Direction.Axis axis = Direction.Axis.getRandom(level.random);
                    ShulkerBullet projectile = new ShulkerBullet(level, player, target, axis);

                    projectile.moveOrInterpolateTo(new Vec3(player.getX(), player.getEyeY(), player.getZ()),
                            player.getYRot(), player.getXRot()
                    );

                    level.addFreshEntity(projectile);
                });
            }
        }

        @Override
        protected String getItemName() {
            return "tears_shulker";
        }
    }
}
