/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-18 下午2:45:34 
 * @version V1.0 
 */
package com.chnl.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.chnl.pojo.Combination;

import antlr.collections.impl.LList;

/** 
 * @Package com.chnl.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-18 下午2:45:34 
 * @version V1.0 
 */
public class TestList
{	
	public static void main( String[] args )
    {
		//test1();
		test2();
    }

	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用)  
     *   void 返回值描述
     * @author liuwu
     * @create_date 2014-8-18 下午2:59:23
     */ 
    private static void test2()
    {
      
    	 List<Student> stu = new ArrayList<Student>();  
    	 Student stu1 = new Student();
    	 stu1.setId( "1" );
    	 stu1.setName( "f" );
    	 Student stu2 = new Student();
    	 stu2.setId( "2" );
    	 stu2.setName( "f" );
    	 
    	 Student stu3 = new Student();
    	 stu3.setId( "3" );
    	 stu3.setName( "f" );
    	
    	 stu.add( stu2 );
    	 stu.add( stu1 );
    	 stu.add( stu3 );
    
    	 System.out.println(stu1.equals( stu2 ));
        /* stu.add(new Student("1","yi"));  
     
         stu.add(new Student("2","er"));  
         stu.add(new Student("2","er"));*/  
         //set集合保存的是引用不同地址的对象  
         Set<Student> ts = new HashSet<Student>();  
         ts.addAll(stu);  
           
         for (Student student : ts) {  
        	 for(Student student2:ts){
        	     System.out.println(student.getId()+"-"+student2.getId());  
        	 }
        
         }    
         
    /*	 List<Student> listTemp= new ArrayList<Student>();  
         Iterator<Student> it=stu.iterator();  
         while(it.hasNext()){  
   		  Student a=it.next();  
   		  if(listTemp.contains(a)){  
   		   it.remove();  
   		  }  
   		  else{  
   		   listTemp.add(a);  
   		  }  
   		 }  
        
   		System.out.println(listTemp.size());*/
    }

	/** 
     * @Description: TODO(这里用一句话描述这个方法的作用)  
     *   void 返回值描述
     * @author liuwu
     * @create_date 2014-8-18 下午2:58:51
     */ 
    private static void test1()
    {
    	List<Integer> list=new ArrayList<Integer>();  
		   
		 list.add(1);  
		 list.add(2);  
		 list.add(4);  
		 list.add(1);  
		 list.add(2);  
		 list.add(5);  
		 list.add(1);  
		 List<Integer> listTemp= new ArrayList<Integer>();  
		 Iterator<Integer> it=list.iterator();  
		 while(it.hasNext()){  
		  int a=it.next();  
		  if(listTemp.contains(a)){  
		   it.remove();  
		  }  
		  else{  
		   listTemp.add(a);  
		  }  
		 }  
		 for(Integer i:list){  
		  System.out.println(i);  
		 }  
		
	    System.out.println(list.size());
    }
	
}
