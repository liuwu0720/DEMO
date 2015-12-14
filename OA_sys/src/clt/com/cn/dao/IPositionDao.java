package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.Position;

public interface IPositionDao
{
	public abstract List< Position > getAllPosition();
	
	public abstract void addPosition( Position p );
	
	public abstract void delPosition( int id );
	
	public abstract Position getPositionById( int id );
	
	public abstract void updatePosition( Position po );
	
	// 根据条件查询
	public abstract List< Position > getPositionInfo( String hql , Object ... p );
	
	// 总条数
	public abstract int getCount();
	
	// 总页数
	public abstract int getpages( int count , int pageSize );
	
	// 查询所有的职位信息（分页）
	public abstract List< Position > getAllPosition( int page );
	
	// 根据职位名称查询条数
	public int getCountByPositionName( String positionName );
	
	public List getPositionByPositionName( String hql , String positionName , int page );
	
	public int getCountBySQL( String sql );
	
	public List getDateBySqlQuery( String sql , int pageSize , int page );
	
	public List getUsersByCondition( String hql , Object ... p );
}
