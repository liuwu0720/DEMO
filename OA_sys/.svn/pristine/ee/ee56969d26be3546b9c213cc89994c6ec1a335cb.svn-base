package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IEmploytripDao;
import clt.com.cn.model.entity.Employtrip;
import clt.com.cn.model.entity.EmploytripTool;

public class EmploytripDao extends BaseDao implements IEmploytripDao{

	public void delEmrById(int id) {
		// TODO Auto-generated method stub
		super.deleteObjectById(Employtrip.class, id);
	}

	public void addEmr(Employtrip emr) {
		// TODO Auto-generated method stub
		super.addObject(emr);
	}

	public void updateEmr(Employtrip emr) {
		// TODO Auto-generated method stub
		super.updateObject(emr);
	}

	public Employtrip getEmploytripById(int id) {
		// TODO Auto-generated method stub
		return (Employtrip)super.getObjectById(Employtrip.class, id);
	}

	public int getCountBySQL(String sql) {
		// TODO Auto-generated method stub
		return super.getCountBySql(sql);
	}

	public List<Employtrip> getObjInfoByCondition(String hql, Object... p) {
		// TODO Auto-generated method stub
		return super.getUsersByCondition(hql, p);
	}

	public List<EmploytripTool> getAllEmploytripTool() {
		// TODO Auto-generated method stub
		return getAllObject(EmploytripTool.class);
	}

	public EmploytripTool getEmploytripTool(int lineid) {
		// TODO Auto-generated method stub
		return (EmploytripTool)super.getObjectById(EmploytripTool.class, lineid);
	}

	public List getDateBySqlQuery(String sql, int pageSize, int page) {
		// TODO Auto-generated method stub
		return super.getDateBySqlQuery(sql, pageSize, page);
	}

}
