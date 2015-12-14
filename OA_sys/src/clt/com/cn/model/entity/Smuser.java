package clt.com.cn.model.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Smuser entity. @author MyEclipse Persistence Tools
 */

public class Smuser implements java.io.Serializable {

	// Fields

	private int lineid;
	private int roleid;
	private Employrecord employrecord;
	private String userno;
	private String password;
	private int admin;
	private int active;
	private Date lastupdatedate;
	private String cuserno2;
	private Date currdate;
	private Set smfiles = new HashSet(0);
	private Set notifies = new HashSet(0);
	private Set employholidaiesForUserid = new HashSet(0);
	private Set notifyshares = new HashSet(0);
	private Set notifyfiles = new HashSet(0);
	private Set employholidaiesForCheckuserid = new HashSet(0);
	private Set fileshares = new HashSet(0);
	private Set employoutsForUserid = new HashSet(0);
	private Set employoutsForCheckuserid = new HashSet(0);
	private Set userprivileges = new HashSet(0);
	

	// Constructors

	/** default constructor */
	public Smuser() {
	}

	/** minimal constructor */
	public Smuser(int lineid, int roleid, int recordid,
			String userno, int admin, int active,
			Date lastupdatedate, Date currdate) {
		this.lineid = lineid;
		this.roleid = roleid;
		this.userno = userno;
		this.admin = admin;
		this.active = active;
		this.lastupdatedate = lastupdatedate;
		this.currdate = currdate;
	}

	/** full constructor */
	public Smuser(int lineid, int roleid, int recordid,
			String userno, String password, int admin,
			int active, Date lastupdatedate, String cuserno2,
			Date currdate, Set smfiles, Set notifies,
			Set employholidaiesForUserid, Set notifyshares, Set notifyfiles,
			Set employholidaiesForCheckuserid, Set fileshares,
			Set employoutsForUserid, Set employoutsForCheckuserid,
			Set userprivileges, Set userlogs) {
		this.lineid = lineid;
		this.roleid = roleid;
		this.userno = userno;
		this.password = password;
		this.admin = admin;
		this.active = active;
		this.lastupdatedate = lastupdatedate;
		this.cuserno2 = cuserno2;
		this.currdate = currdate;
		this.smfiles = smfiles;
		this.notifies = notifies;
		this.employholidaiesForUserid = employholidaiesForUserid;
		this.notifyshares = notifyshares;
		this.notifyfiles = notifyfiles;
		this.employholidaiesForCheckuserid = employholidaiesForCheckuserid;
		this.fileshares = fileshares;
		this.employoutsForUserid = employoutsForUserid;
		this.employoutsForCheckuserid = employoutsForCheckuserid;
		this.userprivileges = userprivileges;
	
	}

	// Property accessors

	public int getLineid() {
		return this.lineid;
	}

	public void setLineid(int lineid) {
		this.lineid = lineid;
	}



	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}



	public Employrecord getEmployrecord() {
		return employrecord;
	}

	public void setEmployrecord(Employrecord employrecord) {
		this.employrecord = employrecord;
	}

	public String getUserno() {
		return this.userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAdmin() {
		return this.admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Date getLastupdatedate() {
		return this.lastupdatedate;
	}

	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	public String getCuserno2() {
		return this.cuserno2;
	}

	public void setCuserno2(String cuserno2) {
		this.cuserno2 = cuserno2;
	}

	public Date getCurrdate() {
		return this.currdate;
	}

	public void setCurrdate(Date currdate) {
		this.currdate = currdate;
	}

	public Set getSmfiles() {
		return this.smfiles;
	}

	public void setSmfiles(Set smfiles) {
		this.smfiles = smfiles;
	}

	public Set getNotifies() {
		return this.notifies;
	}

	public void setNotifies(Set notifies) {
		this.notifies = notifies;
	}

	public Set getEmployholidaiesForUserid() {
		return this.employholidaiesForUserid;
	}

	public void setEmployholidaiesForUserid(Set employholidaiesForUserid) {
		this.employholidaiesForUserid = employholidaiesForUserid;
	}

	public Set getNotifyshares() {
		return this.notifyshares;
	}

	public void setNotifyshares(Set notifyshares) {
		this.notifyshares = notifyshares;
	}

	public Set getNotifyfiles() {
		return this.notifyfiles;
	}

	public void setNotifyfiles(Set notifyfiles) {
		this.notifyfiles = notifyfiles;
	}

	public Set getEmployholidaiesForCheckuserid() {
		return this.employholidaiesForCheckuserid;
	}

	public void setEmployholidaiesForCheckuserid(
			Set employholidaiesForCheckuserid) {
		this.employholidaiesForCheckuserid = employholidaiesForCheckuserid;
	}

	public Set getFileshares() {
		return this.fileshares;
	}

	public void setFileshares(Set fileshares) {
		this.fileshares = fileshares;
	}

	public Set getEmployoutsForUserid() {
		return this.employoutsForUserid;
	}

	public void setEmployoutsForUserid(Set employoutsForUserid) {
		this.employoutsForUserid = employoutsForUserid;
	}

	public Set getEmployoutsForCheckuserid() {
		return this.employoutsForCheckuserid;
	}

	public void setEmployoutsForCheckuserid(Set employoutsForCheckuserid) {
		this.employoutsForCheckuserid = employoutsForCheckuserid;
	}

	public Set getUserprivileges() {
		return this.userprivileges;
	}

	public void setUserprivileges(Set userprivileges) {
		this.userprivileges = userprivileges;
	}


}