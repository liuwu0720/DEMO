package clt.com.cn.model.entity;

import java.util.Date;

/**
 * CostAuditrecord entity. @author MyEclipse Persistence Tools
 */

public class CostAuditrecord implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
    private static final long serialVersionUID = - 6855374706304862984L;
	private Integer lineid;
	private Integer iCostapply;
	private Date dtAudit;
	private Integer iUser;
	private String vcUser;
	private String vcNote;
	private Integer iDept;
	private String vcDept;
	private String vcComment;
	
	// Constructors
	
	/** default constructor */
	public CostAuditrecord()
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
	 * @return the iCostapply
	 */
	public Integer getiCostapply()
	{
		return iCostapply;
	}

	/**
	 * @param iCostapply the iCostapply to set
	 */
	public void setiCostapply( Integer iCostapply )
	{
		this.iCostapply = iCostapply;
	}

	/**
	 * @return the dtAudit
	 */
	public Date getDtAudit()
	{
		return dtAudit;
	}

	/**
	 * @param dtAudit the dtAudit to set
	 */
	public void setDtAudit( Date dtAudit )
	{
		this.dtAudit = dtAudit;
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
	 * @return the vcUser
	 */
	public String getVcUser()
	{
		return vcUser;
	}

	/**
	 * @param vcUser the vcUser to set
	 */
	public void setVcUser( String vcUser )
	{
		this.vcUser = vcUser;
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
	 * @return the vcComment
	 */
	public String getVcComment()
	{
		return vcComment;
	}

	/**
	 * @param vcComment the vcComment to set
	 */
	public void setVcComment( String vcComment )
	{
		this.vcComment = vcComment;
	}


	
}