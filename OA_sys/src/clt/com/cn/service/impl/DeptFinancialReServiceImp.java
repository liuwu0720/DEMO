package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IDeptFinanciaReDao;
import clt.com.cn.model.entity.DeptFinanciaRe;
import clt.com.cn.service.IDeptFinancialReService;

public class DeptFinancialReServiceImp implements IDeptFinancialReService
{
	private IDeptFinanciaReDao financialDao;
	
	public void setiDeptFinanciaReDao( IDeptFinanciaReDao iDeptFinanciaReDao )
	{
		this.financialDao = iDeptFinanciaReDao;
	}
	
	public DeptFinanciaRe get( int id )
	{
		return financialDao.get( id );
	}
	
	public void save( DeptFinanciaRe entity )
	{
		financialDao.save( entity );
	}
	
	public void update( DeptFinanciaRe entity )
	{
		financialDao.update( entity );
	}
	
	public void merge( DeptFinanciaRe entity )
	{
		financialDao.merge( entity );
	}
	
	public List pageQuery( String hql , Integer pageSize , Integer page , Object ... p )
	{
		return ( List< DeptFinanciaRe > ) financialDao.pageQuery( hql , pageSize , page ,
		        p );
	}
	
	public List< DeptFinanciaRe > getAllObjectOrder( String hql )
	{
		return ( List< DeptFinanciaRe > ) financialDao.getAllObjectOrder( hql );
	}
	
	public int getCountByHql( String hql )
	{
		return financialDao.getCountByHql( hql );
	}
	
	public int getpages( int count , int pageSize )
	{
		return financialDao.getpages( count , pageSize );
	}
	
}
