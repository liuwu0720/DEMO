package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.DeptTotalcost;

public interface IDeptTotalCostService
{
	public DeptTotalcost get( int id );
	
	public void save( DeptTotalcost entity );
	
	public void update( DeptTotalcost entity );
	
	public void merge( DeptTotalcost entity );
	
	public List< DeptTotalcost > pageQuery( final String hql , final Integer pageSize ,
	        final Integer page , final Object ... p );
	
	public int getCountByHql( final String hql );
	
	public int getpages( int count , int pageSize );
}
