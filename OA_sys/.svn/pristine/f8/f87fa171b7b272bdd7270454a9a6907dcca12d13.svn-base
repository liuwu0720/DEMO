package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IEmployAccreditDao;
import clt.com.cn.model.entity.Employaccredit;

public class EmployAccreditDao extends BaseDao implements IEmployAccreditDao {

	public void delEmrById(int id) {
		// TODO Auto-generated method stub
		super.deleteObjectById(Employaccredit.class, id);
	}

	public void addEmr(Employaccredit emr) {
		// TODO Auto-generated method stub
		super.addObject(emr);
	}

	public void updateEmr(Employaccredit emr) {
		// TODO Auto-generated method stub
		super.updateObject(emr);
	}

	public List getAllEmployaccreditByUid(int userid,int page,int pageSize) {
		String hql =" from Employaccredit emp where emp.certigierUser.lineid = "+userid;
		return super.pageQuery(hql, pageSize, page);
	}
	public int getAllEmployaccreditByUidCount(int userid) {
		String sql =" select count(*) nums from Employaccredit emp where emp.certigieruid = "+userid;
		int count=getCountBySql(sql);
		return count;
	}

	public Employaccredit getEmployaccreditById(int id) {
		// TODO Auto-generated method stub
		return (Employaccredit)super.getObjectById(Employaccredit.class, id);
	}

	public int getCountBySQL(String sql) {
		// TODO Auto-generated method stub
		return super.getCountBySql(sql);
	}

	public List getDateBySqlQuery(String sql,int pageSize,int page) {
		// TODO Auto-generated method stub
		return super.getDateBySqlQuery(sql,pageSize,page);
	}

	public List getpageDateBySqlQuery(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return super.getpageDateBySqlQuery(sql, page, pageSize);
	}

	public int getpages(int count, int pageSize){
		return super.getpages(count, pageSize);
	}
	
}
