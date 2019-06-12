package com.dunk.tfc.Items.ItemBlocks;

import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.api.Constant.Global;

import net.minecraft.block.Block;

public class ItemStone extends ItemTerraBlock
{
	public ItemStone(Block b)
	{
		super(b);
		if(TFC_Core.isStoneIgEx(b)) metaNames = Global.STONE_IGEX;
		else if(TFC_Core.isStoneIgIn(b)) metaNames = Global.STONE_IGIN;
		else if(TFC_Core.isStoneSed(b)) metaNames = Global.STONE_SED;
		else if(TFC_Core.isStoneMM(b)) metaNames = Global.STONE_MM;
	}
}
