/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-8-8 下午1:14:32
 * @version V1.0
 */
package com.chnl.service.iml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnl.dao.CarInfoDAO;
import com.chnl.dao.LegCarInfoDAO;
import com.chnl.dao.PrLegfileimportDAO;
import com.chnl.dao.PrSelfinputlegDAO;
import com.chnl.dao.SmcustomerDAO;
import com.chnl.dao.SmstylerateDAO;
import com.chnl.pojo.CarInfo;
import com.chnl.pojo.LegCarInfo;
import com.chnl.pojo.PrLegfileimport;
import com.chnl.pojo.PrSelfinputleg;
import com.chnl.pojo.Smcustomer;
import com.chnl.pojo.Smstylerate;
import com.chnl.service.CarInfoService;

/**
 * @Package com.chnl.service.iml
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-8-8 下午1:14:32
 * @version V1.0
 */
@Service
public class CarInfoServiceImp implements CarInfoService
{
	@Autowired
	public CarInfoDAO carInfoDAO;
	@Autowired
	public LegCarInfoDAO legCarInfoDAO;

	@Autowired
	private PrSelfinputlegDAO prSelfinputlegDAO;
	
	@Autowired
	public SmstylerateDAO smstylerateDAO;

	@Autowired
	public PrLegfileimportDAO prLegfileimportDAO;
	
	@Autowired
	SmcustomerDAO smcustomerDAO;

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param car1Id
	 * @param currentLegId
	 * @return
	 * @author liuwu
	 * @create_date 2014-8-19 下午4:18:53
	 */
	public CarInfo findCarInfoById( int car1Id , int currentLegId )
	{
		CarInfo carInfo = carInfoDAO.findById( car1Id );
		LegCarInfo legCarInfo = legCarInfoDAO.getIncomeRatio( car1Id ,
		        currentLegId );
		carInfo.setIncomePrice( legCarInfo.getIncomeprice() );
		carInfo.setRatio( legCarInfo.getRatio() );
		return carInfo;
	}

