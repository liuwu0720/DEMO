/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-10-13 上午9:55:14
 * @version V1.0
 */
package com.chnl.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chnl.entity.PrLegFileImportForm;
import com.chnl.entity.PrSelfInputLegForm;
import com.chnl.entity.SmUser;
import com.chnl.pojo.PrLegfileimport;
import com.chnl.pojo.PrSelfinputleg;
import com.chnl.pojo.TruckInfo;
import com.chnl.service.CarInfoService;
import com.chnl.service.PrLegFileImportService;
import com.chnl.service.PrSelfImputLegService;
import com.chnl.service.ReadExcelService;
import com.chnl.service.TruckInfoService;

/**
 * @Package com.chnl.controller
 * @Description: TODO(登录后第一个页面：计算前所选择线路，拖车，商品车)
 * @author liuwu
 * @date 2014-10-13 上午9:55:14
 * @version V1.0
 */
@Controller
public class SelectPageController
{
	
	@Autowired
	private ReadExcelService readExcelService;
	
	@Autowired
	private PrLegFileImportService prLegFileImportService;
	
	@Autowired
	private PrSelfImputLegService prSelfImputLegService;

	@Autowired
	private TruckInfoService truckInfoService;
	
	@Autowired
	private CarInfoService carInfoService;

	private List< String > startProvinceList;

	private PrLegFileImportForm prLegFileImportForm = new PrLegFileImportForm();
	
	private PrSelfInputLegForm prSelfInputLegForm = new PrSelfInputLegForm();

	private String typeId;// 判断是自已输入还是用户导入文件：0：导入文件；1：用户自已输入线路
	
	@RequestMapping( value = "layer.do" )
	public String layer( HttpServletRequest request ,
	        HttpServletResponse response )
	{	
		return "layer";
	}

	@SuppressWarnings( "unchecked" )
	@RequestMapping( value = "/view.do" )
	public ModelAndView selectView( HttpServletRequest request , Model model ,
	        HttpSession session , ModelMap modelMap , String history ,
	        String hUser )
	{
		Map< String , Object > paramMap = model.asMap();
		if ( StringUtils.isNotBlank( history ) )
		{
			paramMap.put( "typeId" , history );
		}
		// paramMap.put( "typeId" , "0" );
		SmUser smUser = new SmUser();

		if ( paramMap.get( "smuser" ) != null )
		{
			String userString = ( String ) paramMap.get( "smuser" );
			smUser.setUserName( userString );
		}else {
			String userString = "pricemodel";
			smUser.setUserName( userString );
		}
		if ( StringUtils.isNotBlank( hUser ) )// 勾选历史记录时
		{
			smUser.setUserName( hUser );
		}

		if ( paramMap.get( "error" ) != null )
		{
			String error = ( String ) paramMap.get( "error" );
			modelMap.put( "error" , error );
		}
		else if ( paramMap.get( "typeId" ) != null )
		{
			if ( paramMap.get( "typeId" ).equals( "0" ) )// 导入文件形式
			{

				List< PrLegfileimport > prLegfileimports = prLegFileImportService
				        .findAll( smUser );
				// prLegFileImportService.getIncomeDistance( prLegfileimports );
				prLegFileImportService
				        .calulateEmptlyDistance( prLegfileimports );
				prLegFileImportForm.setPrLegfileimports( prLegfileimports );

				modelMap.put( "prLegFileImportForm" , prLegFileImportForm );
				modelMap.put( "selecttype" , "importfile" );
				/*List< PrSelfinputleg > prSelfinputlegs = prSelfImputLegService
				        .findAll();
				prSelfImputLegService.checkLegInfoAfterData( prSelfinputlegs );
				modelMap.put( "prSelfinputlegs" , prSelfinputlegs );*/

			}
			else if ( paramMap.get( "typeId" ).equals( "1" ) )// 用户自己输入线路
			{
				
				List< PrSelfinputleg > prSelfinputlegs = prSelfImputLegService
				        .findAll( smUser );
				// prSelfImputLegService.getIncomeDistance( prSelfinputlegs );
				prSelfImputLegService.checkLegInfoAfterData( prSelfinputlegs );
				prSelfInputLegForm.setPrSelfinputlegs( prSelfinputlegs );
				modelMap.put( "selecttype" , "selfinput" );
				modelMap.put( "prSelfInputLegForm" , prSelfInputLegForm );
			}
		}
		
		/*List< PrSelfinputleg > prSelfinputlegs = prSelfImputLegService
		        .findAll();
		prSelfImputLegService.checkLegInfoAfterData( prSelfinputlegs );
		modelMap.put( "prSelfinputlegs" , prSelfinputlegs );*/
		modelMap.addAttribute( "smuser" , smUser.getUserName() );
		List< TruckInfo > truckInfos = truckInfoService.findAllTruckInfos();
		modelMap.put( "truckInfos" , truckInfos );
		return new ModelAndView( "main/select" , modelMap );

	}

