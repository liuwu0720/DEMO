package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IEmployrecordCheckDao;
import clt.com.cn.model.entity.EmployrecordCheck;

/**
 * @Package clt.com.cn.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenbin
 * @date 2014-7-22 下午4:13:20
 * @version V1.0
 */
public class EmployrecordCheckDao extends BaseDao implements IEmployrecordCheckDao
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-22 下午4:16:09
	 */
	public List< EmployrecordCheck > getAllEmployrecordCheck()
	{
		// TODO Auto-generated method stub
		return super.getAllObject( EmployrecordCheck.class );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param obj
	 * @author chenbin
	 * @create_date 2014-7-22 下午4:16:09
	 */
	public void addEmployrecordCheck( EmployrecordCheck obj )
	{
		// TODO Auto-generated method stub
		super.addObject( obj );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @author chenbin
	 * @create_date 2014-7-22 下午4:16:09
	 */
	public void delEmployrecordCheckById( int id )
	{
		// TODO Auto-generated method stub
		super.deleteObjectById( EmployrecordCheck.class , id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-22 下午4:16:09
	 */
	public EmployrecordCheck getEmployrecordCheckById( int id )
	{
		// TODO Auto-generated method stub
		return ( EmployrecordCheck ) super.getObjectById( EmployrecordCheck.class , id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param obj
	 * @author chenbin
	 * @create_date 2014-7-22 下午4:16:09
	 */
	public void updateEmployrecordCheck( EmployrecordCheck obj )
	{
		// TODO Auto-generated method stub
		super.updateObject( obj );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @param p
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-22 下午4:16:09
	 */
	public List< EmployrecordCheck > getUserInfo( String hql , Object ... p )
	{
		// TODO Auto-generated method stub
		return super.getUsersByCondition( hql , p );
	}
	
}
