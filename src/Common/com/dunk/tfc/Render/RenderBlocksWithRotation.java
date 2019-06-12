package com.dunk.tfc.Render;

import org.lwjgl.opengl.GL11;

import com.dunk.tfc.api.TFCBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;

public class RenderBlocksWithRotation extends RenderBlocks
{
	public RenderBlocksWithRotation(RenderBlocks old)
	{
		this.blockAccess = old.blockAccess;
	}

	public static double inv16 = 1d / 16d;
	public static double inv32 = 1d / 32d;

	public float rotation = (float) (-45d * (Math.PI / 180d));
	public float rot30 = (float) (30d * (Math.PI / 180d));

	public float rot1 = (float) (Math.PI / 180d);

	public float yRotation = 0;

	public void update(RenderBlocks old)
	{
		this.blockAccess = old.blockAccess;
	}

	public void renderPalmFrond(double x, double y, double z, double yRotation, double xRotation, double scale)
	{
		Tessellator tessellator = Tessellator.instance;

		int textureOffsetX = 0, textureOffsetY = 0;
		IIcon frondA = TFCBlocks.fauxPalm.getIcon(0, 0);
		IIcon frondB = TFCBlocks.fauxPalm.getIcon(0, 1);
		IIcon frondAD = TFCBlocks.fauxPalm.getIcon(0, 2);
		IIcon frondBD = TFCBlocks.fauxPalm.getIcon(0, 3);
		double AminU, AminV, AmaxU, AmaxV, BminU, BminV, BmaxU, BmaxV,ADminU, ADminV, ADmaxU, ADmaxV, BDminU, BDminV, BDmaxU, BDmaxV;
		AminU = frondA.getMinU();
		AminV = frondA.getMinV();
		AmaxU = frondA.getMaxU();
		AmaxV = frondA.getMaxV();
		
		BminU = frondB.getMinU();
		BminV = frondB.getMinV();
		BmaxU = frondB.getMaxU();
		BmaxV = frondB.getMaxV();
		
		ADminU = frondAD.getMinU();
		ADminV = frondAD.getMinV();
		ADmaxU = frondAD.getMaxU();
		ADmaxV = frondAD.getMaxV();
		
		BDminU = frondBD.getMinU();
		BDminV = frondBD.getMinV();
		BDmaxU = frondBD.getMaxU();
		BDmaxV = frondBD.getMaxV();
		double originX = 0d;
		double originY = 0.1d * inv16;
		double originZ = 4d* inv16;
		Vec3[] vertexPositions = new Vec3[16];

		// vertexPositions = new PositionTextureVertex[16];
		// quadList = new TexturedQuad[4];
		double maxX = (originX +16) * inv16;
		double maxZ = (originZ*16 + 32) * inv16;
		double maxX2 = (originX - 16) * inv16;

		// First part of frond
		double[] vert0 = new double[] { originX, originY + 8d * inv16, originZ, AminU, AminV };
		double[] vert1 = new double[] { maxX - 2d * inv16, originY + 4d * inv16, originZ, AmaxU, AminV };
		double[] vert2 = new double[] { maxX, originY + 6d * inv16, maxZ, AmaxU, AmaxV };
		double[] vert3 = new double[] { originX, originY + 12d * inv16, maxZ, AminU, AmaxV };
		
		double[] vert0D = new double[] { originX, originY + 8d * inv16, originZ, ADminU, ADminV };
		double[] vert1D = new double[] { maxX - 2d * inv16, originY + 4d * inv16, originZ, ADmaxU, ADminV };
		double[] vert2D = new double[] { maxX, originY + 6d * inv16, maxZ, ADmaxU, ADmaxV };
		double[] vert3D = new double[] { originX, originY + 12d * inv16, maxZ, ADminU, ADmaxV };

		vertexPositions[0] = Vec3.createVectorHelper(vert0[0], vert0[1], vert0[2]);
		vertexPositions[1] = Vec3.createVectorHelper(vert1[0], vert1[1], vert1[2]);
		vertexPositions[2] = Vec3.createVectorHelper(vert2[0], vert2[1], vert2[2]);
		vertexPositions[3] = Vec3.createVectorHelper(vert3[0], vert3[1], vert3[2]);


		double[] vert02 = new double[] { originX, ((originY + 12d * inv16) + (originY + 8d * inv16)) * 0.5d,
				maxZ * 0.5d, BminU, BminV };
		double[] vert12 = new double[] { maxX - 2d * inv16, ((originY + 6d * inv16) + (originY + 4d * inv16)) * 0.5d,
				maxZ * 0.5d, BmaxU, BminV };
		double[] vert22 = new double[] { maxX, originY + 6d * inv16, maxZ, BmaxU, BmaxV };
		double[] vert32 = new double[] { originX, originY + 12d * inv16, maxZ, BminU, BmaxV };
		
		double[] vert02D = new double[] { originX, ((originY + 12d * inv16) + (originY + 8d * inv16)) * 0.5d,
				maxZ * 0.5d, BDminU, BDminV };
		double[] vert12D = new double[] { maxX - 2d * inv16, ((originY + 6d * inv16) + (originY + 4d * inv16)) * 0.5d,
				maxZ * 0.5d, BDmaxU, BDminV };
		double[] vert22D = new double[] { maxX, originY + 6d * inv16, maxZ, BDmaxU, BDmaxV };
		double[] vert32D = new double[] { originX, originY + 12d * inv16, maxZ, BDminU, BDmaxV };

		// Second part of frond
		double[] vert0b = new double[] { originX, originY + 8d * inv16, originZ, AminU, AminV };
		double[] vert1b = new double[] { maxX2 + 2d * inv16, originY + 4d * inv16, originZ, AmaxU, AminV };
		double[] vert2b = new double[] { maxX2, originY + 6d * inv16, maxZ, AmaxU, AmaxV };
		double[] vert3b = new double[] { originX, originY + 12d * inv16, maxZ, AminU, AmaxV };

		double[] vert02b = new double[] { originX, originY + 8d * inv16, originZ, BminU, BminV };
		double[] vert12b = new double[] { maxX2 + 2d * inv16, originY + 4d * inv16, originZ, BmaxU, BminV };
		double[] vert22b = new double[] { maxX2, originY + 6d * inv16, maxZ, BmaxU, BmaxV };
		double[] vert32b = new double[] { originX, originY + 12d * inv16, maxZ, BminU, BmaxV };
		

		vertexPositions[4] = Vec3.createVectorHelper(vert0b[0], vert0b[1], vert0b[2]);
		vertexPositions[5] = Vec3.createVectorHelper(vert1b[0], vert1b[1], vert1b[2]);
		vertexPositions[6] = Vec3.createVectorHelper(vert2b[0], vert2b[1], vert2b[2]);
		vertexPositions[7] = Vec3.createVectorHelper(vert3b[0], vert3b[1], vert3b[2]);

		// Third part of frond
		double[] vert0c = new double[] { originX, originY + 12d * inv16, maxZ, AminU, AminV };
		double[] vert1c = new double[] { maxX, originY + 6d * inv16, maxZ, AmaxU, AminV };
		double[] vert2c = new double[] { maxX - 6 * inv16, originY + 2d * inv16, maxZ * 2, AmaxU, AmaxV };
		double[] vert3c = new double[] { originX, originY + 6d * inv16, maxZ * 2, AminU, AmaxV };

		double[] vert02c = new double[] { originX, originY + 12d * inv16, maxZ, BminU, BminV };
		double[] vert12c = new double[] { maxX, originY + 6d * inv16, maxZ, BmaxU, BminV };
		double[] vert22c = new double[] { maxX - 6 * inv16, originY + 2d * inv16, maxZ * 2, BmaxU, BmaxV };
		double[] vert32c = new double[] { originX, originY + 6d * inv16, maxZ * 2, BminU, BmaxV };

		vertexPositions[8] = Vec3.createVectorHelper(vert0c[0], vert0c[1], vert0c[2]);
		vertexPositions[9] = Vec3.createVectorHelper(vert1c[0], vert1c[1], vert1c[2]);
		vertexPositions[10] = Vec3.createVectorHelper(vert2c[0], vert2c[1], vert2c[2]);
		vertexPositions[11] = Vec3.createVectorHelper(vert3c[0], vert3c[1], vert3c[2]);

		// Fourth part of frond
		double[] vert0d = new double[] { originX, originY + 12d * inv16, maxZ, AminU, AminV };
		double[] vert1d = new double[] { maxX2, originY + 6d * inv16, maxZ, AmaxU, AminV };
		double[] vert2d = new double[] { maxX2 + 6d * inv16, originY + 2d * inv16, maxZ * 2, AmaxU, AmaxV };
		double[] vert3d = new double[] { originX, originY + 6d * inv16, maxZ * 2, AminU, AmaxV };

		double[] vert02d = new double[] { originX, originY + 12d * inv16, maxZ, BminU, BminV };
		double[] vert12d = new double[] { maxX2, originY + 6d * inv16, maxZ, BmaxU, BminV };
		double[] vert22d = new double[] { maxX2 + 6d * inv16, originY + 2d * inv16, maxZ * 2, BmaxU, BmaxV };
		double[] vert32d = new double[] { originX, originY + 6d * inv16, maxZ * 2, BminU, BmaxV };

		vertexPositions[12] = Vec3.createVectorHelper(vert0d[0], vert0d[1], vert0d[2]);
		vertexPositions[13] = Vec3.createVectorHelper(vert1d[0], vert1d[1], vert1d[2]);
		vertexPositions[14] = Vec3.createVectorHelper(vert2d[0], vert2d[1], vert2d[2]);
		vertexPositions[15] = Vec3.createVectorHelper(vert3d[0], vert3d[1], vert3d[2]);

		for (int i = 0; i < 16; i++)
		{
			vertexPositions[i].rotateAroundX((float) xRotation);
		}
		for (int i = 0; i < 16; i++)
		{
			vertexPositions[i].rotateAroundY((float) yRotation);
		}
		for (int i = 0; i < 16; i++)
		{
			vertexPositions[i].xCoord *= scale;
			vertexPositions[i].yCoord *= scale;
			vertexPositions[i].zCoord *= scale;
		}

		int x2 = textureOffsetX + 0;
		int x3 = textureOffsetX + 32;

		int y1 = textureOffsetY + 0;
		int y2 = textureOffsetY + 16;
		GL11.glDisable(GL11.GL_CULL_FACE);

		// 0,3,2,1
		doQuads(tessellator, x, y, z, vertexPositions[0], vertexPositions[3], vertexPositions[2], vertexPositions[1],
				vert0, vert3, vert2, vert1, vert02, vert32, vert22, vert12, scale, true);
		doQuads(tessellator, x, y, z, vertexPositions[1], vertexPositions[2], vertexPositions[3], vertexPositions[0],
				vert1D, vert2D, vert3D, vert0D, vert12D, vert22D, vert32D, vert02D, scale, true);

		// 7,4,5,6
		doQuads(tessellator, x, y, z, vertexPositions[7], vertexPositions[4], vertexPositions[5], vertexPositions[6],
				vert3b, vert0b, vert1b, vert2b, vert32b, vert02b, vert12b, vert22b, scale, false);
		doQuads(tessellator, x, y, z, vertexPositions[6], vertexPositions[5], vertexPositions[4], vertexPositions[7],
				vert2D, vert1D, vert0D, vert3D, vert22D, vert12D, vert02D, vert32D, scale, false);

		// 8,11,10,9
		doQuads(tessellator, x, y, z, vertexPositions[8], vertexPositions[11], vertexPositions[10], vertexPositions[9],
				vert0c, vert3c, vert2c, vert1c, vert02c, vert32c, vert22c, vert12c, scale, true);
		doQuads(tessellator, x, y, z, vertexPositions[9], vertexPositions[10], vertexPositions[11], vertexPositions[8],
				vert1D, vert2D, vert3D, vert0D, vert12D, vert22D, vert32D, vert02D, scale, true);

		// 15,12,13,14
		doQuads(tessellator, x, y, z, vertexPositions[15], vertexPositions[12], vertexPositions[13],
				vertexPositions[14], vert3d, vert0d, vert1d, vert2d, vert32d, vert02d, vert12d, vert22d, scale, false);
		doQuads(tessellator, x, y, z, vertexPositions[14], vertexPositions[13], vertexPositions[12],
				vertexPositions[15], vert2D, vert1D, vert0D, vert3D, vert22D, vert12D, vert02D, vert32D, scale, false);
		/*
		 * quadList[0] = new TexturedQuad(new PositionTextureVertex[]
		 * {vertexPositions[0], vertexPositions[3], vertexPositions[2],
		 * vertexPositions[1]}, x2, y1, x3, y2, 32, 16); // long quadList[1] =
		 * new TexturedQuad(new PositionTextureVertex[] {vertexPositions[7],
		 * vertexPositions[4], vertexPositions[5], vertexPositions[6]}, x2, y1,
		 * x3, y2, 32, 16); // long quadList[2] = new TexturedQuad(new
		 * PositionTextureVertex[] {vertexPositions[8], vertexPositions[11],
		 * vertexPositions[10], vertexPositions[9]}, x2, y1, x3, y2, 32, 16); //
		 * long quadList[3] = new TexturedQuad(new PositionTextureVertex[]
		 * {vertexPositions[15], vertexPositions[12], vertexPositions[13],
		 * vertexPositions[14]}, x2, y1, x3, y2, 32, 16); // long
		 */

		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	// Now, we'll handle the crappy stuff in here
	/**
	 * 
	 * @param tessellator
	 * @param x
	 * @param y
	 * @param z
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param aUV
	 *            the first 3 are the same as a, etc.
	 * @param bUV
	 * @param cUV
	 * @param dUV
	 */
	private void doQuads(Tessellator tessellator, double x, double y, double z, Vec3 a, Vec3 b, Vec3 c, Vec3 d,
			double[] aUV, double[] bUV, double[] cUV, double[] dUV, double[] aUV2, double[] bUV2, double[] cUV2,
			double[] dUV2, double scale, boolean inverse)
	{
		// If inverse, we just do things reversed
		// If inverse, a and d are big, otherwise b and c are big
		// So for example 3012, 0321
		// If our scale is 1, we'd be at the right height. If it's 0.5, we're
		// half too short. At 0.25, it's 0.75 too short. At 2? it would be twice
		// too big. So our thing should be
		// That if we're at 0.5, we go up by 0.5. At 2, we go down by 1.
		double yTranslate = (1d - scale) / 2d;

		if (enableAO)
		{
			tessellator.setColorOpaque_F(colorRedTopLeft, colorGreenTopLeft, colorBlueTopLeft);
			tessellator.setBrightness(brightnessTopLeft);
		}
		tessellator.addVertexWithUV((inverse ? a.xCoord : (a.xCoord + b.xCoord) * 0.5) + 0.5 + x,
				(inverse ? a.yCoord : (a.yCoord + b.yCoord) * 0.5) + yTranslate + y,
				(inverse ? a.zCoord : (a.zCoord + b.zCoord) * 0.5) + 0.5 + z, aUV[3], aUV[4]);

		if (enableAO)
		{
			tessellator.setColorOpaque_F(colorRedTopLeft, colorGreenTopLeft, colorBlueTopLeft);
			tessellator.setBrightness(brightnessTopLeft);
		}
		tessellator.addVertexWithUV((inverse ? (b.xCoord + a.xCoord) * 0.5 : b.xCoord) + 0.5 + x,
				(inverse ? (b.yCoord + a.yCoord) * 0.5 : b.yCoord) + yTranslate + y,
				(inverse ? (b.zCoord + a.zCoord) * 0.5 : b.zCoord) + 0.5 + z, bUV[3], bUV[4]);

		if (enableAO)
		{
			tessellator.setColorOpaque_F(colorRedTopLeft, colorGreenTopLeft, colorBlueTopLeft);
			tessellator.setBrightness(brightnessTopLeft);
		}
		tessellator.addVertexWithUV((inverse ? (c.xCoord + d.xCoord) * 0.5 : c.xCoord) + 0.5 + x,
				(inverse ? (c.yCoord + d.yCoord) * 0.5 : c.yCoord) + yTranslate + y,
				(inverse ? (c.zCoord + d.zCoord) * 0.5 : c.zCoord) + 0.5 + z, cUV[3], cUV[4]);

		if (enableAO)
		{
			tessellator.setColorOpaque_F(colorRedTopLeft, colorGreenTopLeft, colorBlueTopLeft);
			tessellator.setBrightness(brightnessTopLeft);
		}
		tessellator.addVertexWithUV((inverse ? d.xCoord : (d.xCoord + c.xCoord) * 0.5) + 0.5 + x,
				(inverse ? d.yCoord : (d.yCoord + c.yCoord) * 0.5) + yTranslate + y,
				(inverse ? d.zCoord : (d.zCoord + c.zCoord) * 0.5) + 0.5 + z, dUV[3], dUV[4]);

		// The second half
		if (enableAO)
		{
			tessellator.setColorOpaque_F(colorRedTopLeft, colorGreenTopLeft, colorBlueTopLeft);
			tessellator.setBrightness(brightnessTopLeft);
		}
		tessellator.addVertexWithUV((!inverse ? a.xCoord : (a.xCoord + b.xCoord) * 0.5) + 0.5 + x,
				(!inverse ? a.yCoord : (a.yCoord + b.yCoord) * 0.5) + yTranslate + y,
				(!inverse ? a.zCoord : (a.zCoord + b.zCoord) * 0.5) + 0.5 + z, aUV2[3], aUV2[4]);

		if (enableAO)
		{
			tessellator.setColorOpaque_F(colorRedTopLeft, colorGreenTopLeft, colorBlueTopLeft);
			tessellator.setBrightness(brightnessTopLeft);
		}
		tessellator.addVertexWithUV((!inverse ? (b.xCoord + a.xCoord) * 0.5 : b.xCoord) + 0.5 + x,
				(!inverse ? (b.yCoord + a.yCoord) * 0.5 : b.yCoord) + yTranslate + y,
				(!inverse ? (b.zCoord + a.zCoord) * 0.5 : b.zCoord) + 0.5 + z, bUV2[3], bUV2[4]);

		if (enableAO)
		{
			tessellator.setColorOpaque_F(colorRedTopLeft, colorGreenTopLeft, colorBlueTopLeft);
			tessellator.setBrightness(brightnessTopLeft);
		}
		tessellator.addVertexWithUV((!inverse ? (c.xCoord + d.xCoord) * 0.5 : c.xCoord) + 0.5 + x,
				(!inverse ? (c.yCoord + d.yCoord) * 0.5 : c.yCoord) + yTranslate + y,
				(!inverse ? (c.zCoord + d.zCoord) * 0.5 : c.zCoord) + 0.5 + z, cUV2[3], cUV2[4]);

		if (enableAO)
		{
			tessellator.setColorOpaque_F(colorRedTopLeft, colorGreenTopLeft, colorBlueTopLeft);
			tessellator.setBrightness(brightnessTopLeft);
		}
		tessellator.addVertexWithUV((!inverse ? d.xCoord : (d.xCoord + c.xCoord) * 0.5) + 0.5 + x,
				(!inverse ? d.yCoord : (d.yCoord + c.yCoord) * 0.5) + yTranslate + y,
				(!inverse ? d.zCoord : (d.zCoord + c.zCoord) * 0.5) + 0.5 + z, dUV2[3], dUV2[4]);
	}

	// We use this class to allow us to rotate blocks before we render them.
	// we'll set up a way to store the rotation and then we'll get rid of the
	// rotation after
	// A rotation is around the center of the block
	// So when we have a rotation, all we're doing is

	/**
	 * Renders the given texture to the bottom face of the block. Args: block,
	 * x, y, z, texture
	 */
	public void renderFaceYNeg(Block p_147768_1_, double x, double y, double z, IIcon p_147768_8_)
	{
		Tessellator tessellator = Tessellator.instance;

		if (p_147768_1_ == TFCBlocks.fauxPalm)
		{
			int i,j,j2;
			for (int n = 0; n < 16; n++)
			{
				
				renderPalmFrond(x, y, z,  (rot30)+(rot30 * (n/4)*0.8) + (3*rot30 * n), (-rot30*1.5) + ((n/4)*(rot30)), 1);
			}
			return;
		}

		if (this.hasOverrideBlockTexture())
		{
			p_147768_8_ = this.overrideBlockTexture;
		}

		double d3 = (double) p_147768_8_.getInterpolatedU(this.renderMinX * 16.0D);
		double d4 = (double) p_147768_8_.getInterpolatedU(this.renderMaxX * 16.0D);
		double d5 = (double) p_147768_8_.getInterpolatedV(this.renderMinZ * 16.0D);
		double d6 = (double) p_147768_8_.getInterpolatedV(this.renderMaxZ * 16.0D);

		if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D)
		{
			d3 = (double) p_147768_8_.getMinU();
			d4 = (double) p_147768_8_.getMaxU();
		}

		if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D)
		{
			d5 = (double) p_147768_8_.getMinV();
			d6 = (double) p_147768_8_.getMaxV();
		}

