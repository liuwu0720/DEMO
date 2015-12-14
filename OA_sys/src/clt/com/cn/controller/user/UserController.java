package clt.com.cn.controller.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Role;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.model.entity.SmuserToken;
import clt.com.cn.model.entity.UserApprove;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IEmployrecordService;
import clt.com.cn.service.IUserService;

/**
 * 操作员控制器
 * 
 * @author huangjx
 * 
 */
@Controller
@RequestMapping( "/UserServlet" )
public class UserController
{
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IEmployrecordService employrecordService;
	@Autowired
	private IEmployrecordService emrService;
	@Autowired
	private IDeptService deptService;
	
	public IUserService getUserService()
	{
		return userService;
	}
	
	public void setUserService( IUserService userService )
	{
		this.userService = userService;
	}
	
	@RequestMapping( "/openlogin" )
	public String openlogin()
	{
		return "login";
	}
	
	/**
	 * 登陆方法
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping( "/userLogin" )
	public String userLogin( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		
		String uname = request.getParameter( "uname" );
		String upass = request.getParameter( "upass" );
		String md5pass = userService.MD5( upass );
		Smuser user = userService.userLogin( uname , md5pass );
		// System.out.println(uname+"---"+upass);
		if ( user != null )
		{
			// Employrecord emr = userService.getEmrById(user.getRecordid());
			session.setAttribute( "deptid" , user.getEmployrecord().getDept().getLineid() );
			session.setAttribute( "recordid" , user.getEmployrecord().getLineid() );
			session.setAttribute( "lineid" , user.getLineid() );
			session.setAttribute( "uname" , uname );
			//UserSession.set( "uname" , uname );
			//UserSession.set( "empId" , user.getEmployrecord().getLineid() );
			return "frame";
			// request.getRequestDispatcher("frame.jsp").forward(request,
			// response);
		}
		else
		{
			// response.sendRedirect("login.jsp");
			return "login";
		}
	}
	
	// 查询用户是否存在
	@RequestMapping( "/ajaxCheckUser" )
	@ResponseBody
	public String ajaxCheckUser( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String uname = request.getParameter( "uname" );
		String upass = request.getParameter( "upass" );
		String md5pass = userService.MD5( upass );
		Smuser user = userService.userLogin( uname , md5pass );
		if ( user != null )
		{
			// Employrecord emr = userService.getEmrById(user.getRecordid());
			session.setAttribute( "deptid" , user.getEmployrecord().getDept().getLineid() );
			session.setAttribute( "recordid" , user.getEmployrecord().getLineid() );
			session.setAttribute( "lineid" , user.getLineid() );
			session.setAttribute( "uname" , uname );
			//UserSession.set( "uname" , uname );
			//System.out.println( "session = " + UserSession.get( "uname" ) );
			return "ok";
		}
		else
		{
			Smuser sm = userService.getUserByUserno( uname );
			if ( sm == null )
			{
				return "usernoError";
			}
			else
			{
				return "pwdError";
			}
			
		}
	}
	
	// 查询用户是否存在
	@RequestMapping( "/ajaxCheckUserNo" )
	@ResponseBody
	public String ajaxCheckUserNo( HttpServletRequest request )
	{
		
		String userno = request.getParameter( "userno" );
		String hql = " from Smuser sm where sm.active=1 and sm.userno='" + userno + "'";
		List< Smuser > smlist = userService.getUsersByCondition( hql , new Object[] {} );
		if ( smlist.size() > 0 )
		{
			return "usnoexits";
		}
		else
		{
			return "ok";
		}
		
	}
	
	// 查询用户是否存在
	@RequestMapping( "checkUser" )
	@ResponseBody
	public String checkUser( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String uname = request.getParameter( "uname" );
		
		Smuser user = userService.userName( uname );
		// System.out.println(uname+"---"+upass);
		if ( user != null )
		{
			// response.getWriter().print("error");
			return "error";
		}
		else
		{
			// response.getWriter().print("ok");
			return "ok";
		}
	}
	
	// 查询用户与密码
	@RequestMapping( "checkPass" )
	@ResponseBody
	public String checkPass( HttpServletRequest request )
	{
		String uname = request.getParameter( "uname" );
		String upass = request.getParameter( "upass" );
		String md5pass = userService.MD5( upass );
		Smuser user = userService.userLogin( uname , md5pass );
		// System.out.println("!!!!!!"+uname+" "+upass);
		if ( user != null )
		{
			// response.getWriter().print("ok");
			return "ok";
		}
		else
		{
			// response.getWriter().print("error");
			return "error";
		}
	}
	
	/**
	 * 添加用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping( "/addUser" )
	public String addUser( HttpServletRequest request )
	{
		String id = request.getParameter( "recordid" );
		if ( id == null )
		{
			id = "0";
		}
		int recordid = Integer.parseInt( id );
		String uname = request.getParameter( "uname" );
		String upass = request.getParameter( "upass" );
		String md5pass = userService.MD5( upass );
		Smuser u = new Smuser();
		Employrecord emp = employrecordService.getEmrById( recordid );
		u.setEmployrecord( emp );
		u.setUserno( uname );
		u.setPassword( md5pass );
		userService.addUser( u );
		// System.out.println(uname+"---"+upass);
		
		return "redirect:/UserServlet/getAllUsers";
		// this.getAllUsers(request);
	}
	
	@RequestMapping( "/getAllUsers" )
	public String getAllUsers( HttpServletRequest request )
	{
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = userService.getCount();
		pages = userService.getpages( count , 5 );
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
		
		List user = userService.getAllUsers( page );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "user" , user );
		request.setAttribute( "returnPath" , "UserServlet/getAllUsers?op=1" );
		return "user";
		// request.getRequestDispatcher("/user.jsp").forward(request, response);
	}
	
	@RequestMapping( "/deleteUserById" )
	public String deleteUserById( HttpServletRequest request )
	{
		int uid = Integer.parseInt( request.getParameter( "uid" ) );
		Smuser sm = userService.getUserById( uid );
		sm.setActive( 0 );
		userService.updateUser( sm );
		return "redirect:/UserServlet/getAllUsers";
		// return this.getAllUsers(request);
	}
	
	@RequestMapping( "/updateUser" )
	public String updateUser( HttpServletRequest request )
	{
		// TODO Auto-generated method stub
		HttpSession session = request.getSession( true );
		String uname = ( String ) session.getAttribute( "uname" );
		
		int uid = Integer.parseInt( request.getParameter( "lineid" ) );
		String password = request.getParameter( "password" );
		int adm = Integer.parseInt( request.getParameter( "admin" ) );
		int roleid = Integer.parseInt( request.getParameter( "roleid" ) );
		int active = Integer.parseInt( request.getParameter( "active" ) );
		
		Smuser sm = userService.getUserById( uid );
		
		String md5pass = userService.MD5( password );
		sm.setPassword( md5pass );
		sm.setLastupdatedate( new Date() );
		sm.setActive( active );
		sm.setAdmin( adm );
		sm.setRoleid( roleid );
		sm.setCuserno2( uname );
		// System.out.println(date);
		
		userService.updateUser( sm );
		return "redirect:/UserServlet/getAllUsers";
		// return this.getAllUsers(request);
	}
	
	@RequestMapping( "/getUpdateUserPage" )
	public String getUpdateUserPage( HttpServletRequest request )
	{
		int uid = Integer.parseInt( request.getParameter( "uid" ) );
		Smuser user = userService.getUserById( uid );
		// System.out.println(uid);
		List< Role > ro = userService.getRolename();
		String upass = user.getPassword();
		request.setAttribute( "user" , user );
		request.setAttribute( "ro" , ro );
		return "updauserpage";
		// request.getRequestDispatcher("/updauserpage.jsp").forward(request,
		// response);
	}
	
	@RequestMapping( "/getUserInfo" )
	public String getUserInfo( HttpServletRequest request ) throws ServletException ,
	        IOException
	{
		String p = request.getParameter( "page" );
		int admin = Integer.parseInt( request.getParameter( "admin" ) );
		// System.out.println(admin);
		int page , pages , count;
		count = userService.getCountByAdmin( admin );
		pages = userService.getpages( count , 5 );
		// System.out.println(count+"---"+pages);
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
		
		List user = userService.userInfo( admin , page );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "admin" , admin );
		request.setAttribute( "user" , user );
		request.setAttribute( "returnPath" , "UserServlet/getUserInfo?op=1&admin="
		        + admin );
		return "userbyadmin";
		// request.getRequestDispatcher("/userbyadmin.jsp").forward(request,
		// response);
	}
	
	// 条件查询
	@RequestMapping( "/getUserByOther" )
	public String getUserByOther( HttpServletRequest request )
	{
		String usname = request.getParameter( "usname" );
		try
		{
			usname = URLDecoder.decode( usname , "UTF-8" );
		}
		catch ( UnsupportedEncodingException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String p = request.getParameter( "page" );
		int page , pages , count;
		
		String sql = "select sm.lineid,sm.recordid,sm.userno,emp.employname,d.deptname,po.positionname,sm.lastupdatedate,sm.active"
		        + " from Smuser  sm,Role  ro ,Employrecord  emp,Position  po,dept d"
		        + " where sm.roleid=ro.lineid and sm.recordid=emp.lineid and emp.positionid=po.lineid and emp.deptid= d.lineid  ";
		
		sql += " and emp.employname like '%" + usname + "%' ";
		
		String countSQL = "";
		int beginPos = sql.toLowerCase().indexOf( "from" );
		if ( beginPos != - 1 )
		{
			countSQL = "select count(*) " + sql.substring( beginPos );
		}
		
		count = emrService.getCountBySQL( countSQL );
		pages = emrService.getpages( count , 5 );
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
		
		List user = emrService.getpageDateBySqlQuery( sql , page , 5 );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "user" , user );
		request.setAttribute( "returnPath" , "UserServlet/getUserByOther?op=1&usname="
		        + usname );
		
		return "user";
		
	}
	
	@RequestMapping( "/getPasswordPage" )
	public String getPasswordPage( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String userno = ( String ) session.getAttribute( "uname" );
		Smuser user = userService.userName( userno );
		request.setAttribute( "user" , user );
		return "updapasspage";
		// request.getRequestDispatcher("/updapasspage.jsp").forward(request,
		// response);
	}
	
	@RequestMapping( "/logOutUser" )
	public String logOutUser( HttpServletRequest request )
	{
		
		HttpSession session = request.getSession( false );
		if ( session != null )
		{
			session.removeAttribute( "deptid" );
			session.removeAttribute( "recordid" );
			session.removeAttribute( "lineid" );
			session.removeAttribute( "uname" );
			session.invalidate();
			// System.out.println("session已移除 ");
			
		}
		// HttpSession session1 = request.getSession(false);
		// System.out.println("session1 >> "+session1);
		return "login";
	}
	
	@RequestMapping( "/getAllUserByApprove" )
	public String getAllUserByApprove( HttpServletRequest request )
	{
		String sql = " select sm.lineid,d.deptname, emr.employname from Smuser sm,Employrecord emr,Dept d  where sm.recordid=emr.lineid  and emr.deptid=d.lineid  and emr.status>0  order by emr.employname desc ";
		List< Object[] > uslist = userService.getDateBySqlQuery( sql , 0 , 0 );
		JSONArray jsarr = new JSONArray();
		
		for ( Object[] objs : uslist )
		{
			JSONObject json = new JSONObject();
			json.element( "lineid" , objs[0] );
			json.element( "deptname" , objs[1] );
			json.element( "usname" , objs[2] );
			jsarr.add( json );
		}
		
		request.setAttribute( "uslist" , jsarr.toString() );
		return "oa_privilege/userApprove";
	}
	
	@RequestMapping( "/saveUserApprove" )
	public String saveUserApprove( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		int opusid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		Smuser user = userService.getUserById( opusid );
		
		int usid = Integer.parseInt( request.getParameter( "usnameid" ) );
		int appusid = Integer.parseInt( request.getParameter( "usnameid1" ) );
		String sql = " delete from userApprove d where d.userid =" + usid;
		deptService.execSQL( sql );
		
		UserApprove us = new UserApprove();
		us.setUserid( usid );
		us.setApproveusid( appusid );
		us.setOpuser( user );
		us.setOptime( new Date() );
		userService.saveUserApprove( us );
		
		return "redirect:/UserServlet/getAllUserByApprove";
	}
	
	@RequestMapping( "/getUserApproveByusID" )
	@ResponseBody
	public String getUserApproveByusID( HttpServletRequest request )
	{
		int usid = Integer.parseInt( request.getParameter( "usid" ) );
		
		String sql = " select app.approveusid,emp.employname from userapprove app,smuser sm,employrecord emp  where app.approveusid=sm.lineid and sm.recordid=emp.lineid and app.userid= "
		        + usid;
		List< Object[] > hqlist = deptService.getDateBySqlQuery( sql , 0 , 0 );
		
		JSONObject json = new JSONObject();
		if ( hqlist.size() > 0 )
		{
			json.element( "usid" , hqlist.get( 0 )[0] );
			json.element( "usname" , hqlist.get( 0 )[1] );
		}
		
		System.out.println( "getUserApproveByusID 获取人员对应审批人信息" + sql );
		return json.toString();
		
	}
	
	/**
	 * 
	 * @Description:这里用一句话描述这个方法的作用
	 * @return 
	 *   String 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月2日 下午2:03:54
	 */
	@RequestMapping( "/frame" )
	public String loginframe()
	{
		return "frame";
		
	}
	
