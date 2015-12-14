package com.chnl.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chnl.base.BaseDAO;
import com.chnl.pojo.Combination;

/**
 * A data access object (DAO) providing persistence and search support for
 * Combination entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.chnl.dao.Combination
 * @author MyEclipse Persistence Tools
 */
@Repository
public class CombinationDAO extends BaseDAO
{
	private static final Logger log = LoggerFactory
	        .getLogger( CombinationDAO.class );
	// property constants
	public static final String TOTALCARS = "totalcars";
	public static final String PRICE = "price";
	public static final String PROB1 = "prob1";
	public static final String PROB2 = "prob2";
	public static final String CAR1NUM = "car1num";
	public static final String CAR2NUM = "car2num";
	public static final String CAR3NUM = "car3num";
	
	public void save( Combination transientInstance )
	{
		log.debug( "saving Combination instance" );
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
	
	public void delete( Combination persistentInstance )
	{
		log.debug( "deleting Combination instance" );
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
	
	public Combination findById( java.lang.Integer id )
	{
		log.debug( "getting Combination instance with id: " + id );
		try
		{
			Combination instance = ( Combination ) getSession().get(
			        "com.chnl.dao.Combination" , id );
			return instance;
		}
		catch ( RuntimeException re )
		{
			log.error( "get failed" , re );
			throw re;
		}
	}
	
	public List findByExample( Combination instance )
	{
		log.debug( "finding Combination instance by example" );
		try
		{
			List results = getSession()
			        .createCriteria( "com.chnl.dao.Combination" )
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
		log.debug( "finding Combination instance with property: "
		        + propertyName + ", value: " + value );
		try
		{
			String queryString = "from Combination as model where model."
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
	
	public List findByTotalcars( Object totalcars )
	{
		return findByProperty( TOTALCARS , totalcars );
	}
	
	public List findByPrice( Object price )
	{
		return findByProperty( PRICE , price );
	}
	
	public List findByProb1( Object prob1 )
	{
		return findByProperty( PROB1 , prob1 );
	}
	
	public List findByProb2( Object prob2 )
	{
		return findByProperty( PROB2 , prob2 );
	}
	
	public List findByCar1num( Object car1num )
	{
		return findByProperty( CAR1NUM , car1num );
	}
	
	public List findByCar2num( Object car2num )
	{
		return findByProperty( CAR2NUM , car2num );
	}
	
	public List findByCar3num( Object car3num )
	{
		return findByProperty( CAR3NUM , car3num );
	}
	
	public List findAll()
	{
		log.debug( "finding all Combination instances" );
		try
		{
			String queryString = "from Combination";
			Query queryObject = getSession().createQuery( queryString );
			return queryObject.list();
		}
		catch ( RuntimeException re )
		{
			log.error( "find all failed" , re );
			throw re;
		}
	}
	
	public Combination merge( Combination detachedInstance )
	{
		log.debug( "merging Combination instance" );
		try
		{
			Combination result = ( Combination ) getSession().merge(
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
	
	public void attachDirty( Combination instance )
	{
		log.debug( "attaching dirty Combination instance" );
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
	
	public void attachClean( Combination instance )
	{
		log.debug( "attaching clean Combination instance" );
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
}