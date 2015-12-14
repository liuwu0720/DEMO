package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IContractDao;
import clt.com.cn.model.entity.Contract;

/** 
 * @Package clt.com.cn.dao.impl 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年10月22日 上午10:05:22 
 * @version V1.0 
 */
public class ContractDaoImp extends BaseDao implements IContractDao
{
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param contract 
	 * @author liuwu
	 * @create_date 2015年10月22日 上午10:08:54
	 */
	public void save( Contract contract )
	{
		// TODO Auto-generated method stub
		super.addObject( contract );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param page
	 * @return 
	 * @author liuwu
	 * @create_date 2015年10月22日 上午11:04:59
	 */
	public List< Contract > getAllContracts( int page , int userId )
	{
		String hql = "from Contract c where c.iUser=" + userId
		        + " and c.nEnable=0 order by c.lineid desc ";
		return super.pageQuery( hql , 5 , page );
		// String sql =
		// "select c.lineid,c.vc_contractno,c.vc_contractname,c.vc_partya,c.vc_partyb,c.dt_start,c.dt_end,c.n_state,t.vc_type"
		// +
		// " from contract c,contracttype_b t where c.i_contracttype=t.id  and c.n_enable=0 and c.i_user="
		// + userId;
		// return super.getDateBySqlQuery( sql , pageSize , page );
	}
	
	/**
	 * 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param sql
	 * @param pageSize
	 * @param page
	 * @return 
	 *   List<Contract> 返回值描述
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午2:11:12
	 */
	public List getDataBySqlQuery( String sql , int pageSize , int page )
	{
		return super.getDateBySqlQuery( sql , pageSize , page );
	}
	
	/** 
	 * @Description:删除
	 * @param id 
	 * @author chengwzh
	 * @create_date 2015年10月23日 下午5:08:26
	 */
	public void delete( int id )
	{
		Contract contract = ( Contract ) super.getObjectById( Contract.class , id );
		contract.setnEnable( 1 );// 置为无效
		super.mergeObject( contract );
	}
	
	/**
	 * 
	 * @Description:通过id获取 
	 * @param id
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年10月23日 下午5:08:52
	 */
	public Contract get( int id )
	{
		return ( Contract ) super.getObjectById( Contract.class , id );
	}
	
	/** 
	 * @Description:修改
	 * @param contract 
	 * @author chengwzh
	 * @create_date 2015年10月23日 下午5:10:55
	 */
	public void update( Contract contract )
	{
		super.updateObject( contract );
	}
	
	/**
	 * 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param sql
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午2:31:21
	 */
	public int getCountBySQL( String sql )
	{
		return super.getCountBySql( sql );
	}
	
	/**
	 * 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param count
	 * @param pageSize
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午2:31:25
	 */
	public int getpages( int count , int pageSize )
	{
		return super.getpages( count , pageSize );
	}
}
