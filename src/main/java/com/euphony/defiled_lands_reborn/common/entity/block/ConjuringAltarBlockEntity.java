package com.euphony.defiled_lands_reborn.common.entity.block;

import com.euphony.defiled_lands_reborn.common.init.DLBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;


public class ConjuringAltarBlockEntity extends BlockEntity {
    public ConjuringAltarBlockEntity(BlockPos pos, BlockState blockState) {
        super(DLBlockEntities.CONJURING_ALTAR_BE.get(), pos, blockState);
    }
}
