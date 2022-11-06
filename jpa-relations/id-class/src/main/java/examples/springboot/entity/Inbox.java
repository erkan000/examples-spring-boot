package examples.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import examples.springboot.entity.pk.Pk;

@Entity
@IdClass(Pk.class)
public class Inbox {

	@Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Document document;

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
