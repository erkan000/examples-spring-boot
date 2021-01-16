package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mvc.pojo.Person;

@Controller
@RequestMapping("/model")
public class ModelView {

	@GetMapping("detailsModelAndView")
	public ModelAndView getEmployeeByObject() {
		return new ModelAndView("details", "ppp", getPerson());
	}
	
	@GetMapping("detailsByModel")
	public String getEmployeeByObject(Model model) {
		model.addAttribute("ppp", getPerson());
		return "details";
	}
	
	private Person getPerson() {
		Person p = new Person();
		p.setName("Erkan");
		p.setId(12);
		p.setCity("Ankara");
		return p;
	}
}
