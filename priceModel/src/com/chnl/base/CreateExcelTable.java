/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-2-2 上午9:42:26
 * @version V1.0
 */
package com.chnl.base;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import com.chnl.entity.LegPriceSummary;
import com.chnl.entity.RouteLegTruckSummary;
import com.chnl.entity.RouteTruckSummary;
import com.chnl.pojo.CarInfo;
import com.chnl.pojo.LegInfo;
import com.chnl.pojo.Smcustomer;
import com.chnl.pojo.TruckInfo;

/**
 * @Package com.chnl.base
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-2-2 上午9:42:26
 * @version V1.0
 */
public class CreateExcelTable
{
	public static void main( String[] args )
	{
		CreateExcelTable.create( "test.xls" );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param string
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-30 下午2:34:23
	 */
	public static void create( String fileString )
	{
		// TODO Auto-generated method stub
		try
		{
			FileOutputStream out = new FileOutputStream( fileString );
			// create a new workbook
			Workbook wb = new HSSFWorkbook();
			// create a new sheet
			
			Sheet sheet = wb.createSheet();
			// 创建第一行
			Row row = sheet.createRow( 0 );
			// 创建第一行
			Row row1 = sheet.createRow( 2 );
			
			// 文件头字体
			Font font0 = createFonts( wb , Font.BOLDWEIGHT_BOLD , "宋体" , false ,
			        ( short ) 200 );
			Font font1 = createFonts( wb , Font.BOLDWEIGHT_NORMAL , "宋体" ,
			        false , ( short ) 200 );
			// 合并第一行的单元格
			// sheet.addMergedRegion( new CellRangeAddress( 0 , 0 , 0 , 1 ) );
			// 设置第一列的文字
			// createCell( wb , row , 0 , "总数" , font0 );
			// 合并第一行的2列以后到8列（不包含第二列）
			sheet.addMergedRegion( new CellRangeAddress( 0 , 1 , 0 , 8 ) );
			// 设置第二列的文字
			createCell( wb , row , 0 , "基本信息" , font0 );
			// 给第二行添加文本
			createCell( wb , row1 , 0 , "序号" , font1 );
			createCell( wb , row1 , 1 , "版本" , font1 );
			createCell( wb , row1 , 2 , "姓名" , font1 );
			createCell( wb , row1 , 3 , "性别" , font1 );
			createCell( wb , row1 , 4 , "年龄" , font1 );
			createCell( wb , row1 , 5 , "年级" , font1 );
			createCell( wb , row1 , 6 , "学校" , font1 );
			createCell( wb , row1 , 7 , "父母名称" , font1 );
			createCell( wb , row1 , 8 , "籍贯" , font1 );
			createCell( wb , row1 , 9 , "联系方式" , font1 );
			List< Smcustomer > smcustomers = getList();
			for ( int i = 0 ; i < smcustomers.size() ; i++ )
			{
				Row row2 = sheet.createRow( i + 3 );
				for ( Smcustomer smcustomer : smcustomers )
				{
					createCell( wb , row2 , 0 , smcustomer.getVcaddress() ,
					        font1 );
					createCell( wb , row2 , 1 , smcustomer.getVcaddress2() ,
					        font1 );
					createCell( wb , row2 , 2 , smcustomer.getVccityname() ,
					        font1 );
					createCell( wb , row2 , 3 , smcustomer.getVccityname() ,
					        font1 );
					createCell( wb , row2 , 4 , "年龄" , font1 );
					createCell( wb , row2 , 5 , "年级" , font1 );
					createCell( wb , row2 , 6 , "学校" , font1 );
					createCell( wb , row2 , 7 , "父母名称" , font1 );
					createCell( wb , row2 , 8 , "籍贯" , font1 );
					createCell( wb , row2 , 9 , "联系方式" , font1 );
				}
			}
			
			wb.setSheetName( 0 , "测试报告" );
			wb.write( out );
			out.close();
			
		}
		catch ( FileNotFoundException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( Exception e )
		{
			// TODO: handle exception
		}
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return List<Smcustomer> 返回值描述
	 * @author liuwu
	 * @create_date 2015-2-2 下午2:02:03
	 */
	private static List< Smcustomer > getList()
	{
		List< Smcustomer > smcustomers = new ArrayList< Smcustomer >();
		for ( int i = 0 ; i < 10 ; i++ )
		{
			Smcustomer smcustomer = new Smcustomer();
			smcustomer.setIlineid( i );
			smcustomer.setVccityname( "city" + i );
			smcustomer.setVccompanyname( "company" + i );
			smcustomer.setVccustomername( "customer" + i );
			smcustomer.setVccontact( "contact" + i );
			smcustomer.setVcaddress( "address" + i );
			smcustomer.setVcaddress2( "address2" + i );
			smcustomer.setVccustomerno( "cusno" + i );
			smcustomer.setVcprovince( "province" + i );
			smcustomers.add( smcustomer );
		}
		return smcustomers;
	}

	/**
	 * 创建单元格并设置样式,值
	 * 
	 * @param wb
	 * @param row
	 * @param column
	 * @param
	 * @param
	 * @param value
	 */

	public static void createCell( Workbook wb , Row row , int column ,
	        String value , Font font )
	{
		Cell cell = row.createCell( column );
		cell.setCellValue( value );
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment( XSSFCellStyle.ALIGN_CENTER );
		cellStyle.setVerticalAlignment( XSSFCellStyle.VERTICAL_BOTTOM );
		cellStyle.setFont( font );
		/*	cellStyle.setBorderBottom( CellStyle.BORDER_THIN );
			cellStyle.setBorderTop( CellStyle.BORDER_THIN );
			cellStyle.setBorderLeft( CellStyle.BORDER_THIN );
			cellStyle.setBorderRight( CellStyle.BORDER_THIN );*/
		cell.setCellStyle( cellStyle );
	}
	
	/**
	 * @Description: TODO(创建字体)
	 * @param wb
	 * @param boldweightBold
	 * @param string
	 * @param b
	 * @param s
	 * @return Font 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-30 下午4:56:05
	 */
	public static Font createFonts( Workbook wb , short bold , String fontName ,
	        boolean isItalic , short hight )
	{
		Font font = wb.createFont();
		font.setFontName( fontName );
		font.setBoldweight( bold );
		font.setItalic( isItalic );
		font.setFontHeight( hight );
		return font;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return Workbook 返回值描述
	 * @author liuwu
	 * @create_date 2015-2-3 下午4:19:28
	 */
	public static Workbook exportFile()
	{
		Workbook wb = new HSSFWorkbook();
		// create a new sheet
		
		Sheet sheet = wb.createSheet();
		// 创建第一行
		Row row = sheet.createRow( 0 );
		// 创建第一行
		Row row1 = sheet.createRow( 2 );
		
		// 文件头字体
		Font font0 = createFonts( wb , Font.BOLDWEIGHT_BOLD , "宋体" , false ,
		        ( short ) 200 );
		Font font1 = createFonts( wb , Font.BOLDWEIGHT_NORMAL , "宋体" , false ,
		        ( short ) 200 );
		// 合并第一行的单元格
		// sheet.addMergedRegion( new CellRangeAddress( 0 , 0 , 0 , 1 ) );
		// 设置第一列的文字
		// createCell( wb , row , 0 , "总数" , font0 );
		// 合并第一行的2列以后到8列（不包含第二列）
		sheet.addMergedRegion( new CellRangeAddress( 0 , 1 , 0 , 8 ) );
		// 设置第二列的文字
		createCell( wb , row , 0 , "基本信息" , font0 );
		// 给第二行添加文本
		createCell( wb , row1 , 0 , "序号" , font1 );
		createCell( wb , row1 , 1 , "版本" , font1 );
		createCell( wb , row1 , 2 , "姓名" , font1 );
		createCell( wb , row1 , 3 , "性别" , font1 );
		createCell( wb , row1 , 4 , "年龄" , font1 );
		createCell( wb , row1 , 5 , "年级" , font1 );
		createCell( wb , row1 , 6 , "学校" , font1 );
		createCell( wb , row1 , 7 , "父母名称" , font1 );
		createCell( wb , row1 , 8 , "籍贯" , font1 );
		createCell( wb , row1 , 9 , "联系方式" , font1 );
		List< Smcustomer > smcustomers = getList();
		for ( int i = 0 ; i < smcustomers.size() ; i++ )
		{
			Row row2 = sheet.createRow( i + 3 );
			for ( Smcustomer smcustomer : smcustomers )
			{
				createCell( wb , row2 , 0 , smcustomer.getVcaddress() , font1 );
				createCell( wb , row2 , 1 , smcustomer.getVcaddress2() , font1 );
				createCell( wb , row2 , 2 , smcustomer.getVccityname() , font1 );
				createCell( wb , row2 , 3 , smcustomer.getVccityname() , font1 );
				createCell( wb , row2 , 4 , "年龄" , font1 );
				createCell( wb , row2 , 5 , "年级" , font1 );
				createCell( wb , row2 , 6 , "学校" , font1 );
				createCell( wb , row2 , 7 , "父母名称" , font1 );
				createCell( wb , row2 , 8 , "籍贯" , font1 );
				createCell( wb , row2 , 9 , "联系方式" , font1 );
			}
		}
		
		wb.setSheetName( 0 , "测试报告" );
		return wb;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param smUser
	 * @param truckInfo
	 * @param routeTruckSummaryList
	 * @return Workbook 返回值描述
	 * @author liuwu
	 * @param getcaculatevalue
	 * @param truckEmptyCost
	 * @param truckInfo2
	 * @create_date 2015-2-3 下午4:48:39
	 */
	public static Workbook exportBaseFile( String user , TruckInfo truckInfo ,
	        List< RouteTruckSummary > routeTruckSummaryList ,
	        String getcaculatetype , String getcaculatevalue ,
	        String truckEmptyCost )
	{
		Workbook wb = new HSSFWorkbook();
		/**
		 * 正文（计算方式、拖车、线路）
		 */
		if ( getcaculatetype.equals( "totalvenprofit" ) )
		{
			getcaculatetype = "分供方利润/月";
		}
		else if ( getcaculatetype.equals( "venprofit" ) )
		{
			getcaculatetype = "分供方占比";
		}
		else if ( getcaculatetype.equals( "unionprofit" ) )
		{
			getcaculatetype = "中联物流占比";
		}
		
		Sheet sheet = wb.createSheet();
		// 创建第一行
		Row row = sheet.createRow( 0 );
		// 创建第二行
		Row row1 = sheet.createRow( 2 );
		// 创建第三行
		Row row2 = sheet.createRow( 4 );
		// 创建第4行
		Row row3 = sheet.createRow( 6 );
		// 创建第5行
		Row row4 = sheet.createRow( 10 );
		// 创建第6行
		Row row5 = sheet.createRow( 12 );
		// 文件头字体
		Font font0 = createFonts( wb , Font.BOLDWEIGHT_BOLD , "宋体" , false ,
		        ( short ) 200 );
		Font font1 = createFonts( wb , Font.BOLDWEIGHT_NORMAL , "宋体" , false ,
		        ( short ) 200 );
		Date d = new Date();
		SimpleDateFormat nowTime = new SimpleDateFormat(
		        "yyyy 年 MM月 dd 日 HH ：mm ：ss" );
		String nowString = nowTime.format( d );
		// 合并样式
		sheet.addMergedRegion( new CellRangeAddress( 0 , 1 , 0 , 1 ) );// 操作用户
		sheet.addMergedRegion( new CellRangeAddress( 0 , 1 , 2 , 3 ) );
		
		sheet.addMergedRegion( new CellRangeAddress( 2 , 3 , 0 , 1 ) );// 操作时间
		sheet.addMergedRegion( new CellRangeAddress( 2 , 3 , 2 , 5 ) );
		
		sheet.addMergedRegion( new CellRangeAddress( 4 , 5 , 0 , 1 ) );// 计算方式
		sheet.addMergedRegion( new CellRangeAddress( 4 , 5 , 2 , 5 ) );
		
		sheet.addMergedRegion( new CellRangeAddress( 6 , 7 , 0 , 1 ) );// 所选拖车
		sheet.addMergedRegion( new CellRangeAddress( 6 , 7 , 2 , 5 ) );
		
		sheet.addMergedRegion( new CellRangeAddress( 10 , 11 , 2 , 5 ) );// 所选线路
		sheet.addMergedRegion( new CellRangeAddress( 12 , 12 , 1 , 3 ) );// 起始地提车库
		sheet.addMergedRegion( new CellRangeAddress( 12 , 12 , 4 , 6 ) );// 客户
		sheet.addMergedRegion( new CellRangeAddress( 12 , 12 , 13 , 15 ) );// 实际里程
		// 设置第一行的文字
		createCell( wb , row , 0 , "操作用户" , font0 );
		createCell( wb , row , 2 , user , font1 );
		createCell( wb , row1 , 0 , "操作时间" , font0 );
		createCell( wb , row1 , 2 , nowString , font1 );
		createCell( wb , row2 , 0 , "计算方式" , font0 );
		createCell( wb , row2 , 2 , getcaculatetype + "[" + getcaculatevalue
		        + "]" , font1 );
		createCell( wb , row3 , 0 , "运输车" , font0 );
		createCell( wb , row3 , 2 , truckInfo.getTrucktype() + "[ 单公里空载成本="
		        + truckEmptyCost + "]" , font1 );
		
		// 创建所选线路
		createCell( wb , row4 , 2 , "所选线路" , font0 );
		createCell( wb , row5 , 0 , "起始地" , font0 );
		createCell( wb , row5 , 1 , "起始地提车库" , font0 );
		createCell( wb , row5 , 4 , "客户" , font0 );
		createCell( wb , row5 , 7 , "目的地" , font0 );
		createCell( wb , row5 , 8 , "备注" , font0 );
		createCell( wb , row5 , 9 , "收入里程" , font0 );
		createCell( wb , row5 , 10 , "空载里程" , font0 );
		createCell( wb , row5 , 11 , "应付公里" , font0 );
		createCell( wb , row5 , 12 , "绕城公里" , font0 );
		createCell( wb , row5 , 13 , "实际里程（包含绕城公里）" , font0 );
		createCell( wb , row5 , 16 , "平均装载量" , font0 );
		List< LegInfo > allLegs = new ArrayList< LegInfo >();
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{
			
			List< LegInfo > legInfos = routeTruckSummary.getRouteSummary()
			        .getLegInfos();
			allLegs.addAll( legInfos );
			
		}
		for ( int i = 0 ; i < allLegs.size() ; i++ )
		{
			int indexrow = ( i + 13 );
			sheet.addMergedRegion( new CellRangeAddress( indexrow , indexrow ,
			        13 , 15 ) );// 实际里程
			Row rowindex = sheet.createRow( indexrow );
			sheet.addMergedRegion( new CellRangeAddress( indexrow , indexrow ,
			        1 , 3 ) );// 起始地提车库
			sheet.addMergedRegion( new CellRangeAddress( indexrow , indexrow ,
			        4 , 6 ) );// 客户
			createCell( wb , rowindex , 0 , allLegs.get( i ).getOrigin() ,
			        font1 );
			createCell( wb , rowindex , 1 , allLegs.get( i ).getStartPoint() ,
			        font1 );
			createCell( wb , rowindex , 4 , allLegs.get( i ).getVcCustomer() ,
			        font1 );
			createCell( wb , rowindex , 7 , allLegs.get( i ).getDestination() ,
			        font1 );
			if ( allLegs.get( i ).getEmptlyFlag().equals( 1 ) )
			{
				createCell( wb , rowindex , 8 , "空载" , font1 );
			}
			else
			{
				createCell( wb , rowindex , 8 , "重载" , font1 );
			}
			createCell( wb , rowindex , 9 , String.format( "%.3f" , allLegs
			        .get( i ).getIncomeDistance() ) , font1 );
			createCell( wb , rowindex , 10 , String.format( "%.3f" , allLegs
			        .get( i ).getEmptyDistance() ) , font1 );
			createCell(
			        wb ,
			        rowindex ,
			        11 ,
			        String.format( "%.3f" , allLegs.get( i ).getCostDistance() ) ,
			        font1 );
			createCell( wb , rowindex , 12 , String.format( "%.3f" , allLegs
			        .get( i ).getAroundDistance() ) , font1 );
			createCell( wb , rowindex , 13 , String.format( "%.3f" , allLegs
			        .get( i ).getActualDistance() ) , font1 );
			createCell( wb , rowindex , 16 , String.format( "%.3f" , allLegs
			        .get( i ).getAvgCarsPerTruckCombo() ) , font1 );
		}
		wb.setSheetName( 0 , "测试报告" );
		return wb;
	}

	/**
	 * @Description: TODO(实际成本)
	 * @param wb
	 *            void 返回值描述
	 * @author liuwu
	 * @param routeTruckSummaryList
	 * @param routeLegTruckSummary_list
	 * @create_date 2015-2-5 下午4:58:05
	 */
	public static int createActualCost( Workbook wb ,
	        List< RouteLegTruckSummary > routeLegTruckSummary_list ,
	        List< RouteTruckSummary > routeTruckSummaryList )
	{
		
		Sheet sheet = wb.createSheet();
		// 创建第一行
		Row row0 = sheet.createRow( 0 );
		Row row = sheet.createRow( 1 );
		// 文件头字体
		Font font0 = createFonts( wb , Font.BOLDWEIGHT_BOLD , "宋体" , false ,
		        ( short ) 200 );
		Font font1 = createFonts( wb , Font.BOLDWEIGHT_NORMAL , "宋体" , false ,
		        ( short ) 200 );
		sheet.addMergedRegion( new CellRangeAddress( 0 , 0 , 0 , 6 ) );
		createCell( wb , row0 , 0 , "实际成本" , font0 );
		createCell( wb , row , 0 , "起始地城市" , font0 );
		createCell( wb , row , 1 , "起始地提车库" , font0 );
		createCell( wb , row , 2 , "客户" , font0 );
		createCell( wb , row , 3 , "目的地城市" , font0 );
		createCell( wb , row , 4 , "/板/公里" , font0 );
		createCell( wb , row , 5 , "/板" , font0 );
		createCell( wb , row , 6 , "/辆/公里" , font0 );
		createCell( wb , row , 7 , "/辆" , font0 );
		createCell( wb , row , 8 , "/月" , font0 );

		int index = 1;
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{
			
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
			{
				if ( routeTruckSummary
				        .getRouteSummary()
				        .getId()
				        .equals( routeLegTruckSummary.getRouteSummary().getId() ) )
				{
					index++ ;
					Row row2 = sheet.createRow( index );
					LegInfo legInfo = routeLegTruckSummary.getLegInfo();
					createCell( wb , row2 , 0 , legInfo.getOrigin() , font1 );
					createCell( wb , row2 , 1 , legInfo.getStartPoint() , font1 );
					createCell( wb , row2 , 2 , legInfo.getVcCustomer() , font1 );
					createCell( wb , row2 , 3 , legInfo.getDestination() ,
					        font1 );
					createCell( wb , row2 , 4 , String.format( "%.3f" ,
					        routeLegTruckSummary.getActualCost_Truck_km() ) ,
					        font1 );
					createCell( wb , row2 , 5 , String.format( "%.3f" ,
					        routeLegTruckSummary.getActualCost_truck_per() ) ,
					        font1 );
					createCell( wb , row2 , 6 , String.format( "%.3f" ,
					        routeLegTruckSummary.getActualCost_Car_km() ) ,
					        font1 );
					createCell(
					        wb ,
					        row2 ,
					        7 ,
					        String.format( "%.3f" ,
					                routeLegTruckSummary.getActualCost_car() ) ,
					        font1 );
					createCell(
					        wb ,
					        row2 ,
					        8 ,
					        String.format( "%.0f" ,
					                routeLegTruckSummary.getActualCost_Month() ) ,
					        font1 );

				}

			}
			index++ ;
			Row row3 = sheet.createRow( index );
			sheet.addMergedRegion( new CellRangeAddress( index , index , 0 , 3 ) );
			createCell( wb , row3 , 0 , "统计" , font1 );
			createCell( wb , row3 , 1 , "" , font1 );
			createCell( wb , row3 , 2 , "" , font1 );
			createCell( wb , row3 , 3 , "" , font1 );
			createCell(
			        wb ,
			        row3 ,
			        4 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalActualCost_truck_km() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        5 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalActualCost_truck() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        6 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalActualCost_car_km() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        7 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalActualCost_car() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        8 ,
			        String.format( "%.0f" ,
			                routeTruckSummary.getTotalActualCost_month() ) ,
			        font1 );

		}
		wb.setSheetName( 1 , "实际成本" );

		return index;
		
	}
	
	/**
	 * @Description: TODO(新的采购价)
	 * @param currentIndex
	 * @param routeLegTruckSummary_list
	 * @param routeTruckSummaryList
	 *            void 返回值描述
	 * @author liuwu
	 * @param wb
	 * @create_date 2015-2-6 下午2:21:33
	 */
	public static void getNewProCostTable( Workbook wb ,
	        List< RouteLegTruckSummary > routeLegTruckSummary_list ,
	        List< RouteTruckSummary > routeTruckSummaryList )
	{

		Sheet sheet = wb.createSheet();
		// 文件头字体
		Font font0 = createFonts( wb , Font.BOLDWEIGHT_BOLD , "宋体" , false ,
		        ( short ) 200 );
		Font font1 = createFonts( wb , Font.BOLDWEIGHT_NORMAL , "宋体" , false ,
		        ( short ) 200 );
		Row row0 = sheet.createRow( 0 );
		Row row = sheet.createRow( 1 );
		createCell( wb , row0 , 0 , "新的采购价" , font0 );
		sheet.addMergedRegion( new CellRangeAddress( 0 , 0 , 0 , 6 ) );
		createCell( wb , row , 0 , "起始地城市" , font0 );
		createCell( wb , row , 1 , "起始地提车库" , font0 );
		createCell( wb , row , 2 , "客户" , font0 );
		createCell( wb , row , 3 , "目的地城市" , font0 );
		createCell( wb , row , 4 , "/板/公里" , font0 );
		createCell( wb , row , 5 , "/板" , font0 );
		createCell( wb , row , 6 , "/辆/公里" , font0 );
		createCell( wb , row , 7 , "/辆" , font0 );
		createCell( wb , row , 8 , "/月" , font0 );

		int index2 = 1;
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{

			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
			{
				if ( routeTruckSummary
				        .getRouteSummary()
				        .getId()
				        .equals( routeLegTruckSummary.getRouteSummary().getId() ) )
				{
					index2++ ;
					Row row2 = sheet.createRow( index2 );
					LegInfo legInfo = routeLegTruckSummary.getLegInfo();
					createCell( wb , row2 , 0 , legInfo.getOrigin() , font1 );
					createCell( wb , row2 , 1 , legInfo.getStartPoint() , font1 );
					createCell( wb , row2 , 2 , legInfo.getVcCustomer() , font1 );
					createCell( wb , row2 , 3 , legInfo.getDestination() ,
					        font1 );
					// /板/公里
					createCell( wb , row2 , 4 , String.format( "%.3f" ,
					        routeLegTruckSummary.getNewProCost_truck_km() ) ,
					        font1 );
					// /板
					createCell( wb , row2 , 5 , String.format( "%.3f" ,
					        routeLegTruckSummary.getNewProCost_truck_per() ) ,
					        font1 );
					// /辆/公里
					createCell( wb , row2 , 6 , String.format( "%.3f" ,
					        routeLegTruckSummary.getNewProCost_car_km() ) ,
					        font1 );
					// /辆
					createCell(
					        wb ,
					        row2 ,
					        7 ,
					        String.format( "%.3f" ,
					                routeLegTruckSummary.getNewProCost_car() ) ,
					        font1 );
					// 每月
					createCell(
					        wb ,
					        row2 ,
					        8 ,
					        String.format( "%.0f" ,
					                routeLegTruckSummary.getNewProCost_month() ) ,
					        font1 );

				}
			}
			index2++ ;
			Row row3 = sheet.createRow( index2 );
			sheet.addMergedRegion( new CellRangeAddress( index2 , index2 , 0 ,
			        3 ) );
			createCell( wb , row3 , 0 , "统计" , font1 );
			createCell( wb , row3 , 1 , "" , font1 );
			createCell( wb , row3 , 2 , "" , font1 );
			createCell( wb , row3 , 3 , "" , font1 );
			createCell(
			        wb ,
			        row3 ,
			        4 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalNewProCost_truck_km() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        5 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalNewProCost_truck() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        6 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalNewProCost_car_km() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        7 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalNewProCost_car() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        8 ,
			        String.format( "%.0f" ,
			                routeTruckSummary.getTotalNewProCost_month() ) ,
			        font1 );
			
		}

		wb.setSheetName( 2 , "新的采购价" );
	}
	
	/**
	 * @Description: TODO(当前采购支出)
	 * @param wb
	 * @param routeLegTruckSummary_list
	 * @param routeTruckSummaryList
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-2-9 上午11:06:19
	 */
	public static void getCurrentProCostTable( Workbook wb ,
	        List< RouteLegTruckSummary > routeLegTruckSummary_list ,
	        List< RouteTruckSummary > routeTruckSummaryList )
	{
		Sheet sheet = wb.createSheet();
		// 文件头字体
		Font font0 = createFonts( wb , Font.BOLDWEIGHT_BOLD , "宋体" , false ,
		        ( short ) 200 );
		Font font1 = createFonts( wb , Font.BOLDWEIGHT_NORMAL , "宋体" , false ,
		        ( short ) 200 );
		Row row0 = sheet.createRow( 0 );
		Row row = sheet.createRow( 1 );
		createCell( wb , row0 , 0 , "当前采购支出" , font0 );
		sheet.addMergedRegion( new CellRangeAddress( 0 , 0 , 0 , 6 ) );
		createCell( wb , row , 0 , "起始地城市" , font0 );
		createCell( wb , row , 1 , "起始地提车库" , font0 );
		createCell( wb , row , 2 , "客户" , font0 );
		createCell( wb , row , 3 , "目的地城市" , font0 );
		createCell( wb , row , 4 , "/板/公里" , font0 );
		createCell( wb , row , 5 , "/板" , font0 );
		createCell( wb , row , 6 , "/辆/公里" , font0 );
		createCell( wb , row , 7 , "/月" , font0 );
		createCell( wb , row , 8 , "/板" , font0 );
		int index2 = 1;
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{
			
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
			{
				if ( routeTruckSummary
				        .getRouteSummary()
				        .getId()
				        .equals( routeLegTruckSummary.getRouteSummary().getId() ) )
				{
					index2++ ;
					Row row2 = sheet.createRow( index2 );
					LegInfo legInfo = routeLegTruckSummary.getLegInfo();
					createCell( wb , row2 , 0 , legInfo.getOrigin() , font1 );
					createCell( wb , row2 , 1 , legInfo.getStartPoint() , font1 );
					createCell( wb , row2 , 2 , legInfo.getVcCustomer() , font1 );
					createCell( wb , row2 , 3 , legInfo.getDestination() ,
					        font1 );
					// /板/公里
					createCell( wb , row2 , 4 , String.format( "%.3f" ,
					        routeLegTruckSummary.getCurProCost_truck_km() ) ,
					        font1 );
					// /板
					createCell( wb , row2 , 5 , String.format( "%.3f" ,
					        routeLegTruckSummary.getCurProCost_truck_per() ) ,
					        font1 );
					// /辆/公里
					createCell( wb , row2 , 6 , String.format( "%.3f" ,
					        routeLegTruckSummary.getCurProCost_car_km() ) ,
					        font1 );
					// /辆
					createCell(
					        wb ,
					        row2 ,
					        7 ,
					        String.format( "%.3f" ,
					                routeLegTruckSummary.getCurProCost_car() ) ,
					        font1 );
					// 每月
					createCell(
					        wb ,
					        row2 ,
					        8 ,
					        String.format( "%.0f" ,
					                routeLegTruckSummary.getCurProCost_month() ) ,
					        font1 );

				}
			}
			index2++ ;
			Row row3 = sheet.createRow( index2 );
			sheet.addMergedRegion( new CellRangeAddress( index2 , index2 , 0 ,
			        3 ) );
			createCell( wb , row3 , 0 , "统计" , font1 );
			createCell( wb , row3 , 1 , "" , font1 );
			createCell( wb , row3 , 2 , "" , font1 );
			createCell( wb , row3 , 3 , "" , font1 );
			createCell(
			        wb ,
			        row3 ,
			        4 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalCurProCost_truck_km() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        5 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalCurProCost_truk() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        6 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalCurProCost_car_km() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        7 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalCurProCost_car() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        8 ,
			        String.format( "%.0f" ,
			                routeTruckSummary.getTotalCurProCost_month() ) ,
			        font1 );
			
		}
		
		wb.setSheetName( 3 , "当前采购支出" );
		
	}
	
	/**
	 * @Description: TODO(平均收入价格)
	 * @param routeLegTruckSummary_list
	 * @param routeTruckSummaryList
	 *            void 返回值描述
	 * @author liuwu
	 * @param wb
	 * @create_date 2015-2-9 上午11:34:49
	 */
	public static void getAvgIncomePrice( Workbook wb ,
	        List< RouteLegTruckSummary > routeLegTruckSummary_list ,
	        List< RouteTruckSummary > routeTruckSummaryList )
	{
		Sheet sheet = wb.createSheet();
		// 文件头字体
		Font font0 = createFonts( wb , Font.BOLDWEIGHT_BOLD , "宋体" , false ,
		        ( short ) 200 );
		Font font1 = createFonts( wb , Font.BOLDWEIGHT_NORMAL , "宋体" , false ,
		        ( short ) 200 );
		Row row0 = sheet.createRow( 0 );
		Row row = sheet.createRow( 1 );
		createCell( wb , row0 , 0 , "平均收入价格" , font0 );
		sheet.addMergedRegion( new CellRangeAddress( 0 , 0 , 0 , 6 ) );
		createCell( wb , row , 0 , "起始地城市" , font0 );
		createCell( wb , row , 1 , "起始地提车库" , font0 );
		createCell( wb , row , 2 , "客户" , font0 );
		createCell( wb , row , 3 , "目的地城市" , font0 );
		createCell( wb , row , 4 , "/板/公里" , font0 );
		createCell( wb , row , 5 , "/板" , font0 );
		createCell( wb , row , 6 , "/辆/公里" , font0 );
		createCell( wb , row , 7 , "/辆" , font0 );
		createCell( wb , row , 8 , "/月" , font0 );

		int index2 = 1;
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{
			
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
			{
				if ( routeTruckSummary
				        .getRouteSummary()
				        .getId()
				        .equals( routeLegTruckSummary.getRouteSummary().getId() ) )
				{
					index2++ ;
					Row row2 = sheet.createRow( index2 );
					LegInfo legInfo = routeLegTruckSummary.getLegInfo();
					createCell( wb , row2 , 0 , legInfo.getOrigin() , font1 );
					createCell( wb , row2 , 1 , legInfo.getStartPoint() , font1 );
					createCell( wb , row2 , 2 , legInfo.getVcCustomer() , font1 );
					createCell( wb , row2 , 3 , legInfo.getDestination() ,
					        font1 );
					// /板/公里
					createCell( wb , row2 , 4 ,
					        String.format( "%.3f" , routeLegTruckSummary
					                .getAvgIncomePrice_truck_km() ) , font1 );
					// 每板
					createCell( wb , row2 , 5 ,
					        String.format( "%.0f" , routeLegTruckSummary
					                .getAvgIncomePrice_truck_per() ) , font1 );
					// 辆每公里
					createCell( wb , row2 , 6 , String.format( "%.3f" ,
					        routeLegTruckSummary.getAvgIncomePrice_car_km() ) ,
					        font1 );
					// /辆
					createCell( wb , row2 , 7 , String.format( "%.3f" ,
					        routeLegTruckSummary.getAvgIncomePrice_car() ) ,
					        font1 );
					// 每月
					createCell( wb , row2 , 8 , String.format( "%.0f" ,
					        routeLegTruckSummary.getAvgIncomePrice_month() ) ,
					        font1 );

				}
			}
			index2++ ;
			Row row3 = sheet.createRow( index2 );
			sheet.addMergedRegion( new CellRangeAddress( index2 , index2 , 0 ,
			        3 ) );
			createCell( wb , row3 , 0 , "统计" , font1 );
			createCell( wb , row3 , 1 , "" , font1 );
			createCell( wb , row3 , 2 , "" , font1 );
			createCell( wb , row3 , 3 , "" , font1 );
			createCell(
			        wb ,
			        row3 ,
			        4 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalAvgInPrice_truck_km() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        5 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalAvgInPrice_truck() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        6 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalAvgInPrice_car_km() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        7 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalAvgInPrice_car() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        8 ,
			        String.format( "%.0f" ,
			                routeTruckSummary.getTotalAvgInPrice_month() ) ,
			        font1 );
			
		}
		
		wb.setSheetName( 4 , "平均收入价格" );
		
	}
	
	/**
	 * @Description: TODO(新方案下供应商利润)
	 * @param wb
	 * @param routeLegTruckSummary_list
	 * @param routeTruckSummaryList
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-2-9 下午2:21:21
	 */
	public static void getNewVendorProfitTable( Workbook wb ,
	        List< RouteLegTruckSummary > routeLegTruckSummary_list ,
	        List< RouteTruckSummary > routeTruckSummaryList )
	{
		Sheet sheet = wb.createSheet();
		// 文件头字体
		Font font0 = createFonts( wb , Font.BOLDWEIGHT_BOLD , "宋体" , false ,
		        ( short ) 200 );
		Font font1 = createFonts( wb , Font.BOLDWEIGHT_NORMAL , "宋体" , false ,
		        ( short ) 200 );
		Row row0 = sheet.createRow( 0 );
		Row row = sheet.createRow( 1 );
		createCell( wb , row0 , 0 , "新方案下供应商利润" , font0 );
		sheet.addMergedRegion( new CellRangeAddress( 0 , 0 , 0 , 6 ) );
		createCell( wb , row , 0 , "起始地城市" , font0 );
		createCell( wb , row , 1 , "起始地提车库" , font0 );
		createCell( wb , row , 2 , "客户" , font0 );
		createCell( wb , row , 3 , "目的地城市" , font0 );
		createCell( wb , row , 4 , "/板/公里" , font0 );
		createCell( wb , row , 5 , "/板" , font0 );
		createCell( wb , row , 6 , "/月" , font0 );
		createCell( wb , row , 7 , "利润率" , font0 );

		int index2 = 1;
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{
			
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
			{
				if ( routeTruckSummary
				        .getRouteSummary()
				        .getId()
				        .equals( routeLegTruckSummary.getRouteSummary().getId() ) )
				{
					index2++ ;
					Row row2 = sheet.createRow( index2 );
					LegInfo legInfo = routeLegTruckSummary.getLegInfo();
					createCell( wb , row2 , 0 , legInfo.getOrigin() , font1 );
					createCell( wb , row2 , 1 , legInfo.getStartPoint() , font1 );
					createCell( wb , row2 , 2 , legInfo.getVcCustomer() , font1 );
					createCell( wb , row2 , 3 , legInfo.getDestination() ,
					        font1 );
					// 板/公里
					createCell( wb , row2 , 4 , String.format( "%.3f" ,
					        routeLegTruckSummary.getVendorProfit_Truck_km() ) ,
					        font1 );
					// 每板
					createCell( wb , row2 , 5 , String.format( "%.3f" ,
					        routeLegTruckSummary.getVendorProfit_truck_per() ) ,
					        font1 );
					// 辆每月
					createCell( wb , row2 , 6 , String.format( "%.3f" ,
					        routeLegTruckSummary.getVendorProfit_Month() ) ,
					        font1 );
					// getVendorProfit_percent
					createCell( wb , row2 , 7 , String.format( "%.3f" ,
					        routeLegTruckSummary.getVendorProfit_percent() ) ,
					        font1 );

				}
			}
			index2++ ;
			Row row3 = sheet.createRow( index2 );
			sheet.addMergedRegion( new CellRangeAddress( index2 , index2 , 0 ,
			        3 ) );
			createCell( wb , row3 , 0 , "统计" , font1 );
			createCell( wb , row3 , 1 , "" , font1 );
			createCell( wb , row3 , 2 , "" , font1 );
			createCell( wb , row3 , 3 , "" , font1 );
			createCell(
			        wb ,
			        row3 ,
			        4 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalVenPro_truck_km() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        5 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalVenPro_truck() ) , font1 );
			createCell(
			        wb ,
			        row3 ,
			        6 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalVenPro_month() ) , font1 );
			createCell(
			        wb ,
			        row3 ,
			        7 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalVendorPro_percent() ) ,
			        font1 );
			
		}
		
		wb.setSheetName( 5 , "新方案下供应商利润" );
		
	}
	
	/**
	 * @Description: TODO(当前供应商利润)
	 * @param wb
	 * @param routeLegTruckSummary_list
	 * @param routeTruckSummaryList
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-2-9 下午2:29:04
	 */
	public static void getCurVenProTable( Workbook wb ,
	        List< RouteLegTruckSummary > routeLegTruckSummary_list ,
	        List< RouteTruckSummary > routeTruckSummaryList )
	{
		Sheet sheet = wb.createSheet();
		// 文件头字体
		Font font0 = createFonts( wb , Font.BOLDWEIGHT_BOLD , "宋体" , false ,
		        ( short ) 200 );
		Font font1 = createFonts( wb , Font.BOLDWEIGHT_NORMAL , "宋体" , false ,
		        ( short ) 200 );
		Row row0 = sheet.createRow( 0 );
		Row row = sheet.createRow( 1 );
		createCell( wb , row0 , 0 , "当前供应商利润" , font0 );
		sheet.addMergedRegion( new CellRangeAddress( 0 , 0 , 0 , 6 ) );
		createCell( wb , row , 0 , "起始地城市" , font0 );
		createCell( wb , row , 1 , "起始地提车库" , font0 );
		createCell( wb , row , 2 , "客户" , font0 );
		createCell( wb , row , 3 , "目的地城市" , font0 );
		createCell( wb , row , 4 , "/板/公里" , font0 );
		createCell( wb , row , 5 , "/板" , font0 );
		createCell( wb , row , 6 , "/月" , font0 );
		createCell( wb , row , 7 , "利润率" , font0 );

		int index2 = 1;
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{
			
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
			{
				if ( routeTruckSummary
				        .getRouteSummary()
				        .getId()
				        .equals( routeLegTruckSummary.getRouteSummary().getId() ) )
				{
					index2++ ;
					Row row2 = sheet.createRow( index2 );
					LegInfo legInfo = routeLegTruckSummary.getLegInfo();
					createCell( wb , row2 , 0 , legInfo.getOrigin() , font1 );
					createCell( wb , row2 , 1 , legInfo.getStartPoint() , font1 );
					createCell( wb , row2 , 2 , legInfo.getVcCustomer() , font1 );
					createCell( wb , row2 , 3 , legInfo.getDestination() ,
					        font1 );
					// 板/公里
					createCell( wb , row2 , 4 , String.format( "%.3f" ,
					        routeLegTruckSummary.getCurVendorPro_truck_km() ) ,
					        font1 );
					// 每板
					createCell( wb , row2 , 5 , String.format( "%.3f" ,
					        routeLegTruckSummary.getCurVendorPro_truck_per() ) ,
					        font1 );
					// 辆每月
					createCell( wb , row2 , 6 , String.format( "%.3f" ,
					        routeLegTruckSummary.getCurVendorPro_month() ) ,
					        font1 );
					// getVendorProfit_percent
					createCell( wb , row2 , 7 , String.format( "%.3f" ,
					        routeLegTruckSummary.getCurVendorPro_percent() ) ,
					        font1 );

				}
			}
			index2++ ;
			Row row3 = sheet.createRow( index2 );
			sheet.addMergedRegion( new CellRangeAddress( index2 , index2 , 0 ,
			        3 ) );
			createCell( wb , row3 , 0 , "统计" , font1 );
			createCell( wb , row3 , 1 , "" , font1 );
			createCell( wb , row3 , 2 , "" , font1 );
			createCell( wb , row3 , 3 , "" , font1 );
			createCell(
			        wb ,
			        row3 ,
			        4 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalCurVenPro_truck_km() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        5 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalCurVenPro_truck() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        6 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalCurVenPro_month() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        7 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalCurVenPro_percent() ) ,
			        font1 );
			
		}
		
		wb.setSheetName( 6 , "当前供应商利润" );
		
	}
	
	/**
	 * @Description: TODO(新方案下中联利润)
	 * @param wb
	 * @param routeLegTruckSummary_list
	 * @param routeTruckSummaryList
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-2-9 下午2:34:54
	 */
	public static void getUnionProfitTable( Workbook wb ,
	        List< RouteLegTruckSummary > routeLegTruckSummary_list ,
	        List< RouteTruckSummary > routeTruckSummaryList )
	{
		Sheet sheet = wb.createSheet();
		// 文件头字体
		Font font0 = createFonts( wb , Font.BOLDWEIGHT_BOLD , "宋体" , false ,
		        ( short ) 200 );
		Font font1 = createFonts( wb , Font.BOLDWEIGHT_NORMAL , "宋体" , false ,
		        ( short ) 200 );
		Row row0 = sheet.createRow( 0 );
		Row row = sheet.createRow( 1 );
		createCell( wb , row0 , 0 , "新方案下中联利润" , font0 );
		sheet.addMergedRegion( new CellRangeAddress( 0 , 0 , 0 , 6 ) );
		createCell( wb , row , 0 , "起始地城市" , font0 );
		createCell( wb , row , 1 , "起始地提车库" , font0 );
		createCell( wb , row , 2 , "客户" , font0 );
		createCell( wb , row , 3 , "目的地城市" , font0 );
		createCell( wb , row , 4 , "/板/公里" , font0 );
		createCell( wb , row , 5 , "/板" , font0 );
		createCell( wb , row , 6 , "/月" , font0 );
		createCell( wb , row , 7 , "利润率" , font0 );

		int index2 = 1;
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{
			
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
			{
				if ( routeTruckSummary
				        .getRouteSummary()
				        .getId()
				        .equals( routeLegTruckSummary.getRouteSummary().getId() ) )
				{
					index2++ ;
					Row row2 = sheet.createRow( index2 );
					LegInfo legInfo = routeLegTruckSummary.getLegInfo();
					createCell( wb , row2 , 0 , legInfo.getOrigin() , font1 );
					createCell( wb , row2 , 1 , legInfo.getStartPoint() , font1 );
					createCell( wb , row2 , 2 , legInfo.getVcCustomer() , font1 );
					createCell( wb , row2 , 3 , legInfo.getDestination() ,
					        font1 );
					// 板/公里
					createCell( wb , row2 , 4 , String.format( "%.3f" ,
					        routeLegTruckSummary.getUnionProfit_truck_km() ) ,
					        font1 );
					// /板
					createCell( wb , row2 , 5 , String.format( "%.3f" ,
					        routeLegTruckSummary.getUnionProfit_truck_per() ) ,
					        font1 );
					// 每月
					createCell( wb , row2 , 6 , String.format( "%.3f" ,
					        routeLegTruckSummary.getUnionProfit_month() ) ,
					        font1 );
					// getVendorProfit_percent
					createCell( wb , row2 , 7 , String.format( "%.3f" ,
					        routeLegTruckSummary.getUnionProfit_percent() ) ,
					        font1 );

				}
			}
			index2++ ;
			Row row3 = sheet.createRow( index2 );
			sheet.addMergedRegion( new CellRangeAddress( index2 , index2 , 0 ,
			        3 ) );
			createCell( wb , row3 , 0 , "统计" , font1 );
			createCell( wb , row3 , 1 , "" , font1 );
			createCell( wb , row3 , 2 , "" , font1 );
			createCell( wb , row3 , 3 , "" , font1 );
			createCell(
			        wb ,
			        row3 ,
			        4 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalUnPro_truck_km() ) ,
			        font1 );
			createCell(
			        wb ,
			        row3 ,
			        5 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalUnPro_truck() ) , font1 );
			createCell(
			        wb ,
			        row3 ,
			        6 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalUnPro_month() ) , font1 );
			createCell(
			        wb ,
			        row3 ,
			        7 ,
			        String.format( "%.3f" ,
			                routeTruckSummary.getTotalUnPro_percent() ) , font1 );
			
		}
		
		wb.setSheetName( 7 , "新方案下中联利润" );
		
	}
	
	/**
	 * @Description: TODO(线路与商品车详情)
	 * @param wb
	 * @param leg_cars
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-23 下午3:05:22
	 */
	public static void getLegCarInfoTable2( Workbook wb ,
	        Hashtable< LegInfo , List< CarInfo >> leg_cars )
	{
		Sheet sheet = wb.createSheet();
		// 文件头字体

		Iterator< LegInfo > keys = leg_cars.keySet().iterator();
		int index = 0;
		while ( keys.hasNext() )
		{
			Font font0 = createFonts( wb , Font.BOLDWEIGHT_BOLD , "宋体" , false ,
			        ( short ) 200 );
			Font font1 = createFonts( wb , Font.BOLDWEIGHT_NORMAL , "宋体" ,
			        false , ( short ) 200 );
			LegInfo legInfo = ( LegInfo ) keys.next();
			index++ ;
			Row row0 = sheet.createRow( index );
			sheet.addMergedRegion( new CellRangeAddress( index , index , 0 , 6 ) );
			index++ ;
			Row row = sheet.createRow( index );
			createCell( wb , row0 , 0 ,
			        legInfo.getOrigin() + "-" + legInfo.getOrigin() + "-"
			                + legInfo.getDestination() + "商品车详情" , font0 );
			createCell( wb , row , 0 , "车型" , font0 );
			createCell( wb , row , 1 , "重量" , font0 );
			createCell( wb , row , 2 , "长度" , font0 );
			createCell( wb , row , 3 , "应收单价" , font0 );
			createCell( wb , row , 4 , "应付单价" , font0 );
			createCell( wb , row , 5 , "发车比例" , font0 );
			createCell( wb , row , 6 , "单公里采购价" , font0 );
			List< CarInfo > carInfos = leg_cars.get( legInfo );
			for ( CarInfo carInfo : carInfos )
			{
				index++ ;
				Row row2 = sheet.createRow( index );
				createCell( wb , row2 , 0 , carInfo.getCarname() , font1 );
				createCell( wb , row2 , 1 , carInfo.getWeight() + "" , font1 );
				createCell( wb , row2 , 2 , carInfo.getLength() + "" , font1 );
				createCell( wb , row2 , 3 , carInfo.getIncomePrice() + "" ,
				        font1 );
				createCell( wb , row2 , 4 ,
				        String.format( "%.3f" , carInfo.getCurrentProCost() )
				                + "" , font1 );
				createCell( wb , row2 , 5 , carInfo.getRatio() + "" , font1 );
				createCell( wb , row2 , 6 , carInfo.getFleetPrice() + "" ,
				        font1 );
			}
		}
		wb.setSheetName( 8 , "线路与商品车详情" );
		
	}
	
	/**
	 * @Description: TODO(价格区间表)
	 * @param wb
	 * @param legPrice_list
	 * @param legInfos
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-23 下午4:40:04
	 */
	public static void getLegPrice( Workbook wb ,
	        List< LegPriceSummary > legPrice_list , List< LegInfo > legInfos )
	{
		Sheet sheet = wb.createSheet();
		Hashtable< Integer , List< LegPriceSummary > > legpriceMap = new Hashtable< Integer , List< LegPriceSummary > >();
		for ( LegPriceSummary legPriceSummary : legPrice_list )
		{
			if ( legpriceMap.containsKey( legPriceSummary.getLegInfo().getId() ) )
			{
				List< LegPriceSummary > legPriceList = legpriceMap
				        .get( legPriceSummary.getLegInfo().getId() );
				legPriceList.add( legPriceSummary );
				legpriceMap.put( legPriceSummary.getLegInfo().getId() ,
				        legPriceList );
			}
			else
			{
				List< LegPriceSummary > legPriceSummaries = new ArrayList< LegPriceSummary >();
				legPriceSummaries.add( legPriceSummary );
				legpriceMap.put( legPriceSummary.getLegInfo().getId() ,
				        legPriceSummaries );
			}
		}
		int index = 0;
		for ( LegInfo legInfo : legInfos )
		{
			Font font0 = createFonts( wb , Font.BOLDWEIGHT_BOLD , "宋体" , false ,
			        ( short ) 200 );
			Font font1 = createFonts( wb , Font.BOLDWEIGHT_NORMAL , "宋体" ,
			        false , ( short ) 200 );
			index++ ;
			Row row0 = sheet.createRow( index );
			sheet.addMergedRegion( new CellRangeAddress( index , index , 0 , 6 ) );
			index++ ;
			Row row = sheet.createRow( index );
			createCell(
			        wb ,
			        row0 ,
			        0 ,
			        legInfo.getOrigin() + "-" + legInfo.getStartPoint() + "-"
			                + legInfo.getVcCustomer() + "--"
			                + legInfo.getDestination() + "价格区间详情" , font0 );
			createCell( wb , row , 0 , "价格" , font0 );
			createCell( wb , row , 1 , "概率(%)" , font0 );
			
			List< LegPriceSummary > newlegPrice_list = new ArrayList< LegPriceSummary >();
			newlegPrice_list = legpriceMap.get( legInfo.getId() );
			
			if ( newlegPrice_list != null )
			{
				List< LegPriceSummary > newLegPriceSummaries = new ArrayList< LegPriceSummary >();
				Collections.sort( newlegPrice_list );// 进行从小到大排序
				Iterator< LegPriceSummary > lIterator = newlegPrice_list
				        .iterator();
				// 去重
				while ( lIterator.hasNext() )
				{
					LegPriceSummary legPriceSummary = lIterator.next();
					if ( newLegPriceSummaries.contains( legPriceSummary ) )
					{
						lIterator.remove();
					}
					else
					{
						newLegPriceSummaries.add( legPriceSummary );
					}
					
				}
			}
			if ( newlegPrice_list != null )
			{
				if ( newlegPrice_list.size() <= 20 )
				{
					for ( LegPriceSummary legPriceSummary : newlegPrice_list )
					{
						index++ ;
						Row row2 = sheet.createRow( index );
						if ( legPriceSummary.getLegInfo().getId()
						        .equals( legInfo.getId() ) )
						{
							createCell(
							        wb ,
							        row2 ,
							        0 ,
							        String.format( "%.2f" ,
							                legPriceSummary.getPriceBytruck() ) ,
							        font1 );
							createCell(
							        wb ,
							        row2 ,
							        1 ,
							        String.format( "%.2f" ,
							                legPriceSummary.getProb3() * 100 ) ,
							        font1 );
						}
					}

				}
				else
				{
					int area = ( int ) ( newlegPrice_list.get(
					        newlegPrice_list.size() - 1 ).getPriceBytruck() - newlegPrice_list
					        .get( 0 ).getPriceBytruck() );// 算出价格区间跨度
					for ( int i = 0 ; i <= area + 1 ; i++ )
					{
						index++ ;
						double minvalue = newlegPrice_list.get( 0 )
						        .getPriceBytruck() + i;
						Row row3 = sheet.createRow( index );
						createCell( wb , row3 , 0 ,
						        String.format( "%.2f" , minvalue - 1 ) + "-"
						                + String.format( "%.2f" , minvalue ) ,
						        font1 );
						
						double curValue = getValue( minvalue ,
						        newlegPrice_list , legInfo.getId() );
						createCell( wb , row3 , 1 ,
						        String.format( "%.2f" , curValue * 100 ) ,
						        font1 );
						
					}

				}
			}
		}
		wb.setSheetName( 9 , "价格区间详情" );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param minvalue
	 * @param legPrice_list
	 * @param id
	 * @return double 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-10 下午4:01:17
	 */
	private static double getValue( double minvalue ,
	        List< LegPriceSummary > newlegPrice_list , Integer id )
	{
		double totalProb3 = 0;
		
		for ( LegPriceSummary legPriceSummary : newlegPrice_list )
		{
			if ( legPriceSummary.getPriceBytruck() > minvalue - 1
			        && legPriceSummary.getPriceBytruck() <= minvalue )
			{
				totalProb3 += legPriceSummary.getProb3();
			}
		}
		return totalProb3;
	}
}
