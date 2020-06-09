package common.utils;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: PictureUtils
 * @Description: 图片处理工具类
 * @author Daomin.Fu
 * @date 2018年8月17日 上午12:25:17
 */
public class ImageKit {

	/**
	 * 
	 * @Title: uploadPictures
	 * @Description: 图片上传
	 * @param upLoadFile
	 * @param savePath
	 *            图片保存路径
	 * @return 返回图片名称,用于数据库路径储存
	 * @author Daomin.Fu
	 * @date 2018年8月17日 上午12:31:14
	 */
	public static String uploadPictures(MultipartFile upLoadFile, String savePath) {

		// 图片名称,唯一不重复
		String fileName = UUID.randomUUID()
				+ upLoadFile.getOriginalFilename().substring(upLoadFile.getOriginalFilename().lastIndexOf("."));

		savePath = StrKit.mergeStringWithSeparator(savePath, fileName);
		File source = new File(savePath);
		try {
			// 将内存中的数据写入磁盘
			upLoadFile.transferTo(source);
		} catch (Exception e) {
			String errorMsg = "Upload file " + source.getAbsoluteFile() + " Error!" + e.getMessage();
			throw new RuntimeException(errorMsg);
		}

		return fileName;

	}

}
