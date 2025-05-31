package com.euphony.defiled_lands_reborn.common.block;

import com.euphony.defiled_lands_reborn.common.init.DLBlocks;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.common.tag.DLBlockTags;
import com.euphony.defiled_lands_reborn.common.tag.DLEntityTags;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.Optional;

public class VilespineBlock extends Block {
    public static final BooleanProperty TOPMOST = BooleanProperty.create("topmost");

    public VilespineBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(TOPMOST, false));
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier) {
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
            if(livingEntity instanceof Player player) {
                if(player.getInventory().contains(DLItems.PHYTOPROSTASIA_AMULET.toStack())) return;
            }
            livingEntity.hurt(new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(DamageTypes.CACTUS)), 3);
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState belowState = level.getBlockState(blockpos);
        return belowState.is(DLBlockTags.BASE_DEFILED_SURFACE)
                || belowState.is(DLBlocks.VILESPINE.get());
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if(!state.canSurvive(level, pos)) return Blocks.AIR.defaultBlockState();

        if(level.getBlockState(pos.above()).isAir() && !state.getValue(TOPMOST)) state = state.setValue(TOPMOST, true);
        else if(!level.getBlockState(pos.above()).isAir() && state.getValue(TOPMOST)) state = state.setValue(TOPMOST, false);

        return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TOPMOST);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(TOPMOST,
                context.getLevel().getBlockState(context.getClickedPos().above()).isAir());
    }
}
