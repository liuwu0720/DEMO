package clt.com.cn.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Dept entity. @author MyEclipse Persistence Tools
 */

public class EmployAddressList implements java.io.Serializable {

	// Fields

	/**
	 *  name		varchar2(100) null,    --名称
		positionName	varchar2(100) null,    --职位名称
		companyName	varchar2(100) null,    --公司名称
		address		varchar2(200) null,    --地址
		mobile		varchar2(20) null,    --手机号码
		tel		varchar2(20) null,    --固定电话
		email		varchar2(100) null,    --邮箱
		opuser		int	null,		--创建者
		optime		date	null,		--创建时间
		status		int	null,		--状态 是否可用
	 */
	private static final long serialVersionUID = -8943696505366937931L;
	private int lineid;
	private String name;
	private String positionName;
	private String companyName;
	private String address;
	private String mobile;
	private String tel;
	private String remarks;
	private String email;
	private int status;
	private Date optime;
	private Smuser opuser;
	private Dept dept;
	

	// Constructors

	/** default constructor */
	public EmployAddressList() {
	}


	public int getLineid() {
		return lineid;
	}


	public void setLineid(int lineid) {
		this.lineid = lineid;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getName() {
		return name;
	}


	public Dept getDept() {
		return dept;
	}


	public void setDept(Dept dept) {
		this.dept = dept;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPositionName() {
		return positionName;
	}


	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public Date getOptime() {
		return optime;
	}


	public void setOptime(Date optime) {
		this.optime = optime;
	}


	public Smuser getOpuser() {
		return opuser;
	}


	public void setOpuser(Smuser opuser) {
		this.opuser = opuser;
	}

	

}