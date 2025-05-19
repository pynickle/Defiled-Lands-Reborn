package com.euphony.defiled_lands_reborn.common.datamap;

import com.euphony.defiled_lands_reborn.common.datamap.block.CorruptionBlock;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

public class DLDataMaps {
    public static final DataMapType<Block, CorruptionBlock> CORRUPTION = DataMapType.builder(
            Utils.prefix("corruption"), Registries.BLOCK, CorruptionBlock.CODEC).synced(CorruptionBlock.CODEC, false).build();
}
