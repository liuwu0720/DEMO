/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-22 下午3:41:13 
 * @version V1.0 
 */
package com.chnl.service.iml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnl.dao.PrLegfileimportDAO;
import com.chnl.dao.PrSelfinputlegDAO;
import com.chnl.dao.SmcustomerDAO;
import com.chnl.dao.SmstylerateDAO;
import com.chnl.entity.SmUser;
import com.chnl.pojo.LegInfo;
import com.chnl.pojo.PrSelfinputleg;
import com.chnl.pojo.Smcustomer;
import com.chnl.service.PrSelfImputLegService;

/** 
 * @Package com.chnl.service.iml 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-22 下午3:41:13 
 * @version V1.0 
 */
@Service
public class PrSelfImputLegServiceImp implements PrSelfImputLegService
{
	@Autowired
	private PrLegfileimportDAO prLegfileimportDAO;
	@Autowired
	private PrSelfinputlegDAO prSelfinputlegDAO;
	@Autowired
	private SmstylerateDAO smstylerateDAO;
	@Autowired
	private SmcustomerDAO smcustomerDAO;
	
	/**
	 * @Description: TODO(检查始发地、提车点、目的地有效性)
	 * @param startcity
	 * @param startpoint
	 * @param endtcity
	 * @return
	 * @author liuwu
	 * @create_date 2014-11-22 下午3:58:46
	 */
	public String checkLineValid( String startcity , String startpoint ,
	        String endcity , String smUserName )
	{
		
		String message = prSelfinputlegDAO.checkDataAvailability( startcity ,
		        startpoint ,
		        endcity );
		prSelfinputlegDAO.initAll();
		if ( message == "" || message == null )
		{
			PrSelfinputleg prSelfinputleg = new PrSelfinputleg();
			prSelfinputleg.setStartcity( startcity );
			prSelfinputleg.setEndcity( endcity );
			prSelfinputleg.setStartpoint( startpoint );
			prSelfinputleg.setUsername( smUserName );
			prSelfinputlegDAO.saveOrUpdate( prSelfinputleg );
		}
		return message;
	}
	
	/**
	 * @Description: 
	 *               (对每一条保存后数据进行分析：1、检查上一条线路目的地与下一条线路的提车库之间是否存在空载距离；2、验证线路是否存在即是否运输过商品车
	 *               ) void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-22 下午4:12:47
	 */
	public void checkLegInfoAfterData( List< PrSelfinputleg > prSelfinputlegs )
	{
		for ( PrSelfinputleg prSelfinputleg : prSelfinputlegs )
		{
			if ( prSelfinputleg.getEmptlyflag() == 0
			        || prSelfinputleg.getEmptlyflag() == 3 )
			{// 第一种情况：0：有效；3：用户待定
				List< Double > carIdList = smstylerateDAO.findByPointName(
				        prSelfinputleg.getStartcity() ,
				        prSelfinputleg.getEndcity() , null );
				if ( carIdList.size() == 0 )
				{
					prSelfinputleg
					        .setMessage( "该条线路查不到商品车请确认是否当作加入空载?默认视为无效线路！" );
					prSelfinputleg.setEmptlyflag( 3 );
				}
				else if ( prSelfinputlegs.size() == 1 )// 如果只输入了一条线路情况下
				{
					prSelfinputleg.setMessage( "请选择车辆继续操作！" );
					prSelfinputleg.setEmptlyflag( 0 );
					prSelfinputlegDAO
					        .getEmptlyDistanceForSingle2( prSelfinputleg );
					setActualCostDistance( prSelfinputlegs );// 验证实际支付公里数是否存在
					setAroundDistance( prSelfinputlegs );// 验证绕城公里数
				}
				else
				{
					setEmptlyDistance( prSelfinputlegs );// 计算空载距离
					setActualCostDistance( prSelfinputlegs );// 验证实际支付公里数是否存在
					setAroundDistance( prSelfinputlegs );// 验证绕城公里数
				}

			}else if (prSelfinputleg.getEmptlyflag()==1) {// 第2种情况1-环线中某条线路完全空载
			
				getLoopEmptlyDisance( prSelfinputleg , prSelfinputlegs );
			}
			else if ( prSelfinputleg.getEmptlyflag() == 2 )// 第3种情况：2环线中某条线路空载无效
			{
				if ( prSelfinputleg.getMessage() == null
				        || prSelfinputleg.getMessage() == "" )
				{
					prSelfinputleg.setMessage( "当前线路无效不能空载，查询不到空载距离" );
					
				}
				else
				{
					prSelfinputleg.setMessage( prSelfinputleg.getMessage() );
				}

			}

		}
		// 如果一条环线中的线路无效则整个环线都高为无效
		List< PrSelfinputleg > prlist = prSelfinputlegs;
		for ( PrSelfinputleg pr : prlist )
		{
			if ( pr.getEmptlyflag() == 2 )
			{
				double loopflag = pr.getLoopflag();
				for ( PrSelfinputleg prSelfinputleg : prSelfinputlegs )
				{
					if ( prSelfinputleg.getLoopflag() == loopflag )
					{
						prSelfinputleg.setEmptlyflag( 2 );
						prSelfinputleg.setMessage( pr.getMessage() );
					}
				}
			}
		}
		for ( PrSelfinputleg prSelfinputleg : prSelfinputlegs )
		{
			prSelfinputlegDAO.attachDirty( prSelfinputleg );
		}
		
	}
	
