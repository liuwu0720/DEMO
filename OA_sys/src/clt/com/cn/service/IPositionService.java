package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.Position;

public interface IPositionService
{
	// 查询所有的信息
	public abstract List< Position > getAllPosition();
	
	public abstract void addPosition( Position p );
	
	public abstract void delPosition( int id );
	
	public abstract Position getPositionById( int id );
	
	public abstract void updatePosition( Position po );
	
	// 通过职位名称查询
	public abstract List< Position > PositionInfoByName( String po );
	
	// 得到总条数
	public abstract int getCount();
	
	// 得到总页数
	public abstract int getpages( int count , int pageSize );
	
	// 查询所有的信息（分页）
	public abstract List< Position > getAllPosition( int page );
	
	public int getCountByPositionName( String positionName );
	
	public List getPositionByPositionName( String positionName , int page );
	
	public int getCountBySQL( String sql );
	
	public List getDateBySqlQuery( String sql , int pageSize , int page );
	
	public List< Position > getPositionInfo( String hql , Object ... p );
}
