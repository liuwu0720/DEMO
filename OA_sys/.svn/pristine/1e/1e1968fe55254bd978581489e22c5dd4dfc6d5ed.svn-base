package clt.com.cn.model.entity;

import java.util.Date;
import java.util.Set;

/**
 * Smfile entity. @author MyEclipse Persistence Tools
 */

public class Smfile implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = 8206130329489418991L;
	private int lineid;
	private int userid;
	private int fileid;
	private String filename;
	private String filepath;
	private Date currdate;
	
	// Constructors
	
	/** default constructor */
	public Smfile()
	{}
	
	/** minimal constructor */
	public Smfile( int lineid , int userid , int fileid , String filename ,
	        String filepath , Date currdate )
	{
		this.lineid = lineid;
		this.userid = userid;
		this.fileid = fileid;
		this.filename = filename;
		this.filepath = filepath;
		this.currdate = currdate;
	}
	
	/** full constructor */
	public Smfile( int lineid , int userid , int fileid , Smuser smuser ,
	        String filename , String filepath , Date currdate , Set fileshares )
	{
		this.lineid = lineid;
		this.userid = userid;
		this.fileid = fileid;
		this.filename = filename;
		this.filepath = filepath;
		this.currdate = currdate;
	}
	
	// Property accessors
	
	public int getLineid()
	{
		return this.lineid;
	}
	
	public void setLineid( int lineid )
	{
		this.lineid = lineid;
	}
	
	public int getUserid()
	{
		return userid;
	}
	
	public void setUserid( int userid )
	{
		this.userid = userid;
	}
	
	public int getFileid()
	{
		return fileid;
	}
	
	public void setFileid( int fileid )
	{
		this.fileid = fileid;
	}
	
	public String getFilename()
	{
		return this.filename;
	}
	
	public void setFilename( String filename )
	{
		this.filename = filename;
	}
	
	public String getFilepath()
	{
		return this.filepath;
	}
	
	public void setFilepath( String filepath )
	{
		this.filepath = filepath;
	}
	
	public Date getCurrdate()
	{
		return this.currdate;
	}
	
	public void setCurrdate( Date currdate )
	{
		this.currdate = currdate;
	}
	
}