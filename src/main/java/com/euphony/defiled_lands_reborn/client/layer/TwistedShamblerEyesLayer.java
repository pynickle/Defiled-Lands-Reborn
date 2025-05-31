package com.euphony.defiled_lands_reborn.client.layer;

import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.world.entity.LivingEntity;

public class TwistedShamblerEyesLayer extends EyesLayer<EndermanRenderState, EndermanModel<EndermanRenderState>> {
    private static final RenderType TWISTED_SHAMBLER_EYES = RenderType.eyes(Utils.prefix("textures/entity/twisted_shambler_eyes.png"));

    public TwistedShamblerEyesLayer(RenderLayerParent<EndermanRenderState, EndermanModel<EndermanRenderState>> p_116964_) {
        super(p_116964_);
    }

    public RenderType renderType() {
        return TWISTED_SHAMBLER_EYES;
    }
}
