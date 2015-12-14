package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.ITakeFeeCompanyDao;
import clt.com.cn.model.entity.TakeFeeCompany;

/** 
 * @Package clt.com.cn.dao.impl 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年12月2日 上午11:05:18 
 * @version V1.0 
 */
public class TakeFeeCompanyDaoImp extends BaseDao implements ITakeFeeCompanyDao
{

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @return 
	 * @author liuwu
	 * @create_date 2015年12月2日 上午11:14:35
	 */ 
    @SuppressWarnings( "unchecked" )
    public List< TakeFeeCompany > findAll()
    {
	    String hql = "from TakeFeeCompany where nEnable=0 order by id ";
	    return super.getAllObjectOrder( hql );
    }	
	
}
