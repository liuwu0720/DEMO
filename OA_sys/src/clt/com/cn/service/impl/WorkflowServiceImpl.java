package clt.com.cn.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;

import clt.com.cn.controller.workflow.WorkflowBean;
import clt.com.cn.dao.IContractApprovalDao;
import clt.com.cn.dao.IContractDao;
import clt.com.cn.dao.IContracttypePositionReDao;
import clt.com.cn.dao.ICostApplyDao;
import clt.com.cn.dao.ICostAuditRecordDao;
import clt.com.cn.dao.ICostBudgetDao;
import clt.com.cn.dao.ICosttypeDao;
import clt.com.cn.dao.IDeptDao;
import clt.com.cn.dao.IDeptFinanciaReDao;
import clt.com.cn.dao.IDeptLevelDao;
import clt.com.cn.dao.IDeptTotalCostDao;
import clt.com.cn.dao.IEmployrecordDao;
import clt.com.cn.dao.IFeeReLevelDao;
import clt.com.cn.model.entity.Contract;
import clt.com.cn.model.entity.ContractApproval;
import clt.com.cn.model.entity.CostApply;
import clt.com.cn.model.entity.CostAuditrecord;
import clt.com.cn.model.entity.CostBudget;
import clt.com.cn.model.entity.Costtype;
import clt.com.cn.model.entity.Dept;
import clt.com.cn.model.entity.DeptFinanciaRe;
import clt.com.cn.model.entity.DeptLevel;
import clt.com.cn.model.entity.DeptTotalcost;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.FeeReLevel;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.model.entity.SmuserToken;
import clt.com.cn.model.entity.UserEmail;
import clt.com.cn.service.IUserService;
import clt.com.cn.service.IWorkflowService;
import clt.com.cn.util.SystemConstance;

public class WorkflowServiceImpl implements IWorkflowService
{
	/**请假申请Dao*/
	
	private RepositoryService repositoryService;
	
	private RuntimeService runtimeService;
	
	private TaskService taskService;
	
	private FormService formService;
	
	private HistoryService historyService;
	
	private IUserService iUserService;
	
	private IContracttypePositionReDao iContracttypePositionReDao;
	
	private IEmployrecordDao iEmployrecordDao;
	
	private IContractDao iContractDao;
	
	private IContractApprovalDao iContractApprovalDao;
	
	private ICosttypeDao iCosttypeDao;
	
	private IDeptLevelDao iDeptLevelDao;
	
	private ICostBudgetDao iCostBudgetDao;
	
	private IDeptTotalCostDao iDeptTotalCostDao;
	
	private IDeptDao iDeptDao;
	
	private IFeeReLevelDao iFeeReLevelDao;
	
	private IDeptFinanciaReDao iDeptFinanciaReDao;
	
	private ICostApplyDao iCostApplyDao;
	
	private ICostAuditRecordDao iCostAuditRecordDao;
	
	/**
	 * @return the iCostAuditRecordDao
	 */
	public ICostAuditRecordDao getiCostAuditRecordDao()
	{
		return iCostAuditRecordDao;
	}
	
	/**
	 * @param iCostAuditRecordDao the iCostAuditRecordDao to set
	 */
	public void setiCostAuditRecordDao( ICostAuditRecordDao iCostAuditRecordDao )
	{
		this.iCostAuditRecordDao = iCostAuditRecordDao;
	}
	
	/**
	 * @return the iCostApplyDao
	 */
	public ICostApplyDao getiCostApplyDao()
	{
		return iCostApplyDao;
	}
	
	/**
	 * @param iCostApplyDao the iCostApplyDao to set
	 */
	public void setiCostApplyDao( ICostApplyDao iCostApplyDao )
	{
		this.iCostApplyDao = iCostApplyDao;
	}
	
	/**
	 * @return the iDeptFinanciaReDao
	 */
	public IDeptFinanciaReDao getiDeptFinanciaReDao()
	{
		return iDeptFinanciaReDao;
	}
	
	/**
	 * @param iDeptFinanciaReDao the iDeptFinanciaReDao to set
	 */
	public void setiDeptFinanciaReDao( IDeptFinanciaReDao iDeptFinanciaReDao )
	{
		this.iDeptFinanciaReDao = iDeptFinanciaReDao;
	}
	
	/**
	 * @return the iFeeReLevelDao
	 */
	public IFeeReLevelDao getiFeeReLevelDao()
	{
		return iFeeReLevelDao;
	}
	
	/**
	 * @param iFeeReLevelDao the iFeeReLevelDao to set
	 */
	public void setiFeeReLevelDao( IFeeReLevelDao iFeeReLevelDao )
	{
		this.iFeeReLevelDao = iFeeReLevelDao;
	}
	
	/**
	 * @return the iDeptDao
	 */
	public IDeptDao getiDeptDao()
	{
		return iDeptDao;
	}
	
	/**
	 * @param iDeptDao the iDeptDao to set
	 */
	public void setiDeptDao( IDeptDao iDeptDao )
	{
		this.iDeptDao = iDeptDao;
	}
	
	/**
	 * @return the iDeptTotalCostDao
	 */
	public IDeptTotalCostDao getiDeptTotalCostDao()
	{
		return iDeptTotalCostDao;
	}
	
	/**
	 * @param iDeptTotalCostDao the iDeptTotalCostDao to set
	 */
	public void setiDeptTotalCostDao( IDeptTotalCostDao iDeptTotalCostDao )
	{
		this.iDeptTotalCostDao = iDeptTotalCostDao;
	}
	
	/**
	 * @return the iCostBudgetDao
	 */
	public ICostBudgetDao getiCostBudgetDao()
	{
		return iCostBudgetDao;
	}
	
	/**
	 * @param iCostBudgetDao the iCostBudgetDao to set
	 */
	public void setiCostBudgetDao( ICostBudgetDao iCostBudgetDao )
	{
		this.iCostBudgetDao = iCostBudgetDao;
	}
	
	/**
	 * @return the iDeptLevelDao
	 */
	public IDeptLevelDao getiDeptLevelDao()
	{
		return iDeptLevelDao;
	}
	
	/**
	 * @param iDeptLevelDao the iDeptLevelDao to set
	 */
	public void setiDeptLevelDao( IDeptLevelDao iDeptLevelDao )
	{
		this.iDeptLevelDao = iDeptLevelDao;
	}
	
	/**
	 * @return the iCosttypeDao
	 */
	public ICosttypeDao getiCosttypeDao()
	{
		return iCosttypeDao;
	}
	
	/**
	 * @param iCosttypeDao the iCosttypeDao to set
	 */
	public void setiCosttypeDao( ICosttypeDao iCosttypeDao )
	{
		this.iCosttypeDao = iCosttypeDao;
	}
	
	/**
	 * @return the iUserService
	 */
	public IUserService getiUserService()
	{
		return iUserService;
	}
	
	/**
	 * @param iUserService the iUserService to set
	 */
	public void setiUserService( IUserService iUserService )
	{
		this.iUserService = iUserService;
	}
	
	/**
	 * @return the iContractApprovalDao
	 */
	public IContractApprovalDao getiContractApprovalDao()
	{
		return iContractApprovalDao;
	}
	
	/**
	 * @param iContractApprovalDao the iContractApprovalDao to set
	 */
	public void setiContractApprovalDao( IContractApprovalDao iContractApprovalDao )
	{
		this.iContractApprovalDao = iContractApprovalDao;
	}
	
	/**
	 * @return the iContractDao
	 */
	public IContractDao getiContractDao()
	{
		return iContractDao;
	}
	
	/**
	 * @param iContractDao the iContractDao to set
	 */
	public void setiContractDao( IContractDao iContractDao )
	{
		this.iContractDao = iContractDao;
	}
	
	/**
	 * @return the iEmployrecordDao
	 */
	public IEmployrecordDao getiEmployrecordDao()
	{
		return iEmployrecordDao;
	}
	
	/**
	 * @param iEmployrecordDao the iEmployrecordDao to set
	 */
	public void setiEmployrecordDao( IEmployrecordDao iEmployrecordDao )
	{
		this.iEmployrecordDao = iEmployrecordDao;
	}
	
	/**
	 * @return the iContracttypePositionReDao
	 */
	public IContracttypePositionReDao getiContracttypePositionReDao()
	{
		return iContracttypePositionReDao;
	}
	
	/**
	 * @param iContracttypePositionReDao the iContracttypePositionReDao to set
	 */
	public void setiContracttypePositionReDao(
	        IContracttypePositionReDao iContracttypePositionReDao )
	{
		this.iContracttypePositionReDao = iContracttypePositionReDao;
	}
	
	public void setHistoryService( HistoryService historyService )
	{
		this.historyService = historyService;
	}
	
	public void setFormService( FormService formService )
	{
		this.formService = formService;
	}
	
	public void setRuntimeService( RuntimeService runtimeService )
	{
		this.runtimeService = runtimeService;
	}
	
	public void setTaskService( TaskService taskService )
	{
		this.taskService = taskService;
	}
	
