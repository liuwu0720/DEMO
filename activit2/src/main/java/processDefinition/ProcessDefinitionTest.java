package processDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

/** 
 * @Package processDefinition 
 * @Description:用一句话描述该文件做什么 
 * @author hjx 
 * @date 2015年10月18日 上午9:46:50 
 * @version V1.0 
 */
public class ProcessDefinitionTest
{
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 部署流程定义
	 */
	@Test
	public void deploymentProcessDefinition()
	{
		Deployment deployment = processEngine.getRepositoryService().createDeployment()// 与流程定义和部署对象相关的service
		        .name( "流程定义" )// 添加部署名称
		        .addClasspathResource( "diagrams/hellowrold.bpmn" )// 加载资源
		        .addClasspathResource( "diagrams/hellowrold.png" )// 加载资源
		        .deploy();// 完成部署
		System.out.println( "部署ID " + deployment.getId() );// 部署ID
		System.out.println( " 部署名称 " + deployment.getName() );// 部署名称
	}
	

	
	/**
	 * zip部署流程定义
	 */
	@Test
	public void deploymentProcessDefinitionZip()
	{
		InputStream in = this.getClass().getClassLoader()
		        .getResourceAsStream( "diagrams/helloworld.zip" );
		ZipInputStream zipInputStream = new ZipInputStream( in );
		Deployment deployment = processEngine.getRepositoryService().createDeployment()// 与流程定义和部署对象相关的service
		        .name( "流程定义" )// 添加部署名称
		        .addZipInputStream( zipInputStream ).deploy();// 完成部署
		System.out.println( "部署ID " + deployment.getId() );// 部署ID
		System.out.println( " 部署名称 " + deployment.getName() );// 部署名称
	}
	
	/**
	 * 查询流程定义
	 */
	@Test
	public void findProcessDefintion()
	{
		List< ProcessDefinition > list = processEngine.getRepositoryService()//
		        .createProcessDefinitionQuery()// 创建流程定义的查询
		        /**.deploymentId( deploymentId )//使用部署对象ID
		        .processDefinitionId( processDefinitionId );//使用流程定义ID
		        */
		        // .list()//返回一个集合列表，封装流程定义
		        // .singleResult();//唯一结果集
		        // .count();//返回结果集数量
		        // .listPage( firstResult , maxResults );//分布查询
		        .orderByProcessDefinitionVersion().asc()// 按照版本升序排序
		        .list();
		if ( list != null && list.size() > 0 )
		{
			for ( ProcessDefinition pd : list )
			{
				System.out.println( "流程定义id " + pd.getId() );
				System.out.println( "流程定义名称：" + pd.getName() );
				System.out.println( "流程定义key:" + pd.getKey() );
				System.out.println( "流程定义的版本：" + pd.getVersion() );
				System.out.println( "bpnm文件：" + pd.getResourceName() );
				System.out.println( "png文件：" + pd.getDiagramResourceName() );
				System.out.println( "部署对象：" + pd.getDeploymentId() );
				System.out.println( "*****************" );
			}
		}
		
	}
	
	/**
	 * 删除流程定义
	 */
	@Test
	public void deleteProcessDefinition()
	{
		// 使用部署ID删除
		String deploymentId = "701";
		/**
		 * 不带级联的删除，只能删除没有启动的流程，如果流程启动就会异常
		 */
		
		// processEngine.getRepositoryService().deleteDeployment( deploymentId
		// );
		/**
		 * 级联删除
		 */
		
		processEngine.getRepositoryService().deleteDeployment( deploymentId , true );
		
		System.out.println( "删除成功：" );
	}
	
	/**
	 * 查看流程图
	 * @throws IOException 
	 */
	@Test
	public void viewPic() throws IOException
	{
		/**
		 * 将生成的图片放到文件下
		 */
		String deploymentId = "601";
		List< String > list = processEngine.getRepositoryService()
		        .getDeploymentResourceNames( deploymentId );
		// 定义图片资源名称
		String resourceName = "";
		if ( list != null && list.size() > 0 )
		{
			for ( String name : list )
			{
				if ( name.indexOf( ".png" ) >= 0 )
				{
					resourceName = name;
				}
			}
		}
		
		InputStream inputStream = processEngine.getRepositoryService()
		        .getResourceAsStream( deploymentId , resourceName );
		// 将图片生成到D盘目录下
		File file = new File( "D:/" + resourceName );
		FileUtils.copyInputStreamToFile( inputStream , file );
	}
	
	/***查询最新重酬的流程定义 */
	@Test
	public void findLastVersionProcessDefinition()
	{
		List< ProcessDefinition > list = processEngine.getRepositoryService()
		        .createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc()
		        .list();
		
		Map< String , ProcessDefinition > map = new LinkedHashMap< String , ProcessDefinition >();
		if ( list != null && list.size() > 0 )
		{
			for ( ProcessDefinition pd : list )
			{
				map.put( pd.getKey() , pd );
			}
		}
		
		List< ProcessDefinition > pdList = new ArrayList< ProcessDefinition >(
		        map.values() );
		if ( pdList != null && pdList.size() > 0 )
		{
			for ( ProcessDefinition pd : pdList )
			{
				System.out.println( "流程定义id " + pd.getId() );
				System.out.println( "流程定义名称：" + pd.getName() );
				System.out.println( "流程定义key:" + pd.getKey() );
				System.out.println( "流程定义的版本：" + pd.getVersion() );
				System.out.println( "bpnm文件：" + pd.getResourceName() );
				System.out.println( "png文件：" + pd.getDiagramResourceName() );
				System.out.println( "部署对象：" + pd.getDeploymentId() );
				System.out.println( "*****************" );
			}
		}
		
	}
	
	/**
	 * 删除KEY相同不同版本的流程定义
	 */
	@Test
	public void deleteProcessDefintionByKey()
	{
		// 先使用流程定义的key查询流程定义，查询出所有版本
		
		String processDefinitionKey = "helloworld";
		List< ProcessDefinition > list = processEngine.getRepositoryService()
		        .createProcessDefinitionQuery()
		        .processDefinitionKey( processDefinitionKey ).list();
		// 遍历
		if ( list != null && list.size() > 0 )
		{
			for ( ProcessDefinition pd : list )
			{
				// 获取部署ID
				String deploymentId = pd.getDeploymentId();
				processEngine.getRepositoryService().deleteDeployment( deploymentId ,
				        true );
				
			}
		}
		
	}
	
}
