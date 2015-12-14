package clt.com.cn.base;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDao extends HibernateDaoSupport
{
	// 添加
	public void addObject( Object o )
	{
		super.getHibernateTemplate().save( o );
	}
	
	// 删除
	public void deleteObject( Object o )
	{
		super.getHibernateTemplate().delete( o );
	}
	
	// 修改
	public void updateObject( Object o )
	{
		super.getHibernateTemplate().update( o );
	}
	
	public void saveUpdate(Object o){
		super.getHibernateTemplate().saveOrUpdate( o );
	}
	
	// 修改
	public void mergeObject( Object o )
	{
		super.getHibernateTemplate().merge( o );
	}
	
	// 查询
	public List getAllObject( Class c )
	{
		return super.getHibernateTemplate().find(
		        "from " + c.getName() + " order by lineid asc " );
	}
	
	// 根据ID查询
	public Object getObjectById( Class c , Serializable id )
	{
		return super.getHibernateTemplate().get( c , id );
	}
	
	// 根据ID删除
	public void deleteObjectById( Class c , Serializable id )
	{
		deleteObject( getObjectById( c , id ) );
	}
	
	// hql查询
	public List getUsersByCondition( String hql , Object ... p )
	{
		return super.getHibernateTemplate().find( hql , p );
	}
	
	// 查询
	public List getAllObjectOrder( String hql )
	{
		return super.getHibernateTemplate().find( hql );
	}
	
	// 分页查询
	public List pageQuery( final String hql , final Integer pageSize ,
	        final Integer page , final Object ... p )
	{
		return super.getHibernateTemplate().executeFind( new HibernateCallback()
		{
			
			public Object doInHibernate( Session session ) throws HibernateException ,
			        SQLException
			{
				Query query = session.createQuery( hql );
				if ( p != null )
				{
					for ( int i = 0 ; i < p.length ; i++ )
					{
						query.setParameter( i , p[i] );
					}
				}
				if ( pageSize != null && pageSize > 0 && page != null && page > 0 )
				{
					query.setFirstResult( ( page - 1 ) * pageSize ).setMaxResults(
					        pageSize );
				}
				return query.list();
			}
			
		} );
	}
	
	// 分页查询
	public List pageSqlQuery( final String hql , final Integer pageSize ,
	        final Integer page , final Object ... p )
	{
		return super.getHibernateTemplate().executeFind( new HibernateCallback()
		{
			
			public Object doInHibernate( Session session ) throws HibernateException ,
			        SQLException
			{
				// TODO Auto-generated method stub
				Query query = session.createSQLQuery( hql );
				if ( p != null )
				{
					for ( int i = 0 ; i < p.length ; i++ )
					{
						query.setParameter( i , p[i] );
					}
				}
				if ( pageSize != null && pageSize > 0 && page != null && page > 0 )
				{
					query.setFirstResult( ( page - 1 ) * pageSize ).setMaxResults(
					        pageSize );
				}
				return query.list();
			}
			
		} );
	}
	
	public List getDateBySqlQuery( final String sql , final int pageSize , final int page )
	{
		System.out.println( "sq;;;;;" + sql );
		return super.getHibernateTemplate().executeFind( new HibernateCallback()
		{
			
			public Object doInHibernate( Session session ) throws HibernateException ,
			        SQLException
			{
				// TODO Auto-generated method stub
				Query query = session.createSQLQuery( sql );
				if ( pageSize > 0 && page > 0 )
				{
					query.setFirstResult( ( page - 1 ) * pageSize ).setMaxResults(
					        pageSize );
				}
				return query.list();
			}
			
		} );
	}
	
	public List getpageDateBySqlQuery( final String sql , final int page ,
	        final int pageSize )
	{
		return super.getHibernateTemplate().executeFind( new HibernateCallback()
		{
			
			public Object doInHibernate( Session session ) throws HibernateException ,
			        SQLException
			{
				// TODO Auto-generated method stub
				Query query = session.createSQLQuery( sql );
				if ( page > 0 )
				{
					query.setFirstResult( ( page - 1 ) * pageSize ).setMaxResults(
					        pageSize );
				}
				return query.list();
			}
		} );
	}
	
	public int getCountBySql( final String sql )
	{
		List countlist = super.getHibernateTemplate().executeFind(
		        new HibernateCallback()
		        {
			        
			        public Object doInHibernate( Session session )
			                throws HibernateException , SQLException
			        {
				        // TODO Auto-generated method stub
				        Query query = session.createSQLQuery( sql );
				        return query.list();
			        }
		        } );
		int count = 0;
		count = Integer.parseInt( countlist.get( 0 ).toString() );
		return count;
	}
	
	public int getCountByHql( final String hql )
	{
		final String hqls = getCountSQLBysql( hql );
		List countlist = super.getHibernateTemplate().executeFind(
		        new HibernateCallback()
		        {
			        
			        public Object doInHibernate( Session session )
			                throws HibernateException , SQLException
			        {
				        // TODO Auto-generated method stub
				        Query query = session.createQuery( hqls );
				        return query.list();
			        }
		        } );
		int count = 0;
		count = Integer.parseInt( countlist.get( 0 ).toString() );
		return count;
	}
	
	public String getCountSQLBysql( String sql )
	{
		String countSQL = "";
		int beginPos = sql.toLowerCase().indexOf( "from" );
		if ( beginPos != - 1 )
		{
			countSQL = "select count(*) " + sql.substring( beginPos );
		}
		return countSQL;
	}
	
	// hql删除
	public void delbyhql( final String hql )
	{
		this.getHibernateTemplate().execute( new HibernateCallback()
		{
			public Object doInHibernate( Session session ) throws SQLException ,
			        HibernateException
			{
				
				Query query = session.createQuery( hql );
				query.executeUpdate();
				return null;
			}
		} );
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
	
	@SuppressWarnings( "unchecked" )
	public void execSQL( final String sql )
	{
		System.out.println( "execSQL " + sql );
		this.getHibernateTemplate().execute( new HibernateCallback()
		{
			public Object doInHibernate( Session session ) throws SQLException ,
			        HibernateException
			{
				
				SQLQuery query = session.createSQLQuery( sql );
				query.executeUpdate();
				return null;
			}
		} );
	}
	
	@SuppressWarnings( "unchecked" )
	public void sendMails( final String rcver_name , final String rcver_mail ,
	        final String subject , final String subContents )
	{
		final String sql = " {call sendmail_noloctoday (?,?,?,?)}";
		this.getHibernateTemplate().execute( new HibernateCallback()
		{
			public Object doInHibernate( Session session ) throws SQLException ,
			        HibernateException
			{
				
				SQLQuery query = session.createSQLQuery( sql );
				query.setString( 0 , rcver_name );
				query.setString( 1 , rcver_mail );
				query.setString( 2 , subject );
				query.setString( 3 , subContents );
				
				return query.executeUpdate();
			}
		} );
	}
}
