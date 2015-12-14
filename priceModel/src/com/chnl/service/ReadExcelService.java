/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-3 下午2:10:57 
 * @version V1.0 
 */
package com.chnl.service;

import java.io.InputStream;
import java.util.List;

import com.chnl.pojo.PrLegfileimport;

/** 
 * @Package com.chnl.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-3 下午2:10:57 
 * @version V1.0 
 */
public interface ReadExcelService
{
	
	/**
	 * @Description: TODO(读取EXCEL)
	 * @param inputStream
	 *            void 返回值描述
	 * @author liuwu
	 * @return
	 * @create_date 2014-11-3 下午2:23:53
	 */
	List< String > readExcel( InputStream inputStream );
	
	/**
	 * @Description: TODO(检查导入文件中数据的有效性)
	 * @param locationList
	 *            void 返回值描述
	 * @author liuwu
	 * @param prLegfileimports
	 * @return
	 * @create_date 2014-11-6 下午1:57:02
	 */
	public String checkDataAvailability( List< String > locationList ,
	        List< PrLegfileimport > prLegfileimports );
	
}
