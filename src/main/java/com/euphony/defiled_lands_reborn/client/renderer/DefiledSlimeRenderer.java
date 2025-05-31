package com.euphony.defiled_lands_reborn.client.renderer;

import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.ResourceLocation;

public class DefiledSlimeRenderer extends SlimeRenderer {
    private static final ResourceLocation DEFILED_SLIME_LOCATION = Utils.prefix("textures/entity/defiled_slime.png");

    public DefiledSlimeRenderer(EntityRendererProvider.Context p_174391_) {
        super(p_174391_);
    }

    @Override
    public ResourceLocation getTextureLocation(SlimeRenderState entity) {
        return DEFILED_SLIME_LOCATION;
    }
}
