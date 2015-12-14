/**
 * @Descriptifor(Combination combination:total1){ combination.setTotalcars(
 *                           combination.getTotalcars() );
 *                           //combinatonService.save( combination ); }on:
 *                           TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-8-5 下午3:15:11
 * @version V1.0
 */
package com.chnl.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.jdom.Document;
import org.jdom.xpath.XPath;

import com.chnl.entity.LegPriceSummary;
import com.chnl.entity.Page;
import com.chnl.entity.RouteLegTruckSummary;
import com.chnl.entity.RouteSummary;
import com.chnl.entity.RouteTruckSummary;
import com.chnl.entity.SmUser;
import com.chnl.pojo.CarInfo;
import com.chnl.pojo.Combination;
import com.chnl.pojo.LegInfo;
import com.chnl.pojo.TruckInfo;

/**
 * @Package com.chnl.controller
 * @Description: TODO(所有逻辑运算)
 * @author liuwu
 * @date 2014-8-5 下午3:15:11
 * @version V1.0
 */
public class DataControl
{
	
	public String[] create( int size )
	{
		String[] arr = new String[size];
		
		for ( int i = 0 ; i < size ; i++ )
		{
			arr[i] = Integer.toString( i + 1 );
			
		}
		
		return arr;
	}
	
	/**
	 * 组合实现的算法
	 * 
	 * @param a
	 *            数据数组
	 * @param num
	 *            M选N中 N的个数
	 * @return
	 */
	public List< String > combine( String[] a , int num )
	{
		List< String > list = new ArrayList< String >();
		StringBuffer sb = new StringBuffer();
		String[] b = new String[a.length];
		for ( int i = 0 ; i < b.length ; i++ )
		{
			if ( i < num )
			{
				b[i] = "1";
			}
			else
				b[i] = "0";
		}
		
		int point = 0;
		int nextPoint = 0;
		int count = 0;
		int sum = 0;
		String temp = "1";
		while ( true )
		{
			// 判断是否全部移位完毕
			for ( int i = b.length - 1 ; i >= b.length - num ; i-- )
			{
				if ( b[i].equals( "1" ) )
					sum += 1;
			}
			// 根据移位生成数据
			for ( int i = 0 ; i < b.length ; i++ )
			{
				if ( b[i].equals( "1" ) )
				{
					point = i;
					sb.append( a[point] );
					sb.append( " " );
					count++ ;
					if ( count == num )
						break;
				}
			}
			// 往返回值列表添加数据
			list.add( sb.toString() );
			
			// 当数组的最后num位全部为1 退出
			if ( sum == num )
			{
				break;
			}
			sum = 0;
			
			// 修改从左往右第一个10变成01
			for ( int i = 0 ; i < b.length - 1 ; i++ )
			{
				if ( b[i].equals( "1" ) && b[i + 1].equals( "0" ) )
				{
					point = i;
					nextPoint = i + 1;
					b[point] = "0";
					b[nextPoint] = "1";
					break;
				}
			}
			// 将 i-point个元素的1往前移动 0往后移动
			for ( int i = 0 ; i < point - 1 ; i++ )
				for ( int j = i ; j < point - 1 ; j++ )
				{
					if ( b[i].equals( "0" ) )
					{
						temp = b[i];
						b[i] = b[j + 1];
						b[j + 1] = temp;
					}
				}
			// 清空 StringBuffer
			sb.setLength( 0 );
			count = 0;
		}
		//
		
		return list;
		
	}
	
