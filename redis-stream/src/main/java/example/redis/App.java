package example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class App implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public void run(String... args) throws Exception {
		// create stream on REDIS
		try {
			  redisTemplate.getConnectionFactory().getConnection().xGroupCreate(
			      "test-stream".getBytes(),
			      "test-stream",
			      ReadOffset.from("0-0"),
			      true // this is important. It's to execute MKSTREAM command from redis
			           // only available from 2.3.0.RELEASE and above
			  );
			} catch (RedisSystemException e) {
			  // your exception handling
			  // getConnection() can also throw NullPointerException
			}
		
	}

}
