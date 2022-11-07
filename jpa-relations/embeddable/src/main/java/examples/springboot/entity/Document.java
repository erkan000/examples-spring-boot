package examples.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Document{
	
	@Id
    private String id;
	
	public Document(String id) {
		this.id = id;
	}
	public Document() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
