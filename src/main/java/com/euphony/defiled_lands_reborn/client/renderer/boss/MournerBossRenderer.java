package com.euphony.defiled_lands_reborn.client.renderer.boss;

import com.euphony.defiled_lands_reborn.client.model.MournerModel;
import com.euphony.defiled_lands_reborn.client.state.MournerRenderState;
import com.euphony.defiled_lands_reborn.common.entity.boss.MournerBoss;
import com.euphony.defiled_lands_reborn.utils.Utils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Pose;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.common.NeoForge;

public class MournerBossRenderer extends MobRenderer<MournerBoss, MournerRenderState, MournerModel> {
    private static final ResourceLocation[] MOURNER_LOCATION = {
            Utils.prefix("textures/entity/the_mourner.png"),
            Utils.prefix("textures/entity/the_mourner_rage1.png"),
            Utils.prefix("textures/entity/the_mourner_rage2.png"),
    };
    private static final ResourceLocation MOURNER_EXPLOSION_LOCATION = Utils.prefix("textures/entity/the_mourner_explosion.png");

    public static final RenderType DECAL = RenderType.entityDecal(MOURNER_LOCATION[0]);
    public static final RenderType DECAL_DEATH = RenderType.entityDecal(MOURNER_LOCATION[2]);

    @Override
    protected void setupRotations(MournerRenderState renderState, PoseStack poseStack, float bodyRot, float scale) {
        if (renderState.isShaking || renderState.deathTime > 0) {
            bodyRot += (float)(Math.cos((double)renderState.ageInTicks * (double)3.25F) * Math.PI * (double)0.4F);
        }

        if (!renderState.hasPose(Pose.SLEEPING)) {
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - bodyRot));
        }

        if (renderState.isAutoSpinAttack) {
            poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F - renderState.xRot));
            poseStack.mulPose(Axis.YP.rotationDegrees(renderState.ageInTicks * -75.0F));
        } else if (renderState.hasPose(Pose.SLEEPING)) {
            Direction direction = renderState.bedOrientation;
            float f1 = direction != null ? sleepDirectionToRotation(direction) : bodyRot;
            poseStack.mulPose(Axis.YP.rotationDegrees(f1));
            poseStack.mulPose(Axis.ZP.rotationDegrees(this.getFlipDegrees()));
            poseStack.mulPose(Axis.YP.rotationDegrees(270.0F));
        } else if (renderState.isUpsideDown) {
            poseStack.translate(0.0F, (renderState.boundingBoxHeight + 0.1F) / scale, 0.0F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
        }
    }

    private static float sleepDirectionToRotation(Direction facing) {
        switch (facing) {
            case SOUTH -> {
                return 90.0F;
            }
            case WEST -> {
                return 0.0F;
            }
            case NORTH -> {
                return 270.0F;
            }
            case EAST -> {
                return 180.0F;
            }
            default -> {
                return 0.0F;
            }
        }
    }

    @Override
    public void render(MournerRenderState renderState, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (!((RenderLivingEvent.Pre<?, ?, ?>)NeoForge.EVENT_BUS.post(new RenderLivingEvent.Pre(renderState, this, renderState.partialTick, poseStack, buffer, packedLight))).isCanceled()) {
            poseStack.pushPose();
            if (renderState.hasPose(Pose.SLEEPING)) {
                Direction direction = renderState.bedOrientation;
                if (direction != null) {
                    float f = renderState.eyeHeight - 0.1F;
                    poseStack.translate((float)(-direction.getStepX()) * f, 0.0F, (float)(-direction.getStepZ()) * f);
                }
            }

            float f1 = renderState.scale;
            poseStack.scale(f1, f1, f1);
            this.setupRotations(renderState, poseStack, renderState.bodyRot, f1);
            poseStack.scale(-1.0F, -1.0F, 1.0F);
            this.scale(renderState, poseStack);
            poseStack.translate(0.0F, -1.501F, 0.0F);
            this.model.setupAnim(renderState);

            boolean flag1 = this.isBodyVisible(renderState);
            boolean flag = !flag1 && !renderState.isInvisibleToPlayer;
            RenderType rendertype = this.getRenderType(renderState, flag1, flag, renderState.appearsGlowing);
            if (rendertype != null) {
                if(renderState.deathTime > 0 || renderState.invulnerableTicks > 0) {
                    float progress;
                    if(renderState.deathTime > 0) {
                        progress = renderState.deathTime / 200.0F;
                    } else {
                        progress = renderState.invulnerableTicks / 200.0F;
                    }
                    int i = ARGB.color(Mth.floor(progress * 255.0F), -1);
                    VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.dragonExplosionAlpha(MOURNER_EXPLOSION_LOCATION));
                    this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, i);
                    VertexConsumer vertexconsumer1 = buffer.getBuffer(DECAL);
                    this.model.renderToBuffer(poseStack, vertexconsumer1, packedLight, OverlayTexture.pack(0.0F, renderState.deathTime > 0));
                } else {
                    VertexConsumer vertexconsumer2 = buffer.getBuffer(rendertype);
                    int i = getOverlayCoords(renderState, this.getWhiteOverlayProgress(renderState));
                    int j = flag ? 654311423 : -1;
                    int k = ARGB.multiply(j, this.getModelTint(renderState));
                    this.model.renderToBuffer(poseStack, vertexconsumer2, packedLight, i, k);
                }
            }

            if (this.shouldRenderLayers(renderState)) {
                for(RenderLayer<MournerRenderState, MournerModel> renderlayer : this.layers) {
                    renderlayer.render(poseStack, buffer, packedLight, renderState, renderState.yRot, renderState.xRot);
                }
            }

            poseStack.popPose();
            super.render(renderState, poseStack, buffer, packedLight);
            NeoForge.EVENT_BUS.post(new RenderLivingEvent.Post(renderState, this, renderState.partialTick, poseStack, buffer, packedLight));
        }
    }

    public MournerBossRenderer(EntityRendererProvider.Context context) {
        super(context, new MournerModel(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
    }

    @Override
    public MournerRenderState createRenderState() {
        return new MournerRenderState();
    }

    @Override
    public void extractRenderState(MournerBoss mourner, MournerRenderState renderState, float p_361157_) {
        super.extractRenderState(mourner, renderState, p_361157_);
        renderState.isShaking = mourner.deathTime > 0;
        renderState.isFiring = mourner.isFiring();

        renderState.rageFactor = mourner.getRageFactor();
        renderState.currentAttack = mourner.getCurrentAttack();

        renderState.deathTime = mourner.deathTime;
        renderState.invulnerableTicks = mourner.getInvulTime();
    }

    @Override
    public ResourceLocation getTextureLocation(MournerRenderState renderState) {
        return MOURNER_LOCATION[renderState.rageFactor - 1];
    }
}
