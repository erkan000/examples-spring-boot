package examples.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Company{
	
	@Id
    private String id;	
	
	public Company(String id) {
		this.id = id;
	}
	public Company() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
