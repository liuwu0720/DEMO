/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-9-24 上午11:03:16 
 * @version V1.0 
 */
package com.chnl.test;

/** 
 * @Package com.chnl.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-9-24 上午11:03:16 
 * @version V1.0 
 */
public class Person implements Comparable< Person >
{
	private String name;
	private Integer order;
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @param name
	 *            the name to set
	 */
	public void setName( String name )
	{
		this.name = name;
	}
	
	/**
	 * @return the order
	 */
	public Integer getOrder()
	{
		return order;
	}
	
	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder( Integer order )
	{
		this.order = order;
	}
	
	public int compareTo( Person arg0 )
	{
		return this.getOrder().compareTo( arg0.getOrder() );
	}

}
