/**
 * 
 */
package common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author 李永宁 2020年1月13日 上午10:43:41
 *
 */
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class User extends BaseModel {

	private String name;
	private String loginName;
}
