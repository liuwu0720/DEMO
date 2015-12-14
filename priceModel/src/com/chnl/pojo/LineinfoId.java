package com.chnl.pojo;

/**
 * LineinfoId entity. @author MyEclipse Persistence Tools
 */

public class LineinfoId implements java.io.Serializable
{
	
	// Fields
	
	private String id;
	private Integer originid;
	private String originprovince;
	private String origincity;
	private Integer pid;
	private Integer desid;
	private String desprovince;
	private String descity;
	
	// Constructors
	
	/** default constructor */
	public LineinfoId()
	{}
	
	/** minimal constructor */
	public LineinfoId( Integer pid )
	{
		this.pid = pid;
	}
	
	/** full constructor */
	public LineinfoId( String id , Integer originid , String originprovince ,
	        String origincity , Integer pid , Integer desid ,
	        String desprovince ,
	        String descity )
	{
		this.id = id;
		this.originid = originid;
		this.originprovince = originprovince;
		this.origincity = origincity;
		this.pid = pid;
		this.desid = desid;
		this.desprovince = desprovince;
		this.descity = descity;
	}
	
	// Property accessors
	
	public String getId()
	{
		return this.id;
	}
	
	public void setId( String id )
	{
		this.id = id;
	}
	
	public Integer getOriginid()
	{
		return this.originid;
	}
	
	public void setOriginid( Integer originid )
	{
		this.originid = originid;
	}
	
	public String getOriginprovince()
	{
		return this.originprovince;
	}
	
	public void setOriginprovince( String originprovince )
	{
		this.originprovince = originprovince;
	}
	
	public String getOrigincity()
	{
		return this.origincity;
	}
	
	public void setOrigincity( String origincity )
	{
		this.origincity = origincity;
	}
	
	public Integer getPid()
	{
		return this.pid;
	}
	
	public void setPid( Integer pid )
	{
		this.pid = pid;
	}
	
	public Integer getDesid()
	{
		return this.desid;
	}
	
	public void setDesid( Integer desid )
	{
		this.desid = desid;
	}
	
	public String getDesprovince()
	{
		return this.desprovince;
	}
	
	public void setDesprovince( String desprovince )
	{
		this.desprovince = desprovince;
	}
	
	public String getDescity()
	{
		return this.descity;
	}
	
	public void setDescity( String descity )
	{
		this.descity = descity;
	}
	
	public boolean equals( Object other )
	{
		if ( ( this == other ) )
			return true;
		if ( ( other == null ) )
			return false;
		if ( ! ( other instanceof LineinfoId ) )
			return false;
		LineinfoId castOther = ( LineinfoId ) other;
		
		return ( ( this.getId() == castOther.getId() ) || ( this.getId() != null
		        && castOther.getId() != null && this.getId().equals(
		        castOther.getId() ) ) )
		        && ( ( this.getOriginid() == castOther.getOriginid() ) || ( this
		                .getOriginid() != null
		                && castOther.getOriginid() != null && this
		                .getOriginid().equals( castOther.getOriginid() ) ) )
		        && ( ( this.getOriginprovince() == castOther
		                .getOriginprovince() ) || ( this.getOriginprovince() != null
		                && castOther.getOriginprovince() != null && this
		                .getOriginprovince().equals(
		                        castOther.getOriginprovince() ) ) )
		        && ( ( this.getOrigincity() == castOther.getOrigincity() ) || ( this
		                .getOrigincity() != null
		                && castOther.getOrigincity() != null && this
		                .getOrigincity().equals( castOther.getOrigincity() ) ) )
		        && ( ( this.getPid() == castOther.getPid() ) || ( this.getPid() != null
		                && castOther.getPid() != null && this.getPid().equals(
		                castOther.getPid() ) ) )
		        && ( ( this.getDesid() == castOther.getDesid() ) || ( this
		                .getDesid() != null && castOther.getDesid() != null && this
		                .getDesid().equals( castOther.getDesid() ) ) )
		        && ( ( this.getDesprovince() == castOther.getDesprovince() ) || ( this
		                .getDesprovince() != null
		                && castOther.getDesprovince() != null && this
		                .getDesprovince().equals( castOther.getDesprovince() ) ) )
		        && ( ( this.getDescity() == castOther.getDescity() ) || ( this
		                .getDescity() != null && castOther.getDescity() != null && this
		                .getDescity().equals( castOther.getDescity() ) ) );
	}
	
	public int hashCode()
	{
		int result = 17;
		
		result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
		result = 37 * result
		        + ( getOriginid() == null ? 0 : this.getOriginid().hashCode() );
		result = 37
		        * result
		        + ( getOriginprovince() == null ? 0 : this.getOriginprovince()
		                .hashCode() );
		result = 37
		        * result
		        + ( getOrigincity() == null ? 0 : this.getOrigincity()
		                .hashCode() );
		result = 37 * result
		        + ( getPid() == null ? 0 : this.getPid().hashCode() );
		result = 37 * result
		        + ( getDesid() == null ? 0 : this.getDesid().hashCode() );
		result = 37
		        * result
		        + ( getDesprovince() == null ? 0 : this.getDesprovince()
		                .hashCode() );
		result = 37 * result
		        + ( getDescity() == null ? 0 : this.getDescity().hashCode() );
		return result;
	}
	
}