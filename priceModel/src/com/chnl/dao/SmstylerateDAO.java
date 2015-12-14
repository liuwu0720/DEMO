package com.chnl.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chnl.base.BaseDAO;
import com.chnl.pojo.CarInfo;
import com.chnl.pojo.PrLegfileimport;
import com.chnl.pojo.PrSelfinputleg;
import com.chnl.pojo.Smstylerate;

/**
 * A data access object (DAO) providing persistence and search support for
 * Smstylerate entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.chnl.pojo.Smstylerate
 * @author MyEclipse Persistence Tools
 */
@Repository
public class SmstylerateDAO extends BaseDAO
{
	private static final Logger log = LoggerFactory
	        .getLogger( SmstylerateDAO.class );
	// property constants
	public static final String DCWEIGHT = "dcweight";
	public static final String DCHIGH = "dchigh";
	public static final String DCLENGTH = "dclength";
	public static final String DCWIDTH = "dcwidth";
	public static final String BACTIVE = "bactive";
	public static final String VCSTYLENAME = "vcstylename";
	
	public void save( Smstylerate transientInstance )
	{
		log.debug( "saving Smstylerate instance" );
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
	
	public void delete( Smstylerate persistentInstance )
	{
		log.debug( "deleting Smstylerate instance" );
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
	
	public Smstylerate findById( java.lang.Double id )
	{
		log.debug( "getting Smstylerate instance with id: " + id );
		try
		{
			Smstylerate instance = ( Smstylerate ) getSession().get(
			        "com.chnl.pojo.Smstylerate" , id );
			return instance;
		}
		catch ( RuntimeException re )
		{
			log.error( "get failed" , re );
			throw re;
		}
	}
	
	public List findByExample( Smstylerate instance )
	{
		log.debug( "finding Smstylerate instance by example" );
		try
		{
			List results = getSession()
			        .createCriteria( "com.chnl.pojo.Smstylerate" )
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
		log.debug( "finding Smstylerate instance with property: "
		        + propertyName + ", value: " + value );
		try
		{
			String queryString = "from Smstylerate as model where model."
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
	
	public List findByDcweight( Object dcweight )
	{
		return findByProperty( DCWEIGHT , dcweight );
	}
	
	public List findByDchigh( Object dchigh )
	{
		return findByProperty( DCHIGH , dchigh );
	}
	
	public List findByDclength( Object dclength )
	{
		return findByProperty( DCLENGTH , dclength );
	}
	
	public List findByDcwidth( Object dcwidth )
	{
		return findByProperty( DCWIDTH , dcwidth );
	}
	
	public List findByBactive( Object bactive )
	{
		return findByProperty( BACTIVE , bactive );
	}
	
	public List findByVcstylename( Object vcstylename )
	{
		return findByProperty( VCSTYLENAME , vcstylename );
	}
	
	public List findAll()
	{
		log.debug( "finding all Smstylerate instances" );
		try
		{
			String queryString = "from Smstylerate";
			Query queryObject = getSession().createQuery( queryString );
			return queryObject.list();
		}
		catch ( RuntimeException re )
		{
			log.error( "find all failed" , re );
			throw re;
		}
	}
	
	public Smstylerate merge( Smstylerate detachedInstance )
	{
		log.debug( "merging Smstylerate instance" );
		try
		{
			Smstylerate result = ( Smstylerate ) getSession().merge(
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
	
	public void attachDirty( Smstylerate instance )
	{
		log.debug( "attaching dirty Smstylerate instance" );
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
	
	public void attachClean( Smstylerate instance )
	{
		log.debug( "attaching clean Smstylerate instance" );
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
	 * @Description: TODO(新需求：根据合同有效日期大于当前系统时间筛选出提车库所运输的商品车名称)
	 * @param startpoint
	 * @return List<Smstylerate> 返回值描述
	 * @author liuwu
	 * @param customerId
	 * @param string
	 * @create_date 2014-11-11 上午11:46:33
	 */
	@SuppressWarnings( "unchecked" )
	public List< Double > findByPointName( String startCity , String endCity ,
	        String customerId )
	{
		List< Double > carIds = new ArrayList< Double >();

		/*	String sql = "select distinct sms.ilineid from smStyle sms left join smstylerate smg "
			        + "on smg.ilineid = sms.IRATE left join soorder so on sms.ilineid = so.iStyleID "
			        + "where so.dtDate>(select add_months(sysdate,-6) from dual) and "
			        + "so.vcStartCity = '"
			        + startCity
			        + "' and so.vcCityName = '"
			        + endCity + "' and sms.irate != 0";*/
		String sql = "";
		if ( endCity.equals( "上海" ) )// 如果目的地是上海保留‘进出口’的合同,其它的去掉
		{
			sql = "select distinct sms.ilineid from smstyle sms left join  smContract smc"
			        + " on sms.iContractID = smc.ilineid left join smGetPoint smg on smc.ilineid = smg.iContractID "
			        + "where smg.vccityname='"
			        + startCity
			        + "' and smc.dtEndDate>(select add_months(sysdate,-2) from dual) and smc.iType=0 "
			        + "and smc.iStatus = 1 and smc.BACTIVE=1 and smc.vcContractName not like '%报告%' "
			        + "and smc.vcContractName not like '%散货%'  ";
		}
		else
		{
			sql = "select distinct sms.ilineid from smstyle sms left join  smContract smc"
			        + " on sms.iContractID = smc.ilineid left join smGetPoint smg on smc.ilineid = smg.iContractID "
			        + "where smg.vccityname='"
			        + startCity
			        + "' and smc.dtEndDate>(select add_months(sysdate,-2) from dual) and smc.iType=0 "
			        + "and smc.iStatus = 1 and smc.BACTIVE=1 and smc.vcContractName not like '%报告%' "
			        + "and smc.vcContractName not like '%进出口%'"
			        + "and smc.vcContractName not like '%散货%'  ";
			
		}
		if ( StringUtils.isNotBlank( customerId ) )
		{
			int custId = Integer.parseInt( customerId );
			sql += "and smc.icustomerid = " + custId;
		}
		
		try
		{
			System.out.println( "sql = " + sql );
			Query query = getSession().createSQLQuery( sql );
			List< Object > querylist = query.list();
			for ( Object ids : querylist )
			{
				String ids2 = String.valueOf( ids );
				double id = Double.parseDouble( ids2 );
				carIds.add( id );
			}
		}
		catch ( Exception e )
		{
			// TODO: handle exception
		}
		
		return carIds;
	}
	
	/**
	 * @Description: TODO(新需求:根据线路信息、商品车ID算出发车比例，取半年有效数据)
	 * @param prLegfileimport
	 * @param carId
	 * @return double 返回值描述
	 * @author liuwu
	 * @param carInfo
	 * @param smsid
	 * @create_date 2014-11-11 下午4:33:03
	 */
	public void getRatioAndIncomePrice( PrLegfileimport prLegfileimport ,
	        CarInfo carInfo , Double carId )
	{

		String callSql = "{Call PRICE_GETLEGCARINFO(?,?,?,?,?,?)}";
		ResultSet rs = null;
		Session session = this.getHibernateTemplate().getSessionFactory()
		        .openSession();
		try
		{
			
			Connection conn = session.connection();
			conn.setAutoCommit( false );
			CallableStatement statement = conn.prepareCall( callSql );
			
			statement.setString( 1 , prLegfileimport.getStartcity() );
			statement.setString( 2 , prLegfileimport.getEndcity() );
			statement.setDouble( 3 , carId );
			statement.registerOutParameter( 4 , Types.DOUBLE );
			statement.registerOutParameter( 5 , Types.DOUBLE );
			statement.registerOutParameter( 6 , Types.DOUBLE );
			statement.execute();
			double ration = statement.getDouble( 4 ); // 发车比率
			double incomePrice = statement.getDouble( 5 );// 应收单价
			double currentProCostCar = statement.getDouble( 6 );// 应付单价
			conn.commit();
			statement.close();
			conn.close();
			session.close();
			carInfo.setRatio( Double.parseDouble( String.format( "%.3f" ,
			        ration ) ) );
			carInfo.setCurrentProCost( currentProCostCar );
			carInfo.setIncomePrice( incomePrice );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			System.err.println( "smstyledao数据库异常查询" );
		}

	}
	
	/**
	 * @Description: TODO(通过订单表的商品车ID查询出商品车对应的详细参数)
	 * @param carId
	 * @return Smstylerate 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-12 上午10:18:15
	 */
	public Smstylerate findByCarId( Double carId )
	{
		Smstylerate smstylerate = null;
		String sql = "select sm.vcstylename from smStyle sm where sm.ilineid=?";
		try
		{
			Query query = getSession().createSQLQuery( sql );
			query.setParameter( 0 , carId );
			/*double smsId = Double
			        .parseDouble( query.list().get( 0 ).toString() );
			 smstylerate = findById( smsId );       
			        */
			String vcCarName = query.list().get( 0 ).toString();
			smstylerate = ( Smstylerate ) findByVcstylename( vcCarName )
			        .get( 0 );
		}
		catch ( Exception e )
		{
			// TODO: handle exception
		}

		return smstylerate;
	}
	
	/**
	 * @Description: TODO(根据线路信息、商品车ID算出发车比例，取半年有效数据)
	 * @param prSelfinputleg
	 * @param carInfo
	 * @param carId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 下午1:26:06
	 */
	public void getRatioAndIncomePrice2( PrSelfinputleg prSelfinputleg ,
	        CarInfo carInfo , Double carId )
	{
		String callSql = "{Call PRICE_GETLEGCARINFO(?,?,?,?,?,?)}";
		ResultSet rs = null;
		Session session = this.getHibernateTemplate().getSessionFactory()
		        .openSession();
		try
		{
			
			Connection conn = session.connection();
			conn.setAutoCommit( false );
			CallableStatement statement = conn.prepareCall( callSql );
			
			statement.setString( 1 , prSelfinputleg.getStartcity() );
			statement.setString( 2 , prSelfinputleg.getEndcity() );
			statement.setDouble( 3 , carId );
			statement.registerOutParameter( 4 , Types.DOUBLE );
			statement.registerOutParameter( 5 , Types.DOUBLE );
			statement.registerOutParameter( 6 , Types.DOUBLE );
			statement.execute();
			double ration = statement.getDouble( 4 ); // 发车比率
			double incomePrice = statement.getDouble( 5 );// 应收单价
			double currentProCostCar = statement.getDouble( 6 );// 应付单价
			conn.commit();
			statement.close();
			conn.close();
			session.close();
			carInfo.setRatio( Double.parseDouble( String.format( "%.3f" ,
			        ration ) ) );
			carInfo.setCurrentProCost( currentProCostCar );
			carInfo.setIncomePrice( incomePrice );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			System.err.println( "smstyledao数据库异常查询" );
		}
		
	}
}