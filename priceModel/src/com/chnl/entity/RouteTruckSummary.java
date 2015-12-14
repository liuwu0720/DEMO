/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-21 下午7:28:30 
 * @version V1.0 
 */
package com.chnl.entity;

import com.chnl.pojo.TruckInfo;

/** 
 * @Package com.chnl.pojo 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-21 下午7:28:30 
 * @version V1.0 
 */
public class RouteTruckSummary
{	
	private RouteSummary routeSummary;
	private TruckInfo truckInfo;
	private Double totalActualCost_truck_km; // 实际支出
	private Double totalActualCost_truck;// 实际支出
	private Double totalActualCost_car_km; // 实际支出
	private Double totalActualCost_car;// 实际支出
	private Double totalActualCost_month; // 实际支出
	private Double totalVenPro_truck_km; // 新方案下供应商利润
	private Double totalVenPro_truck;// 新方案下供应商利润
	private Double totalVenPro_month;// 新方案下供应商利润
	private Double totalVendorPro_percent; // 新方案下供应商利润
	private Double totalNewProCost_truck_km; // 新的采购支出
	private Double totalNewProCost_truck;// 新的采购支出
	private Double totalNewProCost_car_km; // 新的采购支出
	private Double totalNewProCost_car;// 新的采购支出
	private Double totalNewProCost_month; // 新的采购支出
	private Double totlaNewProCost_car;// 新的采购支出
	private Double totalCurProCost_truck_km;// 当前采购支出
	private Double totalCurProCost_truk;// 当前采购支出
	private Double totalCurProCost_month;// 当前采购支出
	private Double totalCurProCost_car_km;// 当前采购支出
	private Double totalCurProCost_car;// 当前采购支出
	private Double totalCurVenPro_truck_km;// 当前供应商利润
	private Double totalCurVenPro_truck;// 当前供应商利润
	private Double totalCurVenPro_month;// 当前供应商利润
	private Double totalCurVenPro_percent;// 当前供应商利润
	private Double totalAvgInPrice_truck_km;// 平均收入价格
	private Double totalAvgInPrice_truck;// 平均收入价格
	private Double totalAvgInPrice_car_km;// 平均收入价格
	private Double totalAvgInPrice_car;// 平均收入价格
	private Double totalAvgInPrice_month;// 平均收入价格
	private Double totalUnPro_truck_km;// 新方案下中联利润
	private Double totalUnPro_truck;// 新方案下中联利润
	private Double totalUnPro_month;// 新方案下中联利润
	private Double totalUnPro_percent;// 新方案下中联利润
	public TruckInfo getTruckInfo()
    {
    	return truckInfo;
    }
	public void setTruckInfo( TruckInfo truckInfo )
    {
    	this.truckInfo = truckInfo;
    }
	
	public Double getTotalActualCost_truck_km()
    {
		return totalActualCost_truck_km;
    }
	
	public void setTotalActualCost_truck_km( Double totalActualCost_truck_km )
    {
		this.totalActualCost_truck_km = totalActualCost_truck_km;
    }
	
	public Double getTotalActualCost_car_km()
    {
		return totalActualCost_car_km;
    }
	
	public void setTotalActualCost_car_km( Double totalActualCost_car_km )
    {
		this.totalActualCost_car_km = totalActualCost_car_km;
    }
	public Double getTotalActualCost_month()
    {
    	return totalActualCost_month;
    }
	public void setTotalActualCost_month( Double totalActualCost_month )
    {
    	this.totalActualCost_month = totalActualCost_month;
    }
	
	public Double getTotalVenPro_truck_km()
    {
		return totalVenPro_truck_km;
    }
	
	public void setTotalVenPro_truck_km( Double totalVenPro_truck_km )
    {
		this.totalVenPro_truck_km = totalVenPro_truck_km;
    }
	public Double getTotalVendorPro_percent()
    {
    	return totalVendorPro_percent;
    }
	public void setTotalVendorPro_percent( Double totalVendorPro_percent )
    {
    	this.totalVendorPro_percent = totalVendorPro_percent;
    }
	
