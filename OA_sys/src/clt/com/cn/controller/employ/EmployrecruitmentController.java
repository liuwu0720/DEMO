package clt.com.cn.controller.employ;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;

import clt.com.cn.helps.SystemStatus;
import clt.com.cn.model.entity.Educationlevel;
import clt.com.cn.model.entity.Employholiday;
import clt.com.cn.model.entity.EmployholidayType;
import clt.com.cn.model.entity.Employovertime;
import clt.com.cn.model.entity.EmployovertimeType;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Employrecruitment;
import clt.com.cn.model.entity.EmployrecruitmentType;
import clt.com.cn.model.entity.Position;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.service.IDeptService;
import clt.com.cn.service.IEmployholidayService;
import clt.com.cn.service.IEmployovertimeService;
import clt.com.cn.service.IEmployrecordService;
import clt.com.cn.service.IEmployrecruitmentService;
import clt.com.cn.service.IUserService;

@Controller
@RequestMapping("/EmployrecruitmentServlet")
public class EmployrecruitmentController {

	@Autowired
	private IEmployholidayService ehdService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IEmployrecordService emrService;
	@Autowired
	private IEmployrecruitmentService employrecruitmentService;
	
	
	public IEmployholidayService getEhdService() {
		return ehdService;
	}

	public void setEhdService(IEmployholidayService ehdService) {
		this.ehdService = ehdService;
	}

	
	
	//招聘申请前  加载请假类别数据
	@RequestMapping("/addbefore")
	public String addbefore(HttpServletRequest request)
	{
		List<Educationlevel> edulist = emrService.getAllEducationlevel();
		List<Position> poslist = emrService.getAllPosition();
		List<EmployrecruitmentType> typelist = employrecruitmentService.getAllEmployrecruitmentType();
		Map<Integer,String> agemap = SystemStatus.getagerange();
		Map<Integer,String> workmap = SystemStatus.getworkyear();
		
		request.setAttribute("educationlist", edulist);
		request.setAttribute("poslist", poslist);
		request.setAttribute("typelist", typelist);
		request.setAttribute("agemap", agemap);
		request.setAttribute("workmap", workmap);
		
		return "oa_employrecruitment/addemployrecruitments";
	}
	
