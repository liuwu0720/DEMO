package processInstance;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/** 
 * @Package processInstance 
 * @Description:用一句话描述该文件做什么 
 * @author hjx 
 * @date 2015年10月18日 上午11:17:20 
 * @version V1.0 
 */
public class ProcessInstanceTest
{
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
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
		        // .taskCandidateUser( candidateUser );//组任务的办理人查询
		        // .processDefinitionId( processDefinitionId );//使用流程定义ID查询
		        // .processInstanceId( processInstanceId );//使用流程实例ID查询
		        // .executionId( executionId );//使用执行对象ID查询
		        .list();
		if ( list != null && list.size() > 0 )
		{
			for ( Task task : list )
			{
				System.out.println( "id" + task.getId() );
				System.out.println( "任务名称：" + task.getName() );
				System.out.println( "time:" + task.getCreateTime() );
				System.out.println( "办理人：" + task.getAssignee() );
				System.out.println( "流程实例ID" + task.getProcessInstanceId() );
				System.out.println( "执行对象ID:" + task.getExecutionId() );
				System.out.println( "流程定义ID" + task.getProcessDefinitionId() );
				
			}
		}
	}
	
	/**
	 * 完成我的任务
	 */
	@Test
	public void completeMyTask()
	{
		String taskIdString = "2703";
		processEngine.getTaskService()// 与正在执行的任务管理相关的service
		        .complete( taskIdString );
		System.out.println( "完成任务：任务ID" + taskIdString );
	}
	
	/**
	 * 查询流程状态（判断正在执行还是结束）
	 */
	@Test
	public void isProcessEnd()
	{
		String processInstanceId = "201";
		ProcessInstance pInstance = processEngine.getRuntimeService() // 正在执行的流程实例和执行对象
		        .createProcessInstanceQuery()// 创建流程实例查询
		        .processInstanceId( processInstanceId )// 使用流程实例ID查询
		        .singleResult();
		if(pInstance == null){
			System.out.println("流程已结束");
		}else {
			System.out.println("流程没有结束！");
		}
		
	}
	/**
	 * 查询历史任务
	 */
	@Test
	public void findHistoryTask(){
		String taskAssignee = "张三";
		
		List< HistoricTaskInstance > list =  processEngine.getHistoryService()//与历史数据相关的service
					  .createHistoricTaskInstanceQuery()//创建历史查询实例
					  .taskAssignee( taskAssignee )//指令历史任务的办理人
					  .list();
		if(list!=null && list.size()>0){
			for(HistoricTaskInstance hs:list){
				System.out.println(hs.getId()+"  "+hs.getName()+"  "+hs.getProcessInstanceId());
				System.out.println("**************************");
			}
		}
	}
	/**
	 * 查询历史流程实例
	 */
	@Test
	public void findHistoryProcessInstance(){
		String processInstanceId = "1001";
		HistoricProcessInstance hp =  processEngine.getHistoryService()
					.createHistoricProcessInstanceQuery()//历史流程实例
					.processInstanceId( processInstanceId )
					.singleResult();
		System.out.println(hp.getId()+"  "+hp.getProcessDefinitionId()+"  "+hp.getStartTime());
	}
	
}
