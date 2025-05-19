package com.euphony.defiled_lands_reborn.client.renderer;

import com.euphony.defiled_lands_reborn.client.layer.TwistedShamblerEyesLayer;
import com.euphony.defiled_lands_reborn.common.entity.TwistedShambler;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class TwistedShamblerRenderer extends MobRenderer<TwistedShambler, EndermanModel<TwistedShambler>> {
    private static final ResourceLocation TWISTED_SHAMBLER_LOCATION = Utils.prefix("textures/entity/shambler.png");

    public TwistedShamblerRenderer(EntityRendererProvider.Context p_173992_) {
        super(p_173992_, new EndermanModel<>(p_173992_.bakeLayer(ModelLayers.ENDERMAN)), 0.5F);
        this.addLayer(new TwistedShamblerEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(@NotNull TwistedShambler twistedShambler) {
        return TWISTED_SHAMBLER_LOCATION;
    }
}
