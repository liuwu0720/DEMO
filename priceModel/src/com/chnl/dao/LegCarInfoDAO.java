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
import com.chnl.pojo.CarInfo;
import com.chnl.pojo.LegCarInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * LegCarInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.chnl.pojo.LegCarInfo
 * @author MyEclipse Persistence Tools
 */
@Repository
public class LegCarInfoDAO extends BaseDAO
{
	private static final Logger log = LoggerFactory
	        .getLogger( LegCarInfoDAO.class );
	
	// property constants
	
	public void save( LegCarInfo transientInstance )
	{
		log.debug( "saving LegCarInfo instance" );
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
	
	public void delete( LegCarInfo persistentInstance )
	{
		log.debug( "deleting LegCarInfo instance" );
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
	
	public LegCarInfo findById( java.lang.Integer id )
	{
		log.debug( "getting LegCarInfo instance with id: " + id );
		try
		{
			LegCarInfo instance = ( LegCarInfo ) getSession().get(
			        "com.chnl.pojo.LegCarInfo" , id );
			return instance;
		}
		catch ( RuntimeException re )
		{
			log.error( "get failed" , re );
			throw re;
		}
	}
	
	public List findByExample( LegCarInfo instance )
	{
		log.debug( "finding LegCarInfo instance by example" );
		try
		{
			List results = getSession()
			        .createCriteria( "com.chnl.pojo.LegCarInfo" )
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
		log.debug( "finding LegCarInfo instance with property: " + propertyName
		        + ", value: " + value );
		try
		{
			String queryString = "from LegCarInfo as model where model."
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
		log.debug( "finding all LegCarInfo instances" );
		try
		{
			String queryString = "from LegCarInfo as l order by l.legInfo.id";
			Query queryObject = getSession().createQuery( queryString );
			return queryObject.list();
		}
		catch ( RuntimeException re )
		{
			log.error( "find all failed" , re );
			throw re;
		}
	}
	
	public LegCarInfo merge( LegCarInfo detachedInstance )
	{
		log.debug( "merging LegCarInfo instance" );
		try
		{
			LegCarInfo result = ( LegCarInfo ) getSession().merge(
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
	
	public void attachDirty( LegCarInfo instance )
	{
		log.debug( "attaching dirty LegCarInfo instance" );
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
	
	public void attachClean( LegCarInfo instance )
	{
		log.debug( "attaching clean LegCarInfo instance" );
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
     * @return 
     *   List<LegCarInfo> 返回值描述
     * @author liuwu
     * @create_date 2014-8-11 下午6:45:46
     */ 
    public List< LegCarInfo > findByLegIdDao( int legId )
    {
	    // TODO Auto-generated method stub
    	String queryString = "from LegCarInfo l where l.legInfo.id = "+ legId;
    	List< LegCarInfo > legCarInfos = getSession().createQuery( queryString ).list();
	    return legCarInfos;
    }

	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param legId
     * @return 
     *   List 返回值描述
     * @author liuwu
	 * @param pageSize 
	 * @param page 
     * @create_date 2014-8-12 上午11:30:35
     */ 
    @SuppressWarnings( "unchecked" )
    public List findByLegId( int legId, int page, int pageSize)
    {
	    // TODO Auto-generated method stub
    	String hql = "from LegCarInfo as l left join l.carInfo c where l.legInfo.id ="+legId;
    	Query query = getSession().createQuery(hql);
		query.setFirstResult((page-1)*pageSize);
		query.setMaxResults(pageSize);
    	List carInfoList = query.list();
	    return carInfoList;
    }
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legId
	 * @return List<LegCarInfo> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-9 上午9:18:22
	 */
	@SuppressWarnings( "unchecked" )
	public List< LegCarInfo > findByLegIdByPage( int legId , int page ,
	        int pageSize )
	{
		String hql = "from LegCarInfo where legInfo.id = " + legId;
		Query query = getSession().createQuery( hql );
		query.setFirstResult( ( page - 1 ) * pageSize );
		query.setMaxResults( pageSize );
		List< LegCarInfo > legCarInfos = query.list();
		return legCarInfos;
	}

	/** 
     * @Description: TODO(根据线路ID查出所有legcarinfo) 
     * @param parseInt
     * @return 
     *   List 返回值描述
     * @author liuwu
     * @create_date 2014-8-12 下午4:38:09
     */ 
    public List findByLegId( int parseInt )
    {
    	String hql = "from LegCarInfo as l left join l.carInfo c where l.legInfo.id ="+parseInt;
    	Query query = getSession().createQuery(hql);
		
    	List carInfoList = query.list();
	    return carInfoList;
    }

	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param parseInt
     * @return 
     *   List<LegCarInfo> 返回值描述
     * @author liuwu
     * @create_date 2014-8-13 下午12:02:28
     */ 
    @SuppressWarnings( "unchecked" )
    public List< LegCarInfo > getLegCarListByLegId( int legId )
    {
	    // TODO Auto-generated method stub
    	String hql = "from LegCarInfo as l where l.legInfo.id ="+ legId+"order by l.carInfo.id";
    	Query query = getSession().createQuery( hql );
    	List< LegCarInfo > legCarInfos = query.list();
	    return legCarInfos;
    }

	/** 
     * @Description: TODO(为分页重写的方法) 
     * @param legId
     * @param page
     * @param pageSize
     * @return 
     *   List<LegCarInfo> 返回值描述
     * @author liuwu
     * @create_date 2014-8-13 下午2:22:25
     */ 
    @SuppressWarnings( "unchecked" )
    public List< LegCarInfo > getLegCarListByLegId( int legId , int page ,
            int pageSize )
    {
    	String hql = "from LegCarInfo as l where l.legInfo.id ="+ legId;
    	Query query = getSession().createQuery( hql );
    	query.setFirstResult((page-1)*pageSize);
		query.setMaxResults(pageSize);
    	List< LegCarInfo > carInfoList = query.list();
	    return carInfoList;
    	
    }

	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param car1Id
     * @param currentLegId
     * @return 
     *   LegCarInfo 返回值描述
     * @author liuwu
     * @create_date 2014-8-19 下午4:24:27
     */ 
    public LegCarInfo getIncomeRatio( int car1Id , int currentLegId )
    {
	    // TODO Auto-generated method stub
    	String hql = "from LegCarInfo as l where l.legInfo.id=? and l.carInfo.id=?";
    	Query query = getSession().createQuery( hql );
    	query.setInteger( 0 , currentLegId );
    	query.setInteger( 1 , car1Id );
    	LegCarInfo legCarInfo = ( LegCarInfo ) query.list().get( 0 );
	    return legCarInfo;
    }

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legcarId
	 * @param incomePrice
	 * @param vendorCost
	 * @param ratio
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-9 下午2:22:16
	 */
	public void updateInfo( int legcarId , double incomePrice ,
	        double vendorCost , double ratio )
	{
		String sql = "update leg_car_info set INCOMEPRICE=" + incomePrice
		        + " ,VENDORCOST=" + vendorCost + " ,RATIO=" + ratio
		        + " where id=" + legcarId;
		int query = jdbcTemplate.update( sql );
		
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param curId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-9 下午5:07:18
	 */
	public void deleteById( int curId )
	{
		String hql = "delete LegCarInfo where id=?";
		Query query = getSession().createQuery( hql );
		query.setInteger( 0 , curId );
		query.executeUpdate();
		
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legId
	 * @return List<Map<String,Object>> 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-9 下午6:59:01
	 */
	public List< Map< String , Object >> getSelectCarBylegId( int legId )
	{
		String sql = "from CarInfo c where c.id not in(select l.carInfo.id from LegCarInfo l where l.legInfo.id=?) order by c.id";
		List< Map< String , Object >> result = new ArrayList< Map< String , Object >>();
		Query query = getSession().createQuery( sql );
		query.setInteger( 0 , legId );
		List< CarInfo > list = query.list();
		for ( CarInfo carInfo : list )
		{
			Map< String , Object > map = new HashMap< String , Object >();
			map.put( "id" , carInfo.getId() );
			map.put( "carname" , carInfo.getCarname().trim() );
			result.add( map );
		}
		return result;
	}

}