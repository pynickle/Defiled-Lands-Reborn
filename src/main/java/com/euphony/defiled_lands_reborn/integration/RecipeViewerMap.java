package com.euphony.defiled_lands_reborn.integration;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.common.datamap.DLDataMaps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class RecipeViewerMap {
    public static List<Pair<Block, Block>> getCorruptionRecipes() {
        List<Pair<Block, Block>> info = new ArrayList<>();
        for (Block input : BuiltInRegistries.BLOCK) {
            // DefiledLandsReborn.LOGGER.info("Found block: " + input);
            var output = input.builtInRegistryHolder().getData(DLDataMaps.CORRUPTION);
            // DefiledLandsReborn.LOGGER.info(output);
            if (output != null) {
                DefiledLandsReborn.LOGGER.info(output);
                info.add(Pair.of(input, output.result()));
            }
        }
        return info;
    }
}
