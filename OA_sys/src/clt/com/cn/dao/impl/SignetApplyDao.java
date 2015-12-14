package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.ISignetApplyDao;
import clt.com.cn.model.entity.SignetApply;

/**
 * @Package clt.com.cn.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenbin
 * @date 2014-7-28 上午10:40:39
 * @version V1.0
 */
public class SignetApplyDao extends BaseDao implements ISignetApplyDao
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-28 上午10:44:34
	 */
	public List< SignetApply > getAllSignetApply()
	{
		// TODO Auto-generated method stub
		return super.getAllObject( SignetApply.class );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param obj
	 * @author chenbin
	 * @create_date 2014-7-28 上午10:44:34
	 */
	public void addSignetApply( SignetApply obj )
	{
		// TODO Auto-generated method stub
		super.addObject( obj );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @author chenbin
	 * @create_date 2014-7-28 上午10:44:34
	 */
	public void delSignetApplyById( int id )
	{
		// TODO Auto-generated method stub
		super.deleteObjectById( SignetApply.class , id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-28 上午10:44:34
	 */
	public SignetApply getSignetApplyById( int id )
	{
		// TODO Auto-generated method stub
		return ( SignetApply ) super.getObjectById( SignetApply.class , id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param d
	 * @author chenbin
	 * @create_date 2014-7-28 上午10:44:34
	 */
	public void updateSignetApply( SignetApply d )
	{
		// TODO Auto-generated method stub
		super.updateObject( d );
	}
	
}
