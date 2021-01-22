package sec.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MethodBasedAuthController {
    
    @Secured("ROLE_USER")
    @GetMapping("roleUser")
    public String roleUser() {
        return "roleUser";
    }    

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("roleAdmin")
    public String roleAdmin() {
        return "roleAdmin";
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("roleAdmin2")
    public String roleAdmin2() {
        return "roleAdmin";
    }  
}
