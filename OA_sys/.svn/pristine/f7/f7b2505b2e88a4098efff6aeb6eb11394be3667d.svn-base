package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.*;

public interface IEmployAccreditDao {
	public void delEmrById(int id);
	
	public void addEmr(Employaccredit emr);
	
	public void updateEmr(Employaccredit emr);
	
	public List getAllEmployaccreditByUid(int userid,int page,int pageSize);
	
	public Employaccredit getEmployaccreditById(int id);
	
	public int getpages(int count, int pageSize);
	public int getCountBySQL(String sql);
	public int getAllEmployaccreditByUidCount(int userid);
	
	public List getDateBySqlQuery(String sql,int pageSize,int page);
	public List getpageDateBySqlQuery(final String sql,final int page,final int pageSize);
}
