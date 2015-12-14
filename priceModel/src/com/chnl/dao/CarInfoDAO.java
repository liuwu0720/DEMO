package com.chnl.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chnl.base.BaseDAO;
import com.chnl.pojo.CarInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * CarInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.chnl.pojo.CarInfo
 * @author MyEclipse Persistence Tools
 */
@Repository
public class CarInfoDAO extends BaseDAO
{
	private static final Logger log = LoggerFactory
	        .getLogger( CarInfoDAO.class );
	// property constants
	public static final String CARNAME = "carname";
	public static final String MANUFACTURER = "manufacturer";
	
	public void save( CarInfo transientInstance )
	{
		log.debug( "saving CarInfo instance" );
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
	
	public void delete( CarInfo persistentInstance )
	{
		log.debug( "deleting CarInfo instance" );
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
	
	public CarInfo findById( double car1Id )
	{
		log.debug( "getting CarInfo instance with id: " + car1Id );
		try
		{
			CarInfo instance = ( CarInfo ) getSession().get(
			        "com.chnl.pojo.CarInfo" , car1Id );
			return instance;
		}
		catch ( RuntimeException re )
		{
			log.error( "get failed" , re );
			throw re;
		}
	}
	
	public List findByExample( CarInfo instance )
	{
		log.debug( "finding CarInfo instance by example" );
		try
		{
			List results = getSession().createCriteria( "com.chnl.pojo.CarInfo" )
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
		log.debug( "finding CarInfo instance with property: " + propertyName
		        + ", value: " + value );
		try
		{
			String queryString = "from CarInfo as model where model."
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
	
	public List findByCarname( Object carname )
	{
		return findByProperty( CARNAME , carname );
	}
	
	public List findByManufacturer( Object manufacturer )
	{
		return findByProperty( MANUFACTURER , manufacturer );
	}
	
	public List findAll()
	{
		log.debug( "finding all CarInfo instances" );
		try
		{
			String queryString = "from CarInfo";
			Query queryObject = getSession().createQuery( queryString );
			return queryObject.list();
		}
		catch ( RuntimeException re )
		{
			log.error( "find all failed" , re );
			throw re;
		}
	}
	
	public CarInfo merge( CarInfo detachedInstance )
	{
		log.debug( "merging CarInfo instance" );
		try
		{
			CarInfo result = ( CarInfo ) getSession().merge( detachedInstance );
			log.debug( "merge successful" );
			return result;
		}
		catch ( RuntimeException re )
		{
			log.error( "merge failed" , re );
			throw re;
		}
	}
	
	public void attachDirty( CarInfo instance )
	{
		log.debug( "attaching dirty CarInfo instance" );
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
	
	public void attachClean( CarInfo instance )
	{
		log.debug( "attaching clean CarInfo instance" );
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
	 * @Description: TODO(按重量排序查出所有商品车)
	 * @param double1
	 * @return CarInfo 返回值描述
	 * @author liuwu
	 * @create_date 2014-8-18 上午10:24:47
	 */ 
	public CarInfo findByWeight( Double double1 )
    {
    	  CarInfo carInfo = null;
    	String hql = "from CarInfo as c  where c.id=? order by c.weight desc";
    	try
        {
    		
	        Query query = getSession().createQuery( hql );
			query.setDouble( 0 , double1 );
	        carInfo = ( CarInfo ) query.list().get( 0 );
        }
        catch ( Exception e )
        {
	        // TODO: handle exception
        }
    	
	  
		return carInfo;
    }

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @param order
	 * @return List<CarInfo> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-5 上午11:01:37
	 */
	@SuppressWarnings( "unchecked" )
	public List< CarInfo > getCarInfoByPage( int page , int pageSize ,
	        String sort , String order )
	{
		String hql = "from CarInfo order by ?" + " " + order;
		Query query = getSession().createQuery( hql );
		query.setFirstResult( ( page - 1 ) * pageSize );
		query.setMaxResults( pageSize );
		query.setParameter( 0 , sort );
		
		List< CarInfo > carInfos = query.list();
		return carInfos;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param carId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-5 下午1:36:19
	 */
	public void delteById( int carId )
	{
		String hql = "delete CarInfo as c where c.id=?";
		Query query = getSession().createQuery( hql );
		query.setInteger( 0 , carId );
		query.executeUpdate();
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param city
	 * @return List<String> 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-22 上午11:21:02
	 */
	public List< String > findAllsmStartCitys( String city )
	{
		String sql = "select t.vccityname from smStartCity  t where t.vccityname like '%"
		        + city + "%'";
		try
		{
			Query query = getSession().createSQLQuery( sql );
			List< String > list = query.list();
			return list;
		}
		catch ( Exception e )
		{
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param startpoint
	 * @return List<String> 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-22 下午1:56:29
	 */
	public List< String > findAllsmStartpoints( String startpoint )
	{
		String sql = "select distinct smg.vcpointname from smGetPoint smg where smg.vccityname='"
		        + startpoint + "'";
		try
		{
			Query query = getSession().createSQLQuery( sql );
			List< String > list = query.list();
			if ( list.size() == 0 )
			{
				list.add( "没有对应的提车库！" );
			}

			return list;
		}
		catch ( Exception e )
		{
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param endcity
	 * @return List<String> 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-22 下午2:23:22
	 */
	public List< String > findAllendcity( String endcity )
	{
		String sql = "select t.vccityname from smDestCity  t where t.vccityname like '%"
		        + endcity + "%'";
		try
		{
			Query query = getSession().createSQLQuery( sql );
			List< String > list = query.list();
			return list;
		}
		catch ( Exception e )
		{
			// TODO: handle exception
		}
		return null;
	}
	

	
}