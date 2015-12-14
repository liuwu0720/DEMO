package clt.com.cn.controller.employ;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import clt.com.cn.model.entity.Approval;
import clt.com.cn.model.entity.ApprovalFlow;
import clt.com.cn.service.IApprovalService;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IPositionService;
import clt.com.cn.service.IUserService;

/**
 * @Package clt.com.cn.controller.employ
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenbin
 * @date 2014-7-24 下午3:40:47
 * @version V1.0
 */

@Controller
@RequestMapping( "/ApprovalServlet" )
public class ApprovalController
{
	
	@Autowired
	private IApprovalService approvalService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IPositionService posService;
	@Autowired
	private IUserService userService;
	
	@RequestMapping( "/getAllApproval" )
	public String getAllApproval( HttpServletRequest request )
	{
		List< Approval > applist = approvalService.getAllApproval();
		System.out.println( "applist  " + applist.size() );
		request.setAttribute( "applist" , applist );
		return "oa_approval/Approvallist";
		
	}
	
	@RequestMapping( "/addBefore" )
	public String addBefore( HttpServletRequest request )
	{
		/*List< Position > poslist = posService.getAllPosition();
		List< Dept > deptlist = deptService.getAllDept();
		
		request.setAttribute( "poslist" , poslist );
		request.setAttribute( "deptlist" , deptlist );*/
		String sql = " select sm.lineid, emp.employname from Smuser sm,Employrecord emp where sm.recordid = emp.lineid ";
		List< Object[] > uslist = userService.getDateBySqlQuery( sql , 0 , 0 );
		JSONArray jsarr = new JSONArray();
		for ( Object[] s : uslist )
		{
			JSONObject obj = new JSONObject();
			obj.element( "lineid" , s[0] );
			obj.element( "employname" , s[1] );
			jsarr.add( obj );
		}
		String usstr = jsarr.toString();
		request.setAttribute( "uslist" , usstr );
		
		return "oa_approval/addApproval";
		
	}
	
	@RequestMapping( "/add" )
	public String add( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		int usid = Integer.parseInt( session.getAttribute( "lineid" ) + "" );
		String approvalName = request.getParameter( "approvalName" );
		String[] strs = request.getParameterValues( "usids" );
		
		Approval appval = new Approval();
		appval.setOpuserid( usid );
		appval.setApprovalName( approvalName );
		appval.setOpdate( new Date() );
		appval.setDetailnumber( strs.length );
		approvalService.addApproval( appval );
		
		for ( int i = 0 ; i < strs.length ; i++ )
		{
			int uid = Integer.parseInt( strs[i] );
			ApprovalFlow flow = new ApprovalFlow();
			flow.setApprovalid( appval.getLineid() );
			flow.setCheckusid( uid );
			flow.setSortnumber( i + 1 );
			approvalService.addApprovalFlow( flow );
		}
		
		return "oa_approval/Approvallist";
		
	}
}
