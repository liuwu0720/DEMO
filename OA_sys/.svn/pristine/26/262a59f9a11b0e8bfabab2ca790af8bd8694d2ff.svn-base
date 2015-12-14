package clt.com.cn.model.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Module entity. @author MyEclipse Persistence Tools
 */

public class Module implements java.io.Serializable {

	// Fields

	private BigDecimal lineid;
	private String moduleno;
	private String modulename;
	private BigDecimal sort;
	private String linkfile;

	// Constructors

	/** default constructor */
	public Module() {
	}

	/** minimal constructor */
	public Module(BigDecimal lineid, String moduleno, String modulename,
			BigDecimal sort,String linkfile) {
		this.lineid = lineid;
		this.moduleno = moduleno;
		this.modulename = modulename;
		this.sort = sort;
		this.linkfile=linkfile;
	}

	/** full constructor */
	public Module(BigDecimal lineid, String moduleno, String modulename,
			BigDecimal sort, String linkfile,Set privileges) {
		this.lineid = lineid;
		this.moduleno = moduleno;
		this.modulename = modulename;
		this.sort = sort;
		this.linkfile=linkfile;
	}

	// Property accessors

	public BigDecimal getLineid() {
		return this.lineid;
	}

	public void setLineid(BigDecimal lineid) {
		this.lineid = lineid;
	}

	public String getModuleno() {
		return this.moduleno;
	}

	public void setModuleno(String moduleno) {
		this.moduleno = moduleno;
	}

	public String getModulename() {
		return this.modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public BigDecimal getSort() {
		return this.sort;
	}

	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}


	public String getLinkfile() {
		return linkfile;
	}

	public void setLinkfile(String linkfile) {
		this.linkfile = linkfile;
	}
	

}