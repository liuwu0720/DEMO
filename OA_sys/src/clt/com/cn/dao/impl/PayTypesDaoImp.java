package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IPayTypesDao;
import clt.com.cn.model.entity.PayTypes;

/** 
 * @Package clt.com.cn.dao.impl 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年12月9日 下午4:11:23 
 * @version V1.0 
 */
public class PayTypesDaoImp  extends BaseDao implements IPayTypesDao
{

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param hql
	 * @return 
	 * @author liuwu
	 * @create_date 2015年12月9日 下午4:28:55
	 */ 
    public List< PayTypes > findByHql( String hql )
    {
	    // TODO Auto-generated method stub
	    return super.getAllObjectOrder( hql );
    }	
	
}
