package ru.cursedsatana.screenmod.other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;

public class SQLDatabase 
{
	public String url;
	public String host;
	public String user;
	public String password;
	public int port;
	public String dbname;
	public String hwid_ban_data_table;
	public String hwid_list_table;
	
	public SQLDatabase()
	{
		host = ShieldConfig.host;
		user = ShieldConfig.user;
		password = ShieldConfig.password;
		port = ShieldConfig.port;
		dbname = ShieldConfig.dbname;
		hwid_ban_data_table = ShieldConfig.hwid_ban_data_table;
		hwid_list_table = ShieldConfig.hwid_list_table;
		url = "jdbc:mysql://" + host + ":" + port + "/" + dbname+"?useSSL=false";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		try {
			Connection c = getConnection();
			Statement s = c.createStatement();
			
			s.executeUpdate("CREATE TABLE IF NOT EXISTS " + hwid_ban_data_table + " (`Nick` TEXT, `HWID` TEXT);");
			s.executeUpdate("CREATE TABLE IF NOT EXISTS " + hwid_list_table + " (`Nick` TEXT, `HWID` TEXT);");
			
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection()
	{
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean isBannedHWID(String HWID) throws SQLException
	{
		try {
		Connection c = getConnection();
		Statement s = c.createStatement();
		
		ResultSet rs = s.executeQuery("SELECT * FROM " + hwid_ban_data_table + " WHERE HWID=`" + HWID + "`");
		
		s.close();
		c.close();
		
		if(rs.getString("HWID")!=null && !rs.getString("HWID").isEmpty())
			return false;
		return true;
		} catch (NullPointerException e)
		{
			return false;
		}
	}
	
	public void banHWID(String name, String HWID) throws SQLException
	{
		Connection c = getConnection();
		Statement s = c.createStatement();
		
		s.executeUpdate(String.format("INSERT INTO " + hwid_ban_data_table + " (`Nick`, `HWID`) VALUES ('%s', '%s');", name, "hwid tipa tut"));
		
		s.close();
		c.close();
	}
	
	public boolean isListedHWID(String Nick) throws SQLException
	{
		try {
		Connection c = getConnection();
		Statement s = c.createStatement();
		
		ResultSet rs = s.executeQuery("SELECT * FROM " + hwid_list_table + " WHERE Nick=`" + Nick + "`");
		
		s.close();
		c.close();
		
		if(rs.getString("Nick")!=null && !rs.getString("Nick").isEmpty())
			return false;
		return true;
		} catch (NullPointerException e)
		{
			return false;
		}
	}
	
	public void addHWID(String name, String HWID) throws SQLException
	{
		Connection c = getConnection();
		Statement s = c.createStatement();
		
		s.executeUpdate(String.format("INSERT INTO " + hwid_list_table + " (`Nick`, `HWID`) VALUES (`%s`, `%s`);", name, HWID));
		
		s.close();
		c.close();
	}
	
	public List<String> getAllByHWID(String HWID) throws SQLException
	{
		List<String> all = new ArrayList<String>();
		Connection c = getConnection();
		Statement statement = c.createStatement();
			
		ResultSet rs = statement.executeQuery("SELECT * FROM " + hwid_list_table + " WHERE HWID=`"+HWID+"`");
		while(rs.next())
			all.add(rs.getString("Nick"));
		
		
		statement.close();
		c.close();
		
		return all;
			
	}
}
