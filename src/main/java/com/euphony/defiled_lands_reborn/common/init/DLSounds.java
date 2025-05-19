package com.euphony.defiled_lands_reborn.common.init;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DLSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, DefiledLandsReborn.MOD_ID);
    public static DeferredHolder<SoundEvent, SoundEvent> wyrmIdle, wyrmHurt, wyrmDeath, wyrmBook, wyrmIndigestion, mutagen;

    static {
        wyrmIdle = initSound("entity.bookwyrm.idle");
        wyrmHurt = initSound("entity.bookwyrm.hurt");
        wyrmDeath = initSound("entity.bookwyrm.death");
        wyrmBook = initSound("entity.bookwyrm.book");
        wyrmIndigestion = initSound("entity.bookwyrm.indigestion");
        mutagen = initSound("item.wyrmutagen.use");
    }

    public static DeferredHolder<SoundEvent, SoundEvent> initSound(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(Utils.prefix(name)));
    }
}
