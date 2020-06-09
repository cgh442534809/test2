/**
 * 
 */
package common.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.constant.Global;

/**
 * 配置文件只能放在编译根目录，不检索子目录
 * 
 * @author 李永宁 2018年12月3日下午2:12:42
 *
 */
public class PropKit {

	protected Logger log = LoggerFactory.getLogger(getClass());

	private static Properties prop = null;

	private static final ConcurrentHashMap<String, Properties> map = new ConcurrentHashMap<String, Properties>();

	public static void init() {
		new PropKit();
	}

	private PropKit() {
		String classRoot = getClassLoader().getResource(File.separator).getPath().replaceAll("%5c", "");
		scanFile(classRoot);
	}

	private PropKit(String fileName) {
		prop = loadProperties(fileName);
	}

	private void scanFile(String filePath) {
		try {
			File files = new File(filePath);
			for (String f : files.list()) {
				if (f.endsWith(".properties")) {
					this.loadProperties(f);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Properties loadProperties(String fileName) {
		InputStream inputStream = null;
		Properties properties = new Properties();
		try {
			inputStream = getClassLoader().getResourceAsStream(fileName);
			if (inputStream == null) {
				throw new IllegalArgumentException("Properties file not found in classpath: " + fileName);
			}
			properties.load(new InputStreamReader(inputStream, Global.DEFAULT_ENCODING));
			map.put(fileName, properties);
			PropKit.prop = properties;
		} catch (IOException e) {
			throw new RuntimeException("Error loading properties file.", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		return properties;
	}

	private ClassLoader getClassLoader() {
		ClassLoader ret = Thread.currentThread().getContextClassLoader();
		return ret != null ? ret : getClass().getClassLoader();
	}

	public static Properties use(String fileName) {
		Properties result = map.get(fileName);
		if (result == null) {
			synchronized (PropKit.class) {
				result = map.get(fileName);
				if (result == null) {
					new PropKit(fileName);
					result = PropKit.prop;
				}
			}
		}
		return result;
	}

	public static String get(String key) {
		return prop.getProperty(key);
	}

	public static String get(String key, String defaultValue) {
		return prop.getProperty(key, defaultValue);
	}

	public static Integer getInt(String key) {
		return getInt(key, null);
	}

	public static Integer getInt(String key, Integer defaultValue) {
		String value = prop.getProperty(key);
		if (value != null) {
			return Integer.parseInt(value.trim());
		}
		return defaultValue;
	}

	public static Long getLong(String key) {
		return getLong(key, null);
	}

	public static Long getLong(String key, Long defaultValue) {
		String value = prop.getProperty(key);
		if (value != null) {
			return Long.parseLong(value.trim());
		}
		return defaultValue;
	}

	public static Boolean getBoolean(String key) {
		return getBoolean(key, null);
	}

	public static Boolean getBoolean(String key, Boolean defaultValue) {
		String value = prop.getProperty(key);
		if (value != null) {
			value = value.toLowerCase().trim();
			if ("true".equals(value)) {
				return true;
			} else if ("false".equals(value)) {
				return false;
			}
			throw new RuntimeException("The value can not parse to Boolean : " + value);
		}
		return defaultValue;
	}

	public boolean containsKey(String key) {
		return prop.containsKey(key);
	}

}
