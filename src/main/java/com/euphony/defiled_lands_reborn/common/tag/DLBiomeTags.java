package com.euphony.defiled_lands_reborn.common.tag;

import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class DLBiomeTags {
    public static final TagKey<Biome> IS_DEFILED = create("is_defiled");

    private static TagKey<Biome> create(String name) {
        return TagKey.create(Registries.BIOME, Utils.prefix(name));
    }

}
