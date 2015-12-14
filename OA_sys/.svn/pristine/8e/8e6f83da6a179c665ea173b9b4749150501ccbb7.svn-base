package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IEmployoutDao;
import clt.com.cn.model.entity.Employholiday;
import clt.com.cn.model.entity.Employout;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IEmployoutService;

public class EmployoutServiceImp implements IEmployoutService{
	private IEmployoutDao emoDao;

	public void setEmoDao(IEmployoutDao emoDao) {
		this.emoDao = emoDao;
	}
	public List<Employout> getAllEmo() {
		// TODO Auto-generated method stub
		return emoDao.getAllEmo();
	}
	public void delEmoById(int id) {
		emoDao.delEmoById(id);
	}
	public void addEmo(Employout emo) {
		emoDao.addEmo(emo);
	}
	public List<Smuser> getUserByName(String name) {
		List<Smuser> list=emoDao.getUserByName(name);
		if(list.size()!=0)
		{
			return list;
		}
		else
		{
			return null;
		}
	}
	public List<Employrecord> getEmrByName(String name) {
		List<Employrecord> list=emoDao.getEmrByName(name);
		if(list.size()!=0)
		{
			return list;
		}
		else
		{
			return null;
		}
	}
	
	public Employout getEmoById(int id) {
		return emoDao.getEmoById(id);
	}
	public void updateEmo(Employout emo) {
		emoDao.updateEmo(emo);
	}
	public List<Employout> getEmoByIscheck(int ischeck) {
		String hql="from Employout emo where emo.ischeck=?";
		List<Employout> ulist=emoDao.getEmoInfo(hql, ischeck);
		if(ulist.size()>0)
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	public List<Employout> getEmoByIscheck(int ischeck, int lineid) {
		String hql="from Employout ehd where ehd.ischeck=? and ehd.opuser.lineid=?";
		List<Employout> ulist=emoDao.getEmoInfo(hql, ischeck,lineid);
		if(ulist.size()>0)
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	public List<Employout> getEmoByChecked(int lineid) {
		String hql="from Employout ehd where (ehd.ischeck=1 or ehd.ischeck=2) and ehd.opuser.lineid=?";
		List<Employout> ulist=emoDao.getEmoInfo(hql,lineid);
		if(ulist.size()>0)
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	public Employrecord getEmrBylineid(int recordid) {
		String hql="from Employrecord emr where emr.lineid="+recordid;
		List<Employrecord> ulist= emoDao.getEmrBylineid(hql);
		if(ulist.size()>0)
		{
			return ulist.get(0);
		}
		else
		{
			return null;
		}
	}
	public int getCount(int deptid) {
		return emoDao.getCount(deptid);
	}
	public List getEmoBydept(int deptid, int page) {
		List ulist= emoDao.getEmoBydept(deptid,page);
		
		if(ulist.size()>0)
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	public int getpages(int count, int pageSize) {
		return emoDao.getpages(count, pageSize);
	}
	public int getCountByIs(int ischeck) {
		return emoDao.getCountByIs(ischeck);
	}
	public List getEmoByIs(int ischeck, int page) {
		List ulist= emoDao.getEmoByIs(ischeck, page);
		if(ulist.size()>0)
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	public int getCountBySQL(String sql) {
		// TODO Auto-generated method stub
		return emoDao.getCountBySQL(sql);
	}
	public List getDateBySqlQuery(String sql,int pageSize,int page) {
		// TODO Auto-generated method stub
		return emoDao.getDateBySqlQuery(sql, pageSize, page);
	}

}

