package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.ICosttypeDao;
import clt.com.cn.dao.IPayTypesDao;
import clt.com.cn.dao.ITrafficToolsDao;
import clt.com.cn.model.entity.Costtype;
import clt.com.cn.model.entity.PayTypes;
import clt.com.cn.model.entity.TrafficTools;
import clt.com.cn.service.ICosttypeService;

public class CosttypeServiceImp implements ICosttypeService
{
	private ICosttypeDao typeDao;
	
	private ITrafficToolsDao iTrafficToolsDao;
	
	private IPayTypesDao iPayTypesDao;
	
	
	
	/**
	 * @return the iTrafficToolsDao
	 */
	public ITrafficToolsDao getiTrafficToolsDao()
	{
		return iTrafficToolsDao;
	}

	/**
	 * @param iTrafficToolsDao the iTrafficToolsDao to set
	 */
	public void setiTrafficToolsDao( ITrafficToolsDao iTrafficToolsDao )
	{
		this.iTrafficToolsDao = iTrafficToolsDao;
	}

	/**
	 * @return the iPayTypesDao
	 */
	public IPayTypesDao getiPayTypesDao()
	{
		return iPayTypesDao;
	}

	/**
	 * @param iPayTypesDao the iPayTypesDao to set
	 */
	public void setiPayTypesDao( IPayTypesDao iPayTypesDao )
	{
		this.iPayTypesDao = iPayTypesDao;
	}

	/**
	 * @return the typeDao
	 */
	public ICosttypeDao getTypeDao()
	{
		return typeDao;
	}

	/**
	 * @param typeDao the typeDao to set
	 */
	public void setTypeDao( ICosttypeDao typeDao )
	{
		this.typeDao = typeDao;
	}

	public void setiCosttypeDao( ICosttypeDao iCosttypeDao )
	{
		this.typeDao = iCosttypeDao;
	}
	
	/**
	 * 
	 * @Description:通过pid获取费用类型
	 * @param id
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年11月17日 下午4:21:55
	 */
	public List< Costtype > getTypesById( int id )
	{
		String hql = "from Costtype where pId=" + id;
		return typeDao.getAllObjectOrder( hql );
	}
	
	public Costtype get( int id )
	{
		return typeDao.get( id );
	}
	
	public List< Costtype > getAllObjectOrder( String hql )
	{
		return ( List< Costtype > ) typeDao.getAllObjectOrder( hql );
	}

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @return 
	 * @author liuwu
	 * @create_date 2015年12月9日 下午4:21:15
	 */ 
    public List< TrafficTools > getAllTrafficTools()
    {
    	String hql = "from TrafficTools where nEnable = 0";
	    return iTrafficToolsDao.findByHql(hql);
    }

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @return 
	 * @author liuwu
	 * @create_date 2015年12月9日 下午4:28:15
	 */ 
    public List< PayTypes > getAllPayTypes()
    {
	    String hql = "from  PayTypes where nEnable = 0 ";
	    return iPayTypesDao.findByHql(hql);
    }
	
    
}
