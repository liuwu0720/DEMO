package clt.com.cn.controller.employ;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import clt.com.cn.helps.DateUtil;
import clt.com.cn.helps.ExcelHelper;
import clt.com.cn.model.entity.Dept;
import clt.com.cn.service.ICheckInfoService;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IEmployrecordService;

@Controller
@RequestMapping( "/CheckInfoController" )
public class CheckInfoController
{
	
	@Autowired
	private ICheckInfoService checkInfoService;
	@Autowired
	private IEmployrecordService emrService;
	@Autowired
	private IDeptService deptService;
	
	@RequestMapping( "/getCheckInfoList" )
	public String getCheckInfoList( HttpServletRequest request )
	{
		
		String hql = " from Dept d where d.pid=0 and d.active=1 ";
		List< Dept > delplist = deptService.getUserInfo( hql );
		
		String p = request.getParameter( "page" );
		String begin = request.getParameter( "begin" );
		String end = request.getParameter( "end" );
		String usname = request.getParameter( "usname" );
		
		int deptid = 0;
		if ( request.getParameter( "deptid" ) != null
		        && request.getParameter( "deptid" ) != "" )
		{
			deptid = Integer.parseInt( request.getParameter( "deptid" ) );
		}
		
		String beginStr = "";
		String endStr = "";
		if ( isNotEmpty( begin ) && ( ! "null".equals( begin ) ) )
		{
			beginStr = "to_date('" + begin + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}
		
		if ( isNotEmpty( end ) && ( "null".equals( end ) ) )
		{
			endStr = "to_date('" + end + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		else
		{
			endStr = "to_date('" + DateUtil.formatDate( new Date() )
			        + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String sql = "";
		if ( isNotEmpty( beginStr ) )
		{
			sql = getSqlByDoubleNotEmpty( beginStr , endStr , deptid );
		}
		else
		{
			sql = getSql( deptid );
		}
		if ( isNotEmpty( usname ) && ( ! "null".equals( usname ) ) )
		{
			try
			{
				usname = URLDecoder.decode( usname , "UTF-8" );
			}
			catch ( UnsupportedEncodingException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sql += "  and e.employname like '%" + usname + "%' ";
		}
		
		System.out.println( " getCheckInfoList sql >>>> " + sql );
		int page , pages , count;
		count = checkInfoService.getCountBySQL( "SELECT COUNT(*)  FROM ( " + sql + " )" );
		pages = checkInfoService.getpages( count , 10 );
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
		List checkInfoList = checkInfoService.getpageDateBySqlQuery( sql , page , 10 );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "checkList" , checkInfoList );
		request.setAttribute( "deptlist" , delplist );
		request.setAttribute( "deptid" , deptid );
		request.setAttribute( "begin" , begin );
		request.setAttribute( "usname" , usname );
		request.setAttribute( "end" , end );
		request.setAttribute( "count" , count );
		request.setAttribute( "returnPath" ,
		        "CheckInfoController/getCheckInfoList?op=1&deptid=" + deptid + "&begin="
		                + begin + "&end=" + end + "&usname=" + usname );
		return "oa_employcheckinfo/checkinfoList";
	}
	
	private boolean isNotEmpty( String str )
	{
		if ( null == str || "".equals( str ) )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	private String getSqlByDoubleNotEmpty( String beginStr , String endStr , int deptid )
	{
		
		String sql = "SELECT t.lineid,  e.employname, "
		        + " (SELECT SUM(h.days)  FROM EMPLOYHOLIDAY h "
		        + "   WHERE t.lineid = h.userid AND h.ISCHECK = 2 and h.date1 > "
		        + beginStr
		        + " and h.date2 <= "
		        + endStr
		        + " ) holiday, "
		        + "  (SELECT sum(CEIL((h2.date2 - h2.date1) * 24)) FROM EMPLOYOUT h2 "
		        + "    WHERE t.lineid = h2.userid AND h2.ISCHECK = 2 and h2.date1 > "
		        + beginStr
		        + " and h2.date2 <= "
		        + endStr
		        + ") outday, "
		        + "  (SELECT SUM( CEIL((ov.date2-ov.date1)*24)) FROM EMPLOYOVERTIME ov "
		        + "    WHERE t.lineid = ov.userid AND ov.ischeck = 2 and ov.employovertimetypeid=1 and ov.date1 > "
		        + beginStr
		        + " and ov.date2 <= "
		        + endStr
		        + ") overtime, "
		        + "   (SELECT SUM(ovweek.days) FROM EMPLOYOVERTIME ovweek "
		        + "   WHERE t.lineid = ovweek.userid AND ovweek.ischeck = 2 and ovweek.employovertimetypeid=2 and ovweek.date1 > "
		        + beginStr
		        + " and ovweek.date2 <= "
		        + endStr
		        + ") overtimeWeek, "
		        + "   (SELECT SUM(jiaweek.days) FROM EMPLOYOVERTIME jiaweek "
		        + "   WHERE t.lineid = jiaweek.userid AND jiaweek.ischeck = 2 and jiaweek.employovertimetypeid=3 and jiaweek.date1 > "
		        + beginStr
		        + " and jiaweek.date2 <= "
		        + endStr
		        + ") overtimejiaWeek, "
		        + "  (SELECT SUM(CEIL(tr.date2 - tr.date1))  FROM EMPLOYTRIP tr "
		        + "   WHERE t.lineid = tr.userid AND tr.ischeck = 2 and tr.date1 > "
		        + beginStr
		        + " and tr.date2 <= "
		        + endStr
		        + ") trip, "
		        + " (SELECT COUNT(att.lineid) FROM EMPLOYATTENDANCE att "
		        + "   WHERE t.lineid = att.userid  AND att.ischeck = 2  and att.opdate > "
		        + beginStr
		        + " and att.opdate <= "
		        + endStr
		        + ") cqyc "
		        + " FROM SMUSER t, EMPLOYRECORD e, dept d "
		        + " WHERE t.recordid = e.lineid  and e.status > 0 "
		        + " and e.deptid = d.lineid "
		        + " and d.lineid in (select dt.lineid from dept dt  start with dt.lineid = "
		        + deptid + " connect by Prior dt.lineid = dt.pid)";
		return sql;
	}
	
	private String getSqlByBeginNotEmpty( String beginStr )
	{
		String sql = "SELECT e.employname, (SELECT SUM(CEIL(h.date2-h.date1)) "
		        + "FROM EMPLOYHOLIDAY h WHERE t.lineid=h.userid AND h.ISCHECK=2" +

		        " AND h.date1>="
		        + beginStr
		        + " ) holiday, "
		        + "( SELECT sum(CEIL((h2.date2-h2.date1)*24))  FROM EMPLOYOUT h2 "
		        + "WHERE  t.lineid=h2.userid AND h2.ISCHECK=2  "
		        + "AND h2.date1>="
		        + beginStr
		        + "  ) outday,"
		        + "( SELECT SUM( CEIL((ov.date2-ov.date1)*24)) FROM EMPLOYOVERTIME ov "
		        + "WHERE t.lineid=ov.userid AND ov.ischeck=2 "
		        + " AND ov.date1>="
		        + beginStr
		        + "   ) overtime,"
		        + "(SELECT SUM(CEIL(tr.date2-tr.date1)) FROM EMPLOYTRIP tr "
		        + "WHERE t.lineid=tr.userid AND tr.ischeck=2 "
		        + "AND tr.date1>="
		        + beginStr
		        + " ) trip,"
		        + "(SELECT COUNT(att.lineid) FROM  EMPLOYATTENDANCE att "
		        + "WHERE t.lineid=att.userid AND att.ischeck=2 "
		        + " AND att.date1>="
		        + beginStr
		        + ") cqyc   "
		        + "FROM  SMUSER t,EMPLOYRECORD e WHERE t.recordid=e.lineid ";
		return sql;
	}
	
	private String getSqlByEndNotEmpty( String endStr )
	{
		String sql = "SELECT e.employname, (SELECT SUM(CEIL(h.date2-h.date1)) "
		        + "FROM EMPLOYHOLIDAY h WHERE t.lineid=h.userid AND h.ISCHECK=2" +

		        "  AND h.date2 <= "
		        + endStr
		        + ") holiday, "
		        + "( SELECT sum(CEIL((h2.date2-h2.date1)*24))  FROM EMPLOYOUT h2 "
		        + "WHERE  t.lineid=h2.userid AND h2.ISCHECK=2  "
		        + "  AND h2.date2 <= "
		        + endStr
		        + " ) outday,"
		        + "( SELECT SUM( CEIL((ov.date2-ov.date1)*24)) FROM EMPLOYOVERTIME ov "
		        + "WHERE t.lineid=ov.userid AND ov.ischeck=2 "
		        + "  AND ov.date2 <= "
		        + endStr
		        + " ) overtime,"
		        + "(SELECT SUM(CEIL(tr.date2-tr.date1)) FROM EMPLOYTRIP tr "
		        + "WHERE t.lineid=tr.userid AND tr.ischeck=2 "
		        + "  AND tr.date2 <= "
		        + endStr
		        + " ) trip,"
		        + "(SELECT COUNT(att.lineid) FROM  EMPLOYATTENDANCE att "
		        + "WHERE t.lineid=att.userid AND att.ischeck=2 "
		        + " AND att.date2 <= "
		        + endStr
		        + ") cqyc   "
		        + "FROM  SMUSER t,EMPLOYRECORD e WHERE t.recordid=e.lineid ";
		return sql;
	}
	
	private String getSql( int deptid )
	{
		
		String sql = "SELECT t.lineid,  e.employname, "
		        + " (SELECT SUM(h.days)  FROM EMPLOYHOLIDAY h "
		        + "   WHERE t.lineid = h.userid AND h.ISCHECK = 2) holiday, "
		        + "  (SELECT sum(CEIL((h2.date2 - h2.date1) * 24)) FROM EMPLOYOUT h2 "
		        + "    WHERE t.lineid = h2.userid AND h2.ISCHECK = 2) outday, "
		        + "  (SELECT SUM( CEIL((ov.date2-ov.date1)*24)) FROM EMPLOYOVERTIME ov "
		        + "    WHERE t.lineid = ov.userid AND ov.ischeck = 2 and ov.employovertimetypeid=1) overtime, "
		        + "   (SELECT SUM(ovweek.days) FROM EMPLOYOVERTIME ovweek "
		        + "   WHERE t.lineid = ovweek.userid AND ovweek.ischeck = 2 and ovweek.employovertimetypeid=2) overtimeWeek, "
		        + "   (SELECT SUM(jiaweek.days) FROM EMPLOYOVERTIME jiaweek "
		        + "   WHERE t.lineid = jiaweek.userid AND jiaweek.ischeck = 2 and jiaweek.employovertimetypeid=3) overtimejiaWeek, "
		        + "  (SELECT SUM(CEIL(tr.date2 - tr.date1))  FROM EMPLOYTRIP tr "
		        + "   WHERE t.lineid = tr.userid AND tr.ischeck = 2) trip, "
		        + " (SELECT COUNT(att.lineid) FROM EMPLOYATTENDANCE att "
		        + "   WHERE t.lineid = att.userid  AND att.ischeck = 2) cqyc "
		        + " FROM SMUSER t, EMPLOYRECORD e, dept d "
		        + " WHERE t.recordid = e.lineid  and e.status >0 "
		        + " and e.deptid = d.lineid "
		        + " and d.lineid in (select dt.lineid from dept dt  start with dt.lineid = "
		        + deptid + " connect by Prior dt.lineid = dt.pid)";
		
		return sql;
	}
	
	@RequestMapping( "/getInfoByusid" )
	public String getInfoByusid( HttpServletRequest request )
	{
		int usid = Integer.parseInt( request.getParameter( "usid" ) );
		String paramsType = request.getParameter( "params" );
		
		String begin = request.getParameter( "begin" );
		String end = request.getParameter( "end" );
		
		String beginStr = "";
		String endStr = "";
		if ( isNotEmpty( begin ) && ( ! "null".equals( begin ) ) )
		{
			beginStr = "to_date('" + begin + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}
		
		if ( isNotEmpty( end ) && ( "null".equals( end ) ) )
		{
			endStr = "to_date('" + end + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		else
		{
			endStr = "to_date('" + DateUtil.formatDate( new Date() )
			        + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		
		List< String > titlist = new ArrayList< String >();
		titlist.add( "姓名" );
		
		String sql = "";
		if ( paramsType.equals( "holiday" ) )
		{
			titlist.add( "开始时间" );
			titlist.add( "结束时间" );
			titlist.add( "天数" );
			titlist.add( "原因" );
			titlist.add( "请假类型" );
			
			sql = " select employ.lineid,emp.employname, employ.date1,employ.date2,employ.days,employ.reason,typ.typename "
			        + "	from employholiday employ,employrecord emp,employholidaytype typ "
			        + "	where emp.lineid = employ.recordid and employ.employholidaytype = typ.lineid and employ.ischeck=2 "
			        + "and employ.userid=" + usid;
			
			if ( isNotEmpty( beginStr ) )
			{
				sql += " and employ.date1 > " + beginStr + " and employ.date2 <= "
				        + endStr + " ";
			}
			
		}
		else if ( paramsType.equals( "layout" ) )
		{
			titlist.add( "开始时间" );
			titlist.add( "结束时间" );
			titlist.add( "原因" );
			sql = " select layout.lineid,emp.employname,layout.date1,layout.date2,layout.reason "
			        + "from employout layout,employrecord emp where layout.recordid = emp.lineid and layout.ischeck=2 "
			        + " and layout.userid=" + usid;
			
			if ( isNotEmpty( beginStr ) )
			{
				sql += " and layout.date1 > " + beginStr + " and layout.date2 <= "
				        + endStr + " ";
			}
		}
		else if ( paramsType.equals( "overtime" ) )
		{
			titlist.add( "开始时间" );
			titlist.add( "结束时间" );
			titlist.add( "小时" );
			titlist.add( "原因" );
			titlist.add( "加班类型" );
			sql = " select empover.lineid,emp.employname,empover.date1,empover.date2,CEIL((empover.date2-empover.date1)*24) opceil,empover.reason,typ.typename from employovertime empover,employrecord emp,employovertimetype typ "
			        + "	where empover.recordid = emp.lineid and  empover.employovertimetypeid = typ.lineid and empover.ischeck=2 and typ.lineid=1 "
			        + " and empover.userid=" + usid;
			if ( isNotEmpty( beginStr ) )
			{
				sql += " and empover.date1 >" + beginStr + " and empover.date2 <= "
				        + endStr + " ";
			}
			
		}
		else if ( paramsType.equals( "overtimeWeek" ) )
		{
			titlist.add( "开始时间" );
			titlist.add( "结束时间" );
			titlist.add( "天数" );
			titlist.add( "原因" );
			titlist.add( "加班类型" );
			sql = " select empover.lineid,emp.employname,empover.date1,empover.date2,empover.days,empover.reason,typ.typename from employovertime empover,employrecord emp,employovertimetype typ "
			        + "	where empover.recordid = emp.lineid and  empover.employovertimetypeid = typ.lineid and empover.ischeck=2  and typ.lineid=2 "
			        + " and empover.userid=" + usid;
			if ( isNotEmpty( beginStr ) )
			{
				sql += " and empover.date1 >" + beginStr + " and empover.date2 <= "
				        + endStr + " ";
			}
		}
		else if ( paramsType.equals( "overtimejiaWeek" ) )
		{
			titlist.add( "开始时间" );
			titlist.add( "结束时间" );
			titlist.add( "天数" );
			titlist.add( "原因" );
			titlist.add( "加班类型" );
			sql = " select empover.lineid,emp.employname,empover.date1,empover.date2,empover.days,empover.reason,typ.typename from employovertime empover,employrecord emp,employovertimetype typ "
			        + "	where empover.recordid = emp.lineid and  empover.employovertimetypeid = typ.lineid and empover.ischeck=2  and typ.lineid=3 "
			        + " and empover.userid=" + usid;
			if ( isNotEmpty( beginStr ) )
			{
				sql += " and empover.date1 >" + beginStr + " and empover.date2 <= "
				        + endStr + " ";
			}
		}
		else if ( paramsType.equals( "attendance" ) )
		{
			titlist.add( "异常时间" );
			titlist.add( "原因" );
			titlist.add( "异常类型" );
			sql = " select atten.lineid,emp.employname,atten.opdate,atten.reason,atten.typename from employattendance atten,employrecord emp where atten.recordid = emp.lineid and atten.ischeck=2 "
			        + " and atten.userid=" + usid;
			
			if ( isNotEmpty( beginStr ) )
			{
				sql += " and atten.opdate > " + beginStr + " and atten.opdate <= "
				        + endStr + "";
			}
			
		}
		else if ( paramsType.equals( "trip" ) )
		{
			titlist.add( "开始时间" );
			titlist.add( "结束时间" );
			titlist.add( "出差地址" );
			titlist.add( "原因" );
			titlist.add( "乘坐工具" );
			sql = " select trips.lineid,emp.employname,trips.date1,trips.date2,trips.address,trips.reason, tool.toolname from employtrip trips,employtriptool tool ,employrecord emp where trips.recordid = emp.lineid and trips.employtriptoolid = tool.lineid and trips.ischeck=2 "
			        + " and trips.userid=" + usid;
			
			if ( isNotEmpty( beginStr ) )
			{
				sql += " and trips.date1 >" + beginStr + " and trips.date2<=" + endStr
				        + " ";
			}
		}
		
		System.out.println( "CheckInfoController  getInfoByusid  sql " + sql );
		List datelist = emrService.getDateBySqlQuery( sql , 0 , 0 );
		System.out.println( "datelist size " + datelist.size() );
		request.setAttribute( "titlist" , titlist );
		request.setAttribute( "datelist" , datelist );
		return "oa_employcheckinfo/showinfoList";
	}
	
	// 查看单个请假条信息
	@RequestMapping( "/getCheckInfoExcel" )
	public void getCheckInfoExcel( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		int deptid = Integer.parseInt( request.getParameter( "deptid" ) );
		String begin = request.getParameter( "begin" );
		String end = request.getParameter( "end" );
		if ( request.getParameter( "deptid" ) != null
		        && request.getParameter( "deptid" ) != "" )
		{
			deptid = Integer.parseInt( request.getParameter( "deptid" ) );
		}
		String usname = request.getParameter( "usname" );
		String beginStr = "";
		String endStr = "";
		if ( isNotEmpty( begin ) && ( ! "null".equals( begin ) ) )
		{
			beginStr = "to_date('" + begin + " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
		}
		
		if ( isNotEmpty( end ) && ( "null".equals( end ) ) )
		{
			endStr = "to_date('" + end + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		else
		{
			endStr = "to_date('" + DateUtil.formatDate( new Date() )
			        + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String sql = "";
		if ( isNotEmpty( beginStr ) )
		{
			sql = getSqlByDoubleNotEmpty( beginStr , endStr , deptid );
		}
		else
		{
			sql = getSql( deptid );
		}
		
		if ( usname != null && "" != usname )
		{
			sql += "  and e.employname like '%" + usname + "%' ";
		}
		
		System.out.println( " exp   sql " + sql );
		List< String > headlist = new ArrayList< String >();
		headlist.add( "姓名" );
		headlist.add( "请假/天" );
		headlist.add( "外出/小时" );
		headlist.add( "拖班/小时" );
		headlist.add( "加班/天数" );
		headlist.add( "法假/天数" );
		headlist.add( "出差/天数" );
		headlist.add( "考勤次数" );
		
		List< Object[] > strslist = emrService.getDateBySqlQuery( sql , 0 , 0 );
		ExcelHelper.getCheckInfoExcel( request , response , headlist , strslist );
	}
	
}
