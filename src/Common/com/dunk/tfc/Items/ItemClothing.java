package com.dunk.tfc.Items;


import java.util.List;

import com.dunk.tfc.Reference;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Core.TFC_Textures;
import com.dunk.tfc.api.Interfaces.IEquipable;
import com.dunk.tfc.api.Interfaces.ISewable;
import com.dunk.tfc.api.Interfaces.IEquipable.ClothingType;
import com.dunk.tfc.api.Interfaces.IEquipable.EquipType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public abstract class ItemClothing extends ItemTerra implements IEquipable, ISewable
{
	ResourceLocation myTexture;
	boolean[][] clothingAlpha;
	IIcon flatIcon;
	ResourceLocation res;
	EquipType[] exclusions;
	protected EquipType overrideEquip;
	
	int thermalHeat =0;
	int thermalCold =0;
	
	ClothingType myClothingType = ClothingType.NULL;
	
	boolean isWool;
	boolean isStraw;
	
	public ItemClothing()
	{
		super();
		exclusions = new EquipType[0];
	}
	
	public ItemClothing(EquipType[] ex)
	{
		super();
		exclusions = ex;
	}
	
	public ItemClothing setResourceLocation(ResourceLocation loc)
	{
		this.myTexture = loc;
		return this;
	}
	
	public int getColor(ItemStack i)
	{
		if(i.stackTagCompound == null)
		{
			return -1;
		}
		else if(!i.stackTagCompound.hasKey("color"))
		{
			return -1;
		}
		else
		{
			return i.stackTagCompound.getInteger("color");
		}
	}


	@Override
	public EquipType getEquipType(ItemStack is)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public ItemClothing setWool(boolean b)
	{
		this.isWool =b;
		return this;
	}
	
	
	public ItemClothing setStraw(boolean b)
	{
		this.isStraw =b;
		return this;
	}
	
	public boolean isStraw()
	{
		return isStraw;
	}
	
	public boolean isWool()
	{
		return isWool;
	}
	
	
	public int getHeatResistance(ItemStack i)
	{
		return this.thermalHeat;
	}
	
	public int getColdResistance(ItemStack i)
	{
		return this.thermalCold;
	}
	
	public ItemClothing setHeatResistance(int i)
	{
		this.thermalHeat = i;
		return this;
	}
	
	public ItemClothing setColdResistance(int i)
	{
		this.thermalCold = i;
		return this;
	}

	@Override
	public void onEquippedRender()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return this.itemIcon;
	}
	
	@Override
    public int getColorFromItemStack(ItemStack is, int renderPass)
    {
		if(is.stackTagCompound != null && is.stackTagCompound.hasKey("color"))
		{
			return is.stackTagCompound.getInteger("color");
		}
        return 16777215;
    }

	@Override
	public ResourceLocation getFlatTexture()
	{
		return res;
	}
	
	@Override
	public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist)
	{		
		if(getMetalType(is) != null)
		{			
			boolean damp = TFC_Core.isClothingDamp(is,player);
			boolean soaked = TFC_Core.isClothingSoaked(is,player);
			if(damp)
			{
				arraylist.add(EnumChatFormatting.BLUE + TFC_Core.translate("gui.damp")  + (player.capabilities.isCreativeMode?": " + TFC_Core.getClothingWetness(is):""));
			}
			else if(soaked)
			{
				arraylist.add(EnumChatFormatting.BLUE + TFC_Core.translate("gui.soaked") + (player.capabilities.isCreativeMode?": " + TFC_Core.getClothingWetness(is):""));
			}
			
			String heatInformation = TFC_Core.translate("gui.warmth")+": " + EnumChatFormatting.GRAY;
			String coldInformation= TFC_Core.translate("gui.coolth")+": "+ EnumChatFormatting.GRAY;

			if(thermalHeat>0)
			{				
				if(soaked)
				{
					heatInformation += EnumChatFormatting.BLUE;
				}
				for(int i =0;i<thermalHeat;i++)
				{
					if(damp && i == thermalHeat-1)
					{
						heatInformation += EnumChatFormatting.BLUE;
					}
					heatInformation+="+";
				}
			}
			else if(thermalHeat<0)
			{
				if(soaked)
				{
					heatInformation += EnumChatFormatting.BLUE;
				}
				for(int i =0;i<-thermalHeat;i++)
				{
					if(damp && i == (-thermalHeat)-1)
					{
						heatInformation += EnumChatFormatting.BLUE;
					}
					heatInformation+="-";
				}
			}			
			if(thermalCold>0)
			{
				if(soaked)
				{
					coldInformation += EnumChatFormatting.BLUE;
				}
				for(int i =0;i<thermalCold;i++)
				{
					if(damp && i == thermalCold-1)
					{
						coldInformation += EnumChatFormatting.BLUE;
					}
					coldInformation+="+";
				}
			}
			else if(thermalCold<0)
			{
				if(soaked)
				{
					coldInformation += EnumChatFormatting.BLUE;
				}
				for(int i =0;i<-thermalCold;i++)
				{
					if(damp && i == (-thermalCold)-1)
					{
						coldInformation += EnumChatFormatting.BLUE;
					}
					coldInformation+="-";
				}
			}
			if(thermalHeat != 0)
			{
				arraylist.add(heatInformation);
			}
			if(thermalCold!= 0)
			{
				arraylist.add(coldInformation);
			}
			if (TFC_Core.showShiftInformation() && isWool)
			{
				arraylist.add(EnumChatFormatting.ITALIC + TFC_Core.translate("gui.wool_penalty"));
				arraylist.add(EnumChatFormatting.ITALIC + TFC_Core.translate("gui.wool_penalty2"));
				arraylist.add(EnumChatFormatting.ITALIC + TFC_Core.translate("gui.wool_penalty3"));
			}
		//	else
		//	{
		//		arraylist.add(TFC_Core.translate("gui.ShowHelp"));
		//	}
		}
		super.addExtraInformation(is, player, arraylist);
	}
	
	public EquipType[] getMutuallyExclusiveSlots()
	{
		return exclusions;
	}

	@Override
	public void registerIcons(IIconRegister registerer)
	{
		this.itemIcon = registerer
				.registerIcon(Reference.MOD_ID + ":" + textureFolder + this.getUnlocalizedName().replace("item.", ""));

/*		flatIcon = registerer.registerIcon(
				Reference.MOD_ID + ":" + textureFolder + this.getUnlocalizedName().replace("item.", "Flat "));
		*/
		if(!isStraw)
		{
		res = new ResourceLocation(Reference.MOD_ID,
				Reference.ASSET_PATH_ITEM +this.textureFolder + this.getUnlocalizedName().replace("item.", "Flat ")+".png");
		clothingAlpha = TFC_Textures.loadClothingPattern(res);
		}
	}

	public boolean[][] getClothingAlpha()
	{
		return clothingAlpha;
	}

	@Override
	public boolean getTooHeavyToCarry(ItemStack is)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ClothingType getClothingType()
	{
		return myClothingType;
	}
	
	@Override
	public ResourceLocation getClothingTexture(Entity entity, ItemStack itemstack, int num)
	{
		return myTexture;
	}

}
