package example.redis.rest;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import example.redis.dto.RedisEntity;
import example.redis.dto.RedisEntityRepository;

@RestController
public class RedisController {
	
	@Autowired
	private RedisEntityRepository repo;
	
	@GetMapping("generate")
	@ResponseBody
	public String sendEvent() {
		RedisEntity entity = new RedisEntity();
		entity.setAd("erkan");
		entity.setBarkod(String.valueOf(new Random().nextInt(100)));
		RedisEntity dbEntity = repo.save(entity);
		return "ID: " + dbEntity.getId();
	}

	@GetMapping("get/{id}")
	@ResponseBody
	public String get(@PathVariable String id) {
		Optional<RedisEntity> dbEntity = repo.findById(id);
		if (dbEntity.isEmpty()) {
			return "Not Found";
		} else {
			return dbEntity.toString();
		}
	}

	@GetMapping("search/{text}")
	@ResponseBody
	public String search(@PathVariable String text) {
		List<RedisEntity> dbEntity = repo.findAllByBarkodLike(text);
		if (dbEntity.isEmpty()) {
			return "Not Found";
		} else {
			StringJoiner jj = new StringJoiner("\r\n");
			dbEntity.forEach(x -> jj.add(x.toString()));
			return jj.toString();
		}
	}

	@GetMapping("fulltext/{text}")
	@ResponseBody
	public String fulltext(@PathVariable String text) {

		ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("barkod", ExampleMatcher.GenericPropertyMatchers.contains());

		RedisEntity aaa = new RedisEntity();
		aaa.setBarkod(text);
		Example<RedisEntity> example = Example.of(new RedisEntity(), customExampleMatcher);

		Iterable<RedisEntity> dbEntity = repo.findAll(example);
		

			StringJoiner jj = new StringJoiner("\r\n");
			dbEntity.forEach(x -> jj.add(x.toString()));
			return jj.toString();

	}


}
