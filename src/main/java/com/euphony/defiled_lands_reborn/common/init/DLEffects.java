package com.euphony.defiled_lands_reborn.common.init;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.common.effect.BleedingEffect;
import com.euphony.defiled_lands_reborn.common.effect.GroundedEffect;
import com.euphony.defiled_lands_reborn.common.effect.VulnerabilityEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DLEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, DefiledLandsReborn.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> VULNERABILITY = EFFECTS.register("vulnerability", VulnerabilityEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> GROUNDED = EFFECTS.register("grounded", GroundedEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> BLEEDING = EFFECTS.register("bleeding", BleedingEffect::new);
}
