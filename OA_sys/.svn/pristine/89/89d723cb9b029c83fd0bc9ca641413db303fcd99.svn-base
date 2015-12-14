package clt.com.cn.controller.employ;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import clt.com.cn.helps.ServiceUtil;
import clt.com.cn.helps.SystemStatus;
import clt.com.cn.model.entity.Employattendance;
import clt.com.cn.model.entity.Employholiday;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IEmployattendanceService;
import clt.com.cn.service.IEmployholidayService;
import clt.com.cn.service.IEmployrecordService;
import clt.com.cn.service.IUserService;

@Controller
@RequestMapping( "/EmployattendanceServlet" )
public class EmployattendanceController
{
	
	@Autowired
	private IEmployholidayService ehdService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IEmployrecordService emrService;
	@Autowired
	private IEmployattendanceService employattendanceService;
	
	// 得到所有的请假条信息
	@RequestMapping( "/getAllEhd" )
	public String getAllEhd( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		int lineid = ( Integer ) session.getAttribute( "lineid" );
		
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = ehdService.getempCountBySatusanduid( Employholiday.class , 0 , lineid );
		pages = ehdService.getpages( count , 5 );
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
		List emplist = ehdService.getempBySatusanduid( Employholiday.class , 0 , lineid );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "emplist" , emplist );
		request.setAttribute( "returnPath" , "EmployholidayServlet/getAllEhd?op=1" );
		
