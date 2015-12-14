package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IContractApprovalDao;
import clt.com.cn.model.entity.ContractApproval;
import clt.com.cn.service.IContractApprovalService;

/** 
 * @Package clt.com.cn.service.impl 
 * @Description:用一句话描述该文件做什么 
 * @author chengwzh 
 * @date 2015年10月29日 下午5:39:49 
 * @version V1.0 
 */
public class ContractApprovalServiceImp implements IContractApprovalService
{
	private IContractApprovalDao approvalDao;
	
	public void setiContractApprovalDao( IContractApprovalDao iContractApprovalDao )
	{
		this.approvalDao = iContractApprovalDao;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param id
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年10月29日 下午5:40:31
	 */
	public ContractApproval get( int id )
	{
		return approvalDao.get( id );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param entity 
	 * @author chengwzh
	 * @create_date 2015年10月29日 下午5:40:31
	 */
	public void save( ContractApproval entity )
	{
		approvalDao.save( entity );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param entity 
	 * @author chengwzh
	 * @create_date 2015年10月29日 下午5:40:31
	 */
	public void update( ContractApproval entity )
	{
		approvalDao.update( entity );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param entity 
	 * @author chengwzh
	 * @create_date 2015年10月29日 下午5:40:31
	 */
	public void delete( ContractApproval entity )
	{
		approvalDao.delete( entity );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param cid
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年10月29日 下午5:40:31
	 */
	public List< ContractApproval > findAllByContractId( int cid )
	{
		return approvalDao.findAllByContractId( cid );
	}
	
}
