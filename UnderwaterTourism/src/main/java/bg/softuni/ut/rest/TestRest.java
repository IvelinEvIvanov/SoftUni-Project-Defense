package bg.softuni.ut.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TestRest {
	
	@GetMapping("/rest/test")
	public String testRest() {
		return "rest/rest_shark_cage_info";
	}
}
