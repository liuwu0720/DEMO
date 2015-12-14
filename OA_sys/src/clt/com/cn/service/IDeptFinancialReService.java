package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.DeptFinanciaRe;

public interface IDeptFinancialReService
{
	public DeptFinanciaRe get( int id );
	
	public void save( DeptFinanciaRe entity );
	
	public void update( DeptFinanciaRe entity );
	
	public void merge( DeptFinanciaRe entity );
	
	// 分页查询
	public List pageQuery( final String hql , final Integer pageSize ,
	        final Integer page , final Object ... p );
	
	// 查询
	public List getAllObjectOrder( String hql );
	
	public int getCountByHql( final String hql );
	
	public int getpages( int count , int pageSize );
	
}
