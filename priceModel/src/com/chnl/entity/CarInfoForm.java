/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-11-20 下午2:16:10 
 * @version V1.0 
 */
package com.chnl.entity;

import java.util.List;

import com.chnl.pojo.CarInfo;

/**
 * @Package com.chnl.entity
 * @Description: TODO(封装CarInfo集合)
 * @author liuwu
 * @date 2014-11-20 下午2:16:10
 * @version V1.0
 */
public class CarInfoForm
{	
	List< CarInfo > carInfos;
	
	public List< CarInfo > getCarInfos()
	{
		return carInfos;
	}
	
	public void setCarInfos( List< CarInfo > carInfos )
	{
		this.carInfos = carInfos;
	}
	
	
}
