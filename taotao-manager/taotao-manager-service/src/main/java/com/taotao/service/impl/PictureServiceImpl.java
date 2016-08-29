package com.taotao.service.impl;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.service.PictureService;
import com.taotao.utils.FilenameUtil;
import com.taotao.utils.FtpUtil;

@Service
public class PictureServiceImpl implements PictureService {
	@Value("${ftp.host}")
	private String host;
	@Value("${ftp.port}")
	private Integer port;
	@Value("${ftp.username}")
	private String username;
	@Value("${ftp.password}")
	private String password;
	@Value("${ftp.basePath}")
	private String basePath;
	@Value("${ftp.baseUrl}")
	private String baseUrl;

	@Override
	public Map uploadPicture(MultipartFile file) {
		Map map = new HashMap<>();
		try {
			String oldName = file.getOriginalFilename();
			String newName = FilenameUtil.getFilename(oldName);
			//将当前时间以指定格式输出
			String filePath = new DateTime().toString("/yyyy/MM/dd");
			InputStream input = file.getInputStream();
			boolean result = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, newName, input);
			//访问此图片的URL
			String url = baseUrl + filePath + "/" + newName;
			//富文本编辑器的图片返回格式
			if (result) {
				map.put("error", 0);
				map.put("url", url);
			}else{
				map.put("error", 1);
				map.put("message", "上传失败");
			}
		} catch (Exception e) {
			map.put("error", 1);
			map.put("message", "上传失败");
		}
		return map;
	}

}
