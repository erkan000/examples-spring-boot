package mvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import mvc.pojo.Person;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class IntegrationTest {

	private static JSONObject personJsonObject;

	private static HttpHeaders headers;

	@Autowired
	private TestRestTemplate template;	
	
	@LocalServerPort
	private int randomPort;
	
	@BeforeAll
	public static void runBeforeAllTestMethods() throws JSONException {
	    headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    personJsonObject = new JSONObject();
	    personJsonObject.put("id", 1);
	    personJsonObject.put("name", "Erkan");
	    personJsonObject.put("city", "Ankara");
	}
	
	@Test
	void postJson() throws JsonMappingException, JsonProcessingException {
		HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);		
		Person p = template.postForObject("http://localhost:" + randomPort + "/rest/postJson", request, Person.class);
		assertEquals("Erkan", p.getName());
		assertEquals(1, p.getId());
		assertEquals("Ankara", p.getCity());
	}
	
	@Test
	void postParam() throws JsonMappingException, JsonProcessingException {		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + randomPort + "/rest/postRequestParam")
		        .queryParam("nam", "Erkan")
		        .queryParam("cit", "Ankara");
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<Person> response = template.exchange(
		        builder.build().encode().toUri(), 
		        HttpMethod.POST, 
		        entity, 
		        Person.class);
		Person p = response.getBody();		
		assertEquals("Erkan", p.getName());
		assertEquals("Ankara", p.getCity());
	}
	
	@Test
	void postParamr() throws JsonMappingException, JsonProcessingException {
		 Map<String, String> params = new HashMap<>();
		    params.put("name", "Erkan");
		    params.put("id", "1");
		Person p = template.getForObject("http://localhost:" + randomPort + "/rest/variable/{name}/{id}", Person.class,params);	
		assertEquals("Erkan", p.getName());
		assertEquals(1, p.getId());
	}

}

