package com.dunk.tfc.Blocks.Flora;

import com.dunk.tfc.Reference;
import com.dunk.tfc.Blocks.BlockTerra;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockFauxPalm extends BlockTerra
{

	IIcon blockIcon2;
	IIcon blockIcon3;
	IIcon blockIcon4;
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return meta==0?this.blockIcon:meta==1?this.blockIcon2:meta==2?this.blockIcon3:this.blockIcon4;
	}

	@Override
	public void registerBlockIcons(IIconRegister reg)
	{
		this.blockIcon = reg.registerIcon(Reference.MOD_ID + ":" + "plants/frondTestA4");
		this.blockIcon2 = reg.registerIcon(Reference.MOD_ID + ":" + "plants/frondTestB4");
		this.blockIcon3 = reg.registerIcon(Reference.MOD_ID + ":" + "plants/frondTestDarkA");
		this.blockIcon4 = reg.registerIcon(Reference.MOD_ID + ":" + "plants/frondTestDarkB");
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return true;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
		return true;
	}
}
