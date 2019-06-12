package com.dunk.tfc.Render.Blocks;

import com.dunk.tfc.Blocks.Flora.BlockBranch;
import com.dunk.tfc.Blocks.Flora.BlockLogNatural;
import com.dunk.tfc.Render.RenderBlocksWithRotation;
import com.dunk.tfc.api.TFCBlocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class RenderBranch implements ISimpleBlockRenderingHandler
{

	private static float rot45 = (float) (-45d * (Math.PI / 180d));
	private static double sqrt2 = Math.sqrt(2);

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderblocks)
	{
		BlockBranch branch = (BlockBranch) block;
		renderblocks = new RenderBlocksWithRotation(renderblocks);

		// ((RenderBlocksWithRotation)renderblocks).yRotation =
		// (float)(((x%4)*90) * (Math.PI)/180f);
		// We're going to make this work.
		// The thinnest branch has 0.2 width
		// The medium has 0.4?
		// The thickest has 0.5?

		// This means the branch points down but not vertical
		float width = 0.2f;
		if (((BlockBranch) block).getSourceBlock(world, x, y, z) instanceof BlockLogNatural)
		{
			width += 0.1f;
		}
		if (((BlockBranch) block).isEnd())
		{
			width = 0.1f;
		}
		if (block == TFCBlocks.branchEnd__y_ && world.getBlockMetadata(x, y, z) == 15)
		{
			((RenderBlocksWithRotation) renderblocks).renderStandardBlock(TFCBlocks.fauxPalm, x, y, z);
		}

		if (block == TFCBlocks.branch2__yz || block == TFCBlocks.branch2_xy_ || block == TFCBlocks.branch2__yZ
				|| block == TFCBlocks.branch2_Xy_ || block == TFCBlocks.branch2_xyz || block == TFCBlocks.branch2_xyZ
				|| block == TFCBlocks.branch2_XyZ || block == TFCBlocks.branch2_Xyz || block == TFCBlocks.branch__yz
				|| block == TFCBlocks.branch_xy_ || block == TFCBlocks.branch__yZ || block == TFCBlocks.branch_Xy_
				|| block == TFCBlocks.branch_xyz || block == TFCBlocks.branch_xyZ || block == TFCBlocks.branch_XyZ
				|| block == TFCBlocks.branch_Xyz || block == TFCBlocks.branchEnd2__yz
				|| block == TFCBlocks.branchEnd2_xy_ || block == TFCBlocks.branchEnd2__yZ
				|| block == TFCBlocks.branchEnd2_Xy_ || block == TFCBlocks.branchEnd2_xyz
				|| block == TFCBlocks.branchEnd2_xyZ || block == TFCBlocks.branchEnd2_XyZ
				|| block == TFCBlocks.branchEnd2_Xyz || block == TFCBlocks.branchEnd__yz
				|| block == TFCBlocks.branchEnd_xy_ || block == TFCBlocks.branchEnd__yZ
				|| block == TFCBlocks.branchEnd_Xy_ || block == TFCBlocks.branchEnd_xyz
				|| block == TFCBlocks.branchEnd_xyZ || block == TFCBlocks.branchEnd_XyZ
				|| block == TFCBlocks.branchEnd_Xyz)
		{
			((RenderBlocksWithRotation) renderblocks).rotation = rot45;
			renderblocks.setRenderBounds(0.5 - width + 0.025, 0.5 - sqrt2, 0.5 - width + 0.025, 0.5 + width - 0.025,
					0.5, 0.5 + width - 0.025);
		}
		else if (block == TFCBlocks.branch2__Yz || block == TFCBlocks.branch2_xY_ || block == TFCBlocks.branch2__YZ
				|| block == TFCBlocks.branch2_XY_ || block == TFCBlocks.branch2_xYz || block == TFCBlocks.branch2_xYZ
				|| block == TFCBlocks.branch2_XYZ || block == TFCBlocks.branch2_XYz || block == TFCBlocks.branch__Yz
				|| block == TFCBlocks.branch_xY_ || block == TFCBlocks.branch__YZ || block == TFCBlocks.branch_XY_
				|| block == TFCBlocks.branch_xYz || block == TFCBlocks.branch_xYZ || block == TFCBlocks.branch_XYZ
				|| block == TFCBlocks.branch_XYz || block == TFCBlocks.branchEnd2__Yz
				|| block == TFCBlocks.branchEnd2_xY_ || block == TFCBlocks.branchEnd2__YZ
				|| block == TFCBlocks.branchEnd2_XY_ || block == TFCBlocks.branchEnd2_xYz
				|| block == TFCBlocks.branchEnd2_xYZ || block == TFCBlocks.branchEnd2_XYZ
				|| block == TFCBlocks.branchEnd2_XYz || block == TFCBlocks.branchEnd__Yz
				|| block == TFCBlocks.branchEnd_xY_ || block == TFCBlocks.branchEnd__YZ
				|| block == TFCBlocks.branchEnd_XY_ || block == TFCBlocks.branchEnd_xYz
				|| block == TFCBlocks.branchEnd_xYZ || block == TFCBlocks.branchEnd_XYZ
				|| block == TFCBlocks.branchEnd_XYz)
		{
			((RenderBlocksWithRotation) renderblocks).rotation = rot45;
			renderblocks.setRenderBounds(0.5 - width + 0.025, 0.5, 0.5 - width + 0.025, 0.5 + width - 0.025,
					0.5 + sqrt2, 0.5 + width - 0.025);
		}
		if (block == TFCBlocks.branch2___z || block == TFCBlocks.branch2_x__ || block == TFCBlocks.branch2___Z
				|| block == TFCBlocks.branch2_X__ || block == TFCBlocks.branch2_x_z || block == TFCBlocks.branch2_x_Z
				|| block == TFCBlocks.branch2_X_Z || block == TFCBlocks.branch2_X_z || block == TFCBlocks.branch___z
				|| block == TFCBlocks.branch_x__ || block == TFCBlocks.branch___Z || block == TFCBlocks.branch_X__
				|| block == TFCBlocks.branch_x_z || block == TFCBlocks.branch_x_Z || block == TFCBlocks.branch_X_Z
				|| block == TFCBlocks.branch_X_z || block == TFCBlocks.branchEnd2___z
				|| block == TFCBlocks.branchEnd2_x__ || block == TFCBlocks.branchEnd2___Z
				|| block == TFCBlocks.branchEnd2_X__ || block == TFCBlocks.branchEnd2_x_z
				|| block == TFCBlocks.branchEnd2_x_Z || block == TFCBlocks.branchEnd2_X_Z
				|| block == TFCBlocks.branchEnd2_X_z || block == TFCBlocks.branchEnd___z
				|| block == TFCBlocks.branchEnd_x__ || block == TFCBlocks.branchEnd___Z
				|| block == TFCBlocks.branchEnd_X__ || block == TFCBlocks.branchEnd_x_z
				|| block == TFCBlocks.branchEnd_x_Z || block == TFCBlocks.branchEnd_X_Z
				|| block == TFCBlocks.branchEnd_X_z)
		{
			((RenderBlocksWithRotation) renderblocks).rotation = (float) (-90 * (Math.PI) / 180f);
			renderblocks.setRenderBounds(0.5 - width, -0.5, 0.5 - width, 0.5 + width, 0.5, 0.5 + width);
		}
		if (block == TFCBlocks.branch2__y_ || block == TFCBlocks.branch__y_ || block == TFCBlocks.branchEnd2__y_
				|| block == TFCBlocks.branchEnd__y_)
		{
			((RenderBlocksWithRotation) renderblocks).rotation = 0;
			renderblocks.setRenderBounds(0.5 - width, -0.5, 0.5 - width, 0.5 + width, 0.5, 0.5 + width);
		}
		// +x-z
		if (block == TFCBlocks.branch2_X_z || block == TFCBlocks.branch2_Xyz || block == TFCBlocks.branch2_XYz
				|| block == TFCBlocks.branch_X_z || block == TFCBlocks.branch_Xyz || block == TFCBlocks.branch_XYz
				|| block == TFCBlocks.branchEnd2_X_z || block == TFCBlocks.branchEnd2_Xyz
				|| block == TFCBlocks.branchEnd2_XYz || block == TFCBlocks.branchEnd_X_z
				|| block == TFCBlocks.branchEnd_Xyz || block == TFCBlocks.branchEnd_XYz)
		{
			((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((135
					+ (((BlockBranch) block).getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
		}
		// -z
		if (block == TFCBlocks.branch2___z || block == TFCBlocks.branch2__yz || block == TFCBlocks.branch2__Yz
				|| block == TFCBlocks.branch___z || block == TFCBlocks.branch__yz || block == TFCBlocks.branch__Yz
				|| block == TFCBlocks.branchEnd2___z || block == TFCBlocks.branchEnd2__yz
				|| block == TFCBlocks.branchEnd2__Yz || block == TFCBlocks.branchEnd___z
				|| block == TFCBlocks.branchEnd__yz || block == TFCBlocks.branchEnd__Yz)
		{
			((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((180
					+ (((BlockBranch) block).getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
		}
		// -x-z
		if (block == TFCBlocks.branch2_x_z || block == TFCBlocks.branch2_xyz || block == TFCBlocks.branch2_xYz
				|| block == TFCBlocks.branch_x_z || block == TFCBlocks.branch_xyz || block == TFCBlocks.branch_xYz
				|| block == TFCBlocks.branchEnd2_x_z || block == TFCBlocks.branchEnd2_xyz
				|| block == TFCBlocks.branchEnd2_xYz || block == TFCBlocks.branchEnd_x_z
				|| block == TFCBlocks.branchEnd_xyz || block == TFCBlocks.branchEnd_xYz)
		{
			((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((225
					+ (((BlockBranch) block).getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
		}
		// +z
		if (block == TFCBlocks.branch2___Z || block == TFCBlocks.branch2__yZ || block == TFCBlocks.branch2__YZ
				|| block == TFCBlocks.branch___Z || block == TFCBlocks.branch__yZ || block == TFCBlocks.branch__YZ
				|| block == TFCBlocks.branchEnd2___Z || block == TFCBlocks.branchEnd2__yZ
				|| block == TFCBlocks.branchEnd2__YZ || block == TFCBlocks.branchEnd___Z
				|| block == TFCBlocks.branchEnd__yZ || block == TFCBlocks.branchEnd__YZ)
		{
			((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((0
					+ (((BlockBranch) block).getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
		}
		// -x+z
		if (block == TFCBlocks.branch2_x_Z || block == TFCBlocks.branch2_xyZ || block == TFCBlocks.branch2_xYZ
				|| block == TFCBlocks.branch_x_Z || block == TFCBlocks.branch_xyZ || block == TFCBlocks.branch_xYZ
				|| block == TFCBlocks.branchEnd2_x_Z || block == TFCBlocks.branchEnd2_xyZ
				|| block == TFCBlocks.branchEnd2_xYZ || block == TFCBlocks.branchEnd_x_Z
				|| block == TFCBlocks.branchEnd_xyZ || block == TFCBlocks.branchEnd_xYZ)
		{
			((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((315
					+ (((BlockBranch) block).getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
		}
		// +x+z
		if (block == TFCBlocks.branch2_X_Z || block == TFCBlocks.branch2_XyZ || block == TFCBlocks.branch2_XYZ
				|| block == TFCBlocks.branch_X_Z || block == TFCBlocks.branch_XyZ || block == TFCBlocks.branch_XYZ
				|| block == TFCBlocks.branchEnd2_X_Z || block == TFCBlocks.branchEnd2_XyZ
				|| block == TFCBlocks.branchEnd2_XYZ || block == TFCBlocks.branchEnd_X_Z
				|| block == TFCBlocks.branchEnd_XyZ || block == TFCBlocks.branchEnd_XYZ)
		{
			((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((45
					+ (((BlockBranch) block).getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
		}
		// +x
		if (block == TFCBlocks.branch2_X__ || block == TFCBlocks.branch2_Xy_ || block == TFCBlocks.branch2_XY_
				|| block == TFCBlocks.branch_X__ || block == TFCBlocks.branch_Xy_ || block == TFCBlocks.branch_XY_
				|| block == TFCBlocks.branchEnd2_X__ || block == TFCBlocks.branchEnd2_Xy_
				|| block == TFCBlocks.branchEnd2_XY_ || block == TFCBlocks.branchEnd_X__
				|| block == TFCBlocks.branchEnd_Xy_ || block == TFCBlocks.branchEnd_XY_)
		{
			((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((90
					+ (((BlockBranch) block).getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
		}
		// -x
		if (block == TFCBlocks.branch2_x__ || block == TFCBlocks.branch2_xy_ || block == TFCBlocks.branch2_xY_
				|| block == TFCBlocks.branch_x__ || block == TFCBlocks.branch_xy_ || block == TFCBlocks.branch_xY_
				|| block == TFCBlocks.branchEnd2_x__ || block == TFCBlocks.branchEnd2_xy_
				|| block == TFCBlocks.branchEnd2_xY_ || block == TFCBlocks.branchEnd_x__
				|| block == TFCBlocks.branchEnd_xy_ || block == TFCBlocks.branchEnd_xY_)
		{
			((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((270
					+ (((BlockBranch) block).getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
			;
		}

		renderblocks.renderStandardBlock(branch, x, y, z);
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
