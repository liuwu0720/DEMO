package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.INotifyDao;
import clt.com.cn.model.entity.Notify;
import clt.com.cn.model.entity.Notifyfile;
import clt.com.cn.model.entity.Notifyshare;
import clt.com.cn.service.INotifyService;

public class NotifyServiceImp implements INotifyService{
	private INotifyDao notifyDao;

	public void setNotifyDao(INotifyDao notifyDao) {
		this.notifyDao = notifyDao;
	}
	
	//----------------------添加-------------------
	//添加新闻
	public void addNotify(Notify notify) {
		notifyDao.addNotify(notify);
	}
	//添加新闻附件
	public void addNotifyFile(Notifyfile notifyfile) {
		notifyDao.addNotifyFile(notifyfile);
	}
	//添加新闻共享
	public void addNotifyShare(Notifyshare notifyshare) {
		notifyDao.addNotifyShare(notifyshare);
	}
	
	//-----------------------查询--------------------
	//查询新闻信息
	public List getAllNotify(int page) {
		List ulist=  notifyDao.getAllNotify(page);
		if(ulist.size()>0)
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	public List getNotifyById(int id) {
		List ulist=  notifyDao.getNotifyById(id);
		if(ulist.size()>0)
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	public Notifyshare getNsById(int id) {
		return notifyDao.getNsById(id);
	}
	public List<Notifyfile> getNofByNoId(int id) {
		List ulist=  notifyDao.getNofByNoId(id);
		if(ulist.size()>0)
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	public List getUpadatePageById(int id) {
		List ulist=  notifyDao.getUpadatePageById(id);
		if(ulist.size()>0)
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	public List getSelfNotify(int deptid, int userid) {
		List ulist=  notifyDao.getSelfNotify(deptid, userid);
		if(ulist.size()>0)
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	public int getCount(int deptid, int userid) {
		return notifyDao.getCount(deptid, userid);
	}
	public int getAllCount() {
		return notifyDao.getAllCount();
	}
	public int getCountByType(int type) {
		return notifyDao.getCountByType(type);
	}
	
	//总页数
	public int getpages(int count, int pageSize) {
		return notifyDao.getpages(count, pageSize);
	}
	public List getNotifyByType(int type,int page) {
		List ulist=  notifyDao.getNotifyByType(type,page);
		if(ulist.size()>0)
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	
	
	
	//-----------------------删除-------------------
	public void delNotifyById(int id) {
		notifyDao.delNotifyById(id);
	}
	public void delNotifyFileById(int id) {
		notifyDao.delNotifyFileById(id);
	}
	public void delNotifyshareById(int id) {
		notifyDao.delNotifyshareById(id);
	}
	//-----------------------修改--------------------
	public void updateNotifyShare(Notifyshare notifyshare) {
		notifyDao.updateNotifyShare(notifyshare);
	}
	public void updateNo(Notify no) {
		notifyDao.updateNo(no);
	}
	public void updateNs(Notifyshare ns) {
		notifyDao.updateNs(ns);
	}
}
