package clt.com.cn.helps;

import java.util.HashMap;
import java.util.Map;

public class SystemStatus
{
	
	// 在个人桌面加载的时候会用到 hr部门id it部门id
	public static int hrdept = 4;
	public static int admindept = 2;
	
	public static int itdept = 11;
	// 公司id 人力id
	public static String hrstr = "12,19;27,30;31,33;35,140;44,48;";
	
	// 档案修改 审批人 总部人力经理 可做页面更改
	public static int getcheckRecord = 2676;
	// 初始化密码
	public static String initpwd = "123456";
	
	// 个人考勤状态
	public static Map getEmpSystemStatus()
	{
		Map< Integer , String > maps = new HashMap< Integer , String >();
		maps.put( 0 , "未审批" );
		maps.put( 1 , "未通过" );
		maps.put( 2 , "已通过" );
		return maps;
	}
	
	public static boolean getisHr( String comid )
	{
		boolean b = false;
		
		String strs[] = hrstr.split( ";" );
		for ( String s : strs )
		{
			if ( s.equals( comid ) )
			{
				b = true;
				break;
			}
		}
		// 返回公司id
		return b;
	}
	
	// 人力行政准备工作
	public static Map getHRprepare()
	{
		Map< Integer , String > maps = new HashMap< Integer , String >();
		maps.put( 1 , "座位安排" );
		maps.put( 2 , "电脑安排" );
		return maps;
	}
	
	public static Map getITprepare()
	{
		Map< Integer , String > maps = new HashMap< Integer , String >();
		maps.put( 11 , "OMS" );
		maps.put( 12 , "OA" );
		return maps;
	}
	
	// 工作年限 在招聘需求新增的时候会用到
	public static Map getworkyear()
	{
		Map< Integer , String > maps = new HashMap< Integer , String >();
		maps.put( 1 , "应届生" );
		maps.put( 2 , "一年" );
		maps.put( 3 , "一到三年" );
		maps.put( 4 , "三年以上" );
		return maps;
	}
	
	// 年龄范围 在招聘需求新增的时候会用到
	public static Map getagerange()
	{
		Map< Integer , String > maps = new HashMap< Integer , String >();
		maps.put( 1 , "20-25岁" );
		maps.put( 2 , "25-30岁" );
		maps.put( 3 , "35以上" );
		return maps;
	}
	
	public static Map getmarital()
	{
		Map< Integer , String > maps = new HashMap< Integer , String >();
		maps.put( 0 , "未选择" );
		maps.put( 1 , "未婚" );
		maps.put( 2 , "已婚" );
		return maps;
	}
	
	public static Map getAttendanceSta()
	{
		Map< Integer , String > maps = new HashMap< Integer , String >();
		maps.put( 1 , "请假待审批,EmployholidayServlet/getAllEhd?op=1" );
		maps.put( 2 , "外出待审批,EmployoutServlet/getAllEmo?op=1" );
		maps.put( 3 , "加班待审批,EmployovertimeServlet/getEhdByIscheck?op=1&ischeck=0" );
		maps.put( 4 , "考勤待审批,EmployattendanceServlet/getEhdByIscheck?op=1&ischeck=0" );
		maps.put( 5 , "出差待审批,EmploytripServlet/getEhdByIscheck?op=1&ischeck=0" );
		maps.put( 6 , "招聘待审批,EmployrecruitmentServlet/getEhdByIscheck?op=1&ischeck=0" );
		maps.put( 7 , "档案待审批,EmployrecordCheckServlet/getEhdByIscheck?op=1&ischeck=0" );
		return maps;
	}
	
	public static Map getnoticeSta()
	{
		Map< Integer , String > maps = new HashMap< Integer , String >();
		maps.put( 1 , "请假通知,EmployholidayServlet/getNewHoliday" );
		maps.put( 2 , "外出通知,EmployoutServlet/getNewOut" );
		maps.put( 3 , "加班通知,EmployovertimeServlet/getNewOverTime" );
		maps.put( 4 , "考勤通知,EmployattendanceServlet/getNewAttendance" );
		maps.put( 5 , "出差通知,EmploytripServlet/getNewTrip" );
		maps.put( 6 , "招聘通知,EmployrecruitmentServlet/getNewRecruitment" );
		maps.put( 7 , "档案通知,EmployrecordCheckServlet/getNewcordCheck" );
		return maps;
	}
}
