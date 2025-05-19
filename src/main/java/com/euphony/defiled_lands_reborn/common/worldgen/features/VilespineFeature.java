package com.euphony.defiled_lands_reborn.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class VilespineFeature extends Feature<VilespineConfiguration> {
    public VilespineFeature(Codec<VilespineConfiguration> codec) {
        super(codec);
    }


    public boolean place(FeaturePlaceContext<VilespineConfiguration> context) {
        WorldGenLevel worldgenlevel = context.level();
        VilespineConfiguration vilespineConfiguration = context.config();
        RandomSource randomsource = context.random();
        int i = vilespineConfiguration.layers().size();
        int[] aint = new int[i];
        int j = 0;

        for(int k = 0; k < i; ++k) {
            aint[k] = vilespineConfiguration.layers().get(k).height().sample(randomsource);
            j += aint[k];
        }

        if (j == 0) {
            return false;
        } else {
            BlockPos.MutableBlockPos blockpos$mutableblockpos1 = context.origin().mutable();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = blockpos$mutableblockpos1.mutable();

            if (!vilespineConfiguration.primaryAllowedPlacement().test(worldgenlevel, blockpos$mutableblockpos)) {
                truncate(aint, j, 0, vilespineConfiguration.prioritizeTip());
            } else {
                blockpos$mutableblockpos.move(vilespineConfiguration.direction());
                for(int l = 1; l < j; ++l) {
                    if (!vilespineConfiguration.allowedPlacement().test(worldgenlevel, blockpos$mutableblockpos)) {
                        truncate(aint, j, l, vilespineConfiguration.prioritizeTip());
                        break;
                    }

                    blockpos$mutableblockpos.move(vilespineConfiguration.direction());
                }
            }

            for(int k1 = 0; k1 < i; ++k1) {
                int i1 = aint[k1];
                if (i1 != 0) {
                    VilespineConfiguration.Layer vilespineConfiguration$layer = vilespineConfiguration.layers().get(k1);

                    for(int j1 = 0; j1 < i1; ++j1) {
                        if(j1 == i1 - 1){
                            worldgenlevel.setBlock(blockpos$mutableblockpos1, vilespineConfiguration.topState().getState(randomsource, blockpos$mutableblockpos1), 2);
                            blockpos$mutableblockpos1.move(vilespineConfiguration.direction());
                        } else {
                            worldgenlevel.setBlock(blockpos$mutableblockpos1, vilespineConfiguration$layer.state().getState(randomsource, blockpos$mutableblockpos1), 2);
                            blockpos$mutableblockpos1.move(vilespineConfiguration.direction());
                        }
                    }
                }
            }

            return true;
        }
    }

    private static void truncate(int[] layerHeights, int totalHeight, int currentHeight, boolean prioritizeTip) {
        int i = totalHeight - currentHeight;
        int j = prioritizeTip ? 1 : -1;
        int k = prioritizeTip ? 0 : layerHeights.length - 1;
        int l = prioritizeTip ? layerHeights.length : -1;

        for(int i1 = k; i1 != l && i > 0; i1 += j) {
            int j1 = layerHeights[i1];
            int k1 = Math.min(j1, i);
            i -= k1;
            layerHeights[i1] -= k1;
        }

    }
}
