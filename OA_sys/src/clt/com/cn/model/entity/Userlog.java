package clt.com.cn.model.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Userlog entity. @author MyEclipse Persistence Tools
 */

public class Userlog implements java.io.Serializable {

	// Fields

	private BigDecimal lineid;
	private Smuser smuser;
	private Privilege privilege;
	private Date currdate;
	private String memo;
	private String ip;

	// Constructors

	/** default constructor */
	public Userlog() {
	}

	/** minimal constructor */
	public Userlog(BigDecimal lineid, Smuser smuser, Privilege privilege,
			Date currdate) {
		this.lineid = lineid;
		this.smuser = smuser;
		this.privilege = privilege;
		this.currdate = currdate;
	}

	/** full constructor */
	public Userlog(BigDecimal lineid, Smuser smuser, Privilege privilege,
			Date currdate, String memo, String ip) {
		this.lineid = lineid;
		this.smuser = smuser;
		this.privilege = privilege;
		this.currdate = currdate;
		this.memo = memo;
		this.ip = ip;
	}

	// Property accessors

	public BigDecimal getLineid() {
		return this.lineid;
	}

	public void setLineid(BigDecimal lineid) {
		this.lineid = lineid;
	}

	public Smuser getSmuser() {
		return this.smuser;
	}

	public void setSmuser(Smuser smuser) {
		this.smuser = smuser;
	}

	public Privilege getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public Date getCurrdate() {
		return this.currdate;
	}

	public void setCurrdate(Date currdate) {
		this.currdate = currdate;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}