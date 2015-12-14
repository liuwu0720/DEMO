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
import com.chnl.pojo.PrLegfileimport;

/**
 * A data access object (DAO) providing persistence and search support for
 * PrLegfileimport entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.chnl.dao.PrLegfileimport
 * @author MyEclipse Persistence Tools
 */
@Repository
public class PrLegfileimportDAO extends BaseDAO
{
	private static final Logger log = LoggerFactory
	        .getLogger( PrLegfileimportDAO.class );
	// property constants
	public static final String STARTCITY = "startcity";
	public static final String STARTPOINT = "startpoint";
	public static final String ENDCITY = "endcity";
	
	public void save( PrLegfileimport transientInstance )
	{
		log.debug( "saving PrLegfileimport instance" );
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
	
	public void delete( PrLegfileimport persistentInstance )
	{
		log.debug( "deleting PrLegfileimport instance" );
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
	
	public PrLegfileimport findById( java.lang.Integer id )
	{
		log.debug( "getting PrLegfileimport instance with id: " + id );
		try
		{
			PrLegfileimport instance = ( PrLegfileimport ) getSession().get(
			        "com.chnl.pojo.PrLegfileimport" , id );
			return instance;
		}
		catch ( RuntimeException re )
		{
			log.error( "get failed" , re );
			throw re;
		}
	}
	
	public List findByExample( PrLegfileimport instance )
	{
		log.debug( "finding PrLegfileimport instance by example" );
		try
		{
			List results = getSession()
			        .createCriteria( "com.chnl.pojo.PrLegfileimport" )
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
		log.debug( "finding PrLegfileimport instance with property: "
		        + propertyName + ", value: " + value );
		try
		{
			String queryString = "from PrLegfileimport as model where model."
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
	
	public List findByStartpoint( Object startpoint )
	{
		return findByProperty( STARTPOINT , startpoint );
	}
	
	public List findByEndcity( Object endcity )
	{
		return findByProperty( ENDCITY , endcity );
	}
	
	public List findAll()
	{
		log.debug( "finding all PrLegfileimport instances" );
		try
		{
			String queryString = "from PrLegfileimport";
			Query queryObject = getSession().createQuery( queryString );
			return queryObject.list();
		}
		catch ( RuntimeException re )
		{
			log.error( "find all failed" , re );
			throw re;
		}
	}
	
	public List findAllOrderBy( SmUser smUser )
	{
		log.debug( "finding all PrLegfileimport instances" );
		try
		{
			String queryString = "from PrLegfileimport as p where p.username = ? order by p.loopflag,p.id";
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
	
	public PrLegfileimport merge( PrLegfileimport detachedInstance )
	{
		log.debug( "merging PrLegfileimport instance" );
		try
		{
			PrLegfileimport result = ( PrLegfileimport ) getSession().merge(
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
	
	public void attachDirty( PrLegfileimport instance )
	{
		log.debug( "attaching dirty PrLegfileimport instance" );
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
	
	public void attachClean( PrLegfileimport instance )
	{
		log.debug( "attaching clean PrLegfileimport instance" );
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
	 * @Description: TODO(清除以前导入的数据) void 返回值描述
	 * @author liuwu
	 * @param smUser
	 * @create_date 2014-11-6 下午3:05:35
	 */
	public void cleanBeforeInser( SmUser smUser )
	{
		String sql = "delete PrLegfileimport where username = ? ";
		try
		{
			Query query = getSession().createQuery( sql );
			query.setParameter( 0 , smUser.getUserName() );
			query.executeUpdate();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		

	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param startcity2
	 * @param startpoint2
	 *            void 返回值描述
	 * @author liuwu
	 * @param customer
	 * @param prLegfileimport
	 * @param endcity2
	 * @return
	 * @create_date 2014-11-18 上午10:01:24
	 */
	public String checkDataAvailability( String startcity , String startpoint ,
	        String endcity , String customer , PrLegfileimport prLegfileimport )
	{
		// 检查出发地城市和目的地城市
		String checkStartcity = "select count(1) from smStartCity where vcCityName='"
		        + startcity + "'";
		
		String checkEndcity = "select count(1) from smDestCity where vcCityName='"
		        + endcity + "'";

		// 检查提车点的有效性
		String checkpoint = "   select count(1) from smGetPoint smg where smg.vccityname='"
		        + startcity + "' and smg.vcpointname='" + startpoint + "' ";
		// 检查客户
		String checkCustomer = "select distinct  t.icustomerid from SMGETPOINT t,smcustomer cu where t.icustomerid=cu.ilineid and t.vcpointname='"
		        + startpoint + "' and cu.vccustomername='" + customer + "'";

		try
		{
			Query checkStartcityQuery = getSession().createSQLQuery(
			        checkStartcity );
			int startcityCount = Integer.parseInt( checkStartcityQuery
			        .list().get( 0 ).toString() );

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
						return "出发地城市：【" + startcity + "】中没有【"
						        + startpoint + "】该提车库;请检查！";
					}
					else
					{
						Query checkCustomerQuery = getSession().createSQLQuery(
						        checkCustomer );
						
						if ( checkCustomerQuery.uniqueResult() == null )
						{
							return "【" + startpoint + "】该提车库,没有客户【" + customer
							        + "】";
						}
						else
						{
							int customerId = Integer
							        .parseInt( checkCustomerQuery.list()
							                .get( 0 ).toString() );
							prLegfileimport.setCustomerId( customerId );
							return "";
						}
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
	 * @Description: TODO(环线情况下的空载距离计算)
	 * @param prLegfileimport
	 * @param endcity2
	 * @param startpoint2
	 *            void 返回值描述
	 * @author liuwu
	 * @return
	 * @create_date 2014-11-18 下午4:53:40
	 */
	public Double getEmptlyDistance( PrLegfileimport prLegfileimport ,
	        String endcity , String startpoint )
	{
		String sql = "select pr.kilometer kilo from pr_empty_kilometer pr where trim(pr.pointname)='"
		        + startpoint + "' and trim(pr.cityname)='" + endcity + "'";
		try
        {
	        Query query = getSession().createSQLQuery( sql );
			System.out.println( query.getQueryString().toString() );
			List< Object > list = query.list();
			if ( prLegfileimport.getEmptlyFlag() == 0 ) // 全程空载情况下状态为1；待定情况：状态3
			{
				if ( list.size() > 0 )
				{
					Double kilometer = Double.parseDouble( list.get( 0 )
					        .toString() );
					prLegfileimport.setEmptlyDistance( kilometer );

					prLegfileimport.setEmptlyFlag( 0 ); // 有效
					prLegfileimport.setMessage( "请选择车辆进行下一步操作！" );
					return kilometer;

				}
				else
				{
					String message = "目的地【" + endcity + "】至提车库【" + startpoint
					        + "】无空载距离！此条环线视为无效线路";
					prLegfileimport.setEmptlyFlag( 2 );// 设为无效
					prLegfileimport.setMessage( message );
					// getSession().saveOrUpdate( prLegfileimport );
					return null;
				}
			}


        }
        catch ( Exception e )
        {
			e.printStackTrace();
        }
		return null;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param prLegfileimport
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-19 下午1:51:08
	 */
	@SuppressWarnings( "unchecked" )
	public void updateLoopLegFlag( PrLegfileimport prLegfileimport )
	{
		double loopflag = prLegfileimport.getLoopflag();
		String hql = "from PrLegfileimport as pr where pr.loopflag=?";
		       

		try
		{
			Query query = getSession().createQuery( hql );
			query.setParameter( 0 , loopflag );
			List< PrLegfileimport > prlist = query.list();
			System.out.println( prlist.size() );
			for ( PrLegfileimport pLegfileimport : prlist )
			{
				String hql2 = "update PrLegfileimport as pr set pr.emptlyFlag=2 where pr.id=?";
				Query query2 = getSession().createQuery( hql2 );
				query2.setParameter( 0 , pLegfileimport.getId() );
				int count = query2.executeUpdate();
				System.out.println( count );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param prLegfileimport
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-19 下午6:23:30
	 */
	public void setValid( PrLegfileimport prLegfileimport )
	{
		String hql2 = "update PrLegfileimport as pr set pr.emptlyFlag=2 where pr.id=?";
		Query query2 = getSession().createQuery( hql2 );
		query2.setParameter( 0 , prLegfileimport.getId() );
		int count = query2.executeUpdate();
		System.out.println( count );
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param endcity2
	 * @param startcity2
	 *            void 返回值描述
	 * @author liuwu
	 * @return
	 * @create_date 2014-11-20 上午9:37:14
	 */
	public boolean checkIfSameProvince( String endcity , String startcity )
	{
		String sql = "select vcprovince from smcity where vccityname= ?";
		try
		{
			Query query = getSession().createSQLQuery( sql );
			query.setParameter( 0 , endcity );
			String endString = query.list().get( 0 ).toString();
			if ( endString != null )
			{
				Query query2 = getSession().createSQLQuery( sql );
				query2.setParameter( 0 , startcity );
				String startString = query2.list().get( 0 ).toString();
				if ( endString.equals( startString ) )
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		catch ( Exception e )
		{
			// TODO: handle exception
		}
		return false;
	}
	
	/**
	 * @Description: TODO(单线情况下计算空载距离：起始地至目的地之间的距离)
	 * @param prLegfileimport
	 * @param startpoint2
	 * @param endcity2
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-20 上午10:33:24
	 */
	public void getEmptlyDistanceForSingle( PrLegfileimport prLegfileimport ,
	        String endcity )
	{
		String sql = "select pr.kilometer kilo from pr_empty_kilometer pr where trim(pr.pointname)='"
		        + prLegfileimport.getStartcity()
		        + "' and trim(pr.cityname)='"
		        + endcity + "'";
		try
		{
			Query query = getSession().createSQLQuery( sql );
			System.out.println( query.getQueryString().toString() );
			List< Object > list = query.list();
			if ( list.size() > 0 )
			{
				Double kilometer = Double
				        .parseDouble( list.get( 0 ).toString() );
				prLegfileimport.setEmptlyDistance( kilometer );
				prLegfileimport.setEmptlyFlag( 1 );
				prLegfileimport.setMessage( "此单线全程空载！起始地【"
				        + prLegfileimport.getStartcity()
				        + "】与目的地【"
				        + endcity + "】空载距离为" + kilometer );

			}
			else
			{
				prLegfileimport.setEmptlyFlag( 2 );// 查询不到距离设为无效
				prLegfileimport.setMessage( "此单线全程空载无效！查询不到起始地【"
				        + prLegfileimport.getStartcity()
				        + "】与目的地【" + endcity + "】空载距离！" );
			}
			

		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
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
	public void getEmptlyDistanceForSingle2( PrLegfileimport prLegfileimport )
	{
		String sql = "SELECT  pr.kilometer FROM PR_EMPTY_KILOMETER pr where trim(pr.cityname)='"
		        + prLegfileimport.getEndcity() + "' order by pr.kilometer asc";
		try
		{
			Query query = getSession().createSQLQuery( sql );
			System.out.println( query.getQueryString().toString() );
			List< Object > list = query.list();
			if ( list.size() > 0 )
			{
				Double kilometer = Double
				        .parseDouble( list.get( 0 ).toString() );
				prLegfileimport.setEmptlyDistance( kilometer );
				prLegfileimport.setEmptlyFlag( 0 );
				prLegfileimport.setMessage( "此单线的目的地【"
				        + prLegfileimport.getEndcity() + "】离最近的提车库之间空载距离为"
				        + kilometer );
				
			}
			else
			{
				prLegfileimport.setEmptlyFlag( 2 );// 查不到设为无效
				prLegfileimport.setMessage( "此单线的目的地【"
				        + prLegfileimport.getEndcity() + "】离最近的提车库之间空载距离查询不到" );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * @Description: TODO 用户选择后设置空载有效性状态
	 * @param prLegfileimport
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-20 上午10:51:44
	 */
	public void setEmptlyState( PrLegfileimport prLegfileimport )
	{
		/*String sql = "select pr.kilometer kilo from pr_empty_kilometer pr where trim(pr.pointname)='"
		        + prLegfileimport.getStartpoint()
		        + "' and pr.cityname='"
		        + prLegfileimport.getEndcity() + "'";

		try
		{
			Query query = getSession().createSQLQuery( sql );

			List< Object > list = query.list();
			if ( list.size() > 0 )// 如果能查到空载距离，则设为空载 状态1
			{
				String hql = "update PrLegfileimport as pr set pr.emptlyFlag=1 where pr.id=? ";
				Query query2 = getSession().createQuery( hql );
				query2.setParameter( 0 , prLegfileimport.getId() );
				query2.executeUpdate();
			}
			else
			// 如果查不到空载距离设置无效线路
			{
				String hql = "update PrLegfileimport as pr set pr.emptlyFlag=2 where pr.id=? ";
				Query query3 = getSession().createQuery( hql );
				query3.setParameter( 0 , prLegfileimport.getId() );
				query3.executeUpdate();
				prLegfileimport.setMessage( "提车库【"
				        + prLegfileimport.getStartpoint() + "】至目的地城市【"
				        + prLegfileimport.getEndcity() + "】查询不到空载距离！" );
				getSession().saveOrUpdate( prLegfileimport );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}*/
		try
		{
			String hql = "update PrLegfileimport as pr set pr.emptlyFlag=1 where pr.id=? ";
			Query query2 = getSession().createQuery( hql );
			query2.setParameter( 0 , prLegfileimport.getId() );
			query2.executeUpdate();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: TODO(调用存储过程算出应收公里)
	 * @param prLegfileimport
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-24 上午9:27:12
	 */
	public void getIncomeDistance( PrLegfileimport prLegfileimport )
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
			
			statement.setString( 1 , prLegfileimport.getStartcity() );
			statement.setString( 2 , prLegfileimport.getEndcity() );
			
			statement.registerOutParameter( 3 , Types.DOUBLE );

			statement.execute();
			double incomeDistance = statement.getDouble( 3 ); // 应收公里
			prLegfileimport.setIncomeDistance( incomeDistance );
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
	 * @Description: TODO(查出应收公里)
	 * @param prLegfileimport
	 * @param parseInt
	 * @return double 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-29 下午3:41:50
	 */
	public double getIncomeDistance2( PrLegfileimport prLegfileimport ,
	        int customerId )
	{
		String sql = "select sma.dckilometer from smARKilometer sma  where sma.istartcityid="
		        + "(select ilineid from smStartCity where vccityname=?) and sma.idestcityid="
		        + "(select ilineid from smDestCity where vccityname=?)  and sma.icustomerid=? and sma.dtedate>sysdate ";
		try
		{
			Query query = getSession().createSQLQuery( sql );
			query.setParameter( 0 , prLegfileimport.getStartcity() );
			query.setParameter( 1 , prLegfileimport.getEndcity() );
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
	 * @Description: TODO(如果这条空载线路是环线的最后一条：当前线路目的地城市至第一条线路出发地提车库的距离 )
	 * @param prLegfileimport
	 * @param loopLegs
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-24 下午2:10:53
	 */
	public void getLoopEmptlyDistance1( PrLegfileimport prLegfileimport ,
	        List< PrLegfileimport > loopLegs )
	{
		String sql = "select pr.kilometer kilo from pr_empty_kilometer pr where trim(pr.pointname)=? and trim(pr.cityname)=?";
		
		Query query = getSession().createSQLQuery( sql );
		query.setParameter( 0 , loopLegs.get( 0 ).getStartpoint() );
		query.setParameter( 1 , prLegfileimport.getEndcity() );
		List< Object > list = query.list();
		if ( list.size() > 0 )
		{
			Double kilometer1 = Double.parseDouble( list.get( 0 ).toString() );
			prLegfileimport.setEmptlyDistance( kilometer1 );
			prLegfileimport.setEmptlyFlag( 1 );
			prLegfileimport.setMessage( "目的地城市【" + prLegfileimport.getEndcity()
			        + "】至第一条线路提车点【" + loopLegs.get( 0 ).getStartpoint()
			        + "】空载距离为" + kilometer1 );
			
		}
		else
		{
			prLegfileimport.setEmptlyFlag( 2 );
			prLegfileimport.setMessage( "无效！目的地城市【"
			        + prLegfileimport.getEndcity() + "】至第一条线路提车点【"
			        + loopLegs.get( 0 ).getStartpoint() + "】查询不到空载距离" );
		}
	}
	
	/**
	 * @Description: TODO(如果这条空载线路不是环线的最后一条:当前线路起始地城市与目的地城市之间的距离
	 *               +目的地至下一条线路出发地提车库的距离)
	 * @param prLegfileimport
	 * @param loopLegs
	 *            void 返回值描述
	 * @author liuwu
	 * @param index
	 * @create_date 2014-11-24 下午2:34:36
	 */
	public void getLoopEmptlyDistance2( PrLegfileimport prLegfileimport ,
	        List< PrLegfileimport > loopLegs , int index )
	{
		String sql = "select pr.kilometer kilo from pr_empty_kilometer pr where trim(pr.pointname)=? and trim(pr.cityname)=?";
		try
		{
			Query query = getSession().createSQLQuery( sql );
			query.setParameter( 0 , loopLegs.get( index + 1 ).getStartpoint() );
			query.setParameter( 1 , prLegfileimport.getEndcity() );// 当前线路目的地城市至下一条线路出发地提车库的距离
			List< Object > list = query.list();
			if ( list.size() > 0 )
			{
				Double kilometer1 = Double.parseDouble( list.get( 0 )
				        .toString() );
				prLegfileimport.setEmptlyFlag( 1 );
				prLegfileimport.setEmptlyDistance( kilometer1 );
				prLegfileimport.setMessage( "目的地城市【"
				        + prLegfileimport.getEndcity() + "】至提车点【"
				        + loopLegs.get( index + 1 ).getStartpoint() + "】距离为"
				        + kilometer1 );

			}
			else
			{
				prLegfileimport.setEmptlyFlag( 2 );// 设为无效
				prLegfileimport.setMessage( "环线"
				        + prLegfileimport.getLoopflag() + "中此条线路无效！目的地城市【"
				        + prLegfileimport.getEndcity() + "】-->提车点【"
				        + loopLegs.get( index + 1 ).getStartpoint()
				        + "】查询不到空载距离" );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @Description: TODO(初始化所有数据) void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 下午3:36:07
	 */
	public void initAll()
	{
		String hql = "update PrLegfileimport as pr set pr.emptlyFlag=0 ";
		Query query3 = getSession().createQuery( hql );
		
		query3.executeUpdate();
		
	}
	
	/**
	 * @Description: TODO(验证应付公里数的有效性)
	 * @param prLegfileimport
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-2-12 下午4:57:07
	 */
	public void getActualCostDistance( PrLegfileimport prLegfileimport )
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
			
			statement.setString( 1 , prLegfileimport.getStartcity() );
			statement.setString( 2 , prLegfileimport.getEndcity() );
			statement.registerOutParameter( 3 , Types.DOUBLE );
			statement.execute();
			double costDistance = statement.getDouble( 3 );// 应付单价
			conn.commit();
			statement.close();
			conn.close();
			session.close();
			if ( costDistance != 0.0 )
			{
				prLegfileimport.setCostDistance( costDistance );
			}
			else
			{
				prLegfileimport.setCostDistance( 0.0 );
				// prLegfileimport.setEmptlyFlag( 2 );
				prLegfileimport.setMessage( "起始地【"
				        + prLegfileimport.getStartcity() + "】---目的地【"
				        + prLegfileimport.getEndcity() + "】查询不到应付公里数！" );
			}

		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: TODO(计算绕城公里数)
	 * @param prLegfileimport
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-2 下午2:32:27
	 */
	public void getAroundDistance( PrLegfileimport prLegfileimport )
	{
		String sql = "select sm.dccirdekm from SMCITY sm where sm.vccityname= ?";
		
		try
		{
			Query query = getSession().createSQLQuery( sql );
			query.setParameter( 0 , prLegfileimport.getEndcity() );
			List< Object > list = query.list();
			if ( query.uniqueResult() != null )
			{
				Double kilometer1 = Double.parseDouble( list.get( 0 )
				        .toString() );
				// prLegfileimport.setEmptlyFlag( 1 );
				prLegfileimport.setAroundDistance( kilometer1 );
			}
			else
			{
				// prLegfileimport.setEmptlyFlag( 2 );// 设为无效
				prLegfileimport.setAroundDistance( 40.0 );
				prLegfileimport.setMessage( "起始地城市【"
				        + prLegfileimport.getStartcity() + "】至目的地城市【"
				        + prLegfileimport.getEndcity() + "】查询不到绕城公里数！" );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	

}