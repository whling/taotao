package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.service.PictureService;
import com.taotao.utils.JsonUtils;

/**
 * 图片上传的controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/pic")
public class PictureController {
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/upload")
	@ResponseBody
	public String uploadPic(MultipartFile uploadFile){
		Map map = pictureService.uploadPicture(uploadFile);
		String json = JsonUtils.objectToJson(map);
		//为了兼容更多的浏览器，需要将对象转化为字符串（纯文本）传输
		return json;
	}
}
