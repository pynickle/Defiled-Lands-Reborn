package com.euphony.defiled_lands_reborn.client.model;

import com.euphony.defiled_lands_reborn.client.state.DestroyerRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;

public class DestroyerModel extends HumanoidModel<DestroyerRenderState> {
    public DestroyerModel(ModelPart root) {
        super(root);
    }

    @Override
    public void setupAnim(DestroyerRenderState renderState) {
        super.setupAnim(renderState);

        if (renderState.isLeaping) {
            rightLeg.xRot += (float) (Math.PI / 5);
            leftLeg.xRot += (float) (Math.PI / 5);
            rightArm.xRot = 3.7699115F;
            leftArm.xRot = 3.7699115F;
        }
    }
}
