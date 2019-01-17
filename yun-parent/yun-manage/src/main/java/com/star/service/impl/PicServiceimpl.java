package com.star.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.star.commons.utils.FtpUtil;
import com.star.commons.utils.IDUtils;
import com.star.service.PicService;

@Service
public class PicServiceimpl implements PicService {
	@Value("${ftpclient.host}")
	private String host;
	@Value("${ftpclient.port}")
	private int port;
	@Value("${ftpclient.username}")
	private String username;
	@Value("${ftpclient.password}")
	private String password;
	@Value("${ftpclient.basepath}")
	private String basePath;
	@Value("${ftpclient.filepath}")
	private String filePath;

	@Override
	public Map<String, Object> upload(MultipartFile file) throws IOException {
		String filename = IDUtils.genImageName()
				+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		boolean resurt = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, filename,
				file.getInputStream());
             Map<String, Object> map = new HashMap<String, Object>();
             if (resurt==true) {
				  map.put("error", 0);
				  map.put("url", "http://"+ host+"/"+filename);
			}else {
				 map.put("error", 1);
				 map.put("message", "图片上传失败");
			}
		return map;
	}
  
}
