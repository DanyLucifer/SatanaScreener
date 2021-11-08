package ru.cursedsatana.screenmod.listener;

import java.sql.SQLException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import ru.cursedsatana.screenmod.SatanaShieldMod;
import ru.cursedsatana.screenmod.other.Util;
import ru.cursedsatana.screenmod.packet.HardRequestPacket;
import ru.cursedsatana.screenmod.wait.HWIDWaitPlayer;
import ru.cursedsatana.screenmod.wait.HWIDWaitPlayer.EnumWaitPlayer;

@EventBusSubscriber
public class BanHandler 
{
	@SubscribeEvent
	public void checkBan(PlayerLoggedInEvent e) throws SQLException
	{
		if(Util.bannedUsers.contains(e.player.getName()))
		{
			SatanaShieldMod.server.commandManager.executeCommand(SatanaShieldMod.server, "kick " + e.player.getName() + " YOU ARE BANNED!");
			return;
		}
		//CHECK MODER FILE
		if(Util.havePerm.contains(e.player.getName()))
			return;
		//CODE
		Util.waitPlayers.add(new HWIDWaitPlayer("dNAJfsFIEbhgsrhsfaThefs", e.player.getName(), EnumWaitPlayer.CHECKLISTANDBAN));
		SatanaShieldMod.network.sendTo(new HardRequestPacket("dNAJfsFIEbhgsrhsfaThefs"), (EntityPlayerMP)e.player);
	}
}
