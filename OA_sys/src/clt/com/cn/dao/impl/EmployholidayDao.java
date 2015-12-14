package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IEmployholidayDao;
import clt.com.cn.model.entity.Employholiday;
import clt.com.cn.model.entity.EmployholidayType;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Smuser;

public class EmployholidayDao extends BaseDao implements IEmployholidayDao
{
	// 查询所有请假条
	public List< Employholiday > getAllEhd()
	{
		return super.getAllObject( Employholiday.class );
	}
	
	// 查询
	// 删除请假条
	public void delEhdById( int id )
	{
		super.deleteObjectById( Employholiday.class , id );
	}
	
	// 申请请假条
	public void addEhd( Employholiday ehd )
	{
		super.addObject( ehd );
	}
	
	public Employholiday get( int lineid )
	{
		return ( Employholiday ) super.getObjectById( Employholiday.class , lineid );
	}
	
	// 根据Uname查询其相关信息
	public List< Smuser > getUserByName( String name )
	{
		String hql = "from Smuser s where s.userno='" + name + "'";
		return super.getAllObjectOrder( hql );
	}
	
	public List< Employrecord > getEmrByName( String name )
	{
		String hql = "from Employrecord e where e.fileno='" + name + "'";
		return super.getAllObjectOrder( hql );
	}
	
	public List getEhdById( int id )
	{
		String sql = " select emp.lineid,e.fileno,e.employname,d.deptname,emp.reason,emp.date1,emp.date2,emp.currdate,emp.ischeck,emp.checkremarks from employholiday emp,dept d,employrecord e where emp.deptid=d.lineid and emp.recordid=e.lineid and emp.lineid="
		        + id;
		return super.getDateBySqlQuery( sql , 0 , 0 );
	}
	
	public void updateEhd( Employholiday ehd )
	{
		super.updateObject( ehd );
	}
	
	public List< Employholiday > getEhdInfo( String hql , Object ... p )
	{
		return super.getUsersByCondition( hql , p );
	}
	
	public List getEhdBydept( int deptid , int page )
	{
		String hql = "select ehd.lineid,ehd.userid,ehd.recordid,ehd.deptid,emr.employname,ehd.date1,ehd.date2,ehd.reason,ehd.ischeck"
		        + " from Employholiday as ehd,Employrecord as emr where ehd.recordid=emr.lineid and ehd.deptid="
		        + deptid;
		return super.pageQuery( hql , 5 , page );
	}
	
	public List< Employrecord > getEmrBylineid( String hql , Object ... p )
	{
		return super.getUsersByCondition( hql , p );
	}
	
	public int getCount( int deptid )
	{
		String hql = "select count(*) from Employholiday ehd where ehd.deptid=" + deptid;
		List list = super.getUsersByCondition( hql );
		int count = Integer.parseInt( list.get( 0 ).toString() );
		return count;
	}
	
	public int getCountByIs( int ischeck )
	{
		String hql = "select count(*) from Employholiday ehd where ehd.ischeck="
		        + ischeck;
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
	
	public List getEhdByIs( int ischeck , int page )
	{
		String hql = "select ehd.lineid,ehd.userid,ehd.recordid,ehd.deptid,emr.employname,ehd.date1,ehd.date2,ehd.reason,ehd.ischeck"
		        + " from Employholiday as ehd,Employrecord as emr where ehd.recordid=emr.lineid and ehd.ischeck="
		        + ischeck;
		return super.pageQuery( hql , 5 , page );
	}
	
	public List getempBySatusanduid( Class cls , int status , int uid )
	{
		String sql = "";
		if ( cls.getSimpleName().equals( "Employholiday" ) )
		{
			sql = "select emp.lineid,e.fileno,e.employname,d.deptname,emp.reason,emp.date1,emp.date2,emp.days,emp.ischeck,emp.filepaths "
			        + " from "
			        + cls.getSimpleName()
			        + " emp,dept d,employrecord e where emp.deptid=d.lineid and emp.recordid=e.lineid and emp.ischeck="
			        + status
			        + " and (emp.checkuserid="
			        + uid
			        + " or emp.checkuserid in(select empdit.certigieruid from employaccredit empdit where empdit.authorizeruid="
			        + uid + " )) ";
			
		}
		else
		{
			sql = "select emp.lineid,e.fileno,e.employname,d.deptname,emp.reason,emp.date1,emp.date2,emp.ischeck "
			        + " from "
			        + cls.getSimpleName()
			        + " emp,dept d,employrecord e where emp.deptid=d.lineid and emp.recordid=e.lineid and emp.ischeck="
			        + status
			        + " and (emp.checkuserid="
			        + uid
			        + " or emp.checkuserid in(select empdit.certigieruid from employaccredit empdit where empdit.authorizeruid="
			        + uid + " )) ";
		}
		System.out.println( "sql>>>" + sql );
		return super.getDateBySqlQuery( sql , 0 , 0 );
	}
	
	public int getempCountBySatusanduid( Class cls , int status , int uid )
	{
		String sql = "select count(*) "
		        + " from "
		        + cls.getSimpleName()
		        + " emp,dept d,employrecord e where emp.deptid=d.lineid and emp.recordid=e.lineid and emp.ischeck="
		        + status
		        + " and (emp.checkuserid="
		        + uid
		        + " or emp.checkuserid in(select empdit.certigieruid from employaccredit empdit where empdit.authorizeruid="
		        + uid + " )) ";
		return super.getCountBySql( sql );
	}
	
	public int getCountBySQL( String sql )
	{
		// TODO Auto-generated method stub
		return super.getCountBySql( sql );
	}
	
	public int getCountByHql( String hql )
	{
		return super.getCountByHql( hql );
	}
	
	public List getDateBySqlQuery( String sql , int pageSize , int page )
	{
		// TODO Auto-generated method stub
		return super.getDateBySqlQuery( sql , pageSize , page );
	}
	
	@SuppressWarnings( "unchecked" )
	public List< EmployholidayType > getAllEmpholidayType()
	{
		return ( List< EmployholidayType > ) super.getAllObject( EmployholidayType.class );
	}
	
	public EmployholidayType getEmpholidayTypeByID( int lineid )
	{
		return ( EmployholidayType ) super.getObjectById( EmployholidayType.class ,
		        lineid );
	}
	
	public List getpageDateBySqlQuery( final String sql , final int page ,
	        final int pageSize )
	{
		return super.getpageDateBySqlQuery( sql , page , pageSize );
	}
	
	public List pageSqlQuery( String hql , Integer pageSize , Integer page , Object ... p )
	{
		return super.pageQuery( hql , pageSize , page , p );
	}
}
