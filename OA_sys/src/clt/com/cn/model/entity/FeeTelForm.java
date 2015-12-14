package clt.com.cn.model.entity;

import java.util.List;

/** 
 * @Package clt.com.cn.model.entity 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年12月3日 下午7:19:04 
 * @version V1.0 
 */
public class FeeTelForm
{	
	private List< FeeTel > feeTels;

	/**
	 * @return the feeTels
	 */
	public List< FeeTel > getFeeTels()
	{
		return feeTels;
	}

	/**
	 * @param feeTels the feeTels to set
	 */
	public void setFeeTels( List< FeeTel > feeTels )
	{
		this.feeTels = feeTels;
	}
	
}
