package com.dunk.tfc.GUI;

import com.dunk.tfc.Reference;
import com.dunk.tfc.Containers.ContainerNestBox;
import com.dunk.tfc.TileEntities.TENestBox;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiNestBox extends GuiContainerTFC
{
	public static ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, Reference.ASSET_PATH_GUI + "gui_nestbox.png");

	public GuiNestBox(InventoryPlayer inventoryplayer, TENestBox te, World world, int i, int j, int k)
	{
		super(new ContainerNestBox(inventoryplayer, te, world, i, j, k), 176, 85);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		this.drawGui(texture);
	}
}
