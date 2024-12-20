package bg.softuni.ut.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import bg.softuni.ut.model.entity.enums.UserRoleEnum;

@Configuration
//@EnableWebSecurity
public class ApplicationSecurityConfiguration { // extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	public ApplicationSecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder);
	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/css/**", "/images/**", "/js/**") // .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .permitAll()
                .antMatchers("/admin/**")
                .hasAnyAuthority(UserRoleEnum.ADMIN.name())
                .antMatchers("/", "/user/login", "/user/register", "/attractions/**", "/shark-cage/**", "/rest/**","/intc/test")
                .permitAll()
                .antMatchers("/**")
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureForwardUrl("/user/login-error")
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        return http.build();
    }

}
