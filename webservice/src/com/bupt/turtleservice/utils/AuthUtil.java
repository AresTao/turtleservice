package com.bupt.turtleservice.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class AuthUtil {
	
	private AuthUtil() {}
	
	public static String getMD5(String seed) throws Exception {  
        String result = null;  
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
                'a', 'b', 'c', 'd', 'e', 'f' };
        
        try {  
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");  
            md.update(seed.getBytes());  
            byte tmp[] = md.digest();
            char str[] = new char[16 * 2];
           
            int k = 0; 
            for (int i = 0; i < 16; i++) {
                str[k++] = hexDigits[tmp[i] >>> 4 & 0xf];
                str[k++] = hexDigits[tmp[i] & 0xf];
            }  
            result = new String(str);
        } catch (Exception e) {  
        	throw new Exception("Fail to load MD5 Algorithm");
        }
        
        return result;  
    }
	
	public static String getHmacSHA1(String data, String key) throws Exception {
		byte[] byteHMAC = null;
		
        try {  
            Mac mac = Mac.getInstance("HmacSHA1");  
            SecretKeySpec spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");  
            mac.init(spec);  
            byteHMAC = mac.doFinal(data.getBytes());  
        } catch (Exception e) {
        	throw new Exception("Fail to load HmacSHA1 Algorithm");
        }
        
        String result = new BASE64Encoder().encode(byteHMAC);  
        return result;
	}
	
	public static String getBASE64(String s) 
	{ 
		if (s == null) return null; 
		return (new sun.misc.BASE64Encoder()).encode( s.getBytes() ); 
	} 
}
