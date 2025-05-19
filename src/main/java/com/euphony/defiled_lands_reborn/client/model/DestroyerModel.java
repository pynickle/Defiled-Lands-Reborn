package com.euphony.defiled_lands_reborn.client.model;

import com.euphony.defiled_lands_reborn.common.entity.boss.DestroyerBoss;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;

public class DestroyerModel<T extends DestroyerBoss> extends HumanoidModel<T> {
    public DestroyerModel(ModelPart root) {
        super(root);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        if (entity.isLeaping()) {
            rightLeg.xRot += (float) (Math.PI / 5);
            leftLeg.xRot += (float) (Math.PI / 5);
            rightArm.xRot = 3.7699115F;
            leftArm.xRot = 3.7699115F;
        }
    }
}
