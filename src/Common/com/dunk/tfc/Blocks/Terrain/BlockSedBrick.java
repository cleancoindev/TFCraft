package com.dunk.tfc.Blocks.Terrain;

import com.dunk.tfc.Reference;

import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockSedBrick extends BlockSedSmooth
{
	public BlockSedBrick()
	{
		super();
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegisterer)
	{
		for(int i = 0; i < names.length; i++)
			icons[i] = iconRegisterer.registerIcon(Reference.MOD_ID + ":" + "rocks/"+names[i]+" Brick");
	}
}

