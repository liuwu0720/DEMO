package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IEmployattendanceDao;
import clt.com.cn.model.entity.Employattendance;
import clt.com.cn.service.IEmployattendanceService;

public class EmployattendanceServiceImp implements IEmployattendanceService{

	private IEmployattendanceDao employattendanceDao;
	
	
	public void setEmployattendanceDao(IEmployattendanceDao employattendanceDao) {
		this.employattendanceDao = employattendanceDao;
	}

	public void delEmrById(int id) {
		// TODO Auto-generated method stub
		employattendanceDao.delEmrById(id);
	}

	public void addEmr(Employattendance emr) {
		// TODO Auto-generated method stub
		employattendanceDao.addEmr(emr);
	}

	public void updateEmr(Employattendance emr) {
		// TODO Auto-generated method stub
		employattendanceDao.updateEmr(emr);
	}

	public Employattendance getEmployattendanceById(int id) {
		// TODO Auto-generated method stub
		return employattendanceDao.getEmployattendanceById(id);
	}

	public int getpages(int count, int pageSize) {
		// TODO Auto-generated method stub
		return employattendanceDao.getpages(count, pageSize);
	}

	public int getCountBySQL(String sql) {
		// TODO Auto-generated method stub
		return employattendanceDao.getCountBySQL(sql);
	}

	public List getDateBySqlQuery(String sql,int pageSize,int page) {
		// TODO Auto-generated method stub
		return employattendanceDao.getDateBySqlQuery(sql,pageSize,page);
	}

	public List getpageDateBySqlQuery(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return employattendanceDao.getpageDateBySqlQuery(sql, page, pageSize);
	}

	public List<Employattendance> getObjInfoByCondition(String hql, Object... p) {
		// TODO Auto-generated method stub
		return employattendanceDao.getObjInfoByCondition(hql, p);
	}

}
