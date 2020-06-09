package common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @ClassName: ExcelUtil
 * @Description: Excel工具类
 * @author Daomin.Fu
 * @date 2018年5月6日 下午5:54:44
 */
public class ExcelKit {

	private final static String excel2003L = ".xls"; // 2003- 版本的excel
	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel

	/************************************* Excel写入 /上传 ****************************/

	/**
	 * @author 李永宁 2019年1月24日下午5:11:43
	 *
	 * @param file
	 */
	public static List<List<String>> excelImport(MultipartFile file) {
		try {
			return excelImport(file.getInputStream(), file.getOriginalFilename());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: ExcelImport
	 * @Description: Excel写入 /上传
	 * @param in
	 * @param fileName
	 * @param clazz
	 * @return
	 * @throws Exception
	 * @author Daomin.Fu
	 * @date 2018年5月29日 上午9:57:57
	 */
	public static List<List<String>> excelImport(InputStream in, String fileName) {
		List<List<String>> list = new ArrayList<>();
		Workbook work = getWorkbook(in, fileName);
		int sheetsLength = work.getNumberOfSheets();
		// 遍历Excel中所有的sheet
		for (int i = 0; i < sheetsLength; i++) {
			Sheet sheet = work.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			int j = 0;
			while (true) {
				Row row = sheet.getRow(j);
				if (row == null) {
					break;
				}
				int cellNum = row.getLastCellNum();
				// 遍历所有的列
				List<String> li = new ArrayList<>();
				for (int y = 0; y < cellNum; y++) {
					String value = getCellValue(row.getCell(y));
					if (y == 0 && StrKit.isBlank(value)) {
						break;
					}
					li.add(value);
				}
				if (li.size() == 0) {
					break;
				}
				list.add(li);
				j++;
			}
		}
		return list;

	}

	/**
	 * 
	 * @Title: getWorkbook
	 * @Description: 根据文件后缀,自适应上传文件的版本
	 * @param inStr
	 * @param fileName
	 * @return
	 * @throws Exception
	 * @author Daomin.Fu
	 * @date 2018年5月28日 下午3:51:49
	 */
	public static Workbook getWorkbook(InputStream inStr, String fileName) {
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		try {
			if (excel2003L.equals(fileType)) {
				wb = new HSSFWorkbook(inStr);
			} else if (excel2007U.equals(fileType)) {
				wb = new XSSFWorkbook(inStr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb;
	}

	/**
	 * 
	 * @Title: getCellValue
	 * @Description: 对表格中数值进行格式化
	 * @param cell
	 * @return
	 * @author Daomin.Fu
	 * @date 2018年5月28日 下午3:52:21
	 */
	public static String getCellValue(Cell cell) {
		String value = "";
		DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
		DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if ("General".equals(cell.getCellStyle().getDataFormatString())) {
				value = df.format(cell.getNumericCellValue());
			} else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
				value = sdf.format(cell.getDateCellValue());
			} else {
				value = df2.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue() + "";
			break;
		default:
			break;
		}
		return value;
	}

}
