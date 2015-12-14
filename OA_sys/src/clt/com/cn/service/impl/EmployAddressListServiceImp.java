package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IDeptDao;
import clt.com.cn.dao.IEmployAddressListDao;
import clt.com.cn.model.entity.Dept;
import clt.com.cn.model.entity.EmployAddressList;
import clt.com.cn.model.entity.Privilege;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IEmployAddressListService;

public class EmployAddressListServiceImp implements IEmployAddressListService {

	private IEmployAddressListDao empaddresslistDao;

	
	public void setEmpaddresslistDao(IEmployAddressListDao empaddresslistDao) {
		this.empaddresslistDao = empaddresslistDao;
	}

	public void delEmrById(int id) {
		// TODO Auto-generated method stub
		empaddresslistDao.delEmrById(id);
	}

	public void addEmr(EmployAddressList emr) {
		// TODO Auto-generated method stub
		empaddresslistDao.addEmr(emr);
	}

	public void updateEmr(EmployAddressList emr) {
		// TODO Auto-generated method stub
		empaddresslistDao.updateEmr(emr);
	}

	public EmployAddressList getEmployAddressListById(int id) {
		// TODO Auto-generated method stub
		return empaddresslistDao.getEmployAddressListById(id);
	}

	public int getpages(int count, int pageSize) {
		// TODO Auto-generated method stub
		return empaddresslistDao.getpages(count, pageSize);
	}

	public int getCountBySQL(String sql) {
		// TODO Auto-generated method stub
		return empaddresslistDao.getCountBySQL(sql);
	}

	public int getCountByHql(String hql) {
		// TODO Auto-generated method stub
		return empaddresslistDao.getCountByHql(hql);
	}

	public List pageQuery(String hql, Integer pageSize, Integer page,
			Object... p) {
		// TODO Auto-generated method stub
		return empaddresslistDao.pageQuery(hql, pageSize, page, p);
	}

	public List getDateBySqlQuery(String sql, int pageSize, int page) {
		// TODO Auto-generated method stub
		return empaddresslistDao.getDateBySqlQuery(sql, pageSize, page);
	}

	public List getpageDateBySqlQuery(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return empaddresslistDao.getpageDateBySqlQuery(sql, page, pageSize);
	}

	public List<EmployAddressList> getObjInfoByCondition(String hql,
			Object... p) {
		// TODO Auto-generated method stub
		return empaddresslistDao.getObjInfoByCondition(hql, p);
	}
	
}
