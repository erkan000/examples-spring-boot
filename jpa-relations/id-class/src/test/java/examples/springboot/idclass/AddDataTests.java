package examples.springboot.idclass;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import examples.springboot.entity.Company;
import examples.springboot.entity.Document;
import examples.springboot.entity.Inbox;
import examples.springboot.repo.CompanyRepository;
import examples.springboot.repo.DocumentRepository;
import examples.springboot.repo.InboxRepository;

@SpringBootTest
class AddDataTests {
	
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private InboxRepository inboxRepository;

	@Test
	void inboxRepositoryTest() {
		
		List<Document> list1 = new ArrayList<>();
		list1.add(new Document("doc1"));
		list1.add(new Document("doc2"));
		documentRepository.saveAll(list1);
		
		List<Company> list2 = new ArrayList<>();
		list2.add(new Company("com1"));
		list2.add(new Company("com2"));
		list2.add(new Company("com3"));
		companyRepository.saveAll(list2);
		
		Inbox inbox = new Inbox();
		inbox.setCompany(new Company("com1"));
		inbox.setDocument(new Document("doc1"));
		inboxRepository.save(inbox);
		
	}
}
