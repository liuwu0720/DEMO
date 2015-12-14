package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IEmployovertimeDao;
import clt.com.cn.model.entity.Employovertime;
import clt.com.cn.model.entity.EmployovertimeType;
import clt.com.cn.service.IEmployovertimeService;

public class EmployovertimeServiceImp implements IEmployovertimeService
{
	
	private IEmployovertimeDao empovertimeDao;
	
	public void setEmpovertimeDao( IEmployovertimeDao empovertimeDao )
	{
		this.empovertimeDao = empovertimeDao;
	}
	
	public void delEmrById( int id )
	{
		// TODO Auto-generated method stub
		empovertimeDao.delEmrById( id );
	}
	
	public void addEmr( Employovertime emr )
	{
		// TODO Auto-generated method stub
		empovertimeDao.addEmr( emr );
	}
	
	public void updateEmr( Employovertime emr )
	{
		// TODO Auto-generated method stub
		empovertimeDao.updateEmr( emr );
	}
	
	public Employovertime getEmployovertimeById( int id )
	{
		// TODO Auto-generated method stub
		return empovertimeDao.getEmployovertimeById( id );
	}
	
	public int getpages( int count , int pageSize )
	{
		// TODO Auto-generated method stub
		return empovertimeDao.getpages( count , pageSize );
	}
	
	public int getCountBySQL( String sql )
	{
		// TODO Auto-generated method stub
		return empovertimeDao.getCountBySQL( sql );
	}
	
	public List getDateBySqlQuery( String sql , int pageSize , int page )
	{
		// TODO Auto-generated method stub
		return empovertimeDao.getDateBySqlQuery( sql , pageSize , page );
	}
	
	public List getpageDateBySqlQuery( String sql , int page , int pageSize )
	{
		// TODO Auto-generated method stub
		return empovertimeDao.getpageDateBySqlQuery( sql , page , pageSize );
	}
	
	public int getEhdCountByIscheck( int ischeck , int lineid )
	{
		String sql = " select count(*) from Employholiday ehd where ehd.ischeck="
		        + ischeck + " and ehd.userid=" + lineid;
		int count = empovertimeDao.getCountBySQL( sql );
		return count;
	}
	
	public List< Employovertime > getObjInfoByCondition( String hql , Object ... p )
	{
		// TODO Auto-generated method stub
		List< Employovertime > ulist = empovertimeDao.getObjInfoByCondition( hql , p );
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
		
	}
	
	public List< EmployovertimeType > getAllEmployovertimeType()
	{
		// TODO Auto-generated method stub
		return empovertimeDao.getAllEmployovertimeType();
	}
	
	public EmployovertimeType getEmployovertimeType( int lineid )
	{
		// TODO Auto-generated method stub
		return empovertimeDao.getEmployovertimeType( lineid );
	}
	
	public int getCountByHql( String hql )
	{
		return empovertimeDao.getCountByHql( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @param pageSize
	 * @param page
	 * @param p
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-25 上午10:58:45
	 */
	public List pageSqlQuery( String hql , Integer pageSize , Integer page , Object ... p )
	{
		// TODO Auto-generated method stub
		return empovertimeDao.pageSqlQuery( hql , pageSize , page , p );
	}
	
}
