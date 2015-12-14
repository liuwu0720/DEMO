package clt.com.cn.controller.sm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import clt.com.cn.model.entity.Privilege;
import clt.com.cn.model.entity.Role;
import clt.com.cn.model.entity.Roleprivilege;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.model.entity.Userprivilege;
import clt.com.cn.service.IModuleService;
import clt.com.cn.service.IUserService;

@Controller
@RequestMapping("/PrivilegeServlet")
public class PrivilegeController {

	@Autowired
	private IModuleService moduleService;
	@Autowired
	private IUserService userService;

	// 查看所有权限
	@RequestMapping("getAllPr")
	public String getAllPr(HttpServletRequest request){
		String rolid = request.getParameter("id");
		//Smuser sm = userService.getUserById(Integer.parseInt(userid));

		List<Privilege> module = moduleService.getAllPrivilege();
		List<Roleprivilege>  roleprilist = moduleService.getprivilegeByRoleId(Integer.parseInt(rolid));
		List<Privilege> prlist = new ArrayList<Privilege>();
		for(Roleprivilege pr : roleprilist)
		{
			prlist.add(pr.getPrivilege());
		}
		System.out.println("roleprilist >>>"+roleprilist.size());

		request.setAttribute("prlist", JSONArray.fromObject(prlist).toString()) ;
		request.setAttribute("modulelist", JSONArray.fromObject(module).toString()) ;
		System.out.println("prlist >> "+JSONArray.fromObject(prlist).toString());
		System.out.println("module >> "+JSONArray.fromObject(module).toString());
		
		
		//List<Userprivilege> upr = moduleService.getUserPrivilege(Integer
				//.parseInt(userid));
		
		
		//request.setAttribute("upr", upr);
		//request.setAttribute("module", module);
		//request.setAttribute("prlist", prlist);
		/*request.getRequestDispatcher("oa_privilege/privilege.jsp").forward(
				request, response);*/
		
		return "oa_privilege/showAllPrivigeByRole";
	}

	// 添加权限
	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request) {
		//PrintWriter out = response.getWriter();
		int gla = 0, pid = 0;
		Date date1;
		Date now = new Date();
		HttpSession session = request.getSession(true);
		String userno = (String) session.getAttribute("uname");
		String userid = request.getParameter("userid");
		String[] qlist = request.getParameterValues("name");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf2.format(now);
		// System.out.println(userno+"--"+userid+"--="+date);
		Userprivilege upr = new Userprivilege();
		if (qlist != null && qlist.length > 0) {
			moduleService.delUserPrivilege(Integer.parseInt(userid));
			for (int i = 0; i < qlist.length; i++) {
				System.out.println(qlist[i]);
				Privilege pr = moduleService.gerPrivilegeById(Integer
						.parseInt(qlist[i]));
				for (int j = 0; j < qlist.length; j++) {
					if (pr.getPid() == Integer.parseInt(qlist[j])
							|| pr.getPid() == 0) {
						gla = 1;
						break;
					}
				}
				if (gla == 0) {
					if (pid != pr.getPid()) {
						upr.setUserid(Integer.parseInt(userid));
						upr.setPrivilegeid(pr.getPid());
						upr.setUserno(userno);
						try {

							date1 = sdf2.parse(date);
							upr.setCurrdate(date1);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						moduleService.addUserPrivilege(upr);
						pid = pr.getPid();
					}
				}

				upr.setUserid(Integer.parseInt(userid));
				upr.setPrivilegeid(Integer.parseInt(qlist[i]));
				upr.setUserno(userno);
				try {

					date1 = sdf2.parse(date);
					upr.setCurrdate(date1);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				moduleService.addUserPrivilege(upr);

			}
			return "<br><br><br><br><center>权限分配成功<br><br><br><a href='UserServlet/getAllUsers'>返回</a></center>";

		} else {
			return "<br><br><br><br><center>权限不能为空<br><br><br><a href='UserServlet/getAllUsers'>返回</a></center>";
		}

	}

	// 查看所有权限
		@RequestMapping("getAllUserByRole")
		public String getAllUserByRole(HttpServletRequest request){
			String sql = " select r.lineid,emp.employname,r.rolename from role r,employrecord emp,smuser sm where sm.roleid = r.lineid and sm.recordid = emp.lineid ";
			
			// 查询所有信息
			String p = request.getParameter("page");
			int page, pages, count;
			
			String countSQL="";
			int beginPos = sql.toLowerCase().indexOf("from");
			if (beginPos != -1) {
				countSQL = "select count(*) "+sql.substring(beginPos);
			}
			
			count = userService.getCountBySql(countSQL);
			pages = userService.getpages(count, 5);
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
			List list = userService.getDateBySqlQuery(sql, 5, page);
			
			request.setAttribute("page", page);
			request.setAttribute("pages", pages);
			request.setAttribute("rollist", list);
			request.setAttribute("returnPath", "PrivilegeServlet/getAllUserByRole?op=1");
			return "oa_privilege/showRoleList";
		}

}
