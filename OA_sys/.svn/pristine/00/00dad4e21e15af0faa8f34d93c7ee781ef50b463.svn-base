package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.*;

public interface INotifyDao {
	//添加新闻
	public abstract void addNotify(Notify notify);
	//添加新闻附件
	public abstract void addNotifyFile(Notifyfile notifyfile);
	//添加新闻共享
	public abstract void addNotifyShare(Notifyshare notifyshare);
	
	//查询所有的新闻信息
	public abstract List getAllNotify(int page);
	//查询新闻内容
	public abstract List getNotifyById(int id);
	//查询新闻共享信息
	public abstract Notifyshare getNsById(int id);
	//查询新闻附件
	public abstract List<Notifyfile> getNofByNoId(int id);
	//得到跟新页面
	public abstract List getUpadatePageById(int id);
	//根据新闻类型查询所有新闻信息
	public abstract List getNotifyByType(int type,int page);
	
	
	
	//删除新闻
	public abstract void delNotifyById(int id);
	
	public abstract void delNotifyFileById(int id);
	
	public abstract void delNotifyshareById(int id);
	
	//修改新闻（启用 禁用）
	public abstract void updateNotifyShare(Notifyshare notifyshare);
	
	public abstract void updateNo(Notify no);
	
	public abstract void updateNs(Notifyshare ns);
	
	//查询可看的新闻
	public abstract List getSelfNotify(int deptid,int userid);
	
	//查询自己可见新闻条数
	public abstract int getCount(int deptid,int userid);
	//查询所有新闻条数
	public abstract int getAllCount();
	//查询指定类型的新闻条数
	public abstract int getCountByType(int type);
	//总页数
	public abstract int getpages(int count,int pageSize);
}
