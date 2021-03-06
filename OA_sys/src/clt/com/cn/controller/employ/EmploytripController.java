package clt.com.cn.controller.employ;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Employtrip;
import clt.com.cn.model.entity.EmploytripTool;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IEmployholidayService;
import clt.com.cn.service.IEmployrecordService;
import clt.com.cn.service.IEmploytripService;
import clt.com.cn.service.IUserService;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Controller
@RequestMapping( "/EmploytripServlet" )
public class EmploytripController
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
	private IEmploytripService employtripService;
	
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
		
		List< EmploytripTool > toollist = employtripService.getAllEmploytripTool();
		
		// 获取对用审批人
		Smuser appuser = userService.getUSerByManagerApproval( sm );
		System.out.println( " 获取对应审批人 " + appuser.getLineid() + " >> "
		        + appuser.getEmployrecord().getEmployname() );
		
		JSONObject obj = new JSONObject();
		obj.element( "usid" , appuser.getLineid() );
		obj.element( "usname" , appuser.getEmployrecord().getEmployname() );
		
		request.setAttribute( "toollist" , toollist );
		request.setAttribute( "usobj" , obj );
		
		return "oa_employtrip/addemploytrip";
	}
	
	// 申请加班
	@RequestMapping( "/add" )
	public String addEhr( HttpServletRequest request )
	{
		// Date now = new Date();
		Date date1 , date2 , date3;
		HttpSession session = request.getSession( true );
		String userno = ( String ) session.getAttribute( "uname" );
		String reason = request.getParameter( "reason" );// 出差事由
		String remarks = request.getParameter( "remarks" );// 备注
		String address = request.getParameter( "address" );// 出差地址
		
		String d1 = request.getParameter( "date1" );// 出差开始时间
		String d2 = request.getParameter( "date2" );// 出差结束时间
		int typeid = Integer.parseInt( request.getParameter( "typeid" ) );// 乘坐工具
		
		String issend = request.getParameter( "issend" );
		int checkUsid = Integer.parseInt( request.getParameter( "checkusid" ) );
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		// String date = sdf.format( now );
		if ( userno != null )
		{
			List< Smuser > list = ehdService.getUserByName( userno );
			Smuser s = ( Smuser ) list.get( 0 );
			EmploytripTool emptool = employtripService.getEmploytripTool( typeid );
			Employrecord em = s.getEmployrecord();
			Employtrip ehd = new Employtrip();
			ehd.setOpuser( s );
			ehd.setRecordid( em.getLineid() );
			ehd.setReason( reason );
			ehd.setIscheck( 0 );
			ehd.setEmploytripTool( emptool );
			ehd.setAddress( address );
			ehd.setRemarks( remarks );
			
			// Smuser smuser = userService.getUSerByManagerApproval( s );
			Smuser smuser = userService.getUserById( checkUsid );
			
			ehd.setCheckuser( smuser );
			ehd.setDept( deptService.getDeptById( em.getDept().getLineid() ) );
			try
			{
				// 发送邮件通知
				if ( issend != null )
				{
					deptService.approveMails( ehd , 5 );
				}
				date1 = sdf.parse( d1 );
				date2 = sdf.parse( d2 );
				// date3 = sdf.parse( date );
				// ehd.setCurrdate( date3 );
				// ehd.setDate1( date1 );
				// ehd.setDate2( date2 );
				Calendar c1 = Calendar.getInstance();// 起始日历
				c1.setTime( date1 );
				SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM" );
				Date dtStart = format.parse( format.format( date1 ) );// 起始年月
				Date dtEnd = format.parse( format.format( date2 ) );// 结束年月
				Calendar startCal = Calendar.getInstance();
				startCal.setTime( dtStart );
				Calendar endCal = Calendar.getInstance();
				endCal.setTime( dtEnd );
				while ( startCal.getTimeInMillis() < endCal.getTimeInMillis() )
				{
					// 涉及跨月的订单进行拆分
					Employtrip entity = new Employtrip();
					entity.setOpuser( ehd.getOpuser() );
					entity.setRecordid( ehd.getRecordid() );
					entity.setReason( ehd.getReason() );
					entity.setIscheck( ehd.getIscheck() );
					entity.setEmploytripTool( ehd.getEmploytripTool() );
					entity.setAddress( ehd.getAddress() );
					entity.setRemarks( ehd.getRemarks() );
					entity.setCheckuser( ehd.getCheckuser() );
					entity.setDept( ehd.getDept() );
					entity.setCurrdate( new Date() );
					entity.setDate1( c1.getTime() );
					// c1.add( Calendar.MONTH , 1 );// 月加1
					// 拆单的时候设置结束时间为当月最后一天的下班时间18：00
					c1.set( Calendar.DAY_OF_MONTH ,
					        c1.getActualMaximum( Calendar.DAY_OF_MONTH ) );// 设置为当月最后一天
					c1.set( Calendar.HOUR_OF_DAY , 18 );
					c1.set( Calendar.MINUTE , 0 );
					c1.set( Calendar.SECOND , 0 );
					entity.setDate2( c1.getTime() );
					employtripService.addEmr( entity );
					// 拆单的时候设置开始时间为月初的上班时间9:00:00
					c1.add( Calendar.DAY_OF_MONTH , 1 );
					c1.set( Calendar.HOUR_OF_DAY , 9 );
					c1.set( Calendar.MINUTE , 0 );
					c1.set( Calendar.SECOND , 0 );
					startCal.add( Calendar.MONTH , 1 );
				}
				// 拆分后的处理
				Employtrip entity = new Employtrip();
				entity.setOpuser( ehd.getOpuser() );
				entity.setRecordid( ehd.getRecordid() );
				entity.setReason( ehd.getReason() );
				entity.setIscheck( ehd.getIscheck() );
				entity.setEmploytripTool( ehd.getEmploytripTool() );
				entity.setAddress( ehd.getAddress() );
				entity.setRemarks( ehd.getRemarks() );
				entity.setCheckuser( ehd.getCheckuser() );
				entity.setDept( ehd.getDept() );
				entity.setCurrdate( new Date() );
				entity.setDate1( c1.getTime() );
				entity.setDate2( date2 );
				employtripService.addEmr( entity );
				// employtripService.addEmr( ehd );
			}
			catch ( Exception e )
			{
				System.out.println( "出差信息  保存失败 " + e.getMessage() );
			}
			
			// this.getEhdByUncheck(request, response);
			return "redirect:/EmploytripServlet/getEhdByUncheck";
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
		Employtrip ehd = employtripService.getEmploytripById( id );
		Map< Integer , String > stamap = SystemStatus.getEmpSystemStatus();
		
		request.setAttribute( "stamap" , stamap );
		request.setAttribute( "ehd" , ehd );
		if ( request.getParameter( "paramType" ) != null )
		{
			return "oa_employtrip/showDateByPrint";
		}
		else
		{
			return "oa_employtrip/getbyid";
		}
		
		/*request.getRequestDispatcher("oa_employholiday/getbyid.jsp").forward(
				request, response);*/
		
	}
	
	// 查看单个请假条信息 来打印
	@RequestMapping( "/getPrintDateByID" )
	public void getPrintDateByID( HttpServletRequest request , HttpServletResponse resp )
	        throws Exception
	{
		System.out.println( "..............." );
		String fname = "出差信息.pdf";
		fname = new String( fname.getBytes() , "iso8859-1" );
		
		resp.setHeader( "Content-Disposition" , "attachment; filename=" + fname ); // 主要是向用户显示文件名
		resp.setContentType( "application/pdf" );
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employtrip ehd = employtripService.getEmploytripById( id );
		Document doc = new Document( PageSize.A4 );
		
		BaseFont bfchina = BaseFont.createFont( "C:/windows/fonts/simsun.ttc,1" ,
		        BaseFont.IDENTITY_H , BaseFont.EMBEDDED );
		PdfWriter.getInstance( doc , resp.getOutputStream() );
		doc.open();
		// doc.add( ( Element ) new Paragraph( "hello sb~" ) );
		
		PdfPTable t = new PdfPTable( 6 );
		float[] widths = { 0.1f , 0.15f , 0.1f , 0.15f , 0.1f , 0.15f };
		t.setWidths( widths );
		
		PdfPCell cel = new PdfPCell( new Paragraph( "出差信息 " , new Font( bfchina ) ) );
		cel.setColspan( 6 );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 50f );
		
		t.addCell( cel );
		
		// ------------
		cel = new PdfPCell( new Paragraph( "申请人 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 25f );
		t.addCell( cel );
		
		t.addCell( new PdfPCell( new Paragraph( ehd.getOpuser().getEmployrecord()
		        .getEmployname() , new Font( bfchina ) ) ) );
		t.addCell( new PdfPCell( new Paragraph( "所属部门 " , new Font( bfchina ) ) ) );
		t.addCell( new PdfPCell( new Paragraph( ehd.getDept().getDeptname() , new Font(
		        bfchina ) ) ) );
		t.addCell( new PdfPCell( new Paragraph( "申请日期" , new Font( bfchina ) ) ) );
		t.addCell( new PdfPCell( new Paragraph( DateUtil.formatDate( ehd.getCurrdate() ) ,
		        new Font( bfchina ) ) ) );
		
		// -----------
		cel = new PdfPCell( new Paragraph( "出差地址 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 25f );
		t.addCell( cel );
		
		t.addCell( new PdfPCell( new Paragraph( ehd.getAddress() , new Font( bfchina ) ) ) );
		
		t.addCell( new PdfPCell( new Paragraph( "工具" , new Font( bfchina ) ) ) );
		t.addCell( new PdfPCell( new Paragraph( ehd.getEmploytripTool().getToolname() ,
		        new Font( bfchina ) ) ) );
		
		t.addCell( new PdfPCell( new Paragraph( " " , new Font( bfchina ) ) ) );
		t.addCell( new PdfPCell( new Paragraph( " " , new Font( bfchina ) ) ) );
		
		// ------------
		cel = new PdfPCell( new Paragraph( "出差事由 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( ehd.getReason() , new Font( bfchina ) ) );
		cel.setColspan( 5 );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setMinimumHeight( 30f );
		t.addCell( cel );
		
		// ---------
		cel = new PdfPCell( new Paragraph( "备注 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		cel = new PdfPCell( new Paragraph( ehd.getRemarks() , new Font( bfchina ) ) );
		cel.setColspan( 5 );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		t.addCell( cel );
		
		// ----------
		cel = new PdfPCell( new Paragraph( "开始时间 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 25f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( DateUtil.formatDate( ehd.getDate1() ) ,
		        new Font( bfchina ) ) );
		cel.setColspan( 2 );
		t.addCell( cel );
		
		t.addCell( new PdfPCell( new Paragraph( "结束时间" , new Font( bfchina ) ) ) );
		cel = new PdfPCell( new Paragraph( DateUtil.formatDate( ehd.getDate2() ) ,
		        new Font( bfchina ) ) );
		cel.setColspan( 2 );
		t.addCell( cel );
		
		// ---------
		cel = new PdfPCell( new Paragraph( "审批人 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 25f );
		
		t.addCell( cel );
		String checkuser = "";
		// ==2 已审批成功 0 初始 1审批失败
		if ( ehd.getIscheck() == 2 )
		{
			checkuser = ehd.getCheckuser().getEmployrecord().getEmployname();
			if ( ehd.getActualuser() != null )
			{
				checkuser = ehd.getActualuser().getEmployrecord().getEmployname();
			}
		}
		
		t.addCell( new PdfPCell( new Paragraph( checkuser , new Font( bfchina ) ) ) );
		
		t.addCell( new PdfPCell( new Paragraph( "审批意见" , new Font( bfchina ) ) ) );
		cel = new PdfPCell( new Paragraph( ehd.getCheckremarks() , new Font( bfchina ) ) );
		cel.setColspan( 3 );
		t.addCell( cel );
		
		// --------------
		
		System.out.println( "t.getNumberOfColumns()2" + t.getNumberOfColumns() );
		System.out.println( "t.getRows() 2 " + t.getRows().size() );
		
		List rows = t.getRows();
		
		doc.add( t );
		resp.flushBuffer();
		doc.close();
		
	}
	
	// 得到单个请假条信息，放的更新页面中
	@RequestMapping( "/getUpdatePage" )
	public String getUpdatePage( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employtrip ehd = employtripService.getEmploytripById( id );
		
		request.setAttribute( "ehd" , ehd );
		/*request.getRequestDispatcher("oa_employholiday/updatepage.jsp")
				.forward(request, response);*/
		return "oa_employtrip/updatepage";
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
		Employtrip ehd = employtripService.getEmploytripById( id );
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
				
				deptService.sendMails( ehd , 5 );
			}
			employtripService.updateEmr( ehd );
			// this.getAllEhd(request, response);
			return "redirect:/EmploytripServlet/getEhdByIscheck?ischeck=0";
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
		
		String sql = "select emp.lineid,e.fileno,e.employname,d.deptname,emp.address, emp.reason,emp.remarks, emp.date1,emp.date2,emp.ischeck "
		        + " from Employtrip emp,dept d,employrecord e where emp.deptid=d.lineid and emp.recordid=e.lineid and emp.ischeck= "
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
		
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = ehdService.getCountBySQL( countSQL );
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
		List emplist = ehdService.getDateBySqlQuery( sql , 5 , page );
		Map< Integer , String > stamap = SystemStatus.getEmpSystemStatus();
		
		request.setAttribute( "stamap" , stamap );
		request.setAttribute( "ischeck" , ischeck );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "emplist" , emplist );
		request.setAttribute( "id" , "a5" );
		request.setAttribute( "freshenParam" , "yes" );
		
		request.setAttribute( "returnPath" ,
		        "EmploytripServlet/getEhdByIscheck?op=1&ischeck=" + ischeck );
		/*	request.getRequestDispatcher("oa_employholiday/ehdbyis.jsp").forward(
					request, response);
			*/
		return "oa_employtrip/employtripBychecklist";
	}
	
	// 查询个人未审批的请假条
	@RequestMapping( "/getEhdByUncheck" )
	public String getEhdByUncheck( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String uname = ( String ) session.getAttribute( "uname" );
		int lineid = ( Integer ) session.getAttribute( "lineid" );
		String hql = " from Employtrip ehd where ehd.ischeck=0 and ehd.opuser.lineid="
		        + lineid + " order by ehd.lineid desc";
		if ( uname != null )
		{
			
			Map< Integer , String > stamap = new SystemStatus().getEmpSystemStatus();
			
			int page , pages , count;
			count = ehdService.getCountByHql( hql );
			String p = request.getParameter( "page" );
			
			pages = ehdService.getpages( count , 5 );
			page = ServiceUtil.getPage( p , pages );
			List< Employtrip > emplist = ehdService.pageSqlQuery( hql , 5 , page , null );
			
			request.setAttribute( "emplist" , emplist );
			request.setAttribute( "page" , page );
			request.setAttribute( "pages" , pages );
			
			request.setAttribute( "stamap" , stamap );
			request.setAttribute( "id" , "a5" );
			request.setAttribute( "returnPath" ,
			        "EmploytripServlet/getEhdByUncheck?op=1&id=a5" );
			
			/*request.getRequestDispatcher("oa_employholiday/uncheckehd.jsp")
					.forward(request, response);*/
			return "oa_employtrip/employtriplist";
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
		employtripService.delEmrById( id );
		// this.getAllEhd(request, response);
		
		return "redirect:/EmploytripServlet/getEhdByIscheck?ischeck=0";
	}
	
	// 删除个人请假条
	@RequestMapping( "/delEhrByGrId" )
	public String delEhrByGrId( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		employtripService.delEmrById( id );
		/*this.getEhdByUncheck(request, response);*/
		return "redirect:/EmploytripServlet/getEhdByUncheck";
	}
	
	// 查询历史记录
	@RequestMapping( "/getEhdByChecked" )
	public String getEhdByChecked( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String uname = ( String ) session.getAttribute( "uname" );
		int lineid = ( Integer ) session.getAttribute( "lineid" );
		String hql = "from Employtrip ehd where (ehd.ischeck=1 or ehd.ischeck=2) and ehd.opuser.lineid="
		        + lineid + " order by ehd.lineid desc";
		if ( uname != null )
		{
			
			Map< Integer , String > stamap = new SystemStatus().getEmpSystemStatus();
			
			int page , pages , count;
			count = ehdService.getCountByHql( hql );
			String p = request.getParameter( "page" );
			
			pages = ehdService.getpages( count , 5 );
			page = ServiceUtil.getPage( p , pages );
			List< Employtrip > emplist = ehdService.pageSqlQuery( hql , 5 , page , null );
			
			request.setAttribute( "emplist" , emplist );
			request.setAttribute( "page" , page );
			request.setAttribute( "pages" , pages );
			
			request.setAttribute( "stamap" , stamap );
			request.setAttribute( "id" , "a5" );
			request.setAttribute( "returnPath" ,
			        "EmploytripServlet/getEhdByChecked?op=1&id=a5" );
			
			return "oa_employtrip/employtriplist";
		}
		else
		{
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			
			return "index";
		}
	}
	
	// 个人桌面HR外出确认
	@RequestMapping( "/updateEmpTripSatByID" )
	public String updateEmpSatByID( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employtrip ehd = employtripService.getEmploytripById( id );
		ehd.setHrstatus( 1 );
		employtripService.updateEmr( ehd );
		/*this.getEhdByUncheck(request, response);*/
		return "redirect:/EmploytripServlet/getNewTrip";
	}
	
	@RequestMapping( "/getNewTrip" )
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
			
			String hodaysql = " select emp.lineid,empcord.employname,d.deptname,po.positionname,emp.reason,emp.remarks,emptool.toolname,to_char(emp.date1,'yyyy/MM/dd HH24:mi:ss') as date1,to_char(emp.date2,'yyyy/MM/dd HH24:mi:ss') as date2  from Employtrip emp,dept d,position po,employrecord empcord,employtriptool emptool where emp.deptid=d.lineid and emp.recordid = empcord.lineid and empcord.positionid = po.lineid  and emp.employtriptoolid =emptool.lineid   "
			        + " and getCompanyIDByDeptID(d.lineid) in ("
			        + depts
			        + ") and emp.ischeck=2 and emp.hrstatus=0 ";
			
			List holidaylist = ehdService.getDateBySqlQuery( hodaysql , 0 , 0 );
			request.setAttribute( "holidaylist" , holidaylist );
		}
		return "oa_employtrip/showNewTrip";
	}
	
}
