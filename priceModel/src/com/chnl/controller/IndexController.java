/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-7-31 下午3:23:42
 * @version V1.0
 */
package com.chnl.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chnl.base.DataControl;
import com.chnl.entity.LegPriceSummary;
import com.chnl.entity.RouteLegTruckSummary;
import com.chnl.entity.RouteSummary;
import com.chnl.entity.RouteTruckSummary;
import com.chnl.pojo.CarInfo;
import com.chnl.pojo.Combination;
import com.chnl.pojo.LegInfo;
import com.chnl.pojo.LegTruckInfo;
import com.chnl.pojo.TruckInfo;
import com.chnl.service.CarInfoService;
import com.chnl.service.CombinationService;
import com.chnl.service.LegCarInfoService;
import com.chnl.service.LegInfoService;
import com.chnl.service.LegTruckInfoService;
import com.chnl.service.TruckInfoService;

/**
 * @Package com.chnl.controller
 * @Description: TODO(首页控制器：查出首页的相关数据)
 * @author liuwu
 * @date 2014-7-31 下午3:23:42
 * @version V1.0
 */
@Controller
@RequestMapping( "/" )
public class IndexController
{
	
	@Autowired
	private LegInfoService legInfoService;
	@Autowired
	private TruckInfoService truckInfoService;
	@Autowired
	private LegTruckInfoService legTruckInfoService;
	@Autowired
	private CarInfoService carInfoService;
	@Autowired
	private LegCarInfoService legCarInfoService;
	@Autowired
	private CombinationService combinatonService;
	
	/**
	 * 商品车分页设置参数
	 */
	private int page = 1;// 显示第几页，默认第一页
	private int pageSize = 10;//
	private int totalPages;// 全部数量
	
	/**
	 * 线路分页设置参数
	 */
	private int legpage = 1;
	private int legpageSize = 5;
	private int legtotalPages;

	public List< LegInfo > legInfos = new ArrayList< LegInfo >();// 所有线路
	public List< TruckInfo > truckInfos = new ArrayList< TruckInfo >();// 所有拖车信息
	public List< CarInfo > carInfos = new ArrayList< CarInfo >();// 所有商品车信息
	// public List< LegCarInfo > legCarInfos = new ArrayList< LegCarInfo >();
	TruckInfo truckInfo;
	

	LegInfo slegInfo;// 查询所选择的线路
	
