package com.dunk.tfc.Blocks;

import java.util.List;
import java.util.Random;

import com.dunk.tfc.Reference;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.Constant.Global;

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

public class BlockPlasterPlanks extends BlockTerra
{
	protected String[] woodNames;
	protected IIcon[] icons;
	protected Block replacedBlock;

	public BlockPlasterPlanks(Material material)
	{
		super(Material.rock);
		//this.setCreativeTab(TFCTabs.TFC_BUILDING);
		woodNames = new String[16];
		System.arraycopy(Global.WOOD_ALL, 0, woodNames, 0, 16);
		icons = new IIcon[1];
		replacedBlock = TFCBlocks.planks;
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
		return icons[0];
	}
	
	/*@Override
	public Item getItemDropped(int metadata, Random rand, int fortune)
	{
		return replacedBlock.getItemDropped(metadata, rand, fortune);
	}*/
	
	@Override
	public int quantityDropped(Random rand)
	{
		return 0;
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		super.harvestBlock(world, player, x, y, z, meta);
		world.setBlock(x, y, z, this.replacedBlock, meta, 0);
		TFC_Core.addPlayerExhaustion(player, 0.001f);
	}

	@Override
	public void registerBlockIcons(IIconRegister registerer)
	{
		icons[0] = registerer.registerIcon(Reference.MOD_ID + ":" + "Plaster");
		Blocks.planks.registerBlockIcons(registerer);
	}
}
