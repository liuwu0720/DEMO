package clt.com.cn.model.entity;

import java.util.Date;

/**
 * Dept entity. @author MyEclipse Persistence Tools
 */
/**
 * 
 * @Package clt.com.cn.model.entity
 * @Description: 档案修改对象
 * @author chenbin
 * @date 2014-8-20 上午9:56:17
 * @version V1.0
 */
public class EmployrecordCheck implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = - 4113115006631870738L;
	private int lineid;
	private int recordid;
	private int bfCompid;
	private int bfDeptid;
	private int bfPosid;
	private int bfofCompid;
	private int bfDeptAdresid;
	private int nowCompid;
	private int nowDeptid;
	private int nowPosid;
	private int nowofCompid;
	private int nowDeptAdresid;
	private int ischeck;
	private int status;
	private int hrstatus;
	private Smuser opuser;
	private Date opDate;
	private Smuser checkus;
	private Date checkDate;
	private String checkremaks;
	
	// Constructors
	
	/** default constructor */
	public EmployrecordCheck()
	{}
	
	public int getLineid()
	{
		return lineid;
	}
	
	public void setLineid( int lineid )
	{
		this.lineid = lineid;
	}
	
	public int getRecordid()
	{
		return recordid;
	}
	
	public void setRecordid( int recordid )
	{
		this.recordid = recordid;
	}
	
	public int getBfDeptid()
	{
		return bfDeptid;
	}
	
	public void setBfDeptid( int bfDeptid )
	{
		this.bfDeptid = bfDeptid;
	}
	
	public int getBfPosid()
	{
		return bfPosid;
	}
	
	public void setBfPosid( int bfPosid )
	{
		this.bfPosid = bfPosid;
	}
	
	public int getBfCompid()
	{
		return bfCompid;
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public void setStatus( int status )
	{
		this.status = status;
	}
	
	public int getHrstatus()
	{
		return hrstatus;
	}
	
	public void setHrstatus( int hrstatus )
	{
		this.hrstatus = hrstatus;
	}
	
	public void setBfCompid( int bfCompid )
	{
		this.bfCompid = bfCompid;
	}
	
	public int getBfofCompid()
	{
		return bfofCompid;
	}
	
	public void setBfofCompid( int bfofCompid )
	{
		this.bfofCompid = bfofCompid;
	}
	
	public int getNowCompid()
	{
		return nowCompid;
	}
	
	public void setNowCompid( int nowCompid )
	{
		this.nowCompid = nowCompid;
	}
	
	public int getNowofCompid()
	{
		return nowofCompid;
	}
	
	public void setNowofCompid( int nowofCompid )
	{
		this.nowofCompid = nowofCompid;
	}
	
	public int getBfDeptAdresid()
	{
		return bfDeptAdresid;
	}
	
	public void setBfDeptAdresid( int bfDeptAdresid )
	{
		this.bfDeptAdresid = bfDeptAdresid;
	}
	
	public Smuser getOpuser()
	{
		return opuser;
	}
	
	public void setOpuser( Smuser opuser )
	{
		this.opuser = opuser;
	}
	
	public Date getOpDate()
	{
		return opDate;
	}
	
	public void setOpDate( Date opDate )
	{
		this.opDate = opDate;
	}
	
	public int getNowDeptid()
	{
		return nowDeptid;
	}
	
	public void setNowDeptid( int nowDeptid )
	{
		this.nowDeptid = nowDeptid;
	}
	
	public int getNowPosid()
	{
		return nowPosid;
	}
	
	public void setNowPosid( int nowPosid )
	{
		this.nowPosid = nowPosid;
	}
	
	public int getNowDeptAdresid()
	{
		return nowDeptAdresid;
	}
	
	public void setNowDeptAdresid( int nowDeptAdresid )
	{
		this.nowDeptAdresid = nowDeptAdresid;
	}
	
	public int getIscheck()
	{
		return ischeck;
	}
	
	public void setIscheck( int ischeck )
	{
		this.ischeck = ischeck;
	}
	
	public Smuser getCheckus()
	{
		return checkus;
	}
	
	public void setCheckus( Smuser checkus )
	{
		this.checkus = checkus;
	}
	
	public Date getCheckDate()
	{
		return checkDate;
	}
	
	public void setCheckDate( Date checkDate )
	{
		this.checkDate = checkDate;
	}
	
	public String getCheckremaks()
	{
		return checkremaks;
	}
	
	public void setCheckremaks( String checkremaks )
	{
		this.checkremaks = checkremaks;
	}
	
}