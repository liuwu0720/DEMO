package com.chnl.base;
 
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * @Package com.clt.util
 * @Description: 后台返回json
 * @author hjx
 * @date 2014年7月18日 上午11:33:58
 * @version V1.0
 */
public class AjaxUtil
{
	private static void rendText( HttpServletResponse response , String content )
	        throws IOException
	
	{
		response.setCharacterEncoding( "UTF-8" );
		response.getWriter().write( content );
	}
	
	/**
	 * @Description: 封装成json
	 * @param response
	 * @param success
	 *            是否成功
	 * @param message
	 *            返回的消息
	 * @throws IOException
	 *             void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月18日 上午11:45:48
	 */
	public static void rendJson( HttpServletResponse response , boolean success ,
	        String message )
	{
		JSONObject json = new JSONObject();
		json.put( "isSuccess" , success );
		json.put( "message" , message );
		try
		{
			rendText( response , json.toString() );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
}
