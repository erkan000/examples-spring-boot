package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/red")
public class Redirect {
	
	@GetMapping("redirect")
	public RedirectView gotoGoogle() {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("https://www.google.com");
		return redirectView;
	}
	
	@GetMapping("redirectView")
	public ModelAndView gotoGoogle2() {
		return new ModelAndView("redirect:https://www.google.com");
	}

}
