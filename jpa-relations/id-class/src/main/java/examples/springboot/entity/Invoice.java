package examples.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import examples.springboot.entity.pk.Pk;

@Entity
@IdClass(Pk.class)
public class Invoice {	

	@Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Document document;

    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
	    @JoinColumn(name="company_id", referencedColumnName = "company_id" , insertable = false, updatable = false),
	    @JoinColumn(name="document_id", referencedColumnName = "document_id" ,insertable = false, updatable = false)
	})
    private Inbox inbox;

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	} 
	
}
