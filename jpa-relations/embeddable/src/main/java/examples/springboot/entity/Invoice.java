package examples.springboot.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import examples.springboot.entity.pk.Pk;

@Entity
public class Invoice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7762258000798524901L;

	@EmbeddedId
	private Pk id;

	@MapsId("Invoice")
	@JoinColumns({
		@JoinColumn(name="company_id", referencedColumnName = "company_id" ),
		@JoinColumn(name="document_id", referencedColumnName = "document_id" )
	})
	@OneToOne(fetch = FetchType.LAZY)
	private Inbox inbox;

	public Pk getId() {
		return id;
	}

	public void setId(Pk id) {
		this.id = id;
	}

	public Inbox getInbox() {
		return inbox;
	}

	public void setInbox(Inbox inbox) {
		this.inbox = inbox;
	}

}
