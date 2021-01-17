package sec.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MethodBasedAuthController {
    
    @Secured("ROLE_USER")
    @GetMapping("roleUser")
    public String roleUser() {
        return "roleUser";
    }    

    @Secured("ROLE_ADMIN")
    @GetMapping("roleAdmin")
    public String roleAdmin() {
        return "roleAdmin";
    }  
}
