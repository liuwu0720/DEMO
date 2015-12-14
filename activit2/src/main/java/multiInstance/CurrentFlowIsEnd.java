package multiInstance;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;

/** 
 * @Package multiInstance 
 * @Description:判断当前流程是否结束 
 * @author liuwu
 * @date 2015年10月26日 下午2:36:23 
 * @version V1.0 
 */
public class CurrentFlowIsEnd  implements ExecutionListener
{

	/** 
	 * @Description:验证是否走下一个流程 
	 * @param execution
	 * @throws Exception 
	 * @author liuwu
	 * @create_date 2015年10月26日 下午5:47:39
	 */ 
    @Override
    public void notify( DelegateExecution execution ) throws Exception
    {
    	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	String executionId = execution.getId();
	    //总共同意的人
    	int singCount = Integer.parseInt( execution.getEngineServices().getRuntimeService().getVariable( executionId , "signCount" ).toString() ) ;
	    
	    execution.setVariable( "outcome" , "全部同意" );
    }
	

	
}
