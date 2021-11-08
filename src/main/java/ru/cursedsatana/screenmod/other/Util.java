package ru.cursedsatana.screenmod.other;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.text.TextComponentString;
import ru.cursedsatana.screenmod.SatanaShieldMod;
import ru.cursedsatana.screenmod.wait.HWIDWaitPlayer;
import ru.cursedsatana.screenmod.wait.HWIDWaitPlayer.EnumWaitPlayer;

public class Util 
{
	public static List<HWIDWaitPlayer> waitPlayers = new ArrayList<HWIDWaitPlayer>();
	public static List<String> havePerm = new ArrayList<String>();
	public static List<String> bannedUsers = new ArrayList<String>();
	public static String PREFIX = "[SATANA_SHIELD]: ";

	public static void checkWaitPlayers(String nick, String hwid) throws SQLException 
	{
		for(HWIDWaitPlayer wp : waitPlayers)
		{
			if(wp.getTargetName().equals(nick))
			{
				if(wp.getAction().name() == EnumWaitPlayer.CHECKLISTANDBAN.name())
				{
					if(!SatanaShieldMod.getDatabase().isListedHWID(nick))
					{
						SatanaShieldMod.getDatabase().addHWID(wp.getTargetName(), hwid);
					}
					if(SatanaShieldMod.getDatabase().isBannedHWID(hwid))
					{
						bannedUsers.add(nick);
						SatanaShieldMod.server.commandManager.executeCommand(SatanaShieldMod.server, "kick " + nick + " YOU ARE BANNED!");
					}
					waitPlayers.remove(wp);
					return;
				}
				if(wp.getAction().name() == EnumWaitPlayer.BANHWID.name())
				{
					SatanaShieldMod.server.logWarning("5"); //DEBUG
					SatanaShieldMod.getDatabase().banHWID(nick, hwid);
					SatanaShieldMod.server.getPlayerList().getPlayerByUsername(wp.getModerName()).sendMessage(new TextComponentString(PREFIX + "Player " + wp.getTargetName() + " with HWID: " + hwid + " succesfully banned, kick him!"));
					waitPlayers.remove(wp);
					bannedUsers.add(nick);
					return;
				} 
				if(wp.getAction().name() == EnumWaitPlayer.CHECKLIST.name())
				{
					//CHECK LIST
					waitPlayers.remove(wp);
					return;
				}
			}
		}
	}
	
	public static void getAllPermToList(File file) throws IOException
	{
		BufferedReader r = new BufferedReader(new FileReader(file));
		String moder = r.readLine();
		while (moder!=null) {
			havePerm.add(moder);
			moder = r.readLine();
		}
	}
}
