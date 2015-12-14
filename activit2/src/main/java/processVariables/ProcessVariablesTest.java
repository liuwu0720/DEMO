package processVariables;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;


/** 
 * @Package processVariables 
 * @Description:流程变量 
 * @author hjx 
 * @date 2015年10月18日 下午3:21:16 
 * @version V1.0 
 */
public class ProcessVariablesTest
{	
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	/**
	 * 部署流程定义
	 */
	@Test
	public void deploymentProcessDefinitionAsStream()
	{
		InputStream inputStreamBpmn = this.getClass().getResourceAsStream( "/diagrams/processVariables.bpmn" );
		InputStream inputStreamPng= this.getClass().getResourceAsStream( "/diagrams/processVariables.png" );
		Deployment deployment = processEngine.getRepositoryService().createDeployment()// 与流程定义和部署对象相关的service
		        .name( "流程变量" )// 添加部署名称
		        .addInputStream( "processVariables.bpmn" , inputStreamBpmn )
		        .addInputStream( "processVariables.png" , inputStreamPng )
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
		        .startProcessInstanceByKey( "processVariables" );// 使用流程定义的key
		                                                   // key=bpmn文件中ID值
		System.out.println( "流程实例ID " + p1.getId() );// 流程实例ID
		System.out.println( "流程定义ID" + p1.getProcessDefinitionId() );// 流程定义ID
		
	}
	/**
	 * 设置流程变量
	 */
	@Test
	public void setVariables(){
		TaskService taskService = processEngine.getTaskService();
		String taskId = "1204";
		String variableName="请假天数";
				
		taskService.setVariable( taskId , variableName , 3 );
		taskService.setVariable( taskId , "请假日期" , new Date() );
		taskService.setVariable( taskId , "请假原因" ,"回家探亲");
		System.out.println("设置成功！！！");
	}
	
	/**
	 * 获取流程变量
	 */
	@Test
	public void getVariables(){
		TaskService taskService = processEngine.getTaskService();
		String taskId = "1602";
		
		Integer daysInteger =  ( Integer ) taskService.getVariable( taskId , "请假天数"  );
		Date date =  ( Date ) taskService.getVariable( taskId , "请假日期"  );
		String reasonString =  ( String ) taskService.getVariable( taskId , "请假原因");
		System.out.println("请假天数   "+daysInteger);
		System.out.println("日期："+date);
		System.out.println("请假原因："+reasonString);
	}
	/**
	 * 模拟设置和获取流程变量的场景
	 */
	public void setAndGetVaribles(){
		//与流程实例，执行对象
		RuntimeService runtimeService = processEngine.getRuntimeService();
		TaskService taskService = processEngine.getTaskService();
		/**
		 * 设置变量
		 */
		//runtimeService.setVariable( executionId , variableName , value );//表示使用执行对象ID
		//runtimeService.setVariables( executionId , variables );//表示使用执行对象ID和MAP集合
	    //taskService.setVariable( taskId , variableName , value );
	    // taskService.setVariables( taskId , variables );
		//runtimeService.startProcessInstanceByKey( processDefinitionKey,variables )//启动流程实例的同时设置流程变量
		//taskService.complete( taskId , variables );完成任务的同时，设置流程变量
		
		/**
		 * 获取流程变量
		 */
		//runtimeService.getVariable( executionId , variableName );//使用执行对象ID和流程变量名称 获取流程变量的值
		//runtimeService.getVariables( executionId );//使用执行对象ID 获取所有流程变量 将流程变量旋转在MAP集合里面 KEY就是流程变量的名称
		//runtimeService.getVariables( executionId , variableNames );//使用执行对象ID，
		
		
	}
	
	
	
	/**
	 * 完成我的任务
	 */
	@Test
	public void completeMyTask()
	{
		String taskIdString = "1004";
		processEngine.getTaskService()// 与正在执行的任务管理相关的service
		        .complete( taskIdString );
		System.out.println( "完成任务：任务ID " + taskIdString );
		
	}
	
	/**查询流程变量历史表**/
	@Test
	public void findHistoryProcessVariables(){
		List< HistoricVariableInstance > list  =  processEngine.getHistoryService()
					 .createHistoricVariableInstanceQuery()
					 .variableName( "请假天数" )
					 .list();
		
		if(list!=null && list.size()>0){
			for(HistoricVariableInstance hv:list){
				System.out.println(" "+hv.getVariableName());
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
}