	/**
	 * 
	 * @Description: TODO(登录后首页页面)
	 * @param legId
	 *            线路ID
	 * @param truckId
	 *            拖车ID
	 * @param pageNo
	 *            拖车分页
	 * @param legpageNo
	 *            线路分页
	 * @return ModelAndView 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-29 下午4:13:19
	 */
	@SuppressWarnings( "unchecked" )
	@ResponseBody
	@RequestMapping( value = "/index.do" )
	public ModelAndView ShowIndexPage(
	        @RequestParam( required = false ) String legId ,
	        @RequestParam( required = false ) String truckId ,
	        @RequestParam( required = false ) String pageNo ,
	        @RequestParam( required = false ) String legpageNo )
	{
		
		ModelMap modelMap = new ModelMap();
		if ( StringUtils.isNotBlank( legpageNo ) )
		{
			legpage = Integer.parseInt( legpageNo );
		}
		else
		{
			legpage = 1;
		}
		Map< String , Object > legmap = legInfoService.findAllLegsFilter(
		        legpage , legpageSize );
		truckInfos = truckInfoService.findAllTruckInfosFilter();
		legInfos = ( List< LegInfo > ) legmap.get( "rows" );
		int totals = ( Integer ) legmap.get( "total" );
		totals = totals % legpageSize == 0 ? totals / legpageSize : totals
		        / legpageSize + 1;
		modelMap.put( "legInfos" , legInfos );
		modelMap.put( "legtotalPages" , totals );
		modelMap.put( "legpage" , legpage );
		modelMap.put( "truckInfos" , truckInfos );
		if ( StringUtils.isNotBlank( legId )
		        || StringUtils.isNotBlank( truckId ) )
		{
			int reqlegId = Integer.parseInt( legId );
			if ( reqlegId > 0 )
			{
				
				for ( LegInfo legInfo : legInfos )
				{
					if ( legInfo.getId() == reqlegId )
					{
						slegInfo = new LegInfo();
						slegInfo = legInfo;
						LegTruckInfo legTruckInfo = new LegTruckInfo();
						legTruckInfo = legTruckInfoService.findObjByProp(
						        Integer.parseInt( legId ) ,
						        Integer.parseInt( truckId ) );
						if ( legTruckInfo != null )
						{
							double costByTruck = legTruckInfo.getFullcost()
							        * slegInfo.getActualDistance()
							        + legTruckInfo.getEmptcost()
							        * slegInfo.getEmptyDistance();
							
							slegInfo.setCostByTruck( costByTruck );
							if ( StringUtils.isNotBlank( pageNo ) )
							{
								page = Integer.parseInt( pageNo );
							}
							else
							{
								page = 1;
							}
							
							List< CarInfo > carInfos = legCarInfoService
							        .findAllCarInfoByLegIdPage(
							                Integer.parseInt( legId ) , page ,
							                pageSize );
							List< CarInfo > carInfos2 = legCarInfoService
							        .findAllCarInfoByLegId( Integer
							                .parseInt( legId ) );
							totalPages = carInfos2.size() % 10 == 0 ? carInfos2
							        .size() / 10 : carInfos2.size() / 10 + 1;
							modelMap.put( "totalPages" , totalPages );
							modelMap.put( "page" , page );
							modelMap.put( "carInfos" , carInfos );
							modelMap.put( "slegInfo" , slegInfo );
						}
						else
						{
							modelMap.put( "message" , "该线路无此拖车" );
						}
						
					}
				}
			}
		}
		
		ModelAndView view = new ModelAndView( "index" , modelMap );
		
		return view;
	}
	
	/**
	 * @Description: 查询拖车基本信息
	 * @param truckId
	 *            拖车ID
	 * @return TruckInfo 返回值描述 拖车表
	 * @author liuwu
	 * @create_date 2014-9-29 下午4:08:32
	 */
	@RequestMapping( "/getTruckInfo.do" )
	@ResponseBody
	public TruckInfo getTruckInfo(
	        @RequestParam( "truckId" ) String truckId )
	{

		int reqTruckId = Integer.parseInt( truckId );
		truckInfo = truckInfoService.findById( reqTruckId );
		truckInfo.setLength1( truckInfo.getLength1() );
		truckInfo.setLength2( truckInfo.getLength2() );
		truckInfo.setLength3( truckInfo.getLength3() );
		return truckInfo;
	}
	
	List< RouteLegTruckSummary > routeLegTruckSummary_list = new ArrayList< RouteLegTruckSummary >();// 复制数据用，为第三个页面创建
	List< LegPriceSummary > legPrice_list = new ArrayList< LegPriceSummary >();// 复制数据用，为第三个页面创建

