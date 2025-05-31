package com.euphony.defiled_lands_reborn.client.renderer;

import com.euphony.defiled_lands_reborn.common.entity.Shambler;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ShamblerRenderer extends MobRenderer<Shambler, EndermanRenderState, EndermanModel<EndermanRenderState>> {
    private static final ResourceLocation SHAMBLER_LOCATION = Utils.prefix("textures/entity/shambler.png");

    public ShamblerRenderer(EntityRendererProvider.Context p_173992_) {
        super(p_173992_, new EndermanModel<>(p_173992_.bakeLayer(ModelLayers.ENDERMAN)), 0.5F);
    }

    @Override
    public EndermanRenderState createRenderState() {
        return new EndermanRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(@NotNull EndermanRenderState Shambler) {
        return SHAMBLER_LOCATION;
    }
}
