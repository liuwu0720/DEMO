package clt.com.cn.controller.employ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import clt.com.cn.helps.DateUtil;
import clt.com.cn.helps.SystemStatus;
import clt.com.cn.model.entity.ContractType;
import clt.com.cn.model.entity.Dept;
import clt.com.cn.model.entity.DeptAddress;
import clt.com.cn.model.entity.Educationlevel;
import clt.com.cn.model.entity.EmployManager;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.EmployrecordCheck;
import clt.com.cn.model.entity.Position;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IEmployholidayService;
import clt.com.cn.service.IEmployrecordCheckService;
import clt.com.cn.service.IEmployrecordService;
import clt.com.cn.service.IUserService;

@Controller
@RequestMapping( "/EmployrecordServlet" )
public class EmployrecordController
{
	
	@Autowired
	private IEmployrecordService emrService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IEmployholidayService empholidayService;
	@Autowired
	private IEmployrecordCheckService empcordCheckService;
	
	@RequestMapping( "/getAllEmr" )
	public String getAllEmr( HttpServletRequest request )
	{
		
		HttpSession session = request.getSession( false );
		int deptid = Integer.parseInt( session.getAttribute( "deptid" ) + "" );
		int usid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		String uname = ( String ) session.getAttribute( "uname" );
		
		String sql = "  select d.deptid from employmanager d where d.userid=" + usid;
		List< Object > hqlist = deptService.getDateBySqlQuery( sql , 0 , 0 );
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
			System.out.println( "depts >>" + depts );
			sql = "select em.lineid,em.fileno,em.empuserno,em.employname,getCompanyByDeptID(em.deptid)  as companyname, getAllDeptName(em.deptid) as deptid,po.positionname  "
			        + "	from Employrecord  em,Position  po where em.positionid=po.lineid and  em.status>=0 and em.deptid in ("
			        + depts + ")";
		}
		else
		{
			sql = "select em.lineid,em.fileno,em.empuserno,em.employname,getCompanyByDeptID(em.deptid)  as companyname, getAllDeptName(em.deptid) as deptid,po.positionname  from Employrecord  em,Position  po where em.positionid=po.lineid and  em.status>=0 and em.fileno='"
			        + uname + "'";
		}
		
