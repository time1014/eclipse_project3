package com.yedam.app.upload.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer{

	// @Value에 프로젝트 우클릭 후 run as -> run configuration에서 프로젝트의 environment에서 해당 ${}안의 값을 이름으로 하고 사용할 경로를 지정하고 저장해두면 그 경로만 배포시 수정하면 사용 가능
	@Value("${file.upload.path}")
	private String uploadPath;
	
//	private String uploadPath = "D:/upload/"; 하드코딩 방식 배포시 경로 틀어짐


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("imgs/**").addResourceLocations("file:///" + uploadPath);
	}
}
