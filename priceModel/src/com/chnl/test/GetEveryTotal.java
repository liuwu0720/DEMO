/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-21 下午2:35:51 
 * @version V1.0 
 */
package com.chnl.test;

import java.util.*;

/** 
 * @Package com.chnl.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-21 下午2:35:51 
 * @version V1.0 
 */
public class GetEveryTotal
{	
	public static void main(String[] args) {  
        // TODO Auto-generated method stub  
		List< Integer > list = new ArrayList< Integer >();
		list.add( 1 );
		list.add( 1 );
		
		
      HashMap< Integer , Double > map = new HashMap< Integer , Double >();
      for(int a:list){
    	  if(map.containsKey( a )){
    		map.put( a , map.get( a )+a );  
    	  }else {
			map.put( a , 2.6 );
		}
      }
      
      System.out.println(map);
    }  
}
