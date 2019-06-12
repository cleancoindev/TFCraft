package com.dunk.tfc.Render.Models;

import org.lwjgl.opengl.GL11;

import com.dunk.tfc.Items.ItemClothing;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;

public class ModelCoat extends ModelBase
{
	private ModelRenderer armR;
	private ModelRenderer armL;

	private ModelRenderer body;

	private float scaleFactor;

	public ModelCoat()
	{
		this(0.0f);
	}

	private TexturedQuad[] quadList;

	public ModelCoat(float f)
	{
		super();
		this.scaleFactor = f;
		// Quiver

		body = new ModelRenderer(this, 16, 0);
		body.addBox(-4F, 2F, -2F, 8, 10, 4, scaleFactor);
		body.setRotationPoint(0, 0, 0f);
		armR = new ModelRenderer(this, 16, 16);
		armR.mirror = true;
		armL = new ModelRenderer(this, 16, 16);
		// Should be locx,locy,locz,sizex,sizey,sizez
		armR.addBox(-3f, -2f, -2f, 4, 8, 4, scaleFactor);
		armL.addBox(-1F, -2f, -2F, 4, 8, 4, scaleFactor);
		armR.setRotationPoint(1.9f, 14f, 0f);
		armL.setRotationPoint(-1.9f, 14f, 0f);
		// body.addChild(armR);
		// body.addChild(armL);
		/// quiver.rotateAngleZ = (float)(Math.PI/4) + (float)(Math.PI);
		// quiver.setTextureSize(64, 32);
		armL.setTextureSize(64, 32);
		armR.setTextureSize(64, 32);

		/*
		 * for(int i = 0; i < arrows.length; i++) { arrows[i] = new
		 * ModelRenderer(this,59,0); arrows[i].addBox(-1,-8,0,2,14,0);
		 * arrows[i].setRotationPoint(0,0,0f); arrows[i].setTextureSize(64,32);
		 * arrows[i].rotateAngleZ = (float)(Math.PI) +
		 * (float)(Math.PI/36)*(i%3)*(i%2==0?1f:-1f); arrows[i].rotateAngleX =
		 * (float)(Math.PI/36)*(i%3)*(i%2==(i%3)?1f:-1f);
		 * quiver.addChild(arrows[i]); }
		 */

	}

