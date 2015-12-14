package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.MailRecord;

public interface IMailRecordDao
{
	public void save( MailRecord entity );
	
	public void update( MailRecord entity );
	
	public void delete( int id );
	
	public MailRecord get( int id );
	
	public List< MailRecord > getByProperties( String hql , Object ... p );
}
