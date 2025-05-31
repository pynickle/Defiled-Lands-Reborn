package com.euphony.defiled_lands_reborn.common.item.tool;//

import com.euphony.defiled_lands_reborn.common.tag.DLItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

public interface DLToolMaterials {
    ToolMaterial UMBRIUM = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 302, 6.0F, 2.0F, 14, DLItemTags.UMBRIUM_TOOL_MATERIALS);
    ToolMaterial SCARLITE = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 1561, 6.0F, 2.0F, 14, DLItemTags.SCARLITE_TOOL_MATERIALS);
    ToolMaterial SCARLITE_RAZOR = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 31, 6.0F, 2.0F, 14, DLItemTags.SCARLITE_RAZOR_TOOL_MATERIALS);
    ToolMaterial RAVAGING = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2107, 8.0F, 3.0F, 10, DLItemTags.RAVAGING_TOOL_MATERIALS);
}