package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.*;

public interface IModuleService {
	public abstract List getAllModule(int userid);
	//查询新闻条数
	public abstract int getCount(int deptid,int userid);
	//查询所有权限
	public abstract List<Privilege> getAllPrivilege();
	//通过权限ID 查询权限信息
	public abstract Privilege gerPrivilegeById(int id);
	//添加权限
	public abstract void addUserPrivilege(Userprivilege upr);
	//查看选中的权限
	public abstract List<Userprivilege> getUserPrivilege(int userid);
	//删除权限
	public abstract void delUserPrivilege(int userid);

	public List<Privilege> getModuleByUserId(int userid);
	public List<Roleprivilege> getprivilegeByRoleId(int roleid);
	
	public String getHandleMessageCount(int deptid, int userid);
}
