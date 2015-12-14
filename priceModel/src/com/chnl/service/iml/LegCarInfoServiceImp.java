/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-8-11 下午6:37:41
 * @version V1.0
 */
package com.chnl.service.iml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnl.dao.CarInfoDAO;
import com.chnl.dao.LegCarInfoDAO;
import com.chnl.entity.Page;
import com.chnl.pojo.CarInfo;
import com.chnl.pojo.LegCarInfo;
import com.chnl.service.LegCarInfoService;

/**
 * @Package com.chnl.service.iml
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-8-11 下午6:37:41
 * @version V1.0
 */
@Service
public class LegCarInfoServiceImp implements LegCarInfoService
{
	@Autowired
	LegCarInfoDAO legCarInfoDAO;

	@Autowired
	CarInfoDAO carInfoDAO;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2014-8-11 下午6:42:13
	 */
	public List< LegCarInfo > findByLegId( int legId )
	{
		// TODO Auto-generated method stub
		List< LegCarInfo > legCarInfos = legCarInfoDAO.findByLegIdDao( legId );
		return legCarInfos;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2014-8-13 上午11:22:34
	 */
	public List< CarInfo > findAllCarInfoByLegId( int legId )
	{
		List< CarInfo > carInfos = new ArrayList< CarInfo >();
		List< LegCarInfo > legCarInfos = legCarInfoDAO
		        .getLegCarListByLegId( legId );
		for ( LegCarInfo legCarInfo : legCarInfos )
		{
			
			CarInfo carInfo = carInfoDAO.findByWeight( legCarInfo.getCarInfo()
			        .getId() );
			carInfo.setIncomePrice( legCarInfo.getIncomeprice() );
			carInfo.setRatio( legCarInfo.getRatio() );
			carInfos.add( carInfo );
		}
		return carInfos;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @param page
	 * @param pageSize
	 * @return
	 * @author liuwu
	 * @create_date 2014-8-13 下午2:20:48
	 */
	public List< CarInfo > findAllCarInfoByLegIdPage( int legId , int page ,
	        int pageSize )
	{
		List< CarInfo > carInfos = new ArrayList< CarInfo >();
		List< LegCarInfo > legCarInfos = legCarInfoDAO.getLegCarListByLegId(
		        legId , page , pageSize );
		for ( LegCarInfo legCarInfo : legCarInfos )
		{
			
			CarInfo carInfo = carInfoDAO.findById( legCarInfo.getCarInfo()
			        .getId() );
			carInfo.setIncomePrice( legCarInfo.getIncomeprice() );
			carInfo.setRatio( legCarInfo.getRatio() );
			carInfo.setCurrentProCost( legCarInfo.getVendorcost() );
			carInfos.add( carInfo );
		}
		List< CarInfo > newcCarInfos = new ArrayList< CarInfo >();
		for ( CarInfo carInfo : carInfos )
		{
			CarInfo newcCarInfo = new CarInfo();
			newcCarInfo.setCarname( carInfo.getCarname() );
			newcCarInfo.setCurrentProCost( carInfo.getCurrentProCost() );
			newcCarInfo.setId( carInfo.getId() );
			newcCarInfo.setIncomePrice( carInfo.getIncomePrice() );
			newcCarInfo.setLength( carInfo.getLength() );
			newcCarInfo.setManufacturer( carInfo.getManufacturer() );
			newcCarInfo.setRatio( carInfo.getRatio() );
			newcCarInfo.setWeight( carInfo.getWeight() );
			newcCarInfo.setCurrentProCost( carInfo.getCurrentProCost() );
			newcCarInfos.add( newcCarInfo );
		}

		return newcCarInfos;
	}

	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param currentLegId
     * @return 
     * @author liuwu
     * @create_date 2014-8-22 下午6:56:27
     */ 
    public double getCurrentProCost( int currentLegId )
    {
	   List< LegCarInfo > legCarInfos = legCarInfoDAO.getLegCarListByLegId( currentLegId );
	   double currentProCost = 0 ;
	   for(LegCarInfo legCarInfo:legCarInfos){
		   currentProCost += legCarInfo.getRatio()*legCarInfo.getVendorcost();
	   }
	   
	    return currentProCost;
    }

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legId
	 * @return
	 * @author liuwu
	 * @create_date 2014-9-9 上午9:08:51
	 */
	public List< LegCarInfo > getOtherCarInfoByLegId( int legId , int page ,
	        int pageSize )
	{
		List< LegCarInfo > legCarInfos = legCarInfoDAO.findByLegIdByPage(
		        legId , page , pageSize );
		List< LegCarInfo > newCarInfos = new ArrayList< LegCarInfo >();
		for ( LegCarInfo legCarInfo : legCarInfos )
		{
			LegCarInfo newCarInfo = new LegCarInfo();
			newCarInfo.setId( legCarInfo.getId() );
			newCarInfo.setIncomeprice( legCarInfo.getIncomeprice() );
			newCarInfo.setRatio( legCarInfo.getRatio() );
			newCarInfo.setVendorcost( legCarInfo.getVendorcost() );

			newCarInfos.add( newCarInfo );
		}
		return newCarInfos;
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param p
	 * @param sql
	 * @return
	 * @author liuwu
	 * @create_date 2014-9-9 下午2:01:14
	 */
	public Map< String , Object > getCarInfoByJDBC( Page p , String sql )
	{
		Map< String , Object > result = carInfoDAO.getSpringSQL( sql , p );
		return result;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legcarId
	 * @param incomePrice
	 * @param vendorCost
	 * @param ratio
	 * @author liuwu
	 * @create_date 2014-9-9 下午2:01:14
	 */
	public void editeInfo( int legcarId , double incomePrice ,
	        double vendorCost , double ratio )
	{
		legCarInfoDAO.updateInfo( legcarId , incomePrice , vendorCost , ratio );

	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param curId
	 * @author liuwu
	 * @create_date 2014-9-9 下午5:06:55
	 */
	public void deleteById( int curId )
	{
		legCarInfoDAO.deleteById( curId );
		
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legId
	 * @return
	 * @author liuwu
	 * @create_date 2014-9-9 下午6:56:37
	 */
	public List< Map< String , Object >> getSelectCar( int legId )
	{
		List< Map< String , Object > > list = legCarInfoDAO
		        .getSelectCarBylegId( legId );
		return list;
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legCarInfo
	 * @author liuwu
	 * @create_date 2014-9-10 上午10:42:11
	 */
	public void addSave( LegCarInfo legCarInfo )
	{
		legCarInfoDAO.save( legCarInfo );
		
	}

}
