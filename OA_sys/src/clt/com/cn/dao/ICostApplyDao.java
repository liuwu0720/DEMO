package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.CostApply;
import clt.com.cn.model.entity.CostApplyitem;

/** 
 * @Package clt.com.cn.dao 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年11月16日 下午2:29:08 
 * @version V1.0 
 */
public interface ICostApplyDao
{
	public void update( CostApply entity );
	
	public void merge( CostApply entity );
	
	public CostApply get( int id );
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param sql
	 * @return 
	 *   List 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月16日 下午3:04:55
	 */
	List findAllByPage( String sql );
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param costApply 
	 *   void 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月16日 下午3:59:31
	 */ 
    void save( CostApply costApply );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param feeId
	 * @return 
	 *   CostApply 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月17日 下午4:16:32
	 */ 
    CostApply getById( int feeId );	
	
	/**
	 * 
	 * @Description:分页查询
	 * @param hql
	 * @param pageSize
	 * @param page
	 * @param p
	 * @return 
	 *   List 返回值描述
	 * @author chengwzh
	 * @create_date 2015年11月17日 上午11:36:49
	 */
	public List< CostApply > pageQuery( final String hql , final Integer pageSize ,
	        final Integer page , final Object ... p );
	
	/**
	 * 
	 * @Description:统计记录条数
	 * @param hql
	 * @return 
	 *   int 返回值描述
	 * @author chengwzh
	 * @create_date 2015年11月17日 下午1:45:11
	 */
	public int getCountByHql( final String hql );
	
	/**
	 * 
	 * @Description:获取页数
	 * @param count
	 * @param pageSize
	 * @return 
	 *   int 返回值描述
	 * @author chengwzh
	 * @create_date 2015年11月17日 下午1:57:36
	 */
	public int getpages( int count , int pageSize );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param countSQL
	 * @return 
	 *   int 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月26日 上午11:29:34
	 */ 
    public int getCountBySql( String countSQL );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param sql
	 * @param pageSize
	 * @param page
	 * @return 
	 *   List 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月26日 上午11:37:05
	 */ 
    public List getDataBySqlQuery( String sql , int pageSize , int page );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param costApplyitems 
	 *   void 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月3日 下午4:34:11
	 */ 
    public void deleteAll( List< CostApplyitem > costApplyitems );
}
