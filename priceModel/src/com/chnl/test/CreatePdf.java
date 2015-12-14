/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-12-5 上午9:58:23
 * @version V1.0
 */
package com.chnl.test;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chnl.pojo.LegInfo;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @Package com.chnl.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-12-5 上午9:58:23
 * @version V1.0
 */
public class CreatePdf
{
	/**
	 * 
	 * 生成PDF的方法
	 * 
	 * @return boolean
	 * 
	 */
	
	public static boolean createPDF( String pdfPath )
	{
		
		Document document = new Document();// 建立一个Document对象
		
		document.setPageSize( PageSize.A4 );// 设置页面大小
		
		try
		{
			
			PdfWriter.getInstance( document , new FileOutputStream( pdfPath ) );// 建立一个PdfWriter对象
			document.open();
			BaseFont bfChinese = BaseFont.createFont( "STSong-Light" ,
			        "UniGB-UCS2-H" , BaseFont.NOT_EMBEDDED );// 设置中文字体
			Font p1Font = new Font( bfChinese , 15 , Font.BOLD );// 设置字体大小
			Font h1Font = new Font( bfChinese , 20 , Font.BOLD , Color.RED );// 设置字体大小(标题)
			Font p2Font = new Font( bfChinese , 15 , Font.BOLD , Color.RED );// 设置字体大小
			Font headFont1 = new Font( bfChinese , 10 , Font.BOLD );// 设置字体大小风格湖uhhhfffgui
			Font inputFont = new Font( bfChinese , 15 , Font.ITALIC ,
			        Color.BLUE );// 输入表字体
			Font tableFont = new Font( bfChinese , 10 , Font.BOLD );// 设置字体大小
			/**
			 * 标题
			 */
			Paragraph titleParagraph = new Paragraph( "线路模型计算报告" , h1Font );
			titleParagraph.setAlignment( Element.ALIGN_CENTER );
			document.add( titleParagraph );
			/**
			 * 正文(时间)
			 */
			Date d = new Date();
			String nowTime = SimpleDateFormat.getDateInstance(
			        SimpleDateFormat.FULL ).format( d );
			Paragraph timeParagraph = new Paragraph( "报告生成时间：" + nowTime ,
			        headFont1 );
			timeParagraph.setSpacingBefore( 20f );
			document.add( timeParagraph );
			/**
			 * 正文（用户）
			 */
			Paragraph userParagraph = new Paragraph( "系统操作用户：liuwu" , headFont1 );
			document.add( userParagraph );
			/**
			 * 正文（输入参数）
			 */
			Paragraph inputParagraph = new Paragraph( "输入参数" , inputFont );
			inputParagraph.setSpacingBefore( 50f );
			inputParagraph.setAlignment( Element.ALIGN_LEFT );
			document.add( inputParagraph );
			/**
			 * 正文（计算方式、拖车、线路）
			 */
			String caculateType = "分供方占比";
			String caculateValue = "0.3";
			Chunk p1 = new Chunk( "计算方式 : " );
			p1.setFont( p1Font );
			Chunk p2 = new Chunk( caculateType + " = " + caculateValue );
			p2.setFont( p2Font );
			Paragraph typeParagraph = new Paragraph();
			typeParagraph.add( p1 );
			typeParagraph.add( p2 );
			typeParagraph.setSpacingBefore( 10f );
			typeParagraph.setAlignment( Element.ALIGN_LEFT );
			document.add( typeParagraph );
			
			String truckType = "6轴航母";
			Chunk p11 = new Chunk( "所选拖车 : " );
			p11.setFont( p1Font );
			Chunk p21 = new Chunk( truckType );
			p21.setFont( p2Font );
			Paragraph truckParagraph = new Paragraph();
			truckParagraph.add( p11 );
			truckParagraph.add( p21 );
			truckParagraph.setSpacingBefore( 10f );
			truckParagraph.setAlignment( Element.ALIGN_LEFT );
			document.add( truckParagraph );
			
			Chunk leg = new Chunk( "所选线路 : " );
			leg.setFont( p1Font );
			Paragraph legPa = new Paragraph();
			legPa.add( leg );
			
			legPa.setSpacingBefore( 10f );
			legPa.setAlignment( Element.ALIGN_LEFT );
			document.add( legPa );

			List< LegInfo > legInfos = getLegs();
			for ( LegInfo legInfo : legInfos )
			{
				String legType = legInfo.getOrigin() + " - "
				        + legInfo.getStartPoint() + " - "
				        + legInfo.getDestination();
				Chunk p211 = new Chunk( legType );
				p211.setFont( p2Font );
				Paragraph legParagraph = new Paragraph();
				legParagraph.add( p211 );
				legParagraph.setSpacingBefore( 10f );
				legParagraph.setAlignment( Element.ALIGN_LEFT );
				document.add( legParagraph );
			}
			
			/**
			 * 输出结果
			 */
			Paragraph outParagraph = new Paragraph( "计算结果" , inputFont );
			outParagraph.setSpacingBefore( 50f );
			outParagraph.setAlignment( Element.ALIGN_LEFT );
			document.add( outParagraph );
			/**
			 * 输出结果：实际支出
			 */
			Paragraph actualcostText = new Paragraph( "实际支出" , p1Font );
			actualcostText.setSpacingBefore( 20f );
			actualcostText.setAlignment( Element.ALIGN_CENTER );
			document.add( actualcostText );
			BaseFont bfchina = BaseFont.createFont(
			        "C:/windows/fonts/STKAITI.TTF" , BaseFont.IDENTITY_H ,
			        BaseFont.EMBEDDED );
			/**
			 * 实际支出表 列
			 */
			PdfPTable t = new PdfPTable( 4 );
			t.setSpacingBefore( 20f );
			float[] widths = { 0.4f , 0.15f , 0.1f , 0.15f };
			t.setWidths( widths );

			PdfPCell cel = new PdfPCell( new Paragraph( "线路 " , new Font(
			        bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel.setFixedHeight( 20f );
			t.addCell( cel );
			
			cel = new PdfPCell( new Paragraph( "/板/公里 " , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			t.addCell( cel );
			
			cel = new PdfPCell( new Paragraph( "/辆/公里 " , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			t.addCell( cel );
			
			cel = new PdfPCell( new Paragraph( "/月" , new Font( bfchina ) ) );
			cel.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			t.addCell( cel );
			
			for ( LegInfo legInfo : legInfos )
			{
				cel = new PdfPCell( new Paragraph( legInfo.getStartPoint()
				        + "--" + legInfo.getOrigin() ,
				        new Font( bfchina ) ) );
				cel.setHorizontalAlignment( Element.ALIGN_CENTER );
				cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
				t.addCell( cel );
				
				cel = new PdfPCell( new Paragraph( legInfo.getOrigin() ,
				        new Font( bfchina ) ) );
				cel.setHorizontalAlignment( Element.ALIGN_CENTER );
				cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
				t.addCell( cel );
				cel = new PdfPCell( new Paragraph( "/月" , new Font( bfchina ) ) );
				cel.setHorizontalAlignment( Element.ALIGN_CENTER );
				cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
				t.addCell( cel );
				cel = new PdfPCell( new Paragraph( legInfo.getDestination() ,
				        new Font( bfchina ) ) );
				cel.setHorizontalAlignment( Element.ALIGN_CENTER );
				cel.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
				t.addCell( cel );
			}
			document.add( t );
			
			/**
			 * 输出结果：新的采购支出
			 */
			Paragraph newCostText = new Paragraph( "新的采购支出" , p1Font );
			newCostText.setSpacingBefore( 20f );
			newCostText.setAlignment( Element.ALIGN_CENTER );
			document.add( newCostText );

			/**
			 * 实际支出表 列
			 */
			PdfPTable t2 = new PdfPTable( 4 );
			t2.setSpacingBefore( 20f );
			float[] widths2 = { 0.4f , 0.15f , 0.1f , 0.15f };
			t2.setWidths( widths2 );
			
			PdfPCell cel2 = new PdfPCell( new Paragraph( "线路 " , new Font(
			        bfchina ) ) );
			cel2.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel2.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			cel2.setFixedHeight( 20f );
			t2.addCell( cel2 );
			
			cel2 = new PdfPCell( new Paragraph( "/板/公里 " , new Font( bfchina ) ) );
			cel2.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel2.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			t2.addCell( cel2 );
			
			cel2 = new PdfPCell( new Paragraph( "/辆/公里 " , new Font( bfchina ) ) );
			cel2.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel2.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			t2.addCell( cel2 );
			
			cel2 = new PdfPCell( new Paragraph( "/月" , new Font( bfchina ) ) );
			cel2.setHorizontalAlignment( Element.ALIGN_CENTER );
			cel2.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
			t2.addCell( cel2 );
			
			for ( LegInfo legInfo : legInfos )
			{
				cel2 = new PdfPCell( new Paragraph( legInfo.getStartPoint()
				        + "--" + legInfo.getOrigin() , new Font( bfchina ) ) );
				cel2.setHorizontalAlignment( Element.ALIGN_CENTER );
				cel2.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
				t2.addCell( cel2 );
				
				cel2 = new PdfPCell( new Paragraph( legInfo.getOrigin() ,
				        new Font( bfchina ) ) );
				cel2.setHorizontalAlignment( Element.ALIGN_CENTER );
				cel2.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
				t2.addCell( cel2 );
				cel2 = new PdfPCell( new Paragraph( "/月" , new Font( bfchina ) ) );
				cel2.setHorizontalAlignment( Element.ALIGN_CENTER );
				cel2.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
				t2.addCell( cel2 );
				cel2 = new PdfPCell( new Paragraph( legInfo.getDestination() ,
				        new Font( bfchina ) ) );
				cel2.setHorizontalAlignment( Element.ALIGN_CENTER );
				cel2.setVerticalAlignment( Element.ALIGN_MIDDLE );// 垂直方向居中对齐
				t2.addCell( cel2 );
			}
			document.add( t2 );
		}
		catch ( DocumentException de )
		{
			
			System.err.println( de.getMessage() );
			
			return false;
			
		}
		
		catch ( IOException ioe )
		{
			
			System.err.println( ioe.getMessage() );
			
			return false;
			
		}
		
		document.close();
		
		return true;
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return List<LegInfo> 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-5 下午2:16:55
	 */
	private static List< LegInfo > getLegs()
	{
		LegInfo legInfo1 = new LegInfo();
		legInfo1.setOrigin( "深圳" );
		legInfo1.setStartPoint( "深圳比亚迪" );
		legInfo1.setDestination( "南昌" );
		
		LegInfo legInfo2 = new LegInfo();
		legInfo2.setOrigin( "深圳1" );
		legInfo2.setStartPoint( "深圳比亚迪1" );
		legInfo2.setDestination( "南昌1" );
		
		LegInfo legInfo3 = new LegInfo();
		legInfo3.setOrigin( "深圳2" );
		legInfo3.setStartPoint( "深圳比亚迪2" );
		legInfo3.setDestination( "南昌3" );
		
		List< LegInfo > legInfos = new ArrayList< LegInfo >();
		legInfos.add( legInfo2 );
		legInfos.add( legInfo3 );
		legInfos.add( legInfo1 );
		return legInfos;
	}

	/**
	 * 
	 * @param args
	 * 
	 */
	
	public static void main( String[] args )
	{
		
		// TODO 自动生成方法存根
		
		CreatePdf.createPDF( "d:/test.pdf" );
		
	}
}
