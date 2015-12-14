package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IContractDao;
import clt.com.cn.model.entity.Contract;
import clt.com.cn.service.IContractManageService;

/** 
 * @Package clt.com.cn.service.impl 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年10月21日 下午6:14:12 
 * @version V1.0 
 */

public class ContractManageServiceImp implements IContractManageService
{
	private IContractDao iContractDao;
	
	/**
	 * @return the iContractDao
	 */
	public IContractDao getiContractDao()
	{
		return iContractDao;
	}
	
	/**
	 * @param iContractDao the iContractDao to set
	 */
	public void setiContractDao( IContractDao iContractDao )
	{
		this.iContractDao = iContractDao;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param count
	 * @param pageSize
	 * @return 
	 * @author liuwu
	 * @create_date 2015年10月21日 下午6:14:31
	 */
	public int getpages( int count , int pageSize )
	{
		return iContractDao.getpages( count , pageSize );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param sql
	 * @return 
	 * @author liuwu
	 * @create_date 2015年10月21日 下午6:14:31
	 */
	public int getCountBySQL( String sql )
	{
		return iContractDao.getCountBySQL( sql );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param page
	 * @return 
	 * @author liuwu
	 * @create_date 2015年10月21日 下午6:18:45
	 */
	public List getAllContracts( int page , int userId )
	{
		
		return iContractDao.getAllContracts( page , userId );
	}
	
	public List< Contract > getDataBySqlQuery( String sql , int pageSize , int page )
	{
		return iContractDao.getDataBySqlQuery( sql , pageSize , page );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @return 
	 * @author liuwu
	 * @create_date 2015年10月21日 下午6:19:14
	 */
	public int getCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param contract 
	 * @author liuwu
	 * @create_date 2015年10月22日 上午10:02:42
	 */
	public void save( Contract contract )
	{
		// TODO Auto-generated method stub
		iContractDao.save( contract );
	}
	
	/**
	 * 
	 * @Description:删除 
	 * @param id 
	 * @author chengwzh
	 * @create_date 2015年10月23日 下午5:13:27
	 */
	
	public void delete( int id )
	{
		iContractDao.delete( id );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param contract 
	 * @author chengwzh
	 * @create_date 2015年10月23日 下午5:13:15
	 */
	public void update( Contract contract )
	{
		iContractDao.update( contract );
	}
	
	/**
	 * 
	 * @Description:通过id获取
	 * @param id
	 * @return 
	 *   Contract 返回值描述
	 * @author chengwzh
	 * @create_date 2015年10月23日 下午5:16:33
	 */
	public Contract get( int id )
	{
		return iContractDao.get( id );
	}
}
