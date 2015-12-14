package clt.com.cn.util;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/** 
 * @Package clt.com.cn.util 
 * @Description:验证财务总监
 * @author liuwu
 * @date 2015年10月30日 下午4:51:11 
 * @version V1.0 
 */
public class ValidFinancialManager implements TaskListener
{

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param delegateTask 
	 * @author liuwu
	 * @create_date 2015年10月30日 下午4:51:22
	 */ 
    public void notify( DelegateTask delegateTask )
    {
	    // TODO Auto-generated method stub
    	  delegateTask.setAssignee( SystemConstance.FINANCIAL_MANAGE );
    }	
	
}
