package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IStaticDao;
import clt.com.cn.model.entity.Static;
import clt.com.cn.service.IStaticService;

/** 
 * @Package clt.com.cn.service.impl 
 * @Description:用一句话描述该文件做什么 
 * @author chengwzh 
 * @date 2015年10月28日 下午5:57:01 
 * @version V1.0 
 */
public class StaticServiceImp implements IStaticService
{
	private IStaticDao staticDao;
	
	public void setiStaticDao( IStaticDao iStaticDao )
	{
		this.staticDao = iStaticDao;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param id
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午5:57:21
	 */
	public Static get( int id )
	{
		return null;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param entity 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午5:57:21
	 */
	public void save( Static entity )
	{	
		
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param entity 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午5:57:21
	 */
	public void update( Static entity )
	{	
		
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param entity 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午5:57:21
	 */
	public void delete( Static entity )
	{	
		
	}
	
	/** 
	 * @Description:通过模块名称获取文件上传常量对象
	 * @param vcModule
	 * @return 
	 * @author chengwzh
	 * @create_date 2015年10月28日 下午6:11:08
	 */
	public Static getByname( String vcModule )
	{
		String hql = "from Static s where s.vcModule='" + vcModule + "'";
		List< Static > list = staticDao.getAllObjectOrder( hql );
		if ( list.size() > 0 )
		{
			return list.get( 0 );
		}
		return null;
	}
	
}
