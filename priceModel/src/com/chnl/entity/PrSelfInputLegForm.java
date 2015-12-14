/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-25 下午1:52:55 
 * @version V1.0 
 */
package com.chnl.entity;

import java.util.List;

import com.chnl.pojo.PrSelfinputleg;

/** 
 * @Package com.chnl.entity 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-25 下午1:52:55 
 * @version V1.0 
 */
public class PrSelfInputLegForm
{	
	private List< PrSelfinputleg > prSelfinputlegs;
	
	public List< PrSelfinputleg > getPrSelfinputlegs()
	{
		return prSelfinputlegs;
	}
	
	public void setPrSelfinputlegs( List< PrSelfinputleg > prSelfinputlegs )
	{
		this.prSelfinputlegs = prSelfinputlegs;
	}
	
}
