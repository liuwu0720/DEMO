package clt.com.cn.model.entity;

import java.util.Date;

/**
 * EmployManager 人员跟公司待办 映射 entity. @author MyEclipse Persistence Tools
 */

public class UserSchedule implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = - 6219895616012250984L;
	private int lineid;
	private int userid;
	private int deptid;
	private Smuser opuser;
	private Date optime;
	
	public int getLineid()
	{
		return lineid;
	}
	
	public void setLineid( int lineid )
	{
		this.lineid = lineid;
	}
	
	public int getUserid()
	{
		return userid;
	}
	
	public void setUserid( int userid )
	{
		this.userid = userid;
	}
	
	public int getDeptid()
	{
		return deptid;
	}
	
	public void setDeptid( int deptid )
	{
		this.deptid = deptid;
	}
	
	public Smuser getOpuser()
	{
		return opuser;
	}
	
	public void setOpuser( Smuser opuser )
	{
		this.opuser = opuser;
	}
	
	public Date getOptime()
	{
		return optime;
	}
	
	public void setOptime( Date optime )
	{
		this.optime = optime;
	}
	
}