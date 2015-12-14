package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IDeptDao;
import clt.com.cn.dao.IManagerApprovalDao;
import clt.com.cn.dao.IUserDao;
import clt.com.cn.dao.IUserTokenDao;
import clt.com.cn.model.entity.Dept;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.ManagerApproval;
import clt.com.cn.model.entity.Role;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.model.entity.SmuserToken;
import clt.com.cn.model.entity.UserApprove;
import clt.com.cn.model.entity.UserSchedule;
import clt.com.cn.service.IUserService;

public class UserServiceImp implements IUserService
{
	private IUserDao userDao;
	private IDeptDao deptDao;
	private IManagerApprovalDao managerApprovalDao;
	
	private IUserTokenDao iUserTokenDao;
	
	/**
	 * @return the iUserTokenDao
	 */
	public IUserTokenDao getiUserTokenDao()
	{
		return iUserTokenDao;
	}
	
	/**
	 * @param iUserTokenDao the iUserTokenDao to set
	 */
	public void setiUserTokenDao( IUserTokenDao iUserTokenDao )
	{
		this.iUserTokenDao = iUserTokenDao;
	}
	
	public void setManagerApprovalDao( IManagerApprovalDao managerApprovalDao )
	{
		this.managerApprovalDao = managerApprovalDao;
	}
	
	public void setDeptDao( IDeptDao deptDao )
	{
		this.deptDao = deptDao;
	}
	
	public void setUserDao( IUserDao userDao )
	{
		this.userDao = userDao;
	}
	
	public void addUser( Smuser u )
	{
		userDao.addUser( u );
	}
	
	public void saveManagerApproval( ManagerApproval u )
	{
		managerApprovalDao.addManagerApproval( u );
	}
	
	public void saveUserApprove( UserApprove us )
	{
		userDao.saveUserApprove( us );
	}
	
	public Smuser userLogin( String uname , String upass )
	{
		String hql = "from Smuser sm where sm.userno=? and sm.password=? and sm.active=1 ";
		
		System.out.println( ">>> " + hql );
		List< Smuser > ulist = userDao.getUsersByCondition( hql , uname.trim() ,
		        upass.trim() );
		if ( ulist.size() == 1 )
		{
			return ulist.get( 0 );
		}
		else
		{
			return null;
		}
	}
	
	public Smuser userName( String uname )
	{
		String hql = "from Smuser sm where sm.userno=?";
		List< Smuser > ulist = userDao.checkUser( hql , uname );
		if ( ulist.size() == 1 )
		{
			return ulist.get( 0 );
		}
		else
		{
			return null;
		}
	}
	
	public List getAllUsers( int page )
	{
		String sql = "select sm.lineid,sm.recordid,sm.userno,emp.employname,d.deptname,po.positionname,sm.lastupdatedate,sm.active"
		        + " from Smuser  sm,Role  ro ,Employrecord  emp,Position  po,dept d"
		        + " where sm.roleid=ro.lineid and sm.recordid=emp.lineid and emp.positionid=po.lineid and emp.deptid= d.lineid and emp.status>0";
		return userDao.getpageDateBySqlQuery( sql , page , 5 );
	}
	
	public Smuser getUserById( int uid )
	{
		return userDao.getUserById( uid );
	}
	
	public Employrecord getEmrById( int id )
	{
		return userDao.getEmrById( id );
	}
	
	public void updateUser( Smuser u )
	{
		userDao.updateUser( u );
	}
	
	public void deleteUserById( int uid )
	{
		userDao.deleteUserById( uid );
	}
	
	public List userInfo( int admin , int page )
	{
		// 根据职位查询
		String hql = "select sm.lineid,sm.recordid,sm.userno,sm.admin,sm.active,sm.lastupdatedate,ro.rolename"
		        + " from Smuser as sm,Role as ro where sm.roleid=ro.lineid and admin="
		        + admin;
		
		List ulist = userDao.getUserInfo( hql , page );
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
		
	}
	
