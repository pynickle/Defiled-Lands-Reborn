package com.euphony.defiled_lands_reborn.common.init;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.common.worldgen.features.tree.placer.TenebraFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DLFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> PLACERS = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, DefiledLandsReborn.MOD_ID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<TenebraFoliagePlacer>> TENEBRA_FOLIAGE_PLACER =
            PLACERS.register("tenebra_foliage_placer", () -> new FoliagePlacerType<>(TenebraFoliagePlacer.CODEC));
}
