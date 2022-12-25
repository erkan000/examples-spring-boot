package examples.springboot.search;

import java.util.UUID;

import examples.springboot.entity.Label;
import examples.springboot.entity.Technology;

public class ResponseDTO {
	
	// TechnologyLabel
	private UUID id;	
	private String userName;

	// Label
	private UUID labelId;	
	private String labelName;
	
	// Technology
	private UUID techId;
    private byte rating;    
    private String techName;
    private String note;
    private String homeUrl;
    private String wikiUrl;
    
    public void setLabel(Label label) {
    	setLabelId(label.getId());
    	setLabelName(label.getName());
    }
    
    public void setTechnology(Technology tech) {
    	setTechId(tech.getId());
    	setRating(tech.getRating());
    	setTechName(tech.getTechnologyName());
    	setNote(tech.getNote());
    	setHomeUrl(tech.getHomeUrl());
    	setWikiUrl(tech.getWikiUrl());
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

	public String getTechName() {
		return techName;
	}

	public void setTechName(String techName) {
		this.techName = techName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getLabelId() {
		return labelId;
	}

	public void setLabelId(UUID labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public UUID getTechId() {
		return techId;
	}

	public void setTechId(UUID techId) {
		this.techId = techId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	public String getWikiUrl() {
		return wikiUrl;
	}

	public void setWikiUrl(String wikiUrl) {
		this.wikiUrl = wikiUrl;
	}
}
