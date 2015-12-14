package clt.com.cn.model.entity;

import java.io.Serializable;

/** 
 * @Package clt.com.cn.model.entity 
 * @Description:发送邮件相关的类，与数据库不关联
 * @author liuwu
 * @date 2015年11月10日 上午9:23:48 
 * @version V1.0 
 */
public class UserEmail implements Serializable
{

	/**
	 * 
	 */
    private static final long serialVersionUID = - 6804265691399005666L;	
    
    private String emailAddress;//收件人地址
    private String emailLink;//收件人链接地址
    
    
	/**
	 * 
	 */
    public UserEmail()
    {
	    super();
    }
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress()
	{
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress( String emailAddress )
	{
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the emailLink
	 */
	public String getEmailLink()
	{
		return emailLink;
	}
	/**
	 * @param emailLink the emailLink to set
	 */
	public void setEmailLink( String emailLink )
	{
		this.emailLink = emailLink;
	}
	
}
