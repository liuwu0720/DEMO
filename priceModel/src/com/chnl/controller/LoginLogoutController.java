/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-29 下午2:04:53 
 * @version V1.0 
 */
package com.chnl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chnl.base.DataControl;
  
@Controller
public class LoginLogoutController {  


    protected static Logger logger = Logger.getLogger("controller");  
	

	/**
	 * 普通页面
	 */
	@RequestMapping( value = "/login.do" )
	public ModelAndView loginPage( HttpServletRequest request ,
	        HttpServletResponse response , HttpSession session ,
	        RedirectAttributes attributes )
	{
		String name = request.getParameter( "userno" );// erp传过来的userid
		System.out.println( "name-" + name );
		DataControl dataControl = new DataControl();
		boolean exist = dataControl.searchUserIfExist( name );

		if ( exist || name != null )
		{
			attributes.addFlashAttribute( "smuser" , name );
			return new ModelAndView( "redirect:/view.do" );
		}
		else
		{
			ModelMap modelMap = new ModelMap();
			modelMap.put( "errormessage" , "你没有通过验证" );
			return new ModelAndView( "error" , modelMap );
		}

    }
	
	/**
	 * 
	 * @Description: TODO(跳转到后台管理页面)
	 * @param request
	 * @param response
	 * @return ModelAndView 返回值描述
	 * @author liuwu
	 * @create_date 2014-9-19 上午9:27:55
	 */
	@RequestMapping( value = "/admin.do" )
	public ModelAndView adminPage( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		
		String name = request.getParameter( "userno" );// erp传过来的userid
		DataControl dataControl = new DataControl();
		boolean exist = dataControl.searchUserIfExist( name );
		if ( ! exist )
		{
			exist = true;
		}
		if ( exist )
		{
			return new ModelAndView( "admin/adminpage" );
		}
		else
		{
			ModelMap modelMap = new ModelMap();
			modelMap.put( "errormessage" , "你没有通过验证" );
			return new ModelAndView( "error" , modelMap );
		}
		
	}
	

}  