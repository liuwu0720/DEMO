package clt.com.cn.controller.dept;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import clt.com.cn.model.entity.Dept;
import clt.com.cn.model.entity.DeptMapping;
import clt.com.cn.model.entity.ManagerApproval;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.model.entity.UserSchedule;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IUserService;

@Controller
@RequestMapping( "/DeptServlet" )
public class DeptServletController
{
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IUserService userService;
	
	public IDeptService getDeptService()
	{
		return deptService;
	}
	
	public void setDeptService( IDeptService deptService )
	{
		this.deptService = deptService;
	}
	
	@RequestMapping( "/getAllDept" )
	public String getAllDept( HttpServletRequest request )
	{
		List< Dept > dept = deptService.getAllDept();
		request.setAttribute( "dept" , dept );
		return "oa_dept/alldept";
		/*request.getRequestDispatcher("oa_dept/alldept.jsp").forward(request,
				response);*/
	}
	
	// 上传文件时得到部门
	@RequestMapping( "/getAllDeptUpload" )
	public String getAllDeptUpload( HttpServletRequest request )
	{
		
		String sql = " select dt.lineid,dt.pid,dt.deptname from dept dt"
		        + " start with dt.lineid = 1 " + " connect by Prior dt.lineid = dt.pid "
		        + " order by dt.lineid asc ";
		List< String[] > hqlist = deptService.getDateBySqlQuery( sql , 0 , 0 );
		
		List< Dept > dept = deptService.getAllDept();
		String sql2 = " select d.lineid,d.pid,d.deptname from Dept d where d.active=1 ";
		List< Object[] > allist = deptService.getDateBySqlQuery( sql2 , 0 , 0 );
		JSONArray arr = new JSONArray();
		for ( Object[] s : allist )
		{
			JSONObject json = new JSONObject();
			json.element( "lineid" , s[0] );
			json.element( "pid" , s[1] );
			json.element( "deptname" , s[2] );
			arr.add( json );
		}
		
		request.setAttribute( "deptlist" , arr.toString() );
		
		return "oa_dept/alldeptupload";
		/*request.getRequestDispatcher("oa_dept/alldeptupload.jsp").forward(
				request, response);*/
	}
	
	// 分页查询
	@RequestMapping( "/getAllDeptByPage" )
	public String getAllDeptByPage( HttpServletRequest request )
	{
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = deptService.getCount();
		pages = deptService.getpages( count , 5 );
		// System.out.println(p);
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
		
		List< Dept > dept = deptService.getAllDeptByPage( page );
		request.setAttribute( "returnPath" , "DeptServlet/getAllDeptByPage?op=1" );
		request.setAttribute( "dept" , dept );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		
		return "oa_dept/alldept";
		/*request.getRequestDispatcher("oa_dept/alldept.jsp").forward(request,
				response);*/
	}
	
	@RequestMapping( "/getDeptTree" )
	public String getDeptTree( HttpServletRequest request )
	{
		List< Dept > dept = deptService.getAllDept();
		request.setAttribute( "dept" , dept );
		
		return "oa_dept/depttree";
		/*request.getRequestDispatcher("oa_dept/depttree.jsp").forward(request,
				response);*/
	}
	
	@RequestMapping( "/getAddpage" )
	public String getAddpage( HttpServletRequest request , HttpServletResponse response )
	        throws ServletException , IOException
	{
		List< Dept > dept = deptService.getAllDept();
		String sql = "select sm.lineid,emp.employname from smuser sm,employrecord emp where sm.recordid=emp.lineid  and emp.status>0 order by emp.employname ";
		List uslist = userService.getDateBySqlQuery( sql , 0 , 0 );
		
		request.setAttribute( "users" , uslist );
		request.setAttribute( "dept" , dept );
		
		return "oa_dept/adddept";
		/*request.getRequestDispatcher("oa_dept/adddept.jsp").forward(request,
				response);*/
	}
	
