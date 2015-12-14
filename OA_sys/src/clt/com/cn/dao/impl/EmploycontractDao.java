package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IDeptDao;
import clt.com.cn.dao.IEmploycontractDao;
import clt.com.cn.model.entity.*;


public class EmploycontractDao extends BaseDao implements IEmploycontractDao {

	public void delEmrById(int id) {
		// TODO Auto-generated method stub
		super.deleteObjectById(Employcontract.class, id);
	}

	public void addEmr(Employcontract emr) {
		// TODO Auto-generated method stub
		super.addObject(emr);
	}

	public void updateEmr(Employcontract emr) {
		// TODO Auto-generated method stub
		super.updateObject(emr);
	}

	public Employcontract getEmploycontractById(int id) {
		// TODO Auto-generated method stub
		return (Employcontract)super.getObjectById(Employcontract.class, id);
	}

	public List<Employcontract> getAllEmploycontract() {
		// TODO Auto-generated method stub
		return super.getAllObject(Employcontract.class);
	}
	public int getCountBySQL(String sql) {
		// TODO Auto-generated method stub
		return super.getCountBySql(sql);
	}

	public List<Employcontract> getObjInfoByCondition(String hql, Object... p) {
		// TODO Auto-generated method stub
		return super.getUsersByCondition(hql, p);
	}

	public List<EmploycontractType> getAllEmploycontractType() {
		// TODO Auto-generated method stub
		return super.getAllObject(EmploycontractType.class);
	}

	public EmploycontractType getEmploycontractType(int lineid) {
		// TODO Auto-generated method stub
		return (EmploycontractType)super.getObjectById(EmploycontractType.class, lineid);
	}

}
