package clt.com.cn.util;

import it.sauronsoftware.base64.Base64;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zharui
 * 
 */
public class MailRemoteCtl
{
	final static String Service_URL = "http://10.20.30.173/roperate.php";
	final static String _domain = "unlcn.com";
	final static String _encryptKey = "Y&2%R$E#7@Q!";
	
	private URL _postUrl = null;
	private HttpURLConnection _conn = null;
	private DataOutputStream _out = null;
	
	public MailRemoteCtl() throws Exception
	{

	}
	
	// public void setEncryptKey(String key) {
	// this._encryptKey=key;
	// }
	
	/**
	 * 向服务方发出http的get请求
	 * 
	 * @param params
	 * @throws IOException
	 */
	private void postRequest( String params ) throws IOException
	{
		String encrypted = encryptWithKey( params );
		String paramsB64 = Base64.encode( encrypted );
		String urlEncoded = URLEncoder.encode( paramsB64 , "UTF-8" );
		try
		{
			_postUrl = new URL( Service_URL + "?" + urlEncoded );
			// 打开连接
			_conn = ( HttpURLConnection ) _postUrl.openConnection();
			// Read from the connection. Default is true.
			_conn.setDoInput( true );
			// Set the post method. Default is GET
			_conn.setRequestMethod( "GET" );
			
			_conn.setUseCaches( false );
			_conn.setInstanceFollowRedirects( false );
			
			_conn.setRequestProperty( "Content-Type" , "text/xml;charset=utf-8" );
			_conn.setRequestProperty( "Connection" , "Kepp-Alive" );
			_conn.setConnectTimeout( 30000 );
			_conn.setReadTimeout( 30000 );
			
			_conn.connect();
		}
		catch ( IOException ioEX )
		{
			ioEX.printStackTrace();
			throw ioEX;
		}
	}
	
	/**
	 * 获取请求结果
	 * 
	 * @return
	 * @throws Exception
	 */
	private String readResponse() throws Exception
	{
		InputStream response = null;
		String ret = "";
		try
		{
			response = _conn.getInputStream();
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
			throw ex;
		}
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		byte[] buffer = new byte[128];
		int len = 0;
		while ( ( len = response.read( buffer ) ) >= 0 )
		{
			bytes.write( buffer , 0 , len );
		}
		
		try
		{
			ret = bytes.toString();
			System.out.println( ret );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			this.close();
		}
		
		return ret;
		
	}
	
	public void close()
	{
		try
		{
			_out.close();
		}
		catch ( Exception ex )
		{
		}
		try
		{
			_out = null;
		}
		catch ( Exception ex )
		{
		}
		
		try
		{
			_conn.disconnect();
		}
		catch ( Exception ex )
		{
		}
		try
		{
			_conn = null;
		}
		catch ( Exception ex )
		{
		}
	}
	
