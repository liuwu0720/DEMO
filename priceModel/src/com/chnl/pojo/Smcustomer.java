package com.chnl.pojo;

import java.util.Date;

/**
 * Smcustomer entity. @author MyEclipse Persistence Tools
 */

public class Smcustomer implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = 4687076104797422438L;
	private Integer ilineid;
	private String vccustomerno;
	private String vccustomername;
	private String vccontact;
	private String vctel;
	private String vcaddress;
	private String vcprovince;
	private String vccityname;
	private Double icompanyid;
	private String vccompanyname;
	private Integer bactive;
	private String vcuserno;
	private Date dtdate;
	private Integer iflag;
	private Integer itype;
	private String vcaddress2;
	private Integer bcheck;
	
	// Constructors
	
	/** default constructor */
	public Smcustomer()
	{}
	
	/**
	 * @param ilineid
	 * @param vccustomerno
	 * @param vccustomername
	 * @param vccontact
	 * @param vctel
	 * @param vcaddress
	 * @param vcprovince
	 * @param vccityname
	 * @param icompanyid
	 * @param vccompanyname
	 * @param bactive
	 * @param vcuserno
	 * @param dtdate
	 * @param iflag
	 * @param itype
	 * @param vcaddress2
	 * @param bcheck
	 * @param sozlships
	 */
	public Smcustomer( Integer ilineid , String vccustomerno ,
	        String vccustomername , String vccontact , String vctel ,
	        String vcaddress , String vcprovince , String vccityname ,
	        Double icompanyid , String vccompanyname , Integer bactive ,
	        String vcuserno , Date dtdate , Integer iflag , Integer itype ,
	        String vcaddress2 , Integer bcheck )
	{
		super();
		this.ilineid = ilineid;
		this.vccustomerno = vccustomerno;
		this.vccustomername = vccustomername;
		this.vccontact = vccontact;
		this.vctel = vctel;
		this.vcaddress = vcaddress;
		this.vcprovince = vcprovince;
		this.vccityname = vccityname;
		this.icompanyid = icompanyid;
		this.vccompanyname = vccompanyname;
		this.bactive = bactive;
		this.vcuserno = vcuserno;
		this.dtdate = dtdate;
		this.iflag = iflag;
		this.itype = itype;
		this.vcaddress2 = vcaddress2;
		this.bcheck = bcheck;

	}
	
	public Integer getIlineid()
	{
		return ilineid;
	}
	
	public void setIlineid( Integer ilineid )
	{
		this.ilineid = ilineid;
	}

	public String getVccustomerno()
	{
		return vccustomerno;
	}

	public void setVccustomerno( String vccustomerno )
	{
		this.vccustomerno = vccustomerno;
	}

	public String getVccustomername()
	{
		return vccustomername;
	}

	public void setVccustomername( String vccustomername )
	{
		this.vccustomername = vccustomername;
	}

	public String getVccontact()
	{
		return vccontact;
	}

	public void setVccontact( String vccontact )
	{
		this.vccontact = vccontact;
	}

	public String getVctel()
	{
		return vctel;
	}

	public void setVctel( String vctel )
	{
		this.vctel = vctel;
	}

	public String getVcaddress()
	{
		return vcaddress;
	}

	public void setVcaddress( String vcaddress )
	{
		this.vcaddress = vcaddress;
	}

	public String getVcprovince()
	{
		return vcprovince;
	}

	public void setVcprovince( String vcprovince )
	{
		this.vcprovince = vcprovince;
	}

	public String getVccityname()
	{
		return vccityname;
	}

	public void setVccityname( String vccityname )
	{
		this.vccityname = vccityname;
	}
	
	public Double getIcompanyid()
	{
		return icompanyid;
	}
	
	public void setIcompanyid( Double icompanyid )
	{
		this.icompanyid = icompanyid;
	}

	public String getVccompanyname()
	{
		return vccompanyname;
	}

	public void setVccompanyname( String vccompanyname )
	{
		this.vccompanyname = vccompanyname;
	}
	
	public Integer getBactive()
	{
		return bactive;
	}
	
	public void setBactive( Integer bactive )
	{
		this.bactive = bactive;
	}

	public String getVcuserno()
	{
		return vcuserno;
	}

	public void setVcuserno( String vcuserno )
	{
		this.vcuserno = vcuserno;
	}
	
	public Date getDtdate()
	{
		return dtdate;
	}
	
	public void setDtdate( Date dtdate )
	{
		this.dtdate = dtdate;
	}

	public Integer getIflag()
	{
		return iflag;
	}

	public void setIflag( Integer iflag )
	{
		this.iflag = iflag;
	}

	public Integer getItype()
	{
		return itype;
	}

	public void setItype( Integer itype )
	{
		this.itype = itype;
	}

	public String getVcaddress2()
	{
		return vcaddress2;
	}

	public void setVcaddress2( String vcaddress2 )
	{
		this.vcaddress2 = vcaddress2;
	}
	
	public Integer getBcheck()
	{
		return bcheck;
	}
	
	public void setBcheck( Integer bcheck )
	{
		this.bcheck = bcheck;
	}
	
}