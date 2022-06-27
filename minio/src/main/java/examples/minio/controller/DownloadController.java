package examples.minio.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import examples.minio.dto.Dto;
import examples.minio.util.Utils;

@RestController
public class DownloadController {

	@Autowired
	Utils utils;

	@GetMapping(path = "/download")
	public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam(value = "file") String file) throws IOException {
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
	
	@GetMapping(path = "/downloadBase64")
	public Dto downloadFileBase64(@RequestParam(value = "file") String file) throws IOException {
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

}
