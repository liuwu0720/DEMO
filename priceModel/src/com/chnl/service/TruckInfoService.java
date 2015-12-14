/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-8 下午4:44:12 
 * @version V1.0 
 */
package com.chnl.service;

import java.util.List;

import com.chnl.pojo.TruckInfo;

/** 
 * @Package com.chnl.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-8 下午4:44:12 
 * @version V1.0 
 */

public interface TruckInfoService
{

	/** 
     * @Description: TODO(查出所有拖车信息) 
     * @return 
     *   List<TruckInfo> 返回值描述：拖车信息
     * @author liuwu
     * @create_date 2014-8-8 下午4:47:51
     */ 
    List< TruckInfo > findAllTruckInfos();

	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param reqTruckId
     * @return 
     *   TruckInfo 返回值描述
     * @author liuwu
     * @create_date 2014-8-11 上午8:54:41
     */ 
	TruckInfo findById( int reqTruckId );
	
	/**
	 * @Description: TODO(分页查询所有拖车的基本信息)
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @param order
	 * @return List<TruckInfo> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-5 下午1:58:48
	 */
	List< TruckInfo > findAllTruckByPage( int page , int pageSize ,
	        String sort , String order );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param truckInfo
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-5 下午2:13:57
	 */
	void saveOrUpdate( TruckInfo truckInfo );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param truckId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-5 下午2:14:47
	 */
	void deleteById( int truckId );
	
	/**
	 * @Description: TODO(通过关联表LEGTRUCKINFO过滤掉FULLCOST EMPTYCOST为空的拖车)
	 * @return List<TruckInfo> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-10 下午3:44:28
	 */
	List< TruckInfo > findAllTruckInfosFilter();
	
}
