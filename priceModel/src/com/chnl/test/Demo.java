/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-13 下午4:44:19 
 * @version V1.0 
 */
package com.chnl.test;

/** 
 * @Package com.chnl.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-8-13 下午4:44:19 
 * @version V1.0 
 */
public class Demo {
	 public void sortArray(int[] array, int m, int n) {
	  if (m > 0) {
	   if (array[n] < array[n - 1]) {
	    swap(array, n);
	   }
	   if (n >= m) {
	    sortArray(array, m - 1, 1);
	   } else {
	    sortArray(array, m, n + 1);
	   }
	  }
	 }
	 void swap(int[] array, int k) {
	  int temp = array[k];
	  array[k] = array[k - 1];
	  array[k - 1] = temp;
	 }
	 public void showArray(int[] array) {
	  for (int i = 0; i < array.length; i++) {
	   System.out.println(array[i]);
	  }
	 }
	 public static void main(String[] args) {
		int a = 6;
		int b = 2;
		float c;
		
		c = ( float ) ( Math.round( a / b ) / 10.0 );// 这样为保持2位
		System.out.println( c );
	 }
	}