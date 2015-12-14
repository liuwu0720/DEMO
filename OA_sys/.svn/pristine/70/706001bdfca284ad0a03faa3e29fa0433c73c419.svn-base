package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IFeeTelDao;
import clt.com.cn.model.entity.FeeTel;

public class FeeTelDaoImp extends BaseDao implements IFeeTelDao
{
	
	public void save( FeeTel entity )
	{
		super.addObject( entity );
	}
	
	public void update( FeeTel entity )
	{
		super.updateObject( entity );
	}
	
	public void merge( FeeTel entity )
	{
		super.mergeObject( entity );
	}
	
	// 查询
	public List< FeeTel > getAllObjectOrder( String hql )
	{
		return ( List< FeeTel > ) super.getAllObjectOrder( hql );
	}

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param telid 
	 * @author liuwu
	 * @create_date 2015年12月3日 下午8:22:33
	 */ 
    public void deleteById( int telid )
    {
	    // TODO Auto-generated method stub
	    super.deleteObjectById( FeeTel.class , telid );
    }

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param getiTableid
	 * @return 
	 * @author liuwu
	 * @create_date 2015年12月3日 下午8:35:53
	 */ 
    public FeeTel getById( Integer id )
    {
	    // TODO Auto-generated method stub
	    return ( FeeTel ) super.getObjectById( FeeTel.class , id );
    }
    
    
}
