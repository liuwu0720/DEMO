package clt.com.cn.model.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Employrecord entity. @author MyEclipse Persistence Tools
 */

public class Employrecord implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6872436932226571345L;
	private Integer lineid;
	private Integer positionid;
	private Dept dept;
	private String fileno;
	private String employname;
	private Date comedate;
	private String mobile;
	private String email;
	private String tel1;
	private String tel2;
	private String telline;
	private String contactno;
	private String cardno;
	private Date birthday;
	private String accountadd;
	private Integer marriage;
	private String address;
	private String hometel;
	private String emergencycontact;
	private String emergencytel;
	private Educationlevel educationlevel;
	private String school;
	private Date graduationdate;
	private String technology;
	private String resume;
	private String userno;
	private String filename;
	private String filepath;
	private Date currdate;
	private Integer status;
	private Integer hrstatus;
	private Integer itstatus;
	
	private String hrprepare;
	private String itprepare;
	
	private DeptAddress deptaddress;
	private String empuserno;
	private ContractType contractType;
	
	private Set employouts = new HashSet( 0 );
	private Set employholidaies = new HashSet( 0 );
	private Set smfiles = new HashSet( 0 );
	
	private String bankcardno;
	
	private String certificate;
	
	public String getBankcardno()
	{
		return bankcardno;
	}
	
	public void setBankcardno( String bankcardno )
	{
		this.bankcardno = bankcardno;
	}
	
	public String getCertificate()
	{
		return certificate;
	}
	
	public void setCertificate( String certificate )
	{
		this.certificate = certificate;
	}
	
	// Constructors
	
	public DeptAddress getDeptaddress()
	{
		return deptaddress;
	}
	
	public void setDeptaddress( DeptAddress deptaddress )
	{
		this.deptaddress = deptaddress;
	}
	
	/** default constructor */
	public Employrecord()
	{}
	
	/** minimal constructor */
	public Employrecord( Integer lineid , Integer positionid , Integer deptid ,
	        String fileno , String employname , Date comedate , String cardno ,
	        String filename , String filepath , Date birthday , Integer marriage ,
	        String address , Integer educationlevel , Date currdate )
	{
		this.lineid = lineid;
		this.positionid = positionid;
		this.fileno = fileno;
		this.employname = employname;
		this.comedate = comedate;
		this.cardno = cardno;
		this.birthday = birthday;
		this.marriage = marriage;
		this.address = address;
		this.currdate = currdate;
		this.filename = filename;
		this.filepath = filepath;
	}
	
	/** full constructor */
	public Employrecord( Integer lineid , Integer positionid , Integer deptid ,
	        String fileno , String employname , Date comedate , String mobile ,
	        String email , String tel1 , String tel2 , String contactno , String cardno ,
	        Date birthday , String accountadd , Integer marriage , String address ,
	        String hometel , String emergencycontact , String emergencytel ,
	        Integer educationlevel , String school , Date graduationdate ,
	        String technology , String resume , String userno , Date currdate ,
	        String filename , String filepath , Set employouts , Set employholidaies ,
	        Set smfiles )
	{
		this.lineid = lineid;
		this.positionid = positionid;
		this.fileno = fileno;
		this.employname = employname;
		this.comedate = comedate;
		this.mobile = mobile;
		this.email = email;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.contactno = contactno;
		this.cardno = cardno;
		this.birthday = birthday;
		this.accountadd = accountadd;
		this.marriage = marriage;
		this.address = address;
		this.hometel = hometel;
		this.emergencycontact = emergencycontact;
		this.emergencytel = emergencytel;
		this.school = school;
		this.graduationdate = graduationdate;
		this.technology = technology;
		this.resume = resume;
		this.userno = userno;
		this.currdate = currdate;
		this.employouts = employouts;
		this.employholidaies = employholidaies;
		this.smfiles = smfiles;
		this.filename = filename;
		this.filepath = filepath;
	}
	
	// Property accessors
	
	public Integer getLineid()
	{
		return this.lineid;
	}
	
	public String getTelline()
	{
		return telline;
	}
	
	public void setTelline( String telline )
	{
		this.telline = telline;
	}
	
	public Integer getStatus()
	{
		return status;
	}
	
	public void setStatus( Integer status )
	{
		this.status = status;
	}
	
	public String getHrprepare()
	{
		return hrprepare;
	}
	
	public void setHrprepare( String hrprepare )
	{
		this.hrprepare = hrprepare;
	}
	
	public String getItprepare()
	{
		return itprepare;
	}
	
	public void setItprepare( String itprepare )
	{
		this.itprepare = itprepare;
	}
	
	public void setLineid( Integer lineid )
	{
		this.lineid = lineid;
	}
	
	public Integer getHrstatus()
	{
		return hrstatus;
	}
	
	public void setHrstatus( Integer hrstatus )
	{
		this.hrstatus = hrstatus;
	}
	
	public Integer getItstatus()
	{
		return itstatus;
	}
	
	public void setItstatus( Integer itstatus )
	{
		this.itstatus = itstatus;
	}
	
	public Integer getPositionid()
	{
		return positionid;
	}
	
	public void setPositionid( Integer positionid )
	{
		this.positionid = positionid;
	}
	
	public String getFileno()
	{
		return this.fileno;
	}
	
	public void setFileno( String fileno )
	{
		this.fileno = fileno;
	}
	
	public String getEmployname()
	{
		return this.employname;
	}
	
	public void setEmployname( String employname )
	{
		this.employname = employname;
	}
	
	public Date getComedate()
	{
		return this.comedate;
	}
	
	public void setComedate( Date comedate )
	{
		this.comedate = comedate;
	}
	
	public String getMobile()
	{
		return this.mobile;
	}
	
	public void setMobile( String mobile )
	{
		this.mobile = mobile;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public void setEmail( String email )
	{
		this.email = email;
	}
	
	public String getTel1()
	{
		return this.tel1;
	}
	
	public void setTel1( String tel1 )
	{
		this.tel1 = tel1;
	}
	
	public String getTel2()
	{
		return this.tel2;
	}
	
	public void setTel2( String tel2 )
	{
		this.tel2 = tel2;
	}
	
	public String getContactno()
	{
		return this.contactno;
	}
	
	public void setContactno( String contactno )
	{
		this.contactno = contactno;
	}
	
	public String getCardno()
	{
		return this.cardno;
	}
	
	public void setCardno( String cardno )
	{
		this.cardno = cardno;
	}
	
	public Date getBirthday()
	{
		return this.birthday;
	}
	
	public void setBirthday( Date birthday )
	{
		this.birthday = birthday;
	}
	
	public String getAccountadd()
	{
		return this.accountadd;
	}
	
	public void setAccountadd( String accountadd )
	{
		this.accountadd = accountadd;
	}
	
	public Integer getMarriage()
	{
		return this.marriage;
	}
	
	public void setMarriage( Integer marriage )
	{
		this.marriage = marriage;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public void setAddress( String address )
	{
		this.address = address;
	}
	
	public String getHometel()
	{
		return this.hometel;
	}
	
	public void setHometel( String hometel )
	{
		this.hometel = hometel;
	}
	
	public String getEmergencycontact()
	{
		return this.emergencycontact;
	}
	
	public void setEmergencycontact( String emergencycontact )
	{
		this.emergencycontact = emergencycontact;
	}
	
	public String getEmergencytel()
	{
		return this.emergencytel;
	}
	
	public void setEmergencytel( String emergencytel )
	{
		this.emergencytel = emergencytel;
	}
	
	public Educationlevel getEducationlevel()
	{
		return educationlevel;
	}
	
	public void setEducationlevel( Educationlevel educationlevel )
	{
		this.educationlevel = educationlevel;
	}
	
	public String getSchool()
	{
		return this.school;
	}
	
	public void setSchool( String school )
	{
		this.school = school;
	}
	
	public Date getGraduationdate()
	{
		return this.graduationdate;
	}
	
	public void setGraduationdate( Date graduationdate )
	{
		this.graduationdate = graduationdate;
	}
	
	public String getTechnology()
	{
		return this.technology;
	}
	
	public void setTechnology( String technology )
	{
		this.technology = technology;
	}
	
	public String getResume()
	{
		return this.resume;
	}
	
	public void setResume( String resume )
	{
		this.resume = resume;
	}
	
	public String getUserno()
	{
		return this.userno;
	}
	
	public Dept getDept()
	{
		return dept;
	}
	
	public void setDept( Dept dept )
	{
		this.dept = dept;
	}
	
	public void setUserno( String userno )
	{
		this.userno = userno;
	}
	
	public Date getCurrdate()
	{
		return this.currdate;
	}
	
	public void setCurrdate( Date currdate )
	{
		this.currdate = currdate;
	}
	
	public Set getEmployouts()
	{
		return this.employouts;
	}
	
	public void setEmployouts( Set employouts )
	{
		this.employouts = employouts;
	}
	
	public Set getEmployholidaies()
	{
		return this.employholidaies;
	}
	
	public void setEmployholidaies( Set employholidaies )
	{
		this.employholidaies = employholidaies;
	}
	
	public Set getSmfiles()
	{
		return this.smfiles;
	}
	
	public void setSmfiles( Set smfiles )
	{
		this.smfiles = smfiles;
	}
	
	public String getFilename()
	{
		return filename;
	}
	
	public void setFilename( String filename )
	{
		this.filename = filename;
	}
	
	public String getFilepath()
	{
		return filepath;
	}
	
	public void setFilepath( String filepath )
	{
		this.filepath = filepath;
	}
	
	public String getEmpuserno()
	{
		return empuserno;
	}
	
	public void setEmpuserno( String empuserno )
	{
		this.empuserno = empuserno;
	}
	
	public ContractType getContractType()
	{
		return contractType;
	}
	
	public void setContractType( ContractType contractType )
	{
		this.contractType = contractType;
	}
	
}