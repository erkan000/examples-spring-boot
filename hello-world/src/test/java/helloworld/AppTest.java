package helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"DB_USER = myUser"})
class AppTest {

	@Test
	void contextLoads() {
		
	}

}

