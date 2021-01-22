package sec.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationObjectController {

    @GetMapping("h1")
    public ModelAndView h1() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	User authPrincibal = (User) authentication.getPrincipal();
    	String username = authPrincibal.getUsername();
        ModelAndView model = new ModelAndView();
		model.addObject("message", username);
		model.setViewName("helloWorld");
        return model;
    }
    
    @GetMapping("h2")
    public ModelAndView h2(Principal princibal) {
    	User authPrincibal = (User) princibal;
    	String username = authPrincibal.getUsername();
        ModelAndView model = new ModelAndView();
		model.addObject("message", username);
		model.setViewName("helloWorld");
        return model;
    }

    @GetMapping("h3")
    public ModelAndView h3(Authentication authentication) {
    	User authPrincibal = (User) authentication.getPrincipal();
    	String username = authPrincibal.getUsername();
        ModelAndView model = new ModelAndView();
		model.addObject("message", username);
		model.setViewName("helloWorld");
        return model;
    }
    
    @GetMapping("h4")
    public ModelAndView h4(@AuthenticationPrincipal User authPrincibal) {
    	String username = authPrincibal.getUsername();
        ModelAndView model = new ModelAndView();
		model.addObject("message", username);
		model.setViewName("helloWorld");
        return model;
    }
}
