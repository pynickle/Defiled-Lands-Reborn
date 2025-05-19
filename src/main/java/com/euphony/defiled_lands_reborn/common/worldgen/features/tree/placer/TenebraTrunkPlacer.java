package com.euphony.defiled_lands_reborn.common.worldgen.features.tree.placer;

import com.euphony.defiled_lands_reborn.common.init.DLTrunkPlacers;
import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class TenebraTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<TenebraTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((p_161786_) -> trunkPlacerParts(p_161786_).apply(p_161786_, TenebraTrunkPlacer::new));

    public TenebraTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    protected TrunkPlacerType<?> type() {
        return DLTrunkPlacers.TENEBRA_TRUNK_PLACER.get();
    }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        // Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        // int i = freeTreeHeight - 1;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();
        BlockPos blockpos = blockpos$mutableblockpos.below();
        setDirtAt(level, blockSetter, random, blockpos, config);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        int height = 0;

        for(int i = 1; i <= random.nextInt(5) + 2; ++i) {
            for (int j = 1; j <= random.nextInt(4) + 1; ++j) {
                if (TreeFeature.validTreePos(level, blockpos$mutableblockpos)) {
                    height += 1;
                    this.placeLog(level, blockSetter, random, blockpos$mutableblockpos, config);
                }
                if(height >= 10) {
                    break;
                }
                list.add(new FoliagePlacer.FoliageAttachment(blockpos$mutableblockpos.immutable(), 0, false));
                blockpos$mutableblockpos.move(Direction.UP);
            }
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            blockpos$mutableblockpos.move(direction);
            if(height >= 10) {
                break;
            }
        }

        return list;
    }
}
