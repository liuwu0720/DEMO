package com.chnl.base;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;

public class CGenrouteLine
{
	private static final String _id = "clt";
	private static final String _pwd = "oms";
	private String _genRouteURL = "";
	private static String _reqXML = null;
	
	public CGenrouteLine() throws KeyManagementException
	{
		java.io.FileInputStream fis = null;
		
		try
		{
			// 默认 访问的是 满载程序CalcBestRoute_M2M
			_genRouteURL = FilePropertiesUtil.getInstance().getCfg(
			        "genRouteURL" )
			        + "genRoute.do";
			
		}
		catch ( Exception e )
		{
		}
	}
	
	/**
	 * 访问其他模型时，访问路径要更改
	 * 
	 * @param url
	 *            路径的后缀名 genRoute.do 满载程序CalcBestRoute_M2M AddCalcBestRoute.do
	 *            追加程序 Add_CalcBestRoute_M2M CalcBestRouteM2M2.do
	 *            第二、三路线程序CalcBestRoute_M2M2 EmptyCalcBestRoute.do
	 *            空载程序Empty_CalcBestRoute
	 * @throws KeyManagementException
	 */
	public CGenrouteLine( String url ) throws KeyManagementException
	{
		java.io.FileInputStream fis = null;
		
		try
		{
			_genRouteURL = FilePropertiesUtil.getInstance().getCfg(
			        "genRouteURL" )
			        + url;
			
		}
		catch ( Exception e )
		{
			
		}
		
	}
	
	/**
	 * 访问 满载程序CalcBestRoute_M2M 或者 空载程序Empty_CalcBestRoute 生产 参数 xml
	 * 
	 * @param userno
	 * @param orderlist
	 * @param vehiclelist
	 * @param start
	 * @param end
	 * @param weight
	 * @param confline
	 *            这个参数 可以随便传，程序会从File.properties 里面读取
	 */
	public static void buildRequestXMLString( String userno , String orderlist ,
	        String vehiclelist , String start , String end , String weight ,
	        String confline )
	{
		StringBuffer sb = new StringBuffer();
		confline = FilePropertiesUtil.getInstance().getCfg(
		        "genRouteDateSource" );
		sb.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" )
		        .append( "<root>" ).append( "<client>" ).append( "<id>" )
		        .append( _id ).append( "</id>" ).append( "<pwd>" )
		        .append( _pwd ).append( "</pwd>" ).append( "</client>" )
		        .append( "<params>" ).append( "<userno>" ).append( userno )
		        .append( "</userno>" ).append( "<orderlist>" )
		        .append( orderlist ).append( "</orderlist>" )
		        .append( "<vehiclelist>" ).append( vehiclelist )
		        .append( "</vehiclelist>" ).append( "<start>" ).append( start )
		        .append( "</start>" ).append( "<end>" ).append( end )
		        .append( "</end>" ).append( "<weighttype>" ).append( weight )
		        .append( "</weighttype>" ).append( "<datasource>" )
		        .append( confline ).append( "</datasource>" )
		        .append( "</params>" ).append( " </root>" );
		
		System.out.println( sb.toString() );
		_reqXML = sb.toString();
	}
	
	/**
	 * 访问 追加程序 Add_CalcBestRoute_M2M 生产 参数 xml
	 * 
	 * @param userno
	 * @param orderlist
	 * @param addstrOrderID
	 * @param vehiclelist
	 * @param start
	 * @param end
	 * @param weight
	 * @param confline
	 *            这个参数 可以随便传，程序会从File.properties 里面读取
	 */
	public static void buildRequestXMLByAdd( String userno , String orderlist ,
	        String addstrOrderID , String vehiclelist , String start ,
	        String end , String weight , String confline )
	{
		StringBuffer sb = new StringBuffer();
		confline = FilePropertiesUtil.getInstance().getCfg(
		        "genRouteDateSource" );
		sb.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" )
		        .append( "<root>" ).append( "<client>" ).append( "<id>" )
		        .append( _id ).append( "</id>" ).append( "<pwd>" )
		        .append( _pwd ).append( "</pwd>" ).append( "</client>" )
		        .append( "<params>" ).append( "<userno>" ).append( userno )
		        .append( "</userno>" ).append( "<orderlist>" )
		        .append( orderlist ).append( "</orderlist>" )
		        .append( "<addstrOrderID>" ).append( addstrOrderID )
		        .append( "</addstrOrderID>" ).append( "<vehiclelist>" )
		        .append( vehiclelist ).append( "</vehiclelist>" )
		        .append( "<start>" ).append( start ).append( "</start>" )
		        .append( "<end>" ).append( end ).append( "</end>" )
		        .append( "<weighttype>" ).append( weight )
		        .append( "</weighttype>" ).append( "<datasource>" )
		        .append( confline ).append( "</datasource>" )
		        .append( "</params>" ).append( " </root>" );
		
		System.out.println( sb.toString() );
		_reqXML = sb.toString();
	}
	
