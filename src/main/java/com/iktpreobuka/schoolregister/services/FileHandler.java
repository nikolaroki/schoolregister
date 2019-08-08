package com.iktpreobuka.schoolregister.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


public interface FileHandler {

	public String singleFileUpload(MultipartFile file) throws IOException;

}
