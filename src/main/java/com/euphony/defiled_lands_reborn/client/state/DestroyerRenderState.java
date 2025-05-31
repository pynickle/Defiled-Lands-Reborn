package com.euphony.defiled_lands_reborn.client.state;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.WitherRenderState;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;

public class DestroyerRenderState extends HumanoidRenderState {
    public boolean isLeaping;
    public boolean isShaking;

    public float deathTime;
    public float invulnerableTicks;
}
