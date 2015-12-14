package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.CostApply;
import clt.com.cn.model.entity.Costtype;
import clt.com.cn.model.entity.DeptTotalcost;

/** 
 * @Package clt.com.cn.dao 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年11月20日 上午9:41:25 
 * @version V1.0 
 */
public interface IDeptTotalCostDao
{
	public DeptTotalcost get( int id );
	
	public void save( DeptTotalcost entity );
	
	public void update( DeptTotalcost entity );
	
	public void merge( DeptTotalcost entity );
	
	public List< DeptTotalcost > pageQuery( final String hql , final Integer pageSize ,
	        final Integer page , final Object ... p );
	
	public int getCountByHql( final String hql );
	
	public int getpages( int count , int pageSize );
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param costApply
	 * @param month
	 * @return 
	 *   DeptTotalcost 返回值描述
	 * @author liuwu
	 * @param costtype 
	 * @create_date 2015年11月20日 上午10:35:05
	 */ 
    DeptTotalcost getByCostType( CostApply costApply , int month, Costtype costtype );	
}
