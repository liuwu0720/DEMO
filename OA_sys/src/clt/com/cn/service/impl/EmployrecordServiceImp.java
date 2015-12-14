package clt.com.cn.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import clt.com.cn.dao.IDeptDao;
import clt.com.cn.dao.IEducationlevelDao;
import clt.com.cn.dao.IEmployrecordDao;
import clt.com.cn.model.entity.ContractType;
import clt.com.cn.model.entity.Dept;
import clt.com.cn.model.entity.Educationlevel;
import clt.com.cn.model.entity.EmployManager;
import clt.com.cn.model.entity.Employrecord;
import clt.com.cn.model.entity.Position;
import clt.com.cn.service.IEmployrecordService;

public class EmployrecordServiceImp implements IEmployrecordService
{
	private IEmployrecordDao emrDao;
	private IEducationlevelDao educationlevelDao;
	private IDeptDao iDeptDao;
	
	
	/**
	 * @return the iDeptDao
	 */
	public IDeptDao getiDeptDao()
	{
		return iDeptDao;
	}

	/**
	 * @param iDeptDao the iDeptDao to set
	 */
	public void setiDeptDao( IDeptDao iDeptDao )
	{
		this.iDeptDao = iDeptDao;
	}

	public void setEducationlevelDao( IEducationlevelDao educationlevelDao )
	{
		this.educationlevelDao = educationlevelDao;
	}
	
	public void setEmrDao( IEmployrecordDao emrDao )
	{
		this.emrDao = emrDao;
	}
	
	public List getAllEmr2( int page )
	{
		return emrDao.getAllEmr2( page );
	}
	
	public List< Employrecord > getAllEmr()
	{
		return emrDao.getAllEmr();
	}
	
	public Employrecord getEmrById( int id )
	{
		return emrDao.getEmrById( id );
	}
	
	public void delEmrById( int id )
	{
		// 按ID删除(员工档案信息)
		emrDao.delEmrById( id );
	}
	
	public void addEmr( Employrecord emr )
	{
		// 添加员工档案
		emrDao.addEmr( emr );
	}
	
	public void updateEmr( Employrecord emr )
	{
		// 修改员工档案
		emrDao.updateEmr( emr );
	}
	
	public List< Dept > getAllDept()
	{
		// 得到所有部门
		return emrDao.getAllDept();
	}
	
	public List< Position > getAllPosition()
	{
		// 得到所有职位
		return emrDao.getAllPosition();
	}
	
	public Dept getDeptById( int id )
	{
		return emrDao.getDeptById( id );
	}
	
	public Position getPositionById( int id )
	{
		// TODO Auto-generated method stub
		return emrDao.getPositionById( id );
	}
	
	public List EmrInfoByOther( String lineid , String fileno , String employname )
	{
		
		String hql = "select em.lineid,em.fileno,em.employname,de.deptname,po.positionname "
		        + " from Employrecord as em,Dept as de,Position as po where em.deptid=de.lineid and em.positionid=po.lineid ";
		if ( ! lineid.equals( "搜索信息（档案号）" ) && lineid != "" && lineid != null )
		{
			hql = hql + " and em.lineid=" + lineid;
		}
		if ( ! fileno.equals( "搜索信息（员工编号）" ) && fileno != "" && fileno != null )
		{
			hql = hql + " and em.fileno='" + fileno + "'";
		}
		System.out.println( ! fileno.equals( "搜索信息（员工编号）" ) && fileno != ""
		        && fileno != null );
		if ( ! employname.equals( "搜索信息（员工姓名）" ) && employname != ""
		        && employname != null )
		{
			hql = hql + "and em.employname='" + employname + "'";
		}
		
		List ulist = emrDao.getEmrInfo( hql );
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
		
	}
	
