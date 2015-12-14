package clt.com.cn.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * @Package clt.com.cn.util 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年11月2日 下午5:48:18 
 * @version V1.0 
 */
public class DateTest
{
	public static void main( String[] args ) throws ParseException
	{
		SimpleDateFormat myFormatter = new SimpleDateFormat( "yyyy-MM-dd" );
		java.util.Date date = myFormatter.parse( "2003-05-1" );
		java.util.Date mydate = myFormatter.parse( "1899-12-30" );
		long day = ( date.getTime() - mydate.getTime() ) / ( 24 * 60 * 60 * 1000 );
		System.out.println( day );
		int days = daysBetween(date,mydate);
		System.out.println(days);
	}
	
	private static int daysBetween( Date now , Date returnDate )
	{
		Calendar cNow = Calendar.getInstance();
		Calendar cReturnDate = Calendar.getInstance();
		cNow.setTime( now );
		cReturnDate.setTime( returnDate );
		setTimeToMidnight( cNow );
		setTimeToMidnight( cReturnDate );
		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;
		return millisecondsToDays( intervalMs );
	}
	
	private static int millisecondsToDays( long intervalMs )
	{
		return ( int ) ( intervalMs / ( 1000 * 86400 ) );
	}
	
	private static void setTimeToMidnight( Calendar calendar )
	{
		calendar.set( Calendar.HOUR_OF_DAY , 0 );
		calendar.set( Calendar.MINUTE , 0 );
		calendar.set( Calendar.SECOND , 0 );
	}
	
}
