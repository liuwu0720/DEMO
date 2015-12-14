package clt.com.cn.model.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Roleprivilege entity. @author MyEclipse Persistence Tools
 */

public class Roleprivilege implements java.io.Serializable {

	// Fields

	private BigDecimal lineid;
	private Privilege privilege;
	private Role role;
	private String userno;
	private Date currdate;

	// Constructors

	/** default constructor */
	public Roleprivilege() {
	}

	/** minimal constructor */
	public Roleprivilege(BigDecimal lineid, Privilege privilege, Role role,
			Date currdate) {
		this.lineid = lineid;
		this.privilege = privilege;
		this.role = role;
		this.currdate = currdate;
	}

	/** full constructor */
	public Roleprivilege(BigDecimal lineid, Privilege privilege, Role role,
			String userno, Date currdate) {
		this.lineid = lineid;
		this.privilege = privilege;
		this.role = role;
		this.userno = userno;
		this.currdate = currdate;
	}

	// Property accessors

	public BigDecimal getLineid() {
		return this.lineid;
	}

	public void setLineid(BigDecimal lineid) {
		this.lineid = lineid;
	}

	public Privilege getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
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

}