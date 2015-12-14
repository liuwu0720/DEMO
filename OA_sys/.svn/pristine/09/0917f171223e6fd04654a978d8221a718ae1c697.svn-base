package clt.com.cn.service;

import java.util.List;

import clt.com.cn.model.entity.ContractType;
import clt.com.cn.model.entity.Dept;
import clt.com.cn.model.entity.Educationlevel;
import clt.com.cn.model.entity.EmployManager;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Position;

public interface IEmployrecordService
{
	public abstract List getAllEmr2( int page );
	
	public abstract List< Employrecord > getAllEmr();
	
	public abstract Employrecord getEmrById( int id );
	
	public ContractType getContractTypeById( int id );
	
	public List< ContractType > getAllContractType();
	
	public abstract void delEmrById( int id );
	
	public abstract void addEmr( Employrecord emr );
	
	public abstract void updateEmr( Employrecord emr );
	
	// 得到所有部门
	public abstract List< Dept > getAllDept();
	
	// 得到所有职位
	public abstract List< Position > getAllPosition();
	
	// 根据ID得到相应的部门
	public abstract Dept getDeptById( int id );
	
	// 根据ID得到相应的职位
	public abstract Position getPositionById( int id );
	
	// 按条件将进行高级查询
	public abstract List EmrInfoByOther( String lineid , String fileno , String employname );
	
	// 根据员工姓名查询联系方式
	public abstract List< Employrecord > EmrInfoByName( String employname , int page );
	
	// 根据姓名查询档案信息
	public abstract List getEmrUserByName( String employname , int page );
	
	// 根据员工编号查询
	public abstract Employrecord getPersonInfo( String fileno );
	
	public List< Employrecord > getEmrInfo( String hql , Object ... p );
	
	public abstract int getCount();
	
	public abstract int getCountBySQL( String sql );
	
	public abstract int getCountByName( String employname );
	
	public abstract int getpages( int count , int pageSize );
	
	// 分页查询
	public abstract List< Employrecord > getAllEmrByPage( int page );
	
	public List< Educationlevel > getAllEducationlevel();
	
	public List getDateBySqlQuery( String sql , int pageSize , int page );
	
	public List getpageDateBySqlQuery( final String sql , final int page ,
	        final int pageSize );
	
	public Educationlevel getEducationlevelbyID( int id );
	
	public String getMaxEmpuserno();
	
	public void saveEmployManager( EmployManager emp );

	/** 
	 * @Description:这里用一句话描述这个方法的作用
	 * @param lineid
	 * @return 
	 *   Dept 返回值描述
	 * @author liuwu
	 * @create_date 2015年12月2日 下午4:07:09
	 */ 
    public abstract Dept getCompany( int lineid );
}
