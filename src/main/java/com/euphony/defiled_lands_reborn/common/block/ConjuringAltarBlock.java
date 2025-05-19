package com.euphony.defiled_lands_reborn.common.block;

import com.euphony.defiled_lands_reborn.common.entity.block.ConjuringAltarBlockEntity;
import com.euphony.defiled_lands_reborn.common.entity.boss.DestroyerBoss;
import com.euphony.defiled_lands_reborn.common.entity.boss.MournerBoss;
import com.euphony.defiled_lands_reborn.common.init.DLBlockEntities;
import com.euphony.defiled_lands_reborn.common.init.DLEntities;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.common.tag.DLBiomeTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ConjuringAltarBlock extends Block implements EntityBlock {
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public static final VoxelShape AABB = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 12.0F, 16.0F);

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AABB;
    }

    public ConjuringAltarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ACTIVE);
    }

    public <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T t) {
        if(level.getBiome(pos).is(DLBiomeTags.IS_DEFILED)
                && level.getBlockState(pos.above()).isAir()
                && level.getBlockState(pos.above().above()).isAir()) {
            if(!state.getValue(ACTIVE)) {
                level.setBlockAndUpdate(pos, state.setValue(ACTIVE, true));
            }
        } else {
            if(state.getValue(ACTIVE)) {
                level.setBlockAndUpdate(pos, state.setValue(ACTIVE, false));
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        Block block = state.getBlock();
        if (state.getValue(ACTIVE) && level.getDifficulty() != Difficulty.PEACEFUL) {
            if (!level.isClientSide) {
                if(stack.is(DLItems.IDOL_SORROW)) {
                    MournerBoss mourner = new MournerBoss(DLEntities.MOURNER.get(), level);
                    mourner.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                    mourner.makeInvulnerable();

                    for (ServerPlayer serverPlayer : level.getEntitiesOfClass(ServerPlayer.class, mourner.getBoundingBox().inflate(50.0D))) {
                        CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayer, mourner);
                    }

                    level.addFreshEntity(mourner);

                    stack.consume(1, player);
                    return ItemInteractionResult.SUCCESS;
                } else if(stack.is(DLItems.CALLING_STONE)) {
                    DestroyerBoss destroyer = new DestroyerBoss(DLEntities.DESTROYER.get(), level);
                    destroyer.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                    destroyer.makeInvulnerable();

                    for (ServerPlayer serverPlayer : level.getEntitiesOfClass(ServerPlayer.class, destroyer.getBoundingBox().inflate(50.0D))) {
                        CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayer, destroyer);
                    }

                    level.addFreshEntity(destroyer);

                    stack.consume(1, player);
                    return ItemInteractionResult.SUCCESS;
                }
            }

        }
        return ItemInteractionResult.CONSUME;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == DLBlockEntities.CONJURING_ALTAR_BE.get() ? this::tick : null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ConjuringAltarBlockEntity(blockPos, blockState);
    }
}
