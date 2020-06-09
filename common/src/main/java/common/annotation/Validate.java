/**
 * @author 李永宁 2018年10月25日下午2:47:50
 */
package common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 李永宁 2018年10月25日下午2:47:50
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Validate {

	Class<?> value();
}
