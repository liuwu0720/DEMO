/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-12-4 下午1:47:38 
 * @version V1.0 
 */
package com.chnl.entity;

import java.io.Serializable;

/** 
 * @Package com.chnl.entity 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-12-4 下午1:47:38 
 * @version V1.0 
 */
public class SmUser implements Serializable
{	
	/**
     * 
     */
	private static final long serialVersionUID = - 5108535165833781544L;
	private String userName;
	
	public String getUserName()
	{
		return userName;
	}
	
	public void setUserName( String userName )
	{
		this.userName = userName;
	}
	
}
