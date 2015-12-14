package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IEmployovertimeDao;
import clt.com.cn.dao.IEmployrecruitmentDao;
import clt.com.cn.model.entity.Employholiday;
import clt.com.cn.model.entity.Employovertime;
import clt.com.cn.model.entity.EmployovertimeType;
import clt.com.cn.model.entity.Employrecruitment;
import clt.com.cn.model.entity.EmployrecruitmentType;

public class EmployrecruitmentDao extends BaseDao  implements IEmployrecruitmentDao {

	public void delEmrById(int id) {
		// TODO Auto-generated method stub
		super.deleteObjectById(Employrecruitment.class, id);
	}

	public void addEmr(Employrecruitment emr) {
		// TODO Auto-generated method stub
		super.addObject(emr);
	}

	public void updateEmr(Employrecruitment emr) {
		// TODO Auto-generated method stub
		super.updateObject(emr);
	}

	public Employrecruitment getEmployrecruitmentById(int id) {
		// TODO Auto-generated method stub
		return (Employrecruitment)super.getObjectById(Employrecruitment.class, id);
	}

	public List getDateBySqlQuery(String sql,int pageSize,int page) {
		// TODO Auto-generated method stub
		return super.getDateBySqlQuery(sql,pageSize,page);
	}

	public List getpageDateBySqlQuery(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return super.getpageDateBySqlQuery(sql, page, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<Employrecruitment> getObjInfoByCondition(String hql, Object... p) {
		return super.getUsersByCondition(hql, p);
	}
	
	public int getCountBySQL(String sql) {
		// TODO Auto-generated method stub
		return super.getCountBySql(sql);
	}

	public List<EmployrecruitmentType> getAllEmployrecruitmentType() {
		// TODO Auto-generated method stub
		return super.getAllObject(EmployrecruitmentType.class);
	}

	public EmployrecruitmentType getEmployrecruitmentType(int lineid) {
		// TODO Auto-generated method stub
		return (EmployrecruitmentType)super.getObjectById(EmployrecruitmentType.class, lineid);
	}
}
