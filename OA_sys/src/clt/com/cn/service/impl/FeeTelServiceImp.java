package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IFeeTelDao;
import clt.com.cn.model.entity.FeeTel;
import clt.com.cn.service.IFeeTelService;

public class FeeTelServiceImp implements IFeeTelService
{
	private IFeeTelDao telDao;
	
	public void setiFeeTelDao( IFeeTelDao iFeeTelDao )
	{
		this.telDao = iFeeTelDao;
	}
	
	public void save( FeeTel entity )
	{
		telDao.save( entity );
	}
	
	public void update( FeeTel entity )
	{
		telDao.update( entity );
	}
	
	public void merge( FeeTel entity )
	{
		telDao.merge( entity );
	}
	
	public List< FeeTel > getAllObjectOrder( String hql )
	{
		return ( List< FeeTel > ) telDao.getAllObjectOrder( hql );
	}
	
}
