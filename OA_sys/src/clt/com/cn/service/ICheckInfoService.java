package clt.com.cn.service;

import java.util.List;


public interface ICheckInfoService {
	
	public int getpages(int count, int pageSize);
	public int getCountBySQL(String sql);
	public List getpageDateBySqlQuery(String sql, int page, int pageSize);
}
