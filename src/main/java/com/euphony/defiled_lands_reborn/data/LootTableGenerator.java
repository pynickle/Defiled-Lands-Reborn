package com.euphony.defiled_lands_reborn.data;

import com.euphony.defiled_lands_reborn.data.loot.BlockLootGenerator;
import com.euphony.defiled_lands_reborn.data.loot.EntityLootGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class LootTableGenerator extends LootTableProvider {
    public LootTableGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(EntityLootGenerator::new, LootContextParamSets.ENTITY),
                new SubProviderEntry(BlockLootGenerator::new, LootContextParamSets.BLOCK)
        ), provider);
    }

    /*
    @Override
    protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector problemreporter$collector) {

    }

     */
}
