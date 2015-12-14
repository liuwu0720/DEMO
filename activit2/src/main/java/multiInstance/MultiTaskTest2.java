package multiInstance;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/** 
 * @Package multiInstance 
 * @Description:串行会签：顺序要求
 * @author liuwu
 * @date 2015年11月11日 下午6:21:03 
 * @version V1.0 
 */
public class MultiTaskTest2
{
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**部署流程定义（从inputStream）*/
	@Test
	public void deploymentProcessDefinition_inputStream()
	{
		InputStream inputStreamBpmn = this.getClass().getResourceAsStream(
		        "multiTask2.bpmn" );
		InputStream inputStreamPng = this.getClass().getResourceAsStream(
		        "multiTask2.png" );
		Deployment deployment = processEngine.getRepositoryService()// 与流程定义和部署对象相关的Service
		        .createDeployment()// 创建一个部署对象
		        .name( "串行流程会签" )// 添加部署的名称
		        .addInputStream( "multiTask2.bpmn" , inputStreamBpmn )//
		        .addInputStream( "multiTask2.png" , inputStreamPng )//
		        .deploy();// 完成部署
		System.out.println( "部署ID：" + deployment.getId() );//
		System.out.println( "部署名称：" + deployment.getName() );//
	}
	
	/**启动流程实例*/
	@Test
	public void startProcessInstance()
	{
		// 流程定义的key
		String processDefinitionKey = "costtask";
		
		Map< String , Object > processInstVar = new HashMap< String , Object >();
		String[] assigneeList = { "hjx" , "liuwu" , "cwz" };
		String[] assigneeList2 = { "hjx2" , "liuwu2" , "cwz2" };
		// 必须是List
		processInstVar.put( "deptleaders" , Arrays.asList( assigneeList ) );
		processInstVar.put( "acountleaders" , Arrays.asList( assigneeList2 ) );
		processInstVar.put( "needboss" , 0 );
		processInstVar.put( "input" , "liuwu" );
		
		ProcessInstance pi = processEngine.getRuntimeService()// 与正在执行的流程实例和执行对象相关的Service
		        // .startProcessInstanceByKey(processDefinitionKey);//使用流程定义的key启动流程实例，key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
		        .startProcessInstanceByKey( processDefinitionKey , processInstVar );
		
		System.out.println( "流程实例ID:" + pi.getId() );// 流程实例ID 101
		System.out.println( "流程定义ID:" + pi.getProcessDefinitionId() );// 流程定义ID
		                                                              // helloworld:1:4
	}
	
	/**完成我的任务*/
	@Test
	public void completeMyPersonalTask()
	{
		// 任务ID
		String taskId = "38608";
		Map< String , Object > vaMap = new HashMap<String , Object>();
		vaMap.put( "deptflag" , "1" );
		vaMap.put( "outcome" , "同意" );
		
		processEngine.getTaskService()// 与正在执行的任务管理相关的Service
		        .complete( taskId ,vaMap);
		
		System.out.println( "完成任务：任务ID：" + taskId );
	}
}
