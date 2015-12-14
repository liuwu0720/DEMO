package clt.com.cn.controller.employ;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import clt.com.cn.helps.ServiceUtil;
import clt.com.cn.helps.SystemStatus;
import clt.com.cn.model.entity.Dept;
import clt.com.cn.model.entity.DeptAddress;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.EmployrecordCheck;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IEmployholidayService;
import clt.com.cn.service.IEmployovertimeService;
import clt.com.cn.service.IEmployrecordCheckService;
import clt.com.cn.service.IEmployrecordService;
import clt.com.cn.service.IUserService;

/**
 * @Package clt.com.cn.controller.employ
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenbin
 * @date 2014-7-22 下午4:22:11
 * @version V1.0
 */
@Controller
@RequestMapping( "/EmployrecordCheckServlet" )
public class EmployrecordCheckController
{
	@Autowired
	private IUserService userService;
	@Autowired
	private IEmployrecordCheckService recordCheckService;
	@Autowired
	private IEmployovertimeService empovertimeService;
	@Autowired
	private IEmployholidayService ehdService;
	@Autowired
	private IEmployrecordService emrService;
	@Autowired
	private IDeptService deptService;
	
	@RequestMapping( "/geAllRecordByUser" )
	public String geAllRecordByUser( HttpServletRequest request )
	{
		
		return "";
	}
	
	// 按审批状态查询
	@RequestMapping( "/getEhdByIscheck" )
	public String getEhdByIscheck( HttpServletRequest request )
	{
		int ischeck = Integer.parseInt( request.getParameter( "ischeck" ) );
		HttpSession sess = request.getSession( false );
		int usid = Integer.parseInt( sess.getAttribute( "lineid" ) + "" );
		
		String sql = "select a.lineid,b.employname,d1.deptname d1,d2.deptname d2,p1.positionname p1,d3.deptname d3,rs1.compayname||' > '||rs1.deptaddress rs1,d11.deptname d11,d12.deptname d12,p2.positionname p2,d13.deptname d13,rs2.compayname||' > '||rs2.deptaddress rs2,a.ischeck  "
		        + "from employrecordcheck a,employrecord b,smuser sm, Dept d1,dept d2,position p1,dept d3,deptaddress rs1, dept d11,dept d12,position p2,dept d13,deptaddress rs2 "
		        + "where a.recordid = b.lineid and a.checkusid = sm.lineid "
		        + "and  a.bfcompid = d1.lineid  and  a.bfdeptid = d2.lineid  "
		        + "and a.bfposid = p1.lineid  and a.bfofcompid = d3.lineid and a.bfdeptadresid = rs1.lineid "
		        + "and a.nowcompid = d11.lineid and a.nowdeptid = d12.lineid "
		        + "and a.nowposid = p2.lineid  and a.nowofcompid = d13.lineid and a.nowdeptadresid = rs2.lineid "
		        + "and a.status>=0 and a.checkusid=" + usid + " and a.ischeck=" + ischeck;
		
		String countSQL = "";
		int beginPos = sql.toLowerCase().indexOf( "from" );
		if ( beginPos != - 1 )
		{
			countSQL = "select count(*) " + sql.substring( beginPos );
		}
		
		System.out.println( " 档案申请 审批 " + sql );
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
		request.setAttribute( "id" , "a6" );
		request.setAttribute( "freshenParam" , "yes" );
		
		request.setAttribute( "returnPath" ,
		        "EmployrecordCheckServlet/getEhdByIscheck?op=1&ischeck=" + ischeck );
		/*	request.getRequestDispatcher("oa_employholiday/ehdbyis.jsp").forward(
					request, response);
			*/
		return "oa_employcordCheck/cordcheckBychecklist";
	}
	
	@RequestMapping( "/getUpdatePage" )
	public String getUpdatePage( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		
		String sql = "select a.lineid,b.employname,d1.deptname d1,d2.deptname d2,p1.positionname p1,d3.deptname d3,rs1.compayname||' > '||rs1.deptaddress rs1,d11.deptname d11,d12.deptname d12,p2.positionname p2,d13.deptname d13,rs2.compayname||' > '||rs2.deptaddress rs2,a.ischeck ,a.checkremaks, e2.employname e2  "
		        + "from employrecordcheck a,employrecord b,smuser sm, Dept d1,dept d2,position p1,dept d3,deptaddress rs1, dept d11,dept d12,position p2,dept d13,deptaddress rs2,employrecord e2 "
		        + "where a.recordid = b.lineid and a.checkusid = sm.lineid and sm.recordid=e2.lineid "
		        + "and  a.bfcompid = d1.lineid  and  a.bfdeptid = d2.lineid  "
		        + "and a.bfposid = p1.lineid  and a.bfofcompid = d3.lineid and a.bfdeptadresid = rs1.lineid "
		        + "and a.nowcompid = d11.lineid and a.nowdeptid = d12.lineid "
		        + "and a.nowposid = p2.lineid  and a.nowofcompid = d13.lineid and a.nowdeptadresid = rs2.lineid  "
		        + "and a.lineid =" + id;
		List emplist = empovertimeService.getDateBySqlQuery( sql , 0 , 0 );
		Map< Integer , String > stamap = SystemStatus.getEmpSystemStatus();
		request.setAttribute( "stamap" , stamap );
		request.setAttribute( "ehd" , emplist.get( 0 ) );
		return "oa_employcordCheck/update";
	}
	
