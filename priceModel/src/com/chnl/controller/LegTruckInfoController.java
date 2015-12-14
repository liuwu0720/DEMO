/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-9-10 下午1:21:03
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.chnl.base.AjaxUtil;
import com.chnl.base.DataControl;
import com.chnl.entity.Page;
import com.chnl.pojo.LegInfo;
import com.chnl.pojo.LegTruckInfo;
import com.chnl.pojo.TruckInfo;
import com.chnl.service.LegTruckInfoService;

/**
 * @Package com.chnl.controller
 * @Description: TODO(线路与拖车关联表的一些操作)
 * @author liuwu
 * @date 2014-9-10 下午1:21:03
 * @version V1.0
 */
@Controller
public class LegTruckInfoController
{
	@Autowired
	private LegTruckInfoService legTruckInfoService;
	
	private int truckId;

	@RequestMapping( "/getLegByTruck.do" )
	@ResponseBody
	public Map< String , Object > getLegInfoByTruckId(
	        HttpServletRequest request , HttpServletResponse response ) // 根据拖车ID获取线路所有信息
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
		if ( request.getParameter( "truckId" ) == null )
		{
			return null;
		}
		else
		{
			truckId = Integer.parseInt( request.getParameter( "truckId" ) );
			DataControl dataControl = new DataControl();
			Page p = dataControl.getcurrPage( request );
			String sql = "select l.origin,l.destination,l.income_distance,l.actual_distance,l.empty_distance,lt.* from leg_info l left join leg_truck_info lt on l.id=lt.legid where lt.truckid="
			        + truckId + "order by lt.id";
			Map< String , Object > legInfos = legTruckInfoService
			        .getLegInfoJDBC( sql , p );
			return legInfos;
		}
		
	}
	
	/**
	 * 编辑保存
	 * 
	 */
	@RequestMapping( "/editSaveTruck.do" )
	@ResponseBody
	public void editTruckSave( HttpServletRequest request ,
	        HttpServletResponse response )
	{	
		int legTruckId = Integer.parseInt( request.getParameter( "ID" ) );
		double fullCost = Double
		        .parseDouble( request.getParameter( "FULLCOST" ) );
		double emptyCost = Double.parseDouble( request
		        .getParameter( "EMPTCOST" ) );
		try
		{
			legTruckInfoService.editSaveTruck( legTruckId , fullCost ,
			        emptyCost );
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
	@RequestMapping( "/delLegTruckInfo.do" )
	public void deleteLegTruck( HttpServletRequest request ,
	        HttpServletResponse response )
	{	
		int legTruckId = Integer.parseInt( request.getParameter( "id" ) );
		try
		{
			legTruckInfoService.deleteLegTruckById( legTruckId );
			AjaxUtil.rendJson( response , true , "删除成功" );
		}
		catch ( Exception e )
		{
			// TODO: handle exception
		}
	}
	
	/**
	 * 新增时下拉框选择
	 */
	@RequestMapping( value = "/selLegInfo.do" )
	@ResponseBody
	public List< Map< String , Object > > getLegInfoList(
	        HttpServletRequest request , HttpServletResponse response )
	{
		// 查出可供选择的线路（过滤掉当前拖车已有的线路）
		
		List< Map< String , Object > > leglist = legTruckInfoService
		        .getSelectLeg( truckId );
		
		return leglist;
		
	}
	/**
	 * 新增保存
	 */
	@RequestMapping( value = "/addSaveLeg.do" )
	@ResponseBody
	public void addSaveLeg( HttpServletRequest request ,
	        HttpServletResponse response )
	{	
		int legId = Integer.parseInt( request.getParameter( "LEGID" ) );
		double fullCost = Double
		        .parseDouble( request.getParameter( "FULLCOST" ) );
		double emptyCost = Double.parseDouble( request
		        .getParameter( "EMPTCOST" ) );
		LegTruckInfo legTruckInfo = new LegTruckInfo();
		legTruckInfo.setEmptcost( emptyCost );
		legTruckInfo.setFullcost( fullCost );
		LegInfo legInfo = new LegInfo();
		legInfo.setId( legId );
		TruckInfo truckInfo = new TruckInfo();
		truckInfo.setId( truckId );
		legTruckInfo.setLegInfo( legInfo );
		legTruckInfo.setTruckInfo( truckInfo );
		legTruckInfoService.addSaveLegTruck( legTruckInfo );
		AjaxUtil.rendJson( response , true , "保存成功" );
	}
}
