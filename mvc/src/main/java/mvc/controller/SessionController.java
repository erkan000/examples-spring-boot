package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mvc.pojo.Person;

@Controller
@SessionAttributes("jspModel")
@RequestMapping("/session")
public class SessionController {
	
	@GetMapping("session")
	public ModelAndView welcome() {
		return new ModelAndView("session", "model", "First page GET req");
	}

//	@GetMapping(value = "getModel")
//	public String getMetodu(@ModelAttribute("jspModel") Person model) {
//		return "session";
//	}

	@PostMapping(value = "setModel")
	public String setMetodu(@ModelAttribute("jspModel") Person model) {
		return "session";
	}
	
	@ModelAttribute("jspModel")
	public Person setup() {
		return new Person("Init jspModel", 85);
	}
	
	@GetMapping(value = "helloParam")
	public ModelAndView requestParam(@RequestParam("na") String name, @RequestParam("id") int id) {
		String message = "You sent " + name + " as name and " + id + " as id";
		return new ModelAndView("session", "model", message);
	}
	

}
