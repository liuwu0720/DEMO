package clt.com.cn.controller.employ;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import clt.com.cn.helps.ServiceUtil;
import clt.com.cn.helps.SystemStatus;
import clt.com.cn.model.entity.Employout;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IEmployholidayService;
import clt.com.cn.service.IEmployoutService;
import clt.com.cn.service.IEmployrecordService;
import clt.com.cn.service.IUserService;

@Controller
@RequestMapping( "/EmployoutServlet" )
public class EmployoutController
{
	
	@Autowired
	private IEmployoutService emoService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IEmployholidayService ehdService;
	@Autowired
	private IEmployrecordService emrService;
	
	// 获取所有未审批的外出申请
	@RequestMapping( "/getAllEmo" )
	public String getAllEmo( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		int lineid = ( Integer ) session.getAttribute( "lineid" );
		
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = ehdService.getempCountBySatusanduid( Employout.class , 0 , lineid );
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
		List emplist = ehdService.getempBySatusanduid( Employout.class , 0 , lineid );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "emplist" , emplist );
		request.setAttribute( "id" , "a2" );
		request.setAttribute( "freshenParam" , "yes" );
		
		request.setAttribute( "returnPath" , "EmployoutServlet/getAllEmo?op=1" );
		/*request.getRequestDispatcher("oa_employout/allemo.jsp").forward(
				request, response);*/
		return "oa_employout/allemo";
	}
	
	@RequestMapping( "/del" )
	public String delEmoById( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		emoService.delEmoById( id );
		// this.getAllEmo(request, response);
		return "redirect:/EmployoutServlet/getAllEmo";
	}
	
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
		
		return "oa_employout/addemo";
	}
	
	@RequestMapping( "/add" )
	public String addEmo( HttpServletRequest request )
	{
		Date now = new Date();
		Date date1 , date2 , date3;
		HttpSession session = request.getSession( true );
		String userno = ( String ) session.getAttribute( "uname" );
		String reason = request.getParameter( "reason" );
		String d1 = request.getParameter( "outdate1" );
		String d2 = request.getParameter( "outdate2" );
		
		String issend = request.getParameter( "issend" );
		int checkUsid = Integer.parseInt( request.getParameter( "checkusid" ) );
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		String date = sdf.format( now );
		if ( userno != null )
		{
			List< Smuser > list = emoService.getUserByName( userno );
			List< Employrecord > list2 = emoService.getEmrByName( userno );
			Smuser s = ( Smuser ) list.get( 0 );
			Employrecord em = s.getEmployrecord();
			
			Employout emo = new Employout();
			emo.setOpuser( s );
			emo.setRecordid( em.getLineid() );
			emo.setReason( reason );
			emo.setCheckmemo( " " );
			emo.setIscheck( 0 );
			
			// Smuser smuser = userService.getUSerByManagerApproval( s );
			Smuser smuser = userService.getUserById( checkUsid );
			
			emo.setCheckuser( smuser );
			emo.setDept( deptService.getDeptById( em.getDept().getLineid() ) );
			try
			{
				date1 = sdf.parse( d1 );
				date2 = sdf.parse( d2 );
				date3 = sdf.parse( date );
				emo.setCurrdate( date3 );
				emo.setDate1( date1 );
				emo.setDate2( date2 );
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
					deptService.approveMails( emo , 2 );
				}
				emoService.addEmo( emo );
			}
			catch ( Exception e )
			{
				System.out.println( "外出信息  保存失败 " + e.getMessage() );
			}
			
			// this.getEmoByUncheck(request, response);
			return "redirect:/EmployoutServlet/getEmoByUncheck";
		}
		else
		{
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			
			return "index";
		}
	}
	
	@RequestMapping( "/getEmoByid" )
	public String getEmoByid( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		// String
		// sql=" select emp.lineid,e.fileno,e.employname,d.deptname,emp.reason,emp.date1,emp.date2,emp.currdate,emp.ischeck,emp.checkremarks from employout emp,dept d,employrecord e where emp.deptid=d.lineid and emp.recordid=e.lineid and emp.lineid="+id;
		Employout emo = emoService.getEmoById( id );
		request.setAttribute( "emo" , emo );
		/*request.getRequestDispatcher("oa_employout/getbyid.jsp").forward(
				request, response);*/
		
		return "oa_employout/getbyid";
	}
	
	@RequestMapping( "/getUpdatePage" )
	public String getUpdatePage( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employout employ = emoService.getEmoById( id );
		request.setAttribute( "emo" , employ );
		/*request.getRequestDispatcher("oa_employout/updatepage.jsp").forward(
				request, response);*/
		return "oa_employout/updatepage";
	}
	
	@RequestMapping( "/updateEmo" )
	public String updateEmo( HttpServletRequest request )
	{
		
		HttpSession session = request.getSession( true );
		String userno = ( String ) session.getAttribute( "uname" );
		int opuserid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		
		int employid = Integer.parseInt( request.getParameter( "employid" ) );
		int ischeck = Integer.parseInt( request.getParameter( "ischeck" ) );
		String checkremarks = request.getParameter( "checkremarks" );
		Smuser smuser = userService.getUserById( opuserid );
		
		Employout employ = emoService.getEmoById( employid );
		if ( userno != null )
		{
			employ.setCheckremarks( checkremarks );
			employ.setIscheck( ischeck );
			employ.setCheckdate( new Date() );
			if ( employ.getCheckuser().getLineid() != opuserid )
			{
				employ.setActualuser( smuser );
			}
			if ( employ.getIscheck() == 2 )
			{
				
				deptService.sendMails( employ , 2 );
			}
			emoService.updateEmo( employ );
			// this.getAllEmo(request, response);
			return "redirect:/EmployoutServlet/getAllEmo";
		}
		else
		{
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			return "index";
		}
	}
	
	@RequestMapping( "/getEmoByIscheck" )
	public String getEmoByIscheck( HttpServletRequest request )
	{
		int ischeck = Integer.parseInt( request.getParameter( "ischeck" ) );
		HttpSession sess = request.getSession( false );
		int usid = Integer.parseInt( sess.getAttribute( "lineid" ) + "" );
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = ehdService.getempCountBySatusanduid( Employout.class , ischeck , usid );
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
		List emplist = ehdService.getempBySatusanduid( Employout.class , ischeck , usid );
		
		request.setAttribute( "ischeck" , ischeck );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "emplist" , emplist );
		request.setAttribute( "returnPath" ,
		        "EmployoutServlet/getEmoByIscheck?op=1&ischeck=" + ischeck );
		/*request.getRequestDispatcher("oa_employout/emobyis.jsp").forward(
				request, response);*/
		
		return "oa_employout/emobyis";
	}
	
	// 查询未审批请假条
	@RequestMapping( "/getEmoByUncheck" )
	public String getEmoByUncheck( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String uname = ( String ) session.getAttribute( "uname" );
		int lineid = ( Integer ) session.getAttribute( "lineid" );
		
		String hql = "from Employout ehd where ehd.ischeck=0 and ehd.opuser.lineid="
		        + lineid + " order by ehd.lineid desc";
		
		if ( uname != null )
		{
			Map stamap = new SystemStatus().getEmpSystemStatus();
			
			int page , pages , count;
			count = ehdService.getCountByHql( hql );
			String p = request.getParameter( "page" );
			
			pages = ehdService.getpages( count , 5 );
			page = ServiceUtil.getPage( p , pages );
			List< Employout > emplist = ehdService.pageSqlQuery( hql , 5 , page , null );
			
			request.setAttribute( "stamap" , stamap );
			request.setAttribute( "ehd" , emplist );
			
			request.setAttribute( "page" , page );
			request.setAttribute( "pages" , pages );
			request.setAttribute( "returnPath" ,
			        "EmployoutServlet/getEmoByUncheck?op=1&id=a2" );
			
			request.setAttribute( "id" , "a2" );
			
			/*request.getRequestDispatcher("oa_employout/uncheckemo.jsp")
					.forward(request, response);*/
			
			return "oa_employout/uncheckemo";
		}
		else
		{
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			return "index";
		}
	}
	
	// 删除个人请假条
	@RequestMapping( "/delEmoByGrid" )
	public String delEmoByGrid( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		emoService.delEmoById( id );
		// this.getEmoByUncheck(request, response);
		return "redirect:/EmployoutServlet/getEmoByUncheck";
	}
	
	// 查询请假记录
	@RequestMapping( "/getEmoByChecked" )
	public String getEmoByChecked( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String uname = ( String ) session.getAttribute( "uname" );
		int lineid = ( Integer ) session.getAttribute( "lineid" );
		String hql = "from Employout ehd where (ehd.ischeck=1 or ehd.ischeck=2) and ehd.opuser.lineid="
		        + lineid + " order by ehd.lineid desc";
		
		if ( uname != null )
		{
			// List< Employout > emo = emoService.getEmoByChecked( lineid );
			
			int page , pages , count;
			count = ehdService.getCountByHql( hql );
			String p = request.getParameter( "page" );
			
			pages = ehdService.getpages( count , 5 );
			page = ServiceUtil.getPage( p , pages );
			List< Employout > emplist = ehdService.pageSqlQuery( hql , 5 , page , null );
			
			Map< Integer , String > stamap = new SystemStatus().getEmpSystemStatus();
			
			request.setAttribute( "emo" , emplist );
			request.setAttribute( "stamap" , stamap );
			request.setAttribute( "page" , page );
			request.setAttribute( "pages" , pages );
			request.setAttribute( "returnPath" ,
			        "EmployoutServlet/getEmoByChecked?op=1&id=a2" );
			
			/*request.getRequestDispatcher("oa_employout/checkedemo.jsp")
					.forward(request, response);*/
			
			return "oa_employout/checkedemo";
		}
		else
		{
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			return "index";
		}
	}
	
	// 个人桌面HR外出确认
	@RequestMapping( "/updateEmpOutSatByID" )
	public String updateEmpSatByID( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employout ehd = emoService.getEmoById( id );
		ehd.setHrstatus( 1 );
		emoService.updateEmo( ehd );
		/*this.getEhdByUncheck(request, response);*/
		return "redirect:/EmployoutServlet/getNewOut";
	}
	
	@RequestMapping( "/getNewOut" )
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
			
			String hodaysql = " select emp.lineid,empcord.employname,d.deptname,po.positionname,to_char(emp.date1,'yyyy/MM/dd HH24:mi:ss') as begdate ,to_char(emp.date2,'yyyy/MM/dd HH24:mi:ss') as enddate,emp.reason from Employout emp,dept d,position po,employrecord empcord where emp.deptid=d.lineid and emp.recordid = empcord.lineid and empcord.positionid = po.lineid "
			        + " and getCompanyIDByDeptID(d.lineid) in ("
			        + depts
			        + ")  and emp.ischeck=2 and emp.hrstatus=0 ";
			List holidaylist = ehdService.getDateBySqlQuery( hodaysql , 0 , 0 );
			request.setAttribute( "holidaylist" , holidaylist );
		}
		
		return "oa_employout/showNewemployOut";
	}
	
}