	/**
	 * 
	 * @Description: TODO(导入文件时的一些验证)
	 * @return List<String> 返回值描述
	 * @author liuwu
	 * @create_date 2014-10-31 上午9:48:10
	 */
	@RequestMapping( value = "/importExcel.do" , method = RequestMethod.POST )
	public String inportExcelFile( String smUserName ,
	        HttpServletRequest request ,
	        RedirectAttributes attributes ) throws Exception
	{
		System.out.println( "smUser.getUserName() --->" + smUserName );
		SmUser smUser = new SmUser();
		smUser.setUserName( smUserName );
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
		        request.getSession().getServletContext() );
		// 判断 request 是否有文件上传,即多部分请求...
		ModelMap map = new ModelMap();
		if ( multipartResolver.isMultipart( request ) )
		{
			// 判断 request 是否有文件上o9传,即多部分请求...
			MultipartHttpServletRequest multiRequest = ( MultipartHttpServletRequest ) ( request );
			Iterator< String > iter = multiRequest.getFileNames();
			while ( iter.hasNext() )
			{
				String message = "";
				MultipartFile file = multiRequest.getFile( iter.next() );
				List< String > locationList = readExcelService.readExcel( file
				        .getInputStream() );
				List< PrLegfileimport > prLegfileimports = new ArrayList< PrLegfileimport >();
				message = readExcelService.checkDataAvailability( locationList ,
				        prLegfileimports );
				if ( message == "" || message == null )
				{
					if ( smUser.getUserName() != null )
					{
						prLegFileImportService.saveDataAfterCheck(
						        prLegfileimports , smUser );
						typeId = "0";// 用户自己导入线路
						attributes.addFlashAttribute( "typeId" , typeId );
						attributes.addFlashAttribute( "smuser" ,
						        smUser.getUserName() );
					}
					else
					{
						message = "非法请求！请重新进入";
						attributes.addFlashAttribute( "error" , message );
					}

				}
				else
				{
					attributes.addFlashAttribute( "error" , message );
				}
				/*List< PrLegfileimport > prLegfileimports = prLegFileImportService
				        .findAll();
				
				attributes.addFlashAttribute( "legs" , prLegfileimports );*/
			}
		}

