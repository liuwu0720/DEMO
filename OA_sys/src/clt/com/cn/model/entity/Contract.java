package clt.com.cn.model.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Contract entity. @author MyEclipse Persistence Tools
 */

public class Contract implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 3619357926000248989L;
	private Integer lineid;
	private String vcContractname;// 合同名称
	private String vcPartya;// 甲方
	private String vcPartyb;// 乙方
	private Integer iContracttype;// 合同类型
	private Date dtStart;// 生效日期
	private Date dtEnd;// 失效日期
	private Integer nNew;// 是否新签(0：新签，1：续签)
	private Integer nEmergency;// 是否紧急(0:正常；1：紧急)
	private String vcContent;// 合同内容
	private Date dtCreate;// 创建日期
	private Integer nLong;// 是否长期合同(0:是，1：不是)
	private Integer nMainsign;// 是否与主机厂签订(0:是，1：不是)
	private Double nOutprice;// 合同价
	private Double nStandprice;// 标准价
	private String vcContractno;// 合同编号
	private Integer nState;// 合同状态(0:初始录入，1：审核中，2：审核通过，3审核失败)
	private Double nTotalmoney;// 总收入
	private Double nTotalprice;// 总成本
	private Double nTotalnum;// 总运量
	private Double nRate;// 毛利率
	private Double nPrice;// 单件单价
	private Double nYearmoney;// 年度金额
	private Integer nExpect;// 是否预期内(0：预期内,1：预期外)
	private String vcFilepaths;// 上传文件路径（,隔开拼接的路径）
	private Integer iUser;// 对应用户id
	private Integer nEnable;// 是否有效
	private String vcTaskId;// 不与数据库关联
	private String vcTaskName;// 不与数据库关联
	private String vcAudit;
	
	
	public Integer getiUser()
	{
		return iUser;
	}
	
	public void setiUser( Integer iUser )
	{
		this.iUser = iUser;
	}
	
	public Integer getnEnable()
	{
		return nEnable;
	}
	
	public void setnEnable( Integer nEnable )
	{
		this.nEnable = nEnable;
	}
	
	public String getVcFilepaths()
	{
		return vcFilepaths;
	}
	
	public void setVcFilepaths( String vcFilepaths )
	{
		this.vcFilepaths = vcFilepaths;
	}
	
	public Integer getLineid()
	{
		return lineid;
	}
	
	public void setLineid( Integer lineid )
	{
		this.lineid = lineid;
	}
	
	public String getVcContractname()
	{
		return vcContractname;
	}
	
	public void setVcContractname( String vcContractname )
	{
		this.vcContractname = vcContractname;
	}
	
	public String getVcPartya()
	{
		return vcPartya;
	}
	
	public void setVcPartya( String vcPartya )
	{
		this.vcPartya = vcPartya;
	}
	
	public String getVcPartyb()
	{
		return vcPartyb;
	}
	
	public void setVcPartyb( String vcPartyb )
	{
		this.vcPartyb = vcPartyb;
	}
	
	public Integer getiContracttype()
	{
		return iContracttype;
	}
	
	public void setiContracttype( Integer iContracttype )
	{
		this.iContracttype = iContracttype;
	}
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	public Date getDtStart()
	{
		return dtStart;
	}
	
	public void setDtStart( Date dtStart )
	{
		this.dtStart = dtStart;
	}
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	public Date getDtEnd()
	{
		return dtEnd;
	}
	
	public void setDtEnd( Date dtEnd )
	{
		this.dtEnd = dtEnd;
	}
	
	public Integer getnNew()
	{
		return nNew;
	}
	
	public void setnNew( Integer nNew )
	{
		this.nNew = nNew;
	}
	
	public Integer getnEmergency()
	{
		return nEmergency;
	}
	
	public void setnEmergency( Integer nEmergency )
	{
		this.nEmergency = nEmergency;
	}
	
	public String getVcContent()
	{
		return vcContent;
	}
	
	public void setVcContent( String vcContent )
	{
		this.vcContent = vcContent;
	}
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	public Date getDtCreate()
	{
		return dtCreate;
	}
	
	public void setDtCreate( Date dtCreate )
	{
		this.dtCreate = dtCreate;
	}
	
	public Integer getnLong()
	{
		return nLong;
	}
	
	public void setnLong( Integer nLong )
	{
		this.nLong = nLong;
	}
	
	public Integer getnMainsign()
	{
		return nMainsign;
	}
	
	public void setnMainsign( Integer nMainsign )
	{
		this.nMainsign = nMainsign;
	}
	
	public Double getnOutprice()
	{
		return nOutprice;
	}
	
	public void setnOutprice( Double nOutprice )
	{
		this.nOutprice = nOutprice;
	}
	
	public String getVcContractno()
	{
		return vcContractno;
	}
	
	public Double getnStandprice()
	{
		return nStandprice;
	}
	
	public void setnStandprice( Double nStandprice )
	{
		this.nStandprice = nStandprice;
	}
	
	public void setVcContractno( String vcContractno )
	{
		this.vcContractno = vcContractno;
	}
	
	public Integer getnState()
	{
		return nState;
	}
	
	public void setnState( Integer nState )
	{
		this.nState = nState;
	}
	
	public Double getnTotalmoney()
	{
		return nTotalmoney;
	}
	
	public void setnTotalmoney( Double nTotalmoney )
	{
		this.nTotalmoney = nTotalmoney;
	}
	
	public Double getnTotalprice()
	{
		return nTotalprice;
	}
	
	public void setnTotalprice( Double nTotalprice )
	{
		this.nTotalprice = nTotalprice;
	}
	
	public Double getnTotalnum()
	{
		return nTotalnum;
	}
	
	public void setnTotalnum( Double nTotalnum )
	{
		this.nTotalnum = nTotalnum;
	}
	
	public Double getnRate()
	{
		return nRate;
	}
	
	public void setnRate( Double nRate )
	{
		this.nRate = nRate;
	}
	
	public Double getnPrice()
	{
		return nPrice;
	}
	
	public void setnPrice( Double nPrice )
	{
		this.nPrice = nPrice;
	}
	
	public Double getnYearmoney()
	{
		return nYearmoney;
	}
	
	public void setnYearmoney( Double nYearmoney )
	{
		this.nYearmoney = nYearmoney;
	}
	
	public Integer getnExpect()
	{
		return nExpect;
	}
	
	public void setnExpect( Integer nExpect )
	{
		this.nExpect = nExpect;
	}
	
	/**
	 * @return the vcTaskId
	 */
	public String getVcTaskId()
	{
		return vcTaskId;
	}
	
	/**
	 * @param vcTaskId the vcTaskId to set
	 */
	public void setVcTaskId( String vcTaskId )
	{
		this.vcTaskId = vcTaskId;
	}
	
	/**
	 * @return the vcTaskName
	 */
	public String getVcTaskName()
	{
		return vcTaskName;
	}
	
	/**
	 * @param vcTaskName the vcTaskName to set
	 */
	public void setVcTaskName( String vcTaskName )
	{
		this.vcTaskName = vcTaskName;
	}

	/**
	 * @return the vcAudit
	 */
	public String getVcAudit()
	{
		return vcAudit;
	}

	/**
	 * @param vcAudit the vcAudit to set
	 */
	public void setVcAudit( String vcAudit )
	{
		this.vcAudit = vcAudit;
	}

	
	
	
	
}