	/**
	 * @Description: TODO(绕城公里数)
	 * @param prSelfinputlegs
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-2 下午2:50:45
	 */
	private void setAroundDistance( List< PrSelfinputleg > prSelfinputlegs )
	{
		for ( PrSelfinputleg prSelfinputleg : prSelfinputlegs )
		{
			if ( ! prSelfinputleg.getEmptlyflag().equals( 2 ) )
			{
				prSelfinputlegDAO.getAroundDistance( prSelfinputleg );
			}
		}
		
	}

	/**
	 * @Description: TODO(查询实际支付公里数)
	 * @param prSelfinputlegs
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-2-13 上午9:51:21
	 */
	private void setActualCostDistance( List< PrSelfinputleg > prSelfinputlegs )
	{
		for ( PrSelfinputleg prSelfinputleg : prSelfinputlegs )
		{
			if ( ! prSelfinputleg.getEmptlyflag().equals( 2 ) )
			{
				prSelfinputlegDAO.getActualCostDistance( prSelfinputleg );
			}
		}
		
	}

	/**
	 * @Description: TODO
	 *               环线情况下当某条线路为空载时算出空载距离总共两种情况：1、如果这条空载线路不是环线的最后一条:当前线路目的地城市至下一条线路出发地提车库的距离
	 *               2、如果这条空载线路是环线的最后一条：当前线路目的地城市至第一条线路出发地提车库的距离
	 * @param prSelfinputleg
	 * @param prSelfinputlegs
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 上午9:58:46
	 */
	private void getLoopEmptlyDisance( PrSelfinputleg prSelfinputleg ,
	        List< PrSelfinputleg > prSelfinputlegs )
	{
		int index = prSelfinputlegs.indexOf( prSelfinputleg );
		if ( prSelfinputlegs.size() == 1 )
		{ // 如果只有一条线路用户把它当作空载的话
			prSelfinputleg.setEmptlyflag( 0 );
			prSelfinputleg.setMessage( "一条线路加入空载不符合要求！请继续添加线路!" );
		}
		else if ( index == prSelfinputlegs.size() - 1 && index > 0 )
		{// 如果这条空载线路是环线的最后一条：当前线路目的地城市至第一条线路出发地提车库的距离
			prSelfinputlegDAO.getloopEmptlyDistance1( prSelfinputleg ,
			        prSelfinputlegs );
		}

		else
		{
			prSelfinputlegDAO.getloopEmptlyDistance2( prSelfinputleg ,
			        prSelfinputlegs , index );
		}
		
	}

	/**
	 * @Description: TODO(计算空载距离：第一条线路的目的地与下一条线路的提车库之间的距离)
	 * @param prSelfinputlegs
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 上午9:19:39
	 */
	private void setEmptlyDistance( List< PrSelfinputleg > prSelfinputlegs )
	{
		for ( int i = 0 ; i <= prSelfinputlegs.size() - 1 ; i++ )
		{
			if ( i < prSelfinputlegs.size() - 1 )// 上一条线路的目的地与下一条线路中提车库空载距离
			{
				getEmptlyDistance( prSelfinputlegs.get( i ) , prSelfinputlegs
				        .get( i ).getEndcity() , prSelfinputlegs.get( i + 1 )
				        .getStartpoint() );
			}
			else if ( i == prSelfinputlegs.size() - 1 )// 最后一条线路的目的地与第一条线路的提车库空载距离
			{
				getEmptlyDistance(
				        prSelfinputlegs.get( prSelfinputlegs.size() - 1 ) ,
				        prSelfinputlegs.get( prSelfinputlegs.size() - 1 )
				                .getEndcity() , prSelfinputlegs.get( 0 )
				                .getStartpoint() );
			}
		}
		
	}

