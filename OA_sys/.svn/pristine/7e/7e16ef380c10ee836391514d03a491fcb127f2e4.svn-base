package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IEmployovertimeDao;
import clt.com.cn.service.ICheckInfoService;

public class CheckInfoServiceImp implements ICheckInfoService {
	private IEmployovertimeDao empovertimeDao;
	
	
	
	public IEmployovertimeDao getEmpovertimeDao() {
		return empovertimeDao;
	}
	public void setEmpovertimeDao(IEmployovertimeDao empovertimeDao) {
		this.empovertimeDao = empovertimeDao;
	}
	public int getpages(int count, int pageSize) {
		return empovertimeDao.getpages(count, pageSize);
	}
	public int getCountBySQL(String sql) {
		return empovertimeDao.getCountBySQL(sql);
	}
	public List getpageDateBySqlQuery(String sql, int page, int pageSize) {
		return empovertimeDao.getpageDateBySqlQuery(sql, page, pageSize);
	}

}
