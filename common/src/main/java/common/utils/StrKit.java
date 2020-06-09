/**
 * @author 李永宁 2018年10月25日下午4:35:10
 */
package common.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 李永宁 2018年10月25日下午4:35:10
 *
 */
public class StrKit {

	/**
	 * 首字母变小写
	 */
	public static String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] += ('a' - 'A');
			return new String(arr);
		}
		return str;
	}

	/**
	 * 首字母变大写
	 */
	public static String firstCharToUpperCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'a' && firstChar <= 'z') {
			char[] arr = str.toCharArray();
			arr[0] -= ('a' - 'A');
			return new String(arr);
		}
		return str;
	}

	/**
	 * 字符串为 null 或者为 "" 时返回 true
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim());
	}

	/**
	 * 字符串不为 null 而且不为 "" 时返回 true
	 */
	public static boolean notBlank(String str) {
		return str != null && !"".equals(str.trim());
	}

	public static boolean notBlank(String... strings) {
		if (strings == null)
			return false;
		for (String str : strings)
			if (str == null || "".equals(str.trim()))
				return false;
		return true;
	}

	public static boolean notNull(Object... paras) {
		if (paras == null)
			return false;
		for (Object obj : paras)
			if (obj == null)
				return false;
		return true;
	}

	public static String toCamelCase(String stringWithUnderline) {
		if (stringWithUnderline.indexOf('_') == -1)
			return stringWithUnderline;

		stringWithUnderline = stringWithUnderline.toLowerCase();
		char[] fromArray = stringWithUnderline.toCharArray();
		char[] toArray = new char[fromArray.length];
		int j = 0;
		for (int i = 0; i < fromArray.length; i++) {
			if (fromArray[i] == '_') {
				// 当前字符为下划线时，将指针后移一位，将紧随下划线后面一个字符转成大写并存放
				i++;
				if (i < fromArray.length)
					toArray[j++] = Character.toUpperCase(fromArray[i]);
			} else {
				toArray[j++] = fromArray[i];
			}
		}
		return new String(toArray, 0, j);
	}

	public static String join(String[] stringArray) {
		StringBuilder sb = new StringBuilder();
		for (String s : stringArray)
			sb.append(s);
		return sb.toString();
	}

	public static String join(String[] stringArray, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stringArray.length; i++) {
			if (i > 0)
				sb.append(separator);
			sb.append(stringArray[i]);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @Title: splitUrl
	 * @Description: 分割解析URL参数传值
	 * @param: @param
	 *             str
	 * @param: @return
	 * @return: Map<String,String>
	 * @throws @Date
	 *             2018-3-14 上午11:24:36
	 */
	public static Map<String, String> splitUrl(String str) {
		String[] queryStringSplit = str.split("&");
		Map<String, String> queryStringMap = new HashMap<String, String>(queryStringSplit.length);
		String[] queryStringParam;
		for (String qs : queryStringSplit) {
			queryStringParam = qs.split("=");
			if (queryStringParam != null) {
				queryStringMap.put(queryStringParam[0], queryStringParam[1]);
			}
		}
		return queryStringMap;
	}

	/**
	 * 
	 * @Title: mergeStringWithSeparator
	 * @Description: 返回斜杠拼接的字符串
	 * @param args
	 * @return
	 * @author Daomin.Fu
	 * @date 2018年8月9日 下午1:44:27
	 */
	public static String mergeStringWithSeparator(String... args) {
		StringBuilder sb = new StringBuilder();
		for (String arg : args) {
			sb.append(arg);
			sb.append(File.separator);
		}

		return sb.substring(0, sb.length() - 1).toString();
	}
}
