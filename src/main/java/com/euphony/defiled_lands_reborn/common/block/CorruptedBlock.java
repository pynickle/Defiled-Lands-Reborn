package com.euphony.defiled_lands_reborn.common.block;

import com.euphony.defiled_lands_reborn.utils.CorruptionUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CorruptedBlock extends Block {
    public CorruptedBlock(Properties properties) {
        super(properties.randomTicks());
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        CorruptionUtils.spread(level, pos, state, random);
    }
}

