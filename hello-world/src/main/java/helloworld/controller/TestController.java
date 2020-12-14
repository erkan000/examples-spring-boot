package helloworld.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {
	
	@Value("${app.version}")
    private String appVersion;
	
	@Value("${datasource.user}")
    private String dbUser;

    @GetMapping
    public Map<String, String> getStatus() {
    	Map<String, String> map = new HashMap<String, String>();
        map.put("version", appVersion);
        map.put("env-variable", dbUser);
        return map;
    }

}
