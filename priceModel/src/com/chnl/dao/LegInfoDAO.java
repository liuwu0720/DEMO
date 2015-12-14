package com.chnl.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chnl.base.BaseDAO;
import com.chnl.pojo.LegInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * LegInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.chnl.pojo.LegInfo
 * @author MyEclipse Persistence Tools
 */
@Repository
public class LegInfoDAO extends BaseDAO
{
	private static final Logger log = LoggerFactory
	        .getLogger( LegInfoDAO.class );
	// property constants
	public static final String ORIGIN = "origin";
	public static final String DESTINATION = "destination";
	
	public void save( LegInfo transientInstance )
	{
		log.debug( "saving LegInfo instance" );
		try
		{
			// getHibernateTemplate().saveOrUpdate( transientInstance );
			 getSession().saveOrUpdate( transientInstance );
			//getSession().saveOrUpdate( "com.chnl.pojo.LegInfo" ,
			// transientInstance );
			log.debug( "save successful" );
		}
		catch ( RuntimeException re )
		{
			log.error( "save failed" , re );
			re.printStackTrace();
			throw re;
		}
	}
	
	public void delete( LegInfo persistentInstance )
	{
		log.debug( "deleting LegInfo instance" );
		try
		{
			getSession().delete( persistentInstance );
			log.debug( "delete successful" );
		}
		catch ( RuntimeException re )
		{
			log.error( "delete failed" , re );
			throw re;
		}
	}
	
	public LegInfo findById( java.lang.Integer id )
	{
		log.debug( "getting LegInfo instance with id: " + id );
		try
		{
			LegInfo instance = ( LegInfo ) getSession().get(
			        "com.chnl.pojo.LegInfo" , id );
			return instance;
		}
		catch ( RuntimeException re )
		{
			log.error( "get failed" , re );
			throw re;
		}
	}
	
	public List findByExample( LegInfo instance )
	{
		log.debug( "finding LegInfo instance by example" );
		try
		{
			List results = getSession().createCriteria( "com.chnl.pojo.LegInfo" )
			        .add( Example.create( instance ) ).list();
			log.debug( "find by example successful, result size: "
			        + results.size() );
			return results;
		}
		catch ( RuntimeException re )
		{
			log.error( "find by example failed" , re );
			throw re;
		}
	}
	
	public List findByProperty( String propertyName , Object value )
	{
		log.debug( "finding LegInfo instance with property: " + propertyName
		        + ", value: " + value );
		try
		{
			String queryString = "from LegInfo as model where model."
			        + propertyName + "= ?";
			Query queryObject = getSession().createQuery( queryString );
			queryObject.setParameter( 0 , value );
			return queryObject.list();
		}
		catch ( RuntimeException re )
		{
			log.error( "find by property name failed" , re );
			throw re;
		}
	}
	
	public List findByOrigin( Object origin )
	{
		return findByProperty( ORIGIN , origin );
	}
	
	public List findByDestination( Object destination )
	{
		return findByProperty( DESTINATION , destination );
	}
	
	public List findAll()
	{
		log.debug( "finding all LegInfo instances" );
		try
		{
			String queryString = "from LegInfo";
			Query queryObject = getSession().createQuery( queryString );
			return queryObject.list();
		}
		catch ( RuntimeException re )
		{
			log.error( "find all failed" , re );
			throw re;
		}
	}
	
	public LegInfo merge( LegInfo detachedInstance )
	{
		log.debug( "merging LegInfo instance" );
		try
		{
			LegInfo result = ( LegInfo ) getSession().merge( detachedInstance );
			log.debug( "merge successful" );
			return result;
		}
		catch ( RuntimeException re )
		{
			log.error( "merge failed" , re );
			throw re;
		}
	}
	
	public void attachDirty( LegInfo instance )
	{
		log.debug( "attaching dirty LegInfo instance" );
		try
		{
			getSession().saveOrUpdate( instance );
			log.debug( "attach successful" );
		}
		catch ( RuntimeException re )
		{
			log.error( "attach failed" , re );
			throw re;
		}
	}
	
	public void attachClean( LegInfo instance )
	{
		log.debug( "attaching clean LegInfo instance" );
		try
		{
			getSession().lock( instance , LockMode.NONE );
			log.debug( "attach successful" );
		}
		catch ( RuntimeException re )
		{
			log.error( "attach failed" , re );
			throw re;
		}
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @param order
	 * @return List<LegInfo> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-4 下午3:27:27
	 */
	@SuppressWarnings( "unchecked" )
	public List< LegInfo > findAllByPage( int page , int pageSize ,
	        String sort , String order )
	{
		String hql = "from LegInfo order by ? " + " " + order;
		Query query = getSession().createQuery( hql );
		query.setFirstResult( ( page - 1 ) * pageSize );
		query.setMaxResults( pageSize );
		query.setParameter( 0 , sort );
		List< LegInfo > legInfos = query.list();
		return legInfos;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-4 下午6:13:49
	 */
	public void deleteById( int legId )
	{
		String hql = "delete LegInfo as l where l.id=?";
		Query query = getSession().createQuery( hql );
		query.setInteger( 0 , legId );
		query.executeUpdate();

	}
	
	/**
	 * @Description: TODO(incomeprice ratio为空的线路 )
	 * @return List<LegInfo> 返回值描述
	 * @author liuwu
	 * @param legpageSize
	 * @param legpage
	 * @create_date 2014-9-10 下午3:26:43
	 */
	@SuppressWarnings( "unchecked" )
	public Map< String , Object > findLegInfoFileter( int legpage ,
	        int legpageSize )
	{
		String hql = "from LegInfo l where l.id in(select lc.legInfo.id from LegCarInfo lc) order by l.id";
		Query query = getSession().createQuery( hql );
		query.setFirstResult( ( legpage - 1 ) * legpageSize );
		query.setMaxResults( legpageSize );
		List< LegInfo > legInfos = query.list();
		Map< String , Object > map = new HashMap< String , Object >();
		map.put( "rows" , legInfos );
		
		String sql = "select count(*) from LegInfo l where l.id in(select lc.legInfo.id from LegCarInfo lc) order by l.id ";
		Query query2 = getSession().createQuery( sql );
		int totals = Integer.parseInt( query2.uniqueResult().toString() );
		map.put( "total" , totals );
		return map;
	}
}