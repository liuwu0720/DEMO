package clt.com.cn.controller.employ;

import java.io.File;
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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import clt.com.cn.helps.ServiceUtil;
import clt.com.cn.helps.SystemStatus;
import clt.com.cn.model.entity.Employholiday;
import clt.com.cn.model.entity.EmployholidayType;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IEmployholidayService;
import clt.com.cn.service.IEmployrecordService;
import clt.com.cn.service.IUserService;

@Controller
@RequestMapping( "/EmployholidayServlet" )
public class EmployholidayController
{
	
	@Autowired
	private IEmployholidayService ehdService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IEmployrecordService emrService;
	
	public IEmployholidayService getEhdService()
	{
		return ehdService;
	}
	
	public void setEhdService( IEmployholidayService ehdService )
	{
		this.ehdService = ehdService;
	}
	
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
		request.setAttribute( "id" , "a1" );
		request.setAttribute( "freshenParam" , "yes" );
		
		request.setAttribute( "returnPath" , "EmployholidayServlet/getAllEhd?op=1" );
		
		return "oa_employholiday/allehd";
		/*request.getRequestDispatcher("oa_employholiday/allehd.jsp").forward(
				request, response);*/
	}
	
	// 删除单个请假条
	@RequestMapping( "/del" )
	public String delEhrById( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		ehdService.delEhdById( id );
		// this.getAllEhd(request, response);
		
		return "redirect:/EmployholidayServlet/getAllEhd";
	}
	
	// 请假页面前 加载请假类别数据
	@RequestMapping( "/addbefore" )
	public String addbefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		int usid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		Smuser sm = userService.getUserById( usid );
		List< EmployholidayType > typelist = ehdService.getAllEmpholidayType();
		// 获取对用审批人
		Smuser appuser = userService.getUSerByManagerApproval( sm );
		System.out.println( " 获取对应审批人 " + appuser.getLineid() + " >> "
		        + appuser.getEmployrecord().getEmployname() );
		
		JSONObject obj = new JSONObject();
		obj.element( "usid" , appuser.getLineid() );
		obj.element( "usname" , appuser.getEmployrecord().getEmployname() );
		
		request.setAttribute( "typelist" , typelist );
		request.setAttribute( "usobj" , obj );
		
		return "oa_employholiday/addehd";
	}
	
	// 申请请假
	@RequestMapping( "/add" )
	public String addEhr( HttpServletRequest request )
	{
		MultipartHttpServletRequest mrequest = ( MultipartHttpServletRequest ) request;
		
		Map< String , MultipartFile > fileMap = mrequest.getFileMap();
		SimpleDateFormat sdff = new SimpleDateFormat( "yyyyMMddHHmmss" );
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		
		HttpSession session = request.getSession( true );
		String userno = ( String ) session.getAttribute( "uname" );
		String reason = request.getParameter( "reason" );
		String d1 = request.getParameter( "date1" );
		String d2 = request.getParameter( "date2" );
		
		String issend = request.getParameter( "issend" );
		int checkUsid = Integer.parseInt( request.getParameter( "checkusid" ) );
		
		int typeid = Integer.parseInt( request.getParameter( "typeid" ) );
		// 加班时间 按天/半天算
		float days = Float.parseFloat( request.getParameter( "days" ) );
		
		String filepath = "d:\\myfiles\\holiday\\";
		System.out.println( " 请假附件 存放地址: " + filepath );
		String fileName = "";
		File f = new File( filepath );
		if ( ! f.exists() )
		{
			f.mkdirs();
		}
		
		try
		{
			for ( Map.Entry< String , MultipartFile > entity : fileMap.entrySet() )
			{
				// 上传文件名
				MultipartFile mf = entity.getValue();
				fileName = mf.getOriginalFilename();
				if ( ! fileName.equals( "" ) && ! ( fileName == null ) )
				{
					// String filePathName = filepath + fileName;
					// 保存
					// count = mySmartUpload.save(filepath);
					// count = mySmartUpload.save(null);
					fileName = sdff.format( new Date() ) + "_" + userno + "_" + fileName;
					File uploadFile = new File( filepath + fileName );
					
					FileCopyUtils.copy( mf.getBytes() , uploadFile );
					
				}
			}
			
		}
		catch ( Exception e )
		{
			System.out.println( "请假   文件上传 出错 " + e.getMessage() );
		}
		
		if ( userno != null )
		{
			List< Smuser > list = ehdService.getUserByName( userno );
			Smuser s = ( Smuser ) list.get( 0 );
			Employrecord em = s.getEmployrecord();
			EmployholidayType emptype = ehdService.getEmpholidayTypeByID( typeid );
			Employholiday ehd = new Employholiday();
			ehd.setOpuser( s );
			ehd.setRecordid( em.getLineid() );
			ehd.setReason( reason );
			ehd.setCheckmemo( " " );
			ehd.setIscheck( 0 );
			ehd.setEmployholidayType( emptype );
			ehd.setHrstatus( 0 );
			ehd.setDays( days );
			
			// Smuser smuser = userService.getUSerByManagerApproval( s );
			Smuser smuser = userService.getUserById( checkUsid );
			
			ehd.setCheckuser( smuser );
			ehd.setDept( deptService.getDeptById( em.getDept().getLineid() ) );
			
			ehd.setCurrdate( new Date() );
			try
			{
				ehd.setDate1( sdf.parse( d1 ) );
				ehd.setDate2( sdf.parse( d2 ) );
			}
			catch ( ParseException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ehd.setFilepaths( fileName );
			try
			{
				// 发送邮件通知
				if ( issend != null )
				{
					deptService.approveMails( ehd , 1 );
				}
				ehdService.addEhd( ehd );
			}
			catch ( Exception e )
			{
				System.out.println( "请假信息  保存失败 " + e.getMessage() );
			}
			
			// this.getEhdByUncheck(request, response);
			return "redirect:/EmployholidayServlet/getEhdByUncheck";
		}
		else
		{
			
			return "index";
		}
	}
	
	// 查看单个请假条信息
	@RequestMapping( "/getEhdByid" )
	public String getEhdByid( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employholiday ehd = ehdService.get( id );
		request.setAttribute( "ehd" , ehd );
		/*request.getRequestDispatcher("oa_employholiday/getbyid.jsp").forward(
				request, response);*/
		
		return "oa_employholiday/getbyid";
	}
	
	// 得到单个请假条信息，放的更新页面中
	@RequestMapping( "/getUpdatePage" )
	public String getUpdatePage( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employholiday ehd = ehdService.get( id );
		request.setAttribute( "ehd" , ehd );
		/*request.getRequestDispatcher("oa_employholiday/updatepage.jsp")
				.forward(request, response);*/
		return "oa_employholiday/updatepage";
	}
	
	// 审批（更新）请假信息
	@RequestMapping( "/updateEhd" )
	public String updateEhd( HttpServletRequest request , HttpServletResponse response )
	        throws ServletException , IOException
	{
		
		HttpSession session = request.getSession( true );
		String userno = ( String ) session.getAttribute( "uname" );
		int opuserid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		
		int empholidayid = Integer.parseInt( request.getParameter( "empholidayid" ) );
		int ischeck = Integer.parseInt( request.getParameter( "ischeck" ) );
		String checkremarks = request.getParameter( "checkremarks" );
		Employholiday ehd = ehdService.get( empholidayid );
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
				
				deptService.sendMails( ehd , 1 );
			}
			ehdService.updateEhd( ehd );
			// this.getAllEhd(request, response);
			return "redirect:/EmployholidayServlet/getAllEhd";
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
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = ehdService
		        .getempCountBySatusanduid( Employholiday.class , ischeck , usid );
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
		List emplist = ehdService.getempBySatusanduid( Employholiday.class , ischeck ,
		        usid );
		request.setAttribute( "ischeck" , ischeck );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "emplist" , emplist );
		request.setAttribute( "returnPath" ,
		        "EmployholidayServlet/getEhdByIscheck?op=1&ischeck=" + ischeck );
		/*	request.getRequestDispatcher("oa_employholiday/ehdbyis.jsp").forward(
					request, response);
			*/
		return "oa_employholiday/ehdbyis";
	}
	
	// 查询个人未审批的请假条
	@RequestMapping( "/getEhdByUncheck" )
	public String getEhdByUncheck( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String uname = ( String ) session.getAttribute( "uname" );
		int lineid = ( Integer ) session.getAttribute( "lineid" );
		String hql = "from Employholiday ehd where ehd.ischeck=0 and ehd.opuser.lineid="
		        + lineid + " order by ehd.lineid desc";
		if ( uname != null )
		{
			
			// List< Employholiday > ehd = ehdService.getEhdByIscheck( 0 ,
			// lineid );
			Map stamap = new SystemStatus().getEmpSystemStatus();
			
			int page , pages , count;
			count = ehdService.getCountByHql( hql );
			String p = request.getParameter( "page" );
			
			pages = ehdService.getpages( count , 5 );
			page = ServiceUtil.getPage( p , pages );
			List< Employholiday > emplist = ehdService.pageSqlQuery( hql , 5 , page ,
			        null );
			
			request.setAttribute( "stamap" , stamap );
			request.setAttribute( "ehd" , emplist );
			request.setAttribute( "id" , "a1" );
			
			request.setAttribute( "page" , page );
			request.setAttribute( "pages" , pages );
			request.setAttribute( "returnPath" ,
			        "EmployholidayServlet/getEhdByUncheck?op=1&id=a1" );
			/*request.getRequestDispatcher("oa_employholiday/uncheckehd.jsp")
					.forward(request, response);*/
			return "oa_employholiday/uncheckehd";
		}
		else
		{
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			
			return "index";
		}
	}
	
	// 删除个人请假条
	@RequestMapping( "/delEhrByGrId" )
	public String delEhrByGrId( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		ehdService.delEhdById( id );
		/*this.getEhdByUncheck(request, response);*/
		return "redirect:/EmployholidayServlet/getEhdByUncheck";
	}
	
	// 查询历史记录
	@RequestMapping( "/getEhdByChecked" )
	public String getEhdByChecked( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String uname = ( String ) session.getAttribute( "uname" );
		int lineid = ( Integer ) session.getAttribute( "lineid" );
		
		String hql = "from Employholiday ehd where (ehd.ischeck=1 or ehd.ischeck=2) and ehd.opuser.lineid="
		        + lineid + " order by ehd.lineid desc";
		if ( uname != null )
		{
			
			int page , pages , count;
			count = ehdService.getCountByHql( hql );
			String p = request.getParameter( "page" );
			
			pages = ehdService.getpages( count , 5 );
			page = ServiceUtil.getPage( p , pages );
			List< Employholiday > emplist = ehdService.pageSqlQuery( hql , 5 , page ,
			        null );
			
			// List< Employholiday > ehd = ehdService.getEhdByChecked( lineid );
			Map< Integer , String > stamap = new SystemStatus().getEmpSystemStatus();
			request.setAttribute( "ehd" , emplist );
			request.setAttribute( "stamap" , stamap );
			request.setAttribute( "page" , page );
			request.setAttribute( "pages" , pages );
			request.setAttribute( "returnPath" ,
			        "EmployholidayServlet/getEhdByChecked?op=1&id=a1" );
			
			return "oa_employholiday/checkedehd";
		}
		else
		{
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			
			return "index";
		}
	}
	
	// 个人桌面HR确认请假
	@RequestMapping( "/updateEmpSatByID" )
	public String updateEmpSatByID( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employholiday ehd = ehdService.get( id );
		ehd.setHrstatus( 1 );
		ehdService.updateEhd( ehd );
		/*this.getEhdByUncheck(request, response);*/
		return "redirect:/EmployholidayServlet/getNewHoliday";
	}
	
	@RequestMapping( "/getNewHoliday" )
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
			
			String hodaysql = " select emp.lineid,empcord.employname,d.deptname,po.positionname,emp.date1,emp.date2,emp.reason from employholiday emp,dept d,position po,employrecord empcord where emp.deptid=d.lineid and emp.recordid = empcord.lineid and empcord.positionid = po.lineid "
			        + " and getCompanyIDByDeptID(d.lineid) in ("
			        + depts
			        + ") and emp.ischeck=2 and emp.hrstatus=0 ";
			List holidaylist = ehdService.getDateBySqlQuery( hodaysql , 0 , 0 );
			request.setAttribute( "holidaylist" , holidaylist );
		}
		
		return "oa_employholiday/showNewHoliday";
	}
}
