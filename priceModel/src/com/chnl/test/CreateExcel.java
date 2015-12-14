/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-1-30 下午2:16:40
 * @version V1.0
 */
package com.chnl.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import com.chnl.pojo.Smcustomer;

/**
 * @Package com.chnl.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-1-30 下午2:16:40
 * @version V1.0
 */
public class CreateExcel
{
	public static void main( String[] args ) throws IOException
	{
		CreateExcel.create( "D:\\test.xls" );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param string
	 *            void 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @create_date 2015-1-30 下午2:34:23
	 */
	private static void create( String fileString ) throws IOException
	{
		// short rownum;
		
		// create a new file
		FileOutputStream out = new FileOutputStream( fileString );
		// create a new workbook
		Workbook wb = new HSSFWorkbook();
		// create a new sheet
		Sheet s = wb.createSheet();
		// declare a row object reference
		Row r = null;
		// declare a cell object reference
		Cell c = null;
		// create 3 cell styles
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();
		CellStyle cs3 = wb.createCellStyle();
		DataFormat df = wb.createDataFormat();
		// create 2 fonts objects
		Font f = wb.createFont();
		Font f2 = wb.createFont();

		// set font 1 to 12 point type
		f.setFontHeightInPoints( ( short ) 12 );
		// make it blue
		f.setColor( ( short ) 0xc );
		// make it bold
		// arial is the default font
		f.setBoldweight( Font.BOLDWEIGHT_BOLD );

		// set font 2 to 10 point type
		f2.setFontHeightInPoints( ( short ) 10 );
		// make it red
		f2.setColor( ( short ) Font.COLOR_RED );
		// make it bold
		f2.setBoldweight( Font.BOLDWEIGHT_BOLD );

		f2.setStrikeout( true );

		// set cell stlye
		cs.setFont( f );
		// set the cell format
		cs.setDataFormat( df.getFormat( "#,##0.0" ) );

		// set a thin border
		cs2.setBorderBottom( cs2.BORDER_THIN );
		// fill w fg fill color
		cs2.setFillPattern( ( short ) CellStyle.SOLID_FOREGROUND );
		// set the cell format to text see DataFormat for a full list
		cs2.setDataFormat( HSSFDataFormat.getBuiltinFormat( "text" ) );

		// set the font
		cs2.setFont( f2 );

		// set the sheet name in Unicode
		wb.setSheetName(
		        0 ,
		        "\u0422\u0435\u0441\u0442\u043E\u0432\u0430\u044F "
		                + "\u0421\u0442\u0440\u0430\u043D\u0438\u0447\u043A\u0430" );
		// in case of plain ascii
		// wb.setSheetName(0, "HSSF Test");
		// create a sheet with 30 rows (0-29)
		int rownum;
		for ( rownum = ( short ) 0 ; rownum < 30 ; rownum++ )
		{
			// create a row
			r = s.createRow( rownum );
			// on every other row
			if ( ( rownum % 2 ) == 0 )
			{
				// make the row height bigger (in twips - 1/20 of a point)
				r.setHeight( ( short ) 0x249 );
			}

			// r.setRowNum(( short ) rownum);
			// create 10 cells (0-9) (the += 2 becomes apparent later
			for ( short cellnum = ( short ) 0 ; cellnum < 10 ; cellnum += 2 )
			{
				// create a numeric cell
				c = r.createCell( cellnum );
				// do some goofy math to demonstrate decimals
				c.setCellValue( rownum
				        * 10000
				        + cellnum
				        + ( ( ( double ) rownum / 1000 ) + ( ( double ) cellnum / 10000 ) ) );

				String cellValue;

				// create a string cell (see why += 2 in the
				c = r.createCell( ( short ) ( cellnum + 1 ) );
				
				// on every other row
				if ( ( rownum % 2 ) == 0 )
				{
					// set this cell to the first cell style we defined
					c.setCellStyle( cs );
					// set the cell's string value to "Test"
					c.setCellValue( "Test" );
				}
				else
				{
					c.setCellStyle( cs2 );
					// set the cell's string value to "\u0422\u0435\u0441\u0442"
					c.setCellValue( "\u0422\u0435\u0441\u0442" );
				}


				// make this column a bit wider
				s.setColumnWidth( ( short ) ( cellnum + 1 ) ,
				        ( short ) ( ( 50 * 8 ) / ( ( double ) 1 / 20 ) ) );
			}
		}

		// draw a thick black border on the row at the bottom using BLANKS
		// advance 2 rows
		rownum++ ;
		rownum++ ;

		r = s.createRow( rownum );

		// define the third style to be the default
		// except with a thick black border at the bottom
		cs3.setBorderBottom( cs3.BORDER_THICK );

		// create 50 cells
		for ( short cellnum = ( short ) 0 ; cellnum < 50 ; cellnum++ )
		{
			// create a blank type cell (no value)
			c = r.createCell( cellnum );
			// set it to the thick black border style
			c.setCellStyle( cs3 );
		}

		// end draw thick black border


		// demonstrate adding/naming and deleting a sheet
		// create a sheet, set its title then delete it
		s = wb.createSheet();
		wb.setSheetName( 1 , "DeletedSheet" );
		// wb.removeSheetAt( 1 );
		// end deleted sheet

		// write the workbook to the output stream
		// close our file (don't blow out our file handles
		wb.write( out );
		out.close();
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
}