	/**
	 * 访问 第二、三路线程序CalcBestRoute_M2M2 生产 参数 xml
	 * 
	 * @param userno
	 * @param orderlist
	 * @param addstrOrderID
	 * @param vehiclelist
	 * @param start
	 * @param end
	 * @param weight
	 * @param confline这个参数
	 *            可以随便传，程序会从File.properties 里面读取
	 */
	public static void buildRequestXMLByTwoThree( String userno ,
	        String vehiclelist , String confline )
	{
		StringBuffer sb = new StringBuffer();
		confline = FilePropertiesUtil.getInstance().getCfg(
		        "genRouteDateSource" );
		sb.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" )
		        .append( "<root>" ).append( "<client>" ).append( "<id>" )
		        .append( _id ).append( "</id>" ).append( "<pwd>" )
		        .append( _pwd ).append( "</pwd>" ).append( "</client>" )
		        .append( "<params>" ).append( "<userno>" ).append( userno )
		        .append( "</userno>" ).append( "<vehiclelist>" )
		        .append( vehiclelist ).append( "</vehiclelist>" )
		        .append( "<datasource>" ).append( confline )
		        .append( "</datasource>" ).append( "</params>" )
		        .append( " </root>" );
		
		System.out.println( sb.toString() );
		_reqXML = sb.toString();
	}
	
	/**
	 * 访问 托运车选择生产 参数 xml
	 * 
	 * @param userno
	 * @param orderlist
	 * @param vehiclelist
	 * @param confline
	 *            这个参数 可以随便传，程序会从File.properties 里面读取
	 */
	public static void buildRequestXMLBySelect( String userno ,
	        String orderlist , String vehiclelist , String confline )
	{
		StringBuffer sb = new StringBuffer();
		confline = FilePropertiesUtil.getInstance().getCfg(
		        "genRouteDateSource" );
		sb.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" )
		        .append( "<root>" ).append( "<client>" ).append( "<id>" )
		        .append( _id ).append( "</id>" ).append( "<pwd>" )
		        .append( _pwd ).append( "</pwd>" ).append( "</client>" )
		        .append( "<params>" ).append( "<userno>" ).append( userno )
		        .append( "</userno>" ).append( "<strOrderID>" )
		        .append( orderlist ).append( "</strOrderID>" )
		        .append( "<strVehicleID>" ).append( vehiclelist )
		        .append( "</strVehicleID>" ).append( "<datasource>" )
		        .append( confline ).append( "</datasource>" )
		        .append( "</params>" ).append( " </root>" );
		
		System.out.println( sb.toString() );
		_reqXML = sb.toString();
	}
	
	public static String getRoute( String servletUrl , String content )
	{
		String result = null;
		
		BufferedReader br = null;
		OutputStreamWriter out = null;
		HttpURLConnection con = null;
		
		try
		{
			URL url = new URL( servletUrl );
			
			con = ( HttpURLConnection ) url.openConnection();
			con.setDoOutput( true );
			con.setRequestMethod( "POST" );
			
			out = new OutputStreamWriter( con.getOutputStream() , "UTF-8" );
			
			out.write( content );
			
			out.flush();
			
			br = new BufferedReader( new InputStreamReader(
			        con.getInputStream() , "UTF-8" ) );
			
			String line = null;
			
			StringBuilder sb = new StringBuilder();
			
			while ( ( line = br.readLine() ) != null )
			{
				sb.append( line );
			}
			
			result = sb.toString();
			
			System.out.println( result );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if ( br != null )
			{
				try
				{
					br.close();
				}
				catch ( IOException e )
				{
					e.printStackTrace();
				}
			}
			
			if ( out != null )
			{
				try
				{
					out.close();
				}
				catch ( IOException e )
				{
					e.printStackTrace();
				}
			}
			
			if ( con != null )
			{
				con.disconnect();
				con = null;
			}
		}
		
		return result;
	}
	
