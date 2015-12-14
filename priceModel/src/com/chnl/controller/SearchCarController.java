/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-11-11 上午11:09:06
 * @version V1.0
 */
package com.chnl.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chnl.entity.CarInfoForm;
import com.chnl.pojo.CarInfo;
import com.chnl.service.CarInfoService;
import com.chnl.service.PrAllLegCarInfoService;

/**
 * @Package com.chnl.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-11-11 上午11:09:06
 * @version V1.0
 */
@Controller
public class SearchCarController
{
	@Autowired
	private CarInfoService carInfoService;
	
	@Autowired
	private PrAllLegCarInfoService prAllLegCarInfoService;

	private CarInfoForm carInfos = new CarInfoForm();
	
	/**
	 * 
	 * @Description: TODO(用户导入文件：查询车辆详细信息)
	 * @param legId
	 * @return ModelAndView 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-20 下午1:31:22
	 */
	@SuppressWarnings( "unused" )
	@RequestMapping( value = "/searchcar.do" )
	public ModelAndView searchCar( String legId , String customerId )
	{
		long time1 = System.currentTimeMillis();
		ModelMap carMap = new ModelMap();
		List< CarInfo > carInfoList = carInfoService.searchCarById( legId ,
		        customerId );

		carInfos.setCarInfos( carInfoList );
		carMap.addAttribute( "leg" , legId );
		carMap.addAttribute( "typeId" , 0 );// 0:文件导入形式
		carMap.addAttribute( "carInfos" , carInfos );
		/**
		 * 查询是否存在历史修改的车辆
		 */
		List< CarInfo > historyCarInfos = new ArrayList< CarInfo >();
		prAllLegCarInfoService.findAllCarInfoByLegId(
		        Integer.parseInt( legId ) , 0 , historyCarInfos );
		
		if ( historyCarInfos.size() > 0 )
		{
			String message = "当前线路存在历史修改记录";
			carMap.put( "message" , message );
			carMap.put( "historyCarInfos" , historyCarInfos );
		}

		if ( carInfoList == null || carInfoList.size() == 0 )
		{
			String message = "当前线路没有查询到应收单价不为‘0’的商品车！请确认是否加入空载?";
			carMap.put( "error1" , message );
		}
		else
		{
			double totalratio = 0.0;
			
			for ( CarInfo carInfo : carInfoList )
			{
				totalratio += carInfo.getRatio();
			}
			totalratio = Double.parseDouble( String
			        .format( "%.3f" , totalratio ) );

			carMap.put( "totalRatio" , totalratio );
			if ( totalratio > 1 || totalratio < 1 )
			{
				String message = "请手动调整发车比例，使比例总和完全等于1！";
				carMap.put( "message" , message );
			}
		}

		return new ModelAndView( "carInfo" , carMap );
		
	}

	/**
	 * 
	 * @Description: TODO(用户输入：查询车辆详细信息)
	 * @param legId
	 * @return ModelAndView 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-20 下午1:31:22
	 */
	@SuppressWarnings( "unused" )
	@RequestMapping( value = "/searchcar2.do" )
	public ModelAndView searchCar2( String legId , String customerId)
	{
		long time1 = System.currentTimeMillis();
		ModelMap carMap = new ModelMap();
		List< CarInfo > carInfoList = new ArrayList< CarInfo >();

		carInfoList = carInfoService.searchCarById2( legId , customerId );
		carInfos.setCarInfos( carInfoList );
		carMap.addAttribute( "leg" , legId );
		carMap.addAttribute( "typeId" , 1 );// 1:用户自己输入
		carMap.addAttribute( "carInfos" , carInfos );
		/**
		 * 查询是否存在历史修改的车辆
		 */
		List< CarInfo > historyCarInfos = new ArrayList< CarInfo >();
		prAllLegCarInfoService.findAllCarInfoByLegId(
		        Integer.parseInt( legId ) , 1 , historyCarInfos );
		
		if ( historyCarInfos.size() > 0 )
		{
			String message = "当前线路存在历史修改记录";
			carMap.put( "message" , message );
			carMap.put( "historyCarInfos" , historyCarInfos );
		}
		if ( carInfoList == null || carInfoList.size() == 0 )
		{
			String message = "当前线路没有查询到应收单价不为‘0’的商品车！请确认是否加入空载?";
			carMap.put( "error1" , message );
		}
		else
		{
			double totalratio = 0.0;
			
			for ( CarInfo carInfo : carInfoList )
			{
				totalratio += carInfo.getRatio();
			}
			totalratio = Double.parseDouble( String
			        .format( "%.3f" , totalratio ) );
			
			carMap.put( "totalRatio" , totalratio );
			if ( totalratio > 1 || totalratio < 1 )
			{
				String message = "请手动调整发车比例，使比例总和完全等于1！";
				carMap.put( "message" , message );
			}
		}
		
		return new ModelAndView( "carInfo2" , carMap );
		
	}
	
