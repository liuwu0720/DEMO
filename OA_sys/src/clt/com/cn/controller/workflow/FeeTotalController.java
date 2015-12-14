package clt.com.cn.controller.workflow;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import clt.com.cn.model.entity.DeptTotalcost;
import clt.com.cn.service.ICosttypeService;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IDeptTotalCostService;

@Controller
@RequestMapping( "/feeTotalController" )
public class FeeTotalController
{
	@Autowired
	private IDeptTotalCostService totalService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private ICosttypeService typeService;
	
	/**
	 * 
	 * @Description:查询所有
	 * @param request
	 * @param vcCompany
	 * @param vcDept
	 * @param nMonth
	 * @return 
	 *   String 返回值描述
	 * @author chengwzh
	 * @create_date 2015年11月27日 下午3:49:09
	 */
	@RequestMapping( "/findAll" )
	public String findAll( HttpServletRequest request ,
	        @RequestParam( value = "vcCompany" , required = false ) String vcCompany ,
	        @RequestParam( value = "vcDept" , required = false ) String vcDept ,
	        @RequestParam( value = "nMonth" , required = false ) Integer nMonth )
	{
		String returnPath = "feeTotalController/findAll?op=1";
		String hql = "from DeptTotalcost where nEnable=0 ";
		if ( StringUtils.isNotBlank( vcCompany ) )
		{
			hql += " and vcCompany like '%" + vcCompany + "%'";
			returnPath += "&vcCompany=" + vcCompany;
		}
		if ( StringUtils.isNotBlank( vcDept ) )
		{
			hql += " and vcDept like '%" + vcDept + "%'";
			returnPath += "&vcDept=" + vcDept;
		}
		if ( nMonth != null && nMonth != 0 )
		{
			hql += " and nMonth=" + nMonth;
			returnPath += "&nMonth=" + nMonth;
		}
		hql += " order by I_DEPT desc,N_MONTH asc";
		String p = request.getParameter( "page" );
		int page , pages , count;
		int pageSize = 10;
		// String countHql = "from CostApply where nEnable=0 and iUser=" +
		// userId;
		count = totalService.getCountByHql( hql );
		pages = totalService.getpages( count , pageSize );
		
		if ( p == null || p == "" )
		{
			page = 1;
		}
		else
		{
			page = Integer.parseInt( p );
			if ( page < 1 )
			{
				page = 1;
			}
			else if ( page > pages )
			{
				page = pages;
			}
		}
		List< DeptTotalcost > totalCosts = totalService.pageQuery( hql , pageSize , page ,
		        null );
		request.setAttribute( "totalCosts" , totalCosts );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "vcCompany" , vcCompany );
		request.setAttribute( "vcDept" , vcDept );
		// request.setAttribute( "returnPath" ,
		// "feeTotalController/findAll?op=1&" );
		request.setAttribute( "returnPath" , returnPath );
		return "oa_fee/feeTotalList";
	}
	
	// @RequestMapping( "/toSave" )
	// public String toSave( @RequestParam( value = "id" , required = false )
	// Integer id ,
	// HttpServletRequest request )
	// {
	// String hql = "from Dept where pid=0 and active=1";
	// List< Dept > companys = deptService.getAllObjectOrder( hql );// 获取公司
	// if ( id != null )
	// {
	// DeptTotalcost totalCost = totalService.get( id );
	// int compId = totalCost.getiCompany();
	// String sql =
	// "select LINEID,DEPTNAME,PID,ACTIVE,USERNO,CURRDATE,MANAGERUSERID,"
	// + "ILEVEL from dept where active=1 and pid!=0 start with lineid="
	// + compId + " connect by prior lineid=pid";
	// List< Dept > depts = ( List< Dept > ) deptService.getDateBySqlQuery( sql
	// , 0 ,
	// 0 );
	// request.setAttribute( "depts" , depts );
	// request.setAttribute( "totalCost" , totalCost );
	// }
	// request.setAttribute( "companys" , companys );
	// return "oa_fee/feeBudgetSave";
	// }
	//
	// @RequestMapping( "/save" )
	// public String save( DeptTotalcost entity , HttpServletRequest request )
	// {
	// try
	// {
	// Integer id = entity.getLineid();
	// int compId = entity.getiCompany();// 公司id
	// Dept comp = deptService.getDeptById( compId );
	// entity.setVcCompany( comp.getDeptname() );
	// int deptId = entity.getiDept();// 部门id
	// Dept dept = deptService.getDeptById( deptId );
	// entity.setVcDept( dept.getDeptname() );
	// SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
	// String dtAddStr = request.getParameter( "dtAddstr" );
	// if ( StringUtils.isNotBlank( dtAddStr ) )
	// {
	// Date dtAdd = format.parse( dtAddStr );
	// entity.setDtAdd( dtAdd );// 添加时间
	// }
	// if ( id == null )
	// {
	// // 新增
	// totalService.save( entity );
	// }
	// else
	// {
	// // 修改
	// totalService.update( entity );
	// }
	// }
	// catch ( Exception e )
	// {
	// e.printStackTrace();
	// }
	// return "redirect:findAll";
	// }
	//
	// @RequestMapping( "/del" )
	// public String del( @RequestParam( value = "id" ) int id )
	// {
	// DeptTotalcost entity = totalService.get( id );
	// entity.setnEnable( 1 );
	// totalService.merge( entity );
	// return "redirect:findAll";
	// }
	//
	// @ResponseBody
	// @RequestMapping( "/getDeptByPid" )
	// public List< Dept > getDeptByPid( @RequestParam( value = "pid" ) int pid
	// )
	// {
	// String sql =
	// "select LINEID,DEPTNAME,PID,ACTIVE,USERNO,CURRDATE,MANAGERUSERID,"
	// + "ILEVEL from dept where active=1 and pid!=0 start with lineid=" + pid
	// + " connect by prior lineid=pid";
	// List< Dept > depts = ( List< Dept > ) deptService.getDateBySqlQuery( sql
	// , 0 , 0 );
	// return depts;
	// }
}
