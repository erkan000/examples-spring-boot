package example.joinfaces;

import java.io.Serializable;

import javax.faces.view.ViewScoped;

import org.springframework.stereotype.Component;

@Component
@ViewScoped
public class WelcomeBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7375429851320751202L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

}