	public Double getTotalNewProCost_truck_km()
    {
		return totalNewProCost_truck_km;
    }
	
	public void setTotalNewProCost_truck_km( Double totalNewProCost_truck_km )
    {
		this.totalNewProCost_truck_km = totalNewProCost_truck_km;
    }
	
	public Double getTotalCurVenPro_truck_km()
    {
		return totalCurVenPro_truck_km;
    }
	
	public void setTotalCurVenPro_truck_km( Double totalCurVenPro_truck_km )
    {
		this.totalCurVenPro_truck_km = totalCurVenPro_truck_km;
    }
	
	public Double getTotalNewProCost_car_km()
    {
		return totalNewProCost_car_km;
    }
	
	public void setTotalNewProCost_car_km( Double totalNewProCost_car_km )
    {
		this.totalNewProCost_car_km = totalNewProCost_car_km;
    }
	public Double getTotalNewProCost_month()
    {
    	return totalNewProCost_month;
    }
	public void setTotalNewProCost_month( Double totalNewProCost_month )
    {
    	this.totalNewProCost_month = totalNewProCost_month;
    }
	
	public Double getTotalCurProCost_truck_km()
    {
		return totalCurProCost_truck_km;
    }
	
	public void setTotalCurProCost_truck_km( Double totalCurProCost_truck_km )
    {
		this.totalCurProCost_truck_km = totalCurProCost_truck_km;
    }
	public Double getTotalCurProCost_month()
    {
    	return totalCurProCost_month;
    }
	public void setTotalCurProCost_month( Double totalCurProCost_month )
    {
    	this.totalCurProCost_month = totalCurProCost_month;
    }


	public Double getTotalCurVenPro_month()
    {
    	return totalCurVenPro_month;
    }
	public void setTotalCurVenPro_month( Double totalCurVenPro_month )
    {
    	this.totalCurVenPro_month = totalCurVenPro_month;
    }
	public Double getTotalCurVenPro_percent()
    {
    	return totalCurVenPro_percent;
    }
	public void setTotalCurVenPro_percent( Double totalCurVenPro_percent )
    {
    	this.totalCurVenPro_percent = totalCurVenPro_percent;
    }
	
	public Double getTotalAvgInPrice_truck_km()
    {
		return totalAvgInPrice_truck_km;
    }
	
	public void setTotalAvgInPrice_truck_km( Double totalAvgInPrice_truck_km )
    {
		this.totalAvgInPrice_truck_km = totalAvgInPrice_truck_km;
    }
	
	public Double getTotalAvgInPrice_car_km()
    {
		return totalAvgInPrice_car_km;
    }
	
	public void setTotalAvgInPrice_car_km( Double totalAvgInPrice_car_km )
    {
		this.totalAvgInPrice_car_km = totalAvgInPrice_car_km;
    }
	public Double getTotalAvgInPrice_month()
    {
    	return totalAvgInPrice_month;
    }
	public void setTotalAvgInPrice_month( Double totalAvgInPrice_month )
    {
    	this.totalAvgInPrice_month = totalAvgInPrice_month;
    }
	
	public Double getTotalUnPro_truck_km()
    {
		return totalUnPro_truck_km;
    }
	
	public void setTotalUnPro_truck_km( Double totalUnPro_truck_km )
    {
		this.totalUnPro_truck_km = totalUnPro_truck_km;
    }
	public Double getTotalUnPro_month()
    {
    	return totalUnPro_month;
    }
	public void setTotalUnPro_month( Double totalUnPro_month )
    {
    	this.totalUnPro_month = totalUnPro_month;
    }
	public Double getTotalUnPro_percent()
    {
    	return totalUnPro_percent;
    }
	public void setTotalUnPro_percent( Double totalUnPro_percent )
    {
    	this.totalUnPro_percent = totalUnPro_percent;
    }
	
