package examples.springboot.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import examples.springboot.entity.pk.Pk;

@Entity
public class Inbox implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5840253040546845066L;

	@EmbeddedId
    private Pk id;
	
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "inbox")
    private Invoice invoice;

	public Pk getId() {
		return id;
	}

	public void setId(Pk id) {
		this.id = id;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}
