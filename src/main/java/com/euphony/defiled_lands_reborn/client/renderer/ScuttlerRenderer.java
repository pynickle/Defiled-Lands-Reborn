package com.euphony.defiled_lands_reborn.client.renderer;

import com.euphony.defiled_lands_reborn.client.layer.ScuttlerEyesLayer;
import com.euphony.defiled_lands_reborn.common.entity.Scuttler;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ScuttlerRenderer extends MobRenderer<Scuttler, SpiderModel<Scuttler>> {
    private static final ResourceLocation SCUTTLER_LOCATION = Utils.prefix("textures/entity/scuttler.png");

    public ScuttlerRenderer(EntityRendererProvider.Context p_174401_) {
        this(p_174401_, ModelLayers.SPIDER);
    }

    public ScuttlerRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer) {
        super(context, new SpiderModel<>(context.bakeLayer(layer)), 0.8F);
        this.addLayer(new ScuttlerEyesLayer<>(this));
    }

    protected float getFlipDegrees(Scuttler livingEntity) {
        return 180.0F;
    }

    public ResourceLocation getTextureLocation(Scuttler entity) {
        return SCUTTLER_LOCATION;
    }
}
