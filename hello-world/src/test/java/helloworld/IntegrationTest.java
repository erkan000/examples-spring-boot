package helloworld;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"DB_USER = myUser"})
@AutoConfigureMockMvc
class IntegrationTest {

	@Autowired
	private TestRestTemplate template;	
	
	@LocalServerPort
	private int randomPort;
	
	@Test
	void contextLoads() throws JsonMappingException, JsonProcessingException {
		String responseJson = template.getForObject("http://localhost:" + randomPort + "/api", String.class);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = mapper.readValue(responseJson, new TypeReference<Map<String,String>>() {});
		assertThat(map.get("env-variable")).isEqualTo("myUser");
	}

}

