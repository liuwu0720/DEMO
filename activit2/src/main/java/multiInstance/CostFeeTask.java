package multiInstance;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/** 
 * @Package multiInstance 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年11月17日 上午10:29:59 
 * @version V1.0 
 */
public class CostFeeTask
{
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**部署流程定义（从inputStream）*/
	@Test
	public void deploymentProcessDefinition_inputStream()
	{
		InputStream inputStreamBpmn = this.getClass()
		        .getResourceAsStream( "costfee.bpmn" );
		InputStream inputStreamPng = this.getClass().getResourceAsStream( "costfee.png" );
		Deployment deployment = processEngine.getRepositoryService()// 与流程定义和部署对象相关的Service
		        .createDeployment()// 创建一个部署对象
		        .name( "费用审批" )// 添加部署的名称
		        .addInputStream( "costfee.bpmn" , inputStreamBpmn )//
		        .addInputStream( "costfee.png" , inputStreamPng )//
		        .deploy();// 完成部署
		System.out.println( "部署ID：" + deployment.getId() );//
		System.out.println( "部署名称：" + deployment.getName() );//
		
		/**添加用户角色组*/
		IdentityService identityService = processEngine.getIdentityService();//
		int size = identityService.createGroupQuery().groupId( "总经理" ).list().size();
		System.out.println("size = "+size);
		//创建角色
		//identityService.saveGroup(new GroupEntity("财务总裁"));
		//创建用户
		identityService.saveUser(new UserEntity("zhuli"));
		identityService.saveUser(new UserEntity("yanyq"));
		//建立用户和角色的关联关系
		identityService.createMembership("zhuli", "财务总裁");
		identityService.createMembership("yanyq", "财务总裁");
		System.out.println("添加组织机构成功");
	}
	
	/**启动流程实例*/
	@Test
	public void startProcessInstance()
	{
		// 流程定义的key
		String processDefinitionKey = "costfeetask";
		
		Map< String , Object > processInstVar = new HashMap< String , Object >();
		String[] assigneeList = { "hjx"  };
		String[] assigneeList2 = { "hjx2"};
		// 必须是List
		processInstVar.put( "deptleaders" , Arrays.asList( assigneeList ) );
		processInstVar.put( "deptfinanleaders" , Arrays.asList( assigneeList2 ) );
		processInstVar.put( "needboss" ,0 );
		processInstVar.put( "needboss2" , 0 );
		processInstVar.put( "inputuser" , "liuwu" );
		
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
		String taskId = "43808";
		Map< String , Object > vaMap = new HashMap<String , Object>();
		vaMap.put( "deptflag" , "1" );
		vaMap.put( "outcome" , "同意" );
		
		processEngine.getTaskService()// 与正在执行的任务管理相关的Service
		        .complete( taskId ,vaMap);
		
		System.out.println( "完成任务：任务ID：" + taskId );
	}
	
	/**查询当前人的个人任务*/
	@Test
	public void findMyPersonalTask(){
		String assignee = "zhuli";
		List<Task> list = processEngine.getTaskService()//与正在执行的任务管理相关的Service
						.createTaskQuery()//创建任务查询对象
						/**查询条件（where部分）*/
						.taskAssignee(assignee)//指定个人任务查询，指定办理人
//						.taskCandidateUser(candidateUser)//组任务的办理人查询
						/**排序*/
						.orderByTaskCreateTime().asc()//使用创建时间的升序排列
						/**返回结果集*/
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
	
	/**查询当前人的组任务*/
	@Test
	public void findMyGroupTask(){
		String candidateUser = "zhuli";
		List<Task> list = processEngine.getTaskService()//与正在执行的任务管理相关的Service
						.createTaskQuery()//创建任务查询对象
						/**查询条件（where部分）*/
						.taskCandidateUser(candidateUser)//组任务的办理人查询
						/**排序*/
						.orderByTaskCreateTime().asc()//使用创建时间的升序排列
						/**返回结果集*/
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
}