		System.out.println( "~~~" + sql );
		// 查询所有信息
		String p = request.getParameter( "page" );
		int page , pages , count;
		
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
		List list = emrService.getpageDateBySqlQuery( sql , page , 5 );
		request.setAttribute( "list" , list );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "returnPath" , "EmployrecordServlet/getAllEmr?op=1" );
		// 将语句放到session中 就不用每次查询前都验证 档案权限了
		session.setAttribute( "empsql" , sql );
		
		return "oa_employrecord/allemr";
	}
	
	/*@RequestMapping( "/getAllEmr" )
	public String getAllEmr( HttpServletRequest request )
	{
		
		HttpSession session = request.getSession( false );
		int deptid = Integer.parseInt( session.getAttribute( "deptid" ) + "" );
		int usid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		String uname = ( String ) session.getAttribute( "uname" );
		String uscount = " select d.lineid from dept d where d.manageruserid=" + usid;
		String sql = "";
		
		String s = "select getCompanyIDByDeptID(" + deptid + ") did from dual ";
		List complist = empholidayService.getpageDateBySqlQuery( s , 0 , 0 );
		String compid = ( String ) complist.get( 0 );
		System.out.println( "deptid " + deptid + " 所属公司id " + compid );
		
		boolean b = SystemStatus.getisHr( compid + "," + deptid );
		
		// 如果部门id是人力行政 则可以查询所有档案 部门领导可查看部门下的 其他只能看自己的
		if ( deptid == SystemStatus.hrdept || deptid == SystemStatus.admindept )
		{
			sql = "select em.lineid,em.fileno,em.employname,getCompanyByDeptID(em.deptid)  as companyname, getAllDeptName(em.deptid) as deptid,po.positionname  from Employrecord  em,Position  po where em.positionid=po.lineid and em.status>=0 ";
		}
		else if ( b )
		{
			// 获取对应分公司的所有人力档案
			// sql =
			// "select em.lineid,em.fileno,em.employname,getCompanyByDeptID(em.deptid)  as companyname, getAllDeptName(em.deptid) as deptid,po.positionname "
			// +
			// " from Employrecord  em,Position  po where em.positionid=po.lineid and em.status>=0 and  "
			// + " getCompanyIDByDeptID(em.deptid) =" + compid;
			// 获取所有的人力档案
			sql = "select em.lineid,em.fileno,em.employname,getCompanyByDeptID(em.deptid)  as companyname, getAllDeptName(em.deptid) as deptid,po.positionname  from Employrecord  em,Position  po where em.positionid=po.lineid and em.status>=0 ";
		}
		else
		{
			List uslist = userService.getDateBySqlQuery( uscount , 0 , 0 );
			if ( uslist.size() > 0 )
			{
				if ( uslist.size() == 1 )
				{
					sql = "select em.lineid,em.fileno,em.employname,getCompanyByDeptID(em.deptid)  as companyname, getAllDeptName(em.deptid) as deptid,po.positionname  from Employrecord  em,Position  po where em.positionid=po.lineid and em.status>=0 and em.deptid="
					        + uslist.get( 0 ).toString();
				}
				else
				{
					String str = "(";
					for ( int i = 0 ; i < uslist.size() ; i++ )
					{
						if ( i == uslist.size() - 1 )
						{
							str = str + uslist.get( i ).toString();
						}
						else
						{
							str = str + uslist.get( i ).toString() + ",";
						}
					}
					str = str + ")";
					System.out.println( "str ??? " + str );
					sql = "select em.lineid,em.fileno,em.employname,getCompanyByDeptID(em.deptid)  as companyname, getAllDeptName(em.deptid) as deptid,po.positionname  from Employrecord  em,Position  po where em.positionid=po.lineid and em.status>=0 and em.deptid in "
					        + str;
				}
			}
			else
			{
				// 只查询自己的档案
				sql = "select em.lineid,em.fileno,em.employname,getCompanyByDeptID(em.deptid)  as companyname, getAllDeptName(em.deptid) as deptid,po.positionname  from Employrecord  em,Position  po where em.positionid=po.lineid and  em.status>=0 and em.fileno='"
				        + uname + "'";
			}
		}
		
		System.out.println( "~~~" + sql );
		// 查询所有信息
		String p = request.getParameter( "page" );
		int page , pages , count;
		
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
		List list = emrService.getpageDateBySqlQuery( sql , page , 5 );
		request.setAttribute( "list" , list );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "returnPath" , "EmployrecordServlet/getAllEmr?op=1" );
		// 将语句放到session中 就不用每次查询前都验证 档案权限了
		session.setAttribute( "empsql" , sql );
		
		return "oa_employrecord/allemr";
	}*/

	@RequestMapping( "/getAllEmrUser" )
	public String getAllEmrUser( HttpServletRequest request )
	{
		// 查询所有信息
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = emrService.getCount();
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
		List list = emrService.getAllEmr2( page );
		request.setAttribute( "list" , list );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "returnPath" , "EmployrecordServlet/getAllEmrUser?op=1" );
		
		return "oa_employrecord/allemruser";
	}
	
	// 获取 用户档案管理 加载之前
	@RequestMapping( "/getPersonArchives" )
	public String getPersonArchives( HttpServletRequest request )
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
		return "oa_employrecord/personarchives";
	}
	
	@RequestMapping( "/getDeptByusID" )
	@ResponseBody
	public String getDeptByusID( HttpServletRequest request )
	{
		int usid = Integer.parseInt( request.getParameter( "usid" ) );
		
		String sql = " select de.lineid,de.pid,de.deptname from dept de where de.lineid in ( select d.deptid from employmanager d where d.userid="
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
		System.out.println( "getDeptByusID 获取人员对应部门档案信息" + sql );
		return arr.toString();
		
	}
	
	// 保存 员工和对应档案的映射
	@RequestMapping( "/saveEmployManager" )
	public String saveEmployManager( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		int opusid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		Smuser user = userService.getUserById( opusid );
		int usid = Integer.parseInt( request.getParameter( "usnameID" ) );
		String deptmappidds = request.getParameter( "deptmappids" );
		System.out.println( "saveEmployManager " + usid + " >>> " + deptmappidds );
		
		String sql = " delete from employmanager d where d.userid =" + usid;
		deptService.execSQL( sql );
		if ( deptmappidds != null && deptmappidds != "" )
		{
			String[] strs = deptmappidds.split( "," );
			for ( String str : strs )
			{
				EmployManager d = new EmployManager();
				d.setUserid( usid );
				d.setDeptid( Integer.parseInt( str ) );
				d.setOptime( new Date() );
				d.setOpuser( user );
				emrService.saveEmployManager( d );
			}
		}
		
		return "redirect:/EmployrecordServlet/getPersonArchives";
		
	}
	
	@RequestMapping( "/getEmrById" )
	public String getEmrById( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employrecord emr = emrService.getEmrById( id );
		String sql = "  select getcompanybydeptid(" + emr.getDept().getLineid()
		        + ") compID from dual ";
		List< Object[] > objlist = emrService.getDateBySqlQuery( sql , 0 , 0 );
		// 所属公司
		Object compyName = objlist.get( 0 );
		
		// 办公所属公司
		Dept compy2 = deptService.getDeptById( emr.getDeptaddress().getComPayID() );
		
		Dept dept = emr.getDept();
		Position po = emrService.getPositionById( emr.getPositionid() );
		request.setAttribute( "emr" , emr );
		request.setAttribute( "dept" , dept );
		request.setAttribute( "po" , po );
		request.setAttribute( "compyName" , compyName );
		request.setAttribute( "compyName2" , compy2.getDeptname() );
		
		return "oa_employrecord/emrbyid";
	}
	
	@RequestMapping( "/del" )
	public String delEmrById( HttpServletRequest request )
	{
		// 根据ID删除，跳转到指定页面
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employrecord emr = emrService.getEmrById( id );
		String filename = emr.getFilename();
		if ( filename != null )
		{
			File file = new File( "d:\\photo" , filename );
			// response.setContentType("text/html;charset=utf-8");
			if ( ! file.isDirectory() )
			{
				file.delete();
				
			}
		}
		emr.setStatus( - 1 );
		emrService.updateEmr( emr );
		
		Smuser smsuer = userService.getUserByUserno( emr.getFileno() );
		smsuer.setActive( 0 );
		userService.updateUser( smsuer );
		
		// this.getAllEmr(request, response);
		return "redirect:/EmployrecordServlet/getAllEmr";
	}
	
	@RequestMapping( "/add" )
	public String addEmr( HttpServletRequest request )
	{
		
		MultipartHttpServletRequest mrequest = ( MultipartHttpServletRequest ) request;
		
		Map< String , MultipartFile > fileMap = mrequest.getFileMap();
		
		// 添加
		Date date1 , date2 , date3 , date4;
		Date now = new Date();
		HttpSession session = request.getSession( true );
		String userno = ( String ) session.getAttribute( "uname" );
		String filepath = "d:\\photo\\";
		System.out.println( "存放地址: " + filepath );
		
		File f = new File( filepath );
		if ( ! f.exists() )
		{
			f.mkdirs();
		}
		
		Employrecord emr = new Employrecord();
		Smuser sm = new Smuser();
		try
		{
			
			// mySmartUpload.setAllowedFilesList("jpg,png");
			for ( Map.Entry< String , MultipartFile > entity : fileMap.entrySet() )
			{
				MultipartFile mf = entity.getValue();
				String fileName = mf.getOriginalFilename();
				if ( ! fileName.equals( "" ) && ! ( fileName == null ) )
				{
					String filename = new Date().getTime() + ".jpg";
					
					File uploadFile = new File( filepath + filename );
					// 保存
					FileCopyUtils.copy( mf.getBytes() , uploadFile );
					
					emr.setFilename( filename );
					emr.setFilepath( filepath );
					
				}
				int deptid = Integer.parseInt( request.getParameter( "deptid" ) );
				int adressID = Integer.parseInt( request.getParameter( "compy2Address" ) );
				int conTypeID = Integer.parseInt( request.getParameter( "conTypeID" ) );
				
				Dept dept = deptService.getDeptById( deptid );
				DeptAddress addres = deptService.getDeptAddressByID( adressID );
				ContractType contype = emrService.getContractTypeById( conTypeID );
				
				int positionid = Integer.parseInt( request.getParameter( "positionid" ) );
				int marriage = Integer.parseInt( request.getParameter( "marriage" ) );
				int educationlevel = Integer.parseInt( request
				        .getParameter( "educationlevel" ) );
				String fileno = request.getParameter( "fileno" );
				String employname = request.getParameter( "employname" );
				String mobile = request.getParameter( "mobile" );
				String email = request.getParameter( "email" );
				String tel1 = request.getParameter( "tel1" );
				String tel2 = request.getParameter( "tel2" );
				String telline = request.getParameter( "telline" ); // 直线电话
				String contactno = request.getParameter( "contactno" );
				String cardno = request.getParameter( "cardno" );
				String accountadd = request.getParameter( "accountadd" );
				String address = request.getParameter( "address" );
				String hometel = request.getParameter( "hometel" );
				String emergencycontact = request.getParameter( "emergencycontact" );
				String emergencytel = request.getParameter( "emergencytel" );
				String school = request.getParameter( "school" );
				String technology = request.getParameter( "technology" );
				String resume = request.getParameter( "resume" );
				String graduationdate = request.getParameter( "graduationdate" );
				String birthday = request.getParameter( "birthday" );
				String comedate = request.getParameter( "comedate" );
				String[] hrprepares = request.getParameterValues( "hrprepare" );
				String[] itprepares = request.getParameterValues( "itprepare" );
				
				String hrprepare = "";
				if ( hrprepares != null )
				{
					for ( int i = 0 ; i < hrprepares.length ; i++ )
					{
						String str = hrprepares[i];
						if ( i == hrprepares.length - 1 )
						{
							hrprepare += str + "";
						}
						else
						{
							hrprepare += str + ",";
						}
					}
				}
				
				System.out.println( "hrprepare>> " + hrprepare );
				String itprepare = "";
				
				if ( itprepares != null )
				{
					for ( int i = 0 ; i < itprepares.length ; i++ )
					{
						String str = itprepares[i];
						if ( i == itprepares.length - 1 )
						{
							itprepare += str + "";
						}
						else
						{
							itprepare += str + ",";
						}
					}
				}
				
				System.out.println( "itprepare>> " + itprepare );
				
				SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
				SimpleDateFormat sdf2 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
				String date = sdf2.format( now );
				
				String certificate = request.getParameter( "certificate" );
				String bankcardno = request.getParameter( "bankcardno" );
				
				// 最大编号
				String usno = emrService.getMaxEmpuserno();
				System.out.println( usno );
				emr.setEmpuserno( usno );
				
				emr.setCertificate( certificate );
				emr.setBankcardno( bankcardno );
				emr.setFileno( fileno );
				emr.setEmployname( employname );
				emr.setMobile( mobile );
				emr.setEmail( email );
				emr.setTel1( tel1 );
				emr.setTel2( tel2 );
				emr.setTelline( telline );
				emr.setContactno( contactno );
				emr.setCardno( cardno );
				emr.setAccountadd( accountadd );
				emr.setAddress( address );
				emr.setHometel( hometel );
				emr.setEmergencycontact( emergencycontact );
				emr.setEmergencytel( emergencytel );
				emr.setSchool( school );
				emr.setTechnology( technology );
				emr.setResume( resume );
				emr.setDept( dept );
				emr.setPositionid( positionid );
				emr.setMarriage( marriage );
				Educationlevel educ = emrService.getEducationlevelbyID( educationlevel );
				emr.setEducationlevel( educ );
				emr.setUserno( userno );
				emr.setStatus( 1 );
				emr.setHrstatus( 0 );
				emr.setItstatus( 0 );
				emr.setHrprepare( hrprepare );
				emr.setItprepare( itprepare );
				emr.setDeptaddress( addres );
				emr.setCurrdate( new Date() );
				emr.setContractType( contype );
				
				try
				{
					
					if ( birthday != null && ! "".equals( birthday ) )
					{
						date1 = sdf.parse( birthday );
						emr.setBirthday( date1 );
					}
					
					if ( comedate != null && ! "".equals( comedate ) )
					{
						date2 = sdf.parse( comedate );
						emr.setComedate( date2 );
					}
					
					if ( graduationdate != null && ! "".equals( graduationdate ) )
					{
						date3 = sdf.parse( graduationdate );
						emr.setGraduationdate( date3 );
					}
					
				}
				catch ( ParseException e )
				{
					e.printStackTrace();
				}
				
				emrService.addEmr( emr );
				sm.setActive( 1 ); // 可用
				sm.setAdmin( 0 );
				sm.setCurrdate( new Date() );
				sm.setRoleid( 3 ); // 普通用户
				sm.setUserno( fileno );
				sm.setEmployrecord( emr );
				sm.setPassword( userService.MD5( SystemStatus.initpwd ) );
				userService.addUser( sm );
			}
			return "redirect:/EmployrecordServlet/getAllEmr";
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			request.setAttribute( "error" , "保存失败！" );
			return "oa_employrecord/addemrFailed";
		}
		
	}
	
	@RequestMapping( "/getUpdatePage" )
	public String getUpdatePage( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		// 根据ID查询
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employrecord emr = emrService.getEmrById( id );
		System.out.println( "lineid:" + emr.getDept().getLineid() );
		String sql = "  select getcompanyidbydeptid(" + emr.getDept().getLineid()
		        + ") compID from dual ";
		int compyID = emrService.getCountBySQL( sql );
		
		List< Object[] > deptlist = deptService.getAllDeptByComID( compyID );
		List< Dept > complist = deptService.getAllCompay();
		
		List< ContractType > conTypelist = emrService.getAllContractType();
		
		List< Object[] > addreslist = deptService.getAllAddressByCompID( emr
		        .getDeptaddress().getComPayID() );
		
		List< Position > po = emrService.getAllPosition();
		List< Educationlevel > educlist = emrService.getAllEducationlevel();
		// System.out.println("educlist >>"+educlist.size());
		
		// 验证 修改 部门 岗位 办公公司 是否需要提交验证
		int usid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		int checkflag = 0;
		if ( SystemStatus.getcheckRecord == usid )
		{
			checkflag = 1;
		}
		
		request.setAttribute( "checkflag" , checkflag );
		request.setAttribute( "emr" , emr );
		request.setAttribute( "compyID" , compyID );
		request.setAttribute( "deptlist" , deptlist );
		request.setAttribute( "addreslist" , addreslist );
		request.setAttribute( "educlist" , educlist );
		request.setAttribute( "compylist" , complist );
		request.setAttribute( "conTypelist" , conTypelist );
		request.setAttribute( "po" , po );
		return "oa_employrecord/updatepage";
		
	}
	
	@RequestMapping( "/updateEmr" )
	public String updateEmr( HttpServletRequest request )
	{
		int checkflag = Integer.parseInt( request.getParameter( "checkflag" ) );
		int issub = Integer.parseInt( request.getParameter( "issub" ) );
		
		HttpSession session = request.getSession( true );
		String userno = ( String ) session.getAttribute( "uname" );
		int recordid = Integer.parseInt( session.getAttribute( "recordid" ) + "" );
		
		int lineid = Integer.parseInt( request.getParameter( "lineid" ) );
		
		int marriage = Integer.parseInt( request.getParameter( "marriage" ) );
		int educationlevel = Integer.parseInt( request.getParameter( "educationlevel" ) );
		String fileno = request.getParameter( "fileno" );
		String employname = request.getParameter( "employname" );
		String mobile = request.getParameter( "mobile" );
		String email = request.getParameter( "email" );
		String tel1 = request.getParameter( "tel1" );
		String tel2 = request.getParameter( "tel2" );
		String telline = request.getParameter( "telline" );
		String contactno = request.getParameter( "contactno" );
		String cardno = request.getParameter( "cardno" );
		String accountadd = request.getParameter( "accountadd" );
		String address = request.getParameter( "address" );
		String hometel = request.getParameter( "hometel" );
		String emergencycontact = request.getParameter( "emergencycontact" );
		String emergencytel = request.getParameter( "emergencytel" );
		String school = request.getParameter( "school" );
		String technology = request.getParameter( "technology" );
		String resume = request.getParameter( "resume" );
		String graduationdate = request.getParameter( "graduationdate" );
		String birthday = request.getParameter( "birthday" );
		String comedate = request.getParameter( "comedate" );
		int conTypeID = Integer.parseInt( request.getParameter( "conTypeID" ) );
		ContractType contype = emrService.getContractTypeById( conTypeID );
		
		Employrecord emr = emrService.getEmrById( lineid );
		String certificate = request.getParameter( "certificate" );
		String bankcardno = request.getParameter( "bankcardno" );
		
		emr.setCertificate( certificate );
		emr.setBankcardno( bankcardno );
		String oldfileno = emr.getFileno();
		// 如果修改的用户编号和以前不一样 修改档案表的编号用户表的编号也修改 因为有关联
		if ( ! oldfileno.equals( fileno ) )
		{
			emr.setFileno( fileno );
			Smuser smuser = userService.getUserByUserno( oldfileno );
			smuser.setUserno( fileno );
			userService.updateUser( smuser );
		}
		
		emr.setEmployname( employname );
		emr.setMobile( mobile );
		emr.setEmail( email );
		emr.setTel1( tel1 );
		emr.setTel2( tel2 );
		emr.setTelline( telline );
		emr.setContactno( contactno );
		emr.setCardno( cardno );
		emr.setAccountadd( accountadd );
		emr.setAddress( address );
		emr.setHometel( hometel );
		emr.setEmergencycontact( emergencycontact );
		emr.setEmergencytel( emergencytel );
		emr.setSchool( school );
		emr.setTechnology( technology );
		emr.setResume( resume );
		emr.setMarriage( marriage );
		Educationlevel educ = emrService.getEducationlevelbyID( educationlevel );
		emr.setEducationlevel( educ );
		emr.setUserno( userno );
		
		emr.setBirthday( DateUtil.parseDate( birthday ) );
		emr.setComedate( DateUtil.parseDate( comedate ) );
		emr.setGraduationdate( DateUtil.parseDate( graduationdate ) );
		emr.setCurrdate( new Date() );
		emr.setContractType( contype );
		
		int bfCompid = Integer.parseInt( request.getParameter( "bfCompid" ) );
		int bfDeptid = Integer.parseInt( request.getParameter( "bfDeptid" ) );
		int bfpoid = Integer.parseInt( request.getParameter( "bfpoid" ) );
		int bfofCompid = Integer.parseInt( request.getParameter( "bfofCompid" ) );
		int bfdeptadrid = Integer.parseInt( request.getParameter( "bfdeptadrid" ) );
		
		int nowCompid = Integer.parseInt( request.getParameter( "cpmpy1" ) );
		int nowDeptid = Integer.parseInt( request.getParameter( "deptid" ) );
		int nowPosid = Integer.parseInt( request.getParameter( "positionid" ) );
		int nowofCompid = Integer.parseInt( request.getParameter( "compay2" ) );
		int nowDeptAdresid = Integer.parseInt( request.getParameter( "compy2Address" ) );
		
		DeptAddress addres = deptService.getDeptAddressByID( nowDeptAdresid );
		Dept dept = deptService.getDeptById( nowDeptid );
		// 如果等于1 可修改的 直接修改档案的 部门 岗位 办公地址
		if ( checkflag == 1 )
		{
			
			emr.setDept( dept );
			emr.setPositionid( nowPosid );
			emr.setDeptaddress( addres );
		}
		// 提交档案申请
		if ( issub == 1 )
		{
			Smuser smuser = userService.getUserByUserno( userno );
			Smuser checkus = userService.getUserById( SystemStatus.getcheckRecord );
			
			EmployrecordCheck empcord = new EmployrecordCheck();
			empcord.setBfCompid( bfCompid );
			empcord.setBfDeptid( bfDeptid );
			empcord.setBfPosid( bfpoid );
			empcord.setBfofCompid( bfofCompid );
			empcord.setBfDeptAdresid( bfdeptadrid );
			
			empcord.setNowCompid( nowCompid );
			empcord.setNowDeptid( nowDeptid );
			empcord.setNowPosid( nowPosid );
			empcord.setNowofCompid( nowofCompid );
			empcord.setNowDeptAdresid( nowDeptAdresid );
			
			empcord.setIscheck( 0 );
			empcord.setRecordid( lineid );
			empcord.setOpuser( smuser );
			empcord.setOpDate( new Date() );
			empcord.setCheckus( checkus );
			empcordCheckService.addEmployrecordCheck( empcord );
		}
		
		emrService.updateEmr( emr );
		
		return "redirect:/EmployrecordServlet/getAllEmr";
	}
	
	@RequestMapping( "/loadDeptPosition" )
	public String loadDeptPosition( HttpServletRequest request )
	{
		// 查询所有信息
		List< Dept > complist = deptService.getAllCompay();
		List< Dept > complist2 = deptService.getAllCompay();
		List< Position > po = emrService.getAllPosition();
		List< Educationlevel > educs = emrService.getAllEducationlevel();
		List< ContractType > conTypelist = emrService.getAllContractType();
		
		Map< Integer , String > hrmap = SystemStatus.getHRprepare();
		Map< Integer , String > itmap = SystemStatus.getITprepare();
		
		// String comstr = JsonUtil.listToJson( complist );
		// String comstr2 = JsonUtil.listToJson( complist2 );
		
		request.setAttribute( "complist" , complist );
		request.setAttribute( "conTypelist" , conTypelist );
		// request.setAttribute( "comstr2" , comstr2 );
		request.setAttribute( "hrmap" , hrmap );
		request.setAttribute( "itmap" , itmap );
		request.setAttribute( "educs" , educs );
		request.setAttribute( "po" , po );
		
		return "oa_employrecord/addemr";
		
	}
	
	@RequestMapping( "/loadPositionForXML" )
	@ResponseBody
	public String loadPositionForXML( HttpServletRequest request )
	{
		// 查询所有信息
		List< Position > po = emrService.getAllPosition();
		StringBuffer str = new StringBuffer();
		// str.append("<?xml version='1.0' encoding='utf-8'?>");
		JSONArray pojson = new JSONArray();
		for ( Position p : po )
		{
			JSONObject obj = new JSONObject();
			obj.element( "lineid" , p.getLineid() );
			obj.element( "positionName" , p.getPositionname() );
			pojson.add( obj );
		}
		
		// request.setAttribute("pojson", pojson);
		System.out.println( str.toString() );
		return pojson.toString();
		
	}
	
	@RequestMapping( "/getAllDeptByCompID" )
	@ResponseBody
	public String getAllDeptByCompID( HttpServletRequest request )
	{
		int compy1 = Integer.parseInt( request.getParameter( "compy1" ) );
		List< Object[] > compylist = deptService.getAllDeptByComID( compy1 );
		
		JSONArray pojson = new JSONArray();
		for ( Object[] p : compylist )
		{
			JSONObject obj = new JSONObject();
			obj.element( "lineid" , p[0] );
			obj.element( "pid" , p[1] );
			obj.element( "deptname" , p[2] );
			pojson.add( obj );
		}
		
		System.out.println( "getAllDeptByCompID >> " + pojson.toString() );
		return pojson.toString();
		
	}
	
	@RequestMapping( "/getCompyAddressByID" )
	@ResponseBody
	public String getCompyAddressByID( HttpServletRequest request )
	{
		int compy1 = Integer.parseInt( request.getParameter( "compy1" ) );
		List< Object[] > compylist = deptService.getAllAddressByCompID( compy1 );
		
		JSONArray pojson = new JSONArray();
		for ( Object[] p : compylist )
		{
			JSONObject obj = new JSONObject();
			obj.element( "lineid" , p[0] );
			obj.element( "pid" , p[1] );
			obj.element( "deptname" , p[2] );
			obj.element( "deptaddress" , p[3] );
			pojson.add( obj );
		}
		
		System.out.println( "getCompyAddressByID >> " + pojson.toString() );
		return pojson.toString();
		
	}
	
	@RequestMapping( "/getInfoByOther" )
	public String getInfoByOther( HttpServletRequest request )
	{
		// 高级查询（序号，员工编号，员工姓名）
		String lineid = request.getParameter( "lineid" );
		String fileno = request.getParameter( "fileno" );
		String employname = request.getParameter( "employname" );
		System.out.println( lineid + "==" + fileno + "==" + employname );
		List list = emrService.EmrInfoByOther( lineid , fileno , employname );
		request.setAttribute( "list" , list );
		return "oa_employrecord/allemr";
		
	}
	
	@RequestMapping( "/getInfoByName" )
	public String getInfoByName( HttpServletRequest request )
	{
		String sql = "select em.lineid,em.fileno,em.employname,getCompanyByDeptID(em.deptid)  as companyname, getAllDeptName(em.deptid) as deptid,po.positionname,em.mobile,em.tel1,em.tel2,em.contactno,em.email "
		        + " from Employrecord  em,Position  po where em.positionid=po.lineid ";
		
		// 按员工姓名查信息
		String employname = request.getParameter( "employname" );
		try
		{
			employname = URLDecoder.decode( employname , "UTF-8" );
		}
		catch ( UnsupportedEncodingException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql = sql + " and em.employname like '%" + employname + "%' ";
		String countSQL = "";
		int beginPos = sql.toLowerCase().indexOf( "from" );
		if ( beginPos != - 1 )
		{
			countSQL = "select count(*) " + sql.substring( beginPos );
		}
		
		String p = request.getParameter( "page" );
		int page , pages , count;
		
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
				page = pages;
			}
		}
		List emr = emrService.getpageDateBySqlQuery( sql , page , 5 );
		request.setAttribute( "emr" , emr );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "employname" , employname );
		request.setAttribute( "returnPath" ,
		        "EmployrecordServlet/getInfoByName?op=1&employname=" + employname );
		return "oa_employrecord/emrbookbyname";
		
	}
	
	// 按员工姓名查询其档案信息(开户)
	@RequestMapping( "/addUserByName" )
	public String addUserByName( HttpServletRequest request )
	{
		String employname = request.getParameter( "employname" );
		String p = request.getParameter( "page" );
		int page , pages , count;
		if ( employname.equals( "搜索信息（员工姓名）" ) )
		{
			// this.getAllEmrUser(request, response);
			return "redirect:/EmployrecordServlet/getAllEmrUser";
		}
		else
		{
			count = emrService.getCountByName( employname );
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
			List emr = emrService.getEmrUserByName( employname , pages );
			request.setAttribute( "emr" , emr );
			request.setAttribute( "page" , page );
			request.setAttribute( "pages" , pages );
			request.setAttribute( "employname" , employname );
			request.setAttribute( "returnPath" ,
			        "EmployrecordServlet/addUserByName?op=1&employname=" + employname );
			
			return "oa_employrecord/adduserbyname";
			
		}
	}
	
	// 按员工姓名查询其档案信息
	@RequestMapping( "/getUserByName" )
	public String getUserByName( HttpServletRequest request )
	{
		
		HttpSession session = request.getSession( false );
		String empsql = ( String ) session.getAttribute( "empsql" );
		
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
		empsql = empsql + " and ( em.employname like '%" + employname
		        + "%' OR em.fileno  like '%" + employname
		        + "%' OR po.positionname like '%" + employname + "%') ";
		System.out.println( "empsql ?? " + empsql );
		
		String countSQL = "";
		int beginPos = empsql.toLowerCase().indexOf( "from" );
		if ( beginPos != - 1 )
		{
			countSQL = "select count(*) " + empsql.substring( beginPos );
		}
		
		System.out.println( "employname 2 " + employname );
		String p = request.getParameter( "page" );
		int page , pages , count;
		
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
		List list = emrService.getpageDateBySqlQuery( empsql , page , 5 );
		request.setAttribute( "list" , list );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "employname" , employname );
		request.setAttribute( "returnPath" ,
		        "EmployrecordServlet/getUserByName?op=1&employname=" + employname );
		
		return "oa_employrecord/allemr";
		
	}
	
	// 查看个人信息
	@RequestMapping( "/getPersonPage" )
	public String getPersonPage( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String fileno = ( String ) session.getAttribute( "uname" );
		Employrecord emr = emrService.getPersonInfo( fileno );
		Dept dept = emr.getDept();
		Position po = emrService.getPositionById( emr.getPositionid() );
		request.setAttribute( "emr" , emr );
		request.setAttribute( "dept" , dept );
		request.setAttribute( "po" , po );
		
		return "oa_employrecord/personpage";
		
	}
	
	// 查询联系方式
	@RequestMapping( "/getEmrBook" )
	public String getEmrBook( HttpServletRequest request )
	{
		// 获取分公司 部门父id为0的
		String hql = " from Dept d where d.pid=0 and d.active=1 ";
		List< Dept > delplist = deptService.getUserInfo( hql );
		Dept dept = null;
		
		/*String sql = "select em.lineid,em.fileno,em.employname,getCompanyByDeptID(em.deptid)  as companyname, getAllDeptName(em.deptid) as deptid,po.positionname,em.mobile,em.tel1,em.tel2,em.telline,em.contactno,em.email "
		        + " from Employrecord  em,Position  po,Dept d where em.positionid=po.lineid and em.deptid = d.lineid and em.status>0 ";*/
		String sql = "select em.lineid,em.fileno,em.employname,getcompanybydeptid(em.deptid)|| '>>'|| addre.deptaddress addres, getAllDeptName(em.deptid) as deptid,po.positionname,em.mobile,em.tel1,em.tel2,em.telline,em.contactno,em.email "
		        + " from Employrecord  em,Position  po,Dept d , deptaddress addre  where em.positionid=po.lineid and em.deptid = d.lineid and addre.lineid = em.deptaddressid and em.status>0 and em.contractTypeID=1  ";
		
		// 按员工姓名查信息
		String employname = request.getParameter( "employname" );
		int deptid = 0;
		if ( request.getParameter( "deptid" ) != null
		        && Integer.parseInt( request.getParameter( "deptid" ) + "" ) != 0 )
		{
			deptid = Integer.parseInt( request.getParameter( "deptid" ) + "" );
			sql = sql + " and d.lineid  in( " + " select dt.lineid " + " from dept dt "
			        + " start with dt.lineid = " + deptid + ""
			        + " connect by Prior  dt.lineid = dt.pid ) ";
			dept = deptService.getDeptById( deptid );
		}
		if ( employname != null && ( ! employname.equals( "" ) )
		        && ( ! employname.equals( "null" ) ) )
		{
			try
			{
				employname = URLDecoder.decode( employname , "UTF-8" );
			}
			catch ( UnsupportedEncodingException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sql = sql + " and em.employname like '%" + employname + "%' ";
		}
		
		String countSQL = "";
		int beginPos = sql.toLowerCase().indexOf( "from" );
		if ( beginPos != - 1 )
		{
			countSQL = "select count(*) " + sql.substring( beginPos );
		}
		sql += " order by em.lineid     ";
		System.out.println( "sql >> " + sql );
		
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = emrService.getCountBySQL( countSQL );
		pages = emrService.getpages( count , 10 );
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
		List emr = emrService.getDateBySqlQuery( sql , 10 , page );
		/*for ( int i = 0 ; i < emr.size() ; i++ )
		{
			Object[] strs = ( Object[] ) emr.get( i );
			System.out.println( " " + strs[0] + " " + strs[1] + " " + strs[2] );
		}*/
		request.setAttribute( "emr" , emr );
		request.setAttribute( "deptlist" , delplist );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "employname" , employname );
		request.setAttribute( "dept" , dept );
		
		request.setAttribute( "returnPath" ,
		        "EmployrecordServlet/getEmrBook?op=1&employname=" + employname
		                + "&deptid=" + deptid );
		
		return "oa_employrecord/emrbook";
	}
	
	@RequestMapping( "/getEmrBookById" )
	public String getEmrBookById( HttpServletRequest request )
	{
		// 根据ID查询
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employrecord emr = emrService.getEmrById( id );
		// System.out.println(id+"--"+emr.getEmployname());
		Dept dept = emr.getDept();
		Position po = emrService.getPositionById( emr.getPositionid() );
		request.setAttribute( "emr" , emr );
		request.setAttribute( "dept" , dept );
		request.setAttribute( "po" , po );
		/*
		 * request.getRequestDispatcher("oa_employrecord/emrbookbyid.jsp")
		 * .forward(request, response);
		 */

		return "oa_employrecord/emrbookbyid";
		
	}
	
	// 读取图片
	@RequestMapping( "/readphoto" )
	public String readphoto( HttpServletRequest request , HttpServletResponse response )
	{
		String url = null;
		if ( request.getParameter( "photourl" ) != null )
		{
			try
			{
				request.setCharacterEncoding( "utf-8" );
				url = new String( request.getParameter( "photourl" ).getBytes(
				        "iso8859-1" ) , "utf-8" );
			}
			catch ( Exception e )
			{
			}
		}
		
		try
		{
			response.setHeader( "Content-Disposition" , "attachment; filename="
			        + URLEncoder.encode( url.substring( url.lastIndexOf( "/" ) + 1 ) ,
			                "utf-8" ) );
		}
		catch ( UnsupportedEncodingException e )
		{
			e.printStackTrace();
		}
		// 主要是向用户显示文件名
		FileInputStream is = null;
		try
		{
			is = new FileInputStream( "d:/photo" + url );
			OutputStream os = response.getOutputStream();
			int len = 0;
			byte[] b = new byte[1024];
			
			while ( ( len = is.read( b ) ) > 0 )
			{ // 依次将各种信息写入到response中。
				os.write( b , 0 , len );
			}
		}
		catch ( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if ( is != null )
			{
				try
				{
					is.close();
				}
				catch ( IOException e )
				{
					e.printStackTrace();
				}
				is = null;
			}
		}
		return null;
	}
	
	// 删除照片
	@RequestMapping( "/delphoto" )
	public String delphoto( HttpServletRequest request )
	{
		// 根据ID删除，跳转到指定页面
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Employrecord emr = emrService.getEmrById( id );
		String filename = emr.getFilename();
		if ( filename != null )
		{
			File file = new File( "d:\\photo" , filename );
			// response.setContentType("text/html;charset=utf-8");
			if ( ! file.isDirectory() )
			{
				file.delete();
				emr.setFilename( "" );
				emr.setFilepath( "" );
				emrService.updateEmr( emr );
			}
		}
		
		// this.getAllEmr(request, response);
		return "redirect:/EmployoutServlet/getAllEmr";
	}
	
	// 添加图片
	@RequestMapping( "/addphoto" )
	public String addphoto( HttpServletRequest request )
	{
		
		MultipartHttpServletRequest mrequest = ( MultipartHttpServletRequest ) request;
		
		Map< String , MultipartFile > fileMap = mrequest.getFileMap();
		
		// 根据ID删除，跳转到指定页面
		int id = Integer.parseInt( request.getParameter( "id" ) );
		System.out.println( id );
		Employrecord emr = emrService.getEmrById( id );
		// 上传文件存放目录
		String filepath = "d:\\photo\\";
		System.out.println( "存放地址: " + filepath );
		
		File f = new File( filepath );
		if ( ! f.exists() )
		{
			f.mkdirs();
		}
		// 变量定义
		int count = 0;
		// 创建一个SmartUpload类
		
		// SmartUpload mySmartUpload = new SmartUpload();
		try
		{
			// 初始化
			HttpSession session = request.getSession();
			// mySmartUpload.initialize(config, request, response);
			
			// 1.限制每个上传文件的最大长度。
			// su.setMaxFileSize(10000);
			// 2.限制总上传数据的长度。
			// su.setTotalMaxFileSize(20000);
			// 3.设定允许上传的文件（通过扩展名限制）,仅允许doc,txt文件。
			// mySmartUpload.setAllowedFilesList("jpg,png");
			// 4.设定禁止上传的文件（通过扩展名限制）,禁止上传带有exe,bat,jsp,htm,html扩展名的文件和没有
			// 扩展名的文件。
			// mySmartUpload.setDeniedFilesList("exe,bat,jsp,htm,html,,");
			// 上传
			// mySmartUpload.upload();
			for ( Map.Entry< String , MultipartFile > entity : fileMap.entrySet() )
			{
				// 上传文件名
				MultipartFile mf = entity.getValue();
				String fileName = mf.getOriginalFilename();
				if ( ! fileName.equals( "" ) && ! ( fileName == null ) )
				{
					String filename = new Date().getTime() + ".jpg";
					// 保存
					File uploadFile = new File( filepath + fileName );
					FileCopyUtils.copy( mf.getBytes() , uploadFile );
					
					emr.setFilename( filename );
					emr.setFilepath( filepath );
					emrService.updateEmr( emr );
				}
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return "redirect:/EmployoutServlet/getAllEmr";
	}
	
	@RequestMapping( "/updateEmpStaByID" )
	public String updateEmpStaByID( HttpServletRequest request )
	{
		
		int id = Integer.parseInt( request.getParameter( "id" ) );
		String params = request.getParameter( "params" );
		Employrecord emr = emrService.getEmrById( id );
		emr.setHrstatus( 1 );
		/*if ( params.equals( "hrprepare" ) )
		{
			int itsta = emr.getItstatus();
			if ( itsta == 1 )
			{
				emr.setStatus( 1 );
			}
			emr.setHrstatus( 1 );
			
		}
		else if ( params.equals( "itprepare" ) )
		{
			int hrsta = emr.getHrstatus();
			if ( hrsta == 1 )
			{
				emr.setStatus( 1 );
			}
			emr.setItstatus( 1 );
		}*/

		emrService.updateEmr( emr );
		request.setAttribute( "params" , params );
		return "redirect:/EmployrecordServlet/getNewEmpCord";
	}
	
	@RequestMapping( "/getNewEmpCord" )
	public String getNewEmpCord( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		int deptid = ( Integer ) session.getAttribute( "deptid" );
		int usid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		
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
			
			sql = " select emp.lineid,emp.employname,d.deptname,po.positionname,emp.status "
			        + "from employrecord emp,dept d,position po where emp.deptid=d.lineid and emp.positionid=po.lineid and emp.status>0 and emp.hrstatus=0 and getCompanyIDByDeptID(d.lineid) in ("
			        + depts + ") ";
			System.out.println( " 获取新员工入职 HR 确认 " + sql );
			List emplist = emrService.getDateBySqlQuery( sql , 0 , 0 );
			List objlist = new ArrayList();
			Map< Integer , String > stamap = SystemStatus.getHRprepare();
			
			request.setAttribute( "stamap" , stamap );
			request.setAttribute( "emplist" , emplist );
			System.out.println( "Module emplist >> " + emplist.size() );
		}
		/*	String params = "";
			if ( deptid == SystemStatus.hrdept )
			{
				params = "hrprepare";
			}
			else if ( deptid == SystemStatus.itdept )
			{
				params = "itprepare";
			}*/

		// 除了人事部和IT部门能看到新员工入职 其他不行
		
		/*for ( int i = 0 ; i < emplist.size() ; i++ )
		{
			Object[] strs = ( Object[] ) emplist.get( i );
			if ( strs[2] != null && strs[2].toString().indexOf( "," ) > 0 )
			{
				Object[] str = strs[2].toString().split( "," );
				
				String s = "";
				for ( Object st : str )
				{
					for ( Map.Entry< Integer , String > entry : stamap.entrySet() )
					{
						if ( Integer.parseInt( st + "" ) == entry.getKey() )
						{
							s = s + entry.getValue() + " ";
						}
					}
					System.out.println( "ssss >>" + s );
				}
				strs[2] = s;
			}
			
		}*/

		return "oa_employrecord/showNewEmpcord";
	}
}
