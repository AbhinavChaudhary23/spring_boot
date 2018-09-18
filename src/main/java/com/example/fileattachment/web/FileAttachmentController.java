package com.example.fileattachment.web;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.fileattachment.service.FileAttachmentService;

@RestController
public class FileAttachmentController {
	
	@Value("${file.maxSize}")
	private String maxFileSizeAllowed;
	 
	@Autowired
	private FileAttachmentService fileService;
	
	
	@GetMapping("/downloadFile")
	public void downloadFile(HttpServletResponse response) throws IOException {
		OutputStream os = response.getOutputStream();
		os.write(fileService.loadResourceDocument());
		
		response.setContentType("application/octet-stream");
		response.setStatus(200);
		response.setHeader("Content-Disposition", "attachment; filename=\"demo.txt\"");
		
		os.close();	
	}
	
	@PostMapping("/uploadFile")
	public String attachFileDocument(@RequestParam("file") MultipartFile file) throws IOException {
		if(fileService.uploadDocument(file)) {
			return "File successfully uploaded- "+file.getOriginalFilename();
		}
		else 
			return "Unable to upload the attached file";
	}
	
	@GetMapping("/testConfig")
	public String getMaxFileSize() {
		return maxFileSizeAllowed;
	}

}