	// 根据员工姓名查询
	public List< Employrecord > EmrInfoByName( String employname , int page )
	{
		// String
		// hql="from Employrecord em where em.employname='"+employname+"'";
		List< Employrecord > ulist = emrDao.getAllEmrByName( employname , page );
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	
	// 根据员工编号查询
	public Employrecord getPersonInfo( String fileno )
	{
		String hql = "from Employrecord em where em.fileno='" + fileno + "'";
		List< Employrecord > ulist = emrDao.getEmrInfo( hql );
		if ( ulist.size() > 0 )
		{
			return ulist.get( 0 );
		}
		else
		{
			return null;
		}
	}
	
	// 总条数
	public int getCount()
	{
		return emrDao.getCount();
	}
	
	public int getCountByName( String employname )
	{
		return emrDao.getCountByName( employname );
	}
	
	public int getpages( int count , int pageSize )
	{
		// 获得总面数
		return emrDao.getpages( count , pageSize );
	}
	
	public List< Employrecord > getAllEmrByPage( int page )
	{
		// 分页查询
		return emrDao.getAllEmrByPage( page );
	}
	
	public List getEmrUserByName( String employname , int page )
	{
		// 根据员工姓名查询档案信息
		List< Employrecord > ulist = emrDao.getEmrUserByName( employname , page );
		if ( ulist.size() > 0 )
		{
			return ulist;
		}
		else
		{
			return null;
		}
	}
	
	public List< Educationlevel > getAllEducationlevel()
	{
		// TODO Auto-generated method stub
		return educationlevelDao.getAllEducationlevel();
	}
	
	public int getCountBySQL( String sql )
	{
		// TODO Auto-generated method stub
		return emrDao.getCountBySQL( sql );
	}
	
	public List getDateBySqlQuery( String sql , int pageSize , int page )
	{
		// TODO Auto-generated method stub
		return emrDao.getDateBySqlQuery( sql , pageSize , page );
	}
	
	public List getpageDateBySqlQuery( String sql , int page , int pageSize )
	{
		// TODO Auto-generated method stub
		return emrDao.getpageDateBySqlQuery( sql , page , pageSize );
	}
	
	public Educationlevel getEducationlevelbyID( int id )
	{
		// TODO Auto-generated method stub
		return educationlevelDao.getEducationlevelById( id );
	}
	
	public List< Employrecord > getEmrInfo( String hql , Object ... p )
	{
		// TODO Auto-generated method stub
		return emrDao.getEmrInfo( hql , p );
	}
	
	public String getMaxEmpuserno()
	{
		String sql = " select max(e.empuserno) from employrecord e ";
		List empnos = emrDao.getDateBySqlQuery( sql , 0 , 0 );
		DecimalFormat df = new DecimalFormat( "0000" );
		String str2 = "";
		if ( empnos.size() == 0 )
		{
			str2 = df.format( Integer.parseInt( "1" ) );
			
		}
		else
		{
			Object obj = empnos.get( 0 );
			
			str2 = df.format( Integer.parseInt( obj + "" ) + 1 );
		}
		
		System.out.println( "Max Empuserno  " + str2 );
		return str2;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param emp
	 * @author chenbin
	 * @create_date 2014-8-26 下午7:26:18
	 */
	public void saveEmployManager( EmployManager emp )
	{
		// TODO Auto-generated method stub
		emrDao.saveEmployManager( emp );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-25 下午4:57:41
	 */
	public ContractType getContractTypeById( int id )
	{
		// TODO Auto-generated method stub
		return emrDao.getContractTypeById( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-25 下午5:17:30
	 */
	public List< ContractType > getAllContractType()
	{
		// TODO Auto-generated method stub
		return emrDao.getAllContractType();
	}
	
	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param lineid
	 * @return 
	 * @author liuwu
	 * @create_date 2015年12月2日 下午4:07:32
	 */
	public Dept getCompany( int lineid )
	{
		Dept dept = iDeptDao.getDeptById( lineid );
		
		while ( dept.getPid() != 0 )
		{
			dept = iDeptDao.getDeptById( dept.getPid() );
		}
		
		return dept;
	}
}
