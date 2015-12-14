package clt.com.cn.model.entity;

import java.math.BigDecimal;

/**
 * Educationlevel entity. @author MyEclipse Persistence Tools
 */

public class Educationlevel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3138502868081521878L;
	private int lineid;
	private String levelname;

	// Constructors

	/** default constructor */
	public Educationlevel() {
	}

	/** full constructor */
	public Educationlevel(int lineid, String levelname) {
		this.lineid = lineid;
		this.levelname = levelname;
	}

	// Property accessors

	public int getLineid() {
		return this.lineid;
	}

	public void setLineid(int lineid) {
		this.lineid = lineid;
	}

	public String getLevelname() {
		return this.levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

}