	public void setRepositoryService( RepositoryService repositoryService )
	{
		this.repositoryService = repositoryService;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param file
	 * @param filename 
	 * @author liuwu
	 * @create_date 2015年10月21日 下午3:12:53
	 */
	public void saveNewDeploye( File file , String filename )
	{
		try
		{
			// 2：将File类型的文件转化成ZipInputStream流
			ZipInputStream zipInputStream = new ZipInputStream(
			        new FileInputStream( file ) );
			repositoryService.createDeployment()// 创建部署对象
			        .name( filename )// 添加部署名称
			        .addZipInputStream( zipInputStream )//
			        .deploy();// 完成部署
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @return 
	 * @author liuwu
	 * @create_date 2015年10月21日 下午3:12:53
	 */
	public List< Deployment > findDeploymentList()
	{
		List< Deployment > list = repositoryService.createDeploymentQuery()// 创建部署对象查询
		        .orderByDeploymenTime().asc()//
		        .list();
		return list;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @return 
	 * @author liuwu
	 * @create_date 2015年10月21日 下午3:12:53
	 */
	public List< ProcessDefinition > findProcessDefinitionList()
	{
		List< ProcessDefinition > list = repositoryService.createProcessDefinitionQuery()// 创建流程定义查询
		        .orderByProcessDefinitionVersion().asc()//
		        .list();
		return list;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param deploymentId
	 * @param imageName
	 * @return 
	 * @author liuwu
	 * @create_date 2015年10月21日 下午3:12:53
	 */
	public InputStream findImageInputStream( String deploymentId , String imageName )
	{
		// TODO Auto-generated method stub
		return repositoryService.getResourceAsStream( deploymentId , imageName );
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param deploymentId 
	 * @author liuwu
	 * @create_date 2015年10月21日 下午3:12:53
	 */
	public void deleteProcessDefinitionByDeploymentId( String deploymentId )
	{
		// TODO Auto-generated method stub
		
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param name
	 * @return 
	 * @author liuwu
	 * @create_date 2015年10月21日 下午3:12:53
	 */
	public List< Task > findTaskListByName( String name , String taskName )
	{
		List< Task > list = taskService.createTaskQuery()//
		        .taskAssignee( name )// 指定个人任务查询
		        .taskNameLike( taskName ).orderByTaskCreateTime().desc()//
		        .list();
		/*if(taskName.endsWith( "%合同审批%" )){
			for(Task task:list){
				// 合同详情
				Contract contract = findContractByTaskId( task.getId() );
				task.setDescription( contract.getVcContractno() );
			}
			
		}*/
		return list;
	}
	
	/** 
	 * @Description:合同审批：当前任务
	 * @param name
	 * @param taskName
	 * @return 
	 * @author liuwu
	 * @create_date 2015年12月10日 上午11:22:15
	 */
	public List< Contract > findTaskListByContract( String name , String taskName )
	{
		List< Task > list = taskService.createTaskQuery()//
		        .taskAssignee( name )// 指定个人任务查询
		        .taskNameLike( taskName ).orderByTaskCreateTime().desc()//
		        .list();
		List< Contract > contracts = new ArrayList< Contract >();
		if ( taskName.endsWith( "%合同审批%" ) )
		{
			for ( Task task : list )
			{
				// 合同详情
				Contract contract = findContractByTaskId( task.getId() );
				contract.setVcTaskId( task.getId() );
				contract.setVcTaskName( task.getName() );
				contracts.add( contract );
			}
			
		}
		return contracts;
	}
	
	/** 
	 * @Description:费用审批：当前任务
	 * @param name
	 * @param taskName
	 * @return 
	 * @author liuwu
	 * @create_date 2015年12月12日 上午10:57:02
	 */
	public List< CostApply > findTaskListByCostApply( String name , String taskName )
	{
		List< Task > list = taskService.createTaskQuery()//
		        .taskAssignee( name )// 指定个人任务查询
		        .taskNameLike( taskName ).orderByTaskCreateTime().desc()//
		        .list();
		List< CostApply > costApplies = new ArrayList< CostApply >();
		if ( taskName.endsWith( "%费用审批%" ) )
		{
			for ( Task task : list )
			{
				// 费用详情
				CostApply costApply = findCostApplyByTaskId( task.getId() );
				
				costApply.setVcTaskId( task.getId() );
				costApply.setVcTaskName( task.getName() );
				costApplies.add( costApply );
			}
			
		}
		return costApplies;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param taskId
	 * @return 
	 * @author liuwu
	 * @create_date 2015年10月21日 下午3:12:53
	 */
	public String findTaskFormKeyByTaskId( String taskId )
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param taskId
	 * @return 
	 * @author liuwu
	 * @create_date 2015年10月21日 下午3:12:53
	 */
	public List< String > findOutComeListByTaskId( String taskId )
	{
		// 返回存放连线的名称集合
		List< String > list = new ArrayList< String >();
		// 1:使用任务ID，查询任务对象
		Task task = taskService.createTaskQuery()//
		        .taskId( taskId )// 使用任务ID查询
		        .singleResult();
		// 2：获取流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();
		// 3：查询ProcessDefinitionEntiy对象
		ProcessDefinitionEntity processDefinitionEntity = ( ProcessDefinitionEntity ) repositoryService
		        .getProcessDefinition( processDefinitionId );
		// 使用任务对象Task获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		// 使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
		        .processInstanceId( processInstanceId )// 使用流程实例ID查询
		        .singleResult();
		// 获取当前活动的id
		String activityId = pi.getActivityId();
		if ( StringUtils.isNotBlank( activityId ) )
		{
			// 4：获取当前的活动
			ActivityImpl activityImpl = processDefinitionEntity.findActivity( activityId );
			// 5：获取当前活动完成之后连线的名称
			List< PvmTransition > pvmList = activityImpl.getOutgoingTransitions();
			if ( pvmList != null && pvmList.size() > 0 )
			{
				for ( PvmTransition pvm : pvmList )
				{
					String name = ( String ) pvm.getProperty( "name" );
					if ( StringUtils.isNotBlank( name ) )
					{
						list.add( name );
					}
					else
					{
						list.add( "默认提交" );
					}
				}
			}
		}
		else
		{
			// 会签流程
			list.add( "同意" );
			list.add( "驳回" );
		}
		
		return list;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param taskId
	 * @return 
	 * @author liuwu
	 * @create_date 2015年10月21日 下午3:12:53
	 */
	public List< Comment > findCommentByTaskId( String taskId )
	{
		List< Comment > list = new ArrayList< Comment >();
		// 使用当前的任务ID，查询当前流程对应的历史任务ID
		// 使用当前任务ID，获取当前任务对象
		Task task = taskService.createTaskQuery()//
		        .taskId( taskId )// 使用任务ID查询
		        .singleResult();
		// 获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		// //使用流程实例ID，查询历史任务，获取历史任务对应的每个任务ID
		// List<HistoricTaskInstance> htiList =
		// historyService.createHistoricTaskInstanceQuery()//历史任务表查询
		// .processInstanceId(processInstanceId)//使用流程实例ID查询
		// .list();
		// //遍历集合，获取每个任务ID
		// if(htiList!=null && htiList.size()>0){
		// for(HistoricTaskInstance hti:htiList){
		// //任务ID
		// String htaskId = hti.getId();
		// //获取批注信息
		// List<Comment> taskList =
		// taskService.getTaskComments(htaskId);//对用历史完成后的任务ID
		// list.addAll(taskList);
		// }
		// }
		list = taskService.getProcessInstanceComments( processInstanceId );
		return list;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param taskId
	 * @return 
	 * @author liuwu
	 * @create_date 2015年10月21日 下午3:12:53
	 */
	public ProcessDefinition findProcessDefinitionByTaskId( String taskId )
	{
		// 使用任务ID，查询任务对象
		Task task = taskService.createTaskQuery()//
		        .taskId( taskId )// 使用任务ID查询
		        .singleResult();
		// 获取流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();
		// 查询流程定义的对象
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()// 创建流程定义查询对象，对应表act_re_procdef
		        .processDefinitionId( processDefinitionId )// 使用流程定义ID查询
		        .singleResult();
		return pd;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param taskId
	 * @return 
	 * @author liuwu
	 * @create_date 2015年10月21日 下午3:12:53
	 */
	public Map< String , Object > findCoordingByTask( String taskId )
	{
		// 存放坐标
		Map< String , Object > map = new HashMap< String , Object >();
		// 使用任务ID，查询任务对象
		Task task = taskService.createTaskQuery()//
		        .taskId( taskId )// 使用任务ID查询
		        .singleResult();
		// 获取流程定义的ID
		String processDefinitionId = task.getProcessDefinitionId();
		// 获取流程定义的实体对象（对应.bpmn文件中的数据）
		ProcessDefinitionEntity processDefinitionEntity = ( ProcessDefinitionEntity ) repositoryService
		        .getProcessDefinition( processDefinitionId );
		// excetionId
		String executionId = task.getExecutionId();
		Execution execution = runtimeService.createExecutionQuery()
		        .executionId( executionId ).singleResult();
		Map< String , Object > maps = runtimeService.getVariables( executionId );
		List< String > assignUsers = ( List< String > ) maps.get( "assigneeList" );
		String applyUser = ( String ) maps.get( "applyuser" );
		/*// 流程实例ID
		 String processInstanceId = task.getProcessInstanceId();
		// 使用流程实例ID，查询正在执行的执行对象表，获取当前活动对应的流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()// 创建流程实例查询
		        .processInstanceId( processInstanceId )// 使用流程实例ID查询
		        .singleResult();*/
		
		// 获取当前活动的ID
		String activityId = execution.getActivityId();
		// 获取当前活动对象
		ActivityImpl activityImpl = processDefinitionEntity.findActivity( activityId );// 活动ID
		// 获取坐标
		
		List< String > newAssignUsers = new ArrayList< String >();
		
		for ( String userNo : assignUsers )
		{
			String userName = iEmployrecordDao.getUserNameByUserNo( userNo );
			newAssignUsers.add( userName );
		}
		String newApplyUser = iEmployrecordDao.getUserNameByUserNo( applyUser );
		map.put( "x" , activityImpl.getX() );
		map.put( "y" , activityImpl.getY() );
		map.put( "width" , activityImpl.getWidth() );
		map.put( "height" , activityImpl.getHeight() );
		map.put( "assignUsers" , newAssignUsers );
		map.put( "applyUser" , newApplyUser );
		return map;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param taskId
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月26日 下午2:16:27
	 */
	public Map< String , Object > findCoordingByFeeTask( String taskId )
	{
		// 存放坐标
		Map< String , Object > map = new HashMap< String , Object >();
		// 使用任务ID，查询任务对象
		Task task = taskService.createTaskQuery()//
		        .taskId( taskId )// 使用任务ID查询
		        .singleResult();
		// 获取流程定义的ID
		String processDefinitionId = task.getProcessDefinitionId();
		// 获取流程定义的实体对象（对应.bpmn文件中的数据）
		ProcessDefinitionEntity processDefinitionEntity = ( ProcessDefinitionEntity ) repositoryService
		        .getProcessDefinition( processDefinitionId );
		// excetionId
		String executionId = task.getExecutionId();
		Execution execution = runtimeService.createExecutionQuery()
		        .executionId( executionId ).singleResult();
		Map< String , Object > maps = runtimeService.getVariables( executionId );
		List< String > deptleaders = ( List< String > ) maps.get( "deptleaders" );
		List< String > deptfinanleaders = ( List< String > ) maps
		        .get( "deptfinanleaders" );
		String inputuser = ( String ) maps.get( "inputuser" );
		
		// 获取当前活动的ID
		String activityId = execution.getActivityId();
		// 获取当前活动对象
		ActivityImpl activityImpl = processDefinitionEntity.findActivity( activityId );// 活动ID
		// 获取坐标
		map.put( "x" , activityImpl.getX() );
		map.put( "y" , activityImpl.getY() );
		map.put( "width" , activityImpl.getWidth() );
		map.put( "height" , activityImpl.getHeight() );
		map.put( "deptleaders" , deptleaders );
		map.put( "deptfinanleaders" , deptfinanleaders );
		map.put( "inputuser" , inputuser );
		return map;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用  
	 * @author liuwu
	 * @create_date 2015年10月21日 下午3:48:02
	 */
	public void startFlowTest()
	{
		InputStream inputStreamBpmn = this.getClass().getResourceAsStream(
		        "sequenceFlow.bpmn" );
		InputStream inputStreamPng = this.getClass().getResourceAsStream(
		        "sequenceFlow.png" );
		Deployment deployment = repositoryService// 与流程定义和部署对象相关的Service
		        .createDeployment()// 创建一个部署对象
		        .name( "连线" )// 添加部署的名称
		        .addInputStream( "sequenceFlow.bpmn" , inputStreamBpmn )//
		        .addInputStream( "sequenceFlow.png" , inputStreamPng )//
		        .deploy();// 完成部署
		System.out.println( "部署ID：" + deployment.getId() );//
		System.out.println( "部署名称：" + deployment.getName() );
		
	}
	
	/** 
	 * @Description:启动流程
	 * @param contract
	 * @param userno 
	 * @author liuwu
	 * @create_date 2015年10月27日 下午4:12:37
	 */
	public void startProgress( Contract contract , String userno ,
	        HttpServletRequest request )
	{
		// 1：获取合同ID，使用合同ID，查询请合同
		Integer id = contract.getLineid();
		
		// 2：更新请假单的请假状态从0变成1（初始录入-->审核中）
		contract.setnState( 1 );
		// 3：使用当前对象获取到流程定义的key（对象的名称就是流程定义的key）
		String key = SystemConstance.CONTRACT_FLOWKEY;
		/**
		 * 4：从Session中获取当前任务的办理人，使用流程变量设置下一个任务的办理人
			    * inputUser是流程变量的名称，
			    * 获取的办理人是流程变量的值
		 */
		// int userId = Integer.parseInt( UserSession.get( "userId" ).toString()
		// );
		int userId = Integer.parseInt( request.getSession().getAttribute( "lineid" )
		        .toString() );
		Smuser sm = iUserService.getUserById( userId );
		// 获取对用审批人
		Smuser appuser = iUserService.getUSerByManagerApproval( sm );
		Map< String , Object > variables = new HashMap< String , Object >();
		variables.put( "inputUser" , userno );// 表示惟一用户
		variables.put( "applyuser" , appuser.getUserno() );// 申请部门领导
		setSignPostionbyContractType( contract , variables );// 根据合同类型，设置会签部门
		// 格式：Contract.id的形式（使用流程变量）
		String objId = key + "." + id;
		variables.put( "objId" , objId );
		// 6：使用流程定义的key，启动流程实例，同时设置流程变量，同时向正在执行的执行对象表中的字段BUSINESS_KEY添加业务数据，同时让流程关联业务
		runtimeService.startProcessInstanceByKey( key , objId , variables );
		
	}
	
	/** 
	 * @Description:根据合同类型，设置会签部门
	 * @param contract
	 * @param variables 
	 *   void 返回值描述
	 * @author liuwu
	 * @throws IOException 
	 * @create_date 2015年10月27日 下午6:44:17
	 */
	private void setSignPostionbyContractType( Contract contract ,
	        Map< String , Object > variables )
	{
		
		// 邮件主题
		String contractname = "《" + contract.getVcContractname() + "》";
		variables.put( "contractname" , contractname );
		
		List< String > assigneeList = new ArrayList< String >();// 所有会签人
		// List< UserEmail > userEmails = new ArrayList< UserEmail >();// 接收邮件的人
		
		// 如果是整车收入合同
		if ( contract.getiContracttype().equals( 8 ) )
		{
			// 如果是临时合同：商务管理部、产品规划部、财务管理部
			if ( contract.getnLong().equals( 1 ) )
			{
				assigneeList.add( SystemConstance.BUSINESS_MANAGE );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE2 );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
				
			}
			else
			// 长期合同:商务管理部、产品规划部、总裁助理、 财务管理部、 总裁
			{
				assigneeList.add( SystemConstance.BUSINESS_MANAGE );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE2 );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
				assigneeList.add( SystemConstance.PRESIDENT_ASSISTANT );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
				assigneeList.add( SystemConstance.PRESIDENT );
				
			}
			
		}
		// 零部件合同
		if ( contract.getiContracttype().equals( 9 ) )
		{
			// 如果是与主机厂签订的 :商务管理部、产品规划部、总裁助理、 财务管理部、 总裁
			if ( contract.getnMainsign().equals( 0 ) )
			{
				assigneeList.add( SystemConstance.BUSINESS_MANAGE );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE2 );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
				assigneeList.add( SystemConstance.PRESIDENT_ASSISTANT );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
				assigneeList.add( SystemConstance.PRESIDENT );
				
			}
			else
			// 商务管理部、产品规划部、财务管理部
			{
				assigneeList.add( SystemConstance.BUSINESS_MANAGE );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE2 );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
				
			}
		}
		// 长期运力采购合同 商务管理部、产品规划部
		if ( contract.getiContracttype().equals( 10 ) )
		{
			assigneeList.add( SystemConstance.BUSINESS_MANAGE );
			assigneeList.add( SystemConstance.PRODUCT_MANAGE2 );
			assigneeList.add( SystemConstance.PRODUCT_MANAGE );
			
		}
		// 零部件采购合同 商务管理部、产品规划部、副总裁、 财务管理部、 总裁
		if ( contract.getiContracttype().equals( 17 ) )
		{
			assigneeList.add( SystemConstance.BUSINESS_MANAGE );
			assigneeList.add( SystemConstance.PRODUCT_MANAGE );
			assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
			assigneeList.add( SystemConstance.PRESIDENT_ASSISTANT );
			assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
			assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
			assigneeList.add( SystemConstance.PRESIDENT );
			
		}
		// 汽车类采购合同 商务管理部、产品规划部、副总裁、 财务管理部、 总裁
		if ( contract.getiContracttype().equals( 12 ) )
		{
			assigneeList.add( SystemConstance.BUSINESS_MANAGE );
			assigneeList.add( SystemConstance.PRODUCT_MANAGE2 );
			assigneeList.add( SystemConstance.PRODUCT_MANAGE );
			assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
			assigneeList.add( SystemConstance.PRESIDENT_ASSISTANT );
			assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
			assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
			assigneeList.add( SystemConstance.PRESIDENT );
			
		}
		// 物流器具设备采购合同
		if ( contract.getiContracttype().equals( 13 ) )
		{
			// 单件单价≤5万 商务管理部、产品规划部、副总裁、 财务管理部
			if ( contract.getnPrice().equals( 0.0 ) )
			{
				assigneeList.add( SystemConstance.BUSINESS_MANAGE );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE2 );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
				assigneeList.add( SystemConstance.PRESIDENT_ASSISTANT );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
			}
			// 单件单价>5万 产品规划部、商务管理部、总裁助理、 财务管理部、总裁
			else
			{
				assigneeList.add( SystemConstance.BUSINESS_MANAGE );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE2 );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
				assigneeList.add( SystemConstance.PRESIDENT_ASSISTANT );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
				assigneeList.add( SystemConstance.PRESIDENT );
				
			}
		}
		// 办公设备采购合同
		if ( contract.getiContracttype().equals( 14 ) )
		{
			// 单件单价>5万 商务管理部、、 财务管理部、 总裁
			if ( contract.getnPrice().equals( 1.0 ) )
			{
				assigneeList.add( SystemConstance.BUSINESS_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
				assigneeList.add( SystemConstance.PRESIDENT );
				
			}
			else
			{
				assigneeList.add( SystemConstance.BUSINESS_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
			}
		}
		// 土地租赁合同
		if ( contract.getiContracttype().equals( 15 ) )
		{
			// 年度金额＞50万 商务管理部、产品规划部、总裁助理、 财务管理部、 总裁
			if ( contract.getnPrice().equals( 1.0 ) )
			{
				assigneeList.add( SystemConstance.BUSINESS_MANAGE );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE2 );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
				assigneeList.add( SystemConstance.PRESIDENT_ASSISTANT );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
				assigneeList.add( SystemConstance.PRESIDENT );
				
			}
			else
			{
				assigneeList.add( SystemConstance.BUSINESS_MANAGE );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE2 );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
				assigneeList.add( SystemConstance.PRESIDENT_ASSISTANT );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
			}
		}
		// 房屋租赁合同 商务管理部、产品规划部、总裁助理、 财务管理部
		if ( contract.getiContracttype().equals( 16 ) )
		{
			assigneeList.add( SystemConstance.BUSINESS_MANAGE );
			assigneeList.add( SystemConstance.PRODUCT_MANAGE2 );
			assigneeList.add( SystemConstance.PRODUCT_MANAGE );
			assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
			assigneeList.add( SystemConstance.PRESIDENT_ASSISTANT );
			assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
			assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
			
		}
		// 以项目向下采购合同
		if ( contract.getiContracttype().equals( 18 ) )
		{
			// 年度金额＞20万 商务管理部、产品规划部、总裁助理、 财务管理部、 总裁
			if ( contract.getnPrice().equals( 1.0 ) )
			{
				assigneeList.add( SystemConstance.BUSINESS_MANAGE );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE2 );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
				assigneeList.add( SystemConstance.PRESIDENT_ASSISTANT );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
				assigneeList.add( SystemConstance.PRESIDENT );
				
			}
			else
			// 商务管理部、产品规划部、总裁助理、 财务管理部
			{
				assigneeList.add( SystemConstance.BUSINESS_MANAGE );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE2 );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
				assigneeList.add( SystemConstance.PRESIDENT_ASSISTANT );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
			}
		}
		// 其他类合同
		if ( contract.getiContracttype().equals( 19 ) )
		{
			// 预算内 商务管理部、产品规划部、总裁助理、 财务管理部
			if ( contract.getnExpect().equals( 0 ) )
			{
				assigneeList.add( SystemConstance.BUSINESS_MANAGE );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE2 );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
				assigneeList.add( SystemConstance.PRESIDENT_ASSISTANT );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
				
			}
			else
			// 商务管理部、产品规划部、总裁助理、 财务管理部、 总裁
			{
				assigneeList.add( SystemConstance.BUSINESS_MANAGE );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE2 );
				assigneeList.add( SystemConstance.PRODUCT_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE2 );
				assigneeList.add( SystemConstance.PRESIDENT_ASSISTANT );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE );
				assigneeList.add( SystemConstance.FINANCIAL_MANAGE3 );
				assigneeList.add( SystemConstance.PRESIDENT );
				
			}
		}
		
		if ( assigneeList != null && assigneeList.size() > 0 )
		{
			// variables.put( "tousers" , userEmails );
			// variables.put( "tousers" , toUsersEmailString );
			variables.put( "assigneeList" , assigneeList );
			variables.put( "signCount" , 0 );
		}
		
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @return 
	 *   UserEmail 返回值描述
	 * @author liuwu
	 * @param manage  收件人
	 * @param manageEmail   收件人邮箱地址
	 * @throws IOException 
	 * @create_date 2015年11月10日 上午9:40:15
	 */
	private UserEmail addUserEmail( String manage , String manageEmail )
	{
		UserEmail userEmail = new UserEmail();
		userEmail.setEmailAddress( manageEmail );
		userEmail.setEmailLink( setEmailLink( manage ) );
		return userEmail;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param businessManage
	 * @return 
	 *   String 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月10日 上午10:39:20
	 */
	private String setEmailLink( String businessManage )
	{
		String tokenString = UUID.randomUUID().toString();
		StringBuilder sbR = new StringBuilder();
		sbR.append( SystemConstance.OA_ADDRESS );
		sbR.append( SystemConstance.OA_ADDRESS_CHECKLOGIN );
		sbR.append( "?username=" + businessManage );
		sbR.append( "&token=" + tokenString );
		sbR.append( "&checkType=email" );
		System.out.println( "sbr = " + sbR.toString() );
		String linkString = "<a href='" + sbR.toString() + "' target='_blank'>"
		        + sbR.toString() + "</a>";
		/**
		 * 保存
		 */
		SmuserToken smuserToken = new SmuserToken();
		smuserToken.setVcToken( tokenString );
		smuserToken.setVcUserName( businessManage );
		iUserService.saveUserToken( smuserToken );
		return linkString;
	}
	
	/** 
	 * @Description:根据岗位ID查出对应人
	 * @param lineid
	 * @return 
	 *   String 返回值描述
	 * @author liuwu
	 * @create_date 2015年10月29日 上午11:09:33
	 */
	private String findManageUserByPosition( int lineid )
	{
		String hql = "from Employrecord where positionid = " + lineid;
		List< Employrecord > employrecords = iEmployrecordDao.getEmrInfo( hql , null );
		if ( employrecords != null && employrecords.size() > 0 )
		{
			return employrecords.get( 0 ).getFileno();
		}
		else
		{
			return " ";
		}
		
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param taskId
	 * @return 
	 * @author liuwu
	 * @create_date 2015年10月29日 下午5:16:03
	 */
	public Contract findContractByTaskId( String taskId )
	{
		// 1：使用任务ID，查询任务对象Task
		Task task = taskService.createTaskQuery()//
		        .taskId( taskId )// 使用任务ID查询
		        .singleResult();
		// 2：使用任务对象Task获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		// 3：使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
		        .processInstanceId( processInstanceId )// 使用流程实例ID查询
		        .singleResult();
		// 4：使用流程实例对象获取BUSINESS_KEY
		String buniness_key = pi.getBusinessKey();
		// 5：获取BUSINESS_KEY对应的主键ID，使用主键ID，查询请假单对象（LeaveBill.1）
		String id = "";
		if ( StringUtils.isNotBlank( buniness_key ) )
		{
			// 截取字符串，取buniness_key小数点的第2个值
			id = buniness_key.split( "\\." )[1];
		}
		
		// 查询合同对象
		Contract contract = iContractDao.get( Integer.parseInt( id ) );
		return contract;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param taskId
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月25日 下午3:26:22
	 */
	public CostApply findCostApplyByTaskId( String taskId )
	{
		// 1：使用任务ID，查询任务对象Task
		Task task = taskService.createTaskQuery()//
		        .taskId( taskId )// 使用任务ID查询
		        .singleResult();
		// 2：使用任务对象Task获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		// 3：使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
		        .processInstanceId( processInstanceId )// 使用流程实例ID查询
		        .singleResult();
		// 4：使用流程实例对象获取BUSINESS_KEY
		String buniness_key = pi.getBusinessKey();
		// 5：获取BUSINESS_KEY对应的主键ID，使用主键ID，查询请假单对象（LeaveBill.1）
		String id = "";
		if ( StringUtils.isNotBlank( buniness_key ) )
		{
			// 截取字符串，取buniness_key小数点的第2个值
			id = buniness_key.split( "\\." )[1];
		}
		CostApply costApply = iCostApplyDao.get( Integer.parseInt( id ) );
		return costApply;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param workflowBean 
	 * @author liuwu
	 * @create_date 2015年10月30日 下午1:47:53
	 */
	public void saveSubmitTask( WorkflowBean workflowBean , HttpServletRequest request )
	{
		// 获取任务ID
		String taskId = workflowBean.getTaskId();
		// 获取连线的名称
		String outcome = workflowBean.getOutcome();
		// 批注信息
		String message = workflowBean.getComment();
		// 获取合同ID
		Integer id = workflowBean.getId();
		
		/**
		 * 1：在完成之前，添加一个批注信息，向act_hi_comment表中添加数据，用于记录对当前申请人的一些审核信息
		 */
		// 使用任务ID，查询任务对象，获取流程流程实例ID
		Task task = taskService.createTaskQuery()//
		        .taskId( taskId )// 使用任务ID查询
		        .singleResult();
		// 获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		/**
		 * 注意：添加批注的时候，由于Activiti底层代码是使用：
		 * 		String userId = Authentication.getAuthenticatedUserId();
			    CommentEntity comment = new CommentEntity();
			    comment.setUserId(userId);
			  所有需要从Session中获取当前登录人，作为该任务的办理人（审核人），对应act_hi_comment表中的User_ID的字段，不过不添加审核人，该字段为null
			 所以要求，添加配置执行使用Authentication.setAuthenticatedUserId();添加当前任务的审核人
		 * */
		// String empId = UserSession.get( "empId" ).toString();
		String empId = request.getSession().getAttribute( "recordid" ).toString();
		Employrecord employrecord = iEmployrecordDao
		        .getEmrById( Integer.parseInt( empId ) );
		Authentication.setAuthenticatedUserId( employrecord.getEmployname() );
		message = message + "【" + outcome + "】";
		taskService.addComment( taskId , processInstanceId , message );
		// String userNo = UserSession.get( "uname" ).toString();
		/**
		 * 2：如果连线的名称是“提交申请”，那么就不需要设置，如果不是，就需要设置流程变量
		 * 在完成任务之前，设置流程变量，按照连线的名称，去完成任务
				 流程变量的名称：outcome
				 流程变量的值：连线的名称
		 */
		Map< String , Object > variables = new HashMap< String , Object >();
		if ( outcome != null && ! outcome.equals( "提交申请" ) )
		{
			variables.put( "outcome" , outcome );
		}
		// 如果是会签流程
		List< Task > tasks = taskService.createTaskQuery().taskName( "合同审批:部门会签" )
		        .processInstanceId( processInstanceId ).list();
		// 如果是会签流程
		if ( tasks != null && tasks.size() > 0 )
		{
			List< Task > taskResultList = taskService.createTaskQuery().taskId( taskId )
			        .list();
			// 当前executionId
			String currentExecutionId = taskResultList.get( 0 ).getExecutionId();
			// 当前签署总数
			/*	String currentSignCount = StringUtils.defaultString( runtimeService
				        .getVariable( currentExecutionId , "signCount" ).toString() , "0" );*/
			if ( outcome.equals( "驳回" ) )
			{
				// 签署数+1
				/*runtimeService.setVariable( currentExecutionId , "signCount" ,
				        Integer.parseInt( currentSignCount ) + 1 );*/
				runtimeService.setVariable( currentExecutionId , "deptflag" , 0 );
			}
			else if ( outcome.equals( "同意" ) )
			{
				runtimeService.setVariable( currentExecutionId , "deptflag" , 1 );
			}
			
		}
		Contract contract = iContractDao.get( id );
		if ( contract.getnState().equals( 3 ) )
		{
			contract.setnState( 1 );// 驳回再申请时改为审批中
		}
		
		if ( outcome.equals( "驳回" ) )
		{
			
			contract.setnState( 3 );// 驳回
		}
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd kk:mm:ss" );
		Date now = new Date();
		String resultDate = sdf.format( now );
		
		contract.setVcAudit( resultDate );
		
		// 3：使用任务ID，完成当前人的个人任务，同时流程变量
		taskService.complete( taskId , variables );
		// 4：当任务完成之后，需要指定下一个任务的办理人（使用类）-----已经开发完成
		
		/**
		 * 5：在完成任务之后，判断流程是否结束
			如果流程结束了，更新合同表的状态从1变成2（审核中-->审核完成）
		 */
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
		        .processInstanceId( processInstanceId )// 使用流程实例ID查询
		        .singleResult();
		// 流程结束了
		if ( pi == null )
		{
			// 更新状态从1变成2（审核中-->审核完成）
			contract.setnState( 2 );
		}
		// 保存合同审核记录表
		ContractApproval contractApproval = new ContractApproval();
		contractApproval.setIContract( id );
		// String userId = UserSession.get( "userId" ).toString();
		String userId = request.getSession().getAttribute( "lineid" ).toString();
		contractApproval.setIUser( Integer.parseInt( userId ) );
		contractApproval.setVcApprovalname( employrecord.getEmployname() );
		contractApproval.setVcComment( message );
		contractApproval.setIDept( employrecord.getDept().getLineid() );
		contractApproval.setVcDeptName( employrecord.getDept().getDeptname() );
		contractApproval.setVcNote( task.getName() );
		iContractApprovalDao.save( contractApproval );
		
		List< Task > tList = taskService.createTaskQuery()
		        .processDefinitionKey( SystemConstance.CONTRACT_FLOWKEY )
		        .processInstanceId( processInstanceId ).list();
		/*if ( tList != null && tList.size() > 0 )
		{
			Map< String , Object > emailVaribleMap = new HashMap< String , Object >();
			List< UserEmail > emails = new ArrayList< UserEmail >();
			emails.add( addUserEmail( tList.get( 0 ).getAssignee() , tList.get( 0 )
			        .getAssignee() + "@unlcn.com" ) );
			emailVaribleMap.put( "touserList" , emails );
			
			emailVaribleMap.put( "object" , "合同《" + contract.getVcContractname()
			        + "》需要审批" );
			emailVaribleMap.put( "html" , "合同《" + contract.getVcContractname() + "》已由【"
			        + employrecord.getEmployname() + "】确认,审批意见:" + message
			        + ";<br/>需要您登录OA进行下一步审批，请点击以下链接直接登录，链接24小时后失效" );
			runtimeService.startProcessInstanceByKey(
			        SystemConstance.CONTRACT_EMAIL_FLOWKEY , emailVaribleMap );
		}
		else
		{
			Map< String , Object > emailVaribleMap = new HashMap< String , Object >();
			List< UserEmail > emails = new ArrayList< UserEmail >();
			emails.add( addUserEmail( SystemConstance.CONTRACT_MANAGE ,
			        SystemConstance.CONTRACT_MANAGE_EMAIL ) );
			emailVaribleMap.put( "touserList" , emails );
			
			emailVaribleMap.put( "object" , "合同《" + contract.getVcContractname()
			        + "》审批流程结束" );
			emailVaribleMap.put( "html" , "合同审批流程结束，合同《" + contract.getVcContractname()
			        + "》已由【" + employrecord.getEmployname() + "】确认,审批意见:" + message
			        + ";<br/>需要您登录OA进行下一步审批，请点击以下链接直接登录，链接24小时后失效" );
			runtimeService.startProcessInstanceByKey(
			        SystemConstance.CONTRACT_EMAIL_FLOWKEY , emailVaribleMap );
		}*/
		if ( tList != null && tList.size() > 0 )
		{
			Map< String , Object > emailVaribleMap = new HashMap< String , Object >();
			List< UserEmail > emails = new ArrayList< UserEmail >();
			emails.add( addUserEmail( tList.get( 0 ).getAssignee() ,
			        SystemConstance.CONTRACT_MANAGE_EMAIL ) );
			emailVaribleMap.put( "touserList" , emails );
			
			emailVaribleMap.put( "object" , "合同《" + contract.getVcContractname()
			        + "》需要审批" );
			emailVaribleMap.put( "html" , "合同《" + contract.getVcContractname() + "》已由【"
			        + employrecord.getEmployname() + "】确认,审批意见:" + message
			        + ";<br/>需要您登录OA进行下一步审批，请点击以下链接直接登录，链接24小时后失效" );
			runtimeService.startProcessInstanceByKey(
			        SystemConstance.CONTRACT_EMAIL_FLOWKEY , emailVaribleMap );
		}
		else
		{
			Map< String , Object > emailVaribleMap = new HashMap< String , Object >();
			List< UserEmail > emails = new ArrayList< UserEmail >();
			emails.add( addUserEmail( SystemConstance.CONTRACT_MANAGE ,
			        SystemConstance.CONTRACT_MANAGE_EMAIL ) );
			emailVaribleMap.put( "touserList" , emails );
			
			emailVaribleMap.put( "object" , "合同《" + contract.getVcContractname()
			        + "》审批流程结束" );
			emailVaribleMap.put( "html" , "合同审批流程结束，合同《" + contract.getVcContractname()
			        + "》已由【" + employrecord.getEmployname() + "】确认,审批意见:" + message
			        + ";<br/>需要您登录OA进行下一步审批，请点击以下链接直接登录，链接24小时后失效" );
			runtimeService.startProcessInstanceByKey(
			        SystemConstance.CONTRACT_EMAIL_FLOWKEY , emailVaribleMap );
		}
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param id
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月2日 上午9:50:49
	 */
	public List< Comment > findCommentByContractId( String id )
	{
		int contractId = Integer.parseInt( id );
		// 使用ID，查询对象
		// Contract contract = iContractDao.get( contractId);
		// 获取对象的名称
		String objectName = SystemConstance.CONTRACT_FLOWKEY;
		// 组织流程表中的字段中的值
		String objId = objectName + "." + id;
		
		/**1:使用历史的流程实例查询，返回历史的流程实例对象，获取流程实例ID*/
		// HistoricProcessInstance hpi =
		// historyService.createHistoricProcessInstanceQuery()//对应历史的流程实例表
		// .processInstanceBusinessKey(objId)//使用BusinessKey字段查询
		// .singleResult();
		// //流程实例ID
		// String processInstanceId = hpi.getId();
		/**2:使用历史的流程变量查询，返回历史的流程变量的对象，获取流程实例ID*/
		HistoricVariableInstance hvi = historyService
		        .createHistoricVariableInstanceQuery()// 对应历史的流程变量表
		        .variableValueEquals( "objId" , objId )// 使用流程变量的名称和流程变量的值查询
		        .singleResult();
		// 流程实例ID
		String processInstanceId = hvi.getProcessInstanceId();
		List< Comment > list = taskService.getProcessInstanceComments( processInstanceId );
		return list;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param taskId 
	 * @author liuwu
	 * @create_date 2015年11月4日 上午9:52:38
	 */
	public void deleteTaskByTaskId( String taskId , HttpServletRequest request )
	{
		Task task = taskService.createTaskQuery()//
		        .taskId( taskId )// 使用任务ID查询
		        .singleResult();
		// 获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		
		// 3：使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
		        .processInstanceId( processInstanceId )// 使用流程实例ID查询
		        .singleResult();
		// 4：使用流程实例对象获取BUSINESS_KEY
		String buniness_key = pi.getBusinessKey();
		// 5：获取BUSINESS_KEY对应的主键ID，使用主键ID，查询请假单对象（LeaveBill.1）
		String id = "";
		if ( StringUtils.isNotBlank( buniness_key ) )
		{
			// 截取字符串，取buniness_key小数点的第2个值
			id = buniness_key.split( "\\." )[1];
		}
		CostApply costApply = iCostApplyDao.get( Integer.parseInt( id ) );
		costApply.setnEnable( 1 );
		costApply.setnState( 4 );
		iCostApplyDao.update( costApply );
		
		CostAuditrecord costAuditrecord = new CostAuditrecord();
		costAuditrecord.setiCostapply( costApply.getId() );
		// String userId = UserSession.get( "userId" ).toString();
		// String empId = UserSession.get( "empId" ).toString();
		String userId = request.getSession().getAttribute( "lineid" ).toString();
		String empId = request.getSession().getAttribute( "recordid" ).toString();
		
		Employrecord employrecord = iEmployrecordDao
		        .getEmrById( Integer.parseInt( empId ) );
		costAuditrecord.setiUser( Integer.parseInt( userId ) );
		costAuditrecord.setVcUser( employrecord.getEmployname() );
		costAuditrecord.setVcComment( "删除任务" );
		costAuditrecord.setiDept( employrecord.getDept().getLineid() );
		costAuditrecord.setVcDept( employrecord.getDept().getDeptname() );
		costAuditrecord.setVcNote( task.getName() );
		iCostAuditRecordDao.save( costAuditrecord );
		
		runtimeService.deleteProcessInstance( processInstanceId , null );
		taskService.deleteTask( taskId );
		
	}
	
	/** 
	 * @Description:查询个人的历史任务
	 * @param name
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月5日 下午1:52:15
	 */
	public List< Contract > findHistoryListByName( String name )
	{
		List< HistoricTaskInstance > list = historyService
		        .createHistoricTaskInstanceQuery().taskAssignee( name )
		        .orderByTaskDueDate().desc().list();
		
		List< Contract > contracts = new ArrayList< Contract >();
		for ( HistoricTaskInstance hInstance : list )
		{
			String proInstanceId = hInstance.getProcessInstanceId();
			// 3：使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
			HistoricProcessInstance pi = historyService
			        .createHistoricProcessInstanceQuery()
			        .processInstanceId( proInstanceId ).singleResult();
			
			// 4：使用流程实例对象获取BUSINESS_KEY
			String buniness_key = pi.getBusinessKey();
			// 5：获取BUSINESS_KEY对应的主键ID，使用主键ID，查询请假单对象（LeaveBill.1）
			String id = "";
			if ( StringUtils.isNotBlank( buniness_key ) )
			{
				// 截取字符串，取buniness_key小数点的第2个值
				id = buniness_key.split( "\\." )[1];
			}
			
			// 查询合同对象
			Contract contract = iContractDao.get( Integer.parseInt( id ) );
			contracts.add( contract );
		}
		System.out.println( contracts.size() );
		return contracts;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param name
	 * @param i
	 * @param page
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月5日 下午4:29:54
	 */
	public List< Contract > findHistoryListByNamePagelist( String name , int pageSize ,
	        int page )
	{
		List< HistoricTaskInstance > list = historyService
		        .createHistoricTaskInstanceQuery().taskAssignee( name )
		        .orderByTaskDueDate().desc()
		        .listPage( ( page - 1 ) * pageSize , pageSize );
		
		List< Contract > contracts = new ArrayList< Contract >();
		for ( HistoricTaskInstance hInstance : list )
		{
			String proInstanceId = hInstance.getProcessInstanceId();
			// 3：使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
			HistoricProcessInstance pi = historyService
			        .createHistoricProcessInstanceQuery()
			        .processInstanceId( proInstanceId ).singleResult();
			
			// 4：使用流程实例对象获取BUSINESS_KEY
			String buniness_key = pi.getBusinessKey();
			// 5：获取BUSINESS_KEY对应的主键ID，使用主键ID，查询请假单对象（LeaveBill.1）
			String id = "";
			if ( StringUtils.isNotBlank( buniness_key ) )
			{
				// 截取字符串，取buniness_key小数点的第2个值
				id = buniness_key.split( "\\." )[1];
			}
			
			// 查询合同对象
			Contract contract = iContractDao.get( Integer.parseInt( id ) );
			contracts.add( contract );
		}
		System.out.println( contracts.size() );
		return contracts;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param id
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月9日 上午11:50:04
	 */
	public ProcessDefinition findProcessDefinitionByContractId( String id )
	{
		String bussinessKey = SystemConstance.CONTRACT_FLOWKEY + "." + id;
		HistoricProcessInstance historicProcessInstance = historyService
		        .createHistoricProcessInstanceQuery()
		        .processInstanceBusinessKey( bussinessKey ).singleResult();
		String definitionId = historicProcessInstance.getProcessDefinitionId();
		// 查询流程定义的对象
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()// 创建流程定义查询对象，对应表act_re_procdef
		        .processDefinitionId( definitionId )// 使用流程定义ID查询
		        .singleResult();
		return pd;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param id
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月26日 下午2:01:07
	 */
	public ProcessDefinition findProcessDefinitionByCostFeeId( String id )
	{
		String bussinessKey = SystemConstance.COSTFEE_FLOWKEY + "." + id;
		HistoricProcessInstance historicProcessInstance = historyService
		        .createHistoricProcessInstanceQuery()
		        .processInstanceBusinessKey( bussinessKey ).singleResult();
		String definitionId = historicProcessInstance.getProcessDefinitionId();
		// 查询流程定义的对象
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()// 创建流程定义查询对象，对应表act_re_procdef
		        .processDefinitionId( definitionId )// 使用流程定义ID查询
		        .singleResult();
		return pd;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param id
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月9日 上午11:50:52
	 */
	public Map< String , Object > findCoordingByContractId( String id )
	{
		// 存放坐标
		Map< String , Object > map = new HashMap< String , Object >();
		// 使用任务ID，查询任务对象
		String bussinessKey = SystemConstance.CONTRACT_FLOWKEY + "." + id;
		List< Task > tasks = taskService.createTaskQuery()
		        .processInstanceBusinessKey( bussinessKey ).list();
		// System.out.println(tasks.size());
		Task task = tasks.get( 0 );// 因为可能是会签部门，所以用List
		// 获取流程定义的ID
		String processDefinitionId = task.getProcessDefinitionId();
		// 获取流程定义的实体对象（对应.bpmn文件中的数据）
		ProcessDefinitionEntity processDefinitionEntity = ( ProcessDefinitionEntity ) repositoryService
		        .getProcessDefinition( processDefinitionId );
		// excetionId
		String executionId = task.getExecutionId();
		Execution execution = runtimeService.createExecutionQuery()
		        .executionId( executionId ).singleResult();
		Map< String , Object > maps = runtimeService.getVariables( executionId );
		List< String > assignUsers = ( List< String > ) maps.get( "assigneeList" );
		String applyUser = ( String ) maps.get( "applyuser" );
		// 流程实例ID
		// String processInstanceId = task.getProcessInstanceId();
		// 使用流程实例ID，查询正在执行的执行对象表，获取当前活动对应的流程实例对象
		/*ProcessInstance pi = runtimeService.createProcessInstanceQuery()// 创建流程实例查询
		        .processInstanceId( processInstanceId )// 使用流程实例ID查询
		        .singleResult();*/
		// 获取当前活动的ID
		String activityId = execution.getActivityId();
		// 获取当前活动对象
		ActivityImpl activityImpl = processDefinitionEntity.findActivity( activityId );// 活动ID
		// 获取坐标
		map.put( "x" , activityImpl.getX() );
		map.put( "y" , activityImpl.getY() );
		map.put( "width" , activityImpl.getWidth() );
		map.put( "height" , activityImpl.getHeight() );
		
		List< String > newAssignUsers = new ArrayList< String >();
		
		for ( String userNo : assignUsers )
		{
			String userName = iEmployrecordDao.getUserNameByUserNo( userNo );
			newAssignUsers.add( userName );
		}
		String newApplyUser = iEmployrecordDao.getUserNameByUserNo( applyUser );
		
		map.put( "assignUsers" , newAssignUsers );
		map.put( "applyUser" , newApplyUser );
		return map;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param id
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月26日 下午2:02:18
	 */
	public Map< String , Object > findCoordingByCostFeeId( String id )
	{
		// 存放坐标
		Map< String , Object > map = new HashMap< String , Object >();
		// 使用任务ID，查询任务对象
		String bussinessKey = SystemConstance.COSTFEE_FLOWKEY + "." + id;
		List< Task > tasks = taskService.createTaskQuery()
		        .processInstanceBusinessKey( bussinessKey ).list();
		// System.out.println(tasks.size());
		Task task = tasks.get( 0 );// 因为可能是会签部门，所以用List
		// 获取流程定义的ID
		String processDefinitionId = task.getProcessDefinitionId();
		// 获取流程定义的实体对象（对应.bpmn文件中的数据）
		ProcessDefinitionEntity processDefinitionEntity = ( ProcessDefinitionEntity ) repositoryService
		        .getProcessDefinition( processDefinitionId );
		// excetionId
		String executionId = task.getExecutionId();
		Execution execution = runtimeService.createExecutionQuery()
		        .executionId( executionId ).singleResult();
		Map< String , Object > maps = runtimeService.getVariables( executionId );
		List< String > deptleaders = ( List< String > ) maps.get( "deptleaders" );
		List< String > deptfinanleaders = ( List< String > ) maps
		        .get( "deptfinanleaders" );
		String inputuser = ( String ) maps.get( "inputuser" );
		// 流程实例ID
		// String processInstanceId = task.getProcessInstanceId();
		// 使用流程实例ID，查询正在执行的执行对象表，获取当前活动对应的流程实例对象
		/*ProcessInstance pi = runtimeService.createProcessInstanceQuery()// 创建流程实例查询
		        .processInstanceId( processInstanceId )// 使用流程实例ID查询
		        .singleResult();*/
		// 获取当前活动的ID
		String activityId = execution.getActivityId();
		// 获取当前活动对象
		ActivityImpl activityImpl = processDefinitionEntity.findActivity( activityId );// 活动ID
		// 获取坐标
		map.put( "x" , activityImpl.getX() );
		map.put( "y" , activityImpl.getY() );
		map.put( "width" , activityImpl.getWidth() );
		map.put( "height" , activityImpl.getHeight() );
		map.put( "deptleaders" , deptleaders );
		map.put( "deptfinanleaders" , deptfinanleaders );
		map.put( "inputuser" , inputuser );
		return map;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param costApply
	 * @param userno 
	 * @author liuwu
	 * @create_date 2015年11月17日 下午4:20:07
	 */
	public String startCostFeeProgress( CostApply costApply , String userno )
	{
		String messageString = "success";
		// 1：获取ID查询
		Integer id = costApply.getId();
		
		if ( ! costApply.getnState().equals( 0 ) )
		{
			messageString = "该费用已经申请审批！";
			return messageString;
		}
		
		// 3：使用当前对象获取到流程定义的key（对象的名称就是流程定义的key）
		String key = SystemConstance.COSTFEE_FLOWKEY;
		/**
		 * 4：从Session中获取当前任务的办理人，使用流程变量设置下一个任务的办理人
			    * inputUser是流程变量的名称，
			    * 获取的办理人是流程变量的值
		 */
		Map< String , Object > variables = new HashMap< String , Object >();
		variables.put( "inputuser" , userno );// 表示惟一用户
		
		/**
		 * 根据用户、费用申请详情设置变量
		 */
		messageString = setCostFeeProgressVarible( costApply , userno , variables );
		
		// 格式：Contract.id的形式（使用流程变量）
		String objId = key + "." + id;
		variables.put( "objId" , objId );
		if ( messageString.equalsIgnoreCase( "success" ) )
		{
			// 2：更新状态从0变成1（初始录入-->审核中）
			costApply.setnState( 1 );
			// 6：使用流程定义的key，启动流程实例，同时设置流程变量，同时向正在执行的执行对象表中的字段BUSINESS_KEY添加业务数据，同时让流程关联业务
			runtimeService.startProcessInstanceByKey( key , objId , variables );
		}
		
		return messageString;
		
	}
	
	/** 
	 * @Description:根据用户、费用申请详情设置变量
	 * 主要变量: 
	 * ${inputuser} ：申请人
	 * needleader:0:需要领导会签 1-不需要领导会签直接流向财务
	 * deptleaders:申请人部门的领导，根据用户及费用类型查询：主要涉及表：dept_level
	 * deptfinanleaders:申请人部门关联的财务部门：根据用户及费用类型查询	dept_financia_re	
	 * needboss: 0--需要总裁确认 1--不需要
	 * @param costApply
	 * @param userno 
	 *   void 返回值描述
	 * @author liuwu
	 * @param variables 
	 * @create_date 2015年11月17日 下午4:31:47
	 */
	private String setCostFeeProgressVarible( CostApply costApply , String userno ,
	        Map< String , Object > variables )
	{
		String message = "success";
		int needleader = 0;
		int needboss = 1;
		// 通过申请人找到部门领导
		int userId = costApply.getiUser();
		Smuser smuser = iUserService.getUserById( userId );
		Employrecord employrecord = iEmployrecordDao.getEmrById( smuser.getEmployrecord()
		        .getLineid() );
		int deptId = employrecord.getDept().getLineid();
		
		// 费用类型
		Costtype costtype = iCosttypeDao.get( costApply.getiCosttype() );
		// 根据部门ID找出所在的分公司ID
		int pid = getPidByDept( employrecord.getDept().getLineid() );// 分公司的部门id
		// 中联中国总部（深圳）
		if ( pid == 1 )
		{
			
			// 条件类型： 1：单项费用预算内 2-单项费用预算外、总额预算内 3--总额预算外 4：需要总裁确认
			int type = getHeadCostFeeType( costApply , costtype );
			if ( type == 0 )
			{
				message = "部门【" + employrecord.getDept().getDeptname() + "】费用类型【"
				        + costtype.getVcName() + "】未维护，或不在审批流程范围！";
				return message;
			}
			/**
			 *  根据条件分公司、费用类别、及条件查询FEE_RE_LEVEL得到需要会签的部门领导等级
			 *  如果为空 说明直接财务审核
			 */
			String hql1 = "from FeeReLevel f where f.iCosttype = " + costtype.getId()
			        + " and f.nCondition = " + type + " and f.iCompanytype = " + pid
			        + " and f.nEnable = 0";
			List< FeeReLevel > feeReLevels = iFeeReLevelDao.findByHql( hql1 );
			
			if ( feeReLevels != null && feeReLevels.size() > 0 )
			{
				String levelString = feeReLevels.get( 0 ).getVcLevel();
				String hqlString = "from DeptLevel where iUser = " + costApply.getiUser();
				List< DeptLevel > deptLevels = iDeptLevelDao.findByHql( hqlString );
				
				String newLevel = feeReLevels.get( 0 ).getVcLevel();
				// 如果当前申请人也是领导，那么层级中只保留他层级以上的 如果是最高等级则不需要领导会签直接走财务
				if ( deptLevels != null && deptLevels.size() > 0 )
				{
					
					newLevel = getNewLevel( deptLevels.get( 0 ).getnLeveal() ,
					        levelString );
				}
				if ( StringUtils.isNotBlank( newLevel ) )
				{
					// 如果是倒板费 由调度中心 即运营计划部
					if ( costApply.getiCosttype() == 18 )
					{
						deptId = 60;
					}
					// 如果是事故直接由安全生产部主管审批
					if ( costApply.getiCosttype() == 19 )
					{
						deptId = 240;
					}
					String hql2 = "from DeptLevel where iDept = " + deptId
					        + " and nLeveal in (" + newLevel
					        + ") and nEnable = 0 order by nLeveal";
					List< DeptLevel > deptLevellist = iDeptLevelDao.findByHql( hql2 );
					variables.put( "deptleaders" , deptLevellist );
				}
				else
				{
					needleader = 1;// 不需要申请部门领导会签
				}
				
				/**
				 * 根据条件：分公司、费用类别、及条件查询 DEPT_FINANCIA_RE得到需要会签的财务部门人员
				 */
				String hql3 = "from DeptFinanciaRe d where d.iDept = " + pid
				        + " and d.nEnable = 0 and nLevel in ("
				        + feeReLevels.get( 0 ).getVcLevel2() + ") order by nLevel";
				List< DeptFinanciaRe > deptFinanciaRes = iDeptFinanciaReDao
				        .findByHql( hql3 );
				variables.put( "deptfinanleaders" , deptFinanciaRes );
				if ( type == 4 )
				{
					needboss = 0;
				}
			}
			else
			{
				needleader = 1;// 不需要申请部门领导会签
			}
			variables.put( "needleader" , needleader );
			variables.put( "needboss" , needboss );
		}
		
		// 分公司
		return message;
		
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param getnLeveal
	 * @param levelString
	 * @return 
	 *   String 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月24日 下午4:33:30
	 */
	@SuppressWarnings( "unused" )
	private String getNewLevel( Integer level , String levelString )
	{
		String[] leStrings = levelString.split( "," );
		List< String > aList = new ArrayList< String >();
		for ( String levelStr : leStrings )
		{
			if ( Integer.parseInt( levelStr ) > level )
			{
				aList.add( levelStr );
			}
			// aList.add( levelStr );
		}
		if ( aList != null && aList.size() > 0 )
		{
			String[] newStrings = aList.toArray( new String[aList.size()] );
			StringBuffer newString = new StringBuffer();
			for ( String str : newStrings )
			{
				
				newString.append( str + "," );
			}
			
			return newString.deleteCharAt( newString.length() - 1 ).toString();
		}
		else
		{
			return null;
		}
		
	}
	
	/** 
	 * @Description:循环查询出该部门属于哪个分公司
	 * @param lineid
	 * @return 
	 *   int 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月23日 下午3:32:56
	 */
	private int getPidByDept( int lineid )
	{
		Dept dept = iDeptDao.getDeptById( lineid );
		
		while ( dept.getPid() != 0 )
		{
			dept = iDeptDao.getDeptById( dept.getPid() );
		}
		System.out.println( "dept.getPid() = " + dept.getPid() );
		return dept.getLineid();
	}
	
	/** 
	 * @Description:总公司：根据总公司人的申请费用类别及金额，判断属于哪种条件
	 * @param costApply
	 * @return 
	 *   int  0:表示查询不到条件，说明基础数据未维护 
	 * @author liuwu
	 * @param costtype  费用类型
	 * @create_date 2015年11月19日 下午4:40:58
	 */
	private int getHeadCostFeeType( CostApply costApply , Costtype costtype )
	{
		// 1、单项费用基础表 先减去费用申请表的费用再比较
		Calendar cal = Calendar.getInstance();
		int month = cal.get( Calendar.MONTH ) + 1;
		CostBudget costBudget = iCostBudgetDao.getByCostType( costApply , month );
		DeptTotalcost deptTotalcost = iDeptTotalCostDao.getByCostType( costApply , month ,
		        costtype );
		if ( costBudget == null )
		{
			return 0;
		}
		if ( deptTotalcost == null )
		{
			return 0;
		}
		// 先减去申请费用再比较
		costBudget.setnLastcost2( costBudget.getnTotalcost() - costApply.getnCost() );
		deptTotalcost
		        .setnLastcost2( deptTotalcost.getnTotalcost() - costApply.getnCost() );
		// 日常费用 1：单项费用预算内 2-单项费用预算外、总额预算内 3-总额预算外
		if ( costtype.getpId() == 1 )
		{
			// 1：单项费用预算内 2-单项费用预算外、总额预算内 3--总额预算外
			if ( costApply.getnCost() < costBudget.getnLastcost2() )
			{
				return 1;
			}
			else if ( costApply.getnCost() > costBudget.getnLastcost2()
			        && costApply.getnCost() < deptTotalcost.getnLastcost2() )
			{
				return 2;
			}
			else
			{
				return 3;
			}
		}
		// 特殊费用
		if ( costtype.getpId() == 2 )
		{
			// 租金支出
			if ( costtype.getVcName().endsWith( "租金支出" ) )
			{
				// 1：单项费用预算内 2-单项费用预算外、总额预算内 3--总额预算外
				if ( costApply.getnCost() < costBudget.getnLastcost2() )
				{
					return 1;
				}
				else if ( costApply.getnCost() > costBudget.getnLastcost2()
				        && costApply.getnCost() < deptTotalcost.getnLastcost2() )
				{
					return 2;
				}
				else
				{
					return 3;
				}
			}
			// 水电费
			if ( costtype.getVcName().endsWith( "水电费" ) )
			{
				// 1：单项费用预算内 2-单项费用预算外、总额预算内 3--总额预算外
				if ( costApply.getnCost() < costBudget.getnLastcost2() )
				{
					return 1;
				}
				else if ( costApply.getnCost() > costBudget.getnLastcost2()
				        && costApply.getnCost() < deptTotalcost.getnLastcost2() )
				{
					return 2;
				}
				else
				{
					return 3;
				}
			}
			// 倒板费
			if ( costtype.getVcName().endsWith( "倒板费" ) )
			{
				// 1：单项费用预算内 2-单项费用预算外、总额预算内 3--总额预算外
				if ( costApply.getnCost() < costBudget.getnLastcost2() )
				{
					return 1;
				}
				else if ( costApply.getnCost() > costBudget.getnLastcost2()
				        && costApply.getnCost() < deptTotalcost.getnLastcost2() )
				{
					return 2;
				}
				else
				{
					return 3;
				}
			}
			// 车辆维护
			if ( costtype.getVcName().endsWith( "车辆维护" ) )
			{
				// 1：单项费用预算内 2-单项费用预算外、总额预算内 3--总额预算外
				if ( costApply.getnCost() < costBudget.getnLastcost2() )
				{
					return 1;
				}
				else if ( costApply.getnCost() > costBudget.getnLastcost2()
				        && costApply.getnCost() < deptTotalcost.getnLastcost2() )
				{
					return 2;
				}
				else
				{
					return 3;
				}
			}
			// 事故
			if ( costtype.getVcName().endsWith( "事故" ) )
			{
				if ( costApply.getnCost() <= 1000 )
				{
					return 1;
				}
				if ( costApply.getnCost() > 1000 && costApply.getnCost() <= 10000 )
				{
					return 2;
				}
				if ( costApply.getnCost() > 10000 && costApply.getnCost() <= 100000 )
				{
					return 3;
				}
				else
				{
					return 4;
				}
			}
			// 质损
			if ( costtype.getVcName().endsWith( "质损" ) )
			{
				if ( costApply.getnCost() <= 1000 )
				{
					return 1;
				}
				if ( costApply.getnCost() > 1000 && costApply.getnCost() <= 10000 )
				{
					return 2;
				}
				if ( costApply.getnCost() > 10000 && costApply.getnCost() <= 100000 )
				{
					return 3;
				}
				else
				{
					return 4;
				}
			}
		}
		return 0;
		
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param id
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月21日 下午4:20:10
	 */
	public List< Comment > findCommentByLeaveBillId( Long id )
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用  
	 * @author liuwu
	 * @create_date 2015年11月21日 下午7:10:52
	 */
	public void deployTest()
	{
		// TODO Auto-generated method stub
		InputStream inputStreamBpmn = this.getClass().getResourceAsStream( "email.bpmn" );
		InputStream inputStreamPng = this.getClass().getResourceAsStream( "email.png" );
		Deployment deployment = repositoryService.createDeployment()
		        // 与流程定义和部署对象相关的service
		        .name( "邮件" )
		        // 添加部署名称
		        .addInputStream( "email.bpmn" , inputStreamBpmn )
		        .addInputStream( "email.png" , inputStreamPng ).deploy();
	}
	
	/** 
	 * @Description:添加评审人 
	 * @param addOther
	 * @return 
	 * @author liuwu
	 * @create_date 2015年11月23日 上午10:13:24
	 */
	public String addAssignee( String addOther , WorkflowBean workflowBean )
	{
		// 获取任务ID
		String taskId = workflowBean.getTaskId();
		/**
		 * 1：在完成之前，添加一个批注信息，向act_hi_comment表中添加数据，用于记录对当前申请人的一些审核信息
		 */
		// 使用任务ID，查询任务对象，获取流程流程实例ID
		Task task = taskService.createTaskQuery()//
		        .taskId( taskId )// 使用任务ID查询
		        .singleResult();
		String executionId = task.getExecutionId();
		
		Map< String , Object > maps = runtimeService.getVariables( executionId );
		List< String > assigneeList = ( List< String > ) maps.get( "assigneeList" );
		if ( assigneeList.contains( addOther ) )
		{
			return "该评审人已存在，无需添加！";
		}
		else
		{
			int index = assigneeList.indexOf( SystemConstance.FINANCIAL_MANAGE2 );
			if ( index > 0 )
			{
				assigneeList.add( index + 1 , addOther );
				System.out.println( assigneeList );
				
			}
			else
			{
				assigneeList.add( addOther );
				
			}
			runtimeService.setVariable( executionId , "assigneeList" , assigneeList );
			return "success";
		}
		
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param workflowBean 
	 * @author liuwu
	 * @create_date 2015年11月25日 下午5:03:25
	 */
	public String saveSubmitFeeTask( WorkflowBean workflowBean ,
	        HttpServletRequest request )
	{
		String errorString = "success";
		// 获取任务ID
		String taskId = workflowBean.getTaskId();
		// 获取连线的名称
		String outcome = workflowBean.getOutcome();
		// 批注信息
		String message = workflowBean.getComment();
		// 获取费用ID
		Integer id = workflowBean.getId();
		
		/**
		 * 1：在完成之前，添加一个批注信息，向act_hi_comment表中添加数据，用于记录对当前申请人的一些审核信息
		 */
		// 使用任务ID，查询任务对象，获取流程流程实例ID
		Task task = taskService.createTaskQuery()//
		        .taskId( taskId )// 使用任务ID查询
		        .singleResult();
		// 获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		/**
		 * 注意：添加批注的时候，由于Activiti底层代码是使用：
		 * 		String userId = Authentication.getAuthenticatedUserId();
			    CommentEntity comment = new CommentEntity();
			    comment.setUserId(userId);
			  所有需要从Session中获取当前登录人，作为该任务的办理人（审核人），对应act_hi_comment表中的User_ID的字段，不过不添加审核人，该字段为null
			 所以要求，添加配置执行使用Authentication.setAuthenticatedUserId();添加当前任务的审核人
		 * */
		// String empId = UserSession.get( "empId" ).toString();
		String empId = request.getSession().getAttribute( "recordid" ).toString();
		
		Employrecord employrecord = iEmployrecordDao
		        .getEmrById( Integer.parseInt( empId ) );
		Authentication.setAuthenticatedUserId( employrecord.getEmployname() );
		message = message + "【" + outcome + "】";
		taskService.addComment( taskId , processInstanceId , message );
		/**
		 * 2：如果连线的名称是“提交申请”，那么就不需要设置，如果不是，就需要设置流程变量
		 * 在完成任务之前，设置流程变量，按照连线的名称，去完成任务
				 流程变量的名称：outcome
				 流程变量的值：连线的名称
		 */
		Map< String , Object > variables = new HashMap< String , Object >();
		if ( outcome != null && ! outcome.equals( "默认提交" ) )
		{
			variables.put( "outcome" , outcome );
		}
		// 如果是申请部门会签流程
		List< Task > tasks = taskService.createTaskQuery().taskName( "费用审批:申请部门审批" )
		        .processInstanceId( processInstanceId ).list();
		// 如果是会签流程
		if ( tasks != null && tasks.size() > 0 )
		{
			List< Task > taskResultList = taskService.createTaskQuery().taskId( taskId )
			        .list();
			// 当前executionId
			String currentExecutionId = taskResultList.get( 0 ).getExecutionId();
			// 当前签署总数
			/*	String currentSignCount = StringUtils.defaultString( runtimeService
				        .getVariable( currentExecutionId , "signCount" ).toString() , "0" );*/
			if ( outcome.equals( "驳回" ) )
			{
				
				runtimeService.setVariable( currentExecutionId , "deptflag" , 0 );
			}
			else if ( outcome.equals( "同意" ) )
			{
				runtimeService.setVariable( currentExecutionId , "deptflag" , 1 );
			}
			
		}
		
		// 如果是财务部门"会签流程
		List< Task > tasks2 = taskService.createTaskQuery().taskName( "费用审批:财务部门" )
		        .processInstanceId( processInstanceId ).list();
		// 如果是会签流程
		if ( tasks2 != null && tasks2.size() > 0 )
		{
			List< Task > taskResultList = taskService.createTaskQuery().taskId( taskId )
			        .list();
			// 当前executionId
			String currentExecutionId = taskResultList.get( 0 ).getExecutionId();
			// 当前签署总数
			/*	String currentSignCount = StringUtils.defaultString( runtimeService
				        .getVariable( currentExecutionId , "signCount" ).toString() , "0" );*/
			if ( outcome.equals( "驳回" ) )
			{
				
				runtimeService.setVariable( currentExecutionId , "deptflag" , 0 );
			}
			else if ( outcome.equals( "同意" ) )
			{
				runtimeService.setVariable( currentExecutionId , "deptflag" , 1 );
			}
			
		}
		
		CostApply costApply = iCostApplyDao.get( id );
		if ( costApply.getnState().equals( 3 ) )
		{
			costApply.setnState( 1 );// 驳回改为审核中
		}
		
		if ( outcome.equals( "驳回" ) )
		{
			
			costApply.setnState( 3 );// 驳回
		}
		
		try
		{
			// 3：使用任务ID，完成当前人的个人任务，同时流程变量
			taskService.complete( taskId , variables );
		}
		catch ( Exception e )
		{
			errorString = "发生未知错误,错误信息：" + e.getMessage();
		}
		/**
		 * 5：在完成任务之后，判断流程是否结束
			如果流程结束了，更新合同表的状态从1变成2（审核中-->审核完成）
		 */
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
		        .processInstanceId( processInstanceId )// 使用流程实例ID查询
		        .singleResult();
		// 流程结束了
		if ( pi == null )
		{
			// 更新状态从1变成2（审核中-->审核完成）
			costApply.setnState( 2 );
			// 减掉预算
			Calendar cal = Calendar.getInstance();
			int month = cal.get( Calendar.MONTH ) + 1;
			CostBudget costBudget = iCostBudgetDao.getByCostType( costApply , month );
			costBudget.setnTotalcost( costBudget.getnTotalcost() - costApply.getnCost() );
			
			Costtype costtype = iCosttypeDao.get( costApply.getiCosttype() );
			DeptTotalcost deptTotalcost = iDeptTotalCostDao.getByCostType( costApply ,
			        month , costtype );
			deptTotalcost.setnTotalcost( deptTotalcost.getnTotalcost()
			        - costApply.getnCost() );
		}
		// 保存费用审核记录表
		
		CostAuditrecord costAuditrecord = new CostAuditrecord();
		costAuditrecord.setiCostapply( id );
		// String userId = UserSession.get( "userId" ).toString();
		String userId = request.getSession().getAttribute( "lineid" ).toString();
		costAuditrecord.setiUser( Integer.parseInt( userId ) );
		costAuditrecord.setVcUser( employrecord.getEmployname() );
		costAuditrecord.setVcComment( message );
		costAuditrecord.setiDept( employrecord.getDept().getLineid() );
		costAuditrecord.setVcDept( employrecord.getDept().getDeptname() );
		costAuditrecord.setVcNote( task.getName() );
		iCostAuditRecordDao.save( costAuditrecord );
		
		List< Task > tList = taskService.createTaskQuery()
		        .processDefinitionKey( SystemConstance.COSTFEE_FLOWKEY )
		        .processInstanceId( processInstanceId ).list();
		// 申请人
		Smuser smuser = iUserService.getUserById( costApply.getiUser() );
		if ( tList != null && tList.size() > 0 )
		{
			Map< String , Object > emailVaribleMap = new HashMap< String , Object >();
			List< UserEmail > emails = new ArrayList< UserEmail >();
			emails.add( addUserEmail( tList.get( 0 ).getAssignee() ,
			        SystemConstance.TEST_EMAIL2 ) );
			emailVaribleMap.put( "touserList" , emails );
			
			emailVaribleMap.put( "object" , "来自【" + costApply.getVcName() + "】费用审批申请" );
			emailVaribleMap.put( "html" , "来自【" + costApply.getVcName() + "】的费用申请已由【"
			        + employrecord.getEmployname() + "】确认,审批意见:" + message
			        + ";<br/>需要您登录OA进行下一步审批，请点击以下链接直接登录，链接24小时后失效" );
			try
			{
				runtimeService.startProcessInstanceByKey(
				        SystemConstance.CONTRACT_EMAIL_FLOWKEY , emailVaribleMap );
			}
			catch ( org.springframework.transaction.UnexpectedRollbackException e )
			{
				// e.printStackTrace();
				errorString = "邮件服务器故障！发送邮件失败！";
				System.out.println( "异常：" );
				
				return errorString;
			}
			catch ( org.activiti.engine.ActivitiException e )
			{
				errorString = "邮件服务器故障！发送邮件失败！";
				
				//return errorString;
				throw e;
			}
			catch ( Exception e )
			{
				errorString = "邮件服务器故障！发送邮件失败！";
				return errorString;
			}
		}
		else
		{
			Map< String , Object > emailVaribleMap = new HashMap< String , Object >();
			List< UserEmail > emails = new ArrayList< UserEmail >();
			emails.add( addUserEmail( smuser.getUserno() ,
			        SystemConstance.CONTRACT_MANAGE_EMAIL ) );
			emailVaribleMap.put( "touserList" , emails );
			
			emailVaribleMap.put( "object" , "来自【" + costApply.getVcName() + "】费用审批申请" );
			emailVaribleMap.put( "html" , "费用审批流程结束，来自【" + costApply.getVcName()
			        + "】的费用申请已由【" + employrecord.getEmployname() + "】确认,审批意见:" + message
			        + ";<br/>需要您登录OA进行下一步审批，请点击以下链接直接登录，链接24小时后失效" );
			try
			{
				runtimeService.startProcessInstanceByKey(
				        SystemConstance.CONTRACT_EMAIL_FLOWKEY , emailVaribleMap );
			}
			catch ( org.activiti.engine.ActivitiException e )
			{
				// e.printStackTrace();
				errorString = "邮件服务器故障！发送邮件失败！";
				return errorString;
			}
			catch ( org.springframework.transaction.UnexpectedRollbackException e )
			{
				errorString = "邮件服务器故障！发送邮件失败！";
				return errorString;
			}
		}
		return errorString;
		
	}
	
}
