package multiInstance;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/** 
 * @Package multiInstance 
 * @Description:用一句话描述该文件做什么 
 * @author liuwu
 * @date 2015年11月17日 下午2:15:43 
 * @version V1.0 
 */
public class CostFeeListener implements ExecutionListener
{
	
	/**
	 * 
	 */
    private static final long serialVersionUID = - 1779195807221210553L;

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param execution
	 * @throws Exception 
	 * @author liuwu
	 * @create_date 2015年11月17日 下午2:16:41
	 */
	@Override
	public void notify( DelegateExecution execution ) throws Exception
	{
		
		// TODO Auto-generated method stub
		int singCount = Integer.parseInt( execution.getEngineServices()
		        .getRuntimeService().getVariable( execution.getId() , "needboss" )
		        .toString() );
		System.out.println("****************************"+singCount);
		if ( singCount > 0 )
		{
		//	execution.setVariable( "needboss" , 1 );
		}
	}
	
}
