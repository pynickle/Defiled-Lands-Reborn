package com.euphony.defiled_lands_reborn.common.worldgen;

import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class DLBiomes {
    public static final ResourceKey<Biome> DEFILED_PLAINS = register("defiled_plains");
    public static final ResourceKey<Biome> DEFILED_DESERT = register("defiled_desert");
    public static final ResourceKey<Biome> DEFILED_HILLS = register("defiled_hills");
    public static final ResourceKey<Biome> DEFILED_SNOWY_PLAINS = register("defiled_snowy_plains");
    public static final ResourceKey<Biome> TENEBRA_FOREST = register("tenebra_forest");
    public static final ResourceKey<Biome> VILESPINE_FOREST = register("vilespine_forest");

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, Utils.prefix(name));
    }
}
