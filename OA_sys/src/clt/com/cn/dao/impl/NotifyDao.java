package clt.com.cn.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.INotifyDao;
import clt.com.cn.model.entity.Notify;
import clt.com.cn.model.entity.Notifyfile;
import clt.com.cn.model.entity.Notifyshare;

public class NotifyDao extends BaseDao implements INotifyDao
{
	
	// 添加信息
	public void addNotify( Notify notify )
	{
		super.addObject( notify );
	}
	
	// 添加新闻附件
	public void addNotifyFile( Notifyfile notifyfile )
	{
		super.addObject( notifyfile );
	}
	
	// 添加新闻共享
	public void addNotifyShare( Notifyshare notifyshare )
	{
		super.addObject( notifyshare );
	}
	
	// 查询所有的新闻
	public List getAllNotify( int page )
	{
		String hql = "select no.lineid,no.type,no.notifydate,no.title,no.content,no.userid,ns.status,ns.sharetype,de.deptname,ns.lineid,sm.userno"
		        + " from Notify no,Notifyshare ns,Dept de,Smuser sm where ns.notifyid = no.lineid and ns.deptid= de.lineid and ns.userid=sm.lineid";
		return super.pageQuery( hql , 5 , page );
	}
	
	// 查询自己看到的新闻
	public List getSelfNotify( int deptid , int userid )
	{
		Date date1 = null;
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		String date = sdf.format( now );
		try
		{
			date1 = sdf.parse( date );
			
		}
		catch ( ParseException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String hql = "select no.lineid,no.type,no.notifydate,no.title,no.content,no.userid,ns.status,ns.sharetype,de.deptname,ns.lineid"
		        + " from Notify no,Notifyshare ns,Dept de where ns.notifyid = no.lineid and ns.deptid= de.lineid and ns.status=1 and (ns.sharetype=0";
		if ( deptid != 0 )
		{
			hql = hql + " or ns.deptid=" + deptid;
		}
		if ( userid != 0 )
		{
			hql = hql + " or ns.userid=" + userid;
		}
		hql = hql + ") ";
		hql = hql + " and (ns.sharedate < to_date('" + date
		        + "','YYYY-MM-DD HH24:MI:SS'))";
		;
		return super.getUsersByCondition( hql );
	}
	
	// 查询新闻内容
	public List getNotifyById( int id )
	{
		String hql = "select nofity.lineid,nofity.type,nofity.notifydate,nofity.title,nofity.content,nof.filename "
		        + " from Notify nofity left join  Notifyfile nof on nof.notifyid=nofity.lineid where nofity.lineid="
		        + id;
		return super.getDateBySqlQuery( hql , 0 , 0 );
	}
	
	// 得到更新页面
	public List getUpadatePageById( int id )
	{
		String hql = "select no.lineid,no.type,no.notifydate,no.title,no.content,no.userid,ns.lineid,ns.status,ns.sharetype,ns.userid,ns.deptid,nof.filename,de.deptname,no.deptid,no.currdate,sm.userno"
		        + " from Notify no,Notifyshare ns,Notifyfile nof,Dept de,Smuser sm where ns.notifyid = no.lineid and nof.notifyid=no.lineid and ns.userid= sm.lineid and ns.deptid= de.lineid and no.lineid="
		        + id;
		return super.getUsersByCondition( hql );
	}
	
	// 查询新闻共享
	public Notifyshare getNsById( int id )
	{
		return ( Notifyshare ) super.getObjectById( Notifyshare.class , id );
	}
	
	// 查询新闻内容
	public List< Notifyfile > getNofByNoId( int id )
	{
		String hql = "From Notifyfile nof where nof.notifyid=" + id;
		return super.getUsersByCondition( hql );
	}
	
	// 根据新闻类型查询所有新闻信息
	public List getNotifyByType( int type , int page )
	{
		String hql = "select no.lineid,no.type,no.notifydate,no.title,no.content,no.userid,ns.status,ns.sharetype,de.deptname,ns.lineid,sm.userno"
		        + " from Notify no,Notifyshare ns,Dept de,Smuser sm where ns.notifyid = no.lineid and ns.deptid= de.lineid and ns.userid=sm.lineid and no.type="
		        + type;
		return super.pageQuery( hql , 5 , page );
	}
	
	// 删除新闻
	public void delNotifyById( int id )
	{
		String hql = "delete Notify no where no.lineid=" + id;
		super.delbyhql( hql );
	}
	
	public void delNotifyFileById( int id )
	{
		String hql = "delete Notifyfile nof where nof.notifyid=" + id;
		super.delbyhql( hql );
	}
	
	public void delNotifyshareById( int id )
	{
		String hql = "delete Notifyshare ns where ns.notifyid=" + id;
		super.delbyhql( hql );
	}
	
	// 修改新闻
	public void updateNotifyShare( Notifyshare notifyshare )
	{
		super.updateObject( notifyshare );
	}
	
	public void updateNo( Notify no )
	{
		super.updateObject( no );
	}
	
	public void updateNs( Notifyshare ns )
	{
		super.updateObject( ns );
	}
	
	// 查询自己可见新闻条数
	public int getCount( int deptid , int userid )
	{
		String hql = "select count(*) from Notify no,Notifyshare ns,Dept de where ns.notifyid = no.lineid and ns.deptid= de.lineid and ns.status=1 and (ns.sharetype=0";
		if ( deptid != 0 )
		{
			hql = hql + " or ns.deptid=" + deptid;
		}
		if ( userid != 0 )
		{
			hql = hql + " or ns.userid=" + userid;
		}
		hql = hql + ")";
		List list = super.getUsersByCondition( hql );
		int count = Integer.parseInt( list.get( 0 ).toString() );
		return count;
	}
	
	// 查询所有的新闻条数
	public int getAllCount()
	{
		String hql = "select count(*) from Notify no,Notifyshare ns,Dept de,Smuser sm where ns.notifyid = no.lineid and ns.deptid= de.lineid and ns.userid=sm.lineid";
		List list = super.getUsersByCondition( hql );
		int count = list.size();
		return count;
	}
	
	public int getCountByType( int type )
	{
		String hql = "select count(*) from Notify no,Notifyshare ns,Dept de,Smuser sm where ns.notifyid = no.lineid and ns.deptid= de.lineid and ns.userid=sm.lineid and no.type="
		        + type;
		List list = super.getUsersByCondition( hql );
		int count = Integer.parseInt( list.get( 0 ).toString() );
		return count;
	}
	
	public int getpages( int count , int pageSize )
	{
		int totalpages = 0;
		try
		{
			totalpages = ( count % pageSize == 0 ) ? ( count / pageSize ) : ( count
			        / pageSize + 1 );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return totalpages;
	}
}
