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

import clt.com.cn.model.entity.EmployAddressList;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IEmployAddressListService;
import clt.com.cn.service.IEmploycontractService;
import clt.com.cn.service.IEmployrecordService;
import clt.com.cn.service.IUserService;

@Controller
@RequestMapping( "/EmploycontractServlet" )
public class EmploycontractController
{
	
	@Autowired
	private IEmploycontractService empcontractService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IEmployrecordService emrService;
	@Autowired
	private IEmployAddressListService empaddressService;
	
	// 得到所有的信息
	@RequestMapping( "/getAllcontractList" )
	public String getAllEhd( HttpServletRequest request )
	{
		
		String hql = " from Employcontract emp where emp.active=0 ";
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = empcontractService.getCountByHql( hql );
		pages = empcontractService.getpages( count , 5 );
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
		List emplist = empcontractService.pageQuery( hql , 5 , page );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "emplist" , emplist );
		request.setAttribute( "returnPath" ,
		        "EmploycontractServlet/getAllEmploycontractList?op=1" );
		
		return "oa_employcontract/employcontractlist";
		/*request.getRequestDispatcher("oa_employholiday/allehd.jsp").forward(
				request, response);*/
	}
	
	// 得到未签订合同的员工信息
	@RequestMapping( "/getAllcontractListBycord" )
	public String getAllcontractListBycord( HttpServletRequest request )
	{
		
		// String hql =" from Employcontract emp where emp.active=0 ";
		String sql = " select * from ( select emp.lineid emplineid,emp.employname,d.deptname,po.positionname from employrecord emp,dept d, position po where emp.deptid= d.lineid and emp.positionid = po.lineid  ) s "
		        + " left join  Employcontract con on s.emplineid = con.employrecordid and con.active = 0 and con.lineid  is not null ";
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = empcontractService.getCountByHql( sql );
		pages = empcontractService.getpages( count , 5 );
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
		List emplist = empcontractService.pageQuery( sql , 5 , page );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "emplist" , emplist );
		request.setAttribute( "returnPath" ,
		        "EmploycontractServlet/getAllEmploycontractList?op=1" );
		
		return "oa_employcontract/employcontractlist";
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
		
		request.setAttribute( "paramType" , "add" );
		
		return "oa_employAddressList/addemployaddressList";
	}
	
	@RequestMapping( "/update" )
	public String update( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		EmployAddressList empadd = empaddressService.getEmployAddressListById( id );
		
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
		
		empaddressService.addEmr( empadd );
		return "redirect:/EmpAddressListServlet/getAllEmpAddressList";
	}
	
	@RequestMapping( "/getByupdate" )
	public String getByupdate( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		EmployAddressList empadd = empaddressService.getEmployAddressListById( id );
		
		request.setAttribute( "paramType" , "update" );
		request.setAttribute( "emp" , empadd );
		return "oa_employAddressList/addemployaddressList";
	}
	
	// 按员工姓名查询其档案信息
	@RequestMapping( "/getInfoByName" )
	public String getUserByName( HttpServletRequest request )
	{
		
		String empsql = " from EmployAddressList emp where emp.status=0 ";
		
		String employname = request.getParameter( "employname" );
		System.out.println( "employname 1 " + employname );
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
		request.setAttribute( "returnPath" ,
		        "EmpAddressListServlet/getInfoByName?op=1&employname=" + employname );
		
		return "oa_employAddressList/allemployaddressList";
		
	}
	
}
