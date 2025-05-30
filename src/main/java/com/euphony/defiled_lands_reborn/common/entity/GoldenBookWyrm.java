package com.euphony.defiled_lands_reborn.common.entity;

import com.euphony.defiled_lands_reborn.common.init.DLEntities;
import com.euphony.defiled_lands_reborn.config.ConfigHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.extensions.IHolderExtension;

import java.util.List;

public class GoldenBookWyrm extends BookWyrm {
    public GoldenBookWyrm(EntityType<? extends BookWyrm> type, Level world) {
        super(type, world);
    }

    @Override
    public List<EnchantmentInstance> getPossibleEnchantments() {
        return EnchantmentHelper.selectEnchantment(random, Items.BOOK.getDefaultInstance(), getEnchLevel(), level().registryAccess().registryOrThrow(Registries.ENCHANTMENT).holders().map(IHolderExtension::getDelegate));
    }

    @Override
    public boolean isGolden(AgeableMob mate) {
        double i = ConfigHelper.common().entity.goldenBookWyrmProbabilityForOneGolden.get();
        if (mate instanceof GoldenBookWyrm) {
            i = ConfigHelper.common().entity.goldenBookWyrmProbabilityForTwoGolden.get();
        }
        if (random.nextDouble() <= i) {
            return true;
        }
        return false;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mate) {
        if(mate instanceof BookWyrm) {
            int i = 25;
            if (mate instanceof GoldenBookWyrm) {
                i = 10;
            }
            if (random.nextInt(i) == 0) {
                GoldenBookWyrm child = DLEntities.GOLDEN_BOOK_WYRM.get().create(level);
                mixGenes(this, (BookWyrm) mate, child, random);
                return child;
            } else {
                BookWyrm child = DLEntities.BOOK_WYRM.get().create(level);
                mixGenes(this, (BookWyrm) mate, child, random);
                return child;
            }
        }
        return null;
    }
}
