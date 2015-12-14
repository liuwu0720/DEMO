package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.EmployAddressList;
import clt.com.cn.model.entity.Employtrip;
import clt.com.cn.model.entity.EmploytripTool;

public interface IEmployAddressListDao {
	public void delEmrById(int id);
	
	public void addEmr(EmployAddressList emr);
	
	public void updateEmr(EmployAddressList emr);
	
	public EmployAddressList getEmployAddressListById(int id);
	
	public int getpages(int count, int pageSize);
	public int getCountBySQL(String sql);
	
	public int getCountByHql(final String hql);
	public List pageQuery(final String hql,final Integer pageSize,final Integer page,final Object...p);
	
	public List getDateBySqlQuery(String sql,int pageSize,int page);
	public List getpageDateBySqlQuery(final String sql,final int page,final int pageSize);
	public List<EmployAddressList> getObjInfoByCondition(String hql, Object... p);
	
	
}
