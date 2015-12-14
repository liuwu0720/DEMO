/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-8-1 下午1:07:14
 * @version V1.0
 */
package com.chnl.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.chnl.entity.Page;

/**
 * @Package com.chnl.base
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-8-1 下午1:07:14
 * @version V1.0
 */
public class BaseDAO extends HibernateDaoSupport
{	
	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	// 根据sql 返回查询count的sql
	public int getCountSQL( String sql )
	{
		String countsql = " select count(*) coun from ( " + sql + " )";
		SQLQuery countquery = this.getSession().createSQLQuery( countsql );
		int recordCount = Integer
		        .valueOf( countquery.uniqueResult().toString() );
		return recordCount;
	}
	
	public Map< String , Object > getSpringSQL( String sql , Page page )
	{
		Map< String , Object > result = new HashMap< String , Object >();
		
		int count = getCountSQL( sql );
		page.setRecordCount( count );
		result.put( "total" , count );
		
		sql = getOraclePageSQL( sql , page );
		System.out.println( "SQL:" + sql );
		List< Map< String , Object >> arlist = jdbcTemplate.queryForList( sql );
		
		result.put( "rows" , arlist );
		
		String srtr = JSONArray.fromObject( arlist ).toString();
		
		return result;
	}
	
	public String getOraclePageSQL( String queryString , Page page )
	{
		if ( StringUtils.isEmpty( queryString ) )
		{
			return null;
		}
		String itemSource = queryString.toLowerCase();
		int endIndex = page.getFirstResult() + page.getRecordCountperPage();
		String endSql = "select * from (select rOraclePageSQL.*,ROWNUM as currentRow from ("
		        + queryString
		        + ") rOraclePageSQL where rownum <="
		        + endIndex
		        + ") where currentRow>" + page.getFirstResult();
		return endSql;
	}
}
