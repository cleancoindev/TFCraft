package com.dunk.tfc.Items.ItemBlocks;

import com.dunk.tfc.TerraFirmaCraft;
import com.dunk.tfc.api.Constant.Global;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemPlasterWood2 extends ItemPlasterWood
{
	public ItemPlasterWood2(Block b)
	{
		super(b);
		metaNames = new String[Global.WOOD_ALL.length - 16];
		System.arraycopy(Global.WOOD_ALL, 16, metaNames, 0, Global.WOOD_ALL.length - 16);
	}

	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		try
		{
			int meta = is.getItemDamage();
			if(meta > 15) meta -= 16;
			if(metaNames != null && meta < metaNames.length)
				return getUnlocalizedName().concat("." + metaNames[meta]);
		}
		catch(Exception ex)
		{
			TerraFirmaCraft.LOG.error(ex.getLocalizedMessage());
		}
		return "Unknown";
	}
}