		double d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (this.uvRotateBottom == 2)
		{
			d3 = (double) p_147768_8_.getInterpolatedU(this.renderMinZ * 16.0D);
			d5 = (double) p_147768_8_.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
			d4 = (double) p_147768_8_.getInterpolatedU(this.renderMaxZ * 16.0D);
			d6 = (double) p_147768_8_.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		}
		else if (this.uvRotateBottom == 1)
		{
			d3 = (double) p_147768_8_.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
			d5 = (double) p_147768_8_.getInterpolatedV(this.renderMinX * 16.0D);
			d4 = (double) p_147768_8_.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
			d6 = (double) p_147768_8_.getInterpolatedV(this.renderMaxX * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		}
		else if (this.uvRotateBottom == 3)
		{
			d3 = (double) p_147768_8_.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
			d4 = (double) p_147768_8_.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
			d5 = (double) p_147768_8_.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
			d6 = (double) p_147768_8_.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		Vec3 xyz = Vec3.createVectorHelper(this.renderMinX - 0.5, this.renderMinY - 0.5, this.renderMinZ - 0.5);
		Vec3 Xyz = Vec3.createVectorHelper(this.renderMaxX - 0.5, this.renderMinY - 0.5, this.renderMinZ - 0.5);
		Vec3 xyZ = Vec3.createVectorHelper(this.renderMinX - 0.5, this.renderMinY - 0.5, this.renderMaxZ - 0.5);
		Vec3 XyZ = Vec3.createVectorHelper(this.renderMaxX - 0.5, this.renderMinY - 0.5, this.renderMaxZ - 0.5);

		xyz.rotateAroundX(rotation);
		xyZ.rotateAroundX(rotation);
		Xyz.rotateAroundX(rotation);
		XyZ.rotateAroundX(rotation);

		xyz.rotateAroundY(yRotation);
		xyZ.rotateAroundY(yRotation);
		Xyz.rotateAroundY(yRotation);
		XyZ.rotateAroundY(yRotation);

		// double d11 = x + this.renderMinX;
		// double d12 = x + this.renderMaxX;
		// double d13 = y + this.renderMinY;
		// double d14 = z + this.renderMinZ;
		// double d15 = z + this.renderMaxZ;

		/*
		 * double d11 = x + 0.5 + tempVector.xCoord; double d12 = x + 0.5 +
		 * tempVector2.xCoord; double d13 = y + 0.5 + tempVector.yCoord; double
		 * d14 = z + 0.5 + tempVector.zCoord; double d15 = z + 0.5 +
		 * tempVector2.zCoord;
		 * 
		 * if (this.renderFromInside) { d11 = x + 0.5 + tempVector2.xCoord; d12
		 * = x + 0.5 + tempVector.xCoord; }
		 */

		/*
		 * if (this.enableAO) {
		 * tessellator.setColorOpaque_F(this.colorRedTopLeft,
		 * this.colorGreenTopLeft, this.colorBlueTopLeft);
		 * tessellator.setBrightness(this.brightnessTopLeft);
		 * tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
		 * tessellator.setColorOpaque_F(this.colorRedBottomLeft,
		 * this.colorGreenBottomLeft, this.colorBlueBottomLeft);
		 * tessellator.setBrightness(this.brightnessBottomLeft);
		 * tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
		 * tessellator.setColorOpaque_F(this.colorRedBottomRight,
		 * this.colorGreenBottomRight, this.colorBlueBottomRight);
		 * tessellator.setBrightness(this.brightnessBottomRight);
		 * tessellator.addVertexWithUV(d12, d13, d14, d7, d9);
		 * tessellator.setColorOpaque_F(this.colorRedTopRight,
		 * this.colorGreenTopRight, this.colorBlueTopRight);
		 * tessellator.setBrightness(this.brightnessTopRight);
		 * tessellator.addVertexWithUV(d12, d13, d15, d4, d6); } else {
		 * tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
		 * tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
		 * tessellator.addVertexWithUV(d12, d13, d14, d7, d9);
		 * tessellator.addVertexWithUV(d12, d13, d15, d4, d6); }
		 */
		GL11.glDisable(GL11.GL_CULL_FACE);
		if (this.enableAO)
		{
			tessellator.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
			tessellator.setBrightness(this.brightnessTopLeft);
			tessellator.addVertexWithUV((!renderFromInside ? xyZ.xCoord : XyZ.xCoord) + 0.5 + x, xyZ.yCoord + 0.5 + y,
					xyZ.zCoord + 0.5 + z, d8, d10); // xyZ
			tessellator.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator.setBrightness(this.brightnessBottomLeft);
			tessellator.addVertexWithUV((!renderFromInside ? xyz.xCoord : Xyz.xCoord) + 0.5 + x, xyz.yCoord + 0.5 + y,
					xyz.zCoord + 0.5 + z, d3, d5); // xyz
			tessellator.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight,
					this.colorBlueBottomRight);
			tessellator.setBrightness(this.brightnessBottomRight);
			tessellator.addVertexWithUV((!renderFromInside ? Xyz.xCoord : xyz.xCoord) + 0.5 + x, Xyz.yCoord + 0.5 + y,
					Xyz.zCoord + 0.5 + z, d7, d9); // Xyz
			tessellator.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator.setBrightness(this.brightnessTopRight);
			tessellator.addVertexWithUV((!renderFromInside ? XyZ.xCoord : xyZ.xCoord) + 0.5 + x, XyZ.yCoord + 0.5 + y,
					XyZ.zCoord + 0.5 + z, d4, d6); // XyZ
		}
		else
		{
			tessellator.addVertexWithUV((!renderFromInside ? xyZ.xCoord : XyZ.xCoord) + 0.5 + x, xyZ.yCoord + 0.5 + y,
					xyZ.zCoord + 0.5 + z, d8, d10);
			tessellator.addVertexWithUV((!renderFromInside ? xyz.xCoord : Xyz.xCoord) + 0.5 + x, xyz.yCoord + 0.5 + y,
					xyz.zCoord + 0.5 + z, d3, d5);
			tessellator.addVertexWithUV((!renderFromInside ? Xyz.xCoord : xyz.xCoord) + 0.5 + x, Xyz.yCoord + 0.5 + y,
					Xyz.zCoord + 0.5 + z, d7, d9);
			tessellator.addVertexWithUV((!renderFromInside ? XyZ.xCoord : xyZ.xCoord) + 0.5 + x, XyZ.yCoord + 0.5 + y,
					XyZ.zCoord + 0.5 + z, d4, d6);
		}
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	/**
	 * Renders the given texture to the top face of the block. Args: block, x,
	 * y, z, texture
	 */
	public void renderFaceYPos(Block p_147806_1_, double x, double y, double z, IIcon p_147806_8_)
	{
		Tessellator tessellator = Tessellator.instance;

		if (p_147806_1_ == TFCBlocks.fauxPalm)
		{
			return;
		}

		if (this.hasOverrideBlockTexture())
		{
			p_147806_8_ = this.overrideBlockTexture;
		}

		double d3 = (double) p_147806_8_.getInterpolatedU(this.renderMinX * 16.0D);
		double d4 = (double) p_147806_8_.getInterpolatedU(this.renderMaxX * 16.0D);
		double d5 = (double) p_147806_8_.getInterpolatedV(this.renderMinZ * 16.0D);
		double d6 = (double) p_147806_8_.getInterpolatedV(this.renderMaxZ * 16.0D);

		if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D)
		{
			d3 = (double) p_147806_8_.getMinU();
			d4 = (double) p_147806_8_.getMaxU();
		}

		if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D)
		{
			d5 = (double) p_147806_8_.getMinV();
			d6 = (double) p_147806_8_.getMaxV();
		}

