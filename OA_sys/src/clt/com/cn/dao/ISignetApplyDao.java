package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.SignetApply;

/**
 * @Package clt.com.cn.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenbin
 * @date 2014-7-28 上午10:36:45
 * @version V1.0
 */
public interface ISignetApplyDao
{
	public List< SignetApply > getAllSignetApply();
	
	public void addSignetApply( SignetApply obj );
	
	public void delSignetApplyById( int id );
	
	public SignetApply getSignetApplyById( int id );
	
	public void updateSignetApply( SignetApply d );
}
