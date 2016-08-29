package com.taotao.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
	/**
	 * 文件上传service
	 * @param file
	 * @return
	 */
	Map uploadPicture(MultipartFile file);
}
