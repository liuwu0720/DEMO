/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-11-25 下午1:57:16
 * @version V1.0
 */
package com.chnl.controller;

import java.awt.Color;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chnl.base.CreateExcelTable;
import com.chnl.base.CreatePdfTable;
import com.chnl.base.DataControl;
import com.chnl.entity.LegPriceSummary;
import com.chnl.entity.RouteLegTruckSummary;
import com.chnl.entity.RouteSummary;
import com.chnl.entity.RouteTruckSummary;
import com.chnl.entity.SmUser;
import com.chnl.pojo.CarInfo;
import com.chnl.pojo.Combination;
import com.chnl.pojo.LegCarInfo;
import com.chnl.pojo.LegInfo;
import com.chnl.pojo.LegTruckInfo;
import com.chnl.pojo.TruckInfo;
import com.chnl.service.PrAllLegCarInfoService;
import com.chnl.service.PrLegFileImportService;
import com.chnl.service.PrSelfImputLegService;
import com.chnl.service.TruckInfoService;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @Package com.chnl.controller
 * @Description: TODO(开始计算)
 * @author liuwu
 * @date 2014-11-25 下午1:57:16
 * @version V1.0
 */
@Controller
public class CaculateController
{
	@Autowired
	private PrLegFileImportService prLegFileImportService;
	@Autowired
	private PrSelfImputLegService prSelfImputLegService;
	@Autowired
	private PrAllLegCarInfoService prAllLegCarInfoService;
	@Autowired
	private TruckInfoService truckInfoService;
	
	Map< String , Object > legPrice_list_Map = new HashMap< String , Object >();// 复制数据用，为第三个页面创建
	Map< String , Object > routeLegTruckSummary_list_Map = new HashMap< String , Object >();// 复制数据用，为第三个页面创建
	Map< String , Object > routeTruckSummaryList_Map = new HashMap< String , Object >();
	Map< String , Object > truckInfo_Map = new HashMap< String , Object >();
	Map< String , Object > leg_cars_Map = new HashMap< String , Object >();// 每条线路对应的商品车
	Map< String , Object > getcaculatetype_Map = new HashMap< String , Object >();// 计算类型
	Map< String , Object > getcaculatevalue_Map = new HashMap< String , Object >();// 计算值
	Map< String , Object > truckEmptyCost_Map = new HashMap< String , Object >();// 运输车单公里空载成本
	Map< String , Object > legInfos_Map = new HashMap< String , Object >();

