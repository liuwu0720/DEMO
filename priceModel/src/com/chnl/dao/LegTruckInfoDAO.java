package com.chnl.dao;

import java.util.ArrayList;
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
import com.chnl.pojo.LegTruckInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * LegTruckInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.chnl.pojo.LegTruckInfo
 * @author MyEclipse Persistence Tools
 */
@Repository
public class LegTruckInfoDAO extends BaseDAO
{
	private static final Logger log = LoggerFactory
	        .getLogger( LegTruckInfoDAO.class );
	
	// property constants
	
	public void save( LegTruckInfo transientInstance )
	{
		log.debug( "saving LegTruckInfo instance" );
		try
		{
			getSession().save( transientInstance );
			log.debug( "save successful" );
		}
		catch ( RuntimeException re )
		{
			log.error( "save failed" , re );
			throw re;
		}
	}
	
	public void delete( LegTruckInfo persistentInstance )
	{
		log.debug( "deleting LegTruckInfo instance" );
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
	
	public LegTruckInfo findById( java.lang.Integer id )
	{
		log.debug( "getting LegTruckInfo instance with id: " + id );
		try
		{
			LegTruckInfo instance = ( LegTruckInfo ) getSession().get(
			        "com.chnl.pojo.LegTruckInfo" , id );
			return instance;
		}
		catch ( RuntimeException re )
		{
			log.error( "get failed" , re );
			throw re;
		}
	}
	
	public List findByExample( LegTruckInfo instance )
	{
		log.debug( "finding LegTruckInfo instance by example" );
		try
		{
			List results = getSession()
			        .createCriteria( "com.chnl.pojo.LegTruckInfo" )
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
		log.debug( "finding LegTruckInfo instance with property: "
		        + propertyName + ", value: " + value );
		try
		{
			String queryString = "from LegTruckInfo as model where model."
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
	
	public List findAll()
	{
		log.debug( "finding all LegTruckInfo instances" );
		try
		{
			String queryString = "from LegTruckInfo";
			Query queryObject = getSession().createQuery( queryString );
			return queryObject.list();
		}
		catch ( RuntimeException re )
		{
			log.error( "find all failed" , re );
			throw re;
		}
	}
	
	public LegTruckInfo merge( LegTruckInfo detachedInstance )
	{
		log.debug( "merging LegTruckInfo instance" );
		try
		{
			LegTruckInfo result = ( LegTruckInfo ) getSession().merge(
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
	
	public void attachDirty( LegTruckInfo instance )
	{
		log.debug( "attaching dirty LegTruckInfo instance" );
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
	
	public void attachClean( LegTruckInfo instance )
	{
		log.debug( "attaching clean LegTruckInfo instance" );
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
	 * @param legId
	 * @param truckId
	 * @return LegTruckInfo 返回值描述
	 * @author liuwu
	 * @create_date 2014-8-11 下午4:01:07
	 */
	@SuppressWarnings( "unchecked" )
	public LegTruckInfo findByLegIdAndTruckId( int legId , int truckId )
	{
		// TODO Auto-generated method stub
		String hql = "from LegTruckInfo l where l.truckInfo.id = " + truckId
		        + "and l.legInfo.id = " + legId;
		
		List< LegTruckInfo > legTruckInfos = null;
		try
		{
			legTruckInfos = getSession().createQuery( hql ).list();
			return legTruckInfos.get( 0 );
		}
		catch ( Exception e )
		{
			System.out.println( "没有查到" );
		}
		
		return null;
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legTruckId
	 * @param fullCost
	 * @param emptyCost
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-10 下午2:28:46
	 */
	public void updateTruckInfo( int legTruckId , double fullCost ,
	        double emptyCost )
	{
		String sql = "update leg_truck_info set FULLCOST=" + fullCost
		        + ",EMPTCOST=" + emptyCost + "where id=" + legTruckId;
		int query = jdbcTemplate.update( sql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legTruckId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-10 下午3:03:11
	 */
	public void deleteById( int legTruckId )
	{
		String hql = "delete LegTruckInfo where id=?";
		Query query = getSession().createQuery( hql );
		query.setInteger( 0 , legTruckId );
		query.executeUpdate();
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param truckId
	 * @return List<Map<String,Object>> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-12 下午4:01:49
	 */
	public List< Map< String , Object >> getSelectLegByTruckId( int truckId )
	{
		String sql = "from LegInfo l where l.id not in(select lt.legInfo.id from LegTruckInfo lt where lt.truckInfo.id=?)order by l.id";
		List< Map< String , Object >> result = new ArrayList< Map< String , Object >>();
		Query query = getSession().createQuery( sql );
		query.setInteger( 0 , truckId );
		List< LegInfo > list = query.list();
		for ( LegInfo legInfo : list )
		{
			Map< String , Object > map = new HashMap< String , Object >();
			map.put( "id" , legInfo.getId() );
			map.put( "legname" , legInfo.getOrigin().trim() + "--"
			        + legInfo.getDestination().trim() );
			result.add( map );
		}
		return result;
	}
}