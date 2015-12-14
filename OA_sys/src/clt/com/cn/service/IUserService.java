package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.ManagerApproval;
import clt.com.cn.model.entity.Role;
import clt.com.cn.model.entity.Smuser;
import clt.com.cn.model.entity.SmuserToken;
import clt.com.cn.model.entity.UserApprove;
import clt.com.cn.model.entity.UserSchedule;

public interface IUserService
{
	public void saveUserToken(SmuserToken smuserToken);
	
	public abstract void addUser( Smuser u );
	
	public void saveManagerApproval( ManagerApproval u );
	
	public void saveUserSchedule( UserSchedule u );
	
	public void saveUserApprove( UserApprove us );
	
	public abstract Smuser userLogin( String uname , String upass );
	
	public List< Smuser > getUsersByCondition( String hql , Object ... p );
	
	public abstract List getAllUsers( int page );
	
	public abstract Smuser getUserById( int uid );
	
	// 查询档案信息
	public abstract Employrecord getEmrById( int id );
	
	public abstract void updateUser( Smuser u );
	
	public abstract void deleteUserById( int uid );
	
	public abstract Smuser userName( String uname );
	
	public abstract List userInfo( int admin , int page );
	
	public abstract List userInfoById( String userno , int page );
	
	// MD5加密
	public abstract String MD5( String s );
	
	public abstract int getCount();
	
	public abstract int getCountByAdmin( int admin );
	
	public List< Smuser > getUsersByAdmin( int admin );
	
	public List< Smuser > getUsersBygtAdmin( int admin );
	
	public abstract int getCountByUserno( String userno );
	
	public abstract int getpages( int count , int pageSize );
	
	// 查询角色
	public abstract List< Role > getRolename();
	
	public List getDateBySqlQuery( String sql , int pageSize , int page );
	
	public Smuser getUserByUserno( String userno );
	
	public Smuser getUSerByManagerApproval( Smuser sm );
	
	public int getCountBySql( final String sql );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param token
	 * @return 
	 *   SmuserToken 返回值描述
	 * @author liuwu
	 * @create_date 2015年11月10日 下午3:14:03
	 */ 
    public SmuserToken findByToken( String token );
}