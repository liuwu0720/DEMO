package clt.com.cn.helps;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.List;

/**
 * @Package com.clt.util
 * @Description: 该工具类是用于解决 hibernate实体类转化为json
 * @author hjx
 * @date 2014年7月17日 下午5:47:42
 * @version V1.0
 */
public class JsonUtil
{
	/**
	 * 传入任意一个 object对象生成一个指定规格的字符串 内部使用的方法
	 * 
	 * @param object
	 *            任意对象
	 * @return String
	 */
	private static String objectToJson( Object object )
	{
		StringBuilder json = new StringBuilder();
		if ( object == null )
		{
			json.append( "\"\"" );
		}
		else if ( object instanceof String || object instanceof Integer
		        || object instanceof Double || object instanceof Long
		        || object instanceof Float )
		{
			json.append( "\"" ).append( object.toString() ).append( "\"" );
		}
		else if ( object instanceof Date )
		{
			json.append( "\"" ).append( DateUtil.formatTime( ( Date ) object ) )
			        .append( "\"" );
		}
		else
		{
			json.append( beanToJson( object ) );
		}
		return json.toString();
	}
	
	/**
	 * 传入任意一个 Javabean对象生成一个指定规格的字符串 对外使用的方法
	 * 
	 * @param bean
	 *            bean对象
	 * @return String "{}"
	 */
	public static String beanToJson( Object bean )
	{
		StringBuilder json = new StringBuilder();
		json.append( "{" );
		PropertyDescriptor[] props = null;
		try
		{
			props = Introspector.getBeanInfo( bean.getClass() , Object.class )
			        .getPropertyDescriptors();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		if ( props != null )
		{
			for ( int i = 0 ; i < props.length ; i++ )
			{
				try
				{
					if ( ! ( props[i].getPropertyType() ).isInterface() )
					{
						String name = objectToJson( props[i].getName() );
						
						if ( ! name.startsWith( "T" ) )
						{
							String value = objectToJson( props[i].getReadMethod().invoke(
							        bean ) );
							json.append( name );
							json.append( ":" );
							json.append( value );
							json.append( "," );
						}
						
					}
				}
				catch ( Exception e )
				{
				}
			}
			json.setCharAt( json.length() - 1 , '}' );
		}
		else
		{
			json.append( "}" );
		}
		return json.toString();
	}
	
	/**
	 * 通过传入一个列表对象,调用指定方法将列表中的数据生成一个JSON规格指定字符串 对外使用的方法
	 * 
	 * @param list
	 *            列表对象
	 * @return String "[{},{}]"
	 */
	public static String listToJson( List< ? > list )
	{
		StringBuilder json = new StringBuilder();
		json.append( "[" );
		if ( list != null && list.size() > 0 )
		{
			for ( Object obj : list )
			{
				json.append( objectToJson( obj ) );
				json.append( "," );
			}
			json.setCharAt( json.length() - 1 , ']' );
		}
		else
		{
			json.append( "]" );
		}
		return json.toString();
	}
	
}
