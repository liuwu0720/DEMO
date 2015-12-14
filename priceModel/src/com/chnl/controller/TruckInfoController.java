/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-9-5 下午1:53:59 
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
import com.chnl.pojo.TruckInfo;
import com.chnl.service.TruckInfoService;

/**
 * @Package com.chnl.controller
 * @Description: TODO(后台管理中，拖车的一些操作)
 * @author liuwu
 * @date 2014-9-5 下午1:53:59
 * @version V1.0
 */
@Controller
public class TruckInfoController
{	
	@Autowired
	private TruckInfoService truckInfoService;
	
	@RequestMapping( "/getTruckInfo2.do" )
	@ResponseBody
	public Map< String , Object > getTruckInfo( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		int page = 1;
		int pageSize = 10;
		Map< String , Object > map = new HashMap< String , Object >();
		List< TruckInfo > truckInfoList = new ArrayList< TruckInfo >();
		if ( request.getParameter( "page" ) != null
		        && request.getParameter( "rows" ) != null )
		{
			page = Integer.parseInt( request.getParameter( "page" ) );
			pageSize = Integer.parseInt( request.getParameter( "rows" ) );
		}
		
		;
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
		truckInfoList = truckInfoService.findAllTruckByPage( page , pageSize ,
		        sort , order );
		List< TruckInfo > totalPages = truckInfoService.findAllTruckInfos();
		map.put( "rows" , truckInfoList );
		if ( request.getParameter( "type" ) != null )// 线路与拖车下拉框时列表
		{
			map.put( "rows" , totalPages );
		}
		map.put( "total" , totalPages.size() );
		
		System.out
		        .println( page + "--" + pageSize + "--" + sort + "--" + order );
		return map;
		
	}
	
	// 保存操作
	@RequestMapping( value = "/saveTruck.do" , method = RequestMethod.POST )
	public void saveCurCar( HttpServletRequest request , TruckInfo truckInfo ,
	        HttpServletResponse response )
	{
		String iid = request.getParameter( "id" );
		if ( StringUtils.isNotBlank( iid ) )
		{
			truckInfo.setId( Integer.parseInt( iid ) );
		}
		truckInfoService.saveOrUpdate( truckInfo );
		AjaxUtil.rendJson( response , true , "保存成功" );
	}
	
	// 删除操作
	@RequestMapping( value = "/deleteTruck.do" , method = RequestMethod.POST )
	public void deleteLegInfo( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		
		System.out.println( "delete....." + request.getParameter( "id" ) );
		int truckId = Integer.parseInt( request.getParameter( "id" ) );
		truckInfoService.deleteById( truckId );
		AjaxUtil.rendJson( response , true , "删除成功" );
	}

}
