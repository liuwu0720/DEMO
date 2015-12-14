package multiInstance;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;


/** 
 * @Package clt.com.cn.util 
 * @Description:验证初审时的合同专员
 * @author liuwu
 * @date 2015年10月22日 下午4:07:09 
 * @version V1.0 
 */
@SuppressWarnings( "serial" )
public class ValidIsContractCommissionerListener  implements TaskListener
{

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param arg0 
	 * @author liuwu
	 * @create_date 2015年10月28日 下午6:19:26
	 */ 
    public void notify( DelegateTask delegateTask )
    {
	    System.out.println("设置商务部合同专员*********************");
	    delegateTask.setAssignee( "xuml" );
    }	
	
}
