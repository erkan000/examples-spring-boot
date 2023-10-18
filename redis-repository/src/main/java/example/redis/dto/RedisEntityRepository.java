package example.redis.dto;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface RedisEntityRepository
		extends CrudRepository<RedisEntity, String>, QueryByExampleExecutor<RedisEntity> {

	List<RedisEntity> findAllByBarkodLike(String ad);

}
