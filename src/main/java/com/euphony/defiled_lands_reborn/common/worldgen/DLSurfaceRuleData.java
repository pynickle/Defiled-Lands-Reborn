package com.euphony.defiled_lands_reborn.common.worldgen;

import com.euphony.defiled_lands_reborn.common.init.DLBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;


public class DLSurfaceRuleData {
    private static final SurfaceRules.RuleSource DEFILED_DIRT = makeStateRule(DLBlocks.DEFILED_DIRT.get());
    private static final SurfaceRules.RuleSource DEFILED_GRASS_BLOCK = makeStateRule(DLBlocks.DEFILED_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource DEFILED_STONE = makeStateRule(DLBlocks.DEFILED_STONE.get());
    private static final SurfaceRules.RuleSource DEFILED_SAND = makeStateRule(DLBlocks.DEFILED_SAND.get());

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DEFILED_GRASS_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, DEFILED_DIRT)
        );
        // SurfaceRules.ConditionSource isAbove0 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(0), 0);
        SurfaceRules.RuleSource defiledSurface = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(DLBiomes.DEFILED_PLAINS, DLBiomes.DEFILED_SNOWY_PLAINS, DLBiomes.VILESPINE_FOREST, DLBiomes.TENEBRA_FOREST),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel,
                                SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), grassSurface)
                        ),
                        DEFILED_STONE
                )
        );

        SurfaceRules.RuleSource sandSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DEFILED_SAND)
        );
        SurfaceRules.RuleSource desertSurface = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(DLBiomes.DEFILED_DESERT),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(isAtOrAboveWaterLevel,
                                SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), sandSurface)
                        ),
                        DEFILED_STONE
                )
        );

        SurfaceRules.RuleSource hillSurface = SurfaceRules.ifTrue(
                SurfaceRules.isBiome(DLBiomes.DEFILED_HILLS),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(surfaceNoiseAbove(1.0F), DEFILED_STONE),
                        grassSurface
                )
        );

        return SurfaceRules.sequence(
                defiledSurface,
                desertSurface,
                hillSurface
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }

    private static SurfaceRules.ConditionSource surfaceNoiseAbove(double value) {
        return SurfaceRules.noiseCondition(Noises.SURFACE, value / (double)8.25F, Double.MAX_VALUE);
    }
}
