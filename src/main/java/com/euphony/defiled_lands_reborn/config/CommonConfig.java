package com.euphony.defiled_lands_reborn.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class CommonConfig {
    public static class General {
        public ModConfigSpec.BooleanValue enableCorruptionSpread;
    }
    public static class Worldgen {
        public ModConfigSpec.ConfigValue<List<? extends String>> oilWorldGenDimensionWhitelist;
        public ModConfigSpec.ConfigValue<List<? extends String>> oilWorldGenDimensionBlacklist;
    }

    public final General general = new General();
    public final Worldgen worldgen = new Worldgen();


    CommonConfig(final ModConfigSpec.Builder builder) {
        builder.push("general");
        general.enableCorruptionSpread = builder
                .comment("Enable Corruption Spread")
                .translation("pneumaticcraft.config.common.general.enable_dungeon_loot")
                .define("enable_corruption_spread", true);
        builder.pop();
    }
}
