package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IEmployovertimeDao;
import clt.com.cn.dao.IEmployrecruitmentDao;
import clt.com.cn.model.entity.Employholiday;
import clt.com.cn.model.entity.Employovertime;
import clt.com.cn.model.entity.EmployovertimeType;
import clt.com.cn.model.entity.Employrecruitment;
import clt.com.cn.model.entity.EmployrecruitmentType;
import clt.com.cn.service.IEmployovertimeService;
import clt.com.cn.service.IEmployrecruitmentService;

public class EmployrecruitmentServiceImp implements IEmployrecruitmentService {

	private IEmployrecruitmentDao employrecruitmentDao;
	
	
	public void setEmployrecruitmentDao(IEmployrecruitmentDao employrecruitmentDao) {
		this.employrecruitmentDao = employrecruitmentDao;
	}

	public void delEmrById(int id) {
		// TODO Auto-generated method stub
		employrecruitmentDao.delEmrById(id);
	}

	public void addEmr(Employrecruitment emr) {
		// TODO Auto-generated method stub
		employrecruitmentDao.addEmr(emr);
	}

	public void updateEmr(Employrecruitment emr) {
		// TODO Auto-generated method stub
		employrecruitmentDao.updateEmr(emr);
	}

	public Employrecruitment getEmployrecruitmentById(int id) {
		// TODO Auto-generated method stub
		return employrecruitmentDao.getEmployrecruitmentById(id);
	}

	public int getpages(int count, int pageSize) {
		// TODO Auto-generated method stub
		return employrecruitmentDao.getpages(count, pageSize);
	}

	public int getCountBySQL(String sql) {
		// TODO Auto-generated method stub
		return employrecruitmentDao.getCountBySQL(sql);
	}

	public List getDateBySqlQuery(String sql, int pageSize, int page) {
		// TODO Auto-generated method stub
		return employrecruitmentDao.getDateBySqlQuery(sql, pageSize, page);
	}

	public List getpageDateBySqlQuery(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return employrecruitmentDao.getpageDateBySqlQuery(sql, page, pageSize);
	}

	public List<Employrecruitment> getObjInfoByCondition(String hql,
			Object... p) {
		// TODO Auto-generated method stub
		return employrecruitmentDao.getObjInfoByCondition(hql, p);
	}

	public List<EmployrecruitmentType> getAllEmployrecruitmentType() {
		// TODO Auto-generated method stub
		return employrecruitmentDao.getAllEmployrecruitmentType();
	}

	public EmployrecruitmentType getEmployrecruitmentType(int lineid) {
		// TODO Auto-generated method stub
		return employrecruitmentDao.getEmployrecruitmentType(lineid);
	}

	
}
