package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.CostApplyitem;

/** 
 * @Package clt.com.cn.dao 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年12月2日 下午4:44:23 
 * @version V1.0 
 */
public interface ICostApplyItemDao
{

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param costApplyitem 
	 *   void 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月2日 下午4:48:04
	 */ 
    void save( CostApplyitem costApplyitem );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param hql
	 * @return 
	 *   List<CostApplyitem> 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月3日 下午2:05:41
	 */ 
    List< CostApplyitem > findByHql( String hql );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param costApplyitem 
	 *   void 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月3日 下午3:45:23
	 */ 
    void saveUpdate( CostApplyitem costApplyitem );	
	
}
