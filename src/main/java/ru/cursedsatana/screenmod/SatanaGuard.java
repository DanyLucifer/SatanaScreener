package ru.cursedsatana.screenmod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import ru.cursedsatana.screenmod.packet.HardPacket;
public class SatanaGuard extends Thread
{
	public EntityPlayerMP p;
	public String system = "";
	public String proc_id = "";
	public String proc_arch = "";
	public String proc_arch6432 = "";
	public int proc_numb = 0;
	public String serial = "";
	public String serial_c = "";
	public String mac = "";
	
	public SatanaGuard(EntityPlayerMP p)
	{
		this.p = p;
	}
	
	public void run() {
	    if (this.p != null) {
	      serverMethod();
	    } else {
	      clientMethod();
	    } 
	  }
	
	public void serverMethod() {}

	public void clientMethod() {
	
	try {
	      this.system = getVal(runCommand("systeminfo"));
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    try {
	      this.proc_id = getVal(System.getenv("PROCESSOR_IDENTIFIER"));
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    try {
	      this.proc_arch = getVal(System.getenv("PROCESSOR_ARCHITECTURE"));
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    try {
	      this.proc_arch6432 = getVal(System.getenv("PROCESSOR_ARCHITEW6432"));
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    try {
	      this.proc_numb = Integer.parseInt(getVal(System.getenv("NUMBER_OF_PROCESSORS")));
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    try {
	      this.serial = getVal(runCommand("wmic diskdrive get serialnumber"));
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    try {
	      this.serial_c = getVal(runCommand("cmd /c vol c:"));
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    try {
	      InetAddress ip = InetAddress.getLocalHost();
	      NetworkInterface network = NetworkInterface.getByInetAddress(ip);
	      byte[] mac = network.getHardwareAddress();
	      StringBuilder sb = new StringBuilder();
	      for (int i = 0; i < mac.length; i++) {
	        sb.append(String.format("%02X%s", new Object[] { Byte.valueOf(mac[i]), (i < mac.length - 1) ? "-" : "" }));
	      } 
	      this.mac = getVal(sb.toString());
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    
	    String nick = Minecraft.getMinecraft().player.getName();
	    SatanaShieldMod.network.sendToServer(new HardPacket(nick, this.system, this.proc_id, this.proc_arch, this.proc_arch6432, this.proc_numb, this.serial, this.serial_c, this.mac));
	  }
	
		public static String getVal(String s) {
		  if (s == null)
		    return ""; 
		  return s;
		}
		
		static String runCommand(String commandName) throws IOException {
		  Runtime runtime = Runtime.getRuntime();
		  Process process = runtime.exec(commandName);
		  BufferedReader systemInformationReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "cp866"));
		  StringBuilder stringBuilder = new StringBuilder();
		  String line;
		  while ((line = systemInformationReader.readLine()) != null) {
		    stringBuilder.append(line);
		    stringBuilder.append(System.lineSeparator());
		  } 
		  return stringBuilder.toString().trim();
		}
}
