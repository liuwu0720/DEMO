/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-8-21 下午1:36:06
 * @version V1.0
 */
package com.chnl.entity;

import com.chnl.pojo.LegInfo;

/**
 * @Package com.chnl.pojo
 * @Description: TODO(输出表：线路价格统计情况)
 * @author liuwu
 * @date 2014-8-21 下午1:36:06
 * @version V1.0
 */
public class LegPriceSummary implements Comparable< LegPriceSummary >,java.io.Serializable
{
	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LegInfo legInfo;
	private Integer combinations;
	private Double prob3;
	private Double priceBytruck;
	private Double prob2;
	
	public Double getProb2()
	{
		return prob2;
	}
	
	public void setProb2( Double prob2 )
	{
		this.prob2 = prob2;
	}
	
	public Double getPriceBytruck()
	{
		return priceBytruck;
	}
	
	public void setPriceBytruck( Double priceBytruck )
	{
		this.priceBytruck = priceBytruck;
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	public LegInfo getLegInfo()
	{
		return legInfo;
	}
	
	public void setLegInfo( LegInfo legInfo )
	{
		this.legInfo = legInfo;
	}
	
	public Integer getCombinations()
	{
		return combinations;
	}
	
	public void setCombinations( Integer combinations )
	{
		this.combinations = combinations;
	}
	
	public Double getProb3()
	{
		return prob3;
	}
	
	public void setProb3( Double prob3 )
	{
		this.prob3 = prob3;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param o
	 * @return
	 * @author liuwu
	 * @create_date 2014-8-27 下午4:05:27
	 */
	public int compareTo( LegPriceSummary o )
	{
		if ( null == o )
		{
			return 1;
		}
		else
		{
			return this.getPriceBytruck().compareTo( o.getPriceBytruck() );
		}
	}
	
	@Override
	public int hashCode()
	{
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public boolean equals( Object obj )
	{
		if ( obj == null )
		{
			return false;
		}
		else
		{
			if(this.getClass() == obj.getClass()){
				LegPriceSummary legPriceSummary = ( LegPriceSummary ) obj;
				if(this.getPriceBytruck().equals( legPriceSummary.getPriceBytruck() )&&this.getCombinations().equals( legPriceSummary.getCombinations() )
					&&this.getProb3().equals( legPriceSummary.getProb3() )	&& this.getLegInfo().getId().equals( legPriceSummary.getLegInfo().getId() )){
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
			
		}
	}
	
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
