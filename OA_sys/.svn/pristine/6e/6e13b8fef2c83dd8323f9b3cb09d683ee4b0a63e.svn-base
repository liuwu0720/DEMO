package clt.com.cn.controller.news;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import clt.com.cn.model.entity.Notify;
import clt.com.cn.model.entity.Notifyfile;
import clt.com.cn.model.entity.Notifyshare;
import clt.com.cn.service.INotifyService;

@Controller
@RequestMapping( "/NotifyServlet" )
public class NotifyController
{
	
	@Autowired
	private INotifyService notifyService;
	
	// 添加新闻
	@RequestMapping( "addNotify" )
	public String addNotify( HttpServletRequest request , HttpServletResponse response )
	        throws ServletException , IOException
	{
		
		MultipartHttpServletRequest mrequest = ( MultipartHttpServletRequest ) request;
		
		Map< String , MultipartFile > fileMap = mrequest.getFileMap();
		
		Date date1 , date2 , sharedate;
		Date now = new Date();
		HttpSession session = request.getSession( true );
		
		String uname = ( String ) session.getAttribute( "uname" );
		int deptid = ( Integer ) session.getAttribute( "deptid" );
		int userid = ( Integer ) session.getAttribute( "lineid" );
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		SimpleDateFormat sdff = new SimpleDateFormat( "yyyyMMddHHmmss" );
		String date = sdf.format( now );
		
		String filepath = "d:\\notifyfiles\\";
		System.out.println( "存放地址: " + filepath );
		
		File f = new File( filepath );
		if ( ! f.exists() )
		{
			f.mkdirs();
		}
		
		Notify no = new Notify();
		Notifyfile nof = new Notifyfile();
		Notifyshare ns = new Notifyshare();
		
		for ( Map.Entry< String , MultipartFile > entity : fileMap.entrySet() )
		{
			
			// 上传文件名
			MultipartFile mf = entity.getValue();
			String title = request.getParameter( "title" );
			String content = request.getParameter( "content" );
			String type = request.getParameter( "type" );
			String notifydate = request.getParameter( "notifydate" );
			
			String fileName = mf.getOriginalFilename();
			
			no.setContent( content );
			no.setDeptid( deptid );
			no.setUserid( userid );
			no.setTitle( title );
			no.setType( Integer.parseInt( type ) );
			try
			{
				date1 = sdf.parse( notifydate );
				date2 = sdf.parse( date );
				no.setNotifydate( date1 );
				no.setCurrdate( date2 );
			}
			catch ( ParseException e )
			{
				e.printStackTrace();
			}
			notifyService.addNotify( no );
			
			if ( ! fileName.equals( "" ) && ! ( fileName == null ) )
			{
				fileName = sdff.format( now ) + "_" + uname + "_" + fileName;
				File uploadFile = new File( filepath + fileName );
				
				FileCopyUtils.copy( mf.getBytes() , uploadFile );
				
				// ------------增加新闻上传附件----------
				nof.setUserid( userid );
				nof.setFilename( fileName );
				nof.setFilepath( filepath );
				nof.setNotifyid( no.getLineid() );
				try
				{
					date2 = sdf.parse( date );
					nof.setCurrdate( date2 );
				}
				catch ( ParseException e )
				{
					e.printStackTrace();
				}
				notifyService.addNotifyFile( nof );
				
			}
			
			// -----------新闻、附件共享 --------------
			String sharetype = request.getParameter( "sharetype" );
			String suserid = request.getParameter( "userid" );
			String sdeptid = request.getParameter( "deptid" );
			String memo = request.getParameter( "memo" );
			if ( ! suserid.equals( "" ) )
			{
				ns.setUserid( Integer.parseInt( suserid ) );
			}
			if ( ! sdeptid.equals( "" ) )
			{
				ns.setDeptid( Integer.parseInt( sdeptid ) );
			}
			ns.setNotifyid( no.getLineid() );
			ns.setSharetype( Integer.parseInt( sharetype ) );
			ns.setMemo( memo );
			ns.setStatus( 1 );
			try
			{
				sharedate = sdf.parse( date );
				ns.setSharedate( sharedate );
			}
			catch ( ParseException e )
			{
				e.printStackTrace();
			}
			notifyService.addNotifyShare( ns );
			
		}
		
		notifyService.addNotifyShare( ns );
		return "redirect:/NotifyServlet/getAllNotify";
	}
	
