package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.Smsmsinfo;

public interface ISmsmsinfoDao
{
	public void save( Smsmsinfo entity );
	
	public Smsmsinfo get( int id );
	
	public void delete( Smsmsinfo entity );
	
	public List< Smsmsinfo > findByProperties( String hql , Object ... p );
}
