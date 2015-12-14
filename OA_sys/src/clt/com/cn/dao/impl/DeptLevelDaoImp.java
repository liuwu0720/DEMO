package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IDeptLevelDao;
import clt.com.cn.model.entity.DeptLevel;

public class DeptLevelDaoImp extends BaseDao implements IDeptLevelDao
{
	
	public void save( DeptLevel entity )
	{
		super.addObject( entity );
	}
	
	public void update( DeptLevel entity )
	{
		super.updateObject( entity );
	}
	
	public DeptLevel get( int id )
	{
		return ( DeptLevel ) super.getObjectById( DeptLevel.class , id );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param lineid
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月19日 上午11:25:44
	 */
	@SuppressWarnings( "unchecked" )
	public List< DeptLevel > getByDept( int lineid )
	{
		String hql = "from DeptLevel where iDept = " + lineid + " order by nLeveal asc";
		return super.getAllObjectOrder( hql );
	}
	
	public List< DeptLevel > pageQuery( final String hql , final Integer pageSize ,
	        final Integer page , final Object ... p )
	{
		return ( List< DeptLevel > ) super.pageQuery( hql , pageSize , page , p );
	}
	
	public int getCountByHql( String hql )
	{
		return super.getCountByHql( hql );
	}
	
	public int getpages( int count , int pageSize )
	{
		return super.getpages( count , pageSize );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param hql2
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月24日 上午10:12:13
	 */
	public List< DeptLevel > findByHql( String hql2 )
	{
		// TODO Auto-generated method stub
		return super.getAllObjectOrder( hql2 );
	}
	
	public void merge( DeptLevel entity )
	{
		super.mergeObject( entity );
	}
	
}
