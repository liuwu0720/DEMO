package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.CostBudget;

public interface ICostBudgetService
{
	public CostBudget get( int id );
	
	public void save( CostBudget entity );
	
	public void update( CostBudget entity );
	
	public void merge( CostBudget entity );
	
	public List< CostBudget > pageQuery( final String hql , final Integer pageSize ,
	        final Integer page , final Object ... p );
	
	public int getCountByHql( final String hql );
	
	public int getpages( int count , int pageSize );
	
	public List getpageDateBySqlQuery( final String sql , final int page ,
	        final int pageSize );
}
