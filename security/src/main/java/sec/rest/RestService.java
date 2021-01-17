package sec.rest;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestService {

	@GetMapping("getLoggedInUser")
	public Principal principal(Principal principal) {
		return principal;
	}
	
	@GetMapping("testUser")
	public String portfolioPositions(@AuthenticationPrincipal Principal principal) {
		String username = principal.getName();
		return "You are " + username;
	}
}
