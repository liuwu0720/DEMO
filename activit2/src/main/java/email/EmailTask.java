package email;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;


/** 
 * @Package email 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年11月6日 上午11:41:17 
 * @version V1.0 
 */
public class EmailTask
{	
ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**部署流程定义（从inputStream）*/
	@Test
	public void deploymentProcessDefinition_inputStream()
	{
		InputStream inputStreamBpmn = this.getClass().getResourceAsStream(
		        "contractemail.bpmn" );
		InputStream inputStreamPng = this.getClass()
		        .getResourceAsStream( "contractemail.png" );
		Deployment deployment = processEngine.getRepositoryService()// 与流程定义和部署对象相关的Service
		        .createDeployment()// 创建一个部署对象
		        .name( "邮件发送" )// 添加部署的名称
		        .addInputStream( "contractemail.bpmn" , inputStreamBpmn )//
		        .addInputStream( "contractemail.png" , inputStreamPng )//
		        .deploy();// 完成部署
		System.out.println( "部署ID：" + deployment.getId() );//
		System.out.println( "部署名称：" + deployment.getName() );//
	}
	
	/**启动流程实例*/
	@Test
	public void startProcessInstance()
	{
		// 流程定义的key
		String processDefinitionKey = "email";
		Map< String , Object > variables = new HashMap<>();
		Map< String , Object > map = new HashMap<>();
	
		/*String[] valist = { "liuwu@unlcn.com" , "zhangyq@unlcn.com" };
		map.put( "k" , Arrays.asList( valist )  );
		map.put( "v" , Arrays.asList( valist )  );
		variables.put( "touserList" , map  );*/
		User user = new User();
		user.setLinkContent("<a href='www.unlcn.com/oa'>登录</a>" );
		user.setUserEmail("liuwu@unlcn.com");
		
		User user2 = new User();
		user2.setLinkContent("zhangyq@unlcn.com" );
		user2.setUserEmail("zhangyq@unlcn.com");
		List< User > list = new ArrayList<User>();
		
		list.add( user );
		list.add( user2 );
		variables.put( "touserList" ,list  );
		
		ProcessInstance pi = processEngine.getRuntimeService()// 与正在执行的流程实例和执行对象相关的Service
		        // .startProcessInstanceByKey(processDefinitionKey);//使用流程定义的key启动流程实例，key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
				.startProcessInstanceByKey( processDefinitionKey ,variables);
		
		System.out.println( "流程实例ID:" + pi.getId() );// 流程实例ID 101
		System.out.println( "流程定义ID:" + pi.getProcessDefinitionId() );// 流程定义ID
		                                                              // helloworld:1:4
	}
	
}
