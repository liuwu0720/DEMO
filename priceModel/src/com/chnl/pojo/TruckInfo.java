package com.chnl.pojo;


/**
 * TruckInfo entity. @author MyEclipse Persistence Tools
 */

public class TruckInfo implements java.io.Serializable
{
	
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	// Fields
	
	private Integer id;
	private String trucktype;
	/**
	 * 与数据库不关联
	 */
	private Double length1;
	private Double length2;
	private Double length3;
	private Double loadingWeight;
	
	/**
	 * 与数据库关联
	 */
	private Integer dcaxle; // 轴数
	private Integer toplayer; // 上层排数
	private Integer lowlayer; // 下层排数
	private Double dcwidth; // 每排宽度
	private Double dcweightSelf;// 自重
	private Double dchighLength;// 上层长度
	private Double dclowLength;// 下层长度
	private Double dclowHigh;// 下层高度
	private Double loadWeight;// 额定载重
	private TruckcostRelated truckCostRelated;
	private TruckcostNorelated truckCostNoRelated;
	private Integer typeId;// 2:单车 3:航母
	// Constructors
	
	/** default constructor */
	public TruckInfo()
	{}
	
	/** minimal constructor */
	public TruckInfo( Integer id )
	{
		this.id = id;
	}
	
	/**
	 * @param id
	 * @param trucktype
	 * @param length1
	 * @param length2
	 * @param length3
	 * @param loadingWeight
	 * @param dcaxle
	 * @param toplayer
	 * @param lowlayer
	 * @param dcwidth
	 * @param dcweightSelf
	 * @param dchighLength
	 * @param dclowLength
	 * @param dclowHigh
	 * @param truckCostRelated
	 * @param truckCostNoRelated
	 */
	public TruckInfo( Integer id , String trucktype , Double length1 ,
	        Double length2 , Double length3 , Double loadingWeight ,
	        Integer dcaxle , Integer toplayer , Integer lowlayer ,
	        Double dcwidth , Double dcweightSelf , Double dchighLength ,
	        Double dclowLength , Double dclowHigh ,
	        TruckcostRelated truckCostRelated ,
	        TruckcostNorelated truckCostNoRelated , Integer typeId ,
	        Double loadWeight )
	{
		super();
		this.id = id;
		this.trucktype = trucktype;
		this.length1 = length1;
		this.length2 = length2;
		this.length3 = length3;
		this.loadingWeight = loadingWeight;
		this.dcaxle = dcaxle;
		this.toplayer = toplayer;
		this.lowlayer = lowlayer;
		this.dcwidth = dcwidth;
		this.dcweightSelf = dcweightSelf;
		this.dchighLength = dchighLength;
		this.dclowLength = dclowLength;
		this.dclowHigh = dclowHigh;
		this.truckCostRelated = truckCostRelated;
		this.truckCostNoRelated = truckCostNoRelated;
		this.typeId = typeId;
		this.loadWeight = loadWeight;
	}

	public TruckInfo( Integer id , String trucktype , Integer dcaxle ,
	        Integer toplayer , Integer lowlayer , Double dcwidth ,
	        Double dcweightSelf , Double dchighLength , Double dclowLength ,
	        Double dclowHigh , TruckcostRelated truckCostRelated ,
	        TruckcostNorelated truckCostNoRelated , Integer typeId ,
	        Double loadWeight )
	{
		super();
		this.id = id;
		this.trucktype = trucktype;
		this.dcaxle = dcaxle;
		this.toplayer = toplayer;
		this.lowlayer = lowlayer;
		this.dcwidth = dcwidth;
		this.dcweightSelf = dcweightSelf;
		this.dchighLength = dchighLength;
		this.dclowLength = dclowLength;
		this.dclowHigh = dclowHigh;
		this.truckCostRelated = truckCostRelated;
		this.truckCostNoRelated = truckCostNoRelated;
		this.typeId = typeId;
		this.loadWeight = loadWeight;
	}
	
	public Integer getTypeId()
	{
		return typeId;
	}
	
