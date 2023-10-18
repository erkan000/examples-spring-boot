package example.redis.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("redisEntity")
public class RedisEntity {

	@Id
	private String id;
	@Indexed
	private String barkod;
	private String ad;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBarkod() {
		return barkod;
	}

	public void setBarkod(String barkod) {
		this.barkod = barkod;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	@Override
	public String toString() {
		return "Name: " + getAd() + "  Barkod: " + getBarkod();
	}

}