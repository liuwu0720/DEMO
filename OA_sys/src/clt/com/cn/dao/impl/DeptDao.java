package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IDeptDao;
import clt.com.cn.model.entity.Dept;
import clt.com.cn.model.entity.DeptAddress;
import clt.com.cn.model.entity.DeptMapping;
import clt.com.cn.model.entity.Smuser;

public class DeptDao extends BaseDao implements IDeptDao
{
	public List< Dept > getAllDept()
	{
		return super.getAllObject( Dept.class );
	}
	
	public void addDept( Dept dept )
	{
		super.addObject( dept );
	}
	
	public void addDeptmapping( DeptMapping deptmapping )
	{
		super.addObject( deptmapping );
	}
	
	public void delDeptById( int id )
	{
		super.deleteObjectById( Dept.class , id );
	}
	
	public Dept getDeptById( int id )
	{
		return ( Dept ) super.getObjectById( Dept.class , id );
	}
	
	public void updateDept( Dept d )
	{
		super.updateObject( d );
	}
	
	public List< Dept > getUserInfo( String hql , Object ... p )
	{
		// 条件查询
		return super.getUsersByCondition( hql , p );
	}
	
	public int getCount()
	{
		String sql = "select count(*) from dept d left join smuser sm on d.manageruserid=sm.lineid left join employrecord  emp on  sm.recordid=emp.lineid ";
		int count = super.getCountBySql( sql );
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
	
	public List< Dept > getAllDeptByPage( int page )
	{
		String hql = "select d.lineid,d.deptname,emp.employname,emp.mobile,emp.email,getCompanyByDeptID(d.lineid) did,d.active from dept d left join smuser sm on d.manageruserid=sm.lineid left join employrecord  emp on  sm.recordid=emp.lineid ";
		return super.pageSqlQuery( hql , 5 , page );
	}
	
	public Smuser getDeptManageridBydeptid( int deptid )
	{
		String hql = " from Dept d where d.lineid=? ";
		List< Dept > deptlists = super.getUsersByCondition( hql , deptid );
		if ( deptlists.size() > 0 )
		{
			Dept dept = deptlists.get( 0 );
			Smuser smuser = dept.getManageruser();
			return smuser;
		}
		else
		{
			return null;
		}
	}
	
	public void execSQL( final String sql )
	{
		
		super.execSQL( sql );
	}
	
	/**
	 * 
	 * @Description: 获取所有公司 部门级别=2
	 * @return List<Dept> 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-18 下午1:38:41
	 */
	public List< Dept > getAllCompay()
	{
		String hql = " from Dept d where d.active=1 and d.level = 2  order by d.lineid asc ";
		List< Dept > complist = super.getUsersByCondition( hql , null );
		return complist;
	}
	
	/**
	 * 
	 * @Description: 根据公司ID获取所有部门 因为会有递归 所有写的sql
	 * @param compid
	 * @return List<String[]> 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-18 下午1:39:03
	 */
	public List< Object[] > getAllDeptByComID( int compid )
	{
		String sql = " select dt.lineid,dt.pid,dt.deptname from dept dt where dt.ACTIVE=1 and dt.lineid!="
		        + compid
		        + "  start with dt.lineid = "
		        + compid
		        + " connect by Prior dt.lineid = dt.pid  order by dt.lineid asc ";
		List< Object[] > complist = super.getDateBySqlQuery( sql , 0 , 0 );
		return complist;
	}
	
	/**
	 * 
	 * @Description: 获取该公司所有地址
	 * @param compid
	 * @return List<Object[]> 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-18 下午8:47:47
	 */
	
	public List< Object[] > getAllAddressByCompID( int compid )
	{
		String sql = " select addres.lineid,d.pid,d.deptname,addres.compayname|| ' > '||addres.deptaddress  from dept d, deptaddress addres where d.lineid=addres.deptid "
		        + " and getcompanyidbydeptid(addres.deptid) =" + compid;
		List< Object[] > complist = super.getDateBySqlQuery( sql , 0 , 0 );
		return complist;
	}
	
	public DeptMapping getDeptMapping( int id )
	{
		return ( DeptMapping ) super.getObjectById( DeptMapping.class , id );
	}
	
	public DeptAddress getDeptAddressByID( int id )
	{
		return ( DeptAddress ) super.getObjectById( DeptAddress.class , id );
	}
	
	public void sendMails( final String rcver_name , final String rcver_mail ,
	        final String subject , final String subContents )
	{
		super.sendMails( rcver_name , rcver_mail , subject , subContents );
	}


	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param lineid
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月23日 下午3:23:22
	 */ 
    public Dept getdeptByPid( int lineid )
    {
	   String hql = "from Dept where pid = "+lineid;
	    return ( Dept ) super.getAllObjectOrder( hql ).get( 0 );
    }

	
	public List< Dept > getAllObjectOrder( String hql )
	{
		return ( List< Dept > ) super.getAllObjectOrder( hql );
	}

}
