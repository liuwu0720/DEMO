package clt.com.cn.model.entity;

import java.util.Date;

/**
 * CostApply entity. @author MyEclipse Persistence Tools
 */

public class CostApply implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4385885697665381654L;
	private Integer id;
	private Integer iUser;
	private String vcName;
	private Date dtApply;
	private Integer iCosttype;
	private String vcCosttypename;
	private Float nCost;
	private String vcNote;
	private Integer nEnable;
	private Integer nState;
	private Integer iDept;
	private String vcDeptName;//报销部门名称
	private String vcCompany;//报销公司
	private String vcBank;//收款人银帐号
	private String vcAcceptMan;//收款人
	private Integer iDept2;//费用承担部门ID
	private String vcDeptName2;//费用承担部门名称
	private String vcCompany2;//费用承担公司
	private Integer iPayType;//结算方式ID
	private String vcPaytype;//结算方式
	private Integer iCompanyId2;//费用承担公司ID
	private String vcApplyNo;//报销单编号
	private String vcReason;//出差事由
	private String vcTaskId;// 不与数据库关联
	private String vcTaskName;// 不与数据库关联
	// Constructors
	
	/** default constructor */
	public CostApply()
	{}
	
	public Integer getId()
	{
		return id;
	}
	
	public Integer getiDept()
	{
		return iDept;
	}
	
	public void setiDept( Integer iDept )
	{
		this.iDept = iDept;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	public Integer getiUser()
	{
		return iUser;
	}
	
	public void setiUser( Integer iUser )
	{
		this.iUser = iUser;
	}
	
	public String getVcName()
	{
		return vcName;
	}
	
	public void setVcName( String vcName )
	{
		this.vcName = vcName;
	}
	
	public Date getDtApply()
	{
		return dtApply;
	}
	
	public void setDtApply( Date dtApply )
	{
		this.dtApply = dtApply;
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
	 * @return the vcCosttypename
	 */
	public String getVcCosttypename()
	{
		return vcCosttypename;
	}
	
	/**
	 * @param vcCosttypename the vcCosttypename to set
	 */
	public void setVcCosttypename( String vcCosttypename )
	{
		this.vcCosttypename = vcCosttypename;
	}
	
	/**
	 * @return the nCost
	 */
	public Float getnCost()
	{
		return nCost;
	}
	
	/**
	 * @param nCost the nCost to set
	 */
	public void setnCost( Float nCost )
	{
		this.nCost = nCost;
	}
	
	/**
	 * @return the vcNote
	 */
	public String getVcNote()
	{
		return vcNote;
	}
	
	/**
	 * @param vcNote the vcNote to set
	 */
	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
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
	 * @return the nState
	 */
	public Integer getnState()
	{
		return nState;
	}
	
	/**
	 * @param nState the nState to set
	 */
	public void setnState( Integer nState )
	{
		this.nState = nState;
	}
	
	/**
	 * @return the vcDeptName
	 */
	public String getVcDeptName()
	{
		return vcDeptName;
	}
	
	/**
	 * @param vcDeptName the vcDeptName to set
	 */
	public void setVcDeptName( String vcDeptName )
	{
		this.vcDeptName = vcDeptName;
	}

	/**
	 * @return the vcCompany
	 */
	public String getVcCompany()
	{
		return vcCompany;
	}

	/**
	 * @param vcCompany the vcCompany to set
	 */
	public void setVcCompany( String vcCompany )
	{
		this.vcCompany = vcCompany;
	}

	/**
	 * @return the vcBank
	 */
	public String getVcBank()
	{
		return vcBank;
	}

	/**
	 * @param vcBank the vcBank to set
	 */
	public void setVcBank( String vcBank )
	{
		this.vcBank = vcBank;
	}

	/**
	 * @return the vcAcceptMan
	 */
	public String getVcAcceptMan()
	{
		return vcAcceptMan;
	}

	/**
	 * @param vcAcceptMan the vcAcceptMan to set
	 */
	public void setVcAcceptMan( String vcAcceptMan )
	{
		this.vcAcceptMan = vcAcceptMan;
	}

	/**
	 * @return the iDept2
	 */
	public Integer getiDept2()
	{
		return iDept2;
	}

	/**
	 * @param iDept2 the iDept2 to set
	 */
	public void setiDept2( Integer iDept2 )
	{
		this.iDept2 = iDept2;
	}

	/**
	 * @return the vcDeptName2
	 */
	public String getVcDeptName2()
	{
		return vcDeptName2;
	}

	/**
	 * @param vcDeptName2 the vcDeptName2 to set
	 */
	public void setVcDeptName2( String vcDeptName2 )
	{
		this.vcDeptName2 = vcDeptName2;
	}

	/**
	 * @return the vcCompany2
	 */
	public String getVcCompany2()
	{
		return vcCompany2;
	}

	/**
	 * @param vcCompany2 the vcCompany2 to set
	 */
	public void setVcCompany2( String vcCompany2 )
	{
		this.vcCompany2 = vcCompany2;
	}

	/**
	 * @return the iPayType
	 */
	public Integer getiPayType()
	{
		return iPayType;
	}

	/**
	 * @param iPayType the iPayType to set
	 */
	public void setiPayType( Integer iPayType )
	{
		this.iPayType = iPayType;
	}

	/**
	 * @return the vcPaytype
	 */
	public String getVcPaytype()
	{
		return vcPaytype;
	}

	/**
	 * @param vcPaytype the vcPaytype to set
	 */
	public void setVcPaytype( String vcPaytype )
	{
		this.vcPaytype = vcPaytype;
	}

	/**
	 * @return the iCompanyId2
	 */
	public Integer getiCompanyId2()
	{
		return iCompanyId2;
	}

	/**
	 * @param iCompanyId2 the iCompanyId2 to set
	 */
	public void setiCompanyId2( Integer iCompanyId2 )
	{
		this.iCompanyId2 = iCompanyId2;
	}

	/**
	 * @return the vcApplyNo
	 */
	public String getVcApplyNo()
	{
		return vcApplyNo;
	}

	/**
	 * @param vcApplyNo the vcApplyNo to set
	 */
	public void setVcApplyNo( String vcApplyNo )
	{
		this.vcApplyNo = vcApplyNo;
	}

	/**
	 * @return the vcReason
	 */
	public String getVcReason()
	{
		return vcReason;
	}

	/**
	 * @param vcReason the vcReason to set
	 */
	public void setVcReason( String vcReason )
	{
		this.vcReason = vcReason;
	}

	/**
	 * @return the vcTaskId
	 */
	public String getVcTaskId()
	{
		return vcTaskId;
	}

	/**
	 * @param vcTaskId the vcTaskId to set
	 */
	public void setVcTaskId( String vcTaskId )
	{
		this.vcTaskId = vcTaskId;
	}

	/**
	 * @return the vcTaskName
	 */
	public String getVcTaskName()
	{
		return vcTaskName;
	}

	/**
	 * @param vcTaskName the vcTaskName to set
	 */
	public void setVcTaskName( String vcTaskName )
	{
		this.vcTaskName = vcTaskName;
	}
	
	
}