	/**
	 * 添加新邮箱用户，验证密码强度，不合格则丢出异常
	 * 
	 * @param userno
	 *            用户名
	 * @param pwd
	 *            密码
	 * @throws Exception
	 */
	public void addUser( String userno , String pwd ) throws Exception
	{
		userno = userno.trim();
		pwd = pwd.trim();
		testPassword( userno , pwd );
		StringBuffer sb = new StringBuffer( "optcmd=" );
		sb.append( "adduser" );
		sb.append( "&user=" ).append( userno );
		sb.append( "&domain=" ).append( _domain );
		sb.append( "&pass=" ).append( pwd );
		// System.out.println(sb.toString());
		try
		{
			this.postRequest( sb.toString() );
			String ret = this.readResponse();
			String err = "";
			if ( ! ret.equalsIgnoreCase( "OK" ) )
			{
				switch ( Integer.parseInt( ret.replaceAll( "\\D" , "" ) ) )
					{
						case 2 :
							err = "已存在同名邮箱";
							break;
						case 3 :
							err = "已存在同名用户别名";
							break;
						case 4 :
							err = "已存在同名用户组";
							break;
						case 5 :
							err = "域邮箱用户数已达限制";
							break;
						case 6 :
							err = "域的磁盘空间限制已满";
							break;
						default :
							err = ret;
							break;
					}
				throw new Exception( "修改用户密码错误,原因:" + err );
			}
		}
		catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改用户密码，验证密码强度，不合格则丢出异常
	 * 
	 * @param userno
	 *            用户名
	 * @param pwd
	 *            新密码
	 * @throws Exception
	 */
	public void changePassword( String userno , String pwd ) throws Exception
	{
		userno = userno.trim();
		pwd = pwd.trim();
		testPassword( userno , pwd );
		StringBuffer sb = new StringBuffer( "optcmd=" );
		sb.append( "changepassword" );
		sb.append( "&user=" ).append( userno );
		sb.append( "&domain=" ).append( _domain );
		sb.append( "&newpwd=" ).append( pwd );
		// System.out.println(sb.toString());
		try
		{
			this.postRequest( sb.toString() );
			String ret = this.readResponse();
			String err = "";
			if ( ! ret.equalsIgnoreCase( "OK" ) )
			{
				switch ( Integer.parseInt( ret.replaceAll( "\\D" , "" ) ) )
					{
						case 2 :
							err = "用户不存在";
							break;
						default :
							err = ret;
							break;
					}
				throw new Exception( err );
			}
		}
		catch ( IOException e )
		{
			e.printStackTrace();
			throw new IOException( "IO异常" );
		}
	}
	
	/**
	 * 验证邮箱用户名，密码，是否正确
	 * 
	 * @param userno
	 *            用户名
	 * @param pwd
	 *            密码
	 * @throws Exception
	 */
	public void authenticate( String userno , String pwd ) throws Exception
	{
		userno = userno.trim();
		pwd = pwd.trim();
		
		StringBuffer sb = new StringBuffer( "optcmd=" );
		sb.append( "authenticate" );
		sb.append( "&user=" ).append( userno );
		sb.append( "&domain=" ).append( _domain );
		sb.append( "&pass=" ).append( pwd );
		// System.out.println(sb.toString());
		try
		{
			this.postRequest( sb.toString() );
			String ret = this.readResponse();
			String err = "";
			if ( ! ret.equalsIgnoreCase( "OK" ) )
			{
				switch ( Integer.parseInt( ret.replaceAll( "\\D" , "" ) ) )
					{
						case 2 :
							err = "用户或密码错误";
							break;
						case 4 :
							err = "用户或密码错误";
							break;
						default :
							err = ret;
							break;
					}
				throw new Exception( "验证用户信息错误,原因:" + err );
			}
		}
		catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试密码强度，密码不能包含空格，长度最少8位，不能包含用户名，不能为纯数字，纯字母，单个字符连续出现次数不能大于等于密码总长度一半（向下取整）
	 * 
	 * @param userno
	 *            用户名
	 * @param pwd
	 *            密码
	 * @throws Exception
	 */
	public static void testPassword( String userno , String pwd ) throws Exception
	{
		List< String > arrSimple = new ArrayList< String >();
		arrSimple.add( "abc" );
		arrSimple.add( "fgh" );
		arrSimple.add( "xyz" );
		arrSimple.add( "qaz" );
		arrSimple.add( "wsx" );
		arrSimple.add( "qwe" );
		arrSimple.add( "123" );
		arrSimple.add( "234" );
		arrSimple.add( "345" );
		arrSimple.add( "456" );
		arrSimple.add( "567" );
		arrSimple.add( "678" );
		arrSimple.add( "789" );
		arrSimple.add( "890" );
		
		if ( pwd.lastIndexOf( " " ) >= 0 )
		{
			throw new Exception( "密码不能包含空格" );
		}
		if ( pwd.length() < 8 )
		{
			throw new Exception( "密码长度至少需要8位" );
		}
		if ( pwd.lastIndexOf( userno ) >= 0 )
		{
			throw new Exception( "密码不能包含用户名" );
		}
		Pattern pattern = Pattern.compile( "^\\d*$|^[a-zA-Z]*$" );
		Matcher matcher = pattern.matcher( pwd );
		if ( matcher.find() )
		{
			throw new Exception( "新密码过于简单，注意密码不能为纯数字，纯字母，且长度必须大于8，密码区分大小写" );
		}
		char chr;
		for ( int i = 0 ; i < pwd.length() ; i++ )
		{
			chr = pwd.charAt( i );
			pattern = Pattern.compile( chr + "{"
			        + Math.round( Math.floor( pwd.length() / 2 ) ) + ",}" );
			matcher = pattern.matcher( pwd );
			if ( matcher.find() )
			{
				throw new Exception( "新密码中" + chr + "不能连续出现"
				        + Math.round( Math.floor( pwd.length() / 2 ) ) + "次以上" );
			}
		}
		for ( int i = 0 ; i < arrSimple.size() ; i++ )
		{
			if ( pwd.lastIndexOf( arrSimple.get( i ) ) >= 0 )
			{
				throw new Exception( "新密码中不能连续出现［" + arrSimple.get( i ) + "］" );
			}
		}
	}
	
	/**
	 * 返回字符串s重复n次的结果字符串
	 * 
	 * @param s
	 * @param n
	 * @return
	 */
	public static String str_repeat( String s , Integer n )
	{
		StringBuilder sb = new StringBuilder( s );
		for ( int i = 0 ; i < n ; i++ )
		{
			sb.append( s );
		}
		return sb.toString();
	}
	
	/**
	 * 字符串data使用密钥k进行异或加密
	 * 
	 * @param data
	 *            待加密字符串
	 * @param k
	 *            密钥
	 * @return
	 */
	public static String encryptWithKey( String data , String k )
	{
		Integer times = ( int ) Math.ceil( data.length() / k.length() );
		String keyRepeater = str_repeat( k , times );
		char[] arr = data.toCharArray();
		int[] dst = new int[arr.length];
		StringBuilder sb = new StringBuilder();
		for ( int i = 0 ; i < arr.length ; i++ )
		{
			dst[i] = ( int ) arr[i] ^ ( int ) keyRepeater.charAt( i );
			sb.append( ( char ) dst[i] );
		}
		
		return sb.toString();
	}
	
	/**
	 * 加密方法重载
	 * 
	 * @param data
	 * @return
	 */
	public static String encryptWithKey( String data )
	{
		return encryptWithKey( data , _encryptKey );
	}
}
