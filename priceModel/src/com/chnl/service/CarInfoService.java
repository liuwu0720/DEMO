/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-8 下午1:13:51 
 * @version V1.0 
 */
package com.chnl.service;

import java.util.List;

import com.chnl.pojo.CarInfo;

/** 
 * @Package com.chnl.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-8 下午1:13:51 
 * @version V1.0 
 */
public interface CarInfoService
{



	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param car1Id
     * @return 
     *   CarInfo 返回值描述
     * @author liuwu
	 * @param currentLegId 
     * @create_date 2014-8-13 下午4:06:02
     */ 
    CarInfo findCarInfoById( int car1Id, int currentLegId );

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @param order
	 * @return List<LegInfo> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-5 上午10:58:32
	 */
	List< CarInfo > findCarInfoByPage( int page , int pageSize , String sort ,
	        String order );
	
	/**
	 * @Description: TODO(获取所有商品车基本信息，分页时调用)
	 * @return List<CarInfo> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-5 上午11:04:04
	 */
	List< CarInfo > findAllCarInfo();
	
	/**
	 * @Description: TODO(新增，编辑时保存操作)
	 * @param carInfo
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-5 上午11:39:11
	 */
	void saveOrUpdate( CarInfo carInfo );
	
	/**
	 * @Description: TODO(删除操作)
	 * @param carId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-5 下午1:35:32
	 */
	void deleteById( int carId );
	
	/**
	 * @Description: TODO(新需求修改后：通过线路查出对应的商品车属性)
	 * @param carId
	 * @return List<CarInfo> 返回值描述
	 * @author liuwu
	 * @param customerId
	 * @create_date 2014-11-11 上午11:21:14
	 */
	List< CarInfo > searchCarById( String legId , String customerId );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param city
	 * @return List<String> 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-22 上午11:18:28
	 */
	List< String > findAllsmStartCitys( String city );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param startpoint
	 * @return List<String> 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-22 下午1:55:36
	 */
	List< String > findAllsmStartpoints( String startpoint );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param endcity
	 * @return List<String> 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-22 下午2:22:25
	 */
	List< String > findAllendcity( String endcity );
	
	

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legId
	 * @return List<CarInfo> 返回值描述
	 * @author liuwu
	 * @param customerId
	 * @create_date 2014-11-25 下午1:21:06
	 */
	List< CarInfo > searchCarById2( String legId , String customerId );
	
	
}
