package com.euphony.defiled_lands_reborn.client.layer;

import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class ScuttlerEyesLayer<M extends SpiderModel> extends EyesLayer<LivingEntityRenderState, M> {
    private static final RenderType SCUTTLER_EYES = RenderType.eyes(Utils.prefix("textures/entity/scuttler_eyes.png"));

    public ScuttlerEyesLayer(RenderLayerParent<LivingEntityRenderState, M> p_117507_) {
        super(p_117507_);
    }

    public RenderType renderType() {
        return SCUTTLER_EYES;
    }
}