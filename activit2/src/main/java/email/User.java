package email;

import java.io.Serializable;

/** 
 * @Package email 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年11月9日 下午5:46:25 
 * @version V1.0 
 */
public class User implements Serializable
{	
	/**
	 * 
	 */
    private static final long serialVersionUID = 3394266052983059040L;
	String userEmail;
	String linkContent;
	
	
	/**
	 * 
	 */
    public User()
    {
	    super();
    }
	/**
	 * @param userEmail
	 * @param linkContent
	 */
    public User( String userEmail , String linkContent )
    {
	    super();
	    this.userEmail = userEmail;
	    this.linkContent = linkContent;
    }
	/**
	 * @return the userEmail
	 */
	public String getUserEmail()
	{
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail( String userEmail )
	{
		this.userEmail = userEmail;
	}
	/**
	 * @return the linkContent
	 */
	public String getLinkContent()
	{
		return linkContent;
	}
	/**
	 * @param linkContent the linkContent to set
	 */
	public void setLinkContent( String linkContent )
	{
		this.linkContent = linkContent;
	}
	
}
