package example.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableCaching
@EnableRedisRepositories
public class RedisConfig {
	
	@Autowired
	private RedisProperties redisProperties;

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {

		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
				redisProperties.getHost(), redisProperties.getPort());

		redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());
		redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
		redisStandaloneConfiguration.setUsername(redisProperties.getUsername());

		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

}
