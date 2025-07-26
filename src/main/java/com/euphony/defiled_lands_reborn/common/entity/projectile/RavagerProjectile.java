package com.euphony.defiled_lands_reborn.common.entity.projectile;

import com.euphony.defiled_lands_reborn.common.init.DLEffects;
import com.euphony.defiled_lands_reborn.common.init.DLEntities;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.common.tag.DLItemTags;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class RavagerProjectile extends AbstractHurtingProjectile implements ItemSupplier {
    private static final EntityDataAccessor<ItemStack> ITEM = SynchedEntityData.defineId(RavagerProjectile.class, EntityDataSerializers.ITEM_STACK);
    private float damage;

    public RavagerProjectile(EntityType<? extends RavagerProjectile> entityType, Level level) {
        super(entityType, level);
        this.damage = 12.0F;
    }

    public RavagerProjectile(Level level, LivingEntity owner, Vec3 movement) {
        super(DLEntities.RAVAGER_PROJECTILE.get(), owner, movement, level);
        this.damage = 12.0F;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ITEM, ItemStack.EMPTY);
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        EntityHitResult entityHit = result;
        Entity target = entityHit.getEntity();
        Entity owner = getOwner();

        DamageSource damageSource = damageSources().thrown(this, owner);
        float baseDamage = getDamage();
        boolean success = true;
        if(!level().isClientSide) {
            success = target.hurtServer((ServerLevel) level(), damageSource, baseDamage);
        }

        if (success) {
            if (target instanceof LivingEntity livingTarget) {
                ItemStack ammoStack = getItem();
                if (ammoStack.is(DLItemTags.RAVAGER_PELLET)) {
                    if(ammoStack.is(DLItems.SPIKED_PELLET)) {
                        int i = 0;
                        if (livingTarget.isAffectedByPotions()) {
                            MobEffectInstance bleedingEffect = livingTarget.getEffect(DLEffects.BLEEDING);
                            if(bleedingEffect != null) {
                                i = bleedingEffect.getAmplifier() + 1;
                            }
                            i = Math.min(i, 255);
                        }
                        livingTarget.addEffect(new MobEffectInstance(DLEffects.BLEEDING, 200, i));
                    } else if(ammoStack.is(DLItems.RAVAGING_PELLET)) {
                        livingTarget.addEffect(new MobEffectInstance(DLEffects.VULNERABILITY, 200));
                    }
                }
            }
        }
    }

    @Override
    public void addAdditionalSaveData(ValueOutput compound) {
        super.addAdditionalSaveData(compound);
        compound.putFloat("Damage", damage);

        ItemStack itemstack = this.getItem();

        if (!itemstack.isEmpty()) {
            compound.store("Item", ItemStack.CODEC, itemstack);
            if(itemstack.isEmpty()) {
                this.discard();
            } else {
                this.setItem(itemstack);
            }
        }
    }

    @Override
    public void readAdditionalSaveData(ValueInput compound) {
        super.readAdditionalSaveData(compound);
        damage = compound.getFloatOr("Damage", 12.0f);
        setItem(compound.read("Item", ItemStack.CODEC).get());
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setItem(ItemStack stack) {
        this.getEntityData().set(ITEM, stack.copyWithCount(1));
    }

    @Override
    public @NotNull ItemStack getItem() {
        return this.getEntityData().get(ITEM);
    }
}
