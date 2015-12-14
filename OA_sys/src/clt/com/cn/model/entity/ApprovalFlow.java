package clt.com.cn.model.entity;

/**
 * @Package clt.com.cn.model.entity
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenbin
 * @date 2014-7-24 下午3:14:58
 * @version V1.0
 */
public class ApprovalFlow implements java.io.Serializable
{
	/**
     * 
     */
	private static final long serialVersionUID = 3027050427642306913L;
	
	private int lineid;
	private int Approvalid;
	private int checkusid;
	private int deptid;
	private int postionid;
	private int sortnumber;
	
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
	
	public int getDeptid()
	{
		return deptid;
	}
	
	public int getCheckusid()
	{
		return checkusid;
	}
	
	public void setCheckusid( int checkusid )
	{
		this.checkusid = checkusid;
	}
	
	public void setDeptid( int deptid )
	{
		this.deptid = deptid;
	}
	
	public int getPostionid()
	{
		return postionid;
	}
	
	public void setPostionid( int postionid )
	{
		this.postionid = postionid;
	}
	
	public int getSortnumber()
	{
		return sortnumber;
	}
	
	public void setSortnumber( int sortnumber )
	{
		this.sortnumber = sortnumber;
	}
	
}
