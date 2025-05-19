package com.euphony.defiled_lands_reborn.common.worldgen.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;

public record VilespineConfiguration(
        List<VilespineConfiguration.Layer> layers, Direction direction, BlockPredicate allowedPlacement, BlockPredicate primaryAllowedPlacement, boolean prioritizeTip, BlockStateProvider topState) implements FeatureConfiguration {
    public static final Codec<VilespineConfiguration> CODEC = RecordCodecBuilder.create(
            (p_191222_) ->
                    p_191222_.group(
                            VilespineConfiguration.Layer.CODEC.listOf()
                                    .fieldOf("layers")
                                    .forGetter(VilespineConfiguration::layers),
                            Direction.CODEC.fieldOf("direction")
                                    .forGetter(VilespineConfiguration::direction),
                            BlockPredicate.CODEC.fieldOf("allowed_placement")
                                    .forGetter(VilespineConfiguration::allowedPlacement),
                            BlockPredicate.CODEC.fieldOf("primary_allowed_placement")
                                    .forGetter(VilespineConfiguration::primaryAllowedPlacement),
                            Codec.BOOL.fieldOf("prioritize_tip")
                                    .forGetter(VilespineConfiguration::prioritizeTip),
                            BlockStateProvider.CODEC.fieldOf("top_state")
                                    .forGetter(VilespineConfiguration::topState)
                            )
                            .apply(p_191222_, VilespineConfiguration::new));

    public static VilespineConfiguration.Layer layer(IntProvider height, BlockStateProvider state) {
        return new VilespineConfiguration.Layer(height, state);
    }

    public static record Layer(IntProvider height, BlockStateProvider state) {
        public static final Codec<Layer> CODEC = RecordCodecBuilder.create(
                (p_191242_) -> p_191242_.group(
                        IntProvider.NON_NEGATIVE_CODEC.fieldOf("height")
                                .forGetter(Layer::height),
                        BlockStateProvider.CODEC.fieldOf("provider")
                                .forGetter(Layer::state)
                        )
                        .apply(p_191242_, Layer::new)
        );
    }
}
