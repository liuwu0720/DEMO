/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-1 下午2:11:59 
 * @version V1.0 
 */
package com.chnl.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/** 
 * @Package com.chnl.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-1 下午2:11:59 
 * @version V1.0 
 */
public class ExcelHelperTest
{	
	
	public static void main( String[] args )
	{
		String path = "E:\\路线模板.xlsx";
		List< String > list = null;
		try
		{
			list = ExcelHelper.exportListFromExcel( new File( path ) , 0 );
			for ( String str : list )
			{
				System.out.println( str.split( "," )[0] + "- "
				        + str.split( "," )[1] );
			}
		}
		catch ( IOException e )
		{

		}
	}
}
