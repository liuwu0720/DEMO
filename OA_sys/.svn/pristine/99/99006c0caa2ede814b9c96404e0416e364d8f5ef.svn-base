package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.Contract;

/** 
 * @Package clt.com.cn.dao 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年10月22日 上午10:04:58 
 * @version V1.0 
 */
public interface IContractDao
{
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param contract 
	 *   void 返回值描述
	 * @author liuwu
	 * @create_date 2015年10月22日 上午10:08:47
	 */
	void save( Contract contract );
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param page
	 * @return 
	 *   List 返回值描述
	 * @author liuwu
	 * @create_date 2015年10月22日 上午11:04:52
	 */
	List< Contract > getAllContracts( int page , int userId );
	
	public List getDataBySqlQuery( String sql , int pageSize , int page );
	
	/**
	 * 通过id删除
	 * @param id
	 */
	void delete( int id );
	
	/**
	 * 通过id查询类
	 * @param id
	 * @return
	 */
	Contract get( int id );
	
	void update( Contract contract );
	
	public int getCountBySQL( String sql );
	
	public int getpages( int count , int pageSize );
}
