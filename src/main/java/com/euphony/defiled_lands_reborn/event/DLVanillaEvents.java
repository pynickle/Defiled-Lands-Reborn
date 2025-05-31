package com.euphony.defiled_lands_reborn.event;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.common.entity.projectile.BlastemFruitProjectile;
import com.euphony.defiled_lands_reborn.common.init.DLBlocks;
import com.euphony.defiled_lands_reborn.common.init.DLEffects;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.common.tag.DLItemTags;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.ExplosionEvent;

import java.util.List;

import static com.euphony.defiled_lands_reborn.utils.ItemUtils.addTooltip;

@EventBusSubscriber(modid = DefiledLandsReborn.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class DLVanillaEvents {
    @SubscribeEvent
    public static void registerTooltip(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        List<Component> components = event.getToolTip();
        if(stack.is(DLItems.PHYTOPROSTASIA_AMULET)){
            addTooltip(components, "item.defiled_lands_reborn.phytoprostasia_amulet.tooltip");
        } else if(stack.is(DLItems.RAVAGING_PELLET)) {
            addTooltip(components, "item.defiled_lands_reborn.ravaging_pellet.tooltip");
        } else if(stack.is(DLItems.IDOL_SORROW)) {
            addTooltip(components, "item.defiled_lands_reborn.idol_sorrow.tooltip");
        } else if(stack.is(DLItems.SPIKED_PELLET)) {
            addTooltip(components, "item.defiled_lands_reborn.spiked_pellet.tooltip");
        } else if(stack.is(DLItems.CALLING_STONE)) {
            addTooltip(components, "item.defiled_lands_reborn.calling_stone.tooltip");
        } else if(stack.is(DLItems.RAVAGING_AXE)) {
            addTooltip(components, "item.defiled_lands_reborn.ravaging_axe.tooltip");
        } else if(stack.is(DLItems.RAVAGING_PICKAXE)) {
            addTooltip(components, "item.defiled_lands_reborn.ravaging_pickaxe.tooltip");
        } else if(stack.is(DLItems.RAVAGING_SHOVEL)) {
            addTooltip(components, "item.defiled_lands_reborn.ravaging_shovel.tooltip");
        } else if(stack.is(DLBlocks.HEPHAESTITE_BLOCK.asItem())) {
            addTooltip(components, "block.defiled_lands_reborn.hephaestite_block.tooltip");
        } else if(stack.is(DLBlocks.HEPHAESTITE_ORE.asItem())) {
            addTooltip(components, "block.defiled_lands_reborn.hephaestite_ore.tooltip");
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onLivingDamage(LivingDamageEvent.Pre event) {
        LivingEntity entity = event.getEntity();
        MobEffectInstance effect = entity.getEffect(DLEffects.VULNERABILITY.getDelegate());

        if (effect != null) {
            float amplifier = effect.getAmplifier() + 1;
            float multiplier = 1 + (0.2F * amplifier);

            event.setNewDamage(event.getOriginalDamage() * multiplier);
        }
    }

    @SubscribeEvent
    public static void onExplosion(ExplosionEvent.Detonate event) {
        if (event.getExplosion().getDirectSourceEntity() instanceof BlastemFruitProjectile) {
            event.getAffectedEntities().removeIf(ent -> ent instanceof ItemEntity);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!event.getLevel().isClientSide() && event.getPlayer() != null) {
            ItemStack item = event.getPlayer().getMainHandItem();

            if (item.is(DLItemTags.RAVAGING_TOOLS)) {
                BlockState state = event.getState();
                event.setCanceled(true);
                state.getBlock().destroy(event.getLevel(), event.getPos(), state);
                event.getLevel().destroyBlock(event.getPos(), false);
            }
        }
    }
}
