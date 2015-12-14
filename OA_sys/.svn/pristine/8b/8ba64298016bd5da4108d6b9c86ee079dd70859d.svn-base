package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IRolePriviege;
import clt.com.cn.model.entity.Roleprivilege;

public class RolePriviegeDao extends BaseDao implements IRolePriviege {

	public void add(Roleprivilege rp) {
		this.addObject(rp);
	}

	public void delByid(int id) {
		this.deleteObjectById(Roleprivilege.class, id);
	}

	public void delByRp(Roleprivilege rp) {
		this.deleteObject(rp);
	}

	public void update(Roleprivilege rp) {
		this.updateObject(rp);
	}

	public List<Roleprivilege> getRpByrole(int roleId) {
		String hql = "from Roleprivilege t where t.role.lineid="+roleId+" order by t.privilege.privilegeno asc ";
		return this.getAllObjectOrder(hql);
	}

}
