package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IEmployovertimeDao;
import clt.com.cn.model.entity.Employovertime;
import clt.com.cn.model.entity.EmployovertimeType;

public class EmployovertimeDao extends BaseDao implements IEmployovertimeDao
{
	
	public void delEmrById( int id )
	{
		// TODO Auto-generated method stub
		super.deleteObjectById( Employovertime.class , id );
	}
	
	public void addEmr( Employovertime emr )
	{
		// TODO Auto-generated method stub
		super.addObject( emr );
	}
	
	public void updateEmr( Employovertime emr )
	{
		// TODO Auto-generated method stub
		super.updateObject( emr );
	}
	
	public Employovertime getEmployovertimeById( int id )
	{
		// TODO Auto-generated method stub
		return ( Employovertime ) super.getObjectById( Employovertime.class , id );
	}
	
	public List getDateBySqlQuery( String sql , int pageSize , int page )
	{
		// TODO Auto-generated method stub
		return super.getDateBySqlQuery( sql , pageSize , page );
	}
	
	public List getpageDateBySqlQuery( String sql , int page , int pageSize )
	{
		// TODO Auto-generated method stub
		return super.getpageDateBySqlQuery( sql , page , pageSize );
	}
	
	@SuppressWarnings( "unchecked" )
	public List< Employovertime > getObjInfoByCondition( String hql , Object ... p )
	{
		return super.getUsersByCondition( hql , p );
	}
	
	public List< EmployovertimeType > getAllEmployovertimeType()
	{
		return super.getAllObject( EmployovertimeType.class );
	}
	
	public EmployovertimeType getEmployovertimeType( int lineid )
	{
		// TODO Auto-generated method stub
		return ( EmployovertimeType ) super.getObjectById( EmployovertimeType.class ,
		        lineid );
	}
	
	public int getCountBySQL( String sql )
	{
		// TODO Auto-generated method stub
		return super.getCountBySql( sql );
	}
	
	public int getCountByHql( String hql )
	{
		// TODO Auto-generated method stub
		return super.getCountByHql( hql );
	}
	
	public List pageSqlQuery( String hql , Integer pageSize , Integer page , Object ... p )
	{
		return super.pageQuery( hql , pageSize , page , p );
	}
}
