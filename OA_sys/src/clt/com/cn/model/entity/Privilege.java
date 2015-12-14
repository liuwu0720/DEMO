package clt.com.cn.model.entity;


import java.util.HashSet;
import java.util.Set;

/**
 * Privilege entity. @author MyEclipse Persistence Tools
 */

public class Privilege implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1566489879301595943L;
	// Fields

	private int lineid;
	private int pid;
	private int type;
	private String privilegeno;
	private String privilegename;
	private String title;
	private String urlpath;
	private String description;
	public int getLineid() {
		return lineid;
	}


	public void setLineid(int lineid) {
		this.lineid = lineid;
	}


	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getPrivilegeno() {
		return privilegeno;
	}


	public void setPrivilegeno(String privilegeno) {
		this.privilegeno = privilegeno;
	}


	public String getPrivilegename() {
		return privilegename;
	}


	public void setPrivilegename(String privilegename) {
		this.privilegename = privilegename;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getUrlpath() {
		return urlpath;
	}


	public void setUrlpath(String urlpath) {
		this.urlpath = urlpath;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	// Constructors

	/** default constructor */
	public Privilege() {
	}


}