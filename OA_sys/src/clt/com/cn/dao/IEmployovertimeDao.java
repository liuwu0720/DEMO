package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.Employovertime;
import clt.com.cn.model.entity.EmployovertimeType;

public interface IEmployovertimeDao
{
	public void delEmrById( int id );
	
	public void addEmr( Employovertime emr );
	
	public void updateEmr( Employovertime emr );
	
	public Employovertime getEmployovertimeById( int id );
	
	public int getpages( int count , int pageSize );
	
	public int getCountBySQL( String sql );
	
	public int getCountByHql( final String hql );
	
	public List getDateBySqlQuery( String sql , int pageSize , int page );
	
	public List getpageDateBySqlQuery( final String sql , final int page ,
	        final int pageSize );
	
	public List< Employovertime > getObjInfoByCondition( String hql , Object ... p );
	
	public List< EmployovertimeType > getAllEmployovertimeType();
	
	EmployovertimeType getEmployovertimeType( int lineid );
	
	public List pageSqlQuery( String hql , Integer pageSize , Integer page , Object ... p );
}
