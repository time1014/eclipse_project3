package com.yedam.app.upload.web;



import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UploadController {

	@Value("${file.upload.path}")
	private String uploadPath;
	
	//페이지
	@GetMapping("formUpload")
	public void formUploadPage(){};
	
	//처리
	@PostMapping("uploadForm")
	public String formUploadFile(@RequestPart("file") MultipartFile images) throws IllegalStateException, IOException {
		log.info(uploadPath);
		log.info(images.getContentType());
		log.info(images.getOriginalFilename());
		log.info(String.valueOf(images.getSize()));
		String fileName = images.getOriginalFilename();
		String saveName = uploadPath +File.separator + fileName;
		
		log.debug("savename : " + saveName);
		
		Path savePath = Paths.get(saveName);
		images.transferTo(savePath);
		return "redirect:formUpload";	
	}
	
	@PostMapping("/uploadAjax")
	@ResponseBody
	public List<String> uploadFile(@RequestPart MultipartFile[] uploads){
		List<String> imageList = new ArrayList();
		for(MultipartFile file : uploads) {
			if(!file.getContentType().startsWith("image")) {
				log.error("this file is not image");
				return null;
			}
			//실제 파일명
			String fileName = file.getOriginalFilename();
			//식별자
			String uuid = UUID.randomUUID().toString();
			// 업로드할 파일명
			String uploadName = uuid + "_" + fileName;
			// 실제 저장될 경로
			String saveName = uploadPath + File.separator + uploadName;
			
			Path savePath = Paths.get(saveName);
			try {
				file.transferTo(savePath);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//DB에 해당 경로 저장
			// 실제 서버에 업로드한 경로
			// 사용자가 업로드할 때 사용한 파일명
			imageList.add(uploadName);
			
		}
		return imageList;
	}
	
}