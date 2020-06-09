package common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @ClassName: TimeUtil
 * @Description: 时间操作工具类
 * @author Daomin.Fu
 * @date 2018年5月28日 下午3:46:07
 */
public class TimeKit {

	/**
	 * 获取当前系统时间
	 * 
	 * @param pattern
	 * @return String
	 */
	public static String getNowTime(String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间yyyy-MM-dd HH:mm:ss
	}

	public static String getNowTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间yyyy-MM-dd HH:mm:ss
	}

	/**
	 * @author 李永宁 2018年9月29日下午2:06:30
	 *
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String getNowTime2() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间yyyy-MM-dd HH:mm:ss
	}

	/**
	 * @author 李永宁 2018年9月29日下午2:06:40
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getNowTime3() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间yyyy-MM-dd HH:mm:ss
	}

	public static String getYear() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间yyyy-MM-dd HH:mm:ss
	}

	public static String date2String(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		return df.format(date);// new Date()为获取当前系统时间yyyy-MM-dd HH:mm:ss
	}

	/**
	 * excel时间戳转化时间
	 * 
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String excelToDate(String time, String pattern) {

		try {
			Integer str = Integer.parseInt(time);
			str = (str - 19 - 70 * 365) * 86400 - 8 * 3600;

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat out = new SimpleDateFormat(pattern);
			Date date = null;
			try {
				date = format.parse("1970-01-01 08:00:00");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.add(Calendar.SECOND, str);
			return out.format(now.getTime());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @Title: getTimeAddYear
	 * @Description: 给当前时间追加年
	 * @param time
	 * @param num
	 * @param pattern
	 * @return
	 * @author Daomin.Fu
	 * @date 2018年8月7日 上午11:27:12
	 */
	public static String getTimeAddYear(String time, Integer num, String pattern) {

		try {
			// 设置日期格式
			SimpleDateFormat df = new SimpleDateFormat(pattern);

			Date nowDate = df.parse(time);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(nowDate);
			calendar.add(Calendar.YEAR, num);

			return df.format(calendar.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Title: getTimeAddMonth
	 * @Description: 给当前时间追加月份
	 * @param time
	 *            当前时间
	 * @param num
	 *            追加月份
	 * @param pattern
	 *            时间格式
	 * @return
	 * @author Daomin.Fu
	 * @date 2018年5月14日 下午6:46:02
	 */
	public static String getTimeAddMonth(String time, Integer num, String pattern) {

		try {
			// 设置日期格式
			SimpleDateFormat df = new SimpleDateFormat(pattern);

			Date nowDate = df.parse(time);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(nowDate);
			calendar.add(Calendar.MONTH, num);

			return df.format(calendar.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Title: getTimeAddDay
	 * @Description: 当前时间追加天
	 * @param time
	 *            当前时间
	 * @param num
	 *            需追加的天数
	 * @param pattern
	 *            日期格式
	 * @return
	 * @author Daomin.Fu
	 * @date 2018年5月14日 下午6:25:42
	 */
	public static String getTimeAddDay(String time, Integer num, String pattern) {

		try {
			// 设置日期格式
			SimpleDateFormat df = new SimpleDateFormat(pattern);

			Date nowDate = df.parse(time);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(nowDate);
			calendar.add(Calendar.DATE, num);

			return df.format(calendar.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Title: getTimeAddHour
	 * @Description: 给指定时间追加小时
	 * @param date
	 * @param pattern
	 * @param num
	 * @return
	 * @author Daomin.Fu
	 * @date 2018年5月14日 下午6:50:03
	 */

	public static String getTimeAddHour(String date, String pattern, Integer num) {

		try {
			// 设置日期格式
			SimpleDateFormat df = new SimpleDateFormat(pattern);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(StrToTime(date, "yyyy-MM-dd HH:mm:ss"));
			calendar.add(Calendar.HOUR, num);
			return df.format(calendar.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Title: getTimeAddMinutes
	 * @Description: 给指定时间追加分钟
	 * @param date
	 * @param pattern
	 * @param num
	 * @return
	 * @author Daomin.Fu
	 * @date 2018年5月14日 下午6:57:50
	 */
	public static String getTimeAddMinutes(String date, String pattern, Integer num) {

		try {
			// 设置日期格式
			SimpleDateFormat df = new SimpleDateFormat(pattern);

			Calendar calendar = Calendar.getInstance();
			Date sdate = new Date();
			if (StrKit.notBlank(date)) {
				sdate = TimeKit.StrToTime(date, "yyyy-MM-dd HH:mm:ss");
			}
			calendar.setTime(sdate);
			calendar.add(Calendar.MINUTE, num);
			return df.format(calendar.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Title: getTimestampToStr
	 * @Description: 将时间戳转换年月日时分秒
	 * @param timestamp
	 * @return
	 * @author Daomin.Fu
	 * @date 2018年5月14日 下午6:37:37
	 */
	public static String getTimestampToStr(long timestamp, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse("1970-01-01 08:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.SECOND, (int) timestamp);
		return format.format(now.getTime());
	}

	/**
	 * 
	 * @Title: getStrToTimestamp
	 * @Description: 将时间字符串转为时间戳
	 * @param user_time
	 * @return
	 * @author Daomin.Fu
	 * @date 2018年5月14日 下午6:35:41
	 */
	public static String getStrToTimestamp(String dateStr) {
		String re_time = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d;
		try {

			d = sdf.parse(dateStr);
			long l = d.getTime();
			String str = String.valueOf(l);
			re_time = str.substring(0, 10);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return re_time;
	}

	/**
	 * 
	 * @Title: getOldTimeToNowTimeMinute
	 * @Description: 计算过去时间到现在相差多少分钟
	 * @param oldTime
	 * @return
	 * @author Daomin.Fu
	 * @date 2018年5月28日 下午3:47:23
	 */
	public static int getOldTimeToNowTimeMinute(String oldTime) {

		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			Date d1 = df.parse(oldTime);
			Date d2 = new Date();
			long time = d2.getTime() - d1.getTime();
			int totalS = new Long(time / 1000 / 60).intValue();
			return totalS;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * 秒
	 * 
	 * @param oldTime
	 * @return
	 */
	public static int getOldTimeToNowTimeSecond(String oldTime) {

		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			Date d1 = df.parse(oldTime);
			Date d2 = new Date();
			long time = d2.getTime() - d1.getTime();
			int totalS = new Long(time / 1000).intValue();
			return totalS;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * 计算过去时间到现在相差了多少分钟
	 * 
	 * @return
	 */
	public static int getOldTimeToNowTimeMinute(String startTime, String oldTime) {

		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			Date d1 = df.parse(startTime);
			Date d2 = df.parse(oldTime);
			long time = d2.getTime() - d1.getTime();
			int totalS = new Long(time / 1000 / 60).intValue();
			return totalS;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * 验证时间是否过期
	 * 
	 * @param time
	 * @return
	 */
	public static Boolean TimeOut(String time) {
		long date = getJavaDateStamp(time.trim() + " 00:00:00");
		long nowdate = getJavaDateStamp(getNowTime("yyyy-MM-dd HH:mm:ss"));
		return date > nowdate;
	}

	/**
	 * java时间戳转换年月日时分秒
	 * 
	 * @param str
	 * @return String
	 */
	public static String getJavaDataForm(long str, String pattern) {

		Date dat = new Date(System.currentTimeMillis() - str);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dat);
		// yyyy-MM-dd hh:mm:ss
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(pattern);
		String sb = format.format(gc.getTime());
		return sb;
	}

	public static long getJavaDateStamp(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sd = date.getTime() + "";
		String dateline = sd.substring(0, 10);
		return Long.parseLong(dateline);
	}

	public static Integer endTime_nowTime(String endTime) {

		if (endTime == null) {
			return 0;
		}

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = df.parse(endTime);
			Date d2 = new Date();
			long diff = d1.getTime() - d2.getTime();
			long days = diff % 86400000 == 0 ? diff / 86400000 : diff / 86400000 + 1;
			return Integer.parseInt(days + "") - 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public static Integer endTime_nowTime(String startTime, String endTime, String pattern) {

		DateFormat df = new SimpleDateFormat(pattern);
		try {
			Date d1 = df.parse(endTime);
			Date d2 = df.parse(startTime);
			long diff = d1.getTime() - d2.getTime();
			long days = diff % 86400000 == 0 ? diff / 86400000 : diff / 86400000 + 1;
			return Integer.parseInt(days + "");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Title: StrToTime
	 * @Description: 时间字符串转换为时间格式
	 * @param dateStr
	 * @param dateFormat
	 * @return
	 * @author Daomin.Fu
	 * @date 2018年5月4日 下午4:45:19
	 */
	public static Date StrToTime(String dateStr, String dateFormat) {

		Date date = null;
		try {
			date = new SimpleDateFormat(dateFormat).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String dateStrToStr(String dateStr, String dateFormat) {

		String str = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			Date date = format.parse(dateStr);

			str = format.format(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 
	 * @Title: DateToStr
	 * @Description: 日期转换成字符串
	 * @param date
	 * @param dateFormat
	 * @return
	 * @author Daomin.Fu
	 * @date 2018年5月28日 下午3:49:06
	 */
	public static String DateToStr(Date date, String dateFormat) {

		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		String str = format.format(date);
		return str;
	}

	/**
	 * 
	 * @Title: differentDays
	 * @Description: 计算两个时间相隔的天数
	 * @param date1
	 *            开始时间
	 * @param date2
	 *            结束时间
	 * @return
	 * @author Daomin.Fu
	 * @date 2018年5月14日 下午7:20:31
	 */
	public static int differentDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);

		// 同一年
		if (year1 != year2) {
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {

				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
					// 闰年
					timeDistance += 366;
				} else {
					// 不是闰年
					timeDistance += 365;
				}
			}

			return timeDistance + (day2 - day1);
		} else {
			// 不同年
			return day2 - day1;
		}
	}

	/**
	 * 计算两个日期的间隔，传入String和上面的一样用哒
	 * 
	 * @author 黎婳
	 * @date 2018年8月1日 下午4:30:09
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDaysString(String date3, String date4) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = sdf.parse(date3);
			date2 = sdf.parse(date4);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);

		// 不同一年
		if (year1 != year2) {
			int timeDistance = 0;
			if (year1 > year2) {
				for (int i = year2; i < year1; i++) {

					if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
						// 闰年
						timeDistance -= 366;
					} else {
						// 不是闰年
						timeDistance -= 365;
					}
				}
			} else {
				for (int i = year1; i < year2; i++) {

					if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
						// 闰年
						timeDistance += 366;
					} else {
						// 不是闰年
						timeDistance += 365;
					}
				}
			}
			return timeDistance + (day2 - day1);
		} else {
			// 同年
			return day2 - day1;
		}
	}

	/**
	 * 
	 * @Title: countMonths
	 * @Description:根据两个时间获取相隔月数
	 * @param date1
	 * @param date2
	 * @param pattern
	 * @return
	 * @throws ParseException
	 * @author Daomin.Fu
	 * @date 2018年5月22日 下午5:08:15
	 */
	public static int countMonths(String date1, String date2, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(sdf.parse(date1));
		c2.setTime(sdf.parse(date2));

		int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);

		// 开始日期若小月结束日期
		if (year < 0) {
			year = -year;
			return year * 12 + c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
		}

		return year * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
	}

	/**
	 * 
	 * @Title: yyyyMMddToyyyy_MM_dd
	 * @Description: 把yyyyMMdd字符串转为yyyy-MM-dd
	 * @param str
	 * @return
	 * @author Daomin.Fu
	 * @date 2018年9月12日 上午12:18:21
	 */
	public static String yyyyMMddToyyyy_MM_dd(String str, String format1, String format2) {
		SimpleDateFormat sf1 = new SimpleDateFormat(format1);
		SimpleDateFormat sf2 = new SimpleDateFormat(format2);
		String sfstr = "";
		try {
			sfstr = sf2.format(sf1.parse(str));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sfstr;
	}

	/**
	 * 获取传入时间所在的时间段，以月为单位
	 * 
	 * @author 黎婳
	 * @date 2018年5月22日 下午2:57:33
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static String getSelectTime(String date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = format.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			String y = String.valueOf(year);
			String m = String.valueOf(month + 1);
			if (month < 9) {
				m = "0" + m;
			}
			date = y + "-" + m;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取传入时间所在的月份的1号
	 * 
	 * @author 黎婳
	 * @date 2018年5月22日 下午2:57:33
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static String getMonth1time(String date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = format.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			String y = String.valueOf(year);
			String m = String.valueOf(month + 1);
			if (month < 9) {
				m = "0" + m;
			}
			date = y + "-" + m + "-01";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * @Title: comparingDate
	 * @Description: 比较两个日期大小
	 * @param beginDate
	 * @param endDate
	 * @param dateFormat
	 * @return
	 * @author Daomin.Fu
	 * @date 2018年8月6日 下午11:09:35
	 */
	public static int comparingDate(String beginDate, String endDate, String dateFormat) {
		try {
			// 设置日期格式
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			Date startDate = sdf.parse(beginDate);
			Date deadline = sdf.parse(endDate);

			if (startDate.before(deadline)) {
				return 1;
			} else if (startDate.after(deadline)) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;

	}

	/**
	 * 传入时间格式为Y-M,获取月份1号
	 * 
	 * @author 黎婳
	 * @date 2018年8月7日 下午2:53:29
	 * @param date
	 * @return
	 */
	public static String getMonth2time(String date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date d = null;
		try {
			d = format.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			String y = String.valueOf(year);
			String m = String.valueOf(month + 1);
			if (month < 9) {
				m = "0" + m;
			}
			date = y + "-" + m + "-01";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

}
