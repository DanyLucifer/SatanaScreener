package ru.cursedsatana.screenmod;

import java.util.ArrayList;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import ru.cursedsatana.screenmod.packet.ScreenRequestPacket;

public class AdminScreenCommand extends CommandBase
{

	@Override
	public String getName() {
		return "adminscreen";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/adminscreen";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length==0)
		{
			sender.sendMessage(new TextComponentString("PLEASE SEND NICK MOTHER FUCKER!"));
			return;
		}
		String nick = args[0];
		/*if(server.getOnlinePlayerNames()[0]==args[0] && server.getOnlinePlayerNames()[1] == args[1])
		{*/
		SatanaShieldMod.network.sendTo(new ScreenRequestPacket(), server.getPlayerList().getPlayerByUsername("CursedSatana"));
			sender.sendMessage(new TextComponentString("WAIT! SENDING PACKET!"));
		//}
	}
	
}
