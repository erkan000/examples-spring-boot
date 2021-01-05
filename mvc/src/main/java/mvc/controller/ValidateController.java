package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mvc.pojo.Car;

import javax.validation.Valid;

@Controller
public class ValidateController {    
    
    @GetMapping("validate")
    public String validateForGet(@ModelAttribute ("cr") Car person) {
        return "control";
    }
    
    @PostMapping("validate")
    public String validateForPost(@Valid @ModelAttribute ("cr") Car c, BindingResult result) {
    	if(result.hasErrors()) {
            return "control";
        }
    	return "redirect:validate";
    }
    
}
