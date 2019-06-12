package com.dunk.tfc.Render;

import java.io.IOException;

import com.dunk.tfc.Core.ColorizerGrassTFC;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GrassColorReloadListener implements IResourceManagerReloadListener
{
	private static final ResourceLocation TEXTURE = new ResourceLocation("textures/colormap/grass.png");

	@Override
	public void onResourceManagerReload(IResourceManager par1ResourceManager)
	{
		try
		{
			ColorizerGrassTFC.setGrassBiomeColorizer(TextureUtil.readImageData(par1ResourceManager, TEXTURE));
		}
		catch (IOException ioexception)
		{
		}
	}
}
