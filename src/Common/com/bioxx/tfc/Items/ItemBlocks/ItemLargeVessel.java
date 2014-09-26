package com.bioxx.tfc.Items.ItemBlocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import com.bioxx.tfc.TFCBlocks;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.TileEntities.TEPottery;
import com.bioxx.tfc.TileEntities.TEVessel;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.IEquipable;

public class ItemLargeVessel extends ItemTerraBlock implements IEquipable
{
	public ItemLargeVessel(Block par1)
	{
		super(par1);
		setMaxDamage(0);
		setHasSubtypes(true);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	@Override
	public EnumSize getSize(ItemStack is)
	{
		return EnumSize.LARGE;
	}

	@Override
	public EnumWeight getWeight(ItemStack is)
	{
		return EnumWeight.HEAVY;
	}

	public void readFromItemNBT(NBTTagCompound nbt, List arraylist)
	{
		if(nbt != null)
		{
			boolean addFluid = false;
			if(nbt.hasKey("fluidNBT"))
			{
				FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag("fluidNBT"));
				if( fluid != null )
				{
					addFluid = true;
					arraylist.add(EnumChatFormatting.BLUE + fluid.getFluid().getLocalizedName());
				}
			}

			if(!addFluid && nbt.hasKey("Items") )
			{
				NBTTagList nbttaglist = nbt.getTagList("Items", 10);
				if( nbttaglist != null )
				{
					int numItems = nbttaglist.tagCount();
					boolean showMoreText = false;
					if( numItems > 4 && !TFC_Core.showShiftInformation())
					{
						numItems = 3;
						showMoreText = true;
					}
					for( int i = 0; i < numItems; i++ )
					{
						NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
						if( nbttagcompound1 != null )
						{
							ItemStack onlyItem = ItemStack.loadItemStackFromNBT(nbttagcompound1);
							if( onlyItem != null )
							{
								arraylist.add(EnumChatFormatting.GOLD + Integer.toString(onlyItem.stackSize)+"x " + onlyItem.getDisplayName() );
							}
						}
					}
					if( showMoreText )
					{
						arraylist.add(StatCollector.translateToLocal("gui.Barrel.MoreItems"));
					}
				}
			}
		}
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List arraylist, boolean flag)
	{
		ItemTerra.addSizeInformation(is, arraylist);
		readFromItemNBT(is.getTagCompound(), arraylist);
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		if(metadata > 0)
		{
			if (!world.setBlock(x, y, z, field_150939_a, metadata&15, 3))
			{
				return false;
			}

			if (world.getBlock(x, y, z) == field_150939_a)
			{
				field_150939_a.onBlockPlacedBy(world, x, y, z, player, stack);
				field_150939_a.onPostBlockPlaced(world, x, y, z, 0);

				TEVessel te = (TEVessel) world.getTileEntity(x, y, z);
				te.barrelType = metadata;
				return true;
			}
		} 
		else if(metadata == 0 && side == 1 && player.isSneaking())
		{
			TEPottery te;
			Block base = world.getBlock(x, y-1, z);
			if(base != TFCBlocks.Pottery && world.isAirBlock(x, y, z))
			{
				//We only want the pottery to be placeable if the block is solid on top.
				if(!world.isSideSolid(x, y-1, z, ForgeDirection.UP))
					return false;
				world.setBlock(x, y, z, TFCBlocks.Pottery);
			}
			else
			{
				return false;
			}


			if(world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TEPottery) 
			{
				te = (TEPottery) world.getTileEntity(x, y, z);
				if(te.canAddItem(0))
				{
					te.inventory[0] = stack.copy();
					te.inventory[0].stackSize = 1;
					world.markBlockForUpdate(x, y, z);
					return true;
				}				
			}
		}

		return false;
	}

	@Override
	public EquipType getEquipType(ItemStack is) {
		return EquipType.BACK;
	}

	@Override
	public void onEquippedRender() 
	{
		GL11.glTranslatef(0, 0.0f, -0.2F);
	}

	@Override
	public boolean getTooHeavyToCarry(ItemStack is)
	{
		return is.hasTagCompound();
	}
}
