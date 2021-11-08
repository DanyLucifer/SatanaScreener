package ru.cursedsatana.screenmod;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import ru.cursedsatana.screenmod.packet.ScreenPacket;

public class SatanaScreener 
{
	static int f;
	static int s;
	static int t;
	static int fo;
	public static void sendScreen() throws AWTException 
	{
		Minecraft mc = Minecraft.getMinecraft();
		int width = mc.displayWidth;
		int height = mc.displayHeight;
		BufferedImage screenShot = new Robot().createScreenCapture(new Rectangle(width, height));
		
		ArrayList<byte[]> sender = getBytes(screenShot);
		
		//SEND PACKET
		int id = 0;
		SatanaShieldMod.network.sendToServer(new ScreenPacket(id, f, sender.get(0)));
		SatanaShieldMod.network.sendToServer(new ScreenPacket(id++, s, sender.get(1)));
		SatanaShieldMod.network.sendToServer(new ScreenPacket(id++, t, sender.get(2)));
		SatanaShieldMod.network.sendToServer(new ScreenPacket(id++, fo, sender.get(3)));
	}
	
	public static ArrayList<byte[]> getBytes(BufferedImage img)
	{
		ArrayList<byte[]> b = new ArrayList<byte[]>();
		ByteArrayOutputStream baos = null;
		try {
		    baos = new ByteArrayOutputStream();
		    ImageIO.write(img, "jpeg", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    try {
		        baos.close();
		    } catch (Exception e) {
		    }
		}
		byte[] k = baos.toByteArray();
		f = k.length/4;
		s = k.length/2;
		t = (int)(k.length*0.75);
		fo = k.length;
		byte[] first = Arrays.copyOfRange(k, 0, k.length/4);
		byte[] second = Arrays.copyOfRange(k, k.length/4, k.length/2);
		byte[] third = Arrays.copyOfRange(k, k.length/2, (int)(k.length * 0.75));
		byte[] fourth = Arrays.copyOfRange(k, (int)(k.length * 0.75), k.length);
		b.add(first);
		b.add(second);
		b.add(third);
		b.add(fourth);
		return b;
	}
}
