package com.euphony.defiled_lands_reborn.common.worldgen.features.tree;

import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class DLTreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> TENEBRA_TREE = createKey("tenebra");

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Utils.prefix(name));
    }
}
