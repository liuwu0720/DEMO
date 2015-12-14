package junit;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/** 
 * @Package junit 
 * @Description:用一句话描述该文件做什么 
 * @author hjx 
 * @date 2015年10月17日 下午3:47:37 
 * @version V1.0 
 */
public class TestActiviti
{
	/**
	 * 使用代码
	 * @Description:这里用一句话描述这个方法的作用 
	 *   void 返回值描述
	 * @author hjx
	 * @create_date 2015年10月17日 下午4:59:30
	 */
	@Test
	public void createTable()
	{
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
		        .createStandaloneProcessEngineConfiguration();
		//连接数据库
		processEngineConfiguration.setJdbcDriver( "com.mysql.jdbc.Driver" );
		processEngineConfiguration.setJdbcUrl( "jdbc:mysql://localhost:3306/activiti2?useUnicode=true&characterEncoding=utf8" );
		processEngineConfiguration.setJdbcUsername( "root" );
		processEngineConfiguration.setJdbcPassword( "123" );
		processEngineConfiguration.setDatabaseSchemaUpdate( ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE );
		//创建表
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		System.err.println("processEngine"+processEngine);
		
		
	}

	@Test
	public void createRable2(){
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource( "activiti.cfg.xml" );
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		System.out.println("processEngine"+processEngine);
	}
	
}
