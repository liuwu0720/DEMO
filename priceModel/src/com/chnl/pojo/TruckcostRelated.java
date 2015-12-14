package com.chnl.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * TruckcostRelated entity. @author MyEclipse Persistence Tools
 */

public class TruckcostRelated implements java.io.Serializable
{
	
	// Fields
	
	private Integer typeid;
	private String vcvechcle;
	private Double truckCostYear;
	private Double oilcostPerK;
	private Double dcds;
	private Double goodsCost;
	private Set truckInfos = new HashSet( 0 );
	
	// Constructors
	
	/** default constructor */
	public TruckcostRelated()
	{}
	
	/** minimal constructor */
	public TruckcostRelated( Integer typeid )
	{
		this.typeid = typeid;
	}
	
	/** full constructor */
	public TruckcostRelated( Integer typeid , String vcvechcle ,
	        Double truckCostYear , Double oilcostPerK , Double dcds ,
	        Double goodsCost , Set truckInfos )
	{
		this.typeid = typeid;
		this.vcvechcle = vcvechcle;
		this.truckCostYear = truckCostYear;
		this.oilcostPerK = oilcostPerK;
		this.dcds = dcds;
		this.goodsCost = goodsCost;
		this.truckInfos = truckInfos;
	}
	
	// Property accessors
	
	public Integer getTypeid()
	{
		return this.typeid;
	}
	
	public void setTypeid( Integer typeid )
	{
		this.typeid = typeid;
	}
	
	public String getVcvechcle()
	{
		return this.vcvechcle;
	}
	
	public void setVcvechcle( String vcvechcle )
	{
		this.vcvechcle = vcvechcle;
	}
	
	public Double getTruckCostYear()
	{
		return this.truckCostYear;
	}
	
	public void setTruckCostYear( Double truckCostYear )
	{
		this.truckCostYear = truckCostYear;
	}
	
	public Double getOilcostPerK()
	{
		return this.oilcostPerK;
	}
	
	public void setOilcostPerK( Double oilcostPerK )
	{
		this.oilcostPerK = oilcostPerK;
	}
	
	public Double getDcds()
	{
		return this.dcds;
	}
	
	public void setDcds( Double dcds )
	{
		this.dcds = dcds;
	}
	
	public Double getGoodsCost()
	{
		return this.goodsCost;
	}
	
	public void setGoodsCost( Double goodsCost )
	{
		this.goodsCost = goodsCost;
	}
	
	public Set getTruckInfos()
	{
		return this.truckInfos;
	}
	
	public void setTruckInfos( Set truckInfos )
	{
		this.truckInfos = truckInfos;
	}
	
}