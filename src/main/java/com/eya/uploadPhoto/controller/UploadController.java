package com.eya.uploadPhoto.controller;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
public class UploadController {
	public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "\\uploads";

	@GetMapping("/uploadimage") public String displayUploadForm() {
		return "imageupload/index";
	}

	@PostMapping(value ="/upload",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String uploadImage(Model model, @RequestPart(required = true) MultipartFile file)
			throws IOException {
		StringBuilder fileNames = new StringBuilder();
		Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
		fileNames.append(file.getOriginalFilename());
		Files.write(fileNameAndPath, file.getBytes());
		model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
		return "imageupload/index";
	}
}
