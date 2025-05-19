package com.euphony.defiled_lands_reborn.common.init;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.common.worldgen.features.AltarFeature;
import com.euphony.defiled_lands_reborn.common.worldgen.features.VilespineConfiguration;
import com.euphony.defiled_lands_reborn.common.worldgen.features.VilespineFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DLFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, DefiledLandsReborn.MOD_ID);

    public static final DeferredHolder<Feature<?>, Feature<?>> VILESPINE = FEATURES.register("vilespine", () -> new VilespineFeature(VilespineConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<?>> ALTAR = FEATURES.register("altar", () -> new AltarFeature(NoneFeatureConfiguration.CODEC));
}
