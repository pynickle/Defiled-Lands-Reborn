package com.euphony.defiled_lands_reborn.common.entity.projectile;

import com.euphony.defiled_lands_reborn.common.init.DLEntities;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class BlazingBlastemFruitProjectile extends BlastemFruitProjectile {
    public BlazingBlastemFruitProjectile(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public ItemStack getItem() {
        return DLItems.BLAZING_BLASTEM_FRUIT.toStack();
    }

    public BlazingBlastemFruitProjectile(Level level, LivingEntity thrower) {
        super(DLEntities.BLAZING_BLASTEM_FRUIT_PROJECTILE.get(), level, thrower);
        damage = 10.0F;
        explosion = 1.5F;
        destructive = true;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        level().explode(this, getX(), getY(), getZ(), explosion, true,
                this.destructive ? Level.ExplosionInteraction.BLOCK : Level.ExplosionInteraction.TRIGGER);
    }
}
