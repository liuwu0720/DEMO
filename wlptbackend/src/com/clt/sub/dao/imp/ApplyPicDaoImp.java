/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-8 下午4:39:13 
 * @version V1.0 
 */
package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IApplyPicDao;
import com.clt.sub.model.TApplyPic;

/** 
 * @Package com.clt.sub.dao.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-8 下午4:39:13 
 * @version V1.0 
 */
@Repository
public class ApplyPicDaoImp extends
        GenericHibernateDao< TApplyPic , Integer > implements IApplyPicDao
{	
	
}
