package examples.springboot.entity.pk;

import java.io.Serializable;

import examples.springboot.entity.Company;
import examples.springboot.entity.Document;

public class Pk implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4524961618090916348L;
	private Company company;
    private Document document;
    
	@Override
	public boolean equals(Object obj) {
		return company.getId().equals(document.getId());
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
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
