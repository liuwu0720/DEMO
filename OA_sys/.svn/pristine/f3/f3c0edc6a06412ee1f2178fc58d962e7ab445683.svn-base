/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-9-17 下午2:14:14 
 * @version V1.0 
 */
package clt.com.cn.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;



/**
 * @Package com.chnl.base
 * @Description: TODO(连接另外一个数据库的工具类)
 * @author liuwu
 * @date 2014-9-17 下午2:14:14
 * @version V1.0
 */
public class DbUtil
{	
	static String driver;
	static String url;
	static String username;
	static String password;
	
	public static void getParam()
	{
		String requrl = getUrl();
		File file = new File( requrl + "/dbconfig/jdbc.properties" );

		Properties pro = new Properties();
		try
		{
			FileInputStream inStr = new FileInputStream( file );
			pro.load( inStr );
			driver = pro.getProperty( "driver" );
			url = pro.getProperty( "url" );
			username = pro.getProperty( "username" );
			password = pro.getProperty( "password" );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}

	}
	

	public static Connection getConnection()
	{
		getParam();
		Connection conn = null;
		try
		{

			conn = DriverManager.getConnection( url , username , password );

		}

		catch ( SQLException e )
		{
			e.printStackTrace();
		}

		return conn;
		
	}
	
	public static void closeConnection( ResultSet rs , PreparedStatement ps ,
	        Connection conn )
	{	
		if ( rs != null )
		{
			try
			{
				rs.close();
			}
			catch ( SQLException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if ( ps != null )
		{
			try
			{
				ps.close();
			}
			catch ( SQLException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if ( conn != null )
		{
			try
			{
				conn.close();
			}
			catch ( SQLException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static String getUrl()
	{
		java.net.URL u = DbUtil.class.getResource( "" );
		String str = u.toString();
		// 截去一些前面6个无用的字符
		str = str.substring( 6 , str.length() );
		// 将%20换成空格（如果文件夹的名称带有空格的话，会在取得的字符串上变成%20）
		
		str = str.replaceAll( "%20" , " " );
		// 查找“WEB-INF”在该字符串的位置
		
		int num = str.indexOf( "WEB-INF" );
		// 截取即可
		str = str.substring( 0 , num + "WEB-INF".length() );
		return str;
	}
	public static void main( String[] args )
	{
		DbUtil dbUtil = new DbUtil();
		System.out.println( dbUtil.getConnection() );
		System.out.println( getUrl() );
	}
}
