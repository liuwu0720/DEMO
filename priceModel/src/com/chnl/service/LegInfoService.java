/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-8 下午1:17:18 
 * @version V1.0 
 */
package com.chnl.service;

import java.util.List;
import java.util.Map;

import com.chnl.pojo.LegInfo;

/** 
 * @Package com.chnl.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-8 下午1:17:18 
 * @version V1.0 
 */
public interface LegInfoService
{

	/** 
     * @Description: TODO(查出所有线路集合) 
     * @return 
     *  List<LegInfo> 返回值描述:返回所有线路集合
     * @author liuwu
     * @create_date 2014-8-8 下午1:23:19
     */ 
   public List< LegInfo > findAllLegs();

	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param currentLegId
     * @return 
     *   LegInfo 返回值描述
     * @author liuwu
     * @create_date 2014-8-14 下午3:09:01
     */ 
	public LegInfo findLegInfoById( int currentLegId );
	
	/**
	 * @Description: TODO(查出线路基本信息；分页并排序)
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @param order
	 * @return List<LegInfo> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-4 下午3:22:42
	 */
	public List< LegInfo > getLegInfoByPage( int page , int pageSize ,
	        String sort , String order );
	
	/**
	 * @Description: TODO(保存操作)
	 * @param legInfo
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-4 下午4:49:20
	 */
	public void saveLeg( LegInfo legInfo );
	
	/**
	 * @Description: TODO(删除线路)
	 * @param legId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-4 下午6:12:16
	 */
	public void deleteLegById( int legId );
	
	/**
	 * @Description: TODO(过滤掉incomeprice ratio为空的线路)
	 * @return List<LegInfo> 返回值描述
	 * @author liuwu
	 * @param legpageSize
	 * @param legpage
	 * @create_date 2014-9-10 下午3:18:40
	 */
	public Map< String , Object > findAllLegsFilter( int legpage ,
	        int legpageSize );
	
}