	/**
	 * 
	 * @Description: TODO(保存修改后的商品车信息，与路线关联) 导入文件的方式
	 * @param
	 * @return
	 * @author liuwu
	 * @create_date 2014-11-20 下午1:31:22
	 */
	@RequestMapping( value = "/updateLegCar.do" , method = RequestMethod.POST )
	public ModelAndView updateCarInfo( HttpServletRequest request ,
	        CarInfoForm carInfoForm )
	{
		String error = "";
		String message = "";
		String message1 = "";
		int legId = Integer.parseInt( request.getParameter( "legid" ) );
		int typeId = Integer.parseInt( request.getParameter( "typeId" ) );
		ModelMap map = new ModelMap();
		List< CarInfo > carInfos = carInfoForm.getCarInfos();
		Double totalCartio = 0.0;
		List< CarInfo > newCarInfos = new ArrayList< CarInfo >();
		CarInfoForm newCarInfoForm = new CarInfoForm();
		for ( CarInfo carInfo : carInfos )
		{
			if ( carInfo.getId() != null )
			{
				totalCartio += carInfo.getRatio();
				newCarInfos.add( carInfo );
			}

		}
		totalCartio = Double
		        .parseDouble( String.format( "%.3f" , totalCartio ) );
		newCarInfoForm.setCarInfos( newCarInfos );
		if ( totalCartio != 1.0 )
		{
			message = "发车比例总和必须等于1，请手动调整！";
			map.put( "leg" , legId );
			map.put( "typeId" , typeId );
			map.put( "message" , message );
			map.put( "totalRatio" , totalCartio );
			map.put( "carInfos" , newCarInfoForm );
		}
		else
		{
			prAllLegCarInfoService.saveHadUpdateCarInfo( newCarInfos , legId ,
			        typeId );
			error = "修改成功！";
			message1 = "这条线路你已选择了【" + newCarInfos.size()
			        + "】 辆商品车，如果重新选择商品车将覆盖原有数据";
			map.put( "message1" , message1 );
			map.put( "leg" , legId );
		}
		map.put( "error" , error );
		return new ModelAndView( "carInfo" , map );
		
	}
	
	/**
	 * 
	 * @Description: TODO(保存修改后的商品车信息，与路线关联) 自己输入
	 * @param
	 * @return
	 * @author liuwu
	 * @create_date 2014-11-20 下午1:31:22
	 */
	@RequestMapping( value = "/updateLegCar2.do" , method = RequestMethod.POST )
	public ModelAndView updateCarInfo2( HttpServletRequest request ,
	        CarInfoForm carInfoForm )
	{
		String error = "";
		String message = "";
		String message1 = "";
		int legId = Integer.parseInt( request.getParameter( "legid" ) );
		int typeId = Integer.parseInt( request.getParameter( "typeId" ) );
		ModelMap map = new ModelMap();
		List< CarInfo > carInfos = carInfoForm.getCarInfos();
		Double totalCartio = 0.0;
		List< CarInfo > newCarInfos = new ArrayList< CarInfo >();
		CarInfoForm newCarInfoForm = new CarInfoForm();
		for ( CarInfo carInfo : carInfos )
		{
			if ( carInfo.getId() != null )
			{
				totalCartio += carInfo.getRatio();
				newCarInfos.add( carInfo );
			}
			
		}
		totalCartio = Double
		        .parseDouble( String.format( "%.3f" , totalCartio ) );
		newCarInfoForm.setCarInfos( newCarInfos );
		if ( totalCartio != 1.0 )
		{
			message = "发车比例总和必须等于1，请手动调整！";
			map.put( "leg" , legId );
			map.put( "typeId" , typeId );
			map.put( "message" , message );
			map.put( "totalRatio" , totalCartio );
			map.put( "carInfos" , newCarInfoForm );
		}
		else
		{
			prAllLegCarInfoService.saveHadUpdateCarInfo2( newCarInfos , legId ,
			        typeId );
			error = "修改成功！";
			message1 = "这条线路你已经选择了" + newCarInfos.size()
			        + " 辆商品车，如果重新选择商品车将覆盖原有数据";
			map.put( "message1" , message1 );
			map.put( "leg" , legId );
		}
		map.put( "error" , error );
		return new ModelAndView( "carInfo2" , map );
		
	}
	
	public CarInfoForm getCarInfoForm()
	{
		return carInfos;
	}
	
	public void setCarInfoForm( CarInfoForm carInfos )
	{
		this.carInfos = carInfos;
	}

}
