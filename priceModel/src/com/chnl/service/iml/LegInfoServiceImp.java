/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-8 下午1:19:12 
 * @version V1.0 
 */
package com.chnl.service.iml;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnl.dao.LegInfoDAO;
import com.chnl.pojo.LegInfo;
import com.chnl.service.LegInfoService;

/** 
 * @Package com.chnl.service.iml 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-8 下午1:19:12 
 * @version V1.0 
 */
@Service
public class LegInfoServiceImp implements LegInfoService
{
	@Autowired
	public LegInfoDAO legInfoDAO;
	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @return 
     * @author liuwu
     * @create_date 2014-8-8 下午1:24:13
     */ 
    @SuppressWarnings( "unchecked" )
    public List< LegInfo > findAllLegs()
    {
	    // TODO Auto-generated method stub
    	List< LegInfo > legInfos = legInfoDAO.findAll();
	    return legInfos;
    }
	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param currentLegId
     * @return 
     * @author liuwu
     * @create_date 2014-8-14 下午3:09:09
     */ 
    public LegInfo findLegInfoById( int currentLegId )
    {
	    // TODO Auto-generated method stub
    	LegInfo legInfo = legInfoDAO.findById( currentLegId );
	    return legInfo;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示数
	 * @param sort
	 *            按什么字段排序
	 * @param order
	 *            升序 降序
	 * @return
	 * @author liuwu
	 * @create_date 2014-9-4 下午3:22:53
	 */
	public List< LegInfo > getLegInfoByPage( int page , int pageSize ,
	        String sort , String order )
	{
		List< LegInfo > legInfoList = legInfoDAO.findAllByPage( page ,
		        pageSize , sort , order );

		return legInfoList;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legInfo
	 * @author liuwu
	 * @create_date 2014-9-4 下午4:49:38
	 */
	public void saveLeg( LegInfo legInfo )
	{
		legInfoDAO.save( legInfo );

	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legId
	 * @author liuwu
	 * @create_date 2014-9-4 下午6:12:32
	 */
	public void deleteLegById( int legId )
	{
		// TODO Auto-generated method stub
		legInfoDAO.deleteById( legId );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author liuwu
	 * @create_date 2014-9-10 下午3:20:15
	 */
	public Map< String , Object > findAllLegsFilter( int legpage ,
	        int legpageSize )
	{
		Map< String , Object > legInfos = legInfoDAO.findLegInfoFileter(
		        legpage , legpageSize );
		return legInfos;
    }	
	
}
