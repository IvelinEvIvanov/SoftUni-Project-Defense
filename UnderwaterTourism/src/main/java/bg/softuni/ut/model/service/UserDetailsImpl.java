package bg.softuni.ut.model.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import bg.softuni.ut.model.entity.UserEntity;

@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails {

	private UserEntity userEntity;

	public UserDetailsImpl(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> simpleGrantedAuthorities = this.userEntity.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());

		return simpleGrantedAuthorities;
	}

	@Override
	public String getPassword() {
		return this.userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return this.userEntity.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getFullName() {
		return userEntity.getFirstName() + " " + userEntity.getLastName();
	}

	public Long getUserId() {
		return this.userEntity.getId();
	}

}