		double d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (this.uvRotateTop == 1)
		{
			d3 = (double) p_147806_8_.getInterpolatedU(this.renderMinZ * 16.0D);
			d5 = (double) p_147806_8_.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
			d4 = (double) p_147806_8_.getInterpolatedU(this.renderMaxZ * 16.0D);
			d6 = (double) p_147806_8_.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		}
		else if (this.uvRotateTop == 2)
		{
			d3 = (double) p_147806_8_.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
			d5 = (double) p_147806_8_.getInterpolatedV(this.renderMinX * 16.0D);
			d4 = (double) p_147806_8_.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
			d6 = (double) p_147806_8_.getInterpolatedV(this.renderMaxX * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		}
		else if (this.uvRotateTop == 3)
		{
			d3 = (double) p_147806_8_.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
			d4 = (double) p_147806_8_.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
			d5 = (double) p_147806_8_.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
			d6 = (double) p_147806_8_.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		Vec3 xYz = Vec3.createVectorHelper(this.renderMinX - 0.5, this.renderMaxY - 0.5, this.renderMinZ - 0.5);
		Vec3 XYz = Vec3.createVectorHelper(this.renderMaxX - 0.5, this.renderMaxY - 0.5, this.renderMinZ - 0.5);
		Vec3 xYZ = Vec3.createVectorHelper(this.renderMinX - 0.5, this.renderMaxY - 0.5, this.renderMaxZ - 0.5);
		Vec3 XYZ = Vec3.createVectorHelper(this.renderMaxX - 0.5, this.renderMaxY - 0.5, this.renderMaxZ - 0.5);

		xYz.rotateAroundX(rotation);
		xYZ.rotateAroundX(rotation);
		XYz.rotateAroundX(rotation);
		XYZ.rotateAroundX(rotation);

		xYz.rotateAroundY(yRotation);
		xYZ.rotateAroundY(yRotation);
		XYz.rotateAroundY(yRotation);
		XYZ.rotateAroundY(yRotation);

		/*
		 * double d11 = p_147806_2_ + this.renderMinX; double d12 = p_147806_2_
		 * + this.renderMaxX; double d13 = p_147806_4_ + this.renderMaxY; double
		 * d14 = p_147806_6_ + this.renderMinZ; double d15 = p_147806_6_ +
		 * this.renderMaxZ;
		 * 
		 * if (this.renderFromInside) { d11 = p_147806_2_ + this.renderMaxX; d12
		 * = p_147806_2_ + this.renderMinX; }
		 */
		GL11.glDisable(GL11.GL_CULL_FACE);
		if (this.enableAO)
		{
			tessellator.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
			tessellator.setBrightness(this.brightnessTopLeft);
			tessellator.addVertexWithUV((renderFromInside ? xYZ.xCoord : XYZ.xCoord) + 0.5 + x, XYZ.yCoord + 0.5 + y,
					XYZ.zCoord + 0.5 + z, d4, d6); // XYZ
			tessellator.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator.setBrightness(this.brightnessBottomLeft);
			tessellator.addVertexWithUV((renderFromInside ? xYz.xCoord : XYz.xCoord) + 0.5 + x, XYz.yCoord + 0.5 + y,
					XYz.zCoord + 0.5 + z, d7, d9);// XYz
			tessellator.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight,
					this.colorBlueBottomRight);
			tessellator.setBrightness(this.brightnessBottomRight);
			tessellator.addVertexWithUV((renderFromInside ? XYz.xCoord : xYz.xCoord) + 0.5 + x, xYz.yCoord + 0.5 + y,
					xYz.zCoord + 0.5 + z, d3, d5);// xYz
			tessellator.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator.setBrightness(this.brightnessTopRight);
			tessellator.addVertexWithUV((renderFromInside ? XYZ.xCoord : xYZ.xCoord) + 0.5 + x, xYZ.yCoord + 0.5 + y,
					xYZ.zCoord + 0.5 + z, d8, d10);// xYZ
		}
		else
		{
			tessellator.addVertexWithUV((renderFromInside ? xYZ.xCoord : XYZ.xCoord) + 0.5 + x, XYZ.yCoord + 0.5 + y,
					XYZ.zCoord + 0.5 + z, d4, d6); // XYZ
			tessellator.addVertexWithUV((renderFromInside ? xYz.xCoord : XYz.xCoord) + 0.5 + x, XYz.yCoord + 0.5 + y,
					XYz.zCoord + 0.5 + z, d7, d9);// XYz
			tessellator.addVertexWithUV((renderFromInside ? XYz.xCoord : xYz.xCoord) + 0.5 + x, xYz.yCoord + 0.5 + y,
					xYz.zCoord + 0.5 + z, d3, d5);// xYz
			tessellator.addVertexWithUV((renderFromInside ? XYZ.xCoord : xYZ.xCoord) + 0.5 + x, xYZ.yCoord + 0.5 + y,
					xYZ.zCoord + 0.5 + z, d8, d10);// xYZ
		}
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	/**
	 * Renders the given texture to the north (z-negative) face of the block.
	 * Args: block, x, y, z, texture
	 */
	public void renderFaceZNeg(Block p_147761_1_, double x, double y, double z, IIcon p_147761_8_)
	{
		Tessellator tessellator = Tessellator.instance;

		if (p_147761_1_ == TFCBlocks.fauxPalm)
		{
			//renderPalmFrond(x, y, z, Math.PI - rot30, 0, 0.5);
		//	renderPalmFrond(x, y, z, Math.PI, 0, 0.5);
		//	renderPalmFrond(x, y, z, Math.PI + rot30, 0, 0.5);
			return;
		}

		if (this.hasOverrideBlockTexture())
		{
			p_147761_8_ = this.overrideBlockTexture;
		}

		double d3 = (double) p_147761_8_.getInterpolatedU(this.renderMinX * 16.0D);
		double d4 = (double) p_147761_8_.getInterpolatedU(this.renderMaxX * 16.0D);

		if (this.field_152631_f)
		{
			d4 = (double) p_147761_8_.getInterpolatedU((1.0D - this.renderMinX) * 16.0D);
			d3 = (double) p_147761_8_.getInterpolatedU((1.0D - this.renderMaxX) * 16.0D);
		}

		double d5 = (double) p_147761_8_.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
		double d6 = (double) p_147761_8_.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
		double d7;

		if (this.flipTexture)
		{
			d7 = d3;
			d3 = d4;
			d4 = d7;
		}

		if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D)
		{
			d3 = (double) p_147761_8_.getMinU();
			d4 = (double) p_147761_8_.getMaxU();
		}

		if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D)
		{
			d5 = (double) p_147761_8_.getMinV();
			d6 = (double) p_147761_8_.getMaxV();
		}

