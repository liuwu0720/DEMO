package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.CostApply;
import clt.com.cn.model.entity.CostApplyitem;
import clt.com.cn.model.entity.CostAuditrecord;
import clt.com.cn.model.entity.FeeTel;
import clt.com.cn.model.entity.FeeTraffic;
import clt.com.cn.model.entity.TakeFeeCompany;
import clt.com.cn.model.entity.Travelfee;

/** 
 * @Package clt.com.cn.service 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年11月16日 下午2:35:59 
 * @version V1.0 
 */
public interface ICostApplyService
{
	public void update( CostApply entity );
	
	public CostApply get( int id );
	
	public void delete( int id );
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param sql
	 * @return 
	 *   List 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月16日 下午3:03:30
	 */
	List findByPage( String sql );
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param costApply 
	 *   void 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月16日 下午3:58:34
	 */
	void save( CostApply costApply );
	
	/**
	 * 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param hql
	 * @param pageSize
	 * @param page
	 * @param p
	 * @return 
	 *   List 返回值描述
	 * @author chengwzh
	 * @create_date 2015年11月17日 上午11:37:44
	 */
	public List< CostApply > pageQuery( final String hql , final Integer pageSize ,
	        final Integer page , final Object ... p );
	
	/**
	 * 
	 * @Description:获取记录条数
	 * @param hql
	 * @return 
	 *   int 返回值描述
	 * @author chengwzh
	 * @create_date 2015年11月17日 上午11:58:03
	 */
	public int getCountByHql( final String hql );
	
	/**
	 * 
	 * @Description:获取总页数
	 * @param count
	 * @param pageSize
	 * @return 
	 *   int 返回值描述
	 * @author chengwzh
	 * @create_date 2015年11月17日 下午1:51:59
	 */
	public int getpages( int count , int pageSize );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param id
	 * @return 
	 *   List<CostAuditrecord> 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月25日 下午3:34:28
	 */ 
    public List< CostAuditrecord > findAllByCostApplyId( Integer id );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param countSQL
	 * @return 
	 *   int 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月26日 上午11:28:53
	 */ 
    public int getCountBySql( String countSQL );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param sql
	 * @param i
	 * @param page
	 * @return 
	 *   List 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月26日 上午11:35:16
	 */ 
    public List getDataBySqlQuery( String sql , int i , int page );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @return 
	 *   List<TakeFeeCompany> 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月2日 上午11:13:35
	 */ 
    public List< TakeFeeCompany > findAllTakeFeeCompany();

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param travelfee 
	 *   void 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月2日 下午4:40:11
	 */ 
    public void saveTravelFee( Travelfee travelfee );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param costApplyitem 
	 *   void 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月2日 下午4:41:16
	 */ 
    public void saveCosApplyItem( CostApplyitem costApplyitem );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param id
	 * @return 
	 *   List<CostApplyitem> 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月3日 下午2:03:49
	 */ 
    public List< CostApplyitem > findAllCostItemsById( Integer id );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param costApplyitems
	 * @return 
	 *   List<Travelfee> 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月3日 下午2:15:12
	 */ 
    public List< Travelfee > findAllTravelFee( List< CostApplyitem > costApplyitems );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param costApplyitem 
	 *   void 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月3日 下午3:44:42
	 */ 
    public void saveOrUpdateCosApplyItem( CostApplyitem costApplyitem );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param id 
	 *   void 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月3日 下午4:32:13
	 */ 
    public void deleteTravelFeeHis( Integer id );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param feeTraffic 
	 *   void 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月3日 下午8:03:00
	 */ 
    public void saveFeeTraffic( FeeTraffic feeTraffic );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param feeTel 
	 *   void 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月3日 下午8:11:17
	 */ 
    public void saveFeeTel( FeeTel feeTel );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param id 
	 *   void 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月3日 下午8:19:31
	 */ 
    public void deleteFeeTracfficeHis( Integer id );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param id 
	 *   void 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月3日 下午8:21:40
	 */ 
    public void deleFeeTelHis( Integer id );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param costApplyitems
	 * @return 
	 *   List<FeeTraffic> 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月3日 下午8:30:39
	 */ 
    public List< FeeTraffic > findAllFeeTraffic( List< CostApplyitem > costApplyitems );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param costApplyitems
	 * @return 
	 *   List<FeeTel> 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月3日 下午8:33:25
	 */ 
    public List< FeeTel > findAllFeeTel( List< CostApplyitem > costApplyitems );
}
