package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IPositionDao;
import clt.com.cn.model.entity.Position;

public class PositionDao extends BaseDao implements IPositionDao
{
	public List< Position > getAllPosition()
	{
		return super.getAllObject( Position.class );
	}
	
	public void addPosition( Position p )
	{
		super.addObject( p );
	}
	
	public void delPosition( int id )
	{
		super.deleteObjectById( Position.class , id );
	}
	
	public Position getPositionById( int id )
	{
		return ( Position ) super.getObjectById( Position.class , id );
	}
	
	public void updatePosition( Position po )
	{
		super.updateObject( po );
	}
	
	// 根据条件查询
	public List< Position > getPositionInfo( String hql , Object ... p )
	{
		return super.getUsersByCondition( hql , p );
	}
	
	public int getCount()
	{
		String hql = "select count(*) from Position";
		List list = super.getUsersByCondition( hql );
		int count = Integer.parseInt( list.get( 0 ).toString() );
		return count;
	}
	
	public int getpages( int count , int pageSize )
	{
		int totalpages = 0;
		try
		{
			totalpages = ( count % pageSize == 0 ) ? ( count / pageSize ) : ( count
			        / pageSize + 1 );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return totalpages;
	}
	
	public List< Position > getAllPosition( int page )
	{
		String hql = "from Position po where po.status >=0 order by po.lineid desc ";
		return super.pageQuery( hql , 5 , page );
	}
	
	public int getCountByPositionName( String positionName )
	{
		String hql = "select count(*) from Position p where p.positionname like '%"
		        + positionName + "%'";
		List list = super.getUsersByCondition( hql );
		int count = Integer.parseInt( list.get( 0 ).toString() );
		return count;
	}
	
	public List getPositionByPositionName( String hql , String positionName , int page )
	{
		return super.pageQuery( hql , 5 , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-30 下午3:42:31
	 */
	public int getCountBySQL( String sql )
	{
		// TODO Auto-generated method stub
		return super.getCountBySql( sql );
	}
	
	public List getDateBySqlQuery( String sql , int pageSize , int page )
	{
		// TODO Auto-generated method stub
		return super.getDateBySqlQuery( sql , pageSize , page );
	}
	
}
