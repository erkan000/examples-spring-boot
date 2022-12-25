package examples.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import examples.springboot.entity.Label;
import examples.springboot.repo.LabelRepository;

@SpringBootTest
class CriteriaApiApplicationTests {
	
	@Autowired
	private LabelRepository labelRepository;
	
	@BeforeEach
	void setup() {	
		
		Label inbox = new Label();
		inbox.setName("my Label 4");
		labelRepository.save(inbox);
		
	}

	@Test
	void inboxRepositoryTest() {
//		Label inbox = labelRepository.findById(new).get();
//		Assertions.assertEquals(inbox.getId().getComId(), primaryKey.getComId());
//		Assertions.assertEquals(inbox.getId().getDocId(), primaryKey.getDocId());
	}
	


}
