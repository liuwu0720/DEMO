/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-14 下午2:21:55 
 * @version V1.0 
 */
package com.chnl.service.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chnl.dao.CombinationDAO;
import com.chnl.pojo.Combination;
import com.chnl.service.CombinationService;

/** 
 * @Package com.chnl.service.iml 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-14 下午2:21:55 
 * @version V1.0 
 */
@Service
public class CombinationServiceImp implements CombinationService
{	
	@Autowired
	private CombinationDAO combinationDAO;

	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param combination 
     * @author liuwu
     * @create_date 2014-8-14 下午2:23:30
     */ 
    public void save( Combination combination )
    {
	    // TODO Auto-generated method stub
    	combinationDAO.save( combination );
	    
    }
	
	
	
	
}