	/**
	 * @Description: TODO(第一次算法：只考虑到第二种车最大载重)
	 * @param carInfo1
	 * @param carInfo2
	 * @param carInfo3
	 * @param truckInfo
	 * @param legInfo
	 *            void 返回值描述
	 * @author liuwu(第一次算法：只考虑到第二种车最大载重)
	 * @param combinations
	 * @create_date 2014-8-23 下午5:03:54
	 */
	public void createCombination1( List< Combination > combinations ,
	        CarInfo carInfo1 , CarInfo carInfo2 , CarInfo carInfo3 ,
	        TruckInfo truckInfo , LegInfo legInfo )
	{
		/*Combination combination = new Combination();
			combination.setCarInfoByCarId1( carInfo1 );
			combination.setCarInfoByCarId2( carInfo2 );
			combination.setCarInfoByCarId3( carInfo3 );
			combination.setLegInfo( legInfo );
			combination.setTruckInfo( truckInfo );*/
		double max1_Num = truckInfo.getLoadingWeight() / carInfo1.getWeight();
		int max1Num = ( int ) max1_Num;
		
		for ( int i = max1Num ; i >= 0 ; i-- )
		{
			Combination combination = new Combination();
			combination.setCar1num( i );
			combination.setCarInfoByCarId1( carInfo1 );
			combination.setCarInfoByCarId2( carInfo2 );
			combination.setCarInfoByCarId3( carInfo3 );
			combination.setLegInfo( legInfo );
			combination.setTruckInfo( truckInfo );
			int max2Num = ( int ) ( ( truckInfo.getLoadingWeight() - carInfo1
			        .getWeight() * i ) / carInfo2.getWeight() );
			if ( max2Num > 0 )
			{
				combination.setCar2num( max2Num );
				int max3Num = ( int ) ( ( truckInfo.getLoadingWeight()
				        - carInfo1.getWeight() * i - carInfo2.getWeight()
				        * max2Num ) / carInfo3.getWeight() );
				if ( max3Num > 0 )
				{
					combination.setCar3num( max3Num );
				}
				
			}
			combinations.add( combination );
		}
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param combinations2
	 * @param carInfo1
	 * @param carInfo2
	 * @param carInfo3
	 * @param truckInfo
	 * @param legInfo
	 *            void 返回值描述
	 * @author liuwu (第二次算法：考虑到第三种的最大载重)
	 * @param combination
	 * @create_date 2014-8-25 上午11:28:09
	 */
	public void createCombination2( Combination combination ,
	        List< Combination > combinations2 , CarInfo carInfo1 ,
	        CarInfo carInfo2 , CarInfo carInfo3 , TruckInfo truckInfo ,
	        LegInfo legInfo )
	{
		double max2_Num = ( truckInfo.getLoadingWeight() - combination
		        .getCar1num() * carInfo1.getWeight() )
		        / carInfo2.getWeight();
		int max2Num = ( int ) max2_Num;
		for ( int i = max2Num ; i >= 0 ; i-- )
		{
			Combination combination2 = new Combination();
			combination2.setCar1num( combination.getCar1num() );
			combination2.setCar2num( i );
			combination2.setCarInfoByCarId1( carInfo1 );
			combination2.setCarInfoByCarId2( carInfo2 );
			combination2.setCarInfoByCarId3( carInfo3 );
			combination2.setLegInfo( legInfo );
			combination2.setTruckInfo( truckInfo );
			int max3Num = ( int ) ( ( truckInfo.getLoadingWeight()
			        - combination.getCar1num() * carInfo1.getWeight() - carInfo2
			        .getWeight() * i ) / carInfo3.getWeight() );
			combination2.setCar3num( max3Num );
			combinations2.add( combination2 );
		}
		
	}
	
	/**
	 * @Description: TODO(生成xml文件，为下面图形化做准备)
	 * @param curRouteLegTruckSummary
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-8-26 下午5:45:07
	 */
	public String createXmlDataFile(
	        RouteLegTruckSummary curRouteLegTruckSummary ) throws Exception
	{
		String xmlStr = "";
		XMLUtil xml = new XMLUtil();
		Element graph = xml.addRoot( "graph" );
		xml.addAttribute( graph , "caption" , "示意图" );
		xml.addAttribute( graph , "subCaption" , "每板价格与成本对比图" );
		xml.addAttribute( graph , "basefontsize" , "12" );
		xml.addAttribute( graph , "decimalPrecision" , "2" );// 小数精确度，0为精确到个位
		xml.addAttribute( graph , "showValues" , "1" );// 在报表上不显示数值
		
		Element set = xml.addNode( graph , "set" );
		set.addAttribute( "name" , "平均收入价格" );
		set.addAttribute( "value" ,
		        curRouteLegTruckSummary.getAvgIncomePrice_truck_km() + "" );
		
		Element set2 = xml.addNode( graph , "set" );
		set2.addAttribute( "name" , "实际成本" );
		set2.addAttribute( "value" ,
		        curRouteLegTruckSummary.getActualCost_Truck_km() + "" );
		set2.addAttribute(
		        "color" ,
		        Integer.toHexString( ( int ) ( Math.random() * 255 * 255 * 255 ) )
		                .toUpperCase() );
		
		Element set3 = xml.addNode( graph , "set" );
		set3.addAttribute( "name" , "新采购价" );
		set3.addAttribute( "value" ,
		        curRouteLegTruckSummary.getNewProCost_truck_km() + "" );
		set3.addAttribute(
		        "color" ,
		        Integer.toHexString( ( int ) ( Math.random() * 255 * 255 * 255 ) )
		                .toUpperCase() );
		
		Element set4 = xml.addNode( graph , "set" );
		set4.addAttribute( "name" , "当前采购价" );
		set4.addAttribute( "value" ,
		        curRouteLegTruckSummary.getCurProCost_truck_km() + "" );
		set4.addAttribute(
		        "color" ,
		        Integer.toHexString( ( int ) ( Math.random() * 255 * 255 * 255 ) )
		                .toUpperCase() );
		
		xmlStr = xml.getXML();
		return xmlStr;
	}
	
	/**
	 * @Description: TODO(创建第二张图)
	 * @param legPrice_list
	 * @return String 返回值描述
	 * @author liuwu
	 * @param curLegId
	 * @create_date 2014-8-27 下午1:42:55
	 */
	public String createXmlDataFile2( List< LegPriceSummary > legPrice_list ,
	        String curLegId )
	{
		List< LegPriceSummary > newLegPriceSummaries = new ArrayList< LegPriceSummary >();
		Collections.sort( legPrice_list );// 进行从小到大排序
		Iterator< LegPriceSummary > lIterator = legPrice_list.iterator();
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

		String xmlStr = "";
		XMLUtil xml = new XMLUtil();
		Element graph = xml.addRoot( "graph" );
		xml.addAttribute( graph , "caption" , "示意图" );
		xml.addAttribute( graph , "subCaption" , "每板收入单价与概率关系图" );
		xml.addAttribute( graph , "xAxisName" , "价格" );
		xml.addAttribute( graph , "yAxisName" , "百分比(%)" );
		xml.addAttribute( graph , "basefontsize" , "12" );
		xml.addAttribute( graph , "showLabels" , "1" );// X轴是否显示值 0显示 1不显示
		xml.addAttribute( graph , "decimals" , "2" );// 小数精确度，0为精确到个位
		xml.addAttribute( graph , "showValues" , "1" );// 在报表上是否显示数值 0显示 1不显示
		xml.addAttribute( graph , "rotateLabels" , "1" );// 是否旋转x轴的坐标值
		xml.addAttribute( graph , "slantLabels" , "1" );// 将x轴坐标值旋转为倾斜的还是完全垂直的0垂直

		xml.addAttribute( graph , "numberSuffix" , "%" );// 数值后缀
		if ( legPrice_list.size() <= 20 )
		{
			for ( LegPriceSummary legPriceSummary : legPrice_list )
			{
				if ( legPriceSummary.getLegInfo().getId()
				        .equals( Integer.parseInt( curLegId ) ) )
				{
					Element element = xml.addNode( graph , "set" );
					element.addAttribute(
					        "name" ,
					        String.format( "%.2f" ,
					                legPriceSummary.getPriceBytruck() ) );
					element.addAttribute( "value" , legPriceSummary.getProb3()
					        * 100 + "" );
				}
			}
		}
		else
		{
			
			int area = ( int ) ( legPrice_list.get( legPrice_list.size() - 1 )
			        .getPriceBytruck() - legPrice_list.get( 0 )
			        .getPriceBytruck() );// 算出价格区间跨度
			for ( int i = 0 ; i <= area + 1 ; i++ )
			{
				Element element = xml.addNode( graph , "set" );
				double minvalue = legPrice_list.get( 0 ).getPriceBytruck() + i;
				
				element.addAttribute( "name" ,
				        "(" + String.format( "%.2f" , minvalue - 1 ) + "-"
				                + String.format( "%.2f" , minvalue ) + ")" );
				double curValue = getValue( minvalue , legPrice_list , curLegId );
				element.addAttribute( "value" , curValue * 100 + "" );
			}
			
		}
		
		xmlStr = xml.getXML();
		return xmlStr;
	}
	
	/**
	 * @Description: TODO(得到在价格区间中Prob3的总和)
	 * @param minvalue
	 *            void 返回值描述
	 * @author liuwu
	 * @param legPrice_list
	 * @param curLegId
	 * @return
	 * @create_date 2014-8-27 下午5:36:11
	 */
	private double getValue( double minvalue ,
	        List< LegPriceSummary > legPrice_list , String curLegId )
	{
		double totalProb3 = 0;
		
		for ( LegPriceSummary legPriceSummary : legPrice_list )
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
	 * @Description: 
	 *               TODO(第一步逻辑运算:1、得到combination表的prob2字段；2、得到相同价格相同LEG下的组合个数即输出表2中FIELD3字段
	 *               ；3、得到第三表中的W字段；4、统计相同价格相同LEG下的prob2总和，为下一步生成
	 *               输出表2中FIELD4准备；5、计算输出表3中第5个字段'J'(同一leg下的prob2*总数的和积))
	 * @param combination_all
	 *            所有的集合
	 * @param sameCombinaton
	 *            统计相同价格相同LEG下的组合个数
	 * @param totalProb2
	 *            统计相同价格相同LEG下的prob2总和
	 * @param legPriceSummaries
	 *            输出表2所有集合
	 * @param avg_income_truck
	 *            第三表中的W字段
	 * @param map
	 *            统计同一条LEG下prob1的总和
	 * @param route_leg_truckMap
	 *            为了计算输出表3中第5个字段'J'(同一leg下的prob2*总数的和积) void 返回值描述
	 * @author liuwu 第一步运算
	 * @return
	 * @create_date 2014-8-28 上午10:35:34
	 */
	public double caculateStep1( List< Combination > combination_all ,
	        HashMap< Object , Integer > sameCombinaton ,
	        HashMap< Object , Double > totalProb2 ,
	        List< LegPriceSummary > legPriceSummarForm ,
	        Map< Integer , Double > leg_w_Map ,
	        HashMap< Object , Double > route_leg_truckMap )
	{
		double avg_income_truck = 0.0;
		for ( Combination combination : combination_all )
		{
			/*combination.setProb2( combination.getProb1()
			        / map.get( combination.getLegInfo().getId() ) );*/
			
			// combinatonService.save( combination );
			if ( sameCombinaton.containsKey( combination.getPrice() + ","
			        + combination.getLegInfo().getId() ) )
			{
				sameCombinaton.put(
				        combination.getPrice() + ","
				                + combination.getLegInfo().getId() ,
				        sameCombinaton.get( combination.getPrice() + ","
				                + combination.getLegInfo().getId() ) + 1 );
				totalProb2.put(
				        combination.getPrice() + ","
				                + combination.getLegInfo().getId() ,
				        totalProb2.get( combination.getPrice() + ","
				                + combination.getLegInfo().getId() )
				                + combination.getProb2() );
			}
			else
			{
				sameCombinaton.put( combination.getPrice() + ","
				        + combination.getLegInfo().getId() , 1 );
				totalProb2.put( combination.getPrice() + ","
				        + combination.getLegInfo().getId() ,
				        combination.getProb2() );
			}
			if ( route_leg_truckMap.containsKey( combination.getLegInfo()
			        .getId() ) )
			{
				route_leg_truckMap.put(
				        combination.getLegInfo().getId() ,
				        route_leg_truckMap.get( combination.getLegInfo()
				                .getId() )
				                + combination.getTotalcars()
				                * combination.getProb2() );
			}
			else
			{
				route_leg_truckMap.put( combination.getLegInfo().getId() ,
				        combination.getTotalcars() * combination.getProb2() );
			}
			avg_income_truck += combination.getProb2() * combination.getPrice(); // W字段
			
			LegPriceSummary legPriceSummary = new LegPriceSummary();
			legPriceSummary.setLegInfo( combination.getLegInfo() );
			legPriceSummary.setPriceBytruck( combination.getPrice() );
			legPriceSummarForm.add( legPriceSummary );
			
		}
		return avg_income_truck;
	}
	
	/**
	 * @Description: TODO(第二步计算：得到输出表4中EE,SS字段;得到输出表3 Y,K,)
	 * @param routeLegTruckSummaries
	 *            输出表3所有集合
	 * @param routeSummary
	 *            基础数据表2，动态生成不存数据库
	 * @param routeTruckSummary
	 *            输出表4 void 返回值描述
	 * @author liuwu
	 * 
	 * @create_date 2014-8-28 上午11:26:58
	 */
	public void caculateStep2(
	        List< RouteLegTruckSummary > routeLegTruckSummaries ,
	        RouteSummary routeSummary , RouteTruckSummary routeTruckSummary )
	{
		for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummaries )
		{
			// 输出表3中K = D * C * “Actual distance”
			// D=LEG-TRUCK-INFO表:ACTUAL_COST_TRUCK
			routeLegTruckSummary.setActualCost_Month( routeLegTruckSummary
			        .getActualCost_Truck_km()
			        * routeSummary.getFrequency()
			        * routeLegTruckSummary.getLegInfo().getActualDistance() );
			// 输出表3中Y=W*C*INCOME_DISTANCE
			routeLegTruckSummary.setAvgIncomePrice_month( routeLegTruckSummary
			        .getAvgIncomePrice_truck_km()
			        * routeSummary.getFrequency()
			        * routeLegTruckSummary.getLegInfo().getIncomeDistance() );
			
		}
		double total_Y = 0;
		double total_K = 0;
		for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummaries )
		{
			total_K += routeLegTruckSummary.getActualCost_Month();
			total_Y += routeLegTruckSummary.getAvgIncomePrice_month();
		}
		routeTruckSummary.setTotalActualCost_month( total_K );
		routeTruckSummary.setTotalAvgInPrice_month( total_Y );
	}
	
