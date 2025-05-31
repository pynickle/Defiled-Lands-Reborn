package com.euphony.defiled_lands_reborn.common.init;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.common.entity.block.ConjuringAltarBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class DLBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, DefiledLandsReborn.MOD_ID);

    public static final Supplier<BlockEntityType<ConjuringAltarBlockEntity>> CONJURING_ALTAR_BE = BLOCK_ENTITY_TYPES.register(
            "conjuring_altar",
            () -> new BlockEntityType<>(ConjuringAltarBlockEntity::new, DLBlocks.CONJURING_ALTAR.get())
    );
}
