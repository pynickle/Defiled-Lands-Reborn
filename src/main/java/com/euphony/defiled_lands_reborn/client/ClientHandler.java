package com.euphony.defiled_lands_reborn.client;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.client.model.BookWyrmModel;
import com.euphony.defiled_lands_reborn.client.renderer.*;
import com.euphony.defiled_lands_reborn.client.renderer.boss.DestroyerBossRenderer;
import com.euphony.defiled_lands_reborn.client.renderer.boss.MournerBossRenderer;
import com.euphony.defiled_lands_reborn.common.init.DLEntities;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = DefiledLandsReborn.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientHandler {
    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DLEntities.DEFILED_SLIME.get(), DefiledSlimeRenderer::new);
        event.registerEntityRenderer(DLEntities.BOOK_WYRM.get(), BookWyrmRenderer::new);
        event.registerEntityRenderer(DLEntities.GOLDEN_BOOK_WYRM.get(), GoldenBookWyrmRenderer::new);
        event.registerEntityRenderer(DLEntities.SCUTTLER.get(), ScuttlerRenderer::new);
        event.registerEntityRenderer(DLEntities.HOST.get(), HostRenderer::new);
        event.registerEntityRenderer(DLEntities.SHAMBLER.get(), ShamblerRenderer::new);
        event.registerEntityRenderer(DLEntities.TWISTED_SHAMBLER.get(), TwistedShamblerRenderer::new);

        event.registerEntityRenderer(DLEntities.MOURNER.get(), MournerBossRenderer::new);
        event.registerEntityRenderer(DLEntities.DESTROYER.get(), DestroyerBossRenderer::new);

        event.registerEntityRenderer(DLEntities.RAVAGER_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(DLEntities.BLASTEM_FRUIT_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(DLEntities.BLAZING_BLASTEM_FRUIT_PROJECTILE.get(), ThrownItemRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BookWyrmModel.MODEL, BookWyrmModel::createBodyLayer);
    }
}
