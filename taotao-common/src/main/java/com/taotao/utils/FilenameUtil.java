package com.taotao.utils;

import java.util.Random;
/**
 * 文件命名的工具类
 * @author Administrator
 *
 */
public class FilenameUtil {
	/**
	 * 通过时间来命名
	 * @param oldName
	 * @return
	 */
	public static String getFilename(String oldName){
		//当前时间的毫秒数
		long timeMillis = System.currentTimeMillis();
		
		Random random = new Random();
		int end3 = random.nextInt(999);
		//如果不足三位前面补0
		String str = timeMillis + String.format("%03d", end3);
		//取文件的后缀名
		String substring = oldName.substring(oldName.lastIndexOf("."));
		return str+substring;
	}
}
