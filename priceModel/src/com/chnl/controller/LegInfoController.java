/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-8-29 下午5:00:12
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
import com.chnl.pojo.LegInfo;
import com.chnl.service.LegInfoService;

/**
 * @Package com.chnl.controller
 * @Description: TODO(后台管理：线路基本信息的增删改查)
 * @author liuwu
 * @date 2014-8-29 下午5:00:12
 * @version V1.0
 */
@Controller
public class LegInfoController
{
	@Autowired
	private LegInfoService legInfoService;

	@RequestMapping( "/getLegInfo.do" )
	@ResponseBody
	public Map< String , Object > getLegInfo( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		int page = 1;
		int pageSize = 10;
		Map< String , Object > map = new HashMap< String , Object >();
		List< LegInfo > legInfolist = new ArrayList< LegInfo >();
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
		legInfolist = legInfoService.getLegInfoByPage( page , pageSize , sort ,
		        order );
		List< LegInfo > totalPages = legInfoService.findAllLegs();
		System.out.println( legInfolist.size() );
		map.put( "rows" , legInfolist );
		if ( request.getParameter( "type" ) != null ) // 线路与商品车：选择线路数据表格下拉框
		{
			map.put( "rows" , totalPages );
		}
		map.put( "total" , totalPages.size() );
		
		System.out
		        .println( page + "--" + pageSize + "--" + sort + "--" + order );
		return map;

	}
	
	/**
	 * @Description:
	 * @param request
	 * @param legInfo
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-5 上午10:10:55
	 */
	@RequestMapping( value = "/saveLeg.do" , method = RequestMethod.POST )
	public void saveLegInfo( HttpServletRequest request , LegInfo legInfo ,
	        HttpServletResponse response )
	{
		System.out.println( "save--" + request.getParameter( "origin" ) );

		legInfo.setDays( Math.floor( legInfo.getActualDistance() / 550 ) + 2 );
		String iid = request.getParameter( "id" );
		if ( StringUtils.isNotBlank( iid ) )
		{
			legInfo.setId( Integer.parseInt( iid ) );
		}
		try
        {
	        legInfoService.saveLeg( legInfo );
			AjaxUtil.rendJson( response , true , "保存成功" );
        }
        catch ( Exception e )
        {
	        e.printStackTrace();
			AjaxUtil.rendJson( response , true , "保存成功55555" );
        }


	}
	
	@RequestMapping( value = "/deleteLeg.do" , method = RequestMethod.POST )
	public void deleteLegInfo( HttpServletRequest request ,
	        HttpServletResponse response )
	{

		System.out.println( "delete....." + request.getParameter( "id" ) );
		int legId = Integer.parseInt( request.getParameter( "id" ) );
		legInfoService.deleteLegById( legId );
		AjaxUtil.rendJson( response , true , "删除成功" );
	}

}
