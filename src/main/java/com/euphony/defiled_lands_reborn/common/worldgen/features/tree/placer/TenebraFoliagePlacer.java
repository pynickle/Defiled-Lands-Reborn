package com.euphony.defiled_lands_reborn.common.worldgen.features.tree.placer;

import com.euphony.defiled_lands_reborn.common.init.DLFoliagePlacers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

public class TenebraFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<TenebraFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((p_161522_) ->
            foliagePlacerParts(p_161522_).apply(p_161522_, TenebraFoliagePlacer::new));

    public TenebraFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    protected @NotNull FoliagePlacerType<?> type() {
        return DLFoliagePlacers.TENEBRA_FOLIAGE_PLACER.get();
    }

    protected void createFoliage(LevelSimulatedReader level, FoliagePlacer.FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliagePlacer.FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos blockpos = attachment.pos();
        BlockPos.MutableBlockPos blockpos$mutableblockpos = blockpos.mutable();

        for(int i = 0; i < random.nextInt(3); ++i) {
            blockpos$mutableblockpos.set(blockpos);
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            blockpos$mutableblockpos.move(direction);
            tryPlaceLeaf(level, blockSetter, random, config, blockpos$mutableblockpos);
        }
    }

    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return 1;
    }

    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return false;
    }
}
