package com.euphony.defiled_lands_reborn.client.renderer.boss;

import com.euphony.defiled_lands_reborn.client.model.DestroyerModel;
import com.euphony.defiled_lands_reborn.client.model.MournerModel;
import com.euphony.defiled_lands_reborn.common.entity.boss.DestroyerBoss;
import com.euphony.defiled_lands_reborn.common.entity.boss.MournerBoss;
import com.euphony.defiled_lands_reborn.utils.Utils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.common.NeoForge;

public class MournerBossRenderer extends MobRenderer<MournerBoss, MournerModel<MournerBoss>> {
    private static final ResourceLocation[] MOURNER_LOCATION = {
            Utils.prefix("textures/entity/the_mourner.png"),
            Utils.prefix("textures/entity/the_mourner_rage1.png"),
            Utils.prefix("textures/entity/the_mourner_rage2.png"),
    };
    private static final ResourceLocation MOURNER_EXPLOSION_LOCATION = Utils.prefix("textures/entity/the_mourner_explosion.png");

    public static final RenderType DECAL = RenderType.entityDecal(MOURNER_LOCATION[0]);
    public static final RenderType DECAL_DEATH = RenderType.entityDecal(MOURNER_LOCATION[2]);

    @Override
    protected void setupRotations(MournerBoss entity, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
        if (this.isShaking(entity) || entity.deathTime > 0) {
            yBodyRot += (float)(Math.cos((double)entity.tickCount * (double)3.25F) * Math.PI * (double)0.4F);
        }

        if (!entity.hasPose(Pose.SLEEPING)) {
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - yBodyRot));
        }

        if (entity.isAutoSpinAttack()) {
            poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F - entity.getXRot()));
            poseStack.mulPose(Axis.YP.rotationDegrees(((float)entity.tickCount + partialTick) * -75.0F));
        } else if (entity.hasPose(Pose.SLEEPING)) {
            Direction direction = entity.getBedOrientation();
            float f1 = direction != null ? sleepDirectionToRotation(direction) : yBodyRot;
            poseStack.mulPose(Axis.YP.rotationDegrees(f1));
            poseStack.mulPose(Axis.ZP.rotationDegrees(this.getFlipDegrees(entity)));
            poseStack.mulPose(Axis.YP.rotationDegrees(270.0F));
        } else if (isEntityUpsideDown(entity)) {
            poseStack.translate(0.0F, (entity.getBbHeight() + 0.1F) / scale, 0.0F);
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
    public void render(MournerBoss entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.deathTime > 0 || entity.getInvulTime() > 0) {
            if (!((RenderLivingEvent.Pre<?, ?>) NeoForge.EVENT_BUS.post(new RenderLivingEvent.Pre(entity, this, partialTicks, poseStack, buffer, packedLight))).isCanceled()) {
                poseStack.pushPose();
                this.model.attackTime = this.getAttackAnim(entity, partialTicks);
                boolean shouldSit = entity.isPassenger() && entity.getVehicle() != null && entity.getVehicle().shouldRiderSit();
                this.model.riding = shouldSit;
                this.model.young = entity.isBaby();
                float f = Mth.rotLerp(partialTicks, entity.yBodyRotO, entity.yBodyRot);
                float f1 = Mth.rotLerp(partialTicks, entity.yHeadRotO, entity.yHeadRot);
                float f2 = f1 - f;
                if (shouldSit) {
                    Entity var12 = entity.getVehicle();
                    if (var12 instanceof LivingEntity) {
                        LivingEntity livingentity = (LivingEntity)var12;
                        f = Mth.rotLerp(partialTicks, livingentity.yBodyRotO, livingentity.yBodyRot);
                        f2 = f1 - f;
                        float f7 = Mth.wrapDegrees(f2);
                        if (f7 < -85.0F) {
                            f7 = -85.0F;
                        }

                        if (f7 >= 85.0F) {
                            f7 = 85.0F;
                        }

                        f = f1 - f7;
                        if (f7 * f7 > 2500.0F) {
                            f += f7 * 0.2F;
                        }

                        f2 = f1 - f;
                    }
                }

                float f6 = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
                if (isEntityUpsideDown(entity)) {
                    f6 *= -1.0F;
                    f2 *= -1.0F;
                }

                f2 = Mth.wrapDegrees(f2);
                if (entity.hasPose(Pose.SLEEPING)) {
                    Direction direction = entity.getBedOrientation();
                    if (direction != null) {
                        float f3 = entity.getEyeHeight(Pose.STANDING) - 0.1F;
                        poseStack.translate((float)(-direction.getStepX()) * f3, 0.0F, (float)(-direction.getStepZ()) * f3);
                    }
                }

                float f8 = entity.getScale();
                poseStack.scale(f8, f8, f8);
                float f9 = this.getBob(entity, partialTicks);
                this.setupRotations(entity, poseStack, f9, f, partialTicks, f8);
                poseStack.scale(-1.0F, -1.0F, 1.0F);
                this.scale(entity, poseStack, partialTicks);
                poseStack.translate(0.0F, -1.501F, 0.0F);
                float f4 = 0.0F;
                float f5 = 0.0F;
                if (!shouldSit && entity.isAlive()) {
                    f4 = entity.walkAnimation.speed(partialTicks);
                    f5 = entity.walkAnimation.position(partialTicks);
                    if (entity.isBaby()) {
                        f5 *= 3.0F;
                    }

                    if (f4 > 1.0F) {
                        f4 = 1.0F;
                    }
                }

                this.model.prepareMobModel(entity, f5, f4, partialTicks);
                this.model.setupAnim(entity, f5, f4, f9, f2, f6);

                float progress;
                if(entity.deathTime > 0) {
                    progress = (float) entity.deathTime / 200.0F;
                } else {
                    progress = entity.getInvulTime() / 200.0F;
                }
                int i = FastColor.ARGB32.color(Mth.floor(progress * 255.0F), -1);
                VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.dragonExplosionAlpha(MOURNER_EXPLOSION_LOCATION));
                this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, i);

                Minecraft minecraft = Minecraft.getInstance();
                boolean flag = this.isBodyVisible(entity);
                boolean flag1 = !flag && !entity.isInvisibleTo(minecraft.player);
                boolean flag2 = minecraft.shouldEntityAppearGlowing(entity);
                RenderType rendertype = this.getRenderType(entity, flag, flag1, flag2);
                if (rendertype != null) {
                    VertexConsumer vertexconsumer1;
                    if(entity.deathTime > 0) {
                        vertexconsumer1 = buffer.getBuffer(DECAL_DEATH);
                    } else {
                        vertexconsumer1 = buffer.getBuffer(DECAL);
                    }
                    this.model.renderToBuffer(poseStack, vertexconsumer1, packedLight, OverlayTexture.pack(0.0F, entity.deathTime > 0));
                }

                if (!entity.isSpectator()) {
                    for(RenderLayer<MournerBoss, MournerModel<MournerBoss>> renderlayer : this.layers) {
                        renderlayer.render(poseStack, buffer, packedLight, entity, f5, f4, partialTicks, f9, f2, f6);
                    }
                }

                poseStack.popPose();
                NeoForge.EVENT_BUS.post(new RenderLivingEvent.Post(entity, this, partialTicks, poseStack, buffer, packedLight));
            }
        } else {
            super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        }
    }

    public MournerBossRenderer(EntityRendererProvider.Context context) {
        super(context, new MournerModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(MournerBoss mournerBoss) {
        return MOURNER_LOCATION[mournerBoss.getRageFactor() - 1];
    }
}
