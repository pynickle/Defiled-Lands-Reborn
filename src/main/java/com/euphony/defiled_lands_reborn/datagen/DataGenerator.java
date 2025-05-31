package com.euphony.defiled_lands_reborn.datagen;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.datagen.tag.BlockTagGenerator;
import com.euphony.defiled_lands_reborn.datagen.tag.ItemTagGenerator;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = DefiledLandsReborn.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerator {
    @SubscribeEvent
    public static void generate(GatherDataEvent.Client event) {
        net.minecraft.data.DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        DatapackBuiltinEntriesProvider datapackProvider = new RegistryDataGenerator(output, provider);
        CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();
        generator.addProvider(true, datapackProvider);

        BlockTagGenerator blockTagGenerator = new BlockTagGenerator(output, lookupProvider);
        generator.addProvider(true, blockTagGenerator);
        generator.addProvider(true, new ItemTagGenerator(output, lookupProvider, blockTagGenerator.contentsGetter()));

        generator.addProvider(true, new RecipeGenerator.Runner(output, lookupProvider));

        generator.addProvider(true, new DataMapGenerator(output, lookupProvider));

        // generator.addProvider(true, new LootTableGenerator(output, lookupProvider));

        generator.addProvider(true, new PackMetadataGenerator(output).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.literal("Resources for Defiled Lands Reborn"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.SERVER_DATA),
                Optional.empty())));
    }
}