	@RequestMapping( "/add" )
	public String addDept( HttpServletRequest request , HttpServletResponse response )
	        throws ServletException , IOException
	{
		HttpSession session = request.getSession( true );
		String user = ( String ) session.getAttribute( "uname" );
		String deptname = request.getParameter( "deptname" );
		int pid = Integer.parseInt( request.getParameter( "pid" ) );
		int managerSel = Integer.parseInt( request.getParameter( "managerSel" ) );
		Smuser sm = userService.getUserById( managerSel );
		
		Dept dpt = deptService.getDeptById( pid );
		// System.out.println(user+"--"+deptname+"--"+contactname+"--"+tel+"--"+email+"--"+pid);
		Dept dept = new Dept();
		dept.setDeptname( deptname );
		dept.setManageruser( sm );
		dept.setPid( pid );
		dept.setUserno( user );
		dept.setActive( 1 );
		dept.setLevel( dpt.getLevel() + 1 );
		
		deptService.addDept( dept );
		
		return "redirect:/DeptServlet/getAllDeptByPage";
		// this.getAllDeptByPage(request, response);
	}
	
	@RequestMapping( "/del" )
	public String deleteDeptById( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		System.out.println( id );
		deptService.delDeptById( id );
		
		return "redirect:/DeptServlet/getAllDeptByPage";
		// this.getAllDeptByPage(request, response);
	}
	
	@RequestMapping( "/getUpdateDeptPage" )
	public String getUpdateDeptPage( HttpServletRequest request )
	{
		// TODO Auto-generated method stub
		int id = Integer.parseInt( request.getParameter( "uid" ) );
		Dept dept = deptService.getDeptById( id );
		Dept paddept = deptService.getDeptById( dept.getPid() );
		
		// List< Dept > deptname = deptService.getAllDept();
		// System.out.println(id+"--"+dept.getDeptname());
		
		String sql = "select sm.lineid,emp.employname from smuser sm,employrecord emp where sm.recordid=emp.lineid and emp.status>0 order by emp.employname ";
		List uslist = userService.getDateBySqlQuery( sql , 0 , 0 );
		
		request.setAttribute( "users" , uslist );
		request.setAttribute( "dept" , dept );
		request.setAttribute( "parDept" , paddept );
		// request.setAttribute( "deptname" , deptname );
		
		return "oa_dept/updatedeptpage";
		/*request.getRequestDispatcher(".jsp").forward(
				request, response);*/
	}
	
	@RequestMapping( "/updateDept" )
	public String updateDept( HttpServletRequest request )
	{
		// TODO Auto-generated method stub
		HttpSession session = request.getSession( true );
		String user = ( String ) session.getAttribute( "uname" );
		int lineid = Integer.parseInt( request.getParameter( "lineid" ) );
		int active = Integer.parseInt( request.getParameter( "active" ) );
		String deptname = request.getParameter( "deptname" );
		int managerSel = Integer.parseInt( request.getParameter( "managerSel" ) );
		Smuser sm = userService.getUserById( managerSel );
		
		Dept dept = deptService.getDeptById( lineid );
		dept.setUserno( user );
		dept.setManageruser( sm );
		dept.setActive( active );
		dept.setDeptname( deptname );
		dept.setCurrdate( new Date() );
		
		deptService.updateDept( dept );
		// this.getAllDeptByPage(request, response);
		return "redirect:/DeptServlet/getAllDeptByPage";
	}
	
	// alldept.jsp中的查询
	@RequestMapping( "/getDeptByName" )
	public String getDeptByName( HttpServletRequest request )
	{
		// request.setCharacterEncoding("utf-8");
		// response.setCharacterEncoding("utf-8");
		String deptname = request.getParameter( "deptname" );
		if ( deptname.equals( "搜索信息（部门名称）" ) )
		{
			// this.getAllDeptByPage(request, response);
			return "redirect:/DeptServlet/getAllDeptByPage";
		}
		else
		{
			List< Dept > dept = deptService.DeptInfoById( deptname );
			request.setAttribute( "dept" , dept );
			/*request.getRequestDispatcher("oa_dept/deptname.jsp").forward(
					request, response);*/
			return "oa_dept/deptname";
		}
	}
	
