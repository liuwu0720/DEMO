package clt.com.cn.util;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/** 
 * @Package clt.com.cn.util 
 * @Description:验证商务部经理
 * @author liuwu
 * @date 2015年10月28日 下午6:32:06 
 * @version V1.0 
 */
public class ValidBusinessManager implements TaskListener
{

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param arg0 
	 * @author liuwu
	 * @create_date 2015年10月28日 下午6:32:30
	 */ 
    public void notify( DelegateTask delegateTask )
    {
    	  System.out.println("设置商务部经理*********************");
	    delegateTask.setAssignee( "zhouyp" );
    }	
	
}