	public void setTypeId( Integer typeId )
	{
		this.typeId = typeId;
	}

	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	public String getTrucktype()
	{
		return this.trucktype;
	}
	
	public void setTrucktype( String trucktype )
	{
		this.trucktype = trucktype;
	}
	
	public Double getLength1()
	{
		if ( this.length1 != null )
		{
			return this.length1;
		}
		else
		{
			double length1 = this.getDchighLength();// 第一排长度
			return length1;
		}

	}
	
	public void setLength1( Double length1 )
	{
		this.length1 = length1;
	}
	
	public Double getLength2()
	{
		if ( this.length2 != null )
		{
			return this.length2;
		}
		else
		{
			double length2 = 0;
			if ( this.getTypeId() == 3 )
			{
				length2 = this.getDchighLength();
			}
			else if ( this.getTypeId() == 2 )
			{
				length2 = this.getDclowLength();
			}
			return length2;
		}

	}
	
	public void setLength2( Double length2 )
	{
		this.length2 = length2;
	}
	
	public Double getLength3()
	{
		if ( this.length3 != null )
		{
			return this.length3;
		}
		else
		{
			double length3 = 0;
			if ( this.getTypeId() == 3 )
			{
				length3 = this.getDclowLength();
			}
			else if ( this.getTypeId() == 2 )
			{
				length3 = 0;
			}
			return length3;
		}

	}
	
	public void setLength3( Double length3 )
	{
		this.length3 = length3;
	}
	
	public Double getLoadingWeight()
	{
		double maxWeigth = this.getLoadWeight() - this.getDcweightSelf();
		return maxWeigth;
	}
	
	public void setLoadingWeight( Double loadingWeight )
	{
		this.loadingWeight = loadingWeight;
	}
	
	public Integer getDcaxle()
	{
		return dcaxle;
	}
	
	public void setDcaxle( Integer dcaxle )
	{
		this.dcaxle = dcaxle;
	}
	
	public Integer getToplayer()
	{
		return toplayer;
	}
	
	public void setToplayer( Integer toplayer )
	{
		this.toplayer = toplayer;
	}
	
	public Integer getLowlayer()
	{
		return lowlayer;
	}
	
	public void setLowlayer( Integer lowlayer )
	{
		this.lowlayer = lowlayer;
	}
	
	public Double getDcwidth()
	{
		return dcwidth;
	}
	
	public void setDcwidth( Double dcwidth )
	{
		this.dcwidth = dcwidth;
	}
	
	public Double getDcweightSelf()
	{
		return dcweightSelf;
	}
	
	public void setDcweightSelf( Double dcweightSelf )
	{
		this.dcweightSelf = dcweightSelf;
	}
	
	public Double getDchighLength()
	{
		return dchighLength;
	}
	
	public void setDchighLength( Double dchighLength )
	{
		this.dchighLength = dchighLength;
	}
	
	public Double getDclowLength()
	{
		return dclowLength;
	}
	
	public void setDclowLength( Double dclowLength )
	{
		this.dclowLength = dclowLength;
	}
	
	public Double getDclowHigh()
	{
		return dclowHigh;
	}
	
	public void setDclowHigh( Double dclowHigh )
	{
		this.dclowHigh = dclowHigh;
	}
	
	public TruckcostRelated getTruckCostRelated()
	{
		return truckCostRelated;
	}
	
	public void setTruckCostRelated( TruckcostRelated truckCostRelated )
	{
		this.truckCostRelated = truckCostRelated;
	}
	
	public TruckcostNorelated getTruckCostNoRelated()
	{
		return truckCostNoRelated;
	}
	
	public void setTruckCostNoRelated( TruckcostNorelated truckCostNoRelated )
	{
		this.truckCostNoRelated = truckCostNoRelated;
	}
	
	public Double getLoadWeight()
	{
		return loadWeight;
	}
	
	public void setLoadWeight( Double loadWeight )
	{
		this.loadWeight = loadWeight;
	}
	

}