package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IStaticDao;
import clt.com.cn.model.entity.Static;

/** 
 * @Package clt.com.cn.dao.impl 
 * @Description:用一句话描述该文件做什么 
 * @author chengwzh 
 * @date 2015年10月28日 下午5:36:57 
 * @version V1.0 
 */
public class StaticDaoImp extends BaseDao implements IStaticDao
{
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param id
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午5:45:22
	 */
	public Static get( int id )
	{
		return ( Static ) super.getObjectById( Static.class , id );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param entity 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午5:45:22
	 */
	public void save( Static entity )
	{
		super.addObject( entity );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param entity 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午5:45:22
	 */
	public void update( Static entity )
	{
		super.updateObject( entity );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param entity 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午5:45:22
	 */
	public void delete( Static entity )
	{
		super.deleteObject( entity );
	}
	
	public List< Static > getAllObjectOrder( String hql )
	{
		return super.getAllObjectOrder( hql );
	}
}
