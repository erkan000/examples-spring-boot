package mvc.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mvc.pojo.Person;

@RestController
public class RestService {
	
	@GetMapping("/person")
	public Person getPerson(@RequestParam(value = "nam", defaultValue = "Erkan") String name,
						@RequestParam(value = "cit", defaultValue = "Ankara") String city) {
		Person p = new Person();
		p.setName(name);
		p.setCity(city);
		return p;	
	}

}
