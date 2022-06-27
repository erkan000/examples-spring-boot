package examples.minio.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import examples.minio.util.Utils;

@RestController
public class UploadController {

	@Autowired
	Utils utils;
	
	@PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Map<String, String> uploadFile(
    		@RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
		utils.uploadFile(file.getOriginalFilename(), file.getBytes());
        Map<String, String> result = new HashMap<>();
        result.put("key", file.getOriginalFilename());
        return result;
    }
	
	@PostMapping(path = "/uploadFileWithVariables", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Map<String, String> uploadFileWithVariables(
    		@RequestPart(value = "file", required = false) MultipartFile files,
    		@RequestParam(value = "param1") String param1,
    		@RequestParam(value = "param2") String param2) throws IOException {
		
		System.out.println("param1:" + param1);
		System.out.println("param2:" + param2);
		
		utils.uploadFile(files.getOriginalFilename(), files.getBytes());
        Map<String, String> result = new HashMap<>();
        result.put("key", files.getOriginalFilename());
        return result;
    }

}
