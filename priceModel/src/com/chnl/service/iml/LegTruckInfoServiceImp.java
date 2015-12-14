/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-11 下午3:38:27 
 * @version V1.0 
 */
package com.chnl.service.iml;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnl.dao.LegInfoDAO;
import com.chnl.dao.LegTruckInfoDAO;
import com.chnl.entity.Page;
import com.chnl.pojo.LegInfo;
import com.chnl.pojo.LegTruckInfo;
import com.chnl.service.LegTruckInfoService;

/** 
 * @Package com.chnl.service.iml 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-11 下午3:38:27 
 * @version V1.0 
 */
@Service
public class LegTruckInfoServiceImp implements LegTruckInfoService
{
	@Autowired
	LegTruckInfoDAO legTruckInfoDAO;
	@Autowired
	LegInfoDAO legInfoDAO;
	
	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param legId
     * @param truckId
     * @return 
     * @author liuwu
     * @create_date 2014-8-11 下午3:56:59
     */ 
    public LegTruckInfo findObjByProp( int legId , int truckId )
    {
	    // TODO Auto-generated method stub
    	LegTruckInfo legTruckInfo = null;
    	try
        {
    	 legTruckInfo = legTruckInfoDAO.findByLegIdAndTruckId(legId,truckId);
        }
        catch ( Exception e )
        {
	        // TODO: handle exception
        	System.out.println("没有查到");
        }
    
	    return legTruckInfo;
    }
	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param parseInt
     * @return 
     * @author liuwu
     * @create_date 2014-8-11 下午6:27:13
     */ 
    public LegTruckInfo findById( int id )
    {
	    // TODO Auto-generated method stub
    	LegTruckInfo legTruckInfo = legTruckInfoDAO.findById( id );
	    return legTruckInfo;
    }
	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param currentLegId
     * @param reqTruckId
     * @return 
     * @author liuwu
     * @create_date 2014-8-21 下午6:21:12
     */ 
    public LegTruckInfo getLegInfo( int currentLegId , int reqTruckId )
    {
    	LegTruckInfo legTruckInfo = legTruckInfoDAO.findByLegIdAndTruckId(currentLegId,reqTruckId);
    	LegInfo legInfo = legInfoDAO.findById( currentLegId );
		if ( legTruckInfo != null )
		{
			legTruckInfo.setActualCostByTruck( ( legInfo.getActualDistance()
			        * legTruckInfo.getFullcost() + legTruckInfo.getEmptcost()
			        * legInfo.getEmptyDistance() )
			        / legInfo.getActualDistance() );
			
			return legTruckInfo;
		}
		else
		{
			return null;
		}
    	
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param p
	 * @return
	 * @author liuwu
	 * @create_date 2014-9-10 下午1:49:22
	 */
	public Map< String , Object > getLegInfoJDBC( String sql , Page p )
	{
		Map< String , Object > maps = legTruckInfoDAO.getSpringSQL( sql , p );
		return maps;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legTruckId
	 * @param fullCost
	 * @param emptyCost
	 * @author liuwu
	 * @create_date 2014-9-10 下午2:26:45
	 */
	public void editSaveTruck( int legTruckId , double fullCost ,
	        double emptyCost )
	{
		legTruckInfoDAO.updateTruckInfo( legTruckId , fullCost , emptyCost );

	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legTruckId
	 * @author liuwu
	 * @create_date 2014-9-10 下午3:02:17
	 */
	public void deleteLegTruckById( int legTruckId )
	{
		legTruckInfoDAO.deleteById( legTruckId );

	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param truckId
	 * @return
	 * @author liuwu
	 * @create_date 2014-9-12 下午4:00:54
	 */
	public List< Map< String , Object >> getSelectLeg( int truckId )
	{
		List< Map< String , Object >> selecList = legTruckInfoDAO
		        .getSelectLegByTruckId( truckId );
		return selecList;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legTruckInfo
	 * @author liuwu
	 * @create_date 2014-9-12 下午4:31:44
	 */
	public void addSaveLegTruck( LegTruckInfo legTruckInfo )
	{
		legTruckInfoDAO.save( legTruckInfo );

    }	
	
}
