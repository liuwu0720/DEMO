package clt.com.cn.dao.impl;

import java.util.ArrayList;
import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IModuleDao;
import clt.com.cn.model.entity.Privilege;
import clt.com.cn.model.entity.Userprivilege;

public class ModuleDao extends BaseDao implements IModuleDao
{
	
	public List getAllModule( String hql )
	{
		// TODO Auto-generated method stub
		return super.getAllObjectOrder( hql );
	}
	
	// 查询新闻条数
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
	
	public String getHandleMessageCount( int deptid , int userid )
	{
		// 获取该用户待审批的记录数
		
		List< String > tables = new ArrayList< String >();
		tables.add( "Employholiday" );
		tables.add( "Employout" );
		tables.add( "Employovertime" );
		tables.add( "Employattendance" );
		tables.add( "Employtrip" );
		tables.add( "Employrecruitment" );
		int count = 0;
		
		String sql = "  select d.deptid from userSchedule d where d.userid=" + userid;
		List< Object > hqlist = super.getDateBySqlQuery( sql , 0 , 0 );
		if ( hqlist.size() > 0 )
		{
			String depts = "";
			for ( int i = 0 ; i < hqlist.size() ; i++ )
			{
				if ( i == hqlist.size() - 1 )
				{
					depts += hqlist.get( i );
				}
				else
				{
					depts += hqlist.get( i ) + ",";
				}
			}
			
			// 新员工入职
			if ( count == 0 )
			{
				sql = " select count(*) from employrecord emp,dept d,position po where emp.deptid=d.lineid and emp.positionid=po.lineid and getCompanyIDByDeptID(d.lineid) in ("
				        + depts + ")  and emp.hrstatus=0  ";
				count += getCountBySql( sql );
				System.out.println( "  新员工入职 待HR 确认 " + count );
			}
			
			// 请假/待确认
			if ( count == 0 )
			{
				for ( int i = 0 ; i < tables.size() ; i++ )
				{
					String sql3 = " select count(*) from "
					        + tables.get( i )
					        + " emp,dept d where "
					        + " d.lineid = emp.deptid and getCompanyIDByDeptID(d.lineid) in ("
					        + depts + " ) and emp.ischeck=2 and emp.hrstatus=0 ";
					count += getCountBySql( sql3 );
					
					if ( count > 0 )
					{
						System.out.println( " 请假 / 待HR 确认 " + count );
						break;
					}
				}
				
			}
			System.out.println( "last count >> " + count );
			
			// 获取档案申请记录 已审批通过的
			sql = "   select count(*) coun from employrecordcheck e,employrecord emp ,dept d where e.recordid=emp.lineid and emp.deptid=d.lineid and getCompanyIDByDeptID(d.lineid) in ("
			        + depts + ") and   e.ischeck=2 and e.status>=0 and e.hrstatus=0 ";
			count += getCountBySql( sql );
			
		}
		
		// 考勤待审批
		
		for ( String str : tables )
		{
			if ( count == 0 )
			{
				sql = "select count(*)  from "
				        + str
				        + " emp where emp.ischeck=0 and (emp.checkuserid="
				        + userid
				        + " or emp.checkuserid in(select empdit.certigieruid from employaccredit empdit where empdit.authorizeruid="
				        + userid + " )) ";
				count += getCountBySql( sql );
			}
		}
		System.out.println( "考勤待审批 " + count );
		
		// 档案待审批
		String sql1 = " select count(*) coun from employrecordcheck e,smuser sm  where e.checkusid=sm.lineid and e.status>=0  and e.ischeck=0 and sm.lineid="
		        + userid;
		count += getCountBySql( sql1 );
		
		if ( count > 0 )
		{
			return "new";
		}
		else
		{
			return "0";
		}
	}
	
	public List< Privilege > getAllPrivilege()
	{
		String hql = "from Privilege pr order by pr.privilegeno asc";
		return super.getAllObjectOrder( hql );
	}
	
	public Privilege gerPrivilegeById( int id )
	{
		return ( Privilege ) super.getObjectById( Privilege.class , id );
	}
	
	public void addUserPrivilege( Userprivilege upr )
	{
		super.addObject( upr );
	}
	
	public List< Userprivilege > getUserPrivilege( int userid )
	{
		String hql = "from Userprivilege upr where upr.userid=" + userid;
		return super.getUsersByCondition( hql );
	}
	
	public void delUserPrivilege( int userid )
	{
		String hql = "delete Userprivilege upr where upr.userid=" + userid;
		super.delbyhql( hql );
	}
}