	/**
	 * @Description: TODO(第三次计算：得到表3一些字段)
	 * @param routeLegTruckSummaries
	 *            输出表3所有集合 void 返回值描述
	 * @author liuwu
	 * @param total_vendorProit_month
	 *            分供方每月利润
	 * @param routeSummary
	 *            基础数据表2，动态生成不存数据库
	 * @param request
	 * @param route_leg_truckMap
	 * @create_date 2014-8-28 下午1:27:01
	 */
	public void caculateStep3(
	        List< RouteLegTruckSummary > routeLegTruckSummaries ,
	        RouteSummary routeSummary , double total_vendorProit_month ,
	        HttpServletRequest request ,
	        HashMap< Object , Double > route_leg_truckMap )
	{
		for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummaries )
		{
			if ( request.getParameter( "venpro" ) != null )
			{ // 用户输入：供应商利润率的情况
				double venpro = Double.parseDouble( request
				        .getParameter( "venpro" ) );
				total_vendorProit_month = routeLegTruckSummary
				        .getActualCost_Month() * venpro / ( 1 - venpro );
			}
			// 输出表3中I
			routeLegTruckSummary.setPropOfLeg_route( routeLegTruckSummary
			        .getLegInfo().getDays() / routeSummary.getDuration() );// 输出表3中
			                                                               // ‘I’
			                                                               // 字段
			// 输出表3中 L = 'TOTAL VENDOR PROFIT'*I/C/ACTUAL DISTANCE
			routeLegTruckSummary.setVendorProfit_Truck_km( routeLegTruckSummary
			        .getPropOfLeg_route()
			        * total_vendorProit_month
			        / routeSummary.getFrequency()
			        / routeLegTruckSummary.getLegInfo().getActualDistance() );
			// 输出表3中 新增字段 vendorProfit/truck = L*ActualDistance
			routeLegTruckSummary
			        .setVendorProfit_truck_per( routeLegTruckSummary
			                .getVendorProfit_Truck_km()
			                * routeLegTruckSummary.getLegInfo()
			                        .getActualDistance() );

			// 输出表3中 M = L * C * ACTUAL DISTANCE
			routeLegTruckSummary.setVendorProfit_Month( routeLegTruckSummary
			        .getVendorProfit_Truck_km()
			        * routeSummary.getFrequency()
			        * routeLegTruckSummary.getLegInfo().getActualDistance() );
			// 输出表3中 O = D + L
			routeLegTruckSummary.setNewProCost_truck_km( routeLegTruckSummary
			        .getActualCost_Truck_km()
			        + routeLegTruckSummary.getVendorProfit_Truck_km() );
			// 输出表3中 Q = O*C*ACTUAL DISTANCE
			routeLegTruckSummary.setNewProCost_month( routeLegTruckSummary
			        .getNewProCost_truck_km()
			        * routeSummary.getFrequency()
			        * routeLegTruckSummary.getLegInfo().getActualDistance() );
			// 输出表3中N = M / Q
			routeLegTruckSummary.setVendorProfit_percent( routeLegTruckSummary
			        .getVendorProfit_Month()
			        / routeLegTruckSummary.getNewProCost_month() );
			
		}
		
	}
	
	/**
	 * @Description: TODO(第4次运算：得到输出表4其它字段)
	 * @param routeLegTruckSummaries
	 *            输出表3所有集合 void 返回值描述
	 * @author liuwu
	 * @param routeTruckSummary
	 *            输出表4
	 * @param total_vendorProit_month
	 *            分供方每月利润
	 * @param routeSummary
	 *            基础表2 动态生成不存数据库
	 * @param request
	 * @create_date 2014-8-28 下午1:55:26
	 */
	public void caculateStep4(
	        List< RouteLegTruckSummary > routeLegTruckSummaries ,
	        RouteTruckSummary routeTruckSummary ,
	        double total_vendorProit_month , RouteSummary routeSummary ,
	        HttpServletRequest request )
	{
		double total_actualDistance = 0.0;
		double d_and_actualDistance = 0.0;
		double total_D = 0;
		double total_J = 0;
		double total_K = 0;
		double o_and_actualDistance = 0.0;
		double total_O = 0;
		double total_Q = 0;
		double r_and_actualDistance = 0.0;
		double total_R = 0.0;
		double total_incomeDistance = 0.0;
		double total_W = 0.0;
		double w_and_incomeDistance = 0.0;
		double total_Y = 0.0;
		double total_S = 0.0;
		double total_R2 = 0.0;
		for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummaries )
		{
			total_actualDistance += routeLegTruckSummary.getLegInfo()
			        .getActualDistance();
			d_and_actualDistance += ( routeLegTruckSummary
			        .getActualCost_Truck_km() * routeLegTruckSummary
			        .getLegInfo()
			        .getActualDistance() );
			total_D += routeLegTruckSummary.getActualCost_Truck_km();
			total_J += routeLegTruckSummary.getAvgCarsPerTruckCombo();
			total_K += routeLegTruckSummary.getActualCost_Month();
			o_and_actualDistance += ( routeLegTruckSummary
			        .getNewProCost_truck_km() * routeLegTruckSummary
			        .getLegInfo()
			        .getActualDistance() );
			total_O += routeLegTruckSummary.getNewProCost_truck_km();
			total_Q += routeLegTruckSummary.getNewProCost_month();
			r_and_actualDistance += ( routeLegTruckSummary
			        .getCurProCost_truck_km() * routeLegTruckSummary
			        .getLegInfo()
			        .getActualDistance() );
			total_R += routeLegTruckSummary.getCurProCost_truck_km();
			total_incomeDistance += routeLegTruckSummary.getLegInfo()
			        .getIncomeDistance();
			total_W += routeLegTruckSummary.getAvgIncomePrice_truck_km();
			w_and_incomeDistance += ( routeLegTruckSummary
			        .getAvgIncomePrice_truck_km() * routeLegTruckSummary
			        .getLegInfo().getIncomeDistance() );
			total_Y += routeLegTruckSummary.getAvgIncomePrice_month();
			total_S += routeLegTruckSummary.getCurProCost_month();
			total_R2 += routeLegTruckSummary.getCurProCost_car_km();
		}
		// CC
		routeTruckSummary.setTotalActualCost_truck_km( d_and_actualDistance
		        / total_actualDistance );
		// DD
		routeTruckSummary.setTotalActualCost_car_km( total_D / total_J );
		// EE
		routeTruckSummary.setTotalActualCost_month( total_K );
		// FF
		routeTruckSummary.setTotalVenPro_truck_km( total_vendorProit_month
		        / routeSummary.getFrequency() / total_actualDistance );
		// JJ
		routeTruckSummary.setTotalNewProCost_month( total_Q );
		// GG
		if ( request.getParameter( "venpro" ) != null )
		{
			double vendorPro_percent = Double.parseDouble( request
			        .getParameter( "venpro" ) );
			routeTruckSummary.setTotalVendorPro_percent( vendorPro_percent );
			
		}
		else
		{
			routeTruckSummary
			        .setTotalVendorPro_percent( total_vendorProit_month
			                / routeTruckSummary.getTotalNewProCost_month() );
		}
		
		// HH
		routeTruckSummary.setTotalNewProCost_truck_km( o_and_actualDistance
		        / total_actualDistance );
		// II
		routeTruckSummary.setTotalNewProCost_car_km( total_O / total_J );
		// KK
		routeTruckSummary.setTotalCurProCost_truck_km( r_and_actualDistance
		        / total_actualDistance );
		// LL
		routeTruckSummary.setTotalCurProCost_month( total_R / total_J );
		// LL2
		routeTruckSummary.setTotalCurProCost_car_km( total_R2 );
		// MM
		routeTruckSummary.setTotalCurVenPro_truck_km( total_S );
		// NN=KK-CC
		routeTruckSummary.setTotalCurVenPro_truck_km( routeTruckSummary
		        .getTotalCurProCost_truck_km()
		        - routeTruckSummary.getTotalActualCost_truck_km() );
		// OO=MM-EE
		routeTruckSummary.setTotalCurVenPro_month( routeTruckSummary
		        .getTotalCurProCost_month()
		        - routeTruckSummary.getTotalActualCost_month() );
		// PP=OO/MM
		routeTruckSummary.setTotalCurVenPro_percent( routeTruckSummary
		        .getTotalCurVenPro_month()
		        / routeTruckSummary.getTotalCurVenPro_truck_km() );
		// QQ
		routeTruckSummary.setTotalAvgInPrice_truck_km( w_and_incomeDistance
		        / total_incomeDistance );
		// RR
		routeTruckSummary.setTotalAvgInPrice_car_km( total_W / total_J );
		// SS
		routeTruckSummary.setTotalAvgInPrice_month( total_Y );
		// TT=QQ-HH
		routeTruckSummary.setTotalUnPro_truck_km( routeTruckSummary
		        .getTotalCurVenPro_percent()
		        - routeTruckSummary.getTotalNewProCost_truck_km() );
		// UU=SS-JJ
		routeTruckSummary.setTotalUnPro_month( routeTruckSummary
		        .getTotalAvgInPrice_car_km()
		        - routeTruckSummary.getTotalNewProCost_month() );
		// VV=UU/SS
		if ( request.getParameter( "unionpro" ) != null )
		{
			double unionPro = Double.parseDouble( request
			        .getParameter( "unionpro" ) );
			routeTruckSummary.setTotalUnPro_percent( unionPro );
		}
		else
		{
			routeTruckSummary.setTotalUnPro_percent( routeTruckSummary
			        .getTotalUnPro_month()
			        / routeTruckSummary.getTotalAvgInPrice_month() );

		}
		
	}
	
	/**
	 * @Description: TODO(车辆大于3的情况)
	 * @param combinations
	 *            组合
	 * @param carInfo1
	 *            车1
	 * @param carInfo2
	 *            车2
	 * @param carInfo3
	 *            车3
	 * @param truckInfo
	 *            拖车
	 * @param legInfo
	 *            线路 void 返回值描述
	 * @author liuwu
	 * @return
	 * @create_date 2014-9-2 下午1:50:30
	 */
	public List< Combination > getCombination(
	        List< Combination > combinations , CarInfo carInfo1 ,
	        CarInfo carInfo2 , CarInfo carInfo3 , TruckInfo truckInfo ,
	        LegInfo legInfo )
	{
		
		int Temp1 , Temp2 , Temp3 , x1 , x2 , x3 , y1 , y2 , y3 , z1 , z2 , z3;
		double TotalWt1 , RestWt1 , TotalWt2 , RestWt2 , TotalWt3 , RestWt3 , comb2 = 0 , comb3 = 0;
		int maxcap1 = ( int ) ( truckInfo.getLoadingWeight() / carInfo1
		        .getWeight() );
		for ( int i = 0 ; i <= maxcap1 ; i++ )
		{
			Temp1 = maxcap1 - i;
			TotalWt1 = carInfo1.getWeight() * Temp1;
			RestWt1 = truckInfo.getLoadingWeight() - TotalWt1;
			int maxcap2 = ( int ) ( RestWt1 / carInfo2.getWeight() );
			for ( int j = maxcap2 ; j >= 0 ; j-- )
			{
				Temp2 = j;
				TotalWt2 = TotalWt1 + carInfo2.getWeight() * Temp2;
				RestWt2 = truckInfo.getLoadingWeight() - TotalWt2;
				int maxcap3 = ( int ) ( RestWt2 / carInfo3.getWeight() );
				int s_index = 0;
				for ( int k = maxcap3 ; k >= 0 ; k-- )
				{
					Temp3 = k;
					TotalWt3 = TotalWt2 + carInfo3.getWeight() * Temp3;
					RestWt3 = truckInfo.getLoadingWeight() - TotalWt3;
					boolean Judge1 = TotalWt3 <= truckInfo.getLoadingWeight();
					s_index++ ;
					/*
					 * 条件限制	
					 */
					double last2 = comb2;
					if ( Temp2 == last2 && Temp3 <= comb3 && s_index != 1 )
					{
						Judge1 = false;
					}
					if ( Judge1 )
					{
						boolean flag1 = false;
						for ( int i1 = 0 ; i1 <= Temp1 && ! flag1 ; i1++ )
						{
							x1 = Temp1 - i1;
							// 第一层装第一种车最大数量
							if ( x1 <= truckInfo.getLength1()
							        / carInfo1.getLength() )
							{
								for ( int j1 = i1 ; j1 >= 0 && ! flag1 ; j1-- )
								{
									x2 = j1;
									x3 = i1 - j1;
									// 第二层和第三层装第一种车的最大数量
									if ( x2 <= truckInfo.getLength2()
									        / carInfo1.getLength()
									        && x3 <= truckInfo.getLength3()
									                / carInfo1.getLength() )
									{
										for ( int i2 = 0 ; i2 <= Temp2
										        && ! flag1 ; i2++ )
										{
											y1 = Temp2 - i2;
											// 第一层装第二种车的最大数量
											if ( y1 <= truckInfo.getLength1()
											        / carInfo2.getLength() )
											{
												for ( int j2 = i2 ; j2 >= 0
												        && ! flag1 ; j2-- )
												{
													y2 = j2;
													y3 = i2 - j2;
													// 第二层和第三层装第二种车的最大数量
													if ( y2 <= truckInfo
													        .getLength2()
													        / carInfo2
													                .getLength()
													        && y3 <= truckInfo
													                .getLength3()
													                / carInfo2
													                        .getLength() )
													{
														for ( int i3 = 0 ; i3 <= Temp3 ; i3++ )
														{
															z1 = Temp3 - i3;
															// 第一层装第三种车的最大数量
															if ( z1 <= truckInfo
															        .getLength1()
															        / carInfo3
															                .getLength() )
															{
																for ( int j3 = i3 ; j3 >= 0 ; j3-- )
																{
																	z2 = j3;
																	z3 = i3
																	        - j3;
																	// 第二层和第三层装第三种车的最大数量
																	if ( z2 <= truckInfo
																	        .getLength2()
																	        / carInfo3
																	                .getLength()
																	        && z3 <= truckInfo
																	                .getLength3()
																	                / carInfo3
																	                        .getLength() )
																	{
																		double TtLen1 = x1
																		        * carInfo1
																		                .getLength()
																		        + y1
																		        * carInfo2
																		                .getLength()
																		        + z1
																		        * carInfo3
																		                .getLength();
																		double TtLen2 = x2
																		        * carInfo1
																		                .getLength()
																		        + y2
																		        * carInfo2
																		                .getLength()
																		        + z2
																		        * carInfo3
																		                .getLength();
																		double TtLen3 = x3
																		        * carInfo1
																		                .getLength()
																		        + y3
																		        * carInfo2
																		                .getLength()
																		        + z3
																		        * carInfo3
																		                .getLength();
																		double RestLen1 = truckInfo
																		        .getLength1()
																		        - TtLen1;
																		double RestLen2 = truckInfo
																		        .getLength2()
																		        - TtLen2;
																		double RestLen3 = truckInfo
																		        .getLength3()
																		        - TtLen3;
																		if ( RestLen1 >= 0
																		        && RestLen2 >= 0
																		        && RestLen3 >= 0 )
																		{
																			/*System.out
																			        .println( "Temp1="
																			                + Temp1
																			                + ",Temp2="
																			                + Temp2
																			                + ",Temp3="
																			                + Temp3
																			                + ",TtLen1="
																			                + TtLen1
																			                + ",TtLen2="
																			                + TtLen2
																			                + ",TtLen3="
																			                + TtLen3 );*/
																			Combination combination = new Combination();
																			combination
																			        .setCar1num( Temp1 );
																			combination
																			        .setCar2num( Temp2 );
																			combination
																			        .setCar3num( Temp3 );
																			combination
																			        .setCarInfoByCarId1( carInfo1 );
																			combination
																			        .setCarInfoByCarId2( carInfo2 );
																			combination
																			        .setCarInfoByCarId3( carInfo3 );
																			combination
																			        .setLegInfo( legInfo );
																			combination
																			        .setTotalLength1( TtLen1 );
																			combination
																			        .setTotalLength2( TtLen2 );
																			combination
																			        .setTotalLength3( TtLen3 );
																			combination
																			        .setTruckInfo( truckInfo );
																			combination
																			        .setTotalWeight( combination
																			                .getTotalWeight() );
																			combination
																			        .setTotalcars( combination
																			                .getTotalcars() );
																			combinations
																			        .add( combination );

																			flag1 = true;
																			comb2 = Temp2;
																			comb3 = Temp3;
																			break;
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
		}
		return combinations;
		
	}
	
	/**
	 * @Description: TODO(可选数量为2的情况)
	 * @param combinations
	 *            组合数
	 * @param carInfo1
	 *            商品车1
	 * @param carInfo2
	 *            商品车2
	 * @param truckInfo
	 *            拖车
	 * @param legInfo
	 *            线路 void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-3 下午1:25:03
	 */
	public void getCombination2( List< Combination > combinations ,
	        CarInfo carInfo1 , CarInfo carInfo2 , TruckInfo truckInfo ,
	        LegInfo legInfo )
	{
		int Temp1 , Temp2 , x1 , x2 , x3 , y1 , y2 , y3;
		double TotalWt1 , RestWt1 , TotalWt2 , RestWt2 , TotalWt3 , RestWt3 , comb1 = 0 , comb2 = 0;
		int maxcap1 = ( int ) ( truckInfo.getLoadingWeight() / carInfo1
		        .getWeight() );
		for ( int i = 0 ; i <= maxcap1 ; i++ )
		{
			Temp1 = maxcap1 - i;
			TotalWt1 = carInfo1.getWeight() * Temp1;
			RestWt1 = truckInfo.getLoadingWeight() - TotalWt1;
			int maxcap2 = ( int ) ( RestWt1 / carInfo2.getWeight() );
			int s_index = 0;
			for ( int j = maxcap2 ; j >= 0 ; j-- )
			{
				Temp2 = j;
				TotalWt1 = carInfo1.getWeight() * Temp1 + carInfo2.getWeight()
				        * Temp2;
				RestWt1 = truckInfo.getLoadingWeight() - TotalWt1;
				boolean Judege = TotalWt1 <= truckInfo.getLoadingWeight();
				s_index++ ;
				if ( Temp1 == comb1 && Temp2 <= comb2 && s_index != 1 )
				{
					Judege = false;
				}
				if ( Judege )
				{
					boolean flag = false;
					for ( int i1 = 0 ; i1 <= Temp1 && ! flag ; i1++ )
					{
						x1 = Temp1 - i1;
						// 第一层装第一种车的最大数量
						if ( x1 <= truckInfo.getLength1()
						        / carInfo1.getLength() )
						{
							for ( int j1 = i1 ; j1 >= 0 && ! flag ; j1-- )
							{
								x2 = j1;
								x3 = i1 - j1;
								// 第二层，第三层装第一种车的最大数量
								if ( x2 <= truckInfo.getLength2()
								        / carInfo1.getLength()
								        && x3 <= truckInfo.getLength3()
								                / carInfo1.getLength() )
								{
									for ( int i2 = 0 ; i2 <= Temp2 && ! flag ; i2++ )
									{
										y1 = Temp2 - i2;
										// 第一层装第二种车的最大数量
										if ( y1 <= truckInfo.getLength1()
										        / carInfo2.getLength() )
										{
											for ( int j2 = i2 ; j2 >= 0
											        && ! flag ; j2-- )
											{
												y2 = j2;
												y3 = i2 - j2;
												// 第二层和第三层装第二种车的最大数量
												if ( y2 <= truckInfo
												        .getLength2()
												        / carInfo2.getLength()
												        && y3 <= truckInfo
												                .getLength3()
												                / carInfo2
												                        .getLength() )
												{
													double TtLen1 = x1
													        * carInfo1
													                .getLength()
													        + y1
													        * carInfo2
													                .getLength();

													double TtLen2 = x2
													        * carInfo1
													                .getLength()
													        + y2
													        * carInfo2
													                .getLength();

													double TtLen3 = x3
													        * carInfo1
													                .getLength()
													        + y3
													        * carInfo2
													                .getLength();
													double RestLen1 = truckInfo
													        .getLength1()
													        - TtLen1;
													double RestLen2 = truckInfo
													        .getLength2()
													        - TtLen2;
													double RestLen3 = truckInfo
													        .getLength3()
													        - TtLen3;
													
													if ( RestLen1 >= 0
													        && RestLen2 >= 0
													        && RestLen3 >= 0 )
													{
														comb1 = Temp1;
														comb2 = Temp2;
														Combination combination = new Combination();
														combination
														        .setCar1num( Temp1 );
														combination
														        .setCar2num( Temp2 );

														combination
														        .setCarInfoByCarId1( carInfo1 );
														combination
														        .setCarInfoByCarId2( carInfo2 );
														
														combination
														        .setLegInfo( legInfo );
														combination
														        .setTotalLength1( TtLen1 );
														combination
														        .setTotalLength2( TtLen2 );
														combination
														        .setTotalLength3( TtLen3 );
														combination
														        .setTruckInfo( truckInfo );
														combination
														        .setTotalWeight( combination
														                .getTotalWeight() );
														combination
														        .setTotalcars( combination
														                .getTotalcars() );
														combinations
														        .add( combination );

														flag = true;
														break;

													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
	}
	
	/**
	 * @Description: 判断当前页是否存在 不存在设置为1
	 * @param request
	 * @return Page 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-16 下午7:27:42
	 */
	public Page getcurrPage( HttpServletRequest request )
	{
		Page p = new Page();
		String page = request.getParameter( "page" );
		String rows = request.getParameter( "rows" );
		String sort = request.getParameter( "sort" );
		if ( StringUtils.isNotBlank( sort ) )
		{
			String order = request.getParameter( "order" );
			p.setSort( sort );
			p.setOrder( order );
		}
		
		if ( ! StringUtils.isNotBlank( page ) )
			page = "1";
		if ( ! StringUtils.isNotBlank( rows ) )
			rows = "10";
		// 设置当前页
		int intPage = page == null || Integer.parseInt( page ) <= 0 ? 1
		        : Integer.parseInt( page );
		// 设置每页显示的数量
		int intPageSize = rows == null || Integer.parseInt( rows ) <= 0 ? 10
		        : Integer.parseInt( rows );
		
		p.setCurrPage( intPage );
		p.setRecordCountperPage( intPageSize );
		
		return p;
	}

	/**
	 * @Description: TODO(根据erp传过来的用户查询相关信息)
	 * @param name
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-17 下午3:28:34
	 */
	public boolean searchUserIfExist( String name )
	{
		Connection conn = DbUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		boolean flag = false;
		try
		{
			String sql = "select t.* from SMUSER t where t.vcuserno2 like '"
			        + name + "'";
			st = conn.createStatement();
			rs = st.executeQuery( sql );
			if ( rs.next() )
			{
				System.out.println( rs.getString( "vcuserno" ) );
				flag = true;
			}
			
		}
		catch ( Exception e )
		{
			System.out.println( "jdbc查询异常" );
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if ( st != null )
				{
					st.close();
				}
			}
			catch ( Exception e2 )
			{
				// TODO: handle exception
			}
			try
			{
				if ( conn != null )
				{
					conn.close();
				}
			}
			catch ( Exception e2 )
			{
				// TODO: handle exception
			}
		}
		return flag;
		
	}

	/**
	 * @Description: TODO(根据运算中所得的一些数据远程请求得到其它数据)
	 * @param combination_all
	 * @param legInfo
	 * @param truckInfo
	 *            void 返回值描述
	 * @author liuwu
	 * @param smUser
	 * @param empty_flag
	 * @create_date 2014-9-24 下午1:58:53
	 */
	public String requestRemote( List< Combination > combination_all ,
	        LegInfo legInfo , TruckInfo truckInfo ,
	        HttpServletResponse response , HttpServletRequest request ,
	        SmUser smUser , String empty_flag )
	{
		
		/**String Gi = "36.5";// Gi：车货总重（板车自重+商品车总重），如：'54'
		String ID = "liuwu"; // 用户名，如：'admin'
		String vcstartcity = "深圳";// vcstartcity：起运城市，如：'南昌'
		String vcstartplace = "深圳比亚迪库";// vcstartplace：起运城市的提车库，如：'南昌江铃迎宾北库'
		String vcdestination = "南昌";// vcdestination：目的地，如：'广州'
		String str_id = "1";// str_id：选用板车的 id ，如：'1'
		String n = "3";// 数据库编号 3为正式数据库
		String msg = null;**/
	
		String Gi = "";
		if ( combination_all.size() > 0 )
		{
			double totalWeight = combination_all.get(
			        combination_all.size() - 1 ).getTotalWeight()
			        + truckInfo.getDcweightSelf();
			Gi = totalWeight + "";
		}
		else
		{
			Gi = truckInfo.getDcweightSelf() + "";// 如果是空载车总重就是板车自重
		}

		String uString = smUser.getUserName();
		if ( uString == null || uString == "" )
		{
			uString = "null";
		}
		String ID = uString;
		String vcstartcity = legInfo.getOrigin();
		String vcstartplace = legInfo.getStartPoint();
		String vcdestination = legInfo.getDestination();
		String str_id = truckInfo.getId() + "";
		String n = "3";// 数据库编号
		String msg = null;
		try
		{
			
			CGenrouteLine genroute = new CGenrouteLine( "EForcecast.do" );
			genroute.buildRequestXMLPrice( Gi , ID , vcstartcity ,
			        vcdestination , vcstartplace , str_id , n , empty_flag );
			Document _doc = genroute.getDocRoute();
			System.out.println( _doc );
			org.jdom.Element root2 = _doc.getRootElement();
			List details = XPath.selectNodes(root2, "//Exception");
			if ( details.size() != 0 )
			{
				org.jdom.Element elementDetail = ( org.jdom.Element ) details
				        .get( 0 );
				msg = elementDetail.getChildText( "msg" );
				System.out.println( msg );
				if ( msg.equals( "success" ) )
				{
					return null;
				}
				else
				{
					return msg;
				}

			}

		}
		catch ( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * @Description: TODO(远程查询调用jar包成功后插入到表t_expense_forecast中的数据)
	 * @return Double 返回值描述
	 * @author liuwu
	 * @param truckInfo
	 * @param legInfo
	 * @param request
	 * @param emptyCost
	 * @param carInfoList
	 * @param smUser
	 * @create_date 2014-9-28 下午2:21:16
	 */
	public Double getActualCostByTruck( LegInfo legInfo , TruckInfo truckInfo ,
	        HttpServletRequest request , Double emptyCost ,
	        List< CarInfo > carInfoList , SmUser smUser )
	{
		Connection conn = DbUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		boolean flag = false;
		double actualCost_truck = 0.0;
		String vcstartcity = legInfo.getOrigin();
		String vcstartpoint = legInfo.getStartPoint();
		String vcdestination = legInfo.getDestination();
		String uString = smUser.getUserName();
		String emptyFlag = "";
		if ( legInfo.getEmptlyFlag().equals( 1 ) )//
		{
			emptyFlag = "1";
		}
		else
		{
			emptyFlag = "0";
		}
		if ( uString == null || uString == "" )
		{
			uString = "pricemodel";
		}
		try
		{
			String sql = "select t.* from t_expense_forecast t where t.vcstartcity='"
			        + vcstartcity
			        + "'and t.vcdestination='"
			        + vcdestination
			        + "'and t.ivechcleid="
			        + truckInfo.getId()
			        + "and t.VCSTARTPLACE = '"
			        + vcstartpoint
			        + "' and username='"
			        + uString
			        + "' and LOADING_FLAG='"
			        + emptyFlag + "'";
			
			st = conn.createStatement();
			rs = st.executeQuery( sql );
			if ( rs.next() )
			{
				/**
				 * 支付公里数
				 */
				double actualDistance = Double.parseDouble( rs
				        .getString( "KILOMETER" ) );
				System.out.println( "线路：" + legInfo.getOrigin() + "-"
				        + legInfo.getStartPoint() + "-"
				        + legInfo.getDestination() + "-" + actualDistance );
				legInfo.setActualDistance( actualDistance
				        + legInfo.getAroundDistance() );// 加上绕城公里数

				/**
				 * 满载成本
				 */
				double fullCostkm = Double.parseDouble( rs
				        .getString( "TOTAL_EXPENSE" ) );

				if ( legInfo.getEmptlyFlag() == 0 )// 非空载
				{
					legInfo.setDays( Math.ceil( legInfo.getActualDistance() / 550 ) + 2 );
				}
				else if ( legInfo.getEmptlyFlag() == 1 )// 空载
				{
					legInfo.setDays( Math.ceil( legInfo.getActualDistance() / 550 ) );
				}

				// 空载距离
				double emptyDistance = legInfo.getEmptyDistance();
				/**
				 * actualCost_truck = (“Full cost/km” * “Actual distance”
				 * +“Empty cost/km” * “Empty distance”) / “Actual distance”
				 */
				// actualCost_truck = ( fullCostkm + emptyCost
				// * emptyDistance )
				// / actualDistance;
				/**
				 * 新更改（20150728）：实际线路成本+(实际线路成本-路桥-罚款-带路)/(实际公里数-绕城) * 绕城公里数
				 * +单公里空载成本*空载距离
				 */
				double roadCost = Double.parseDouble( rs
				        .getString( "TOTAL_ROADCOST" ) );// 路桥费
				double fineCost = Double.parseDouble( rs
				        .getString( "TOTAL_FINECOST" ) );// 罚款
				double leadCost = Double.parseDouble( rs
				        .getString( "TOTAL_LEADCOST" ) );// 带路费
				/*double total_oc = Double
				        .parseDouble( rs.getString( "TOTAL_OC" ) );// 杂费
				fullCostkm = fullCostkm - total_oc + 500.0 + 150.0 + 200.0 + 25
				        * legInfo.getAvgCarsPerTruckCombo();*/
				double total1 = ( fullCostkm - roadCost - fineCost - leadCost )
				        / actualDistance;
				
				actualCost_truck = ( fullCostkm + emptyCost * emptyDistance + total1
				        * legInfo.getAroundDistance() )
				        / legInfo.getActualDistance();

				/**
				 * 所有线路对应商品车的应付单价都除以实际公里数
				 */
				for ( CarInfo carInfo : carInfoList )
				{
					if ( carInfo.getCurrentProCost() != null
					        && carInfo.getCurrentProCost() > 10 )
					{
						carInfo.setCurrentProCost( carInfo.getCurrentProCost()
						        / actualDistance );
					}
				}
			}
			
		}
		catch ( Exception e )
		{
			System.out.println( "t_expense_forecast查询异常" );
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if ( st != null )
				{
					st.close();
				}
			}
			catch ( Exception e2 )
			{
				// TODO: handle exception
			}
			try
			{
				if ( conn != null )
				{
					conn.close();
				}
			}
			catch ( Exception e2 )
			{
				// TODO: handle exception
			}
		}
		return Double.parseDouble( String.format( "%.3f" , actualCost_truck ) );
	}

	/**
	 * @Description: TODO(计算B C :环线、单线分别处理)
	 * @param legInfos
	 * @param routeSummaryForm
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-27 下午1:39:45
	 */
	public void getRouteSummary( List< LegInfo > legInfos ,
	        List< RouteSummary > routeSummaryForm )
	{
		Hashtable< Double , List< LegInfo > > legMap = new Hashtable< Double , List< LegInfo > >();
		for ( LegInfo legInfo : legInfos )
		{
			if ( legMap.containsKey( legInfo.getLoopFlag() ) )
			{
				legMap.get( legInfo.getLoopFlag() ).add( legInfo );
				legMap.put( legInfo.getLoopFlag() ,
				        legMap.get( legInfo.getLoopFlag() ) );
			}
			else
			{
				List< LegInfo > legList = new ArrayList< LegInfo >();
				legList.add( legInfo );
				legMap.put( legInfo.getLoopFlag() , legList );
			}
		}
		Iterator< Double > keys = legMap.keySet().iterator();
		while ( keys.hasNext() )
		{
			Double key = keys.next();
			List< LegInfo > legs = legMap.get( key );
			RouteSummary routeSummary = new RouteSummary();
			routeSummary.setLegInfos( legs );

			double duration = 0;
			for ( LegInfo legInfo : legs )
			{
				if ( legInfo.getDays() != null )
				{
					duration += legInfo.getDays();
				}
				
			}
			routeSummary.setId( key );
			routeSummary.setDuration( duration );// B
			routeSummary.setFrequency( 30 / routeSummary.getDuration() );// C
			routeSummaryForm.add( routeSummary );
		}
	}

	/**
	 * @Description: TODO(计算输出表3中字段I)
	 * @param routeSummaryForm
	 * @param routeLegTruckSummaryForm
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-27 下午4:32:19
	 */
	public void getproportion( List< RouteSummary > routeSummaryForm ,
	        List< RouteLegTruckSummary > routeLegTruckSummaryForm )
	{
		for ( RouteSummary routeSummary : routeSummaryForm )
		{
			double duration = routeSummary.getDuration();
			List< LegInfo > legInfos = routeSummary.getLegInfos();
			for ( LegInfo legInfo : legInfos )
			{
				RouteLegTruckSummary routeLegTruckSummary = new RouteLegTruckSummary();
				routeLegTruckSummary.setLegInfo( legInfo );
				routeLegTruckSummary.setPropOfLeg_route( legInfo.getDays()
				        / duration );
				routeLegTruckSummary.setActualCost_Truck_km( legInfo
				        .getActualCostTruckKm() );
				routeLegTruckSummary.setActualCost_truck_per( legInfo
				        .getActualCostTruckKm() * legInfo.getActualDistance() );// 新增每板数据
				routeLegTruckSummary.setRouteSummary( routeSummary );
				routeLegTruckSummaryForm.add( routeLegTruckSummary );
			}
		}
		
	}

	/**
	 * @Description: TODO(计算输出表3中的R字段)
	 * @param carInfoList
	 * @return double 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-28 下午4:35:37
	 */
	public double getCurrentProCost_truck( List< CarInfo > carInfoList )
	{
		double totalCurProCost_truck = 0.0;
		for ( CarInfo carInfo : carInfoList )
		{
			if ( carInfo.getCurrentProCost() != null )
			{
				totalCurProCost_truck += carInfo.getRatio()
				        * carInfo.getCurrentProCost();
			}

		}
		return totalCurProCost_truck;
	}

	/**
	 * @Description: TODO(计算table4的其它字段)
	 * @param route_RouteLegTruckSummaryMap
	 * @param routeTruckSummary
	 *            void 返回值描述
	 * @author liuwu
	 * @param d
	 * @create_date 2014-12-1 上午9:23:08
	 */
	public void getTable4(
	        Hashtable< Double , List< RouteLegTruckSummary >> route_RouteLegTruckSummaryMap2 ,
	        RouteTruckSummary routeTruckSummary , String caculatetype ,
	        double value )
	{
		Iterator< Double > keys = route_RouteLegTruckSummaryMap2.keySet()
		        .iterator();
		while ( keys.hasNext() )
		{
			Double routeId = ( Double ) keys.next();
			if ( routeId.equals( routeTruckSummary.getRouteSummary().getId() ) )
			{
				List< RouteLegTruckSummary > routeLegTruckSummaries = route_RouteLegTruckSummaryMap2
				        .get( routeId );
				double total_D = 0.0;
				double totla_D2 = 0.0;
				double total_J = 0.0;
				double total_K = 0.0;
				double total_CostDistance = 0.0;
				double total_ActualDistance = 0.0;
				double total_IncomeDistance = 0.0;
				double total_O_and_ActualDistance = 0.0;
				double total_R_and_ActualDistance = 0.0;
				double total_W_and_IncomelDistance = 0.0;
				double total_O = 0.0;
				double total_O2 = 0.0;
				double total_Q = 0.0;
				double total_R = 0.0;
				double total_R2 = 0.0;
				double total_S = 0.0;
				double total_W = 0.0;
				double total_W2 = 0.0;
				double total_Y = 0.0;
				double total_vendorProfit_month = 0.0;
				for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummaries )
				{
					total_D += routeLegTruckSummary.getLegInfo()
					        .getActualCostTruckKm();
					totla_D2 += routeLegTruckSummary.getLegInfo()
					        .getActualCostTruck();
					total_J += routeLegTruckSummary.getAvgCarsPerTruckCombo();
					total_K += routeLegTruckSummary.getActualCost_Month();
					total_ActualDistance += routeLegTruckSummary.getLegInfo()
					        .getActualDistance();

					total_O_and_ActualDistance += routeLegTruckSummary
					        .getNewProCost_truck_km()
					        * routeLegTruckSummary.getLegInfo()
					                .getActualDistance();
					total_O += routeLegTruckSummary.getNewProCost_truck_km();
					total_O2 += routeLegTruckSummary.getNewProCost_truck_per();
					total_Q += routeLegTruckSummary.getNewProCost_month();
					total_R_and_ActualDistance += routeLegTruckSummary
					        .getCurProCost_truck_km()
					        * routeLegTruckSummary.getLegInfo()
					                .getActualDistance();
					total_R += routeLegTruckSummary.getCurProCost_truck_km();
					if ( routeLegTruckSummary.getCurProCost_truck_per() != null )
					{
						total_R2 += routeLegTruckSummary
						        .getCurProCost_truck_per();
					}
					if ( routeLegTruckSummary.getCurProCost_month() != null )
					{
						total_S += routeLegTruckSummary.getCurProCost_month();
					}

					total_W_and_IncomelDistance += routeLegTruckSummary
					        .getAvgIncomePrice_truck_km()
					        * routeLegTruckSummary.getLegInfo()
					                .getIncomeDistance();
					if ( routeLegTruckSummary.getLegInfo().getIncomeDistance() > 1 )
					{
						total_IncomeDistance += routeLegTruckSummary
						        .getLegInfo().getIncomeDistance();
					}
					else
					{
						total_IncomeDistance += routeLegTruckSummary
						        .getLegInfo().getActualDistance();
					}
					if ( routeLegTruckSummary.getLegInfo().getCostDistance() > 1 )
					{
						total_CostDistance += routeLegTruckSummary.getLegInfo()
						        .getCostDistance();
					}
					else
					{
						total_CostDistance += routeLegTruckSummary.getLegInfo()
						        .getActualDistance();

					}

					total_W += routeLegTruckSummary
					        .getAvgIncomePrice_truck_km();
					total_W2 += routeLegTruckSummary
					        .getAvgIncomePrice_truck_per();
					total_Y += routeLegTruckSummary.getAvgIncomePrice_month();
					
					total_vendorProfit_month += routeLegTruckSummary
					        .getVendorProfit_Month();

				}
				// DD = total_D/total_J;
				routeTruckSummary.setTotalActualCost_car_km( total_D / total_J );
				// DD2 = totla_D2 / total_J
				routeTruckSummary.setTotalActualCost_car( totla_D2 / total_J );
				// EE = total_K
				routeTruckSummary.setTotalActualCost_month( total_K );
				// FF = totalVenProfit/C/totalActualDistance
				routeTruckSummary
				        .setTotalVenPro_truck_km( routeLegTruckSummaries
				        .get( 0 ).getTotalVendorProfitMonth()
				        / routeTruckSummary.getRouteSummary().getFrequency()
				        / total_ActualDistance );
				routeTruckSummary
				        .setTotalVenPro_month( total_vendorProfit_month );

				// GG = totalVenProfit/JJ
				if ( caculatetype.equalsIgnoreCase( "venprofit" ) )
				{
					routeTruckSummary.setTotalVendorPro_percent( value );
				}
				else
				{
					routeTruckSummary
					        .setTotalVendorPro_percent( routeLegTruckSummaries
					                .get( 0 ).getTotalVendorProfitMonth()
					                / total_Q );

				}
				// HH = total_O_and_ActualDistance / total_ActualDistance;
				routeTruckSummary
				        .setTotalNewProCost_truck_km( total_O_and_ActualDistance
				                / total_ActualDistance );
				//HH2 = total_O2
				routeTruckSummary.setTotalNewProCost_truck( total_O2 );
				// II = total_O / total_J
				routeTruckSummary.setTotalNewProCost_car_km( total_O / total_J );
				// II2 = HH2 / total_J
				routeTruckSummary.setTotalNewProCost_car( routeTruckSummary
				        .getTotalNewProCost_truck() / total_J );
				// JJ = total_Q
				routeTruckSummary.setTotalNewProCost_month( total_Q );
				// KK = total_R2/totalCostDistance
				routeTruckSummary.setTotalCurProCost_truck_km( total_R2
				        / total_CostDistance );
				//KK2 = total_R2
				routeTruckSummary.setTotalCurProCost_truk( total_R2 );
				
				// LL = total_R / total_J
				routeTruckSummary.setTotalCurProCost_car_km( total_R / total_J );
				// LL2 = KK2/total_J
				routeTruckSummary.setTotalCurProCost_car( routeTruckSummary
				        .getTotalCurProCost_truk() / total_J );
				// MM = total_S
				routeTruckSummary.setTotalCurProCost_month( total_S );
				// QQ = total_W_and_IncomeDistance / total_IncomeDistance
				routeTruckSummary
				        .setTotalAvgInPrice_truck_km( total_W_and_IncomelDistance
				                / total_IncomeDistance );
				// QQ2 = total_W2
				routeTruckSummary.setTotalAvgInPrice_truck( total_W2 );
				// RR = total_W / total_J
				routeTruckSummary.setTotalAvgInPrice_car_km( total_W / total_J );
				// RR2 = QQ2/total_J
				routeTruckSummary.setTotalAvgInPrice_car( routeTruckSummary
				        .getTotalAvgInPrice_truck() / total_J );
				// FF2 = HH2-CC2
				routeTruckSummary.setTotalVenPro_truck( routeTruckSummary
				        .getTotalNewProCost_truck()
				        - routeTruckSummary.getTotalActualCost_truck() );
				// NN2 = KK2 - CC2
				routeTruckSummary.setTotalCurVenPro_truck( routeTruckSummary
				        .getTotalCurProCost_truk()
				        - routeTruckSummary.getTotalActualCost_truck() );
				// SS = total_Y
				routeTruckSummary.setTotalAvgInPrice_month( total_Y );

			}
			
		}


	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param combinationForm
	 *            组合的所有集合
	 * @param leg_w_Map
	 *            为计算输出表3中 W void 返回值描述
	 * @author liuwu
	 * @param sameCombinaton
	 * @param route_leg_truckMap
	 * @param legPriceSummarForm
	 * @create_date 2014-12-1 上午11:55:34
	 */
	public void getDataStep1( List< Combination > combinationForm ,
	        HashMap< Integer , Double > leg_w_Map ,
	        HashMap< Object , Double > totalProb2 ,
	        HashMap< Object , Integer > sameCombinaton ,
	        HashMap< Object , Double > route_leg_truckMap ,
	        List< LegPriceSummary > legPriceSummarForm )
	{
		for ( Combination combination : combinationForm )
		{

			if ( sameCombinaton.containsKey( combination.getPrice() + ","
			        + combination.getLegInfo().getId() ) )
			{
				sameCombinaton.put(
				        combination.getPrice() + ","
				                + combination.getLegInfo().getId() ,
				        sameCombinaton.get( combination.getPrice() + ","
				                + combination.getLegInfo().getId() ) + 1 );
				totalProb2.put(
				        combination.getPrice() + ","
				                + combination.getLegInfo().getId() ,
				        totalProb2.get( combination.getPrice() + ","
				                + combination.getLegInfo().getId() )
				                + combination.getProb2() );
			}
			else
			{
				sameCombinaton.put( combination.getPrice() + ","
				        + combination.getLegInfo().getId() , 1 );
				totalProb2.put( combination.getPrice() + ","
				        + combination.getLegInfo().getId() ,
				        combination.getProb2() );
			}

			if ( leg_w_Map.containsKey( combination.getLegInfo().getId() ) )
			{
				double price_and_prob2 = combination.getProb2()
				        * combination.getPrice();
				leg_w_Map.put( combination.getLegInfo().getId() ,
				        leg_w_Map.get( combination.getLegInfo().getId() )
				                + price_and_prob2 );
			}
			else
			{
				double price_and_prob2 = combination.getProb2()
				        * combination.getPrice();
				leg_w_Map.put( combination.getLegInfo().getId() ,
				        price_and_prob2 );
			}

			if ( route_leg_truckMap.containsKey( combination.getLegInfo()
			        .getId() ) )
			{
				double avgCarPerTruckCombo = combination.getTotalcars()
				        * combination.getProb2();
				route_leg_truckMap.put(
				        combination.getLegInfo().getId() ,
				        route_leg_truckMap.get( combination.getLegInfo()
				                .getId() ) + avgCarPerTruckCombo );
			}
			else
			{
				double avgCarPerTruckCombo = combination.getTotalcars()
				        * combination.getProb2();
				route_leg_truckMap.put( combination.getLegInfo().getId() ,
				        avgCarPerTruckCombo );
			}
			LegPriceSummary legPriceSummary = new LegPriceSummary();
			legPriceSummary.setLegInfo( combination.getLegInfo() );
			legPriceSummary.setPriceBytruck( combination.getPrice() );
			legPriceSummarForm.add( legPriceSummary );
		}

	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param routeLegTruckSummary
	 * @param leg_w_Map
	 * @return double 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-1 下午1:18:34
	 */
	public double getavg_IncomeTruck(
	        RouteLegTruckSummary routeLegTruckSummary ,
	        HashMap< Integer , Double > leg_w_Map )
	{
		double avg_income_truck = 0.0;
		Iterator< Integer > keys = leg_w_Map.keySet().iterator();
		while ( keys.hasNext() )
		{
			Integer legId = keys.next();
			if ( legId == routeLegTruckSummary.getLegInfo().getId() )
			{
				avg_income_truck = leg_w_Map.get( legId );
			}
			
		}
		
		return avg_income_truck;
	}

	/**
	 * @Description: TODO(将输出表3 RouteLegTruckSummary按route分组)
	 * @param route_RouteLegTruckSummaryMap
	 *            void 返回值描述
	 * @author liuwu
	 * @param routeLegTruckSummaryForm
	 * @create_date 2014-12-1 下午2:55:49
	 */
	public void groupByRoute2(
	        Hashtable< Double , List< RouteLegTruckSummary >> route_RouteLegTruckSummaryMap2 ,
	        List< RouteLegTruckSummary > routeLegTruckSummaryForm )
	{
		for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummaryForm )
		{
			if ( route_RouteLegTruckSummaryMap2
			        .containsKey( routeLegTruckSummary
			        .getRouteSummary().getId() ) )
			{
				List< RouteLegTruckSummary > list = route_RouteLegTruckSummaryMap2
				        .get( routeLegTruckSummary.getRouteSummary().getId() );
				list.add( routeLegTruckSummary );
				route_RouteLegTruckSummaryMap2.put( routeLegTruckSummary
				        .getRouteSummary().getId() , list );
			}
			else
			{
				List< RouteLegTruckSummary > routeLegTruckSummaries = new ArrayList< RouteLegTruckSummary >();
				routeLegTruckSummaries.add( routeLegTruckSummary );
				route_RouteLegTruckSummaryMap2.put( routeLegTruckSummary
				        .getRouteSummary().getId() , routeLegTruckSummaries );
			}
			
		}
		
	}

	/**
	 * @Description: TODO(用户输入中联占比 totalVendorProfit=SS*(1-venProvalue)-EE)
	 * @param route_RouteLegTruckSummaryMap
	 * @param venProvalue
	 * @param routeLegTruckSummary
	 * @return double 返回值描述
	 * @author liuwu
	 * @param caculatetype
	 * @create_date 2014-12-1 下午4:03:56
	 */
	public double getTotalVendorProfit(
	        Hashtable< Double , List< RouteLegTruckSummary >> route_RouteLegTruckSummaryMap ,
	        double venProvalue , String caculatetype ,
	        RouteLegTruckSummary routeLegTruckSummary )
	{
		Iterator< Double > keys = route_RouteLegTruckSummaryMap.keySet()
		        .iterator();
		double totalVendorProfit = 0.0;

		while ( keys.hasNext() )
		{
			Double routeId = ( Double ) keys.next();
			if ( routeId
			        .equals( routeLegTruckSummary.getRouteSummary().getId() ) )
			{
				double totalY = 0.0;// SS
				double totalK = 0.0;// EE
				List< RouteLegTruckSummary > routeLegTruckSummaries = route_RouteLegTruckSummaryMap
				        .get( routeId );
				for ( RouteLegTruckSummary routeLegTruckSummary2 : routeLegTruckSummaries )
				{
					totalY += routeLegTruckSummary2.getAvgIncomePrice_month();
					totalK += routeLegTruckSummary2.getActualCost_Month();
				}
				if ( caculatetype.equalsIgnoreCase( "unionprofit" ) )
				{
					// SS*(1-venProvalue)-EE)
					totalVendorProfit = totalY * ( 1 - venProvalue ) - totalK;
				}
				 //分供方占比 totalVendorProfit = EE*venprofit%/(1-venprofit%)
				else if ( caculatetype.equalsIgnoreCase( "venprofit" ) )
				{
					totalVendorProfit = ( totalK * venProvalue )
					        / ( 1 - venProvalue );
				}
				else if ( caculatetype.equalsIgnoreCase( "totalvenprofit" ) )
				{
					totalVendorProfit = venProvalue;
				}

			}
			
		}

		return totalVendorProfit;
	}

}