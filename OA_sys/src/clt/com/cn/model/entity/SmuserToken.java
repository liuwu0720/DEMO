package clt.com.cn.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/** 
 * @Package clt.com.cn.model.entity 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年11月10日 下午2:18:17 
 * @version V1.0 
 */
public class SmuserToken implements Serializable
{	
	/**
	 * 
	 */
    private static final long serialVersionUID = - 7748973641817236104L;
	private Integer id;
	private String vcToken;
	private String vcUserName;
	private Timestamp dtAdd;
	
	
	/**
	 * 
	 */
    public SmuserToken()
    {
	    super();
	    // TODO Auto-generated constructor stub
    }
	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId( Integer id )
	{
		this.id = id;
	}
	/**
	 * @return the vcToken
	 */
	public String getVcToken()
	{
		return vcToken;
	}
	/**
	 * @param vcToken the vcToken to set
	 */
	public void setVcToken( String vcToken )
	{
		this.vcToken = vcToken;
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
	/**
	 * @return the dtAdd
	 */
	public Timestamp getDtAdd()
	{
		return dtAdd;
	}
	/**
	 * @param dtAdd the dtAdd to set
	 */
	public void setDtAdd( Timestamp dtAdd )
	{
		this.dtAdd = dtAdd;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
	
	
}
