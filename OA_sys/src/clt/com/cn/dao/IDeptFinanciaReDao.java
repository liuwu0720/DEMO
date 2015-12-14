package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.DeptFinanciaRe;

/** 
 * @Package clt.com.cn.dao 
 * @Description:部门与财务人员关联 
 * @author liuwu
 * @date 2015年11月24日 上午11:32:22 
 * @version V1.0 
 */
public interface IDeptFinanciaReDao
{
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param hql3
	 * @return 
	 *   List<DeptFinanciaRe> 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月24日 上午11:37:00
	 */
	List< DeptFinanciaRe > findByHql( String hql3 );
	
	public DeptFinanciaRe get( int id );
	
	public void save( DeptFinanciaRe entity );
	
	public void update( DeptFinanciaRe entity );
	
	public void merge( DeptFinanciaRe entity );
	
	// 分页查询
	public List pageQuery( final String hql , final Integer pageSize ,
	        final Integer page , final Object ... p );
	
	// 查询
	public List getAllObjectOrder( String hql );
	
	public int getCountByHql( final String hql );
	
	public int getpages( int count , int pageSize );
}
