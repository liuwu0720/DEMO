package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.ICostBudgetDao;
import clt.com.cn.model.entity.CostBudget;
import clt.com.cn.service.ICostBudgetService;

public class CostBudgetServiceImp implements ICostBudgetService
{
	private ICostBudgetDao budgetDao;
	
	public void setiCostBudgetDao( ICostBudgetDao iCostBudgetDao )
	{
		budgetDao = iCostBudgetDao;
	}
	
	public void save( CostBudget entity )
	{
		budgetDao.save( entity );
	}
	
	public void update( CostBudget entity )
	{
		budgetDao.update( entity );
	}
	
	public List< CostBudget > pageQuery( String hql , Integer pageSize , Integer page ,
	        Object ... p )
	{
		return budgetDao.pageQuery( hql , pageSize , page , p );
	}
	
	public int getCountByHql( String hql )
	{
		return budgetDao.getCountByHql( hql );
	}
	
	public int getpages( int count , int pageSize )
	{
		return budgetDao.getpages( count , pageSize );
	}
	
	public CostBudget get( int id )
	{
		return budgetDao.get( id );
	}
	
	public void merge( CostBudget entity )
	{
		budgetDao.merge( entity );
	}
	
	public List getpageDateBySqlQuery( String sql , int page , int pageSize )
	{
		return budgetDao.getpageDateBySqlQuery( sql , page , pageSize );
	}
	
}