	private Document getDocFromString( String strInput )
	{
		Document _doc = null; // 文档对象
		Pattern patt = Pattern.compile( "<Result>.+</Result>" );
		Matcher matcher = patt.matcher( strInput );
		while ( matcher.find() )
		{
			strInput = matcher.group( 0 );
		}
		try
		{
			SAXBuilder builder = new SAXBuilder( false ); // 建立SAX工厂
			
			builder.setEntityResolver( new NoOpEntityResolver() );
			builder.setExpandEntities( false );
			_doc = builder.build( new ByteArrayInputStream( strInput
			        .getBytes( "UTF-8" ) ) ); // 生XML文档树
		}
		catch ( Exception e )
		{
			System.out.println( e.toString() );
			System.out.println( "----Str2DocErr" );
			_doc = null;
		}
		finally
		{
			
		}
		return _doc;
	}
	
	public Document getDocRoute()
	{
		Document docRet = null;
		String strXML = getRoute( _genRouteURL , _reqXML );
		if ( null == strXML )
			return null;
		docRet = getDocFromString( strXML );
		return docRet;
	}
	
	public String getStringRoute()
	{
		String strXML = getRoute( _genRouteURL , _reqXML );
		return strXML;
	}
	
	/**
	 * @调用说明
	 */
	public static void main( String[] args )
	{
		
		String MAS_ID = "1";
		String PASSWORD = "password";
		String hehe = "13667097426";
		String strOrderNo = "0170274510/JYD99710";
		// String strDN = (strOrderNo.lastIndexOf("/")>-1) ?
		// strOrderNo.substring(0,strOrderNo.lastIndexOf("/")):strOrderNo;
		String strDN = strOrderNo.substring( 0 , strOrderNo.lastIndexOf( "/" ) );
		System.out.println( strDN );
		buildRequestXMLString( "lzlu" , "(386691)" , "(204)" , "九江" , "北京" ,
		        "1" , "1" );
		
		getRoute( "http://192.168.0.211:7001/genRoute/genRoute.do" , _reqXML );
		// getRoute(_genRouteURL,_reqXML);
	}
	
	/**
	 * @Description: TODO(生成XML文件传递参数)
	 * @param gi
	 * @param iD
	 * @param vcstartcity
	 * @param vcdestination
	 * @param vcstartplace
	 * @param str_id
	 * @param n
	 *            void 返回值描述
	 * @author liuwu
	 * @param empty_flag
	 * @create_date 2014-9-24 下午5:36:19
	 */
	public void buildRequestXMLPrice( String gi , String iD ,
	        String vcstartcity , String vcdestination , String vcstartplace ,
	        String str_id , String n , String empty_flag )
	{
		StringBuffer sb = new StringBuffer();
		n = FilePropertiesUtil.getInstance().getCfg( "genRouteDateSource" );
		sb.append( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" )
		        .append( "<root>" ).append( "<params>" ).append( "<ID>" )
		        .append( iD ).append( "</ID>" ).append( "<Gi>" ).append( gi )
		        .append( "</Gi>" ).append( "<vcstartcity>" )
		        .append( vcstartcity ).append( "</vcstartcity>" )
		        .append( "<vcstartplace>" ).append( vcstartplace )
		        .append( "</vcstartplace>" ).append( "<vcdestination>" )
		        .append( vcdestination ).append( "</vcdestination>" )
		        .append( "<str_id>" ).append( str_id ).append( "</str_id>" )
		        .append( "<n>" ).append( n ).append( "</n>" )
		        .append( "<empty_flag>" ).append( empty_flag )
		        .append( "</empty_flag>" ).append( "</params>" )
		        .append( "</root>" );
		
		System.out.println( sb.toString() );
		_reqXML = sb.toString();
		
	}

}
