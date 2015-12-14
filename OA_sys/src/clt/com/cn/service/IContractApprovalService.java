package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.ContractApproval;

/** 
 * @Package clt.com.cn.service 
 * @Description:用一句话描述该文件做什么 
 * @author chengwzh 
 * @date 2015年10月29日 下午5:39:19 
 * @version V1.0 
 */
public interface IContractApprovalService
{
	public ContractApproval get( int id );
	
	public void save( ContractApproval entity );
	
	public void update( ContractApproval entity );
	
	public void delete( ContractApproval entity );
	
	public List< ContractApproval > findAllByContractId( int cid );
}
