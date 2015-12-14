package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IDeptFinanciaReDao;
import clt.com.cn.model.entity.DeptFinanciaRe;

/** 
 * @Package clt.com.cn.dao.impl 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年11月24日 上午11:34:05 
 * @version V1.0 
 */
public class DeptFinanciaReDaoImp extends BaseDao implements IDeptFinanciaReDao
{
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param hql3
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月24日 上午11:37:06
	 */
	public List< DeptFinanciaRe > findByHql( String hql3 )
	{
		return super.getAllObjectOrder( hql3 );
	}
	
	public DeptFinanciaRe get( int id )
	{
		return ( DeptFinanciaRe ) super.getObjectById( DeptFinanciaRe.class , id );
	}
	
	public void save( DeptFinanciaRe entity )
	{
		super.addObject( entity );
	}
	
	public void update( DeptFinanciaRe entity )
	{
		super.updateObject( entity );
	}
	
	public void merge( DeptFinanciaRe entity )
	{
		super.mergeObject( entity );
	}
	
	// 分页查询
	public List pageQuery( final String hql , final Integer pageSize ,
	        final Integer page , final Object ... p )
	{
		return super.pageQuery( hql , pageSize , page , p );
	}
	
	// 查询
	public List getAllObjectOrder( String hql )
	{
		return super.getAllObjectOrder( hql );
	}
	
	public int getCountByHql( final String hql )
	{
		return super.getCountByHql( hql );
	}
	
	public int getpages( int count , int pageSize )
	{
		return super.getpages( count , pageSize );
	}
}
