package com.euphony.defiled_lands_reborn.utils;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;

public class Utils {
    public static ResourceLocation prefix(String path) {
        return ResourceLocation.fromNamespaceAndPath(DefiledLandsReborn.MOD_ID, path);
    }

    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }
}
