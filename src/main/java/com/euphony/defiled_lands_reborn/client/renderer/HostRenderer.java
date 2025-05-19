package com.euphony.defiled_lands_reborn.client.renderer;

import com.euphony.defiled_lands_reborn.common.entity.Host;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class HostRenderer extends HumanoidMobRenderer<Host, ZombieModel<Host>> {
    private static final ResourceLocation HOST_LOCATION = Utils.prefix("textures/entity/host.png");

    public HostRenderer(EntityRendererProvider.Context context) {
        super(context, new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new ZombieModel(context.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)), new ZombieModel(context.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)), context.getModelManager()));
    }

    @Override
    public ResourceLocation getTextureLocation(Host entity) {
        return HOST_LOCATION;
    }
}
