package com.help.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.help.dto.CharityDetails;

public interface HelpService {

	String storeFile(MultipartFile file);
	
	Resource loadFileAsResource(String fileName);
	
	Integer save(CharityDetails charityDetails) throws Exception;
}
