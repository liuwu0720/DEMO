package clt.com.cn.model.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Dept entity. @author MyEclipse Persistence Tools
 */

public class Dept implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = - 4113115006631870738L;
	private int lineid;
	private int pid;
	private String deptname;
	private int active;
	private int level;
	private String userno;
	private Date currdate;
	private Smuser manageruser;
	private Set notifies = new HashSet( 0 );
	private Set employrecords = new HashSet( 0 );
	
	// Constructors
	
	/** default constructor */
	public Dept()
	{}
	
	/** minimal constructor */
	public Dept( int lineid , int pid , String deptname , String contactname ,
	        String tel , String email , int active , String userno , Date currdate )
	{
		this.lineid = lineid;
		this.pid = pid;
		this.deptname = deptname;
		this.active = active;
		this.userno = userno;
		this.currdate = currdate;
	}
	
	/** full constructor */
	public Dept( int lineid , int pid , String deptname , String contactname ,
	        String tel , String email , int active , String userno , Date currdate ,
	        Set notifies , Set depts , Set employrecords )
	{
		this.lineid = lineid;
		this.pid = pid;
		this.deptname = deptname;
		this.active = active;
		this.userno = userno;
		this.currdate = currdate;
		this.notifies = notifies;
		this.employrecords = employrecords;
	}
	
	// Property accessors
	
	public int getLineid()
	{
		return this.lineid;
	}
	
	public void setLineid( int lineid )
	{
		this.lineid = lineid;
	}
	
	public int getPid()
	{
		return pid;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel( int level )
	{
		this.level = level;
	}
	
	public void setPid( int pid )
	{
		this.pid = pid;
	}
	
	public String getDeptname()
	{
		return this.deptname;
	}
	
	public Smuser getManageruser()
	{
		return manageruser;
	}
	
	public void setManageruser( Smuser manageruser )
	{
		this.manageruser = manageruser;
	}
	
	public void setDeptname( String deptname )
	{
		this.deptname = deptname;
	}
	
	public int getActive()
	{
		return this.active;
	}
	
	public void setActive( int active )
	{
		this.active = active;
	}
	
	public String getUserno()
	{
		return this.userno;
	}
	
	public void setUserno( String userno )
	{
		this.userno = userno;
	}
	
	public Date getCurrdate()
	{
		return this.currdate;
	}
	
	public void setCurrdate( Date currdate )
	{
		this.currdate = currdate;
	}
	
	public Set getNotifies()
	{
		return this.notifies;
	}
	
	public void setNotifies( Set notifies )
	{
		this.notifies = notifies;
	}
	
	public Set getEmployrecords()
	{
		return this.employrecords;
	}
	
	public void setEmployrecords( Set employrecords )
	{
		this.employrecords = employrecords;
	}
	
}