package clt.com.cn.model.entity;


/**
 * 档案合同类型. 通讯录只显示正式员工的信息 @author MyEclipse Persistence Tools
 */

public class ContractType implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 4738345776097043799L;
	private int lineid;
	private String typename;
	
	public int getLineid()
	{
		return lineid;
	}
	
	public void setLineid( int lineid )
	{
		this.lineid = lineid;
	}
	
	public String getTypename()
	{
		return typename;
	}
	
	public void setTypename( String typename )
	{
		this.typename = typename;
	}
	
}