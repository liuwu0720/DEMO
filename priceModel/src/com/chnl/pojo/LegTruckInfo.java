package com.chnl.pojo;

/**
 * LegTruckInfo entity. @author MyEclipse Persistence Tools
 */

public class LegTruckInfo implements java.io.Serializable
{
	
	// Fields
	
	private Integer id;
	private TruckInfo truckInfo;
	private LegInfo legInfo;
	private Double fullcost;
	private Double emptcost;
	private Double actualCostByTruck = 0.0;// 不与数据库关联
	private Double actualCostByCar = 0.0;// 不与数据库关联
	
	// Constructors
	
	public Double getActualCostByTruck()
    {
    	return actualCostByTruck;
    }

	public void setActualCostByTruck( Double actualCostByTruck )
    {
    	this.actualCostByTruck = actualCostByTruck;
    }

	public Double getActualCostByCar()
    {
    	return actualCostByCar;
    }

	public void setActualCostByCar( Double actualCostByCar )
    {
    	this.actualCostByCar = actualCostByCar;
    }

	/** default constructor */
	public LegTruckInfo()
	{}
	
	/** minimal constructor */
	public LegTruckInfo( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public LegTruckInfo( Integer id , TruckInfo truckInfo , LegInfo legInfo ,
	        Double fullcost , Double emptcost )
	{
		this.id = id;
		this.truckInfo = truckInfo;
		this.legInfo = legInfo;
		this.fullcost = fullcost;
		this.emptcost = emptcost;
	}
	
	// Property accessors
	
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	public TruckInfo getTruckInfo()
	{
		return this.truckInfo;
	}
	
	public void setTruckInfo( TruckInfo truckInfo )
	{
		this.truckInfo = truckInfo;
	}
	
	public LegInfo getLegInfo()
	{
		return this.legInfo;
	}
	
	public void setLegInfo( LegInfo legInfo )
	{
		this.legInfo = legInfo;
	}
	
	public Double getFullcost()
	{
		return this.fullcost;
	}
	
	public void setFullcost( Double fullcost )
	{
		this.fullcost = fullcost;
	}
	
	public Double getEmptcost()
	{
		return this.emptcost;
	}
	
	public void setEmptcost( Double emptcost )
	{
		this.emptcost = emptcost;
	}
	
}