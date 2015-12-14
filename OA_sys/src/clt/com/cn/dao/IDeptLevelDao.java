package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.DeptLevel;

public interface IDeptLevelDao
{
	public void save( DeptLevel entity );
	
	public void update( DeptLevel entity );
	
	public void merge( DeptLevel entity );
	
	public DeptLevel get( int id );
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param lineid
	 * @return 
	 *   List<DeptLevel> 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月19日 上午11:25:36
	 */
	public List< DeptLevel > getByDept( int lineid );
	
	public List< DeptLevel > pageQuery( final String hql , final Integer pageSize ,
	        final Integer page , final Object ... p );
	
	public int getCountByHql( final String hql );
	
	public int getpages( int count , int pageSize );
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param hql2
	 * @return 
	 *   List<DeptLevel> 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月24日 上午10:11:58
	 */
	public List< DeptLevel > findByHql( String hql2 );
}
