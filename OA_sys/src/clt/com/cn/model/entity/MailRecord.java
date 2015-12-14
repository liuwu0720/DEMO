package clt.com.cn.model.entity;

import java.io.Serializable;
import java.util.Date;

public class MailRecord implements Serializable
{
	private Integer lineid;
	private Integer iEmpRecordId;
	private Date dtUpdate;
	private String vcIp;
	private String vcAcount;
	
	public Integer getLineid()
	{
		return lineid;
	}
	
	public void setLineid( Integer lineid )
	{
		this.lineid = lineid;
	}
	
	public Integer getiEmpRecordId()
	{
		return iEmpRecordId;
	}
	
	public void setiEmpRecordId( Integer iEmpRecordId )
	{
		this.iEmpRecordId = iEmpRecordId;
	}
	
	public Date getDtUpdate()
	{
		return dtUpdate;
	}
	
	public void setDtUpdate( Date dtUpdate )
	{
		this.dtUpdate = dtUpdate;
	}
	
	public String getVcIp()
	{
		return vcIp;
	}
	
	public void setVcIp( String vcIp )
	{
		this.vcIp = vcIp;
	}
	
	public String getVcAcount()
	{
		return vcAcount;
	}
	
	public void setVcAcount( String vcAcount )
	{
		this.vcAcount = vcAcount;
	}
	
}
