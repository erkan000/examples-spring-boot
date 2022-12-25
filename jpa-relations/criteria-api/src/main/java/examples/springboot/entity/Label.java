package examples.springboot.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Label {
	
	@Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID id;
	
	@Column(nullable = false, length = 30)
    private String name;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
