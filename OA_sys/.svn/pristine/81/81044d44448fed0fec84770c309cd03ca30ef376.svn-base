package clt.com.cn.helps;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelHelper
{
	
	public static List< List< Object >> getExcelContent( File excelFile , int sheetIndex )
	        throws Exception
	{
		List< List< Object >> excelData = new ArrayList< List< Object >>();
		InputStream is = new FileInputStream( excelFile );
		
		Workbook wb = WorkbookFactory.create( is );
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();// 该对象用来获取excel单元格公式
		Sheet sheet = wb.getSheetAt( sheetIndex );
		// int maxCellNum=0;
		
		// 循环遍历excel表格数据
		for ( int i = 0 ; i <= sheet.getLastRowNum() ; i++ )
		{
			// boolean isDefault = false;
			Row row = sheet.getRow( i );
			
			if ( row == null )
				continue;
			
			List< Object > rowData = new ArrayList< Object >( row.getLastCellNum() + 1 );
			// int i = 0;
			/* 用for (Cell cell : row)遍历会跳过空单元格，原因未知 */
			for ( int k = 0 ; k <= row.getLastCellNum() ; k++ )
			{
				Cell cell = row.getCell( k );
				
				// 处理空单元格
				if ( cell == null || "".equals( cell.toString() ) )
				{
					rowData.add( "" );
				}
				else
				{// 处理非空单元格
				
					switch ( cell.getCellType() )
						{
							case Cell.CELL_TYPE_STRING :
								rowData.add( cell.getRichStringCellValue().getString() );
								break;
							case Cell.CELL_TYPE_NUMERIC :
								if ( DateUtil.isCellDateFormatted( cell ) )
								{
									
									rowData.add( cell.getDateCellValue() );
								}
								else
								{
									
									// String
									// rs=NumberFormat.getInstance().format(cell.getNumericCellValue());
									
									BigDecimal cellValue = BigDecimal.valueOf( cell
									        .getNumericCellValue() );
									
									rowData.add( cellValue.toPlainString() );
								}
								break;
							case Cell.CELL_TYPE_BOOLEAN :
								rowData.add( cell.getBooleanCellValue() );
								break;
							case Cell.CELL_TYPE_FORMULA :
								// 获得公式计算后的结果
								String formula = evaluator.evaluate( cell )
								        .getStringValue();
								
								rowData.add( formula );
								break;
							default :
						}
				}
			}
			boolean existData = false;
			for ( Object obj : rowData )
			{
				if ( ! "".equals( obj.toString().trim() )
				        && null != obj.toString().trim() )
				{
					existData = true;
					break;
				}
			}
			if ( existData )
				excelData.add( rowData );
		}
		
		return excelData;
	}
	
	@SuppressWarnings( "deprecation" )
	public static void getCheckInfoExcel( HttpServletRequest request ,
	        HttpServletResponse response , List< String > headlist ,
	        List< Object[] > datelist )
	{
		try
		{
			ServletOutputStream output = response.getOutputStream();
			response.reset();
			
			String datestr = clt.com.cn.helps.DateUtil.formatDate( new Date() ,
			        "yyyyMMdd" );
			String filename = datestr + "excel.xls";
			response.setHeader( "Content-disposition" , "attachment; filename="
			        + filename ); // 设定输出文件头
			response.setContentType( "application/msexcel" ); // 定义输出类型
			
			HSSFWorkbook hssf = new HSSFWorkbook();
			HSSFSheet hsfe = hssf.createSheet( "考勤数据" );
			
			HSSFCellStyle headcellstyle = hssf.createCellStyle();
			headcellstyle.setAlignment( HSSFCellStyle.ALIGN_CENTER );
			headcellstyle.setVerticalAlignment( HSSFCellStyle.VERTICAL_CENTER );
			
			HSSFCellStyle sty = hssf.createCellStyle();
			sty.setBorderBottom( HSSFCellStyle.BORDER_THIN );
			sty.setBorderLeft( HSSFCellStyle.BORDER_THIN );
			sty.setBorderRight( HSSFCellStyle.BORDER_THIN );
			sty.setBorderTop( HSSFCellStyle.BORDER_THIN );
			sty.setAlignment( HSSFCellStyle.ALIGN_CENTER );
			sty.setAlignment( HSSFCellStyle.ALIGN_CENTER );
			sty.setVerticalAlignment( HSSFCellStyle.VERTICAL_CENTER );
			
			HSSFFont font = hssf.createFont();
			font.setBoldweight( HSSFFont.BOLDWEIGHT_BOLD );
			font.setFontName( "宋体" );
			font.setFontHeight( ( short ) 230 );
			headcellstyle.setFont( font );
			
			HSSFRow headrow = hsfe.createRow( 0 );
			for ( int i = 0 ; i < headlist.size() ; i++ )
			{
				HSSFCell cell = headrow.createCell( ( short ) i );
				cell.setCellValue( headlist.get( i ).toString().trim() );
				cell.setCellStyle( headcellstyle );
			}
			hsfe.setColumnWidth( ( short ) 0 , ( short ) 2400 );
			hsfe.setColumnWidth( ( short ) 1 , ( short ) 2400 );
			hsfe.setColumnWidth( ( short ) 2 , ( short ) 3000 );
			hsfe.setColumnWidth( ( short ) 3 , ( short ) 3000 );
			hsfe.setColumnWidth( ( short ) 4 , ( short ) 3000 );
			hsfe.setColumnWidth( ( short ) 5 , ( short ) 3000 );
			
			for ( int i = 0 ; i < datelist.size() ; i++ )
			{
				Object[] obj = datelist.get( i );
				HSSFRow row = hsfe.createRow( i + 1 );
				for ( int j = 1 ; j < obj.length ; j++ )
				{
					HSSFCell cell = row.createCell( ( short ) ( j - 1 ) );
					if ( obj[j] == null || obj[j].toString().equals( "null" )
					        || obj[j].toString().equals( null )
					        || obj[j].toString().trim().equals( "0" ) )
					{
						cell.setCellValue( "" );
					}
					else
					{
						cell.setCellValue( obj[j].toString() );
					}
					cell.setCellStyle( sty );
				}
			}
			
			hssf.write( output );
			output.flush();
			output.close();
		}
		catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
