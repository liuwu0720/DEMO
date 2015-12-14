package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.Employholiday;
import clt.com.cn.model.entity.EmployholidayType;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Smuser;

public interface IEmployholidayService
{
	public abstract List< Employholiday > getAllEhd();
	
	public abstract void delEhdById( int id );
	
	public abstract void addEhd( Employholiday ehd );
	
	// 根据Uname查询相关信息
	public abstract List< Smuser > getUserByName( String name );
	
	// 根据Uname查询员工档案相关信息
	public abstract List< Employrecord > getEmrByName( String name );
	
	// 根据ID查询详细信息
	public abstract List getEhdById( int id );
	
	public Employholiday get( int lineid );
	
	public abstract void updateEhd( Employholiday ehd );
	
	// 根据状态ID查询信息
	public abstract List< Employholiday > getEhdByIscheck( int ischeck );
	
	// 根据状态ID查询个人信息
	public abstract List< Employholiday > getEhdByIscheck( int ischeck , int lineid );
	
	// 请假历史记录
	public abstract List< Employholiday > getEhdByChecked( int lineid );
	
	// 查询部门所有的请假信息
	public abstract List getEhdBydept( int deptid , int page );
	
	// 查询员工部门id
	public abstract Employrecord getEmrBylineid( int recordid );
	
	public abstract int getCount( int deptid );
	
	public abstract int getCountByIs( int ischeck );
	
	// 总页数
	public abstract int getpages( int count , int pageSize );
	
	// 根据条件查询(分页)
	public abstract List getEhdByIs( int ischeck , int page );
	
	public int getempCountBySatusanduid( Class cls , int status , int uid );
	
	public List getempBySatusanduid( Class cls , int status , int uid );
	
	public int getCountBySQL( String sql );
	
	public List getDateBySqlQuery( String sql , int pageSize , int page );
	
	public int getEhdCountByIscheck( int ischeck , int lineid );
	
	public List< EmployholidayType > getAllEmpholidayType();
	
	public EmployholidayType getEmpholidayTypeByID( int lineid );
	
	public List< Employholiday > getEhdInfo( String hql , Object ... p );
	
	public List getpageDateBySqlQuery( final String sql , final int page ,
	        final int pageSize );
	
	public int getCountByHql( String hql );
	
	public List pageSqlQuery( String hql , Integer pageSize , Integer page , Object ... p );
}
