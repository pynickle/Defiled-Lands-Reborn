package com.euphony.defiled_lands_reborn.common.datamap.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

public record CorruptionBlock(Block result) {

    public static final Codec<CorruptionBlock> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.BLOCK.byNameCodec()
                    .fieldOf("result")
                    .forGetter(CorruptionBlock::result)
    ).apply(instance, CorruptionBlock::new));
}