	@RequestMapping( "/getEhdByid" )
	public String getEhdByid( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		
		String sql = "select a.lineid,b.employname,d1.deptname d1 ,d2.deptname d2 ,p1.positionname p1,"
		        + "d3.deptname d3,rs1.compayname||' > '||rs1.deptaddress rs1,d11.deptname d11,d12.deptname d12,p2.positionname p2,"
		        + "d13.deptname d13,rs2.compayname||' > '||rs2.deptaddress rs2,a.ischeck ,a.checkremaks, e2.employname e2  "
		        + "from employrecordcheck a,employrecord b,smuser sm, Dept d1,dept d2,position p1,dept d3,deptaddress rs1, dept d11,dept d12,position p2,dept d13,deptaddress rs2 ,employrecord e2 "
		        + "where a.recordid = b.lineid and a.checkusid = sm.lineid and sm.recordid=e2.lineid "
		        + "and  a.bfcompid = d1.lineid  and  a.bfdeptid = d2.lineid  "
		        + "and a.bfposid = p1.lineid  and a.bfofcompid = d3.lineid and a.bfdeptadresid = rs1.lineid "
		        + "and a.nowcompid = d11.lineid and a.nowdeptid = d12.lineid "
		        + "and a.nowposid = p2.lineid  and a.nowofcompid = d13.lineid and a.nowdeptadresid = rs2.lineid  "
		        + "and a.lineid =" + id;
		
		List emplist = empovertimeService.getDateBySqlQuery( sql , 0 , 0 );
		Map< Integer , String > stamap = SystemStatus.getEmpSystemStatus();
		request.setAttribute( "stamap" , stamap );
		request.setAttribute( "ehd" , emplist.get( 0 ) );
		return "oa_employcordCheck/getbyid";
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
		EmployrecordCheck cordcheck = recordCheckService.getEmployrecordCheckById( id );
		cordcheck.setCheckDate( new Date() );
		cordcheck.setCheckremaks( checkremarks );
		cordcheck.setIscheck( ischeck );
		recordCheckService.updateEmployrecordCheck( cordcheck );
		
		if ( ischeck == 2 )
		{
			Employrecord emr = emrService.getEmrById( cordcheck.getRecordid() );
			Dept d = deptService.getDeptById( cordcheck.getNowDeptid() );
			DeptAddress addre = deptService.getDeptAddressByID( cordcheck
			        .getNowDeptAdresid() );
			
			emr.setDept( d );
			emr.setPositionid( cordcheck.getNowPosid() );
			emr.setDeptaddress( addre );
			emrService.updateEmr( emr );
			
			// 发送邮件
			deptService.sendMails( cordcheck , 6 );
			
		}
		
		return "redirect:/EmployrecordCheckServlet/getEhdByIscheck?op=1&ischeck=0";
	}
	
	@RequestMapping( "/del" )
	public String del( HttpServletRequest request , HttpServletResponse response )
	        throws ServletException , IOException
	{
		
		int id = Integer.parseInt( request.getParameter( "id" ) );
		EmployrecordCheck cordcheck = recordCheckService.getEmployrecordCheckById( id );
		cordcheck.setStatus( - 1 );
		recordCheckService.updateEmployrecordCheck( cordcheck );
		
		return "redirect:/EmployrecordCheckServlet/getEhdByIscheck?op=1&ischeck=0";
	}
	