	// 申请加班
	@RequestMapping("/add")
	public String addEhr(HttpServletRequest request) {
		Date now = new Date();
		Date date1, date2, date3;
		HttpSession session = request.getSession(true);
		String userno = (String) session.getAttribute("uname");
		int possid = Integer.parseInt(request.getParameter("possid"));
		int numbers = Integer.parseInt(request.getParameter("numbers"));
		int typeid = Integer.parseInt(request.getParameter("typeid"));
		int sexID = Integer.parseInt(request.getParameter("sexID"));
		int maritalsta = Integer.parseInt(request.getParameter("maritalsta"));
		int ageID = Integer.parseInt(request.getParameter("ageID"));
		int educationID = Integer.parseInt(request.getParameter("educationID"));
		int workyear = Integer.parseInt(request.getParameter("workyear"));
		

		String englevel = request.getParameter("englevel");
		String professional = request.getParameter("professional");
		String expertise = request.getParameter("expertise");
		String workcontents = request.getParameter("workcontents");
		
		String d1 = request.getParameter("date1");
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(now);
		if (userno != null) {
			List<Smuser> list = ehdService.getUserByName(userno);
			Smuser s = (Smuser) list.get(0);
			Employrecord em = s.getEmployrecord();
			EmployrecruitmentType emptype = employrecruitmentService.getEmployrecruitmentType(typeid);
			Position pos = emrService.getPositionById(possid);
			Educationlevel edu = emrService.getEducationlevelbyID(educationID);
			
			Employrecruitment ehd = new Employrecruitment();
			ehd.setPosition(pos);
			ehd.setPeopleNumber(numbers);
			ehd.setSex(sexID);
			ehd.setMarital(maritalsta);
			ehd.setAgerange(ageID);
			ehd.setWorkyear(workyear);
			ehd.setEnglevel(englevel);
			ehd.setProfessional(professional);
			ehd.setExpertise(expertise);
			ehd.setWorkcontents(workcontents);
			
			ehd.setOpuser(s);
			ehd.setEmployrecord(em);
			ehd.setIscheck(0);
			ehd.setEmployrecruitmentType(emptype);
			ehd.setEducationlevel(edu);
			
			Smuser smuser= userService.getUSerByManagerApproval(s);
			
			ehd.setCheckuser(smuser);
			ehd.setDept(deptService.getDeptById(em.getDept().getLineid()));
			try {
				date1 = sdf.parse(d1);
				date3 = sdf.parse(date);
				ehd.setCurrdate(date3);
				ehd.setChecktime(date1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			employrecruitmentService.addEmr(ehd);
			//this.getEhdByUncheck(request, response);
			return "redirect:/EmployrecruitmentServlet/getEhdByUncheck";
		} else {
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			
			return "index";
		}
	}

	// 查看单个请假条信息
	@RequestMapping("/getEhdByid")
	public String getEhdByid(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employrecruitment emp =  employrecruitmentService.getEmployrecruitmentById(id);
		Map<Integer,String> agemap = SystemStatus.getagerange();
		Map<Integer,String> workmap = SystemStatus.getworkyear();
		Map<Integer,String> maritalmap = SystemStatus.getmarital();
		Map<Integer,String> stamap = SystemStatus.getEmpSystemStatus();
		

		request.setAttribute("maritalmap", maritalmap);
		request.setAttribute("agemap", agemap);
		request.setAttribute("workmap", workmap);
		request.setAttribute("stamap", stamap);
		request.setAttribute("emp", emp);
		/*request.getRequestDispatcher("oa_employholiday/getbyid.jsp").forward(
				request, response);*/
		
		return "oa_employrecruitment/getbyid";
	}

	// 得到单个请假条信息，放的更新页面中
	@RequestMapping("/getUpdatePage")
	public String getUpdatePage(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employrecruitment emp =employrecruitmentService.getEmployrecruitmentById(id);
		Map<Integer,String> agemap = SystemStatus.getagerange();
		Map<Integer,String> workmap = SystemStatus.getworkyear();
		Map<Integer,String> maritalmap = SystemStatus.getmarital();
		

		request.setAttribute("maritalmap", maritalmap);
		request.setAttribute("agemap", agemap);
		request.setAttribute("workmap", workmap);
		request.setAttribute("emp", emp);
		/*request.getRequestDispatcher("oa_employholiday/updatepage.jsp")
				.forward(request, response);*/
		return "oa_employrecruitment/updatepage";
	}

	// 审批（更新）请假信息
	@RequestMapping("/updateEhd")
	public String updateEhd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		String userno = (String) session.getAttribute("uname");
		int opuserid = Integer.parseInt(session.getAttribute("lineid")+"");
		
		
		int id = Integer.parseInt(request.getParameter("id"));
		int ischeck = Integer.parseInt(request.getParameter("ischeck"));
		String checkremarks = request.getParameter("checkremarks");
		Employrecruitment ehd = employrecruitmentService.getEmployrecruitmentById(id);
		Smuser smuser = userService.getUserById(opuserid);
		
		if (userno != null) {
			ehd.setCheckremarks(checkremarks);
			ehd.setIscheck(ischeck);
			ehd.setCheckdate(new Date());
			//如果登录的用户和该请假对应的审批人不一样(普通员工默认审批为自己经理  经理则祝丽)  则设置实际审批人字段为登陆人 
			if(ehd.getCheckuser().getLineid()!=opuserid)
			{
				ehd.setActualuser(smuser);
			}
			
			employrecruitmentService.updateEmr(ehd);
			//this.getAllEhd(request, response);
			
			return "redirect:/EmployrecruitmentServlet/getEhdByIscheck?ischeck=0";
		} else {
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			return "index";
		}
	}

	// 按审批状态查询
	@RequestMapping("/getEhdByIscheck")
	public String getEhdByIscheck(HttpServletRequest request) {
		int ischeck = Integer.parseInt(request.getParameter("ischeck"));
		HttpSession sess = request.getSession(false);
		int usid = Integer.parseInt(sess.getAttribute("lineid")+"");
		
		String sql = " select emp.lineid,empcord.employname,d.deptname,po.positionname,emp.peoplenumber,emptype.typename,emp.ischeck,emp.checkremarks from employrecruitment emp, dept d,employrecord empcord,position po,employrecruitmentType emptype "+
		        		" where emp.recordid = empcord.lineid and emp.positionid = po.lineid and emp.employrecruitmenttypeid=emptype.lineid and emp.deptid=d.lineid "+
						" and emp.ischeck="+ischeck+ 
						" and (emp.checkuserid="+usid+" or emp.checkuserid in(select empdit.certigieruid from employaccredit empdit where empdit.authorizeruid="+usid+" )) ";
		
		String countSQL="";
		int beginPos = sql.toLowerCase().indexOf("from");
		if (beginPos != -1) {
			countSQL = "select count(*) "+sql.substring(beginPos);
		}
		
		System.out.println("招聘审批 "+sql);
		String p = request.getParameter("page");
		int page, pages, count;
		count = employrecruitmentService.getCountBySQL(countSQL);
		pages = ehdService.getpages(count, 5);
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
		List emplist = employrecruitmentService.getDateBySqlQuery(sql, 5, page);
		Map<Integer,String> stamap = SystemStatus.getEmpSystemStatus();
		
		request.setAttribute("ischeck", ischeck);
		request.setAttribute("page", page);
		request.setAttribute("pages", pages);
		request.setAttribute("emplist", emplist);
		request.setAttribute("stamap", stamap);
		request.setAttribute("returnPath", "EmployrecruitmentServlet/getEhdByIscheck?op=1&ischeck="+ischeck);
	/*	request.getRequestDispatcher("oa_employholiday/ehdbyis.jsp").forward(
				request, response);
		*/
		return "oa_employrecruitment/EmployrecruitmentBychecklist";
	}

	// 查询个人未审批的请假条
	@RequestMapping("/getEhdByUncheck")
	public String getEhdByUncheck(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String uname = (String) session.getAttribute("uname");
		String hql=" from Employrecruitment ehd where ehd.ischeck=? and ehd.opuser.lineid=?";
		if (uname != null) {
			int lineid = (Integer) session.getAttribute("lineid");
			List<Employrecruitment> ehd = employrecruitmentService.getObjInfoByCondition(hql,0, lineid);
			Map<Integer,String> workmap = new SystemStatus().getworkyear();
			Map<Integer,String> stamap = new SystemStatus().getEmpSystemStatus();
			
			request.setAttribute("workmap", workmap);
			request.setAttribute("stamap", stamap);
			request.setAttribute("emplist", ehd);
			/*request.getRequestDispatcher("oa_employholiday/uncheckehd.jsp")
					.forward(request, response);*/
			return "oa_employrecruitment/employrecruitmentlist";
		} else {
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			
			return "index";
		}
	}

	// 删除单个请假条
	@RequestMapping("/del")
	public String delEhrById(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		employrecruitmentService.delEmrById(id);
		//this.getAllEhd(request, response);
		
		return "redirect:/EmployrecruitmentServlet/getEhdByIscheck?ischeck=0";
	}
		
	// 删除个人请假条
	@RequestMapping("/delEhrByGrId")
	public String delEhrByGrId(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		employrecruitmentService.delEmrById(id);
		/*this.getEhdByUncheck(request, response);*/
		return "redirect:/EmployrecruitmentServlet/getEhdByUncheck";
	}

	// 查询历史记录
	@RequestMapping("/getEhdByChecked")
	public String getEhdByChecked(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String uname = (String) session.getAttribute("uname");
		String hql="from Employrecruitment ehd where (ehd.ischeck=1 or ehd.ischeck=2) and ehd.opuser.lineid=?";
		if (uname != null) {
			int lineid = (Integer) session.getAttribute("lineid");
			List<Employrecruitment> emplist = employrecruitmentService.getObjInfoByCondition(hql, lineid);
			Map<Integer,String> workmap = new SystemStatus().getworkyear();
			Map<Integer,String> stamap = new SystemStatus().getEmpSystemStatus();
			
			
			request.setAttribute("workmap", workmap);
			request.setAttribute("stamap", stamap);
			request.setAttribute("emplist", emplist);
			return  "oa_employrecruitment/employrecruitmentlist";
		} else {
			/*request.getRequestDispatcher("index.jsp")
					.forward(request, response);*/
			
			return "index";
		}
	}
}
