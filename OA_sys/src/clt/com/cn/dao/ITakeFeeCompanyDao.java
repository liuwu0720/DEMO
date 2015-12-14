package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.TakeFeeCompany;

/** 
 * @Package clt.com.cn.dao 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年12月2日 上午11:04:41 
 * @version V1.0 
 */
public interface ITakeFeeCompanyDao
{

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @return 
	 *   List<TakeFeeCompany> 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月2日 上午11:14:29
	 */ 
    List< TakeFeeCompany > findAll();	
	
}
