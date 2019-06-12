package com.dunk.tfc.Items;

import com.dunk.tfc.Core.TFCTabs;
import com.dunk.tfc.Render.Item.LogItemRenderer;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.Constant.Global;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ItemThickLogs extends ItemTerra {

	public ItemThickLogs()
	{
		super();
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(TFCTabs.TFC_MATERIALS);
		this.metaNames = Global.WOOD_ALL.clone();
		this.setWeight(EnumWeight.HEAVY);
		this.setSize(EnumSize.MEDIUM);
	}
	
	@Override
	public void registerIcons(IIconRegister registerer)
	{
		super.registerIcons(registerer);
		MinecraftForgeClient.registerItemRenderer(this, new LogItemRenderer());
	}
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{			
				int m = itemstack.getItemDamage();
				Block block = m>15?TFCBlocks.woodVert2:TFCBlocks.woodVert;

				if(side == 0 && block.canPlaceBlockAt(world, x, y-1, z) && world.canPlaceEntityOnSide(TFCBlocks.woodVert, x, y-1, z, false, side, null, itemstack))
				{
					world.setBlock(x, y-1, z, block, m,0x2);
					itemstack.stackSize = itemstack.stackSize-1;
					playSound(world, x, y, z);
				}
				else if(side == 1 && block.canPlaceBlockAt(world, x, y+1, z) && world.canPlaceEntityOnSide(TFCBlocks.woodVert, x, y+1, z, false, side, null, itemstack))
				{
					world.setBlock(x, y+1, z, block, m,0x2);
					itemstack.stackSize = itemstack.stackSize-1;
					playSound(world, x, y, z);
				}
				else if(side == 2 && block.canPlaceBlockAt(world, x, y, z-1) && world.canPlaceEntityOnSide(TFCBlocks.woodVert, x, y, z-1, false, side, null, itemstack))
				{
					setSide(world, itemstack, m, side, x, y, z-1);
				}
				else if(side == 3 && block.canPlaceBlockAt(world, x, y, z+1) && world.canPlaceEntityOnSide(TFCBlocks.woodVert, x, y, z+1, false, side, null, itemstack))
				{
					setSide(world, itemstack, m, side, x, y, z+1);
				}
				else if(side == 4 && block.canPlaceBlockAt(world, x-1, y, z) && world.canPlaceEntityOnSide(TFCBlocks.woodVert, x-1, y, z, false, side, null, itemstack))
				{
					setSide(world, itemstack, m, side, x-1, y, z);
				}
				else if(side == 5 && block.canPlaceBlockAt(world, x+1, y, z) && world.canPlaceEntityOnSide(TFCBlocks.woodVert, x+1, y, z, false, side, null, itemstack))
				{
					setSide(world, itemstack, m, side, x+1, y, z);
				}
				return true;
		}
		return false;
	}

	public void setSide(World world, ItemStack itemstack, int m, int side, int x, int y, int z)
	{
		// don't call this function with side==0 or side==1, it won't do anything

		int meta = m % 8;
		Block log = TFCBlocks.woodHoriz;
		switch (m/8) {
		case 1:
			log = TFCBlocks.woodHoriz2;
			break;
		case 2:
			log = TFCBlocks.woodHoriz3;
			break;
		case 3:
			//log = TFCBlocks.WoodHoriz4;
			break;
		}

		if (side == 2 || side == 3) {
			world.setBlock(x, y, z, log, meta, 0x2);
			itemstack.stackSize = itemstack.stackSize-1;
			playSound(world, x, y, z);
		}
		else if (side == 4 || side == 5) {
			world.setBlock(x, y, z, log, meta | 8, 0x2);
			itemstack.stackSize = itemstack.stackSize-1;
			playSound(world, x, y, z);
		}
	}
	
	private void playSound(World world, int x, int y, int z)
	{
		world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, TFCBlocks.logNatural.stepSound.func_150496_b(), (TFCBlocks.logNatural.stepSound.getVolume() + 1.0F) / 2.0F, TFCBlocks.logNatural.stepSound.getPitch() * 0.8F);
	}
}
