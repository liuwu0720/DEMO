package clt.com.cn.controller.sm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import clt.com.cn.service.IModuleService;
@Controller
@RequestMapping("/GetCountServlet")
public class GetCountController {

	@Autowired
	private IModuleService moduleService;
	
	@RequestMapping("/getCount")
	@ResponseBody
	public String getCount(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		if(session.getAttribute("lineid") == null) {
			return "";
		}
		int userid=(Integer)session.getAttribute("lineid");
		int deptid=(Integer)session.getAttribute("deptid");
		//int count=moduleService.getCount(deptid, userid);
		String strcount = moduleService.getHandleMessageCount(deptid, userid);
		
		return strcount;
	}
}
