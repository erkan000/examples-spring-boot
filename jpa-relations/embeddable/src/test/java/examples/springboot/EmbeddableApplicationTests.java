package examples.springboot;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import examples.springboot.entity.Company;
import examples.springboot.entity.Document;
import examples.springboot.entity.Inbox;
import examples.springboot.entity.Invoice;
import examples.springboot.entity.pk.Pk;
import examples.springboot.entity.pk.PkCompany;
import examples.springboot.entity.pk.PkDocument;
import examples.springboot.repo.CompanyRepository;
import examples.springboot.repo.DocumentRepository;
import examples.springboot.repo.InboxRepository;
import examples.springboot.repo.InvoiceRepository;

@SpringBootTest
class EmbeddableApplicationTests {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private InboxRepository inboxRepository;
	
	private Pk primaryKey;
	
	@BeforeEach
	void setup() {
		List<Document> list1 = new ArrayList<>();
		list1.add(new Document("doc1"));
		list1.add(new Document("doc2"));
		documentRepository.saveAll(list1);
		
		List<Company> list2 = new ArrayList<>();
		list2.add(new Company("com1"));
		list2.add(new Company("com2"));
		list2.add(new Company("com3"));
		companyRepository.saveAll(list2);
		
		primaryKey = new Pk();
		primaryKey.setComId(new PkCompany("com1"));
		primaryKey.setDocId(new PkDocument("doc1"));
		
		Inbox inbox = new Inbox();
		inbox.setId(primaryKey);
		inboxRepository.save(inbox);
		
		Invoice invoice = new Invoice();
		invoice.setId(primaryKey);
		invoice.setInbox(inbox);
		invoiceRepository.save(invoice);
	}

	@Test
	void inboxRepositoryTest() {
		Inbox inbox = inboxRepository.findById(primaryKey).get();
		Assertions.assertEquals(inbox.getId().getComId(), primaryKey.getComId());
		Assertions.assertEquals(inbox.getId().getDocId(), primaryKey.getDocId());
	}
	
	@Test
	void invoiceRepositoryTest() {		
		Invoice invoice = invoiceRepository.findById(primaryKey).get();
		Assertions.assertEquals(invoice.getId().getComId(), primaryKey.getComId());
		Assertions.assertEquals(invoice.getId().getDocId(), primaryKey.getDocId());
	}


}
