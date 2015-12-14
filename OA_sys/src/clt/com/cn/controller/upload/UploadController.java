package clt.com.cn.controller.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.FilesMapping;
import clt.com.cn.model.entity.Fileshare;
import clt.com.cn.model.entity.Smfile;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IUploadService;
import clt.com.cn.service.IUserService;

@Controller
@RequestMapping( "/UploadServlet" )
public class UploadController
{
	@Autowired
	private IUploadService uploadService;
	@Autowired
	private IUserService userService;
	
	@RequestMapping( "upload" )
	public String upload( HttpServletRequest request )
	{
		
		MultipartHttpServletRequest mrequest = ( MultipartHttpServletRequest ) request;
		
		Map< String , MultipartFile > fileMap = mrequest.getFileMap();
		
		Date now = new Date();
		Date date1 , sharedate;
		int flog = 0;
		
		HttpSession session = request.getSession( true );
		String uname = ( String ) session.getAttribute( "uname" );
		int userlineid = ( Integer ) session.getAttribute( "lineid" );
		int fileid = ( Integer ) session.getAttribute( "recordid" );
		int deptid = ( Integer ) session.getAttribute( "deptid" );
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		SimpleDateFormat sdff = new SimpleDateFormat( "yyyyMMddHHmmss" );
		String date = sdf.format( now );
		
		Smuser us = userService.getUserById( userlineid );
		
		String filepath = "d:\\myfiles\\";
		// String filepath = getServletContext().getRealPath("/")+"myfiles";
		// //上传文件存放目录
		// String temp=getServletContext().getRealPath("/")+"temp"; //临时目录
		System.out.println( "存放地址: " + filepath );
		
		File f = new File( filepath );
		if ( ! f.exists() )
		{
			f.mkdirs();
		}
		
		try
		{
			
			// su.setAllowedFilesList("doc,txt");
			
			Smfile sm = null;
			Fileshare fs = null;
			for ( Map.Entry< String , MultipartFile > entity : fileMap.entrySet() )
			{
				sm = new Smfile();
				fs = new Fileshare();
				// 上传文件名
				MultipartFile mf = entity.getValue();
				String fileName = mf.getOriginalFilename();
				if ( ! fileName.equals( "" ) && ! ( fileName == null ) )
				{
					// String filePathName = filepath + fileName;
					// 保存
					// count = mySmartUpload.save(filepath);
					// count = mySmartUpload.save(null);
					fileName = sdff.format( now ) + "_" + uname + "_" + fileName;
					File uploadFile = new File( filepath + fileName );
					
					FileCopyUtils.copy( mf.getBytes() , uploadFile );
					
					sm.setUserid( userlineid );
					sm.setFileid( fileid );
					sm.setFilename( fileName );
					sm.setFilepath( filepath );
					try
					{
						date1 = sdf.parse( date );
						sm.setCurrdate( date1 );
					}
					catch ( ParseException e )
					{
						e.printStackTrace();
					}
					uploadService.addFile( sm );
					
					String sharetype = request.getParameter( "sharetype" );
					int shart = Integer.parseInt( sharetype );
					String memo = request.getParameter( "memo" );
					// 1等于部门共享 2 用户共享
					if ( shart == 1 )
					{
						String[] didstr = request.getParameter( "deptids" ).split( "," );
						
						for ( String did : didstr )
						{
							FilesMapping fm = new FilesMapping();
							fm.setOpuser( us );
							fm.setOptime( new Date() );
							fm.setDeptid( Integer.parseInt( did ) );
							fm.setFileid( sm.getLineid() );
							uploadService.saveFileMapping( fm );
						}
					}
					else if ( shart == 2 )
					{
						String[] usids = request.getParameter( "userids" ).split( "," );
						for ( String did : usids )
						{
							FilesMapping fm = new FilesMapping();
							fm.setOpuser( us );
							fm.setOptime( new Date() );
							fm.setUserid( Integer.parseInt( did ) );
							fm.setFileid( sm.getLineid() );
							uploadService.saveFileMapping( fm );
						}
					}
					
					// System.out.println(sm.getLineid()+"---"+userid+"---"+deptid+"---"+sharetype);
					fs.setFileid( sm.getLineid() );
					
					// fs.setDeptid(Integer.parseInt(deptid));
					fs.setSharetype( shart );
					fs.setMemo( memo );
					fs.setStatus( 1 );
					try
					{
						sharedate = sdf.parse( date );
						fs.setSharedate( sharedate );
					}
					catch ( ParseException e )
					{
						e.printStackTrace();
					}
					uploadService.shareFile( fs );
				}
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		// this.getSelfFile(request, response);
		return "redirect:/UploadServlet/getSelfFile";
		
	}
	
	@RequestMapping( "dowfilebyname" )
	public void dowfilebyname( HttpServletRequest request , HttpServletResponse response )
	        throws ServletException , IOException
	{
		
		// 文件都是放在myfiles文件夹下的 这个可以自己改
		// String root = getServletContext().getRealPath("/");
		String root = "d:\\";
		root = root + "myfiles/";
		String fname = request.getParameter( "filename" ); // 取得文件名
		// fname = new String( fname.getBytes( "ISO-8859-1" ) , "UTF-8" );
		
		System.out.println( fname );
		try
		{
			// fname = new String(fname.getBytes("iso8859-1"),"utf-8");
			// //为其重新编码，目的是为了让其能下载中文文件
			OutputStream os = response.getOutputStream(); // 输出流（从response中获取）
			FileInputStream fis = new FileInputStream( root + fname ); // 以服务器端文件为基础构建输入流（读）
			
			String s = new String( fname.getBytes( "utf-8" ) , "ISO-8859-1" );
			response.setContentType( "unknown" ); // 设置文件响应类型
			response.setHeader( "Content-Disposition" , "attachment; filename=\"" + s
			        + "\"" ); // 主要是向用户显示文件名
			response.setCharacterEncoding( "UTF-8" );
			request.setCharacterEncoding( "UTF-8" );
			
			BufferedInputStream bufIn = new BufferedInputStream( fis ); // 对流进行封装
			BufferedOutputStream bufOut = new BufferedOutputStream( os );
			byte[] b = new byte[1024];
			int i = 0;
			while ( ( i = bufIn.read( b ) ) > 0 )
			{ // 依次将各种信息写入到response中。
				bufOut.write( b , 0 , i );
			}
			bufOut.flush();
			bufOut.close();
			bufIn.close();
			fis.close();
			os.flush();
			os.close();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	@RequestMapping( "dowfilebyholiday" )
	public void dowfilebyholiday( HttpServletRequest request ,
	        HttpServletResponse response ) throws ServletException , IOException
	{
		response.setCharacterEncoding( "utf-8" );
		request.setCharacterEncoding( "utf-8" );
		// 文件都是放在myfiles文件夹下的 这个可以自己改
		// String root = getServletContext().getRealPath("/");
		String root = "d:\\myfiles\\holiday\\";
		String fname = request.getParameter( "filename" ); // 取得文件名
		fname = new String( fname.getBytes( "ISO-8859-1" ) , "UTF-8" );
		
		System.out.println( fname );
		try
		{
			// fname = new String(fname.getBytes("iso8859-1"),"utf-8");
			// //为其重新编码，目的是为了让其能下载中文文件
			OutputStream os = response.getOutputStream(); // 输出流（从response中获取）
			FileInputStream fis = new FileInputStream( root + fname ); // 以服务器端文件为基础构建输入流（读）
			
			String s = new String( fname.getBytes( "GBK" ) , "ISO-8859-1" );
			
			response.setContentType( "unknown" ); // 设置文件响应类型
			response.setHeader( "Content-Disposition" , "attachment; filename=\"" + s
			        + "\"" ); // 主要是向用户显示文件名
			
			BufferedInputStream bufIn = new BufferedInputStream( fis ); // 对流进行封装
			BufferedOutputStream bufOut = new BufferedOutputStream( os );
			byte[] b = new byte[1024];
			int i = 0;
			while ( ( i = bufIn.read( b ) ) > 0 )
			{ // 依次将各种信息写入到response中。
				bufOut.write( b , 0 , i );
			}
			bufOut.flush();
			bufOut.close();
			bufIn.close();
			fis.close();
			os.flush();
			os.close();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	// 得到自己所上传的文件
	@RequestMapping( "getSelfFile" )
	public String getSelfFile( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		int fileid = ( Integer ) session.getAttribute( "recordid" );
		// System.out.println(fileid);
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = uploadService.getCountByFileid( fileid );
		pages = uploadService.getpages( count , 5 );
		// System.out.println(p);
		if ( p == null || p == "" )
		{
			page = 1;
		}
		else
		{
			page = Integer.parseInt( p );
			if ( page < 1 )
			{
				page = 1;
			}
			else if ( page > pages )
			{
				page = pages;
			}
		}
		// System.out.println(page);
		List< Smfile > sm = uploadService.getSelfFile( fileid , page );
		request.setAttribute( "recordid" , fileid );
		request.setAttribute( "sm" , sm );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "returnPath" , "UploadServlet/getSelfFile?op=1" );
		
		return "oa_upload/myfile";
	}
	
	@RequestMapping( "getAllFile" )
	public String getAllFile( HttpServletRequest request )
	{
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = uploadService.getCount();
		pages = uploadService.getpages( count , 5 );
		// System.out.println(p);
		if ( p == null || p == "" )
		{
			page = 1;
		}
		else
		{
			page = Integer.parseInt( p );
			if ( page < 1 )
			{
				page = 1;
			}
			else if ( page > pages )
			{
				page = pages;
			}
		}
		// System.out.println(page);
		List< Smfile > sm = uploadService.getAllFile( page );
		request.setAttribute( "sm" , sm );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "returnPath" , "UploadServlet/getAllFile?op=1" );
		
		return "oa_upload/allfile";
	}
	
	/**
	 * 不仅删除上传文件 表里的数据,还把对应的文件删除了
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping( "del" )
	public String delfile( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		uploadService.delSmfileById( id );
		return "redirect:/UploadServlet/getSelfFile";
	}
	
	@RequestMapping( "shareFile" )
	public String shareFile( HttpServletRequest request )
	{
		Date now = new Date();
		Date sharedate;
		String fileid = request.getParameter( "fileid" );
		String userid = request.getParameter( "userid" );
		String deptid = request.getParameter( "deptid" );
		String sharetype = request.getParameter( "sharetype" );
		String memo = request.getParameter( "memo" );
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		String date = sdf.format( now );
		if ( userid == "" )
		{
			userid = "0";
		}
		if ( deptid == "" )
		{
			deptid = "0";
		}
		Fileshare fs = new Fileshare();
		System.out.println( userid + "---" + deptid + "---" + sharetype );
		fs.setFileid( Integer.parseInt( fileid ) );
		fs.setUserid( Integer.parseInt( userid ) );
		fs.setDeptid( Integer.parseInt( deptid ) );
		fs.setSharetype( Integer.parseInt( sharetype ) );
		fs.setMemo( memo );
		fs.setStatus( 1 );
		try
		{
			sharedate = sdf.parse( date );
			fs.setSharedate( sharedate );
		}
		catch ( ParseException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		uploadService.shareFile( fs );
		// this.getShareFile(request, response);
		return "redirect:/UploadServlet/getShareFile";
	}
	
	@RequestMapping( "getUserInfo" )
	public String getUserInfo( HttpServletRequest request )
	{
		List< Object[] > uslist = uploadService.getUserInfo();
		JSONArray arr = new JSONArray();
		for ( Object[] s : uslist )
		{
			JSONObject json = new JSONObject();
			json.element( "lineid" , s[0] );
			json.element( "deptname" , s[1] );
			json.element( "usname" , s[2] );
			arr.add( json );
		}
		System.out.println( "arr.toString() " + arr.toString() );
		request.setAttribute( "uslist" , arr.toString() );
		return "oa_upload/user";
	}
	
	@RequestMapping( "getShareFile" )
	public String getShareFile( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		int fileid = ( Integer ) session.getAttribute( "recordid" );
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = uploadService.getCountShareFile( fileid );
		pages = uploadService.getpages( count , 5 );
		// System.out.println(p);
		if ( p == null || p == "" )
		{
			page = 1;
		}
		else
		{
			page = Integer.parseInt( p );
			if ( page < 1 )
			{
				page = 1;
			}
			else if ( page > pages )
			{
				page = pages;
			}
		}
		List list = uploadService.getShareFile( fileid , page );
		request.setAttribute( "list" , list );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "returnPath" , "UploadServlet/getShareFile?op=1" );
		
		return "oa_upload/mysharefile";
	}
	
	@RequestMapping( "getShareFileById" )
	public String getShareFileById( HttpServletRequest request )
	{
		int lineid = Integer.parseInt( request.getParameter( "lineid" ) );
		int status = Integer.parseInt( request.getParameter( "status" ) );
		Fileshare fs = uploadService.getShareFileById( lineid );
		fs.setStatus( status );
		uploadService.updateShareFile( fs );
		// this.getShareFile(request, response);
		
		return "redirect:/UploadServlet/getShareFile";
	}
	
	@RequestMapping( "delShareFile" )
	public String delShareFile( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		uploadService.delSharefileById( id );
		// this.getShareFile(request, response);
		
		return "redirect:/UploadServlet/getShareFile";
	}
	
	@RequestMapping( "getUplodShareFile" )
	public String getUplodShareFile( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		int fileid = ( Integer ) session.getAttribute( "recordid" );
		int smuserid = ( Integer ) session.getAttribute( "lineid" );
		Employrecord em = uploadService.getEmrById( fileid );
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = uploadService.getCountDownShareFile( em.getDept().getLineid() , smuserid );
		pages = uploadService.getpages( count , 5 );
		if ( p == null || p == "" )
		{
			page = 1;
		}
		else
		{
			page = Integer.parseInt( p );
			if ( page < 1 )
			{
				page = 1;
			}
			else if ( page > pages )
			{
				page = pages;
			}
		}
		List list = uploadService.getUplodShareFile( page , em.getDept().getLineid() ,
		        smuserid );
		request.setAttribute( "list" , list );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "returnPath" , "UploadServlet/getUplodShareFile?op=1" );
		
		return "oa_upload/downsharefile";
	}
	
}
