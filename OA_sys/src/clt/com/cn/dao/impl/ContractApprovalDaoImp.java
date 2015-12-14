package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IContractApprovalDao;
import clt.com.cn.model.entity.ContractApproval;

/** 
 * @Package clt.com.cn.dao.impl 
 * @Description:用一句话描述该文件做什么 
 * @author chengwzh 
 * @date 2015年10月29日 下午5:30:43 
 * @version V1.0 
 */
public class ContractApprovalDaoImp extends BaseDao implements IContractApprovalDao
{
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param id
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年10月29日 下午5:31:28
	 */
	public ContractApproval get( int id )
	{
		return ( ContractApproval ) super.getObjectById( ContractApproval.class , id );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param entity 
	 * @author chengwzh
	 * @create_date 2015年10月29日 下午5:31:28
	 */
	public void save( ContractApproval entity )
	{
		super.addObject( entity );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param entity 
	 * @author chengwzh
	 * @create_date 2015年10月29日 下午5:31:28
	 */
	public void update( ContractApproval entity )
	{
		super.updateObject( entity );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param entity 
	 * @author chengwzh
	 * @create_date 2015年10月29日 下午5:31:28
	 */
	public void delete( ContractApproval entity )
	{
		super.deleteObject( entity );
	}
	
	/**
	 * 
	 * @Description:通过合同id获取合同审批列表
	 * @return 
	 *   List<ContractApproval> 返回值描述
	 * @author chengwzh
	 * @create_date 2015年10月29日 下午5:36:19
	 */
	public List< ContractApproval > findAllByContractId( int cid )
	{
		String hql = "from ContractApproval c where c.IContract=" + cid+" order by c.id desc";
		return super.getAllObjectOrder( hql );
	}
}
