package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.*;

public interface IEmployoutDao {
	public abstract List<Employout> getAllEmo();
	
	public abstract void delEmoById(int id);
	
	public abstract void addEmo(Employout emo);
	//根据员工编号查询Sumer信息
	public abstract List<Smuser> getUserByName(String name);
	//根据员工编号查询员工档案信息
	public abstract List<Employrecord> getEmrByName(String name);
	
	public abstract Employout getEmoById(int id);
	//查询员工部门id
	public abstract List<Employrecord> getEmrBylineid(String hql,Object...p);
	
	public abstract void updateEmo(Employout emo);
	
	//根据条件查询
	public abstract List<Employout> getEmoInfo(String hql,Object...p);
	
	public abstract int getCount(int deptid);
	
	public abstract int getpages(int count,int pageSize);
	
	public abstract List getEmoBydept(int deptid,int page);
	
	public abstract int getCountByIs(int ischeck);
	//根据条件查询(分页)
	public abstract List getEmoByIs(int ischeck,int page);
	public int getCountBySQL(String sql);
	public List getDateBySqlQuery(String sql,int pageSize,int page);
}
