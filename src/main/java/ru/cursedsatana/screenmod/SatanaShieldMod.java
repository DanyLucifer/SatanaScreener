package ru.cursedsatana.screenmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import ru.cursedsatana.screenmod.packet.HardPacket;
import ru.cursedsatana.screenmod.packet.HardRequestPacket;
import ru.cursedsatana.screenmod.packet.ScreenPacket;
import ru.cursedsatana.screenmod.packet.ScreenRequestPacket;

@Mod(modid = "shieldmod", name = "Shield Mod", version="0.1", acceptedMinecraftVersions = "1.12.2")
public class SatanaShieldMod 
{
	public static SimpleNetworkWrapper network;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		network = NetworkRegistry.INSTANCE.newSimpleChannel("satana");
	    int id = 0;
	    network.registerMessage(ScreenRequestPacket.Handler.class, ScreenRequestPacket.class, ++id, Side.CLIENT);
	    network.registerMessage(HardRequestPacket.Handler.class, HardRequestPacket.class, ++id, Side.CLIENT);
	    network.registerMessage(ScreenPacket.Handler.class, ScreenPacket.class, ++id, Side.SERVER);
	    network.registerMessage(HardPacket.Handler.class, HardPacket.class, ++id, Side.SERVER);
	}
}
