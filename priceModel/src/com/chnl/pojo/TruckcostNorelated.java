package com.chnl.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * TruckcostNorelated entity. @author MyEclipse Persistence Tools
 */

public class TruckcostNorelated implements java.io.Serializable
{
	
	// Fields
	
	private Integer reid;
	private Double dcai;
	private Double dcmvalci;
	private Double dcac;
	private Double dcml;
	private Double dcmc;
	private Double dcdi;
	private Double valuerate;
	private Double years;
	private Double inrate;
	private Double oilprice;
	private Double km;
	private Set truckInfos = new HashSet( 0 );
	
	// Constructors
	
	/** default constructor */
	public TruckcostNorelated()
	{}
	
	/** minimal constructor */
	public TruckcostNorelated( Integer reid )
	{
		this.reid = reid;
	}
	
	/** full constructor */
	public TruckcostNorelated( Integer reid , Double dcai , Double dcmvalci ,
	        Double dcac , Double dcml , Double dcmc , Double dcdi ,
	        Double valuerate , Double years , Double inrate , Double oilprice ,
	        Double km , Set truckInfos )
	{
		this.reid = reid;
		this.dcai = dcai;
		this.dcmvalci = dcmvalci;
		this.dcac = dcac;
		this.dcml = dcml;
		this.dcmc = dcmc;
		this.dcdi = dcdi;
		this.valuerate = valuerate;
		this.years = years;
		this.inrate = inrate;
		this.oilprice = oilprice;
		this.km = km;
		this.truckInfos = truckInfos;
	}
	
	// Property accessors
	
	public Integer getReid()
	{
		return this.reid;
	}
	
	public void setReid( Integer reid )
	{
		this.reid = reid;
	}
	
	public Double getDcai()
	{
		return this.dcai;
	}
	
	public void setDcai( Double dcai )
	{
		this.dcai = dcai;
	}
	
	public Double getDcmvalci()
	{
		return this.dcmvalci;
	}
	
	public void setDcmvalci( Double dcmvalci )
	{
		this.dcmvalci = dcmvalci;
	}
	
	public Double getDcac()
	{
		return this.dcac;
	}
	
	public void setDcac( Double dcac )
	{
		this.dcac = dcac;
	}
	
	public Double getDcml()
	{
		return this.dcml;
	}
	
	public void setDcml( Double dcml )
	{
		this.dcml = dcml;
	}
	
	public Double getDcmc()
	{
		return this.dcmc;
	}
	
	public void setDcmc( Double dcmc )
	{
		this.dcmc = dcmc;
	}
	
	public Double getDcdi()
	{
		return this.dcdi;
	}
	
	public void setDcdi( Double dcdi )
	{
		this.dcdi = dcdi;
	}
	
	public Double getValuerate()
	{
		return this.valuerate;
	}
	
	public void setValuerate( Double valuerate )
	{
		this.valuerate = valuerate;
	}
	
	public Double getYears()
	{
		return this.years;
	}
	
	public void setYears( Double years )
	{
		this.years = years;
	}
	
	public Double getInrate()
	{
		return this.inrate;
	}
	
	public void setInrate( Double inrate )
	{
		this.inrate = inrate;
	}
	
	public Double getOilprice()
	{
		return this.oilprice;
	}
	
	public void setOilprice( Double oilprice )
	{
		this.oilprice = oilprice;
	}
	
	public Double getKm()
	{
		return this.km;
	}
	
	public void setKm( Double km )
	{
		this.km = km;
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