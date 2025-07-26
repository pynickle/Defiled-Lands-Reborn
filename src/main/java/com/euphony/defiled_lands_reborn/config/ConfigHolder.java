package com.euphony.defiled_lands_reborn.config;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigHolder {
    static CommonConfig common;

    private static ModConfigSpec configCommonSpec;

    public static void init(ModContainer container, IEventBus modBus) {
        final Pair<CommonConfig, ModConfigSpec> spec2 = new ModConfigSpec.Builder().configure(CommonConfig::new);
        common = spec2.getLeft();
        configCommonSpec = spec2.getRight();

        container.registerConfig(ModConfig.Type.COMMON, ConfigHolder.configCommonSpec);
    }
}
