/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-9-5 上午10:47:13 
 * @version V1.0 
 */
package com.chnl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chnl.base.AjaxUtil;
import com.chnl.pojo.CarInfo;
import com.chnl.service.CarInfoService;

/**
 * @Package com.chnl.controller
 * @Description: TODO(后台管理：商品车基本信息的增删改查)
 * @author liuwu
 * @date 2014-9-5 上午10:47:13
 * @version V1.0
 */
@Controller
public class CarInfoController
{	
	@Autowired
	private CarInfoService carInfoService;
	
	@RequestMapping( "/getCarInfo.do" )
	@ResponseBody
	public Map< String , Object > getCarInfo( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		int page = 1;
		int pageSize = 10;
		Map< String , Object > map = new HashMap< String , Object >();
		List< CarInfo > carInfoList = new ArrayList< CarInfo >();
		if ( request.getParameter( "page" ) != null
		        && request.getParameter( "rows" ) != null )
		{
			page = Integer.parseInt( request.getParameter( "page" ) );
			pageSize = Integer.parseInt( request.getParameter( "rows" ) );
		}
		

		String sort = request.getParameter( "sort" );// 按什么排序
		String order = request.getParameter( "order" );// 升序还是降序
		if ( sort == null )
		{
			sort = "id";
		}
		if ( order == null )
		{
			order = "asc";
		}
		carInfoList = carInfoService.findCarInfoByPage( page , pageSize , sort ,
		        order );
		List< CarInfo > totalPages = carInfoService.findAllCarInfo();
		System.out.println( carInfoList.size() );
		map.put( "rows" , carInfoList );
		map.put( "total" , totalPages.size() );
		
		System.out
		        .println( page + "--" + pageSize + "--" + sort + "--" + order );
		return map;
	
	}
	
	// 保存操作
	@RequestMapping( value = "/saveCar.do" , method = RequestMethod.POST )
	public void saveCurCar( HttpServletRequest request , CarInfo carInfo ,
	        HttpServletResponse response )
	{	
		String iid = request.getParameter( "id" );
			if ( StringUtils.isNotBlank( iid ) )
			{
			carInfo.setId( Double.parseDouble( iid ) );
		}
		carInfoService.saveOrUpdate( carInfo );
		AjaxUtil.rendJson( response , true , "保存成功" );
	}
	
	// 删除操作
	@RequestMapping( value = "/deleteCar.do" , method = RequestMethod.POST )
	public void deleteLegInfo( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		
		System.out.println( "delete....." + request.getParameter( "id" ) );
		int carId = Integer.parseInt( request.getParameter( "id" ) );
		carInfoService.deleteById( carId );
		AjaxUtil.rendJson( response , true , "删除成功" );
	}

}
