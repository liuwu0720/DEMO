package clt.com.cn.model.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Travelfee entity. @author MyEclipse Persistence Tools
 */

public class Travelfee implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 5070673825791407874L;
	private Integer id;
	private Date dtStart;
	private Date dtEnd;
	private String vcTool;
	private Integer nToollist;//交通费单据张数
	private Float nTool = null;//交通费金额
	private String vcPlace;
	private Float nDays;
	private Float nSubsidies= null;//出差补贴
	private String vcUser;
	private Float nHotel= null;
	private Float nOther= null;
	private Integer nHotellist;//住宿费单据张数
	private Integer nOtherlist;
	private String vcNote;
	private Float nTotal= null;//总额
	
	// Constructors
	
	/** default constructor */
	public Travelfee()
	{}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId( Integer id )
	{
		this.id = id;
	}

	/**
	 * @return the dtStart
	 */
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	public Date getDtStart()
	{
		return dtStart;
	}

	/**
	 * @param dtStart the dtStart to set
	 */
	public void setDtStart( Date dtStart )
	{
		this.dtStart = dtStart;
	}

	/**
	 * @return the dtEnd
	 */
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	public Date getDtEnd()
	{
		return dtEnd;
	}

	/**
	 * @param dtEnd the dtEnd to set
	 */
	public void setDtEnd( Date dtEnd )
	{
		this.dtEnd = dtEnd;
	}

	/**
	 * @return the vcTool
	 */
	public String getVcTool()
	{
		return vcTool;
	}

	/**
	 * @param vcTool the vcTool to set
	 */
	public void setVcTool( String vcTool )
	{
		this.vcTool = vcTool;
	}

	/**
	 * @return the nToollist
	 */
	public Integer getnToollist()
	{
		return nToollist;
	}

	/**
	 * @param nToollist the nToollist to set
	 */
	public void setnToollist( Integer nToollist )
	{
		this.nToollist = nToollist;
	}

	/**
	 * @return the nTool
	 */
	public Float getnTool()
	{
		return nTool;
	}

	/**
	 * @param nTool the nTool to set
	 */
	public void setnTool( Float nTool )
	{
		this.nTool = nTool;
	}

	/**
	 * @return the vcPlace
	 */
	public String getVcPlace()
	{
		return vcPlace;
	}

	/**
	 * @param vcPlace the vcPlace to set
	 */
	public void setVcPlace( String vcPlace )
	{
		this.vcPlace = vcPlace;
	}

	/**
	 * @return the nDays
	 */
	public Float getnDays()
	{
		return nDays;
	}

	/**
	 * @param nDays the nDays to set
	 */
	public void setnDays( Float nDays )
	{
		this.nDays = nDays;
	}

	/**
	 * @return the nSubsidies
	 */
	public Float getnSubsidies()
	{
		return nSubsidies;
	}

	/**
	 * @param nSubsidies the nSubsidies to set
	 */
	public void setnSubsidies( Float nSubsidies )
	{
		this.nSubsidies = nSubsidies;
	}

	/**
	 * @return the vcUser
	 */
	public String getVcUser()
	{
		return vcUser;
	}

	/**
	 * @param vcUser the vcUser to set
	 */
	public void setVcUser( String vcUser )
	{
		this.vcUser = vcUser;
	}

	/**
	 * @return the nHotel
	 */
	public Float getnHotel()
	{
		return nHotel;
	}

	/**
	 * @param nHotel the nHotel to set
	 */
	public void setnHotel( Float nHotel )
	{
		this.nHotel = nHotel;
	}

	/**
	 * @return the nOther
	 */
	public Float getnOther()
	{
		return nOther;
	}

	/**
	 * @param nOther the nOther to set
	 */
	public void setnOther( Float nOther )
	{
		this.nOther = nOther;
	}

	/**
	 * @return the nHotellist
	 */
	public Integer getnHotellist()
	{
		return nHotellist;
	}

	/**
	 * @param nHotellist the nHotellist to set
	 */
	public void setnHotellist( Integer nHotellist )
	{
		this.nHotellist = nHotellist;
	}

	/**
	 * @return the nOtherlist
	 */
	public Integer getnOtherlist()
	{
		return nOtherlist;
	}

	/**
	 * @param nOtherlist the nOtherlist to set
	 */
	public void setnOtherlist( Integer nOtherlist )
	{
		this.nOtherlist = nOtherlist;
	}

	/**
	 * @return the vcNote
	 */
	public String getVcNote()
	{
		return vcNote;
	}

	/**
	 * @param vcNote the vcNote to set
	 */
	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
	}

	/**
	 * @return the nTotal
	 */
	public Float getnTotal()
	{
		float nTotalCost = this.getnHotel()+this.getnOther()+this.getnTool();
		return nTotalCost;
	}

	/**
	 * @param nTotal the nTotal to set
	 */
	public void setnTotal( Float nTotal )
	{
		this.nTotal = nTotal;
	}
	
	
	
}