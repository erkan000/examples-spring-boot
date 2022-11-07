package examples.springboot.entity.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PkDocument implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3639103795708160779L;
	
	private String documentId;
	
	public PkDocument() {
	}
	
	public PkDocument(String id) {
		this.documentId = id;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}


}