	/**
	 * Sets the rotation on a model where the provided params are in radians
	 * 
	 * @param model
	 *            The model
	 * @param x
	 *            The x angle
	 * @param y
	 *            The y angle
	 * @param z
	 *            The z angle
	 */
	protected void setRotationRadians(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	/**
	 * Sets the rotation on a model where the provided params are in degrees
	 * 
	 * @param model
	 *            The model
	 * @param x
	 *            The x angle
	 * @param y
	 *            The y angle
	 * @param z
	 *            The z angle
	 */
	protected void setRotationDegrees(ModelRenderer model, float x, float y, float z)
	{
		this.setRotationRadians(model, (float) Math.toRadians(x), (float) Math.toRadians(y), (float) Math.toRadians(z));
	}

	/**
	 * Returns a rotation angle that is inbetween two other rotation angles.
	 * par1 and par2 are the angles between which to interpolate, par3 is
	 * probably a float between 0.0 and 1.0 that tells us where "between" the
	 * two angles we are. Example: par1 = 30, par2 = 50, par3 = 0.5, then return
	 * = 40
	 */
	private float interpolateRotation(float p_77034_1_, float p_77034_2_, float p_77034_3_)
	{
		float f3;

		for (f3 = p_77034_2_ - p_77034_1_; f3 < -180.0F; f3 += 360.0F)
		{
			;
		}

		while (f3 >= 180.0F)
		{
			f3 -= 360.0F;
		}

		return p_77034_1_ + p_77034_3_ * f3;
	}

	public void render(EntityLivingBase theEntity, ItemStack item, RenderPlayer renderer, float partialRenderTick)
	{
		// The ARMS WERE BACKWARDS DAMMNIT
		if (theEntity instanceof EntityPlayer)
		{
			this.armR.rotateAngleZ = 0.0F;
			this.armL.rotateAngleZ = 0.0F;
			this.armR.rotateAngleY = 0.0F;
			this.armL.rotateAngleY = 0.0F;
			this.armR.rotateAngleX = 0.0F;
			this.armL.rotateAngleX = 0.0F;

			float wetnessMult = 1f;
			if (item.stackTagCompound != null)
			{
				wetnessMult = (12000f - (float) item.stackTagCompound.getInteger("wetness")) / 12000f;
			}

			int j = ((ItemClothing) (item.getItem())).getColor(item);
			if (j != -1)
			{
				float f1 = (float) (j >> 16 & 255) / 255.0F;
				float f2 = (float) (j >> 8 & 255) / 255.0F;
				float f3 = (float) (j & 255) / 255.0F;
				GL11.glColor3f(f1 * wetnessMult, f2 * wetnessMult, f3 * wetnessMult);

			}
			else
			{
				GL11.glColor3f(1.0F * wetnessMult, 1.0F * wetnessMult, 1.0F * wetnessMult);
			}

			float rotateAngle;

			body.rotateAngleX = renderer.modelBipedMain.bipedBody.rotateAngleX;
			body.rotateAngleY = renderer.modelBipedMain.bipedBody.rotateAngleY;
			body.rotateAngleZ = renderer.modelBipedMain.bipedBody.rotateAngleZ;

			armL.rotateAngleX = renderer.modelBipedMain.bipedLeftArm.rotateAngleX;
			armL.rotateAngleY = renderer.modelBipedMain.bipedLeftArm.rotateAngleY;
			armL.rotateAngleZ = renderer.modelBipedMain.bipedLeftArm.rotateAngleZ;

			armL.rotationPointX = renderer.modelBipedMain.bipedBody.rotationPointX;
			armL.rotationPointY = renderer.modelBipedMain.bipedBody.rotationPointY;
			armL.rotationPointZ = renderer.modelBipedMain.bipedBody.rotationPointZ;

			armL.rotationPointX += renderer.modelBipedMain.bipedLeftArm.rotationPointX;
			armL.rotationPointY += renderer.modelBipedMain.bipedLeftArm.rotationPointY;
			armL.rotationPointZ += renderer.modelBipedMain.bipedLeftArm.rotationPointZ;

			armR.rotateAngleX = renderer.modelBipedMain.bipedRightArm.rotateAngleX;
			armR.rotateAngleY = renderer.modelBipedMain.bipedRightArm.rotateAngleY;
			armR.rotateAngleZ = renderer.modelBipedMain.bipedRightArm.rotateAngleZ;

			armR.rotationPointX = renderer.modelBipedMain.bipedBody.rotationPointX;
			armR.rotationPointY = renderer.modelBipedMain.bipedBody.rotationPointY;
			armR.rotationPointZ = renderer.modelBipedMain.bipedBody.rotationPointZ;

			armR.rotationPointX += renderer.modelBipedMain.bipedRightArm.rotationPointX;
			armR.rotationPointY += renderer.modelBipedMain.bipedRightArm.rotationPointY;
			armR.rotationPointZ += renderer.modelBipedMain.bipedRightArm.rotationPointZ;

			// this.vertexPositions = new PositionTextureVertex[8];
			this.quadList = new TexturedQuad[12];

			// Here, we need to calculate where our coordinates are. We take the
			// initial location of the leg, and handle the rotations.
			// Translate to our rotation point, then do rotations in the order
			// Z, Y, X.
			// Let's establish that the point we want to render is halfway down
			// the leg?
			Vec3 rightLegVector = Vec3.createVectorHelper(0, 4f, 0);
			Vec3 rightLegVector2 = Vec3.createVectorHelper(0, 4f, 0);
			Vec3 rightLegButtVector = Vec3.createVectorHelper(0, 0, 2.35);

			rightLegButtVector.rotateAroundZ(renderer.modelBipedMain.bipedRightLeg.rotateAngleZ);
			rightLegButtVector.rotateAroundY(renderer.modelBipedMain.bipedRightLeg.rotateAngleY);
			rightLegButtVector.rotateAroundX(renderer.modelBipedMain.bipedRightLeg.rotateAngleX);

			Vec3 leftLegVector = Vec3.createVectorHelper(0, 4f, 0);
			Vec3 leftLegVector2 = Vec3.createVectorHelper(0, 4f, 0);

			rightLegVector.rotateAroundZ(renderer.modelBipedMain.bipedRightLeg.rotateAngleZ);
			rightLegVector.rotateAroundY(renderer.modelBipedMain.bipedRightLeg.rotateAngleY);
			rightLegVector2.rotateAroundZ(renderer.modelBipedMain.bipedRightLeg.rotateAngleZ);
			rightLegVector2.rotateAroundY(renderer.modelBipedMain.bipedRightLeg.rotateAngleY);

			leftLegVector.rotateAroundZ(renderer.modelBipedMain.bipedLeftLeg.rotateAngleZ);
			leftLegVector.rotateAroundY(renderer.modelBipedMain.bipedLeftLeg.rotateAngleY);
			leftLegVector2.rotateAroundZ(renderer.modelBipedMain.bipedLeftLeg.rotateAngleZ);
			leftLegVector2.rotateAroundY(renderer.modelBipedMain.bipedLeftLeg.rotateAngleY);

			if (theEntity.isSneaking())
			{
				rightLegVector2.rotateAroundX(0.4f);
				leftLegVector2.rotateAroundX(0.4f);
			}
			else
			{
				rightLegVector.rotateAroundX(-0.1f);
				leftLegVector.rotateAroundX(-0.1f);
			}

			if (theEntity.ridingEntity != null)
			{
				rightLegVector.rotateAroundX(-0.4f);
				leftLegVector.rotateAroundX(-0.4f);
			}

			if (renderer.modelBipedMain.bipedRightLeg.rotateAngleX < 0)
			{
				rightLegVector.rotateAroundX(renderer.modelBipedMain.bipedRightLeg.rotateAngleX);
			}
			else if (renderer.modelBipedMain.bipedRightLeg.rotateAngleX > 0)
			{
				rightLegVector2.rotateAroundX(renderer.modelBipedMain.bipedRightLeg.rotateAngleX);
			}

			if (renderer.modelBipedMain.bipedLeftLeg.rotateAngleX > 0)
			{
				leftLegVector2.rotateAroundX(renderer.modelBipedMain.bipedLeftLeg.rotateAngleX);
			}
			else if (renderer.modelBipedMain.bipedLeftLeg.rotateAngleX < 0)
			{
				leftLegVector.rotateAroundX(renderer.modelBipedMain.bipedLeftLeg.rotateAngleX);
			}

			/*
			 * leftLegVector.rotateAroundZ(renderer.modelBipedMain.bipedLeftLeg.
			 * rotateAngleZ);
			 * leftLegVector.rotateAroundY(renderer.modelBipedMain.bipedLeftLeg.
			 * rotateAngleY);
			 * if(renderer.modelBipedMain.bipedLeftLeg.rotateAngleX < 0) {
			 * leftLegVector.rotateAroundX(renderer.modelBipedMain.bipedLeftLeg.
			 * rotateAngleX); }
			 */

			float hipDisplacementX, hipDisplacementY, hipDisplacementZ;

			float sneakLegDepth = theEntity.isSneaking() ? -0.4f : 0f;
			float sneakButtHeight = theEntity.isSneaking() ? -0.2f : 0f;

			hipDisplacementX = renderer.modelBipedMain.bipedRightLeg.rotationPointX + 6 + scaleFactor;
			hipDisplacementY = renderer.modelBipedMain.bipedRightLeg.rotationPointY + 0;
			hipDisplacementZ = renderer.modelBipedMain.bipedRightLeg.rotationPointZ + 2 + scaleFactor;

			if (theEntity.isSneaking())
			{
				// GL11.glTranslatef(0f / 16f, 2f / 16f, -1.5f / 16f);
				hipDisplacementY += 2f;
				hipDisplacementZ += -7.5f;
			}

			PositionTextureVertex rightFrontOuterLeg = new PositionTextureVertex(
					0.25f + hipDisplacementX + (float) rightLegVector.xCoord,
					hipDisplacementY + (float) rightLegVector.yCoord,
					0.25f + hipDisplacementZ + (float) rightLegVector.zCoord, 0, 4);
			PositionTextureVertex rightFrontInnerLeg = new PositionTextureVertex(
					hipDisplacementX - (4f + scaleFactor) + (float) rightLegVector.xCoord,
					hipDisplacementY + (float) rightLegVector.yCoord, hipDisplacementZ + (float) rightLegVector.zCoord,
					4, 4);
			PositionTextureVertex rightBackOuterLeg = new PositionTextureVertex(
					0.25f + hipDisplacementX + (float) rightLegVector2.xCoord,
					sneakButtHeight + hipDisplacementY + (float) rightLegVector2.yCoord,
					sneakLegDepth - 0.25f + hipDisplacementZ - (4f + scaleFactor * 2) + (float) rightLegVector2.zCoord,
					0, 0);
			PositionTextureVertex rightBackInnerLeg = new PositionTextureVertex(
					hipDisplacementX - (4f + scaleFactor) + (float) rightLegVector2.xCoord,
					sneakButtHeight + hipDisplacementY + (float) rightLegVector2.yCoord,
					sneakLegDepth - 0.25f + hipDisplacementZ - (4f + scaleFactor * 2) + (float) rightLegVector2.zCoord,
					0, 0);

			PositionTextureVertex rightFrontOuterHip = new PositionTextureVertex(hipDisplacementX, hipDisplacementY,
					hipDisplacementZ, 0, 0);
			PositionTextureVertex rightFrontInnerHip = new PositionTextureVertex(hipDisplacementX - (4f + scaleFactor),
					hipDisplacementY, hipDisplacementZ, 0, 0);
			PositionTextureVertex rightBackOuterHip = new PositionTextureVertex(hipDisplacementX,
					sneakButtHeight + hipDisplacementY,
					sneakLegDepth + hipDisplacementZ - (4f + 0.25f + scaleFactor * 2), 0, 0);
			PositionTextureVertex rightBackInnerHip = new PositionTextureVertex(hipDisplacementX - (4f + scaleFactor),
					sneakButtHeight + hipDisplacementY,
					sneakLegDepth + hipDisplacementZ - (4f + 0.25f + scaleFactor * 2), 4, 0);

			hipDisplacementX = renderer.modelBipedMain.bipedLeftLeg.rotationPointX - 7 + scaleFactor;
			hipDisplacementY = renderer.modelBipedMain.bipedLeftLeg.rotationPointY + 0;
			hipDisplacementZ = renderer.modelBipedMain.bipedLeftLeg.rotationPointZ + 2 + scaleFactor;

			if (theEntity.isSneaking())
			{
				// GL11.glTranslatef(0f / 16f, 2f / 16f, -1.5f / 16f);
				hipDisplacementY += 2f;
				hipDisplacementZ += -7.5f;
			}

			PositionTextureVertex leftFrontOuterLeg = new PositionTextureVertex(
					-0.25f + hipDisplacementX + (float) leftLegVector.xCoord,
					hipDisplacementY + (float) leftLegVector.yCoord,
					0.25f + hipDisplacementZ + (float) leftLegVector.zCoord, 0, 8);
			PositionTextureVertex leftFrontInnerLeg = new PositionTextureVertex(
					hipDisplacementX + (4f + scaleFactor) + (float) leftLegVector.xCoord,
					hipDisplacementY + (float) leftLegVector.yCoord, hipDisplacementZ + (float) leftLegVector.zCoord, 8,
					8);
			PositionTextureVertex leftBackOuterLeg = new PositionTextureVertex(
					-0.25f + hipDisplacementX + (float) leftLegVector2.xCoord,
					sneakButtHeight + hipDisplacementY + (float) leftLegVector2.yCoord, sneakLegDepth - 0.25f
							+ hipDisplacementZ - (4f + 0.25f + scaleFactor * 2) + (float) leftLegVector2.zCoord,
					0, 0);
			PositionTextureVertex leftBackInnerLeg = new PositionTextureVertex(
					hipDisplacementX + (4f + scaleFactor) + (float) leftLegVector2.xCoord,
					sneakButtHeight + hipDisplacementY + (float) leftLegVector2.yCoord, sneakLegDepth - 0.25f
							+ hipDisplacementZ - (4f + 0.25f + scaleFactor * 2) + (float) leftLegVector2.zCoord,
					0, 0);

			PositionTextureVertex leftFrontOuterHip = new PositionTextureVertex(hipDisplacementX, hipDisplacementY,
					hipDisplacementZ, 0, 0);
			PositionTextureVertex leftFrontInnerHip = new PositionTextureVertex(hipDisplacementX + (4f + scaleFactor),
					hipDisplacementY, hipDisplacementZ, 0, 0);
			PositionTextureVertex leftBackOuterHip = new PositionTextureVertex(hipDisplacementX,
					sneakButtHeight + hipDisplacementY,
					sneakLegDepth + hipDisplacementZ - (4f + 0.25f + scaleFactor * 2), 0, 0);
			PositionTextureVertex leftBackInnerHip = rightBackInnerHip;// new
																		// PositionTextureVertex(hipDisplacementX+(4f
																		// +
																		// scaleFactor),hipDisplacementY,hipDisplacementZ-(4f
																		// +
																		// 0.25f
																		// +
																		// scaleFactor*2),8,0);

			int textureOffsetX = 40;
			int textureOffsetY = 15;
			int textureWidth = 64;
			int textureHeight = 32;

			// Right front thigh
			this.quadList[0] = new TexturedQuad(
					new PositionTextureVertex[] { rightFrontOuterHip, rightFrontInnerHip, rightFrontInnerLeg,
							rightFrontOuterLeg },
					textureOffsetX + 4, textureOffsetY + 0, textureOffsetX + 4 + 4, textureOffsetY + 4, textureWidth,
					textureHeight); // petit

			this.quadList[1] = new TexturedQuad(
					new PositionTextureVertex[] { rightFrontInnerHip, rightFrontOuterHip, rightFrontOuterLeg,
							rightFrontInnerLeg },
					textureOffsetX + 4, textureOffsetY + 0, textureOffsetX + 4 + 4, textureOffsetY + 4, textureWidth,
					textureHeight); // petit
			// Right side thigh
			this.quadList[2] = new TexturedQuad(
					new PositionTextureVertex[] { rightBackOuterHip, rightFrontOuterHip, rightFrontOuterLeg,
							rightBackOuterLeg },
					textureOffsetX + 0, textureOffsetY + 0, textureOffsetX + 4, textureOffsetY + 4, textureWidth,
					textureHeight); // petit

			this.quadList[3] = new TexturedQuad(
					new PositionTextureVertex[] { rightFrontOuterHip, rightBackOuterHip, rightBackOuterLeg,
							rightFrontOuterLeg },
					textureOffsetX + 0, textureOffsetY + 0, textureOffsetX + 4, textureOffsetY + 4, textureWidth,
					textureHeight); // petit

			if (renderer.modelBipedMain.bipedRightLeg.rotateAngleX < 0)
			{
				// asscheeks
				this.quadList[4] = new TexturedQuad(
						new PositionTextureVertex[] { rightBackOuterHip, rightBackInnerHip, leftBackInnerLeg,
								rightBackOuterLeg },
						textureOffsetX + 20, textureOffsetY + 0, textureOffsetX + 20 + 4, textureOffsetY + 4,
						textureWidth, textureHeight); // petit

				this.quadList[5] = new TexturedQuad(
						new PositionTextureVertex[] { rightBackInnerHip, rightBackOuterHip, rightBackOuterLeg,
								leftBackInnerLeg },
						textureOffsetX + 20, textureOffsetY + 0, textureOffsetX + 20 + 4, textureOffsetY + 4,
						textureWidth, textureHeight); // petit

				this.quadList[6] = new TexturedQuad(
						new PositionTextureVertex[] { leftBackOuterHip, leftBackInnerHip, leftBackInnerLeg,
								leftBackOuterLeg },
						textureOffsetX + 16, textureOffsetY + 0, textureOffsetX + 16 + 4, textureOffsetY + 4,
						textureWidth, textureHeight); // petit

				this.quadList[7] = new TexturedQuad(
						new PositionTextureVertex[] { leftBackInnerHip, leftBackOuterHip, leftBackOuterLeg,
								leftBackInnerLeg },
						textureOffsetX + 16, textureOffsetY + 0, textureOffsetX + 16 + 4, textureOffsetY + 4,
						textureWidth, textureHeight); // petit

			}
			else
			{

				this.quadList[4] = new TexturedQuad(
						new PositionTextureVertex[] { rightBackOuterHip, rightBackInnerHip, rightBackInnerLeg,
								rightBackOuterLeg },
						textureOffsetX + 20, textureOffsetY + 0, textureOffsetX + 20 + 4, textureOffsetY + 4,
						textureWidth, textureHeight); // petit

				this.quadList[5] = new TexturedQuad(
						new PositionTextureVertex[] { rightBackInnerHip, rightBackOuterHip, rightBackOuterLeg,
								rightBackInnerLeg },
						textureOffsetX + 20, textureOffsetY + 0, textureOffsetX + 20 + 4, textureOffsetY + 4,
						textureWidth, textureHeight); // petit

				this.quadList[6] = new TexturedQuad(
						new PositionTextureVertex[] { rightBackOuterHip, rightBackInnerHip, leftBackInnerLeg,
								rightBackOuterLeg },
						textureOffsetX + 16, textureOffsetY + 0, textureOffsetX + 16 + 4, textureOffsetY + 4,
						textureWidth, textureHeight); // petit

				this.quadList[7] = new TexturedQuad(
						new PositionTextureVertex[] { leftBackInnerHip, leftBackOuterHip, leftBackOuterLeg,
								rightBackInnerLeg },
						textureOffsetX + 16, textureOffsetY + 0, textureOffsetX + 16 + 4, textureOffsetY + 4,
						textureWidth, textureHeight); // petit
			}
			// left front thigh
			this.quadList[8] = new TexturedQuad(
					new PositionTextureVertex[] { leftFrontOuterHip, leftFrontInnerHip, leftFrontInnerLeg,
							leftFrontOuterLeg },
					textureOffsetX + 8, textureOffsetY + 0, textureOffsetX + 8 + 4, textureOffsetY + 4, textureWidth,
					textureHeight); // petit

			this.quadList[9] = new TexturedQuad(
					new PositionTextureVertex[] { leftFrontInnerHip, leftFrontOuterHip, leftFrontOuterLeg,
							leftFrontInnerLeg },
					textureOffsetX + 8, textureOffsetY + 0, textureOffsetX + 8 + 4, textureOffsetY + 4, textureWidth,
					textureHeight); // petit
			// left side thigh
			this.quadList[10] = new TexturedQuad(
					new PositionTextureVertex[] { leftBackOuterHip, leftFrontOuterHip, leftFrontOuterLeg,
							leftBackOuterLeg },
					textureOffsetX + 12, textureOffsetY + 0, textureOffsetX + 12 + 4, textureOffsetY + 4, textureWidth,
					textureHeight); // petit

			this.quadList[11] = new TexturedQuad(
					new PositionTextureVertex[] { leftFrontOuterHip, leftBackOuterHip, leftBackOuterLeg,
							leftFrontOuterLeg },
					textureOffsetX + 12, textureOffsetY + 0, textureOffsetX + 12 + 4, textureOffsetY + 4, textureWidth,
					textureHeight); // petit

			if (!renderer.modelBipedMain.aimedBow)
			{
			}

			if (theEntity.ridingEntity != null && theEntity.ridingEntity instanceof EntityLiving)
			{
				rotateAngle = ((EntityLiving) theEntity.ridingEntity).prevRenderYawOffset
						+ (((EntityLiving) theEntity.ridingEntity).renderYawOffset
								- ((EntityLiving) theEntity.ridingEntity).prevRenderYawOffset) * partialRenderTick;
			}
			else
			{
				rotateAngle = theEntity.prevRenderYawOffset
						+ (theEntity.renderYawOffset - theEntity.prevRenderYawOffset) * partialRenderTick;
			}

			GL11.glPushMatrix();
			if (!theEntity.isSneaking())
			{
				GL11.glTranslatef(0f / 16f, 1f / 16f, 0f / 16f);
			}

			if(!theEntity.isPlayerSleeping())
			{
				GL11.glRotatef(rotateAngle, 0, 1, 0);
			}

			GL11.glRotatef(180, 0, 1, 0);
			if(theEntity.isPlayerSleeping())
			{
				GL11.glRotatef(-90,1,0,0);
				GL11.glTranslatef(0, 0.25f, 1.75f);
				GL11.glRotatef(180, 0, 0, 1);
				GL11.glRotatef(180, 1, 0, 0);
			}
			for (int var3 = 0; var3 < 12; ++var3)
			{
				this.quadList[var3].draw(Tessellator.instance, 0.0625F);
			}
			GL11.glRotatef(180, 0, 1, 0);
			if (theEntity.isSneaking())
			{
				GL11.glTranslatef(0 / 16F, 1f / 16F, -1.5f / 16F);
			}
			
			this.body.render(0.0625F);
			if (theEntity.isSneaking())
			{
				GL11.glTranslatef(0 / 16F, 2.25f / 16F, 1.5f / 16F);
			}
			else
			{
				GL11.glTranslatef(0 / 16F, 2.5f / 16F, /*-0.25*/0f / 16F);
			}
			GL11.glTranslatef(-0.5f/16f, -0.5f/16f, 0);
			this.armL.render(0.0625F);
			GL11.glTranslatef(1f/16f, 0, 0);
			this.armR.render(0.0625F);

			GL11.glPopMatrix();
		}

	}

	/**
	 * Defines what float the third param in setRotationAngles of ModelBase is
	 */
	protected float handleRotationFloat(EntityLivingBase p_77044_1_, float p_77044_2_)
	{
		return (float) p_77044_1_.ticksExisted + p_77044_2_;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{

		if (entity instanceof EntityPlayer)
		{

			this.armL.render(0.0625F);
			this.armR.render(0.0625F);
		}
	}

}
