package com.euphony.defiled_lands_reborn.client.renderer;

import com.euphony.defiled_lands_reborn.common.entity.Host;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class HostRenderer extends HumanoidMobRenderer<Host, ZombieRenderState, ZombieModel<ZombieRenderState>> {
    private static final ResourceLocation HOST_LOCATION = Utils.prefix("textures/entity/host.png");

    public HostRenderer(EntityRendererProvider.Context context) {
        super(context, new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE)), 0.5f);
        this.addLayer(new HumanoidArmorLayer<>(this, new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)), new ZombieModel(context.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)), context.getEquipmentRenderer()));
    }

    @Override
    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ZombieRenderState entity) {
        return HOST_LOCATION;
    }
}
