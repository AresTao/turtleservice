package com.bupt.turtleservice.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil
{
	/**
	 * 
	 * @param date
	 * @return 'yyyyMMdd'
	 */
	public static String getYMDTime(Date date) {
		return getSimpleDateFormat("yyyyMMdd").format(date);
	}
	
	public static Date getYMDDate(String strDate) throws Exception {
		return getSimpleDateFormat("yyyyMMdd").parse(strDate);
	}
	
	/**
	 * 
	 * @param date
	 * @return 'yyyyMMddHHmmss'
	 */
	public static String getYMDHMSTime(Date date) {
		return getSimpleDateFormat("yyyyMMddHHmmss").format(date);
	}
	
	/**
	 * 
	 * @param date
	 * @return EEE, d MMM yyyy HH:mm:ss 'GMT'
	 */
	public static String getGMT(Date date) {
		return getSimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US).format(date);
	}
	
	/**
	 * 
	 * @param date
	 * @return 'yyyy-MM-dd HH:mm:ss'
	 */
	public static String getDatetime(Date date) {
		return getSimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	// helper function
	private static DateFormat getSimpleDateFormat(String pattern)
	{
		return new SimpleDateFormat(pattern);
	}
	
	private static DateFormat getSimpleDateFormat(String pattern, Locale locale) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		return sdf;
	}
	
	public static String getCurYMDHMS()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curDate = format.format(new Date());
		return curDate;
	}
	
	public static String getCurYMD_Start()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = format.format(new Date());
		return curDate + " 00:00:00";
	}
	
    public static String getDistanceDay(String date, int day)
    {
    	String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date d;
        Calendar c = Calendar.getInstance();
        try
        {
            d = dateFormat.parse(date);
            c.setTime(d);
            c.add(Calendar.DATE, day);
        } 
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return dateFormat.format(c.getTime());
    }
    
    public static Date getDistanceDay(Date date, int day)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
		c.add(Calendar.DATE, day);
        return c.getTime();
    }
    
    public static long calDayDistance(Date start, Date end)
    {
    	long diff = end.getTime() - start.getTime();
    	long distance = diff / (1000 * 60 * 60 * 24);
    	return distance;
    }
    
    public static String getURIParameterDate(String date)
    {	
    	String result = (date + "%2B08:00").replaceAll(" ", "T");
    	return result;
    }
    
    public static Date parseStringToDate(String time)
    {
    	SimpleDateFormat sdf = null;
    	Date res = null;
    	try
    	{
    		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		res = sdf.parse(time);
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	return res;
    }
    
    public static Date parseStringToDateToDay(String time)
    {
    	SimpleDateFormat sdf = null;
    	Date res = null;
    	try
    	{
    		sdf = new SimpleDateFormat("yyyy-MM-dd");
    		res = sdf.parse(time);
    	} catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	return res;
    }
}