		d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (this.uvRotateEast == 2)
		{
			d3 = (double) p_147761_8_.getInterpolatedU(this.renderMinY * 16.0D);
			d4 = (double) p_147761_8_.getInterpolatedU(this.renderMaxY * 16.0D);
			d5 = (double) p_147761_8_.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
			d6 = (double) p_147761_8_.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		}
		else if (this.uvRotateEast == 1)
		{
			d3 = (double) p_147761_8_.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
			d4 = (double) p_147761_8_.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
			d5 = (double) p_147761_8_.getInterpolatedV(this.renderMaxX * 16.0D);
			d6 = (double) p_147761_8_.getInterpolatedV(this.renderMinX * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		}
		else if (this.uvRotateEast == 3)
		{
			d3 = (double) p_147761_8_.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
			d4 = (double) p_147761_8_.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
			d5 = (double) p_147761_8_.getInterpolatedV(this.renderMaxY * 16.0D);
			d6 = (double) p_147761_8_.getInterpolatedV(this.renderMinY * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		Vec3 xYz = Vec3.createVectorHelper(this.renderMinX - 0.5, this.renderMaxY - 0.5, this.renderMinZ - 0.5);
		Vec3 XYz = Vec3.createVectorHelper(this.renderMaxX - 0.5, this.renderMaxY - 0.5, this.renderMinZ - 0.5);
		Vec3 Xyz = Vec3.createVectorHelper(this.renderMaxX - 0.5, this.renderMinY - 0.5, this.renderMinZ - 0.5);
		Vec3 xyz = Vec3.createVectorHelper(this.renderMinX - 0.5, this.renderMinY - 0.5, this.renderMinZ - 0.5);

		xYz.rotateAroundX(rotation);
		xyz.rotateAroundX(rotation);
		XYz.rotateAroundX(rotation);
		Xyz.rotateAroundX(rotation);

		xYz.rotateAroundY(yRotation);
		xyz.rotateAroundY(yRotation);
		XYz.rotateAroundY(yRotation);
		Xyz.rotateAroundY(yRotation);
		/*
		 * double d11 = x + this.renderMinX; double d12 = x + this.renderMaxX;
		 * double d13 = y + this.renderMinY; double d14 = y + this.renderMaxY;
		 * double d15 = z + this.renderMinZ;
		 * 
		 * if (this.renderFromInside) { d11 = x + this.renderMaxX; d12 = x +
		 * this.renderMinX; }
		 */
		GL11.glDisable(GL11.GL_CULL_FACE);
		if (this.enableAO)
		{
			tessellator.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
			tessellator.setBrightness(this.brightnessTopLeft);
			tessellator.addVertexWithUV((renderFromInside ? XYz.xCoord : xYz.xCoord) + 0.5 + x, xYz.yCoord + 0.5 + y,
					xYz.zCoord + 0.5 + z, d7, d9); // xYz
			tessellator.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator.setBrightness(this.brightnessBottomLeft);
			tessellator.addVertexWithUV((renderFromInside ? xYz.xCoord : XYz.xCoord) + 0.5 + x, XYz.yCoord + 0.5 + y,
					XYz.zCoord + 0.5 + z, d3, d5); // XYz
			tessellator.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight,
					this.colorBlueBottomRight);
			tessellator.setBrightness(this.brightnessBottomRight);
			tessellator.addVertexWithUV((renderFromInside ? xyz.xCoord : Xyz.xCoord) + 0.5 + x, Xyz.yCoord + 0.5 + y,
					Xyz.zCoord + 0.5 + z, d8, d10); // Xyz
			tessellator.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator.setBrightness(this.brightnessTopRight);
			tessellator.addVertexWithUV((renderFromInside ? Xyz.xCoord : xyz.xCoord) + 0.5 + x, xyz.yCoord + 0.5 + y,
					xyz.zCoord + 0.5 + z, d4, d6); // xyz
		}
		else
		{
			tessellator.addVertexWithUV((renderFromInside ? XYz.xCoord : xYz.xCoord) + 0.5 + x, xYz.yCoord + 0.5 + y,
					xYz.zCoord + 0.5 + z, d7, d9); // xYz
			tessellator.addVertexWithUV((renderFromInside ? xYz.xCoord : XYz.xCoord) + 0.5 + x, XYz.yCoord + 0.5 + y,
					XYz.zCoord + 0.5 + z, d3, d5); // XYz
			tessellator.addVertexWithUV((renderFromInside ? xyz.xCoord : Xyz.xCoord) + 0.5 + x, Xyz.yCoord + 0.5 + y,
					Xyz.zCoord + 0.5 + z, d8, d10); // Xyz
			tessellator.addVertexWithUV((renderFromInside ? Xyz.xCoord : xyz.xCoord) + 0.5 + x, xyz.yCoord + 0.5 + y,
					xyz.zCoord + 0.5 + z, d4, d6); // xyz
		}
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	/**
	 * Renders the given texture to the south (z-positive) face of the block.
	 * Args: block, x, y, z, texture
	 */
	public void renderFaceZPos(Block p_147734_1_, double x, double y, double z, IIcon p_147734_8_)
	{
		Tessellator tessellator = Tessellator.instance;

		if (p_147734_1_ == TFCBlocks.fauxPalm)
		{
			//renderPalmFrond(x, y, z, -rot30, 0, 0.5);
		//	renderPalmFrond(x, y, z, 0, 0, 0.5);
		//	renderPalmFrond(x, y, z, rot30, 0, 0.5);
			return;
		}

		if (this.hasOverrideBlockTexture())
		{
			p_147734_8_ = this.overrideBlockTexture;
		}

		double d3 = (double) p_147734_8_.getInterpolatedU(this.renderMinX * 16.0D);
		double d4 = (double) p_147734_8_.getInterpolatedU(this.renderMaxX * 16.0D);
		double d5 = (double) p_147734_8_.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
		double d6 = (double) p_147734_8_.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
		double d7;

		if (this.flipTexture)
		{
			d7 = d3;
			d3 = d4;
			d4 = d7;
		}

		if (this.renderMinX < 0.0D || this.renderMaxX > 1.0D)
		{
			d3 = (double) p_147734_8_.getMinU();
			d4 = (double) p_147734_8_.getMaxU();
		}

		if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D)
		{
			d5 = (double) p_147734_8_.getMinV();
			d6 = (double) p_147734_8_.getMaxV();
		}

		d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (this.uvRotateWest == 1)
		{
			d3 = (double) p_147734_8_.getInterpolatedU(this.renderMinY * 16.0D);
			d6 = (double) p_147734_8_.getInterpolatedV(16.0D - this.renderMinX * 16.0D);
			d4 = (double) p_147734_8_.getInterpolatedU(this.renderMaxY * 16.0D);
			d5 = (double) p_147734_8_.getInterpolatedV(16.0D - this.renderMaxX * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		}
		else if (this.uvRotateWest == 2)
		{
			d3 = (double) p_147734_8_.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
			d5 = (double) p_147734_8_.getInterpolatedV(this.renderMinX * 16.0D);
			d4 = (double) p_147734_8_.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
			d6 = (double) p_147734_8_.getInterpolatedV(this.renderMaxX * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		}
		else if (this.uvRotateWest == 3)
		{
			d3 = (double) p_147734_8_.getInterpolatedU(16.0D - this.renderMinX * 16.0D);
			d4 = (double) p_147734_8_.getInterpolatedU(16.0D - this.renderMaxX * 16.0D);
			d5 = (double) p_147734_8_.getInterpolatedV(this.renderMaxY * 16.0D);
			d6 = (double) p_147734_8_.getInterpolatedV(this.renderMinY * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		Vec3 xYZ = Vec3.createVectorHelper(this.renderMinX - 0.5, this.renderMaxY - 0.5, this.renderMaxZ - 0.5);
		Vec3 XYZ = Vec3.createVectorHelper(this.renderMaxX - 0.5, this.renderMaxY - 0.5, this.renderMaxZ - 0.5);
		Vec3 XyZ = Vec3.createVectorHelper(this.renderMaxX - 0.5, this.renderMinY - 0.5, this.renderMaxZ - 0.5);
		Vec3 xyZ = Vec3.createVectorHelper(this.renderMinX - 0.5, this.renderMinY - 0.5, this.renderMaxZ - 0.5);

		xYZ.rotateAroundX(rotation);
		xyZ.rotateAroundX(rotation);
		XYZ.rotateAroundX(rotation);
		XyZ.rotateAroundX(rotation);

		xYZ.rotateAroundY(yRotation);
		xyZ.rotateAroundY(yRotation);
		XYZ.rotateAroundY(yRotation);
		XyZ.rotateAroundY(yRotation);
		/*
		 * double d11 = x + this.renderMinX; double d12 = x + this.renderMaxX;
		 * double d13 = y + this.renderMinY; double d14 = y + this.renderMaxY;
		 * double d15 = z + this.renderMaxZ;
		 * 
		 * if (this.renderFromInside) { d11 = x + this.renderMaxX; d12 = x +
		 * this.renderMinX; }
		 */
		GL11.glDisable(GL11.GL_CULL_FACE);
		if (this.enableAO)
		{
			tessellator.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
			tessellator.setBrightness(this.brightnessTopLeft);
			tessellator.addVertexWithUV((renderFromInside ? XYZ.xCoord : xYZ.xCoord) + 0.5 + x, xYZ.yCoord + 0.5 + y,
					xYZ.zCoord + 0.5 + z, d3, d5); // xYZ
			tessellator.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator.setBrightness(this.brightnessBottomLeft);
			tessellator.addVertexWithUV((renderFromInside ? XyZ.xCoord : xyZ.xCoord) + 0.5 + x, xyZ.yCoord + 0.5 + y,
					xyZ.zCoord + 0.5 + z, d8, d10); // xyZ
			tessellator.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight,
					this.colorBlueBottomRight);
			tessellator.setBrightness(this.brightnessBottomRight);
			tessellator.addVertexWithUV((renderFromInside ? xyZ.xCoord : XyZ.xCoord) + 0.5 + x, XyZ.yCoord + 0.5 + y,
					XyZ.zCoord + 0.5 + z, d4, d6); // XyZ
			tessellator.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator.setBrightness(this.brightnessTopRight);
			tessellator.addVertexWithUV((renderFromInside ? xYZ.xCoord : XYZ.xCoord) + 0.5 + x, XYZ.yCoord + 0.5 + y,
					XYZ.zCoord + 0.5 + z, d7, d9); // XYZ
		}
		else
		{
			tessellator.addVertexWithUV((renderFromInside ? XYZ.xCoord : xYZ.xCoord) + 0.5 + x, xYZ.yCoord + 0.5 + y,
					xYZ.zCoord + 0.5 + z, d3, d5); // xYZ
			tessellator.addVertexWithUV((renderFromInside ? XyZ.xCoord : xyZ.xCoord) + 0.5 + x, xyZ.yCoord + 0.5 + y,
					xyZ.zCoord + 0.5 + z, d8, d10); // xyZ
			tessellator.addVertexWithUV((renderFromInside ? xyZ.xCoord : XyZ.xCoord) + 0.5 + x, XyZ.yCoord + 0.5 + y,
					XyZ.zCoord + 0.5 + z, d4, d6); // XyZ
			tessellator.addVertexWithUV((renderFromInside ? xYZ.xCoord : XYZ.xCoord) + 0.5 + x, XYZ.yCoord + 0.5 + y,
					XYZ.zCoord + 0.5 + z, d7, d9); // XYZ
		}
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	/**
	 * Renders the given texture to the west (x-negative) face of the block.
	 * Args: block, x, y, z, texture
	 */
	public void renderFaceXNeg(Block p_147798_1_, double x, double y, double z, IIcon p_147798_8_)
	{
		Tessellator tessellator = Tessellator.instance;

		if (p_147798_1_ == TFCBlocks.fauxPalm)
		{
		//	renderPalmFrond(x, y, z, 0.5 * Math.PI - rot30, 0, 0.5);
		//	renderPalmFrond(x, y, z, 0.5 * Math.PI, 0, 0.5);
		//	renderPalmFrond(x, y, z, 0.5 * Math.PI + rot30, 0, 0.5);
			return;
		}

		if (this.hasOverrideBlockTexture())
		{
			p_147798_8_ = this.overrideBlockTexture;
		}

		double d3 = (double) p_147798_8_.getInterpolatedU(this.renderMinZ * 16.0D);
		double d4 = (double) p_147798_8_.getInterpolatedU(this.renderMaxZ * 16.0D);
		double d5 = (double) p_147798_8_.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
		double d6 = (double) p_147798_8_.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
		double d7;

		if (this.flipTexture)
		{
			d7 = d3;
			d3 = d4;
			d4 = d7;
		}

		if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D)
		{
			d3 = (double) p_147798_8_.getMinU();
			d4 = (double) p_147798_8_.getMaxU();
		}

		if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D)
		{
			d5 = (double) p_147798_8_.getMinV();
			d6 = (double) p_147798_8_.getMaxV();
		}

		d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (this.uvRotateNorth == 1)
		{
			d3 = (double) p_147798_8_.getInterpolatedU(this.renderMinY * 16.0D);
			d5 = (double) p_147798_8_.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
			d4 = (double) p_147798_8_.getInterpolatedU(this.renderMaxY * 16.0D);
			d6 = (double) p_147798_8_.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		}
		else if (this.uvRotateNorth == 2)
		{
			d3 = (double) p_147798_8_.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
			d5 = (double) p_147798_8_.getInterpolatedV(this.renderMinZ * 16.0D);
			d4 = (double) p_147798_8_.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
			d6 = (double) p_147798_8_.getInterpolatedV(this.renderMaxZ * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		}
		else if (this.uvRotateNorth == 3)
		{
			d3 = (double) p_147798_8_.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
			d4 = (double) p_147798_8_.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
			d5 = (double) p_147798_8_.getInterpolatedV(this.renderMaxY * 16.0D);
			d6 = (double) p_147798_8_.getInterpolatedV(this.renderMinY * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		Vec3 xYZ = Vec3.createVectorHelper(this.renderMinX - 0.5, this.renderMaxY - 0.5, this.renderMaxZ - 0.5);
		Vec3 xYz = Vec3.createVectorHelper(this.renderMinX - 0.5, this.renderMaxY - 0.5, this.renderMinZ - 0.5);
		Vec3 xyz = Vec3.createVectorHelper(this.renderMinX - 0.5, this.renderMinY - 0.5, this.renderMinZ - 0.5);
		Vec3 xyZ = Vec3.createVectorHelper(this.renderMinX - 0.5, this.renderMinY - 0.5, this.renderMaxZ - 0.5);

		xYZ.rotateAroundX(rotation);
		xYz.rotateAroundX(rotation);
		xyz.rotateAroundX(rotation);
		xyZ.rotateAroundX(rotation);

		xYZ.rotateAroundY(yRotation);
		xYz.rotateAroundY(yRotation);
		xyz.rotateAroundY(yRotation);
		xyZ.rotateAroundY(yRotation);
		/*
		 * double d11 = x + this.renderMinX; double d12 = y + this.renderMinY;
		 * double d13 = y + this.renderMaxY; double d14 = z + this.renderMinZ;
		 * double d15 = z + this.renderMaxZ;
		 * 
		 * if (this.renderFromInside) { d14 = z + this.renderMaxZ; d15 = z +
		 * this.renderMinZ; }
		 */
		GL11.glDisable(GL11.GL_CULL_FACE);
		if (this.enableAO)
		{
			tessellator.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
			tessellator.setBrightness(this.brightnessTopLeft);
			tessellator.addVertexWithUV(xYZ.xCoord + 0.5 + x, xYZ.yCoord + 0.5 + y,
					(renderFromInside ? xYz.zCoord : xYZ.zCoord) + 0.5 + z, d7, d9); // xYZ
			tessellator.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator.setBrightness(this.brightnessBottomLeft);
			tessellator.addVertexWithUV(xYz.xCoord + 0.5 + x, xYz.yCoord + 0.5 + y,
					(renderFromInside ? xYZ.zCoord : xYz.zCoord) + 0.5 + z, d3, d5); // xYz
			tessellator.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight,
					this.colorBlueBottomRight);
			tessellator.setBrightness(this.brightnessBottomRight);
			tessellator.addVertexWithUV(xyz.xCoord + 0.5 + x, xyz.yCoord + 0.5 + y,
					(renderFromInside ? xyZ.zCoord : xyz.zCoord) + 0.5 + z, d8, d10); // xyz
			tessellator.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator.setBrightness(this.brightnessTopRight);
			tessellator.addVertexWithUV(xyZ.xCoord + 0.5 + x, xyZ.yCoord + 0.5 + y,
					(renderFromInside ? xyz.zCoord : xyZ.zCoord) + 0.5 + z, d4, d6); // xyZ
		}
		else
		{
			tessellator.addVertexWithUV(xYZ.xCoord + 0.5 + x, xYZ.yCoord + 0.5 + y,
					(renderFromInside ? xYz.zCoord : xYZ.zCoord) + 0.5 + z, d7, d9); // xYZ
			tessellator.addVertexWithUV(xYz.xCoord + 0.5 + x, xYz.yCoord + 0.5 + y,
					(renderFromInside ? xYZ.zCoord : xYz.zCoord) + 0.5 + z, d3, d5); // xYz
			tessellator.addVertexWithUV(xyz.xCoord + 0.5 + x, xyz.yCoord + 0.5 + y,
					(renderFromInside ? xyZ.zCoord : xyz.zCoord) + 0.5 + z, d8, d10); // xyz
			tessellator.addVertexWithUV(xyZ.xCoord + 0.5 + x, xyZ.yCoord + 0.5 + y,
					(renderFromInside ? xyz.zCoord : xyZ.zCoord) + 0.5 + z, d4, d6); // xyZ
		}
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	/**
	 * Renders the given texture to the east (x-positive) face of the block.
	 * Args: block, x, y, z, texture
	 */
	public void renderFaceXPos(Block p_147764_1_, double x, double y, double z, IIcon p_147764_8_)
	{
		Tessellator tessellator = Tessellator.instance;

		if (p_147764_1_ == TFCBlocks.fauxPalm)
		{
		//	renderPalmFrond(x, y, z, 1.5 * Math.PI - rot30, (-8+(z%11)*z%2==0?1:-1)*rot1, 0.5*(((z%10)/20d)+0.5));
		//	renderPalmFrond(x, y, z, 1.5 * Math.PI, (10+(x%7)*x%2==0?1:-1)*rot1, 0.5*(((x%10)/20d)+0.5));
		//	renderPalmFrond(x, y, z, 1.5 * Math.PI + rot30, (5+(y%13)*y%2==0?1:-1)*rot1, 0.5*(((y%10)/20d)+0.5));
			return;
		}

		if (this.hasOverrideBlockTexture())
		{
			p_147764_8_ = this.overrideBlockTexture;
		}

		double d3 = (double) p_147764_8_.getInterpolatedU(this.renderMinZ * 16.0D);
		double d4 = (double) p_147764_8_.getInterpolatedU(this.renderMaxZ * 16.0D);

		if (this.field_152631_f)
		{
			d4 = (double) p_147764_8_.getInterpolatedU((1.0D - this.renderMinZ) * 16.0D);
			d3 = (double) p_147764_8_.getInterpolatedU((1.0D - this.renderMaxZ) * 16.0D);
		}

		double d5 = (double) p_147764_8_.getInterpolatedV(16.0D - this.renderMaxY * 16.0D);
		double d6 = (double) p_147764_8_.getInterpolatedV(16.0D - this.renderMinY * 16.0D);
		double d7;

		if (this.flipTexture)
		{
			d7 = d3;
			d3 = d4;
			d4 = d7;
		}

		if (this.renderMinZ < 0.0D || this.renderMaxZ > 1.0D)
		{
			d3 = (double) p_147764_8_.getMinU();
			d4 = (double) p_147764_8_.getMaxU();
		}

		if (this.renderMinY < 0.0D || this.renderMaxY > 1.0D)
		{
			d5 = (double) p_147764_8_.getMinV();
			d6 = (double) p_147764_8_.getMaxV();
		}

		d7 = d4;
		double d8 = d3;
		double d9 = d5;
		double d10 = d6;

		if (this.uvRotateSouth == 2)
		{
			d3 = (double) p_147764_8_.getInterpolatedU(this.renderMinY * 16.0D);
			d5 = (double) p_147764_8_.getInterpolatedV(16.0D - this.renderMinZ * 16.0D);
			d4 = (double) p_147764_8_.getInterpolatedU(this.renderMaxY * 16.0D);
			d6 = (double) p_147764_8_.getInterpolatedV(16.0D - this.renderMaxZ * 16.0D);
			d9 = d5;
			d10 = d6;
			d7 = d3;
			d8 = d4;
			d5 = d6;
			d6 = d9;
		}
		else if (this.uvRotateSouth == 1)
		{
			d3 = (double) p_147764_8_.getInterpolatedU(16.0D - this.renderMaxY * 16.0D);
			d5 = (double) p_147764_8_.getInterpolatedV(this.renderMaxZ * 16.0D);
			d4 = (double) p_147764_8_.getInterpolatedU(16.0D - this.renderMinY * 16.0D);
			d6 = (double) p_147764_8_.getInterpolatedV(this.renderMinZ * 16.0D);
			d7 = d4;
			d8 = d3;
			d3 = d4;
			d4 = d8;
			d9 = d6;
			d10 = d5;
		}
		else if (this.uvRotateSouth == 3)
		{
			d3 = (double) p_147764_8_.getInterpolatedU(16.0D - this.renderMinZ * 16.0D);
			d4 = (double) p_147764_8_.getInterpolatedU(16.0D - this.renderMaxZ * 16.0D);
			d5 = (double) p_147764_8_.getInterpolatedV(this.renderMaxY * 16.0D);
			d6 = (double) p_147764_8_.getInterpolatedV(this.renderMinY * 16.0D);
			d7 = d4;
			d8 = d3;
			d9 = d5;
			d10 = d6;
		}

		Vec3 XYZ = Vec3.createVectorHelper(this.renderMaxX - 0.5, this.renderMaxY - 0.5, this.renderMaxZ - 0.5);
		Vec3 XYz = Vec3.createVectorHelper(this.renderMaxX - 0.5, this.renderMaxY - 0.5, this.renderMinZ - 0.5);
		Vec3 Xyz = Vec3.createVectorHelper(this.renderMaxX - 0.5, this.renderMinY - 0.5, this.renderMinZ - 0.5);
		Vec3 XyZ = Vec3.createVectorHelper(this.renderMaxX - 0.5, this.renderMinY - 0.5, this.renderMaxZ - 0.5);

		XYZ.rotateAroundX(rotation);
		XYz.rotateAroundX(rotation);
		Xyz.rotateAroundX(rotation);
		XyZ.rotateAroundX(rotation);

		XYZ.rotateAroundY(yRotation);
		XYz.rotateAroundY(yRotation);
		Xyz.rotateAroundY(yRotation);
		XyZ.rotateAroundY(yRotation);

		/*
		 * double d11 = x + this.renderMaxX; double d12 = y + this.renderMinY;
		 * double d13 = y + this.renderMaxY; double d14 = z + this.renderMinZ;
		 * double d15 = z + this.renderMaxZ;
		 * 
		 * if (this.renderFromInside) { d14 = z + this.renderMaxZ; d15 = z +
		 * this.renderMinZ; }
		 */
		GL11.glDisable(GL11.GL_CULL_FACE);
		if (this.enableAO)
		{
			tessellator.setColorOpaque_F(this.colorRedTopLeft, this.colorGreenTopLeft, this.colorBlueTopLeft);
			tessellator.setBrightness(this.brightnessTopLeft);
			tessellator.addVertexWithUV(XyZ.xCoord + 0.5 + x, XyZ.yCoord + 0.5 + y,
					(renderFromInside ? Xyz.zCoord : XyZ.zCoord) + 0.5 + z, d8, d10); // XyZ
			tessellator.setColorOpaque_F(this.colorRedBottomLeft, this.colorGreenBottomLeft, this.colorBlueBottomLeft);
			tessellator.setBrightness(this.brightnessBottomLeft);
			tessellator.addVertexWithUV(Xyz.xCoord + 0.5 + x, Xyz.yCoord + 0.5 + y,
					(renderFromInside ? XyZ.zCoord : Xyz.zCoord) + 0.5 + z, d4, d6); // Xyz
			tessellator.setColorOpaque_F(this.colorRedBottomRight, this.colorGreenBottomRight,
					this.colorBlueBottomRight);
			tessellator.setBrightness(this.brightnessBottomRight);
			tessellator.addVertexWithUV(XYz.xCoord + 0.5 + x, XYz.yCoord + 0.5 + y,
					(renderFromInside ? XYZ.zCoord : XYz.zCoord) + 0.5 + z, d7, d9); // XYz
			tessellator.setColorOpaque_F(this.colorRedTopRight, this.colorGreenTopRight, this.colorBlueTopRight);
			tessellator.setBrightness(this.brightnessTopRight);
			tessellator.addVertexWithUV(XYZ.xCoord + 0.5 + x, XYZ.yCoord + 0.5 + y,
					(renderFromInside ? XYz.zCoord : XYZ.zCoord) + 0.5 + z, d3, d5); // XYZ
		}
		else
		{
			tessellator.addVertexWithUV(XyZ.xCoord + 0.5 + x, XyZ.yCoord + 0.5 + y,
					(renderFromInside ? Xyz.zCoord : XyZ.zCoord) + 0.5 + z, d8, d10); // XyZ
			tessellator.addVertexWithUV(Xyz.xCoord + 0.5 + x, Xyz.yCoord + 0.5 + y,
					(renderFromInside ? XyZ.zCoord : Xyz.zCoord) + 0.5 + z, d4, d6); // Xyz
			tessellator.addVertexWithUV(XYz.xCoord + 0.5 + x, XYz.yCoord + 0.5 + y,
					(renderFromInside ? XYZ.zCoord : XYz.zCoord) + 0.5 + z, d7, d9); // XYz
			tessellator.addVertexWithUV(XYZ.xCoord + 0.5 + x, XYZ.yCoord + 0.5 + y,
					(renderFromInside ? XYz.zCoord : XYZ.zCoord) + 0.5 + z, d3, d5); // XYZ
		}
		GL11.glEnable(GL11.GL_CULL_FACE);
	}
}
