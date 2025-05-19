package com.euphony.defiled_lands_reborn.common.block;

import com.euphony.defiled_lands_reborn.common.init.DLBlocks;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.common.tag.DLEntityTags;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.Optional;

public class BlastemBlock extends Block {
    public BlastemBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(BlockStateProperties.AGE_15, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.AGE_15);
    }

    protected static final VoxelShape SHAPE = Block.box(3.0F, 0.0F, 3.0F, 13.0F, 14.0F, 13.0F);

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState blockState = level.getBlockState(blockpos);
        return blockState.is(DLBlocks.DEFILED_GRASS_BLOCK) || blockState.is(DLBlocks.DEFILED_DIRT);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if(state.getValue(BlockStateProperties.AGE_15) == 15) {
            if(entity instanceof LivingEntity livingEntity) {
                if(livingEntity.getType().getTags().anyMatch((p) -> p.equals(DLEntityTags.IS_DEFILED))){
                    return;
                }
                if (Utils.isModLoaded("curios")) {
                    Optional<ICuriosItemHandler> curiosInventory = CuriosApi.getCuriosInventory(livingEntity);
                    if(curiosInventory.isPresent()) {
                        if (curiosInventory.get().isEquipped(DLItems.PHYTOPROSTASIA_AMULET.get())) {
                            return;
                        }
                    }
                }
            }
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.AGE_15, 0));

            level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 1.5F, false, Level.ExplosionInteraction.BLOCK);
        }
    }
}
