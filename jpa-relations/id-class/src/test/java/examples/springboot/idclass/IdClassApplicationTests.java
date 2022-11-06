package examples.springboot.idclass;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import examples.springboot.entity.Company;
import examples.springboot.entity.Document;
import examples.springboot.entity.Inbox;
import examples.springboot.entity.Invoice;
import examples.springboot.entity.pk.Pk;
import examples.springboot.repo.CompanyRepository;
import examples.springboot.repo.DocumentRepository;
import examples.springboot.repo.InboxRepository;
import examples.springboot.repo.InvoiceRepository;

@SpringBootTest
class IdClassApplicationTests {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private InboxRepository inboxRepository;
	
	private Company company;
	private Document document;
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
		
		Inbox inbox = new Inbox();
		inbox.setCompany(new Company("com1"));
		inbox.setDocument(new Document("doc1"));
		inboxRepository.save(inbox);
		
		Invoice invoice = new Invoice();
		invoice.setCompany(new Company("com1"));
		invoice.setDocument(new Document("doc1"));
		invoiceRepository.save(invoice);
		
		company = new Company("com1");
		document = new Document("doc1");
		
		primaryKey = new Pk();
		primaryKey.setCompany(company);
		primaryKey.setDocument(document);
	}

	@Test
	void inboxRepositoryTest() {
		Inbox inbox = inboxRepository.findById(primaryKey).get();
		Assertions.assertEquals(company.getId(), inbox.getCompany().getId());
		Assertions.assertEquals(document.getId(), inbox.getDocument().getId());
	}
	
	@Test
	void invoiceRepositoryTest() {		
		Invoice invoice = invoiceRepository.findById(primaryKey).get();
		Assertions.assertEquals(company.getId(), invoice.getCompany().getId());
		Assertions.assertEquals(document.getId(), invoice.getDocument().getId());
	}

	@Test
	void whenInExsitingInbox_throwError_onInvoiceSave() {		
		Invoice entity = new Invoice();
		entity.setCompany(new Company("com2"));
		entity.setDocument(new Document("doc2"));
		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			invoiceRepository.save(entity);
	    });
		String expectedMessage = "could not execute statement";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void onInvoiceSave() {		
		Invoice entity = new Invoice();
		entity.setCompany(new Company("com1"));
		entity.setDocument(new Document("doc2"));
		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			invoiceRepository.save(entity);
	    });
		String expectedMessage = "could not execute statement";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
}
