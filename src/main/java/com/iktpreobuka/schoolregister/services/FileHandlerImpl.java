package com.iktpreobuka.schoolregister.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileHandlerImpl implements FileHandler{
	
	//Save the uploaded file to this folder
		private static String UPLOADED_FOLDER = "C:\\temp\\";
		//private static String LOG_PATH = "C:\\Users\\Kovacic\\Documents\\workspace1\\school_register\\logs";
		
		@Override
		public String singleFileUpload(MultipartFile file) throws IOException {
			if (file.isEmpty()) {
				return null;
			}
			try {
				// Get the file and save it somewhere
				byte[] bytes = file.getBytes();
				Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
				Files.write(path, bytes);
				return path.toString();
			} catch (IOException e) {
				throw e;
			}
		}
		


}
