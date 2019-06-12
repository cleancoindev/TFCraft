package com.dunk.tfc.api.Crafting;

import com.dunk.tfc.api.Food;
import com.dunk.tfc.api.Enums.EnumFoodGroup;
import com.dunk.tfc.api.Interfaces.IFood;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class BarrelVinegarRecipe extends BarrelRecipe
{

	public BarrelVinegarRecipe(FluidStack inputFluid, FluidStack outputFluid)
	{
		super(null, inputFluid, null, outputFluid);
		this.setMinTechLevel(0);
	}

	@Override
	public Boolean matches(ItemStack item, FluidStack fluid)
	{
		if(item != null && item.getItem() instanceof IFood)
		{
			if (fluid.isFluidEqual(recipeFluid) && ((IFood) item.getItem()).getFoodGroup() == EnumFoodGroup.Fruit && Food.getWeight(item) >= 1f * (fluid.amount / 100))
			{
				return true;
			}
		}
		return false;
	}
}
