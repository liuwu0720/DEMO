package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.Static;

/** 
 * @Package clt.com.cn.dao 
 * @Description:用一句话描述该文件做什么 
 * @author chengwzh 
 * @date 2015年10月28日 下午5:33:45 
 * @version V1.0 
 */
public interface IStaticDao
{
	public Static get( int id );
	
	public void save( Static entity );
	
	public void update( Static entity );
	
	public void delete( Static entity );
	
	public List< Static > getAllObjectOrder( String hql );
}
