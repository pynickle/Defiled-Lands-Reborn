package com.euphony.defiled_lands_reborn.client.model;

import com.euphony.defiled_lands_reborn.common.entity.boss.MournerBoss;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.NotNull;

public class MournerModel<T extends MournerBoss> extends HumanoidModel<T> {
    public MournerModel(ModelPart root) {
        super(root);
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        if (entity.isFiring()) {
            switch (entity.getCurrentAttack()) {
                case MournerBoss.ATTACK_FIREBALLS:
                    rightArm.yRot = -0.1F + head.yRot;
                    rightArm.xRot = -(float)Math.PI / 2 + head.xRot;
                    break;
                case MournerBoss.ATTACK_SHULKER:
                    leftArm.yRot = 0.1F + head.yRot;
                    leftArm.xRot = -(float)Math.PI / 2 + head.xRot;
                    break;
                case MournerBoss.ATTACK_GHAST:
                    rightArm.xRot = (float)Math.PI;
                    leftArm.xRot = (float)Math.PI;
                    break;
            }
        }
    }
}
