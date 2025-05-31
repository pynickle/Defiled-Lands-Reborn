package com.euphony.defiled_lands_reborn.client.state;

import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class DestroyerRenderState extends HumanoidRenderState {
    public boolean isLeaping;
    public boolean isShaking;

    public float deathTime;
    public float invulnerableTicks;
}