		return "redirect:/view.do";

	}
	
	/**
	 * 
	 * @Description: TODO(导入文件：将待定线路加入空载)
	 * @return List<String> 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-19 下午4:46:12
	 */
	@RequestMapping( value = "/setEmtly.do" )
	public String setCurrentLegEmptly( HttpServletRequest request ,
	        RedirectAttributes attributes ) throws Exception
	{
		String message = "";
		int legId = Integer.parseInt( request.getParameter( "leg" ) );
		String smuser = request.getParameter( "user" );

		PrLegfileimport prLegfileimport = prLegFileImportService
		        .getPrLegByLegId( legId );
		if ( prLegfileimport.getEmptlyFlag() != null )// 选定的情况
		{
			
			prLegFileImportService
			        .calulateEmptlyDistanceByLegId( prLegfileimport );

		}

		attributes.addFlashAttribute( "typeId" , "0" );
		attributes.addFlashAttribute( "smuser" , smuser );
		return "redirect:/view.do";
	}
	
	/**
	 * 
	 * @Description: TODO(用户自己输入线路：将待定线路加入空载)
	 * @return List<String> 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-19 下午4:46:12
	 */
	@RequestMapping( value = "/setEmtly2.do" )
	public String setCurrentLegEmptly2( HttpServletRequest request ,
	        RedirectAttributes attributes ) throws Exception
	{
		String message = "";
		int legId = Integer.parseInt( request.getParameter( "leg" ) );
		PrSelfinputleg prSelfinputleg = prSelfImputLegService
		        .getPrLegByLegId( legId );
		if ( prSelfinputleg.getEmptlyflag() != null )// 选定的情况
		{
			
			prSelfImputLegService
			        .calulateEmptlyDistanceByLegId( prSelfinputleg );
			
		}
		String smuser = request.getParameter( "user" );
		attributes.addFlashAttribute( "smuser" , smuser );
		attributes.addFlashAttribute( "typeId" , "1" );

		return "redirect:/view.do";
	}
	
	/**
	 * 
	 * @Description: TODO(导入文件情况：用户删除此条空载线路)
	 * @return List<String> 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-20 上午11:40:22
	 */
	@RequestMapping( value = "/emtlydelete.do" )
	public String deleteEmtlyLeg( HttpServletRequest request ,
	        RedirectAttributes attributes )
	{
		
		int legId = Integer.parseInt( request.getParameter( "leg" ) );

		prLegFileImportService.deleteThisLeg( legId );
		String smuser = request.getParameter( "user" );
		attributes.addFlashAttribute( "smuser" , smuser );
		attributes.addFlashAttribute( "typeId" , "0" );
		return "redirect:/view.do";

	}
	
	/**
	 * 
	 * @Description: TODO(用户自定义线路：用户删除此条空载线路)
	 * @return List<String> 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-20 上午11:40:22
	 */
	@RequestMapping( value = "/emtlydelete2.do" )
	public String deleteEmtlyLeg2( HttpServletRequest request ,
	        RedirectAttributes attributes )
	{
		
		int legId = Integer.parseInt( request.getParameter( "leg" ) );
		prSelfImputLegService.deleteThisLeg( legId );
		String smuser = request.getParameter( "user" );
		attributes.addFlashAttribute( "smuser" , smuser );
		attributes.addFlashAttribute( "typeId" , "1" );
		return "redirect:/view.do";
		
	}
	
	/**
	 * 
	 * @Description: TODO(通过ajax取拖车信息)
	 * @param truckId
	 * @return TruckInfo 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-21 下午4:11:13
	 */
	@RequestMapping( "/getTruck.do" )
	@ResponseBody
	public TruckInfo getTruckInfo( @RequestParam( "truckId" ) String truckId )
	{
		
		int reqTruckId = Integer.parseInt( truckId );
		TruckInfo truckInfo = truckInfoService.findById( reqTruckId );
		truckInfo.setLength1( truckInfo.getLength1() );
		truckInfo.setLength2( truckInfo.getLength2() );
		truckInfo.setLength3( truckInfo.getLength3() );

		return truckInfo;
	}
	
	/**
	 * ajax拉取下拉框取值
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用) void 返回值描述
	 * @author liuwu
	 * @throws UnsupportedEncodingException
	 * @create_date 2014-11-22 上午10:27:05
	 */
	@RequestMapping( value = "/smStartCity.do" , method = RequestMethod.POST )
	@ResponseBody
	public JSONArray getSmStartCity( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		
		System.out.println( request.getParameter( "startcity" ) );
		String city = request.getParameter( "startcity" );
		List< String > cList = carInfoService.findAllsmStartCitys( city );
		System.out.println( cList );
		
		return JSONArray.fromObject( cList );
	}
	
	/**
	 * ajax拉取下拉框取值
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用) void 返回值描述
	 * @author liuwu
	 * @throws UnsupportedEncodingException
	 * @create_date 2014-11-22 上午10:27:05
	 */
	@RequestMapping( value = "/startpoint.do" , method = RequestMethod.POST )
	@ResponseBody
	public JSONArray getSmStartCityPoint( HttpServletRequest request ,
	        HttpServletResponse response )
	{

		System.out.println( request.getParameter( "startpoint" ) );
		String startpoint = request.getParameter( "startpoint" );
		List< String > cList = carInfoService.findAllsmStartpoints( startpoint );
		System.out.println( cList );
		
		return JSONArray.fromObject( cList );
	}
	
	/**
	 * ajax拉取下拉框取值
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用) void 返回值描述
	 * @author liuwu
	 * @throws UnsupportedEncodingException
	 * @create_date 2014-11-22 上午10:27:05
	 */
	@RequestMapping( value = "/endtcity.do" , method = RequestMethod.POST )
	@ResponseBody
	public JSONArray getSmEndCityPoint( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		
		System.out.println( request.getParameter( "endcity" ) );
		String endcity = request.getParameter( "endcity" );
		List< String > cList = carInfoService.findAllendcity( endcity );
		System.out.println( cList );
		
		return JSONArray.fromObject( cList );
	}
	
	/**
	 * 
	 * @Description: TODO(用户自定输入确定后)
	 * @param startcity
	 * @param startpoint
	 * @param endtcity
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2014-11-22 下午2:57:42
	 */
	@RequestMapping( value = "/selfselect.do" , method = RequestMethod.POST )
	public String inputSure( String startcity , String startpoint ,
	        String smUserName ,
	        String endtcity , RedirectAttributes attributes )
	{
		String errorString = "";
		SmUser smUser = new SmUser();
		smUser.setUserName( smUserName );
		errorString = prSelfImputLegService.checkLineValid( startcity ,
		        startpoint , endtcity , smUserName );
		if ( errorString == "" || errorString == null )
		{
			// prSelfImputLegService.checkLegInfoAfterData();
			attributes.addFlashAttribute( "typeId" , "1" );// 1表示自己输入
			attributes.addFlashAttribute( "smuser" , smUser.getUserName() );
		}
		else
		{
			attributes.addFlashAttribute( "error" , errorString );
		}
		return "redirect:/view.do";
		
	}
	
	/**
	 * 
	 * @Description: TODO(删除该用户所有输入的线路)
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-15 上午11:49:41
	 */
	@RequestMapping( value = "/deleteall.do" )
	public String deleteAll( HttpServletRequest request ,
	        RedirectAttributes attributes )
	{
		String smuser = request.getParameter( "user" );
		prSelfImputLegService.deleteallLegByUser( smuser );
		attributes.addFlashAttribute( "typeId" , "1" );// 1表示自己输入
		return "redirect:/view.do";
		
	}

	public List< String > getStartProvinceList()
	{
		return startProvinceList;
	}
	
	public void setStartProvinceList( List< String > startProvinceList )
	{
		this.startProvinceList = startProvinceList;
	}



}