	//
	@RequestMapping( "/getDeptByName2" )
	public String getDeptByName2( HttpServletRequest request )
	{
		// request.setCharacterEncoding("utf-8");
		// response.setCharacterEncoding("utf-8");
		String deptname = request.getParameter( "deptname" );
		try
		{
			deptname = new String( deptname.getBytes( "iso8859-1" ) , "utf-8" );
		}
		catch ( UnsupportedEncodingException e )
		{
			e.printStackTrace();
		}
		if ( deptname.equals( "搜索信息(部门名称)" ) )
		{
			// this.getAllDept(request, response);
			return "redirect:/DeptServlet/getAllDept";
		}
		else
		{
			List< Dept > dept = deptService.DeptInfoById( deptname );
			request.setAttribute( "dept" , dept );
			/*request.getRequestDispatcher("oa_dept/deptbyname.jsp").forward(
					request, response);*/
			return "oa_dept/deptbyname";
		}
	}
	
	/**
	 * 
	 * @Description: 获得总部的部门信息
	 * @param request
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-17 下午4:18:58
	 */
	@RequestMapping( "/gethqDepts" )
	public String gethqDepts( HttpServletRequest request )
	{
		String sql = " select dt.lineid,dt.deptname from dept dt"
		        + " start with dt.lineid = 1 " + " connect by Prior dt.lineid = dt.pid "
		        + " order by dt.lineid asc ";
		List hqlist = deptService.getDateBySqlQuery( sql , 0 , 0 );
		
		String sql2 = " select d.lineid,d.pid,d.deptname from Dept d";
		List< Object[] > allist = deptService.getDateBySqlQuery( sql2 , 0 , 0 );
		JSONArray arr = new JSONArray();
		for ( Object[] s : allist )
		{
			JSONObject json = new JSONObject();
			json.element( "lineid" , s[0] );
			json.element( "pid" , s[1] );
			json.element( "deptname" , s[2] );
			arr.add( json );
		}
		
		request.setAttribute( "hqlist" , hqlist );
		request.setAttribute( "alldeptlist" , arr.toString() );
		return "oa_dept/deptMapping";
		
	}
	
	@RequestMapping( "/getLowerByDeptID" )
	@ResponseBody
	public String getLowerByDeptID( HttpServletRequest request )
	{
		int deptid = Integer.parseInt( request.getParameter( "deptid" ) );
		
		String sql = " select de.lineid,de.pid,de.deptname from dept de where de.lineid in ( select d.deptmappid from dept_mapping d where d.deptid="
		        + deptid + " ) ";
		List< Object[] > hqlist = deptService.getDateBySqlQuery( sql , 0 , 0 );
		
		JSONArray arr = new JSONArray();
		for ( Object[] s : hqlist )
		{
			JSONObject json = new JSONObject();
			json.element( "lineid" , s[0] );
			json.element( "pid" , s[1] );
			json.element( "deptname" , s[2] );
			arr.add( json );
		}
		
		return arr.toString();
		
	}
	
	@RequestMapping( "/saveDeptMapping" )
	public String saveDeptMapping( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		int usid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		Smuser user = userService.getUserById( usid );
		int deptid = Integer.parseInt( request.getParameter( "deptid" ) );
		String deptmappidds = request.getParameter( "deptmappids" );
		
		String sql = " delete from dept_mapping d where d.deptid =" + deptid;
		deptService.execSQL( sql );
		
		String[] strs = deptmappidds.split( "," );
		for ( String str : strs )
		{
			DeptMapping d = new DeptMapping();
			d.setDeptid( deptid );
			d.setDeptmappid( Integer.parseInt( str ) );
			d.setOptime( new Date() );
			d.setOpuser( user );
			deptService.addDeptmapping( d );
		}
		
		return "redirect:/DeptServlet/gethqDepts";
		
	}
	
	// 部门人员调整 将该部门的人全部移到新部门 Before
	@RequestMapping( "/DeptTurnover" )
	public String DeptTurnover( HttpServletRequest request )
	{
		List< Dept > delist = deptService.getAllDept();
		request.setAttribute( "deptlist" , delist );
		return "oa_dept/DeptTurnover";
		
	}
	
