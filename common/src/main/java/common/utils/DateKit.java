package common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 * 日期工具类
 * 
 * @author 李永宁 2018年10月25日下午10:19:14
 */
public class DateKit {

	/**
	 * 获取昨天日期
	 * 
	 * @auth 李永宁 2019年7月6日下午6:34:19
	 * @return yyyy-MM-dd
	 */
	public static String getYesterday() {
		return getTimeAddDay(getNow2(), -1, "yyyy-MM-dd");
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @param pattern
	 * @return String
	 */
	public static String getNow(String pattern) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return now.format(formatter);
	}

	/**
	 * "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @author 李永宁 2018年12月23日上午12:42:30
	 *
	 * @return
	 */
	public static String getNow() {
		return getNow("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * "yyyy-MM-dd"
	 * 
	 * @author 李永宁 2018年9月29日下午2:06:40
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getNow2() {
		return getNow("yyyy-MM-dd");
	}

	/**
	 * yyyy-MM-dd HH:mm
	 * 
	 * @author 李永宁 2018年9月29日下午2:06:30
	 *
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String getNow3() {
		return getNow("yyyy-MM-dd HH:mm");
	}

	/**
	 * yyyy
	 * 
	 * @author 李永宁 2018年12月23日上午12:42:56
	 *
	 * @return
	 */
	public static String getYear() {
		return getNow("yyyy");
	}

	/**
	 * yyyy-MM-dd
	 * 
	 * @author 李永宁 2018年12月23日上午12:43:03
	 *
	 * @param date
	 * @return
	 */
	public static String toStr(Date date) {
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
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			Date nowDate = df.parse(time);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(nowDate);
			calendar.add(Calendar.DATE, num);
			return df.format(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
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
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(toDate(date));
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
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
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
	public static String toStrFrom1970(long timestamp, String pattern) {
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

	public static String toStrFrom1970(long timestamp) {
		return toStrFrom1970(timestamp, "yyyy-MM-dd HH:mm:ss");
	}

	public static String toStr(long timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(timestamp);
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
	public static String toTimestamp(String dateStr) {
		Date d = toDate(dateStr);
		long l = d.getTime();
		String str = String.valueOf(l);
		return str.substring(0, 10);
	}

	/**
	 * 时间差--小时
	 * 
	 * @author 李永宁 2018年12月22日下午1:17:21
	 *
	 * @param start
	 *            开始时间
	 * @return
	 */
	public static long getDifferenceDay(String start) {
		return getDifferenceHour(start) / 24;
	}

	/**
	 * 时间差--小时
	 * 
	 * @author 李永宁 2018年12月22日下午1:17:21
	 *
	 * @param start
	 *            开始时间
	 * @return
	 */
	public static long getDifferenceHour(String start) {
		return getDifferenceMinute(toDate(start), new Date()) / 60;
	}

	/**
	 * 时间差--分钟
	 * 
	 * @author 李永宁 2018年12月22日下午1:17:21
	 *
	 * @param start
	 *            开始时间
	 * @return
	 */
	public static long getDifferenceMinute(String start) {
		return getDifferenceMinute(toDate(start), new Date());
	}

	/**
	 * 时间差--秒
	 * 
	 * @author 李永宁 2018年12月22日下午1:17:21
	 *
	 * @param start
	 *            开始时间
	 * @return
	 */
	public static long getDifferenceSecond(String start) {
		return getDifferenceSecond(toDate(start), new Date());
	}

	/**
	 * 时间差--分钟
	 * 
	 * @author 李永宁 2018年12月22日下午1:17:21
	 *
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	public static long getDifferenceMinute(String start, String end) {
		return getDifferenceMinute(toDate(start), toDate(end));
	}

	/**
	 * 时间差--分钟
	 * 
	 * @author 李永宁 2018年12月22日下午1:17:21
	 *
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	public static long getDifferenceMinute(Date start, Date end) {
		return getDifferenceSecond(start, end) / 60;
	}

	/**
	 * 时间差--秒
	 * 
	 * @author 李永宁 2018年12月22日下午1:17:21
	 *
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	public static long getDifferenceSecond(Date start, Date end) {
		long time = end.getTime() - start.getTime();
		return time / 1000;
	}

	/**
	 * 验证时间是否过期
	 * 
	 * @param time
	 * @return
	 */
	public static Boolean timeOut(String time) {
		long date = getJavaDateStamp(time.trim() + " 00:00:00");
		long nowdate = getJavaDateStamp(getNow("yyyy-MM-dd HH:mm:ss"));
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
			e.printStackTrace();
		}
		String sd = date.getTime() + "";
		String dateline = sd.substring(0, 10);
		return Long.parseLong(dateline);
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
	public static Date toDate(String dateStr, String dateFormat) {
		Date date = null;
		try {
			date = new SimpleDateFormat(dateFormat).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @author 李永宁 2018年12月22日下午1:12:11
	 *
	 * @param dateStr
	 * @return
	 */
	public static Date toDate(String dateStr) {
		return toDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * yyyy-MM-dd
	 * 
	 * @author 李永宁 2018年12月22日下午1:12:21
	 *
	 * @param dateStr
	 * @return
	 */
	public static Date toDate2(String dateStr) {
		return toDate(dateStr, "yyyy-MM-dd");
	}

	public static String toStr(String dateStr, String dateFormat) {
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

	public static String toStr(String date) {
		return toStr(date, "yyyy-MM-dd");
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
	public static String toStr(Date date, String dateFormat) {
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
	public static int differentDays(String date1, String date2) {
		return differentDays(toDate2(date1), toDate2(date2));
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
	public static int countMonths(String date1, String date2, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(sdf.parse(date1));
			c2.setTime(sdf.parse(date2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public static String getMonthFirstDay(String date) {
		if (Pattern.matches("^\\d{4}-\\d{2}$", date)) {
			date = date + "-01 00:00:00";
		} else if (Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", date)) {
			date = date + " 00:00:00";
		}
		return getMonthFirstDay(toDate(date));
	}

	/**
	 * 获取当前时间月份1号
	 * 
	 * @author 李永宁 2019年1月18日下午4:18:25
	 *
	 * @return
	 */
	public static String getMonthFirstDay() {
		return getNow("yyyy-MM-01");
	}

	/**
	 * 获取某个时间的月份1号
	 * 
	 * @author 李永宁 2019年1月18日下午4:18:41
	 *
	 * @param date
	 * @return
	 */
	public static String getMonthFirstDay(Date date) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-01");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return df.format(calendar.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取当前时间相差月份数的月份1号
	 * 
	 * @author 李永宁 2019年1月18日下午4:19:05
	 *
	 * @param month
	 * @return
	 */
	public static String getMonthFirstDay(int month) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-01");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.MONTH, month);
			return df.format(calendar.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取上个月
	 * 
	 * @author 李永宁 2019年1月17日下午6:05:29
	 *
	 * @return
	 */
	public static String getPrevMonthFirst() {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-01");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.MONTH, -1);
			return df.format(calendar.getTime());
		} catch (Exception e) {
			return null;
		}
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


}
