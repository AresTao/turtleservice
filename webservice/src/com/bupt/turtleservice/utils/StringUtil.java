package com.bupt.turtleservice.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil
{
 
    public static boolean isBlank(String str)
    {
        boolean result = false;
        if (str == null || str.equals("") || str.trim().equals(""))
        {
            result = true;
        }
        return result;
    }
    
    public static String getRondomStr(int strLength)
    {
        //!@#$%^&*()_+-=
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";  
        final int BASE_LEN = 36;
                
        Random random = new Random();   
        StringBuffer sb = new StringBuffer();   
        
        for (int i = 0; i < strLength; i++) 
        {   
            int number = random.nextInt(BASE_LEN);   
            sb.append(base.charAt(number));   
        }   
        
        return sb.toString();   
    }
    
    public static Date getExpireTime(int expireMinute)
    {
        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.MINUTE, expireMinute);
        return cl.getTime();
    }
    
    public static boolean isNumeric(String str)
    {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if(!isNum.matches())
        {
            return false;
        }
        return true;
    } 
    
    public static int getIntFromString(String str, int def)
    {
        int value = def;
        if(isNumeric(str))
        {
            value = Integer.parseInt(str);
        }
        return value;
    }
}
