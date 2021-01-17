package sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FirstController {

    @GetMapping("hello")
    public ModelAndView hello() {
        ModelAndView model = new ModelAndView();
		model.addObject("message", "Erkan");
		model.setViewName("helloWorld");
        return model;
    }

}
