package com.example.fileattachment.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileAttachmentService {

	public byte[] loadResourceDocument() throws IOException {
		byte[] byteArray;
		// getting properties file from resource folder as sample
		File file = ResourceUtils.getFile("classpath:application.properties");
		byteArray = new byte[(int) file.length()]; 
		
	    FileInputStream fis = new FileInputStream(file);
	    fis.read(byteArray); //read file into bytes[]
	    fis.close();
	    return byteArray;
	}
	
	public boolean uploadDocument(MultipartFile file) {
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get("D:\\" + file.getOriginalFilename());
			Files.write(path, bytes);
			return true;
		}
		catch(IOException exception) {
			return false;
		}
	}
}
