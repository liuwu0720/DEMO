package parallelGateWay;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;



/** 
 * @Package parallelGateWay 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年10月23日 上午10:00:25 
 * @version V1.0 
 */
public class AutoDistrcitTaskListener implements JavaDelegate
{

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param execution
	 * @throws Exception 
	 * @author liuwu
	 * @create_date 2015年10月23日 下午1:54:42
	 */ 
    @Override
    public void execute( DelegateExecution execution ) throws Exception
    {
	    System.out.println("根据会审结果来分配流程走向！！！！！！！");
	    execution.setVariable( "flag" , 0 );
		System.out.println( "ProcessInstanceId = " + execution.getProcessInstanceId() );
		System.out.println( "ProcessDefinitionId = " + execution.getProcessDefinitionId() );
		//execution.getEngineServices().getTaskService().getVariable( taskId , variableName )
		
    }

	
	
}
