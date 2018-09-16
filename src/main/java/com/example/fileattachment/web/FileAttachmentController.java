package com.example.fileattachment.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileAttachmentController {
	
	private byte[] byteArray; 
	
	@GetMapping("/downloadFile/{fileName}")
	public void downloadFile(@PathVariable("fileName")String fileName,HttpServletResponse response) throws IOException {
		
		File file = ResourceUtils.getFile("classpath:application.properties");
		byteArray = new byte[(int) file.length()]; 

		  FileInputStream fis = new FileInputStream(file);
		  fis.read(byteArray); //read file into bytes[]
		response.setContentType("application/octet-stream");
		response.setStatus(200);
		response.setHeader("Content-Disposition", "attachment; filename=\"demo.txt\"");
		
		OutputStream os = response.getOutputStream();
		os.write(byteArray);
		fis.close();
		os.close();
		
	}

}
