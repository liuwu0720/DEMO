package sequenceFlow;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/** 
 * @Package sequenceFlow 
 * @Description:用一句话描述该文件做什么 
 * @author hjx 
 * @date 2015年10月19日 上午9:25:43 
 * @version V1.0 
 */
public class SequenceFlowTest2
{	
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	/**
	 * 部署流程定义
	 */
	@Test
	public void deploymentProcessDefinitionAsStream()
	{
		InputStream inputStreamBpmn = this.getClass().getResourceAsStream( "/diagrams/SequenceFlowTest.bpmn" );
		InputStream inputStreamPng= this.getClass().getResourceAsStream( "/diagrams/SequenceFlowTest.png" );
		Deployment deployment = processEngine.getRepositoryService().createDeployment()// 与流程定义和部署对象相关的service
		        .name( "连线" )// 添加部署名称
		        .addInputStream( "SequenceFlowTest.bpmn" , inputStreamBpmn )
		        .addInputStream( "SequenceFlowTest.png" , inputStreamPng )
		        .deploy();
		        
		System.out.println( "部署ID " + deployment.getId() );// 部署ID
		System.out.println( " 部署名称 " + deployment.getName() );// 部署名称
	}
	
	/**
	 * 启动流程实例 使用key启动默认是按照最新版本的流程定义启动
	 */
	@Test
	public void startProcessInstance()
	{
		ProcessInstance p1 = processEngine.getRuntimeService()// 与正在执行的流程实例和执行对象相关的service
		        .startProcessInstanceByKey( "sequenceFlow" );// 使用流程定义的key
		                                                   // key=bpmn文件中ID值
		System.out.println( "流程实例ID " + p1.getId() );// 流程实例ID
		System.out.println( "流程定义ID" + p1.getProcessDefinitionId() );// 流程定义ID
		
	}
	/**查询当前人的个人任务*/
	@Test
	public void findMyPersonalTask(){
		String assignee = "赵六";
		List<Task> list = processEngine.getTaskService()//与正在执行的任务管理相关的Service
						.createTaskQuery()//创建任务查询对象
						/**查询条件（where部分）*/
						.taskAssignee(assignee)//指定个人任务查询，指定办理人
//						.taskCandidateUser(candidateUser)//组任务的办理人查询
//						.processDefinitionId(processDefinitionId)//使用流程定义ID查询
//						.processInstanceId(processInstanceId)//使用流程实例ID查询
//						.executionId(executionId)//使用执行对象ID查询
						/**排序*/
						.orderByTaskCreateTime().asc()//使用创建时间的升序排列
						/**返回结果集*/
//						.singleResult()//返回惟一结果集
//						.count()//返回结果集的数量
//						.listPage(firstResult, maxResults);//分页查询
						.list();//返回列表
		if(list!=null && list.size()>0){
			for(Task task:list){
				System.out.println("任务ID:"+task.getId());
				System.out.println("任务名称:"+task.getName());
				System.out.println("任务的创建时间:"+task.getCreateTime());
				System.out.println("任务的办理人:"+task.getAssignee());
				System.out.println("流程实例ID："+task.getProcessInstanceId());
				System.out.println("执行对象ID:"+task.getExecutionId());
				System.out.println("流程定义ID:"+task.getProcessDefinitionId());
				System.out.println("########################################################");
			}
		}
	}
	
	/**完成我的任务*/
	@Test
	public void completeMyPersonalTask(){
		//任务ID
		String taskId = "4703";
		//完成任务的同时，设置流程变量，使用流程变量用来指定完成任务后，下一个连线，对应sequenceFlow.bpmn文件中${message=='不重要'}
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("message", "重要");
		processEngine.getTaskService()//与正在执行的任务管理相关的Service
					.complete(taskId,variables);
		System.out.println("完成任务：任务ID："+taskId);
	}
}
