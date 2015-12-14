package clt.com.cn.util;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/** 
 * @Package multiInstance 
 * @Description:会签流程：判断当前流程是否结束 
 * @author liuwu
 * @date 2015年10月26日 下午2:36:23 
 * @version V1.0 
 */
@SuppressWarnings( "serial" )
public class CurrentFlowIsEnd implements ExecutionListener
{
	
	/** 
	 * @Description:验证是否走下一个流程 
	 * @param execution
	 * @throws Exception 
	 * @author liuwu
	 * @create_date 2015年10月26日 下午5:47:39
	 */
	public void notify( DelegateExecution execution ) throws Exception
	{
		System.out.println( "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" );
		String executionId = execution.getId();
		// 总共同意的人
		int signCount = Integer.parseInt( execution.getEngineServices()
		        .getRuntimeService().getVariable( executionId , "signCount" ).toString() );
		
		// 总共会签的人数
		List list = ( List ) execution.getEngineServices().getRuntimeService()
		        .getVariable( executionId , "assigneeList" );
		int signSize = list.size();
		if(signCount == signSize){
			execution.setVariable( "outcome" , "全部同意" );
		}else {
			execution.setVariable( "outcome" , "有人不同意" );
			execution.setVariable( "signCount" , 0 );
		}
		
	}
	
}