	/**
	 * 
	 * @Description: TODO(开始计算)
	 * @param request
	 * @param response
	 * @return ModelAndView 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-29 下午4:12:08
	 */
	@RequestMapping( "/caculate.do" )
	@ResponseBody
	public ModelAndView caculateStep1( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		ModelMap modelMap = new ModelMap();
		
		DataControl dataControl = new DataControl();// 逻辑运算
		Map< Integer , Double > map = new HashMap< Integer , Double >();
		String[] legIdArr = request.getParameter( "legId" ).split( "," );
		double duration = 0;
		List< LegTruckInfo > legTruckInfos = new ArrayList< LegTruckInfo >();
		List< RouteLegTruckSummary > routeLegTruckSummaries = new ArrayList< RouteLegTruckSummary >(); // 输出表3所有集合
		RouteTruckSummary routeTruckSummary = new RouteTruckSummary();// 输出表4
		double total_vendorProit_month = 0;
		if ( request.getParameter( "vendor" ) != null )
		{
			total_vendorProit_month = Double.parseDouble( request
			        .getParameter( "vendor" ) ); // 用户输入的数据:供应商每月利润 如5000/月
		}
		
		/*	for ( int i = 0 ; i < legIdArr.length ; i++ )
			{
				int currentLegId = Integer.parseInt( legIdArr[i] );
				int reqTruckId = Integer
				        .parseInt( request.getParameter( "truckId" ) );
				LegInfo legInfo = legInfoService.findLegInfoById( currentLegId );
				duration += legInfo.getDays();
				LegTruckInfo legTruckInfo = new LegTruckInfo();
				legTruckInfo = legTruckInfoService.getLegInfo( currentLegId ,
				        reqTruckId );
				
					RouteLegTruckSummary routeLegTruckSummary = new RouteLegTruckSummary();
					routeLegTruckSummary.setLegInfo( legInfo );
					routeLegTruckSummary.setTruckInfo( truckInfo );
					// 输出表D字段
					routeLegTruckSummary.setActualCost_Truck( legTruckInfo
					        .getActualCostByTruck() );
					
					routeLegTruckSummaries.add( routeLegTruckSummary );
			}

				
				RouteSummary routeSummary = new RouteSummary(); // 基础数据表2，动态生成不存数据库
				routeSummary.setDuration( duration ); // B
				routeSummary.setFrequency( 30 / routeSummary.getDuration() ); // C
		*/
			List< Combination > total1 = new ArrayList< Combination >(); // 先将第一种情况下所有产生的集合放到里面、
			List< Combination > combination_List1 = new ArrayList< Combination >();// 第一种情况下的集合：
			List< Combination > combination_List1_length = new ArrayList< Combination >(); // 去掉重复元素后的新集合（长度考虑）
			List< Combination > combination_List2 = new ArrayList< Combination >();// 第二种情况下的集合
			List< Combination > combination_List3 = new ArrayList< Combination >();// 第三种情况下的集合
			List< Combination > combination_all = new ArrayList< Combination >();// 所有的集合
			List< LegInfo > legInfos = new ArrayList< LegInfo >();
			for ( int i = 0 ; i < legIdArr.length ; i++ )
			{
				List< CarInfo > carInfoList = new ArrayList< CarInfo >();
				carInfoList = legCarInfoService.findAllCarInfoByLegId( Integer
				        .parseInt( legIdArr[i] ) );
				int currentLegId = Integer.parseInt( legIdArr[i] );
				LegInfo legInfo = legInfoService.findLegInfoById( currentLegId );
				legInfos.add( legInfo );
				String[] arr = new String[carInfoList.size()];
				for ( int j = 0 ; j < carInfoList.size() ; j++ )
				{
					arr[j] = carInfoList.get( j ).getId() + "";
				}
				List< String > combinationList = new ArrayList< String >();// 所有组合
				int reqTruckId = Integer.parseInt( request
				        .getParameter( "truckId" ) );
				TruckInfo truckInfo = truckInfoService.findById( reqTruckId );
				if ( arr.length >= 3 ) // 第一种情况： 商品车辆数量大于3的情况组合
				{
					combinationList = dataControl.combine( arr , 3 );
					double maxNum;
					double max2Num;
					double max3Num;
					for ( String carId : combinationList )
					{
						int car1Id = Integer.parseInt( carId.split( " " )[0] );
						int car2Id = Integer.parseInt( carId.split( " " )[1] );
						int car3Id = Integer.parseInt( carId.split( " " )[2] );
						CarInfo carInfo1 = carInfoService.findCarInfoById(
						        car1Id , currentLegId );
						CarInfo carInfo2 = carInfoService.findCarInfoById(
						        car2Id , currentLegId );
						CarInfo carInfo3 = carInfoService.findCarInfoById(
						        car3Id , currentLegId );
						maxNum = truckInfo.getLoadingWeight()
						        / carInfo1.getWeight();
						List< Combination > combinations = new ArrayList< Combination >();
						/**
						 * 进行组合运算
						 */
						dataControl.getCombination( combinations , carInfo1 ,
						        carInfo2 , carInfo3 , truckInfo , legInfo );
						
						total1.addAll( combinations );
					}
					
					System.out.println( "total1.size()=" + total1.size() );
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
					System.out.println( "combination_List1.size:"
					        + combination_List1.size() );
					
					double totalProb1 = 0;
					for ( Combination combination : combination_List1 )
					{
						combination.setPrice( combination.getPrice() );
						combination.setProb1( combination.getProb1() );
						totalProb1 += combination.getProb1();
						combination_List1_length.add( combination );
					}
					
					map.put( currentLegId , totalProb1 );
				}
				else
				// /车辆数量小于3的情况组合
				{
					combinationList = dataControl.combine( arr , arr.length );
					if ( arr.length == 2 )
					{
						double maxNum;
						for ( String carId : combinationList )
						{
							int car1Id = Integer
							        .parseInt( carId.split( " " )[0] );
							int car2Id = Integer
							        .parseInt( carId.split( " " )[1] );
							CarInfo carInfo1 = carInfoService.findCarInfoById(
							        car1Id , currentLegId );
							CarInfo carInfo2 = carInfoService.findCarInfoById(
							        car2Id , currentLegId );
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
						double totalProb2 = 0.0;
						for ( Combination combination : combination_List2 )
						{
							combination.setPrice( combination.getPrice() );
							combination.setProb1( combination.getProb1() );
							totalProb2 += combination.getProb1();
						}
						map.put( currentLegId , totalProb2 );
						
					}
					else if ( arr.length == 1 )
					{
						double maxNum;
						for ( String carId : combinationList )
						{
							int car1Id = Integer
							        .parseInt( carId.split( " " )[0] );
							CarInfo carInfo1 = carInfoService.findCarInfoById(
							        car1Id , currentLegId );
							maxNum = truckInfo.getLoadingWeight()
							        / carInfo1.getWeight();
							
							for ( int j = ( int ) maxNum ; j > 0 ; j-- )
							{
								int car1Num = ( int ) maxNum;
								Combination combination = new Combination();
								combination.setCarInfoByCarId1( carInfo1 );
								if ( combination.getCarInfoByCarId1()
								        .getWeight() * car1Num <= truckInfo
								            .getLoadingWeight()
								        && car1Num * carInfo1.getLength() <= ( truckInfo
								                .getLength1()
								                + truckInfo.getLength2() + truckInfo
								                    .getLength3() ) )
								{
									combination.setCar1num( car1Num );
									combination.setLegInfo( legInfo );
									combination.setTruckInfo( truckInfo );
									combination.setProb1( combination
									        .getProb1() );
									combination_List3.add( combination );
									map.put( currentLegId ,
									        combination.getProb1() );
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
				
			}
			combination_all.addAll( combination_List1_length );
			combination_all.addAll( combination_List2 );
			combination_all.addAll( combination_List3 );
		boolean flag = true;
		String msg = null;
			// 组合按总重量进行排序
			Collections.sort( combination_all );
			// 向211服务器请求
			for ( LegInfo legInfo : legInfos )
			{
			/*msg = dataControl.requestRemote( combination_all ,
				        legInfo ,
			        truckInfo , response , request );*/
				if ( msg != null )
				{
				flag = false;
					break;
				}
			}
		if ( flag )
		{
			HashMap< Object , Integer > sameCombinaton = new HashMap< Object , Integer >();// 统计相同价格的组合个数
			HashMap< Object , Double > totalProb2 = new HashMap< Object , Double >();// 统计相同价格相同LEG下的prob2总和
			List< LegPriceSummary > legPriceSummaries = new ArrayList< LegPriceSummary >();// 输出表2
			HashMap< Object , Double > route_leg_truckMap = new HashMap< Object , Double >();// 为了计算输出表3中第5个字段'J'(同一leg下的prob2*总数的和积)
			for ( int i = 0 ; i < legIdArr.length ; i++ )
			{
				int currentLegId = Integer.parseInt( legIdArr[i] );
				int reqTruckId = Integer.parseInt( request
				        .getParameter( "truckId" ) );
				LegInfo legInfo = legInfoService.findLegInfoById( currentLegId );
				duration += legInfo.getDays();
				LegTruckInfo legTruckInfo = new LegTruckInfo();
				legTruckInfo.setLegInfo( legInfo );
				legTruckInfo.setTruckInfo( truckInfo );
				/*	legTruckInfo.setActualCostByTruck( dataControl
					        .getActualCostByTruck( legInfo , truckInfo , request ,
					                total_vendorProit_month , carInfos ) );*/
				System.out.println( legTruckInfo.getActualCostByTruck() );
					RouteLegTruckSummary routeLegTruckSummary = new RouteLegTruckSummary();
					routeLegTruckSummary.setLegInfo( legInfo );
					routeLegTruckSummary.setTruckInfo( truckInfo );
					// 输出表D字段
				routeLegTruckSummary.setActualCost_Truck_km( legTruckInfo
					        .getActualCostByTruck() );
					
					routeLegTruckSummaries.add( routeLegTruckSummary );
				

			}
			
			RouteSummary routeSummary = new RouteSummary(); // 基础数据表2，动态生成不存数据库
															// 路线概要
			routeSummary.setDuration( duration ); // B字段 周期
			// C 概率
			routeSummary.setFrequency( 30 / routeSummary.getDuration() );
			double avg_income_truck1 = 0;
			/**
			 * 得到输出表3 W(Avg.income_price/truck/km) ,J(Avg.cars per truck combo);
			 */
			double avg_income_truck = dataControl.caculateStep1(
			        combination_all , sameCombinaton , totalProb2 ,
			        legPriceSummaries , map ,
			        route_leg_truckMap );
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummaries )
			{
				routeLegTruckSummary
				        .setAvgIncomePrice_truck_km( avg_income_truck );
			}
			
			for ( LegPriceSummary legPriceSummary : legPriceSummaries )
			{
				legPriceSummary.setCombinations( sameCombinaton
				        .get( legPriceSummary.getPriceBytruck() + ","
				                + legPriceSummary.getLegInfo().getId() ) );
				legPriceSummary.setProb3( totalProb2.get( legPriceSummary
				        .getPriceBytruck()
				        + ","
				        + legPriceSummary.getLegInfo().getId() ) );
				
			}
			
			/**
			 * 得到输出表4中EE,SS字段;得到输出表3 Y,K,
			 */
			
			dataControl.caculateStep2( routeLegTruckSummaries , routeSummary ,
			        routeTruckSummary );
			/**
			 * 输入UNIONPROFIT% total_vendorPro = SS*(1-UNIONPROFIT%)-EE
			 */
			if ( request.getParameter( "unionpro" ) != null )
			{
				double unionProfit = Double.parseDouble( request
				        .getParameter( "unionpro" ) );
				total_vendorProit_month = routeTruckSummary
				        .getTotalAvgInPrice_month()
				        * ( 1 - unionProfit )
				        - routeTruckSummary.getTotalActualCost_month();
			}
			
			/**
			 * 得到输出表3 I,L,M,O,Q,N
			 */
			dataControl.caculateStep3( routeLegTruckSummaries , routeSummary ,
			        total_vendorProit_month , request , route_leg_truckMap );
			
			legPrice_list = legPriceSummaries;
			/**
			 * 得到输出表3 E R J P S T U V X Z AA BB
			 */
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummaries )
			{
				double J = route_leg_truckMap.get( routeLegTruckSummary
				        .getLegInfo().getId() );
				// 输出表3中E
				routeLegTruckSummary.setActualCost_Car_km( routeLegTruckSummary
				        .getActualCost_Truck_km() / ( int ) J );
				double avgCarPerTruck = Math.floor( J );
				double currentProCost_car = legCarInfoService
				        .getCurrentProCost( routeLegTruckSummary.getLegInfo()
				                .getId() );
				// 输出表3：R
				routeLegTruckSummary.setCurProCost_car_km( currentProCost_car );
				// 输出表3中 R = CURRENTPRO_COST_CAR*J
				routeLegTruckSummary.setCurProCost_truck_km( currentProCost_car
				        * avgCarPerTruck );
				// 输出表3中 J (向下取整)
				routeLegTruckSummary.setAvgCarsPerTruckCombo( avgCarPerTruck );
				// 输出表3中P = O /J
				routeLegTruckSummary.setNewProCost_car_km( routeLegTruckSummary
				        .getNewProCost_truck_km()
				        / routeLegTruckSummary.getAvgCarsPerTruckCombo() );
				// 输出表3中S=R*C*ACTUAL_DISTANCE
				routeLegTruckSummary
				        .setCurProCost_month( routeLegTruckSummary
				                .getCurProCost_truck_km()
				                * routeSummary.getFrequency()
				                * routeLegTruckSummary.getLegInfo()
				                        .getActualDistance() );
				// 输出表3中T=R-D
				routeLegTruckSummary
				        .setCurVendorPro_truck_km( routeLegTruckSummary
				                .getCurProCost_truck_km()
				                - routeLegTruckSummary.getActualCost_Truck_km() );
				// 输出表3中U=S-K
				routeLegTruckSummary
				        .setCurVendorPro_month( routeLegTruckSummary
				                .getCurProCost_month()
				                - routeLegTruckSummary.getActualCost_Month() );
				// 输出表3中V=U/S
				routeLegTruckSummary
				        .setCurVendorPro_percent( routeLegTruckSummary
				                .getCurVendorPro_month()
				                / routeLegTruckSummary.getCurProCost_month() );
				
				// 输出表3中X=W/J
				routeLegTruckSummary
				        .setAvgIncomePrice_car_km( routeLegTruckSummary
				                .getAvgIncomePrice_truck_km()
				                / routeLegTruckSummary
				                        .getAvgCarsPerTruckCombo() );
				// 输出表3中Z=W-O
				routeLegTruckSummary
				        .setUnionProfit_truck_km( routeLegTruckSummary
				        .getAvgIncomePrice_truck_km()
				        - routeLegTruckSummary.getNewProCost_truck_km() );
				// 输出表3中AA=Y-Q
				routeLegTruckSummary.setUnionProfit_month( routeLegTruckSummary
				        .getAvgIncomePrice_month()
				        - routeLegTruckSummary.getNewProCost_month() );
				// 输出表3中BB=AA/Y
				routeLegTruckSummary
				        .setUnionProfit_percent( routeLegTruckSummary
				                .getUnionProfit_month()
				                / routeLegTruckSummary
				                        .getAvgIncomePrice_month() );
			}
			/**
			 * 得到输出表4其它字段
			 */
			dataControl.caculateStep4( routeLegTruckSummaries ,
			        routeTruckSummary , total_vendorProit_month , routeSummary ,
			        request );
			routeLegTruckSummary_list = routeLegTruckSummaries;
			modelMap.put( "routeTruckSummary" , routeTruckSummary );
			modelMap.put( "routeSummary" , routeSummary );
			modelMap.put( "routeLegTruckSummaries" , routeLegTruckSummaries );
			return new ModelAndView( "screen2" , modelMap );
		}
		else
		{
			modelMap.put( "errormessage" , msg );
			return new ModelAndView( "error" , modelMap );
		}

		
	}
	
	// 更多详情
	@ResponseBody
	@RequestMapping( value = "/detail.do" )
	public ModelAndView showDetails( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		RouteLegTruckSummary curRouteLegTruckSummary = new RouteLegTruckSummary();
		String curLegId = request.getParameter( "curLegId" );
		if ( StringUtils.isNotBlank( curLegId ) )
		{
			int currLegId = Integer.parseInt( curLegId );
			for ( RouteLegTruckSummary routeLegTruckSummary : routeLegTruckSummary_list )
			{
				if ( routeLegTruckSummary.getLegInfo().getId() == currLegId )
				{
					curRouteLegTruckSummary = routeLegTruckSummary;
				}
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

		ModelMap modelMap = new ModelMap();
		modelMap.put( "curRouteLegTruckSummary" , curRouteLegTruckSummary );
		modelMap.put( "createXmlDataFile1" , createXmlDataFile1 );
		modelMap.put( "createXmlDataFile2" , createXmlDataFile2 );
		ModelAndView view = new ModelAndView( "screen3" , modelMap );
		return view;
		
	}
	
	

}