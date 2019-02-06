package net.mo.ChocolateShopFrontEnd.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
    
	private static final String ABS_PATH = "D:\\programeJavaEE\\JavaChocolateShop\\ChocolateShopFrontEnd\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH = "";
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
	
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		 
		//get the real path
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		
		logger.info(REAL_PATH);
		
		//make sure the directory exists, if not create it
		if(!new File(ABS_PATH).exists()) {
			new File(ABS_PATH).mkdirs();
		}
		if(!new File(REAL_PATH).exists()) {
			new File(REAL_PATH).mkdirs();
		}
		
		try {
			//server upload
			file.transferTo(new File(REAL_PATH + code + "." + FilenameUtils.getExtension(file.getOriginalFilename()) ));
			//project directory
			file.transferTo(new File(ABS_PATH + code + "." + FilenameUtils.getExtension(file.getOriginalFilename()) ));
		}
		catch(IOException ex) {
			
		}
	}
}
