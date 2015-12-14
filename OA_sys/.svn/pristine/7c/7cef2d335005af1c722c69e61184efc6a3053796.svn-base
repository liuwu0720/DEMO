package clt.com.cn.controller.employ;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import clt.com.cn.model.entity.Employaccredit;
import clt.com.cn.model.entity.Employout;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IEmployAccreditService;
import clt.com.cn.service.IEmployoutService;
import clt.com.cn.service.IUserService;

@Controller
@RequestMapping("/EmployAccredit")
public class EmployAccreditController {

	@Autowired
	private IEmployAccreditService empAccreditService;
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/getEmployAccreditByUser")
	public String getEmoByid(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		int usid =Integer.parseInt(session.getAttribute("lineid")+"");
		
		String p = request.getParameter("page");
		int page, pages, count;
		count = empAccreditService.getAllEmployaccreditByUidCount(usid);
		pages = empAccreditService.getpages(count, 5);
		
		if (p == null || p == "") {
			page = 1;
		} else {
			page = Integer.parseInt(p);
			if (page < 1) {
				page = 1;
			} else if (page > pages) {
				page = pages;
			}
		}
		
		
		List<Employaccredit> emplist = empAccreditService.getAllEmployaccreditByUid(usid, page, 5);
		
		request.setAttribute("page", page);
		request.setAttribute("pages", pages);
		request.setAttribute("emplist", emplist);
		return "oa_employAccredit/allemployAccredit";
	}
	
	@RequestMapping("/getauthorizerByadd")
	public String getauthorizerByadd(HttpServletRequest request) {
		
		HttpSession session =request.getSession();
		int uid = Integer.parseInt(session.getAttribute("lineid")+"");
		String sql ="select sm.lineid,emp.employname from dept d,smuser sm,employrecord emp where d.manageruserid=sm.lineid and sm.recordid=emp.lineid and sm.lineid!="+uid+
					" union "+
					" select sm.lineid,emp.employname from dept d,smuser sm,employrecord emp where d.lineid=emp.deptid and emp.lineid=sm.recordid and d.lineid=2  and sm.lineid!="+uid;
		List uslist = userService.getDateBySqlQuery(sql,0,0);
		request.setAttribute("uslist", uslist);
		return "oa_employAccredit/addemployAccredit";
	}
	
	@RequestMapping("/addauthorizer")
	public String addauthorizer(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int loguid = Integer.parseInt(session.getAttribute("lineid")+"") ;
		int usid = Integer.parseInt(request.getParameter("ussel"));
		
		Employaccredit empdit = new Employaccredit();
		empdit.setCurrdate(new Date());
		empdit.setAuthorizerUser(userService.getUserById(usid));
		empdit.setCertigierUser(userService.getUserById(loguid));
		empAccreditService.addEmr(empdit);
		
		return "redirect:/EmployAccredit/getEmployAccreditByUser";
	}
	@RequestMapping("/delauthorizer")
	public String delauthorizer(HttpServletRequest request) {
		
		int empid = Integer.parseInt(request.getParameter("lineid")) ;
		Employaccredit empdit = empAccreditService.getEmployaccreditById(empid);
		System.out.println(">>>"+empdit.getCurrdate());
		empAccreditService.delEmrById(empid);
		System.out.println("@@@@删除成功.");
		return "redirect:/EmployAccredit/getEmployAccreditByUser";
	}
}
