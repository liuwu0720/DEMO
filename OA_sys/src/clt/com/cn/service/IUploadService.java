package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.FilesMapping;
import clt.com.cn.model.entity.Fileshare;
import clt.com.cn.model.entity.Smfile;
import clt.com.cn.model.entity.Smuser;

public interface IUploadService
{
	// 根据Uname查询相关信息
	public abstract List< Smuser > getUserByName( String name );
	
	// 根据Uname查询员工档案相关信息
	public abstract List< Employrecord > getEmrByName( String name );
	
	// 根据ID查询员工档案信息
	public abstract Employrecord getEmrById( int id );
	
	public Smfile getSmFileById( int id );
	
	public abstract void addFile( Smfile sm );
	
	public abstract List< Smfile > getAllFile( int page );
	
	public abstract List< Smfile > getSelfFile( int fileid , int page );
	
	public abstract void delSmfileById( int id );
	
	public abstract void delSharefileById( int id );
	
	public abstract int getCount();
	
	public abstract int getCountByFileid( int fileid );
	
	public abstract int getCountShareFile( int fileid );
	
	public abstract int getCountDownShareFile( int deptid , int userid );
	
	public abstract int getpages( int count , int pageSize );
	
	// 将自己的文件共享
	public abstract void shareFile( Fileshare fs );
	
	public abstract List getUserInfo();
	
	// 共享管理
	public abstract List getShareFile( int fileid , int page );
	
	// 得到单个文件信息
	public abstract Fileshare getShareFileById( int lineid );
	
	// 修改启用禁用信息
	public abstract void updateShareFile( Fileshare fs );
	
	public abstract List getUplodShareFile( int page , int deptid , int userid );
	
	public void saveFileMapping( FilesMapping sm );
}
