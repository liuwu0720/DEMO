package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.CostApply;
import clt.com.cn.model.entity.CostBudget;

/** 
 * @Package clt.com.cn.dao 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年11月20日 上午9:35:56 
 * @version V1.0 
 */
public interface ICostBudgetDao
{
	public CostBudget get( int id );
	
	public void save( CostBudget entity );
	
	public void update( CostBudget entity );
	
	public void merge( CostBudget entity );
	
	public List< CostBudget > pageQuery( final String hql , final Integer pageSize ,
	        final Integer page , final Object ... p );
	
	public int getCountByHql( final String hql );
	
	public int getpages( int count , int pageSize );
	
	/** 
	 * @Description:根据费用类型及当前月份查询出预算
	 * @param costApply
	 * @param month
	 * @return 
	 *   CostBudget 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月20日 上午10:16:10
	 */
	CostBudget getByCostType( CostApply costApply , int month );
	
	public List getpageDateBySqlQuery( final String sql , final int page ,
	        final int pageSize );
}
