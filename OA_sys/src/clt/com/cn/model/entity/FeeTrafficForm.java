package clt.com.cn.model.entity;

import java.util.List;

/** 
 * @Package clt.com.cn.model.entity 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年12月3日 下午7:18:07 
 * @version V1.0 
 */
public class FeeTrafficForm
{	
	private List< FeeTraffic > feeTraffics;

	/**
	 * @return the feeTraffics
	 */
	public List< FeeTraffic > getFeeTraffics()
	{
		return feeTraffics;
	}

	/**
	 * @param feeTraffics the feeTraffics to set
	 */
	public void setFeeTraffics( List< FeeTraffic > feeTraffics )
	{
		this.feeTraffics = feeTraffics;
	}
	
	
}
