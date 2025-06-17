package com.euphony.defiled_lands_reborn.common.entity.projectile;

import com.euphony.defiled_lands_reborn.common.init.DLEntities;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class BlastemFruitProjectile extends ThrowableItemProjectile implements ItemSupplier {
    public BlastemFruitProjectile(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected Item getDefaultItem() {
        return DLItems.BLASTEM_FRUIT.get();
    }

    @Override
    public ItemStack getItem() {
        return DLItems.BLASTEM_FRUIT.toStack();
    }

    protected boolean destructive;
    protected float damage, explosion;

    public BlastemFruitProjectile(Level level, LivingEntity thrower) {
        super(DLEntities.BLASTEM_FRUIT_PROJECTILE.get(), thrower, level, DLItems.BLASTEM_FRUIT.toStack());
        damage = 7.0F;
        explosion = 1.0F;
        destructive = true;
    }

    public BlastemFruitProjectile(EntityType<? extends BlastemFruitProjectile> entityType, Level level, LivingEntity thrower) {
        super(entityType, thrower, level, DLItems.BLASTEM_FRUIT.toStack());
        damage = 7.0F;
        explosion = 1.0F;
        destructive = true;
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        result.getEntity().hurt(damageSources().thrown(this, this.getOwner()), damage);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        level().explode(this, getX(), getY(), getZ(), explosion, false,
                this.destructive ? Level.ExplosionInteraction.BLOCK : Level.ExplosionInteraction.TRIGGER);
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setDestructive(boolean destructive) {
        this.destructive = destructive;
    }

    public float getExplosion() {
        return explosion;
    }

    public void setExplosion(float explosion) {
        this.explosion = explosion;
    }

    @Override
    public void addAdditionalSaveData(ValueOutput compound) {
        super.addAdditionalSaveData(compound);
        compound.putFloat("Damage", damage);
        compound.putFloat("Explosion", explosion);
        compound.putBoolean("Destructive", destructive);
    }

    @Override
    public void readAdditionalSaveData(ValueInput compound) {
        super.readAdditionalSaveData(compound);
        this.damage = compound.getFloatOr("Damage", 7.0f);
        this.explosion = compound.getFloatOr("Explosion", 1.0f);
        this.destructive = compound.getBooleanOr("Destructive", true);
    }
}
