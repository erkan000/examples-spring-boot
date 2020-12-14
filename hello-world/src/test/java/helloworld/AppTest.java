package helloworld;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import helloworld.controller.TestController;

@SpringBootTest
@TestPropertySource(properties = {"DB_USER = myUser"})
class AppTest {

	@Autowired
	private TestController controller;
	
	
	
	@Test
	void contextLoads() {
		assertThat(controller).isInstanceOf(TestController.class);
		System.out.println(controller.getStatus());
	}

}

