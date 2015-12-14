package clt.com.cn.model.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * ContractApprovalId entity. @author MyEclipse Persistence Tools
 */

public class ContractApproval implements java.io.Serializable
{
	
	// Fields
	
	private Integer id;
	private Integer IContract;
	private Integer IUser;
	private Integer IDept;
	private String vcApprovalname;
	private Timestamp dtApproval;
	private String vcComment;
	private Integer NGrade;
	private String vcDeptName;
	private String vcNote;
	
	// Constructors
	
	/** default constructor */
	public ContractApproval()
	{}
	

	
	// Property accessors
	
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	public Integer getIContract()
	{
		return this.IContract;
	}
	
	public void setIContract( Integer IContract )
	{
		this.IContract = IContract;
	}
	
	public Integer getIUser()
	{
		return this.IUser;
	}
	
	public void setIUser( Integer IUser )
	{
		this.IUser = IUser;
	}
	
	public Integer getIDept()
	{
		return this.IDept;
	}
	
	public void setIDept( Integer IDept )
	{
		this.IDept = IDept;
	}
	
	public String getVcApprovalname()
	{
		return this.vcApprovalname;
	}
	
	public void setVcApprovalname( String vcApprovalname )
	{
		this.vcApprovalname = vcApprovalname;
	}
	
	public Date getDtApproval()
	{
		return this.dtApproval;
	}
	
	public void setDtApproval( Timestamp dtApproval )
	{
		this.dtApproval = dtApproval;
	}
	
	public String getVcComment()
	{
		return this.vcComment;
	}
	
	public void setVcComment( String vcComment )
	{
		this.vcComment = vcComment;
	}
	
	public Integer getNGrade()
	{
		return this.NGrade;
	}
	
	public void setNGrade( Integer NGrade )
	{
		this.NGrade = NGrade;
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
	
}