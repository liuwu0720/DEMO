package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IEmployoutDao;
import clt.com.cn.model.entity.*;

public class EmployoutDao extends BaseDao implements IEmployoutDao{
	//查询所有请假条
	public List<Employout> getAllEmo() {
		return super.getAllObject(Employout.class);
	}
	public void delEmoById(int id) {
		super.deleteObjectById(Employout.class, id);
	}
	public void addEmo(Employout emo) {
		super.addObject(emo);
	}
	public List<Smuser> getUserByName(String name) {
		String hql="from Smuser s where s.userno='"+name+"'";
		return super.getAllObjectOrder(hql);
	}
	public List<Employrecord> getEmrByName(String name) {
		String hql="from Employrecord e where e.fileno='"+name+"'";
		return super.getAllObjectOrder(hql);
	}
	public Employout getEmoById(int id) {
		return (Employout)super.getObjectById(Employout.class, id);
	}
	public void updateEmo(Employout emo) {
		super.updateObject(emo);
	}
	public List<Employout> getEmoInfo(String hql, Object... p) {
		return super.getUsersByCondition(hql, p);
	}
	public List<Employrecord> getEmrBylineid(String hql, Object... p) {
		return super.getUsersByCondition(hql, p);
	}
	public int getCount(int deptid) {
		String hql="select count(*) from Employout emo where emo.deptid="+deptid;
		List list=super.getUsersByCondition(hql);
		int count=Integer.parseInt(list.get(0).toString());
		return count;
	}
	public List getEmoBydept(int deptid, int page) {
		String hql="select ehd.lineid,ehd.userid,ehd.recordid,ehd.deptid,emr.employname,ehd.outdate1,ehd.outdate2,ehd.reason,ehd.ischeck" +
		" from Employout as ehd,Employrecord as emr where ehd.recordid=emr.lineid and ehd.deptid="+deptid;
		//System.out.println("********"+hql);
		return super.pageQuery(hql,5,page);
	}
	public int getpages(int count, int pageSize) {
		int totalpages=0;
		try {
			totalpages=(count%pageSize==0)?(count/pageSize):(count/pageSize+1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalpages;
	}
	public int getCountByIs(int ischeck) {
		String hql="select count(*) from Employout ehd where ehd.ischeck="+ischeck;
		List list=super.getUsersByCondition(hql);
		int count=Integer.parseInt(list.get(0).toString());
		return count;
	}
	public List getEmoByIs(int ischeck, int page) {
		String hql="select ehd.lineid,ehd.userid,ehd.recordid,ehd.deptid,emr.employname,ehd.outdate1,ehd.outdate2,ehd.reason,ehd.ischeck" +
		" from Employout as ehd,Employrecord as emr where ehd.recordid=emr.lineid and ehd.ischeck="+ischeck;
		return super.pageQuery(hql,5,page);
	}
	public int getCountBySQL(String sql) {
		// TODO Auto-generated method stub
		return super.getCountBySql(sql);
	}
	public List getDateBySqlQuery(String sql,int pageSize,int page) {
		// TODO Auto-generated method stub
		return super.getDateBySqlQuery(sql,pageSize,page);
	}
}
