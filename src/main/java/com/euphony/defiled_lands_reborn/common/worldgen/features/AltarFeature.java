package com.euphony.defiled_lands_reborn.common.worldgen.features;

import com.euphony.defiled_lands_reborn.common.init.DLBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class AltarFeature extends Feature<NoneFeatureConfiguration> {
    public AltarFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        WorldGenLevel level = featurePlaceContext.level();
        BlockPos pos = featurePlaceContext.origin();
        for(int i = -2; i <= 2; i++) {
            for(int j = -2; j <= 2; j++) {
                BlockPos blockPos = pos.offset(i, 1, j);
                if (!level.isEmptyBlock(blockPos)) {
                    return false;
                }
            }
        }
        for(int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                BlockPos blockPos = pos.offset(i, -1, j);
                if (level.isEmptyBlock(blockPos) || level.getBlockState(blockPos).is(Blocks.WATER)) {
                    return false;
                }
            }
        }
        for(int i = -2; i <= 2; i++) {
            for(int j = -2; j <= 2; j++) {
                BlockPos blockPos = pos.offset(i, 0, j);
                BlockState blockState = Math.abs(i) != 2 && Math.abs(j) != 2 ?
                        DLBlocks.DEFILED_SANDSTONE.get().defaultBlockState() :
                        DLBlocks.DEFILED_STONE_BRICKS.get().defaultBlockState();
                level.setBlock(blockPos, blockState, 3);
            }
        }
        level.setBlock(pos.above(), DLBlocks.CONJURING_ALTAR.get().defaultBlockState(), 3);
        int[][] arrays = {
                {2, -2},
                {2, -2},
                {1, 2, 3}
        };

        for (int a : arrays[0]) {
            for (int b : arrays[1]) {
                for (int c : arrays[2]) {
                    BlockPos blockPos = pos.offset(a, c, b);
                    level.setBlock(blockPos, DLBlocks.DEFILED_CRACKED_STONE_BRICKS.get().defaultBlockState(), 3);
                }
            }
        }

        return true;
    }
}
