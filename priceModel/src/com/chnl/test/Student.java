/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-8-18 下午3:17:29
 * @version V1.0
 */
package com.chnl.test;



/**
 * @Package com.chnl.test
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-8-18 下午3:17:29
 * @version V1.0
 */
public class Student
{
	
	public String id ;
	public String name;
	public String password;
	
	public Student()
	{}
	
	public Student( String id , String name ,String password)
	{
		
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public String getPassword()
    {
    	return password;
    }

	public void setPassword( String password )
    {
    	this.password = password;
    }

	/**
     * @param string
     */
    public Student( String string )
    {
	    // TODO Auto-generated constructor stub
    }

	public String getId()
	{
		return id;
	}
	
	public void setId( String id )
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName( String name )
	{
		this.name = name;
	}

	@Override
    public int hashCode()
    {
	    // TODO Auto-generated method stub
	    return super.hashCode();
    }

	@Override
    public boolean equals( Object obj )
    {
		if(obj==null){
			return false;
		}else {
			if(this.getClass()== obj.getClass() ){
				Student student = ( Student ) obj;
				if(this.getName().equals( student.getName() ) && this.getPassword().equals( student.getPassword() )){
					return true;
				}else {
					return false;
				}
				
			}else {
				return false;
			}
		}
	   
    }

	@Override
    public String toString()
    {
		  
	    return super.toString();
    }




/*	@Override
	public boolean equals( Object obj )
	{
		Student s = ( Student ) obj;
		System.out.println(s.name);
		return name.equals( s.name );
	}
	
	@Override
	public int hashCode()
	{
		String in = name;
		return in.hashCode();
	}*/
	public static void main( String[] args )
    {
	    Student student = new Student();
	    Student student2 = new Student();
	    System.out.println(student.getId()== null);
	    		
    }
}