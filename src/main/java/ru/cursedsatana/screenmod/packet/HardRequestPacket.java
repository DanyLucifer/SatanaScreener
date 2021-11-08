package ru.cursedsatana.screenmod.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.cursedsatana.screenmod.SatanaGuard;

public class HardRequestPacket implements IMessage
{
	private String nick;
	
	public HardRequestPacket() {}
	
	public HardRequestPacket(String nick)
	{
		this.nick = nick;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.nick = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeUTF8String(buf, nick);
	}
	
	public static class Handler implements IMessageHandler<HardRequestPacket, IMessage>
	{
		public IMessage onMessage(HardRequestPacket packet, MessageContext con)
		{
			new SatanaGuard(null).start();
			return null;
		}
	}
}
