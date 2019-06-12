package com.dunk.tfc.Blocks;

import java.util.List;

import com.dunk.tfc.Reference;
import com.dunk.tfc.Core.TFCTabs;
import com.dunk.tfc.Items.Tools.ItemHammer;
import com.dunk.tfc.Items.Tools.ItemTrowel;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCItems;
import com.dunk.tfc.api.Constant.Global;
import com.dunk.tfc.api.Tools.IToolChisel;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockPlanks extends BlockTerra
{
	protected String[] woodNames;
	protected IIcon[] icons;
	protected int plasterDamage = 25;

	public BlockPlanks(Material material)
	{
		super(Material.wood);
		this.setCreativeTab(TFCTabs.TFC_BUILDING);
		woodNames = new String[16];
		System.arraycopy(Global.WOOD_ALL, 0, woodNames, 0, 16);
		icons = new IIcon[woodNames.length];
	}

	@SideOnly(Side.CLIENT)
	@Override
	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List list)
	{
		for(int i = 0; i < woodNames.length; i++)
			list.add(new ItemStack(this,1,i));
	}

	@Override
	public int damageDropped(int j) 
	{
		return j;
	}

	@Override
	public IIcon getIcon(int i, int j)
	{
		if(j < 0)
			return icons[0];
		if(j<icons.length)
			return icons[j];
		return TFCBlocks.planks2.getIcon(i, j-16);
	}

	@Override
	public void registerBlockIcons(IIconRegister registerer)
	{
		for(int i = 0; i < woodNames.length; i++)
			icons[i] = registerer.registerIcon(Reference.MOD_ID + ":" + "wood/"+woodNames[i]+" Plank");
		Blocks.planks.registerBlockIcons(registerer);
	}

	/**
	 * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float par7, float par8, float par9) 
	{
		boolean hasHammer = false;
		int plaster = -1;

		for(int i = 0; i < 9;i++)
		{
			if(entityplayer.inventory.mainInventory[i] != null && entityplayer.inventory.mainInventory[i].getItem() instanceof ItemHammer)
				hasHammer = true;
			if(entityplayer.inventory.mainInventory[i] != null && entityplayer.inventory.mainInventory[i].getItem().equals(TFCItems.woodenBucketPlaster)
					&& entityplayer.inventory.mainInventory[i].getItemDamage() < entityplayer.inventory.mainInventory[i].getMaxDamage())
				plaster = i;
		}
		if(!world.isRemote && entityplayer.getCurrentEquippedItem() != null && 
				entityplayer.getCurrentEquippedItem().getItem() instanceof IToolChisel && 
				hasHammer && ((IToolChisel)entityplayer.getCurrentEquippedItem().getItem()).canChisel(entityplayer, x, y, z))
		{
			Block block = world.getBlock(x, y, z);
			byte meta = (byte) world.getBlockMetadata(x, y, z);

			return ((IToolChisel)entityplayer.getCurrentEquippedItem().getItem()).onUsed(world, entityplayer, x, y, z, block, meta, side, par7, par8, par9);
		}
		else if(entityplayer.getCurrentEquippedItem() != null && 
				entityplayer.getCurrentEquippedItem().getItem() instanceof ItemTrowel && 
				plaster != -1 && entityplayer.inventory.mainInventory[plaster].getItemDamage() < entityplayer.inventory.mainInventory[plaster].getMaxDamage())
		{
			entityplayer.inventory.mainInventory[plaster].damageItem(plasterDamage, entityplayer);
			if(entityplayer.inventory.mainInventory[plaster].getItemDamage() > entityplayer.inventory.mainInventory[plaster].getMaxDamage()-plasterDamage)
			{
				entityplayer.inventory.mainInventory[plaster] = new ItemStack(TFCItems.woodenBucketEmpty,1);
			}
			if(!world.isRemote)
			{
				entityplayer.getCurrentEquippedItem().damageItem(1, entityplayer);
				boolean b = world.setBlock(x, y, z, world.getBlock(x, y, z) instanceof BlockPlanks2 ? TFCBlocks.plasterPlanks2 :TFCBlocks.plasterPlanks, world.getBlockMetadata(x, y, z), 2);
				return b;
			}
			return true;
		}
		return false;
	}

}
