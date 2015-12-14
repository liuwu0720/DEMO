package clt.com.cn.controller.sm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import clt.com.cn.helps.SystemStatus;
import clt.com.cn.model.entity.Privilege;
import clt.com.cn.service.IEmployholidayService;
import clt.com.cn.service.IEmployrecordService;
import clt.com.cn.service.IModuleService;

@Controller
@RequestMapping( "/ModuleServlet" )
public class ModuleController
{
	
	@Autowired
	private IModuleService moduleService;
	@Autowired
	private IEmployrecordService emrService;
	@Autowired
	private IEmployholidayService empholidayService;
	
	@RequestMapping( "/goLef" )
	public String goLef( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		int userid = ( Integer ) session.getAttribute( "lineid" );
		int deptid = ( Integer ) session.getAttribute( "deptid" );
		// List module=moduleService.getAllModule(userid);
		List< Privilege > module = moduleService.getModuleByUserId( userid );
		int count = moduleService.getCount( deptid , userid );
		JSONArray jsarr = new JSONArray();
		for ( Privilege p : module )
		{
			JSONObject obj = new JSONObject();
			obj.element( "id" , p.getLineid() );
			obj.element( "pId" , p.getPid() );
			obj.element( "name" , p.getPrivilegename() );
			obj.element( "title" , p.getTitle() );
			obj.element( "url" , p.getUrlpath() );
			obj.element( "target" , "mainFrame" );
			jsarr.add( obj );
		}
		System.out.println( "jsarr size " + jsarr.size() );
		String s1 = "{id:1, pId:0, name:\"test1\" , open:true}";
		String s2 = "{id:2, pId:1, name:\"test2\",url:\"www.baidu.com\"}";
		String s3 = "{id:3, pId:1, name:\"test3\",url:\"www.baidu.com\"}";
		String s4 = "{id:4, pId:2, name:\"test4\",url:\"www.baidu.com\"}";
		List< String > lstTree = new ArrayList< String >();
		lstTree.add( s1 );
		lstTree.add( s2 );
		lstTree.add( s3 );
		lstTree.add( s4 );
		
		System.out.println( jsarr.toString() );
		
		request.setAttribute( "module" , module );
		request.setAttribute( "count" , count );
		request.setAttribute( "meaujson" , jsarr.toString() );
		request.setAttribute( "meaujson2" , JSONArray.fromObject( lstTree ).toString() );
		System.out.println( jsarr.toString() );
		System.out.println( JSONArray.fromObject( lstTree ).toString() );
		return "left";
	}
	
	@RequestMapping( "/gorightBefore" )
	public String gorightBefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		int userid = ( Integer ) session.getAttribute( "lineid" );
		int deptid = ( Integer ) session.getAttribute( "deptid" );
		
		String params = "";
		int newEmpcount = 0 , newEmpHolidayCount = 0 , newcordChckcount = 0;
		List< String > tables = new ArrayList< String >();
		tables.add( "Employholiday" );
		tables.add( "Employout" );
		tables.add( "Employovertime" );
		tables.add( "Employattendance" );
		tables.add( "Employtrip" );
		tables.add( "Employrecruitment" );
		
		Map noticemaps = new HashMap< Integer , String >();
		
		String s = "select getCompanyIDByDeptID(" + deptid + ") did from dual ";
		List complist = empholidayService.getpageDateBySqlQuery( s , 0 , 0 );
		String compid = ( String ) complist.get( 0 );
		System.out.println( "deptid " + deptid + " 所属公司id " + compid );
		
		boolean b = SystemStatus.getisHr( compid + "," + deptid );
		// 如果部门等于 人力行政 或者 测试部门 如果部门等于总部 人力部门 则获取所有的请假 入职信息 如果不是则验证是否是分公司的人力部门
		// 获取属于该分公司的请假入职信息
		
		// 获取该员工对应的 分公司id
		String sql = "  select d.deptid from userSchedule d where d.userid=" + userid;
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
			
			System.out.println( "depts >> " + depts );
			sql = " select count(*) from employrecord emp,dept d,position po where emp.deptid=d.lineid and emp.positionid=po.lineid and getCompanyIDByDeptID(d.lineid) in ("
			        + depts + ")  and emp.hrstatus=0  ";
			System.out.println( "sqllll" + sql );
			newEmpcount = emrService.getCountBySQL( sql );
			
			for ( int i = 0 ; i < tables.size() ; i++ )
			{
				String hodaysql = " select count(*) from "
				        + tables.get( i )
				        + " emp,dept d where "
				        + " d.lineid = emp.deptid and getCompanyIDByDeptID(d.lineid) in ("
				        + depts + " ) and emp.ischeck=2 and emp.hrstatus=0 ";
				int count = empholidayService.getCountBySQL( hodaysql );
				if ( count > 0 )
				{
					noticemaps.put( ( i + 1 ) , count );
					
				}
			}
			
			// 获取档案申请记录 已审批通过的
			sql = "   select count(*) coun from employrecordcheck e,employrecord emp ,dept d where e.recordid=emp.lineid and emp.deptid=d.lineid and getCompanyIDByDeptID(d.lineid) in ("
			        + depts + ") and   e.ischeck=2 and e.status>=0 and e.hrstatus=0 ";
			newcordChckcount = emrService.getCountBySQL( sql );
			if ( newcordChckcount > 0 )
			{
				noticemaps.put( 7 , newcordChckcount );
				
			}
			
		}
		
		/*else if(deptid==SystemStatus.itdept)
		{
			params = "itprepare";
			String sql = " select count(*) from employrecord emp,dept d,position po where emp.deptid=d.lineid and emp.positionid=po.lineid and emp.status=0 and emp.itstatus=0 ";
			System.out.println("sqllll"+sql);
			newEmpcount = emrService.getCountBySQL(sql);
		
		}*/
		Map maps = new HashMap< Integer , String >();
		
		for ( int i = 0 ; i < tables.size() ; i++ )
		{
			sql = "select count(*)  from "
			        + tables.get( i )
			        + " emp where emp.ischeck=0 and (emp.checkuserid="
			        + userid
			        + " or emp.checkuserid in(select empdit.certigieruid from employaccredit empdit where empdit.authorizeruid="
			        + userid + " )) ";
			int count = emrService.getCountBySQL( sql );
			if ( count > 0 )
			{
				maps.put( ( i + 1 ) , count );
			}
			
		}
		
		// 档案待审批数据
		String sql1 = " select count(*) coun from employrecordcheck e,smuser sm  where e.checkusid=sm.lineid and e.status>=0  and e.ischeck=0 and sm.lineid="
		        + userid;
		int count = emrService.getCountBySQL( sql1 );
		if ( count > 0 )
		{
			maps.put( 7 , count );
		}
		
		request.setAttribute( "newEmpcount" , newEmpcount );
		request.setAttribute( "newEmpHolidayCount" , newEmpHolidayCount );
		request.setAttribute( "maps" , maps );
		request.setAttribute( "noticemaps" , noticemaps );
		request.setAttribute( "noticeSta" , SystemStatus.getnoticeSta() );
		request.setAttribute( "stamaps" , SystemStatus.getAttendanceSta() );
		
		request.setAttribute( "params" , params );
		return "right";
	}
	
}
