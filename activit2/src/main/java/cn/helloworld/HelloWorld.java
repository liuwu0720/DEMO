package cn.helloworld;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;



/** 
 * @Package cn.helloworld 
 * @Description:用一句话描述该文件做什么 
 * @author hjx 
 * @date 2015年10月17日 下午4:39:06 
 * @version V1.0 
 */
public class HelloWorld
{
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 部署流程定义
	 */
	@Test
	public void deploymentProcessDefinition()
	{
		Deployment deployment = processEngine.getRepositoryService().createDeployment()// 与流程定义和部署对象相关的service
		        .name( "helloworld入门程序" )// 添加部署名称
		        .addClasspathResource( "diagrams/hellowrold.bpmn" )// 加载资源
		        .addClasspathResource( "diagrams/hellowrold.png" )// 加载资源
		        .deploy();// 完成部署
		System.out.println( deployment.getId() );// 部署ID
		System.out.println( deployment.getName() );// 部署名称
	}
	
	/**
	 * 启动流程实例 使用key启动默认是按照最新版本的流程定义启动
	 */
	@Test
	public void startProcessInstance()
	{
		ProcessInstance p1 = processEngine.getRuntimeService()// 与正在执行的流程实例和执行对象相关的service
		        .startProcessInstanceByKey( "hellowrold" );// 使用流程定义的key
														   // key=bpmn文件中ID值
		System.out.println( "流程实例ID " + p1.getId() );// 流程实例ID
		System.out.println( "流程定义ID" + p1.getProcessDefinitionId() );// 流程定义ID
		
	}
	
	/**
	 *查询当前人的个人任务
	 */
	@Test
	public void findMyProcess()
	{
		String assignee = "王五";
		
		List< Task > list = processEngine.getTaskService()// 与正在执行的任务管理相关的service
		        .createTaskQuery()// 创建查询对象
		        .taskAssignee( assignee )// 指定个人任务查询，指定办理人
		        .list();
		if(list !=null && list.size()>0){
			for(Task task:list){
				System.out.println("id"+task.getId());
				System.out.println("任务名称："+task.getName());
				System.out.println("time:"+task.getCreateTime());
				System.out.println("办理人："+task.getAssignee());
				System.out.println("流程实例ID"+task.getProcessInstanceId());
				System.out.println("执行对象ID:"+task.getExecutionId());
				System.out.println("流程定义ID"+task.getProcessDefinitionId());
				
			}
		}
	}
	
	/**
	 * 完成我的任务
	 */
	@Test
	public void completeMyTask(){
		String taskIdString = "402";
		processEngine.getTaskService()//与正在执行的任务管理相关的service
					.complete( taskIdString );	
		System.out.println("完成任务：任务ID"+taskIdString);
		System.out.println("完成任务：任务ID"+taskIdString);
	}
	
	
	
}
