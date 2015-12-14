package com.chnl.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * PrSelfinputleg entity. @author MyEclipse Persistence Tools
 */

public class PrSelfinputleg implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = - 7038230469878853646L;
	private Integer id;
	private String startcity;
	private String startpoint;
	private String endcity;
	private Integer emptlyflag = 0;// 状态:0:默认值有效(即重载)；1：空载；2：无效;3待定
	private Double loopflag;
	private String message;
	private Double emptlyDistance = 0.0;
	private Double incomeDistance = 0.0;
	private String username;
	private List< Smcustomer > smcustomers = new ArrayList< Smcustomer >();
	private String selectCustomer;
	private Double costDistance = 0.0;// 应付公里数（结算）
	private Double aroundDistance = 0.0;// 绕路公里数
	private Double actualCostDistance = 0.0;// 实际支付公里(核销管理查询得到)
	// Constructors
	
	/** default constructor */
	public PrSelfinputleg()
	{}
	
	/** minimal constructor */
	public PrSelfinputleg( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public PrSelfinputleg( Integer id , String startcity , String startpoint ,
	        String endcity , Integer emptlyflag , Double loopflag ,
	        Double incomeDistance , Double emptlyDistance , String username ,
	        List< Smcustomer > smcustomers , String selectCustomer ,
	        Double costDistance , Double aroundDistance ,
	        Double actualCostDistance )
	{
		this.id = id;
		this.startcity = startcity;
		this.startpoint = startpoint;
		this.endcity = endcity;
		this.emptlyflag = emptlyflag;
		this.loopflag = loopflag;
		this.incomeDistance = incomeDistance;
		this.emptlyDistance = emptlyDistance;
		this.username = username;
		this.smcustomers = smcustomers;
		this.selectCustomer = selectCustomer;
		this.costDistance = costDistance;
		this.aroundDistance = aroundDistance;
		this.actualCostDistance = actualCostDistance;
	}
	
	public PrSelfinputleg( Integer id , String startcity , String startpoint ,
	        String endcity , Integer emptlyflag , Double loopflag ,
	        String message , Double incomeDistance , Double emptlyDistance ,
	        String username , List< Smcustomer > smcustomers ,
	        String selectCustomer , Double costDistance ,
	        Double aroundDistance , Double actualCostDistance )
	{
		this.id = id;
		this.startcity = startcity;
		this.startpoint = startpoint;
		this.endcity = endcity;
		this.emptlyflag = emptlyflag;
		this.loopflag = loopflag;
		this.message = message;
		this.incomeDistance = incomeDistance;
		this.emptlyDistance = emptlyDistance;
		this.username = username;
		this.smcustomers = smcustomers;
		this.selectCustomer = selectCustomer;
		this.costDistance = costDistance;
		this.aroundDistance = aroundDistance;
		this.actualCostDistance = actualCostDistance;
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
	
	public String getStartcity()
	{
		return this.startcity;
	}
	
	public void setStartcity( String startcity )
	{
		this.startcity = startcity;
	}
	
	public String getStartpoint()
	{
		return this.startpoint;
	}
	
	public void setStartpoint( String startpoint )
	{
		this.startpoint = startpoint;
	}
	
	public String getEndcity()
	{
		return this.endcity;
	}
	
	public void setEndcity( String endcity )
	{
		this.endcity = endcity;
	}
	
	public Integer getEmptlyflag()
	{
		return this.emptlyflag;
	}
	
	public void setEmptlyflag( Integer emptlyflag )
	{
		this.emptlyflag = emptlyflag;
	}
	
	public Double getLoopflag()
	{
		return this.loopflag;
	}
	
	public void setLoopflag( Double loopflag )
	{
		this.loopflag = loopflag;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage( String message )
	{
		this.message = message;
	}
	
	public Double getEmptlyDistance()
	{
		return emptlyDistance;
	}
	
	public void setEmptlyDistance( Double emptlyDistance )
	{
		this.emptlyDistance = emptlyDistance;
	}
	
	public Double getIncomeDistance()
	{
		return incomeDistance;
	}
	
	public void setIncomeDistance( Double incomeDistance )
	{
		this.incomeDistance = incomeDistance;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername( String username )
	{
		this.username = username;
	}
	
	public List< Smcustomer > getSmcustomers()
	{
		return smcustomers;
	}
	
	public void setSmcustomers( List< Smcustomer > smcustomers )
	{
		this.smcustomers = smcustomers;
	}
	
	public String getSelectCustomer()
	{
		return selectCustomer;
	}
	
	public void setSelectCustomer( String selectCustomer )
	{
		this.selectCustomer = selectCustomer;
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
	
	public Double getActualCostDistance()
	{
		return actualCostDistance;
	}
	
	public void setActualCostDistance( Double actualCostDistance )
	{
		this.actualCostDistance = actualCostDistance;
	}
	
	

}