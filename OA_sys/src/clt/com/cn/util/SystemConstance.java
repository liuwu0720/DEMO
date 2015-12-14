package clt.com.cn.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * @Package clt.com.cn.util 
 * @Description:系统常量类
 * @author chengwzh 
 * @date 2015年10月27日 上午11:12:00 
 * @version V1.0 
 */
public class SystemConstance
{
	/**
	 * 合同审批流程KEY
	 */
	public static final String CONTRACT_FLOWKEY = "contract";
	/**
	 * 费用审批流程KEY
	 */
	public static final String COSTFEE_FLOWKEY = "costfee";
	/**
	 * 合同审批：邮件任务
	 */
	public static final String CONTRACT_EMAIL_FLOWKEY = "contractemail";
	/**
	 * 商务部经理
	 */
	public static final String BUSINESS_MANAGE = "zhouyp";
	
	public static final String BUSINESS_MANAGE_EMAIL = "zhouyp@unlcn.com";
	public static final String PRODUCT_MANAGE_EMAIL = "zhangyg@unlcn.com";
	public static final String PRESIDENT_ASSISTANT_EMAIL = "xuguang@unlcn.com";
	public static final String CAPACITY_MANAGE_EMAIL = "cuidy@unlcn.com";
	public static final String FINANCIAL_MANAGE_EMAIL = "yanyq@unlcn.com";
	public static final String PRESIDENT_EMAIL = "zhujh@unlcn.com";
	/*public static final String OA_ADDRESS = "http://222.126.229.155/oa/";// 正式数据库
	public static final String CONTRACT_MANAGE_EMAIL = "xuml@unlcn.com";   //正式 
*/
	/*public static final String BUSINESS_MANAGE_EMAIL = "liuwu@unlcn.com";
	public static final String PRODUCT_MANAGE_EMAIL = "liuwu@unlcn.com";
	public static final String PRESIDENT_ASSISTANT_EMAIL = "liuwu@unlcn.com";
	public static final String CAPACITY_MANAGE_EMAIL = "chengwzh@unlcn.com";
	public static final String FINANCIAL_MANAGE_EMAIL = "liuwu@unlcn.com";
	public static final String PRESIDENT_EMAIL = "liuwu@unlcn.com";*/
	public static final String OA_ADDRESS = "http://127.0.0.1:8080/OA_sys/";// 本地
	public static final String CONTRACT_MANAGE_EMAIL = "zhangyq@unlcn.com";//本地

	/**
	 * 产品规划部总监
	 */
	public static final String PRODUCT_MANAGE = "zhangyg";
	public static final String PRODUCT_MANAGE2 = "yhshi";
	/**
	 * 总裁助理
	 */
	public static final String PRESIDENT_ASSISTANT = "xuguang";
	
	/**
	 * 运力管理部
	 */
	public static final String CAPACITY_MANAGE = "cuidy";
	
	/**
	 * 财务总监
	 */
	public static final String FINANCIAL_MANAGE = "yanyq";
	public static final String FINANCIAL_MANAGE2 = "wenwen";
	public static final String FINANCIAL_MANAGE3 = "zhuli";
	/**
	 * 总裁
	 */
	public static final String PRESIDENT = "zhujh";
	
	/**
	 * 商务部合同专员
	 */
	public static final String CONTRACT_MANAGE = "xuml";
	//public static final String CONTRACT_MANAGE_EMAIL = "zhangyq@unlcn.com";
	/**
	 * 测试帐号邮箱
	 * 
	 */
	public static final String TEST_EMAIL = "liuwu@unlcn.com";
	public static final String TEST_EMAIL2 = "liuwu@unlcn.com";
	/**
	 * OA链接地址
	 */
	//
	
	// public static final String OA_ADDRESS =
	// "http://10.2.4.147:8080/OA_sys/";// 测试数据库
	/**
	 * OA通过邮件链接登录servlet
	 */
	public static final String OA_ADDRESS_CHECKLOGIN = "UserServlet/loginByCheck";
	
	public static void main( String[] args )
	{
		String levelString = "10,20,30";
		String[] leStrings = levelString.split( "," );
		String a = "10";
		List< String > aList = new ArrayList< String >();
		for ( String level : leStrings )
		{
			if ( Integer.parseInt( level ) > Integer.parseInt( a ) )
			{
				aList.add( level );
			}
		}
		String[] toBeStored = aList.toArray(new String[aList.size()]); 
		StringBuffer newString = new StringBuffer();
		for(String str:toBeStored){
			System.out.println(str);
			newString.append( str+",");
		}
		System.out.println(newString.deleteCharAt( newString.length()-1 ).toString());
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss" );
		String strDate = sdf.format( date );
		System.out.println(strDate);
		
	}
	
}