	// 查看所有新闻
	@RequestMapping( "/getAllNotify" )
	public String getAllNotify( HttpServletRequest request )
	{
		
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = notifyService.getAllCount();
		pages = notifyService.getpages( count , 5 );
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
		List no = notifyService.getAllNotify( page );
		request.setAttribute( "no" , no );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "returnPath" , "NotifyServlet/getAllNotify?op=1" );
		
		return "oa_news/getallnews";
	}
	
	// 查看新闻内容
	@RequestMapping( "/getNotifyById" )
	public String getNotifyById( HttpServletRequest request )
	{
		String id = request.getParameter( "id" );
		List no = notifyService.getNotifyById( Integer.parseInt( id ) );
		request.setAttribute( "no" , no );
		return "oa_news/getnewsbyid";
	}
	
	// 下载新闻附件
	@RequestMapping( "/downsfile" )
	public void downsfile( HttpServletRequest request , HttpServletResponse response )
	        throws ServletException , IOException
	{
		response.setCharacterEncoding( "utf-8" );
		request.setCharacterEncoding( "utf-8" );
		// 文件都是放在myfiles文件夹下的 这个可以自己改
		// String root = getServletContext().getRealPath("/");
		String root = "d:\\";
		root = root + "notifyfiles/";
		String fname = request.getParameter( "filename" ); // 取得文件名
		
		response.setContentType( "unknown" ); // 设置文件响应类型
		response.setHeader( "Content-Disposition" , "attachment; filename=\"" + fname
		        + "\"" ); // 主要是向用户显示文件名
		fname = new String( fname.getBytes( "iso8859-1" ) , "utf-8" );
		System.out.println( fname );
		try
		{
			// fname = new String(fname.getBytes("iso8859-1"),"utf-8");
			// //为其重新编码，目的是为了让其能下载中文文件
			OutputStream os = response.getOutputStream(); // 输出流（从response中获取）
			FileInputStream fis = new FileInputStream( root + fname ); // 以服务器端文件为基础构建输入流（读）
			
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
	
	// 删除新闻
	@RequestMapping( "/delNotify" )
	public String delNotify( HttpServletRequest request )
	{
		String id = request.getParameter( "id" );
		notifyService.delNotifyshareById( Integer.parseInt( id ) );
		List< Notifyfile > nof = notifyService.getNofByNoId( Integer.parseInt( id ) );
		String filename = nof.get( 0 ).getFilename();
		if ( ! filename.equals( "" ) && ! ( filename == null ) )
		{
			File file = new File( "d:\\notifyfiles" , filename );
			if ( ! file.isDirectory() )
			{
				file.delete();
				notifyService.delNotifyFileById( Integer.parseInt( id ) );
			}
		}
		
		notifyService.delNotifyById( Integer.parseInt( id ) );
		
		return "redirect:/NotifyServlet/getAllNotify";
	}
	
	// 新闻的发布与禁用
	@RequestMapping( "/updateNStatus" )
	public String updateNStatus( HttpServletRequest request , HttpServletResponse response )
	        throws ServletException , IOException
	{
		response.setCharacterEncoding( "utf-8" );
		request.setCharacterEncoding( "utf-8" );
		int lineid = Integer.parseInt( request.getParameter( "lineid" ) );
		int status = Integer.parseInt( request.getParameter( "status" ) );
		Notifyshare ns = notifyService.getNsById( lineid );
		ns.setStatus( status );
		notifyService.updateNotifyShare( ns );
		return "redirect:/NotifyServlet/getAllNotify";
	}
	
	// 得到跟新页面
	@RequestMapping( "/getupdatepage" )
	public String getupdatepage( HttpServletRequest request )
	{
		int lineid = Integer.parseInt( request.getParameter( "lineid" ) );
		List no = notifyService.getUpadatePageById( lineid );
		request.setAttribute( "no" , no );
		
		return "oa_news/updatepage";
	}
	
	// 更新
	@RequestMapping( "/updateNews" )
	public String updateNews( HttpServletRequest request )
	        throws UnsupportedEncodingException
	{
		request.setCharacterEncoding( "utf-8" );
		Date date1 , date2;
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		int lineid = Integer.parseInt( request.getParameter( "lineid" ) );
		int type = Integer.parseInt( request.getParameter( "type" ) );
		int nuserid = Integer.parseInt( request.getParameter( "nuserid" ) );
		int ndeptid = Integer.parseInt( request.getParameter( "ndeptid" ) );
		String notifydate = request.getParameter( "notifydate" );
		String currdate = request.getParameter( "currdate" );
		String title = request.getParameter( "title" );
		String content = request.getParameter( "content" );
		int nsid = Integer.parseInt( request.getParameter( "nsid" ) );
		int status = Integer.parseInt( request.getParameter( "status" ) );
		int sharetype = Integer.parseInt( request.getParameter( "sharetype" ) );
		String userid = request.getParameter( "userid" );
		String deptid = request.getParameter( "deptid" );
		Notify no = new Notify();
		no.setLineid( lineid );
		no.setType( type );
		no.setUserid( nuserid );
		no.setDeptid( ndeptid );
		no.setTitle( title );
		no.setContent( content );
		try
		{
			date1 = sdf.parse( notifydate );
			date2 = sdf.parse( currdate );
			no.setNotifydate( date1 );
			no.setCurrdate( date2 );
		}
		catch ( ParseException e )
		{
			e.printStackTrace();
		}
		notifyService.updateNo( no );
		
		Notifyshare ns = new Notifyshare();
		if ( userid.equals( "" ) )
		{
			userid = "0";
		}
		if ( deptid.equals( "" ) )
		{
			deptid = "0";
		}
		ns.setLineid( nsid );
		ns.setNotifyid( lineid );
		ns.setUserid( Integer.parseInt( userid ) );
		ns.setDeptid( Integer.parseInt( deptid ) );
		ns.setSharetype( sharetype );
		ns.setStatus( status );
		try
		{
			date2 = sdf.parse( currdate );
			ns.setSharedate( date2 );
		}
		catch ( ParseException e )
		{
			e.printStackTrace();
		}
		notifyService.updateNs( ns );
		return "redirect:/NotifyServlet/getAllNotify";
	}
	
	// 查询自己可看的新闻
	@RequestMapping( "/getSelNotify" )
	public String getSelNotify( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		int userid = ( Integer ) session.getAttribute( "lineid" );
		int deptid = ( Integer ) session.getAttribute( "deptid" );
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = notifyService.getCount( deptid , userid );
		pages = notifyService.getpages( count , 5 );
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
		List no = notifyService.getSelfNotify( deptid , userid );
		request.setAttribute( "no" , no );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		
		request.setAttribute( "returnPath" , "NotifyServlet/getSelNotify?op=1" );
		
		return "oa_news/getselfnews";
	}
	
	// 查看新闻内容
	@RequestMapping( "/getSelfNotifyById" )
	public String getSelfNotifyById( HttpServletRequest request )
	{
		String id = request.getParameter( "id" );
		List no = notifyService.getNotifyById( Integer.parseInt( id ) );
		
		request.setAttribute( "no" , no );
		
		return "oa_news/getselfnewsbyid";
	}
	
	// 根据新闻类型查询所有新闻信息
	@RequestMapping( "/getNotifyByType" )
	public String getNotifyByType( HttpServletRequest request )
	{
		String type = request.getParameter( "type" );
		
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = notifyService.getCountByType( Integer.parseInt( type ) );
		pages = notifyService.getpages( count , 5 );
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
		List no = notifyService.getNotifyByType( Integer.parseInt( type ) , page );
		request.setAttribute( "type" , type );
		request.setAttribute( "no" , no );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "returnPath" , "NotifyServlet/getNotifyByType?op=1&type="
		        + type );
		
		return "oa_news/getnewsbytype";
	}
	
}
