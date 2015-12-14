package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IMailRecordDao;
import clt.com.cn.model.entity.MailRecord;
import clt.com.cn.service.IMailRecordService;

public class MailRecordService implements IMailRecordService
{
	private IMailRecordDao mailRecordDao;
	
	public void setMailRecordDao( IMailRecordDao mailRecordDao )
	{
		this.mailRecordDao = mailRecordDao;
	}
	
	public void delete( int id )
	{
		mailRecordDao.delete( id );
	}
	
	public MailRecord get( int id )
	{
		
		return mailRecordDao.get( id );
	}
	
	public void save( MailRecord entity )
	{
		mailRecordDao.save( entity );
	}
	
	public void update( MailRecord entity )
	{
		mailRecordDao.update( entity );
	}
	
	public List< MailRecord > getByProperties( String hql , Object ... p )
	{
		return mailRecordDao.getByProperties( hql , p );
	}
	
}
