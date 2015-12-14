package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.FeeTraffic;

public interface IFeeTrafficService
{
	public void save( FeeTraffic entity );
	
	public void update( FeeTraffic entity );
	
	public void merge( FeeTraffic entity );
	
	// 查询
	public List< FeeTraffic > getAllObjectOrder( String hql );
}
