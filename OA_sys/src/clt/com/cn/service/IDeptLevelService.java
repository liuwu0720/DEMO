package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.DeptLevel;

public interface IDeptLevelService
{
	public void save( DeptLevel entity );
	
	public void update( DeptLevel entity );
	
	public void merge( DeptLevel entity );
	
	public DeptLevel get( int id );
	
	public List< DeptLevel > pageQuery( final String hql , final Integer pageSize ,
	        final Integer page , final Object ... p );
	
	public int getCountByHql( final String hql );
	
	public int getpages( int count , int pageSize );
}