	/**
	 * 
	 * @Description: TODO(开始计算)
	 * @param select
	 *            2种类型：用户导入文件importfile 用户自己输入selfinput
	 * @param truck
	 *            拖车id
	 * @param caculatetype
	 *            计算方式
	 * 
	 * @param truckInfo2
	 *            页面传过来的修改后拖车信息
	 * 
	 * @param venpro
	 *            用户输入的三种方式的数值
	 * @param truckcost
	 *            拖车空载成本
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 下午4:17:57
	 */
	@RequestMapping( value = "/startcaculate.do" )
	public ModelAndView startCaculate( String select , String truck ,
	        TruckInfo truckInfo2 ,
	        String caculatetype , String venpro , String truckcost ,
	        HttpServletRequest request , HttpServletResponse response ,
	        HttpSession session , String smUserName )
	{
		List< LegPriceSummary > legPrice_list = new ArrayList< LegPriceSummary >();// 复制数据用，为第三个页面创建
		List< RouteLegTruckSummary > routeLegTruckSummary_list = new ArrayList< RouteLegTruckSummary >();// 复制数据用，为第三个页面创建
		List< RouteTruckSummary > routeTruckSummaryList = null;// 复制数据
		TruckInfo truckInfo; // 拖车
		int type = 0;
		SmUser smUser = new SmUser();
		
		Hashtable< LegInfo , List< CarInfo > > leg_cars = null;// 每条线路对应的商品车
		
		String getcaculatetype;// 计算类型
		String getcaculatevalue;// 计算值
		String truckEmptyCost;
		List< LegInfo > legInfos = null;

		routeTruckSummaryList = new ArrayList< RouteTruckSummary >();// 复制数据
		getcaculatetype = caculatetype;
		getcaculatevalue = venpro;
		truckEmptyCost = truckcost;
		ModelMap modelMap = new ModelMap();
		long time1 = System.currentTimeMillis();
		legInfos = new ArrayList< LegInfo >();
		Double emptyCost = Double.parseDouble( truckcost );// 每公里空载成本
		truckInfo = truckInfoService.findById( Integer.parseInt( truck ) );
		truckInfo.setLength1( truckInfo2.getLength1() );
		truckInfo.setLength2( truckInfo2.getLength2() );
		truckInfo.setLength3( truckInfo2.getLength3() );
		truckInfo.setLoadWeight( truckInfo2.getLoadWeight() );

		DataControl dataControl = new DataControl();// 逻辑运算

		smUser.setUserName( smUserName );
		if ( select.equalsIgnoreCase( "importfile" ) )// 用户导入文件
		{
			type = 0;
			prLegFileImportService.getUseableLegs( legInfos , emptyCost ,
			        smUser );
		}
		else if ( select.equalsIgnoreCase( "selfinput" ) ) // 用户自己输入
		{
			type = 1;
			prSelfImputLegService
			        .getUseableLegs( legInfos , emptyCost , smUser );
		}
		
		boolean flag = true;
		String msg = null;

		List< Combination > combinationForm = new ArrayList< Combination >();// 统计所有线路遍历完后的集合
		for ( LegInfo legInfo : legInfos )
		{
			List< Combination > total1 = new ArrayList< Combination >(); // 先将第一种情况下所有产生的集合放到里面、
			List< Combination > combination_List1 = new ArrayList< Combination >();// 第一种情况下去掉重复元素后的新集合（长度考虑）
			List< Combination > combination_List2 = new ArrayList< Combination >();// 第二种情况下的集合
			List< Combination > combination_List3 = new ArrayList< Combination >();// 第三种情况下的集合
			List< Combination > combination_all = new ArrayList< Combination >();// 三种情况下所有的集合
			if ( legInfo.getEmptlyFlag().equals( 0 ) ) // 非空载情况
			{
				List< CarInfo > carInfoList = new ArrayList< CarInfo >();// 当前线路的所有商品车
				prAllLegCarInfoService.findAllCarInfoByLegId( legInfo.getId() ,
				        type , carInfoList );
				if ( carInfoList.size() == 0 )
				{
					msg = "线路:" + legInfo.getOrigin() + "---"
					        + legInfo.getDestination() + "为非空载线路要选择商品车！";
					flag = false;
					break;
				}
				String[] arr = new String[carInfoList.size()];
				for ( int j = 0 ; j < carInfoList.size() ; j++ )
				{
					arr[j] = carInfoList.get( j ).getId() + "";
				}
				List< String > combinationList = new ArrayList< String >();// 所有商品车组合
				if ( arr.length >= 3 )// 第一种情况： 商品车辆数量大于3的情况组合
				{
					combinationList = dataControl.combine( arr , 3 );
					double maxNum;
					double max2Num;
					double max3Num;
					for ( String carId : combinationList )
					{
						double car1Id = Double
						        .parseDouble( carId.split( " " )[0] );
						double car2Id = Double
						        .parseDouble( carId.split( " " )[1] );
						double car3Id = Double
						        .parseDouble( carId.split( " " )[2] );
					
						CarInfo carInfo1 = new CarInfo();
						CarInfo carInfo2 = new CarInfo();
						CarInfo carInfo3 = new CarInfo();
						for ( CarInfo carInfo : carInfoList )
						{
							if ( carInfo.getId() == car1Id )
							{
								carInfo1.setId( car1Id );
								carInfo1.setCarname( carInfo.getCarname() );
								carInfo1.setCurrentProCost( carInfo
								        .getCurrentProCost() );
								carInfo1.setIncomePrice( carInfo
								        .getIncomePrice() );
								carInfo1.setLength( carInfo.getLength() );
								carInfo1.setRatio( carInfo.getRatio() );
								carInfo1.setWeight( carInfo.getWeight() );
							}
							else if ( carInfo.getId() == car2Id )
							{
								carInfo2.setId( car1Id );
								carInfo2.setCarname( carInfo.getCarname() );
								carInfo2.setCurrentProCost( carInfo
								        .getCurrentProCost() );
								carInfo2.setIncomePrice( carInfo
								        .getIncomePrice() );
								carInfo2.setLength( carInfo.getLength() );
								carInfo2.setRatio( carInfo.getRatio() );
								carInfo2.setWeight( carInfo.getWeight() );
							}
							else if ( carInfo.getId() == car3Id )
							{
								carInfo3.setId( car1Id );
								carInfo3.setCarname( carInfo.getCarname() );
								carInfo3.setCurrentProCost( carInfo
								        .getCurrentProCost() );
								carInfo3.setIncomePrice( carInfo
								        .getIncomePrice() );
								carInfo3.setLength( carInfo.getLength() );
								carInfo3.setRatio( carInfo.getRatio() );
								carInfo3.setWeight( carInfo.getWeight() );
							}
						}
						
						/**
						 * 进行组合运算
						 */
						List< Combination > combinations = new ArrayList< Combination >();
						dataControl.getCombination( combinations , carInfo1 ,
						        carInfo2 , carInfo3 , truckInfo , legInfo );
						total1.addAll( combinations );
					}

					Iterator< Combination > coIterator = total1.iterator();
					
					while ( coIterator.hasNext() )
					{
						Combination combination = coIterator.next();
						
						if ( combination_List1.contains( combination ) )
						{
							
							coIterator.remove();
						}
						else
						{
							combination_List1.add( combination );
						}
						
					}
					System.out.println( "时间："
					        + ( System.currentTimeMillis() - time1 ) );
					/*	double totalProb1 = 0;
						for ( Combination combination : combination_List1 )
						{
							combination.setPrice( combination.getPrice() );
							combination.setProb1( combination.getProb1() );
							totalProb1 += combination.getProb1();
							// combination_List1_length.add( combination );
						}
						
						map.put( legInfo.getId() , totalProb1 );*/
				}
				else
				// 车辆数量小于3的情况组合
				{
					
					combinationList = dataControl.combine( arr , arr.length );
					if ( arr.length == 2 )
					{
						double maxNum;
						for ( String carId : combinationList )
						{
							double car1Id = Double.parseDouble( carId
							        .split( " " )[0] );
							double car2Id = Double.parseDouble( carId
							        .split( " " )[1] );
							/*CarInfo carInfo1 = prAllLegCarInfoService
							        .getCarInfo( car1Id , legInfo.getId() ,
							                type );
							CarInfo carInfo2 = prAllLegCarInfoService
							        .getCarInfo( car2Id , legInfo.getId() ,
							                type );*/
							CarInfo carInfo1 = new CarInfo();
							CarInfo carInfo2 = new CarInfo();
							
							for ( CarInfo carInfo : carInfoList )
							{
								if ( carInfo.getId() == car1Id )
								{
									carInfo1.setId( car1Id );
									carInfo1.setCarname( carInfo.getCarname() );
									carInfo1.setCurrentProCost( carInfo
									        .getCurrentProCost() );
									carInfo1.setIncomePrice( carInfo
									        .getIncomePrice() );
									carInfo1.setLength( carInfo.getLength() );
									carInfo1.setRatio( carInfo.getRatio() );
									carInfo1.setWeight( carInfo.getWeight() );
								}
								else if ( carInfo.getId() == car2Id )
								{
									carInfo2.setId( car1Id );
									carInfo2.setCarname( carInfo.getCarname() );
									carInfo2.setCurrentProCost( carInfo
									        .getCurrentProCost() );
									carInfo2.setIncomePrice( carInfo
									        .getIncomePrice() );
									carInfo2.setLength( carInfo.getLength() );
									carInfo2.setRatio( carInfo.getRatio() );
									carInfo2.setWeight( carInfo.getWeight() );
								}
								
							}
							
							maxNum = truckInfo.getLoadingWeight()
							        / carInfo1.getWeight();
							int car1Num = 0;
							int car2Num = 0;
							List< Combination > combinations = new ArrayList< Combination >();
							/**
							 * 进行组合运算
							 */
							dataControl.getCombination2( combinations ,
							        carInfo1 , carInfo2 , truckInfo , legInfo );
							
							combination_List2.addAll( combinations );
							
						}
						/*	double totalProb2 = 0.0;
							for ( Combination combination : combination_List2 )
							{
								combination.setPrice( combination.getPrice() );
								combination.setProb1( combination.getProb1() );
								totalProb2 += combination.getProb1();
							}
							map.put( legInfo.getId() , totalProb2 );*/
						
					}
					else if ( arr.length == 1 )
					{
						double maxNum;
						for ( String carId : combinationList )
						{
							double car1Id = Double.parseDouble( carId
							        .split( " " )[0] );
							CarInfo carInfo1 = prAllLegCarInfoService
							        .getCarInfo( car1Id , legInfo.getId() ,
							                type );
							maxNum = truckInfo.getLoadingWeight()
							        / carInfo1.getWeight();
							
							for ( int j = ( int ) maxNum ; j > 0 ; j-- )
							{
								int car1Num = j;
								Combination combination = new Combination();
								combination.setCarInfoByCarId1( carInfo1 );
								double totalLength = truckInfo.getLength1()
								        + truckInfo.getLength2()
								        + truckInfo.getLength3();
								if ( combination.getCarInfoByCarId1()
								        .getWeight() * car1Num <= truckInfo
								            .getLoadingWeight()
								        && car1Num * carInfo1.getLength() <= totalLength )
								{
									combination.setCar1num( car1Num );
									combination.setLegInfo( legInfo );
									combination.setCar2num( 0 );
									combination.setTruckInfo( truckInfo );
									combination_List3.add( combination );
									break;
								}
								else
								{
									continue;
								}
								
							}
						}
					}
				}
				combination_all.addAll( combination_List1 );
				combination_all.addAll( combination_List2 );
				combination_all.addAll( combination_List3 );
				
				double sumProb1 = 0.0;
				for ( Combination combination : combination_all )
				{
					combination.setProb1( combination.getProb1() );
					sumProb1 += combination.getProb1();
				}
				
				/**
				 * 计算G
				 */
				for ( Combination combination : combination_all )
				{
					combination.setProb2( combination.getProb1() / sumProb1 );
				}
				
				combinationForm.addAll( combination_all );

				// 组合按总重量进行排序
				Collections.sort( combination_all );
				// 向211服务器请求
				String empty_flag = "0"; // empty_flag=0，表示为重载
				msg = dataControl.requestRemote( combination_all , legInfo ,
				        truckInfo , response , request , smUser , empty_flag );
				if ( msg != null )
				{
					flag = false;
					break;
				}
				
			}
			else
			// 空载情况下直接调用线路模型
			{

				List< Combination > combinations = new ArrayList< Combination >();
				String empty_flag = "1"; // empty_flag=1，表示为空载
				// 向211服务器请求
				msg = dataControl.requestRemote( combinations , legInfo ,
				        truckInfo , response , request , smUser , empty_flag );
				if ( msg != null )
				{
					flag = false;
					break;
				}
			}
			
		}

		if ( flag )
		{
			List< RouteSummary > routeSummaryForm = new ArrayList< RouteSummary >();// 统计线路表：不同环线、不同单线
			List< LegTruckInfo > legTruckInfoForm = new ArrayList< LegTruckInfo >();// 表6
			List< RouteTruckSummary > routeTruckSummarForm = new ArrayList< RouteTruckSummary >();// 表4
			HashMap< Object , Integer > sameCombinaton = new HashMap< Object , Integer >();// 统计相同价格的组合个数
			HashMap< Object , Double > totalProb2 = new HashMap< Object , Double >();// 统计相同价格相同LEG下的prob2总和
			List< LegPriceSummary > legPriceSummarForm = new ArrayList< LegPriceSummary >();// 输出表2
			HashMap< Object , Double > route_leg_truckMap = new HashMap< Object , Double >();// 为了计算输出表3中第5个字段'J'(同一leg下的prob2*总数的和积)
			HashMap< Integer , Double > leg_w_Map = new HashMap< Integer , Double >();// 输出表3：同一条Leg下W与price/truck/km乘积和
			List< LegCarInfo > legCarInfoForm = new ArrayList< LegCarInfo >();// 线路-商品车关系
			List< RouteLegTruckSummary > routeLegTruckSummaryForm = new ArrayList< RouteLegTruckSummary >();// 表3路线-线路-拖车关系表
			leg_cars = new Hashtable< LegInfo , List< CarInfo > >();// 线路对应商品车

			for ( LegInfo legInfo : legInfos )
			{
				LegTruckInfo legTruckInfo = new LegTruckInfo();
				legTruckInfo.setLegInfo( legInfo );
				legTruckInfo.setTruckInfo( truckInfo );
				
				List< CarInfo > carInfoList = new ArrayList< CarInfo >();// 当前线路的所有商品车
				prAllLegCarInfoService.findAllCarInfoByLegId( legInfo.getId() ,
				        type , carInfoList );
				/**
				 * 计算出A D (另外算出商品车的应付单价：大于10的都除以实际公里数)
				 */
				double actualCostTruck = dataControl.getActualCostByTruck(
				        legInfo , truckInfo , request , emptyCost ,
				        carInfoList , smUser );

				/**
				 * 将算出后的线路-商品车存储于此便于图表生成使用
				 */
				
				leg_cars.put( legInfo , carInfoList );
				legTruckInfo.setActualCostByTruck( actualCostTruck );// D
				legInfo.setActualCostTruckKm( actualCostTruck );// D
				legInfo.setActualCostTruck( legInfo.getActualCostTruckKm()
				        * legInfo.getActualDistance() );
				legTruckInfoForm.add( legTruckInfo );
				
				LegCarInfo legCarInfo = new LegCarInfo();
				legCarInfo.setCarInfos( carInfoList );
				legCarInfo.setLegInfo( legInfo );
				legCarInfoForm.add( legCarInfo );
			}
			
			/**
			 * 计算B C
			 */
			dataControl.getRouteSummary( legInfos , routeSummaryForm );
			Collections.sort( routeSummaryForm );
			
			/**
			 * 计算输出表3 I
			 */
			dataControl.getproportion( routeSummaryForm ,
			        routeLegTruckSummaryForm );
			/**
			 * 计算输出表4 CC
			 */
			for ( RouteSummary routeSummary : routeSummaryForm )
			{
				RouteTruckSummary routeTruckSummary = new RouteTruckSummary();
				List< LegInfo > legs = routeSummary.getLegInfos();
				double total_d_and_actualDistance = 0.0;
				double total_actualDistance = 0.0;
				for ( LegInfo legInfo : legs )
				{
					total_actualDistance += legInfo.getActualDistance();
					total_d_and_actualDistance += legInfo
					        .getActualCostTruckKm()
					        * legInfo.getActualDistance();
				}
				// CC
				routeTruckSummary
				        .setTotalActualCost_truck_km( total_d_and_actualDistance
				                / total_actualDistance );
				// CC2
				routeTruckSummary
				        .setTotalActualCost_truck( total_d_and_actualDistance );
				routeTruckSummary.setRouteSummary( routeSummary );
				routeTruckSummarForm.add( routeTruckSummary );
			}

			/**
			 * 得到输出表3 ：leg_w_Map:同一条Leg下W与price/truck/km乘积和
			 * route_leg_truckMap:同一leg下的prob2*总数的和积
			 * 
			 */
			dataControl.getDataStep1( combinationForm , leg_w_Map , totalProb2 ,
			        sameCombinaton , route_leg_truckMap , legPriceSummarForm );
			
			/*for ( LegPriceSummary legPriceSummary : legPriceSummarForm )
			{
				legPriceSummary.setCombinations( sameCombinaton
				        .get( legPriceSummary.getPriceBytruck() + ","
				                + legPriceSummary.getLegInfo().getId() ) );
				legPriceSummary.setProb3( totalProb2.get( legPriceSummary
				        .getPriceBytruck()
				        + ","
				        + legPriceSummary.getLegInfo().getId() ) );
				
			}*/

			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummaryForm )
			{

				double avg_income_truck = dataControl.getavg_IncomeTruck(
				        routeLegTruckSummary , leg_w_Map );
				/**
				 * 得到输出表3 W(Avg.income_price/truck/km)
				 */
				routeLegTruckSummary
				        .setAvgIncomePrice_truck_km( avg_income_truck );
				/**
				 * 输出表3 新增字段 avgIncomePrice_truck_per =
				 * W*IncomeDistance(incomeDistance>1) avgIncomePrice_truck_per =
				 * W*actualdistance(incomeDistance=1)
				 */
				if ( routeLegTruckSummary.getLegInfo().getIncomeDistance() > 1 )
				{
					routeLegTruckSummary
					        .setAvgIncomePrice_truck_per( routeLegTruckSummary
					                .getAvgIncomePrice_truck_km()
					                * routeLegTruckSummary.getLegInfo()
					                        .getIncomeDistance() );
				}
				else
				{
					routeLegTruckSummary
					        .setAvgIncomePrice_truck_per( routeLegTruckSummary
					                .getAvgIncomePrice_truck_km()
					                * routeLegTruckSummary.getLegInfo()
					                        .getActualDistance() );
				}
				
				/**
				 * 得到输出表3 K =(D * C * “Actual distance”)
				 */
				routeLegTruckSummary
				        .setActualCost_Month( routeLegTruckSummary.getLegInfo()
				                .getActualCostTruckKm()
				                * routeLegTruckSummary.getRouteSummary()
				                        .getFrequency()
				                * routeLegTruckSummary.getLegInfo()
				                        .getActualDistance() );
				/**
				 * 得到输出表3 Y=W2*C
				 */
				routeLegTruckSummary
				        .setAvgIncomePrice_month( routeLegTruckSummary
				                .getAvgIncomePrice_truck_per()
				                * routeLegTruckSummary.getRouteSummary()
				                        .getFrequency() );
				/**
				 * 得到输出表3 J=sumproduct the 'totalcars'and G
				 */
				double J = 0.0;
				if ( route_leg_truckMap.get( routeLegTruckSummary.getLegInfo()
				        .getId() ) == null )
				{
					J = 0.0;
				}
				else
				{
					J = route_leg_truckMap.get( routeLegTruckSummary
					        .getLegInfo().getId() );
				}

				routeLegTruckSummary.setAvgCarsPerTruckCombo( Math.floor( J ) );
				routeLegTruckSummary.getLegInfo().setAvgCarsPerTruckCombo(
				        Math.floor( J ) );
				/**
				 * 输出表3 E 新增字段 E=D/J 商品车/每辆/每公里
				 */
				if ( J == 0.0 )
				{
					routeLegTruckSummary.setActualCost_Car_km( null );
				}
				else
				{
					routeLegTruckSummary
					        .setActualCost_Car_km( routeLegTruckSummary
					                .getLegInfo().getActualCostTruckKm()
					                / routeLegTruckSummary
					                        .getAvgCarsPerTruckCombo() );
				}
				/**
				 * E2 = E*ActualDistance
				 */
				if ( routeLegTruckSummary.getLegInfo().getEmptlyFlag()
				        .equals( 1 ) )
				{
					routeLegTruckSummary.setActualCost_car( null );
				}
				else
				{
					routeLegTruckSummary
					        .setActualCost_car( routeLegTruckSummary
					                .getActualCost_Car_km()
					                * routeLegTruckSummary.getLegInfo()
					                        .getActualDistance() );

				}
				
				/**
				 * 输出表3 R = CURRENTPRO_COST_CAR*J(线路对应商品车的比例*应付单价的和)
				 */
				List< CarInfo > carInfoList = new ArrayList< CarInfo >();// 当前线路的所有商品车
				carInfoList = leg_cars.get( routeLegTruckSummary.getLegInfo() );
				/*prAllLegCarInfoService.findAllCarInfoByLegId(
				        routeLegTruckSummary.getLegInfo().getId() , type ,
				        carInfoList );*/
				// 应付单价
				double currentProCost_car = dataControl
				        .getCurrentProCost_truck( carInfoList );
				routeLegTruckSummary.setCurProCost_car_km( currentProCost_car );
				/**
				 * 输出表3 R=currentProCost_car*J;
				 */
				routeLegTruckSummary.setCurProCost_truck_km( currentProCost_car
				        * routeLegTruckSummary.getAvgCarsPerTruckCombo() );
				/**
				 * R3 = currentProCost/car/km*CostDistance (costDistance>1) =
				 * currentProCost/car/km*ActualDistance(costDistance=1)
				 */
				if ( ! routeLegTruckSummary.getLegInfo().getEmptlyFlag()
				        .equals( 1 )
				        && routeLegTruckSummary.getLegInfo().getCostDistance() > 1 )
				{
					routeLegTruckSummary
					        .setCurProCost_car( routeLegTruckSummary
					                .getCurProCost_car_km()
					                * routeLegTruckSummary.getLegInfo()
					                        .getCostDistance() );
				}
				else if ( ! routeLegTruckSummary.getLegInfo().getEmptlyFlag()
				        .equals( 1 )
				        && routeLegTruckSummary.getLegInfo().getCostDistance() == 1 )
				{
					routeLegTruckSummary
					        .setCurProCost_car( routeLegTruckSummary
					                .getCurProCost_car_km()
					                * routeLegTruckSummary.getLegInfo()
					                        .getActualDistance() );
				}
				else
				{
					routeLegTruckSummary.setCurProCost_car( null );
				}
				
				/**
				 * 输出表3 curProCost_truck_per (R2)= R3*J
				 */
				if ( routeLegTruckSummary.getLegInfo().getEmptlyFlag()
				        .equals( 1 ) )
				{
					routeLegTruckSummary.setCurProCost_truck_per( null );
				}
				else if ( routeLegTruckSummary.getCurProCost_car() == null )
				{
					routeLegTruckSummary.setCurProCost_truck_per( null );
				}
				else
				{
					routeLegTruckSummary
					        .setCurProCost_truck_per( routeLegTruckSummary
					                .getCurProCost_car()
					                * routeLegTruckSummary
					                        .getAvgCarsPerTruckCombo() );
				}

				/**
				 * 新需求：输出表3 S=R2*C
				 */
				if ( routeLegTruckSummary.getLegInfo().getEmptlyFlag()
				        .equals( 1 )
				        || routeLegTruckSummary.getCurProCost_truck_per() == null )
				{
					routeLegTruckSummary.setCurProCost_month( null );
				}

				else
				{
					routeLegTruckSummary
					        .setCurProCost_month( routeLegTruckSummary
					                .getCurProCost_truck_per()
					                * routeLegTruckSummary.getRouteSummary()
					                        .getFrequency() );
				}

				/**
				 * 输出表3 T=R-D
				 */
				routeLegTruckSummary
				        .setCurVendorPro_truck_km( routeLegTruckSummary
				                .getCurProCost_truck_km()
				                - routeLegTruckSummary.getLegInfo()
				                        .getActualCostTruckKm() );
				/**
				 * 输出表3 新增字段 T2=R2-D2 curVendorPro_truck_per =
				 * curVendorPro_truck_per - ActualCost/truck
				 */
				if ( routeLegTruckSummary.getLegInfo().getEmptlyFlag()
				        .equals( 1 )
				        || routeLegTruckSummary.getCurProCost_truck_per() == null )
				{
					routeLegTruckSummary.setCurVendorPro_truck_per( null );
				}
				else
				{
					routeLegTruckSummary
					        .setCurVendorPro_truck_per( routeLegTruckSummary
					                .getCurProCost_truck_per()
					                - routeLegTruckSummary
					                        .getActualCost_truck_per() );
				}
				
				/**
				 * 输出表3 U=S-K
				 */
				if ( routeLegTruckSummary.getLegInfo().getEmptlyFlag()
				        .equals( 1 )
				        || routeLegTruckSummary.getCurProCost_month() == null )
				{
					routeLegTruckSummary.setCurVendorPro_month( null );
				}
				else
				{
					routeLegTruckSummary
					        .setCurVendorPro_month( routeLegTruckSummary
					                .getCurProCost_month()
					                - routeLegTruckSummary
					                        .getActualCost_Month() );
				}

				/**
				 * 输出表3 X=W/J
				 */
				if ( routeLegTruckSummary.getAvgCarsPerTruckCombo()
				        .equals( 0.0 ) )
				{
					routeLegTruckSummary.setAvgIncomePrice_car_km( 0.0 );
				}
				else
				{
					routeLegTruckSummary
					        .setAvgIncomePrice_car_km( routeLegTruckSummary
					                .getAvgIncomePrice_truck_km()
					                / routeLegTruckSummary
					                        .getAvgCarsPerTruckCombo() );
				}
				/**
				 * 输出表3 X2 = W2/J
				 */
				if ( routeLegTruckSummary.getAvgCarsPerTruckCombo()
				        .equals( 0.0 ) )
				{
					routeLegTruckSummary.setAvgIncomePrice_car( null );
				}
				else
				{
					routeLegTruckSummary
					        .setAvgIncomePrice_car( routeLegTruckSummary
					                .getAvgIncomePrice_truck_per()
					                / routeLegTruckSummary
					                        .getAvgCarsPerTruckCombo() );
				}
				
				/**
				 * 输出表3 Y=W*C*INCOMEDISTANCE
				 */
				routeLegTruckSummary
				        .setAvgIncomePrice_month( routeLegTruckSummary
				                .getAvgIncomePrice_truck_km()
				                * routeLegTruckSummary.getRouteSummary()
				                        .getFrequency()
				                * routeLegTruckSummary.getLegInfo()
				                        .getIncomeDistance() );
			}
			// 将输出表3 RouteLegTruckSummary按route分组 第一次分组
			Hashtable< Double , List< RouteLegTruckSummary > > route_RouteLegTruckSummaryMap = new Hashtable< Double , List< RouteLegTruckSummary > >();
			dataControl.groupByRoute2( route_RouteLegTruckSummaryMap ,
			        routeLegTruckSummaryForm );

			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummaryForm )
			{
				double totalVenProfit_month = 0.0;
				double venProvalue = Double.parseDouble( venpro );

				totalVenProfit_month = dataControl.getTotalVendorProfit(
				        route_RouteLegTruckSummaryMap , venProvalue ,
				        caculatetype , routeLegTruckSummary );
				routeLegTruckSummary
				        .setTotalVendorProfitMonth( totalVenProfit_month );
				/**
				 * 输出表3 L= totalVenProfit_month * I /C /ACTUALDISTANCE
				 */
				routeLegTruckSummary
				        .setVendorProfit_Truck_km( totalVenProfit_month
				                * routeLegTruckSummary.getPropOfLeg_route()
				                / routeLegTruckSummary.getRouteSummary()
				                        .getFrequency()
				                / routeLegTruckSummary.getLegInfo()
				                        .getActualDistance() );
				/**
				 * 新增字段 vnedorProfit/truck = L *ActualDistance
				 */
				routeLegTruckSummary
				        .setVendorProfit_truck_per( routeLegTruckSummary
				                .getVendorProfit_Truck_km()
				                * routeLegTruckSummary.getLegInfo()
				                        .getActualDistance() );
				
				/**
				 * 输出表3 M = L*C*ACTUALDISTANCE
				 */
				routeLegTruckSummary
				        .setVendorProfit_Month( routeLegTruckSummary
				                .getVendorProfit_Truck_km()
				                * routeLegTruckSummary.getRouteSummary()
				                        .getFrequency()
				                * routeLegTruckSummary.getLegInfo()
				                        .getActualDistance() );
				/**
				 * 输出表3 O = D + L
				 */
				routeLegTruckSummary
				        .setNewProCost_truck_km( routeLegTruckSummary
				                .getLegInfo().getActualCostTruckKm()
				                + routeLegTruckSummary
				                        .getVendorProfit_Truck_km() );
				/**
				 * 新增字段 newProCost_turck_per(每板) =
				 * actual_truck_per*vendorProfit_truck_per
				 */
				routeLegTruckSummary
				        .setNewProCost_truck_per( routeLegTruckSummary
				                .getActualCost_truck_per()
				                + routeLegTruckSummary
				                        .getVendorProfit_truck_per() );
				
				/**
				 * 输出表3 Q=O*C*ACTUALDISTANCE
				 */
				routeLegTruckSummary
				        .setNewProCost_month( routeLegTruckSummary
				                .getNewProCost_truck_km()
				                * routeLegTruckSummary.getRouteSummary()
				                        .getFrequency()
				                * routeLegTruckSummary.getLegInfo()
				                        .getActualDistance() );
				
				/**
				 * 输出表3 N=M/Q
				 */
				routeLegTruckSummary
				        .setVendorProfit_percent( routeLegTruckSummary
				                .getVendorProfit_Month()
				                / routeLegTruckSummary.getNewProCost_month() );
				/**
				 * 输出表3 P=O/J
				 */
				if ( routeLegTruckSummary.getAvgCarsPerTruckCombo()
				        .equals( 0.0 ) )
				{
					routeLegTruckSummary.setNewProCost_car_km( null );
				}
				else
				{
					routeLegTruckSummary
					        .setNewProCost_car_km( routeLegTruckSummary
					                .getNewProCost_truck_km()
					                / routeLegTruckSummary
					                        .getAvgCarsPerTruckCombo() );
				}
				/**
				 * 输出表3 P2=O2/J 如果空载则为空 否则 = O2/J
				 */
				if ( routeLegTruckSummary.getLegInfo().getEmptlyFlag()
				        .equals( 1 ) )
				{
					routeLegTruckSummary.setNewProCost_car( null );
				}
				else
				{
					routeLegTruckSummary
					        .setNewProCost_car( routeLegTruckSummary
					                .getNewProCost_truck_per()
					                / routeLegTruckSummary
					                        .getAvgCarsPerTruckCombo() );
				}
				
				/**
				 * 输出表3 Q=O*C*ACTUALDISTANCE
				 */
				routeLegTruckSummary
				        .setNewProCost_month( routeLegTruckSummary
				                .getNewProCost_truck_km()
				                * routeLegTruckSummary.getRouteSummary()
				                        .getFrequency()
				                * routeLegTruckSummary.getLegInfo()
				                        .getActualDistance() );
				/**
				 * 输出表3 V=U/S
				 */
				if ( routeLegTruckSummary.getCurVendorPro_month() == null
				        || routeLegTruckSummary.getCurProCost_month() == 0.0 )
				{
					routeLegTruckSummary.setCurVendorPro_percent( null );
				}
				else
				{
					routeLegTruckSummary
					        .setCurVendorPro_percent( routeLegTruckSummary
					                .getCurVendorPro_month()
					                / routeLegTruckSummary
					                        .getCurProCost_month() );
				}

				/**
				 * 输出表3 Z=W-O
				 */
				routeLegTruckSummary
				        .setUnionProfit_truck_km( routeLegTruckSummary
				                .getAvgIncomePrice_truck_km()
				                - routeLegTruckSummary.getNewProCost_truck_km() );
				/**
				 * 输出表3 Z2=W2-O2
				 */
				routeLegTruckSummary
				        .setUnionProfit_truck_per( routeLegTruckSummary
				                .getAvgIncomePrice_truck_per()
				                - routeLegTruckSummary
				                        .getNewProCost_truck_per() );
				
				/**
				 * 输出表3 unionProfit_truck_per = avg.IncomePrice/truck -
				 * newProCost/truck
				 */
				routeLegTruckSummary
				        .setUnionProfit_truck_per( routeLegTruckSummary
				                .getAvgIncomePrice_truck_per()
				                - routeLegTruckSummary
				                        .getNewProCost_truck_per() );
				/**
				 * 输出表3 AA=Y-Q
				 */
				routeLegTruckSummary.setUnionProfit_month( routeLegTruckSummary
				        .getAvgIncomePrice_month()
				        - routeLegTruckSummary.getNewProCost_month() );
				/**
				 * 输出表3 BB = AA/Y
				 */
				if ( routeLegTruckSummary.getAvgIncomePrice_month() == 0.0 )
				{
					routeLegTruckSummary.setUnionProfit_percent( null );
				}
				else
				{
					routeLegTruckSummary
					        .setUnionProfit_percent( routeLegTruckSummary
					                .getUnionProfit_month()
					                / routeLegTruckSummary
					                        .getAvgIncomePrice_month() );
				}

			}
			/**
			 * 按route进行第二次分组
			 */
			Hashtable< Double , List< RouteLegTruckSummary > > route_RouteLegTruckSummaryMap2 = new Hashtable< Double , List< RouteLegTruckSummary > >();
			dataControl.groupByRoute2( route_RouteLegTruckSummaryMap2 ,
			        routeLegTruckSummaryForm );
			for ( RouteTruckSummary routeTruckSummary : routeTruckSummarForm )
			{
				dataControl.getTable4( route_RouteLegTruckSummaryMap2 ,
				        routeTruckSummary , caculatetype ,
				        Double.parseDouble( venpro ) );
				// NN = KK - CC
				routeTruckSummary.setTotalCurVenPro_truck_km( routeTruckSummary
				        .getTotalCurProCost_truck_km()
				        - routeTruckSummary.getTotalActualCost_truck_km() );
				// OO = MM - EE
				if ( routeTruckSummary.getTotalCurProCost_month() == null )
				{
					routeTruckSummary.setTotalCurVenPro_month( null );
				}
				else
				{
					routeTruckSummary
					        .setTotalCurVenPro_month( routeTruckSummary
					                .getTotalCurProCost_month()
					                - routeTruckSummary
					                        .getTotalActualCost_month() );
				}
				
				// pp = oo/mm
				if ( routeTruckSummary.getTotalCurVenPro_month() == null
				        || routeTruckSummary.getTotalCurProCost_month() == null )
				{
					routeTruckSummary.setTotalCurVenPro_percent( null );
				}
				else
				{
					routeTruckSummary
					        .setTotalCurVenPro_percent( routeTruckSummary
					                .getTotalCurVenPro_month()
					                / routeTruckSummary
					                        .getTotalCurProCost_month() );
				}

				// TT = QQ - HH
				routeTruckSummary.setTotalUnPro_truck_km( routeTruckSummary
				        .getTotalAvgInPrice_truck_km()
				        - routeTruckSummary.getTotalNewProCost_truck_km() );
				// TT2 = QQ2-HH2
				routeTruckSummary.setTotalUnPro_truck( routeTruckSummary
				        .getTotalAvgInPrice_truck()
				        - routeTruckSummary.getTotalNewProCost_truck() );

				// UU = SS - JJ
				routeTruckSummary.setTotalUnPro_month( routeTruckSummary
				        .getTotalAvgInPrice_month()
				        - routeTruckSummary.getTotalNewProCost_month() );
				// vv = uu /ss
				if ( caculatetype.equalsIgnoreCase( "unionprofit" ) )
				{
					
					routeTruckSummary.setTotalUnPro_percent( Double
					        .parseDouble( venpro ) );
				}
				else
				{
					routeTruckSummary.setTotalUnPro_percent( routeTruckSummary
					        .getTotalUnPro_month()
					        / routeTruckSummary.getTotalAvgInPrice_month() );
				}
			}
			
