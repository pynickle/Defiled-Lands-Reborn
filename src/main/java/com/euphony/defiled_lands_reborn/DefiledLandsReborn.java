package com.euphony.defiled_lands_reborn;

import com.euphony.defiled_lands_reborn.common.init.*;
import com.euphony.defiled_lands_reborn.common.item.armor.DLArmorMaterials;
import com.euphony.defiled_lands_reborn.common.worldgen.DLRegion;
import com.euphony.defiled_lands_reborn.common.worldgen.DLSurfaceRuleData;
import com.euphony.defiled_lands_reborn.config.ConfigHolder;
import com.euphony.defiled_lands_reborn.integration.curios.CuriosCompat;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;


@Mod(DefiledLandsReborn.MOD_ID)
public class DefiledLandsReborn {
    public static final String MOD_ID = "defiled_lands_reborn";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public DefiledLandsReborn(IEventBus modEventBus, ModContainer modContainer) {
        ConfigHolder.init(modContainer, modEventBus);

        DLSounds.SOUNDS.register(modEventBus);

        DLBlocks.BLOCKS.register(modEventBus);
        DLItems.ITEMS.register(modEventBus);
        DLBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        DLEntities.ENTITIES.register(modEventBus);
        DLEffects.EFFECTS.register(modEventBus);
        DLCreativeTabs.TABS.register(modEventBus);

        DLFoliagePlacers.PLACERS.register(modEventBus);
        DLTrunkPlacers.PLACERS.register(modEventBus);

        DLFeatures.FEATURES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        if (Utils.isModLoaded("curios")) {
            modEventBus.addListener(CuriosCompat::registerCapabilities);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new DLRegion());
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, DLSurfaceRuleData.makeRules());
        });
    }
}
