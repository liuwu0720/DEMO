package clt.com.cn.dao.impl;

import java.io.File;
import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IUploadDao;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.FilesMapping;
import clt.com.cn.model.entity.Fileshare;
import clt.com.cn.model.entity.Smfile;
import clt.com.cn.model.entity.Smuser;

public class UploadDao extends BaseDao implements IUploadDao
{
	// 根据Uname查询其相关信息
	public List< Smuser > getUserByName( String name )
	{
		String hql = "from Smuser s where s.userno='" + name + "'";
		return super.getAllObjectOrder( hql );
	}
	
	public List< Employrecord > getEmrByName( String name )
	{
		String hql = "from Employrecord e where e.fileno='" + name + "'";
		return super.getAllObjectOrder( hql );
	}
	
	public Employrecord getEmrById( int id )
	{
		return ( Employrecord ) super.getObjectById( Employrecord.class , id );
	}
	
	public void addFile( Smfile sm )
	{
		super.addObject( sm );
	}
	
	public void saveFileMapping( FilesMapping sm )
	{
		super.addObject( sm );
	}
	
	// 根据上传文件的id,删除对应的文件
	public void delFile( int smId )
	{
		Smfile sm = ( Smfile ) super.getObjectById( Smfile.class , smId );
		if ( sm != null )
		{
			String filepath = sm.getFilepath() + sm.getFilename();
			File file = new File( filepath );
			if ( ! file.isDirectory() && file.exists() )
			{
				file.delete();
			}
			String hql = "from Fileshare t where t.fileid = " + smId + "";
			List< Fileshare > list = super.getAllObjectOrder( hql );
			for ( Fileshare fs : list )
			{
				super.deleteObject( fs );
			}
			super.deleteObject( sm );
		}
	}
	
	public List< Smfile > getAllFile( int page )
	{
		String hql = "from Smfile";
		return super.pageQuery( hql , 5 , page );
		// return super.getAllObject(Smfile.class);
	}
	
	public List< Smfile > getSelfile( int fileid , int page )
	{
		String hql = "from Smfile sm where sm.fileid=" + fileid;
		return super.pageQuery( hql , 5 , page );
	}
	
	// -------------------删--------------------
	public void delSmfileById( int id )
	{
		super.deleteObjectById( Smfile.class , id );
	}
	
	public void delSharefileById( int id )
	{
		super.deleteObjectById( Fileshare.class , id );
	}
	
	// ---------------总条数 总页面-------------------
	public int getCount()
	{
		String hql = "select count(*) from Smfile";
		List list = super.getUsersByCondition( hql );
		int count = Integer.parseInt( list.get( 0 ).toString() );
		return count;
	}
	
	public int getCountByFileid( int fileid )
	{
		String hql = "select count(*) from Smfile sm where sm.fileid=" + fileid;
		List list = super.getUsersByCondition( hql );
		int count = Integer.parseInt( list.get( 0 ).toString() );
		return count;
	}
	
	public int getCountShareFile( int fileid )
	{
		String sql = "select  count(*) coun "
		        + " from Smfile sf,Fileshare fs,Dept de,Smuser sm where fs.fileid=sf.lineid and de.lineid=fs.deptid and sf.userid=sm.lineid and sf.fileid="
		        + fileid;
		
		int count = super.getCountBySql( sql );
		return count;
	}
	
