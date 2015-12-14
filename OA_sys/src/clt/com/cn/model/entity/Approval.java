package clt.com.cn.model.entity;

import java.util.Date;

/**
 * @Package clt.com.cn.model.entity
 * @Description: 审批模式名称
 * @author chenbin
 * @date 2014-7-24 下午3:11:33
 * @version V1.0
 */
public class Approval implements java.io.Serializable
{
	/**
     * 
     */
	private static final long serialVersionUID = - 3769602145626645206L;
	
	private int lineid;
	private String approvalName;
	private int deptid;
	private int detailnumber;
	private int opuserid;
	private Date opdate;
	private int status;
	
	public int getLineid()
	{
		return lineid;
	}
	
	public void setLineid( int lineid )
	{
		this.lineid = lineid;
	}
	
	public int getDeptid()
	{
		return deptid;
	}
	
	public String getApprovalName()
	{
		return approvalName;
	}
	
	public void setApprovalName( String approvalName )
	{
		this.approvalName = approvalName;
	}
	
	public void setDeptid( int deptid )
	{
		this.deptid = deptid;
	}
	
	public int getDetailnumber()
	{
		return detailnumber;
	}
	
	public void setDetailnumber( int detailnumber )
	{
		this.detailnumber = detailnumber;
	}
	
	public int getOpuserid()
	{
		return opuserid;
	}
	
	public void setOpuserid( int opuserid )
	{
		this.opuserid = opuserid;
	}
	
	public Date getOpdate()
	{
		return opdate;
	}
	
	public void setOpdate( Date opdate )
	{
		this.opdate = opdate;
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public void setStatus( int status )
	{
		this.status = status;
	}
	
}
