package com.euphony.defiled_lands_reborn.client.state;

import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class MournerRenderState extends HumanoidRenderState {
    public int rageFactor;
    public int currentAttack;

    public boolean isFiring;
    public boolean isShaking;

    public float deathTime;
    public float invulnerableTicks;
}
