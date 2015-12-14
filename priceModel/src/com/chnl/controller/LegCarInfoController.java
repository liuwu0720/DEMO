/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-9-5 下午4:02:50 
 * @version V1.0 
 */
package com.chnl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chnl.base.AjaxUtil;
import com.chnl.base.DataControl;
import com.chnl.entity.Page;
import com.chnl.pojo.CarInfo;
import com.chnl.pojo.LegCarInfo;
import com.chnl.pojo.LegInfo;
import com.chnl.service.LegCarInfoService;

/**
 * @Package com.chnl.controller
 * @Description: TODO(线路与商品车关联表的一些操作)
 * @author liuwu
 * @date 2014-9-5 下午4:02:50
 * @version V1.0
 */
@Controller
public class LegCarInfoController
{	
	@Autowired
	private LegCarInfoService legCarInfoService;
	
	private int legId;

	@RequestMapping( "/getCarByLeg.do" )
	@ResponseBody
	public Map< String , Object > getLegCarInfo( HttpServletRequest request ,
	        HttpServletResponse response )
	{

		int page = 1;
		int pageSize = 10;

		Map< String , Object > map = new HashMap< String , Object >();

		if ( request.getParameter( "page" ) != null
		        && request.getParameter( "rows" ) != null )
		{
			page = Integer.parseInt( request.getParameter( "page" ) );
			pageSize = Integer.parseInt( request.getParameter( "rows" ) );
		}
		if ( request.getParameter( "legId" ) == null )
		{
			return null;
		}
		else
		{
			legId = Integer.parseInt( request.getParameter( "legId" ) );

			DataControl dataControl = new DataControl();
			Page p = dataControl.getcurrPage( request );
			String sql = "select c.carname ,c.weight,c.length, l.* from car_info c left join leg_car_info l on  c.id=l.car_id where l.leg_id = "
			        + legId + " order by c.id";
			Map< String , Object > carInfos = legCarInfoService
			        .getCarInfoByJDBC( p , sql );
			
			return carInfos;
		}
		
		
	}
	
	/**
	 * 
	 * 编辑保存
	 * 
	 */
	@RequestMapping( "/editSaveCar.do" )
	public void editSaveInfo( HttpServletRequest request ,
	        HttpServletResponse response )
	{	
		System.out.println( "ID=" + request.getParameter( "ID" ) );
		int legcarId = Integer.parseInt( request.getParameter( "ID" ) );
		double incomePrice = Double.parseDouble( request
		        .getParameter( "INCOMEPRICE" ) );
		double vendorCost = Double.parseDouble( request
		        .getParameter( "VENDORCOST" ) );
		double ratio = Double.parseDouble( request.getParameter( "RATIO" ) );
		try
        {
	        legCarInfoService.editeInfo( legcarId , incomePrice , vendorCost ,
	                ratio );
			AjaxUtil.rendJson( response , true , "保存成功" );
        }
        catch ( Exception e )
        {
	        e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存" + e.getMessage() );
        }

	}
	
	/**
	 * 删除
	 */
	@RequestMapping( value = "/delLegCarInfo.do" , method = RequestMethod.POST )
	public void deleLegCarInfo( HttpServletRequest request ,
	        HttpServletResponse response )
	{	
		int curId = Integer.parseInt( request.getParameter( "id" ) );
		legCarInfoService.deleteById( curId );

		AjaxUtil.rendJson( response , true , "删除成功" );
	}
	
	/**
	 * 新增时下拉框选择
	 */
	@RequestMapping( value = "/selCarInfo.do" )
	@ResponseBody
	public List selectCarInfo( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		// 查出可供选择的商品车（过滤掉当前线路已经有的商品车）
		List< Map< String , Object > > carlist = legCarInfoService
		        .getSelectCar( legId );
		return carlist;
	}
	
	/**
	 * 新增保存
	 * 
	 */
	@RequestMapping( value = "/addSaveCar.do" )
	@ResponseBody
	public void getMoreCarInfo( HttpServletRequest request ,
	        HttpServletResponse response )
	{	
		double carId = Double.parseDouble( request.getParameter( "CARID" ) );
		double incomePrice = Double.parseDouble( request
		        .getParameter( "INCOMEPRICE" ) );
		double ratio = Double.parseDouble( request.getParameter( "RATIO" ) );
		double vendor = Double
		        .parseDouble( request.getParameter( "VENDORCOST" ) );
		CarInfo carInfo = new CarInfo();
		carInfo.setId( carId );
		LegInfo legInfo = new LegInfo();
		legInfo.setId( legId );
		LegCarInfo legCarInfo = new LegCarInfo();
		legCarInfo.setCarInfo( carInfo );
		legCarInfo.setIncomeprice( incomePrice );
		legCarInfo.setLegInfo( legInfo );
		legCarInfo.setRatio( ratio );
		legCarInfo.setVendorcost( vendor );
		legCarInfoService.addSave( legCarInfo );
		AjaxUtil.rendJson( response , true , "保存成功" );
	}
	



}
