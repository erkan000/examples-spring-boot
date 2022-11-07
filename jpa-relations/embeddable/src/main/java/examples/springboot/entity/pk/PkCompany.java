package examples.springboot.entity.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PkCompany implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3639103795708160779L;

	private String companyId;
	
	public PkCompany() {
	}
	
	public PkCompany(String id) {
		this.companyId = id;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