	// 获取该用户申请的档案修改记录 未删除的
	@RequestMapping( "/getEhdByChecked" )
	public String getEhdByChecked( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		int recordid = Integer.parseInt( session.getAttribute( "recordid" ) + "" );
		int usid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		
		String sql = "select a.lineid,b.employname,d1.deptname d1 ,d2.deptname d2 ,p1.positionname p1,"
		        + "d3.deptname d3,rs1.compayname||' > '||rs1.deptaddress rs1,d11.deptname d11,d12.deptname d12,p2.positionname p2,"
		        + "d13.deptname d13,rs2.compayname||' > '||rs2.deptaddress rs2,a.ischeck ,a.checkremaks, e2.employname e2  "
		        + "from employrecordcheck a,employrecord b,smuser sm, Dept d1,dept d2,position p1,dept d3,deptaddress rs1, dept d11,dept d12,position p2,dept d13,deptaddress rs2 ,employrecord e2 "
		        + "where a.recordid = b.lineid and a.checkusid = sm.lineid and sm.recordid=e2.lineid "
		        + "and  a.bfcompid = d1.lineid  and  a.bfdeptid = d2.lineid  "
		        + "and a.bfposid = p1.lineid  and a.bfofcompid = d3.lineid and a.bfdeptadresid = rs1.lineid "
		        + "and a.nowcompid = d11.lineid and a.nowdeptid = d12.lineid "
		        + "and a.nowposid = p2.lineid  and a.nowofcompid = d13.lineid and a.nowdeptadresid = rs2.lineid  "
		        + "and a.status>=0 and a.userid =" + usid;
		
		String countSQL = "";
		int beginPos = sql.toLowerCase().indexOf( "from" );
		if ( beginPos != - 1 )
		{
			countSQL = "select count(*) " + sql.substring( beginPos );
		}
		
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = empovertimeService.getCountBySQL( countSQL );
		pages = ehdService.getpages( count , 5 );
		page = ServiceUtil.getPage( p , pages );
		
		List emplist = empovertimeService.getDateBySqlQuery( sql , 5 , page );
		Map< Integer , String > stamap = SystemStatus.getEmpSystemStatus();
		request.setAttribute( "stamap" , stamap );
		request.setAttribute( "emplist" , emplist );
		System.out.println( "emplist siez " + emplist.size() );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "id" , "a6" );
		request.setAttribute( "returnPath" ,
		        "EmployrecordCheckServlet/getEhdByChecked?op=1&id=a6" );
		return "oa_employcordCheck/cordcheckBylist";
	}
	
	@RequestMapping( "/getNewcordCheck" )
	public String getNewcordCheck( HttpServletRequest request )
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
			
			sql = "select a.lineid,b.employname,d1.deptname d1 ,d2.deptname d2 ,p1.positionname p1,"
			        + "d3.deptname d3,rs1.compayname||' > '||rs1.deptaddress rs1,d11.deptname d11,d12.deptname d12,p2.positionname p2,"
			        + "d13.deptname d13,rs2.compayname||' > '||rs2.deptaddress rs2,a.ischeck ,a.checkremaks, e2.employname e2  "
			        + "from employrecordcheck a,employrecord b,smuser sm, Dept d, Dept d1,dept d2,position p1,dept d3,deptaddress rs1, dept d11,dept d12,position p2,dept d13,deptaddress rs2 ,employrecord e2 "
			        + "where a.recordid = b.lineid and b.deptid=d.lineid and a.checkusid = sm.lineid and sm.recordid=e2.lineid "
			        + "and  a.bfcompid = d1.lineid  and  a.bfdeptid = d2.lineid  "
			        + "and a.bfposid = p1.lineid  and a.bfofcompid = d3.lineid and a.bfdeptadresid = rs1.lineid "
			        + "and a.nowcompid = d11.lineid and a.nowdeptid = d12.lineid "
			        + "and a.nowposid = p2.lineid  and a.nowofcompid = d13.lineid and a.nowdeptadresid = rs2.lineid  "
			        + "and a.ischeck=2 and a.hrstatus=0 and getCompanyIDByDeptID(d.lineid) in ("
			        + depts + ") ";
			
			List emplist = empovertimeService.getDateBySqlQuery( sql , 0 , 0 );
			Map< Integer , String > stamap = SystemStatus.getEmpSystemStatus();
			request.setAttribute( "stamap" , stamap );
			request.setAttribute( "emplist" , emplist );
		}
		
		return "oa_employcordCheck/showNewCheck";
	}
	
	@RequestMapping( "/updateEmpSatByID" )
	public String updateEmpSatByID( HttpServletRequest request ,
	        HttpServletResponse response ) throws ServletException , IOException
	{
		
		int id = Integer.parseInt( request.getParameter( "id" ) );
		EmployrecordCheck cordcheck = recordCheckService.getEmployrecordCheckById( id );
		cordcheck.setHrstatus( 1 );
		recordCheckService.updateEmployrecordCheck( cordcheck );
		
		return "redirect:/EmployrecordCheckServlet/getNewcordCheck";
	}
	
}
