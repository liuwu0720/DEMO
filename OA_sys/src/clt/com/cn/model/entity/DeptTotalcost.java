package clt.com.cn.model.entity;

import java.util.Date;

/**
 * DeptTotalcost entity. @author MyEclipse Persistence Tools
 */

public class DeptTotalcost implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 5982307627720410346L;
	private Integer lineid;
	private Integer iDept;
	private String vcDept;
	private Float nLastcost;
	private Float nLastcost2;
	private Float nTotalcost;
	private Integer nEnable;
	private Integer nMonth;
	private Date dtAdd;
	private Integer iCompany;
	private String vcCompany;
	private Integer iCosttype;
	private String vcCosttype;
	
	// Constructors
	
	public Integer getiCompany()
	{
		return iCompany;
	}
	
	public void setiCompany( Integer iCompany )
	{
		this.iCompany = iCompany;
	}
	
	public String getVcCompany()
	{
		return vcCompany;
	}
	
	public void setVcCompany( String vcCompany )
	{
		this.vcCompany = vcCompany;
	}
	
	/** default constructor */
	public DeptTotalcost()
	{}
	
	/**
	 * @return the lineid
	 */
	public Integer getLineid()
	{
		return lineid;
	}
	
	/**
	 * @param lineid the lineid to set
	 */
	public void setLineid( Integer lineid )
	{
		this.lineid = lineid;
	}
	
	/**
	 * @return the iDept
	 */
	public Integer getiDept()
	{
		return iDept;
	}
	
	/**
	 * @param iDept the iDept to set
	 */
	public void setiDept( Integer iDept )
	{
		this.iDept = iDept;
	}
	
	/**
	 * @return the vcDept
	 */
	public String getVcDept()
	{
		return vcDept;
	}
	
	/**
	 * @param vcDept the vcDept to set
	 */
	public void setVcDept( String vcDept )
	{
		this.vcDept = vcDept;
	}
	
	/**
	 * @return the nLastcost
	 */
	public Float getnLastcost()
	{
		return nLastcost;
	}
	
	/**
	 * @param nLastcost the nLastcost to set
	 */
	public void setnLastcost( Float nLastcost )
	{
		this.nLastcost = nLastcost;
	}
	
	/**
	 * @return the nLastcost2
	 */
	public Float getnLastcost2()
	{
		return nLastcost2;
	}
	
	/**
	 * @param nLastcost2 the nLastcost2 to set
	 */
	public void setnLastcost2( Float nLastcost2 )
	{
		this.nLastcost2 = nLastcost2;
	}
	
	/**
	 * @return the nTotalcost
	 */
	public Float getnTotalcost()
	{
		return nTotalcost;
	}
	
	/**
	 * @param nTotalcost the nTotalcost to set
	 */
	public void setnTotalcost( Float nTotalcost )
	{
		this.nTotalcost = nTotalcost;
	}
	
	/**
	 * @return the nEnable
	 */
	public Integer getnEnable()
	{
		return nEnable;
	}
	
	/**
	 * @param nEnable the nEnable to set
	 */
	public void setnEnable( Integer nEnable )
	{
		this.nEnable = nEnable;
	}
	
	/**
	 * @return the nMonth
	 */
	public Integer getnMonth()
	{
		return nMonth;
	}
	
	/**
	 * @param nMonth the nMonth to set
	 */
	public void setnMonth( Integer nMonth )
	{
		this.nMonth = nMonth;
	}
	
	/**
	 * @return the dtAdd
	 */
	public Date getDtAdd()
	{
		return dtAdd;
	}
	
	/**
	 * @param dtAdd the dtAdd to set
	 */
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
	}

	/**
	 * @return the iCosttype
	 */
	public Integer getiCosttype()
	{
		return iCosttype;
	}

	/**
	 * @param iCosttype the iCosttype to set
	 */
	public void setiCosttype( Integer iCosttype )
	{
		this.iCosttype = iCosttype;
	}

	/**
	 * @return the vcCosttype
	 */
	public String getVcCosttype()
	{
		return vcCosttype;
	}

	/**
	 * @param vcCosttype the vcCosttype to set
	 */
	public void setVcCosttype( String vcCosttype )
	{
		this.vcCosttype = vcCosttype;
	}
	
}