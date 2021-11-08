package ru.cursedsatana.screenmod.packet;

import java.sql.SQLException;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.cursedsatana.screenmod.SatanaShieldMod;
import ru.cursedsatana.screenmod.other.Util;

public class HardPacket implements IMessage
{
	private String nick;
	private String system; 
	private String proc_id;
	private String proc_arch;
	private String proc_arch6432; 
	private int proc_numb;
	private String serial;
	private String serial_c;
	private String mac;
	
	public HardPacket() {}
	
	public HardPacket(String nick, String system, String proc_id, String proc_arch, String proc_arch6432, int proc_numb, String serial, String serial_c, String mac)
	{
		this.nick = nick;
		this.system = system;
	    if (this.system.length() > 15000)
	      this.system = this.system.substring(15000); 
	    this.proc_id = proc_id;
	    this.proc_arch = proc_arch;
	    this.proc_arch6432 = proc_arch6432;
	    this.proc_numb = proc_numb;
	    this.serial = serial;
	    this.serial_c = serial_c;
	    this.mac = mac;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.nick = ByteBufUtils.readUTF8String(buf);
		this.system = ByteBufUtils.readUTF8String(buf);
	    this.proc_id = ByteBufUtils.readUTF8String(buf);
	    this.proc_arch = ByteBufUtils.readUTF8String(buf);
	    this.proc_arch6432 = ByteBufUtils.readUTF8String(buf);
	    this.proc_numb = buf.readInt();
	    this.serial = ByteBufUtils.readUTF8String(buf);
	    this.serial_c = ByteBufUtils.readUTF8String(buf);
	    this.mac = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeUTF8String(buf, this.nick);;
		ByteBufUtils.writeUTF8String(buf, this.system);
	    ByteBufUtils.writeUTF8String(buf, this.proc_id);
	    ByteBufUtils.writeUTF8String(buf, this.proc_arch);
	    ByteBufUtils.writeUTF8String(buf, this.proc_arch6432);
	    buf.writeInt(this.proc_numb);
	    ByteBufUtils.writeUTF8String(buf, this.serial);
	    ByteBufUtils.writeUTF8String(buf, this.serial_c);
	    ByteBufUtils.writeUTF8String(buf, this.mac);
	}
	
	public static class Handler implements IMessageHandler<HardPacket, IMessage>
	{
		public IMessage onMessage(HardPacket packet, MessageContext con)
		{
			String hwid = packet.system + packet.proc_id + packet.proc_arch + packet.proc_arch6432 + packet.proc_numb + packet.serial + packet.serial_c;
			try {
				Util.checkWaitPlayers(packet.nick, hwid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
