package examples.minio.config;

import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class MinioConfig {

	public static final String minioUrl = "http://localhost:9000";
	public static final String minioUser = "minioadmin";
	public static final String minioPass = "minioadmin";
	public static final String minioBucketName = "kovam";

	public MinioClient getClient() {
		return MinioClient.builder()
				.endpoint(minioUrl)
				.credentials(minioUser, minioPass)
				.build();
	}
	
}
