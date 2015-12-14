package clt.com.cn.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Package clt.com.cn.model.entity
 * @Description: 印章申请
 * @author chenbin
 * @date 2014-7-25 下午5:20:16
 * @version V1.0
 */
public class SignetApply implements Serializable
{
	
	/**
     * 
     */
	private static final long serialVersionUID = - 919394643738369943L;
	
	private int lineid;
	private int applyusid;
	private int applyDeptid;
	private Date applyDate;
	
	private String notice;
	private String remarks;
	
	private int signetcount;
	private int firstcheckusid;
	private int firstischeck;
	private Date firstcheckDate;
	private String firstcheckRemarks;
	
	private int isfirstcheck;
	private int Approvalid;
	private int ApprovalFlowid;
	private int ischeck;
	
	public int getLineid()
	{
		return lineid;
	}
	
	public void setLineid( int lineid )
	{
		this.lineid = lineid;
	}
	
	public int getApplyusid()
	{
		return applyusid;
	}
	
	public void setApplyusid( int applyusid )
	{
		this.applyusid = applyusid;
	}
	
	public int getApplyDeptid()
	{
		return applyDeptid;
	}
	
	public void setApplyDeptid( int applyDeptid )
	{
		this.applyDeptid = applyDeptid;
	}
	
	public Date getApplyDate()
	{
		return applyDate;
	}
	
	public void setApplyDate( Date applyDate )
	{
		this.applyDate = applyDate;
	}
	
	public String getNotice()
	{
		return notice;
	}
	
	public void setNotice( String notice )
	{
		this.notice = notice;
	}
	
	public String getRemarks()
	{
		return remarks;
	}
	
	public void setRemarks( String remarks )
	{
		this.remarks = remarks;
	}
	
	public int getSignetcount()
	{
		return signetcount;
	}
	
	public void setSignetcount( int signetcount )
	{
		this.signetcount = signetcount;
	}
	
	public int getFirstcheckusid()
	{
		return firstcheckusid;
	}
	
	public void setFirstcheckusid( int firstcheckusid )
	{
		this.firstcheckusid = firstcheckusid;
	}
	
	public int getFirstischeck()
	{
		return firstischeck;
	}
	
	public void setFirstischeck( int firstischeck )
	{
		this.firstischeck = firstischeck;
	}
	
	public Date getFirstcheckDate()
	{
		return firstcheckDate;
	}
	
	public void setFirstcheckDate( Date firstcheckDate )
	{
		this.firstcheckDate = firstcheckDate;
	}
	
	public String getFirstcheckRemarks()
	{
		return firstcheckRemarks;
	}
	
	public void setFirstcheckRemarks( String firstcheckRemarks )
	{
		this.firstcheckRemarks = firstcheckRemarks;
	}
	
	public int getIsfirstcheck()
	{
		return isfirstcheck;
	}
	
	public void setIsfirstcheck( int isfirstcheck )
	{
		this.isfirstcheck = isfirstcheck;
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
	
	public int getIscheck()
	{
		return ischeck;
	}
	
	public void setIscheck( int ischeck )
	{
		this.ischeck = ischeck;
	}
	
}