	public int getCountDownShareFile( int deptid , int userid )
	{
		String sql = "select count(1) from ( select a.lineid,b.sharetype,a.filename,emp.employname,b.memo,d.deptname,b.sharedate from "
		        + " smfile a, fileshare b,dept d,employrecord emp where a.lineid=b.fileid and emp.deptid = d.lineid and a.fileid=emp.lineid and b.status=1 and b.sharetype=0 "
		        + " union all "
		        + " select a.lineid,b.sharetype,a.filename,emp.employname,b.memo,d.deptname,b.sharedate from "
		        + " smfile a, fileshare b,dept d,employrecord emp where a.lineid=b.fileid  "
		        + " and emp.deptid = d.lineid and a.fileid=emp.lineid  "
		        + " and b.status=1 and b.sharetype=1 "
		        + "    and "
		        + deptid
		        + " in ("
		        + " select fm.deptid from filemapping fm where fm.fileid=a.lineid )"
		        + "  union all "
		        + " select a.lineid,b.sharetype,a.filename,emp.employname,b.memo,d.deptname,b.sharedate "
		        + " from  smfile a, fileshare b,dept d,employrecord emp "
		        + " where a.lineid=b.fileid  "
		        + " and emp.deptid = d.lineid and a.fileid=emp.lineid "
		        + " and b.status=1 and b.sharetype=2 "
		        + "     and "
		        + userid
		        + " in"
		        + " ( "
		        + "    select fm.userid from filemapping fm where fm.fileid=a.lineid"
		        + " )) ";
		int count = super.getCountBySql( sql );
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
	
	public void shareFile( Fileshare fs )
	{
		super.addObject( fs );
	}
	
	public List getUserInfo()
	{
		String sql = " select sm.lineid,d.deptname, emr.employname from Smuser sm,Employrecord emr,Dept d  where sm.recordid=emr.lineid and emr.deptid=d.lineid ";
		return super.getDateBySqlQuery( sql , 0 , 0 );
	}
	
	public List getShareFile( int fileid , int page )
	{
		String sql = "select fs.lineid,sf.filename,fs.sharetype,fs.userid,fs.deptid,de.deptname,fs.sharedate,fs.memo,fs.status,sm.userno"
		        + " from Smfile sf,Fileshare fs,Dept de,Smuser sm where fs.fileid=sf.lineid and de.lineid=fs.deptid and sf.userid=sm.lineid and sf.fileid="
		        + fileid;
		return super.pageSqlQuery( sql , 5 , page );
	}
	
	public Fileshare getShareFileById( int lineid )
	{
		return ( Fileshare ) super.getObjectById( Fileshare.class , lineid );
	}
	
	public void updateShareFile( Fileshare fs )
	{
		super.updateObject( fs );
	}
	
	public List getUplodShareFile( int page , int deptid , int userid )
	{
		
		// 如果 用户也要共享的话 select
		// 可用union all 连接部门共享结果 and b.userid=
		/* String sql ="  select  b.lineid,b.sharetype,a.filename,emp.employname,b.memo,d.deptname,b.sharedate from smfile a,fileshare b,dept d,employrecord emp where a.lineid = b.fileid and b.deptid=d.lineid and emp.lineid = a.fileid and b.status=1 and b.sharetype=0 "+
				 	" union all"+
				 " select b.lineid,b.sharetype,a.filename,emp.employname,b.memo,d.deptname,b.sharedate from smfile a,fileshare b,dept d,employrecord emp where a.lineid = b.fileid and b.deptid=d.lineid and emp.lineid = a.fileid and b.status=1  and "+deptid+" in "+
		         "("+
		           "     select dt.lineid "+
		                      " from dept dt "+
		             " start with dt.lineid = b.deptid "+
		             " connect by Prior  dt.lineid = dt.pid "+
		          ")";*/
		/*String sql = "select a.lineid,b.sharetype,a.filename,emp.employname,b.memo,d.deptname,b.sharedate from "
		        + " smfile a, fileshare b,dept d,employrecord emp where a.lineid=b.fileid and b.deptid = d.lineid and a.fileid=emp.lineid and b.status=1 and b.sharetype=0 "
		        + " union all"
		        + " select a.lineid,b.sharetype,a.filename,emp.employname,b.memo,d.deptname,b.sharedate from "
		        + " smfile a, fileshare b,dept d,employrecord emp where a.lineid=b.fileid and b.deptid = d.lineid and a.fileid=emp.lineid and b.status=1 and b.sharetype=1 "
		        + " and d.lineid in "
		        + " ( select dp.deptid from dept_mapping dp where dp.deptmappid ="
		        + deptid
		        + " union  select de.lineid from dept de where de.lineid="
		        + deptid + " ) ";*/
		
		// 查询 共享所有的 union all 共享部门的 union all 共享给人的
		String sql = "select * from ( select a.lineid,b.sharetype,a.filename,emp.employname,b.memo,d.deptname,b.sharedate from "
		        + " smfile a, fileshare b,dept d,employrecord emp where a.lineid=b.fileid and emp.deptid = d.lineid and a.fileid=emp.lineid and b.status=1 and b.sharetype=0 "
		        + " union all "
		        + " select a.lineid,b.sharetype,a.filename,emp.employname,b.memo,d.deptname,b.sharedate from "
		        + " smfile a, fileshare b,dept d,employrecord emp where a.lineid=b.fileid  "
		        + " and emp.deptid = d.lineid and a.fileid=emp.lineid  "
		        + " and b.status=1 and b.sharetype=1 "
		        + "    and "
		        + deptid
		        + " in ("
		        + " select fm.deptid from filemapping fm where fm.fileid=a.lineid )"
		        + "  union all "
		        + " select a.lineid,b.sharetype,a.filename,emp.employname,b.memo,d.deptname,b.sharedate "
		        + " from  smfile a, fileshare b,dept d,employrecord emp "
		        + " where a.lineid=b.fileid  "
		        + " and emp.deptid = d.lineid and a.fileid=emp.lineid "
		        + " and b.status=1 and b.sharetype=2 "
		        + "     and "
		        + userid
		        + " in"
		        + " ( "
		        + "    select fm.userid from filemapping fm where fm.fileid=a.lineid"
		        + " )  ) fdate order by fdate.sharedate desc ";
		System.out.println( "hql >> 1" + sql );
		return super.pageSqlQuery( sql , 5 , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-31 上午11:29:44
	 */
	public Smfile getSmFileById( int id )
	{
		// TODO Auto-generated method stub
		return ( Smfile ) super.getObjectById( Smfile.class , id );
	}
}
