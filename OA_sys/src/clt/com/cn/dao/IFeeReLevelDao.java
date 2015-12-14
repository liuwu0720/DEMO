package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.FeeReLevel;

/** 
 * @Package clt.com.cn.dao 
 * @Description:费用条件与审批层级关系维护
 * @author liuwu
 * @date 2015年11月19日 下午6:00:22 
 * @version V1.0 
 */
public interface IFeeReLevelDao
{

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param hql1
	 * @return 
	 *   List<FeeReLevel> 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月24日 上午9:33:53
	 */ 
    List< FeeReLevel > findByHql( String hql1 );	
	
}