	@RequestMapping( "/getDeptPersonByID" )
	@ResponseBody
	public String getDeptPersonByID( HttpServletRequest request , HttpServletResponse resp )
	{
		int deptid = Integer.parseInt( request.getParameter( "deptid" ) );
		
		String sql = " select e.employname from employrecord e where e.status>0 and e.deptid="
		        + deptid;
		List hqlist = deptService.getDateBySqlQuery( sql , 0 , 0 );
		
		String names = "";
		JSONObject ob = new JSONObject();
		for ( int i = 0 ; i < hqlist.size() ; i++ )
		{
			String name = hqlist.get( i ).toString();
			if ( i > 0 && i % 4 == 0 )
			{
				names += name.toString() + " \n ";
			}
			else
			{
				names += name.toString() + "  ";
			}
			
		}
		ob.element( "names" , names );
		System.out.println( "names " + names );
		return ob.toString();
		
	}
	
	// 部门人员调整 将该部门的人全部移到新部门
	@RequestMapping( "/saveDeptPerson" )
	@ResponseBody
	public String saveDeptPerson( HttpServletRequest request )
	{
		// lid 为需要调整的部门 rid调整之后的部门 将A部门的人修改为B部门
		int lid = Integer.parseInt( request.getParameter( "lid" ) );
		int rid = Integer.parseInt( request.getParameter( "rid" ) );
		
		String sql = " update employrecord e set e.deptid=" + rid + " where e.deptid="
		        + lid;
		JSONObject obj = new JSONObject();
		try
		{
			
			deptService.execSQL( sql );
			obj.element( "result" , "ok" );
		}
		catch ( Exception e )
		{
			System.out.println( e.getMessage() );
			obj.element( "result" , e.getMessage() );
		}
		
		return obj.toString();
		
	}
	
	@RequestMapping( "/getSchedulebef" )
	public String getSchedulebef( HttpServletRequest request , HttpServletResponse resp )
	{
		
		String sql = "select sm.lineid,emp.employname from smuser sm,employrecord emp where sm.recordid=emp.lineid and emp.status>0 order by emp.employname desc ";
		List< Object[] > uslist = userService.getDateBySqlQuery( sql , 0 , 0 );
		JSONArray jsarr = new JSONArray();
		
		for ( Object[] objs : uslist )
		{
			JSONObject obj = new JSONObject();
			obj.element( "id" , objs[0] );
			obj.element( "uname" , objs[1] );
			jsarr.add( obj );
		}
		
		String jsstr = jsarr.toString();
		System.out.println( "jsstr " + jsstr );
		
		String sql2 = " select d.lineid,d.pid,d.deptname from Dept d where d.pid=0 and d.active=1 "
		        + "	union all select d.lineid,d.pid,d.deptname from Dept d where  d.lineid=0 ";
		List< Object[] > allist = deptService.getDateBySqlQuery( sql2 , 0 , 0 );
		JSONArray arr = new JSONArray();
		for ( Object[] s : allist )
		{
			JSONObject json = new JSONObject();
			json.element( "lineid" , s[0] );
			json.element( "pid" , s[1] );
			json.element( "deptname" , s[2] );
			arr.add( json );
		}
		
		request.setAttribute( "uslist" , jsarr.toString() );
		request.setAttribute( "deptlist" , arr.toString() );
		return "oa_dept/saveScheduleByusid";
		
	}
	
	@RequestMapping( "/getScheduleByusID" )
	@ResponseBody
	public String getScheduleByusID( HttpServletRequest request )
	{
		int usid = Integer.parseInt( request.getParameter( "usid" ) );
		
		String sql = " select de.lineid,de.pid,de.deptname from dept de where de.lineid in ( select d.deptid from userSchedule d where d.userid="
		        + usid + " ) ";
		List< Object[] > hqlist = deptService.getDateBySqlQuery( sql , 0 , 0 );
		
		JSONArray arr = new JSONArray();
		for ( Object[] s : hqlist )
		{
			JSONObject json = new JSONObject();
			json.element( "lineid" , s[0] );
			json.element( "pid" , s[1] );
			json.element( "deptname" , s[2] );
			arr.add( json );
		}
		System.out.println( " 获取 人 对应待办公司 事项  " + sql );
		return arr.toString();
		
	}
	
