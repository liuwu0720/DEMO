package clt.com.cn.controller.position;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import clt.com.cn.model.entity.Position;
import clt.com.cn.service.IPositionService;

@Controller
@RequestMapping( "/PositionServlet" )
public class PositionController
{
	
	@Autowired
	private IPositionService positionService;
	
	@RequestMapping( "/getAllPosition" )
	public String getAllPosition( HttpServletRequest request )
	{
		List< Position > position = positionService.getAllPosition();
		request.setAttribute( "position" , position );
		/*request.getRequestDispatcher("oa_position/allposition.jsp").forward(
				request, response);*/
		
		return "oa_position/allposition";
	}
	
	@RequestMapping( "/getAllPoByPage" )
	public String getAllPoByPage( HttpServletRequest request )
	{
		String p = request.getParameter( "page" );
		int page , pages , count;
		count = positionService.getCount();
		pages = positionService.getpages( count , 5 );
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
		List< Position > position = positionService.getAllPosition( page );
		request.setAttribute( "page" , page );
		request.setAttribute( "pages" , pages );
		request.setAttribute( "position" , position );
		request.setAttribute( "returnPath" , "PositionServlet/getAllPoByPage?op=1" );
		/*request.getRequestDispatcher("oa_position/allposition.jsp").forward(
				request, response);*/
		
		return "oa_position/allposition";
	}
	
	@RequestMapping( "/add" )
	public String addPosition( HttpServletRequest request )
	{
		String positionname = request.getParameter( "positionname" );
		// System.out.println(positionname);
		Position po = new Position();
		po.setPositionname( positionname );
		positionService.addPosition( po );
		// this.getAllPoByPage(request, response);
		return "redirect:/PositionServlet/getAllPoByPage";
	}
	
	public String delPosition( HttpServletRequest request )
	{
		HttpSession session = request.getSession( true );
		String userno = ( String ) session.getAttribute( "uname" );
		
		int id = Integer.parseInt( request.getParameter( "id" ) );
		Position pos = positionService.getPositionById( id );
		// 该岗位设置为 -1 则不显示
		pos.setStatus( - 1 );
		pos.setOpUserName( userno );
		pos.setOptime( new Date() );
		
		// System.out.println(id);
		positionService.updatePosition( pos );
		// this.getAllPoByPage(request, response);
		return "redirect:/PositionServlet/getAllPoByPage";
	}
	
	// 删除之前判断该职位是否有在职人员 有则不能删除
	@RequestMapping( "/del" )
	@ResponseBody
	public String del( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		HttpSession session = request.getSession( true );
		String userno = ( String ) session.getAttribute( "uname" );
		Position pos = positionService.getPositionById( id );
		JSONObject obj = new JSONObject();
		obj.element( "result" , "ok" );
		
		String sql = " select emp.lineid,emp.employname from employrecord emp,position po where emp.positionid=po.lineid and emp.status>0 and po.lineid="
		        + id;
		List< Object[] > strlist = positionService.getDateBySqlQuery( sql , 0 , 0 );
		String msg = "ok";
		if ( strlist.size() > 0 )
		{
			obj.element( "result" , "bad" );
			msg = "该岗位 " + pos.getPositionname() + " 现有员工姓名：  ";
			for ( Object[] str : strlist )
			{
				msg += str[1] + " ";
			}
			obj.element( "msg" , msg );
		}
		else
		{
			
			// 该岗位设置为 -1 则不显示
			pos.setStatus( - 1 );
			pos.setOpUserName( userno );
			pos.setOptime( new Date() );
			
			System.out.println( "禁用成功  " + pos.getLineid() );
			positionService.updatePosition( pos );
			
		}
		System.out.println( "msg >> " + msg );
		
		return obj.toString();
	}
	
	// 验证岗位是否存在
	@RequestMapping( "/checkPosName" )
	@ResponseBody
	public String checkPosName( HttpServletRequest request )
	{
		String posName = request.getParameter( "posName" );
		String hql = " from Position pos where pos.positionname='" + posName
		        + "' and pos.status > 0 ";
		JSONObject obj = new JSONObject();
		obj.element( "result" , "ok" );
		
		List< Position > poslist = positionService.getPositionInfo( hql , null );
		if ( poslist.size() > 0 )
		{
			obj.element( "result" , "no" );
		}
		
		return obj.toString();
	}
	
	@RequestMapping( "/getUpdatePage" )
	public String getUpdatePage( HttpServletRequest request )
	{
		int id = Integer.parseInt( request.getParameter( "id" ) );
		// System.out.println(id);
		Position po = positionService.getPositionById( id );
		request.setAttribute( "po" , po );
		
		return "oa_position/updatepage";
	}
	
	@RequestMapping( "/updatePosition" )
	public String updatePosition( HttpServletRequest request )
	{
		int lineid = Integer.parseInt( request.getParameter( "lineid" ) );
		String positionname = request.getParameter( "positionname" );
		Position po = new Position();
		po.setLineid( lineid );
		po.setPositionname( positionname );
		positionService.updatePosition( po );
		// this.getAllPoByPage(request, response);
		return "redirect:/PositionServlet/getAllPoByPage";
	}
	
	@RequestMapping( "/getInfoByName" )
	public String getInfoByName( HttpServletRequest request )
	{
		String positionname = request.getParameter( "positionname" );
		String p = request.getParameter( "page" );
		int page , pages , count;
		
		if ( positionname.equals( "搜索信息(职位名称)" ) )
		{
			// this.getAllPosition(request, response);
			return "redirect:/PositionServlet/getAllPosition";
		}
		else
		{
			
			count = positionService.getCountByPositionName( positionname );
			pages = positionService.getpages( count , 5 );
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
			
			List< Position > position = positionService.getPositionByPositionName(
			        positionname , page );
			
			request.setAttribute( "page" , page );
			request.setAttribute( "pages" , pages );
			request.setAttribute( "positionname" , positionname );
			request.setAttribute( "position" , position );
			request.setAttribute( "returnPath" ,
			        "PositionServlet/getInfoByName?op=1&positionname=" + positionname );
			/*request.getRequestDispatcher("oa_position/positionbyname.jsp")
					.forward(request, response);*/
			return "oa_position/positionbyname";
		}
	}
	
}
