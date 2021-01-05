package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mvc.pojo.Person;

import java.util.Map;

@Controller
public class FirstController {

    @GetMapping("hello")
    public String hello(Map<String, Object> model) {
        model.put("message", "Hello World!");
        return "helloWorld";
    }    
    
    @GetMapping("postData")
    public String post1(@ModelAttribute ("per") Person person) {
        return "form";
    }
    
    @PostMapping("postData")
    public String post2(@ModelAttribute ("per") Person person) {
    	System.out.println(person.getName());
//        return "redirect:postData";
    	return "form";
    }
    
    @GetMapping("i18n")
    public String i18n() {
        return "i18n";
    }
}