	// 保存 员工和对应公司待办的映射
	@RequestMapping( "/saveUserSchedule" )
	public String saveUserSchedule( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		int opusid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		Smuser user = userService.getUserById( opusid );
		int usid = Integer.parseInt( request.getParameter( "usnameID" ) );
		String deptmappidds = request.getParameter( "deptmappids" );
		System.out.println( "saveUserSchedule " + usid + " >>> " + deptmappidds );
		
		String sql = " delete from userSchedule d where d.userid =" + usid;
		deptService.execSQL( sql );
		if ( deptmappidds != null && deptmappidds != "" )
		{
			String[] strs = deptmappidds.split( "," );
			for ( String str : strs )
			{
				UserSchedule d = new UserSchedule();
				d.setUserid( usid );
				d.setDeptid( Integer.parseInt( str ) );
				d.setOptime( new Date() );
				d.setOpuser( user );
				userService.saveUserSchedule( d );
			}
		}
		
		return "redirect:/DeptServlet/getSchedulebef";
		
	}
	
	@RequestMapping( "/getManagerApprove" )
	public String getManagerApprove( HttpServletRequest request , HttpServletResponse resp )
	{
		
		String sql = "select sm.lineid,emp.employname from smuser sm,employrecord emp where sm.recordid=emp.lineid and emp.status>0 order by emp.employname desc ";
		List< Object[] > uslist = userService.getDateBySqlQuery( sql , 0 , 0 );
		JSONArray jsarr = new JSONArray();
		
		for ( Object[] objs : uslist )
		{
			JSONObject obj = new JSONObject();
			obj.element( "id" , objs[0] );
			obj.element( "uname" , objs[1] );
			jsarr.add( obj );
		}
		
		String jsstr = jsarr.toString();
		System.out.println( "jsstr " + jsstr );
		
		String sql2 = " select d.lineid,d.pid,d.deptname from Dept d where d.active=1 ";
		List< Object[] > allist = deptService.getDateBySqlQuery( sql2 , 0 , 0 );
		JSONArray arr = new JSONArray();
		for ( Object[] s : allist )
		{
			JSONObject json = new JSONObject();
			json.element( "lineid" , s[0] );
			json.element( "pid" , s[1] );
			json.element( "deptname" , s[2] );
			arr.add( json );
		}
		
		request.setAttribute( "uslist" , jsarr.toString() );
		request.setAttribute( "deptlist" , arr.toString() );
		return "oa_dept/saveManagerApprove";
		
	}
	
	@RequestMapping( "/getManagerApproveByID" )
	@ResponseBody
	public String getManagerApproveByID( HttpServletRequest request )
	{
		int deptid = Integer.parseInt( request.getParameter( "deptid" ) );
		
		String sql = " select sm.lineid,sm.userno,d.deptname, emp.employname from smuser sm ,managerapproval app,employrecord emp,dept d where sm.recordid=emp.lineid and sm.lineid=app.smuserid and app.deptid=d.lineid and app.deptid="
		        + deptid + " ";
		System.out.println( "getManagerApproveByID   >>" + sql );
		List< Object[] > hqlist = deptService.getDateBySqlQuery( sql , 0 , 0 );
		
		JSONObject json = new JSONObject();
		if ( hqlist.size() > 0 )
		{
			json.element( "id" , hqlist.get( 0 )[0] );
			json.element( "uname" , hqlist.get( 0 )[3] );
			System.out.println( " 部门负责人对应审批  " + deptid + "  " + hqlist.get( 0 )[3] );
		}
		
		return json.toString();
		
	}
	
	// 保存 员工和对应公司待办的映射
	@RequestMapping( "/saveManagerApprove" )
	public String saveManagerApprove( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		int opusid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		Smuser user = userService.getUserById( opusid );
		
		int usid = Integer.parseInt( request.getParameter( "userID" ) );
		int deptid = Integer.parseInt( request.getParameter( "deptids" ) );
		System.out.println( "saveManagerApprove " + usid + " >>> " + deptid );
		
		if ( usid != 0 && deptid != 0 )
		{
			String sql = " delete from managerapproval d where d.deptid =" + deptid;
			deptService.execSQL( sql );
			
			ManagerApproval mana = new ManagerApproval();
			mana.setDeptID( deptid );
			mana.setOptime( new Date() );
			mana.setOpuser( user );
			mana.setSmuserID( usid );
			userService.saveManagerApproval( mana );
		}
		
		return "redirect:/DeptServlet/getManagerApprove";
		
	}
}
