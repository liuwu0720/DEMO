/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-11-6 下午4:29:15
 * @version V1.0
 */
package com.chnl.service.iml;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnl.dao.PrAlllegcarinfoDAO;
import com.chnl.dao.PrLegfileimportDAO;
import com.chnl.dao.SmcustomerDAO;
import com.chnl.dao.SmstylerateDAO;
import com.chnl.entity.SmUser;
import com.chnl.pojo.LegInfo;
import com.chnl.pojo.PrLegfileimport;
import com.chnl.service.PrLegFileImportService;

/**
 * @Package com.chnl.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-11-6 下午4:29:15
 * @version V1.0
 */
@Service
public class PrLegFileImportServiceImp implements PrLegFileImportService
{
	@Autowired
	private PrLegfileimportDAO prLegfileimportDAO;
	
	@Autowired
	private SmstylerateDAO smstylerateDAO;
	
	@Autowired
	private SmcustomerDAO smcustomerDAO;

	@Autowired
	private PrAlllegcarinfoDAO prAlllegcarinfoDAO;

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author liuwu
	 * @create_date 2014-11-6 下午4:29:26
	 */
	@SuppressWarnings( "unchecked" )
	public List< PrLegfileimport > findAll( SmUser smUser )
	{
		List< PrLegfileimport > prLegfileimports = prLegfileimportDAO
		        .findAllOrderBy( smUser );
		// 查询是否存在已修改的车辆。有提示修改保存了几辆车，没有提示选择车辆
		for ( PrLegfileimport prLegfileimport : prLegfileimports )
		{
			// 设置【选择车辆】按钮上的提示信息
			prAlllegcarinfoDAO.setBtnMessage( prLegfileimport );
			
		}
		return prLegfileimports;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param prLegfileimports
	 * @author liuwu
	 * @create_date 2014-11-18 上午11:39:12
	 */
	public void saveDataAfterCheck( List< PrLegfileimport > prLegfileimports ,
	        SmUser smUser )
	{

		prLegfileimportDAO.cleanBeforeInser( smUser );
		for ( PrLegfileimport legfileimport : prLegfileimports )
		{
			legfileimport.setUsername( smUser.getUserName() );
			prLegfileimportDAO.save( legfileimport );
		}
		
	}
	
	/**
	 * @Description: TODO(计算线路的空载距离)
	 * @param prLegfileimports
	 * @author liuwu
	 * @create_date 2014-11-18 下午2:37:29
	 */
	@SuppressWarnings( "unchecked" )
	public List< PrLegfileimport > calulateEmptlyDistance(
	        List< PrLegfileimport > prLegfileimports )
	{
		Hashtable< Double , List< PrLegfileimport > > legMap = new Hashtable< Double , List< PrLegfileimport > >();
		// 第一步：先进行环线，单线归类
		for ( PrLegfileimport prLegfileimport : prLegfileimports )
		{
			if ( legMap.containsKey( prLegfileimport.getLoopflag() ) )
			{
				legMap.get( prLegfileimport.getLoopflag() ).add(
				        prLegfileimport );
				legMap.put( prLegfileimport.getLoopflag() ,
				        legMap.get( prLegfileimport.getLoopflag() ) );
			}
			else
			{
				List< PrLegfileimport > prList = new ArrayList< PrLegfileimport >();
				prList.add( prLegfileimport );
				legMap.put( prLegfileimport.getLoopflag() , prList );
			}
		}
		// 第二步：分别处理单线、环线的数据
		Iterator< Double > keys = legMap.keySet().iterator();
		while ( keys.hasNext() )
		{
			Double key = keys.next();
			List< PrLegfileimport > prlegs = legMap.get( key );
			if ( prlegs.size() > 1 )// 如果是环线
			{
				List< PrLegfileimport > loopLegs = legMap.get( key );
				for ( PrLegfileimport prLegfileimport : loopLegs )
				{
					if ( prLegfileimport.getEmptlyFlag() == 0 // 第一种情况：0：有效；3：用户待定
					        || prLegfileimport.getEmptlyFlag() == 3 )
					{
						List< Double > carIdList = smstylerateDAO
						        .findByPointName(
						                prLegfileimport.getStartcity() ,
						                prLegfileimport.getEndcity() , null );
						if ( carIdList.size() == 0 )
						{
							prLegfileimport
							        .setMessage( "该条线路查不到商品车请确认是否当作加入空载?默认视为无效线路！" );
							prLegfileimport.setEmptlyFlag( 3 );// 状态3表示待定，需要用户确认
						}
						else
						{
							
							setEmptlyDistance( loopLegs );// 验证空载距离是否存在
							setActualCostDistance( loopLegs );// 验证实际支付公里数是否存在
							setAroundDistance( loopLegs );// 验证绕城公里数
						}
					}
					else if ( prLegfileimport.getEmptlyFlag() == 1 )// 第二种情况
					                                                // ：1-环线中某条线路完全空载
					{
						getLoopEmptlyDistance( prLegfileimport , loopLegs );
					}
					else if ( prLegfileimport.getEmptlyFlag() == 2 )// 第3种情况：2环线中某条线路空载无效
					{
						if ( prLegfileimport.getMessage() == null
						        || prLegfileimport.getMessage() == "" )
						{
							prLegfileimport.setMessage( "当前线路无效不能空载，查询不到空载距离" );
							
						}
						else
						{
							prLegfileimport.setMessage( prLegfileimport
							        .getMessage() );
						}

					}
				}
				// setEmptlyDistance( prlegs );

			}
			else
			// 如果是单线，先查询是否有商品车，没有商品车再让用户确认是否当作空载再计算空载距离
			{
				List< PrLegfileimport > singleLegs = legMap.get( key );
				for ( PrLegfileimport prLegfileimport : singleLegs )
				{
					if ( prLegfileimport.getEmptlyFlag() == 0
					        || prLegfileimport.getEmptlyFlag() == 3 )
					{ // 默认值0：有效 3：用户待定情况
						List< Double > carIdList = smstylerateDAO
						        .findByPointName(
						                prLegfileimport.getStartcity() ,
						                prLegfileimport.getEndcity() , "" );
						if ( carIdList.size() == 0 )
						{
							prLegfileimport
							        .setMessage( "此条单线查询不到商品车也查询不到空载距离，无法计算！视为无效" );
							prLegfileimport.setEmptlyFlag( 2 );// 状态3表示待定，需要用户确认
						}
						else
						{
							// 有商品车的情况下计算是否存在空载距离
							prLegfileimport.setEmptlyFlag( 0 );// 有效空载(这条单线有运输商品车的情况下查找目的地离最近的提车库之间的距离)
							getEmptlyDistanceForSingle2( prLegfileimport );
							prLegfileimportDAO
							        .getActualCostDistance( prLegfileimport );
							prLegfileimportDAO
							        .getAroundDistance( prLegfileimport );
						}
					}
					/*else if ( prLegfileimport.getEmptlyFlag() == 1 )// 状态1表示全程空载
					{ // 单线空载有效：算出空载距离
						getEmptlyDistanceForSingle( prLegfileimport ,

						        prLegfileimport.getEndcity() );
					}*/
					else if ( prLegfileimport.getEmptlyFlag() == 2 )
					{// 2：无效的空载线路
						if ( prLegfileimport.getMessage() == null
						        || prLegfileimport.getMessage() == "" )
						{
							prLegfileimport
							        .setMessage( "此条单线查询不到商品车也查询不到空载距离，无法计算！视为无效" );
							
						}
						else
						{
							prLegfileimport.setMessage( prLegfileimport
							        .getMessage() );
						}

					}

				}
			}
			
		}
		// 如果一条环线中的线路无效则整个环线都高为无效
		List< PrLegfileimport > prlist = prLegfileimports;
		for ( PrLegfileimport pr : prlist )
		{
			if ( pr.getEmptlyFlag() == 2 )
			{
				double loopflag = pr.getLoopflag();
				for ( PrLegfileimport prLegfileimport : prLegfileimports )
				{
					if ( prLegfileimport.getLoopflag() == loopflag )
					{
						prLegfileimport.setEmptlyFlag( 2 );
						prLegfileimport.setMessage( pr.getMessage() );
					}
				}
			}
		}
		for ( PrLegfileimport prLegfileimport : prLegfileimports )
		{
			prLegfileimportDAO.attachDirty( prLegfileimport );
		}
		return null;

	}
	
	/**
	 * @Description: TODO(验证绕城公里数是否存在)
	 * @param loopLegs
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-2 下午2:31:39
	 */
	private void setAroundDistance( List< PrLegfileimport > loopLegs )
	{
		for ( PrLegfileimport prLegfileimport : loopLegs )
		{
			if ( ! prLegfileimport.getEmptlyFlag().equals( 2 ) )// 有效载情况下验证
			{
				prLegfileimportDAO.getAroundDistance( prLegfileimport );
			}
		}
		
	}

	/**
	 * @Description: TODO(验证支付公里数的有效性)
	 * @param loopLegs
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-2-12 下午4:53:57
	 */
	private void setActualCostDistance( List< PrLegfileimport > loopLegs )
	{
		for ( PrLegfileimport prLegfileimport : loopLegs )
		{
			if ( ! prLegfileimport.getEmptlyFlag().equals( 2 ) )// 有效情况下验证
			{
				prLegfileimportDAO.getActualCostDistance( prLegfileimport );
			}
		}
		
	}

	/**
	 * @Description: 环线情况下当某条线路为空载时算出空载距离总共两种情况：1、如果这条空载线路不是环线的最后一条:当前线路目的地城市
	 *               与下一条线路出发地提车库的距离 2、如果这条空载线路是环线的最后一条：当前线路目的地城市至第一条线路出发地提车库的距离
	 * @param prLegfileimport
	 * @param loopLegs
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-24 下午1:59:19
	 */
	private void getLoopEmptlyDistance( PrLegfileimport prLegfileimport ,
	        List< PrLegfileimport > loopLegs )
	{
		int index = loopLegs.indexOf( prLegfileimport );
		if ( index == loopLegs.size() - 1 )// 2、如果这条空载线路是环线的最后一条：当前线路目的地城市至第一条线路出发地提车库的距离
		{
			prLegfileimportDAO.getLoopEmptlyDistance1( prLegfileimport ,
			        loopLegs );
		}
		else
		{
			prLegfileimportDAO.getLoopEmptlyDistance2( prLegfileimport ,
			        loopLegs , index );
		}

	}

	/**
	 * @Description: TODO(单线情况1：完全空载情况计算空载距离：起始地城市至目的地城市之间的距离)
	 * @param prLegfileimport
	 * @param startpoint
	 * @param endcity
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-20 上午10:31:27
	 */
	private void getEmptlyDistanceForSingle( PrLegfileimport prLegfileimport ,
	        String endcity )
	{
		prLegfileimportDAO.getEmptlyDistanceForSingle( prLegfileimport ,
		        endcity );
		
	}
	
	/**
	 * @Description: TODO(单线情况2：部分空载情况计算空载距离：查找目的地和目的地最近的提车库之间的距离)
	 * @param prLegfileimport
	 * @param startpoint
	 * @param endcity
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-20 上午10:31:27
	 */
	private void getEmptlyDistanceForSingle2( PrLegfileimport prLegfileimport )
	{
		prLegfileimportDAO.getEmptlyDistanceForSingle2( prLegfileimport );
		
	}

	/**
	 * @Description: TODO(计算空载距离：第一条线路的目的地与下一条线路的提车库之间的距离)
	 * @param prlegs
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-18 下午4:27:29
	 */
	private void setEmptlyDistance( List< PrLegfileimport > prlegs )
	{
		
		for ( int i = 0 ; i <= prlegs.size() - 1 ; i++ )
		{
			if ( i < prlegs.size() - 1 )// 上一条线路的目的地与下一条线路中提车库空载距离
			{
				
				getEmptlyDistance( prlegs.get( i ) , prlegs.get( i )
				        .getEndcity() , prlegs.get( i + 1 ).getStartpoint() );
			}
			else if ( i == prlegs.size() - 1 )// 最后一条线路的目的地与第一条线路的提车库空载距离
			{

				getEmptlyDistance( prlegs.get( prlegs.size() - 1 ) , prlegs
				        .get( prlegs.size() - 1 ).getEndcity() , prlegs.get( 0 )
				        .getStartpoint() );
			}

		}

	}
	
	/**
	 * @Description: TODO(验证是否存在空载距离)，后续增加了新需求验证是否存在实际支付公里数
	 * @param prLegfileimport
	 * @param endcity
	 * @param startpoint
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-18 下午4:47:27
	 */
	private void getEmptlyDistance( PrLegfileimport prLegfileimport ,
	        String endcity , String startpoint )
	{
		Double emptlydistance = prLegfileimportDAO.getEmptlyDistance(
		        prLegfileimport , endcity , startpoint );

	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legId
	 * @return
	 * @author liuwu
	 * @create_date 2014-11-19 下午5:27:30
	 */
	public PrLegfileimport getPrLegByLegId( int legId )
	{
		PrLegfileimport p = new PrLegfileimport();
		p = prLegfileimportDAO.findById( legId );
		return p;
	}
	
	/**
	 * @Description: TODO(设置单线线路的状态)
	 * @param prLegfileimport
	 * @return
	 * @author liuwu
	 * @create_date 2014-11-19 下午5:31:51
	 */
	public void calulateEmptlyDistanceByLegId( PrLegfileimport prLegfileimport )
	{
		prLegfileimportDAO.setEmptlyState( prLegfileimport );
	}
	
	/**
	 * @Description: TODO(删除无效线路)
	 * @param legId
	 * @author liuwu
	 * @create_date 2014-11-20 上午11:43:02
	 */
	public void deleteThisLeg( int legId )
	{
		PrLegfileimport persistentInstance = prLegfileimportDAO
		        .findById( legId );
		prLegfileimportDAO.delete( persistentInstance );
		prLegfileimportDAO.initAll();
	}
	
	/**
	 * @Description: TODO(获取有用的线路:只要空载1和有效的0)
	 * @param legInfos
	 * @author liuwu
	 * @create_date 2014-11-25 下午4:56:30
	 */
	@SuppressWarnings( "unchecked" )
	public void getUseableLegs( List< LegInfo > legInfos , Double emptyCost ,
	        SmUser smUser )
	{
		List< PrLegfileimport > prLegfileimports = prLegfileimportDAO
		        .findAllOrderBy( smUser );
		for ( PrLegfileimport prLegfileimport : prLegfileimports )
		{
			if ( prLegfileimport.getEmptlyFlag().equals( 0 )
			        || prLegfileimport.getEmptlyFlag().equals( 1 ) )
			{
				LegInfo legInfo = new LegInfo();
				legInfo.setId( prLegfileimport.getId() );
				legInfo.setCostByTruck( emptyCost );
				legInfo.setDestination( prLegfileimport.getEndcity() );
				legInfo.setEmptlyFlag( prLegfileimport.getEmptlyFlag() );
				legInfo.setEmptyDistance( prLegfileimport.getEmptlyDistance() );
				legInfo.setIncomeDistance( prLegfileimport.getIncomeDistance() );
				legInfo.setLoopFlag( prLegfileimport.getLoopflag() );
				legInfo.setOrigin( prLegfileimport.getStartcity() );
				legInfo.setStartPoint( prLegfileimport.getStartpoint() );
				legInfo.setVcCustomer( prLegfileimport.getSelectCustomer() );
				legInfo.setCostDistance( prLegfileimport.getCostDistance() );
				legInfo.setAroundDistance( prLegfileimport.getAroundDistance() );
				legInfos.add( legInfo );
			}
		}
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param prLegfileimports
	 * @author liuwu
	 * @create_date 2015-3-20 下午5:18:51
	 */
	public void getIncomeDistance( List< PrLegfileimport > prLegfileimports )
	{
		// TODO Auto-generated method stub
		
	}
	
}
