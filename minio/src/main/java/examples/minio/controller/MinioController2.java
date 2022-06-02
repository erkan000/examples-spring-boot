package examples.minio.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import examples.minio.dto.Dto;
import examples.minio.util.Utils;

@RestController
public class MinioController2 {

	@Autowired
	Utils utils;

	@GetMapping(path = "/download2")
	public Dto uploadFile(@RequestParam(value = "file") String file) throws IOException {
		byte[] data = null;
		Dto dto = new Dto();
		try {
			data = utils.downloadFile(file);
			dto.setAdi(file);
			dto.setId(1);
			dto.setData(Base64.getEncoder().encodeToString(data));
		} catch (Exception e) {
			System.err.println(e);
			dto.setAdi(e.getMessage());
		}
		return dto;
	}
	
	@PostMapping(path = "/upload2", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Map<String, String> uploadFile(@RequestPart(value = "file", required = false) MultipartFile files) throws IOException {
		utils.uploadFile(files.getOriginalFilename(), files.getBytes());
        Map<String, String> result = new HashMap<>();
        result.put("key", files.getOriginalFilename());
        return result;
    }

}
