package com.dunk.tfc.Handlers.Network;

import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.TileEntities.TEFoodPrep;
import com.dunk.tfc.api.Constant.Global;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public class CreateMealPacket extends AbstractPacket
{
	private byte flag; //this flag is for future use, if we need other options
	private int x;
	private int y;
	private int z;

	public CreateMealPacket(){}

	public CreateMealPacket(int f, TEFoodPrep te)
	{
		this.flag = (byte)f;
		this.x = te.xCoord;
		this.y = te.yCoord;
		this.z = te.zCoord;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		buffer.writeByte(this.flag);
		if(flag == 0)
		{
			buffer.writeInt(this.x);
			buffer.writeInt(this.y);
			buffer.writeInt(this.z);
		}
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		this.flag = buffer.readByte();
		if(this.flag == 0)
		{
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
		}
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		if(flag == 0)
		{
			TEFoodPrep te = (TEFoodPrep) player.worldObj.getTileEntity(x, y, z);
			TFC_Core.getSkillStats(player).increaseSkill(Global.SKILL_COOKING, 1);
			te.actionCreate(player);
		}
	}

}
