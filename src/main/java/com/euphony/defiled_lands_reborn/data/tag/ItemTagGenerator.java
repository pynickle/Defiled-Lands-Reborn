package com.euphony.defiled_lands_reborn.data.tag;

import com.euphony.defiled_lands_reborn.DefiledLandsReborn;
import com.euphony.defiled_lands_reborn.common.init.DLItems;
import com.euphony.defiled_lands_reborn.common.tag.DLItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {
    public ItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, DefiledLandsReborn.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(DLItemTags.FOOD_POISONING).add(DLItems.SCUTTLER_EYE.get(), DLItems.BLACK_HEART.get(), DLItems.RAW_BOOK_WYRM.get(),
                DLItems.FOUL_CANDY.get());
        tag(DLItemTags.FOODS).add(DLItems.COOKED_BOOK_WYRM.get());
        tag(ItemTags.MEAT).add(DLItems.COOKED_BOOK_WYRM.get(), DLItems.RAW_BOOK_WYRM.get());

        tag(ItemTags.AXES).add(DLItems.RAVAGING_AXE.get(), DLItems.UMBRIUM_AXE.get());
        tag(ItemTags.SHOVELS).add(DLItems.RAVAGING_SHOVEL.get(), DLItems.UMBRIUM_SHOVEL.get());
        tag(ItemTags.PICKAXES).add(DLItems.RAVAGING_PICKAXE.get(), DLItems.UMBRIUM_PICKAXE.get());
        tag(ItemTags.HOES).add(DLItems.UMBRIUM_HOE.get());
        tag(ItemTags.SWORDS).add(DLItems.UMBRIUM_SWORD.get(), DLItems.SCARLITE_RAZOR.get(), DLItems.SCARLITE_REAVER.get());

        tag(ItemTags.HEAD_ARMOR).add(DLItems.UMBRIUM_HELMET.get(), DLItems.BOOK_WYRM_SCALE_HELMET.get(), DLItems.GOLDEN_BOOK_WYRM_SCALE_HELMET.get());
        tag(ItemTags.CHEST_ARMOR).add(DLItems.UMBRIUM_CHESTPLATE.get(), DLItems.BOOK_WYRM_SCALE_CHESTPLATE.get(), DLItems.GOLDEN_BOOK_WYRM_SCALE_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR).add(DLItems.UMBRIUM_LEGGINGS.get(), DLItems.BOOK_WYRM_SCALE_LEGGINGS.get(), DLItems.BOOK_WYRM_SCALE_BOOTS.get());
        tag(ItemTags.FOOT_ARMOR).add(DLItems.UMBRIUM_BOOTS.get(), DLItems.BOOK_WYRM_SCALE_BOOTS.get(), DLItems.GOLDEN_BOOK_WYRM_SCALE_BOOTS.get());

        tag(DLItemTags.BLASTER_AMMO).add(DLItems.BLASTEM_FRUIT.get(), DLItems.BLAZING_BLASTEM_FRUIT.get());
        tag(DLItemTags.BLASTER_ENCHANTABLE).add(DLItems.UMBRA_BLASTER.get());
        tag(DLItemTags.DESTRUCTIVE_AVAILABLE).add(DLItems.UMBRA_BLASTER.get(), DLItems.CONCUSSION_SMASHER.get());
        tag(DLItemTags.GUN_ENCHANTABLE).add(DLItems.THE_RAVAGER.get(), DLItems.UMBRA_BLASTER.get());
        tag(DLItemTags.RAVAGER_PELLET).add(DLItems.RAVAGING_PELLET.get(), DLItems.SPIKED_PELLET.get(), DLItems.UMBRIUM_PELLET.get());
    }
}
