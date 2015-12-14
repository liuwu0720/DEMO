package com.chnl.pojo;

/**
 * 商品车参数表 Smstylerate entity. @author MyEclipse Persistence Tools
 */

public class Smstylerate implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = - 6441163287844513381L;
	private Double ilineid;// id
	private Double dcweight;// 重量
	private Double dchigh;// 高度
	private Double dclength;// 长度
	private Double dcwidth;// 宽度
	private Double bactive;// 有效性
	private String vcstylename;// 车名
	
	// Constructors
	
	/** default constructor */
	public Smstylerate()
	{}
	
	/** minimal constructor */
	public Smstylerate( Double ilineid , Double dcweight , Double dchigh ,
	        Double dclength , Double dcwidth , Double bactive )
	{
		this.ilineid = ilineid;
		this.dcweight = dcweight;
		this.dchigh = dchigh;
		this.dclength = dclength;
		this.dcwidth = dcwidth;
		this.bactive = bactive;
	}
	
	/** full constructor */
	public Smstylerate( Double Integer , Double dcweight , Double dchigh ,
	        Double dclength , Double dcwidth , Double bactive ,
	        String vcstylename )
	{
		this.ilineid = ilineid;
		this.dcweight = dcweight;
		this.dchigh = dchigh;
		this.dclength = dclength;
		this.dcwidth = dcwidth;
		this.bactive = bactive;
		this.vcstylename = vcstylename;
	}
	
	// Property accessors
	
	public Double getIlineid()
	{
		return this.ilineid;
	}
	
	public void setIlineid( Double ilineid )
	{
		this.ilineid = ilineid;
	}
	
	public Double getDcweight()
	{
		return this.dcweight;
	}
	
	public void setDcweight( Double dcweight )
	{
		this.dcweight = dcweight;
	}
	
	public Double getDchigh()
	{
		return this.dchigh;
	}
	
	public void setDchigh( Double dchigh )
	{
		this.dchigh = dchigh;
	}
	
	public Double getDclength()
	{
		return this.dclength;
	}
	
	public void setDclength( Double dclength )
	{
		this.dclength = dclength;
	}
	
	public Double getDcwidth()
	{
		return this.dcwidth;
	}
	
	public void setDcwidth( Double dcwidth )
	{
		this.dcwidth = dcwidth;
	}
	
	public Double getBactive()
	{
		return this.bactive;
	}
	
	public void setBactive( Double bactive )
	{
		this.bactive = bactive;
	}
	
	public String getVcstylename()
	{
		return this.vcstylename;
	}
	
	public void setVcstylename( String vcstylename )
	{
		this.vcstylename = vcstylename;
	}
	
}