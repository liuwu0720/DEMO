package clt.com.cn.model.entity;


/**
 * FeeReLevel entity. @author MyEclipse Persistence Tools
 */

public class FeeReLevel implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 8380864426829090357L;
	private Integer lineid;
	private Integer iCosttype;//费用类别
	private String vcCostname;//费用类别名称
	private Integer nCondition;//条件标识
	private String vcCondition;//条件说明
	private String vcLevel;//部门等级
	private Integer nEnable;//0:有效 1无效
	private Integer iCompanytype;//公司类别 1：总公司 2-分公司 3-车队
	private String vcLevel2;//财务等级
	
	
	/**
	 * 
	 */
    public FeeReLevel()
    {
	    super();
	    // TODO Auto-generated constructor stub
    }
	/**
	 * @return the lineid
	 */
	public Integer getLineid()
	{
		return lineid;
	}
	/**
	 * @param lineid the lineid to set
	 */
	public void setLineid( Integer lineid )
	{
		this.lineid = lineid;
	}
	/**
	 * @return the iCosttype
	 */
	public Integer getiCosttype()
	{
		return iCosttype;
	}
	/**
	 * @param iCosttype the iCosttype to set
	 */
	public void setiCosttype( Integer iCosttype )
	{
		this.iCosttype = iCosttype;
	}
	/**
	 * @return the vcCostname
	 */
	public String getVcCostname()
	{
		return vcCostname;
	}
	/**
	 * @param vcCostname the vcCostname to set
	 */
	public void setVcCostname( String vcCostname )
	{
		this.vcCostname = vcCostname;
	}
	/**
	 * @return the nCondition
	 */
	public Integer getnCondition()
	{
		return nCondition;
	}
	/**
	 * @param nCondition the nCondition to set
	 */
	public void setnCondition( Integer nCondition )
	{
		this.nCondition = nCondition;
	}
	/**
	 * @return the vcCondition
	 */
	public String getVcCondition()
	{
		return vcCondition;
	}
	/**
	 * @param vcCondition the vcCondition to set
	 */
	public void setVcCondition( String vcCondition )
	{
		this.vcCondition = vcCondition;
	}
	
	/**
	 * @return the vcLevel
	 */
	public String getVcLevel()
	{
		return vcLevel;
	}
	/**
	 * @param vcLevel the vcLevel to set
	 */
	public void setVcLevel( String vcLevel )
	{
		this.vcLevel = vcLevel;
	}
	/**
	 * @return the nEnable
	 */
	public Integer getnEnable()
	{
		return nEnable;
	}
	/**
	 * @param nEnable the nEnable to set
	 */
	public void setnEnable( Integer nEnable )
	{
		this.nEnable = nEnable;
	}
	/**
	 * @return the iCompanytype
	 */
	public Integer getiCompanytype()
	{
		return iCompanytype;
	}
	/**
	 * @param iCompanytype the iCompanytype to set
	 */
	public void setiCompanytype( Integer iCompanytype )
	{
		this.iCompanytype = iCompanytype;
	}
	/**
	 * @return the vcLevel2
	 */
	public String getVcLevel2()
	{
		return vcLevel2;
	}
	/**
	 * @param vcLevel2 the vcLevel2 to set
	 */
	public void setVcLevel2( String vcLevel2 )
	{
		this.vcLevel2 = vcLevel2;
	}
	
	
}