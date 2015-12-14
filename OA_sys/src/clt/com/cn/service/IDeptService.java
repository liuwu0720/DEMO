package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.Dept;
import clt.com.cn.model.entity.DeptAddress;
import clt.com.cn.model.entity.DeptMapping;
import clt.com.cn.model.entity.Smuser;

public interface IDeptService
{
	public abstract List< Dept > getAllDept();
	
	public abstract void addDept( Dept dept );
	
	public abstract void delDeptById( int id );
	
	public abstract Dept getDeptById( int id );
	
	public DeptMapping getDeptMapping( int id );
	
	public abstract void updateDept( Dept d );
	
	// 条件查询（分页）
	public abstract List< Dept > DeptInfoById( String deptname );
	
	// 总条数
	public abstract int getCount();
	
	// 总页数
	public abstract int getpages( int count , int pageSize );
	
	public abstract List< Dept > getAllDeptByPage( int page );
	
	public Smuser getDeptManageridBydeptid( int deptid );
	
	public List< Dept > getUserInfo( String hql , Object ... p );
	
	public List getDateBySqlQuery( final String sql , final int pageSize , final int page );
	
	public void execSQL( final String sql );
	
	public void addDeptmapping( DeptMapping deptmapping );
	
	/**
	 * 
	 * @Description: 获取所有公司 部门级别=2
	 * @return List<Dept> 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-18 下午1:38:41
	 */
	public List< Dept > getAllCompay();
	
	/**
	 * 
	 * @Description: 根据公司ID获取所有部门 因为会有递归 所有写的sql
	 * @param compid
	 * @return List<String[]> 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-18 下午1:39:03
	 */
	public List< Object[] > getAllDeptByComID( int compid );
	
	public List< Object[] > getAllAddressByCompID( int compid );
	
	public DeptAddress getDeptAddressByID( int id );
	
	public void sendMails( Object obj , int paramType );
	
	/**
	 * 
	 * @Description: 提交申请可发送邮件 给审批人
	 * @param obj
	 * @param paramType
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-12 下午4:39:08
	 */
	public void approveMails( Object obj , int paramType );
	
	public List< Dept > getAllObjectOrder( String hql );
	
}
