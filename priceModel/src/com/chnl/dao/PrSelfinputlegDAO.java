package com.chnl.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.chnl.base.BaseDAO;
import com.chnl.entity.SmUser;
import com.chnl.pojo.PrSelfinputleg;

/**
 * A data access object (DAO) providing persistence and search support for
 * PrSelfinputleg entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.chnl.pojo.PrSelfinputleg
 * @author MyEclipse Persistence Tools
 */
@Repository
public class PrSelfinputlegDAO extends BaseDAO
{
	private static final Logger log = LoggerFactory
	        .getLogger( PrSelfinputlegDAO.class );
	
	public void save( PrSelfinputleg transientInstance )
	{
		log.debug( "saving PrSelfinputleg instance" );
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
	
	public void delete( PrSelfinputleg persistentInstance )
	{
		log.debug( "deleting PrSelfinputleg instance" );
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
	
	public PrSelfinputleg findById( java.lang.Integer id )
	{
		log.debug( "getting PrSelfinputleg instance with id: " + id );
		try
		{
			PrSelfinputleg instance = ( PrSelfinputleg ) getSession().get(
			        "com.chnl.pojo.PrSelfinputleg" , id );
			return instance;
		}
		catch ( RuntimeException re )
		{
			log.error( "get failed" , re );
			throw re;
		}
	}
	
	public List findByExample( PrSelfinputleg instance )
	{
		log.debug( "finding PrSelfinputleg instance by example" );
		try
		{
			List results = getSession()
			        .createCriteria( "com.chnl.pojo.PrSelfinputleg" )
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
		log.debug( "finding PrSelfinputleg instance with property: "
		        + propertyName + ", value: " + value );
		try
		{
			String queryString = "from PrSelfinputleg as model where model."
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
	
	public List findAll( SmUser smUser )
	{
		log.debug( "finding all PrSelfinputleg instances" );
		try
		{
			String queryString = "from PrSelfinputleg as p where p.username=? order by p.id";
			Query queryObject = getSession().createQuery( queryString );
			queryObject.setParameter( 0 , smUser.getUserName() );
			return queryObject.list();
		}
		catch ( RuntimeException re )
		{
			log.error( "find all failed" , re );
			throw re;
		}
	}
	
	public PrSelfinputleg merge( PrSelfinputleg detachedInstance )
	{
		log.debug( "merging PrSelfinputleg instance" );
		try
		{
			PrSelfinputleg result = ( PrSelfinputleg ) getSession().merge(
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
	
	public void attachDirty( PrSelfinputleg instance )
	{
		log.debug( "attaching dirty PrSelfinputleg instance" );
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
	
	public void attachClean( PrSelfinputleg instance )
	{
		log.debug( "attaching clean PrSelfinputleg instance" );
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
	 * @param prSelfinputleg
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-22 下午5:42:24
	 */
	public void saveOrUpdate( PrSelfinputleg prSelfinputleg )
	{
		try
		{
			getSession().saveOrUpdate( prSelfinputleg );
		}
		catch ( Exception e )
		{
			// TODO: handle exception
		}
		
	}
	
	/**
	 * @Description: TODO(环线情况下的空载距离计算)
	 * @param prSelfinputleg
	 * @param endcity
	 * @param startpoint
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 上午9:28:12
	 */
	public void getEmptlyDistance( PrSelfinputleg prSelfinputleg ,
	        String endcity , String startpoint )
	{
		String sql = "select pr.kilometer kilo from pr_empty_kilometer pr where trim(pr.pointname)='"
		        + startpoint + "' and trim(pr.cityname)='" + endcity + "'";
		try
		{
			Query query = getSession().createSQLQuery( sql );
			System.out.println( query.getQueryString().toString() );
			List< Object > list = query.list();
			if ( prSelfinputleg.getEmptlyflag() == 0 ) // 全程空载情况下状态为1；待定情况：状态3
			{
				if ( list.size() > 0 )
				{
					Double kilometer = Double.parseDouble( list.get( 0 )
					        .toString() );
					prSelfinputleg.setEmptlyDistance( kilometer );
					
					prSelfinputleg.setEmptlyflag( 0 ); // 有效
					prSelfinputleg.setMessage( "目的地【" + endcity + "】至提车点【"
					        + startpoint + "】空载距离为" + kilometer + "请选择车辆！" );
					
				}
				else
				{
					String message = "目的地【" + endcity + "】至提车库【" + startpoint
					        + "】无空载距离！此条环线视为无效线路";
					prSelfinputleg.setEmptlyflag( 2 );// 设为无效
					prSelfinputleg.setMessage( message );
					// getSession().saveOrUpdate( prLegfileimport );
					
				}
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

	}
	
	/**
	 * @Description: TODO(如果这条空载线路是环线的最后一条：当前线路目的地城市至第一条线路出发地提车库的距离 )
	 * @param prSelfinputleg
	 * @param prSelfinputlegs
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 上午10:02:19
	 */
	public void getloopEmptlyDistance1( PrSelfinputleg prSelfinputleg ,
	        List< PrSelfinputleg > prSelfinputlegs )
	{
		String sql = "select pr.kilometer kilo from pr_empty_kilometer pr where trim(pr.pointname)=? and trim(pr.cityname)=?";
		
		Query query = getSession().createSQLQuery( sql );
		query.setParameter( 0 , prSelfinputlegs.get( 0 ).getStartpoint() );
		query.setParameter( 1 , prSelfinputleg.getEndcity() );
		List< Object > list = query.list();
		if ( list.size() > 0 )
		{
			Double kilometer1 = Double.parseDouble( list.get( 0 ).toString() );
			
			prSelfinputleg.setEmptlyDistance( kilometer1 );
			prSelfinputleg.setEmptlyflag( 1 );// 设为空载
			prSelfinputleg.setMessage( "此条线路全程空载！目的地城市【"
			        + prSelfinputleg.getEndcity() + "】至第一条线路提车点【"
			        + prSelfinputlegs.get( 0 ).getStartpoint() + "】空载距离为"
			        + kilometer1 );
			
		}
		else
		{
			prSelfinputleg.setEmptlyflag( 2 );
			prSelfinputleg.setMessage( "此条线路无效！目的地城市【"
			        + prSelfinputleg.getEndcity() + "】至第一条线路提车点【"
			        + prSelfinputlegs.get( 0 ).getStartpoint() + "】无空载距离！" );
		}
		
	}
	
	/**
	 * @Description: TODO如果这条空载线路不是环线的最后一条:当前线路目的地城市至下一条线路出发地提车库的距离)
	 * @param prSelfinputleg
	 * @param prSelfinputlegs
	 * @param index
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 上午10:05:28
	 */
	public void getloopEmptlyDistance2( PrSelfinputleg prSelfinputleg ,
	        List< PrSelfinputleg > prSelfinputlegs , int index )
	{
		
		String sql = "select pr.kilometer kilo from pr_empty_kilometer pr where trim(pr.pointname)=? and trim(pr.cityname)=?";
		try
		{
			Query query = getSession().createSQLQuery( sql );
			query.setParameter( 0 , prSelfinputlegs.get( index + 1 )
			        .getStartpoint() );// 下一条线路出发地提车库
			query.setParameter( 1 , prSelfinputleg.getEndcity() );// 当前线路目的地城市与下一条线路出发地提车库的距离
			List< Object > list = query.list();
			if ( list.size() > 0 )
			{
				Double kilometer1 = Double.parseDouble( list.get( 0 )
				        .toString() );
				
				prSelfinputleg.setEmptlyDistance( kilometer1 );
				prSelfinputleg.setEmptlyflag( 1 );// 设为空载
				prSelfinputleg.setMessage( "目的地城市【"
				        + prSelfinputleg.getEndcity() + "】至提车点【"
				        + prSelfinputlegs.get( index + 1 ).getStartpoint()
				        + "】空载距离为" + kilometer1 );

			}
			else
			{
				prSelfinputleg.setEmptlyflag( 2 );// 设为无效
				prSelfinputleg.setMessage( "此条线路无效！目的地城市【"
				        + prSelfinputleg.getEndcity() + "】至提车点【"
				        + prSelfinputlegs.get( index + 1 ).getStartpoint()
				        + "】查询不到空载距离" );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param prSelfinputleg
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 上午10:27:42
	 */
	public void setEmptlyState( PrSelfinputleg prSelfinputleg )
	{
		/*String sql = "select pr.kilometer kilo from pr_empty_kilometer pr where trim(pr.pointname)='"
		        + prSelfinputleg.getStartpoint()
		        + "' and pr.cityname='"
		        + prSelfinputleg.getEndcity() + "'";
		
		try
		{
			Query query = getSession().createSQLQuery( sql );
			
			List< Object > list = query.list();
			if ( list.size() > 0 )// 如果能查到空载距离，则设为空载 状态1
			{
				String hql = "update PrSelfinputleg as pr set pr.emptlyflag=1 where pr.id=? ";
				Query query2 = getSession().createQuery( hql );
				query2.setParameter( 0 , prSelfinputleg.getId() );
				query2.executeUpdate();
			}
			else
			// 如果查不到空载距离设置无效线路
			{
				String hql = "update PrSelfinputleg as pr set pr.emptlyflag=2 where pr.id=? ";
				Query query3 = getSession().createQuery( hql );
				query3.setParameter( 0 , prSelfinputleg.getId() );
				query3.executeUpdate();
				prSelfinputleg.setMessage( "提车库【"
				        + prSelfinputleg.getStartpoint() + "】至目的地城市【"
				        + prSelfinputleg.getEndcity() + "】查询不到空载距离！" );
				getSession().saveOrUpdate( prSelfinputleg );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}*/
		try
		{
			String hql = "update PrSelfinputleg as pr set pr.emptlyflag=1 where pr.id=? ";
			Query query2 = getSession().createQuery( hql );
			query2.setParameter( 0 , prSelfinputleg.getId() );
			query2.executeUpdate();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @Description: TODO(调用存储过程算出应收公里)
	 * @param prSelfinputleg
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 下午1:43:34
	 */
	public void getIncomeDistance( PrSelfinputleg prSelfinputleg )
	{
		String callSql = "{Call  price_legincomedistance(?,?,?)}";
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
			
			statement.registerOutParameter( 3 , Types.DOUBLE );
			
			statement.execute();
			double incomeDistance = statement.getDouble( 3 ); // 应收公里
			prSelfinputleg.setIncomeDistance( incomeDistance );
			conn.commit();
			statement.close();
			conn.close();
			session.close();
			

		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @Description: TODO(初始化所有状态) void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 下午3:41:32
	 */
	public void initAll()
	{
		String hql = "update PrSelfinputleg as pr set pr.emptlyflag=0  ";
		Query query3 = getSession().createQuery( hql );

		query3.executeUpdate();
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param smuser
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-15 上午11:53:06
	 */
	public void deleteallLegByUser( String smuser )
	{
		String hql = "delete PrSelfinputleg as pr where pr.username = '"
		        + smuser + "'";
		Query query = getSession().createQuery( hql );
		query.executeUpdate();
	}
	
	/**
	 * @Description: TODO(查出应收公里)
	 * @param prSelfinputleg
	 * @param parseInt
	 * @return double 返回值描述
	 * @author liuwu
	 * @create_date 2015-2-5 下午2:00:57
	 */
	public double getIncomeDistance2( PrSelfinputleg prSelfinputleg ,
	        int customerId )
	{
		String sql = "select sma.dckilometer from smARKilometer sma  where sma.istartcityid="
		        + "(select ilineid from smStartCity where vccityname=?) and sma.idestcityid="
		        + "(select ilineid from smDestCity where vccityname=?)  and sma.icustomerid=? and sma.dtedate>sysdate";
		try
		{
			Query query = getSession().createSQLQuery( sql );
			query.setParameter( 0 , prSelfinputleg.getStartcity() );
			query.setParameter( 1 , prSelfinputleg.getEndcity() );
			query.setParameter( 2 , customerId );

			if ( query.list().size() > 0 )
			{
				double incomeDistance = Double.parseDouble( query.list()
				        .get( 0 ).toString() );
				System.out.println( "incomeDistance" + incomeDistance );
				return incomeDistance;
			}
			else
			{
				return 0;
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}

		return 0;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param prSelfinputleg
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-2-13 上午9:57:04
	 */
	public void getActualCostDistance( PrSelfinputleg prSelfinputleg )
	{
		String callSql = "{Call price_legcostdistance(?,?,?)}";
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
			statement.registerOutParameter( 3 , Types.DOUBLE );
			statement.execute();
			double costDistance = statement.getDouble( 3 );// 应付单价
			conn.commit();
			statement.close();
			conn.close();
			session.close();
			if ( costDistance != 0.0 )
			{
				prSelfinputleg.setCostDistance( costDistance );
			}
			else
			{
				prSelfinputleg.setCostDistance( 0.0 );
				// prLegfileimport.setEmptlyFlag( 2 );
				prSelfinputleg.setMessage( "起始地【"
				        + prSelfinputleg.getStartcity() + "】---目的地【"
				        + prSelfinputleg.getEndcity() + "】查询不到应付公里数！" );
			}

		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @Description: TODO(查询绕城公里数)
	 * @param prSelfinputleg
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-2 下午2:51:45
	 */
	public void getAroundDistance( PrSelfinputleg prSelfinputleg )
	{
		String sql = "select sm.dccirdekm from SMCITY sm where sm.vccityname= ?";
		
		try
		{
			Query query = getSession().createSQLQuery( sql );
			query.setParameter( 0 , prSelfinputleg.getEndcity() );
			List< Object > list = query.list();
			if ( query.uniqueResult() != null )
			{
				Double kilometer1 = Double.parseDouble( list.get( 0 )
				        .toString() );
				// prLegfileimport.setEmptlyFlag( 1 );
				prSelfinputleg.setAroundDistance( kilometer1 );
			}
			else
			{
				// prLegfileimport.setEmptlyFlag( 2 );// 设为无效
				prSelfinputleg.setAroundDistance( 40.0 );// 默认值是40
				prSelfinputleg.setMessage( "起始地城市【"
				        + prSelfinputleg.getStartcity() + "】至目的地城市【"
				        + prSelfinputleg.getEndcity() + "】查询不到绕城公里数！" );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param startcity
	 * @param startpoint
	 * @param endcity
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-2 下午4:31:36
	 */
	public String checkDataAvailability( String startcity , String startpoint ,
	        String endcity )
	{
		// 检查出发地城市和目的地城市
		String checkStartcity = "select count(1) from smStartCity where vcCityName='"
		        + startcity + "'";
		
		String checkEndcity = "select count(1) from smDestCity where vcCityName='"
		        + endcity + "'";
		
		// 检查提车点的有效性
		String checkpoint = "   select count(1) from smGetPoint smg where smg.vccityname='"
		        + startcity + "' and smg.vcpointname='" + startpoint + "' ";
		try
		{
			Query checkStartcityQuery = getSession().createSQLQuery(
			        checkStartcity );
			int startcityCount = Integer.parseInt( checkStartcityQuery.list()
			        .get( 0 ).toString() );
			
			if ( startcityCount == 0 )
			{
				return "出发地城市【" + startcity + "】无效！请检查！";
			}
			else
			{
				Query checkEndCityQuery = getSession().createSQLQuery(
				        checkEndcity );
				int endcityCount = Integer.parseInt( checkEndCityQuery.list()
				        .get( 0 ).toString() );
				if ( endcityCount == 0 )
				{
					return "目的地城市【" + endcity + "】无效！";
				}
				else
				{
					Query checkPointquery = getSession().createSQLQuery(
					        checkpoint );
					int checkPointCount = Integer.parseInt( checkPointquery
					        .list().get( 0 ).toString() );
					System.out.println( checkPointCount );
					if ( checkPointCount == 0 )
					{
						return "出发地城市：【" + startcity + "】中没有【" + startpoint
						        + "】该提车库;请检查！";
					}
					else
					{
						return "";
					}
				}
				
			}
			
		}
		catch ( Exception e )
		{
			// TODO: handle exception
		}
		return "";
	}
	
	/**
	 * @Description: TODO(单线情况2：部分空载情况计算空载距离：查找目的地和目的地最近的提车库之间的距离)
	 * @param prLegfileimport
	 * @param startpoint2
	 * @param endcity2
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-20 上午10:33:24
	 */
	public void getEmptlyDistanceForSingle2( PrSelfinputleg prSelfinputleg )
	{
		String sql = "SELECT  pr.kilometer FROM PR_EMPTY_KILOMETER pr where trim(pr.cityname)='"
		        + prSelfinputleg.getEndcity() + "' order by pr.kilometer asc";
		try
		{
			Query query = getSession().createSQLQuery( sql );
			System.out.println( query.getQueryString().toString() );
			List< Object > list = query.list();
			if ( list.size() > 0 )
			{
				Double kilometer = Double
				        .parseDouble( list.get( 0 ).toString() );
				prSelfinputleg.setEmptlyDistance( kilometer );
				prSelfinputleg.setEmptlyflag( 0 );
				prSelfinputleg.setMessage( "此单线的目的地【"
				        + prSelfinputleg.getEndcity() + "】离最近的提车库之间空载距离为"
				        + kilometer );
				
			}
			else
			{
				prSelfinputleg.setEmptlyflag( 2 );// 查不到设为无效
				prSelfinputleg.setMessage( "此单线的目的地【"
				        + prSelfinputleg.getEndcity() + "】离最近的提车库之间空载距离查询不到" );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}
}