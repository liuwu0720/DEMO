package clt.com.cn.dao.impl;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IPrivilegeDao;
import clt.com.cn.model.entity.Privilege;

public class PrivilegeDao extends BaseDao implements IPrivilegeDao {

	public Privilege getbyId(int id) {
		return (Privilege) super.getObjectById(Privilege.class, id);
	}

	public void delByObj(Privilege pr) {
		super.deleteObject(pr);
	}

	public void delByid(int id) {
		super.deleteObjectById(Privilege.class, id);
	}

	public void add(Privilege pr) {
		super.addObject(pr);
	}

}
