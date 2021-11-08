package ru.cursedsatana.screenmod.wait;

public class HWIDWaitPlayer 
{
	public String moderName;
	public String targetName;
	public EnumWaitPlayer action;
	
	
	public HWIDWaitPlayer(String moderName, String targetName, EnumWaitPlayer action)
	{
		this.moderName = moderName;
		this.targetName = targetName;
		this.action = action;
	}
	
	public String getModerName() {
		return moderName;
	}



	public void setModerName(String moderName) {
		this.moderName = moderName;
	}



	public String getTargetName() {
		return targetName;
	}



	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}



	public EnumWaitPlayer getAction() {
		return action;
	}



	public void setAction(EnumWaitPlayer action) {
		this.action = action;
	}
	
	
	
	public static enum EnumWaitPlayer
	{
		CHECKLIST,
		CHECKLISTANDBAN,
		BANHWID
	}
}
