package com.dunk.tfc.Food;

import java.util.Random;

import com.dunk.tfc.TileEntities.TECrop;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CropIndexPepper extends CropIndex
{
	public CropIndexPepper(int id, String name, int type, int growth, int stages, float minGTemp, float minATemp, Item seed)
	{
		super(id,name,type,growth,stages,minGTemp,minATemp, seed);
	}
	public CropIndexPepper(int id, String name, int type, int growth, int stages, float minGTemp, float minATemp, float nutrientUsageMultiplier, Item seed)
	{
		super(id,name,type,growth,stages,minGTemp,minATemp, seed);
		nutrientUsageMult = nutrientUsageMultiplier;
	}
	public CropIndexPepper(int id, String name, int type, int growth, int stages, float minGTemp, float minATemp, float nutrientUsageMultiplier, Item seed, int[] nutriRestore)
	{
		super(id,name,type,growth,stages,minGTemp,minATemp, seed);
		nutrientExtraRestore = nutriRestore.clone();
		nutrientUsageMult = nutrientUsageMultiplier;
	}

	@Override
	public ItemStack getOutput1(TECrop crop)
	{
		if(output1 != null && crop.growth >= 5 && crop.growth < 6)
		{
			ItemStack is = new ItemStack(output1);
			Random r = new Random();
			if(r.nextInt(100) < chanceForOutput1)
			{
				ItemFoodTFC.createTag(is, getWeight(output1Avg, r));
				return is;
			}
		}
		return null;
	}
	@Override
	public ItemStack getOutput2(TECrop crop)
	{
		if(output2 != null && crop.growth >= 6)
		{
			ItemStack is = new ItemStack(output2);
			Random r = new Random();
			if(r.nextInt(100) < chanceForOutput2)
			{
				ItemFoodTFC.createTag(is, getWeight(output2Avg, r));
				return is;
			}
		}
		return null;
	}
}
