package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.Employtrip;
import clt.com.cn.model.entity.EmploytripTool;

public interface IEmploytripDao {
	public void delEmrById(int id);
	
	public void addEmr(Employtrip emr);
	
	public void updateEmr(Employtrip emr);
	
	public Employtrip getEmploytripById(int id);
	
	public int getpages(int count, int pageSize);
	public int getCountBySQL(String sql);
	
	public List getDateBySqlQuery(String sql,int pageSize,int page);
	public List getpageDateBySqlQuery(final String sql,final int page,final int pageSize);
	public List<Employtrip> getObjInfoByCondition(String hql, Object... p);
	
	public List<EmploytripTool> getAllEmploytripTool();
	EmploytripTool getEmploytripTool(int lineid);
}
