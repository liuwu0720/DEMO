package com.chnl.pojo;

import java.sql.Timestamp;

/**
 * PrAlllegcarinfo entity. @author liuwu 保存用户修改后车辆信息
 */

public class PrAlllegcarinfo implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String startcity;
	private String endcity;
	private Double carid;
	private Timestamp updatetime;
	private Double ratio = 0.0; // 发车比例
	private Double incomeprice = 0.0;// 应收单价
	private Double outprocost = 0.0;// 应付单价
	private String carname;
	private Double weight;
	private Double length;
	private Integer legId;
	private Integer typeId;// 0:文件导入；1：用户输入
	// Constructors
	private String username;
	/** default constructor */
	public PrAlllegcarinfo()
	{}
	
	
	/**
	 * @param id
	 * @param updatetime
	 */
	public PrAlllegcarinfo( Integer id , Timestamp updatetime )
	{
		super();
		this.id = id;
		this.updatetime = updatetime;
	}

	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	public String getStartcity()
	{
		return this.startcity;
	}
	
	public void setStartcity( String startcity )
	{
		this.startcity = startcity;
	}
	
	public String getEndcity()
	{
		return this.endcity;
	}
	
	public Integer getLegId()
	{
		return legId;
	}
	
	public void setLegId( Integer legId )
	{
		this.legId = legId;
	}

	public void setEndcity( String endcity )
	{
		this.endcity = endcity;
	}
	
	public Double getCarid()
	{
		return this.carid;
	}
	
	public void setCarid( Double carid )
	{
		this.carid = carid;
	}
	
	public Timestamp getUpdatetime()
	{
		return this.updatetime;
	}
	
	public void setUpdatetime( Timestamp updatetime )
	{
		this.updatetime = updatetime;
	}
	
	public Double getRatio()
	{
		return this.ratio;
	}
	
	public void setRatio( Double ratio )
	{
		this.ratio = ratio;
	}
	
	public Double getIncomeprice()
	{
		return this.incomeprice;
	}
	
	public void setIncomeprice( Double incomeprice )
	{
		this.incomeprice = incomeprice;
	}
	
	public Double getOutprocost()
	{
		return this.outprocost;
	}
	
	public void setOutprocost( Double outprocost )
	{
		this.outprocost = outprocost;
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
	
	/**
	 * @param id
	 * @param startcity
	 * @param endcity
	 * @param carid
	 * @param updatetime
	 * @param ratio
	 * @param incomeprice
	 * @param outprocost
	 * @param carname
	 * @param weight
	 * @param length
	 */
	public PrAlllegcarinfo( Integer id , String startcity , String endcity ,
	        Double carid , Timestamp updatetime , Double ratio ,
	        Double incomeprice , Double outprocost , String carname ,
	        Double weight , Double length , Integer legId , Integer typeId ,
	        String username )
	{

		this.id = id;
		this.startcity = startcity;
		this.endcity = endcity;
		this.carid = carid;
		this.updatetime = updatetime;
		this.ratio = ratio;
		this.incomeprice = incomeprice;
		this.outprocost = outprocost;
		this.carname = carname;
		this.weight = weight;
		this.length = length;
		this.legId = legId;
		this.typeId = typeId;
		this.username = username;
	}
	
	public PrAlllegcarinfo( Integer id ,String startcity , String endcity , Double carid ,
	        Double ratio , Double incomeprice , Double outprocost ,
 String carname , Double weight , Double length ,
	        Integer legId , Integer typeId , String username )
	{
		this.id = id;
		this.startcity = startcity;
		this.endcity = endcity;
		this.carid = carid;
		this.ratio = ratio;
		this.incomeprice = incomeprice;
		this.outprocost = outprocost;
		this.carname = carname;
		this.weight = weight;
		this.length = length;
		this.legId = legId;
		this.typeId = typeId;
		this.username = username;
	}


	public Integer getTypeId()
    {
    	return typeId;
    }


	public void setTypeId( Integer typeId )
    {
    	this.typeId = typeId;
    }
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername( String username )
	{
		this.username = username;
	}
	
}