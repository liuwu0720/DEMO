/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-11-3 下午2:22:06
 * @version V1.0
 */
package com.chnl.service.iml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chnl.dao.PrLegfileimportDAO;
import com.chnl.pojo.PrLegfileimport;
import com.chnl.service.ReadExcelService;

/**
 * @Package com.chnl.service.iml
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-11-3 下午2:22:06
 * @version V1.0
 */
@Service( "readExcel" )
@Transactional
public class ReadExcelServiceImp implements ReadExcelService
{
	@Autowired
	private PrLegfileimportDAO prLegfileimportDAO;

	/**
	 * @Description: TODO(读取excel文件)
	 * @param inputStream
	 * @author liuwu
	 * @create_date 2014-11-3 下午2:24:02
	 */
	public List< String > readExcel( InputStream inputStream )
	{
		List< String > locationList = new ArrayList< String >();
		try
		{

			Workbook wb = WorkbookFactory.create( inputStream );
			Sheet sheet = wb.getSheetAt( 0 );
			for ( int i = 1 ; i <= sheet.getLastRowNum() ; i++ )
			{
				StringBuffer sb = new StringBuffer();
				Row row = sheet.getRow( i ); // 获取行(row)对象
				for ( int j = 0 ; j < row.getLastCellNum() ; j++ )
				{
					if ( row.getCell( j ) != null )
					{
						sb.append( row.getCell( j ) + "," );
					}

				}
				
				locationList.add( sb.toString() );

			}
		}
		catch ( InvalidFormatException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return locationList;
		
	}
	
	/**
	 * @Description: TODO(检查导入文件的有效)
	 * @param locationList
	 * @author liuwu
	 * @create_date 2014-11-6 下午1:57:57
	 */
	public String checkDataAvailability( List< String > locationList ,
	        List< PrLegfileimport > prLegfileimports )
	{
		String errormessage = "";
		for ( String str : locationList )
		{
			
			double loopflag = Double.parseDouble( str.split( "," )[0].trim() );
			String startcity = str.split( "," )[1].trim();
			String startpoint = str.split( "," )[2].trim();
			String customer = str.split( "," )[3].trim();
			String endcity = str.split( "," )[4].trim();
			PrLegfileimport prLegfileimport = new PrLegfileimport();
			errormessage = prLegfileimportDAO.checkDataAvailability( startcity ,
			        startpoint , endcity , customer , prLegfileimport );

			if ( errormessage == "" || errormessage == null )
			{

				prLegfileimport.setLoopflag( loopflag );
				prLegfileimport.setEndcity( endcity );
				prLegfileimport.setStartcity( startcity );
				prLegfileimport.setStartpoint( startpoint );
				prLegfileimport.setSelectCustomer( customer );
				prLegfileimports.add( prLegfileimport );
				continue;
			}
			else
			{
				break;
			}

		}
		if ( prLegfileimports.size() > 100 )
		{
			errormessage = "系统最多测算100条线路";
		}
		else
		{
			// 检查是否符合环线标准：
			Hashtable< Double , List< PrLegfileimport > > legMap = new Hashtable< Double , List< PrLegfileimport > >();
			// 第一步：先进行环线，单线归类
			for ( PrLegfileimport prLegfileimport : prLegfileimports )
			{
				if ( legMap.containsKey( prLegfileimport.getLoopflag() ) )
				{
					legMap.get( prLegfileimport.getLoopflag() ).add(
					        prLegfileimport );
					legMap.put( prLegfileimport.getLoopflag() ,
					        legMap.get( prLegfileimport.getLoopflag() ) );
				}
				else
				{
					List< PrLegfileimport > prList = new ArrayList< PrLegfileimport >();
					prList.add( prLegfileimport );
					legMap.put( prLegfileimport.getLoopflag() , prList );
				}
			}
			Iterator< Double > keys = legMap.keySet().iterator();
			while ( keys.hasNext() )
			{
				Double key = keys.next();
				List< PrLegfileimport > prlegs = legMap.get( key );
				if ( prlegs.size() > 1 )// 如果是环线
				{
					for ( int i = 0 ; i <= prlegs.size() - 1 ; i++ )
					{
						if ( i < prlegs.size() - 1 )// 检查上一条线路的目的地与下一条线路的出发地是否在同一省份
						{
							boolean flag1 = checkSameProvince( prlegs.get( i )
							        .getEndcity() , prlegs.get( i + 1 )
							        .getStartcity() );
							if ( flag1 )
							{
								continue;
							}
							else
							{
								errormessage = "上一条线路的目的地：【"
								        + prlegs.get( i ).getEndcity()
								        + "】与下一条线路的出发地：【"
								        + prlegs.get( i + 1 ).getStartcity()
								        + "】不在同一省份；不符合环线标准！请修改后重新导入！";
								break;
							}

						}
						else if ( i == prlegs.size() - 1 )// 最后一条线路的目的地与第一条线路的出发地是否在同一省份
						{
							
							boolean flag2 = checkSameProvince(
							        prlegs.get( prlegs.size() - 1 )
							                .getEndcity() , prlegs.get( 0 )
							                .getStartcity() );
							if ( flag2 )
							{
								continue;
							}
							else
							{
								errormessage = "最后一条线路的目的地：【"
								        + prlegs.get( i ).getEndcity()
								        + "】与第一条线路的出发地：【"
								        + prlegs.get( i + 1 ).getStartcity()
								        + "】不在同一省份；不符合环线标准！请修改后重新导入！";
								break;
							}
						}
						
					}
					
				}
			}
		}

		return errormessage;

	}
	
	/**
	 * @Description: TODO(检查环线的有效性)
	 * @param endcity
	 * @param startcity
	 *            void 返回值描述
	 * @author liuwu
	 * @return
	 * @create_date 2014-11-20 上午9:32:54
	 */
	private boolean checkSameProvince( String endcity , String startcity )
	{
		boolean flag = prLegfileimportDAO.checkIfSameProvince( endcity ,
		        startcity );
		return flag;
	}
	
}
