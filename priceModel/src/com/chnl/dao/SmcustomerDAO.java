package com.chnl.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chnl.base.BaseDAO;
import com.chnl.pojo.Smcustomer;

/**
 * A data access object (DAO) providing persistence and search support for
 * Smcustomer entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.chnl.pojo.Smcustomer
 * @author MyEclipse Persistence Tools
 */
@Repository
public class SmcustomerDAO extends BaseDAO
{
	private static final Logger log = LoggerFactory
	        .getLogger( SmcustomerDAO.class );
	// property constants
	public static final String VCCUSTOMERNO = "vccustomerno";
	public static final String VCCUSTOMERNAME = "vccustomername";
	public static final String VCCONTACT = "vccontact";
	public static final String VCTEL = "vctel";
	public static final String VCADDRESS = "vcaddress";
	public static final String VCPROVINCE = "vcprovince";
	public static final String VCCITYNAME = "vccityname";
	public static final String VCCOMPANYNAME = "vccompanyname";
	public static final String BACTIVE = "bactive";
	public static final String VCUSERNO = "vcuserno";
	public static final String IFLAG = "iflag";
	public static final String ITYPE = "itype";
	public static final String VCADDRESS2 = "vcaddress2";
	public static final String BCHECK = "bcheck";
	
	public void save( Smcustomer transientInstance )
	{
		log.debug( "saving Smcustomer instance" );
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
	
	public void delete( Smcustomer persistentInstance )
	{
		log.debug( "deleting Smcustomer instance" );
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
	
	public Smcustomer findById( Integer ilineid )
	{
		log.debug( "getting Smcustomer instance with id: " + ilineid );
		try
		{
			Smcustomer instance = ( Smcustomer ) getSession().get(
			        "com.chnl.pojo.Smcustomer" , ilineid );
			return instance;
		}
		catch ( RuntimeException re )
		{
			log.error( "get failed" , re );
			throw re;
		}
	}
	
	public List findByExample( Smcustomer instance )
	{
		log.debug( "finding Smcustomer instance by example" );
		try
		{
			List results = getSession()
			        .createCriteria( "com.chnl.pojo.Smcustomer" )
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
		log.debug( "finding Smcustomer instance with property: " + propertyName
		        + ", value: " + value );
		try
		{
			String queryString = "from Smcustomer as model where model."
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
	
	public List findByVccustomerno( Object vccustomerno )
	{
		return findByProperty( VCCUSTOMERNO , vccustomerno );
	}
	
	public List findByVccustomername( Object vccustomername )
	{
		return findByProperty( VCCUSTOMERNAME , vccustomername );
	}
	
	public List findByVccontact( Object vccontact )
	{
		return findByProperty( VCCONTACT , vccontact );
	}
	
	public List findByVctel( Object vctel )
	{
		return findByProperty( VCTEL , vctel );
	}
	
	public List findByVcaddress( Object vcaddress )
	{
		return findByProperty( VCADDRESS , vcaddress );
	}
	
	public List findByVcprovince( Object vcprovince )
	{
		return findByProperty( VCPROVINCE , vcprovince );
	}
	
	public List findByVccityname( Object vccityname )
	{
		return findByProperty( VCCITYNAME , vccityname );
	}
	
	public List findByVccompanyname( Object vccompanyname )
	{
		return findByProperty( VCCOMPANYNAME , vccompanyname );
	}
	
	public List findByBactive( Object bactive )
	{
		return findByProperty( BACTIVE , bactive );
	}
	
	public List findByVcuserno( Object vcuserno )
	{
		return findByProperty( VCUSERNO , vcuserno );
	}
	
	public List findByIflag( Object iflag )
	{
		return findByProperty( IFLAG , iflag );
	}
	
	public List findByItype( Object itype )
	{
		return findByProperty( ITYPE , itype );
	}
	
	public List findByVcaddress2( Object vcaddress2 )
	{
		return findByProperty( VCADDRESS2 , vcaddress2 );
	}
	
	public List findByBcheck( Object bcheck )
	{
		return findByProperty( BCHECK , bcheck );
	}
	
	public List findAll()
	{
		log.debug( "finding all Smcustomer instances" );
		try
		{
			String queryString = "from Smcustomer";
			Query queryObject = getSession().createQuery( queryString );
			return queryObject.list();
		}
		catch ( RuntimeException re )
		{
			log.error( "find all failed" , re );
			throw re;
		}
	}
	
	public Smcustomer merge( Smcustomer detachedInstance )
	{
		log.debug( "merging Smcustomer instance" );
		try
		{
			Smcustomer result = ( Smcustomer ) getSession().merge(
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
	
	public void attachDirty( Smcustomer instance )
	{
		log.debug( "attaching dirty Smcustomer instance" );
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
	
	public void attachClean( Smcustomer instance )
	{
		log.debug( "attaching clean Smcustomer instance" );
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
	 * @param startpoint
	 * @param smcustomers
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-29 上午10:31:47
	 */
	@SuppressWarnings( "unchecked" )
	public void getCustomerByPointName( String startpoint ,
	        List< Smcustomer > smcustomers )
	{
		String sql = "select distinct  t.icustomerid from SMGETPOINT t where t.vcpointname=?";
		try
		{
			Query query = getSession().createSQLQuery( sql );
			query.setParameter( 0 , startpoint );
			
			List cusIds = query.list();
			for ( int i = 0 ; i < cusIds.size() ; i++ )
			{
				int customerId = Integer.valueOf( cusIds.get( i ).toString() );
				System.out.println( "customerId " + cusIds.get( i ) );
				Smcustomer smcustomer = this.findById( customerId );
				smcustomers.add( smcustomer );
			}
		}
		catch ( Exception e )
		{

			System.out.println( "error" + e.getMessage() );
		}
	}
}