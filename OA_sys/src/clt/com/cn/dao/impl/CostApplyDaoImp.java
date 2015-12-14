package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.ICostApplyDao;
import clt.com.cn.model.entity.CostApply;
import clt.com.cn.model.entity.CostApplyitem;

/** 
 * @Package clt.com.cn.dao.impl 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年11月16日 下午2:29:40 
 * @version V1.0 
 */
public class CostApplyDaoImp extends BaseDao implements ICostApplyDao
{
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param sql
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月16日 下午3:05:05
	 */
	public List findAllByPage( String sql )
	{
		// TODO Auto-generated method stub
		return super.getDateBySqlQuery( sql , 5 , 1 );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param costApply 
	 * @author liuwu
	 * @create_date 2015年11月16日 下午3:59:38
	 */
	public void save( CostApply costApply )
	{
		// TODO Auto-generated method stub
		super.addObject( costApply );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param feeId
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月17日 下午4:16:42
	 */
	public CostApply getById( int feeId )
	{
		// TODO Auto-generated method stub
		return ( CostApply ) super.getObjectById( CostApply.class , feeId );
	}
	
	/**
	 * 
	 * @Description:分页查询
	 * @param hql
	 * @param pageSize
	 * @param page
	 * @param p
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年11月17日 上午11:54:09
	 */
	public List< CostApply > pageQuery( String hql , Integer pageSize , Integer page ,
	        Object ... p )
	{
		
		return ( List< CostApply > ) super.pageQuery( hql , pageSize , page , p );
	}
	
	/**
	 * 
	 * @Description:统计条数
	 * @param hql
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年11月17日 下午1:46:34
	 */
	public int getCountByHql( final String hql )
	{
		return super.getCountByHql( hql );
	}
	
	/**
	 * 
	 * @Description:获取页数
	 * @param count
	 * @param pageSize
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年11月17日 下午1:55:06
	 */
	public int getpages( int count , int pageSize )
	{
		return super.getpages( count , pageSize );
	}
	
	public CostApply get( int id )
	{
		return ( CostApply ) super.getObjectById( CostApply.class , id );
	}
	
	public void update( CostApply entity )
	{
		super.updateObject( entity );
	}
	
	public void merge( CostApply entity )
	{
		super.mergeObject( entity );
	}
	
	public int getCountBySql( String countSQL )
	{
		return super.getCountBySql( countSQL );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param sql
	 * @param pageSize
	 * @param page
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月26日 上午11:37:16
	 */
	public List getDataBySqlQuery( String sql , int pageSize , int page )
	{
		// TODO Auto-generated method stub
		return super.getDateBySqlQuery( sql , pageSize , page );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param costApplyitems 
	 * @author liuwu
	 * @create_date 2015年12月3日 下午4:34:20
	 */
	public void deleteAll( List< CostApplyitem > costApplyitems )
	{
		// TODO Auto-generated method stub
		for ( CostApplyitem costApplyitem : costApplyitems )
		{
			super.deleteObject( costApplyitem );
		}
		
	}
}
