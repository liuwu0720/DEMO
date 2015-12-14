package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.Costtype;
import clt.com.cn.model.entity.PayTypes;
import clt.com.cn.model.entity.TrafficTools;

public interface ICosttypeService
{
	public List< Costtype > getTypesById( int id );
	
	public Costtype get( int id );
	
	public List< Costtype > getAllObjectOrder( String hql );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @return 
	 *   List<TrafficTools> 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月9日 下午4:21:09
	 */ 
    public List< TrafficTools > getAllTrafficTools();

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @return 
	 *   List<PayTypes> 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月9日 下午4:27:59
	 */ 
    public List< PayTypes > getAllPayTypes();
}
