package com.taotao.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.utils.FtpUtil;

/**
 * 测试文件上传到ftp服务器
 * @author Administrator
 *
 */
public class FTPFileuploadTest {
	@Test
	public void testFileupload() throws IOException, Exception{
		FTPClient client=new FTPClient();
		client.connect("192.168.1.150", 21);
		client.login("whling", "01303273gF");
		client.changeWorkingDirectory("/home/whling/www/images");
		FileInputStream inputStrem=new FileInputStream(new File("D:\\j2ee.jpg"));
		client.setFileType(FTP.BINARY_FILE_TYPE);
		client.storeFile("hello.jpg", inputStrem);
		client.logout();
		inputStrem.close();
	}
	/**
	 * 测试文件上传工具类的使用
	 * @throws Exception
	 */
	@Test
	public void testFTPUtil() throws Exception{
		FileInputStream inputStrem=new FileInputStream(new File("D:\\j2ee.jpg"));
		FtpUtil.uploadFile("192.168.1.150", 21, "whling", "01303273gF", "/home/whling/www/images", "/2016/07/29", "hello.jpg", inputStrem);
		inputStrem.close();
	}
}
