package clt.com.cn.dao.impl;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.ITravelFeeDao;
import clt.com.cn.model.entity.Travelfee;

/** 
 * @Package clt.com.cn.dao.impl 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年12月2日 上午11:07:55 
 * @version V1.0 
 */
public class TravelFeeDaoImp extends BaseDao implements ITravelFeeDao
{

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param travelfee 
	 * @author liuwu
	 * @create_date 2015年12月2日 下午4:40:49
	 */ 
    public void save( Travelfee travelfee )
    {
	    // TODO Auto-generated method stub
	    super.addObject( travelfee );
    }

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param getiTableid
	 * @return 
	 * @author liuwu
	 * @create_date 2015年12月3日 下午2:17:40
	 */ 
    public Travelfee findById( Integer id )
    {
	    // TODO Auto-generated method stub
	    return ( Travelfee ) super.getObjectById(Travelfee.class , id );
    }

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param travefeeId 
	 * @author liuwu
	 * @create_date 2015年12月3日 下午4:37:15
	 */ 
    public void deleteById( int travefeeId )
    {
	    // TODO Auto-generated method stub
	    super.deleteObjectById( Travelfee.class , travefeeId );
    }	
	
}
