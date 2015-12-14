package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Role;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.model.entity.UserApprove;
import clt.com.cn.model.entity.UserSchedule;

public interface IUserDao
{
	
	public abstract void addUser( Smuser u );
	
	public void saveUserSchedule( UserSchedule u );
	
	public void saveUserApprove( UserApprove us );
	
	public Smuser getUserApprove( Smuser sm );
	
	public abstract String MD5( String s );
	
	// 查询档案信息
	public abstract Employrecord getEmrById( int id );
	
	public abstract List< Smuser > getUsersByCondition( String hql , Object ... p );
	
	public abstract List getAllUsers( String hql , int page , Object ... p );
	
	public abstract List< Smuser > checkUser( String hql , Object ... p );
	
	public abstract Smuser getUserById( int uid );
	
	public abstract void updateUser( Smuser u );
	
	public abstract void deleteUserById( int uid );
	
	// 根据条件查询
	public abstract List getUserInfo( String hql , int page , Object ... p );
	
	// 总条数
	public abstract int getCount();
	
	public abstract int getCountByAdmin( int admin );
	
	public List< Smuser > getUsersByAdmin( int admin );
	
	public List< Smuser > getUsersBygtAdmin( int admin );
	
	public abstract int getCountByUserno( String userno );
	
	// 总页数
	public abstract int getpages( int count , int pageSize );
	
	// 查询角色
	public abstract List< Role > getRolename();
	
	public List getDateBySqlQuery( String sql , int pageSize , int page );
	
	public List getpageDateBySqlQuery( String sql , int page , int pageSize );
	
	public List< Smuser > getUserByUserno( String userno );
	
	public int getCountBySql( final String sql );
	
}