package helloworld;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import helloworld.controller.TestController;

@WebMvcTest(TestController.class)
@TestPropertySource(properties = {"DB_USER = myUser"})
class UnitTest {

	@Autowired
	private MockMvc mock;
	
	@Test
	void contextLoads() throws Exception {
		
		this.mock.perform(MockMvcRequestBuilders.get("/api")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.env-variable").exists())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}

}

