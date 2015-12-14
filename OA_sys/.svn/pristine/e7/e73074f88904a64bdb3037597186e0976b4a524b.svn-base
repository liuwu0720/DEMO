package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IContractFileDao;
import clt.com.cn.model.entity.ContractFile;
import clt.com.cn.service.IContractFileService;

/** 
 * @Package clt.com.cn.service.impl 
 * @Description:用一句话描述该文件做什么 
 * @author chengwzh 
 * @date 2015年10月28日 下午5:26:04 
 * @version V1.0 
 */
public class ContractFileServiceImp implements IContractFileService
{
	private IContractFileDao fileDao;
	
	public void setiContractFileDao( IContractFileDao iContractFileDao )
	{
		this.fileDao = iContractFileDao;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param id
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午5:27:41
	 */
	public ContractFile get( int id )
	{
		return fileDao.get( id );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午5:27:41
	 */
	public List< ContractFile > findAllByContractId( int cid )
	{
		String hql = "from ContractFile f where f.iContract=" + cid;
		return fileDao.getAllObjectOrder( hql );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param entity 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午5:27:41
	 */
	public void save( ContractFile entity )
	{
		fileDao.save( entity );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param entity 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午5:27:41
	 */
	public void update( ContractFile entity )
	{
		fileDao.update( entity );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param entity 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午5:27:41
	 */
	public void delete( ContractFile entity )
	{
		fileDao.delete( entity );
	}
	
}
