/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-19 下午5:21:54 
 * @version V1.0 
 */
package com.chnl.test;


/** 
 * @Package com.chnl.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-19 下午5:21:54 
 * @version V1.0 
 */
public class MathPow
{	
	public static void main( String[] args )
    {
		float ration = ( Float.parseFloat( 2 + "" ) / Float
		        .parseFloat( 11 + "" ) ) * 33;
		System.out.println( ration );
    }
	


	private static double factorial( int num )
	    {
		   double sum=1;
	        if(num < 0)
	            throw new IllegalArgumentException("必须为正整数!");//抛出不合理参数异常
	        if(num==1){
	            return 1;//根据条件,跳出循环
	        }else{
	            sum=num * factorial(num-1);//运用递归计算
	            return sum;
	        }
		    
	    }
}
