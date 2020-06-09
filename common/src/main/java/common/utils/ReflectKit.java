/**
 * @author 李永宁 2018年10月25日下午3:09:51
 */
package common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class ReflectKit {

	/**
	 * 使用反射调用方法
	 *
	 * @param o          被调用对象
	 * @param methodName 被调用对象的方法名称
	 * @param args       被调用方法所需传入的参数列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object callMethod(Object o, String methodName, Object... args) {
		Object result = null;
		try {
			int argslen = args.length;
			Class c = o.getClass();
			Method m = null;

			Class<?> argsTypes[] = new Class<?>[argslen];
			// 基本类型在参数传递过程中会自动封住成对象类型，这里还原成基本类型的Class
			for (int i = 0; i < argslen; i++) {
				if (args[i] != null)
					argsTypes[i] = args[i].getClass();

				// if (argsTypes[i] == Integer.class) {
				// argsTypes[i] = int.class;
				// }
				// if (argsTypes[i] == Float.class) {
				// argsTypes[i] = float.class;
				// }
				// if (argsTypes[i] == Long.class) {
				// argsTypes[i] = long.class;
				// }
				// if (argsTypes[i] == Byte.class) {
				// argsTypes[i] = byte.class;
				// }
				// if (argsTypes[i] == Double.class) {
				// argsTypes[i] = double.class;
				// }
				// if (argsTypes[i] == Boolean.class) {
				// argsTypes[i] = boolean.class;
				// }
			}

			if (argslen == 0) {
				m = c.getDeclaredMethod(methodName);
			} else {
				Method[] methodes = c.getMethods();

				for (Method method : methodes) {
					Class<?>[] pTypes = method.getParameterTypes();
					if (method.getName().equals(methodName) && pTypes.length == argslen) {
						for (int i = 0; i < argslen; i++) {
							if (argsTypes[i] == pTypes[i] || argsTypes[i].getSuperclass() == pTypes[i]) {
								m = method;
								break;
							}
						}
					}
				}
			}
			if (m == null) {
				String argsName = Arrays.toString(argsTypes).replace("[", "(").replace("]", ")");
				throw new NoSuchMethodException(methodName + argsName);
			}
			m.setAccessible(true);
			result = m.invoke(o, args);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 使用反射设置变量值
	 *
	 * @param target    被调用对象
	 * @param fieldName 被调用对象的字段，一般是成员变量或静态变量，不可是常量！
	 * @param value     值
	 * @param <T>       value类型，泛型
	 */
	@SuppressWarnings("rawtypes")
	public static <T> void setValue(Object target, String fieldName, T value) {
		try {
			Class c = target.getClass();
			Field f = c.getDeclaredField(fieldName);
			f.setAccessible(true);
			Class<?> clz = f.getType();
			if (clz == Integer.class) {
				f.set(target, Integer.parseInt(value.toString()));
			} else if (clz == Float.class) {
				f.set(target, Float.parseFloat(value.toString()));
			} else if (clz == Long.class) {
				f.set(target, Long.parseLong(value.toString()));
			} else if (clz == Byte.class) {
				f.set(target, Byte.parseByte(value.toString()));
			} else if (clz == Double.class) {
				f.set(target, Double.parseDouble(value.toString()));
			} else if (clz == Boolean.class) {
				f.set(target, Boolean.parseBoolean(value.toString()));
			} else {
				f.set(target, value);
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用反射获取变量值
	 *
	 * @param target    被调用对象
	 * @param fieldName 被调用对象的字段，一般是成员变量或静态变量，不可以是常量
	 * @param <T>       返回类型，泛型
	 * @return 值
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T getValue(Object target, String fieldName) {
		T value = null;
		try {
			Class c = target.getClass();
			Field f = c.getDeclaredField(fieldName);
			f.setAccessible(true);
			value = (T) f.get(target);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 通过反射,获得指定类的父类的泛型参数的实际类型. 如BuyerServiceBean extends DaoSupport<Buyer>
	 * 
	 * @param clazz clazz 需要反射的类,该类必须继承范型父类
	 * @param index 泛型参数所在索引,从0开始.
	 * @return 范型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {
		Type genType = clazz.getGenericSuperclass();// 得到泛型父类
		// 如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		// 返回表示此类型实际类型参数的Type对象的数组,数组里放的都是对应类型的Class, 如BuyerServiceBean extends
		// DaoSupport<Buyer,Contact>就返回Buyer和Contact类型
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class<?>) params[index];
	}
}
