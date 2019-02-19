package com.help.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.help.dao.HelpDao;
import com.help.dao.HelpRepo;
import com.help.dto.CharityDetails;
import com.help.exception.handling.FileStorageException;
import com.help.exception.handling.MyFileNotFoundException;
import com.help.util.FileStorageProperties;

@Service
public class HelpServiceImpl implements HelpService {

	private final Path fileStorageLocation;
	
	@Autowired
	HelpDao helpdao;
	
	@Autowired
	HelpRepo helpRepo;
	
	@Autowired
    public HelpServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

	@Override
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

	@Override
	public Integer save(CharityDetails charityDetails) throws Exception{
		// TODO Auto-generated method stub
	/*	List<Float> list = new ArrayList<Float>(charityDetails.getRatings());*/
		helpRepo.save(charityDetails);
		return 0;
	}

	@Override
    public List < CharityDetails > findAll() {
		
        return helpRepo.findAll();
    }
	@Override
    public List < CharityDetails > findByRating() {
	Sort sortDescending = new Sort(Direction.DESC, "ratings");
    return helpRepo.findAll(sortDescending);
	
	}
}
