package com.situ.mvc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/fileUploadDemo")
public class FileUploadControllerDemo {
	
	// SpringMVC帮我们封装的和文件上传相关的信息（文件名、路径、大小）的类是MultipartFile
	@RequestMapping(value = "/upload")
	public void upload(String name, MultipartFile multipartFile) {
		// 上传文件输入的name
		System.out.println(name);
		// 上传的文件类型
		System.out.println(multipartFile.getContentType());
		// multipartFile
		System.out.println(multipartFile.getName());
		// 上传文件的名称
		System.out.println(multipartFile.getOriginalFilename());
		// 上传的文件的大小（单位：b）
		System.out.println(multipartFile.getSize());
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = multipartFile.getInputStream();
			outputStream = new FileOutputStream(new File("D:/test/" + multipartFile.getOriginalFilename()));
			// MVC封装好的复制文件的方法
			IOUtils.copy(inputStream, outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 后打开的先关闭
			IOUtils.closeQuietly(outputStream);
			IOUtils.closeQuietly(inputStream);
		}
	}
}
