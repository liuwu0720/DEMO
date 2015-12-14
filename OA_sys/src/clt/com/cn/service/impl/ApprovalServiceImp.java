package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IApprovalDao;
import clt.com.cn.model.entity.Approval;
import clt.com.cn.model.entity.ApprovalFlow;
import clt.com.cn.model.entity.ApprovalRecord;
import clt.com.cn.service.IApprovalService;

/**
 * @Package clt.com.cn.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenbin
 * @date 2014-7-24 下午3:37:28
 * @version V1.0
 */
public class ApprovalServiceImp implements IApprovalService
{
	
	private IApprovalDao approvalDao;
	
	public void setApprovalDao( IApprovalDao approvalDao )
	{
		this.approvalDao = approvalDao;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param obj
	 * @author chenbin
	 * @create_date 2014-7-24 下午3:37:41
	 */
	public void addApproval( Approval obj )
	{
		// TODO Auto-generated method stub
		approvalDao.addApproval( obj );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @author chenbin
	 * @create_date 2014-7-24 下午3:37:41
	 */
	public void delApprovalById( int id )
	{
		// TODO Auto-generated method stub
		approvalDao.delApprovalById( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-24 下午3:37:41
	 */
	public Approval getApprovalById( int id )
	{
		// TODO Auto-generated method stub
		return approvalDao.getApprovalById( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-24 下午3:37:41
	 */
	public ApprovalFlow getApprovalFlowById( int id )
	{
		// TODO Auto-generated method stub
		return approvalDao.getApprovalFlowById( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-24 下午3:37:41
	 */
	public ApprovalRecord getApprovalRecordById( int id )
	{
		// TODO Auto-generated method stub
		return approvalDao.getApprovalRecordById( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param d
	 * @author chenbin
	 * @create_date 2014-7-24 下午3:37:41
	 */
	public void updateApproval( Approval d )
	{
		// TODO Auto-generated method stub
		approvalDao.updateApproval( d );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-24 下午3:54:32
	 */
	public List< Approval > getAllApproval()
	{
		// TODO Auto-generated method stub
		return approvalDao.getAllApproval();
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param obj
	 * @author chenbin
	 * @create_date 2014-7-25 下午2:25:09
	 */
	public void addApprovalFlow( ApprovalFlow obj )
	{
		// TODO Auto-generated method stub
		approvalDao.addApprovalFlow( obj );
	}
	
}
