package ru.cursedsatana.screenmod;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import ru.cursedsatana.screenmod.other.SQLDatabase;
import ru.cursedsatana.screenmod.other.Util;
import ru.cursedsatana.screenmod.packet.HardPacket;
import ru.cursedsatana.screenmod.packet.HardRequestPacket;
import ru.cursedsatana.screenmod.packet.ScreenPacket;
import ru.cursedsatana.screenmod.packet.ScreenRequestPacket;

@Mod(modid = "shieldmod", name = "Shield Mod", version="0.1", acceptedMinecraftVersions = "1.12.2")
public class SatanaShieldMod 
{	
	public static SQLDatabase db;
	public static Map<String, String> hwidNick = new HashMap<String, String>();
	public static SimpleNetworkWrapper network;
	public static MinecraftServer server;
	public static Configuration config;
	public static File haveperm;
	
	@Mod.EventHandler
	public void serverStart(FMLServerStartingEvent e)
	{
		server = e.getServer();
		e.registerServerCommand(new AdminScreenCommand());
		e.registerServerCommand(new BanHWIDCommand());
		e.registerServerCommand(new HWIDListCommand());
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e) throws IOException
	{
		/*config = new Configuration(e.getSuggestedConfigurationFile(), "0.1", true);
		config.defaultEncoding = "UTF-8";*/
		//db = new SQLDatabase();
		haveperm = new File(e.getModConfigurationDirectory() + File.separator + "satana_ban_permission.txt");
		if(!haveperm.exists())
			haveperm.createNewFile();
		Util.getAllPermToList(haveperm);
		ConfigManager.sync("shieldmod", Config.Type.INSTANCE);
		db = new SQLDatabase();
		network = NetworkRegistry.INSTANCE.newSimpleChannel("satana");
	    int id = 0;
	    network.registerMessage(ScreenRequestPacket.Handler.class, ScreenRequestPacket.class, ++id, Side.CLIENT);
	    network.registerMessage(HardRequestPacket.Handler.class, HardRequestPacket.class, ++id, Side.CLIENT);
	    network.registerMessage(ScreenPacket.Handler.class, ScreenPacket.class, ++id, Side.SERVER);
	    network.registerMessage(HardPacket.Handler.class, HardPacket.class, ++id, Side.SERVER);
	    e.getModLog().warn("DATABASE IS - " + db);
	}
	
	public static SQLDatabase getDatabase()
	{
		return db;
	}
}
