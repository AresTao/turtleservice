package com.bupt.turtleservice.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public class StreamUtil {
	
	private StreamUtil() {}
	
	private final static int BUFFER_SIZE = 1024;
	private final static int MAX_CONTENT_LENGTH = 1024 * 1024;
	
	public static byte[] inputStream2ByteArray(InputStream inStream) throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] data = new byte[BUFFER_SIZE];  
        int count = -1;  
        long totalCount = 0;
        while((count = inStream.read(data, 0, BUFFER_SIZE)) != -1) {
        	outStream.write(data, 0, count);  
        	totalCount += count;
        	
        	if (totalCount > MAX_CONTENT_LENGTH) {
        		throw new IOException("Content length larger than max threshold 1M");
        	}
        }
            
        return outStream.toByteArray();  
	}
	
	public static String inputStream2String(InputStream inStream) throws IOException {
        return inputStream2String(inStream, "ISO-8859-1");
	}
	
	public static String inputStream2String(InputStream inStream, String encoding) throws IOException {
        return new String(inputStream2ByteArray(inStream), encoding);
	}
	
	public static InputStream string2InputStream(String str) throws IOException {
		ByteArrayInputStream inStream = new ByteArrayInputStream(str.getBytes());    
		return inStream;
	}
	
	public static InputStream byteArray2InputStream(byte[] data) {
		ByteArrayInputStream inStream = new ByteArrayInputStream(data);    
		return inStream;
	}
	
	public static JSONObject getRequestJsonObject(HttpServletRequest req) throws Exception
    {
        StringBuffer stringBuffer = new StringBuffer();
        JSONObject jsonObject = null;
        String strLine = null;
        
        try 
        {  
            BufferedReader bufferedReader = req.getReader(); 
            while(null != (strLine = bufferedReader.readLine()))
            {  
            	stringBuffer.append(strLine);  
            }  
            jsonObject = JSONObject.fromObject(stringBuffer.toString());
        }  
        catch(Exception e) 
        {  
           throw new Exception("Invalid request json format");
        }  
        
        return jsonObject;
    }

}
