package clt.com.cn.service.impl;

import java.util.List;

import clt.com.cn.dao.IUploadDao;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.FilesMapping;
import clt.com.cn.model.entity.Fileshare;
import clt.com.cn.model.entity.Smfile;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IUploadService;

public class UploadServiceImp implements IUploadService
{
	private IUploadDao uploadDao;
	
	public void setUploadDao( IUploadDao uploadDao )
	{
		this.uploadDao = uploadDao;
	}
	
	public List< Employrecord > getEmrByName( String name )
	{
		// TODO Auto-generated method stub
		return uploadDao.getEmrByName( name );
	}
	
	public List< Smuser > getUserByName( String name )
	{
		// TODO Auto-generated method stub
		return uploadDao.getUserByName( name );
	}
	
	public Employrecord getEmrById( int id )
	{
		return uploadDao.getEmrById( id );
	}
	
	public void addFile( Smfile sm )
	{
		uploadDao.addFile( sm );
	}
	
	public List< Smfile > getAllFile( int page )
	{
		return uploadDao.getAllFile( page );
	}
	
	public List< Smfile > getSelfFile( int fileid , int page )
	{
		return uploadDao.getSelfile( fileid , page );
	}
	
	public void delSmfileById( int id )
	{
		
		uploadDao.delFile( id );
		// uploadDao.delSmfileById(id);
	}
	
	public void delSharefileById( int id )
	{
		uploadDao.delSharefileById( id );
	}
	
	// ������
	public int getCount()
	{
		return uploadDao.getCount();
	}
	
	public int getCountByFileid( int fileid )
	{
		return uploadDao.getCountByFileid( fileid );
	}
	
	public int getCountDownShareFile( int deptid , int userid )
	{
		return uploadDao.getCountDownShareFile( deptid , userid );
	}
	
	public int getpages( int count , int pageSize )
	{
		return uploadDao.getpages( count , pageSize );
	}
	
	public void shareFile( Fileshare fs )
	{
		uploadDao.shareFile( fs );
	}
	
	public List getUserInfo()
	{
		// TODO Auto-generated method stub
		List ulist = uploadDao.getUserInfo();
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	
	public List getShareFile( int fileid , int page )
	{
		List ulist = uploadDao.getShareFile( fileid , page );
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	
	public Fileshare getShareFileById( int lineid )
	{
		return uploadDao.getShareFileById( lineid );
	}
	
	public void updateShareFile( Fileshare fs )
	{
		uploadDao.updateShareFile( fs );
	}
	
	public int getCountShareFile( int fileid )
	{
		return uploadDao.getCountShareFile( fileid );
	}
	
	public List getUplodShareFile( int page , int deptid , int userid )
	{
		List ulist = uploadDao.getUplodShareFile( page , deptid , userid );
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-31 上午11:28:59
	 */
	public Smfile getSmFileById( int id )
	{
		// TODO Auto-generated method stub
		return uploadDao.getSmFileById( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sm
	 * @author chenbin
	 * @create_date 2014-8-27 下午6:28:04
	 */
	public void saveFileMapping( FilesMapping sm )
	{
		// TODO Auto-generated method stub
		uploadDao.saveFileMapping( sm );
	}
}
