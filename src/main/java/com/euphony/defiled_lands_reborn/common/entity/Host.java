package com.euphony.defiled_lands_reborn.common.entity;

import com.euphony.defiled_lands_reborn.common.init.DLEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import org.jetbrains.annotations.NotNull;

public class Host extends Zombie {
    public Host(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0F).add(Attributes.MOVEMENT_SPEED, 0.27F).add(Attributes.ATTACK_DAMAGE, 5.0F).add(Attributes.ARMOR, 2.0F).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public boolean isUnderWaterConverting() {
        return false;
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    protected void actuallyHurt(DamageSource damageSource, float damageAmount) {
        if (!this.isInvulnerableTo(damageSource)) {
            this.damageContainers.peek().setReduction(DamageContainer.Reduction.ARMOR, this.damageContainers.peek().getNewDamage() - this.getDamageAfterArmorAbsorb(damageSource, ((DamageContainer)this.damageContainers.peek()).getNewDamage()));
            this.getDamageAfterMagicAbsorb(damageSource, this.damageContainers.peek().getNewDamage());
            float damage = CommonHooks.onLivingDamagePre(this, this.damageContainers.peek());
            this.damageContainers.peek().setReduction(DamageContainer.Reduction.ABSORPTION, Math.min(this.getAbsorptionAmount(), damage));
            float absorbed = Math.min(damage, this.damageContainers.peek().getReduction(DamageContainer.Reduction.ABSORPTION));
            this.setAbsorptionAmount(Math.max(0.0F, this.getAbsorptionAmount() - absorbed));
            float f1 = this.damageContainers.peek().getNewDamage();
            if (absorbed > 0.0F && absorbed < 3.4028235E37F) {
                Entity var8 = damageSource.getEntity();
                if (var8 instanceof ServerPlayer) {
                    ServerPlayer serverplayer = (ServerPlayer)var8;
                    serverplayer.awardStat(Stats.DAMAGE_DEALT_ABSORBED, Math.round(absorbed * 10.0F));
                }
            }

            if (f1 != 0.0F) {
                this.getCombatTracker().recordDamage(damageSource, f1);
                this.setHealth(this.getHealth() - f1);
                this.gameEvent(GameEvent.ENTITY_DAMAGE);
                this.onDamageTaken(this.damageContainers.peek());

                spawnSlimes((int) f1);
            }

            CommonHooks.onLivingDamagePost(this, this.damageContainers.peek());
        }
    }

    protected void spawnSlimes(int num) {
        Level level = level();
        if (level.isClientSide) return;

        int health = (int) getMaxHealth();
        int slimeNum = Math.min(num, health);

        while (slimeNum >= 2)
        {
            int i = 1;
            if (num > 8) i = 2;

            int k = slimeNum / 2;
            float f = ((float) (k % 2) - 0.5F) * 0.5F;
            float f1 = ((float) (k / 2) - 0.5F) * 0.5F;
            DefiledSlime slime = new DefiledSlime(DLEntities.DEFILED_SLIME.get(), level);

            slime.setSize(i, true);
            BlockPos pos = getOnPos();
            slime.setPos(pos.getX() + f, pos.getY() + 1f, pos.getZ() + f1);
            slime.setYRot(random.nextFloat() * 360.0F);

            level.addFreshEntity(slime);

            if (i == 1) slimeNum -= 2;
            else slimeNum -= i * 4;
        }
    }
}
