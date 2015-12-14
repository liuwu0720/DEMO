package clt.com.cn.dao.impl;

import java.security.MessageDigest;
import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IUserDao;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Role;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.model.entity.UserApprove;
import clt.com.cn.model.entity.UserSchedule;

public class UserDao extends BaseDao implements IUserDao
{
	/* (non-Javadoc)
	 * @see com.dao.IUserDao#addUser(com.pojos.Users)
	 */
	public void addUser( Smuser u )
	{
		super.addObject( u );
	}
	
	public void saveUserSchedule( UserSchedule u )
	{
		super.addObject( u );
	}
	
	public void saveUserApprove( UserApprove us )
	{
		super.addObject( us );
	}
	
	// 根据id查询档案信息
	public Employrecord getEmrById( int id )
	{
		return ( Employrecord ) super.getObjectById( Employrecord.class , id );
	}
	
	@SuppressWarnings( "unchecked" )
	public List< Smuser > getUsersByCondition( String hql , Object ... p )
	{
		// TODO Auto-generated method stub
		System.out.println( "hql " + hql );
		return super.getUsersByCondition( hql , p );
	}
	
	public List getAllUsers( String hql , int page , Object ... p )
	{
		// TODO Auto-generated method stub
		return super.pageQuery( hql , 5 , page );
	}
	
	public Smuser getUserById( int uid )
	{
		// TODO Auto-generated method stub
		return ( Smuser ) super.getObjectById( Smuser.class , uid );
	}
	
	public void updateUser( Smuser u )
	{
		// TODO Auto-generated method stub
		super.updateObject( u );
	}
	
	public void deleteUserById( int uid )
	{
		// TODO Auto-generated method stub
		super.deleteObjectById( Smuser.class , uid );
	}
	
	public List< Smuser > checkUser( String hql , Object ... p )
	{
		// TODO Auto-generated method stub
		return super.getUsersByCondition( hql , p );
	}
	
	public List getUserInfo( String hql , int page , Object ... p )
	{
		// TODO Auto-generated method stub
		return super.pageQuery( hql , 5 , page );
	}
	
	// MD5加密算法
	public String MD5( String s )
	{
		char hexDigits[] = { '0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9' ,
		        'A' , 'B' , 'C' , 'D' , 'E' , 'F' };
		try
		{
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance( "MD5" );
			// 使用指定的字节更新摘要
			mdInst.update( btInput );
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for ( int i = 0 ; i < j ; i++ )
			{
				byte byte0 = md[i];
				str[k++ ] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++ ] = hexDigits[byte0 & 0xf];
			}
			return new String( str );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	public int getCount()
	{
		String hql = "select count(*) from Smuser";
		List list = super.getUsersByCondition( hql );
		int count = Integer.parseInt( list.get( 0 ).toString() );
		return count;
	}
	
	public int getCountByAdmin( int admin )
	{
		String hql = "select count(*) from Smuser sm where sm.admin=?";
		List list = super.getUsersByCondition( hql , admin );
		int count = Integer.parseInt( list.get( 0 ).toString() );
		return count;
	}
	
	public List< Smuser > getUsersByAdmin( int admin )
	{
		String hql = " from Smuser sm where sm.admin=?";
		List< Smuser > list = super.getUsersByCondition( hql , admin );
		return list;
	}
	
	public List< Smuser > getUsersBygtAdmin( int admin )
	{
		String hql = " from Smuser sm where sm.admin > ?";
		List< Smuser > list = super.getUsersByCondition( hql , admin );
		return list;
	}
	
	public int getCountByUserno( String userno )
	{
		String hql = "select count(*) from Smuser sm where sm.userno LIKE '%" + userno
		        + "%'";
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
	
	// 查询
	public List< Role > getRolename()
	{
		return super.getAllObject( Role.class );
		
	}
	
	public List getDateBySqlQuery( String sql , int pageSize , int page )
	{
		return super.getDateBySqlQuery( sql , pageSize , page );
	}
	
	public List getpageDateBySqlQuery( String sql , int page , int pageSize )
	{
		// TODO Auto-generated method stub
		return super.getDateBySqlQuery( sql , pageSize , page );
	}
	
	public List< Smuser > getUserByUserno( String userno )
	{
		String hql = "from Smuser s where s.userno='" + userno + "'";
		return super.getAllObjectOrder( hql );
	}
	
	public int getCountBySql( String sql )
	{
		// TODO Auto-generated method stub
		return super.getCountBySql( sql );
	}
	
	public Smuser getUserApprove( Smuser sm )
	{
		String hql = "from UserApprove s where s.userid=" + sm.getLineid();
		List< UserApprove > smlist = super.getAllObjectOrder( hql );
		Smuser smus = null;
		if ( smlist.size() > 0 )
		{
			UserApprove us = smlist.get( 0 );
			smus = ( Smuser ) super.getObjectById( Smuser.class , us.getApproveusid() );
		}
		
		return smus;
	}
	
}
