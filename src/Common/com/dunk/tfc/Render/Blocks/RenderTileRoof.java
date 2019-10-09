package com.dunk.tfc.Render.Blocks;

import org.lwjgl.opengl.GL11;

import com.dunk.tfc.Blocks.BlockMudBricks;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.Render.RenderBlocksWithRotation;
import com.dunk.tfc.api.TFCBlocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;

public class RenderTileRoof implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		if (metadata == 0)
		{
			// TODO Auto-generated method stub
			renderer.setRenderBounds(0F, 0F, 0.4F, 1F, 1F, 0.6F);
			// GL11.glRotatef(-30, 0, 1, 0);
			GL11.glRotatef(-45, 1, 0, 0);
			// rotate(renderer, 1);
			renderer.overrideBlockTexture = block.getIcon(block==TFCBlocks.tileRoof?123:124, 0);
			renderInvBlock(block, 0, renderer);
			GL11.glRotatef(45, 1, 0, 0);
			renderer.clearOverrideBlockTexture();
			// GL11.glRotatef(30, 0, 1, 0);
			// rotate(renderer, 0);
		}
		else if (metadata == 4)
		{
			// TODO Auto-generated method stub
			GL11.glRotatef(90, 0, 1, 0);
			renderer.setRenderBounds(0F, 0F, 0.4F, 1F, 0.5F, 0.6F);
			// GL11.glRotatef(-30, 0, 1, 0);

			GL11.glRotatef(-45, 1, 0, 0);
			// rotate(renderer, 1);
			renderer.overrideBlockTexture = block.getIcon(123, 0);
			renderInvBlock(block, 0, renderer);
			GL11.glRotatef(45, 1, 0, 0);
			renderer.clearOverrideBlockTexture();
			// GL11.glRotatef(30, 0, 1, 0);
			// rotate(renderer, 0);

			// TODO Auto-generated method stub
			renderer.setRenderBounds(0F, 0F, 0.4F, 1F, 0.5F, 0.6F);
			// GL11.glRotatef(-30, 0, 1, 0);
			GL11.glRotatef(180, 0, 1, 0);
			GL11.glRotatef(-45, 1, 0, 0);
			// rotate(renderer, 1);
			renderer.overrideBlockTexture = block.getIcon(123, 0);
			renderInvBlock(block, 0, renderer);
			GL11.glRotatef(45, 1, 0, 0);
			GL11.glRotatef(180, 0, 1, 0);
			renderer.clearOverrideBlockTexture();

			renderer.setRenderBounds(0F, 0.5F, 0.4F, 1F, 0.6F, 0.6F);
			// GL11.glRotatef(-30, 0, 1, 0);

			GL11.glRotatef(-45, 1, 0, 0);
			// rotate(renderer, 1);
			renderer.overrideBlockTexture = block.getIcon(123, 0);
			renderInvBlock(block, 0, renderer);
			GL11.glRotatef(45, 1, 0, 0);

			renderer.clearOverrideBlockTexture();

			GL11.glRotatef(-90, 0, 1, 0);
			// GL11.glRotatef(30, 0, 1, 0);
			// rotate(renderer, 0);
		}
	}

	public static void renderInvBlock(Block block, int m, RenderBlocks renderer)
	{

		Tessellator var14 = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		var14.startDrawingQuads();
		var14.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, m));
		var14.draw();
		var14.startDrawingQuads();
		var14.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, m));
		var14.draw();
		var14.startDrawingQuads();
		var14.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, m));
		var14.draw();
		var14.startDrawingQuads();
		var14.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, m));
		var14.draw();
		var14.startDrawingQuads();
		var14.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, m));
		var14.draw();
		var14.startDrawingQuads();
		var14.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, m));
		var14.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer)
	{
		double tileWidth, tileHeight, tileLength;
		tileWidth = tileHeight = tileLength = 1d;
		// tileLength = 0.3;
		// tileHeight = 0.5;
		double tileHeightOffset = 1;// 0.1;
		boolean centerPiece = false;
		boolean centerSlopeRight = false;
		boolean centerSlopeLeft = false;
		int meta = world.getBlockMetadata(x, y, z);
		if (block == TFCBlocks.tileRoof)
		{
			if (meta == 4 || meta == 5)
			{
				centerPiece = true;
			}
			for (int center = 0; center < (centerPiece ? 2 : 1); center++)
			{
				if (center > 0 && !centerPiece)
				{
					// meta = (meta + 2) % 4;
				}
				else if (center > 0 && centerPiece)
				{
					meta = meta + 2;
				}
				boolean corner = false;
				boolean outCorner = false;
				renderer = new RenderBlocksWithRotation(renderer);
				renderer.renderAllFaces = true;

				boolean renderSolidRoof = false;
				((RenderBlocksWithRotation) renderer).yRotation = (float) (meta % 4) * (float) Math.PI / 2f;
				((RenderBlocksWithRotation) renderer).staticTexture = true;
				if (renderSolidRoof)
				{

					((RenderBlocksWithRotation) renderer).renderDiagonal = 1;
					((RenderBlocksWithRotation) renderer).yRotation = 0;
					((RenderBlocksWithRotation) renderer).rotation = -((RenderBlocksWithRotation) renderer).rot45;
					renderer.setRenderBounds(0, 0 - 0.2, 0 + 0.5, 1, 1.414 - 0.2, 0.3 + 0.5);
					renderer.renderStandardBlock(block, x, y, z);
					((RenderBlocksWithRotation) renderer).renderDiagonal = 3;
					((RenderBlocksWithRotation) renderer).yRotation = (float) Math.PI / 2f;
					// renderer.setRenderBounds(0, 0, 0, 1, 1, 0.3);
					renderer.renderStandardBlock(block, x, y, z);
					((RenderBlocksWithRotation) renderer).renderDiagonal = -1;
					if (1 == 1)
					{
						return true;
					}
				}

				renderer.renderAllFaces = true;
				((RenderBlocksWithRotation) renderer).rotation = ((RenderBlocksWithRotation) renderer).rot45 - (4f * ((RenderBlocksWithRotation) renderer).rot1);

				Vec3 xV = Vec3.createVectorHelper(1, 0, 0);
				Vec3 zV = Vec3.createVectorHelper(0, 0, 1);

				xV.rotateAroundY(((RenderBlocksWithRotation) renderer).yRotation);
				zV.rotateAroundY(((RenderBlocksWithRotation) renderer).yRotation);
				xV.xCoord = Math.round(xV.xCoord);
				xV.yCoord = Math.round(xV.yCoord);
				xV.zCoord = Math.round(xV.zCoord);
				zV.xCoord = Math.round(zV.xCoord);
				zV.yCoord = Math.round(zV.yCoord);
				zV.zCoord = Math.round(zV.zCoord);

				Block blockFront = world.getBlock(x + (int) zV.xCoord, y, z + (int) zV.zCoord);
				Block blockBack = world.getBlock(x - (int) zV.xCoord, y, z - (int) zV.zCoord);
				Block blockFrontLeft = world.getBlock(x + (int) zV.xCoord + (int) xV.xCoord, y,
						z + (int) zV.zCoord + (int) xV.zCoord);
				Block blockFrontRight = world.getBlock(x - (int) xV.xCoord + (int) zV.xCoord, y,
						z - (int) xV.zCoord + (int) zV.zCoord);
				Block blockUpFront = world.getBlock(x + (int) zV.xCoord, y + 1, z + (int) zV.zCoord);
				Block blockDownBack = world.getBlock(x - (int) zV.xCoord, y - 1, z - (int) zV.zCoord);
				Block blockDownRight = world.getBlock(x - (int) xV.xCoord, y - 1, z - (int) xV.zCoord);
				Block blockDownLeft = world.getBlock(x + (int) xV.xCoord, y - 1, z + (int) xV.zCoord);
				Block blockLeft = world.getBlock(x + (int) xV.xCoord, y, z + (int) xV.zCoord);
				Block blockRight = world.getBlock(x - (int) xV.xCoord, y, z - (int) xV.zCoord);
				Block blockBackLeft = world.getBlock(x + (int) xV.xCoord - (int) zV.xCoord, y,
						z + (int) xV.zCoord - (int) zV.zCoord);

				int blockFrontMeta, blockBackMeta, blockUpFrontMeta, blockDownBackMeta, blockLeftMeta, blockRightMeta;
				int blockFrontLeftMeta, blockFrontRightMeta, blockBackLeftMeta, blockDownRightMeta, blockDownLeftMeta;

				blockFrontMeta = world.getBlockMetadata(x + (int) zV.xCoord, y, z + (int) zV.zCoord);
				blockBackMeta = world.getBlockMetadata(x - (int) zV.xCoord, y, z - (int) zV.zCoord);
				blockFrontLeftMeta = world.getBlockMetadata(x + (int) zV.xCoord + (int) xV.xCoord, y,
						z + (int) zV.zCoord + (int) xV.zCoord);
				blockFrontRightMeta = world.getBlockMetadata(x - (int) xV.xCoord + (int) zV.xCoord, y,
						z - (int) xV.zCoord + (int) zV.zCoord);
				blockUpFrontMeta = world.getBlockMetadata(x + (int) zV.xCoord, y + 1, z + (int) zV.zCoord);
				blockDownBackMeta = world.getBlockMetadata(x - (int) zV.xCoord, y - 1, z - (int) zV.zCoord);
				blockLeftMeta = world.getBlockMetadata(x + (int) xV.xCoord, y, z + (int) xV.zCoord);
				blockRightMeta = world.getBlockMetadata(x - (int) xV.xCoord, y, z - (int) xV.zCoord);
				blockBackLeftMeta = world.getBlockMetadata(x + (int) xV.xCoord - (int) zV.xCoord, y,
						z + (int) xV.zCoord - (int) zV.zCoord);
				blockDownRightMeta = world.getBlockMetadata(x - (int) xV.xCoord, y - 1, z - (int) xV.zCoord);
				blockDownLeftMeta = world.getBlockMetadata(x + (int) xV.xCoord, y - 1, z + (int) xV.zCoord);
				centerSlopeRight = false;
				centerSlopeLeft = false;
				if (centerPiece)
				{
					if (blockDownRight == block && blockDownRightMeta == ((meta % 4) + 1) % 4)
					{
						if (meta % 4 < 2)
						{
							centerSlopeRight = true;
						}
						else
						{
							centerSlopeLeft = true;
						}
					}
					if (blockDownLeft == block && blockDownLeftMeta == ((meta % 4) + 3) % 4)
					{
						if (meta % 4 < 2)
						{
							centerSlopeLeft = true;
						}
						else
						{
							centerSlopeRight = true;
						}
					}
				}

				float yRotChange = 0;
				int orMeta = world.getBlockMetadata(x, y, z);
				if (meta < 2 && !centerPiece && ((blockLeft == block && blockLeftMeta == meta && blockBack == block && blockBackLeft == block && blockFront == block && Math
						.abs(blockBackLeftMeta - blockBackMeta) == 2) || (blockLeft == block && blockBackLeftMeta == meta && blockBack == block && blockBackLeft == block && blockFront == block && Math
								.abs(blockLeftMeta - blockBackMeta) == 2) || (blockLeft == block && blockFrontLeft == block && (blockFrontMeta + 1) % 4 == meta && blockFront == block && Math
										.abs(blockFrontLeftMeta - meta) == 2) || (blockFront == block && Math
												.abs(blockFrontMeta - meta) == 2)))
				{
					((RenderBlocksWithRotation) renderer).yRotation += (float) Math.PI / 2f;
					float prevRot = ((RenderBlocksWithRotation) renderer).rotation;
					((RenderBlocksWithRotation) renderer).rotation = 4f * ((RenderBlocksWithRotation) renderer).rot1;
					if (blockLeft == block && blockFrontLeft == block && blockLeftMeta == meta && blockFrontLeftMeta == blockFrontMeta)
					{
						renderer.setRenderBounds(-0.125, 1.0625, 0, 0.125, 1.1875, 0.625);
					}
					else
					{
						renderer.setRenderBounds(-0.125, 1.0625, -0.125, 0.125, 1.1875, 0.625);
					}
					renderer.renderStandardBlock(block, x, y, z);
					if (blockRight == block && blockFrontRight == block && blockRightMeta == meta && blockFrontRightMeta == blockFrontMeta)
					{
						renderer.setRenderBounds(-0.126, 1.0625 - 0.025, 0.5, 0.124, 1.1875 - 0.025, 1.125);
					}
					else
					{
						renderer.setRenderBounds(-0.126, 1.0625 - 0.025, 0.5, 0.124, 1.1875 - 0.025, 1.25);
					}
					renderer.renderStandardBlock(block, x, y, z);
					((RenderBlocksWithRotation) renderer).rotation = prevRot;
					((RenderBlocksWithRotation) renderer).yRotation -= (float) Math.PI / 2f;
				}
				else if (centerPiece && center == 0)
				{
					((RenderBlocksWithRotation) renderer).yRotation += (float) Math.PI / 2f;
					float prevRot = ((RenderBlocksWithRotation) renderer).rotation;
					((RenderBlocksWithRotation) renderer).rotation = 4f * ((RenderBlocksWithRotation) renderer).rot1;
					if (blockRight == block && blockRightMeta == orMeta && !centerSlopeRight)
					{
						renderer.setRenderBounds(0.5 - 0.125, 1.0625 - 0.5, 0, 0.5 + 0.125, 1.1875 - 0.5, 0.625);
						renderer.renderStandardBlock(block, x, y, z);
					}
					else if (blockRight == block && blockRightMeta > 3 && blockRightMeta != orMeta && !centerSlopeRight)
					{
						renderer.setRenderBounds(0.5 - 0.125, 1.0625 - 0.5, -0.4, 0.5 + 0.125, 1.1875 - 0.5, 0.625);
						renderer.renderStandardBlock(block, x, y, z);
					}
					else if (!centerSlopeRight)
					{
						renderer.setRenderBounds(0.5 - 0.125, 1.0625 - 0.5, -0.125, 0.5 + 0.125, 1.1875 - 0.5, 0.625);
						renderer.renderStandardBlock(block, x, y, z);
					}

					if (blockLeft == block && blockLeftMeta == orMeta && !centerSlopeLeft)
					{
						renderer.setRenderBounds(0.5 - 0.126, 1.0625 - 0.025 - 0.5, 0.5, 0.5 + 0.124,
								1.1875 - 0.025 - 0.5, 1.125);
						renderer.renderStandardBlock(block, x, y, z);
					}
					else if (blockLeft == block && blockLeftMeta > 3 && blockLeftMeta != orMeta && !centerSlopeLeft)
					{
						renderer.setRenderBounds(0.5 - 0.126, 1.0625 - 0.025 - 0.5, 0.5, 0.5 + 0.124,
								1.1875 - 0.025 - 0.5, 1.125 + 0.275);
						renderer.renderStandardBlock(block, x, y, z);
					}
					else if (!centerSlopeLeft)
					{
						renderer.setRenderBounds(0.5 - 0.126, 1.0625 - 0.025 - 0.5, 0.5, 0.5 + 0.124,
								1.1875 - 0.025 - 0.5, 1.25);
						renderer.renderStandardBlock(block, x, y, z);
					}
					// renderer.renderStandardBlock(block, x, y, z);
					((RenderBlocksWithRotation) renderer).rotation = prevRot;
					((RenderBlocksWithRotation) renderer).yRotation -= (float) Math.PI / 2f;
				}

				if (!centerPiece)
				{
					if (blockBackMeta == (meta + 3) % 4 && blockBack == block)
					{
						corner = true;
						outCorner = true;
					}
					else if (blockBackMeta == (meta + 1) % 4 && blockBack == block)
					{
						corner = true;
						outCorner = true;
						yRotChange = (float) Math.PI / 2f;
						((RenderBlocksWithRotation) renderer).yRotation += yRotChange;
					}
					else if (blockFrontMeta == (meta + 3) % 4 && blockFront == block && !(blockFrontRight == block && (blockFrontRightMeta == blockFrontMeta)) && !(blockFrontLeft == block && (blockFrontLeftMeta == blockFrontMeta || blockFrontLeftMeta == (meta + 2) % 4)))
					{
						corner = true;
					}
					else if (blockFrontMeta == (meta + 1) % 4 && blockFront == block && !(blockFrontRight == block && (blockFrontRightMeta == blockFrontMeta || blockFrontRightMeta == (meta + 2) % 4)) && !(blockFrontLeft == block && (blockFrontLeftMeta == blockFrontMeta)))
					{
						corner = true;
						yRotChange = (float) Math.PI / 2f;
						((RenderBlocksWithRotation) renderer).yRotation += yRotChange;
					}
				}
				else
				{
					if (blockBackMeta - 4 == ((meta - 4) + 1) % 2 && blockBack == block)
					{
						corner = true;
						outCorner = true;
						yRotChange = (float) Math.PI / 2f;
						((RenderBlocksWithRotation) renderer).yRotation += yRotChange;
					}
					// else if (blockFrontMeta -4 == ((meta-4) + 1)%2 &&
					// blockFront
					// == block )
					// {
					// corner = true;
					// outCorner = false;
					// }
				}

				if (((meta < 2) || (blockLeft == block && blockLeftMeta == meta && blockBack == block && blockBackLeft == block && Math
						.abs(blockBackLeftMeta - blockBackMeta) == 2)) && ((blockLeft == block && blockLeftMeta == meta && blockBack == block && blockBackLeft == block && Math
								.abs(blockBackLeftMeta - blockBackMeta) == 2) || (blockLeft == block && blockBackLeftMeta == meta && blockBack == block && blockBackLeft == block && Math
										.abs(blockLeftMeta - blockBackMeta) == 2) || (blockLeft == block && blockFrontLeft == block && (blockFrontMeta + 1) % 4 == meta && blockFront == block && Math
												.abs(blockFrontLeftMeta - meta) == 2) || (blockRight == block && blockFrontRight == block && (blockFrontMeta + 3) % 4 == meta && blockFront == block && Math
														.abs(blockFrontRightMeta - meta) == 2) || (blockFront == block && Math
																.abs(blockFrontMeta - meta) == 2)) && yRotChange != 0)
				{

					((RenderBlocksWithRotation) renderer).yRotation += (float) Math.PI / 2f;
					float prevRot = ((RenderBlocksWithRotation) renderer).rotation;
					((RenderBlocksWithRotation) renderer).rotation = 4f * ((RenderBlocksWithRotation) renderer).rot1;
					if (blockLeft == block && blockFrontLeft == block && blockLeftMeta == meta && blockFrontLeftMeta == blockFrontMeta)
					{
						renderer.setRenderBounds(-0.125, 1.0625, 0, 0.125, 1.1875, 0.625);
					}
					else
					{
						renderer.setRenderBounds(-0.125, 1.0625, -0.125, 0.125, 1.1875, 0.625);
					}
					renderer.renderStandardBlock(block, x, y, z);
					if (blockRight == block && blockFrontRight == block && blockRightMeta == meta && blockFrontRightMeta == blockFrontMeta)
					{
						renderer.setRenderBounds(-0.126, 1.0625 - 0.025, 0.5, 0.124, 1.1875 - 0.025, 1.125);
					}
					else
					{
						renderer.setRenderBounds(-0.126, 1.0625 - 0.025, 0.5, 0.124, 1.1875 - 0.025, 1.25);
					}
					renderer.renderStandardBlock(block, x, y, z);
					((RenderBlocksWithRotation) renderer).rotation = prevRot;
					((RenderBlocksWithRotation) renderer).yRotation -= (float) Math.PI / 2f;
				}

				// This means that we draw a line from the bottom left corner up
				// to
				// the
				// top right corner. Any tile that is entirely above that line
				// is
				// skipped
				// Any tile that is partially above that line is adjusted down
				// to
				// the
				// line.

				boolean cutDiagonallyUpLeft = false;
				boolean cutDiagonallyDownLeft = false;
				boolean overrideBlockTexture = false;
				int maxCor = Math.max((centerSlopeRight && meta % 4 > 1) || (centerSlopeLeft && meta % 4 < 2) ? 2 : 1,
						(corner ? (centerPiece && outCorner ? 3 : centerPiece ? 2 : 2) : 1));
				if (maxCor == 1 && outCorner)
				{
					maxCor = 2;
				}
				boolean tempOutCorner = false;
				for (int cor = 0; cor < maxCor; cor++)
				{
					outCorner |= tempOutCorner;

					if ((centerSlopeRight || centerSlopeLeft) && center == 0 && cor == 1)
					{
						// System.out.println("hasdas");
					}
					if (cor == 0 && corner)
					{
						cutDiagonallyUpLeft = true;
						cutDiagonallyDownLeft = false;
						if (centerPiece)
						{
							// cutDiagonallyDownLeft = true;
						}
						else
						{
							maxCor = 2;
						}
					}
					else if (corner)
					{
						((RenderBlocksWithRotation) renderer).yRotation -= ((float) Math.PI / 2f);
						cutDiagonallyUpLeft = false;
						cutDiagonallyDownLeft = true;
						if (centerPiece && cor == 1)
						{
							cutDiagonallyUpLeft = true;
						}
						if (centerPiece && cor == 2)
						{
							cutDiagonallyUpLeft = false;
							// cutDiagonallyDownLeft = false;
							// cutDiagonallyUpLeft = true;
							// cutDiagonallyUpRight = false;
							// cutDiagonallyDownRight = true;
						}
					}
					else if (cor > 0 && !corner)
					{
						((RenderBlocksWithRotation) renderer).yRotation -= ((float) Math.PI / 2f);
						cutDiagonallyUpLeft = true;
						cutDiagonallyDownLeft = true;
					}
					if (cor == 1 && blockBack == block && ((blockBackMeta == 4 && world.getBlockMetadata(x, y,
							z) == 5) || (blockBackMeta == 5 && world.getBlockMetadata(x, y,
									z) == 4)) && centerPiece && centerSlopeLeft && centerSlopeRight)
					{
						// ((RenderBlocksWithRotation) renderer).yRotation -=
						// ((float) Math.PI / 2f);
						// continue;
					}
					if (centerPiece && centerSlopeRight && !centerSlopeLeft && cor == 0)
					{
						if (meta < 6)
						{
							cutDiagonallyUpLeft = false;
							cutDiagonallyDownLeft = true;
						}
						else
						{
							cutDiagonallyUpLeft = true;
							cutDiagonallyDownLeft = false;
						}
					}
					else if (centerPiece && centerSlopeLeft && !centerSlopeRight && cor == 0)
					{
						if (meta < 6)
						{
							cutDiagonallyUpLeft = true;
							cutDiagonallyDownLeft = false;
						}
						else
						{
							cutDiagonallyUpLeft = false;
							cutDiagonallyDownLeft = true;
						}
					}
					else if (centerPiece && centerSlopeLeft && centerSlopeRight && cor == 0)
					{
						cutDiagonallyUpLeft = true;
						cutDiagonallyDownLeft = true;
					}
					if (cor == 1 && centerSlopeLeft && centerSlopeRight)
					{
						cutDiagonallyUpLeft = true;
						cutDiagonallyDownLeft = true;
					}
					tempOutCorner = outCorner;
					if (corner && centerSlopeRight && cor > 0)
					{
						cutDiagonallyUpLeft = true;
						cutDiagonallyDownLeft = false;
						outCorner = false;
						// corner = false;
					}
					if (corner && centerSlopeLeft && cor == 0)
					{
						cutDiagonallyUpLeft = false;
						cutDiagonallyDownLeft = true;
						outCorner = false;
					}
					if (corner && centerSlopeLeft && centerSlopeRight && cor == 1)
					{
						continue;
					}
					if (corner && centerSlopeLeft && centerSlopeRight && cor == 0)
					{
						cutDiagonallyUpLeft = false;
						cutDiagonallyDownLeft = false;
						// continue;
					}
					else if (!corner && centerSlopeLeft && cor == 1)
					{
						cutDiagonallyUpLeft = false;
						cutDiagonallyDownLeft = true;
						// continue;
					}
					else if (!corner && centerSlopeRight && cor == 1)
					{
						cutDiagonallyUpLeft = true;
						cutDiagonallyDownLeft = false;
						// continue;
					}
					else if (corner && centerSlopeLeft && cor == 1)
					{
						cutDiagonallyUpLeft = false;
						cutDiagonallyDownLeft = true;
						// continue;
					}
					if (center == 0 && corner && centerSlopeRight)
					{
						cutDiagonallyDownLeft = cor == 1;
						cutDiagonallyUpLeft = cor != 1;
						// continue;
					}
					else if (!corner && centerSlopeRight && cor == 1)
					{
						cutDiagonallyDownLeft = true;
						corner = true;
						// continue;
					}
					if (center == 1 && corner && centerSlopeRight)
					{
						cutDiagonallyUpLeft = blockFront != block;
					}
					if (center == 1 && corner && cor == 1 && centerSlopeRight)
					{
						cutDiagonallyUpLeft = !(blockFront == block && blockFrontMeta > 3);
						cutDiagonallyDownLeft = true;
						outCorner = tempOutCorner;
					}
					if (!corner && centerPiece && !(blockFront == block && blockFrontMeta == orMeta) && centerSlopeLeft && cor == 1)
					{
						cutDiagonallyUpLeft = true;

					}
					if (corner && centerPiece && blockBack == block && cor != 2 && !outCorner)
					{
						// continue;
					}
					if (!corner && centerPiece && (center == 0 || blockFront == block) && cor == 1)
					{
						cutDiagonallyDownLeft = true;
					}
					if (corner && center == 0 && cor == 2 && centerSlopeLeft)
					{
						cutDiagonallyUpLeft = true;
						cutDiagonallyDownLeft = false;
						outCorner = false;
					}
					if (corner && centerSlopeLeft && centerSlopeRight && cor == 2)
					{
						// cutDiagonallyDownLeft = false;
						outCorner = false;
						// continue;
					}

					if (corner && centerPiece && !(blockBack == block && blockBackMeta == orMeta) && centerSlopeRight && cor == 1)
					{
						cutDiagonallyDownLeft = true;
						cutDiagonallyUpLeft = false;
						// continue;
					}
					if (centerSlopeRight && blockFront != block && cor == 1)
					{
						cutDiagonallyUpLeft = true;
					}
					if (center == 0 && blockBack == block && corner && cor == 1 && centerPiece)
					{
						cutDiagonallyDownLeft = true;
						// outCorner = true;
						// continue;
					}
					boolean canHaveTile = true;
					for (int i = 0; i < 2 / tileLength; i++)
					{
						double xL, yL, zL;
						xL = 0 - 0.01;
						overrideBlockTexture = true;
						zL = i * tileLength * (1.414 / 2d) - 0.25;
						yL = ((centerSlopeRight || centerSlopeLeft) && centerPiece && cor == 2 ? 0
								: 0) + (0.4 * tileHeight) + 0.045 * i;
						xL += 0.04165;

						double length = 1.414 / 2d;
						canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);

						if (((zL + (centerPiece ? 0.5
								: 0.25) >= ((centerPiece ? 0.85
										: 1) - xL) * 1.414 && !outCorner) || (zL + 0.25 + (tileLength * length) <= ((centerPiece
												? 0.85 : 1) - xL) * 1.414 && outCorner)) && cutDiagonallyUpLeft)
						{
							canHaveTile = false;
						}
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= xL * 1.414 && !outCorner) || (zL + 0.25 <= (xL - 0.25) * 1.414 && outCorner)) && cutDiagonallyDownLeft)
						{
							canHaveTile = (cutDiagonallyUpLeft && canHaveTile);
						}
						else if (cutDiagonallyDownLeft)
						{
							canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						}
						if (outCorner && canHaveTile)
						{
							if (cutDiagonallyDownLeft && !cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, (xL - 0.3) * 1.414);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (!cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, ((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, Math.min((xL - 0.25) * 1.414,
										((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength));
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
						}
						if (zL + 0.25 + (tileLength * length) >= ((centerPiece ? 0.85
								: 1) - xL) * 1.414 && !outCorner && cutDiagonallyUpLeft && canHaveTile)
						{
							length = (((centerPiece ? 0.85 : 1) - xL) - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						else if (zL + 0.25 + (length * tileLength) >= (xL + 0.15) * 1.414 && !outCorner && cutDiagonallyDownLeft && canHaveTile)
						{
							length = (xL + 0.25 - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						if (canHaveTile)
						{

							overrideBlockTexture = true;
							renderer.renderFromInside = false;
							if (length > 0)
							{
								renderer.setRenderBounds(xL, yL, zL, xL + 0.25 * tileWidth, yL + 0.125 * tileHeight,
										zL + length * tileLength);
							}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}
							renderer.renderFromInside = false;
							// overrideBlockTexture=!overrideBlockTexture;renderer.setRenderBounds(xL
							// + 0.0625, yL + 0.0625, zL, xL + 0.25 - 0.0625, yL
							// +
							// 0.0625, zL +
							// length);if(overrideBlockTexture)renderer.setOverrideBlockTexture(block.getIcon(0,
							// 16));else
							// renderer.clearOverrideBlockTexture();renderer.renderStandardBlock(block,
							// x, y, z);renderer.setRenderBounds(xL + 0.0625,
							// yL,
							// zL, xL
							// + 0.0625, yL + 0.0625, zL +
							// length);renderer.renderStandardBlock(block, x, y,
							// z);renderer.setRenderBounds(xL + 0.5 - 0.0625,
							// yL,
							// zL, xL
							// + 0.5 - 0.0625, yL + 0.0625, zL +
							// length);if(length>0){renderer.renderStandardBlock(block,
							// x, y, z);}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}

							overrideBlockTexture = false;
						}

						xL = 0.25 - 0.0833 - 0.01;
						overrideBlockTexture = false;
						zL = i * tileLength * (1.414 / 2d) - 0.5;
						yL = ((centerSlopeRight || centerSlopeLeft) && centerPiece && cor == 2 ? 0
								: 0) + ((0.4 + (tileHeightOffset * 0.05)) * tileHeight) + 0.045 * i;
						xL += 0.04165;
						length = 1.414 / 2d;
						canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= ((centerPiece ? 0.85
										: 1) - xL) * 1.414 && !outCorner) || (zL + 0.25 + (tileLength * length) <= ((centerPiece
												? 0.85 : 1) - xL) * 1.414 && outCorner)) && cutDiagonallyUpLeft)
						{
							canHaveTile = false;

						}
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= xL * 1.414 && !outCorner) || (zL + 0.25 <= (xL - 0.25) * 1.414 && outCorner)) && cutDiagonallyDownLeft)
						{
							canHaveTile = (cutDiagonallyUpLeft && canHaveTile);
						}
						else if (cutDiagonallyDownLeft)
						{
							canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						}

						if (outCorner && canHaveTile)
						{
							if (cutDiagonallyDownLeft && !cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, (xL - 0.3) * 1.414);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (!cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, ((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, Math.min((xL - 0.25) * 1.414,
										((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength));
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
						}
						if (zL + 0.25 + (tileLength * length) >= ((centerPiece ? 0.85
								: 1) - xL) * 1.414 && !outCorner && cutDiagonallyUpLeft && canHaveTile)
						{
							length = (((centerPiece ? 0.85 : 1) - xL) - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						else if (zL + 0.25 + (length * tileLength) >= (xL + 0.15) * 1.414 && !outCorner && cutDiagonallyDownLeft && canHaveTile)
						{
							length = (xL + 0.25 - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						if (canHaveTile)
						{
							renderer.renderFromInside = false;
							if (length > 0)
							{
								renderer.setRenderBounds(xL, yL, zL, xL + 0.25 * tileWidth, yL + 0.125 * tileHeight,
										zL + length * tileLength);
							}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}
							renderer.renderFromInside = false;
							// overrideBlockTexture=!overrideBlockTexture;renderer.setRenderBounds(xL
							// + 0.0625, yL + 0.0625, zL, xL + 0.25 - 0.0625, yL
							// +
							// 0.0625, zL +
							// length);if(overrideBlockTexture)renderer.setOverrideBlockTexture(block.getIcon(0,
							// 16));else
							// renderer.clearOverrideBlockTexture();renderer.renderStandardBlock(block,
							// x, y, z);renderer.setRenderBounds(xL + 0.0625,
							// yL,
							// zL, xL
							// + 0.0625, yL + 0.0625, zL +
							// length);renderer.renderStandardBlock(block, x, y,
							// z);renderer.setRenderBounds(xL + 0.5 - 0.0625,
							// yL,
							// zL, xL
							// + 0.5 - 0.0625, yL + 0.0625, zL +
							// length);if(length>0){renderer.renderStandardBlock(block,
							// x, y, z);}
						}

						xL = (0.25 - 0.0833) * 2 - 0.01;
						overrideBlockTexture = true;
						zL = i * tileLength * (1.414 / 2d) - 0.25;
						yL = ((centerSlopeRight || centerSlopeLeft) && centerPiece && cor == 2 ? 0
								: 0) + (0.4 * tileHeight) + 0.045 * i;
						xL += 0.04165;
						length = 1.414 / 2d;
						canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= ((centerPiece ? 0.85
										: 1) - xL) * 1.414 && !outCorner) || (zL + 0.25 + (tileLength * length) <= ((centerPiece
												? 0.85 : 1) - xL) * 1.414 && outCorner)) && cutDiagonallyUpLeft)
						{
							canHaveTile = false;

						}
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= xL * 1.414 && !outCorner) || (zL + 0.25 <= (xL - 0.25) * 1.414 && outCorner)) && cutDiagonallyDownLeft)
						{
							canHaveTile = (cutDiagonallyUpLeft && canHaveTile);
						}
						else if (cutDiagonallyDownLeft)
						{
							canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						}
						if (outCorner && canHaveTile)
						{
							if (cutDiagonallyDownLeft && !cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, (xL - 0.3) * 1.414);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (!cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, ((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, Math.min((xL - 0.25) * 1.414,
										((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength));
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
						}
						if (zL + 0.25 + (tileLength * length) >= ((centerPiece ? 0.85
								: 1) - xL) * 1.414 && !outCorner && cutDiagonallyUpLeft && canHaveTile)
						{
							length = (((centerPiece ? 0.85 : 1) - xL) - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						else if (zL + 0.25 + (length * tileLength) >= (xL + 0.15) * 1.414 && !outCorner && cutDiagonallyDownLeft && canHaveTile)
						{
							length = (xL + 0.25 - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						if (canHaveTile)
						{
							overrideBlockTexture = true;
							renderer.renderFromInside = false;
							if (length > 0)
							{
								renderer.setRenderBounds(xL, yL, zL, xL + 0.25 * tileWidth, yL + 0.125 * tileHeight,
										zL + length * tileLength);
							}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}
							renderer.renderFromInside = false;
							// overrideBlockTexture=!overrideBlockTexture;renderer.setRenderBounds(xL
							// + 0.0625, yL + 0.0625, zL, xL + 0.25 - 0.0625, yL
							// +
							// 0.0625, zL +
							// length);if(overrideBlockTexture)renderer.setOverrideBlockTexture(block.getIcon(0,
							// 16));else
							// renderer.clearOverrideBlockTexture();renderer.renderStandardBlock(block,
							// x, y, z);renderer.setRenderBounds(xL + 0.0625,
							// yL,
							// zL, xL
							// + 0.0625, yL + 0.0625, zL +
							// length);renderer.renderStandardBlock(block, x, y,
							// z);renderer.setRenderBounds(xL + 0.5 - 0.0625,
							// yL,
							// zL, xL
							// + 0.5 - 0.0625, yL + 0.0625, zL +
							// length);if(length>0){renderer.renderStandardBlock(block,
							// x, y, z);}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}
							overrideBlockTexture = false;
						}

						xL = (0.25 - 0.0833) * 3 - 0.01;
						overrideBlockTexture = false;
						zL = i * tileLength * (1.414 / 2d) - 0.5;
						yL = ((centerSlopeRight || centerSlopeLeft) && centerPiece && cor == 2 ? 0
								: 0) + ((0.4 + (tileHeightOffset * 0.05)) * tileHeight) + 0.045 * i;
						xL += 0.04165;
						length = 1.414 / 2d;
						canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= ((centerPiece ? 0.85
										: 1) - xL) * 1.414 && !outCorner) || (zL + 0.25 + (tileLength * length) <= ((centerPiece
												? 0.85 : 1) - xL) * 1.414 && outCorner)) && cutDiagonallyUpLeft)
						{
							canHaveTile = false;

						}
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= xL * 1.414 && !outCorner) || (zL + 0.25 <= (xL - 0.25) * 1.414 && outCorner)) && cutDiagonallyDownLeft)
						{
							canHaveTile = (cutDiagonallyUpLeft && canHaveTile);
						}
						else if (cutDiagonallyDownLeft)
						{
							canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						}
						if (outCorner && canHaveTile)
						{
							if (cutDiagonallyDownLeft && !cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, (xL - 0.3) * 1.414);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (!cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, ((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, Math.min((xL - 0.25) * 1.414,
										((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength));
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
						}
						if (zL + 0.25 + (tileLength * length) >= ((centerPiece ? 0.85
								: 1) - xL) * 1.414 && !outCorner && cutDiagonallyUpLeft && canHaveTile)
						{
							length = (((centerPiece ? 0.85 : 1) - xL) - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						else if (zL + 0.25 + (length * tileLength) >= (xL + 0.15) * 1.414 && !outCorner && cutDiagonallyDownLeft && canHaveTile)
						{
							length = (xL + 0.25 - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						if (canHaveTile)
						{
							renderer.renderFromInside = false;
							if (length > 0)
							{
								renderer.setRenderBounds(xL, yL, zL, xL + 0.25 * tileWidth, yL + 0.125 * tileHeight,
										zL + length * tileLength);
							}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}
							renderer.renderFromInside = false;
							// overrideBlockTexture=!overrideBlockTexture;renderer.setRenderBounds(xL
							// + 0.0625, yL + 0.0625, zL, xL + 0.25 - 0.0625, yL
							// +
							// 0.0625, zL +
							// length);if(overrideBlockTexture)renderer.setOverrideBlockTexture(block.getIcon(0,
							// 16));else
							// renderer.clearOverrideBlockTexture();renderer.renderStandardBlock(block,
							// x, y, z);renderer.setRenderBounds(xL + 0.0625,
							// yL,
							// zL, xL
							// + 0.0625, yL + 0.0625, zL +
							// length);renderer.renderStandardBlock(block, x, y,
							// z);renderer.setRenderBounds(xL + 0.5 - 0.0625,
							// yL,
							// zL, xL
							// + 0.5 - 0.0625, yL + 0.0625, zL +
							// length);if(length>0){renderer.renderStandardBlock(block,
							// x, y, z);}
						}

						xL = (0.25 - 0.0833) * 4 - 0.01;
						overrideBlockTexture = true;
						zL = i * tileLength * (1.414 / 2d) - 0.25;
						yL = ((centerSlopeRight || centerSlopeLeft) && centerPiece && cor == 2 ? 0
								: 0) + (0.4 * tileHeight) + 0.045 * i;
						xL += 0.04165;
						length = 1.414 / 2d;
						canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= ((centerPiece ? 0.85
										: 1) - xL) * 1.414 && !outCorner) || (zL + 0.25 + (tileLength * length) <= ((centerPiece
												? 0.85 : 1) - xL) * 1.414 && outCorner)) && cutDiagonallyUpLeft)
						{
							canHaveTile = false;

						}
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= xL * 1.414 && !outCorner) || (zL + 0.25 <= (xL - 0.25) * 1.414 && outCorner)) && cutDiagonallyDownLeft)
						{
							canHaveTile = (cutDiagonallyUpLeft && canHaveTile);
						}
						else if (cutDiagonallyDownLeft)
						{
							canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						}
						if (outCorner && canHaveTile)
						{
							if (cutDiagonallyDownLeft && !cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, (xL - 0.3) * 1.414);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (!cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, ((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, Math.min((xL - 0.25) * 1.414,
										((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength));
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
						}
						if (zL + 0.25 + (tileLength * length) >= ((centerPiece ? 0.85
								: 1) - xL) * 1.414 && !outCorner && cutDiagonallyUpLeft && canHaveTile)
						{
							length = (((centerPiece ? 0.85 : 1) - xL) - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						else if (zL + 0.25 + (length * tileLength) >= (xL + 0.15) * 1.414 && !outCorner && cutDiagonallyDownLeft && canHaveTile)
						{
							length = (xL + 0.25 - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						if (canHaveTile)
						{
							renderer.renderFromInside = false;
							if (length > 0)
							{
								renderer.setRenderBounds(xL, yL, zL, xL + 0.25 * tileWidth, yL + 0.125 * tileHeight,
										zL + length * tileLength);
							}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}
							renderer.renderFromInside = false;
							// overrideBlockTexture=!overrideBlockTexture;renderer.setRenderBounds(xL
							// + 0.0625, yL + 0.0625, zL, xL + 0.25 - 0.0625, yL
							// +
							// 0.0625, zL +
							// length);if(overrideBlockTexture)renderer.setOverrideBlockTexture(block.getIcon(0,
							// 16));else
							// renderer.clearOverrideBlockTexture();renderer.renderStandardBlock(block,
							// x, y, z);renderer.setRenderBounds(xL + 0.0625,
							// yL,
							// zL, xL
							// + 0.0625, yL + 0.0625, zL +
							// length);renderer.renderStandardBlock(block, x, y,
							// z);renderer.setRenderBounds(xL + 0.5 - 0.0625,
							// yL,
							// zL, xL
							// + 0.5 - 0.0625, yL + 0.0625, zL +
							// length);if(length>0){renderer.renderStandardBlock(block,
							// x, y, z);}
						}

						xL = (0.25 - 0.0833) * -1 - 0.01;
						overrideBlockTexture = false;
						zL = i * tileLength * (1.414 / 2d) - 0.5;
						yL = ((centerSlopeRight || centerSlopeLeft) && centerPiece && cor == 2 ? 0
								: 0) + ((0.4 + (tileHeightOffset * 0.05)) * tileHeight) + 0.045 * i;
						xL += 0.04165;
						length = 1.414 / 2d;

						canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= ((centerPiece ? 0.85
										: 1) - xL) * 1.414 && !outCorner) || (zL + 0.25 + (tileLength * length) <= ((centerPiece
												? 0.85 : 1) - xL) * 1.414 && outCorner)) && cutDiagonallyUpLeft)
						{
							canHaveTile = false;

						}
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= xL * 1.414 && !outCorner) || (zL + 0.25 <= (xL - 0.25) * 1.414 && outCorner)) && cutDiagonallyDownLeft)
						{
							canHaveTile = (cutDiagonallyUpLeft && canHaveTile);
						}
						else if (cutDiagonallyDownLeft)
						{
							canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						}
						if (outCorner && canHaveTile)
						{
							if (cutDiagonallyDownLeft && !cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, (xL - 0.3) * 1.414);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (!cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, ((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, Math.min((xL - 0.25) * 1.414,
										((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength));
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
						}
						if (zL + 0.25 + (tileLength * length) >= ((centerPiece ? 0.85
								: 1) - xL) * 1.414 && !outCorner && cutDiagonallyUpLeft && canHaveTile)
						{
							length = (((centerPiece ? 0.85 : 1) - xL) - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						else if (zL + 0.25 + (length * tileLength) >= (xL + 0.15) * 1.414 && !outCorner && cutDiagonallyDownLeft && canHaveTile)
						{
							length = (xL + 0.25 - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						if (canHaveTile)
						{
							renderer.renderFromInside = false;
							if (length > 0)
							{
								renderer.setRenderBounds(xL, yL, zL, xL + 0.25 * tileWidth, yL + 0.125 * tileHeight,
										zL + length * tileLength);
							}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}
							renderer.renderFromInside = false;
							// overrideBlockTexture=!overrideBlockTexture;renderer.setRenderBounds(xL
							// + 0.0625, yL + 0.0625, zL, xL + 0.25 - 0.0625, yL
							// +
							// 0.0625, zL +
							// length);if(overrideBlockTexture)renderer.setOverrideBlockTexture(block.getIcon(0,
							// 16));else
							// renderer.clearOverrideBlockTexture();renderer.renderStandardBlock(block,
							// x, y, z);renderer.setRenderBounds(xL + 0.0625,
							// yL,
							// zL, xL
							// + 0.0625, yL + 0.0625, zL +
							// length);renderer.renderStandardBlock(block, x, y,
							// z);renderer.setRenderBounds(xL + 0.5 - 0.0625,
							// yL,
							// zL, xL
							// + 0.5 - 0.0625, yL + 0.0625, zL +
							// length);if(length>0){renderer.renderStandardBlock(block,
							// x, y, z);}
						}

						if (blockLeft != block && ((!corner || (((!outCorner && orMeta < 4) || (outCorner && orMeta >= 4)) && corner && blockLeft != block && yRotChange != 0)) /*
																																												 * ||
																																												 * (outCorner
																																												 * &&
																																												 * cor
																																												 * ==
																																												 * 1
																																												 * &&
																																												 * !
																																												 * (
																																												 * blockBack
																																												 * ==
																																												 * block
																																												 * &&
																																												 * blockBackMeta
																																												 * !
																																												 * =
																																												 * orMeta
																																												 * )
																																												 * )
																																												 */) /*
																																													 * &&
																																													 * (blockFront
																																													 * !=
																																													 * block
																																													 * &&
																																													 * corner)
																																													 */)
						{
							xL = (0.25 - 0.0833) * 5 - 0.01;
							overrideBlockTexture = false;
							zL = i * tileLength * (1.414 / 2d) - 0.5;
							yL = ((centerSlopeRight || centerSlopeLeft) && centerPiece && cor == 2 ? 0
									: 0) + 0.45 + 0.045 * i;
							xL += 0.04165;
							length = 1.414 / 2d;
							canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
							if (((zL + (centerPiece ? 0.5
									: 0.25) >= ((centerPiece ? 0.85
											: 1) - xL) * 1.414 && !outCorner) || (zL + 0.25 + (tileLength * length) <= ((centerPiece
													? 0.85 : 1) - xL) * 1.414 && outCorner)) && cutDiagonallyUpLeft)
							{
								canHaveTile = false;

							}
							if (((zL + (centerPiece ? 0.5
									: 0.25) >= xL * 1.414 && !outCorner) || (zL + 0.25 <= (xL - 0.25) * 1.414 && outCorner)) && cutDiagonallyDownLeft)
							{
								canHaveTile = (cutDiagonallyUpLeft && canHaveTile);
							}
							else if (cutDiagonallyDownLeft)
							{
								canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
							}
							if (outCorner && canHaveTile)
							{
								if (cutDiagonallyDownLeft && !cutDiagonallyUpLeft)
								{
									double oldzL = zL;
									zL = Math.max(zL, (xL - 0.3) * 1.414);
									length -= zL - oldzL;
									if (length <= 0.1)
									{
										canHaveTile = false;
									}
								}
								else if (!cutDiagonallyDownLeft && cutDiagonallyUpLeft)
								{
									double oldzL = zL;
									zL = Math.max(zL, ((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength);
									length -= zL - oldzL;
									if (length <= 0.1)
									{
										canHaveTile = false;
									}
								}
								else if (cutDiagonallyDownLeft && cutDiagonallyUpLeft)
								{
									double oldzL = zL;
									zL = Math.max(zL, Math.min((xL - 0.25) * 1.414,
											((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength));
									length -= zL - oldzL;
									if (length <= 0.1)
									{
										canHaveTile = false;
									}
								}
							}
							if (zL + 0.25 + (tileLength * length) >= ((centerPiece ? 0.85
									: 1) - xL) * 1.414 && !outCorner && cutDiagonallyUpLeft && canHaveTile)
							{
								length = (((centerPiece ? 0.85 : 1) - xL) - (zL + 0.25)) * 1.414;
								length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
							}
							else if (zL + 0.25 + (length * tileLength) >= (xL + 0.15) * 1.414 && !outCorner && cutDiagonallyDownLeft && canHaveTile)
							{
								length = (xL + 0.25 - (zL + 0.25)) * 1.414;
								length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
							}
							if (canHaveTile)
							{
								renderer.renderFromInside = false;
								if (length > 0)
								{
									renderer.setRenderBounds(xL, yL, zL, xL + 0.25 * tileWidth, yL + 0.125 * tileHeight,
											zL + length * tileLength);
								}
								if (overrideBlockTexture)
									renderer.setOverrideBlockTexture(block.getIcon(0, 16));
								else
									renderer.clearOverrideBlockTexture();
								if (length > 0)
								{
									renderer.renderStandardBlock(block, x, y, z);
								}
								renderer.renderFromInside = false;
								// overrideBlockTexture=!overrideBlockTexture;renderer.setRenderBounds(xL
								// + 0.0625, yL + 0.0625, zL, xL + 0.25 -
								// 0.0625, yL
								// +
								// 0.0625, zL +
								// length);if(overrideBlockTexture)renderer.setOverrideBlockTexture(block.getIcon(0,
								// 16));else
								// renderer.clearOverrideBlockTexture();renderer.renderStandardBlock(block,
								// x, y, z);renderer.setRenderBounds(xL +
								// 0.0625,
								// yL,
								// zL, xL + 0.0625, yL + 0.0625, zL +
								// length);renderer.renderStandardBlock(block,
								// x, y,
								// z);renderer.setRenderBounds(xL + 0.5 -
								// 0.0625,
								// yL,
								// zL, xL + 0.5 - 0.0625, yL + 0.0625, zL +
								// length);renderer.renderStandardBlock(block,
								// x, y,
								// z);
								if (overrideBlockTexture)
									renderer.setOverrideBlockTexture(block.getIcon(0, 16));
								else
									renderer.clearOverrideBlockTexture();
								if (length > 0)
								{
									renderer.renderStandardBlock(block, x, y, z);
								}
							}
						}
					}

					if (blockDownBack != block)
					{
						int i = -1;
						double xL, yL, zL;
						xL = 0 + 0.01;
						overrideBlockTexture = true;
						zL = i * (1.414 / 4d) - 0.25;
						yL = ((centerSlopeRight || centerSlopeLeft) && centerPiece && cor == 2 ? 0
								: 0) + (0.4 * tileHeight) + 0.045 * i;
						xL += 0.04165;

						double length = 1.414 / 4d;
						canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= ((centerPiece ? 0.85
										: 1) - xL) * 1.414 && !outCorner) || (zL + 0.25 + (tileLength * length) <= ((centerPiece
												? 0.85 : 1) - xL) * 1.414 && outCorner)) && cutDiagonallyUpLeft)
						{
							canHaveTile = false;
						}
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= xL * 1.414 && !outCorner) || (zL + 0.25 <= (xL - 0.25) * 1.414 && outCorner)) && cutDiagonallyDownLeft)
						{
							canHaveTile = (cutDiagonallyUpLeft && canHaveTile);
						}
						else if (cutDiagonallyDownLeft)
						{
							canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						}
						if (outCorner && canHaveTile)
						{
							if (cutDiagonallyDownLeft && !cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, (xL - 0.3) * 1.414);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (!cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, ((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, Math.min((xL - 0.25) * 1.414,
										((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength));
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
						}
						if (zL + 0.25 + (tileLength * length) >= ((centerPiece ? 0.85
								: 1) - xL) * 1.414 && !outCorner && cutDiagonallyUpLeft && canHaveTile)
						{
							length = (((centerPiece ? 0.85 : 1) - xL) - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						else if (zL + 0.25 + (length * tileLength) >= (xL + 0.15) * 1.414 && !outCorner && cutDiagonallyDownLeft && canHaveTile)
						{
							length = (xL + 0.25 - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						if (canHaveTile)
						{

							renderer.renderFromInside = false;
							if (length > 0)
							{
								renderer.setRenderBounds(xL, yL, zL, xL + 0.25 * tileWidth, yL + 0.125 * tileHeight,
										zL + length * tileLength);
							}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}
							renderer.renderFromInside = false;
							// overrideBlockTexture=!overrideBlockTexture;renderer.setRenderBounds(xL
							// + 0.0625, yL + 0.0625, zL, xL + 0.25 - 0.0625, yL
							// +
							// 0.0625, zL +
							// length);if(overrideBlockTexture)renderer.setOverrideBlockTexture(block.getIcon(0,
							// 16));else
							// renderer.clearOverrideBlockTexture();renderer.renderStandardBlock(block,
							// x, y, z);renderer.setRenderBounds(xL + 0.0625,
							// yL,
							// zL, xL
							// + 0.0625, yL + 0.0625, zL +
							// length);renderer.renderStandardBlock(block, x, y,
							// z);renderer.setRenderBounds(xL + 0.5 - 0.0625,
							// yL,
							// zL, xL
							// + 0.5 - 0.0625, yL + 0.0625, zL +
							// length);if(length>0){renderer.renderStandardBlock(block,
							// x, y, z);}
						}
						/*
						 * xL = 0.25 - 0.0833; zL = i*(1.414 / 4d)-0.5; yL =
						 * 0.45+ 0.045*i; renderer.setRenderBounds(xL, yL, zL,
						 * xL + 0.25, yL + 0.125, zL + 1.414 / 2d );
						 * if(length>0){renderer.renderStandardBlock(block, x,
						 * y, z);}
						 */

						xL = (0.25 - 0.0833) * 2 - 0.01;
						overrideBlockTexture = true;
						zL = i * (1.414 / 4d) - 0.25;
						yL = ((centerSlopeRight || centerSlopeLeft) && centerPiece && cor == 2 ? 0
								: 0) + (0.4 * tileHeight) + 0.045 * i;
						xL += 0.04165;

						length = 1.414 / 4d;
						canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= ((centerPiece ? 0.85
										: 1) - xL) * 1.414 && !outCorner) || (zL + 0.25 + (tileLength * length) <= ((centerPiece
												? 0.85 : 1) - xL) * 1.414 && outCorner)) && cutDiagonallyUpLeft)
						{
							canHaveTile = false;
						}
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= xL * 1.414 && !outCorner) || (zL + 0.25 <= (xL - 0.25) * 1.414 && outCorner)) && cutDiagonallyDownLeft)
						{
							canHaveTile = (cutDiagonallyUpLeft && canHaveTile);
						}
						else if (cutDiagonallyDownLeft)
						{
							canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						}
						if (outCorner && canHaveTile)
						{
							if (cutDiagonallyDownLeft && !cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, (xL - 0.3) * 1.414);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (!cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, ((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, Math.min((xL - 0.25) * 1.414,
										((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength));
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
						}
						if (zL + 0.25 + (tileLength * length) >= ((centerPiece ? 0.85
								: 1) - xL) * 1.414 && !outCorner && cutDiagonallyUpLeft && canHaveTile)
						{
							length = (((centerPiece ? 0.85 : 1) - xL) - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						else if (zL + 0.25 + (length * tileLength) >= (xL + 0.15) * 1.414 && !outCorner && cutDiagonallyDownLeft && canHaveTile)
						{
							length = (xL + 0.25 - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						if (canHaveTile)
						{
							renderer.renderFromInside = false;
							if (length > 0)
							{
								renderer.setRenderBounds(xL, yL, zL, xL + 0.25 * tileWidth, yL + 0.125 * tileHeight,
										zL + length * tileLength);
							}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}
							renderer.renderFromInside = false;
							// overrideBlockTexture=!overrideBlockTexture;renderer.setRenderBounds(xL
							// + 0.0625, yL + 0.0625, zL, xL + 0.25 - 0.0625, yL
							// +
							// 0.0625, zL +
							// length);if(overrideBlockTexture)renderer.setOverrideBlockTexture(block.getIcon(0,
							// 16));else
							// renderer.clearOverrideBlockTexture();renderer.renderStandardBlock(block,
							// x, y, z);renderer.setRenderBounds(xL + 0.0625,
							// yL,
							// zL, xL
							// + 0.0625, yL + 0.0625, zL +
							// length);renderer.renderStandardBlock(block, x, y,
							// z);renderer.setRenderBounds(xL + 0.5 - 0.0625,
							// yL,
							// zL, xL
							// + 0.5 - 0.0625, yL + 0.0625, zL +
							// length);if(length>0){renderer.renderStandardBlock(block,
							// x, y, z);}
						}

						/*
						 * xL = (0.25 - 0.0833) * 3; zL = i*(1.414 / 4d)-0.5; yL
						 * = 0.45+ 0.045*i; renderer.setRenderBounds(xL, yL, zL,
						 * xL + 0.25, yL + 0.125, zL + 1.414 / 2d );
						 * if(length>0){renderer.renderStandardBlock(block, x,
						 * y, z);}
						 */

						xL = (0.25 - 0.0833) * 4 - 0.01;
						overrideBlockTexture = true;
						zL = i * (1.414 / 4d) - 0.25;
						yL = ((centerSlopeRight || centerSlopeLeft) && centerPiece && cor == 2 ? 0
								: 0) + (0.4 * tileHeight) + 0.045 * i;
						xL += 0.04165;

						length = 1.414 / 4d;
						canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= ((centerPiece ? 0.85
										: 1) - xL) * 1.414 && !outCorner) || (zL + 0.25 + (tileLength * length) <= ((centerPiece
												? 0.85 : 1) - xL) * 1.414 && outCorner)) && cutDiagonallyUpLeft)
						{
							canHaveTile = false;
						}
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= xL * 1.414 && !outCorner) || (zL + 0.25 <= (xL - 0.25) * 1.414 && outCorner)) && cutDiagonallyDownLeft)
						{
							canHaveTile = (cutDiagonallyUpLeft && canHaveTile);
						}
						else if (cutDiagonallyDownLeft)
						{
							canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						}
						if (outCorner && canHaveTile)
						{
							if (cutDiagonallyDownLeft && !cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, (xL - 0.3) * 1.414);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (!cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, ((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, Math.min((xL - 0.25) * 1.414,
										((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength));
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
						}
						if (zL + 0.25 + (tileLength * length) >= ((centerPiece ? 0.85
								: 1) - xL) * 1.414 && !outCorner && cutDiagonallyUpLeft && canHaveTile)
						{
							length = (((centerPiece ? 0.85 : 1) - xL) - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						else if (zL + 0.25 + (length * tileLength) >= (xL + 0.15) * 1.414 && !outCorner && cutDiagonallyDownLeft && canHaveTile)
						{
							length = (xL + 0.25 - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						if (canHaveTile)
						{
							renderer.renderFromInside = false;
							if (length > 0)
							{
								renderer.setRenderBounds(xL, yL, zL, xL + 0.25 * tileWidth, yL + 0.125 * tileHeight,
										zL + length * tileLength);
							}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}
							renderer.renderFromInside = false;
							// overrideBlockTexture=!overrideBlockTexture;renderer.setRenderBounds(xL
							// + 0.0625, yL + 0.0625, zL, xL + 0.25 - 0.0625, yL
							// +
							// 0.0625, zL +
							// length);if(overrideBlockTexture)renderer.setOverrideBlockTexture(block.getIcon(0,
							// 16));else
							// renderer.clearOverrideBlockTexture();renderer.renderStandardBlock(block,
							// x, y, z);renderer.setRenderBounds(xL + 0.0625,
							// yL,
							// zL, xL
							// + 0.0625, yL + 0.0625, zL +
							// length);renderer.renderStandardBlock(block, x, y,
							// z);renderer.setRenderBounds(xL + 0.5 - 0.0625,
							// yL,
							// zL, xL
							// + 0.5 - 0.0625, yL + 0.0625, zL +
							// length);if(length>0){renderer.renderStandardBlock(block,
							// x, y, z);}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}

						}

						/*
						 * xL = (0.25 - 0.0833) * -1; zL = i*(1.414 / 4d)-0.5;
						 * yL = 0.45+ 0.045*i; renderer.setRenderBounds(xL, yL,
						 * zL, xL + 0.25, yL + 0.125, zL + 1.414 / 2d );
						 * if(overrideBlockTexture)renderer.
						 * setOverrideBlockTexture( block.getIcon(0, 16));else
						 * renderer.clearOverrideBlockTexture();renderer.
						 * renderStandardBlock(block, x, y, z);
						 */

						/*
						 * if (world.getBlock(x + 1, y, z) != block) { xL =
						 * (0.25 - 0.0833) * 5; zL = i*(1.414 / 4d)-0.5; yL =
						 * 0.45+ 0.045*i; renderer.setRenderBounds(xL, yL, zL,
						 * xL + 0.25, yL + 0.125, zL + 1.414 / 2d );
						 * if(length>0){renderer.renderStandardBlock(block, x,
						 * y, z);} }
						 */
					}
					if (blockUpFront != block || (blockUpFrontMeta >= 4 && blockUpFrontMeta % 2 != meta % 2))
					{
						int i = 4;
						double xL, yL, zL;

						/*
						 * xL = 0; zL = i*(1.414 / 4d)-0.25; yL = 0.4 + 0.045*i;
						 * 
						 * renderer.setRenderBounds(xL, yL, zL, xL + 0.25, yL +
						 * 0.125, zL + 1.414 / 2d );
						 * if(length>0){renderer.renderStandardBlock(block, x,
						 * y, z);}
						 */

						xL = 0.25 - 0.0833 - 0.01;
						overrideBlockTexture = false;
						zL = i * tileLength * (1.414 / 4d) - 0.5;
						yL = 0.4 - (0.15 * tileHeight) + 0.045 * i;
						xL += 0.04165;

						double length = 1.414 / 4d;
						canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= ((centerPiece ? 0.85
										: 1) - xL) * 1.414 && !outCorner) || (zL + 0.25 + (tileLength * length) <= ((centerPiece
												? 0.85 : 1) - xL) * 1.414 && outCorner)) && cutDiagonallyUpLeft)
						{
							canHaveTile = false;
						}
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= xL * 1.414 && !outCorner) || (zL + 0.25 <= (xL - 0.25) * 1.414 && outCorner)) && cutDiagonallyDownLeft)
						{
							canHaveTile = (cutDiagonallyUpLeft && canHaveTile);
						}
						else if (cutDiagonallyDownLeft)
						{
							canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						}
						if (outCorner && canHaveTile)
						{
							if (cutDiagonallyDownLeft && !cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, (xL - 0.3) * 1.414);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (!cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, ((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, Math.min((xL - 0.25) * 1.414,
										((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength));
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
						}
						if (zL + 0.25 + (tileLength * length) >= ((centerPiece ? 0.85
								: 1) - xL) * 1.414 && !outCorner && cutDiagonallyUpLeft && canHaveTile)
						{
							length = (((centerPiece ? 0.85 : 1) - xL) - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						else if (zL + 0.25 + (length * tileLength) >= (xL + 0.15) * 1.414 && !outCorner && cutDiagonallyDownLeft && canHaveTile)
						{
							length = (xL + 0.25 - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						if (canHaveTile)
						{

							renderer.renderFromInside = false;
							if (length > 0)
							{
								renderer.setRenderBounds(xL, yL, zL, xL + 0.25 * tileWidth, yL + 0.125 * tileHeight,
										zL + length * tileLength);
							}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}
							renderer.renderFromInside = false;
							// overrideBlockTexture=!overrideBlockTexture;renderer.setRenderBounds(xL
							// + 0.0625, yL + 0.0625, zL, xL + 0.25 - 0.0625, yL
							// +
							// 0.0625, zL +
							// length);if(overrideBlockTexture)renderer.setOverrideBlockTexture(block.getIcon(0,
							// 16));else
							// renderer.clearOverrideBlockTexture();renderer.renderStandardBlock(block,
							// x, y, z);renderer.setRenderBounds(xL + 0.0625,
							// yL,
							// zL, xL
							// + 0.0625, yL + 0.0625, zL +
							// length);renderer.renderStandardBlock(block, x, y,
							// z);renderer.setRenderBounds(xL + 0.5 - 0.0625,
							// yL,
							// zL, xL
							// + 0.5 - 0.0625, yL + 0.0625, zL +
							// length);if(length>0){renderer.renderStandardBlock(block,
							// x, y, z);}
						}

						/*
						 * xL = (0.25 - 0.0833) * 2; zL = i*(1.414 / 4d)-0.25;
						 * yL = 0.4+ 0.045*i; renderer.setRenderBounds(xL, yL,
						 * zL, xL + 0.25, yL + 0.125, zL + 1.414 / 2d );
						 * if(length>0){renderer.renderStandardBlock(block, x,
						 * y, z);}
						 */

						xL = (0.25 - 0.0833) * 3 - 0.01;
						overrideBlockTexture = false;
						zL = i * tileLength * (1.414 / 4d) - 0.5;
						yL = 0.4 - (0.15 * tileHeight) + 0.045 * i;
						xL += 0.04165;

						length = 1.414 / 4d;
						canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= ((centerPiece ? 0.85
										: 1) - xL) * 1.414 && !outCorner) || (zL + 0.25 + (tileLength * length) <= ((centerPiece
												? 0.85 : 1) - xL) * 1.414 && outCorner)) && cutDiagonallyUpLeft)
						{
							canHaveTile = false;
						}
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= xL * 1.414 && !outCorner) || (zL + 0.25 <= (xL - 0.25) * 1.414 && outCorner)) && cutDiagonallyDownLeft)
						{
							canHaveTile = (cutDiagonallyUpLeft && canHaveTile);
						}
						else if (cutDiagonallyDownLeft)
						{
							canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						}
						if (outCorner && canHaveTile)
						{
							if (cutDiagonallyDownLeft && !cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, (xL - 0.3) * 1.414);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (!cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, ((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, Math.min((xL - 0.25) * 1.414,
										((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength));
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
						}
						if (zL + 0.25 + (tileLength * length) >= ((centerPiece ? 0.85
								: 1) - xL) * 1.414 && !outCorner && cutDiagonallyUpLeft && canHaveTile)
						{
							length = (((centerPiece ? 0.85 : 1) - xL) - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						else if (zL + 0.25 + (length * tileLength) >= (xL + 0.15) * 1.414 && !outCorner && cutDiagonallyDownLeft && canHaveTile)
						{
							length = (xL + 0.25 - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						if (canHaveTile)
						{
							renderer.renderFromInside = false;
							if (length > 0)
							{
								renderer.setRenderBounds(xL, yL, zL, xL + 0.25 * tileWidth, yL + 0.125 * tileHeight,
										zL + length * tileLength);
							}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}
							renderer.renderFromInside = false;
							// overrideBlockTexture=!overrideBlockTexture;renderer.setRenderBounds(xL
							// + 0.0625, yL + 0.0625, zL, xL + 0.25 - 0.0625, yL
							// +
							// 0.0625, zL +
							// length);if(overrideBlockTexture)renderer.setOverrideBlockTexture(block.getIcon(0,
							// 16));else
							// renderer.clearOverrideBlockTexture();renderer.renderStandardBlock(block,
							// x, y, z);renderer.setRenderBounds(xL + 0.0625,
							// yL,
							// zL, xL
							// + 0.0625, yL + 0.0625, zL +
							// length);renderer.renderStandardBlock(block, x, y,
							// z);renderer.setRenderBounds(xL + 0.5 - 0.0625,
							// yL,
							// zL, xL
							// + 0.5 - 0.0625, yL + 0.0625, zL +
							// length);if(length>0){renderer.renderStandardBlock(block,
							// x, y, z);}
						}

						/*
						 * xL = (0.25 - 0.0833) * 4; zL = i*(1.414 / 4d)-0.25;
						 * yL = 0.4+ 0.045*i; renderer.setRenderBounds(xL, yL,
						 * zL, xL + 0.25, yL + 0.125, zL + 1.414 / 2d );
						 * if(length>0){renderer.renderStandardBlock(block, x,
						 * y, z);}
						 */

						xL = (0.25 - 0.0833) * -1 - 0.01;
						overrideBlockTexture = false;
						zL = i * tileLength * (1.414 / 4d) - 0.5;
						yL = 0.4 - (0.15 * tileHeight) + 0.045 * i;
						xL += 0.04165;

						length = 1.414 / 4d;
						canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= ((centerPiece ? 0.85
										: 1) - xL) * 1.414 && !outCorner) || (zL + 0.25 + (tileLength * length) <= ((centerPiece
												? 0.85 : 1) - xL) * 1.414 && outCorner)) && cutDiagonallyUpLeft)
						{
							canHaveTile = false;
						}
						if (((zL + (centerPiece ? 0.5
								: 0.25) >= xL * 1.414 && !outCorner) || (zL + 0.25 <= (xL - 0.25) * 1.414 && outCorner)) && cutDiagonallyDownLeft)
						{
							canHaveTile = (cutDiagonallyUpLeft && canHaveTile);
						}
						else if (cutDiagonallyDownLeft)
						{
							canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
						}
						if (outCorner && canHaveTile)
						{
							if (cutDiagonallyDownLeft && !cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, (xL - 0.3) * 1.414);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (!cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, ((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength);
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
							else if (cutDiagonallyDownLeft && cutDiagonallyUpLeft)
							{
								double oldzL = zL;
								zL = Math.max(zL, Math.min((xL - 0.25) * 1.414,
										((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength));
								length -= zL - oldzL;
								if (length <= 0.1)
								{
									canHaveTile = false;
								}
							}
						}
						if (zL + 0.25 + (tileLength * length) >= ((centerPiece ? 0.85
								: 1) - xL) * 1.414 && !outCorner && cutDiagonallyUpLeft && canHaveTile)
						{
							length = (((centerPiece ? 0.85 : 1) - xL) - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						else if (zL + 0.25 + (length * tileLength) >= (xL + 0.15) * 1.414 && !outCorner && cutDiagonallyDownLeft && canHaveTile)
						{
							length = (xL + 0.25 - (zL + 0.25)) * 1.414;
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
						}
						if (canHaveTile)
						{

							renderer.renderFromInside = false;
							if (length > 0)
							{
								renderer.setRenderBounds(xL, yL, zL, xL + 0.25 * tileWidth, yL + 0.125 * tileHeight,
										zL + length * tileLength);
							}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}
							renderer.renderFromInside = false;
							// overrideBlockTexture=!overrideBlockTexture;renderer.setRenderBounds(xL
							// + 0.0625, yL + 0.0625, zL, xL + 0.25 - 0.0625, yL
							// +
							// 0.0625, zL +
							// length);if(overrideBlockTexture)renderer.setOverrideBlockTexture(block.getIcon(0,
							// 16));else
							// renderer.clearOverrideBlockTexture();renderer.renderStandardBlock(block,
							// x, y, z);renderer.setRenderBounds(xL + 0.0625,
							// yL,
							// zL, xL
							// + 0.0625, yL + 0.0625, zL +
							// length);renderer.renderStandardBlock(block, x, y,
							// z);renderer.setRenderBounds(xL + 0.5 - 0.0625,
							// yL,
							// zL, xL
							// + 0.5 - 0.0625, yL + 0.0625, zL +
							// length);if(length>0){renderer.renderStandardBlock(block,
							// x, y, z);}
						}

						if (blockLeft != block && !corner/*
															 * && (blockFront !=
															 * block && corner)
															 */)
						{
							xL = (0.25 - 0.0833) * 5 - 0.01;
							overrideBlockTexture = false;
							zL = i * tileLength * (1.414 / 4d) - 0.5;
							yL = 0.4 - (0.15 * tileHeight) + 0.045 * i;
							xL += 0.04165;

							length = 1.414 / 4d;
							canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
							length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
							if (((zL + (centerPiece ? 0.5
									: 0.25) >= ((centerPiece ? 0.85
											: 1) - xL) * 1.414 && !outCorner) || (zL + 0.25 + (tileLength * length) <= ((centerPiece
													? 0.85 : 1) - xL) * 1.414 && outCorner)) && cutDiagonallyUpLeft)
							{
								canHaveTile = false;
							}
							if (((zL + (centerPiece ? 0.5
									: 0.25) >= xL * 1.414 && !outCorner) || (zL + 0.25 <= (xL - 0.25) * 1.414 && outCorner)) && cutDiagonallyDownLeft)
							{
								canHaveTile = (cutDiagonallyUpLeft && canHaveTile);
							}
							else if (cutDiagonallyDownLeft)
							{
								canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
							}
							else if (cutDiagonallyDownLeft)
							{
								canHaveTile = !(centerPiece && zL + 0.25 >= (1.414 / 2d) / tileLength);
							}
							if (outCorner && canHaveTile)
							{
								if (cutDiagonallyDownLeft && !cutDiagonallyUpLeft)
								{
									double oldzL = zL;
									zL = Math.max(zL, (xL - 0.3) * 1.414);
									length -= zL - oldzL;
									if (length <= 0.1)
									{
										canHaveTile = false;
									}
								}
								else if (!cutDiagonallyDownLeft && cutDiagonallyUpLeft)
								{
									double oldzL = zL;
									zL = Math.max(zL, ((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength);
									length -= zL - oldzL;
									if (length <= 0.1)
									{
										canHaveTile = false;
									}
								}
								else if (cutDiagonallyDownLeft && cutDiagonallyUpLeft)
								{
									double oldzL = zL;
									zL = Math.max(zL, Math.min((xL - 0.25) * 1.414,
											((centerPiece ? 0.85 : 1) - xL) * 1.414 * tileLength));
									length -= zL - oldzL;
									if (length <= 0.1)
									{
										canHaveTile = false;
									}
								}
							}
							if (zL + 0.25 + (tileLength * length) >= ((centerPiece ? 0.85
									: 1) - xL) * 1.414 && !outCorner && cutDiagonallyUpLeft && canHaveTile)
							{
								length = (((centerPiece ? 0.85 : 1) - xL) - (zL + 0.25)) * 1.414;
								length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
							}
							else if (zL + 0.25 + (length * tileLength) >= (xL + 0.15) * 1.414 && !outCorner && cutDiagonallyDownLeft && canHaveTile)
							{
								length = (xL + 0.25 - (zL + 0.25)) * 1.414;
								length = Math.min(length, centerPiece ? 1.414 / 2d - (zL + 0.25) : 100);
							}
							if (canHaveTile)
							{
								renderer.renderFromInside = false;
								if (length > 0)
								{
									renderer.setRenderBounds(xL, yL, zL, xL + 0.25 * tileWidth, yL + 0.125 * tileHeight,
											zL + length * tileLength);
								}
								if (overrideBlockTexture)
									renderer.setOverrideBlockTexture(block.getIcon(0, 16));
								else
									renderer.clearOverrideBlockTexture();
								if (length > 0)
								{
									renderer.renderStandardBlock(block, x, y, z);
								}
								renderer.renderFromInside = false;
								// overrideBlockTexture=!overrideBlockTexture;renderer.setRenderBounds(xL
								// + 0.0625, yL + 0.0625, zL, xL + 0.25 -
								// 0.0625, yL
								// +
								// 0.0625, zL +
								// length);if(overrideBlockTexture)renderer.setOverrideBlockTexture(block.getIcon(0,
								// 16));else
								// renderer.clearOverrideBlockTexture();renderer.renderStandardBlock(block,
								// x, y, z);renderer.setRenderBounds(xL +
								// 0.0625,
								// yL,
								// zL, xL + 0.0625, yL + 0.0625, zL +
								// length);renderer.renderStandardBlock(block,
								// x, y,
								// z);renderer.setRenderBounds(xL + 0.5 -
								// 0.0625,
								// yL,
								// zL, xL + 0.5 - 0.0625, yL + 0.0625, zL +
								// length);renderer.renderStandardBlock(block,
								// x, y,
								// z);
								if (overrideBlockTexture)
									renderer.setOverrideBlockTexture(block.getIcon(0, 16));
								else
									renderer.clearOverrideBlockTexture();
								if (length > 0)
								{
									renderer.renderStandardBlock(block, x, y, z);
								}
							}
						}
					}
					if ((cor == 1 && !centerPiece) || (centerPiece && ((corner && blockBack == block && center == 0 && cor != 0) || (corner && blockBack == block && center == 1 && cor != 0) || (cor == 0 && centerSlopeLeft && center == 1) || (cor == 0 && centerSlopeRight && center == 0) || (cor == 1 && centerSlopeRight && center == 1) || (cor == 1 && centerSlopeLeft && center == 0))))
					{
						float oldRot = ((RenderBlocksWithRotation) renderer).rotation;
						((RenderBlocksWithRotation) renderer).rotation = 35f * ((RenderBlocksWithRotation) renderer).rot1;
						((RenderBlocksWithRotation) renderer).yRotation += (float) Math.PI / 4f;
						double xL, yL, zL;
						for (int i = 0; i < ((!centerPiece ? 3 : 2) / tileLength); i++)
						{
							xL = (0.25 - 0.0833) * 2 - 0.01;
							zL = i * tileLength * (1.414 / 2d) - 0.75;
							yL = (outCorner ? 0.6 : 0.5) - (0.15 * tileHeight) + (centerPiece ? 0.1
									: 0) + 0.045 * i + 0.1;
							xL += 0.04165;

							double length = Math.min(1.414 / 2d, 1.5 - zL);
							length = Math.min(length, centerPiece ? 1.4 / 2d : 100);
							renderer.renderFromInside = false;
							if (length > 0)
							{
								renderer.setRenderBounds(xL, yL, zL, xL + 0.25 * tileWidth, yL + 0.125 * tileHeight,
										zL + length * tileLength);
							}
							if (overrideBlockTexture)
								renderer.setOverrideBlockTexture(block.getIcon(0, 16));
							else
								renderer.clearOverrideBlockTexture();
							if (length > 0)
							{
								renderer.renderStandardBlock(block, x, y, z);
							}
							renderer.renderFromInside = false;
							// overrideBlockTexture=!overrideBlockTexture;renderer.setRenderBounds(xL
							// + 0.0625, yL + 0.0625, zL, xL + 0.25 - 0.0625, yL
							// +
							// 0.0625, zL +
							// length);if(overrideBlockTexture)renderer.setOverrideBlockTexture(block.getIcon(0,
							// 16));else
							// renderer.clearOverrideBlockTexture();renderer.renderStandardBlock(block,
							// x, y, z);renderer.setRenderBounds(xL + 0.0625,
							// yL,
							// zL, xL
							// + 0.0625, yL + 0.0625, zL +
							// length);renderer.renderStandardBlock(block, x, y,
							// z);renderer.setRenderBounds(xL + 0.5 - 0.0625,
							// yL,
							// zL, xL
							// + 0.5 - 0.0625, yL + 0.0625, zL +
							// length);if(length>0){renderer.renderStandardBlock(block,
							// x, y, z);}
						}
						((RenderBlocksWithRotation) renderer).yRotation -= (centerPiece ? 1f
								: -1f) * (float) Math.PI / 4f;
						cutDiagonallyUpLeft = false;
						cutDiagonallyDownLeft = false;

					}

				}
				boolean backwardsCorner = false;
				if (center == 0)
				{
					if (yRotChange != 0)
					{
						backwardsCorner = true;
						// ((RenderBlocksWithRotation) renderer).yRotation -=
						// yRotChange;
					}

					((RenderBlocksWithRotation) renderer).staticTexture = false;
					float oldYRot = ((RenderBlocksWithRotation) renderer).yRotation;
					((RenderBlocksWithRotation) renderer).yRotation = 0;
					Block b = world.getBlock(x, y - 1, z);
					if (b != null && b
							.isOpaqueCube() && (b.getMaterial() == Material.rock || b.getMaterial() == Material.wood || b instanceof BlockMudBricks))
					{
						// renderer.overrideBlockTexture = b.getIcon(2,
						// world.getBlockMetadata(x, y - 1, z));
						renderer.clearOverrideBlockTexture();
						((RenderBlocksWithRotation) renderer).rotation = 0;
						double startX, endX;
						startX = world.getBlock(x - 1, y, z) == block ? 0 : 0.001;
						endX = blockRight == block ? 1 : 0.9;
						((RenderBlocksWithRotation) renderer).renderDiagonal = (int) (oldYRot / (Math.PI / 2)) % 4;
						if (corner)
						{
							// If there's a corner, we're also the rotated
							// version
							// of ourselves.
							if (!backwardsCorner)
							{
								((RenderBlocksWithRotation) renderer).renderDiagonal += (int) ((((oldYRot / (Math.PI / 2)) + 1) % 4) + 1) << 2;
							}
							else
							{
								((RenderBlocksWithRotation) renderer).renderDiagonal += (int) ((((oldYRot / (Math.PI / 2)) - 1) % 4) + 1) << 2;
							}
						}
						if (((RenderBlocksWithRotation) renderer).renderDiagonal == 15 && meta == 2)
						{
							((RenderBlocksWithRotation) renderer).renderDiagonal = 7;
						}
						else if (meta == 1 && ((RenderBlocksWithRotation) renderer).renderDiagonal == 10)
						{
							((RenderBlocksWithRotation) renderer).renderDiagonal = 18;
						}
						else if (meta == 0 && ((RenderBlocksWithRotation) renderer).renderDiagonal == 5)
						{
							((RenderBlocksWithRotation) renderer).renderDiagonal = 13;
						}
						if (outCorner)
						{
							((RenderBlocksWithRotation) renderer).renderDiagonal += 32;
						}
						if (centerPiece && world.getBlockMetadata(x, y, z) >= 4)
						{
							if (orMeta == 4)
							{
								((RenderBlocksWithRotation) renderer).renderDiagonal = 5;
								if (corner && blockBack == block && blockBackMeta == 5)
								{
									((RenderBlocksWithRotation) renderer).renderDiagonal += 2;
								}
								if (corner && blockFront == block && blockFrontMeta == 5)
								{
									((RenderBlocksWithRotation) renderer).renderDiagonal += 8;
								}
							}
							else if (orMeta == 5)
							{
								((RenderBlocksWithRotation) renderer).renderDiagonal = 10;
								if (corner && blockBack == block && blockBackMeta == 4)
								{
									((RenderBlocksWithRotation) renderer).renderDiagonal += 4;
								}
								if (corner && blockFront == block && blockFrontMeta == 4)
								{
									((RenderBlocksWithRotation) renderer).renderDiagonal += 1;
								}
							}

							((RenderBlocksWithRotation) renderer).renderDiagonal = ((RenderBlocksWithRotation) renderer).renderDiagonal << 6;
						}
						((RenderBlocksWithRotation) renderer).yRotation = 0;
						renderer.setRenderBounds(0, 1, 0, 1, 2, 1);
						renderer.renderStandardBlock(world.getBlock(x, y - 1, z), x, y - 1, z);
						((RenderBlocksWithRotation) renderer).renderDiagonal = -1;
						/*
						 * for (int i = 0; i < 8; i++) { if(oldYRot == 0) {
						 * renderer.setRenderBounds(startX, 0, 0.125 * (i),
						 * endX, i * (1d / 8), 0.125 * ( i) + 0.125); } else
						 * if(oldYRot == (float)Math.PI/2f) {
						 * renderer.setRenderBounds(0.125 * (i), 0, startX,
						 * 0.125 * ( i) + 0.125, i * (1d / 8), endX); }
						 * renderer.renderStandardBlock(b, x, y, z);
						 * 
						 * } if (endX == 0.9) { for (int i = 0; i < 16; i++) {
						 * if(oldYRot == 0) { renderer.setRenderBounds(endX, 0,
						 * 0.0625 * (i), 1, i * (1d / 16), 0.0625 * (i) +
						 * 0.0625); } else if(oldYRot == (float)Math.PI/2f) {
						 * renderer.setRenderBounds(0.0625 * (i), 0, endX,
						 * 0.0625 * ( i) + 0.0625, i * (1d / 16), 0.99); }
						 * renderer.renderStandardBlock(b, x, y, z); } }
						 */
					}

				}
			}
			renderer.renderAllFaces = false;
		}
		else
		{
			renderer = new RenderBlocksWithRotation(renderer);
			((RenderBlocksWithRotation)renderer).yRotation =(float)( Math.PI +  (meta * (Math.PI/2)));
			
			((RenderBlocksWithRotation) renderer).staticTexture = true;
			Vec3 xV = Vec3.createVectorHelper(1, 0, 0);
			Vec3 zV = Vec3.createVectorHelper(0, 0, 1);

			xV.rotateAroundY(((RenderBlocksWithRotation) renderer).yRotation);
			zV.rotateAroundY(((RenderBlocksWithRotation) renderer).yRotation);
			xV.xCoord = Math.round(xV.xCoord);
			xV.yCoord = Math.round(xV.yCoord);
			xV.zCoord = Math.round(xV.zCoord);
			zV.xCoord = Math.round(zV.xCoord);
			zV.yCoord = Math.round(zV.yCoord);
			zV.zCoord = Math.round(zV.zCoord);

			Block blockFront = world.getBlock(x - (int) zV.xCoord, y, z - (int) zV.zCoord);
			Block blockBack = world.getBlock(x + (int) zV.xCoord, y, z + (int) zV.zCoord);
			
			int blockFrontMeta,blockBackMeta;
			blockFrontMeta = world.getBlockMetadata(x - (int) zV.xCoord, y, z - (int) zV.zCoord);
			 blockBackMeta = world.getBlockMetadata(x + (int) zV.xCoord, y, z + (int) zV.zCoord);
			double xP,yP,zP;
			xP = 0d;
			yP = -0.25d;
			zP = 0.5d;
			renderer.setRenderBounds(xP, yP, zP,xP+ 1F, yP+ 1.414F,zP+ 0.4F);
			// GL11.glRotatef(-30, 0, 1, 0);
			renderer.renderAllFaces = true;
			((RenderBlocksWithRotation)renderer).rotation = (float) (Math.PI/4);
			// rotate(renderer, 1);
			renderer.overrideBlockTexture = block.getIcon(124, 0);
			renderer.renderStandardBlock(block, x, y, z);
			if(blockFront == block && (blockFrontMeta+2)%4 == meta && meta  < 2)
			{
				((RenderBlocksWithRotation)renderer).rotation = 0;

				renderer.setRenderBounds(-0.01, 0.9, -0.31,1.01, 1.25,0.31);
				renderer.renderStandardBlock(block, x, y, z);
			}
			
			((RenderBlocksWithRotation) renderer).staticTexture = false;
			float oldYRot = ((RenderBlocksWithRotation) renderer).yRotation;
			((RenderBlocksWithRotation) renderer).yRotation = 0;
			Block b = world.getBlock(x, y - 1, z);
			if (b != null && b
					.isOpaqueCube() && (b.getMaterial() == Material.rock || b.getMaterial() == Material.wood || b instanceof BlockMudBricks))
			{
				// renderer.overrideBlockTexture = b.getIcon(2,
				// world.getBlockMetadata(x, y - 1, z));
				renderer.clearOverrideBlockTexture();
				((RenderBlocksWithRotation) renderer).rotation = 0;
				double startX, endX;
				startX = world.getBlock(x - 1, y, z) == block ? 0 : 0.001;
				endX =  0.9;
				((RenderBlocksWithRotation) renderer).renderDiagonal = (int) (oldYRot / (Math.PI / 2)) % 4;
				((RenderBlocksWithRotation) renderer).renderDiagonal = meta;
				((RenderBlocksWithRotation) renderer).yRotation = 0;
				
				renderer.setRenderBounds(0, 1, 0, 1, 2, 1);
				renderer.renderStandardBlock(world.getBlock(x, y - 1, z), x, y - 1, z);
				((RenderBlocksWithRotation) renderer).renderDiagonal = -1;
			}
			renderer.clearOverrideBlockTexture();
		}
		return true;

	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getRenderId()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
