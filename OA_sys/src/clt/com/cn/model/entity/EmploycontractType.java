package clt.com.cn.model.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 合同类型. @author MyEclipse Persistence Tools
 */

public class EmploycontractType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4738345776097043799L;
	private int lineid;
	private String typename;
	public int getLineid() {
		return lineid;
	}
	public void setLineid(int lineid) {
		this.lineid = lineid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	

}