package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.ICostBudgetDao;
import clt.com.cn.model.entity.CostApply;
import clt.com.cn.model.entity.CostBudget;

/** 
 * @Package clt.com.cn.dao.impl 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年11月20日 上午9:36:37 
 * @version V1.0 
 */
public class CostBudgetDaoImp extends BaseDao implements ICostBudgetDao
{
	
	public void save( CostBudget entity )
	{
		super.addObject( entity );
	}
	
	public void update( CostBudget entity )
	{
		super.updateObject( entity );
	}
	
	public List< CostBudget > pageQuery( String hql , Integer pageSize , Integer page ,
	        Object ... p )
	{
		return ( List< CostBudget > ) super.pageQuery( hql , pageSize , page , p );
	}
	
	public int getCountByHql( String hql )
	{
		return super.getCountByHql( hql );
	}
	
	public int getpages( int count , int pageSize )
	{
		return super.getpages( count , pageSize );
	}
	
	public CostBudget get( int id )
	{
		return ( CostBudget ) super.getObjectById( CostBudget.class , id );
	}
	
	public void merge( CostBudget entity )
	{
		super.mergeObject( entity );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param getiCosttype
	 * @param month
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月20日 上午10:16:44
	 */
	@SuppressWarnings( "unchecked" )
	public CostBudget getByCostType( CostApply costApply , int month )
	{
		String hql = "from CostBudget where iCosttype = " + costApply.getiCosttype()
		        + " and nMonth = " + month + " and iDept = " + costApply.getiDept();
		List< CostBudget > costBudgets = super.getAllObjectOrder( hql );
		if ( costBudgets != null && costBudgets.size() > 0 )
		{
			return costBudgets.get( 0 );
		}
		else
		{
			return null;
		}
		
	}
	
	public List getpageDateBySqlQuery( final String sql , final int page ,
	        final int pageSize )
	{
		return super.getpageDateBySqlQuery( sql , page , pageSize );
	}
}
