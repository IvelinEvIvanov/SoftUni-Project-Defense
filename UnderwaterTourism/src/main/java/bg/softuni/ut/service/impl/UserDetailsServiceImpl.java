package bg.softuni.ut.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bg.softuni.ut.model.entity.UserEntity;
import bg.softuni.ut.model.service.UserDetailsImpl;
import bg.softuni.ut.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;

	public UserDetailsServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity=this.userService.findByEmail(email);
		if(userEntity==null) {
			throw new UsernameNotFoundException("Email dos not exists!");
		}
		
		return new UserDetailsImpl(userEntity);
	}

}
