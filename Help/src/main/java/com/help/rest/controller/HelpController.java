package com.help.rest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.help.dto.CharityDetails;
import com.help.dto.CharityDetailsResponse;
import com.help.dto.UploadFileResponse;
import com.help.service.HelpService;

@RestController
public class HelpController {

	private static final Logger logger = LoggerFactory.getLogger(HelpController.class);

	@Autowired
	HelpService helpService;

	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = helpService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();

		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	@PostMapping("/uploadMultipleFiles")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = helpService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	
	/**@author chaitra.honnur 
	 * 
	 * @param charityDetails
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public CharityDetailsResponse save(@RequestBody CharityDetails charityDetails) {
		try {
			Integer statusCode = helpService.save(charityDetails);
			if (statusCode == 0)
				return new CharityDetailsResponse("Success", 200);
			else
				return new CharityDetailsResponse("Oops!! we are trying to fix the issue !! please try again", 417);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new CharityDetailsResponse("Internal Server Error", 500);
		}

	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getcharitydetails")
    public Iterable<CharityDetails> getDetails() {
		try {
			List<CharityDetails> list =helpService.findAll();
			if(list != null && !list.isEmpty())
				return list;
			else
				return null;
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return (Iterable<CharityDetails>) new CharityDetailsResponse("Internal Server Error", 500);
		}
	
    }
	
	
	@RequestMapping(method=RequestMethod.GET, value="/getTopTenCharities")
    public Iterable<CharityDetails> gettoptenimages() {
		try {
		List<CharityDetails> list=helpService.findByRating().subList(0, 10);
		
		if(list != null && !list.isEmpty())
		return list;
		else 
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return (Iterable<CharityDetails>) new CharityDetailsResponse("Internal Server Error", 500);
		}
		
	
	}
}
