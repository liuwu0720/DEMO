package clt.com.cn.model.entity;

import java.util.Date;
/**
 * Dept entity. @author MyEclipse Persistence Tools
 */

public class Employaccredit implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3115677162621805329L;
	
	private int lineid;
	private String accreditcause;
	private Date currdate;
	private Date accreditBeginTime;
	private Date accreditEndTime;
	private Smuser certigierUser;    //授权人
	private Smuser authorizerUser;   //被授权者

	// Constructors

	/** default constructor */
	public Employaccredit() {
	}

	public int getLineid() {
		return lineid;
	}

	public void setLineid(int lineid) {
		this.lineid = lineid;
	}

	public String getAccreditcause() {
		return accreditcause;
	}

	public void setAccreditcause(String accreditcause) {
		this.accreditcause = accreditcause;
	}

	public Date getCurrdate() {
		return currdate;
	}

	public void setCurrdate(Date currdate) {
		this.currdate = currdate;
	}

	public Smuser getCertigierUser() {
		return certigierUser;
	}

	public void setCertigierUser(Smuser certigierUser) {
		this.certigierUser = certigierUser;
	}

	public Smuser getAuthorizerUser() {
		return authorizerUser;
	}

	public void setAuthorizerUser(Smuser authorizerUser) {
		this.authorizerUser = authorizerUser;
	}

	public Date getAccreditBeginTime() {
		return accreditBeginTime;
	}

	public void setAccreditBeginTime(Date accreditBeginTime) {
		this.accreditBeginTime = accreditBeginTime;
	}

	public Date getAccreditEndTime() {
		return accreditEndTime;
	}

	public void setAccreditEndTime(Date accreditEndTime) {
		this.accreditEndTime = accreditEndTime;
	}

	


}