			/**
			 * 计算H
			 */
			for ( LegPriceSummary legPriceSummary : legPriceSummarForm )
			{
				if ( legPriceSummary.getLegInfo().getEmptlyFlag() == 2 )
				{
					legPriceSummary.setCombinations( null );
					legPriceSummary.setProb3( null );
				}
				else
				{
					legPriceSummary.setCombinations( sameCombinaton
					        .get( legPriceSummary.getPriceBytruck() + ","
					                + legPriceSummary.getLegInfo().getId() ) );
					legPriceSummary.setProb3( totalProb2.get( legPriceSummary
					        .getPriceBytruck()
					        + ","
					        + legPriceSummary.getLegInfo().getId() ) );
				}
				
			}
			legPrice_list = legPriceSummarForm;
			routeLegTruckSummary_list = routeLegTruckSummaryForm;
			routeTruckSummaryList = routeTruckSummarForm;
			modelMap.put( "routeTruckSummarForm" , routeTruckSummarForm );
			modelMap.put( "routeSummaryForm" , routeSummaryForm );
			modelMap.put( "routeLegTruckSummaryForm" , routeLegTruckSummaryForm );
			modelMap.put( "smUser" , smUser );
			
			/*Map< String , Object > legPrice_list_Map = new HashMap< String , Object >();// 复制数据用，为第三个页面创建
			Map< String , Object > routeLegTruckSummary_list_Map = new HashMap< String , Object >();// 复制数据用，为第三个页面创建
			Map< String , Object > routeTruckSummaryList_Map = new HashMap< String , Object >();
			Map< String , Object > truckInfo_Map = new HashMap< String , Object >();
			Map< String , Object > leg_cars_Map = new HashMap< String , Object >();// 每条线路对应的商品车
			Map< String , Object > getcaculatetype_Map = new HashMap< String , Object >();// 计算类型
			Map< String , Object > getcaculatevalue_Map = new HashMap< String , Object >();// 计算值
			Map< String , Object > truckEmptyCost_Map = new HashMap< String , Object >();// 运输车单公里空载成本
			*/
			legPrice_list_Map.put( smUserName , legPrice_list );
			routeLegTruckSummary_list_Map.put( smUserName ,
			        routeLegTruckSummary_list );
			routeTruckSummaryList_Map.put( smUserName , routeTruckSummaryList );
			truckInfo_Map.put( smUserName , truckInfo );
			leg_cars_Map.put( smUserName , leg_cars );
			getcaculatetype_Map.put( smUserName , getcaculatetype );
			getcaculatevalue_Map.put( smUserName , getcaculatevalue );
			truckEmptyCost_Map.put( smUserName , truckEmptyCost );
			legInfos_Map.put( smUserName , legInfos );
			return new ModelAndView( "main/statistics" , modelMap );
		}
		else
		{
			modelMap.put( "errormessage" , msg );
			return new ModelAndView( "error" , modelMap );
		}
		
	}
	
	// 更多详情生成图表

	@RequestMapping( value = "/chart.do" )
	public ModelAndView showDetails( HttpServletRequest request ,
	        HttpServletResponse response , String smUser )
	{
		List< RouteLegTruckSummary > routeLegTruckSummary_list2 = ( List< RouteLegTruckSummary > ) routeLegTruckSummary_list_Map
		        .get( smUser );
		Hashtable< LegInfo , List< CarInfo > > leg_cars = ( Hashtable< LegInfo , List< CarInfo >> ) leg_cars_Map
		        .get( smUser );
		TruckInfo truckInfo = ( TruckInfo ) truckInfo_Map.get( smUser );
		List< LegPriceSummary > legPrice_list = ( List< LegPriceSummary > ) legPrice_list_Map
		        .get( smUser );
		
		
		RouteLegTruckSummary curRouteLegTruckSummary = new RouteLegTruckSummary();
		List< CarInfo > carInfoList = new ArrayList< CarInfo >();// 当前线路的所有商品车
		String curLegId = request.getParameter( "curLegId" );
		if ( StringUtils.isNotBlank( curLegId ) )
		{
			int currLegId = Integer.parseInt( curLegId );

			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list2 )
			{
				if ( routeLegTruckSummary.getLegInfo().getId() == currLegId )
				{
					curRouteLegTruckSummary = routeLegTruckSummary;
				}
			}

			Iterator< LegInfo > keys = leg_cars.keySet().iterator();
			while ( keys.hasNext() )
			{
				LegInfo legInfo = keys.next();
				if ( legInfo.getId() == currLegId )
				{
					carInfoList = leg_cars.get( legInfo );
				}
				
			}

			/*prAllLegCarInfoService.findAllCarInfoByLegId( currLegId , type ,
			        carInfoList );*/
			for ( CarInfo carInfo : carInfoList )
			{
				double maxNum = Math.floor( truckInfo.getLoadingWeight()
				        / carInfo.getWeight() );
				double maxNum2 = Math.floor( ( truckInfo.getLength1()
				        + truckInfo.getLength2() + truckInfo.getLength3() )
				        / carInfo.getLength() );
				double fleetPrice = curRouteLegTruckSummary
				        .getNewProCost_truck_km() / Math.min( maxNum , maxNum2 );
				fleetPrice = Double.parseDouble( String.format( "%.3f" ,
				        fleetPrice ) );
				carInfo.setFleetPrice( fleetPrice );
			}

		}
		DataControl dataControl = new DataControl();
		String createXmlDataFile1 = "";
		String createXmlDataFile2 = "";
		List< LegPriceSummary > curLegPriceSummaries = new ArrayList< LegPriceSummary >();
		for ( LegPriceSummary legPriceSummary : legPrice_list )
		{
			if ( legPriceSummary.getLegInfo().getId()
			        .equals( Integer.parseInt( curLegId ) ) )
			{
				curLegPriceSummaries.add( legPriceSummary );
			}
		}
		
		try
		{
			createXmlDataFile1 = dataControl
			        .createXmlDataFile( curRouteLegTruckSummary ); // 第一张图
			createXmlDataFile2 = dataControl.createXmlDataFile2(
			        curLegPriceSummaries , curLegId ); // 第二张图
			
		}
		catch ( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*	String minvalue = request.getParameter( "min" );
			String maxvalue = request.getParameter( "max" );*/
		ModelMap modelMap = new ModelMap();
		modelMap.put( "curRouteLegTruckSummary" , curRouteLegTruckSummary );
		modelMap.put( "createXmlDataFile1" , createXmlDataFile1 );
		modelMap.put( "createXmlDataFile2" , createXmlDataFile2 );
		modelMap.put( "carInfoList" , carInfoList );

		ModelAndView view = new ModelAndView( "main/chart" , modelMap );
		return view;
		
	}
	
	/**
	 * 
	 * @Description: TODO(导出PDF)
	 * @param request
	 * @param resp
	 * @param user
	 * @throws Exception
	 *             void 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-28 上午10:57:35
	 */
	@SuppressWarnings( "unchecked" )
	@RequestMapping( value = "/exprot.do" )
	public void exportReport( HttpServletRequest request ,
	        HttpServletResponse resp , String user ) throws Exception
	{
		String getcaculatetype = ( String ) getcaculatetype_Map.get( user );
		String getcaculatevalue = ( String ) getcaculatevalue_Map.get( user );
		TruckInfo truckInfo = ( TruckInfo ) truckInfo_Map.get( user );
		String truckEmptyCost = ( String ) truckEmptyCost_Map.get( user );
		List< RouteTruckSummary > routeTruckSummaryList = ( List< RouteTruckSummary > ) routeTruckSummaryList_Map
		        .get( user );
		List< RouteLegTruckSummary > routeLegTruckSummary_list = ( List< RouteLegTruckSummary > ) routeLegTruckSummary_list_Map
		        .get( user );
		Hashtable< LegInfo , List< CarInfo > > leg_cars = ( Hashtable< LegInfo , List< CarInfo >> ) leg_cars_Map
		        .get( user );
		List< LegPriceSummary > legPrice_list = ( List< LegPriceSummary > ) legPrice_list_Map
		        .get( user );
		List< LegInfo > legInfos = ( List< LegInfo > ) legInfos_Map.get( user );
		String fname = "线路模型计算报告.pdf";
		fname = new String( fname.getBytes() , "iso8859-1" );
		
		resp.setHeader( "Content-Disposition" , "attachment; filename=" + fname ); // 主要是向用户显示文件名
		resp.setContentType( "application/pdf" );

		Document document = new Document( PageSize.A4 );
		
		BaseFont bfchina = BaseFont.createFont(
		        "C:/windows/fonts/simsun.ttc,1" , BaseFont.IDENTITY_H ,
		        BaseFont.EMBEDDED );
		PdfWriter.getInstance( document , resp.getOutputStream() );
		document.open();
		BaseFont bfChinese = BaseFont.createFont( "STSong-Light" ,
		        "UniGB-UCS2-H" , BaseFont.NOT_EMBEDDED );// 设置中文字体

		Font p1Font = new Font( bfChinese , 15 , Font.BOLD );// 设置字体大小
		Font h1Font = new Font( bfChinese , 20 , Font.BOLD , Color.RED );// 设置字体大小(标题)
		Font p2Font = new Font( bfChinese , 15 , Font.BOLD , Color.RED );// 设置字体大小
		Font headFont1 = new Font( bfChinese , 10 , Font.BOLD );// 设置字体大小风格湖uhhhfffgui
		Font inputFont = new Font( bfChinese , 15 , Font.ITALIC , Color.BLUE );// 输入表字体
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
		SimpleDateFormat nowTime = new SimpleDateFormat(
		        "yyyy 年 MM月 dd 日 HH ：mm ：ss" );
		Paragraph timeParagraph = new Paragraph(
		        "报告生成时间：" + nowTime.format( d ) , headFont1 );
		timeParagraph.setSpacingBefore( 20f );
		document.add( timeParagraph );
		/**
		 * 正文（用户）
		 */
		Paragraph userParagraph = new Paragraph( "系统操作用户：" + user , headFont1 );
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

		String caculateType = getcaculatetype;
		String caculateValue = getcaculatevalue;
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
		
		String truckType = truckInfo.getTrucktype();
		Chunk p11 = new Chunk( "所选运输车 : " );
		p11.setFont( p1Font );
		Chunk p21 = new Chunk( truckType + "(单公里空载成本= " + truckEmptyCost + ")" );
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
		/**
		 * 写入所选线路
		 */
		CreatePdfTable.getSelectTable( document , routeTruckSummaryList );
		/**
		 * 输出结果
		 */
		Paragraph outParagraph = new Paragraph( "计算结果" , inputFont );
		outParagraph.setSpacingBefore( 50f );
		outParagraph.setAlignment( Element.ALIGN_LEFT );
		document.add( outParagraph );
		
		/**
		 * 写入实际支出表
		 */
		CreatePdfTable.getActualCostTable( document ,
		        routeLegTruckSummary_list , routeTruckSummaryList );
		/**
		 * 写入新的采购支出表
		 */
		CreatePdfTable.getNewProCostTable( document ,
		        routeLegTruckSummary_list , routeTruckSummaryList );
		/**
		 * 写入当前采购支出
		 */
		CreatePdfTable.getCurrentProCostTable( document ,
		        routeLegTruckSummary_list , routeTruckSummaryList );
		/**
		 * 平均收入价格
		 */
		CreatePdfTable.getAvgIncomePrice( document , routeLegTruckSummary_list ,
		        routeTruckSummaryList );
		/**
		 * 新方案下供应商利润
		 */
		CreatePdfTable.getNewVendorProfitTable( document ,
		        routeLegTruckSummary_list , routeTruckSummaryList );
		/**
		 * 当前供应商利润
		 */
		CreatePdfTable.getCurVenProTable( document , routeLegTruckSummary_list ,
		        routeTruckSummaryList );
		/**
		 * 新方案下中联利润
		 */
		CreatePdfTable.getUnionProfitTable( document ,
		        routeLegTruckSummary_list , routeTruckSummaryList );
		/**
		 * 线路与商品车详情
		 */
		for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
		{
			
			List< CarInfo > carInfoList = leg_cars.get( routeLegTruckSummary
			        .getLegInfo() );
			for ( CarInfo carInfo : carInfoList )
			{
				double maxNum = Math.floor( truckInfo.getLoadingWeight()
				        / carInfo.getWeight() );
				double maxNum2 = Math.floor( ( truckInfo.getLength1()
				        + truckInfo.getLength2() + truckInfo.getLength3() )
				        / carInfo.getLength() );
				double fleetPrice = routeLegTruckSummary
				        .getNewProCost_truck_km() / Math.min( maxNum , maxNum2 );
				fleetPrice = Double.parseDouble( String.format( "%.3f" ,
				        fleetPrice ) );
				carInfo.setFleetPrice( fleetPrice );
			}

		}

		CreatePdfTable.getLegCarInfoTable( document , leg_cars );
		/**
		 * 价格区间表
		 */
		CreatePdfTable.getLegPrice( document , legPrice_list , legInfos );
		/**
		 * 线路平均装载量
		 */
		CreatePdfTable
		        .getAvgCarsPerTruck( document , routeLegTruckSummary_list );

		document.close();
	}
	
	/**
	 * 
	 * @Description: TODO(导出EXCEL)
	 * @param request
	 * @param resp
	 * @throws Exception
	 *             void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-5 下午2:16:06
	 */
	@SuppressWarnings( "unchecked" )
	@RequestMapping( value = "/exprot2.do" )
	public void exportReport2( HttpServletRequest request ,
	        HttpServletResponse resp , String user ) throws Exception
	{
		String getcaculatetype = ( String ) getcaculatetype_Map.get( user );
		String getcaculatevalue = ( String ) getcaculatevalue_Map.get( user );
		TruckInfo truckInfo = ( TruckInfo ) truckInfo_Map.get( user );
		String truckEmptyCost = ( String ) truckEmptyCost_Map.get( user );
		List< RouteTruckSummary > routeTruckSummaryList = ( List< RouteTruckSummary > ) routeTruckSummaryList_Map
		        .get( user );
		List< RouteLegTruckSummary > routeLegTruckSummary_list = ( List< RouteLegTruckSummary > ) routeLegTruckSummary_list_Map
		        .get( user );
		Hashtable< LegInfo , List< CarInfo > > leg_cars = ( Hashtable< LegInfo , List< CarInfo >> ) leg_cars_Map
		        .get( user );
		List< LegPriceSummary > legPrice_list = ( List< LegPriceSummary > ) legPrice_list_Map
		        .get( user );
		List< LegInfo > legInfos = ( List< LegInfo > ) legInfos_Map.get( user );
		
		String fname = "线路模型测试报告.xls";
		fname = new String( fname.getBytes() , "iso8859-1" );
		
		resp.setHeader( "Content-Disposition" , "attachment; filename=" + fname ); // 主要是向用户显示文件名
		resp.setContentType( "application/vnd.ms-excel" );
		/**
		 * 报告基本信息：生成时间 操作人等
		 */
		Workbook wb = CreateExcelTable.exportBaseFile( user , truckInfo ,
		        routeTruckSummaryList , getcaculatetype , getcaculatevalue ,
		        truckEmptyCost );

		/**
		 * 实际成本
		 */
		CreateExcelTable.createActualCost( wb , routeLegTruckSummary_list ,
		        routeTruckSummaryList );
		/**
		 * 新的采购价
		 */
		CreateExcelTable.getNewProCostTable( wb , routeLegTruckSummary_list ,
		        routeTruckSummaryList );
		/**
		 * 当前采购支出
		 */
		CreateExcelTable.getCurrentProCostTable( wb ,
		        routeLegTruckSummary_list , routeTruckSummaryList );
		/**
		 * 平均收入价格
		 */
		CreateExcelTable.getAvgIncomePrice( wb , routeLegTruckSummary_list ,
		        routeTruckSummaryList );
		/**
		 * 新方案下供应商利润
		 */
		CreateExcelTable.getNewVendorProfitTable( wb ,
		        routeLegTruckSummary_list , routeTruckSummaryList );
		/**
		 * 当前供应商利润
		 */
		CreateExcelTable.getCurVenProTable( wb , routeLegTruckSummary_list ,
		        routeTruckSummaryList );
		/**
		 * 新方案下中联利润
		 */
		CreateExcelTable.getUnionProfitTable( wb , routeLegTruckSummary_list ,
		        routeTruckSummaryList );
		/**
		 * 线路与商品车详情
		 */
		for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
		{
			
			List< CarInfo > carInfoList = leg_cars.get( routeLegTruckSummary
			        .getLegInfo() );
			for ( CarInfo carInfo : carInfoList )
			{
				double maxNum = Math.floor( truckInfo.getLoadingWeight()
				        / carInfo.getWeight() );
				double maxNum2 = Math.floor( ( truckInfo.getLength1()
				        + truckInfo.getLength2() + truckInfo.getLength3() )
				        / carInfo.getLength() );
				double fleetPrice = routeLegTruckSummary
				        .getNewProCost_truck_km() / Math.min( maxNum , maxNum2 );
				fleetPrice = Double.parseDouble( String.format( "%.3f" ,
				        fleetPrice ) );
				carInfo.setFleetPrice( fleetPrice );
			}
			
		}

		CreateExcelTable.getLegCarInfoTable2( wb , leg_cars );
		/**
		 * 价格区间表
		 */
		CreateExcelTable.getLegPrice( wb , legPrice_list , legInfos );

		OutputStream outputStream = resp.getOutputStream();
		wb.write( outputStream );

		outputStream.flush();
		outputStream.close();
	}
}
