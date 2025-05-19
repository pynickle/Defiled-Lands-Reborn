package com.euphony.defiled_lands_reborn.client.renderer;

import com.euphony.defiled_lands_reborn.client.model.BookWyrmModel;
import com.euphony.defiled_lands_reborn.common.entity.BookWyrm;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GoldenBookWyrmRenderer extends MobRenderer<BookWyrm, BookWyrmModel<BookWyrm>> {
private static final ResourceLocation TEXTURE = Utils.prefix("textures/entity/golden_book_wyrm.png");


    public GoldenBookWyrmRenderer(EntityRendererProvider.Context context) {
        super(context, new BookWyrmModel<>(context.bakeLayer(BookWyrmModel.MODEL)), 0.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(BookWyrm entity) {
        return TEXTURE;
    }
}