	/**
	 * 
	 * @Description:检查登录
	 * @param request
	 * @return 
	 *   String 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月10日 上午11:47:06
	 */
	@RequestMapping( "/loginByCheck" )
	public String loginByCheck( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String username = request.getParameter( "username" );
		String checkType = request.getParameter( "checkType" );
		String token = request.getParameter( "token" );
		Smuser user = userService.getUserByUserno( username );
		SmuserToken smuserToken = userService.findByToken( token );
		if ( user != null && smuserToken != null )
		{
			if ( username.equalsIgnoreCase( smuserToken.getVcUserName() ) )
			{
				Date nowTimeDate = new Date();
				long add = smuserToken.getDtAdd().getTime();
				long now = nowTimeDate.getTime();
				long minus = now - add;
				long siff = 1000 * 60 * 60 * 24;
				double days = Double.parseDouble( minus + "" )
				        / Double.parseDouble( siff + "" );
				if ( days > 1 )
				{
					return "login";
				}
				else
				{
					session.setAttribute( "deptid" , user.getEmployrecord().getDept()
					        .getLineid() );
					session.setAttribute( "recordid" , user.getEmployrecord().getLineid() );
					session.setAttribute( "lineid" , user.getLineid() );
					session.setAttribute( "uname" , username );
					//UserSession.set( "uname" , username );
					//System.out.println( "session = " + UserSession.get( "uname" ) );
					return "frame";
				}
				
			}
			else
			{
				return "usernoError";
			}
			
		}
		else
		{
			
			return "usernoError";
			
		}
	}
}
