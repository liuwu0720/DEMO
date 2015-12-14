/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-21 下午6:44:33 
 * @version V1.0 
 */
package com.chnl.entity;

import com.chnl.pojo.LegInfo;
import com.chnl.pojo.TruckInfo;

/** 
 * @Package com.chnl.pojo 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-21 下午6:44:33 
 * @version V1.0 
 */
public class RouteLegTruckSummary
{	
	private RouteSummary routeSummary;
	private LegInfo legInfo; //Field2
	private TruckInfo truckInfo;      //Field3
	private Double propOfLeg_route;    //Field4
	private Double avgCarsPerTruckCombo;// Field5
	private Double actualCost_Month; // 实际支出 每月
	private Double actualCost_Truck_km;// 实际支出 /板/公里
	private Double actualCost_Car_km;// 实际支出 /辆/公里
	private Double actualCost_car;// 实际支出 /辆
	private Double actualCost_truck_per;// 实际支出 /每板
	private Double vendorProfit_Truck_km; // 新方案下供应商利润 每板每公里
	private Double vendorProfit_Month; // 新方案下供应商利润 每月
	private Double vendorProfit_percent;// 新方案下供应商利润
	private Double vendorProfit_truck_per;// 新方案下供应商利润 每板
	private Double newProCost_truck_km; // 新的采购支出 /板/公里
	private Double newProCost_car_km; // 新的采购支出 /辆/公里
	private Double newProCost_car;// 新的采购支出 /辆
	private Double newProCost_month; // 新的采购支出 /月
	private Double newProCost_truck_per;// 新的采购支出 /板
	private Double curProCost_truck_km; // 当前采购支出
	private Double curProCost_car_km; // 当前采购支出
	private Double curProCost_car;// 当前采购支出
	private Double curProCost_month; // 当前采购支出
	private Double curProCost_truck_per;// 当前采购支出

	private Double curVendorPro_truck_km; // 当前供应商利润
	private Double curVendorPro_month; // 当前供应商利润
	private Double curVendorPro_percent;// 当前供应商利润
	private Double curVendorPro_truck_per;
	private Double avgIncomePrice_truck_km;// 平均收入价格
	private Double avgIncomePrice_truck_per;// 平均收入价格 每板
	private Double avgIncomePrice_car_km; // 平均收入价格
	private Double avgIncomePrice_car;// 平均收入价格
	private Double avgIncomePrice_month;// 平均收入价格
	private Double unionProfit_truck_km; // 新方案下中联利润
	private Double unionProfit_month; // 新方案下中联利润
	private Double unionProfit_percent;// 新方案下中联利润率
	private Double unionProfit_truck_per;// 新方案下中联利润 每板
	private Double totalVendorProfitMonth;

	public RouteSummary getRouteSummary()
    {
    	return routeSummary;
    }
	public void setRouteSummary( RouteSummary routeSummary )
    {
    	this.routeSummary = routeSummary;
    }
	public LegInfo getLegInfo()
    {
    	return legInfo;
    }
	
	public Double getActualCost_Truck_km()
    {
		return actualCost_Truck_km;
    }
	public void setActualCost_Truck_km( Double actualCost_Truck_km )
    {
		this.actualCost_Truck_km = actualCost_Truck_km;
    }

	public Double getActualCost_Car_km()
    {
		return actualCost_Car_km;
    }
	
	public void setActualCost_Car_km( Double actualCost_Car_km )
    {
		this.actualCost_Car_km = actualCost_Car_km;
    }
	public void setLegInfo( LegInfo legInfo )
    {
    	this.legInfo = legInfo;
    }
	
	public Double getCurProCost_car_km()
    {
		return curProCost_car_km;
    }
	
	public void setCurProCost_car_km( Double curProCost_car_km )
    {
		this.curProCost_car_km = curProCost_car_km;
    }
	public TruckInfo getTruckInfo()
    {
    	return truckInfo;
    }
	public void setTruckInfo( TruckInfo truckInfo )
    {
    	this.truckInfo = truckInfo;
    }
	public Double getPropOfLeg_route()
    {
    	return propOfLeg_route;
    }
	public void setPropOfLeg_route( Double propOfLeg_route )
    {
    	this.propOfLeg_route = propOfLeg_route;
    }


