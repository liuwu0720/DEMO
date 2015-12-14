package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IDeptLevelDao;
import clt.com.cn.model.entity.DeptLevel;
import clt.com.cn.service.IDeptLevelService;

public class DeptLevelServiceImp implements IDeptLevelService
{
	private IDeptLevelDao deptLevelDao;
	
	public void setiDeptLevelDao( IDeptLevelDao iDeptLevelDao )
	{
		this.deptLevelDao = iDeptLevelDao;
	}
	
	public void save( DeptLevel entity )
	{
		deptLevelDao.save( entity );
	}
	
	public void update( DeptLevel entity )
	{
		deptLevelDao.update( entity );
	}
	
	public DeptLevel get( int id )
	{
		return deptLevelDao.get( id );
	}
	
	public List< DeptLevel > pageQuery( String hql , Integer pageSize , Integer page ,
	        Object ... p )
	{
		return deptLevelDao.pageQuery( hql , pageSize , page , p );
	}
	
	public int getCountByHql( String hql )
	{
		return deptLevelDao.getCountByHql( hql );
	}
	
	public int getpages( int count , int pageSize )
	{
		return deptLevelDao.getpages( count , pageSize );
	}
	
	public void merge( DeptLevel entity )
	{
		deptLevelDao.merge( entity );
	}
	
}
