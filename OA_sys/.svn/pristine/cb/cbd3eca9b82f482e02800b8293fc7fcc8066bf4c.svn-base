package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IDeptDao;
import clt.com.cn.dao.IEmployAccreditDao;
import clt.com.cn.model.entity.Dept;
import clt.com.cn.model.entity.Employaccredit;
import clt.com.cn.model.entity.Privilege;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IEmployAccreditService;

public class EmployAccreditServiceImp implements IEmployAccreditService {
	private IEmployAccreditDao employAccreditDao;
	
	
	public void setEmployAccreditDao(IEmployAccreditDao employAccreditDao) {
		this.employAccreditDao = employAccreditDao;
	}

	public void delEmrById(int id) {
		// TODO Auto-generated method stub
		employAccreditDao.delEmrById(id);
	}

	public void addEmr(Employaccredit emr) {
		// TODO Auto-generated method stub
		employAccreditDao.addEmr(emr);
	}

	public void updateEmr(Employaccredit emr) {
		// TODO Auto-generated method stub
		employAccreditDao.updateEmr(emr);
	}

	public List getAllEmployaccreditByUid(int userid, int page, int pageSize) {
		// TODO Auto-generated method stub
		return employAccreditDao.getAllEmployaccreditByUid(userid, page, pageSize);
	}

	public Employaccredit getEmployaccreditById(int id) {
		// TODO Auto-generated method stub
		return employAccreditDao.getEmployaccreditById(id);
	}

	public int getpages(int count, int pageSize) {
		// TODO Auto-generated method stub
		return employAccreditDao.getpages(count, pageSize);
	}

	public int getCountBySQL(String sql) {
		// TODO Auto-generated method stub
		return employAccreditDao.getCountBySQL(sql);
	}

	public int getAllEmployaccreditByUidCount(int userid) {
		// TODO Auto-generated method stub
		return employAccreditDao.getAllEmployaccreditByUidCount(userid);
	}

	public List getDateBySqlQuery(String sql,int pageSize,int page) {
		// TODO Auto-generated method stub
		return employAccreditDao.getDateBySqlQuery(sql,pageSize,page);
	}

	public List getpageDateBySqlQuery(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return employAccreditDao.getpageDateBySqlQuery(sql, page, pageSize);
	}
}
