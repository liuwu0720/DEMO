package clt.com.cn.model.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * FeeTrafficId entity. @author MyEclipse Persistence Tools
 */

public class FeeTraffic implements java.io.Serializable
{
	
	// Fields
	
	private Integer id;
	private Date dtStart;
	private Date dtArrive;
	private String vcStart;
	private String vcArrive;
	private Integer iEmploytriptool;
	private String vcEmploytriptool;
	private String vcReason;
	private Float nReimburse;
	private Float nTravel;
	private String vcNote;
	private String vcApplyname;
	private Integer nEnable;
	private Date dtCreate;
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	public Date getDtStart()
	{
		return dtStart;
	}
	
	public void setDtStart( Date dtStart )
	{
		this.dtStart = dtStart;
	}
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	public Date getDtArrive()
	{
		return dtArrive;
	}
	
	public void setDtArrive( Date dtArrive )
	{
		this.dtArrive = dtArrive;
	}
	
	public String getVcStart()
	{
		return vcStart;
	}
	
	public void setVcStart( String vcStart )
	{
		this.vcStart = vcStart;
	}
	
	public String getVcArrive()
	{
		return vcArrive;
	}
	
	public void setVcArrive( String vcArrive )
	{
		this.vcArrive = vcArrive;
	}
	
	public Integer getiEmploytriptool()
	{
		return iEmploytriptool;
	}
	
	public void setiEmploytriptool( Integer iEmploytriptool )
	{
		this.iEmploytriptool = iEmploytriptool;
	}
	
	public String getVcEmploytriptool()
	{
		return vcEmploytriptool;
	}
	
	public void setVcEmploytriptool( String vcEmploytriptool )
	{
		this.vcEmploytriptool = vcEmploytriptool;
	}
	
	public String getVcReason()
	{
		return vcReason;
	}
	
	public void setVcReason( String vcReason )
	{
		this.vcReason = vcReason;
	}
	
	/**
	 * @return the nReimburse
	 */
	public Float getnReimburse()
	{
		return nReimburse;
	}


	/**
	 * @param nReimburse the nReimburse to set
	 */
	public void setnReimburse( Float nReimburse )
	{
		this.nReimburse = nReimburse;
	}

	public Float getnTravel()
	{
		return nTravel;
	}
	
	public void setnTravel( Float nTravel )
	{
		this.nTravel = nTravel;
	}
	
	public String getVcNote()
	{
		return vcNote;
	}
	
	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
	}
	
	public String getVcApplyname()
	{
		return vcApplyname;
	}
	
	public void setVcApplyname( String vcApplyname )
	{
		this.vcApplyname = vcApplyname;
	}
	
	public Integer getnEnable()
	{
		return nEnable;
	}
	
	public void setnEnable( Integer nEnable )
	{
		this.nEnable = nEnable;
	}
	
	public Date getDtCreate()
	{
		return dtCreate;
	}
	
	public void setDtCreate( Date dtCreate )
	{
		this.dtCreate = dtCreate;
	}
	
}