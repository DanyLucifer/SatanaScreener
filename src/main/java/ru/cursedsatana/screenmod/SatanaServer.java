package ru.cursedsatana.screenmod;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import ru.cursedsatana.screenmod.packet.ScreenPacket;

public class SatanaServer 
{
	public static Map<Integer, byte[]> bytes = new HashMap<Integer, byte[]>();
	public static Map<Integer, Integer> fs = new HashMap<Integer, Integer>();
	
	public static void saveScreenshot(ScreenPacket packet)
	{
		byte[] i =  packet.i;
		int id = packet.id;
		int f = packet.f;
		if(bytes.size()<=3)
		{
			bytes.put(id, i);
			fs.put(id, f);
			return;
		}
		try {
			File file = new File("screenshots");
			if(!file.exists())
				file.createNewFile();
			BufferedImage screen = ImageIO.read(new ByteArrayInputStream(i));
			File toSave = new File(file + File.pathSeparator + "test.jpeg");
			ImageIO.write(screen, "jpeg", toSave);
			bytes.clear();
			fs.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
