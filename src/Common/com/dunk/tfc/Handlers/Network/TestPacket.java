package com.dunk.tfc.Handlers.Network;

import com.dunk.tfc.TerraFirmaCraft;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public class TestPacket extends AbstractPacket
{
	private String msg;

	public TestPacket() {}

	public TestPacket(String s)
	{
		msg = s;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		ByteBufUtils.writeUTF8String(buffer, msg);
		
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		msg = ByteBufUtils.readUTF8String(buffer);
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		TerraFirmaCraft.LOG.info("++++++++++++Client: " + msg);
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		TerraFirmaCraft.LOG.info("++++++++++++Server: " + msg);
	}

}
