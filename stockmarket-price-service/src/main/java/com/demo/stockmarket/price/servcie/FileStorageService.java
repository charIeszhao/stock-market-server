package com.demo.stockmarket.price.servcie;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

public interface FileStorageService {

	public String storeFile(MultipartFile file);
	
	public Resource loadFileAsResource(String fileName);
}
