package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IUserTokenDao;
import clt.com.cn.model.entity.SmuserToken;

/** 
 * @Package clt.com.cn.dao.impl 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年11月10日 下午2:49:18 
 * @version V1.0 
 */
public class UserTokenDaoImp extends BaseDao implements IUserTokenDao
{
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param smuserToken 
	 * @author liuwu
	 * @create_date 2015年11月10日 下午2:49:49
	 */
	public void addUserToken( SmuserToken smuserToken )
	{
		// TODO Auto-generated method stub
		super.addObject( smuserToken );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param token
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月10日 下午3:16:15
	 */
	public SmuserToken findByToken( String token )
	{
		String hql = "from SmuserToken where vcToken = '" + token + "'";
		List< SmuserToken > smList = getAllObjectOrder( hql );
		if ( smList != null && smList.size() > 0 )
		{
			return smList.get( 0 );
		}
		else
		{
			return null;
		}
		
	}
	
}
