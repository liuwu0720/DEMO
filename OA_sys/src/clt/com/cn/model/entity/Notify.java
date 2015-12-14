package clt.com.cn.model.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Notify entity. @author MyEclipse Persistence Tools
 */

public class Notify implements java.io.Serializable {

	// Fields

	private int lineid;
	private int deptid;
	private int userid;
	private int type;
	private String notifyno;
	private Date notifydate;
	private String title;
	private String content;
	private Date currdate;


	// Constructors

	/** default constructor */
	public Notify() {
	}

	/** minimal constructor */
	public Notify(int lineid, int deptid, int userid, int type,
			Date notifydate, String content, Date currdate) {
		this.lineid = lineid;
		this.deptid = deptid;
		this.userid = userid;
		this.type = type;
		this.notifydate = notifydate;
		this.content = content;
		this.currdate = currdate;
	}

	/** full constructor */
	public Notify(int lineid, int deptid, int userid, int type,
			String notifyno, Date notifydate, String title, String content,
			Date currdate) {
		this.lineid = lineid;
		this.deptid = deptid;
		this.userid = userid;
		this.type = type;
		this.notifyno = notifyno;
		this.notifydate = notifydate;
		this.title = title;
		this.content = content;
		this.currdate = currdate;

	}

	// Property accessors

	public int getLineid() {
		return this.lineid;
	}

	public void setLineid(int lineid) {
		this.lineid = lineid;
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getNotifyno() {
		return this.notifyno;
	}

	public void setNotifyno(String notifyno) {
		this.notifyno = notifyno;
	}

	public Date getNotifydate() {
		return this.notifydate;
	}

	public void setNotifydate(Date notifydate) {
		this.notifydate = notifydate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCurrdate() {
		return this.currdate;
	}

	public void setCurrdate(Date currdate) {
		this.currdate = currdate;
	}
	
}