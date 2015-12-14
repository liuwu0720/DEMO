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

import clt.com.cn.helps.DateUtil;
import clt.com.cn.helps.ServiceUtil;
import clt.com.cn.helps.SystemStatus;
import clt.com.cn.model.entity.Employholiday;
import clt.com.cn.model.entity.Employovertime;
import clt.com.cn.model.entity.EmployovertimeType;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IEmployholidayService;
import clt.com.cn.service.IEmployovertimeService;
import clt.com.cn.service.IEmployrecordService;
import clt.com.cn.service.IUserService;

@Controller
@RequestMapping( "/EmployovertimeServlet" )
public class EmployovertimeController
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
	private IEmployovertimeService empovertimeService;
	
	public IEmployholidayService getEhdService()
	{
		return ehdService;
	}
	
	public void setEhdService( IEmployholidayService ehdService )
	{
		this.ehdService = ehdService;
	}
	
	// 得到所有的加班信息
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
		request.setAttribute( "id" , "a3" );
		
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
		
		List< EmployovertimeType > typelist = empovertimeService
		        .getAllEmployovertimeType();
		
		// 获取对用审批人
		Smuser appuser = userService.getUSerByManagerApproval( sm );
		System.out.println( " 获取对应审批人 " + appuser.getLineid() + " >> "
		        + appuser.getEmployrecord().getEmployname() );
		
		JSONObject obj = new JSONObject();
		obj.element( "usid" , appuser.getLineid() );
		obj.element( "usname" , appuser.getEmployrecord().getEmployname() );
		
		request.setAttribute( "typelist" , typelist );
		request.setAttribute( "usobj" , obj );
		
		return "oa_employovertime/addemployovertime";
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
		String contents = request.getParameter( "contents" );
		String d1 = request.getParameter( "date1" );
		String d2 = request.getParameter( "date2" );
		// 加班时间 按天/半天算
		float days = Float.parseFloat( request.getParameter( "overtimedays" ) );
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
			EmployovertimeType emptype = empovertimeService
			        .getEmployovertimeType( typeid );
			Employovertime ehd = new Employovertime();
			ehd.setOpuser( s );
			ehd.setRecordid( em.getLineid() );
			ehd.setReason( reason );
			ehd.setContents( contents );
			ehd.setIscheck( 0 );
			ehd.setEmployovertimeType( emptype );
			ehd.setDays( days );
			
			// Smuser smuser = userService.getUSerByManagerApproval( s );
			Smuser smuser = userService.getUserById( checkUsid );
			
			ehd.setCheckuser( smuser );
			ehd.setDept( deptService.getDeptById( em.getDept().getLineid() ) );
			try
			{
				date1 = sdf.parse( d1 );
				date2 = sdf.parse( d2 );
				date3 = sdf.parse( date );
				ehd.setCurrdate( date3 );
				ehd.setDate1( date1 );
				ehd.setDate2( date2 );
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
					deptService.approveMails( ehd , 3 );
				}
				empovertimeService.addEmr( ehd );
			}
			catch ( Exception e )
			{
				System.out.println( "请假信息  保存失败 " + e.getMessage() );
			}
			
			// this.getEhdByUncheck(request, response);
			return "redirect:/EmployovertimeServlet/getEhdByUncheck";
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
		Employovertime emptime = empovertimeService.getEmployovertimeById( id );
		Map< Integer , String > stamap = SystemStatus.getEmpSystemStatus();
		
		request.setAttribute( "stamap" , stamap );
		request.setAttribute( "ehd" , emptime );
		/*request.getRequestDispatcher("oa_employholiday/getbyid.jsp").forward(
				request, response);*/
		
		return "oa_employovertime/getbyid";
	}
	
	// 得到单个请假条信息，放的更新页面中
	@RequestMapping( "/getUpdatePage" )
	public String getUpdatePage( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employovertime emptime = empovertimeService.getEmployovertimeById( id );
		request.setAttribute( "ehd" , emptime );
		/*request.getRequestDispatcher("oa_employholiday/updatepage.jsp")
				.forward(request, response);*/
		return "oa_employovertime/updatepage";
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
		Employovertime ehd = empovertimeService.getEmployovertimeById( id );
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
				
				deptService.sendMails( ehd , 3 );
			}
			empovertimeService.updateEmr( ehd );
			// this.getAllEhd(request, response);
			
			return "redirect:/EmployovertimeServlet/getEhdByIscheck?ischeck=0";
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
		
		String sql = "select emp.lineid,e.fileno,e.employname,d.deptname,emp.reason,emp.contents, emp.date1,emp.date2,emp.days,emp.ischeck "
		        + " from employovertime emp,dept d,employrecord e where emp.deptid=d.lineid and emp.recordid=e.lineid and emp.ischeck="
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
		
		System.out.println( "加班 " + sql );
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = empovertimeService.getCountBySQL( countSQL );
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
		List emplist = empovertimeService.getDateBySqlQuery( sql , 5 , page );
		Map< Integer , String > stamap = SystemStatus.getEmpSystemStatus();
		
		request.setAttribute( "ischeck" , ischeck );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "emplist" , emplist );
		request.setAttribute( "stamap" , stamap );
		request.setAttribute( "id" , "a3" );
		request.setAttribute( "freshenParam" , "yes" );
		
		request.setAttribute( "returnPath" ,
		        "EmployovertimeServlet/getEhdByIscheck?op=1&ischeck=" + ischeck );
		/*	request.getRequestDispatcher("oa_employholiday/ehdbyis.jsp").forward(
					request, response);
			*/
		return "oa_employovertime/employovertimeBychecklist";
	}
	
	// 查询个人未审批的请假条
	@RequestMapping( "/getEhdByUncheck" )
	public String getEhdByUncheck( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String uname = ( String ) session.getAttribute( "uname" );
		int lineid = ( Integer ) session.getAttribute( "lineid" );
		
		String hql = " from Employovertime ehd where ehd.ischeck=0 and ehd.opuser.lineid="
		        + lineid + " order by ehd.lineid desc";
		if ( uname != null )
		{
			
			Map< Integer , String > stamap = new SystemStatus().getEmpSystemStatus();
			
			int page , pages , count;
			count = empovertimeService.getCountByHql( hql );
			String p = request.getParameter( "page" );
			
			pages = ehdService.getpages( count , 5 );
			page = ServiceUtil.getPage( p , pages );
			List< Employovertime > emplist = empovertimeService.pageSqlQuery( hql , 5 ,
			        page , null );
			
			for ( Employovertime emp : emplist )
			{
				
				try
				{
					long[] counts = DateUtil.getDistanceDays( emp.getDate1() ,
					        emp.getDate2() );
					emp.setDifferdate( Integer.parseInt( counts[1] + "" ) );
				}
				catch ( Exception e )
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			request.setAttribute( "emplist" , emplist );
			request.setAttribute( "page" , page );
			request.setAttribute( "pages" , pages );
			
			request.setAttribute( "stamap" , stamap );
			request.setAttribute( "id" , "a3" );
			request.setAttribute( "returnPath" ,
			        "EmployovertimeServlet/getEhdByUncheck?op=1&id=a3" );
			
			return "oa_employovertime/employovertimelist";
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
		empovertimeService.delEmrById( id );
		// this.getAllEhd(request, response);
		
		return "redirect:/EmployovertimeServlet/getAllEhd?ischeck=0";
	}
	
	// 删除个人请假条
	@RequestMapping( "/delEhrByGrId" )
	public String delEhrByGrId( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		empovertimeService.delEmrById( id );
		/*this.getEhdByUncheck(request, response);*/
		return "redirect:/EmployovertimeServlet/getEhdByUncheck";
	}
	
	// 查询历史记录
	@RequestMapping( "/getEhdByChecked" )
	public String getEhdByChecked( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String uname = ( String ) session.getAttribute( "uname" );
		int lineid = ( Integer ) session.getAttribute( "lineid" );
		String hql = "from Employovertime ehd where (ehd.ischeck=1 or ehd.ischeck=2) and ehd.opuser.lineid="
		        + lineid + " order by ehd.lineid desc";
		if ( uname != null )
		{
			
			Map< Integer , String > stamap = new SystemStatus().getEmpSystemStatus();
			
			int page , pages , count;
			count = empovertimeService.getCountByHql( hql );
			String p = request.getParameter( "page" );
			
			pages = ehdService.getpages( count , 5 );
			page = ServiceUtil.getPage( p , pages );
			List< Employovertime > emplist = empovertimeService.pageSqlQuery( hql , 5 ,
			        page , null );
			
			request.setAttribute( "emplist" , emplist );
			request.setAttribute( "stamap" , stamap );
			request.setAttribute( "page" , page );
			request.setAttribute( "pages" , pages );
			request.setAttribute( "returnPath" ,
			        "EmployovertimeServlet/getEhdByChecked?op=1&id=a3" );
			return "oa_employovertime/employovertimelist";
		}
		else
		{
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			
			return "index";
		}
	}
	
	// 个人桌面HR加班确认
	@RequestMapping( "/updateEmpOverTimeSatByID" )
	public String updateEmpSatByID( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employovertime ehd = empovertimeService.getEmployovertimeById( id );
		ehd.setHrstatus( 1 );
		empovertimeService.updateEmr( ehd );
		/*this.getEhdByUncheck(request, response);*/
		return "redirect:/EmployovertimeServlet/getNewOverTime";
	}
	
	@RequestMapping( "/getNewOverTime" )
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
			
			String hodaysql = " select emp.lineid,empcord.employname,d.deptname,po.positionname,to_char(emp.date1,'yyyy/MM/dd HH24:mi:ss') as begdate ,to_char(emp.date2,'yyyy/MM/dd HH24:mi:ss') as enddate,emp.reason,emp.contents,emptype.typename from Employovertime emp,dept d,position po,employrecord empcord,employovertimetype emptype  where emp.deptid=d.lineid and emp.recordid = empcord.lineid and empcord.positionid = po.lineid and emp.employovertimetypeid = emptype.lineid "
			        + " and getCompanyIDByDeptID(d.lineid) in ("
			        + depts
			        + ")  and emp.ischeck=2 and emp.hrstatus=0 ";
			
			List holidaylist = ehdService.getDateBySqlQuery( hodaysql , 0 , 0 );
			request.setAttribute( "holidaylist" , holidaylist );
		}
		
		return "oa_employovertime/showNewemployOverTime";
	}
	
	// 加班时间计算
	@RequestMapping( "/getTimeByOvertime" )
	public String getTimeByOvertime( HttpServletRequest request )
	{
		String begstr = request.getParameter( "beginDate" );
		String endstr = request.getParameter( "endDate" );
		Date beginDate = DateUtil.parseTime( begstr );
		Date endDate = DateUtil.parseTime( endstr );
		
		// 判断是否同一天
		boolean issame = DateUtil.isSameDay( beginDate , endDate );
		if ( issame )
		{
			
		}
		return " ";
	}
}
