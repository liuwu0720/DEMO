package clt.com.cn.model.entity;

/**
 * 
 */

public class DeptLevel implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 7962433550837281246L;
	private Integer lineid;
	private Integer iDept;
	private String vcDeptname;
	private Integer iUser;
	private String vcUserno;
	private Integer nLeveal;
	private Integer iPosition;
	private String vcPosition;
	private String vcEmail;
	private Integer nEnable;
	private Integer iCompany;
	private String vcCompany;
	private String vcUserName;
	
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
	public DeptLevel()
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
	 * @return the vcDeptname
	 */
	public String getVcDeptname()
	{
		return vcDeptname;
	}
	
	/**
	 * @param vcDeptname the vcDeptname to set
	 */
	public void setVcDeptname( String vcDeptname )
	{
		this.vcDeptname = vcDeptname;
	}
	
	/**
	 * @return the iUser
	 */
	public Integer getiUser()
	{
		return iUser;
	}
	
	/**
	 * @param iUser the iUser to set
	 */
	public void setiUser( Integer iUser )
	{
		this.iUser = iUser;
	}
	
	/**
	 * @return the vcUserno
	 */
	public String getVcUserno()
	{
		return vcUserno;
	}
	
	/**
	 * @param vcUserno the vcUserno to set
	 */
	public void setVcUserno( String vcUserno )
	{
		this.vcUserno = vcUserno;
	}
	
	/**
	 * @return the nLeveal
	 */
	public Integer getnLeveal()
	{
		return nLeveal;
	}
	
	/**
	 * @param nLeveal the nLeveal to set
	 */
	public void setnLeveal( Integer nLeveal )
	{
		this.nLeveal = nLeveal;
	}
	
	/**
	 * @return the iPosition
	 */
	public Integer getiPosition()
	{
		return iPosition;
	}
	
	/**
	 * @param iPosition the iPosition to set
	 */
	public void setiPosition( Integer iPosition )
	{
		this.iPosition = iPosition;
	}
	
	/**
	 * @return the vcPosition
	 */
	public String getVcPosition()
	{
		return vcPosition;
	}
	
	/**
	 * @param vcPosition the vcPosition to set
	 */
	public void setVcPosition( String vcPosition )
	{
		this.vcPosition = vcPosition;
	}
	
	/**
	 * @return the vcEmail
	 */
	public String getVcEmail()
	{
		return vcEmail;
	}
	
	/**
	 * @param vcEmail the vcEmail to set
	 */
	public void setVcEmail( String vcEmail )
	{
		this.vcEmail = vcEmail;
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
	 * @return the vcUserName
	 */
	public String getVcUserName()
	{
		return vcUserName;
	}

	/**
	 * @param vcUserName the vcUserName to set
	 */
	public void setVcUserName( String vcUserName )
	{
		this.vcUserName = vcUserName;
	}
	
	
	
}