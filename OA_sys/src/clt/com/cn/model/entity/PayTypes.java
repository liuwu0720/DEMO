package clt.com.cn.model.entity;

import java.io.Serializable;

/** 
 * @Package clt.com.cn.model.entity 
 * @Description:结算方式
 * @author liuwu
 * @date 2015年12月9日 下午4:09:16 
 * @version V1.0 
 */
public class PayTypes implements Serializable
{	
	/**
	 * 
	 */
    private static final long serialVersionUID = - 4386173619876845391L;
	private Integer id;
	private String vcName;
	private Integer nEnable;
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
