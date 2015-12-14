package com.chnl.pojo;

import java.util.List;

/**
 * LegCarInfo entity. @author MyEclipse Persistence Tools
 */

public class LegCarInfo implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = - 6790552654546504153L;
	private Integer id;
	private LegInfo legInfo;
	private CarInfo carInfo;
	private Double incomeprice;
	private Double ratio;
	private Double vendorcost; //采购支出
	private List< CarInfo > carInfos;

	// Constructors
	
	/** default constructor */
	public LegCarInfo()
	{}
	
	/** minimal constructor */
	public LegCarInfo( Integer id , LegInfo legInfo , CarInfo carInfo )
	{
		this.id = id;
		this.legInfo = legInfo;
		this.carInfo = carInfo;
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
	
	public LegInfo getLegInfo()
	{
		return this.legInfo;
	}
	
	public void setLegInfo( LegInfo legInfo )
	{
		this.legInfo = legInfo;
	}
	
	public CarInfo getCarInfo()
	{
		return this.carInfo;
	}
	
	public void setCarInfo( CarInfo carInfo )
	{
		this.carInfo = carInfo;
	}
	
	public Double getIncomeprice()
	{
		return this.incomeprice;
	}
	
	public void setIncomeprice( Double incomeprice )
	{
		this.incomeprice = incomeprice;
	}
	
	public Double getRatio()
	{
		return this.ratio;
	}
	
	public void setRatio( Double ratio )
	{
		this.ratio = ratio;
	}
	
	public Double getVendorcost()
	{
		return this.vendorcost;
	}
	
	public void setVendorcost( Double vendorcost )
	{
		this.vendorcost = vendorcost;
	}

	public List< CarInfo > getCarInfos()
	{
		return carInfos;
	}
	
	public void setCarInfos( List< CarInfo > carInfos )
	{
		this.carInfos = carInfos;
	}

	/**
	 * @param id
	 * @param legInfo
	 * @param carInfo
	 * @param incomeprice
	 * @param ratio
	 * @param vendorcost
	 * @param carInfos
	 */
	public LegCarInfo( Integer id , LegInfo legInfo , CarInfo carInfo ,
	        Double incomeprice , Double ratio , Double vendorcost ,
	        List< CarInfo > carInfos )
	{
		super();
		this.id = id;
		this.legInfo = legInfo;
		this.carInfo = carInfo;
		this.incomeprice = incomeprice;
		this.ratio = ratio;
		this.vendorcost = vendorcost;
		this.carInfos = carInfos;
	}

}