	/**
	 * @Description: TODO(计算空载距离)
	 * @param prSelfinputleg
	 * @param endcity
	 * @param startpoint
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-25 上午9:25:23
	 */
	private void getEmptlyDistance( PrSelfinputleg prSelfinputleg ,
	        String endcity , String startpoint )
	{
		// TODO Auto-generated method stub
		prSelfinputlegDAO.getEmptlyDistance( prSelfinputleg , endcity ,
		        startpoint );
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author liuwu
	 * @create_date 2014-11-22 下午4:44:28
	 */
	@SuppressWarnings( "unchecked" )
	public List< PrSelfinputleg > findAll( SmUser smUser )
	{
		List< PrSelfinputleg > prSelfinputlegs = prSelfinputlegDAO
		        .findAll( smUser );
		for ( PrSelfinputleg prSelfinputleg : prSelfinputlegs )
		{
			List< Smcustomer > smcustomers = new ArrayList< Smcustomer >();
			smcustomerDAO.getCustomerByPointName(
			        prSelfinputleg.getStartpoint() , smcustomers );
			prSelfinputleg.setSmcustomers( smcustomers );
		}
		return prSelfinputlegs;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param legId
	 * @return
	 * @author liuwu
	 * @create_date 2014-11-25 上午10:21:08
	 */
	public PrSelfinputleg getPrLegByLegId( int legId )
	{
		PrSelfinputleg p = new PrSelfinputleg();
		p = prSelfinputlegDAO.findById( legId );
		return p;
	}
	
	/**
	 * @Description: TODO(计算是否存在空载距离)
	 * @param prLegfileimport
	 * @author liuwu
	 * @create_date 2014-11-25 上午10:26:27
	 */
	public void calulateEmptlyDistanceByLegId( PrSelfinputleg prSelfinputleg )
	{
		prSelfinputlegDAO.setEmptlyState( prSelfinputleg );
		
	}
	
	/**
	 * @Description: TODO(用户自定义线路：用户删除此条空载线路)
	 * @param legId
	 * @author liuwu
	 * @create_date 2014-11-25 上午11:37:02
	 */
	public void deleteThisLeg( int legId )
	{
		PrSelfinputleg persistentInstance = prSelfinputlegDAO.findById( legId );
		prSelfinputlegDAO.delete( persistentInstance );
		prSelfinputlegDAO.initAll();
	}
	
	/**
	 * @Description: TODO(算出线路的应收公里)
	 * @param prSelfinputlegs
	 * @author liuwu
	 * @create_date 2014-11-25 下午1:42:12
	 */
	public void getIncomeDistance( List< PrSelfinputleg > prSelfinputlegs )
	{
		for ( PrSelfinputleg prSelfinputleg : prSelfinputlegs )
		{
			prSelfinputlegDAO.getIncomeDistance( prSelfinputleg );
		}
		
	}
	
	/**
	 * @Description: TODO(获取有用的线路:只要空载1和有效的0
	 * @param legInfos
	 * @author liuwu
	 * @create_date 2014-11-25 下午5:17:06
	 */
	@SuppressWarnings( "unchecked" )
	public void getUseableLegs( List< LegInfo > legInfos , Double emptyCost ,
	        SmUser smUser )
	{
		List< PrSelfinputleg > prSelfinputlegs = prSelfinputlegDAO
		        .findAll( smUser );
		for ( PrSelfinputleg prSelfinputleg : prSelfinputlegs )
		{
			if ( prSelfinputleg.getEmptlyflag().equals( 0 )
			        || prSelfinputleg.getEmptlyflag().equals( 1 ) )
			{
				LegInfo legInfo = new LegInfo();
				legInfo.setId( prSelfinputleg.getId() );
				legInfo.setCostByTruck( emptyCost );
				legInfo.setDestination( prSelfinputleg.getEndcity() );
				legInfo.setEmptlyFlag( prSelfinputleg.getEmptlyflag() );
				legInfo.setEmptyDistance( prSelfinputleg.getEmptlyDistance() );
				legInfo.setIncomeDistance( prSelfinputleg.getIncomeDistance() );
				legInfo.setLoopFlag( prSelfinputleg.getLoopflag() );
				legInfo.setOrigin( prSelfinputleg.getStartcity() );
				legInfo.setStartPoint( prSelfinputleg.getStartpoint() );
				legInfo.setVcCustomer( prSelfinputleg.getSelectCustomer() );
				legInfo.setCostDistance( prSelfinputleg.getCostDistance() );
				legInfo.setAroundDistance( prSelfinputleg.getAroundDistance() );
				legInfos.add( legInfo );
			}
		}
		
	}
	
	/**
	 * @Description: TODO(删除该用户所有输入的线路)
	 * @param smuser
	 * @author liuwu
	 * @create_date 2014-12-15 上午11:52:33
	 */
	public void deleteallLegByUser( String smuser )
	{
		prSelfinputlegDAO.deleteallLegByUser( smuser );
		
	}
	
}
