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
import com.chnl.pojo.PrAlllegcarinfo;
import com.chnl.pojo.PrLegfileimport;

/**
 * A data access object (DAO) providing persistence and search support for
 * PrAlllegcarinfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.chnl.pojo.PrAlllegcarinfo
 * @author MyEclipse Persistence Tools
 */
@Repository
public class PrAlllegcarinfoDAO extends BaseDAO
{
	private static final Logger log = LoggerFactory
	        .getLogger( PrAlllegcarinfoDAO.class );
	// property constants
	public static final String STARTCITY = "startcity";
	public static final String ENDCITY = "endcity";
	public static final String CARID = "carid";
	public static final String RATIO = "ratio";
	public static final String INCOMEPRICE = "incomeprice";
	public static final String OUTPROCOST = "outprocost";
	
	public void save( PrAlllegcarinfo transientInstance )
	{
		log.debug( "saving PrAlllegcarinfo instance" );
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
	
	public void delete( PrAlllegcarinfo persistentInstance )
	{
		log.debug( "deleting PrAlllegcarinfo instance" );
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
	
	public PrAlllegcarinfo findById( java.lang.Double id )
	{
		log.debug( "getting PrAlllegcarinfo instance with id: " + id );
		try
		{
			PrAlllegcarinfo instance = ( PrAlllegcarinfo ) getSession().get(
			        "com.chnl.pojo.PrAlllegcarinfo" , id );
			return instance;
		}
		catch ( RuntimeException re )
		{
			log.error( "get failed" , re );
			throw re;
		}
	}
	
	public List findByExample( PrAlllegcarinfo instance )
	{
		log.debug( "finding PrAlllegcarinfo instance by example" );
		try
		{
			List results = getSession()
			        .createCriteria( "com.chnl.pojo.PrAlllegcarinfo" )
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
		log.debug( "finding PrAlllegcarinfo instance with property: "
		        + propertyName + ", value: " + value );
		try
		{
			String queryString = "from PrAlllegcarinfo as model where model."
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
	
	public List findByStartcity( Object startcity )
	{
		return findByProperty( STARTCITY , startcity );
	}
	
	public List findByEndcity( Object endcity )
	{
		return findByProperty( ENDCITY , endcity );
	}
	
	public List findByCarid( Object carid )
	{
		return findByProperty( CARID , carid );
	}
	
	public List findByRatio( Object ratio )
	{
		return findByProperty( RATIO , ratio );
	}
	
	public List findByIncomeprice( Object incomeprice )
	{
		return findByProperty( INCOMEPRICE , incomeprice );
	}
	
	public List findByOutprocost( Object outprocost )
	{
		return findByProperty( OUTPROCOST , outprocost );
	}
	
	public List findAll()
	{
		log.debug( "finding all PrAlllegcarinfo instances" );
		try
		{
			String queryString = "from PrAlllegcarinfo";
			Query queryObject = getSession().createQuery( queryString );
			return queryObject.list();
		}
		catch ( RuntimeException re )
		{
			log.error( "find all failed" , re );
			throw re;
		}
	}
	
	public PrAlllegcarinfo merge( PrAlllegcarinfo detachedInstance )
	{
		log.debug( "merging PrAlllegcarinfo instance" );
		try
		{
			PrAlllegcarinfo result = ( PrAlllegcarinfo ) getSession().merge(
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
	
	public void attachDirty( PrAlllegcarinfo instance )
	{
		log.debug( "attaching dirty PrAlllegcarinfo instance" );
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
	
	public void attachClean( PrAlllegcarinfo instance )
	{
		log.debug( "attaching clean PrAlllegcarinfo instance" );
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
	 * @Description: TODO(清除以前的数据) void 返回值描述
	 * @author liuwu
	 * @param legId
	 * @param typeId
	 * @create_date 2014-11-21 下午2:36:11
	 */
	public void cleanBeforeData( int legId , int typeId )
	{
		String sql = "delete from PR_ALLLEGCARINFO where LEGID = " + legId
		        + "and TYPEID=" + typeId;
		try
		{
			Query query = getSession().createSQLQuery( sql );
			query.executeUpdate();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @Description: TODO(根据线路取出所有商品车信息)
	 * @param id
	 * @param type
	 *            void 返回值描述
	 * @author liuwu
	 * @return
	 * @create_date 2014-11-26 上午9:23:02
	 */
	@SuppressWarnings( "unchecked" )
	public List< PrAlllegcarinfo > findAllCarInfoByLegId( Integer id , int type )
	{
		String hql = "from PrAlllegcarinfo as pr where pr.legId=? and pr.typeId = ?";
		Query query = getSession().createQuery( hql );
		query.setParameter( 0 , id );
		query.setParameter( 1 , type );
		List< PrAlllegcarinfo > prAlllegcarinfos = query.list();
		return prAlllegcarinfos;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param car1Id
	 * @param legid
	 * @param type
	 * @return CarInfo 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-26 上午10:59:07
	 */
	public CarInfo getCarInfo( double car1Id , Integer legid , int type )
	{
		String hql = "from PrAlllegcarinfo as pr where pr.legId=? and pr.typeId = ? and pr.carid = ?";
		Query query = getSession().createQuery( hql );
		query.setParameter( 0 , legid );
		query.setParameter( 1 , type );
		query.setParameter( 2 , car1Id );
		PrAlllegcarinfo prAlllegcarinfo = ( PrAlllegcarinfo ) query.list().get(
		        0 );
		CarInfo carInfo = new CarInfo();
		carInfo.setId( prAlllegcarinfo.getCarid() );
		carInfo.setCarname( prAlllegcarinfo.getCarname() );
		carInfo.setCurrentProCost( prAlllegcarinfo.getOutprocost() );
		carInfo.setIncomePrice( prAlllegcarinfo.getIncomeprice() );
		carInfo.setLength( prAlllegcarinfo.getLength() );
		carInfo.setRatio( prAlllegcarinfo.getRatio() );
		carInfo.setWeight( prAlllegcarinfo.getWeight() );
		return carInfo;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param prLegfileimport
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-4 上午11:36:01
	 */
	public void setBtnMessage( PrLegfileimport prLegfileimport )
	{
		String hql = "from PrAlllegcarinfo as pr where pr.legId=? and pr.typeId = ?";
		Query query = getSession().createQuery( hql );
		query.setParameter( 0 , prLegfileimport.getId() );
		query.setParameter( 1 , 0 );
		List< PrAlllegcarinfo > prAlllegcarinfos = query.list();

			if ( prAlllegcarinfos.size() > 0 )
			{
				int size = prAlllegcarinfos.size();
			prLegfileimport.setBtnMessage( "该条线路已选择了【" + size
			        + "】辆车,如果重新选择商品车将覆盖原有数据" );
			}
			else
			{
			prLegfileimport.setBtnMessage( null );
			}
			

	}
}