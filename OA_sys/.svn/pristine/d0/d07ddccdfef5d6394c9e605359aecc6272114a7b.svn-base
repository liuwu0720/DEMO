package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IMailRecordDao;
import clt.com.cn.model.entity.MailRecord;

public class MailRecordDao extends BaseDao implements IMailRecordDao
{
	
	public void delete( int id )
	{
		super.deleteObjectById( MailRecord.class , id );
		
	}
	
	public MailRecord get( int id )
	{
		return ( MailRecord ) super.getObjectById( MailRecord.class , id );
		
	}
	
	public void save( MailRecord entity )
	{
		super.addObject( entity );
	}
	
	public void update( MailRecord entity )
	{
		super.updateObject( entity );
	}
	
	public List< MailRecord > getByProperties( String hql , Object ... p )
	{
		return super.getUsersByCondition( hql , p );
	}
	
}
