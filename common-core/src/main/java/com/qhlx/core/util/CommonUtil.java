package com.qhlx.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	/*
	 * 保存个人照片和现场照
	 *
	 */
	public static String saveImg(String imageBase64,String path , String context,String idNum)
	{
//		try {
//			Date now = new Date();
//			String diskPath = path+ File.separator;
//			String picPath = context  + File.separator + DateUtil.getCurrentDateTime()
//					+ File.separator + idNum + File.separator +File.separator + idNum + "_" + now.getTime()
//					+ ".png";
//			String realPath = diskPath + picPath;
//
//			if (Img.base642img(imageBase64, realPath)) {
//				return picPath;
//			} else {
//				return null;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("存照片发生错误", e);
//			return null;
//		}
		return null;

	}
	
	/**
	 * 保存证件�?
	 * @param imageBase64
	 */
//	public String saveCardImage(String imageBase64)
//	{
//		try {
//			String diskPath = Constant.DISKPATH + File.separator;
//			String filePath = Constant.CARDCONTEXT + File.separator + DateUtil.dateToYYYYMMString(new Date())
//					+ File.separator + UUID.randomUUID().toString() + ".jpg";
//
//			if (Img.base642img(imageBase64, diskPath + filePath)) {
//				return filePath;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("ocr 证据图片 保存本地失败", e);
//		}
//		return null;
//	}

}
