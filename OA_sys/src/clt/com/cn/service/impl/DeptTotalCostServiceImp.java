package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IDeptTotalCostDao;
import clt.com.cn.model.entity.DeptTotalcost;
import clt.com.cn.service.IDeptTotalCostService;

public class DeptTotalCostServiceImp implements IDeptTotalCostService
{
	private IDeptTotalCostDao totalDao;
	
	public void setiDeptTotalCostDao( IDeptTotalCostDao iDeptTotalCostDao )
	{
		this.totalDao = iDeptTotalCostDao;
	}
	
	public void save( DeptTotalcost entity )
	{
		totalDao.save( entity );
	}
	
	public void update( DeptTotalcost entity )
	{
		totalDao.update( entity );
	}
	
	public List< DeptTotalcost > pageQuery( String hql , Integer pageSize , Integer page ,
	        Object ... p )
	{
		return totalDao.pageQuery( hql , pageSize , page , p );
	}
	
	public int getCountByHql( String hql )
	{
		return totalDao.getCountByHql( hql );
	}
	
	public int getpages( int count , int pageSize )
	{
		return totalDao.getpages( count , pageSize );
	}
	
	public DeptTotalcost get( int id )
	{
		return totalDao.get( id );
	}
	
	public void merge( DeptTotalcost entity )
	{
		totalDao.merge( entity );
	}
	
}
