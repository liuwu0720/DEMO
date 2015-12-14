/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-9-17 下午2:31:10 
 * @version V1.0 
 */
package com.chnl.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.chnl.base.DbUtil;

/** 
 * @Package com.chnl.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-9-17 下午2:31:10 
 * @version V1.0 
 */
public class TestConnection extends TestCase
{	
	@Test
	public void testGetConn()
	{
		System.out.print( DbUtil.getConnection() );
	}
}
