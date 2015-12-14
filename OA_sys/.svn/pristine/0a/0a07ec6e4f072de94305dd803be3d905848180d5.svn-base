package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.ISmsmsinfoDao;
import clt.com.cn.model.entity.Smsmsinfo;

public class SmsmsinfoDao extends BaseDao implements ISmsmsinfoDao
{
	
	public Smsmsinfo get( int id )
	{
		
		return ( Smsmsinfo ) super.getObjectById( Smsmsinfo.class , id );
	}
	
	public void save( Smsmsinfo entity )
	{
		super.addObject( entity );
	}
	
	public void delete( Smsmsinfo entity )
	{
		
		super.deleteObject( entity );
	}
	
	public List< Smsmsinfo > findByProperties( String hql , Object ... p )
	{
		return super.getUsersByCondition( hql , p );
	}
	
}
