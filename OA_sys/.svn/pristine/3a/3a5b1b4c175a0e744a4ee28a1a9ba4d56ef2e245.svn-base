package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.EmployAddressList;
import clt.com.cn.model.entity.Employcontract;
import clt.com.cn.model.entity.EmploycontractType;
import clt.com.cn.model.entity.EmployovertimeType;
import clt.com.cn.model.entity.Employtrip;
import clt.com.cn.model.entity.EmploytripTool;

public interface IEmploycontractDao {
	public void delEmrById(int id);
	
	public void addEmr(Employcontract emr);
	
	public void updateEmr(Employcontract emr);
	
	public Employcontract getEmploycontractById(int id);	
	public List<Employcontract> getAllEmploycontract();
	
	public int getpages(int count, int pageSize);
	public int getCountBySQL(String sql);
	
	public int getCountByHql(final String hql);
	public List pageQuery(final String hql,final Integer pageSize,final Integer page,final Object...p);
	
	public List getDateBySqlQuery(String sql,int pageSize,int page);
	public List getpageDateBySqlQuery(final String sql,final int page,final int pageSize);
	public List<Employcontract> getObjInfoByCondition(String hql, Object... p);
	
	public List<EmploycontractType> getAllEmploycontractType();
	EmploycontractType getEmploycontractType(int lineid);
	
}
