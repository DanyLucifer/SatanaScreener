package ru.cursedsatana.screenmod.other;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

@Config(modid="shieldmod", type = Config.Type.INSTANCE, name = "satana_shield", category = "mySQL")
public class ShieldConfig 
{
    @Comment({"mySQL host"})
	public static String host = "host";
    @Comment({"mySQL user"})
	public static String user = "user";
    @Comment({"mySQL password"})
	public static String password = "password";
    @Comment({"mySQL port"})
	public static int port = 3306;
    @Comment({"mySQL dbname"})
	public static String dbname = "dbname";
    @Comment({"mySQL hwid ban table"})
	public static String hwid_ban_data_table = "hwid_ban";
    @Comment({"mySQL hwid list table"})
	public static String hwid_list_table = "hwid_list";
}
