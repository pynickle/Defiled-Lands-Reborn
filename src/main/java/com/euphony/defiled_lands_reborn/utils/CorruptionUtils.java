package com.euphony.defiled_lands_reborn.utils;

import com.euphony.defiled_lands_reborn.common.datamap.DLDataMaps;
import com.euphony.defiled_lands_reborn.common.tag.DLBiomeTags;
import com.euphony.defiled_lands_reborn.config.ConfigHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;

public class CorruptionUtils {
    public static void spread(Level level, BlockPos pos, BlockState state, RandomSource rand) {
        if (!level.isClientSide()) {
            if (canSpread(level, pos, rand) && rand.nextInt(3) == 0) {
                for (int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.offset(rand.nextInt(5) - 2, rand.nextInt(5) - 2, rand.nextInt(5) - 2);

                    if (blockpos.getY() > -60 && blockpos.getY() < 320 && level.isLoaded(blockpos)) {
                        return;
                    }

                    BlockState blockstate = level.getBlockState(blockpos);
                    corrupt(level, blockpos, blockstate);
                }
            }
        }
    }

    public static boolean canSpread(Level level, BlockPos pos, RandomSource rand) {
        Holder<Biome> biome = level.getBiome(pos);

        return biome.is(DLBiomeTags.IS_DEFILED) || ConfigHelper.common().general.enableCorruptionSpread.get();
    }

    public static boolean corrupt(Level level, BlockPos blockpos, BlockState state) {
        var corruptionMap = state.getBlock().builtInRegistryHolder().getData(DLDataMaps.CORRUPTION);
        if (corruptionMap == null) return false;

        level.setBlockAndUpdate(blockpos, corruptionMap.result().defaultBlockState());
        return true;
    }

}

