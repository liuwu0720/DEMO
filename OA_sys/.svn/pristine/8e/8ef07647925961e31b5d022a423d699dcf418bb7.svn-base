package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.Employovertime;
import clt.com.cn.model.entity.EmployovertimeType;
import clt.com.cn.model.entity.Employrecruitment;
import clt.com.cn.model.entity.EmployrecruitmentType;

public interface IEmployrecruitmentDao {
	public void delEmrById(int id);
	
	public void addEmr(Employrecruitment emr);
	
	public void updateEmr(Employrecruitment emr);
	
	public Employrecruitment getEmployrecruitmentById(int id);
	
	public int getpages(int count, int pageSize);
	public int getCountBySQL(String sql);
	
	public List getDateBySqlQuery(String sql,int pageSize,int page);
	public List getpageDateBySqlQuery(final String sql,final int page,final int pageSize);
	public List<Employrecruitment> getObjInfoByCondition(String hql, Object... p);
	
	public List<EmployrecruitmentType> getAllEmployrecruitmentType();
	EmployrecruitmentType getEmployrecruitmentType(int lineid);
}
