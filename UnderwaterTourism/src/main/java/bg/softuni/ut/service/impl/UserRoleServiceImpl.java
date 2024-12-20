package bg.softuni.ut.service.impl;

import org.springframework.stereotype.Service;

import bg.softuni.ut.model.entity.UserRoleEntity;
import bg.softuni.ut.model.entity.enums.UserRoleEnum;
import bg.softuni.ut.repository.UserRoleRepository;
import bg.softuni.ut.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	private final UserRoleRepository userRoleRepository;

	public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public UserRoleEntity findByRole(UserRoleEnum role) {
		return this.userRoleRepository.findByRole(role);
	}

}
