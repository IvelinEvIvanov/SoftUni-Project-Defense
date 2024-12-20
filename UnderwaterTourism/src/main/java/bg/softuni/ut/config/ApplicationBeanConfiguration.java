package bg.softuni.ut.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.context.annotation.SessionScope;

import bg.softuni.ut.model.service.UserDetailsImpl;
import bg.softuni.ut.repository.UserRepository;
import bg.softuni.ut.service.UserService;
import bg.softuni.ut.service.impl.UserDetailsServiceImpl;
import bg.softuni.ut.service.impl.UserServiceIml;

@Configuration
public class ApplicationBeanConfiguration {

	@Bean
	public ModelMapper modelMapper() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		ModelMapper modelMapper = new ModelMapper();

		Converter<String, LocalDate> toLocalDate = new Converter<String, LocalDate>() {

			@Override
			public LocalDate convert(MappingContext<String, LocalDate> context) {
				if (context.getSource() != null) {
					return LocalDate.parse(context.getSource(), formatter);
				}

				return null;
			}
		};

		modelMapper.addConverter(toLocalDate);

		return modelMapper;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new Pbkdf2PasswordEncoder();
	}
	
	@Bean
	@SessionScope
	public UserDetailsImpl getUserDetailsImpl() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetailsImpl= (UserDetailsImpl) authentication.getPrincipal();
		
		return userDetailsImpl;
	}

}
