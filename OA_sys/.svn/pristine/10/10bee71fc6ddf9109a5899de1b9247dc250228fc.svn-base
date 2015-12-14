package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IEmploycontractDao;
import clt.com.cn.dao.IEmployovertimeDao;
import clt.com.cn.model.entity.Employcontract;
import clt.com.cn.model.entity.EmploycontractType;
import clt.com.cn.service.ICheckInfoService;
import clt.com.cn.service.IEmploycontractService;

public class EmploycontractServiceImp implements IEmploycontractService {
	private IEmploycontractDao empcontractDao;
	
	
	
	public void setEmpcontractDao(IEmploycontractDao empcontractDao) {
		this.empcontractDao = empcontractDao;
	}

	public void delEmrById(int id) {
		// TODO Auto-generated method stub
		empcontractDao.delEmrById(id);
	}

	public void addEmr(Employcontract emr) {
		// TODO Auto-generated method stub
		empcontractDao.addEmr(emr);
	}

	public void updateEmr(Employcontract emr) {
		// TODO Auto-generated method stub
		empcontractDao.updateEmr(emr);
	}

	public Employcontract getEmploycontractById(int id) {
		// TODO Auto-generated method stub
		return empcontractDao.getEmploycontractById(id);
	}

	public List<Employcontract> getAllEmploycontract() {
		// TODO Auto-generated method stub
		return empcontractDao.getAllEmploycontract();
	}

	public int getpages(int count, int pageSize) {
		// TODO Auto-generated method stub
		return empcontractDao.getpages(count, pageSize);
	}

	public int getCountBySQL(String sql) {
		// TODO Auto-generated method stub
		return empcontractDao.getCountBySQL(sql);
	}

	public int getCountByHql(String hql) {
		// TODO Auto-generated method stub
		return empcontractDao.getCountByHql(hql);
	}

	public List pageQuery(String hql, Integer pageSize, Integer page,
			Object... p) {
		// TODO Auto-generated method stub
		return empcontractDao.pageQuery(hql, pageSize, page, p);
	}

	public List getDateBySqlQuery(String sql, int pageSize, int page) {
		// TODO Auto-generated method stub
		return empcontractDao.getDateBySqlQuery(sql, pageSize, page);
	}

	public List getpageDateBySqlQuery(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return empcontractDao.getpageDateBySqlQuery(sql, page, pageSize);
	}

	public List<Employcontract> getObjInfoByCondition(String hql, Object... p) {
		// TODO Auto-generated method stub
		return empcontractDao.getObjInfoByCondition(hql, p);
	}

	public List<EmploycontractType> getAllEmploycontractType() {
		// TODO Auto-generated method stub
		return empcontractDao.getAllEmploycontractType();
	}

	public EmploycontractType getEmploycontractType(int lineid) {
		// TODO Auto-generated method stub
		return empcontractDao.getEmploycontractType(lineid);
	}
	
	
	

}
