package com.star.Controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.star.service.impl.PicServiceimpl;

@Controller
public class PicController {

	@Resource
	private PicServiceimpl picServiceimpl;

	@RequestMapping("pic/upload")
	@ResponseBody
	public Map<String, Object> upload(MultipartFile uploadFile) {

		Map<String, Object> map = null;
		try {
			map = picServiceimpl.upload(uploadFile);
		} catch (IOException e) {
			map.put("error", 1);
			map.put("message", "图片服务器异常");
		}
		return map;

	}

}
