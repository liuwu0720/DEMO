/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-22 下午3:40:57 
 * @version V1.0 
 */
package com.chnl.service;

import java.util.List;

import com.chnl.entity.SmUser;
import com.chnl.pojo.LegInfo;
import com.chnl.pojo.PrSelfinputleg;

/** 
 * @Package com.chnl.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-22 下午3:40:57 
 * @version V1.0 
 */
public interface PrSelfImputLegService
{
	
	/**
	 * @Description: TODO(检查始发地、提车点、目的地有效性)
	 * @param startcity
	 * @param startpoint
	 * @param endtcity
	 * @return String 返回值描述
	 * @author liuwu
	 * @param smUserName
	 * @create_date 2014-11-22 下午3:58:37
	 */
	String checkLineValid( String startcity , String startpoint ,
	        String endtcity , String smUserName );
	
	/**
	 * @Description: 
	 *               (对每一条保存后数据进行分析：1、检查上一条线路目的地与下一条线路的提车库之间是否存在空载距离；2、验证线路是否存在即是否运输过商品车
	 *               ) void 返回值描述
	 * @author liuwu
	 * @param prSelfinputlegs
	 * @create_date 2014-11-22 下午4:12:47
	 */
	void checkLegInfoAfterData( List< PrSelfinputleg > prSelfinputlegs );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return List<PrSelfinputleg> 返回值描述
	 * @author liuwu
	 * @param smUser
	 * @create_date 2014-11-22 下午4:44:19
	 */
	List< PrSelfinputleg > findAll( SmUser smUser );
	
	/**
	 * @Description: TODO(通过ID查询出详细信息)
	 * @param legId
	 * @return PrSelfinputleg 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 上午10:20:30
	 */
	PrSelfinputleg getPrLegByLegId( int legId );
	
	/**
	 * @Description: TODO(计算是否存在空载距离)
	 * @param prLegfileimport
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 上午10:25:53
	 */
	void calulateEmptlyDistanceByLegId( PrSelfinputleg prSelfinputleg );
	
	/**
	 * @Description: TODO(用户自定义线路：用户删除此条空载线路)
	 * @param legId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 上午11:36:50
	 */
	void deleteThisLeg( int legId );
	
	/**
	 * @Description: TODO(算出线路的应收公里)
	 * @param prSelfinputlegs
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 下午1:42:04
	 */
	void getIncomeDistance( List< PrSelfinputleg > prSelfinputlegs );
	
	/**
	 * @Description: TODO(获取有用的线路)
	 * @param legInfos
	 *            void 返回值描述
	 * @author liuwu
	 * @param emptyCost
	 * @param smUser
	 * @create_date 2014-11-25 下午5:16:48
	 */
	void getUseableLegs( List< LegInfo > legInfos , Double emptyCost ,
	        SmUser smUser );
	
	/**
	 * @Description: TODO(删除该用户所有输入的线路)
	 * @param smuser
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-15 上午11:52:09
	 */
	void deleteallLegByUser( String smuser );
	
}
