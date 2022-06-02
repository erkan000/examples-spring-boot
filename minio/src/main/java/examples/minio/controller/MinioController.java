package examples.minio.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import examples.minio.util.Utils;

@RestController
public class MinioController {

	@Autowired
	Utils utils;

	@GetMapping(path = "/download")
	public ResponseEntity<ByteArrayResource> uploadFile(@RequestParam(value = "file") String file) throws IOException {
		file = "testFileName.txt";
		ResponseEntity<ByteArrayResource> resp = null;
		try {
			byte[] data = utils.downloadFile(file);
			ByteArrayResource resource = new ByteArrayResource(data);
			resp = ResponseEntity
					.ok()
					.contentLength(data.length)
					.header("Content-type", "application/octet-stream")
					.header("Content-disposition", "attachment; filename=\"" + file + "\"")
					.body(resource);
		} catch (Exception e) {
			System.err.println(e);
		}
		return resp;
	}
	
	@PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Map<String, String> uploadFile(@RequestPart(value = "file", required = false) MultipartFile files) throws IOException {
		utils.uploadFile(files.getOriginalFilename(), files.getBytes());
        Map<String, String> result = new HashMap<>();
        result.put("key", files.getOriginalFilename());
        return result;
    }

}
