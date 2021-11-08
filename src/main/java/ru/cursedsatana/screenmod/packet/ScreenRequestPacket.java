package ru.cursedsatana.screenmod.packet;

import java.awt.AWTException;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.cursedsatana.screenmod.SatanaScreener;

public class ScreenRequestPacket implements IMessage
{

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		
	}
	
	public static class Handler implements IMessageHandler<ScreenRequestPacket, IMessage>
	{
		public IMessage onMessage(ScreenRequestPacket packet, MessageContext con)
		{
			try {
				SatanaScreener.sendScreen();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
}
