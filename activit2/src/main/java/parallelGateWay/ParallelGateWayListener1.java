package parallelGateWay;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;

/** 
 * @Package parallelGateWay 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年10月22日 下午4:56:33 
 * @version V1.0 
 */
public class ParallelGateWayListener1  implements ExecutionListener
{

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param execution
	 * @throws Exception 
	 * @author liuwu
	 * @create_date 2015年10月22日 下午5:33:27
	 */ 
    @Override
    public void notify( DelegateExecution execution ) throws Exception
    {
	    System.out.println("********************************");
	    execution.setVariable( "inputUser" , "liuwu" );
    }

	
	
}
