package com.euphony.defiled_lands_reborn.common.block;

import com.euphony.defiled_lands_reborn.utils.CorruptionUtils;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CorruptedLeavesBlock extends LeavesBlock {
    public static final MapCodec<CorruptedLeavesBlock> CODEC = RecordCodecBuilder.mapCodec((p_399854_) -> p_399854_.group(ExtraCodecs.floatRange(0.0F, 1.0F).fieldOf("leaf_particle_chance").forGetter((p_399708_) -> p_399708_.leafParticleChance), propertiesCodec()).apply(p_399854_, CorruptedLeavesBlock::new));

    public CorruptedLeavesBlock(float leafParticleChance, Properties properties) {
        super(leafParticleChance, properties.randomTicks());
    }

    @Override
    public MapCodec<? extends LeavesBlock> codec() {
        return null;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        CorruptionUtils.spread(level, pos, state, random);
    }

    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos blockPos, RandomSource randomSource) {

    }
}
