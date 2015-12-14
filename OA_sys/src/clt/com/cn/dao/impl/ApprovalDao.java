package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IApprovalDao;
import clt.com.cn.model.entity.Approval;
import clt.com.cn.model.entity.ApprovalFlow;
import clt.com.cn.model.entity.ApprovalRecord;

/**
 * @Package clt.com.cn.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenbin
 * @date 2014-7-24 下午3:32:05
 * @version V1.0
 */
public class ApprovalDao extends BaseDao implements IApprovalDao
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param obj
	 * @author chenbin
	 * @create_date 2014-7-24 下午3:32:25
	 */
	public void addApproval( Approval obj )
	{
		// TODO Auto-generated method stub
		super.addObject( obj );
	}
	
	public void addApprovalFlow( ApprovalFlow obj )
	{
		// TODO Auto-generated method stub
		super.addObject( obj );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @author chenbin
	 * @create_date 2014-7-24 下午3:32:25
	 */
	public void delApprovalById( int id )
	{
		// TODO Auto-generated method stub
		super.deleteObjectById( Approval.class , id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-24 下午3:32:25
	 */
	public Approval getApprovalById( int id )
	{
		// TODO Auto-generated method stub
		return ( Approval ) super.getObjectById( Approval.class , id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-24 下午3:32:25
	 */
	public ApprovalFlow getApprovalFlowById( int id )
	{
		// TODO Auto-generated method stub
		return ( ApprovalFlow ) super.getObjectById( ApprovalFlow.class , id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-24 下午3:32:25
	 */
	public ApprovalRecord getApprovalRecordById( int id )
	{
		// TODO Auto-generated method stub
		return ( ApprovalRecord ) super.getObjectById( ApprovalRecord.class , id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param d
	 * @author chenbin
	 * @create_date 2014-7-24 下午3:32:25
	 */
	public void updateApproval( Approval d )
	{
		// TODO Auto-generated method stub
		super.updateObject( d );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-24 下午3:52:29
	 */
	public List< Approval > getAllApproval()
	{
		// TODO Auto-generated method stub
		return super.getAllObjectOrder( "from Approval a where a.status>=0 " );
	}
	
}
