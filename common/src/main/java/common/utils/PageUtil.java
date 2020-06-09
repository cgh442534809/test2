package common.utils;

/**
 * 分页相关方法
 * 
 * @author 阿海
 * 
 */
public class PageUtil {

	/**
	 * 返回当前分页起始记录编号
	 * 
	 * @param pageId
	 *            分页编号
	 * @param size
	 *            分页大小
	 * @return 当前分页起始记录编号
	 */
	public static int getStart(Integer pageId, int size) {
		if (pageId == null) {
			pageId = 1;
		}
		return (pageId - 1) * size;
	}

	/**
	 * 获取分页显示的view，如果是ajax获取分页数据，在原来的view后面加上Ajax
	 * 
	 * 
	 * @param view
	 * @param ajaxPageMore
	 * @return
	 */
	public static String getAjaxPageView(String view, String ajaxPageMore) {
		if (StrKit.notBlank(ajaxPageMore)) {
			view += "Ajax";
		}
		return view;
	}
}
