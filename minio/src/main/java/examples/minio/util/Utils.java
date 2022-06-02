package examples.minio.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import examples.minio.config.MinioConfig;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.MinioException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

@Configuration
public class Utils {
	
	@Autowired
	MinioConfig conf;

	public void checkBucket(MinioClient minioClient) throws MinioException, IOException, InvalidKeyException, NoSuchAlgorithmException {
		
		boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(MinioConfig.minioBucketName).build());
		if (!found) {
			logger.info("Bucket doesnt exists, creating new one.");
			minioClient.makeBucket(MakeBucketArgs.builder().bucket(MinioConfig.minioBucketName).build());
		} else {
			logger.info("Bucket already exists.");
		}
		
	}
	
	public byte[] downloadFile(String fileName) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException {
		GetObjectArgs object = GetObjectArgs.builder()
				.bucket(MinioConfig.minioBucketName)
				.object(fileName)
				.build();
		
		GetObjectResponse response = conf.getClient().getObject(object);
		byte[] content = IOUtils.toByteArray(response);
		response.close();
		return content;
	}
	
	 public void uploadFile(String name, byte[] content) {
	        File file = new File("/tmp/" + name);
	        file.canWrite();
	        file.canRead();
	        try {
	            FileOutputStream iofs = new FileOutputStream(file);
	            iofs.write(content);
	            PutObjectArgs object = PutObjectArgs.builder()
	            		.bucket(MinioConfig.minioBucketName)
	    				.object(name)
	    				.build();
				conf.getClient().putObject(object);
				iofs.close();
	        } catch (Exception e) {
	           throw new RuntimeException(e.getMessage());
	        }

	    }
	
	private Logger logger = LoggerFactory.getLogger(Utils.class);

}