	public Double getAvgCarsPerTruckCombo()
    {
    	return avgCarsPerTruckCombo;
    }
	
	public void setAvgCarsPerTruckCombo( Double avgCarsPerTruckCombo )
    {
    	this.avgCarsPerTruckCombo = avgCarsPerTruckCombo;
    }
	public Double getActualCost_Month()
    {
    	return actualCost_Month;
    }
	public void setActualCost_Month( Double actualCost_Month )
    {
    	this.actualCost_Month = actualCost_Month;
    }
	
	public Double getVendorProfit_Truck_km()
    {
		return vendorProfit_Truck_km;
    }
	
	public void setVendorProfit_Truck_km( Double vendorProfit_Truck_km )
    {
		this.vendorProfit_Truck_km = vendorProfit_Truck_km;
    }
	public Double getVendorProfit_Month()
    {
    	return vendorProfit_Month;
    }
	public void setVendorProfit_Month( Double vendorProfit_Month )
    {
    	this.vendorProfit_Month = vendorProfit_Month;
    }
	public Double getVendorProfit_percent()
    {
    	return vendorProfit_percent;
    }
	public void setVendorProfit_percent( Double vendorProfit_percent )
    {
    	this.vendorProfit_percent = vendorProfit_percent;
    }
	
	public Double getNewProCost_truck_km()
    {
		return newProCost_truck_km;
    }
	
	public void setNewProCost_truck_km( Double newProCost_truck_km )
    {
		this.newProCost_truck_km = newProCost_truck_km;
    }
	
	public Double getNewProCost_car_km()
    {
		return newProCost_car_km;
    }
	
	public void setNewProCost_car_km( Double newProCost_car_km )
    {
		this.newProCost_car_km = newProCost_car_km;
    }
	public Double getNewProCost_month()
    {
    	return newProCost_month;
    }
	public void setNewProCost_month( Double newProCost_month )
    {
    	this.newProCost_month = newProCost_month;
    }
	
	public Double getCurProCost_truck_km()
    {
		return curProCost_truck_km;
    }
	
	public void setCurProCost_truck_km( Double curProCost_truck_km )
    {
		this.curProCost_truck_km = curProCost_truck_km;
    }
	public Double getCurProCost_month()
    {
    	return curProCost_month;
    }
	public void setCurProCost_month( Double curProCost_month )
    {
    	this.curProCost_month = curProCost_month;
    }
	
	public Double getCurVendorPro_truck_km()
    {
		return curVendorPro_truck_km;
    }
	
	public void setCurVendorPro_truck_km( Double curVendorPro_truck_km )
    {
		this.curVendorPro_truck_km = curVendorPro_truck_km;
    }
	public Double getCurVendorPro_month()
    {
    	return curVendorPro_month;
    }
	public void setCurVendorPro_month( Double curVendorPro_month )
    {
    	this.curVendorPro_month = curVendorPro_month;
    }
	public Double getCurVendorPro_percent()
    {
    	return curVendorPro_percent;
    }
	public void setCurVendorPro_percent( Double curVendorPro_percent )
    {
    	this.curVendorPro_percent = curVendorPro_percent;
    }
	
	public Double getAvgIncomePrice_truck_km()
    {
		return avgIncomePrice_truck_km;
    }
	
	public void setAvgIncomePrice_truck_km( Double avgIncomePrice_truck_km )
    {
		this.avgIncomePrice_truck_km = avgIncomePrice_truck_km;
    }
	
	public Double getAvgIncomePrice_car_km()
    {
		return avgIncomePrice_car_km;
    }
	
