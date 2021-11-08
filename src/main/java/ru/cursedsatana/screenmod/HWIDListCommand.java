package ru.cursedsatana.screenmod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import ru.cursedsatana.screenmod.other.Util;
import ru.cursedsatana.screenmod.packet.HardRequestPacket;
import ru.cursedsatana.screenmod.wait.HWIDWaitPlayer;
import ru.cursedsatana.screenmod.wait.HWIDWaitPlayer.EnumWaitPlayer;

public class HWIDListCommand extends CommandBase
{

	@Override
	public String getName() {
		return "hwidlist";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/hwidlist nick";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(!Util.havePerm.contains(sender.getName())){
			return;
		}
		if(args.length==0)
		{
			sender.sendMessage(new TextComponentString(Util.PREFIX + "Send please target name"));
			return;
		}
		if(server.getPlayerList().getPlayerByUsername(args[0])==null)
		{
			sender.sendMessage(new TextComponentString(Util.PREFIX + "Player is not online"));
			return;
		}
		
		sender.sendMessage(new TextComponentString(Util.PREFIX+"Please wait...."));
		Util.waitPlayers.add(new HWIDWaitPlayer(sender.getName(), args[0], EnumWaitPlayer.CHECKLIST));
		SatanaShieldMod.network.sendTo(new HardRequestPacket(sender.getName()), SatanaShieldMod.server.getPlayerList().getPlayerByUsername(args[0]));
	}

}
