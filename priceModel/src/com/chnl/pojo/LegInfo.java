package com.chnl.pojo;


/**
 * LegInfo entity. @author MyEclipse Persistence Tools
 */

public class LegInfo implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = 5361283351229695102L;
	private Integer id;
	private String origin; // 始发地城市
	private String startPoint;// 提车点
	private String destination; // 目的地城市
	private Double incomeDistance;//收入里程
	private Double actualDistance;// 支付公里数，（线路模型得到）核销公里
	private Double emptyDistance;//空载里程
	private Double days;
	private Double costByTruck;// 空载成本，用户自己输入
	private Double loopFlag;// 环线标识 标识数相同则视为环线
	private Integer emptlyFlag; // 空载标识 状态:0:默认值有效；1：空载；2：无效;3待定
	private Double actualCostTruckKm = 0.0; // 实际板车支出：与线路所选择的拖车相关
	private Double actualCostTruck = 0.0;
	private String vcCustomer;
	private Double costDistance;// 应付公里
	private Double aroundDistance;// 绕城公里
	private Double avgCarsPerTruckCombo;// 平均装载量
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	public String getOrigin()
	{
		return origin;
	}
	
	public void setOrigin( String origin )
	{
		this.origin = origin;
	}
	
	public String getStartPoint()
	{
		return startPoint;
	}
	
	public void setStartPoint( String startPoint )
	{
		this.startPoint = startPoint;
	}
	
	public String getDestination()
	{
		return destination;
	}
	
	public void setDestination( String destination )
	{
		this.destination = destination;
	}
	
	public Double getIncomeDistance()
	{
		return incomeDistance;
	}
	
	public void setIncomeDistance( Double incomeDistance )
	{
		this.incomeDistance = incomeDistance;
	}
	
	public Double getActualDistance()
	{
		return actualDistance;
	}
	
	public void setActualDistance( Double actualDistance )
	{
		this.actualDistance = actualDistance;
	}
	
	public Double getEmptyDistance()
	{
		return emptyDistance;
	}
	
	public void setEmptyDistance( Double emptyDistance )
	{
		this.emptyDistance = emptyDistance;
	}
	
	public Double getDays()
	{
		return days;
	}
	
	public void setDays( Double days )
	{
		this.days = days;
	}
	
	public Double getCostByTruck()
	{
		return costByTruck;
	}
	
	public void setCostByTruck( Double costByTruck )
	{
		this.costByTruck = costByTruck;
	}
	
	public Double getLoopFlag()
	{
		return loopFlag;
	}
	
	public void setLoopFlag( Double loopFlag )
	{
		this.loopFlag = loopFlag;
	}
	
	public Integer getEmptlyFlag()
	{
		return emptlyFlag;
	}
	
	public void setEmptlyFlag( Integer emptlyFlag )
	{
		this.emptlyFlag = emptlyFlag;
	}
	
	public Double getActualCostTruckKm()
	{
		return actualCostTruckKm;
	}
	
	public void setActualCostTruckKm( Double actualCostTruckKm )
	{
		this.actualCostTruckKm = actualCostTruckKm;
	}

	/**
	 */
	public LegInfo()
	{

	}
	
	public String getVcCustomer()
	{
		return vcCustomer;
	}
	
	public void setVcCustomer( String vcCustomer )
	{
		this.vcCustomer = vcCustomer;
	}
	
	
	
	public Double getCostDistance()
	{
		return costDistance;
	}
	
	public void setCostDistance( Double costDistance )
	{
		this.costDistance = costDistance;
	}

	public Double getAroundDistance()
	{
		return aroundDistance;
	}
	
	public void setAroundDistance( Double aroundDistance )
	{
		this.aroundDistance = aroundDistance;
	}
	
	public Double getActualCostTruck()
	{
		return actualCostTruck;
	}
	
	public void setActualCostTruck( Double actualCostTruck )
	{
		this.actualCostTruck = actualCostTruck;
	}
	
	public Double getAvgCarsPerTruckCombo()
	{
		return avgCarsPerTruckCombo;
	}
	
	public void setAvgCarsPerTruckCombo( Double avgCarsPerTruckCombo )
	{
		this.avgCarsPerTruckCombo = avgCarsPerTruckCombo;
	}
	
	
	
	
}