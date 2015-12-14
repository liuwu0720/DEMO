package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IEmploytripDao;
import clt.com.cn.model.entity.Employtrip;
import clt.com.cn.model.entity.EmploytripTool;
import clt.com.cn.service.IEmploytripService;

public class EmploytripServiceImp implements IEmploytripService{

	private  IEmploytripDao employtripDao;
	
	
	public void setEmploytripDao(IEmploytripDao employtripDao) {
		this.employtripDao = employtripDao;
	}

	public void delEmrById(int id) {
		// TODO Auto-generated method stub
		employtripDao.delEmrById(id);
	}

	public void addEmr(Employtrip emr) {
		// TODO Auto-generated method stub
		employtripDao.addEmr(emr);
	}

	public void updateEmr(Employtrip emr) {
		// TODO Auto-generated method stub
		employtripDao.updateEmr(emr);
	}

	public Employtrip getEmploytripById(int id) {
		// TODO Auto-generated method stub
		return employtripDao.getEmploytripById(id);
	}

	public int getpages(int count, int pageSize) {
		// TODO Auto-generated method stub
		return employtripDao.getpages(count, pageSize);
	}

	public int getCountBySQL(String sql) {
		// TODO Auto-generated method stub
		return employtripDao.getCountBySQL(sql);
	}

	public List getDateBySqlQuery(String sql,int pageSize,int page) {
		// TODO Auto-generated method stub
		return employtripDao.getDateBySqlQuery(sql, pageSize, page);
	}

	public List getpageDateBySqlQuery(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return employtripDao.getpageDateBySqlQuery(sql, page, pageSize);
	}

	public List<Employtrip> getObjInfoByCondition(String hql, Object... p) {
		// TODO Auto-generated method stub
		return employtripDao.getObjInfoByCondition(hql, p);
	}

	public List<EmploytripTool> getAllEmploytripTool() {
		// TODO Auto-generated method stub
		return employtripDao.getAllEmploytripTool();
	}

	public EmploytripTool getEmploytripTool(int lineid) {
		// TODO Auto-generated method stub
		return employtripDao.getEmploytripTool(lineid);
	}

}
