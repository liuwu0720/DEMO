/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-21 下午3:35:03 
 * @version V1.0 
 */
package com.chnl.entity;

import java.util.List;

import com.chnl.pojo.PrLegfileimport;

/**
 * @Package com.chnl.entity
 * @Description: TODO(封装prLegfileimports)
 * @author liuwu
 * @date 2014-11-21 下午3:35:03
 * @version V1.0
 */
public class PrLegFileImportForm
{	
	private List< PrLegfileimport > prLegfileimports;
	
	public List< PrLegfileimport > getPrLegfileimports()
	{
		return prLegfileimports;
	}
	
	public void setPrLegfileimports( List< PrLegfileimport > prLegfileimports )
	{
		this.prLegfileimports = prLegfileimports;
	}
	
}
