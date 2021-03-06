package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.Contract;

/** 
 * @Package clt.com.cn.service 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年10月21日 下午6:13:23 
 * @version V1.0 
 */
public interface IContractManageService
{
	public int getpages( int count , int pageSize );
	
	public int getCountBySQL( String sql );
	
	public List getDataBySqlQuery( String sql , int pageSize , int page );
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param page
	 * @return 
	 *   List 返回值描述
	 * @author liuwu
	 * @create_date 2015年10月21日 下午6:18:06
	 */
	public List< Contract > getAllContracts( int page , int userId );
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @return 
	 *   int 返回值描述
	 * @author liuwu
	 * @create_date 2015年10月21日 下午6:18:55
	 */
	public int getCount();
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param contract 
	 *   void 返回值描述
	 * @author liuwu
	 * @create_date 2015年10月22日 上午10:02:35
	 */
	public void save( Contract contract );
	
	/**
	 * 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param id 
	 *   void 返回值描述
	 * @author chengwzh
	 * @create_date 2015年10月23日 下午5:12:02
	 */
	public void delete( int id );
	
	/**
	 * 
	 * @Description:修改
	 * @param contract 
	 *   void 返回值描述
	 * @author chengwzh
	 * @create_date 2015年10月23日 下午5:12:38
	 */
	public void update( Contract contract );
	
	/**
	 * 
	 * @Description:通过id获取
	 * @param id
	 * @return 
	 *   Contract 返回值描述
	 * @author chengwzh
	 * @create_date 2015年10月23日 下午5:24:14
	 */
	public Contract get( int id );
}
