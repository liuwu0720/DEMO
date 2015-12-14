/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-8 下午4:44:58 
 * @version V1.0 
 */
package com.chnl.service.iml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnl.dao.TruckInfoDAO;
import com.chnl.pojo.TruckInfo;
import com.chnl.service.TruckInfoService;

/** 
 * @Package com.chnl.service.iml 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-8 下午4:44:58 
 * @version V1.0 
 */
@Service
public class TruckInfoServiceImp implements TruckInfoService
{
	@Autowired
	private TruckInfoDAO truckInfoDAO;
	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @return 
     * @author liuwu
     * @create_date 2014-8-8 下午4:48:04
     */ 
    public List< TruckInfo > findAllTruckInfos()
    {
	    // TODO Auto-generated method stub
    	List< TruckInfo > truckInfos = truckInfoDAO.findAll();
    	
	    return truckInfos;
    }
	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param reqTruckId
     * @return 
     * @author liuwu
     * @create_date 2014-8-11 上午8:54:49
     */ 
    public TruckInfo findById( int reqTruckId )
    {
	    // TODO Auto-generated method stub
    	TruckInfo  truckInfo = new TruckInfo();
    	truckInfo = truckInfoDAO.findById( reqTruckId );
	    return truckInfo;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @param order
	 * @return
	 * @author liuwu
	 * @create_date 2014-9-5 下午1:59:11
	 */
	public List< TruckInfo > findAllTruckByPage( int page , int pageSize ,
	        String sort , String order )
	{
		List< TruckInfo > truckInfos = truckInfoDAO.getAllTruckByPage( page ,
		        pageSize , sort , order );
		
		return truckInfos;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param truckInfo
	 * @author liuwu
	 * @create_date 2014-9-5 下午2:14:08
	 */
	public void saveOrUpdate( TruckInfo truckInfo )
	{
		truckInfoDAO.save( truckInfo );

	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param truckId
	 * @author liuwu
	 * @create_date 2014-9-5 下午2:14:55
	 */
	public void deleteById( int truckId )
	{
		truckInfoDAO.deleteById( truckId );

	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author liuwu
	 * @create_date 2014-9-10 下午3:46:36
	 */
	public List< TruckInfo > findAllTruckInfosFilter()
	{
		List< TruckInfo > truckInfos = truckInfoDAO.findAllFilter();
		return truckInfos;
    }	
	
}
