package com.euphony.defiled_lands_reborn.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.FallingBlock;
import org.jetbrains.annotations.NotNull;

public class CorruptedFallingBlock extends FallingBlock {
    private static final MapCodec<CorruptedFallingBlock> CODEC = simpleCodec(CorruptedFallingBlock::new);

    public CorruptedFallingBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends FallingBlock> codec() {
        return CODEC;
    }
}
