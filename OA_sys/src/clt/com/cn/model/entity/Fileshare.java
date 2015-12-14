package clt.com.cn.model.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Fileshare entity. @author MyEclipse Persistence Tools
 */

public class Fileshare implements java.io.Serializable {

	// Fields

	private int lineid;
	private int fileid;
	private int userid;
	private int sharetype;
	private int deptid;
	private Date sharedate;
	private String memo;
	private int status;

	// Constructors

	/** default constructor */
	public Fileshare() {
	}

	/** minimal constructor */
	public Fileshare(int lineid, int fileid, int userid,
			int sharetype, int deptid, Date sharedate,
			int status) {
		this.lineid = lineid;
		this.fileid = fileid;
		this.userid = userid;
		this.sharetype = sharetype;
		this.deptid = deptid;
		this.sharedate = sharedate;
		this.status = status;
	}

	/** full constructor */
	public Fileshare(int lineid, int fileid, int userid,
			int sharetype, int deptid, Date sharedate,
			String memo, int status) {
		this.lineid = lineid;
		this.fileid = fileid;
		this.userid = userid;
		this.sharetype = sharetype;
		this.deptid = deptid;
		this.sharedate = sharedate;
		this.memo = memo;
		this.status = status;
	}

	// Property accessors

	public int getLineid() {
		return this.lineid;
	}

	public void setLineid(int lineid) {
		this.lineid = lineid;
	}
	
	public int getFileid() {
		return fileid;
	}

	public void setFileid(int fileid) {
		this.fileid = fileid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getSharetype() {
		return this.sharetype;
	}

	public void setSharetype(int sharetype) {
		this.sharetype = sharetype;
	}

	public int getDeptid() {
		return this.deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public Date getSharedate() {
		return this.sharedate;
	}

	public void setSharedate(Date sharedate) {
		this.sharedate = sharedate;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}