	public List userInfoById( String userno , int page )
	{
		String hql = "select sm.lineid,sm.employrecord.lineid,sm.userno,sm.admin,sm.active,sm.lastupdatedate,ro.rolename"
		        + " from Smuser as sm,Role as ro where sm.roleid=ro.lineid and sm.userno LIKE '%"
		        + userno + "%'";
		List ulist = userDao.getUserInfo( hql , page );
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	
	public String MD5( String s )
	{
		return userDao.MD5( s );
	}
	
	public int getCount()
	{
		return userDao.getCount();
	}
	
	public int getpages( int count , int pageSize )
	{
		return userDao.getpages( count , pageSize );
	}
	
	public List< Role > getRolename()
	{
		return userDao.getRolename();
	}
	
	public int getCountByAdmin( int admin )
	{
		return userDao.getCountByAdmin( admin );
	}
	
	public int getCountByUserno( String userno )
	{
		return userDao.getCountByUserno( userno );
	}
	
	public List getDateBySqlQuery( String sql , int pageSize , int page )
	{
		// TODO Auto-generated method stub
		return userDao.getDateBySqlQuery( sql , pageSize , page );
	}
	
	public List< Smuser > getUsersByAdmin( int admin )
	{
		// TODO Auto-generated method stub
		return userDao.getUsersByAdmin( admin );
	}
	
	public List< Smuser > getUsersBygtAdmin( int admin )
	{
		// TODO Auto-generated method stub
		return userDao.getUsersBygtAdmin( admin );
	}
	
	public Smuser getUserByUserno( String userno )
	{
		List< Smuser > uslist = userDao.getUserByUserno( userno );
		Smuser smuser = null;
		if ( uslist.size() > 1 )
		{
			return null;
			
		}
		else if ( uslist.size() == 1 )
		{
			smuser = uslist.get( 0 );
		}
		return smuser;
	}
	
	// 根据部门id 获取审批人 (经理级别以上 普通员工直接获取对应部门的负责人)
	public Smuser getUSerByManagerApproval( Smuser sm )
	{
		Smuser smuser = null;
		Dept dept = sm.getEmployrecord().getDept();
		Smuser mauser = deptDao.getDeptManageridBydeptid( dept.getLineid() );
		// admin =0 代表普通员工
		
		// 查看是否 该用户有特殊审批人
		smuser = userDao.getUserApprove( sm );
		if ( smuser == null )
		{
			// 如果该维护不是 该部门的审批人
			if ( sm.getLineid() != mauser.getLineid() )
			{
				// 默认部门管理用户
				smuser = dept.getManageruser();
				
			}
			else
			{
				String hql = " from ManagerApproval maapp where maapp.deptID = "
				        + dept.getLineid();
				
				List< ManagerApproval > malist = managerApprovalDao.getUserInfo( hql );
				if ( malist.size() > 0 )
				{
					smuser = userDao.getUserById( malist.get( 0 ).getSmuserID() );
				}
				else
				
				{
					System.out.println( "获取ManagerApproval  部门对应管理人出错  size=0" );
				}
			}
		}
		System.out.println( " >> " + sm.getUserno() + " >> 审批人  " + smuser.getUserno() );
		return smuser;
		
	}
	
	public int getCountBySql( String sql )
	{
		// TODO Auto-generated method stub
		return userDao.getCountBySql( sql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @param p
	 * @return
	 * @author chenbin
	 * @create_date 2014-8-22 下午2:59:13
	 */
	public List< Smuser > getUsersByCondition( String hql , Object ... p )
	{
		// TODO Auto-generated method stub
		return userDao.getUsersByCondition( hql , p );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param u
	 * @author chenbin
	 * @create_date 2014-9-4 下午6:02:23
	 */
	public void saveUserSchedule( UserSchedule u )
	{
		// TODO Auto-generated method stub
		userDao.saveUserSchedule( u );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param smuserToken 
	 * @author liuwu
	 * @create_date 2015年11月10日 下午2:54:05
	 */
	public void saveUserToken( SmuserToken smuserToken )
	{
		// TODO Auto-generated method stub
		iUserTokenDao.addUserToken( smuserToken );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param token
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月10日 下午3:14:14
	 */
	public SmuserToken findByToken( String token )
	{
		SmuserToken smuserToken = iUserTokenDao.findByToken( token );
		return smuserToken;
	}
}
