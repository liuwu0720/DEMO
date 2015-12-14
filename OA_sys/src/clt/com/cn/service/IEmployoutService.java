package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.*;

public interface IEmployoutService {
	public abstract List<Employout> getAllEmo();
	public abstract void delEmoById(int id);
	public abstract void addEmo(Employout emo);
	//根据Uname查询相关信息
	public abstract List<Smuser> getUserByName(String name);
	//根据Uname查询员工档案相关信息
	public abstract List<Employrecord> getEmrByName(String name);
	
	public abstract Employout getEmoById(int id);
	
	public abstract void updateEmo(Employout emo);
	
	public abstract List<Employout> getEmoByIscheck(int ischeck);
	//根据状态ID查询个人信息
	public abstract List<Employout> getEmoByIscheck(int ischeck,int lineid);
	//请假历史记录
	public abstract List<Employout> getEmoByChecked(int lineid);
	//查询员工部门id
	public abstract Employrecord getEmrBylineid(int recordid);
	//查询部门所有的请假信息
	public abstract List getEmoBydept(int deptid,int page);
	
	public abstract int getCount(int deptid);
	
	public abstract int getpages(int count,int pageSize);
	
	public abstract int getCountByIs(int ischeck);
	//根据条件查询(分页)
	public abstract List getEmoByIs(int ischeck,int page);
	public int getCountBySQL(String sql);
	public List getDateBySqlQuery(String sql,int pageSize,int page);

}