	public void setAvgIncomePrice_car_km( Double avgIncomePrice_car_km )
    {
		this.avgIncomePrice_car_km = avgIncomePrice_car_km;
    }
	public Double getAvgIncomePrice_month()
    {
    	return avgIncomePrice_month;
    }
	public void setAvgIncomePrice_month( Double avgIncomePrice_month )
    {
    	this.avgIncomePrice_month = avgIncomePrice_month;
    }
	
	public Double getUnionProfit_truck_km()
    {
		return unionProfit_truck_km;
    }
	
	public void setUnionProfit_truck_km( Double unionProfit_truck_km )
    {
		this.unionProfit_truck_km = unionProfit_truck_km;
    }
	public Double getUnionProfit_month()
    {
    	return unionProfit_month;
    }
	public void setUnionProfit_month( Double unionProfit_month )
    {
    	this.unionProfit_month = unionProfit_month;
    }
	public Double getUnionProfit_percent()
    {
    	return unionProfit_percent;
    }
	public void setUnionProfit_percent( Double unionProfit_percent )
    {
    	this.unionProfit_percent = unionProfit_percent;
    }
	
	public Double getTotalVendorProfitMonth()
	{
		return totalVendorProfitMonth;
	}
	
	public void setTotalVendorProfitMonth( Double totalVendorProfitMonth )
	{
		this.totalVendorProfitMonth = totalVendorProfitMonth;
	}
	
	/**
     * 
     */
	public RouteLegTruckSummary()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Double getVendorProfit_truck_per()
	{
		return vendorProfit_truck_per;
	}
	
	public void setVendorProfit_truck_per( Double vendorProfit_truck_per )
	{
		this.vendorProfit_truck_per = vendorProfit_truck_per;
	}
	
	public Double getActualCost_truck_per()
	{
		return actualCost_truck_per;
	}
	
	public void setActualCost_truck_per( Double actualCost_truck_per )
	{
		this.actualCost_truck_per = actualCost_truck_per;
	}
	
	public Double getNewProCost_truck_per()
	{
		return newProCost_truck_per;
	}
	
	public void setNewProCost_truck_per( Double newProCost_truck_per )
	{
		this.newProCost_truck_per = newProCost_truck_per;
	}
	
	public Double getCurProCost_truck_per()
	{
		return curProCost_truck_per;
	}
	
	public void setCurProCost_truck_per( Double curProCost_truck_per )
	{
		this.curProCost_truck_per = curProCost_truck_per;
	}
	
	public Double getCurVendorPro_truck_per()
	{
		return curVendorPro_truck_per;
	}
	
	public void setCurVendorPro_truck_per( Double curVendorPro_truck_per )
	{
		this.curVendorPro_truck_per = curVendorPro_truck_per;
	}
	
	public Double getAvgIncomePrice_truck_per()
	{
		return avgIncomePrice_truck_per;
	}
	
	public void setAvgIncomePrice_truck_per( Double avgIncomePrice_truck_per )
	{
		this.avgIncomePrice_truck_per = avgIncomePrice_truck_per;
	}
	
	public Double getUnionProfit_truck_per()
	{
		return unionProfit_truck_per;
	}
	
	public void setUnionProfit_truck_per( Double unionProfit_truck_per )
	{
		this.unionProfit_truck_per = unionProfit_truck_per;
	}
	
	public Double getNewProCost_car()
	{
		return newProCost_car;
	}
	
	public void setNewProCost_car( Double newProCost_car )
	{
		this.newProCost_car = newProCost_car;
	}
	
	public Double getCurProCost_car()
	{
		return curProCost_car;
	}
	
	public void setCurProCost_car( Double curProCost_car )
	{
		this.curProCost_car = curProCost_car;
	}
	
	public Double getAvgIncomePrice_car()
	{
		return avgIncomePrice_car;
	}
	
	public void setAvgIncomePrice_car( Double avgIncomePrice_car )
	{
		this.avgIncomePrice_car = avgIncomePrice_car;
	}
	
	public Double getActualCost_car()
	{
		return actualCost_car;
	}
	
	public void setActualCost_car( Double actualCost_car )
	{
		this.actualCost_car = actualCost_car;
	}
	
	
}
