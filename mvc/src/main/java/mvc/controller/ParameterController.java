package mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import mvc.pojo.Person;

@Controller
@RequestMapping("/params")
public class ParameterController {

	@GetMapping("/getParam")
	public ModelAndView welcome(@RequestParam("name") String name, @RequestParam("surname") String surname) {
		return new ModelAndView("details", "ppp", new Person(name, 100));
	}
	
	@GetMapping(path = "/{name}")
	@ResponseStatus(code = HttpStatus.OK)
	public ModelAndView userById(@PathVariable("name") String name) {
		return new ModelAndView("details", "ppp", new Person(name, 32));
	}
}
