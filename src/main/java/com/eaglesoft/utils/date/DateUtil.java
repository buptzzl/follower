package com.eaglesoft.utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final SimpleDateFormat DF_YYYY_MM_DD_HH_MI_SS=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat DF_HH_MI_SS=new SimpleDateFormat("HH:mm:ss");
	public static final SimpleDateFormat DF_YYYY_MM_DD=new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat DF_YYYY_MM_DD_HH_MI_SS2=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	
	
	public static Date addMinutes(Date baseTime, int addingMinutes) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(baseTime);
	    cal.add(Calendar.MINUTE, addingMinutes);
	    return cal.getTime();
	}
	
	public static Date minusMinutes(Date baseTime, int addingMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(baseTime);
        cal.add(Calendar.MINUTE, 0-addingMinutes);
        return cal.getTime();
    }
}
