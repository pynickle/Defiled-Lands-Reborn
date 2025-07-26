package com.euphony.defiled_lands_reborn.integration.curios;

import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.utils.Utils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class CuriosCompat {
    public static void registerCapabilities(RegisterCapabilitiesEvent evt) {
        if(Utils.isModLoaded("curios")) {
            evt.registerItem(
                    CuriosCapability.ITEM,
                    (stack, context) -> new ICurio() {
                        @Override
                        public ItemStack getStack() {
                            return stack;
                        }

                        @Override
                        public void curioTick(SlotContext slotContext) {
                            Player player = (Player) slotContext.entity();
                            if (player.tickCount % 200 == 0) {
                                player.heal(1);
                            }
                        }
                    },
                    DLItems.SCARLITE_RING.get()
            );
            evt.registerItem(
                    CuriosCapability.ITEM,
                    (stack, context) -> () -> stack,
                    DLItems.PHYTOPROSTASIA_AMULET.get()
            );
        }
    }
}


