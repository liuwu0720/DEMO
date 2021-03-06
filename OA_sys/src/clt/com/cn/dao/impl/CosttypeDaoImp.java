package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.ICosttypeDao;
import clt.com.cn.model.entity.Costtype;

public class CosttypeDaoImp extends BaseDao implements ICosttypeDao
{
	public List< Costtype > getAllObjectOrder( String hql )
	{
		return super.getAllObjectOrder( hql );
	}
	
	public Costtype get( int id )
	{
		return ( Costtype ) super.getObjectById( Costtype.class , id );
	}
	
}
