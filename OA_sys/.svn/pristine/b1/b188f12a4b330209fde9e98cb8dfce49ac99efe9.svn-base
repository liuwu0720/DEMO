package clt.com.cn.controller.employ;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import clt.com.cn.model.entity.Dept;
import clt.com.cn.model.entity.EmployAddressList;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IEmployAddressListService;
import clt.com.cn.service.IEmployrecordService;
import clt.com.cn.service.IUserService;

@Controller
@RequestMapping( "/EmpAddressListServlet" )
public class EmployAddressListController
{
	
	@Autowired
	private IEmployAddressListService empaddressService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IEmployrecordService emrService;
	
	// 得到所有的请假条信息
	@RequestMapping( "/getAllEmpAddressList" )
	public String getAllEhd( HttpServletRequest request )
	{
		
		// 获取分公司 部门父id为0的
		String hql1 = " from Dept d where d.pid=0 ";
		List< Dept > delplist = deptService.getUserInfo( hql1 );
		
		String hql = " from EmployAddressList emp where emp.status=0 order by emp.dept.deptname desc ";
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = empaddressService.getCountByHql( hql );
		pages = empaddressService.getpages( count , 5 );
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
		List emplist = empaddressService.pageQuery( hql , 5 , page );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "emplist" , emplist );
		request.setAttribute( "deptlist" , delplist );
		
		request.setAttribute( "returnPath" ,
		        "EmpAddressListServlet/getAllEmpAddressList?op=1" );
		
		return "oa_employAddressList/allemployaddressList";
		/*request.getRequestDispatcher("oa_employholiday/allehd.jsp").forward(
				request, response);*/
	}
	
	// 删除单个请假条
	@RequestMapping( "/del" )
	public String delEhrById( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		EmployAddressList empadd = empaddressService.getEmployAddressListById( id );
		empadd.setStatus( 1 );
		
		empaddressService.updateEmr( empadd );
		
		return "redirect:/EmpAddressListServlet/getAllEmpAddressList";
	}
	
	// 请假页面前 加载请假类别数据
	@RequestMapping( "/addbefore" )
	public String addbefore( HttpServletRequest request )
	{
		// 获取分公司 部门父id为0的
		String hql1 = " from Dept d where d.pid=0 ";
		List< Dept > delplist = deptService.getUserInfo( hql1 );
		
		request.setAttribute( "deptlist" , delplist );
		request.setAttribute( "paramType" , "add" );
		
		return "oa_employAddressList/addemployaddressList";
	}
	
	@RequestMapping( "/update" )
	public String update( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		EmployAddressList empadd = empaddressService.getEmployAddressListById( id );
		
		int deptid = Integer.parseInt( request.getParameter( "deptid" ) );
		Dept de = deptService.getDeptById( deptid );
		
		String name = request.getParameter( "name" );
		String positionName = request.getParameter( "positionName" );
		String companyName = request.getParameter( "companyName" );
		String address = request.getParameter( "address" );
		String mobile = request.getParameter( "mobile" );
		String tel = request.getParameter( "tel" );
		String email = request.getParameter( "email" );
		
		empadd.setName( name );
		empadd.setCompanyName( companyName );
		empadd.setPositionName( positionName );
		empadd.setAddress( address );
		empadd.setEmail( email );
		empadd.setMobile( mobile );
		empadd.setTel( tel );
		empadd.setDept( de );
		
		empaddressService.updateEmr( empadd );
		return "redirect:/EmpAddressListServlet/getAllEmpAddressList";
	}
	
	@RequestMapping( "/add" )
	public String add( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		int usid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		
		EmployAddressList empadd = new EmployAddressList();
		Smuser opuser = userService.getUserById( usid );
		
		String name = request.getParameter( "name" );
		int deptid = Integer.parseInt( request.getParameter( "deptid" ) );
		Dept de = deptService.getDeptById( deptid );
		
		String positionName = request.getParameter( "positionName" );
		String companyName = request.getParameter( "companyName" );
		String address = request.getParameter( "address" );
		String mobile = request.getParameter( "mobile" );
		String tel = request.getParameter( "tel" );
		String email = request.getParameter( "email" );
		
		empadd.setName( name );
		empadd.setCompanyName( companyName );
		empadd.setPositionName( positionName );
		empadd.setAddress( address );
		empadd.setEmail( email );
		empadd.setMobile( mobile );
		empadd.setTel( tel );
		empadd.setStatus( 0 );
		empadd.setOptime( new Date() );
		empadd.setOpuser( opuser );
		empadd.setDept( de );
		
		empaddressService.addEmr( empadd );
		return "redirect:/EmpAddressListServlet/getAllEmpAddressList";
	}
	
	@RequestMapping( "/getByupdate" )
	public String getByupdate( HttpServletRequest request )
	{
		// 获取分公司 部门父id为0的
		String hql1 = " from Dept d where d.pid=0 ";
		List< Dept > delplist = deptService.getUserInfo( hql1 );
		
		int id = Integer.parseInt( request.getParameter( "id" ) );
		EmployAddressList empadd = empaddressService.getEmployAddressListById( id );
		
		request.setAttribute( "paramType" , "update" );
		request.setAttribute( "emp" , empadd );
		request.setAttribute( "deptlist" , delplist );
		return "oa_employAddressList/addemployaddressList";
	}
	
	// 获取 要查看的数据的id
	@RequestMapping( "/getEmrById" )
	public String getEmrById( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		EmployAddressList empadd = empaddressService.getEmployAddressListById( id );
		
		request.setAttribute( "emp" , empadd );
		return "oa_employAddressList/showAddresslistByID";
	}
	
	// 按员工姓名查询其档案信息
	@RequestMapping( "/getInfoByName" )
	public String getUserByName( HttpServletRequest request )
	{
		// 获取分公司 部门父id为0的
		String hql1 = " from Dept d where d.pid=0 ";
		List< Dept > delplist = deptService.getUserInfo( hql1 );
		
		String empsql = " from EmployAddressList emp where emp.status=0 ";
		
		String employname = request.getParameter( "employname" ).trim();
		int deptid = Integer.parseInt( request.getParameter( "deptid" ) );
		if ( deptid != 0 )
		{
			empsql = empsql + " and emp.dept.lineid = " + deptid;
		}
		System.out.println( "employname 1 " + employname );
		if ( employname != "" && employname != null )
		{
			try
			{
				employname = URLDecoder.decode( employname , "UTF-8" );
			}
			catch ( UnsupportedEncodingException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			empsql = empsql + " and ( emp.name like '%" + employname
			        + "% ' OR emp.positionName  like '%" + employname
			        + "%' OR emp.companyName like '%" + employname
			        + "%' OR emp.address like '" + employname + "') ";
			System.out.println( "getAllEmpAddressList getInfoByName  " + empsql );
		}
		
		System.out.println( "employname 2 " + employname );
		String p = request.getParameter( "page" );
		int page , pages , count;
		
		count = empaddressService.getCountByHql( empsql );
		pages = empaddressService.getpages( count , 5 );
		
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
		List list = empaddressService.pageQuery( empsql , 5 , page );
		request.setAttribute( "emplist" , list );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "employname" , employname );
		request.setAttribute( "deptlist" , delplist );
		request.setAttribute( "returnPath" ,
		        "EmpAddressListServlet/getInfoByName?op=1&employname=" + employname
		                + "&deptid=" + deptid );
		
		return "oa_employAddressList/allemployaddressList";
		
	}
	
}
