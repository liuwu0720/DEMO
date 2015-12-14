/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-21 上午11:43:02 
 * @version V1.0 
 */
package com.chnl.service;

import java.util.List;

import com.chnl.pojo.CarInfo;

/** 
 * @Package com.chnl.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-21 上午11:43:02 
 * @version V1.0 
 */
public interface PrAllLegCarInfoService
{
	
	/**
	 * @Description: TODO(这保存修改后的商品车信息，与路线关联)
	 * @param carInfos
	 *            void 返回值描述
	 * @author liuwu
	 * @param legId
	 * @param typeId
	 * @create_date 2014-11-21 上午11:47:35
	 */
	void saveHadUpdateCarInfo( List< CarInfo > carInfos , int legId , int typeId );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param newCarInfos
	 * @param legId
	 * @param typeId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 下午1:34:25
	 */
	void saveHadUpdateCarInfo2( List< CarInfo > newCarInfos , int legId ,
	        int typeId );
	
	/**
	 * @Description: TODO(根据线路取出所有商品车信息)
	 * @param id
	 * @param type
	 * @param carInfoList
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-26 上午9:16:14
	 */
	void findAllCarInfoByLegId( Integer id , int type ,
	        List< CarInfo > carInfoList );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param car1Id
	 * @param id
	 * @param type
	 * @return CarInfo 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-26 上午10:37:05
	 */
	CarInfo getCarInfo( double car1Id , Integer id , int type );
	
}
