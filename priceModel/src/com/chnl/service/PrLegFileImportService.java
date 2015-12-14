/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-6 下午4:27:56 
 * @version V1.0 
 */
package com.chnl.service;

import java.util.List;

import com.chnl.entity.SmUser;
import com.chnl.pojo.LegInfo;
import com.chnl.pojo.PrLegfileimport;

/** 
 * @Package com.chnl.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-6 下午4:27:56 
 * @version V1.0 
 */
public interface PrLegFileImportService
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return List<PrLegfileimport> 返回值描述
	 * @author liuwu
	 * @param smUser
	 * @create_date 2014-11-6 下午4:28:57
	 */
	List< PrLegfileimport > findAll( SmUser smUser );
	
	/**
	 * @Description: TODO(保存检查后的数据)
	 * @param prLegfileimports
	 *            void 返回值描述
	 * @author liuwu
	 * @param smUser
	 * @create_date 2014-11-18 上午11:38:46
	 */
	void saveDataAfterCheck( List< PrLegfileimport > prLegfileimports ,
	        SmUser smUser );
	
	/**
	 * @Description: TODO(计算空载距离)
	 * @param prLegfileimports
	 *            void 返回值描述
	 * @author liuwu
	 * @return
	 * @create_date 2014-11-18 下午2:37:07
	 */
	List< PrLegfileimport > calulateEmptlyDistance(
	        List< PrLegfileimport > prLegfileimports );
	
	/**
	 * @Description: TODO(通过ID查询出详细信息)
	 * @param legId
	 * @return PrLegfileimport 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-19 下午5:26:53
	 */
	PrLegfileimport getPrLegByLegId( int legId );
	
	/**
	 * @Description: TODO(单线路计算空载距离)
	 * @param prLegfileimport
	 * @return Double 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-19 下午5:29:50
	 */
	void calulateEmptlyDistanceByLegId( PrLegfileimport prLegfileimport );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-20 上午11:42:40
	 */
	void deleteThisLeg( int legId );
	
	/**
	 * @Description: TODO(算出线路的应收公里)
	 * @param prLegfileimports
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-24 上午9:25:32
	 */
	void getIncomeDistance( List< PrLegfileimport > prLegfileimports );
	
	/**
	 * @Description: TODO(获取有用的线路)
	 * @param legInfos
	 *            void 返回值描述
	 * @author liuwu
	 * @param emptyCost
	 * @param smUser
	 * @create_date 2014-11-25 下午4:54:32
	 */
	void getUseableLegs( List< LegInfo > legInfos , Double emptyCost ,
	        SmUser smUser );
	
	

}