		return "oa_employholiday/allehd";
		/*request.getRequestDispatcher("oa_employholiday/allehd.jsp").forward(
				request, response);*/
	}
	
	// 请假页面前 加载请假类别数据
	@RequestMapping( "/addbefore" )
	public String addbefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		int usid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		Smuser sm = userService.getUserById( usid );
		
		// 获取对用审批人
		Smuser appuser = userService.getUSerByManagerApproval( sm );
		System.out.println( " 获取对应审批人 " + appuser.getLineid() + " >> "
		        + appuser.getEmployrecord().getEmployname() );
		
		JSONObject obj = new JSONObject();
		obj.element( "usid" , appuser.getLineid() );
		obj.element( "usname" , appuser.getEmployrecord().getEmployname() );
		
		request.setAttribute( "usobj" , obj );
		
		return "oa_employattendance/addemployattendance";
	}
	
	// 申请加班
	@RequestMapping( "/add" )
	public String addEhr( HttpServletRequest request )
	{
		Date now = new Date();
		Date date1 , date2 , date3;
		HttpSession session = request.getSession( true );
		String userno = ( String ) session.getAttribute( "uname" );
		String reason = request.getParameter( "reason" );
		String d1 = request.getParameter( "date1" );
		int typeid = Integer.parseInt( request.getParameter( "typeid" ) );
		
		String issend = request.getParameter( "issend" );
		int checkUsid = Integer.parseInt( request.getParameter( "checkusid" ) );
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		String date = sdf.format( now );
		if ( userno != null )
		{
			List< Smuser > list = ehdService.getUserByName( userno );
			Smuser s = ( Smuser ) list.get( 0 );
			Employrecord em = s.getEmployrecord();
			Employattendance ehd = new Employattendance();
			ehd.setOpuser( s );
			ehd.setRecordid( em.getLineid() );
			ehd.setReason( reason );
			ehd.setIscheck( 0 );
			if ( typeid == 1 )
			{
				ehd.setTypename( "上班异常" );
			}
			else
			{
				ehd.setTypename( "下班异常" );
			}
			
			// Smuser smuser = userService.getUSerByManagerApproval( s );
			Smuser smuser = userService.getUserById( checkUsid );
			
			ehd.setCheckuser( smuser );
			ehd.setDept( deptService.getDeptById( em.getDept().getLineid() ) );
			try
			{
				date1 = sdf.parse( d1 );
				date3 = sdf.parse( date );
				ehd.setCurrdate( date3 );
				ehd.setDate1( date1 );
			}
			catch ( ParseException e )
			{
				e.printStackTrace();
			}
			try
			{
				// 发送邮件通知
				if ( issend != null )
				{
					deptService.approveMails( ehd , 4 );
				}
				employattendanceService.addEmr( ehd );
			}
			catch ( Exception e )
			{
				System.out.println( "请假信息  保存失败 " + e.getMessage() );
			}
			
			// this.getEhdByUncheck(request, response);
			return "redirect:/EmployattendanceServlet/getEhdByUncheck";
		}
		else
		{
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			
			return "index";
		}
	}
	
	// 查看单个请假条信息
	@RequestMapping( "/getEhdByid" )
	public String getEhdByid( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employattendance ehd = employattendanceService.getEmployattendanceById( id );
		Map< Integer , String > stamap = SystemStatus.getEmpSystemStatus();
		
		request.setAttribute( "stamap" , stamap );
		request.setAttribute( "ehd" , ehd );
		/*request.getRequestDispatcher("oa_employholiday/getbyid.jsp").forward(
				request, response);*/
		
		return "oa_employattendance/getbyid";
	}
	
	// 得到单个请假条信息，放的更新页面中
	@RequestMapping( "/getUpdatePage" )
	public String getUpdatePage( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employattendance ehd = employattendanceService.getEmployattendanceById( id );
		request.setAttribute( "ehd" , ehd );
		/*request.getRequestDispatcher("oa_employholiday/updatepage.jsp")
				.forward(request, response);*/
		return "oa_employattendance/updatepage";
	}
	
	// 审批（更新）请假信息
	@RequestMapping( "/updateEhd" )
	public String updateEhd( HttpServletRequest request , HttpServletResponse response )
	        throws ServletException , IOException
	{
		
		HttpSession session = request.getSession( true );
		String userno = ( String ) session.getAttribute( "uname" );
		int opuserid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		
		int id = Integer.parseInt( request.getParameter( "id" ) );
		int ischeck = Integer.parseInt( request.getParameter( "ischeck" ) );
		String checkremarks = request.getParameter( "checkremarks" );
		Employattendance ehd = employattendanceService.getEmployattendanceById( id );
		Smuser smuser = userService.getUserById( opuserid );
		
		if ( userno != null )
		{
			ehd.setCheckremarks( checkremarks );
			ehd.setIscheck( ischeck );
			ehd.setCheckdate( new Date() );
			// 如果登录的用户和该请假对应的审批人不一样(普通员工默认审批为自己经理 经理则祝丽) 则设置实际审批人字段为登陆人
			if ( ehd.getCheckuser().getLineid() != opuserid )
			{
				ehd.setActualuser( smuser );
			}
			if ( ehd.getIscheck() == 2 )
			{
				
				deptService.sendMails( ehd , 4 );
			}
			employattendanceService.updateEmr( ehd );
			// this.getAllEhd(request, response);
			return "redirect:/EmployattendanceServlet/getEhdByIscheck?ischeck=0";
		}
		else
		{
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			return "index";
		}
	}
	
	// 按审批状态查询
	@RequestMapping( "/getEhdByIscheck" )
	public String getEhdByIscheck( HttpServletRequest request )
	{
		int ischeck = Integer.parseInt( request.getParameter( "ischeck" ) );
		HttpSession sess = request.getSession( false );
		int usid = Integer.parseInt( sess.getAttribute( "lineid" ) + "" );
		String sql = "select emp.lineid,e.fileno,e.employname,d.deptname,emp.reason,emp.opdate,emp.ischeck "
		        + " from Employattendance emp,dept d,employrecord e where emp.deptid=d.lineid and emp.recordid=e.lineid and emp.ischeck= "
		        + ischeck
		        + " and (emp.checkuserid="
		        + usid
		        + " or emp.checkuserid in(select empdit.certigieruid from employaccredit empdit where empdit.authorizeruid="
		        + usid + " )) ";
		
		String countSQL = "";
		int beginPos = sql.toLowerCase().indexOf( "from" );
		if ( beginPos != - 1 )
		{
			countSQL = "select count(*) " + sql.substring( beginPos );
		}
		
		System.out.println( "考勤 " + sql );
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = employattendanceService.getCountBySQL( countSQL );
		pages = ehdService.getpages( count , 5 );
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
		List emplist = employattendanceService.getDateBySqlQuery( sql , 5 , page );
		Map< Integer , String > stamap = SystemStatus.getEmpSystemStatus();
		
		request.setAttribute( "ischeck" , ischeck );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "emplist" , emplist );
		request.setAttribute( "stamap" , stamap );
		request.setAttribute( "id" , "a4" );
		request.setAttribute( "freshenParam" , "yes" );
		
		request.setAttribute( "returnPath" ,
		        "EmployattendanceServlet/getEhdByIscheck?op=1&ischeck=" + ischeck );
		/*	request.getRequestDispatcher("oa_employholiday/ehdbyis.jsp").forward(
					request, response);
			*/
		return "oa_employattendance/employattendanceBychecklist";
	}
	
	// 查询个人未审批的请假条
	@RequestMapping( "/getEhdByUncheck" )
	public String getEhdByUncheck( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String uname = ( String ) session.getAttribute( "uname" );
		int lineid = ( Integer ) session.getAttribute( "lineid" );
		
		String hql = " from Employattendance ehd where ehd.ischeck=0 and ehd.opuser.lineid="
		        + lineid + " order by ehd.lineid desc";
		if ( uname != null )
		{
			// List< Employattendance > ehd =
			// employattendanceService.getObjInfoByCondition(
			// hql , 0 , lineid );
			
			int page , pages , count;
			count = ehdService.getCountByHql( hql );
			String p = request.getParameter( "page" );
			
			pages = ehdService.getpages( count , 5 );
			page = ServiceUtil.getPage( p , pages );
			List< Employattendance > emplist = ehdService.pageSqlQuery( hql , 5 , page ,
			        null );
			
			Map< Integer , String > stamap = new SystemStatus().getEmpSystemStatus();
			request.setAttribute( "emplist" , emplist );
			request.setAttribute( "page" , page );
			request.setAttribute( "pages" , pages );
			request.setAttribute( "stamap" , stamap );
			request.setAttribute( "id" , "a4" );
			
			request.setAttribute( "returnPath" ,
			        "EmployattendanceServlet/getEhdByUncheck?op=1&id=a4" );
			/*request.getRequestDispatcher("oa_employholiday/uncheckehd.jsp")
					.forward(request, response);*/
			return "oa_employattendance/employattendancelist";
		}
		else
		{
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			
			return "index";
		}
	}
	
	// 删除单个请假条
	@RequestMapping( "/del" )
	public String delEhrById( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		employattendanceService.delEmrById( id );
		// this.getAllEhd(request, response);
		
		return "redirect:/EmployattendanceServlet/getEhdByIscheck?ischeck=0";
	}
	
	// 删除个人请假条
	@RequestMapping( "/delEhrByGrId" )
	public String delEhrByGrId( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		employattendanceService.delEmrById( id );
		/*this.getEhdByUncheck(request, response);*/
		return "redirect:/EmployattendanceServlet/getEhdByUncheck";
	}
	
	// 查询历史记录
	@RequestMapping( "/getEhdByChecked" )
	public String getEhdByChecked( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String uname = ( String ) session.getAttribute( "uname" );
		int lineid = ( Integer ) session.getAttribute( "lineid" );
		String hql = "from Employattendance ehd where (ehd.ischeck=1 or ehd.ischeck=2) and ehd.opuser.lineid="
		        + lineid + " order by ehd.lineid desc";
		
		if ( uname != null )
		{
			int page , pages , count;
			count = ehdService.getCountByHql( hql );
			String p = request.getParameter( "page" );
			
			pages = ehdService.getpages( count , 5 );
			page = ServiceUtil.getPage( p , pages );
			List< Employattendance > emplist = ehdService.pageSqlQuery( hql , 5 , page ,
			        null );
			
			Map< Integer , String > stamap = new SystemStatus().getEmpSystemStatus();
			request.setAttribute( "emplist" , emplist );
			request.setAttribute( "page" , page );
			request.setAttribute( "pages" , pages );
			request.setAttribute( "stamap" , stamap );
			request.setAttribute( "id" , "a4" );
			
			request.setAttribute( "returnPath" ,
			        "EmployattendanceServlet/getEhdByChecked?op=1&id=a4" );
			
			return "oa_employattendance/employattendancelist";
		}
		else
		{
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			
			return "index";
		}
	}
	
	// 个人桌面HR外出确认
	@RequestMapping( "/updateEmpAttentSatByID" )
	public String updateEmpSatByID( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employattendance ehd = employattendanceService.getEmployattendanceById( id );
		ehd.setHrstatus( 1 );
		employattendanceService.updateEmr( ehd );
		/*this.getEhdByUncheck(request, response);*/
		return "redirect:/EmployattendanceServlet/getNewAttendance";
	}
	
	@RequestMapping( "/getNewAttendance" )
	public String getNewHoliday( HttpServletRequest request )
	{
		HttpSession sess = request.getSession();
		
		int usid = Integer.parseInt( sess.getAttribute( "lineid" ) + "" );
		
		String sql = "  select d.deptid from userSchedule d where d.userid=" + usid;
		List< Object > hqlist = emrService.getDateBySqlQuery( sql , 0 , 0 );
		
		if ( hqlist.size() > 0 )
		{
			String depts = "";
			for ( int i = 0 ; i < hqlist.size() ; i++ )
			{
				if ( i == hqlist.size() - 1 )
				{
					depts += hqlist.get( i );
				}
				else
				{
					depts += hqlist.get( i ) + ",";
				}
			}
			
			String hodaysql = " select emp.lineid,empcord.employname,d.deptname,po.positionname,emp.reason,emp.typename,to_char(emp.opdate,'yyyy/MM/dd HH24:mi:ss') as opdate  from Employattendance emp,dept d,position po,employrecord empcord where emp.deptid=d.lineid and emp.recordid = empcord.lineid and empcord.positionid = po.lineid "
			        + " and getCompanyIDByDeptID(d.lineid) in ("
			        + depts
			        + ") and emp.ischeck=2 and emp.hrstatus=0 ";
			
			List holidaylist = ehdService.getDateBySqlQuery( hodaysql , 0 , 0 );
			request.setAttribute( "holidaylist" , holidaylist );
		}
		return "oa_employattendance/showNewAttendance";
	}
}
