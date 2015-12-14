/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-9-4 下午6:51:43 
 * @version V1.0 
 */
package com.chnl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.chnl.test.Person;

/**
 * @Package com.chnl.controller
 * @Description: TODO(后台选项卡页面控制)
 * @author liuwu
 * @date 2014-9-4 下午6:51:43
 * @version V1.0
 */
@Controller
public class AdminController extends MultiActionController
{
	private Person person = new Person();
	
	@RequestMapping( "/legInfo.do" )
	public ModelAndView legInfoPage()
	{
		
		return new ModelAndView( "admin/legInfolist" );
		
	}
	
	@RequestMapping( "/car.do" )
	public ModelAndView carPage()
	{
		
		return new ModelAndView( "admin/carInfoList" );
		
	}
	@RequestMapping("/truck.do")
	public ModelAndView truckPage()
	{
		return new ModelAndView( "admin/truckInfoList" );
	}
	
	@RequestMapping( "/legCarInfo.do" )
	public ModelAndView legCarPage()
	{
		return new ModelAndView( "admin/legCarInfo" );
	}
	
	@RequestMapping( "/legTruckInfo.do" )
	public ModelAndView legTruckInfoPage()
	{
		return new ModelAndView( "admin/legTruckInfo" );
	}
	
	@RequestMapping( "/test.do" )
	public String test()
	{
		person.setName( "fdfdf" );
		return "admin/test";
	}
	
	public Person getPerson()
	{
		return person;
	}
	
	public void setPerson( Person person )
	{
		this.person = person;
	}
	
	

}
