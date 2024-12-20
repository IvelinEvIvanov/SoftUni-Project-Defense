package bg.softuni.ut.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import bg.softuni.ut.interceptor.AttractionCounterInterceptor;

@Configuration
public class WebConfiguration  implements WebMvcConfigurer{
	
	private final AttractionCounterInterceptor myInterceptor;

	public WebConfiguration(AttractionCounterInterceptor myInterceptor) {
		this.myInterceptor = myInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myInterceptor).addPathPatterns("/attractions/Shark Cage Dive/details");
	}
	
	
	
}
