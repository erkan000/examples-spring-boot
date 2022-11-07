package examples.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examples.springboot.entity.Invoice;
import examples.springboot.entity.pk.Pk;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Pk>{

//	Invoice findByDocument(String document);
}
