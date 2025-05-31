package com.euphony.defiled_lands_reborn.client.renderer;

import com.euphony.defiled_lands_reborn.client.model.BookWyrmModel;
import com.euphony.defiled_lands_reborn.common.entity.BookWyrm;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;


public class BookWyrmRenderer extends MobRenderer<BookWyrm, LivingEntityRenderState, BookWyrmModel> {
    private static final ResourceLocation TEXTURE = Utils.prefix("textures/entity/book_wyrm.png");


    public BookWyrmRenderer(Context context) {
        super(context, new BookWyrmModel(context.bakeLayer(BookWyrmModel.MODEL)), 0.7f);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return null;
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState entity) {
        return TEXTURE;
    }
}
