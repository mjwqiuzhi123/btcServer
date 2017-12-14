package com.btc.app.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date dateMinutesSub(Date nowDate, int minutes)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar ca = Calendar.getInstance();
		ca.setTime(nowDate);
		ca.set(12, ca.get(12) - minutes);
		System.out.println(sdf.format(ca.getTime()));
		sdf.parse(sdf.format(ca.getTime()));
		return ca.getTime();
	}

	public static String dateMinutesAdd(Date nowDate, int minutes)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar ca = Calendar.getInstance();
		ca.setTime(nowDate);
		ca.set(12, ca.get(12) + minutes);
		System.out.println(sdf.format(ca.getTime()));
		return sdf.format(ca.getTime());
	}

	public static Date returnDateTime(String dateTimeStr, String format)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateTimeStr);
	}

	public static Date GetDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(sdf.format(date));
		} catch (ParseException e) {
		}
		return date;
	}

	public static String millisecondString(String format) {
		DateFormat dformat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		return dformat.format(Long.valueOf(cal.getTimeInMillis()));
	}

	public static String GetStringDate(Date nowDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar ca = Calendar.getInstance();
		ca.setTime(nowDate);
		return sdf.format(ca.getTime());
	}

	public static String GetStringDate(Date nowDate, String fromat)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(fromat);
		Calendar ca = Calendar.getInstance();
		ca.setTime(nowDate);
		return sdf.format(ca.getTime());
	}

	public static String GetChineseYMD(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(1) + "年" + (ca.get(2) + 1) + "" + "月" + ca.get(5) + "日";
	}

	public static long diffInDays(Date dateBegin, Date dateEnd)
			throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String dateBeginString = sf.format(dateBegin);
		String dateEndString = sf.format(dateEnd);
		Date dateBeginDate = sf.parse(dateBeginString);
		Date dateEndDate = sf.parse(dateEndString);
		long days = (dateEndDate.getTime() - dateBeginDate.getTime()) / 86400000L;
		return days;
	}

	public static Date getAfterDays(Date baseDate, int days) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(baseDate);
		ca.add(5, days);
		return ca.getTime();
	}

	public static Date formatDate(Date date) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.parse(sf.format(date));
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd")
				.format(formatDate(new Date())));
		System.out.println(getAfterDays(new Date(), -10));
	}
}