	/**
	 * @Description: TODO(分页查询商品车基本信息)
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @param order
	 * @return
	 * @author liuwu
	 * @create_date 2014-9-5 上午10:58:45
	 */
	public List< CarInfo > findCarInfoByPage( int page , int pageSize ,
	        String sort , String order )
	{
		List< CarInfo > carInfos = carInfoDAO.getCarInfoByPage( page ,
		        pageSize , sort , order );
		return carInfos;
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author liuwu
	 * @create_date 2014-9-5 上午11:04:56
	 */
	@SuppressWarnings( "unchecked" )
	public List< CarInfo > findAllCarInfo()
	{
		List< CarInfo > carInfos = carInfoDAO.findAll();
		return carInfos;
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param carInfo
	 * @author liuwu
	 * @create_date 2014-9-5 上午11:39:30
	 */
	public void saveOrUpdate( CarInfo carInfo )
	{
		carInfoDAO.save( carInfo );
		
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param carId
	 * @author liuwu
	 * @create_date 2014-9-5 下午1:35:50
	 */
	public void deleteById( int carId )
	{
		carInfoDAO.delteById( carId );
		
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param carId
	 * @return
	 * @author liuwu
	 * @create_date 2014-11-11 上午11:23:02
	 */
	public List< CarInfo > searchCarById( String legId , String customerId )
	{
		PrLegfileimport prLegfileimport = prLegfileimportDAO.findById( Integer
		        .parseInt( legId ) );
		double incomeDistance = prLegfileimportDAO.getIncomeDistance2(
		        prLegfileimport , Integer.parseInt( customerId ) );
		prLegfileimport.setIncomeDistance( incomeDistance );
		/*Smcustomer smcustomer = smcustomerDAO.findById( Integer
		        .parseInt( customerId ) );
		
		prLegfileimport.setSelectCustomer( smcustomer.getVccustomername() );*/
		prLegfileimportDAO.attachDirty( prLegfileimport );
		List< Double > carIdList = smstylerateDAO.findByPointName(
		        prLegfileimport.getStartcity() , prLegfileimport.getEndcity() ,
		        customerId );
		List< CarInfo > carInfos = null;
		if ( carIdList.size() == 0 )
		{
			return null;
		}
		else
		{
			carInfos = new ArrayList< CarInfo >();
			for ( Double carId : carIdList )
			{
				Smstylerate smstylerate = smstylerateDAO.findByCarId( carId );

				if ( smstylerate != null )
				{
					/*Double carRatio = smstylerateDAO.getRation(
					        prLegfileimport , carId );*/
					
					CarInfo carInfo = new CarInfo();
					carInfo.setCarname( smstylerate.getVcstylename() );
					carInfo.setLength( smstylerate.getDclength() );
					carInfo.setWeight( smstylerate.getDcweight() );

					/*smstylerateDAO.getRatioAndIncomePrice( prLegfileimport ,
					        carInfo , carId );
					carInfo.setId( smstylerate.getIlineid() );
					if ( ! carInfo.getIncomePrice().equals( 0.0 ) )
					{
						carInfos.add( carInfo );
					}*/
					carInfo.setId( smstylerate.getIlineid() );
					carInfos.add( carInfo );
				}
				
			}
			System.out.println( carInfos.size() );
			return carInfos;
		}

	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param city
	 * @return
	 * @author liuwu
	 * @create_date 2014-11-22 上午11:18:44
	 */
	public List< String > findAllsmStartCitys( String city )
	{

		List< String > citys = carInfoDAO.findAllsmStartCitys( city );
		return citys;
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param startpoint
	 * @return
	 * @author liuwu
	 * @create_date 2014-11-22 下午1:55:44
	 */
	public List< String > findAllsmStartpoints( String startpoint )
	{
		List< String > startpoints = carInfoDAO
		        .findAllsmStartpoints( startpoint );
		return startpoints;
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param endcity
	 * @return
	 * @author liuwu
	 * @create_date 2014-11-22 下午2:22:47
	 */
	public List< String > findAllendcity( String endcity )
	{
		List< String > endcitys = carInfoDAO.findAllendcity( endcity );
		return endcitys;
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legId
	 * @return
	 * @author liuwu
	 * @create_date 2014-11-25 下午1:21:25
	 */
	public List< CarInfo > searchCarById2( String legId , String customerId )
	{
		PrSelfinputleg prSelfinputleg = prSelfinputlegDAO.findById( Integer
		        .parseInt( legId ) );
		double incomeDistance = prSelfinputlegDAO.getIncomeDistance2(
		        prSelfinputleg , Integer.parseInt( customerId ) );
		prSelfinputleg.setIncomeDistance( incomeDistance );
		Smcustomer smcustomer = smcustomerDAO.findById( Integer
		        .parseInt( customerId ) );
		
		prSelfinputleg.setSelectCustomer( smcustomer.getVccustomername() );
		prSelfinputlegDAO.attachDirty( prSelfinputleg );
		List< Double > carIdList = smstylerateDAO.findByPointName(
		        prSelfinputleg.getStartcity() , prSelfinputleg.getEndcity() ,
		        customerId );
		List< CarInfo > carInfos = null;
		if ( carIdList.size() == 0 )
		{
			return null;
		}
		else
		{
			carInfos = new ArrayList< CarInfo >();
			for ( Double carId : carIdList )
			{
				Smstylerate smstylerate = smstylerateDAO.findByCarId( carId );

				if ( smstylerate != null )
				{
					/*Double carRatio = smstylerateDAO.getRation(
					        prLegfileimport , carId );*/
					
					CarInfo carInfo = new CarInfo();
					carInfo.setCarname( smstylerate.getVcstylename() );
					carInfo.setLength( smstylerate.getDclength() );
					carInfo.setWeight( smstylerate.getDcweight() );

					/*smstylerateDAO.getRatioAndIncomePrice2( prSelfinputleg ,
					        carInfo , carId );
					carInfo.setId( smstylerate.getIlineid() );
					if ( ! carInfo.getIncomePrice().equals( 0.0 ) )
					{
						carInfos.add( carInfo );
					}*/
					carInfo.setId( smstylerate.getIlineid() );
					carInfos.add( carInfo );
				}
				
			}
			return carInfos;
		}
	}
}
