/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-11 下午3:32:10 
 * @version V1.0 
 */
package com.chnl.service;

import java.util.List;
import java.util.Map;

import com.chnl.entity.Page;
import com.chnl.pojo.LegTruckInfo;

/** 
 * @Package com.chnl.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-11 下午3:32:10 
 * @version V1.0 
 */
public interface LegTruckInfoService
{

	/** 
     * @Description: TODO(根据线路和拖车关联查出满载成本、空载成本) 
     * @param legId 线路ID
     * @param truckId  拖车ID
     * @return 
     *   LegTruckInfo 返回值描述：线路拖车关联查询后的对象
     * @author liuwu
     * @create_date 2014-8-11 下午3:54:37
     */ 
    LegTruckInfo findObjByProp( int legId , int truckId );

	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param parseInt
     * @return 
     *   LegTruckInfo 返回值描述
     * @author liuwu
     * @create_date 2014-8-11 下午6:27:04
     */ 
    LegTruckInfo findById( int id );

	/** 
     * @Description: TODO(获取当前线路与拖车关联的相关信息) 
     * @param currentLegId
     * @param reqTruckId
     * @return 
     *   LegTruckInfo 返回值描述
     * @author liuwu
     * @create_date 2014-8-21 下午6:21:02
     */ 
    LegTruckInfo getLegInfo( int currentLegId , int reqTruckId );

	/**
	 * @Description: TODO(通过JDBC方式查询出线路所有与拖车关联信息)
	 * @param sql
	 * @param p
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-10 下午1:47:54
	 */
	Map< String , Object > getLegInfoJDBC( String sql , Page p );
	
	/**
	 * @Description: TODO(编辑保存)
	 * @param legTruckId
	 * @param fullCost
	 * @param emptyCost
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-10 下午2:26:28
	 */
	void editSaveTruck( int legTruckId , double fullCost , double emptyCost );
	
	/**
	 * @Description: TODO(删除)
	 * @param legTruckId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-10 下午3:02:02
	 */
	void deleteLegTruckById( int legTruckId );
	
	/**
	 * @Description: TODO(获取下拉框中的数据)
	 * @param truckId
	 * @return List<Map<String,Object>> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-12 下午4:00:47
	 */
	List< Map< String , Object >> getSelectLeg( int truckId );
	
	/**
	 * @Description: TODO(新增保存)
	 * @param legTruckInfo
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-12 下午4:31:08
	 */
	void addSaveLegTruck( LegTruckInfo legTruckInfo );
	
	
}
