package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IFeeTrafficDao;
import clt.com.cn.model.entity.FeeTraffic;

public class FeeTrafficDaoImp extends BaseDao implements IFeeTrafficDao
{
	
	public void save( FeeTraffic entity )
	{
		super.addObject( entity );
	}
	
	public void update( FeeTraffic entity )
	{
		super.updateObject( entity );
	}
	
	public void merge( FeeTraffic entity )
	{
		super.mergeObject( entity );
	}
	
	// 查询
	public List< FeeTraffic > getAllObjectOrder( String hql )
	{
		return ( List< FeeTraffic > ) super.getAllObjectOrder( hql );
	}

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param id 
	 * @author liuwu
	 * @create_date 2015年12月3日 下午8:20:46
	 */ 
    public void deleteById( int id )
    {
	    // TODO Auto-generated method stub
	    super.deleteObjectById( FeeTraffic.class , id );
    }

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param getiTableid
	 * @return 
	 * @author liuwu
	 * @create_date 2015年12月3日 下午8:32:42
	 */ 
    public FeeTraffic getById( Integer id )
    {
	    // TODO Auto-generated method stub
	    return ( FeeTraffic ) super.getObjectById( FeeTraffic.class , id );
    }
}
