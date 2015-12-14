package clt.com.cn.model.entity;

/**
 * Costtype entity. @author MyEclipse Persistence Tools
 */

public class Costtype implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8059268191222192292L;
	private Integer id;
	private String vcName;
	private Integer pId;
	private Integer nEnable;
	
	// Constructors
	
	/** default constructor */
	public Costtype()
	{}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	/**
	 * @return the vcName
	 */
	public String getVcName()
	{
		return vcName;
	}
	
	/**
	 * @param vcName the vcName to set
	 */
	public void setVcName( String vcName )
	{
		this.vcName = vcName;
	}
	
	/**
	 * @return the pId
	 */
	public Integer getpId()
	{
		return pId;
	}
	
	/**
	 * @param pId the pId to set
	 */
	public void setpId( Integer pId )
	{
		this.pId = pId;
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
	
}