package com.euphony.defiled_lands_reborn.common.item;

import com.euphony.defiled_lands_reborn.common.entity.projectile.RavagerProjectile;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.common.tag.DLItemTags;
import com.euphony.defiled_lands_reborn.utils.ItemUtils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class RavagerItem extends GunItem {
    public RavagerItem(Properties properties) {
        super(properties
                .durability(2107)
                .repairable(DLItems.RAVAGING_INGOT.get())
        );
    }

    protected boolean isAmmo(ItemStack stack) {
        return stack.is(DLItemTags.RAVAGER_PELLET);
    };

    @Override
    @NotNull
    public InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack gunStack = player.getItemInHand(hand);
        RegistryAccess registryAccess = level.registryAccess();
        boolean flag = player.isCreative() || ItemUtils.getEnchantmentLevel(registryAccess, gunStack, Enchantments.INFINITY) > 0;;
        ItemStack ammo = findAmmo(player, DLItems.UMBRIUM_PELLET.toStack());

        if (ammo.isEmpty() && !flag) {
            return InteractionResult.FAIL;
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL,
                0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        if (!level.isClientSide) {
            Vec3 lookAngle = player.getLookAngle();
            RavagerProjectile projectile = new RavagerProjectile(
                    level, player,
                    new Vec3(lookAngle.x, lookAngle.y, lookAngle.z)
            );

            projectile.setPos(
                    player.getX(),
                    player.getY() + player.getEyeHeight(),
                    player.getZ()
            );
            float i = getSharpshooterBonus(registryAccess, gunStack);
            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F * i, 1.0F / i);

            projectile.setItem(ammo);
            int damage = ammo.is(DLItems.RAVAGING_PELLET) ? 20 : 10;
            projectile.setDamage(damage);

            level.addFreshEntity(projectile);
            gunStack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(gunStack));
        }

        consumeAmmo(gunStack, ammo, player, level.getRandom());

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        ItemUtils.addTooltip(tooltipAdder, "item.defiled_lands_reborn.the_ravager.tooltip");
    }
}
