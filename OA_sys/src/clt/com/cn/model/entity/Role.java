package clt.com.cn.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields

	private BigDecimal lineid;
	private String rolename;
	private BigDecimal active;
	private String userno;
	private Date currdate;
	private Set smusers = new HashSet(0);
	private Set roleprivileges = new HashSet(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(BigDecimal lineid, String rolename, BigDecimal active,
			Date currdate) {
		this.lineid = lineid;
		this.rolename = rolename;
		this.active = active;
		this.currdate = currdate;
	}

	/** full constructor */
	public Role(BigDecimal lineid, String rolename, BigDecimal active,
			String userno, Date currdate, Set smusers, Set roleprivileges) {
		this.lineid = lineid;
		this.rolename = rolename;
		this.active = active;
		this.userno = userno;
		this.currdate = currdate;
		this.smusers = smusers;
		this.roleprivileges = roleprivileges;
	}

	// Property accessors

	public BigDecimal getLineid() {
		return this.lineid;
	}

	public void setLineid(BigDecimal lineid) {
		this.lineid = lineid;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public BigDecimal getActive() {
		return this.active;
	}

	public void setActive(BigDecimal active) {
		this.active = active;
	}

	public String getUserno() {
		return this.userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public Date getCurrdate() {
		return this.currdate;
	}

	public void setCurrdate(Date currdate) {
		this.currdate = currdate;
	}

	public Set getSmusers() {
		return this.smusers;
	}

	public void setSmusers(Set smusers) {
		this.smusers = smusers;
	}

	public Set getRoleprivileges() {
		return this.roleprivileges;
	}

	public void setRoleprivileges(Set roleprivileges) {
		this.roleprivileges = roleprivileges;
	}

}