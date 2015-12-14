/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-9-30 下午2:51:21 
 * @version V1.0 
 */
package com.chnl.test;

import java.util.List;

import org.junit.Test;

import com.chnl.dao.LegInfoDAO;
import com.chnl.pojo.LegInfo;

/** 
 * @Package com.chnl.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-9-30 下午2:51:21 
 * @version V1.0 
 */
public class TestView
{
	
	@Test
	public void test()
	{

		LegInfoDAO legInfoDAO = new LegInfoDAO();
		List< LegInfo > legInfos = legInfoDAO.findAll();
		System.out.println( legInfos.size() );
	}
	
}
