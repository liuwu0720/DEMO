/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-21 下午1:43:31 
 * @version V1.0 
 */
package com.chnl.entity;

import java.util.List;

import com.chnl.pojo.LegInfo;

/** 
 * @Package com.chnl.pojo 
 * @Description: TODO(一条ROUTE可以有多条leg) 
 * @author liuwu 
 * @date 2014-8-21 下午1:43:31 
 * @version V1.0 
 */
public class RouteSummary implements Comparable< RouteSummary > ,
        java.io.Serializable
{	
	/**
     * 
     */
	private static final long serialVersionUID = - 109068106535113074L;
	private Double id;
	private Double duration;
	private Double frequency;
	private List< LegInfo > legInfos;
	
	public Double getId()
    {
    	return id;
    }
	
	public void setId( Double key )
    {
		this.id = key;
    }
	
	public Double getDuration()
    {
    	return duration;
    }
	
	public void setDuration( Double duration )
    {
    	this.duration = duration;
    }
	
	public Double getFrequency()
    {
    	return frequency;
    }
	
	public void setFrequency( Double frequency )
    {
    	this.frequency = frequency;
    }
	public List< LegInfo > getLegInfos()
	{
		return legInfos;
	}
	public void setLegInfos( List< LegInfo > legInfos )
	{
		this.legInfos = legInfos;
	}
	
	/**
	 * @param id
	 * @param duration
	 * @param frequency
	 * @param legInfos
	 */
	public RouteSummary( Double id , Double duration , Double frequency ,
	        List< LegInfo > legInfos )
	{
		super();
		this.id = id;
		this.duration = duration;
		this.frequency = frequency;
		this.legInfos = legInfos;
	}
	
	/**
     * 
     */
	public RouteSummary()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param o
	 * @return
	 * @author liuwu
	 * @create_date 2014-12-10 下午4:53:49
	 */
	public int compareTo( RouteSummary o )
	{
		if ( null == o )
		{
			return 1;
		}
		else
		{
			return this.getId().compareTo( o.getId() );
		}
	}
	
	
	

}
