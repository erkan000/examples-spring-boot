package examples.springboot.entity.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Pk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -863165244111031984L;

	private PkDocument docId;

	private PkCompany comId;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pk other = (Pk) obj;
        return other.getComId().equals(comId.getCompanyId())
                && other.getDocId().equals(docId.getDocumentId());

	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	public PkDocument getDocId() {
		return docId;
	}
	public void setDocId(PkDocument docId) {
		this.docId = docId;
	}
	public PkCompany getComId() {
		return comId;
	}
	public void setComId(PkCompany comId) {
		this.comId = comId;
	}

	
}
