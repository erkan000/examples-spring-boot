package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import mvc.pojo.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("hello")
    public String hello(Map<String, Object> model) {
        model.put("message", "Hello World!");
        return "helloWorld";
    }    
    
    @GetMapping("postData")
    public String getPage(@ModelAttribute ("per") Person person) {
        return "form";
    }
    
    @PostMapping("addme")
    public String addPage(@ModelAttribute ("per") Person person) {
    	System.out.println(person.getName());
    	return "form";
    }
    
    @GetMapping("i18n")
    public String i18n() {
        return "i18n";
    }
    
	@GetMapping(path = "json", produces = "application/json")
	@ResponseBody
	public List<Person> listUsers(Model model) {
		List<Person> list = new ArrayList<>();
		list.add(new Person("Erkan", 12));
		list.add(new Person("Nurdan", 24));
		return list;
	}
}
