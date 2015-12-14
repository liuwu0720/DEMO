package com.chnl.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chnl.base.BaseDAO;
import com.chnl.pojo.TruckInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * TruckInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.chnl.pojo.TruckInfo
 * @author MyEclipse Persistence Tools
 */
@Repository
public class TruckInfoDAO extends BaseDAO
{
	private static final Logger log = LoggerFactory
	        .getLogger( TruckInfoDAO.class );
	// property constants
	public static final String TRUCKTYPE = "trucktype";
	
	public void save( TruckInfo transientInstance )
	{
		log.debug( "saving TruckInfo instance" );
		try
		{
			getSession().saveOrUpdate( transientInstance );
			log.debug( "save successful" );
		}
		catch ( RuntimeException re )
		{
			log.error( "save failed" , re );
			throw re;
		}
	}
	
	public void delete( TruckInfo persistentInstance )
	{
		log.debug( "deleting TruckInfo instance" );
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
	
	public TruckInfo findById( java.lang.Integer id )
	{
		log.debug( "getting TruckInfo instance with id: " + id );
		try
		{
			TruckInfo instance = ( TruckInfo ) getSession().get(
			        "com.chnl.pojo.TruckInfo" , id );
			return instance;
		}
		catch ( RuntimeException re )
		{
			log.error( "get failed" , re );
			throw re;
		}
	}
	
	public List findByExample( TruckInfo instance )
	{
		log.debug( "finding TruckInfo instance by example" );
		try
		{
			List results = getSession()
			        .createCriteria( "com.chnl.pojo.TruckInfo" )
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
		log.debug( "finding TruckInfo instance with property: " + propertyName
		        + ", value: " + value );
		try
		{
			String queryString = "from TruckInfo as model where model."
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
	
	public List findByTrucktype( Object trucktype )
	{
		return findByProperty( TRUCKTYPE , trucktype );
	}
	
	public List findAll()
	{
		log.debug( "finding all TruckInfo instances" );
		try
		{
			String queryString = "from TruckInfo";
			Query queryObject = getSession().createQuery( queryString );
			return queryObject.list();
		}
		catch ( RuntimeException re )
		{
			log.error( "find all failed" , re );
			throw re;
		}
	}
	
	public TruckInfo merge( TruckInfo detachedInstance )
	{
		log.debug( "merging TruckInfo instance" );
		try
		{
			TruckInfo result = ( TruckInfo ) getSession().merge(
			        detachedInstance );
			log.debug( "merge successful" );
			return result;
		}
		catch ( RuntimeException re )
		{
			log.error( "merge failed" , re );
			throw re;
		}
	}
	
	public void attachDirty( TruckInfo instance )
	{
		log.debug( "attaching dirty TruckInfo instance" );
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
	
	public void attachClean( TruckInfo instance )
	{
		log.debug( "attaching clean TruckInfo instance" );
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
	 * @return List<TruckInfo> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-5 下午1:59:57
	 */
	@SuppressWarnings( "unchecked" )
	public List< TruckInfo > getAllTruckByPage( int page , int pageSize ,
	        String sort , String order )
	{
		String hql = "from TruckInfo order by ?" + " " + order;
		Query query = getSession().createQuery( hql );
		query.setFirstResult( ( page - 1 ) * pageSize );
		query.setMaxResults( pageSize );
		query.setParameter( 0 , sort );
		List< TruckInfo > truckInfos = query.list();
		return truckInfos;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param truckId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-5 下午2:15:17
	 */
	public void deleteById( int truckId )
	{
		String hql = "delete TruckInfo as t where t.id=?";
		Query query = getSession().createQuery( hql );
		query.setInteger( 0 , truckId );
		query.executeUpdate();
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return List<TruckInfo> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-10 下午3:47:17
	 */
	@SuppressWarnings( "unchecked" )
	public List< TruckInfo > findAllFilter()
	{
		String hql = "from TruckInfo t where t.id in (select lt.truckInfo.id from LegTruckInfo lt) order by t.id";
		Query query = getSession().createQuery( hql );
		List< TruckInfo > truckInfos = query.list();
		return truckInfos;
	}
}