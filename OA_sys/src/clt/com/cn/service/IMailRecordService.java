package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.MailRecord;

public interface IMailRecordService
{
	public void save( MailRecord entity );
	
	public void update( MailRecord entity );
	
	public void delete( int id );
	
	public MailRecord get( int id );
	
	public List< MailRecord > getByProperties( String hql , Object ... p );
}
