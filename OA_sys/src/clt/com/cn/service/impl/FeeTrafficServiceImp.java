package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IFeeTrafficDao;
import clt.com.cn.model.entity.FeeTraffic;
import clt.com.cn.service.IFeeTrafficService;

public class FeeTrafficServiceImp implements IFeeTrafficService
{
	private IFeeTrafficDao trafficDao;
	
	public void setiFeeTrafficDao( IFeeTrafficDao iFeeTrafficDao )
	{
		this.trafficDao = iFeeTrafficDao;
	}
	
	public void save( FeeTraffic entity )
	{
		trafficDao.save( entity );
	}
	
	public void update( FeeTraffic entity )
	{
		trafficDao.update( entity );
	}
	
	public void merge( FeeTraffic entity )
	{
		trafficDao.merge( entity );
	}
	
	// 查询
	public List< FeeTraffic > getAllObjectOrder( String hql )
	{
		return trafficDao.getAllObjectOrder( hql );
	}
	
}
