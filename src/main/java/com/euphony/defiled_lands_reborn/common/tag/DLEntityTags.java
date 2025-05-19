package com.euphony.defiled_lands_reborn.common.tag;

import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class DLEntityTags {
    public static final TagKey<EntityType<?>> IS_DEFILED = create("is_defiled");

    private static TagKey<EntityType<?>> create(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, Utils.prefix(name));
    }
}
