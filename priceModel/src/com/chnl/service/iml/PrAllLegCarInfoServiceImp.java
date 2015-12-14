/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-21 上午11:43:18 
 * @version V1.0 
 */
package com.chnl.service.iml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnl.dao.PrAlllegcarinfoDAO;
import com.chnl.dao.PrLegfileimportDAO;
import com.chnl.dao.PrSelfinputlegDAO;
import com.chnl.pojo.CarInfo;
import com.chnl.pojo.PrAlllegcarinfo;
import com.chnl.pojo.PrLegfileimport;
import com.chnl.pojo.PrSelfinputleg;
import com.chnl.service.PrAllLegCarInfoService;

/** 
 * @Package com.chnl.service.iml 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-21 上午11:43:18 
 * @version V1.0 
 */
@Service
public class PrAllLegCarInfoServiceImp implements PrAllLegCarInfoService
{
	@Autowired
	private PrAlllegcarinfoDAO prAlllegcarinfoDAO;
	
	@Autowired
	private PrLegfileimportDAO prLegfileimportDAO;
	
	@Autowired
	private PrSelfinputlegDAO prSelfinputlegDAO;

	/**
	 * @Description: TODO(用户导入：保存修改后的商品车信息，与路线关联)
	 * @param carInfos
	 * @param legId
	 * @author liuwu
	 * @create_date 2014-11-21 上午11:50:23
	 */
	public void saveHadUpdateCarInfo( List< CarInfo > carInfos , int legId ,
	        int typeId )
	{
		prAlllegcarinfoDAO.cleanBeforeData( legId , typeId );
		PrLegfileimport prLegfileimport = prLegfileimportDAO.findById( legId );
		for ( CarInfo carInfo : carInfos )
		{
			PrAlllegcarinfo prAlllegcarinfo = new PrAlllegcarinfo();
			prAlllegcarinfo.setCarname( carInfo.getCarname() );
			prAlllegcarinfo.setEndcity( prLegfileimport.getEndcity() );
			prAlllegcarinfo.setIncomeprice( carInfo.getIncomePrice() );
			prAlllegcarinfo.setLength( carInfo.getLength() );
			prAlllegcarinfo.setOutprocost( carInfo.getCurrentProCost() );
			prAlllegcarinfo.setRatio( carInfo.getRatio() );
			prAlllegcarinfo.setStartcity( prLegfileimport.getStartcity() );
			prAlllegcarinfo.setWeight( carInfo.getWeight() );
			prAlllegcarinfo.setCarid( carInfo.getId() );
			prAlllegcarinfo.setLegId( legId );
			prAlllegcarinfo.setTypeId( typeId );
			prAlllegcarinfoDAO.save( prAlllegcarinfo );

		}
		
	}
	
	/**
	 * @Description: TODO(用户输入：保存修改后的商品车信息，与路线关联)
	 * @param newCarInfos
	 * @param legId
	 * @param typeId
	 * @author liuwu
	 * @create_date 2014-11-25 下午1:34:40
	 */
	public void saveHadUpdateCarInfo2( List< CarInfo > newCarInfos , int legId ,
	        int typeId )
	{
		prAlllegcarinfoDAO.cleanBeforeData( legId , typeId );
		PrSelfinputleg prSelfinputleg = prSelfinputlegDAO.findById( legId );
		for ( CarInfo carInfo : newCarInfos )
		{
			PrAlllegcarinfo prAlllegcarinfo = new PrAlllegcarinfo();
			prAlllegcarinfo.setCarname( carInfo.getCarname() );
			prAlllegcarinfo.setEndcity( prSelfinputleg.getEndcity() );
			prAlllegcarinfo.setIncomeprice( carInfo.getIncomePrice() );
			prAlllegcarinfo.setLength( carInfo.getLength() );
			prAlllegcarinfo.setOutprocost( carInfo.getCurrentProCost() );
			prAlllegcarinfo.setRatio( carInfo.getRatio() );
			prAlllegcarinfo.setStartcity( prSelfinputleg.getStartcity() );
			prAlllegcarinfo.setWeight( carInfo.getWeight() );
			prAlllegcarinfo.setCarid( carInfo.getId() );
			prAlllegcarinfo.setLegId( legId );
			prAlllegcarinfo.setTypeId( typeId );
			prAlllegcarinfoDAO.save( prAlllegcarinfo );
			
		}
		
	}
	
	/**
	 * @Description: TODO(根据线路取出所有商品车信息)
	 * @param id
	 * @param type
	 * @param carInfoList
	 * @author liuwu
	 * @create_date 2014-11-26 上午9:16:56
	 */
	public void findAllCarInfoByLegId( Integer id , int type ,
	        List< CarInfo > carInfoList )
	{

		List< PrAlllegcarinfo > prAlllegcarinfos = prAlllegcarinfoDAO
		        .findAllCarInfoByLegId( id , type );
		for ( PrAlllegcarinfo prAlllegcarinfo : prAlllegcarinfos )
		{
			CarInfo carInfo = new CarInfo();
			carInfo.setId( prAlllegcarinfo.getCarid() );
			carInfo.setCarname( prAlllegcarinfo.getCarname() );
			carInfo.setCurrentProCost( prAlllegcarinfo.getOutprocost() );
			carInfo.setIncomePrice( prAlllegcarinfo.getIncomeprice() );
			carInfo.setLength( prAlllegcarinfo.getLength() );
			carInfo.setWeight( prAlllegcarinfo.getWeight() );
			carInfo.setRatio( prAlllegcarinfo.getRatio() );
			carInfoList.add( carInfo );
		}
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param car1Id
	 * @param id
	 * @param type
	 * @return
	 * @author liuwu
	 * @create_date 2014-11-26 上午10:53:41
	 */
	public CarInfo getCarInfo( double car1Id , Integer legid , int type )
	{
		CarInfo carInfo = prAlllegcarinfoDAO.getCarInfo( car1Id , legid , type );
		return carInfo;
	}
	
}
