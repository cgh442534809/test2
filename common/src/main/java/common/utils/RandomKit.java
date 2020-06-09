/**
 * 
 */
package common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @author 李永宁 2019年1月3日上午11:48:44
 *
 */
public class RandomKit {

	private static String[] code = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b",
			"c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"0" };

	public static String getStr(int num) {
		StringBuilder randomNum = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			randomNum.append(code[random.nextInt(code.length - 1)]);
		}
		return randomNum.toString();
	}

	/**
	 * 获取随机数
	 * 
	 * @author 李永宁 2019年1月3日上午11:49:27
	 *
	 * @param length
	 *            长度
	 * @return
	 */
	public static String get(Integer length) {
		String randomNum = "";
		for (int i = 0; i < length; i++) {
			randomNum += (int) (Math.random() * length);
		}
		return randomNum;
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	
}
