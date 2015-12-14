package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.Employattendance;

public interface IEmployattendanceService {

	public void delEmrById(int id);
	
	public void addEmr(Employattendance emr);
	
	public void updateEmr(Employattendance emr);
	
	public Employattendance getEmployattendanceById(int id);
	
	public int getpages(int count, int pageSize);
	public int getCountBySQL(String sql);
	
	public List getDateBySqlQuery(String sql,int pageSize,int page);
	public List getpageDateBySqlQuery(final String sql,final int page,final int pageSize);
	public List<Employattendance> getObjInfoByCondition(String hql, Object... p);
	
}
