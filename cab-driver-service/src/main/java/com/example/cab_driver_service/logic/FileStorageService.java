package com.example.cab_driver_service.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService{

	private final Path fileStorageLocation;//Files save aagura folder path store panna variable

	public FileStorageService()throws IOException{
		this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation);//project root-la uploads folder//full system path
	}

	//Save files

	public String storeFile(MultipartFile file)throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());//s- We used StringUtils.isBlank(filename) to quickly validate uploaded filenames. இது null, empty, whitespace எல்லாம் handle பண்ணும்
        Path targetLocation = this.fileStorageLocation.resolve(fileName);//original file name replace agum
        file.transferTo(targetLocation);//folderyum file combine aggum//
        return fileName;//filename call pannanum
    }

}
