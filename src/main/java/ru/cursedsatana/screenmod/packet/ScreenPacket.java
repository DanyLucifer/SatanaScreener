package ru.cursedsatana.screenmod.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.cursedsatana.screenmod.SatanaServer;

public class ScreenPacket implements IMessage
{
	public static byte[] i;
	public static int id;
	public static int f;
	
	public ScreenPacket(int id, int f, byte[] array)
	{
		this.f= f;
		this.id = id;
		this.i = array;
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) {
		int k = buf.readInt();
		i = buf.readBytes(k).array();
		f = buf.readInt();
		id = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(i.length);
		buf.writeBytes(i);
		buf.writeInt(f);
		buf.writeInt(id);
	}
	
	public static class Handler implements IMessageHandler<ScreenPacket, IMessage>
	{
		public IMessage onMessage(ScreenPacket packet, MessageContext con)
		{
			SatanaServer.saveScreenshot(packet);
			return null;
		}
	}
}
