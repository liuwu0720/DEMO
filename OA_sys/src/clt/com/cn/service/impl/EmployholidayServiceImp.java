package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IEmployholidayDao;
import clt.com.cn.model.entity.Employholiday;
import clt.com.cn.model.entity.EmployholidayType;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IEmployholidayService;

public class EmployholidayServiceImp implements IEmployholidayService
{
	private IEmployholidayDao ehdDao;
	
	public void setEhdDao( IEmployholidayDao ehdDao )
	{
		this.ehdDao = ehdDao;
	}
	
	// 查询所有请假条
	public List< Employholiday > getAllEhd()
	{
		return ehdDao.getAllEhd();
	}
	
	// 删除请假条
	public void delEhdById( int id )
	{
		ehdDao.delEhdById( id );
	}
	
	// 申请请假条
	public void addEhd( Employholiday ehd )
	{
		ehdDao.addEhd( ehd );
	}
	
	public List< Smuser > getUserByName( String name )
	{
		List< Smuser > list = ehdDao.getUserByName( name );
		if ( list.size() != 0 )
		{
			return list;
		}
		else
		{
			return null;
		}
	}
	
	public List< Employrecord > getEmrByName( String name )
	{
		List< Employrecord > list = ehdDao.getEmrByName( name );
		if ( list.size() != 0 )
		{
			return list;
		}
		else
		{
			return null;
		}
	}
	
	public List getEhdById( int id )
	{
		List ulist = ehdDao.getEhdById( id );
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	
	public void updateEhd( Employholiday ehd )
	{
		ehdDao.updateEhd( ehd );
	}
	
	public List< Employholiday > getEhdByIscheck( int ischeck )
	{
		String hql = "from Employholiday ehd where ehd.ischeck=?";
		List< Employholiday > ulist = ehdDao.getEhdInfo( hql , ischeck );
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	
	public List< Employholiday > getEhdByIscheck( int ischeck , int lineid )
	{
		String hql = " from Employholiday ehd where ehd.ischeck=? and ehd.opuser.lineid=?";
		List< Employholiday > ulist = ehdDao.getEhdInfo( hql , ischeck , lineid );
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	
	public int getEhdCountByIscheck( int ischeck , int lineid )
	{
		String sql = " select count(*) from Employholiday ehd where ehd.ischeck="
		        + ischeck + " and ehd.userid=" + lineid;
		int count = ehdDao.getCountBySQL( sql );
		return count;
	}
	
	public List< Employholiday > getEhdByChecked( int lineid )
	{
		String hql = "from Employholiday ehd where (ehd.ischeck=1 or ehd.ischeck=2) and ehd.opuser.lineid=?";
		List< Employholiday > ulist = ehdDao.getEhdInfo( hql , lineid );
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	
	public List getEhdBydept( int deptid , int page )
	{
		List ulist = ehdDao.getEhdBydept( deptid , page );
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	
	public Employrecord getEmrBylineid( int recordid )
	{
		String hql = "from Employrecord emr where emr.lineid=" + recordid;
		List< Employrecord > ulist = ehdDao.getEmrBylineid( hql );
		if ( ulist.size() > 0 )
		{
			return ulist.get( 0 );
		}
		else
		{
			return null;
		}
	}
	
	public int getCount( int deptid )
	{
		return ehdDao.getCount( deptid );
	}
	
	public int getCountByIs( int ischeck )
	{
		return ehdDao.getCountByIs( ischeck );
	}
	
	public int getpages( int count , int pageSize )
	{
		// TODO Auto-generated method stub
		return ehdDao.getpages( count , pageSize );
	}
	
	public List getEhdByIs( int ischeck , int page )
	{
		List ulist = ehdDao.getEhdByIs( ischeck , page );
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	
	public Employholiday get( int lineid )
	{
		// TODO Auto-generated method stub
		return ehdDao.get( lineid );
	}
	
	public int getempCountBySatusanduid( Class cls , int status , int uid )
	{
		// TODO Auto-generated method stub
		return ehdDao.getempCountBySatusanduid( cls , status , uid );
	}
	
	public List getempBySatusanduid( Class cls , int status , int uid )
	{
		// TODO Auto-generated method stub
		return ehdDao.getempBySatusanduid( cls , status , uid );
	}
	
	public int getCountBySQL( String sql )
	{
		// TODO Auto-generated method stub
		return ehdDao.getCountBySQL( sql );
	}
	
	public List getDateBySqlQuery( String sql , int pageSize , int page )
	{
		// TODO Auto-generated method stub
		return ehdDao.getDateBySqlQuery( sql , pageSize , page );
	}
	
	public List< EmployholidayType > getAllEmpholidayType()
	{
		// TODO Auto-generated method stub
		return ehdDao.getAllEmpholidayType();
	}
	
	public EmployholidayType getEmpholidayTypeByID( int lineid )
	{
		// TODO Auto-generated method stub
		return ehdDao.getEmpholidayTypeByID( lineid );
	}
	
	public List< Employholiday > getEhdInfo( String hql , Object ... p )
	{
		// TODO Auto-generated method stub
		return ehdDao.getEhdInfo( hql , p );
	}
	
	public List getpageDateBySqlQuery( String sql , int page , int pageSize )
	{
		// TODO Auto-generated method stub
		return ehdDao.getpageDateBySqlQuery( sql , page , pageSize );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-25 上午11:32:38
	 */
	public int getCountByHql( String hql )
	{
		// TODO Auto-generated method stub
		return ehdDao.getCountByHql( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @param pageSize
	 * @param page
	 * @param p
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-25 上午11:34:53
	 */
	public List pageSqlQuery( String hql , Integer pageSize , Integer page , Object ... p )
	{
		// TODO Auto-generated method stub
		return ehdDao.pageSqlQuery( hql , pageSize , page , p );
	}
	
}
