package clt.com.cn.model.entity;

import java.io.Serializable;

/**
 * @Package clt.com.cn.model.entity
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenbin
 * @date 2014-7-24 下午3:20:52
 * @version V1.0
 */

public class ApprovalRecord implements Serializable
{
	/**
     * 
     */
	private static final long serialVersionUID = - 6190118211224279462L;
	
	private int lineid;
	private int Approvalid;
	private int ApprovalFlowid;
	private String checkremarks;
	private int checkusid;
	private int ischeck;
	private int businessid;
	private int typeid;
	
	public int getLineid()
	{
		return lineid;
	}
	
	public void setLineid( int lineid )
	{
		this.lineid = lineid;
	}
	
	public int getApprovalid()
	{
		return Approvalid;
	}
	
	public void setApprovalid( int approvalid )
	{
		Approvalid = approvalid;
	}
	
	public int getApprovalFlowid()
	{
		return ApprovalFlowid;
	}
	
	public void setApprovalFlowid( int approvalFlowid )
	{
		ApprovalFlowid = approvalFlowid;
	}
	
	public String getCheckremarks()
	{
		return checkremarks;
	}
	
	public void setCheckremarks( String checkremarks )
	{
		this.checkremarks = checkremarks;
	}
	
	public int getCheckusid()
	{
		return checkusid;
	}
	
	public void setCheckusid( int checkusid )
	{
		this.checkusid = checkusid;
	}
	
	public int getIscheck()
	{
		return ischeck;
	}
	
	public void setIscheck( int ischeck )
	{
		this.ischeck = ischeck;
	}
	
	public int getBusinessid()
	{
		return businessid;
	}
	
	public void setBusinessid( int businessid )
	{
		this.businessid = businessid;
	}
	
	public int getTypeid()
	{
		return typeid;
	}
	
	public void setTypeid( int typeid )
	{
		this.typeid = typeid;
	}
	
}
