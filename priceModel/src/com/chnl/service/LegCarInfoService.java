/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-11 下午6:36:52 
 * @version V1.0 
 */
package com.chnl.service;

import java.util.List;
import java.util.Map;

import com.chnl.entity.Page;
import com.chnl.pojo.CarInfo;
import com.chnl.pojo.LegCarInfo;

/** 
 * @Package com.chnl.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-11 下午6:36:52 
 * @version V1.0 
 */
public interface LegCarInfoService
{

	/**
	 * @Description: TODO(根据线路ID查出对象集合)
	 * @param parseInt
	 * @return List<LegCarInfo> 返回值描述
	 * @author liuwu
	 * @create_date 2014-8-11 下午6:42:05
	 */
	List< LegCarInfo > findByLegId( int legId );

	/** 
     * @Description: TODO(根据线路ID取出所有车辆信息，并按重量进行排序) 
     * @param parseInt
     * @return 
     *   List<CarInfo> 返回值描述
     * @author liuwu
     * @create_date 2014-8-13 上午11:22:25
     */ 
    List< CarInfo > findAllCarInfoByLegId( int legId );

	/**
	 * @Description: TODO(分页查询出所有商品车的信息，包括与线路关联的其它属性)
	 * @param parseInt
	 * @param page
	 * @param pageSize
	 * @return List<CarInfo> 返回值描述
	 * @author liuwu
	 * @create_date 2014-8-13 下午2:20:33
	 */ 
    List< CarInfo > findAllCarInfoByLegIdPage( int legId , int page ,
            int pageSize );

	/** 
     * @Description: TODO(查出当前线路下采购支出) 
     * @param currentLegId 
     * @return 
     *   double 返回值描述
     * @author liuwu
     * @create_date 2014-8-22 下午6:55:35
     */ 
    double getCurrentProCost( int currentLegId );


    List< LegCarInfo > getOtherCarInfoByLegId( int legId , int page ,
	        int pageSize );
	
	/**
	 * @Description: TODO(通过JDBC方式查询出相关信息)
	 * @param p
	 * @param sql
	 * @return List<Map<String,Object>> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-9 上午11:17:39
	 */
	Map< String , Object > getCarInfoByJDBC( Page p , String sql );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legcarId
	 * @param incomePrice
	 * @param vendorCost
	 * @param ratio
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-9 下午2:00:31
	 */
	void editeInfo( int legcarId , double incomePrice , double vendorCost ,
	        double ratio );
	
	/**
	 * @Description: TODO(根据ID删除)
	 * @param curId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-9 下午5:06:37
	 */
	void deleteById( int curId );
	
	/**
	 * @Description: TODO(下拉框：查出商品车)
	 * @param legId
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-9 下午6:49:02
	 */
	List< Map< String , Object >> getSelectCar( int legId );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legCarInfo
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-10 上午10:42:02
	 */
	void addSave( LegCarInfo legCarInfo );
	

}
