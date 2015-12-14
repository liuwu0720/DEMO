package clt.com.cn.helps;

/**
 * @Package clt.com.cn.helps
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenbin
 * @date 2014-9-25 上午10:41:41
 * @version V1.0
 */
public class ServiceUtil
{
	
	public static int getPage( String p , int pages )
	{
		int page = 0;
		if ( p == null || p == "" )
		{
			page = 1;
		}
		else
		{
			page = Integer.parseInt( p );
			if ( page < 1 )
			{
				page = 1;
			}
			else if ( page > pages )
			{
				page = pages;
			}
		}
		return page;
	}
}
