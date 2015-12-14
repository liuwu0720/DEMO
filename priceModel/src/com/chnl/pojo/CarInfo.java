package com.chnl.pojo;


/**
 * CarInfo entity. @author MyEclipse Persistence Tools
 */

public class CarInfo implements java.io.Serializable
{
	
	// Fields
	
	private Double id;
	private String carname;
	private Double weight;
	private Double length;
	private Double hight;
	private Double bactive;// 有效性
	private Double width;// 宽

	private String manufacturer;// 厂商

	private Double incomePrice = 0.0; //收入单价，不和数据库关联
	private Double ratio = 0.0;//出现比率，不和数据库关联
	private Double currentProCost = 0.0;// 供应商支出应付单价 不与数据库关联
	// Constructors
	private Double fleetPrice = 0.0;
	
	
	/**
     * 
     */
	public CarInfo()
	{
		// TODO Auto-generated constructor stub
	}

	public Double getId()
	{
		return id;
	}
	
	public void setId( Double id )
	{
		this.id = id;
	}
	
	public String getCarname()
	{
		return carname;
	}
	
	public void setCarname( String carname )
	{
		this.carname = carname;
	}
	
	public Double getWeight()
	{
		return weight;
	}
	
	public void setWeight( Double weight )
	{
		this.weight = weight;
	}
	
	public Double getLength()
	{
		return length;
	}
	
	public void setLength( Double length )
	{
		this.length = length;
	}
	
	public Double getHight()
	{
		return hight;
	}
	
	public void setHight( Double hight )
	{
		this.hight = hight;
	}
	
	public Double getBactive()
	{
		return bactive;
	}
	
	public void setBactive( Double bactive )
	{
		this.bactive = bactive;
	}
	
	public Double getWidth()
	{
		return width;
	}
	
	public void setWidth( Double width )
	{
		this.width = width;
	}
	
	public String getManufacturer()
	{
		return manufacturer;
	}
	
	public void setManufacturer( String manufacturer )
	{
		this.manufacturer = manufacturer;
	}
	
	public Double getIncomePrice()
	{
		return incomePrice;
	}
	
	public void setIncomePrice( Double incomePrice )
	{
		this.incomePrice = incomePrice;
	}
	
	public Double getRatio()
	{
		return ratio;
	}
	
	public void setRatio( Double ratio )
	{
		this.ratio = ratio;
	}
	
	public Double getCurrentProCost()
	{
		return currentProCost;
	}
	
	public void setCurrentProCost( Double currentProCost )
	{
		this.currentProCost = currentProCost;
	}
	
	public Double getFleetPrice()
	{
		return fleetPrice;
	}
	
	public void setFleetPrice( Double fleetPrice )
	{
		this.fleetPrice = fleetPrice;
	}
	
	/**
	 * @param id
	 * @param carname
	 * @param weight
	 * @param length
	 * @param hight
	 * @param bactive
	 * @param width
	 * @param manufacturer
	 * @param incomePrice
	 * @param ratio
	 * @param currentProCost
	 * @param fleetPrice
	 */
	public CarInfo( Double id , String carname , Double weight , Double length ,
	        Double hight , Double bactive , Double width , String manufacturer ,
	        Double incomePrice , Double ratio , Double currentProCost ,
	        Double fleetPrice )
	{
		super();
		this.id = id;
		this.carname = carname;
		this.weight = weight;
		this.length = length;
		this.hight = hight;
		this.bactive = bactive;
		this.width = width;
		this.manufacturer = manufacturer;
		this.incomePrice = incomePrice;
		this.ratio = ratio;
		this.currentProCost = currentProCost;
		this.fleetPrice = fleetPrice;
	}
	
}