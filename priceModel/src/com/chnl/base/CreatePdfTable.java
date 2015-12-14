/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-12-6 下午3:30:18
 * @version V1.0
 */
package com.chnl.base;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import com.chnl.entity.LegPriceSummary;
import com.chnl.entity.RouteLegTruckSummary;
import com.chnl.entity.RouteTruckSummary;
import com.chnl.pojo.CarInfo;
import com.chnl.pojo.LegInfo;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

/**
 * @Package com.chnl.base
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-12-6 下午3:30:18
 * @version V1.0
 */
public class CreatePdfTable
{

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param document
	 * @param routeTruckSummaryList
	 *            void 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @throws DocumentException
	 * @create_date 2014-12-6 下午4:17:50
	 */
	public static void getSelectTable( Document document ,
	        List< RouteTruckSummary > routeTruckSummaryList )
	        throws DocumentException , IOException
	{
		BaseFont bfChinese = BaseFont.createFont( "STSong-Light" ,
		        "UniGB-UCS2-H" , BaseFont.NOT_EMBEDDED );// 设置中文字体
		Font p2Font = new Font( bfChinese , 15 , Font.BOLD , Color.RED );// 设置字体大小
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{
			List< LegInfo > legInfos = routeTruckSummary.getRouteSummary()
			        .getLegInfos();
			for ( LegInfo legInfo : legInfos )
			{
				String empString = "";
				if ( legInfo.getEmptlyFlag().equals( 1 ) )
				{
					empString = "(空载)";
				}
				String legType = legInfo.getOrigin() + " - "
				        + legInfo.getStartPoint() + " - "
				        + legInfo.getVcCustomer() + "-"
				        + legInfo.getDestination() + empString;
				Chunk p211 = new Chunk( legType );
				p211.setFont( p2Font );
				Paragraph legParagraph = new Paragraph();
				legParagraph.add( p211 );
				legParagraph.setSpacingBefore( 10f );
				legParagraph.setAlignment( Element.ALIGN_LEFT );
				document.add( legParagraph );
			}
			
		}
		
	}
	
