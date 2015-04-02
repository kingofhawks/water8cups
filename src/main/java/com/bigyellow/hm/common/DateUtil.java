package com.bigyellow.hm.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;


public class DateUtil {
	private static SimpleDateFormat formatter1 =
		new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss", new Locale("en", "US"));
	
	public static final String patten_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    
    public static final String[] patterns = { "yyyy-MM-dd", 
                                               "yyyy-MM-dd HH:mm:ss",
                                               "yyyy-MM-dd'T'hh:mm:ss", 
                                               "yyyy-MM-dd'T'hh:mm:ss'Z'" };
    
    private static final SimpleDateFormat format_minute = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
    public static final String[] SUBTIME = { "HH:mm", "dd HH:mm", "MM-dd HH:mm", "yyyy-MM-dd HH:mm" };
    
	public static Date parse(String s){
    	Date result = null;
		try {
            result = DateUtils.parseDate(s, patterns);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
	}
	
	public static Date parse(String s, String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
    	Date result = null;
		try {
            result = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
	}
    
    public static Date parseAsGMT(String date, String pattern) throws ParseException {
    	
        SimpleDateFormat formatter = new SimpleDateFormat(pattern,Locale.UK);
        formatter.setTimeZone(DateUtils.UTC_TIME_ZONE);
        Date d = null;
        try {
            d = formatter.parse(date);
        } catch (ParseException e) {
            throw e;
        }
        return d;
    }
	
	public static String format(Date d) {
		return formatter1.format(d);
	}
    
    public static String format(Date date, String pattern) {
        return FastDateFormat.getInstance(pattern).format(date);
    }
    
    public static String format(Long time, String pattern){
    	return FastDateFormat.getInstance(pattern).format(time);
    }
    
    public static String format(Date date, String pattern, TimeZone timezone) {
        return FastDateFormat.getInstance(pattern, timezone).format(date);
    }
    
    public static String formatAsGMT(Date date, String pattern) {
        return FastDateFormat.getInstance(pattern, DateUtils.UTC_TIME_ZONE).format(date);
    }
    
    public static String extendMonth(int n) {
        Calendar cal = Calendar.getInstance();
        return extendMonth(cal.getTime(), n);
    }
    
    public static String extendMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        int month = cal.get(Calendar.MONTH);        
        if ((month + n) > 12) {
            cal.set(Calendar.MONTH, (month + n) % 12);
            cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
        }
        
        return FastDateFormat.getInstance("yyyy-MM-dd").format(cal);
    }
    
    /**
     * get the long value of 00:00 of the day
     * @return
     */
	public static long getTodayStart() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.MILLISECOND, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.HOUR_OF_DAY, 0);
		return today.getTimeInMillis();
    }
	
    public static long getTimeStart(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
    }
    
    public static String getPeriodByTimeMillis(long timeMillis){
		StringBuffer str = new StringBuffer();
		long day = (timeMillis / 1000) / 86400;
		long hr = (timeMillis / 1000 - day * 86400) / 3600;
		long minute = (timeMillis / 1000 - day * 86400 - hr * 3600) / 60;
		str.append(day).append(" days ");
		if (hr < 10) {
			str.append('0').append(hr);
		} else {
			str.append(hr);
		}
		str.append(" hrs ");
		if (minute < 10) {
			str.append('0').append(minute);
		} else {
			str.append(minute);
		}
		str.append(" mins");
		return str.toString();
	}
    
    public static String getUpTimeByTimeMillis(long timeMillis){
		StringBuffer str = new StringBuffer();
		long day = (timeMillis / 1000) / 86400;
		long hr = (timeMillis / 1000 - day * 86400) / 3600;
		long minute = (timeMillis / 1000 - day * 86400 - hr * 3600) / 60;
		long second = timeMillis / 1000 - day * 86400 - hr * 3600 - minute*60;
		long millisecond  = timeMillis -  (timeMillis/1000)*1000 ;
		if(day != 0){
			str.append(day).append(" days ");
		}
		
		if(hr != 0){
			str.append(hr);
			str.append(" hrs ");
		}
		
		if(minute != 0) {
			str.append(minute);
			str.append(" mins ");
		}
		
		if(second != 0) {
			str.append(second);
			str.append(" seconds ");
		}
		
		if(millisecond != 0) {
			str.append(millisecond);
			str.append(" milliseconds");
		}
		
		return str.toString();
	}
    /**
     * get latest half hour, for example,
     * if current is 2009-10-10 12:31:30, return 2009-10-10 12:30:00
     * if current is 2009-10-10 12:12:30, return 2009-10-10 12:00:00
     * @param pattern
     * @return
     */
    public static long getLatestHalfHour(){
    	Calendar cal = Calendar.getInstance();
    	int minute = cal.get(Calendar.MINUTE);
    	cal.set(Calendar.SECOND, 0);
    	if(minute<30){
    		cal.set(Calendar.MINUTE, 0);
    	}else{
    		cal.set(Calendar.MINUTE, 30);
    	}
    	return cal.getTimeInMillis();

    }
    
    /**
     * get current time in minute
     * @return
     */
    public static String getCurrentTimeStrInMinute(){
    	Calendar cal = Calendar.getInstance();
    	Date dt = cal.getTime();
    	return format_minute.format(dt);
    }
    
    /**
     * get current time in minute
     * @return
     */
    public static Date getCurrentTimeInMinute(){
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    	return cal.getTime();
    }       
    
    /**
     * get current time minus milisecond in minute
     * @param milisecond
     * @return
     */
    public static String getTimeInMinuteCurrentMinus(long milisecond){
    	Calendar cal = Calendar.getInstance();
    	long time = cal.getTimeInMillis() - milisecond;
    	cal.setTimeInMillis(time);
    	Date dt = cal.getTime();
    	return format_minute.format(dt);
    }
    
    /**
     * parse long to time in minute
     * @param time
     * @return
     */
    public static String long2TimeInMinute(long time){
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(time);
    	Date dt = cal.getTime();
    	return format_minute.format(dt);    	
    }
    
    public static void main(String[] args){
    	
    	System.out.println(getUpTimeByTimeMillis(1900));
    }
    
    public static final String getSubTimeValue(long time, String subTimeFormat) {
		String result = "";
		String date = DateUtil.format(time, "yyyy-MM-dd HH:mm:00");
		int hhmmIndex = date.indexOf(" ");
		int ssIndex = date.lastIndexOf(":");
		String hhmm = date.substring(hhmmIndex + 1, ssIndex);
		String ddhhmm = date.substring(hhmmIndex - 2, ssIndex);
		String mmddhhmm = date.substring(hhmmIndex - 5, ssIndex);
		String yymmddhhmm = date.substring(0, ssIndex);
		if(subTimeFormat.equals(SUBTIME[0])) {
			return hhmm;
		}
		if(subTimeFormat.equals(SUBTIME[1])) {
			return ddhhmm;
		}
		if(subTimeFormat.equals(SUBTIME[2])) {
			return mmddhhmm;
		}
		if(subTimeFormat.equals(SUBTIME[3])) {
			return yymmddhhmm;
		}
		return result;
	}
}