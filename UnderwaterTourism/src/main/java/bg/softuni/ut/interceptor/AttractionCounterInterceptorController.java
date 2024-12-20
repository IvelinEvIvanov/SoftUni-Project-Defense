package bg.softuni.ut.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttractionCounterInterceptorController {
	
	private final AttractionCounterInterceptorService myInterceptorService;

	public AttractionCounterInterceptorController(AttractionCounterInterceptorService myInterceptorService) {
		this.myInterceptorService = myInterceptorService;
	}
	
	@GetMapping("/interceptor/test")
	public String viewInterceptor(Model model) {
		
		model.addAttribute("counter", this.myInterceptorService.getMyInterceptorView());
		
		return "interceptor/my_interceptor";
	}
}