	/**
	 * @Description: TODO(实际支出表)
	 * @param document
	 * @param routeLegTruckSummary_list
	 * @param routeTruckSummaryList
	 *            void 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @throws DocumentException
	 * @create_date 2014-12-6 下午4:32:31
	 */
	public static void getActualCostTable( Document document ,
	        List< RouteLegTruckSummary > routeLegTruckSummary_list ,
	        List< RouteTruckSummary > routeTruckSummaryList )
	        throws DocumentException , IOException
	{
		BaseFont bfChinese = BaseFont.createFont( "STSong-Light" ,
		        "UniGB-UCS2-H" , BaseFont.NOT_EMBEDDED );// 设置中文字体
		Font p1Font = new Font( bfChinese , 15 , Font.BOLD );// 设置字体大小
		Paragraph actualcostText = new Paragraph( "实际成本" , p1Font );
		actualcostText.setSpacingBefore( 20f );
		actualcostText.setAlignment( Element.ALIGN_CENTER );
		document.add( actualcostText );
		BaseFont bfchina = BaseFont.createFont( "C:/windows/fonts/STKAITI.TTF" ,
		        BaseFont.IDENTITY_H , BaseFont.EMBEDDED );
		
		PdfPTable t = new PdfPTable( 6 );
		t.setSpacingBefore( 20f );
		float[] widths = { 2.4f , 0.75f , 0.75f , 0.75f , 0.75f , 0.75f };
		t.setWidths( widths );
		
		PdfPCell cel = new PdfPCell(
		        new Paragraph( "线路 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/板/公里 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/板 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );

		cel = new PdfPCell( new Paragraph( "/辆/公里 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/辆" , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );

		cel = new PdfPCell( new Paragraph( "/月" , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );

		/**
		 * 实际支出表 列
		 */
		int index = 0;
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{
			index++ ;
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
			{
				if ( routeTruckSummary
				        .getRouteSummary()
				        .getId()
				        .equals( routeLegTruckSummary.getRouteSummary().getId() ) )
				{

					// 线路
					LegInfo legInfo = routeLegTruckSummary.getLegInfo();
					cel = new PdfPCell( new Paragraph( legInfo.getOrigin()
					        + "--" + legInfo.getStartPoint() + "--"
					        + legInfo.getVcCustomer() + "--"
					        + legInfo.getDestination() , new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 板/公里
					
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getActualCost_Truck_km() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					
					// /板
					
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getActualCost_truck_per() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );

					// 辆每公里
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getActualCost_Car_km() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					
					// /辆
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getActualCost_car() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 每月
					cel = new PdfPCell( new Paragraph( String.format( "%.0f" ,
					        routeLegTruckSummary.getActualCost_Month() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );

				}
				
			}
			
			// 统计
			cel = new PdfPCell( new Paragraph( "统计:(发车频率："
			        + routeTruckSummary.getRouteSummary().getFrequency()
			        + " 趟/月; 耗时："
			        + routeTruckSummary.getRouteSummary().getDuration()
			        + " 天 )" , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );

			// 板/公里
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalActualCost_truck_km() ) ,
			        new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			
			// /板
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalActualCost_truck() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// /辆/公里
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalActualCost_car_km() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			
			// /辆
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalActualCost_car() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 每月
			cel = new PdfPCell( new Paragraph( String.format( "%.0f" ,
			        routeTruckSummary.getTotalActualCost_month() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );

			// 增加一个隔行
			if ( index < routeTruckSummaryList.size() )
			{
				cel = new PdfPCell( new Paragraph( "" ) );
				cel.setColspan( 6 );
				cel.setFixedHeight( 30f );
				t.addCell( cel );
			}
			
		}
		document.add( t );
		
	}
	
	/**
	 * @Description: TODO(新的采购支出表)
	 * @param document
	 * @param routeLegTruckSummary_list
	 * @param routeTruckSummaryList
	 *            void 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @throws DocumentException
	 * @create_date 2014-12-6 下午6:02:54
	 */
	public static void getNewProCostTable( Document document ,
	        List< RouteLegTruckSummary > routeLegTruckSummary_list ,
	        List< RouteTruckSummary > routeTruckSummaryList )
	        throws DocumentException , IOException
	{
		BaseFont bfChinese = BaseFont.createFont( "STSong-Light" ,
		        "UniGB-UCS2-H" , BaseFont.NOT_EMBEDDED );// 设置中文字体
		Font p1Font = new Font( bfChinese , 15 , Font.BOLD );// 设置字体大小
		Paragraph actualcostText = new Paragraph( "新的采购价" , p1Font );
		actualcostText.setSpacingBefore( 20f );
		actualcostText.setAlignment( Element.ALIGN_CENTER );
		document.add( actualcostText );
		BaseFont bfchina = BaseFont.createFont( "C:/windows/fonts/STKAITI.TTF" ,
		        BaseFont.IDENTITY_H , BaseFont.EMBEDDED );
		
		PdfPTable t = new PdfPTable( 6 );
		t.setSpacingBefore( 20f );
		float[] widths = { 2.4f , 0.75f , 0.75f , 0.75f , 0.75f , 0.75f };
		t.setWidths( widths );
		
		PdfPCell cel = new PdfPCell(
		        new Paragraph( "线路 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/板/公里 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/板 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );

		cel = new PdfPCell( new Paragraph( "/辆/公里 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/辆 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );

		cel = new PdfPCell( new Paragraph( "/月" , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );

		/**
		 * 实际支出表 列
		 */
		int index = 0;
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{
			index++ ;
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
			{
				if ( routeTruckSummary
				        .getRouteSummary()
				        .getId()
				        .equals( routeLegTruckSummary.getRouteSummary().getId() ) )
				{
					// 线路
					LegInfo legInfo = routeLegTruckSummary.getLegInfo();
					cel = new PdfPCell( new Paragraph( legInfo.getOrigin()
					        + "--" + legInfo.getStartPoint() + "--"
					        + legInfo.getVcCustomer() + "--"
					        + legInfo.getDestination() , new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 板/公里
					
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getNewProCost_truck_km() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 每板
					cel = new PdfPCell( new Paragraph( String.format( "%.0f" ,
					        routeLegTruckSummary.getNewProCost_truck_per() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 辆每公里
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getNewProCost_car_km() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// /辆
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getNewProCost_car() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 每月
					cel = new PdfPCell( new Paragraph( String.format( "%.0f" ,
					        routeLegTruckSummary.getNewProCost_month() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );

				}
				
			}
			
			// 统计
			cel = new PdfPCell( new Paragraph( "统计:(发车频率："
			        + routeTruckSummary.getRouteSummary().getFrequency()
			        + " 趟/月; 耗时："
			        + routeTruckSummary.getRouteSummary().getDuration()
			        + " 天 )" , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			
			// 板/公里
			
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalNewProCost_truck_km() ) ,
			        new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 每板
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalNewProCost_truck() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 辆每公里
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalNewProCost_car_km() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// /辆
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalNewProCost_car() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 每月
			cel = new PdfPCell( new Paragraph( String.format( "%.0f" ,
			        routeTruckSummary.getTotalNewProCost_month() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			
			// 增加一个隔行
			if ( index < routeTruckSummaryList.size() )
			{
				cel = new PdfPCell( new Paragraph( "" ) );
				cel.setColspan( 6 );
				cel.setFixedHeight( 30f );
				t.addCell( cel );
			}

		}
		document.add( t );
		
	}
	
	/**
	 * @Description: TODO(写入当前采购支出)
	 * @param document
	 * @param routeLegTruckSummary_list
	 * @param routeTruckSummaryList
	 *            void 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @throws DocumentException
	 * @create_date 2014-12-6 下午6:08:06
	 */
	public static void getCurrentProCostTable( Document document ,
	        List< RouteLegTruckSummary > routeLegTruckSummary_list ,
	        List< RouteTruckSummary > routeTruckSummaryList )
	        throws DocumentException , IOException
	{
		BaseFont bfChinese = BaseFont.createFont( "STSong-Light" ,
		        "UniGB-UCS2-H" , BaseFont.NOT_EMBEDDED );// 设置中文字体
		Font p1Font = new Font( bfChinese , 15 , Font.BOLD );// 设置字体大小
		Paragraph actualcostText = new Paragraph( "当前采购价" , p1Font );
		actualcostText.setSpacingBefore( 20f );
		actualcostText.setAlignment( Element.ALIGN_CENTER );
		document.add( actualcostText );
		BaseFont bfchina = BaseFont.createFont( "C:/windows/fonts/STKAITI.TTF" ,
		        BaseFont.IDENTITY_H , BaseFont.EMBEDDED );
		
		PdfPTable t = new PdfPTable( 6 );
		t.setSpacingBefore( 30f );
		float[] widths = { 2.4f , 0.75f , 0.75f , 0.75f , 0.75f , 0.75f };
		t.setWidths( widths );
		
		PdfPCell cel = new PdfPCell(
		        new Paragraph( "线路 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/板/公里 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/板" , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );

		cel = new PdfPCell( new Paragraph( "/辆/公里 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/辆" , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );

		cel = new PdfPCell( new Paragraph( "/月" , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		/**
		 * 当前采购支出表 列
		 */
		int index = 0;
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{
			index++ ;
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
			{
				if ( routeTruckSummary
				        .getRouteSummary()
				        .getId()
				        .equals( routeLegTruckSummary.getRouteSummary().getId() ) )
				{

					// 线路
					LegInfo legInfo = routeLegTruckSummary.getLegInfo();
					cel = new PdfPCell( new Paragraph( legInfo.getOrigin()
					        + "--" + legInfo.getStartPoint() + "--"
					        + legInfo.getVcCustomer() + "--"
					        + legInfo.getDestination() , new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 板/公里
					
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getCurProCost_truck_km() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					
					// 每板
					cel = new PdfPCell( new Paragraph( String.format( "%.0f" ,
					        routeLegTruckSummary.getCurProCost_truck_per() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );

					// 辆每公里
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getCurProCost_car_km() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// /辆
					cel = new PdfPCell( new Paragraph( String.format( "%.0f" ,
					        routeLegTruckSummary.getCurProCost_car() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 每月
					cel = new PdfPCell( new Paragraph( String.format( "%.0f" ,
					        routeLegTruckSummary.getCurProCost_month() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );

				}
				
			}
			
			// 统计
			cel = new PdfPCell( new Paragraph( "统计:(发车频率："
			        + routeTruckSummary.getRouteSummary().getFrequency()
			        + " 趟/月; 耗时："
			        + routeTruckSummary.getRouteSummary().getDuration()
			        + " 天 )" , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			
			// 板/公里
			
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalCurProCost_truck_km() ) ,
			        new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 每板
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalCurProCost_truk() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 辆每公里
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalCurProCost_car_km() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// /辆
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalCurProCost_car() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 每月
			cel = new PdfPCell( new Paragraph( String.format( "%.0f" ,
			        routeTruckSummary.getTotalCurProCost_month() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			
			// 增加一个隔行
			if ( index < routeTruckSummaryList.size() )
			{
				cel = new PdfPCell( new Paragraph( "" ) );
				cel.setColspan( 6 );
				cel.setFixedHeight( 30f );
				t.addCell( cel );
			}
		}
		document.add( t );
		
	}
	
	/**
	 * @Description: TODO(平均收入价格)
	 * @param document
	 * @param routeLegTruckSummary_list
	 * @param routeTruckSummaryList
	 *            void 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @throws DocumentException
	 * @create_date 2014-12-6 下午6:11:24
	 */
	public static void getAvgIncomePrice( Document document ,
	        List< RouteLegTruckSummary > routeLegTruckSummary_list ,
	        List< RouteTruckSummary > routeTruckSummaryList )
	        throws DocumentException , IOException
	{
		BaseFont bfChinese = BaseFont.createFont( "STSong-Light" ,
		        "UniGB-UCS2-H" , BaseFont.NOT_EMBEDDED );// 设置中文字体
		Font p1Font = new Font( bfChinese , 15 , Font.BOLD );// 设置字体大小
		Paragraph actualcostText = new Paragraph( "平均收入价格" , p1Font );
		actualcostText.setSpacingBefore( 20f );
		actualcostText.setAlignment( Element.ALIGN_CENTER );
		document.add( actualcostText );
		BaseFont bfchina = BaseFont.createFont( "C:/windows/fonts/STKAITI.TTF" ,
		        BaseFont.IDENTITY_H , BaseFont.EMBEDDED );
		
		PdfPTable t = new PdfPTable( 6 );
		t.setSpacingBefore( 30f );
		float[] widths = { 2.4f , 0.75f , 0.75f , 0.75f , 0.75f , 0.75f };
		t.setWidths( widths );
		
		PdfPCell cel = new PdfPCell(
		        new Paragraph( "线路 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/板/公里 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );

		cel = new PdfPCell( new Paragraph( "/板" , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/辆/公里 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/辆 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );

		cel = new PdfPCell( new Paragraph( "/月" , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		/**
		 * 实际支出表 列
		 */
		int index = 0;
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{
			index++ ;
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
			{
				if ( routeTruckSummary
				        .getRouteSummary()
				        .getId()
				        .equals( routeLegTruckSummary.getRouteSummary().getId() ) )
				{
					
					// 线路
					LegInfo legInfo = routeLegTruckSummary.getLegInfo();
					cel = new PdfPCell( new Paragraph( legInfo.getOrigin()
					        + "--" + legInfo.getStartPoint() + "--"
					        + legInfo.getVcCustomer() + "--"
					        + legInfo.getDestination() , new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 板/公里
					
					cel = new PdfPCell( new Paragraph(
					        String.format( "%.3f" , routeLegTruckSummary
					                .getAvgIncomePrice_truck_km() ) , new Font(
					                bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					
					// 每板
					cel = new PdfPCell( new Paragraph(
					        String.format( "%.0f" , routeLegTruckSummary
					                .getAvgIncomePrice_truck_per() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 辆每公里
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getAvgIncomePrice_car_km() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// /辆
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getAvgIncomePrice_car() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 每月
					cel = new PdfPCell( new Paragraph( String.format( "%.0f" ,
					        routeLegTruckSummary.getAvgIncomePrice_month() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );

				}
				
			}
			
			// 统计
			cel = new PdfPCell( new Paragraph( "统计:(发车频率："
			        + routeTruckSummary.getRouteSummary().getFrequency()
			        + " 趟/月; 耗时："
			        + routeTruckSummary.getRouteSummary().getDuration()
			        + " 天 )" , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			
			// 板/公里
			
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalAvgInPrice_truck_km() ) ,
			        new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 辆每公里
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalAvgInPrice_car_km() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 每月
			cel = new PdfPCell( new Paragraph( String.format( "%.0f" ,
			        routeTruckSummary.getTotalAvgInPrice_month() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 每板
			cel = new PdfPCell( new Paragraph( null , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 增加一个隔行
			if ( index < routeTruckSummaryList.size() )
			{
				cel = new PdfPCell( new Paragraph( "" ) );
				cel.setColspan( 6 );
				cel.setFixedHeight( 30f );
				t.addCell( cel );
			}
		}
		document.add( t );
		
	}
	
	/**
	 * @Description: TODO(新方案下供应商利润)
	 * @param document
	 * @param routeLegTruckSummary_list
	 * @param routeTruckSummaryList
	 *            void 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @throws DocumentException
	 * @create_date 2014-12-6 下午6:20:44
	 */
	public static void getNewVendorProfitTable( Document document ,
	        List< RouteLegTruckSummary > routeLegTruckSummary_list ,
	        List< RouteTruckSummary > routeTruckSummaryList )
	        throws DocumentException , IOException
	{
		BaseFont bfChinese = BaseFont.createFont( "STSong-Light" ,
		        "UniGB-UCS2-H" , BaseFont.NOT_EMBEDDED );// 设置中文字体
		Font p1Font = new Font( bfChinese , 15 , Font.BOLD );// 设置字体大小
		Paragraph actualcostText = new Paragraph( "新方案下供应商利润" , p1Font );
		actualcostText.setSpacingBefore( 20f );
		actualcostText.setAlignment( Element.ALIGN_CENTER );
		document.add( actualcostText );
		BaseFont bfchina = BaseFont.createFont( "C:/windows/fonts/STKAITI.TTF" ,
		        BaseFont.IDENTITY_H , BaseFont.EMBEDDED );
		
		PdfPTable t = new PdfPTable( 5 );
		t.setSpacingBefore( 20f );
		float[] widths = { 1.6f , 0.55f , 0.35f , 0.55f , 0.35f };
		t.setWidths( widths );
		
		PdfPCell cel = new PdfPCell(
		        new Paragraph( "线路 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/板/公里 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/板" , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );

		cel = new PdfPCell( new Paragraph( "/月 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "利润率" , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		/**
		 * 实际支出表 列
		 */
		int index = 0;
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{
			index++ ;
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
			{
				if ( routeTruckSummary
				        .getRouteSummary()
				        .getId()
				        .equals( routeLegTruckSummary.getRouteSummary().getId() ) )
				{
					
					// 线路
					LegInfo legInfo = routeLegTruckSummary.getLegInfo();
					cel = new PdfPCell( new Paragraph( legInfo.getOrigin()
					        + "--" + legInfo.getStartPoint() + "--"
					        + legInfo.getVcCustomer() + "--"
					        + legInfo.getDestination() , new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 板/公里
					
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getVendorProfit_Truck_km() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 每板
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getVendorProfit_truck_per() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 辆每月
					cel = new PdfPCell( new Paragraph( String.format( "%.0f" ,
					        routeLegTruckSummary.getVendorProfit_Month() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 利润率
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getVendorProfit_percent() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );

				}
				
			}
			
			// 统计
			cel = new PdfPCell( new Paragraph( "统计:(发车频率："
			        + routeTruckSummary.getRouteSummary().getFrequency()
			        + " 趟/月; 耗时："
			        + routeTruckSummary.getRouteSummary().getDuration()
			        + " 天 )" , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			
			// 板/公里
			
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalVenPro_truck_km() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 每板
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalVenPro_truck() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 每月
			cel = new PdfPCell( new Paragraph( String.format( "%.0f" ,
			        routeTruckSummary.getTotalVenPro_month() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 利润率
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalVendorPro_percent() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );

			// 增加一个隔行
			if ( index < routeTruckSummaryList.size() )
			{
				cel = new PdfPCell( new Paragraph( "" ) );
				cel.setColspan( 5 );
				cel.setFixedHeight( 30f );
				t.addCell( cel );
			}
		}
		document.add( t );
		
	}
	
	/**
	 * @Description: TODO(当前供应商利润)
	 * @param document
	 * @param routeLegTruckSummary_list
	 * @param routeTruckSummaryList
	 *            void 返回值描述
	 * @author liuwu
	 * @throws DocumentException
	 * @throws IOException
	 * @create_date 2014-12-8 上午9:46:23
	 */
	public static void getCurVenProTable( Document document ,
	        List< RouteLegTruckSummary > routeLegTruckSummary_list ,
	        List< RouteTruckSummary > routeTruckSummaryList )
	        throws DocumentException , IOException
	{
		BaseFont bfChinese = BaseFont.createFont( "STSong-Light" ,
		        "UniGB-UCS2-H" , BaseFont.NOT_EMBEDDED );// 设置中文字体
		Font p1Font = new Font( bfChinese , 15 , Font.BOLD );// 设置字体大小
		Paragraph actualcostText = new Paragraph( "当前供应商利润" , p1Font );
		actualcostText.setSpacingBefore( 20f );
		actualcostText.setAlignment( Element.ALIGN_CENTER );
		document.add( actualcostText );
		BaseFont bfchina = BaseFont.createFont( "C:/windows/fonts/STKAITI.TTF" ,
		        BaseFont.IDENTITY_H , BaseFont.EMBEDDED );
		
		PdfPTable t = new PdfPTable( 5 );
		t.setSpacingBefore( 30f );
		float[] widths = { 1.6f , 0.55f , 0.35f , 0.55f , 0.35f };
		t.setWidths( widths );
		
		PdfPCell cel = new PdfPCell(
		        new Paragraph( "线路 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/板/公里 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/板" , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );

		cel = new PdfPCell( new Paragraph( "/月 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "利润率" , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		/**
		 * 实际支出表 列
		 */
		int index = 0;
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{
			index++ ;
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
			{
				if ( routeTruckSummary
				        .getRouteSummary()
				        .getId()
				        .equals( routeLegTruckSummary.getRouteSummary().getId() ) )
				{
					
					// 线路
					LegInfo legInfo = routeLegTruckSummary.getLegInfo();
					cel = new PdfPCell( new Paragraph( legInfo.getOrigin()
					        + "--" + legInfo.getStartPoint() + "--"
					        + legInfo.getVcCustomer() + "--"
					        + legInfo.getDestination() , new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 板/公里
					
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getCurVendorPro_truck_km() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// /板
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getCurVendorPro_truck_per() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 辆每月
					cel = new PdfPCell( new Paragraph( String.format( "%.0f" ,
					        routeLegTruckSummary.getCurVendorPro_month() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 利润率
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getCurVendorPro_percent() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );

				}
				
			}
			
			// 统计
			cel = new PdfPCell( new Paragraph( "统计:(发车频率："
			        + routeTruckSummary.getRouteSummary().getFrequency()
			        + " 趟/月; 耗时："
			        + routeTruckSummary.getRouteSummary().getDuration()
			        + " 天 )" , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			
			// 板/公里
			
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalCurVenPro_truck_km() ) ,
			        new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 每板
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalCurVenPro_truck() ) ,
			        new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 每月
			cel = new PdfPCell( new Paragraph( String.format( "%.0f" ,
			        routeTruckSummary.getTotalCurVenPro_month() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 利润率
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalCurVenPro_percent() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			
			// 增加一个隔行
			if ( index < routeTruckSummaryList.size() )
			{
				cel = new PdfPCell( new Paragraph( "" ) );
				cel.setColspan( 5 );
				cel.setFixedHeight( 30f );
				t.addCell( cel );
			}
		}
		document.add( t );
		
	}
	
	/**
	 * @Description: TODO(新方案下中联利润)
	 * @param document
	 * @param routeLegTruckSummary_list
	 * @param routeTruckSummaryList
	 *            void 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @throws DocumentException
	 * @create_date 2014-12-8 上午9:53:36
	 */
	public static void getUnionProfitTable( Document document ,
	        List< RouteLegTruckSummary > routeLegTruckSummary_list ,
	        List< RouteTruckSummary > routeTruckSummaryList )
	        throws DocumentException , IOException
	{
		BaseFont bfChinese = BaseFont.createFont( "STSong-Light" ,
		        "UniGB-UCS2-H" , BaseFont.NOT_EMBEDDED );// 设置中文字体
		Font p1Font = new Font( bfChinese , 15 , Font.BOLD );// 设置字体大小
		Paragraph actualcostText = new Paragraph( "新方案下中联利润" , p1Font );
		actualcostText.setSpacingBefore( 20f );
		actualcostText.setAlignment( Element.ALIGN_CENTER );
		document.add( actualcostText );
		BaseFont bfchina = BaseFont.createFont( "C:/windows/fonts/STKAITI.TTF" ,
		        BaseFont.IDENTITY_H , BaseFont.EMBEDDED );
		
		PdfPTable t = new PdfPTable( 5 );
		t.setSpacingBefore( 30f );
		float[] widths = { 1.6f , 0.55f , 0.35f , 0.55f , 0.35f };
		t.setWidths( widths );
		
		PdfPCell cel = new PdfPCell(
		        new Paragraph( "线路 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/板/公里 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "/板" , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );

		cel = new PdfPCell( new Paragraph( "/月 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "利润率" , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 30f );
		t.addCell( cel );
		
		/**
		 * 实际支出表 列
		 */
		int index = 0;
		for ( RouteTruckSummary routeTruckSummary : routeTruckSummaryList )
		{
			index++ ;
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
			{
				if ( routeTruckSummary
				        .getRouteSummary()
				        .getId()
				        .equals( routeLegTruckSummary.getRouteSummary().getId() ) )
				{
					
					// 线路
					LegInfo legInfo = routeLegTruckSummary.getLegInfo();
					cel = new PdfPCell( new Paragraph( legInfo.getOrigin()
					        + "--" + legInfo.getStartPoint() + "--"
					        + legInfo.getVcCustomer() + "--"
					        + legInfo.getDestination() , new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 板/公里
					
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getUnionProfit_truck_km() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// /板
					
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getUnionProfit_truck_per() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 辆每月
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getUnionProfit_month() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
					// 利润率
					cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
					        routeLegTruckSummary.getUnionProfit_percent() ) ,
					        new Font( bfchina ) ) );
					cel.setHorizontalAlignment( Element.ALIGN_CENTER );
					cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
					cel.setFixedHeight( 30f );
					t.addCell( cel );
				}
				
			}
			
			// 统计
			cel = new PdfPCell( new Paragraph( "统计:(发车频率："
			        + routeTruckSummary.getRouteSummary().getFrequency()
			        + " 趟/月; 耗时："
			        + routeTruckSummary.getRouteSummary().getDuration()
			        + " 天 )" , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			
			// 板/公里
			
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalUnPro_truck_km() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 每板
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalUnPro_truck() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 每月
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalUnPro_month() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			// 利润率
			cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
			        routeTruckSummary.getTotalUnPro_percent() ) , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			

			// 增加一个隔行
			if ( index < routeTruckSummaryList.size() )
			{
				cel = new PdfPCell( new Paragraph( "" ) );
				cel.setColspan( 5 );
				cel.setFixedHeight( 30f );
				t.addCell( cel );
			}
		}
		document.add( t );
		
	}
	
	/**
	 * @Description: TODO( 线路与商品车详情)
	 * @param document
	 * @param leg_cars
	 *            void 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @throws DocumentException
	 * @create_date 2014-12-8 上午11:08:45
	 */
	public static void getLegCarInfoTable( Document document ,
	        Hashtable< LegInfo , List< CarInfo >> leg_cars )
	        throws DocumentException , IOException
	{
		Iterator< LegInfo > keys = leg_cars.keySet().iterator();
		while ( keys.hasNext() )
		{
			LegInfo legInfo = keys.next();
			BaseFont bfChinese = BaseFont.createFont( "STSong-Light" ,
			        "UniGB-UCS2-H" , BaseFont.NOT_EMBEDDED );// 设置中文字体
			Font p1Font = new Font( bfChinese , 15 , Font.BOLD );// 设置字体大小
			Paragraph actualcostText = new Paragraph( "线路:"
			        + legInfo.getOrigin() + "--" + legInfo.getStartPoint()
			        + "--" + legInfo.getVcCustomer() + "--"
			        + legInfo.getDestination()
			        + "对应的商品车详情" , p1Font );
			actualcostText.setSpacingBefore( 20f );
			actualcostText.setAlignment( Element.ALIGN_CENTER );
			document.add( actualcostText );
			BaseFont bfchina = BaseFont.createFont(
			        "C:/windows/fonts/STKAITI.TTF" , BaseFont.IDENTITY_H ,
			        BaseFont.EMBEDDED );
			
			PdfPTable t = new PdfPTable( 7 );
			t.setSpacingBefore( 20f );
			float[] widths = { 0.15f , 0.15f , 0.15f , 0.15f , 0.15f , 0.15f ,
			        0.15f };
			t.setWidths( widths );
			
			PdfPCell cel = new PdfPCell( new Paragraph( "车型 " , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 20f );
			t.addCell( cel );
			
			cel = new PdfPCell( new Paragraph( "重量 " , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 20f );
			t.addCell( cel );
			
			cel = new PdfPCell( new Paragraph( "长度 " , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 20f );
			t.addCell( cel );
			
			cel = new PdfPCell( new Paragraph( "应收单价" , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			
			cel = new PdfPCell( new Paragraph( "应付单价" , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			
			cel = new PdfPCell( new Paragraph( "发车比例" , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			
			cel = new PdfPCell( new Paragraph( "单公里采购价" , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 30f );
			t.addCell( cel );
			
			List< CarInfo > carInfos = leg_cars.get( legInfo );
			for ( CarInfo carInfo : carInfos )
			{
				// 车型
				cel = new PdfPCell( new Paragraph( carInfo.getCarname() ,
				        new Font( bfchina ) ) );
				cel.setHorizontalAlignment( Element.ALIGN_CENTER );
				cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
				cel.setFixedHeight( 20f );
				t.addCell( cel );
				// 重量
				cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
				        carInfo.getWeight() ) , new Font( bfchina ) ) );
				cel.setHorizontalAlignment( Element.ALIGN_CENTER );
				cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
				cel.setFixedHeight( 20f );
				t.addCell( cel );
				// 长度
				cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
				        carInfo.getLength() ) , new Font( bfchina ) ) );
				cel.setHorizontalAlignment( Element.ALIGN_CENTER );
				cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
				cel.setFixedHeight( 20f );
				t.addCell( cel );
				// 应收单价
				cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
				        carInfo.getIncomePrice() ) , new Font( bfchina ) ) );
				cel.setHorizontalAlignment( Element.ALIGN_CENTER );
				cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
				cel.setFixedHeight( 20f );
				t.addCell( cel );
				// 应付单价
				cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
				        carInfo.getCurrentProCost() ) , new Font( bfchina ) ) );
				cel.setHorizontalAlignment( Element.ALIGN_CENTER );
				cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
				cel.setFixedHeight( 20f );
				t.addCell( cel );
				// 发车频率
				cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
				        carInfo.getRatio() ) , new Font( bfchina ) ) );
				cel.setHorizontalAlignment( Element.ALIGN_CENTER );
				cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
				cel.setFixedHeight( 20f );
				t.addCell( cel );
				// 单公里采购价
				cel = new PdfPCell( new Paragraph( String.format( "%.3f" ,
				        carInfo.getFleetPrice() ) , new Font( bfchina ) ) );
				cel.setHorizontalAlignment( Element.ALIGN_CENTER );
				cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
				cel.setFixedHeight( 20f );
				t.addCell( cel );
			}
			document.add( t );
		}
		
	}
	
	/**
	 * @Description: TODO(价格区间表)
	 * @param document
	 * @param legPrice_list
	 *            void 返回值描述
	 * @author liuwu
	 * @param legInfos
	 * @throws IOException
	 * @throws DocumentException
	 * @create_date 2014-12-9 下午3:42:38
	 */
	public static void getLegPrice( Document document ,
	        List< LegPriceSummary > legPrice_list , List< LegInfo > legInfos )
	        throws DocumentException , IOException
	{
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
		
		for ( LegInfo legInfo : legInfos )
		{
			
			BaseFont bfChinese = BaseFont.createFont( "STSong-Light" ,
			        "UniGB-UCS2-H" , BaseFont.NOT_EMBEDDED );// 设置中文字体
			Font p1Font = new Font( bfChinese , 15 , Font.BOLD );// 设置字体大小
			Paragraph actualcostText = new Paragraph( "线路:"
			        + legInfo.getOrigin() + "--" + legInfo.getStartPoint()
			        + "--" + legInfo.getVcCustomer() + "--"
			        + legInfo.getDestination()
			        + "价格区间表" , p1Font );
			actualcostText.setSpacingBefore( 20f );
			actualcostText.setAlignment( Element.ALIGN_CENTER );
			document.add( actualcostText );
			BaseFont bfchina = BaseFont.createFont(
			        "C:/windows/fonts/STKAITI.TTF" , BaseFont.IDENTITY_H ,
			        BaseFont.EMBEDDED );
			
			PdfPTable t = new PdfPTable( 2 );
			t.setSpacingBefore( 20f );
			float[] widths = { 0.15f , 0.15f };
			t.setWidths( widths );
			
			PdfPCell cel = new PdfPCell( new Paragraph( "价格 " , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 20f );
			t.addCell( cel );
			
			cel = new PdfPCell( new Paragraph( "概率(%) " , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 20f );
			t.addCell( cel );
			
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
						if ( legPriceSummary.getLegInfo().getId()
						        .equals( legInfo.getId() ) )
						{
							cel = new PdfPCell(
							        new Paragraph(
							                String.format( "%.2f" ,
							                        legPriceSummary
							                                .getPriceBytruck() ) ,
							                new Font( bfchina ) ) );
							cel.setHorizontalAlignment( Element.ALIGN_CENTER );
							cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
							cel.setFixedHeight( 20f );
							t.addCell( cel );
							
							cel = new PdfPCell( new Paragraph(
							        String.format( "%.2f" ,
							                legPriceSummary.getProb3() * 100 ) ,
							        new Font( bfchina ) ) );
							cel.setHorizontalAlignment( Element.ALIGN_CENTER );
							cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
							cel.setFixedHeight( 20f );
							t.addCell( cel );
						}
					}
					document.add( t );
				}
				else
				{
					int area = ( int ) ( newlegPrice_list.get(
					        newlegPrice_list.size() - 1 ).getPriceBytruck() - newlegPrice_list
					        .get( 0 ).getPriceBytruck() );// 算出价格区间跨度
					for ( int i = 0 ; i <= area + 1 ; i++ )
					{
						
						double minvalue = newlegPrice_list.get( 0 )
						        .getPriceBytruck() + i;
						
						cel = new PdfPCell( new Paragraph( String.format(
						        "%.2f" , minvalue - 1 )
						        + "-"
						        + String.format( "%.2f" , minvalue ) ,
						        new Font( bfchina ) ) );
						cel.setHorizontalAlignment( Element.ALIGN_CENTER );
						cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
						cel.setFixedHeight( 20f );
						t.addCell( cel );
						
						double curValue = getValue( minvalue ,
						        newlegPrice_list , legInfo.getId() );
						cel = new PdfPCell(
						        new Paragraph( String.format( "%.3f" ,
						                curValue * 100 ) , new Font( bfchina ) ) );
						cel.setHorizontalAlignment( Element.ALIGN_CENTER );
						cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
						cel.setFixedHeight( 20f );
						t.addCell( cel );

					}
					document.add( t );
				}
			}

		}
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
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param document
	 * @param routeLegTruckSummary_list
	 *            void 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @throws DocumentException
	 * @create_date 2014-12-12 下午6:11:20
	 */
	public static void getAvgCarsPerTruck( Document document ,
	        List< RouteLegTruckSummary > routeLegTruckSummary_list )
	        throws DocumentException , IOException
	{
		BaseFont bfChinese = BaseFont.createFont( "STSong-Light" ,
		        "UniGB-UCS2-H" , BaseFont.NOT_EMBEDDED );// 设置中文字体
		Font p1Font = new Font( bfChinese , 15 , Font.BOLD );// 设置字体大小
		Paragraph actualcostText = new Paragraph( "线路平均装载量" , p1Font );
		actualcostText.setSpacingBefore( 20f );
		actualcostText.setAlignment( Element.ALIGN_CENTER );
		document.add( actualcostText );
		BaseFont bfchina = BaseFont.createFont( "C:/windows/fonts/STKAITI.TTF" ,
		        BaseFont.IDENTITY_H , BaseFont.EMBEDDED );
		
		PdfPTable t = new PdfPTable( 2 );
		t.setSpacingBefore( 20f );
		float[] widths = { 0.75f , 0.15f };
		t.setWidths( widths );
		
		PdfPCell cel = new PdfPCell(
		        new Paragraph( "线路 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 20f );
		t.addCell( cel );
		
		cel = new PdfPCell( new Paragraph( "装载量 " , new Font( bfchina ) ) );
		cel.setHorizontalAlignment( Element.ALIGN_CENTER );
		cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
		cel.setFixedHeight( 20f );
		t.addCell( cel );
		for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
		{
			cel = new PdfPCell( new Paragraph( routeLegTruckSummary
			        .getLegInfo().getOrigin()
			        + "--- "
			        + routeLegTruckSummary.getLegInfo().getDestination() ,
			        new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 20f );
			t.addCell( cel );
			
			cel = new PdfPCell( new Paragraph(
			        routeLegTruckSummary.getAvgCarsPerTruckCombo() + "" ,
			        new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 20f );
			t.addCell( cel );
		}
		document.add( t );
	}
	
	

}
