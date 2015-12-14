package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IContracttypeBDao;
import clt.com.cn.model.entity.ContracttypeB;

/** 
 * @Package clt.com.cn.dao.impl 
 * @Description:用一句话描述该文件做什么 
 * @author chengwzh 
 * @date 2015年10月26日 下午3:16:06 
 * @version V1.0 
 */
public class ContracttypeBDaoImp extends BaseDao implements IContracttypeBDao
{
	/** 
	 * @Description:通过父id查询合同子类
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年10月26日 下午3:18:26
	 */
	public List< ContracttypeB > getByPid( int pid )
	{
		String hql = "from ContracttypeB where iPid=" + pid;
		return super.getAllObjectOrder( hql );
	}
	
	/** 
	 * @Description:通过id获取
	 * @param id
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年10月26日 下午3:52:22
	 */
	public ContracttypeB get( int id )
	{
		return ( ContracttypeB ) super.getObjectById( ContracttypeB.class , id );
	}
	
}
