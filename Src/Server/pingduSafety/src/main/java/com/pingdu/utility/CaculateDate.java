package com.pingdu.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CaculateDate {

	/**
	 * 此函数实现：给定日期和经过天数，算出结果日期
	 *@author 刘鹏
		其中sDate为指定日期，iDate为多少时间段（可以是 年、月、日...  具体根据iCal来确定）
		iCal为某种时间段例如  月：Calendar.MONTH（具体可查询api中Calendar类）
		sStr为日期格式 例如："yyyyMMdd"（具体可查询api中Calendar类）
	 * @param sDate
	 * @param iDate
	 * @param iCal
	 * @param sStr
	 * @return
	 */
	public static String getNextDate(String sDate, int iDate,int iCal, String sStr){
        String sNextDate = "";
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(sStr);
        Date date = null;
        try {
            date = formatter.parse(sDate);
        } catch (ParseException e) {
        	e.printStackTrace();
        }
        calendar.setTime(date);
        calendar.add(iCal, iDate);
        sNextDate = formatter.format(calendar.getTime());
        return sNextDate ;
    }
	
	
	/**
	 * 返回两个日期之间相差几天
	 * @author 刘鹏
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int daysOfTwo(Date fDate, Date oDate) {

	       Calendar aCalendar = Calendar.getInstance();

	       aCalendar.setTime(fDate);

	       int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

	       aCalendar.setTime(oDate);

	       int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

	       return day2 - day1;

	    }
}