	public Double getTotalCurProCost_car_km()
    {
		return totalCurProCost_car_km;
    }
	
	public void setTotalCurProCost_car_km( Double totalCurProCost_car_km )
    {
		this.totalCurProCost_car_km = totalCurProCost_car_km;
    }
	
	public RouteSummary getRouteSummary()
	{
		return routeSummary;
	}
	
	public void setRouteSummary( RouteSummary routeSummary )
	{
		this.routeSummary = routeSummary;
	}
	
	public Double getTotalVenPro_month()
	{
		return totalVenPro_month;
	}
	
	public void setTotalVenPro_month( Double totalVenPro_month )
	{
		this.totalVenPro_month = totalVenPro_month;
	}
	
	public Double getTotalActualCost_truck()
	{
		return totalActualCost_truck;
	}
	
	public void setTotalActualCost_truck( Double totalActualCost_truck )
	{
		this.totalActualCost_truck = totalActualCost_truck;
	}
	
	public Double getTotalActualCost_car()
	{
		return totalActualCost_car;
	}
	
	public void setTotalActualCost_car( Double totalActualCost_car )
	{
		this.totalActualCost_car = totalActualCost_car;
	}
	
	public Double getTotlaNewProCost_car()
	{
		return totlaNewProCost_car;
	}
	
	public void setTotlaNewProCost_car( Double totlaNewProCost_car )
	{
		this.totlaNewProCost_car = totlaNewProCost_car;
	}
	
	public Double getTotalNewProCost_truck()
	{
		return totalNewProCost_truck;
	}
	
	public void setTotalNewProCost_truck( Double totalNewProCost_truck )
	{
		this.totalNewProCost_truck = totalNewProCost_truck;
	}
	
	public Double getTotalNewProCost_car()
	{
		return totalNewProCost_car;
	}
	
	public void setTotalNewProCost_car( Double totalNewProCost_car )
	{
		this.totalNewProCost_car = totalNewProCost_car;
	}
	
	public Double getTotalCurProCost_truk()
	{
		return totalCurProCost_truk;
	}
	
	public void setTotalCurProCost_truk( Double totalCurProCost_truk )
	{
		this.totalCurProCost_truk = totalCurProCost_truk;
	}
	
	public Double getTotalCurProCost_car()
	{
		return totalCurProCost_car;
	}
	
	public void setTotalCurProCost_car( Double totalCurProCost_car )
	{
		this.totalCurProCost_car = totalCurProCost_car;
	}
	
	public Double getTotalAvgInPrice_truck()
	{
		return totalAvgInPrice_truck;
	}
	
	public void setTotalAvgInPrice_truck( Double totalAvgInPrice_truck )
	{
		this.totalAvgInPrice_truck = totalAvgInPrice_truck;
	}
	
	public Double getTotalAvgInPrice_car()
	{
		return totalAvgInPrice_car;
	}
	
	public void setTotalAvgInPrice_car( Double totalAvgInPrice_car )
	{
		this.totalAvgInPrice_car = totalAvgInPrice_car;
	}
	
	public Double getTotalVenPro_truck()
	{
		return totalVenPro_truck;
	}
	
	public void setTotalVenPro_truck( Double totalVenPro_truck )
	{
		this.totalVenPro_truck = totalVenPro_truck;
	}
	
	public Double getTotalCurVenPro_truck()
	{
		return totalCurVenPro_truck;
	}
	
	public void setTotalCurVenPro_truck( Double totalCurVenPro_truck )
	{
		this.totalCurVenPro_truck = totalCurVenPro_truck;
	}
	
	public Double getTotalUnPro_truck()
	{
		return totalUnPro_truck;
	}
	
	public void setTotalUnPro_truck( Double totalUnPro_truck )
	{
		this.totalUnPro_truck = totalUnPro_truck;
	}
	/**
     * 
     */
	public RouteTruckSummary()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
