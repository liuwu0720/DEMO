package com.chnl.pojo;


/**
 * PrLegfileimport entity. @author MyEclipse Persistence Tools
 */

public class PrLegfileimport implements Comparable< PrLegfileimport > ,
        java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = 4717460495421820605L;
	private Integer id;
	private String startcity;
	private String startpoint;// 始发地城市提车点
	private String endcity;
	private Double loopflag;// 环线标识
	private Integer emptlyFlag = 0;// 状态:0:默认值有效；1：空载；2：无效;3待定
	private Double emptlyDistance = 0.0;
	private Double incomeDistance = 0.0;
	private Double actualCostDistance = 0.0;// 实际支付公里(核销管理查询得到)
	private String username;
	
	private String message;// 提示信息
	private Integer customerId;
	private String selectCustomer;
	private Double aroundDistance = 0.0;
	
	private String btnMessage;// 选择按钮上的提示信息
	
	private Double costDistance = 0.0;// 应付公里数
	// Constructors
	
	/** default constructor */
	public PrLegfileimport()
	{}
	
	/** minimal constructor */
	public PrLegfileimport( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public PrLegfileimport( Integer id , String startcity , String startpoint ,
	        String endcity , Double loopflag , Integer emptlyFlag ,
	        Double emptlyDistance , String message , Double incomeDistance ,
	        String username ,
 String selectCustomer ,
	        Double actualCostDistance , Double aroundDistance ,
	        Integer customerId , String btnMessage , Double costDistance )
	{
		this.id = id;
		this.startcity = startcity;
		this.startpoint = startpoint;
		this.endcity = endcity;
		this.loopflag = loopflag;
		this.emptlyFlag = emptlyFlag;
		this.emptlyDistance = emptlyDistance;
		this.message = message;
		this.incomeDistance = incomeDistance;
		this.username = username;
		this.selectCustomer = selectCustomer;
		this.actualCostDistance = actualCostDistance;
		this.aroundDistance = aroundDistance;
		this.customerId = customerId;
		this.btnMessage = btnMessage;
		this.costDistance = costDistance;
	}
	
	public PrLegfileimport( Integer id , String startcity , String startpoint ,
	        String endcity , Double loopflag , Integer emptlyFlag ,
	        Double incomeDistance , String username , String selectCustomer ,
	        Double actualCostDistance , Double aroundDistance ,
	        Integer customerId , String btnMessage , Double costDistance )
	{
		this.id = id;
		this.startcity = startcity;
		this.startpoint = startpoint;
		this.endcity = endcity;
		this.loopflag = loopflag;
		this.emptlyFlag = emptlyFlag;
		this.incomeDistance = incomeDistance;
		this.username = username;
		this.selectCustomer = selectCustomer;
		this.actualCostDistance = actualCostDistance;
		this.aroundDistance = aroundDistance;
		this.customerId = customerId;
		this.btnMessage = btnMessage;
		this.costDistance = costDistance;
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
	
	public Double getLoopflag()
	{
		return loopflag;
	}
	
	public void setLoopflag( Double loopflag )
	{
		this.loopflag = loopflag;
	}
	

	public Integer getEmptlyFlag()
	{
		return emptlyFlag;
	}

	public void setEmptlyFlag( Integer emptlyFlag )
	{
		this.emptlyFlag = emptlyFlag;
	}

	public Double getEmptlyDistance()
	{
		return emptlyDistance;
	}
	
	public void setEmptlyDistance( Double emptlyDistance )
	{
		this.emptlyDistance = emptlyDistance;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage( String message )
	{
		this.message = message;
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

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param o
	 * @return
	 * @author liuwu
	 * @create_date 2014-11-18 上午9:44:19
	 */
	public int compareTo( PrLegfileimport o )
    {
    	if ( null == o )
		{
			return 1;
		}
		else
		{
			return this.getId().compareTo( o.getId() );
		}
    }

	
	public String getSelectCustomer()
	{
		return selectCustomer;
	}
	
	public void setSelectCustomer( String selectCustomer )
	{
		this.selectCustomer = selectCustomer;
	}
	
	public Double getActualCostDistance()
	{
		return actualCostDistance;
	}
	
	public void setActualCostDistance( Double actualCostDistance )
	{
		this.actualCostDistance = actualCostDistance;
	}
	
	public Double getAroundDistance()
	{
		return aroundDistance;
	}
	
	public void setAroundDistance( Double aroundDistance )
	{
		this.aroundDistance = aroundDistance;
	}
	
	public Integer getCustomerId()
	{
		return customerId;
	}
	
	public void setCustomerId( Integer customerId )
	{
		this.customerId = customerId;
	}
	
	public String getBtnMessage()
	{
		return btnMessage;
	}
	
	public void setBtnMessage( String btnMessage )
	{
		this.btnMessage = btnMessage;
	}
	
	public Double getCostDistance()
	{
		return costDistance;
	}
	
	public void setCostDistance( Double costDistance )
	{
		this.costDistance = costDistance;
	}
	
	

}