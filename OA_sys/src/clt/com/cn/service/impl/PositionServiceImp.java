package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IPositionDao;
import clt.com.cn.model.entity.Position;
import clt.com.cn.service.IPositionService;

public class PositionServiceImp implements IPositionService
{
	private IPositionDao positionDao;
	
	public void setPositionDao( IPositionDao positionDao )
	{
		this.positionDao = positionDao;
	}
	
	public List< Position > getAllPosition()
	{
		return positionDao.getAllPosition();
	}
	
	public void addPosition( Position p )
	{
		positionDao.addPosition( p );
	}
	
	public void delPosition( int id )
	{
		positionDao.delPosition( id );
	}
	
	public Position getPositionById( int id )
	{
		return positionDao.getPositionById( id );
	}
	
	public void updatePosition( Position po )
	{
		positionDao.updatePosition( po );
	}
	
	public List< Position > PositionInfoByName( String po )
	{
		String hql = "from Position po where po.positionname LIKE '%" + po + "%'";
		List< Position > ulist = positionDao.getPositionInfo( hql );
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	
	public int getCount()
	{
		// 总条数
		return positionDao.getCount();
	}
	
	public int getpages( int count , int pageSize )
	{
		// 总页数
		return positionDao.getpages( count , pageSize );
	}
	
	// 分页查询
	public List< Position > getAllPosition( int page )
	{
		return positionDao.getAllPosition( page );
	}
	
	public int getCountByPositionName( String positionName )
	{
		// TODO Auto-generated method stub
		return positionDao.getCountByPositionName( positionName );
	}
	
	public List getPositionByPositionName( String positionName , int page )
	{
		// TODO Auto-generated method stub
		String hql = " from Position p where p.positionname like '%" + positionName
		        + "%' order by positionname desc";
		return positionDao.getPositionByPositionName( hql , positionName , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-30 下午3:41:20
	 */
	public int getCountBySQL( String sql )
	{
		// TODO Auto-generated method stub
		return positionDao.getCountBySQL( sql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param pageSize
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-30 下午3:51:31
	 */
	public List getDateBySqlQuery( String sql , int pageSize , int page )
	{
		// TODO Auto-generated method stub
		return positionDao.getDateBySqlQuery( sql , pageSize , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @param p
	 * @return
	 * @author chenbin
	 * @create_date 2014-11-3 上午9:33:29
	 */
	public List< Position > getPositionInfo( String hql , Object ... p )
	{
		// TODO Auto-generated method stub
		return positionDao.getUsersByCondition( hql , p );
	}
}
