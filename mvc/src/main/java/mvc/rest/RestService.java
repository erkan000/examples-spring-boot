package mvc.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mvc.pojo.Person;

@RestController
@RequestMapping("/rest")
public class RestService {
	
//	http://localhost:8080/rest/person?name=Erkan
	@GetMapping("person")
	public Person getPerson(@RequestParam(value = "nam", defaultValue = "Erkan") String name,
						@RequestParam(value = "cit", defaultValue = "Ankara") String city) {
		Person p = new Person();
		p.setName(name);
		p.setCity(city);
		return p;	
	}
	
//	http://localhost:8080/rest/postRequestParam?nam=ss&cit=aa
	@PostMapping("postRequestParam")
	public Person postReq(@RequestParam("nam") String name,
						@RequestParam("cit") String city) {
		Person p = new Person();
		p.setName(name);
		p.setCity(city);
		return p;	
	}
	
//	{"id":0,"name":"test","birthDay":"2020-02-02","active":false,"city":""}
	@PostMapping("postJson")
	public Person save(@RequestBody Person u) {
		return u;
	}
	
//	http://localhost:8080/rest/variable/{name}/{id}
	@RequestMapping(path = "variable/{name}/{id}",method = RequestMethod.GET)
	public Person var(@PathVariable(value = "name") String name,
									@PathVariable(value = "id") int id) {		
		return new Person(name,id);
	}  

}
