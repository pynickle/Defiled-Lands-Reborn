package com.euphony.defiled_lands_reborn.common.entity;

import com.euphony.defiled_lands_reborn.common.init.DLItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import org.jetbrains.annotations.NotNull;

public class DefiledSlime extends Slime {
    public DefiledSlime(EntityType<? extends Slime> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected @NotNull ParticleOptions getParticleType() {
        return new ItemParticleOption(ParticleTypes.ITEM, DLItems.FOUL_SLIME.toStack());
    }

    public static boolean checkDefiledSlimeSpawnRules(EntityType<DefiledSlime> entityType, LevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        if (level.getDifficulty() != Difficulty.PEACEFUL) {
            if (EntitySpawnReason.isSpawner(spawnReason)) {
                return checkMobSpawnRules(entityType, level, spawnReason, pos, random);
            }

            if (level.getBiome(pos).is(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS) && pos.getY() > 50 && pos.getY() < 70 && random.nextFloat() < 0.5F && random.nextFloat() < level.getMoonBrightness() && level.getMaxLocalRawBrightness(pos) <= random.nextInt(8)) {
                return checkMobSpawnRules(entityType, level, spawnReason, pos, random);
            }

            if (!(level instanceof WorldGenLevel)) {
                return false;
            }

            ChunkPos chunkpos = new ChunkPos(pos);
            boolean flag = WorldgenRandom.seedSlimeChunk(chunkpos.x, chunkpos.z, ((WorldGenLevel)level).getSeed(), 987234911L).nextInt(10) == 0;
            if (random.nextInt(10) == 0 && flag && pos.getY() < 40) {
                return checkMobSpawnRules(entityType, level, spawnReason, pos, random);
            }
        }

        return false;
    }
}
