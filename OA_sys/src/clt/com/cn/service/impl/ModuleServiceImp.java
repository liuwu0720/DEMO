package clt.com.cn.service.impl;

import java.util.ArrayList;
import java.util.List;

import clt.com.cn.dao.IModuleDao;
import clt.com.cn.dao.IUserDao;
import clt.com.cn.dao.impl.PrivilegeDao;
import clt.com.cn.dao.impl.RolePriviegeDao;
import clt.com.cn.model.entity.Privilege;
import clt.com.cn.model.entity.Roleprivilege;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.model.entity.Userprivilege;
import clt.com.cn.service.IModuleService;

public class ModuleServiceImp implements IModuleService
{
	private IModuleDao moduleDao;
	
	private IUserDao userDao;
	
	private RolePriviegeDao rpDao;
	
	private PrivilegeDao privilegeDao;
	
	public void setModuleDao( IModuleDao moduleDao )
	{
		this.moduleDao = moduleDao;
	}
	
	public List getAllModule( int userid )
	{
		// TODO Auto-generated method stub
		String hql = "select pr.lineid,pr.privilegeno,pr.privilegename,pr.filename,pr.pid"
		        + " from Privilege pr,Userprivilege upr where upr.privilegeid=pr.lineid and upr.userid="
		        + userid + " order by pr.privilegeno asc";
		List list = moduleDao.getAllModule( hql );
		if ( list.size() != 0 )
		{
			return list;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * 根据用户id获得对应用户角色id，通过角色id获得该角色的权限--model
	 * 
	 * @param userid
	 * @return
	 */
	public List< Privilege > getModuleByUserId( int userid )
	{
		Smuser suser = userDao.getUserById( userid );
		int roleid = suser.getRoleid();
		List< Roleprivilege > rpList = rpDao.getRpByrole( roleid );
		if ( rpList != null && rpList.size() > 0 )
		{
			List< Privilege > prlist = new ArrayList< Privilege >();
			for ( Roleprivilege rp : rpList )
			{
				prlist.add( rp.getPrivilege() );
			}
			return prlist;
		}
		return null;
	}
	
	public int getCount( int deptid , int userid )
	{
		return moduleDao.getCount( deptid , userid );
	}
	
	public List< Privilege > getAllPrivilege()
	{
		List< Privilege > list = moduleDao.getAllPrivilege();
		if ( list.size() != 0 )
		{
			return list;
		}
		else
		{
			return null;
		}
	}
	
	public Privilege gerPrivilegeById( int id )
	{
		return moduleDao.gerPrivilegeById( id );
	}
	
	public void addUserPrivilege( Userprivilege upr )
	{
		moduleDao.addUserPrivilege( upr );
	}
	
	public List< Userprivilege > getUserPrivilege( int userid )
	{
		List< Userprivilege > list = moduleDao.getUserPrivilege( userid );
		if ( list.size() != 0 )
		{
			return list;
		}
		else
		{
			return null;
		}
	}
	
	public void delUserPrivilege( int userid )
	{
		moduleDao.delUserPrivilege( userid );
	}
	
	public IUserDao getUserDao()
	{
		return userDao;
	}
	
	public void setUserDao( IUserDao userDao )
	{
		this.userDao = userDao;
	}
	
	public RolePriviegeDao getRpDao()
	{
		return rpDao;
	}
	
	public void setRpDao( RolePriviegeDao rpDao )
	{
		this.rpDao = rpDao;
	}
	
	public PrivilegeDao getPrivilegeDao()
	{
		return privilegeDao;
	}
	
	public void setPrivilegeDao( PrivilegeDao privilegeDao )
	{
		this.privilegeDao = privilegeDao;
	}
	
	public String getHandleMessageCount( int deptid , int userid )
	{
		return moduleDao.getHandleMessageCount( deptid , userid );
	}
	
	public List< Roleprivilege > getprivilegeByRoleId( int roleid )
	{
		return rpDao.getRpByrole( roleid );
	}
}
