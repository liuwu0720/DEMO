package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.ISmsmsinfoDao;
import clt.com.cn.model.entity.Smsmsinfo;
import clt.com.cn.service.ISmsmsinfoService;

public class SmsmsinfoService implements ISmsmsinfoService
{
	private ISmsmsinfoDao sinfoDao;
	
	public void setSinfoDao( ISmsmsinfoDao sinfoDao )
	{
		this.sinfoDao = sinfoDao;
	}
	
	public void delete( Smsmsinfo entity )
	{
		sinfoDao.delete( entity );
		
	}
	
	public Smsmsinfo get( int id )
	{
		return sinfoDao.get( id );
	}
	
	public void save( Smsmsinfo entity )
	{
		sinfoDao.save( entity );
	}
	
	public List< Smsmsinfo > findByProperties( String hql , Object ... p )
	{
		return sinfoDao.findByProperties( hql , p );
	}
	
}
