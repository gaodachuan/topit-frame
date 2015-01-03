 package com.topit.frame.common.util;

import java.security.MessageDigest;
 

 /** 
* @ClassName: MD5encrypt 
* @Description: md5加密 
* @author qiugui
* @date 2014年12月5日 下午2:35:50 
*  
*/ 
public class MD5Encrypt {

	 public static String encipher(String strs){
		 char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b',
				 'c','d','e','f'};
		 
		 try {
			byte[] strTemp=strs.getBytes();
			
			MessageDigest messageDigestTemp=MessageDigest.getInstance("MD5");
			
			messageDigestTemp.update(strTemp);
			
			byte[] messageDigest=messageDigestTemp.digest();
			
			int j=messageDigest.length;
			
			char str[]=new char[j*2];
			
			int k=0;
			
			for(int i=0;i<j;i++){
				byte byte0=messageDigest[i];
				
				str[k++]=hexDigits[byte0 >>> 4 & 0xf];
				str[k++]=hexDigits[byte0 & 0xf];
			}
			
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

 