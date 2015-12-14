package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IContracttypeBDao;
import clt.com.cn.model.entity.ContracttypeB;
import clt.com.cn.service.IContracttypeBService;

/** 
 * @Package clt.com.cn.service.impl 
 * @Description:用一句话描述该文件做什么 
 * @author chengwzh 
 * @date 2015年10月26日 下午3:28:15 
 * @version V1.0 
 */
public class ContracttypeBServiceImp implements IContracttypeBService
{
	private IContracttypeBDao iContracttypeBDao;
	
	/**
	 * @return the iContracttypeBDao
	 */
	public IContracttypeBDao getiContracttypeBDao()
	{
		return iContracttypeBDao;
	}

	/**
	 * @param iContracttypeBDao the iContracttypeBDao to set
	 */
	public void setiContracttypeBDao( IContracttypeBDao iContracttypeBDao )
	{
		this.iContracttypeBDao = iContracttypeBDao;
	}

	/** 
	 * @Description:通过父id获取合同子类型
	 * @param pid
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年10月26日 下午3:29:00
	 */
	public List< ContracttypeB > getByPid( int pid )
	{
		return iContracttypeBDao.getByPid( pid );
	}
	
	/** 
	 * @Description:通过id获取
	 * @param id
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年10月26日 下午3:55:56
	 */
	public ContracttypeB get( int id )
	{
		return iContracttypeBDao.get( id );
	}
	
}
